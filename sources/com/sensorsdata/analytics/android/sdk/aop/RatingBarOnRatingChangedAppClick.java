package com.sensorsdata.analytics.android.sdk.aop;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import com.sensorsdata.analytics.android.sdk.AopConstants;
import com.sensorsdata.analytics.android.sdk.R;
import com.sensorsdata.analytics.android.sdk.SALog;
import com.sensorsdata.analytics.android.sdk.SensorsDataAPI;
import com.sensorsdata.analytics.android.sdk.SensorsDataAPI.AutoTrackEventType;
import com.sensorsdata.analytics.android.sdk.util.AopUtil;
import org.aspectj.lang.JoinPoint;
import org.json.JSONObject;

public class RatingBarOnRatingChangedAppClick {
    private static final String TAG = "RatingBarOnRatingChangedAppClick";

    public static void onAppClick(JoinPoint joinPoint) {
        try {
            if (SensorsDataAPI.sharedInstance().isAutoTrackEnabled() && !SensorsDataAPI.sharedInstance().isAutoTrackEventTypeIgnored(AutoTrackEventType.APP_CLICK) && joinPoint != null && joinPoint.getArgs() != null && joinPoint.getArgs().length == 3) {
                View view = (View) joinPoint.getArgs()[0];
                if (view != null) {
                    Context context = view.getContext();
                    if (context != null) {
                        Activity activity = AopUtil.getActivityFromContext(context, view);
                        if ((activity == null || !SensorsDataAPI.sharedInstance().isActivityAutoTrackAppClickIgnored(activity.getClass())) && !AopUtil.isViewIgnored(view)) {
                            float rating = ((Float) joinPoint.getArgs()[1]).floatValue();
                            JSONObject properties = new JSONObject();
                            String idString = AopUtil.getViewId(view);
                            if (!TextUtils.isEmpty(idString)) {
                                properties.put(AopConstants.ELEMENT_ID, idString);
                            }
                            if (activity != null) {
                                properties.put(AopConstants.SCREEN_NAME, activity.getClass().getCanonicalName());
                                String activityTitle = AopUtil.getActivityTitle(activity);
                                if (!TextUtils.isEmpty(activityTitle)) {
                                    properties.put(AopConstants.TITLE, activityTitle);
                                }
                            }
                            properties.put(AopConstants.ELEMENT_TYPE, "RatingBar");
                            properties.put(AopConstants.ELEMENT_CONTENT, String.valueOf(rating));
                            AopUtil.getFragmentNameFromView(view, properties);
                            JSONObject p = (JSONObject) view.getTag(R.id.sensors_analytics_tag_view_properties);
                            if (p != null) {
                                AopUtil.mergeJSONObject(p, properties);
                            }
                            SensorsDataAPI.sharedInstance().track(AopConstants.APP_CLICK_EVENT_NAME, properties);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            SALog.m1608i(TAG, "RatingBar.OnRatingBarChangeListener.onRatingChanged AOP ERROR: " + e.getMessage());
        }
    }
}
