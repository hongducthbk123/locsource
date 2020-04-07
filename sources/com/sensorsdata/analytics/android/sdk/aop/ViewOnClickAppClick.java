package com.sensorsdata.analytics.android.sdk.aop;

import android.app.Activity;
import android.content.Context;
import android.support.p003v7.widget.SwitchCompat;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
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
import com.sensorsdata.analytics.android.sdk.SensorsDataIgnoreTrackOnClick;
import com.sensorsdata.analytics.android.sdk.util.AopUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.json.JSONObject;

public class ViewOnClickAppClick {
    private static final String TAG = "ViewOnClickAppClick";

    public static void onAppClick(JoinPoint joinPoint) {
        try {
            if (joinPoint.getArgs() != null && joinPoint.getArgs().length == 1 && SensorsDataAPI.sharedInstance().isAutoTrackEnabled() && ((SensorsDataIgnoreTrackOnClick) ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(SensorsDataIgnoreTrackOnClick.class)) == null && !SensorsDataAPI.sharedInstance().isAutoTrackEventTypeIgnored(AutoTrackEventType.APP_CLICK)) {
                View view = (View) joinPoint.getArgs()[0];
                if (view != null) {
                    Context context = view.getContext();
                    if (context == null) {
                    }
                    Activity activity = AopUtil.getActivityFromContext(context, view);
                    if ((activity == null || !SensorsDataAPI.sharedInstance().isActivityAutoTrackAppClickIgnored(activity.getClass())) && !AopUtil.isViewIgnored(view)) {
                        long currentOnClickTimestamp = System.currentTimeMillis();
                        String tag = (String) view.getTag(R.id.sensors_analytics_tag_view_onclick_timestamp);
                        if (!TextUtils.isEmpty(tag)) {
                            try {
                                if (currentOnClickTimestamp - Long.parseLong(tag) < 500) {
                                    SALog.m1608i(TAG, "This onClick maybe extends from super, IGNORE");
                                    return;
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        view.setTag(R.id.sensors_analytics_tag_view_onclick_timestamp, String.valueOf(currentOnClickTimestamp));
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
                        } else if (view instanceof SwitchCompat) {
                            viewType = "SwitchCompat";
                            SwitchCompat switchCompat = (SwitchCompat) view;
                            boolean isChecked = switchCompat.isChecked();
                            viewText = switchCompat.getTextOn();
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
                        } else if (view instanceof ViewGroup) {
                            try {
                                viewText = AopUtil.traverseView(new StringBuilder(), (ViewGroup) view);
                                if (!TextUtils.isEmpty(viewText)) {
                                    viewText = viewText.toString().substring(0, viewText.length() - 1);
                                }
                            } catch (Exception e2) {
                                e2.printStackTrace();
                            }
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
        } catch (Exception e3) {
            e3.printStackTrace();
            SALog.m1608i(TAG, "onViewClickMethod error: " + e3.getMessage());
        }
    }
}
