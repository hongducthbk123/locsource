package com.sensorsdata.analytics.android.sdk.aop;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import com.sensorsdata.analytics.android.sdk.AopConstants;
import com.sensorsdata.analytics.android.sdk.R;
import com.sensorsdata.analytics.android.sdk.SALog;
import com.sensorsdata.analytics.android.sdk.SensorsDataAPI;
import com.sensorsdata.analytics.android.sdk.SensorsDataAPI.AutoTrackEventType;
import com.sensorsdata.analytics.android.sdk.util.AopUtil;
import org.aspectj.lang.JoinPoint;
import org.json.JSONObject;

public class SpinnerOnItemSelectedAppClick {
    private static final String TAG = "SpinnerOnItemSelectedAppClick";

    public static void onAppClick(JoinPoint joinPoint) {
        try {
            if (SensorsDataAPI.sharedInstance().isAutoTrackEnabled() && !SensorsDataAPI.sharedInstance().isAutoTrackEventTypeIgnored(AutoTrackEventType.APP_CLICK) && joinPoint != null && joinPoint.getArgs() != null && joinPoint.getArgs().length == 4) {
                AdapterView adapterView = (AdapterView) joinPoint.getArgs()[0];
                if (adapterView != null) {
                    Context context = adapterView.getContext();
                    if (context != null) {
                        Activity activity = AopUtil.getActivityFromContext(context, adapterView);
                        if ((activity == null || !SensorsDataAPI.sharedInstance().isActivityAutoTrackAppClickIgnored(activity.getClass())) && !AopUtil.isViewIgnored((View) adapterView)) {
                            int position = ((Integer) joinPoint.getArgs()[2]).intValue();
                            JSONObject properties = new JSONObject();
                            String idString = AopUtil.getViewId(adapterView);
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
                            if (adapterView instanceof Spinner) {
                                properties.put(AopConstants.ELEMENT_TYPE, "Spinner");
                                Object item = adapterView.getItemAtPosition(position);
                                properties.put(AopConstants.ELEMENT_POSITION, String.valueOf(position));
                                if (item != null && (item instanceof String)) {
                                    properties.put(AopConstants.ELEMENT_CONTENT, item);
                                }
                            } else {
                                properties.put(AopConstants.ELEMENT_TYPE, adapterView.getClass().getCanonicalName());
                            }
                            AopUtil.getFragmentNameFromView(adapterView, properties);
                            JSONObject p = (JSONObject) adapterView.getTag(R.id.sensors_analytics_tag_view_properties);
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
            SALog.m1608i(TAG, " AdapterView.OnItemSelectedListener.onItemSelected AOP ERROR: " + e.getMessage());
        }
    }
}
