package com.sensorsdata.analytics.android.sdk.aop;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import com.sensorsdata.analytics.android.sdk.AopConstants;
import com.sensorsdata.analytics.android.sdk.SALog;
import com.sensorsdata.analytics.android.sdk.SensorsDataAPI;
import com.sensorsdata.analytics.android.sdk.SensorsDataAPI.AutoTrackEventType;
import com.sensorsdata.analytics.android.sdk.util.AopUtil;
import java.lang.reflect.Method;
import org.aspectj.lang.JoinPoint;
import org.json.JSONObject;

public class ReactNativeViewAppClick {
    private static final String TAG = "ReactNativeViewAppClick";

    public static void onAppClick(JoinPoint joinPoint) {
        try {
            if (SensorsDataAPI.sharedInstance().isReactNativeAutoTrackEnabled() && SensorsDataAPI.sharedInstance().isAutoTrackEnabled() && !SensorsDataAPI.sharedInstance().isAutoTrackEventTypeIgnored(AutoTrackEventType.APP_CLICK)) {
                int reactTag = ((Integer) joinPoint.getArgs()[0]).intValue();
                int intValue = ((Integer) joinPoint.getArgs()[1]).intValue();
                int intValue2 = ((Integer) joinPoint.getArgs()[1]).intValue();
                Object target = joinPoint.getTarget();
                JSONObject properties = new JSONObject();
                properties.put(AopConstants.ELEMENT_TYPE, "RNView");
                if (target != null) {
                    Method resolveViewMethod = Class.forName("com.facebook.react.uimanager.NativeViewHierarchyManager").getMethod("resolveView", new Class[]{Integer.TYPE});
                    if (resolveViewMethod != null) {
                        Object object = resolveViewMethod.invoke(target, new Object[]{Integer.valueOf(reactTag)});
                        if (object != null) {
                            View view = (View) object;
                            Context context = view.getContext();
                            if (context == null) {
                            }
                            Activity activity = AopUtil.getActivityFromContext(context, view);
                            if (activity != null) {
                                properties.put(AopConstants.SCREEN_NAME, activity.getClass().getCanonicalName());
                                String activityTitle = AopUtil.getActivityTitle(activity);
                                if (!TextUtils.isEmpty(activityTitle)) {
                                    properties.put(AopConstants.TITLE, activityTitle);
                                }
                            }
                            if (view instanceof CompoundButton) {
                                return;
                            }
                            if (view instanceof TextView) {
                                TextView textView = (TextView) view;
                                if (!TextUtils.isEmpty(textView.getText())) {
                                    properties.put(AopConstants.ELEMENT_CONTENT, textView.getText().toString());
                                }
                            } else if (view instanceof ViewGroup) {
                                String viewText = AopUtil.traverseView(new StringBuilder(), (ViewGroup) view);
                                if (!TextUtils.isEmpty(viewText)) {
                                    viewText = viewText.substring(0, viewText.length() - 1);
                                }
                                properties.put(AopConstants.ELEMENT_CONTENT, viewText);
                            }
                        }
                    }
                }
                SensorsDataAPI.sharedInstance().track(AopConstants.APP_CLICK_EVENT_NAME, properties);
            }
        } catch (Exception e) {
            e.printStackTrace();
            SALog.m1608i(TAG, "onNativeViewHierarchyManagerSetJSResponderAOP error: " + e.getMessage());
        }
    }
}
