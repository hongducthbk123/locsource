package com.btgame.onesdk.btadjust;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import com.adjust.sdk.Adjust;
import com.adjust.sdk.AdjustAttribution;
import com.adjust.sdk.AdjustConfig;
import com.adjust.sdk.AdjustEvent;
import com.adjust.sdk.AdjustEventFailure;
import com.adjust.sdk.AdjustEventSuccess;
import com.adjust.sdk.AdjustSessionFailure;
import com.adjust.sdk.AdjustSessionSuccess;
import com.adjust.sdk.LogLevel;
import com.adjust.sdk.OnAttributionChangedListener;
import com.adjust.sdk.OnDeeplinkResponseListener;
import com.adjust.sdk.OnEventTrackingFailedListener;
import com.adjust.sdk.OnEventTrackingSucceededListener;
import com.adjust.sdk.OnSessionTrackingFailedListener;
import com.adjust.sdk.OnSessionTrackingSucceededListener;
import com.btgame.sdk.util.BtUtils;
import com.facebook.appevents.AppEventsConstants;

public class AdjustImpl implements IAdjustImpl {
    private static final String KEY_IMEI = "imei";
    private static final String KEY_PLATFORM = "platformId";
    private static final String KEY_TERMINAL = "terminal";
    private static final String TAG = "AdjustBaitian";
    private static AdjustImpl instance;
    private String mIMEI = null;
    private String mTracker = null;

    public static AdjustImpl getInstance() {
        if (instance == null) {
            instance = new AdjustImpl();
        }
        return instance;
    }

    private AdjustImpl() {
    }

    public void onApplicationCreate(Context context, String appToken, boolean isSandBox) {
        AdjustConfig config = new AdjustConfig(context, appToken, isSandBox ? AdjustConfig.ENVIRONMENT_SANDBOX : AdjustConfig.ENVIRONMENT_PRODUCTION);
        config.setLogLevel(LogLevel.VERBOSE);
        config.setDelayStart(5.0d);
        config.setOnAttributionChangedListener(new OnAttributionChangedListener() {
            public void onAttributionChanged(AdjustAttribution attribution) {
                Log.d(AdjustImpl.TAG, "onAttributionChanged: " + attribution.toString());
            }
        });
        config.setOnEventTrackingSucceededListener(new OnEventTrackingSucceededListener() {
            public void onFinishedEventTrackingSucceeded(AdjustEventSuccess eventSuccessResponseData) {
                Log.d(AdjustImpl.TAG, "onFinishedEventTrackingSucceeded: " + eventSuccessResponseData.toString());
            }
        });
        config.setOnEventTrackingFailedListener(new OnEventTrackingFailedListener() {
            public void onFinishedEventTrackingFailed(AdjustEventFailure eventFailureResponseData) {
                Log.d(AdjustImpl.TAG, "onFinishedEventTrackingFailed: " + eventFailureResponseData.toString());
            }
        });
        config.setOnSessionTrackingSucceededListener(new OnSessionTrackingSucceededListener() {
            public void onFinishedSessionTrackingSucceeded(AdjustSessionSuccess sessionSuccessResponseData) {
                Log.d(AdjustImpl.TAG, "onFinishedSessionTrackingSucceeded: " + sessionSuccessResponseData.toString());
            }
        });
        config.setOnSessionTrackingFailedListener(new OnSessionTrackingFailedListener() {
            public void onFinishedSessionTrackingFailed(AdjustSessionFailure sessionFailureResponseData) {
                Log.d(AdjustImpl.TAG, "onFinishedSessionTrackingFailed: " + sessionFailureResponseData.toString());
            }
        });
        config.setOnDeeplinkResponseListener(new OnDeeplinkResponseListener() {
            public boolean launchReceivedDeeplink(Uri deeplink) {
                Log.d(AdjustImpl.TAG, "launchReceivedDeepLink: " + deeplink);
                return true;
            }
        });
        config.setSendInBackground(true);
        if (TextUtils.isEmpty(this.mTracker)) {
            Log.d(TAG, "警告！未设置DefaultTracker！");
        } else {
            config.setDefaultTracker(this.mTracker);
        }
        Adjust.onCreate(config);
        Adjust.addSessionCallbackParameter(KEY_TERMINAL, AppEventsConstants.EVENT_PARAM_VALUE_NO);
        String imei = TextUtils.isEmpty(this.mIMEI) ? BtUtils.getIMEI(context) : this.mIMEI;
        Adjust.addSessionCallbackParameter(KEY_IMEI, imei);
        Log.d(TAG, "Adjust get IMEI: " + imei);
    }

    public void onResume() {
        Adjust.onResume();
    }

    public void onPause() {
        Adjust.onPause();
    }

    public void onEventAction(int platformId, String eventToken) {
        Adjust.trackEvent(new AdjustEvent(eventToken));
    }

    public void onActiveNew(int platformId, String eventToken, String orderId, float money, String unit) {
        AdjustEvent event = new AdjustEvent(eventToken);
        event.setOrderId(orderId);
        event.setRevenue((double) money, unit);
        Adjust.trackEvent(event);
    }

    public String getAdjustAdId() {
        return Adjust.getAdid();
    }

    public void setDefaultTracker(String tracker) {
        this.mTracker = tracker;
    }

    public void setIMEI(String imei) {
        this.mIMEI = imei;
    }
}
