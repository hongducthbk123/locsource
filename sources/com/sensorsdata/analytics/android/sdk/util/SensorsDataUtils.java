package com.sensorsdata.analytics.android.sdk.util;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build.VERSION;
import android.os.Process;
import android.provider.Settings.Secure;
import android.support.p000v4.content.ContextCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.webkit.WebSettings;
import com.facebook.places.model.PlaceFields;
import com.sensorsdata.analytics.android.sdk.AopConstants;
import com.sensorsdata.analytics.android.sdk.SALog;
import java.lang.reflect.Constructor;
import java.net.NetworkInterface;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

public final class SensorsDataUtils {
    private static final String SHARED_PREF_DEVICE_ID_KEY = "sensorsdata.device.id";
    private static final String SHARED_PREF_EDITS_FILE = "sensorsdata";
    private static final String SHARED_PREF_USER_AGENT_KEY = "sensorsdata.user.agent";
    private static final String TAG = "SA.SensorsDataUtils";
    private static final String fileAddressMac = "/sys/class/net/wlan0/address";
    private static final SimpleDateFormat mDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.CHINA);
    private static final List<String> mInvalidAndroidId = new ArrayList<String>() {
        {
            add("9774d56d682e549c");
            add("0123456789abcdef");
        }
    };
    private static final String marshmallowMacAddress = "02:00:00:00:00:00";
    private static final Map<String, String> sCarrierMap = new HashMap<String, String>() {
        {
            put("46000", "中国移动");
            put("46002", "中国移动");
            put("46007", "中国移动");
            put("46008", "中国移动");
            put("46001", "中国联通");
            put("46006", "中国联通");
            put("46009", "中国联通");
            put("46003", "中国电信");
            put("46005", "中国电信");
            put("46011", "中国电信");
        }
    };

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
                String toolbarTitle = getToolbarTitle(activity);
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

    public static String getCurrentProcessName(Context context) {
        try {
            int pid = Process.myPid();
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            if (activityManager == null) {
                return null;
            }
            for (RunningAppProcessInfo appProcess : activityManager.getRunningAppProcesses()) {
                if (appProcess != null && appProcess.pid == pid) {
                    return appProcess.processName;
                }
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean isMainProcess(Context context, String mainProcessName) {
        if (!TextUtils.isEmpty(mainProcessName) && !mainProcessName.equals(getCurrentProcessName(context.getApplicationContext()))) {
            return false;
        }
        return true;
    }

    public static String operatorToCarrier(String operator) {
        String other = "其他";
        if (TextUtils.isEmpty(operator)) {
            return other;
        }
        for (Entry<String, String> entry : sCarrierMap.entrySet()) {
            if (operator.startsWith((String) entry.getKey())) {
                return (String) entry.getValue();
            }
        }
        return other;
    }

    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(SHARED_PREF_EDITS_FILE, 0);
    }

    @TargetApi(11)
    public static String getToolbarTitle(Activity activity) {
        ActionBar actionBar = activity.getActionBar();
        if (actionBar == null || TextUtils.isEmpty(actionBar.getTitle())) {
            return null;
        }
        return actionBar.getTitle().toString();
    }

    public static void getScreenNameAndTitleFromActivity(JSONObject properties, Activity activity) {
        if (activity != null && properties != null) {
            try {
                properties.put(AopConstants.SCREEN_NAME, activity.getClass().getCanonicalName());
                String activityTitle = activity.getTitle().toString();
                if (VERSION.SDK_INT >= 11) {
                    String toolbarTitle = getToolbarTitle(activity);
                    if (!TextUtils.isEmpty(toolbarTitle)) {
                        activityTitle = toolbarTitle;
                    }
                }
                if (TextUtils.isEmpty(activityTitle)) {
                    PackageManager packageManager = activity.getPackageManager();
                    if (packageManager != null) {
                        ActivityInfo activityInfo = packageManager.getActivityInfo(activity.getComponentName(), 0);
                        if (activityInfo != null) {
                            activityTitle = activityInfo.loadLabel(packageManager).toString();
                        }
                    }
                }
                if (!TextUtils.isEmpty(activityTitle)) {
                    properties.put(AopConstants.TITLE, activityTitle);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void cleanUserAgent(Context context) {
        try {
            Editor editor = getSharedPreferences(context).edit();
            editor.putString(SHARED_PREF_USER_AGENT_KEY, null);
            editor.apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void mergeJSONObject(JSONObject source, JSONObject dest) throws JSONException {
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
    }

    public static String getUserAgent(Context context) {
        try {
            SharedPreferences preferences = getSharedPreferences(context);
            String userAgent = preferences.getString(SHARED_PREF_USER_AGENT_KEY, null);
            if (TextUtils.isEmpty(userAgent)) {
                if (VERSION.SDK_INT >= 17) {
                    try {
                        if (Class.forName("android.webkit.WebSettings").getMethod("getDefaultUserAgent", new Class[0]) != null) {
                            userAgent = WebSettings.getDefaultUserAgent(context);
                        }
                    } catch (Exception e) {
                        SALog.m1608i(TAG, "WebSettings NoSuchMethod: getDefaultUserAgent");
                    }
                } else {
                    try {
                        Class<?> webSettingsClassicClass = Class.forName("android.webkit.WebSettingsClassic");
                        Constructor<?> constructor = webSettingsClassicClass.getDeclaredConstructor(new Class[]{Context.class, Class.forName("android.webkit.WebViewClassic")});
                        constructor.setAccessible(true);
                        userAgent = (String) webSettingsClassicClass.getMethod("getUserAgentString", new Class[0]).invoke(constructor.newInstance(new Object[]{context, null}), new Object[0]);
                    } catch (Exception e2) {
                    }
                }
            }
            if (TextUtils.isEmpty(userAgent)) {
                userAgent = System.getProperty("http.agent");
            }
            if (TextUtils.isEmpty(userAgent)) {
                return userAgent;
            }
            Editor editor = preferences.edit();
            editor.putString(SHARED_PREF_USER_AGENT_KEY, userAgent);
            editor.apply();
            return userAgent;
        } catch (Exception e3) {
            e3.printStackTrace();
            return null;
        }
    }

    public static String getDeviceID(Context context) {
        SharedPreferences preferences = getSharedPreferences(context);
        String storedDeviceID = preferences.getString(SHARED_PREF_DEVICE_ID_KEY, null);
        if (storedDeviceID != null) {
            return storedDeviceID;
        }
        String storedDeviceID2 = UUID.randomUUID().toString();
        Editor editor = preferences.edit();
        editor.putString(SHARED_PREF_DEVICE_ID_KEY, storedDeviceID2);
        editor.apply();
        return storedDeviceID2;
    }

    public static boolean checkHasPermission(Context context, String permission) {
        try {
            if (ContextCompat.checkSelfPermission(context, permission) == 0) {
                return true;
            }
            SALog.m1608i(TAG, "You can fix this by adding the following to your AndroidManifest.xml file:\n<uses-permission android:name=\"" + permission + "\" />");
            return false;
        } catch (Exception e) {
            SALog.m1608i(TAG, e.toString());
            return false;
        }
    }

    public static String networkType(Context context) {
        if (!checkHasPermission(context, "android.permission.ACCESS_NETWORK_STATE")) {
            return "NULL";
        }
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService("connectivity");
        if (manager != null) {
            NetworkInfo networkInfo = manager.getNetworkInfo(1);
            if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {
                return "WIFI";
            }
        }
        switch (((TelephonyManager) context.getSystemService(PlaceFields.PHONE)).getNetworkType()) {
            case 1:
            case 2:
            case 4:
            case 7:
            case 11:
                return "2G";
            case 3:
            case 5:
            case 6:
            case 8:
            case 9:
            case 10:
            case 12:
            case 14:
            case 15:
                return "3G";
            case 13:
                return "4G";
            default:
                return "NULL";
        }
    }

    public static boolean isNetworkAvailable(Context context) {
        if (!checkHasPermission(context, "android.permission.ACCESS_NETWORK_STATE")) {
            return false;
        }
        try {
            NetworkInfo networkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (networkInfo == null || !networkInfo.isConnected()) {
                return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String getIMEI(Context mContext) {
        String imei = "";
        try {
            if (ContextCompat.checkSelfPermission(mContext, "android.permission.READ_PHONE_STATE") != 0) {
                return imei;
            }
            TelephonyManager tm = (TelephonyManager) mContext.getSystemService(PlaceFields.PHONE);
            if (tm != null) {
                imei = tm.getDeviceId();
            }
            return imei;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getAndroidID(Context mContext) {
        String androidID = "";
        try {
            return Secure.getString(mContext.getContentResolver(), "android_id");
        } catch (Exception e) {
            e.printStackTrace();
            return androidID;
        }
    }

    public static String getApplicationMetaData(Context mContext, String metaKey) {
        try {
            ApplicationInfo appInfo = mContext.getApplicationContext().getPackageManager().getApplicationInfo(mContext.getApplicationContext().getPackageName(), 128);
            String value = appInfo.metaData.getString(metaKey);
            int iValue = -1;
            if (value == null) {
                iValue = appInfo.metaData.getInt(metaKey, -1);
            }
            if (iValue != -1) {
                return String.valueOf(iValue);
            }
            return value;
        } catch (Exception e) {
            return "";
        }
    }

    private static String getMacAddressByInterface() {
        try {
            for (NetworkInterface nif : Collections.list(NetworkInterface.getNetworkInterfaces())) {
                if (nif.getName().equalsIgnoreCase("wlan0")) {
                    byte[] macBytes = nif.getHardwareAddress();
                    if (macBytes == null) {
                        return "";
                    }
                    StringBuilder res1 = new StringBuilder();
                    for (byte b : macBytes) {
                        res1.append(String.format("%02X:", new Object[]{Byte.valueOf(b)}));
                    }
                    if (res1.length() > 0) {
                        res1.deleteCharAt(res1.length() - 1);
                    }
                    return res1.toString();
                }
            }
        } catch (Exception e) {
        }
        return null;
    }

    public static String getMacAddress(Context context) {
        try {
            if (!checkHasPermission(context, "android.permission.ACCESS_WIFI_STATE")) {
                return "";
            }
            WifiInfo wifiInfo = ((WifiManager) context.getApplicationContext().getSystemService("wifi")).getConnectionInfo();
            if (wifiInfo == null || !marshmallowMacAddress.equals(wifiInfo.getMacAddress())) {
                if (wifiInfo != null) {
                    if (wifiInfo.getMacAddress() != null) {
                        return wifiInfo.getMacAddress();
                    }
                }
                return "";
            }
            try {
                String result = getMacAddressByInterface();
                if (result != null) {
                    return result;
                }
            } catch (Exception e) {
            }
            return marshmallowMacAddress;
        } catch (Exception e2) {
            return "";
        }
    }

    public static boolean isValidAndroidId(String androidId) {
        if (!TextUtils.isEmpty(androidId) && !mInvalidAndroidId.contains(androidId.toLowerCase())) {
            return true;
        }
        return false;
    }

    public static boolean hasUtmProperties(JSONObject properties) {
        if (properties == null) {
            return false;
        }
        if (properties.has("$utm_source") || properties.has("$utm_medium") || properties.has("$utm_term") || properties.has("$utm_content") || properties.has("$utm_campaign")) {
            return true;
        }
        return false;
    }
}
