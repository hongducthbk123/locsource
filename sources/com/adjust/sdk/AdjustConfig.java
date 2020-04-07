package com.adjust.sdk;

import android.content.Context;
import java.util.List;

public class AdjustConfig {
    public static final String ENVIRONMENT_PRODUCTION = "production";
    public static final String ENVIRONMENT_SANDBOX = "sandbox";
    String appSecret;
    String appToken;
    String basePath;
    Context context;
    Class deepLinkComponent;
    String defaultTracker;
    Double delayStart;
    Boolean deviceKnown;
    String environment;
    boolean eventBufferingEnabled;
    ILogger logger;
    OnAttributionChangedListener onAttributionChangedListener;
    OnDeeplinkResponseListener onDeeplinkResponseListener;
    OnEventTrackingFailedListener onEventTrackingFailedListener;
    OnEventTrackingSucceededListener onEventTrackingSucceededListener;
    OnSessionTrackingFailedListener onSessionTrackingFailedListener;
    OnSessionTrackingSucceededListener onSessionTrackingSucceededListener;
    List<IRunActivityHandler> preLaunchActionsArray;
    String processName;
    String pushToken;
    boolean readMobileEquipmentIdentity;
    String sdkPrefix;
    String secretId;
    boolean sendInBackground;
    Boolean startEnabled;
    boolean startOffline;
    String userAgent;

    public AdjustConfig(Context context2, String appToken2, String environment2) {
        init(context2, appToken2, environment2, false);
    }

    public AdjustConfig(Context context2, String appToken2, String environment2, boolean allowSuppressLogLevel) {
        init(context2, appToken2, environment2, allowSuppressLogLevel);
    }

    private void init(Context context2, String appToken2, String environment2, boolean allowSuppressLogLevel) {
        this.logger = AdjustFactory.getLogger();
        if (!allowSuppressLogLevel || !ENVIRONMENT_PRODUCTION.equals(environment2)) {
            setLogLevel(LogLevel.INFO, environment2);
        } else {
            setLogLevel(LogLevel.SUPRESS, environment2);
        }
        if (context2 != null) {
            context2 = context2.getApplicationContext();
        }
        this.context = context2;
        this.appToken = appToken2;
        this.environment = environment2;
        this.eventBufferingEnabled = false;
        this.sendInBackground = false;
    }

    public void setEventBufferingEnabled(Boolean eventBufferingEnabled2) {
        if (eventBufferingEnabled2 == null) {
            this.eventBufferingEnabled = false;
        } else {
            this.eventBufferingEnabled = eventBufferingEnabled2.booleanValue();
        }
    }

    public void setSendInBackground(boolean sendInBackground2) {
        this.sendInBackground = sendInBackground2;
    }

    public void setLogLevel(LogLevel logLevel) {
        setLogLevel(logLevel, this.environment);
    }

    public void setSdkPrefix(String sdkPrefix2) {
        this.sdkPrefix = sdkPrefix2;
    }

    public void setProcessName(String processName2) {
        this.processName = processName2;
    }

    public void setDefaultTracker(String defaultTracker2) {
        this.defaultTracker = defaultTracker2;
    }

    public void setOnAttributionChangedListener(OnAttributionChangedListener onAttributionChangedListener2) {
        this.onAttributionChangedListener = onAttributionChangedListener2;
    }

    public void setDeviceKnown(boolean deviceKnown2) {
        this.deviceKnown = Boolean.valueOf(deviceKnown2);
    }

    public void setDeepLinkComponent(Class deepLinkComponent2) {
        this.deepLinkComponent = deepLinkComponent2;
    }

    public void setOnEventTrackingSucceededListener(OnEventTrackingSucceededListener onEventTrackingSucceededListener2) {
        this.onEventTrackingSucceededListener = onEventTrackingSucceededListener2;
    }

    public void setOnEventTrackingFailedListener(OnEventTrackingFailedListener onEventTrackingFailedListener2) {
        this.onEventTrackingFailedListener = onEventTrackingFailedListener2;
    }

    public void setOnSessionTrackingSucceededListener(OnSessionTrackingSucceededListener onSessionTrackingSucceededListener2) {
        this.onSessionTrackingSucceededListener = onSessionTrackingSucceededListener2;
    }

    public void setOnSessionTrackingFailedListener(OnSessionTrackingFailedListener onSessionTrackingFailedListener2) {
        this.onSessionTrackingFailedListener = onSessionTrackingFailedListener2;
    }

    public void setOnDeeplinkResponseListener(OnDeeplinkResponseListener onDeeplinkResponseListener2) {
        this.onDeeplinkResponseListener = onDeeplinkResponseListener2;
    }

    public void setDelayStart(double delayStart2) {
        this.delayStart = Double.valueOf(delayStart2);
    }

    public void setUserAgent(String userAgent2) {
        this.userAgent = userAgent2;
    }

    public void setAppSecret(long secretId2, long info1, long info2, long info3, long info4) {
        this.secretId = Util.formatString("%d", Long.valueOf(secretId2));
        this.appSecret = Util.formatString("%d%d%d%d", Long.valueOf(info1), Long.valueOf(info2), Long.valueOf(info3), Long.valueOf(info4));
    }

    public void setReadMobileEquipmentIdentity(boolean readMobileEquipmentIdentity2) {
        this.readMobileEquipmentIdentity = readMobileEquipmentIdentity2;
    }

    public boolean isValid() {
        if (checkAppToken(this.appToken) && checkEnvironment(this.environment) && checkContext(this.context)) {
            return true;
        }
        return false;
    }

    private void setLogLevel(LogLevel logLevel, String environment2) {
        this.logger.setLogLevel(logLevel, ENVIRONMENT_PRODUCTION.equals(environment2));
    }

    private boolean checkContext(Context context2) {
        if (context2 == null) {
            this.logger.error("Missing context", new Object[0]);
            return false;
        } else if (Util.checkPermission(context2, "android.permission.INTERNET")) {
            return true;
        } else {
            this.logger.error("Missing permission: INTERNET", new Object[0]);
            return false;
        }
    }

    private boolean checkAppToken(String appToken2) {
        if (appToken2 == null) {
            this.logger.error("Missing App Token", new Object[0]);
            return false;
        } else if (appToken2.length() == 12) {
            return true;
        } else {
            this.logger.error("Malformed App Token '%s'", appToken2);
            return false;
        }
    }

    private boolean checkEnvironment(String environment2) {
        if (environment2 == null) {
            this.logger.error("Missing environment", new Object[0]);
            return false;
        } else if (environment2.equals(ENVIRONMENT_SANDBOX)) {
            this.logger.warnInProduction("SANDBOX: Adjust is running in Sandbox mode. Use this setting for testing. Don't forget to set the environment to `production` before publishing!", new Object[0]);
            return true;
        } else if (environment2.equals(ENVIRONMENT_PRODUCTION)) {
            this.logger.warnInProduction("PRODUCTION: Adjust is running in Production mode. Use this setting only for the build that you want to publish. Set the environment to `sandbox` if you want to test your app!", new Object[0]);
            return true;
        } else {
            this.logger.error("Unknown environment '%s'", environment2);
            return false;
        }
    }
}
