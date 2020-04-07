package com.sensorsdata.analytics.android.sdk.aop;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.MenuItem;
import com.sensorsdata.analytics.android.sdk.AopConstants;
import com.sensorsdata.analytics.android.sdk.SALog;
import com.sensorsdata.analytics.android.sdk.SensorsDataAPI;
import com.sensorsdata.analytics.android.sdk.SensorsDataAPI.AutoTrackEventType;
import com.sensorsdata.analytics.android.sdk.util.AopUtil;
import org.aspectj.lang.JoinPoint;
import org.json.JSONObject;

public class MenuItemAppClick {
    private static final String TAG = "MenuItemAppClick";

    public static void onAppClick(JoinPoint joinPoint, int menuItemIndex) {
        try {
            if (SensorsDataAPI.sharedInstance().isAutoTrackEnabled() && !SensorsDataAPI.sharedInstance().isAutoTrackEventTypeIgnored(AutoTrackEventType.APP_CLICK) && joinPoint != null && joinPoint.getArgs() != null && joinPoint.getArgs().length != 0) {
                MenuItem menuItem = (MenuItem) joinPoint.getArgs()[menuItemIndex];
                if (menuItem != null && !AopUtil.isViewIgnored(MenuItem.class)) {
                    Object object = joinPoint.getTarget();
                    if (object != null) {
                        Context context = null;
                        if (object instanceof Context) {
                            context = (Context) object;
                        }
                        if (context != null) {
                            Activity activity = AopUtil.getActivityFromContext(context, null);
                            if (activity == null || !SensorsDataAPI.sharedInstance().isActivityAutoTrackAppClickIgnored(activity.getClass())) {
                                String idString = null;
                                try {
                                    idString = context.getResources().getResourceEntryName(menuItem.getItemId());
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                JSONObject properties = new JSONObject();
                                if (activity != null) {
                                    properties.put(AopConstants.SCREEN_NAME, activity.getClass().getCanonicalName());
                                    String activityTitle = AopUtil.getActivityTitle(activity);
                                    if (!TextUtils.isEmpty(activityTitle)) {
                                        properties.put(AopConstants.TITLE, activityTitle);
                                    }
                                }
                                if (!TextUtils.isEmpty(idString)) {
                                    properties.put(AopConstants.ELEMENT_ID, idString);
                                }
                                if (!TextUtils.isEmpty(menuItem.getTitle())) {
                                    properties.put(AopConstants.ELEMENT_CONTENT, menuItem.getTitle());
                                }
                                properties.put(AopConstants.ELEMENT_TYPE, "MenuItem");
                                SensorsDataAPI.sharedInstance().track(AopConstants.APP_CLICK_EVENT_NAME, properties);
                            }
                        }
                    }
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            SALog.m1608i(TAG, " error: " + e2.getMessage());
        }
    }
}
