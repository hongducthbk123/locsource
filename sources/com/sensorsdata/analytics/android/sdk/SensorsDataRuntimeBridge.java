package com.sensorsdata.analytics.android.sdk;

import android.app.Activity;
import android.support.p000v4.app.Fragment;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import com.sensorsdata.analytics.android.sdk.aop.AdapterViewOnItemClickListenerAppClick;
import com.sensorsdata.analytics.android.sdk.aop.CheckBoxOnCheckedChangedAppClick;
import com.sensorsdata.analytics.android.sdk.aop.DialogOnClickAppClick;
import com.sensorsdata.analytics.android.sdk.aop.ExpandableListViewItemChildAppClick;
import com.sensorsdata.analytics.android.sdk.aop.MenuItemAppClick;
import com.sensorsdata.analytics.android.sdk.aop.RadioGroupOnCheckedAppClick;
import com.sensorsdata.analytics.android.sdk.aop.RatingBarOnRatingChangedAppClick;
import com.sensorsdata.analytics.android.sdk.aop.ReactNativeViewAppClick;
import com.sensorsdata.analytics.android.sdk.aop.SeekBarOnSeekBarChangeAppClick;
import com.sensorsdata.analytics.android.sdk.aop.SpinnerOnItemSelectedAppClick;
import com.sensorsdata.analytics.android.sdk.aop.TabHostOnTabChangedAppClick;
import com.sensorsdata.analytics.android.sdk.aop.TrackViewOnAppClick;
import com.sensorsdata.analytics.android.sdk.aop.ViewOnClickAppClick;
import com.sensorsdata.analytics.android.sdk.util.AopUtil;
import com.sensorsdata.analytics.android.sdk.util.SensorsDataUtils;
import java.lang.reflect.Method;
import java.util.Locale;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.json.JSONObject;

public class SensorsDataRuntimeBridge {
    private static final String TAG = "SensorsDataRuntimeBridge";

    public static void onFragmentOnResumeMethod(JoinPoint joinPoint) {
        try {
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
            Method targetMethod = methodSignature.getMethod();
            String fragmentName = joinPoint.getTarget().getClass().getName();
            if (((SensorsDataIgnoreTrackAppViewScreen) methodSignature.getMethod().getAnnotation(SensorsDataIgnoreTrackAppViewScreen.class)) == null) {
                Fragment targetFragment = (Fragment) joinPoint.getTarget();
                if (targetFragment.getClass().getAnnotation(SensorsDataIgnoreTrackAppViewScreen.class) == null) {
                    Activity activity = targetFragment.getActivity();
                    if (!"android.support.v4.app.Fragment".equals(targetMethod.getDeclaringClass().getName()) && !targetFragment.isHidden() && targetFragment.getUserVisibleHint()) {
                        trackFragmentViewScreen(targetFragment, fragmentName, activity);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void onFragmentSetUserVisibleHintMethod(JoinPoint joinPoint) {
        try {
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
            Method method = methodSignature.getMethod();
            String fragmentName = joinPoint.getTarget().getClass().getName();
            if (((SensorsDataIgnoreTrackAppViewScreen) methodSignature.getMethod().getAnnotation(SensorsDataIgnoreTrackAppViewScreen.class)) == null) {
                Fragment targetFragment = (Fragment) joinPoint.getTarget();
                if (targetFragment.getClass().getAnnotation(SensorsDataIgnoreTrackAppViewScreen.class) == null) {
                    Activity activity = targetFragment.getActivity();
                    if (((Boolean) joinPoint.getArgs()[0]).booleanValue() && targetFragment.isResumed() && !targetFragment.isHidden()) {
                        trackFragmentViewScreen(targetFragment, fragmentName, activity);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void onFragmentHiddenChangedMethod(JoinPoint joinPoint) {
        try {
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
            Method method = methodSignature.getMethod();
            String fragmentName = joinPoint.getTarget().getClass().getName();
            if (((SensorsDataIgnoreTrackAppViewScreen) methodSignature.getMethod().getAnnotation(SensorsDataIgnoreTrackAppViewScreen.class)) == null) {
                Fragment targetFragment = (Fragment) joinPoint.getTarget();
                if (targetFragment.getClass().getAnnotation(SensorsDataIgnoreTrackAppViewScreen.class) == null) {
                    Activity activity = targetFragment.getActivity();
                    if (!((Boolean) joinPoint.getArgs()[0]).booleanValue() && targetFragment.isResumed() && targetFragment.getUserVisibleHint()) {
                        trackFragmentViewScreen(targetFragment, fragmentName, activity);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void trackFragmentView(JoinPoint joinPoint, Object result) {
        try {
            String fragmentName = ((MethodSignature) joinPoint.getSignature()).getMethod().getDeclaringClass().getName();
            if (result instanceof ViewGroup) {
                traverseView(fragmentName, (ViewGroup) result);
            } else if (result instanceof View) {
                ((View) result).setTag(R.id.sensors_analytics_tag_view_fragment_name, fragmentName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void trackFragmentViewScreen(Fragment targetFragment, String fragmentName, Activity activity) {
        if (targetFragment != null) {
            try {
                if (SensorsDataAPI.sharedInstance().isTrackFragmentAppViewScreenEnabled() && !"com.bumptech.glide.manager.SupportRequestManagerFragment".equals(fragmentName)) {
                    JSONObject properties = new JSONObject();
                    if (activity != null) {
                        String activityTitle = AopUtil.getActivityTitle(activity);
                        if (!TextUtils.isEmpty(activityTitle)) {
                            properties.put(AopConstants.TITLE, activityTitle);
                        }
                        properties.put(AopConstants.SCREEN_NAME, String.format(Locale.CHINA, "%s|%s", new Object[]{activity.getClass().getCanonicalName(), fragmentName}));
                    } else {
                        properties.put(AopConstants.SCREEN_NAME, fragmentName);
                    }
                    if (targetFragment instanceof ScreenAutoTracker) {
                        ScreenAutoTracker screenAutoTracker = (ScreenAutoTracker) targetFragment;
                        String screenUrl = screenAutoTracker.getScreenUrl();
                        JSONObject otherProperties = screenAutoTracker.getTrackProperties();
                        if (otherProperties != null) {
                            SensorsDataUtils.mergeJSONObject(otherProperties, properties);
                        }
                        SensorsDataAPI.sharedInstance().trackViewScreen(screenUrl, properties);
                        return;
                    }
                    SensorsDataAutoTrackAppViewScreenUrl autoTrackAppViewScreenUrl = (SensorsDataAutoTrackAppViewScreenUrl) targetFragment.getClass().getAnnotation(SensorsDataAutoTrackAppViewScreenUrl.class);
                    if (autoTrackAppViewScreenUrl != null) {
                        String screenUrl2 = autoTrackAppViewScreenUrl.url();
                        if (TextUtils.isEmpty(screenUrl2)) {
                            screenUrl2 = fragmentName;
                        }
                        SensorsDataAPI.sharedInstance().trackViewScreen(screenUrl2, properties);
                        return;
                    }
                    SensorsDataAPI.sharedInstance().track("$AppViewScreen", properties);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void traverseView(String fragmentName, ViewGroup root) {
        try {
            if (!TextUtils.isEmpty(fragmentName) && root != null) {
                int childCount = root.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    View child = root.getChildAt(i);
                    if ((child instanceof ListView) || (child instanceof GridView) || (child instanceof Spinner) || (child instanceof RadioGroup)) {
                        child.setTag(R.id.sensors_analytics_tag_view_fragment_name, fragmentName);
                    } else if (child instanceof ViewGroup) {
                        traverseView(fragmentName, (ViewGroup) child);
                    } else {
                        child.setTag(R.id.sensors_analytics_tag_view_fragment_name, fragmentName);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void onAdapterViewItemClick(JoinPoint joinPoint) {
        AdapterViewOnItemClickListenerAppClick.onAppClick(joinPoint);
    }

    public static void onCheckBoxCheckedChanged(JoinPoint joinPoint) {
        CheckBoxOnCheckedChangedAppClick.onAppClick(joinPoint);
    }

    public static void onMultiChoiceClick(JoinPoint joinPoint) {
        DialogOnClickAppClick.onMultiChoiceAppClick(joinPoint);
    }

    public static void onDialogClick(JoinPoint joinPoint) {
        DialogOnClickAppClick.onAppClick(joinPoint);
    }

    public static void onExpandableListViewItemGroupClick(JoinPoint joinPoint) {
        ExpandableListViewItemChildAppClick.onItemGroupClick(joinPoint);
    }

    public static void onExpandableListViewItemChildClick(JoinPoint joinPoint) {
        ExpandableListViewItemChildAppClick.onItemChildClick(joinPoint);
    }

    public static void onMenuClick(JoinPoint joinPoint, int menuItemIndex) {
        MenuItemAppClick.onAppClick(joinPoint, menuItemIndex);
    }

    public static void onRadioGroupCheckedChanged(JoinPoint joinPoint) {
        RadioGroupOnCheckedAppClick.onAppClick(joinPoint);
    }

    public static void onRatingBarChanged(JoinPoint joinPoint) {
        RatingBarOnRatingChangedAppClick.onAppClick(joinPoint);
    }

    public static void onReactNativeViewAppClick(JoinPoint joinPoint) {
        ReactNativeViewAppClick.onAppClick(joinPoint);
    }

    public static void onSeekBarChange(JoinPoint joinPoint) {
        SeekBarOnSeekBarChangeAppClick.onAppClick(joinPoint);
    }

    public static void onSpinnerItemSelected(JoinPoint joinPoint) {
        SpinnerOnItemSelectedAppClick.onAppClick(joinPoint);
    }

    public static void onTabHostChanged(JoinPoint joinPoint) {
        TabHostOnTabChangedAppClick.onAppClick(joinPoint);
    }

    public static void trackEventAOP(JoinPoint joinPoint) {
        try {
            SensorsDataTrackEvent trackEvent = (SensorsDataTrackEvent) ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(SensorsDataTrackEvent.class);
            String eventName = trackEvent.eventName();
            if (!TextUtils.isEmpty(eventName)) {
                String pString = trackEvent.properties();
                JSONObject properties = new JSONObject();
                if (!TextUtils.isEmpty(pString)) {
                    properties = new JSONObject(pString);
                }
                SensorsDataAPI.sharedInstance().track(eventName, properties);
            }
        } catch (Exception e) {
            e.printStackTrace();
            SALog.m1608i(TAG, "trackEventAOP error: " + e.getMessage());
        }
    }

    public static void trackViewOnClick(JoinPoint joinPoint) {
        TrackViewOnAppClick.onAppClick(joinPoint);
    }

    public static void onButterknifeClick(JoinPoint joinPoint) {
        try {
            if (SensorsDataAPI.sharedInstance().isButterknifeOnClickEnabled()) {
                onViewOnClick(joinPoint);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void onViewOnClick(JoinPoint joinPoint) {
        ViewOnClickAppClick.onAppClick(joinPoint);
    }
}
