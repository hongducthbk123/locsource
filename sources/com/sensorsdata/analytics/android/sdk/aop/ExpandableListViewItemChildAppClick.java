package com.sensorsdata.analytics.android.sdk.aop;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import com.sensorsdata.analytics.android.sdk.AopConstants;
import com.sensorsdata.analytics.android.sdk.R;
import com.sensorsdata.analytics.android.sdk.SALog;
import com.sensorsdata.analytics.android.sdk.SensorsDataAPI;
import com.sensorsdata.analytics.android.sdk.SensorsDataAPI.AutoTrackEventType;
import com.sensorsdata.analytics.android.sdk.SensorsExpandableListViewItemTrackProperties;
import com.sensorsdata.analytics.android.sdk.util.AopUtil;
import java.util.Locale;
import org.aspectj.lang.JoinPoint;
import org.json.JSONException;
import org.json.JSONObject;

public class ExpandableListViewItemChildAppClick {
    private static final String TAG = "ExpandableListViewItemChildAppClick";

    public static void onItemChildClick(JoinPoint joinPoint) {
        try {
            if (SensorsDataAPI.sharedInstance().isAutoTrackEnabled() && !SensorsDataAPI.sharedInstance().isAutoTrackEventTypeIgnored(AutoTrackEventType.APP_CLICK) && joinPoint != null && joinPoint.getArgs() != null && joinPoint.getArgs().length == 5) {
                ExpandableListView expandableListView = (ExpandableListView) joinPoint.getArgs()[0];
                if (expandableListView != null) {
                    Context context = expandableListView.getContext();
                    if (context != null) {
                        Activity activity = AopUtil.getActivityFromContext(context, expandableListView);
                        if ((activity == null || !SensorsDataAPI.sharedInstance().isActivityAutoTrackAppClickIgnored(activity.getClass())) && !AopUtil.isViewIgnored(ExpandableListView.class) && !AopUtil.isViewIgnored((View) expandableListView)) {
                            View view = (View) joinPoint.getArgs()[1];
                            if (!AopUtil.isViewIgnored(view)) {
                                int groupPosition = ((Integer) joinPoint.getArgs()[2]).intValue();
                                int childPosition = ((Integer) joinPoint.getArgs()[3]).intValue();
                                JSONObject properties = (JSONObject) view.getTag(R.id.sensors_analytics_tag_view_properties);
                                if (properties == null) {
                                    properties = new JSONObject();
                                }
                                properties.put(AopConstants.ELEMENT_POSITION, String.format(Locale.CHINA, "%d:%d", new Object[]{Integer.valueOf(groupPosition), Integer.valueOf(childPosition)}));
                                ExpandableListAdapter listAdapter = expandableListView.getExpandableListAdapter();
                                if (listAdapter != null && (listAdapter instanceof SensorsExpandableListViewItemTrackProperties)) {
                                    JSONObject jsonObject = ((SensorsExpandableListViewItemTrackProperties) listAdapter).getSensorsChildItemTrackProperties(groupPosition, childPosition);
                                    if (jsonObject != null) {
                                        AopUtil.mergeJSONObject(jsonObject, properties);
                                    }
                                }
                                if (activity != null) {
                                    properties.put(AopConstants.SCREEN_NAME, activity.getClass().getCanonicalName());
                                    String activityTitle = AopUtil.getActivityTitle(activity);
                                    if (!TextUtils.isEmpty(activityTitle)) {
                                        properties.put(AopConstants.TITLE, activityTitle);
                                    }
                                }
                                String idString = AopUtil.getViewId(expandableListView);
                                if (!TextUtils.isEmpty(idString)) {
                                    properties.put(AopConstants.ELEMENT_ID, idString);
                                }
                                properties.put(AopConstants.ELEMENT_TYPE, "ExpandableListView");
                                String viewText = null;
                                if (view instanceof ViewGroup) {
                                    try {
                                        viewText = AopUtil.traverseView(new StringBuilder(), (ViewGroup) view);
                                        if (!TextUtils.isEmpty(viewText)) {
                                            viewText = viewText.substring(0, viewText.length() - 1);
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                                if (!TextUtils.isEmpty(viewText)) {
                                    properties.put(AopConstants.ELEMENT_CONTENT, viewText);
                                }
                                AopUtil.getFragmentNameFromView(expandableListView, properties);
                                JSONObject p = (JSONObject) view.getTag(R.id.sensors_analytics_tag_view_properties);
                                if (p != null) {
                                    AopUtil.mergeJSONObject(p, properties);
                                }
                                SensorsDataAPI.sharedInstance().track(AopConstants.APP_CLICK_EVENT_NAME, properties);
                            }
                        }
                    }
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            SALog.m1608i(TAG, " ExpandableListView.OnChildClickListener.onChildClick AOP ERROR: " + e2.getMessage());
        }
    }

    public static void onItemGroupClick(JoinPoint joinPoint) {
        try {
            if (SensorsDataAPI.sharedInstance().isAutoTrackEnabled() && !SensorsDataAPI.sharedInstance().isAutoTrackEventTypeIgnored(AutoTrackEventType.APP_CLICK) && joinPoint != null && joinPoint.getArgs() != null && joinPoint.getArgs().length == 4) {
                ExpandableListView expandableListView = (ExpandableListView) joinPoint.getArgs()[0];
                if (expandableListView != null) {
                    Context context = expandableListView.getContext();
                    if (context != null) {
                        Activity activity = null;
                        if (context instanceof Activity) {
                            activity = (Activity) context;
                        }
                        if ((activity == null || !SensorsDataAPI.sharedInstance().isActivityAutoTrackAppClickIgnored(activity.getClass())) && !AopUtil.isViewIgnored(joinPoint.getArgs()[0].getClass()) && !AopUtil.isViewIgnored((View) expandableListView)) {
                            View view = (View) joinPoint.getArgs()[1];
                            int groupPosition = ((Integer) joinPoint.getArgs()[2]).intValue();
                            JSONObject properties = new JSONObject();
                            if (activity != null) {
                                properties.put(AopConstants.SCREEN_NAME, activity.getClass().getCanonicalName());
                                String activityTitle = AopUtil.getActivityTitle(activity);
                                if (!TextUtils.isEmpty(activityTitle)) {
                                    properties.put(AopConstants.TITLE, activityTitle);
                                }
                            }
                            String idString = AopUtil.getViewId(expandableListView);
                            if (!TextUtils.isEmpty(idString)) {
                                properties.put(AopConstants.ELEMENT_ID, idString);
                            }
                            properties.put(AopConstants.ELEMENT_TYPE, "ExpandableListView");
                            AopUtil.getFragmentNameFromView(expandableListView, properties);
                            JSONObject p = (JSONObject) view.getTag(R.id.sensors_analytics_tag_view_properties);
                            if (p != null) {
                                AopUtil.mergeJSONObject(p, properties);
                            }
                            ExpandableListAdapter listAdapter = expandableListView.getExpandableListAdapter();
                            if (listAdapter != null && (listAdapter instanceof SensorsExpandableListViewItemTrackProperties)) {
                                try {
                                    JSONObject jsonObject = ((SensorsExpandableListViewItemTrackProperties) listAdapter).getSensorsGroupItemTrackProperties(groupPosition);
                                    if (jsonObject != null) {
                                        AopUtil.mergeJSONObject(jsonObject, properties);
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            SensorsDataAPI.sharedInstance().track(AopConstants.APP_CLICK_EVENT_NAME, properties);
                        }
                    }
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            SALog.m1608i(TAG, " ExpandableListView.OnChildClickListener.onGroupClick AOP ERROR: " + e2.getMessage());
        }
    }
}
