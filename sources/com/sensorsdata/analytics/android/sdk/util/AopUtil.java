package com.sensorsdata.analytics.android.sdk.util;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Build.VERSION;
import android.support.p003v7.widget.SwitchCompat;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.ToggleButton;
import com.google.android.vending.expansion.downloader.Constants;
import com.sensorsdata.analytics.android.sdk.AopConstants;
import com.sensorsdata.analytics.android.sdk.R;
import com.sensorsdata.analytics.android.sdk.SensorsDataAPI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

public class AopUtil {
    private static final SimpleDateFormat mDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.getDefault());

    public static String traverseView(StringBuilder stringBuilder, ViewGroup root) {
        if (root == null) {
            try {
                return stringBuilder.toString();
            } catch (Exception e) {
                e.printStackTrace();
                return stringBuilder.toString();
            }
        } else {
            int childCount = root.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View child = root.getChildAt(i);
                if (child.getVisibility() == 0) {
                    if (child instanceof ViewGroup) {
                        traverseView(stringBuilder, (ViewGroup) child);
                    } else if (!isViewIgnored(child)) {
                        CharSequence viewText = null;
                        if (child instanceof CheckBox) {
                            viewText = ((CheckBox) child).getText();
                        } else if (child instanceof SwitchCompat) {
                            viewText = ((SwitchCompat) child).getTextOn();
                        } else if (child instanceof RadioButton) {
                            viewText = ((RadioButton) child).getText();
                        } else if (child instanceof ToggleButton) {
                            ToggleButton toggleButton = (ToggleButton) child;
                            viewText = toggleButton.isChecked() ? toggleButton.getTextOn() : toggleButton.getTextOff();
                        } else if (child instanceof Button) {
                            viewText = ((Button) child).getText();
                        } else if (child instanceof CheckedTextView) {
                            viewText = ((CheckedTextView) child).getText();
                        } else if (child instanceof TextView) {
                            viewText = ((TextView) child).getText();
                        } else if (child instanceof ImageView) {
                        }
                        if (!TextUtils.isEmpty(viewText)) {
                            stringBuilder.append(viewText.toString());
                            stringBuilder.append(Constants.FILENAME_SEQUENCE_SEPARATOR);
                        }
                    }
                }
            }
            return stringBuilder.toString();
        }
    }

    public static void getFragmentNameFromView(View view, JSONObject properties) {
        if (view != null) {
            try {
                String fragmentName = (String) view.getTag(R.id.sensors_analytics_tag_view_fragment_name);
                String fragmentName2 = (String) view.getTag(R.id.sensors_analytics_tag_view_fragment_name2);
                if (!TextUtils.isEmpty(fragmentName2)) {
                    fragmentName = fragmentName2;
                }
                if (!TextUtils.isEmpty(fragmentName)) {
                    String screenName = properties.getString(AopConstants.SCREEN_NAME);
                    if (!TextUtils.isEmpty(fragmentName)) {
                        properties.put(AopConstants.SCREEN_NAME, String.format(Locale.CHINA, "%s|%s", new Object[]{screenName, fragmentName}));
                        return;
                    }
                    properties.put(AopConstants.SCREEN_NAME, fragmentName);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static Activity getActivityFromContext(Context context, View view) {
        if (context == null) {
            return null;
        }
        try {
            if (context instanceof Activity) {
                return (Activity) context;
            }
            if (context instanceof ContextWrapper) {
                while (!(context instanceof Activity) && (context instanceof ContextWrapper)) {
                    context = ((ContextWrapper) context).getBaseContext();
                }
                if (context instanceof Activity) {
                    return (Activity) context;
                }
                return null;
            } else if (view == null) {
                return null;
            } else {
                Object object = view.getTag(R.id.sensors_analytics_tag_view_activity);
                if (object == null || !(object instanceof Activity)) {
                    return null;
                }
                return (Activity) object;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getViewId(View view) {
        try {
            String idString = (String) view.getTag(R.id.sensors_analytics_tag_view_id);
            if (!TextUtils.isEmpty(idString) || view.getId() == -1) {
                return idString;
            }
            return view.getContext().getResources().getResourceEntryName(view.getId());
        } catch (Exception e) {
            return null;
        }
    }

    public static boolean isViewIgnored(Class viewType) {
        if (viewType == null) {
            return true;
        }
        try {
            List<Class> mIgnoredViewTypeList = SensorsDataAPI.sharedInstance().getIgnoredViewTypeList();
            if (mIgnoredViewTypeList != null) {
                for (Class clazz : mIgnoredViewTypeList) {
                    if (clazz.isAssignableFrom(viewType)) {
                        return true;
                    }
                }
            }
            return false;
        } catch (Exception e) {
            return true;
        }
    }

    public static boolean isViewIgnored(View view) {
        if (view == null) {
            return true;
        }
        try {
            List<Class> mIgnoredViewTypeList = SensorsDataAPI.sharedInstance().getIgnoredViewTypeList();
            if (mIgnoredViewTypeList != null) {
                for (Class clazz : mIgnoredViewTypeList) {
                    if (clazz.isAssignableFrom(view.getClass())) {
                        return true;
                    }
                }
            }
            if (!"1".equals(view.getTag(R.id.sensors_analytics_tag_view_ignored))) {
                return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }

    public static String getActivityTitle(Activity activity) {
        if (activity == null) {
            return null;
        }
        String activityTitle = null;
        try {
            if (!TextUtils.isEmpty(activity.getTitle())) {
                activityTitle = activity.getTitle().toString();
            }
            if (VERSION.SDK_INT >= 11) {
                String toolbarTitle = SensorsDataUtils.getToolbarTitle(activity);
                if (!TextUtils.isEmpty(toolbarTitle)) {
                    activityTitle = toolbarTitle;
                }
            }
            if (!TextUtils.isEmpty(activityTitle)) {
                return activityTitle;
            }
            PackageManager packageManager = activity.getPackageManager();
            if (packageManager == null) {
                return activityTitle;
            }
            ActivityInfo activityInfo = packageManager.getActivityInfo(activity.getComponentName(), 0);
            return (activityInfo == null || TextUtils.isEmpty(activityInfo.loadLabel(packageManager))) ? activityTitle : activityInfo.loadLabel(packageManager).toString();
        } catch (Exception e) {
            return null;
        }
    }

    public static void mergeJSONObject(JSONObject source, JSONObject dest) throws JSONException {
        try {
            Iterator<String> superPropertiesIterator = source.keys();
            while (superPropertiesIterator.hasNext()) {
                String key = (String) superPropertiesIterator.next();
                Object value = source.get(key);
                if (value instanceof Date) {
                    synchronized (mDateFormat) {
                        dest.put(key, mDateFormat.format((Date) value));
                    }
                } else {
                    dest.put(key, value);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
