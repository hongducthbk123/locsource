package com.sensorsdata.analytics.android.sdk.aop;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.ListView;
import com.sensorsdata.analytics.android.sdk.AopConstants;
import com.sensorsdata.analytics.android.sdk.R;
import com.sensorsdata.analytics.android.sdk.SALog;
import com.sensorsdata.analytics.android.sdk.SensorsDataAPI;
import com.sensorsdata.analytics.android.sdk.SensorsDataAPI.AutoTrackEventType;
import com.sensorsdata.analytics.android.sdk.util.AopUtil;
import org.aspectj.lang.JoinPoint;
import org.json.JSONObject;

public class DialogOnClickAppClick {
    private static final String TAG = "DialogOnClickAppClick";

    public static void onAppClick(JoinPoint joinPoint) {
        try {
            if (SensorsDataAPI.sharedInstance().isAutoTrackEnabled() && !SensorsDataAPI.sharedInstance().isAutoTrackEventTypeIgnored(AutoTrackEventType.APP_CLICK) && joinPoint != null && joinPoint.getArgs() != null && joinPoint.getArgs().length == 2) {
                DialogInterface dialogInterface = (DialogInterface) joinPoint.getArgs()[0];
                if (dialogInterface != null) {
                    int whichButton = ((Integer) joinPoint.getArgs()[1]).intValue();
                    Dialog dialog = null;
                    if (dialogInterface instanceof Dialog) {
                        dialog = (Dialog) dialogInterface;
                    }
                    if (dialog != null) {
                        Activity activity = AopUtil.getActivityFromContext(dialog.getContext(), null);
                        if (activity == null) {
                            activity = dialog.getOwnerActivity();
                        }
                        if ((activity == null || !SensorsDataAPI.sharedInstance().isActivityAutoTrackAppClickIgnored(activity.getClass())) && !AopUtil.isViewIgnored(Dialog.class)) {
                            JSONObject properties = new JSONObject();
                            try {
                                if (dialog.getWindow() != null) {
                                    String idString = (String) dialog.getWindow().getDecorView().getTag(R.id.sensors_analytics_tag_view_id);
                                    if (!TextUtils.isEmpty(idString)) {
                                        properties.put(AopConstants.ELEMENT_ID, idString);
                                    }
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            if (activity != null) {
                                properties.put(AopConstants.SCREEN_NAME, activity.getClass().getCanonicalName());
                                String activityTitle = AopUtil.getActivityTitle(activity);
                                if (!TextUtils.isEmpty(activityTitle)) {
                                    properties.put(AopConstants.TITLE, activityTitle);
                                }
                            }
                            properties.put(AopConstants.ELEMENT_TYPE, "Dialog");
                            if (dialog instanceof AlertDialog) {
                                AlertDialog alertDialog = (AlertDialog) dialog;
                                Button button = alertDialog.getButton(whichButton);
                                if (button == null) {
                                    ListView listView = alertDialog.getListView();
                                    if (listView != null) {
                                        Object object = listView.getAdapter().getItem(whichButton);
                                        if (object != null && (object instanceof String)) {
                                            properties.put(AopConstants.ELEMENT_CONTENT, (String) object);
                                        }
                                    }
                                } else if (!TextUtils.isEmpty(button.getText())) {
                                    properties.put(AopConstants.ELEMENT_CONTENT, button.getText());
                                }
                            } else if (dialog instanceof android.support.p003v7.app.AlertDialog) {
                                android.support.p003v7.app.AlertDialog alertDialog2 = (android.support.p003v7.app.AlertDialog) dialog;
                                Button button2 = alertDialog2.getButton(whichButton);
                                if (button2 == null) {
                                    ListView listView2 = alertDialog2.getListView();
                                    if (listView2 != null) {
                                        Object object2 = listView2.getAdapter().getItem(whichButton);
                                        if (object2 != null && (object2 instanceof String)) {
                                            properties.put(AopConstants.ELEMENT_CONTENT, (String) object2);
                                        }
                                    }
                                } else if (!TextUtils.isEmpty(button2.getText())) {
                                    properties.put(AopConstants.ELEMENT_CONTENT, button2.getText());
                                }
                            }
                            SensorsDataAPI.sharedInstance().track(AopConstants.APP_CLICK_EVENT_NAME, properties);
                        }
                    }
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            SALog.m1608i(TAG, " DialogInterface.OnClickListener.onClick AOP ERROR: " + e2.getMessage());
        }
    }

    public static void onMultiChoiceAppClick(JoinPoint joinPoint) {
        try {
            if (SensorsDataAPI.sharedInstance().isAutoTrackEnabled() && !SensorsDataAPI.sharedInstance().isAutoTrackEventTypeIgnored(AutoTrackEventType.APP_CLICK) && joinPoint != null && joinPoint.getArgs() != null && joinPoint.getArgs().length == 3) {
                DialogInterface dialogInterface = (DialogInterface) joinPoint.getArgs()[0];
                if (dialogInterface != null) {
                    int whichButton = ((Integer) joinPoint.getArgs()[1]).intValue();
                    boolean booleanValue = ((Boolean) joinPoint.getArgs()[2]).booleanValue();
                    Dialog dialog = null;
                    if (dialogInterface instanceof Dialog) {
                        dialog = (Dialog) dialogInterface;
                    }
                    if (dialog != null) {
                        Context context = dialog.getContext();
                        Activity activity = null;
                        if (context instanceof Activity) {
                            activity = (Activity) context;
                        }
                        if ((activity == null || !SensorsDataAPI.sharedInstance().isActivityAutoTrackAppClickIgnored(activity.getClass())) && !AopUtil.isViewIgnored(Dialog.class)) {
                            JSONObject properties = new JSONObject();
                            try {
                                if (dialog.getWindow() != null) {
                                    String idString = (String) dialog.getWindow().getDecorView().getTag(R.id.sensors_analytics_tag_view_id);
                                    if (!TextUtils.isEmpty(idString)) {
                                        properties.put(AopConstants.ELEMENT_ID, idString);
                                    }
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            if (activity != null) {
                                properties.put(AopConstants.SCREEN_NAME, activity.getClass().getCanonicalName());
                                String activityTitle = AopUtil.getActivityTitle(activity);
                                if (!TextUtils.isEmpty(activityTitle)) {
                                    properties.put(AopConstants.TITLE, activityTitle);
                                }
                            }
                            properties.put(AopConstants.ELEMENT_TYPE, "Dialog");
                            if (dialog instanceof AlertDialog) {
                                AlertDialog alertDialog = (AlertDialog) dialog;
                                Button button = alertDialog.getButton(whichButton);
                                if (button == null) {
                                    ListView listView = alertDialog.getListView();
                                    if (listView != null) {
                                        Object object = listView.getAdapter().getItem(whichButton);
                                        if (object != null && (object instanceof String)) {
                                            properties.put(AopConstants.ELEMENT_CONTENT, (String) object);
                                        }
                                    }
                                } else if (!TextUtils.isEmpty(button.getText())) {
                                    properties.put(AopConstants.ELEMENT_CONTENT, button.getText());
                                }
                            } else if (dialog instanceof android.support.p003v7.app.AlertDialog) {
                                android.support.p003v7.app.AlertDialog alertDialog2 = (android.support.p003v7.app.AlertDialog) dialog;
                                Button button2 = alertDialog2.getButton(whichButton);
                                if (button2 == null) {
                                    ListView listView2 = alertDialog2.getListView();
                                    if (listView2 != null) {
                                        Object object2 = listView2.getAdapter().getItem(whichButton);
                                        if (object2 != null && (object2 instanceof String)) {
                                            properties.put(AopConstants.ELEMENT_CONTENT, (String) object2);
                                        }
                                    }
                                } else if (!TextUtils.isEmpty(button2.getText())) {
                                    properties.put(AopConstants.ELEMENT_CONTENT, button2.getText());
                                }
                            }
                            SensorsDataAPI.sharedInstance().track(AopConstants.APP_CLICK_EVENT_NAME, properties);
                        }
                    }
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            SALog.m1608i(TAG, " DialogInterface.OnMultiChoiceClickListener.onClick AOP ERROR: " + e2.getMessage());
        }
    }
}
