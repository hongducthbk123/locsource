package com.sensorsdata.analytics.android.sdk.aop;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import com.sensorsdata.analytics.android.sdk.AopConstants;
import com.sensorsdata.analytics.android.sdk.R;
import com.sensorsdata.analytics.android.sdk.SALog;
import com.sensorsdata.analytics.android.sdk.SensorsAdapterViewItemTrackProperties;
import com.sensorsdata.analytics.android.sdk.SensorsDataAPI;
import com.sensorsdata.analytics.android.sdk.SensorsDataAPI.AutoTrackEventType;
import com.sensorsdata.analytics.android.sdk.util.AopUtil;
import org.aspectj.lang.JoinPoint;
import org.json.JSONException;
import org.json.JSONObject;

public class AdapterViewOnItemClickListenerAppClick {
    private static final String TAG = "AdapterViewOnItemClickListenerAppClick";

    public static void onAppClick(JoinPoint joinPoint) {
        try {
            if (SensorsDataAPI.sharedInstance().isAutoTrackEnabled() && !SensorsDataAPI.sharedInstance().isAutoTrackEventTypeIgnored(AutoTrackEventType.APP_CLICK) && joinPoint != null && joinPoint.getArgs() != null && joinPoint.getArgs().length == 4) {
                Object object = joinPoint.getArgs()[0];
                if (object != null) {
                    View view = (View) joinPoint.getArgs()[1];
                    if (view != null) {
                        Context context = view.getContext();
                        if (context != null) {
                            Activity activity = AopUtil.getActivityFromContext(context, view);
                            if ((activity == null || !SensorsDataAPI.sharedInstance().isActivityAutoTrackAppClickIgnored(activity.getClass())) && !AopUtil.isViewIgnored(object.getClass())) {
                                int position = ((Integer) joinPoint.getArgs()[2]).intValue();
                                JSONObject properties = new JSONObject();
                                AdapterView adapterView = (AdapterView) object;
                                if (SensorsDataAPI.sharedInstance().getIgnoredViewTypeList() != null) {
                                    if (adapterView instanceof ListView) {
                                        properties.put(AopConstants.ELEMENT_TYPE, "ListView");
                                        if (AopUtil.isViewIgnored(ListView.class)) {
                                            return;
                                        }
                                    } else if (adapterView instanceof GridView) {
                                        properties.put(AopConstants.ELEMENT_TYPE, "GridView");
                                        if (AopUtil.isViewIgnored(GridView.class)) {
                                            return;
                                        }
                                    }
                                }
                                Adapter adapter = adapterView.getAdapter();
                                if (adapter != null && (adapter instanceof SensorsAdapterViewItemTrackProperties)) {
                                    try {
                                        JSONObject jsonObject = ((SensorsAdapterViewItemTrackProperties) adapter).getSensorsItemTrackProperties(position);
                                        if (jsonObject != null) {
                                            AopUtil.mergeJSONObject(jsonObject, properties);
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                                if (activity != null) {
                                    properties.put(AopConstants.SCREEN_NAME, activity.getClass().getCanonicalName());
                                    String activityTitle = AopUtil.getActivityTitle(activity);
                                    if (!TextUtils.isEmpty(activityTitle)) {
                                        properties.put(AopConstants.TITLE, activityTitle);
                                    }
                                }
                                properties.put(AopConstants.ELEMENT_POSITION, String.valueOf(position));
                                String viewText = null;
                                if (view instanceof ViewGroup) {
                                    try {
                                        viewText = AopUtil.traverseView(new StringBuilder(), (ViewGroup) view);
                                        if (!TextUtils.isEmpty(viewText)) {
                                            viewText = viewText.substring(0, viewText.length() - 1);
                                        }
                                    } catch (Exception e2) {
                                        e2.printStackTrace();
                                    }
                                }
                                if (!TextUtils.isEmpty(viewText)) {
                                    properties.put(AopConstants.ELEMENT_CONTENT, viewText);
                                }
                                AopUtil.getFragmentNameFromView(adapterView, properties);
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
        } catch (Exception e3) {
            e3.printStackTrace();
            SALog.m1608i(TAG, " AdapterView.OnItemClickListener.onItemClick AOP ERROR: " + e3.getMessage());
        }
    }
}
