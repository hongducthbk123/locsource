package com.sensorsdata.analytics.android.sdk.aop;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.ToggleButton;
import com.sensorsdata.analytics.android.sdk.AopConstants;
import com.sensorsdata.analytics.android.sdk.R;
import com.sensorsdata.analytics.android.sdk.SALog;
import com.sensorsdata.analytics.android.sdk.SensorsDataAPI;
import com.sensorsdata.analytics.android.sdk.SensorsDataAPI.AutoTrackEventType;
import com.sensorsdata.analytics.android.sdk.util.AopUtil;
import org.aspectj.lang.JoinPoint;
import org.json.JSONObject;

public class TrackViewOnAppClick {
    private static final String TAG = "TrackViewOnAppClick";

    public static void onAppClick(JoinPoint joinPoint) {
        try {
            if (SensorsDataAPI.sharedInstance().isAutoTrackEnabled() && !SensorsDataAPI.sharedInstance().isAutoTrackEventTypeIgnored(AutoTrackEventType.APP_CLICK) && joinPoint != null && joinPoint.getArgs() != null && joinPoint.getArgs().length == 1) {
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
                            String viewType = view.getClass().getCanonicalName();
                            CharSequence viewText = null;
                            if (view instanceof CheckBox) {
                                viewType = "CheckBox";
                                viewText = ((CheckBox) view).getText();
                            } else if (view instanceof RadioButton) {
                                viewType = "RadioButton";
                                viewText = ((RadioButton) view).getText();
                            } else if (view instanceof ToggleButton) {
                                viewType = "ToggleButton";
                                ToggleButton toggleButton = (ToggleButton) view;
                                viewText = toggleButton.isChecked() ? toggleButton.getTextOn() : toggleButton.getTextOff();
                            } else if (view instanceof Button) {
                                viewType = "Button";
                                viewText = ((Button) view).getText();
                            } else if (view instanceof CheckedTextView) {
                                viewType = "CheckedTextView";
                                viewText = ((CheckedTextView) view).getText();
                            } else if (view instanceof TextView) {
                                viewType = "TextView";
                                viewText = ((TextView) view).getText();
                            } else if (view instanceof ImageButton) {
                                viewType = "ImageButton";
                            } else if (view instanceof ImageView) {
                                viewType = "ImageView";
                            }
                            if (!TextUtils.isEmpty(viewText)) {
                                properties.put(AopConstants.ELEMENT_CONTENT, viewText.toString());
                            }
                            properties.put(AopConstants.ELEMENT_TYPE, viewType);
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
            SALog.m1608i(TAG, "TrackViewOnClick error: " + e.getMessage());
        }
    }
}
