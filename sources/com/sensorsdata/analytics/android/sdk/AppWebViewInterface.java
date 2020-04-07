package com.sensorsdata.analytics.android.sdk;

import android.content.Context;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import org.json.JSONException;
import org.json.JSONObject;

class AppWebViewInterface {
    private static final String TAG = "SA.AppWebViewInterface";
    private Context mContext;
    private JSONObject properties;

    AppWebViewInterface(Context c, JSONObject p) {
        this.mContext = c;
        this.properties = p;
    }

    @JavascriptInterface
    public String sensorsdata_call_app() {
        try {
            if (this.properties == null) {
                this.properties = new JSONObject();
            }
            this.properties.put("type", "Android");
            String loginId = SensorsDataAPI.sharedInstance(this.mContext).getLoginId();
            if (!TextUtils.isEmpty(loginId)) {
                this.properties.put("distinct_id", loginId);
                this.properties.put("is_login", true);
            } else {
                this.properties.put("distinct_id", SensorsDataAPI.sharedInstance(this.mContext).getAnonymousId());
                this.properties.put("is_login", false);
            }
            return this.properties.toString();
        } catch (JSONException e) {
            SALog.m1608i(TAG, e.getMessage());
            return null;
        }
    }

    @JavascriptInterface
    public void sensorsdata_track(String event) {
        SensorsDataAPI.sharedInstance(this.mContext).trackEventFromH5(event);
    }
}
