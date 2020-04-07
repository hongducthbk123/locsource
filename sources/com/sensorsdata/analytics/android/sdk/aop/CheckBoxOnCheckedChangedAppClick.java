package com.sensorsdata.analytics.android.sdk.aop;

import android.app.Activity;
import android.content.Context;
import android.support.p003v7.widget.SwitchCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.ToggleButton;
import com.sensorsdata.analytics.android.sdk.AopConstants;
import com.sensorsdata.analytics.android.sdk.R;
import com.sensorsdata.analytics.android.sdk.SALog;
import com.sensorsdata.analytics.android.sdk.SensorsDataAPI;
import com.sensorsdata.analytics.android.sdk.SensorsDataAPI.AutoTrackEventType;
import com.sensorsdata.analytics.android.sdk.util.AopUtil;
import org.aspectj.lang.JoinPoint;
import org.json.JSONObject;

public class CheckBoxOnCheckedChangedAppClick {
    private static final String TAG = "CheckBoxOnCheckedChangedAppClick";

    public static void onAppClick(JoinPoint joinPoint) {
        try {
            if (SensorsDataAPI.sharedInstance().isAutoTrackEnabled() && !SensorsDataAPI.sharedInstance().isAutoTrackEventTypeIgnored(AutoTrackEventType.APP_CLICK) && joinPoint != null && joinPoint.getArgs() != null && joinPoint.getArgs().length == 2) {
                View view = (View) joinPoint.getArgs()[0];
                if (view != null) {
                    Context context = view.getContext();
                    if (context != null) {
                        Activity activity = AopUtil.getActivityFromContext(context, view);
                        if ((activity == null || !SensorsDataAPI.sharedInstance().isActivityAutoTrackAppClickIgnored(activity.getClass())) && !AopUtil.isViewIgnored(view)) {
                            boolean isChecked = ((Boolean) joinPoint.getArgs()[1]).booleanValue();
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
                            String viewText = null;
                            if (view instanceof CheckBox) {
                                properties.put(AopConstants.ELEMENT_TYPE, "CheckBox");
                                CompoundButton compoundButton = (CompoundButton) view;
                                if (!TextUtils.isEmpty(compoundButton.getText())) {
                                    viewText = compoundButton.getText().toString();
                                }
                            } else if (view instanceof SwitchCompat) {
                                properties.put(AopConstants.ELEMENT_TYPE, "SwitchCompat");
                                SwitchCompat switchCompat = (SwitchCompat) view;
                                if (!TextUtils.isEmpty(switchCompat.getTextOn())) {
                                    viewText = switchCompat.getTextOn().toString();
                                }
                            } else if (view instanceof ToggleButton) {
                                properties.put(AopConstants.ELEMENT_TYPE, "ToggleButton");
                                ToggleButton toggleButton = (ToggleButton) view;
                                if (isChecked) {
                                    if (!TextUtils.isEmpty(toggleButton.getTextOn())) {
                                        viewText = toggleButton.getTextOn().toString();
                                    }
                                } else if (!TextUtils.isEmpty(toggleButton.getTextOff())) {
                                    viewText = toggleButton.getTextOff().toString();
                                }
                            } else if (view instanceof RadioButton) {
                                properties.put(AopConstants.ELEMENT_TYPE, "RadioButton");
                                RadioButton radioButton = (RadioButton) view;
                                if (!TextUtils.isEmpty(radioButton.getText())) {
                                    viewText = radioButton.getText().toString();
                                }
                            } else {
                                properties.put(AopConstants.ELEMENT_TYPE, view.getClass().getCanonicalName());
                            }
                            if (!TextUtils.isEmpty(viewText)) {
                                properties.put(AopConstants.ELEMENT_CONTENT, viewText);
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
        } catch (Exception e) {
            e.printStackTrace();
            SALog.m1608i(TAG, " onCheckedChanged AOP ERROR: " + e.getMessage());
        }
    }
}
