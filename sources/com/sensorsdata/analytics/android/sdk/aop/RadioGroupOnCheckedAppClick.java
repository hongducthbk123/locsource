package com.sensorsdata.analytics.android.sdk.aop;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.sensorsdata.analytics.android.sdk.AopConstants;
import com.sensorsdata.analytics.android.sdk.R;
import com.sensorsdata.analytics.android.sdk.SALog;
import com.sensorsdata.analytics.android.sdk.SensorsDataAPI;
import com.sensorsdata.analytics.android.sdk.SensorsDataAPI.AutoTrackEventType;
import com.sensorsdata.analytics.android.sdk.util.AopUtil;
import org.aspectj.lang.JoinPoint;
import org.json.JSONObject;

public class RadioGroupOnCheckedAppClick {
    private static final String TAG = "RadioGroupOnCheckedAppClick";

    public static void onAppClick(JoinPoint joinPoint) {
        try {
            if (SensorsDataAPI.sharedInstance().isAutoTrackEnabled() && !SensorsDataAPI.sharedInstance().isAutoTrackEventTypeIgnored(AutoTrackEventType.APP_CLICK) && joinPoint != null && joinPoint.getArgs() != null && joinPoint.getArgs().length == 2) {
                View view = (View) joinPoint.getArgs()[0];
                if (view != null) {
                    Context context = view.getContext();
                    if (context != null) {
                        Activity activity = AopUtil.getActivityFromContext(context, view);
                        if ((activity == null || !SensorsDataAPI.sharedInstance().isActivityAutoTrackAppClickIgnored(activity.getClass())) && !AopUtil.isViewIgnored(view)) {
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
                            if (view instanceof RadioGroup) {
                                properties.put(AopConstants.ELEMENT_TYPE, "RadioGroup");
                                int checkedRadioButtonId = ((RadioGroup) view).getCheckedRadioButtonId();
                                if (activity != null) {
                                    try {
                                        RadioButton radioButton = (RadioButton) activity.findViewById(checkedRadioButtonId);
                                        if (radioButton != null && !TextUtils.isEmpty(radioButton.getText())) {
                                            String viewText = radioButton.getText().toString();
                                            if (!TextUtils.isEmpty(viewText)) {
                                                properties.put(AopConstants.ELEMENT_CONTENT, viewText);
                                            }
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            } else {
                                properties.put(AopConstants.ELEMENT_TYPE, view.getClass().getCanonicalName());
                            }
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
        } catch (Exception e2) {
            e2.printStackTrace();
            SALog.m1608i(TAG, "RadioGroup.OnCheckedChangeListener.onCheckedChanged AOP ERROR: " + e2.getMessage());
        }
    }
}
