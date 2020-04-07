package com.sensorsdata.analytics.android.sdk;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;
import android.text.TextUtils;
import com.sensorsdata.analytics.android.sdk.SensorsDataAPI.AutoTrackEventType;
import com.sensorsdata.analytics.android.sdk.util.SensorsDataUtils;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;

@TargetApi(14)
class SensorsDataActivityLifecycleCallbacks implements ActivityLifecycleCallbacks {
    private static final String TAG = "SA.LifecycleCallbacks";
    private final Object mActivityLifecycleCallbacksLock = new Object();
    private final PersistentFirstStart mFirstStart;
    private final String mMainProcessName;
    private final SensorsDataAPI mSensorsDataInstance;
    private boolean resumeFromBackground = false;
    private Integer startedActivityCount = Integer.valueOf(0);

    public SensorsDataActivityLifecycleCallbacks(SensorsDataAPI instance, PersistentFirstStart firstStart, String mainProcessName) {
        this.mSensorsDataInstance = instance;
        this.mFirstStart = firstStart;
        this.mMainProcessName = mainProcessName;
    }

    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    public void onActivityStarted(Activity activity) {
        try {
            synchronized (this.mActivityLifecycleCallbacksLock) {
                if (this.startedActivityCount.intValue() == 0) {
                    boolean firstStart = ((Boolean) this.mFirstStart.get()).booleanValue();
                    if (firstStart) {
                        this.mFirstStart.commit(Boolean.valueOf(false));
                    }
                    try {
                        this.mSensorsDataInstance.appBecomeActive();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (SensorsDataUtils.isMainProcess(activity, this.mMainProcessName)) {
                        if (this.mSensorsDataInstance.isAutoTrackEnabled()) {
                            try {
                                if (!this.mSensorsDataInstance.isAutoTrackEventTypeIgnored(AutoTrackEventType.APP_START)) {
                                    JSONObject properties = new JSONObject();
                                    properties.put("$resume_from_background", this.resumeFromBackground);
                                    properties.put("$is_first_time", firstStart);
                                    SensorsDataUtils.getScreenNameAndTitleFromActivity(properties, activity);
                                    this.mSensorsDataInstance.track("$AppStart", properties);
                                }
                                if (!this.mSensorsDataInstance.isAutoTrackEventTypeIgnored(AutoTrackEventType.APP_END)) {
                                    this.mSensorsDataInstance.trackTimer("$AppEnd", TimeUnit.SECONDS);
                                }
                            } catch (Exception e2) {
                                SALog.m1610i(TAG, (Throwable) e2);
                            }
                        }
                        this.resumeFromBackground = true;
                    }
                }
                this.startedActivityCount = Integer.valueOf(this.startedActivityCount.intValue() + 1);
            }
            return;
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }

    public void onActivityResumed(Activity activity) {
        boolean mShowAutoTrack = true;
        try {
            if (this.mSensorsDataInstance.isActivityAutoTrackAppViewScreenIgnored(activity.getClass())) {
                mShowAutoTrack = false;
            }
            if (this.mSensorsDataInstance.isAutoTrackEnabled() && mShowAutoTrack && !this.mSensorsDataInstance.isAutoTrackEventTypeIgnored(AutoTrackEventType.APP_VIEW_SCREEN)) {
                try {
                    JSONObject properties = new JSONObject();
                    properties.put(AopConstants.SCREEN_NAME, activity.getClass().getCanonicalName());
                    SensorsDataUtils.getScreenNameAndTitleFromActivity(properties, activity);
                    if (activity instanceof ScreenAutoTracker) {
                        ScreenAutoTracker screenAutoTracker = (ScreenAutoTracker) activity;
                        String screenUrl = screenAutoTracker.getScreenUrl();
                        JSONObject otherProperties = screenAutoTracker.getTrackProperties();
                        if (otherProperties != null) {
                            SensorsDataUtils.mergeJSONObject(otherProperties, properties);
                        }
                        this.mSensorsDataInstance.trackViewScreen(screenUrl, properties);
                        return;
                    }
                    SensorsDataAutoTrackAppViewScreenUrl autoTrackAppViewScreenUrl = (SensorsDataAutoTrackAppViewScreenUrl) activity.getClass().getAnnotation(SensorsDataAutoTrackAppViewScreenUrl.class);
                    if (autoTrackAppViewScreenUrl != null) {
                        String screenUrl2 = autoTrackAppViewScreenUrl.url();
                        if (TextUtils.isEmpty(screenUrl2)) {
                            screenUrl2 = activity.getClass().getCanonicalName();
                        }
                        this.mSensorsDataInstance.trackViewScreen(screenUrl2, properties);
                        return;
                    }
                    this.mSensorsDataInstance.track("$AppViewScreen", properties);
                } catch (Exception e) {
                    SALog.m1610i(TAG, (Throwable) e);
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void onActivityPaused(Activity activity) {
    }

    public void onActivityStopped(Activity activity) {
        try {
            synchronized (this.mActivityLifecycleCallbacksLock) {
                this.startedActivityCount = Integer.valueOf(this.startedActivityCount.intValue() - 1);
                if (this.startedActivityCount.intValue() == 0) {
                    try {
                        this.mSensorsDataInstance.appEnterBackground();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (SensorsDataUtils.isMainProcess(activity, this.mMainProcessName) && this.mSensorsDataInstance.isAutoTrackEnabled()) {
                        try {
                            if (!this.mSensorsDataInstance.isAutoTrackEventTypeIgnored(AutoTrackEventType.APP_END)) {
                                JSONObject properties = new JSONObject();
                                SensorsDataUtils.getScreenNameAndTitleFromActivity(properties, activity);
                                this.mSensorsDataInstance.track("$AppEnd", properties);
                            }
                        } catch (Exception e2) {
                            SALog.m1610i(TAG, (Throwable) e2);
                        }
                    }
                    try {
                        this.mSensorsDataInstance.flush();
                    } catch (Exception e3) {
                        e3.printStackTrace();
                    }
                }
            }
        } catch (Exception e4) {
            e4.printStackTrace();
        }
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public void onActivityDestroyed(Activity activity) {
    }
}
