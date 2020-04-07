package com.adjust.sdk;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Process;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class ActivityHandler implements IActivityHandler {
    private static final String ACTIVITY_STATE_NAME = "Activity state";
    private static final String ATTRIBUTION_NAME = "Attribution";
    private static long BACKGROUND_TIMER_INTERVAL = 0;
    private static final String BACKGROUND_TIMER_NAME = "Background timer";
    private static final String DELAY_START_TIMER_NAME = "Delay Start timer";
    private static long FOREGROUND_TIMER_INTERVAL = 0;
    private static final String FOREGROUND_TIMER_NAME = "Foreground timer";
    private static long FOREGROUND_TIMER_START = 0;
    private static final String SESSION_CALLBACK_PARAMETERS_NAME = "Session Callback parameters";
    private static long SESSION_INTERVAL = 0;
    private static final String SESSION_PARAMETERS_NAME = "Session parameters";
    private static final String SESSION_PARTNER_PARAMETERS_NAME = "Session Partner parameters";
    private static long SUBSESSION_INTERVAL = 0;
    private static final String TIME_TRAVEL = "Time travel!";
    private ActivityState activityState;
    /* access modifiers changed from: private */
    public AdjustConfig adjustConfig;
    /* access modifiers changed from: private */
    public AdjustAttribution attribution;
    private IAttributionHandler attributionHandler;
    private TimerOnce backgroundTimer;
    private String basePath;
    private TimerOnce delayStartTimer;
    private DeviceInfo deviceInfo;
    private TimerCycle foregroundTimer;
    private InstallReferrer installReferrer;
    /* access modifiers changed from: private */
    public InternalState internalState;
    /* access modifiers changed from: private */
    public ILogger logger = AdjustFactory.getLogger();
    private IPackageHandler packageHandler;
    private CustomScheduledExecutor scheduledExecutor;
    private ISdkClickHandler sdkClickHandler;
    private SessionParameters sessionParameters;

    public class InternalState {
        boolean background;
        boolean delayStart;
        boolean enabled;
        boolean firstLaunch;
        boolean firstSdkStart;
        boolean offline;
        boolean sessionResponseProcessed;
        boolean updatePackages;

        public InternalState() {
        }

        public boolean isEnabled() {
            return this.enabled;
        }

        public boolean isDisabled() {
            return !this.enabled;
        }

        public boolean isOffline() {
            return this.offline;
        }

        public boolean isOnline() {
            return !this.offline;
        }

        public boolean isInBackground() {
            return this.background;
        }

        public boolean isInForeground() {
            return !this.background;
        }

        public boolean isInDelayedStart() {
            return this.delayStart;
        }

        public boolean isNotInDelayedStart() {
            return !this.delayStart;
        }

        public boolean itHasToUpdatePackages() {
            return this.updatePackages;
        }

        public boolean isFirstLaunch() {
            return this.firstLaunch;
        }

        public boolean isNotFirstLaunch() {
            return !this.firstLaunch;
        }

        public boolean hasSessionResponseNotBeenProcessed() {
            return !this.sessionResponseProcessed;
        }

        public boolean hasFirstSdkStartOcurred() {
            return this.firstSdkStart;
        }

        public boolean hasFirstSdkStartNotOcurred() {
            return !this.firstSdkStart;
        }
    }

    public void teardown() {
        if (this.backgroundTimer != null) {
            this.backgroundTimer.teardown();
        }
        if (this.foregroundTimer != null) {
            this.foregroundTimer.teardown();
        }
        if (this.delayStartTimer != null) {
            this.delayStartTimer.teardown();
        }
        if (this.scheduledExecutor != null) {
            try {
                this.scheduledExecutor.shutdownNow();
            } catch (SecurityException e) {
            }
        }
        if (this.packageHandler != null) {
            this.packageHandler.teardown();
        }
        if (this.attributionHandler != null) {
            this.attributionHandler.teardown();
        }
        if (this.sdkClickHandler != null) {
            this.sdkClickHandler.teardown();
        }
        if (this.sessionParameters != null) {
            if (this.sessionParameters.callbackParameters != null) {
                this.sessionParameters.callbackParameters.clear();
            }
            if (this.sessionParameters.partnerParameters != null) {
                this.sessionParameters.partnerParameters.clear();
            }
        }
        teardownActivityStateS();
        teardownAttributionS();
        teardownAllSessionParametersS();
        this.packageHandler = null;
        this.logger = null;
        this.foregroundTimer = null;
        this.scheduledExecutor = null;
        this.backgroundTimer = null;
        this.delayStartTimer = null;
        this.internalState = null;
        this.deviceInfo = null;
        this.adjustConfig = null;
        this.attributionHandler = null;
        this.sdkClickHandler = null;
        this.sessionParameters = null;
    }

    static void deleteState(Context context) {
        deleteActivityState(context);
        deleteAttribution(context);
        deleteSessionCallbackParameters(context);
        deleteSessionPartnerParameters(context);
        new SharedPreferencesManager(context).clear();
    }

    private ActivityHandler(AdjustConfig adjustConfig2) {
        init(adjustConfig2);
        this.logger.lockLogLevel();
        this.scheduledExecutor = new CustomScheduledExecutor("ActivityHandler", false);
        this.internalState = new InternalState();
        this.internalState.enabled = adjustConfig2.startEnabled != null ? adjustConfig2.startEnabled.booleanValue() : true;
        this.internalState.offline = adjustConfig2.startOffline;
        this.internalState.background = true;
        this.internalState.delayStart = false;
        this.internalState.updatePackages = false;
        this.internalState.sessionResponseProcessed = false;
        this.internalState.firstSdkStart = false;
        this.scheduledExecutor.submit(new Runnable() {
            public void run() {
                ActivityHandler.this.initI();
            }
        });
    }

    public AdjustConfig getAdjustConfig() {
        return this.adjustConfig;
    }

    public DeviceInfo getDeviceInfo() {
        return this.deviceInfo;
    }

    public ActivityState getActivityState() {
        return this.activityState;
    }

    public SessionParameters getSessionParameters() {
        return this.sessionParameters;
    }

    public void init(AdjustConfig adjustConfig2) {
        this.adjustConfig = adjustConfig2;
    }

    public static ActivityHandler getInstance(AdjustConfig adjustConfig2) {
        if (adjustConfig2 == null) {
            AdjustFactory.getLogger().error("AdjustConfig missing", new Object[0]);
            return null;
        } else if (!adjustConfig2.isValid()) {
            AdjustFactory.getLogger().error("AdjustConfig not initialized correctly", new Object[0]);
            return null;
        } else {
            if (adjustConfig2.processName != null) {
                int currentPid = Process.myPid();
                ActivityManager manager = (ActivityManager) adjustConfig2.context.getSystemService("activity");
                if (manager != null) {
                    Iterator it = manager.getRunningAppProcesses().iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        RunningAppProcessInfo processInfo = (RunningAppProcessInfo) it.next();
                        if (processInfo.pid == currentPid) {
                            if (!processInfo.processName.equalsIgnoreCase(adjustConfig2.processName)) {
                                AdjustFactory.getLogger().info("Skipping initialization in background process (%s)", processInfo.processName);
                                return null;
                            }
                        }
                    }
                } else {
                    return null;
                }
            }
            return new ActivityHandler(adjustConfig2);
        }
    }

    public void onResume() {
        this.internalState.background = false;
        this.scheduledExecutor.submit(new Runnable() {
            public void run() {
                ActivityHandler.this.delayStartI();
                ActivityHandler.this.stopBackgroundTimerI();
                ActivityHandler.this.startForegroundTimerI();
                ActivityHandler.this.logger.verbose("Subsession start", new Object[0]);
                ActivityHandler.this.startI();
            }
        });
    }

    public void onPause() {
        this.internalState.background = true;
        this.scheduledExecutor.submit(new Runnable() {
            public void run() {
                ActivityHandler.this.stopForegroundTimerI();
                ActivityHandler.this.startBackgroundTimerI();
                ActivityHandler.this.logger.verbose("Subsession end", new Object[0]);
                ActivityHandler.this.endI();
            }
        });
    }

    public void trackEvent(final AdjustEvent event) {
        this.scheduledExecutor.submit(new Runnable() {
            public void run() {
                if (ActivityHandler.this.internalState.hasFirstSdkStartNotOcurred()) {
                    ActivityHandler.this.logger.warn("Event tracked before first activity resumed.\nIf it was triggered in the Application class, it might timestamp or even send an install long before the user opens the app.\nPlease check https://github.com/adjust/android_sdk#can-i-trigger-an-event-at-application-launch for more information.", new Object[0]);
                    ActivityHandler.this.startI();
                }
                ActivityHandler.this.trackEventI(event);
            }
        });
    }

    public void finishedTrackingActivity(ResponseData responseData) {
        if (responseData instanceof SessionResponseData) {
            this.attributionHandler.checkSessionResponse((SessionResponseData) responseData);
        } else if (responseData instanceof SdkClickResponseData) {
            checkForInstallReferrerInfo((SdkClickResponseData) responseData);
            this.attributionHandler.checkSdkClickResponse((SdkClickResponseData) responseData);
        } else if (responseData instanceof EventResponseData) {
            launchEventResponseTasks((EventResponseData) responseData);
        }
    }

    public void setEnabled(final boolean enabled) {
        this.scheduledExecutor.submit(new Runnable() {
            public void run() {
                ActivityHandler.this.setEnabledI(enabled);
            }
        });
    }

    public void setOfflineMode(final boolean offline) {
        this.scheduledExecutor.submit(new Runnable() {
            public void run() {
                ActivityHandler.this.setOfflineModeI(offline);
            }
        });
    }

    public boolean isEnabled() {
        return isEnabledI();
    }

    private boolean isEnabledI() {
        if (this.activityState != null) {
            return this.activityState.enabled;
        }
        return this.internalState.isEnabled();
    }

    public void readOpenUrl(final Uri url, final long clickTime) {
        this.scheduledExecutor.submit(new Runnable() {
            public void run() {
                ActivityHandler.this.readOpenUrlI(url, clickTime);
            }
        });
    }

    private void updateAdidI(String adid) {
        if (adid != null && !adid.equals(this.activityState.adid)) {
            this.activityState.adid = adid;
            writeActivityStateI();
        }
    }

    public boolean updateAttributionI(AdjustAttribution attribution2) {
        if (attribution2 == null || attribution2.equals(this.attribution)) {
            return false;
        }
        this.attribution = attribution2;
        writeAttributionI();
        return true;
    }

    public void setAskingAttribution(final boolean askingAttribution) {
        this.scheduledExecutor.submit(new Runnable() {
            public void run() {
                ActivityHandler.this.setAskingAttributionI(askingAttribution);
            }
        });
    }

    public void sendReftagReferrer() {
        this.scheduledExecutor.submit(new Runnable() {
            public void run() {
                ActivityHandler.this.sendReftagReferrerI();
            }
        });
    }

    public void sendInstallReferrer(long clickTime, long installBegin, String installReferrer2) {
        final long j = clickTime;
        final long j2 = installBegin;
        final String str = installReferrer2;
        this.scheduledExecutor.submit(new Runnable() {
            public void run() {
                ActivityHandler.this.sendInstallReferrerI(j, j2, str);
            }
        });
    }

    public void launchEventResponseTasks(final EventResponseData eventResponseData) {
        this.scheduledExecutor.submit(new Runnable() {
            public void run() {
                ActivityHandler.this.launchEventResponseTasksI(eventResponseData);
            }
        });
    }

    public void launchSdkClickResponseTasks(final SdkClickResponseData sdkClickResponseData) {
        this.scheduledExecutor.submit(new Runnable() {
            public void run() {
                ActivityHandler.this.launchSdkClickResponseTasksI(sdkClickResponseData);
            }
        });
    }

    public void launchSessionResponseTasks(final SessionResponseData sessionResponseData) {
        this.scheduledExecutor.submit(new Runnable() {
            public void run() {
                ActivityHandler.this.launchSessionResponseTasksI(sessionResponseData);
            }
        });
    }

    public void launchAttributionResponseTasks(final AttributionResponseData attributionResponseData) {
        this.scheduledExecutor.submit(new Runnable() {
            public void run() {
                ActivityHandler.this.launchAttributionResponseTasksI(attributionResponseData);
            }
        });
    }

    public void sendFirstPackages() {
        this.scheduledExecutor.submit(new Runnable() {
            public void run() {
                ActivityHandler.this.sendFirstPackagesI();
            }
        });
    }

    public void addSessionCallbackParameter(final String key, final String value) {
        this.scheduledExecutor.submit(new Runnable() {
            public void run() {
                ActivityHandler.this.addSessionCallbackParameterI(key, value);
            }
        });
    }

    public void addSessionPartnerParameter(final String key, final String value) {
        this.scheduledExecutor.submit(new Runnable() {
            public void run() {
                ActivityHandler.this.addSessionPartnerParameterI(key, value);
            }
        });
    }

    public void removeSessionCallbackParameter(final String key) {
        this.scheduledExecutor.submit(new Runnable() {
            public void run() {
                ActivityHandler.this.removeSessionCallbackParameterI(key);
            }
        });
    }

    public void removeSessionPartnerParameter(final String key) {
        this.scheduledExecutor.submit(new Runnable() {
            public void run() {
                ActivityHandler.this.removeSessionPartnerParameterI(key);
            }
        });
    }

    public void resetSessionCallbackParameters() {
        this.scheduledExecutor.submit(new Runnable() {
            public void run() {
                ActivityHandler.this.resetSessionCallbackParametersI();
            }
        });
    }

    public void resetSessionPartnerParameters() {
        this.scheduledExecutor.submit(new Runnable() {
            public void run() {
                ActivityHandler.this.resetSessionPartnerParametersI();
            }
        });
    }

    public void setPushToken(final String token, final boolean preSaved) {
        this.scheduledExecutor.submit(new Runnable() {
            public void run() {
                if (!preSaved) {
                    new SharedPreferencesManager(ActivityHandler.this.getContext()).savePushToken(token);
                }
                if (!ActivityHandler.this.internalState.hasFirstSdkStartNotOcurred()) {
                    ActivityHandler.this.setPushTokenI(token);
                }
            }
        });
    }

    public Context getContext() {
        return this.adjustConfig.context;
    }

    public void foregroundTimerFired() {
        this.scheduledExecutor.submit(new Runnable() {
            public void run() {
                ActivityHandler.this.foregroundTimerFiredI();
            }
        });
    }

    public void backgroundTimerFired() {
        this.scheduledExecutor.submit(new Runnable() {
            public void run() {
                ActivityHandler.this.backgroundTimerFiredI();
            }
        });
    }

    public String getAdid() {
        if (this.activityState == null) {
            return null;
        }
        return this.activityState.adid;
    }

    public AdjustAttribution getAttribution() {
        return this.attribution;
    }

    public String getBasePath() {
        return this.basePath;
    }

    public ActivityPackage getAttributionPackageI() {
        return new PackageBuilder(this.adjustConfig, this.deviceInfo, this.activityState, this.sessionParameters, System.currentTimeMillis()).buildAttributionPackage();
    }

    public InternalState getInternalState() {
        return this.internalState;
    }

    /* access modifiers changed from: private */
    public void initI() {
        SESSION_INTERVAL = AdjustFactory.getSessionInterval();
        SUBSESSION_INTERVAL = AdjustFactory.getSubsessionInterval();
        FOREGROUND_TIMER_INTERVAL = AdjustFactory.getTimerInterval();
        FOREGROUND_TIMER_START = AdjustFactory.getTimerStart();
        BACKGROUND_TIMER_INTERVAL = AdjustFactory.getTimerInterval();
        readAttributionI(this.adjustConfig.context);
        readActivityStateI(this.adjustConfig.context);
        this.sessionParameters = new SessionParameters();
        readSessionCallbackParametersI(this.adjustConfig.context);
        readSessionPartnerParametersI(this.adjustConfig.context);
        if (this.adjustConfig.startEnabled != null) {
            if (this.adjustConfig.preLaunchActionsArray == null) {
                this.adjustConfig.preLaunchActionsArray = new ArrayList();
            }
            this.adjustConfig.preLaunchActionsArray.add(new IRunActivityHandler() {
                public void run(ActivityHandler activityHandler) {
                    activityHandler.setEnabledI(ActivityHandler.this.adjustConfig.startEnabled.booleanValue());
                }
            });
        }
        if (this.internalState.hasFirstSdkStartOcurred()) {
            this.internalState.enabled = this.activityState.enabled;
            this.internalState.updatePackages = this.activityState.updatePackages;
            this.internalState.firstLaunch = false;
        } else {
            this.internalState.firstLaunch = true;
        }
        readConfigFile(this.adjustConfig.context);
        this.deviceInfo = new DeviceInfo(this.adjustConfig.context, this.adjustConfig.sdkPrefix);
        if (this.adjustConfig.eventBufferingEnabled) {
            this.logger.info("Event buffering is enabled", new Object[0]);
        }
        if (this.deviceInfo.playAdId == null) {
            this.logger.warn("Unable to get Google Play Services Advertising ID at start time", new Object[0]);
            if (this.deviceInfo.macSha1 == null && this.deviceInfo.macShortMd5 == null && this.deviceInfo.androidId == null) {
                this.logger.error("Unable to get any device id's. Please check if Proguard is correctly set with Adjust SDK", new Object[0]);
            }
        } else {
            this.logger.info("Google Play Services Advertising ID read correctly at start time", new Object[0]);
        }
        if (this.adjustConfig.defaultTracker != null) {
            this.logger.info("Default tracker: '%s'", this.adjustConfig.defaultTracker);
        }
        if (this.adjustConfig.pushToken != null) {
            this.logger.info("Push token: '%s'", this.adjustConfig.pushToken);
            if (this.internalState.hasFirstSdkStartOcurred()) {
                setPushToken(this.adjustConfig.pushToken, false);
            } else {
                new SharedPreferencesManager(getContext()).savePushToken(this.adjustConfig.pushToken);
            }
        } else if (this.internalState.hasFirstSdkStartOcurred()) {
            setPushToken(new SharedPreferencesManager(getContext()).getPushToken(), true);
        }
        this.foregroundTimer = new TimerCycle(new Runnable() {
            public void run() {
                ActivityHandler.this.foregroundTimerFired();
            }
        }, FOREGROUND_TIMER_START, FOREGROUND_TIMER_INTERVAL, FOREGROUND_TIMER_NAME);
        if (this.adjustConfig.sendInBackground) {
            this.logger.info("Send in background configured", new Object[0]);
            this.backgroundTimer = new TimerOnce(new Runnable() {
                public void run() {
                    ActivityHandler.this.backgroundTimerFired();
                }
            }, BACKGROUND_TIMER_NAME);
        }
        if (this.internalState.hasFirstSdkStartNotOcurred() && this.adjustConfig.delayStart != null && this.adjustConfig.delayStart.doubleValue() > 0.0d) {
            this.logger.info("Delay start configured", new Object[0]);
            this.internalState.delayStart = true;
            this.delayStartTimer = new TimerOnce(new Runnable() {
                public void run() {
                    ActivityHandler.this.sendFirstPackages();
                }
            }, DELAY_START_TIMER_NAME);
        }
        UtilNetworking.setUserAgent(this.adjustConfig.userAgent);
        this.basePath = this.adjustConfig.basePath;
        this.packageHandler = AdjustFactory.getPackageHandler(this, this.adjustConfig.context, toSendI(false));
        this.attributionHandler = AdjustFactory.getAttributionHandler(this, getAttributionPackageI(), toSendI(false));
        this.sdkClickHandler = AdjustFactory.getSdkClickHandler(this, toSendI(true));
        if (isToUpdatePackagesI()) {
            updatePackagesI();
        }
        preLaunchActionsI(this.adjustConfig.preLaunchActionsArray);
        this.installReferrer = new InstallReferrer(this.adjustConfig.context, this);
        sendReftagReferrerI();
    }

    private void readConfigFile(Context context) {
        try {
            InputStream inputStream = context.getAssets().open("adjust_config.properties");
            Properties properties = new Properties();
            properties.load(inputStream);
            this.logger.verbose("adjust_config.properties file read and loaded", new Object[0]);
            String defaultTracker = properties.getProperty("defaultTracker");
            if (defaultTracker != null) {
                this.adjustConfig.defaultTracker = defaultTracker;
            }
        } catch (Exception e) {
            this.logger.debug("%s file not found in this app", e.getMessage());
        }
    }

    private void preLaunchActionsI(List<IRunActivityHandler> preLaunchActionsArray) {
        if (preLaunchActionsArray != null) {
            for (IRunActivityHandler preLaunchAction : preLaunchActionsArray) {
                preLaunchAction.run(this);
            }
        }
    }

    /* access modifiers changed from: private */
    public void startI() {
        if (this.internalState.hasFirstSdkStartNotOcurred()) {
            startFirstSessionI();
        } else if (this.activityState.enabled) {
            updateHandlersStatusAndSendI();
            processSessionI();
            checkAttributionStateI();
        }
    }

    private void startFirstSessionI() {
        updateHandlersStatusAndSendI();
        this.activityState = new ActivityState();
        this.internalState.firstSdkStart = true;
        long now = System.currentTimeMillis();
        SharedPreferencesManager sharedPreferencesManager = new SharedPreferencesManager(getContext());
        this.activityState.pushToken = sharedPreferencesManager.getPushToken();
        if (this.internalState.isEnabled()) {
            this.activityState.sessionCount = 1;
            transferSessionPackageI(now);
            checkAfterNewStartI(sharedPreferencesManager);
        }
        this.activityState.resetSessionAttributes(now);
        this.activityState.enabled = this.internalState.isEnabled();
        this.activityState.updatePackages = this.internalState.itHasToUpdatePackages();
        writeActivityStateI();
        sharedPreferencesManager.removePushToken();
    }

    private void processSessionI() {
        long now = System.currentTimeMillis();
        long lastInterval = now - this.activityState.lastActivity;
        if (lastInterval < 0) {
            this.logger.error(TIME_TRAVEL, new Object[0]);
            this.activityState.lastActivity = now;
            writeActivityStateI();
        } else if (lastInterval > SESSION_INTERVAL) {
            trackNewSessionI(now);
            checkAfterNewStartI();
        } else if (lastInterval > SUBSESSION_INTERVAL) {
            this.activityState.subsessionCount++;
            this.activityState.sessionLength += lastInterval;
            this.activityState.lastActivity = now;
            this.logger.verbose("Started subsession %d of session %d", Integer.valueOf(this.activityState.subsessionCount), Integer.valueOf(this.activityState.sessionCount));
            writeActivityStateI();
            this.installReferrer.startConnection();
        } else {
            this.logger.verbose("Time span since last activity too short for a new subsession", new Object[0]);
        }
    }

    private void trackNewSessionI(long now) {
        long lastInterval = now - this.activityState.lastActivity;
        this.activityState.sessionCount++;
        this.activityState.lastInterval = lastInterval;
        transferSessionPackageI(now);
        this.activityState.resetSessionAttributes(now);
        writeActivityStateI();
    }

    private void checkAttributionStateI() {
        if (checkActivityStateI(this.activityState)) {
            if (this.internalState.isFirstLaunch() && this.internalState.hasSessionResponseNotBeenProcessed()) {
                return;
            }
            if (this.attribution == null || this.activityState.askingAttribution) {
                this.attributionHandler.getAttribution();
            }
        }
    }

    /* access modifiers changed from: private */
    public void endI() {
        if (!toSendI()) {
            pauseSendingI();
        }
        if (updateActivityStateI(System.currentTimeMillis())) {
            writeActivityStateI();
        }
    }

    /* access modifiers changed from: private */
    public void trackEventI(AdjustEvent event) {
        if (checkActivityStateI(this.activityState) && isEnabledI() && checkEventI(event) && checkOrderIdI(event.orderId)) {
            long now = System.currentTimeMillis();
            this.activityState.eventCount++;
            updateActivityStateI(now);
            ActivityPackage eventPackage = new PackageBuilder(this.adjustConfig, this.deviceInfo, this.activityState, this.sessionParameters, now).buildEventPackage(event, this.internalState.isInDelayedStart());
            this.packageHandler.addPackage(eventPackage);
            if (this.adjustConfig.eventBufferingEnabled) {
                this.logger.info("Buffered event %s", eventPackage.getSuffix());
            } else {
                this.packageHandler.sendFirstPackage();
            }
            if (this.adjustConfig.sendInBackground && this.internalState.isInBackground()) {
                startBackgroundTimerI();
            }
            writeActivityStateI();
        }
    }

    /* access modifiers changed from: private */
    public void launchEventResponseTasksI(final EventResponseData eventResponseData) {
        updateAdidI(eventResponseData.adid);
        Handler handler = new Handler(this.adjustConfig.context.getMainLooper());
        if (eventResponseData.success && this.adjustConfig.onEventTrackingSucceededListener != null) {
            this.logger.debug("Launching success event tracking listener", new Object[0]);
            handler.post(new Runnable() {
                public void run() {
                    if (ActivityHandler.this.adjustConfig != null && ActivityHandler.this.adjustConfig.onEventTrackingSucceededListener != null) {
                        ActivityHandler.this.adjustConfig.onEventTrackingSucceededListener.onFinishedEventTrackingSucceeded(eventResponseData.getSuccessResponseData());
                    }
                }
            });
        } else if (!eventResponseData.success && this.adjustConfig.onEventTrackingFailedListener != null) {
            this.logger.debug("Launching failed event tracking listener", new Object[0]);
            handler.post(new Runnable() {
                public void run() {
                    if (ActivityHandler.this.adjustConfig != null && ActivityHandler.this.adjustConfig.onEventTrackingFailedListener != null) {
                        ActivityHandler.this.adjustConfig.onEventTrackingFailedListener.onFinishedEventTrackingFailed(eventResponseData.getFailureResponseData());
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void launchSdkClickResponseTasksI(SdkClickResponseData sdkClickResponseData) {
        updateAdidI(sdkClickResponseData.adid);
        Handler handler = new Handler(this.adjustConfig.context.getMainLooper());
        if (updateAttributionI(sdkClickResponseData.attribution)) {
            launchAttributionListenerI(handler);
        }
    }

    /* access modifiers changed from: private */
    public void launchSessionResponseTasksI(SessionResponseData sessionResponseData) {
        updateAdidI(sessionResponseData.adid);
        Handler handler = new Handler(this.adjustConfig.context.getMainLooper());
        if (updateAttributionI(sessionResponseData.attribution)) {
            launchAttributionListenerI(handler);
        }
        if (sessionResponseData.success) {
            new SharedPreferencesManager(getContext()).setInstallTracked();
        }
        launchSessionResponseListenerI(sessionResponseData, handler);
        this.internalState.sessionResponseProcessed = true;
    }

    private void launchSessionResponseListenerI(final SessionResponseData sessionResponseData, Handler handler) {
        if (sessionResponseData.success && this.adjustConfig.onSessionTrackingSucceededListener != null) {
            this.logger.debug("Launching success session tracking listener", new Object[0]);
            handler.post(new Runnable() {
                public void run() {
                    if (ActivityHandler.this.adjustConfig != null && ActivityHandler.this.adjustConfig.onSessionTrackingSucceededListener != null) {
                        ActivityHandler.this.adjustConfig.onSessionTrackingSucceededListener.onFinishedSessionTrackingSucceeded(sessionResponseData.getSuccessResponseData());
                    }
                }
            });
        } else if (!sessionResponseData.success && this.adjustConfig.onSessionTrackingFailedListener != null) {
            this.logger.debug("Launching failed session tracking listener", new Object[0]);
            handler.post(new Runnable() {
                public void run() {
                    if (ActivityHandler.this.adjustConfig != null && ActivityHandler.this.adjustConfig.onSessionTrackingFailedListener != null) {
                        ActivityHandler.this.adjustConfig.onSessionTrackingFailedListener.onFinishedSessionTrackingFailed(sessionResponseData.getFailureResponseData());
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void launchAttributionResponseTasksI(AttributionResponseData attributionResponseData) {
        updateAdidI(attributionResponseData.adid);
        Handler handler = new Handler(this.adjustConfig.context.getMainLooper());
        if (updateAttributionI(attributionResponseData.attribution)) {
            launchAttributionListenerI(handler);
        }
        prepareDeeplinkI(attributionResponseData.deeplink, handler);
    }

    private void launchAttributionListenerI(Handler handler) {
        if (this.adjustConfig.onAttributionChangedListener != null) {
            handler.post(new Runnable() {
                public void run() {
                    if (ActivityHandler.this.adjustConfig != null && ActivityHandler.this.adjustConfig.onAttributionChangedListener != null) {
                        ActivityHandler.this.adjustConfig.onAttributionChangedListener.onAttributionChanged(ActivityHandler.this.attribution);
                    }
                }
            });
        }
    }

    private void prepareDeeplinkI(final Uri deeplink, Handler handler) {
        if (deeplink != null) {
            this.logger.info("Deferred deeplink received (%s)", deeplink);
            final Intent deeplinkIntent = createDeeplinkIntentI(deeplink);
            handler.post(new Runnable() {
                public void run() {
                    if (ActivityHandler.this.adjustConfig != null) {
                        boolean toLaunchDeeplink = true;
                        if (ActivityHandler.this.adjustConfig.onDeeplinkResponseListener != null) {
                            toLaunchDeeplink = ActivityHandler.this.adjustConfig.onDeeplinkResponseListener.launchReceivedDeeplink(deeplink);
                        }
                        if (toLaunchDeeplink) {
                            ActivityHandler.this.launchDeeplinkMain(deeplinkIntent, deeplink);
                        }
                    }
                }
            });
        }
    }

    private Intent createDeeplinkIntentI(Uri deeplink) {
        Intent mapIntent;
        if (this.adjustConfig.deepLinkComponent == null) {
            mapIntent = new Intent("android.intent.action.VIEW", deeplink);
        } else {
            mapIntent = new Intent("android.intent.action.VIEW", deeplink, this.adjustConfig.context, this.adjustConfig.deepLinkComponent);
        }
        mapIntent.setFlags(268435456);
        mapIntent.setPackage(this.adjustConfig.context.getPackageName());
        return mapIntent;
    }

    /* access modifiers changed from: private */
    public void launchDeeplinkMain(Intent deeplinkIntent, Uri deeplink) {
        boolean isIntentSafe;
        if (this.adjustConfig.context.getPackageManager().queryIntentActivities(deeplinkIntent, 0).size() > 0) {
            isIntentSafe = true;
        } else {
            isIntentSafe = false;
        }
        if (!isIntentSafe) {
            this.logger.error("Unable to open deferred deep link (%s)", deeplink);
            return;
        }
        this.logger.info("Open deferred deep link (%s)", deeplink);
        this.adjustConfig.context.startActivity(deeplinkIntent);
    }

    /* access modifiers changed from: private */
    public void setEnabledI(boolean enabled) {
        boolean z = true;
        if (hasChangedStateI(isEnabledI(), enabled, "Adjust already enabled", "Adjust already disabled")) {
            this.internalState.enabled = enabled;
            if (this.internalState.hasFirstSdkStartNotOcurred()) {
                if (enabled) {
                    z = false;
                }
                updateStatusI(z, "Handlers will start as paused due to the SDK being disabled", "Handlers will still start as paused", "Handlers will start as active due to the SDK being enabled");
                return;
            }
            if (enabled) {
                SharedPreferencesManager sharedPreferencesManager = new SharedPreferencesManager(getContext());
                if (!sharedPreferencesManager.getInstallTracked()) {
                    trackNewSessionI(System.currentTimeMillis());
                }
                checkAfterNewStartI(sharedPreferencesManager);
            }
            this.activityState.enabled = enabled;
            writeActivityStateI();
            if (enabled) {
                z = false;
            }
            updateStatusI(z, "Pausing handlers due to SDK being disabled", "Handlers remain paused", "Resuming handlers due to SDK being enabled");
        }
    }

    private void checkAfterNewStartI() {
        checkAfterNewStartI(new SharedPreferencesManager(getContext()));
    }

    private void checkAfterNewStartI(SharedPreferencesManager sharedPreferencesManager) {
        String pushToken = sharedPreferencesManager.getPushToken();
        if (pushToken != null && !pushToken.equals(this.activityState.pushToken)) {
            setPushToken(pushToken, true);
        }
        if (sharedPreferencesManager.getRawReferrerArray() != null) {
            sendReftagReferrer();
        }
        this.installReferrer.startConnection();
    }

    /* access modifiers changed from: private */
    public void setOfflineModeI(boolean offline) {
        if (hasChangedStateI(this.internalState.isOffline(), offline, "Adjust already in offline mode", "Adjust already in online mode")) {
            this.internalState.offline = offline;
            if (this.internalState.hasFirstSdkStartNotOcurred()) {
                updateStatusI(offline, "Handlers will start paused due to SDK being offline", "Handlers will still start as paused", "Handlers will start as active due to SDK being online");
            } else {
                updateStatusI(offline, "Pausing handlers to put SDK offline mode", "Handlers remain paused", "Resuming handlers to put SDK in online mode");
            }
        }
    }

    private boolean hasChangedStateI(boolean previousState, boolean newState, String trueMessage, String falseMessage) {
        if (previousState != newState) {
            return true;
        }
        if (previousState) {
            this.logger.debug(trueMessage, new Object[0]);
            return false;
        }
        this.logger.debug(falseMessage, new Object[0]);
        return false;
    }

    private void updateStatusI(boolean pausingState, String pausingMessage, String remainsPausedMessage, String unPausingMessage) {
        if (pausingState) {
            this.logger.info(pausingMessage, new Object[0]);
        } else if (!pausedI(false)) {
            this.logger.info(unPausingMessage, new Object[0]);
        } else if (pausedI(true)) {
            this.logger.info(remainsPausedMessage, new Object[0]);
        } else {
            this.logger.info(remainsPausedMessage + ", except the Sdk Click Handler", new Object[0]);
        }
        updateHandlersStatusAndSendI();
    }

    /* access modifiers changed from: private */
    public void setAskingAttributionI(boolean askingAttribution) {
        this.activityState.askingAttribution = askingAttribution;
        writeActivityStateI();
    }

    /* access modifiers changed from: private */
    public void sendReftagReferrerI() {
        if (isEnabledI() && !this.internalState.hasFirstSdkStartNotOcurred()) {
            this.sdkClickHandler.sendReftagReferrers();
        }
    }

    /* access modifiers changed from: private */
    public void sendInstallReferrerI(long clickTime, long installBegin, String installReferrer2) {
        if (isEnabledI() && installReferrer2 != null) {
            if (clickTime == this.activityState.clickTime && installBegin == this.activityState.installBegin) {
                if (installReferrer2.equals(this.activityState.installReferrer)) {
                    return;
                }
            }
            this.sdkClickHandler.sendSdkClick(PackageFactory.buildInstallReferrerSdkClickPackage(installReferrer2, clickTime, installBegin, this.activityState, this.adjustConfig, this.deviceInfo, this.sessionParameters));
        }
    }

    /* access modifiers changed from: private */
    public void readOpenUrlI(Uri url, long clickTime) {
        if (isEnabledI()) {
            ActivityPackage sdkClickPackage = PackageFactory.buildDeeplinkSdkClickPackage(url, clickTime, this.activityState, this.adjustConfig, this.deviceInfo, this.sessionParameters);
            if (sdkClickPackage != null) {
                this.sdkClickHandler.sendSdkClick(sdkClickPackage);
            }
        }
    }

    private void updateHandlersStatusAndSendI() {
        if (!toSendI()) {
            pauseSendingI();
            return;
        }
        resumeSendingI();
        if (!this.adjustConfig.eventBufferingEnabled || (this.internalState.isFirstLaunch() && this.internalState.hasSessionResponseNotBeenProcessed())) {
            this.packageHandler.sendFirstPackage();
        }
    }

    private void pauseSendingI() {
        this.attributionHandler.pauseSending();
        this.packageHandler.pauseSending();
        if (!toSendI(true)) {
            this.sdkClickHandler.pauseSending();
        } else {
            this.sdkClickHandler.resumeSending();
        }
    }

    private void resumeSendingI() {
        this.attributionHandler.resumeSending();
        this.packageHandler.resumeSending();
        this.sdkClickHandler.resumeSending();
    }

    private boolean updateActivityStateI(long now) {
        if (!checkActivityStateI(this.activityState)) {
            return false;
        }
        long lastInterval = now - this.activityState.lastActivity;
        if (lastInterval > SESSION_INTERVAL) {
            return false;
        }
        this.activityState.lastActivity = now;
        if (lastInterval < 0) {
            this.logger.error(TIME_TRAVEL, new Object[0]);
        } else {
            this.activityState.sessionLength += lastInterval;
            this.activityState.timeSpent += lastInterval;
        }
        return true;
    }

    public static boolean deleteActivityState(Context context) {
        return context.deleteFile(Constants.ACTIVITY_STATE_FILENAME);
    }

    public static boolean deleteAttribution(Context context) {
        return context.deleteFile(Constants.ATTRIBUTION_FILENAME);
    }

    public static boolean deleteSessionCallbackParameters(Context context) {
        return context.deleteFile(Constants.SESSION_CALLBACK_PARAMETERS_FILENAME);
    }

    public static boolean deleteSessionPartnerParameters(Context context) {
        return context.deleteFile(Constants.SESSION_PARTNER_PARAMETERS_FILENAME);
    }

    private void transferSessionPackageI(long now) {
        this.packageHandler.addPackage(new PackageBuilder(this.adjustConfig, this.deviceInfo, this.activityState, this.sessionParameters, now).buildSessionPackage(this.internalState.isInDelayedStart()));
        this.packageHandler.sendFirstPackage();
    }

    /* access modifiers changed from: private */
    public void startForegroundTimerI() {
        if (isEnabledI()) {
            this.foregroundTimer.start();
        }
    }

    /* access modifiers changed from: private */
    public void stopForegroundTimerI() {
        this.foregroundTimer.suspend();
    }

    /* access modifiers changed from: private */
    public void foregroundTimerFiredI() {
        if (!isEnabledI()) {
            stopForegroundTimerI();
            return;
        }
        if (toSendI()) {
            this.packageHandler.sendFirstPackage();
        }
        if (updateActivityStateI(System.currentTimeMillis())) {
            writeActivityStateI();
        }
    }

    /* access modifiers changed from: private */
    public void startBackgroundTimerI() {
        if (this.backgroundTimer != null && toSendI() && this.backgroundTimer.getFireIn() <= 0) {
            this.backgroundTimer.startIn(BACKGROUND_TIMER_INTERVAL);
        }
    }

    /* access modifiers changed from: private */
    public void stopBackgroundTimerI() {
        if (this.backgroundTimer != null) {
            this.backgroundTimer.cancel();
        }
    }

    /* access modifiers changed from: private */
    public void backgroundTimerFiredI() {
        if (toSendI()) {
            this.packageHandler.sendFirstPackage();
        }
    }

    /* access modifiers changed from: private */
    public void delayStartI() {
        if (!this.internalState.isNotInDelayedStart() && !isToUpdatePackagesI()) {
            double delayStartSeconds = this.adjustConfig.delayStart != null ? this.adjustConfig.delayStart.doubleValue() : 0.0d;
            long maxDelayStartMilli = AdjustFactory.getMaxDelayStart();
            long delayStartMilli = (long) (1000.0d * delayStartSeconds);
            if (delayStartMilli > maxDelayStartMilli) {
                double maxDelayStartSeconds = (double) (maxDelayStartMilli / 1000);
                this.logger.warn("Delay start of %s seconds bigger than max allowed value of %s seconds", Util.SecondsDisplayFormat.format(delayStartSeconds), Util.SecondsDisplayFormat.format(maxDelayStartSeconds));
                delayStartMilli = maxDelayStartMilli;
                delayStartSeconds = maxDelayStartSeconds;
            }
            this.logger.info("Waiting %s seconds before starting first session", Util.SecondsDisplayFormat.format(delayStartSeconds));
            this.delayStartTimer.startIn(delayStartMilli);
            this.internalState.updatePackages = true;
            if (this.activityState != null) {
                this.activityState.updatePackages = true;
                writeActivityStateI();
            }
        }
    }

    /* access modifiers changed from: private */
    public void sendFirstPackagesI() {
        if (this.internalState.isNotInDelayedStart()) {
            this.logger.info("Start delay expired or never configured", new Object[0]);
            return;
        }
        updatePackagesI();
        this.internalState.delayStart = false;
        this.delayStartTimer.cancel();
        this.delayStartTimer = null;
        updateHandlersStatusAndSendI();
    }

    private void updatePackagesI() {
        this.packageHandler.updatePackages(this.sessionParameters);
        this.internalState.updatePackages = false;
        if (this.activityState != null) {
            this.activityState.updatePackages = false;
            writeActivityStateI();
        }
    }

    private boolean isToUpdatePackagesI() {
        if (this.activityState != null) {
            return this.activityState.updatePackages;
        }
        return this.internalState.itHasToUpdatePackages();
    }

    public void addSessionCallbackParameterI(String key, String value) {
        if (Util.isValidParameter(key, "key", "Session Callback") && Util.isValidParameter(value, "value", "Session Callback")) {
            if (this.sessionParameters.callbackParameters == null) {
                this.sessionParameters.callbackParameters = new LinkedHashMap();
            }
            String oldValue = (String) this.sessionParameters.callbackParameters.get(key);
            if (value.equals(oldValue)) {
                this.logger.verbose("Key %s already present with the same value", key);
                return;
            }
            if (oldValue != null) {
                this.logger.warn("Key %s will be overwritten", key);
            }
            this.sessionParameters.callbackParameters.put(key, value);
            writeSessionCallbackParametersI();
        }
    }

    public void addSessionPartnerParameterI(String key, String value) {
        if (Util.isValidParameter(key, "key", "Session Partner") && Util.isValidParameter(value, "value", "Session Partner")) {
            if (this.sessionParameters.partnerParameters == null) {
                this.sessionParameters.partnerParameters = new LinkedHashMap();
            }
            String oldValue = (String) this.sessionParameters.partnerParameters.get(key);
            if (value.equals(oldValue)) {
                this.logger.verbose("Key %s already present with the same value", key);
                return;
            }
            if (oldValue != null) {
                this.logger.warn("Key %s will be overwritten", key);
            }
            this.sessionParameters.partnerParameters.put(key, value);
            writeSessionPartnerParametersI();
        }
    }

    public void removeSessionCallbackParameterI(String key) {
        if (Util.isValidParameter(key, "key", "Session Callback")) {
            if (this.sessionParameters.callbackParameters == null) {
                this.logger.warn("Session Callback parameters are not set", new Object[0]);
            } else if (((String) this.sessionParameters.callbackParameters.remove(key)) == null) {
                this.logger.warn("Key %s does not exist", key);
            } else {
                this.logger.debug("Key %s will be removed", key);
                writeSessionCallbackParametersI();
            }
        }
    }

    public void removeSessionPartnerParameterI(String key) {
        if (Util.isValidParameter(key, "key", "Session Partner")) {
            if (this.sessionParameters.partnerParameters == null) {
                this.logger.warn("Session Partner parameters are not set", new Object[0]);
            } else if (((String) this.sessionParameters.partnerParameters.remove(key)) == null) {
                this.logger.warn("Key %s does not exist", key);
            } else {
                this.logger.debug("Key %s will be removed", key);
                writeSessionPartnerParametersI();
            }
        }
    }

    public void resetSessionCallbackParametersI() {
        if (this.sessionParameters.callbackParameters == null) {
            this.logger.warn("Session Callback parameters are not set", new Object[0]);
        }
        this.sessionParameters.callbackParameters = null;
        writeSessionCallbackParametersI();
    }

    public void resetSessionPartnerParametersI() {
        if (this.sessionParameters.partnerParameters == null) {
            this.logger.warn("Session Partner parameters are not set", new Object[0]);
        }
        this.sessionParameters.partnerParameters = null;
        writeSessionPartnerParametersI();
    }

    /* access modifiers changed from: private */
    public void setPushTokenI(String token) {
        if (checkActivityStateI(this.activityState) && isEnabledI() && token != null && !token.equals(this.activityState.pushToken)) {
            this.activityState.pushToken = token;
            writeActivityStateI();
            ActivityPackage infoPackage = new PackageBuilder(this.adjustConfig, this.deviceInfo, this.activityState, this.sessionParameters, System.currentTimeMillis()).buildInfoPackage(Constants.PUSH);
            this.packageHandler.addPackage(infoPackage);
            new SharedPreferencesManager(getContext()).removePushToken();
            if (this.adjustConfig.eventBufferingEnabled) {
                this.logger.info("Buffered event %s", infoPackage.getSuffix());
                return;
            }
            this.packageHandler.sendFirstPackage();
        }
    }

    private void readActivityStateI(Context context) {
        try {
            this.activityState = (ActivityState) Util.readObject(context, Constants.ACTIVITY_STATE_FILENAME, ACTIVITY_STATE_NAME, ActivityState.class);
        } catch (Exception e) {
            this.logger.error("Failed to read %s file (%s)", ACTIVITY_STATE_NAME, e.getMessage());
            this.activityState = null;
        }
        if (this.activityState != null) {
            this.internalState.firstSdkStart = true;
        }
    }

    private void readAttributionI(Context context) {
        try {
            this.attribution = (AdjustAttribution) Util.readObject(context, Constants.ATTRIBUTION_FILENAME, ATTRIBUTION_NAME, AdjustAttribution.class);
        } catch (Exception e) {
            this.logger.error("Failed to read %s file (%s)", ATTRIBUTION_NAME, e.getMessage());
            this.attribution = null;
        }
    }

    private void readSessionCallbackParametersI(Context context) {
        try {
            this.sessionParameters.callbackParameters = (Map) Util.readObject(context, Constants.SESSION_CALLBACK_PARAMETERS_FILENAME, SESSION_CALLBACK_PARAMETERS_NAME, Map.class);
        } catch (Exception e) {
            this.logger.error("Failed to read %s file (%s)", SESSION_CALLBACK_PARAMETERS_NAME, e.getMessage());
            this.sessionParameters.callbackParameters = null;
        }
    }

    private void readSessionPartnerParametersI(Context context) {
        try {
            this.sessionParameters.partnerParameters = (Map) Util.readObject(context, Constants.SESSION_PARTNER_PARAMETERS_FILENAME, SESSION_PARTNER_PARAMETERS_NAME, Map.class);
        } catch (Exception e) {
            this.logger.error("Failed to read %s file (%s)", SESSION_PARTNER_PARAMETERS_NAME, e.getMessage());
            this.sessionParameters.partnerParameters = null;
        }
    }

    private void writeActivityStateI() {
        synchronized (ActivityState.class) {
            if (this.activityState != null) {
                Util.writeObject(this.activityState, this.adjustConfig.context, Constants.ACTIVITY_STATE_FILENAME, ACTIVITY_STATE_NAME);
            }
        }
    }

    private void teardownActivityStateS() {
        synchronized (ActivityState.class) {
            if (this.activityState != null) {
                this.activityState = null;
            }
        }
    }

    private void writeAttributionI() {
        synchronized (AdjustAttribution.class) {
            if (this.attribution != null) {
                Util.writeObject(this.attribution, this.adjustConfig.context, Constants.ATTRIBUTION_FILENAME, ATTRIBUTION_NAME);
            }
        }
    }

    private void teardownAttributionS() {
        synchronized (AdjustAttribution.class) {
            if (this.attribution != null) {
                this.attribution = null;
            }
        }
    }

    private void writeSessionCallbackParametersI() {
        synchronized (SessionParameters.class) {
            if (this.sessionParameters != null) {
                Util.writeObject(this.sessionParameters.callbackParameters, this.adjustConfig.context, Constants.SESSION_CALLBACK_PARAMETERS_FILENAME, SESSION_CALLBACK_PARAMETERS_NAME);
            }
        }
    }

    private void writeSessionPartnerParametersI() {
        synchronized (SessionParameters.class) {
            if (this.sessionParameters != null) {
                Util.writeObject(this.sessionParameters.partnerParameters, this.adjustConfig.context, Constants.SESSION_PARTNER_PARAMETERS_FILENAME, SESSION_PARTNER_PARAMETERS_NAME);
            }
        }
    }

    private void teardownAllSessionParametersS() {
        synchronized (SessionParameters.class) {
            if (this.sessionParameters != null) {
                this.sessionParameters = null;
            }
        }
    }

    private boolean checkEventI(AdjustEvent event) {
        if (event == null) {
            this.logger.error("Event missing", new Object[0]);
            return false;
        } else if (event.isValid()) {
            return true;
        } else {
            this.logger.error("Event not initialized correctly", new Object[0]);
            return false;
        }
    }

    private boolean checkOrderIdI(String orderId) {
        if (orderId == null || orderId.isEmpty()) {
            return true;
        }
        if (this.activityState.findOrderId(orderId)) {
            this.logger.info("Skipping duplicated order ID '%s'", orderId);
            return false;
        }
        this.activityState.addOrderId(orderId);
        this.logger.verbose("Added order ID '%s'", orderId);
        return true;
    }

    private boolean checkActivityStateI(ActivityState activityState2) {
        if (!this.internalState.hasFirstSdkStartNotOcurred()) {
            return true;
        }
        this.logger.error("Sdk did not yet start", new Object[0]);
        return false;
    }

    private boolean pausedI() {
        return pausedI(false);
    }

    private boolean pausedI(boolean sdkClickHandlerOnly) {
        if (sdkClickHandlerOnly) {
            if (this.internalState.isOffline() || !isEnabledI()) {
                return true;
            }
            return false;
        } else if (this.internalState.isOffline() || !isEnabledI() || this.internalState.isInDelayedStart()) {
            return true;
        } else {
            return false;
        }
    }

    private boolean toSendI() {
        return toSendI(false);
    }

    private boolean toSendI(boolean sdkClickHandlerOnly) {
        if (pausedI(sdkClickHandlerOnly)) {
            return false;
        }
        if (this.adjustConfig.sendInBackground) {
            return true;
        }
        return this.internalState.isInForeground();
    }

    private void checkForInstallReferrerInfo(SdkClickResponseData responseData) {
        if (responseData.isInstallReferrer) {
            this.activityState.clickTime = responseData.clickTime;
            this.activityState.installBegin = responseData.installBegin;
            this.activityState.installReferrer = responseData.installReferrer;
            writeActivityStateI();
        }
    }
}
