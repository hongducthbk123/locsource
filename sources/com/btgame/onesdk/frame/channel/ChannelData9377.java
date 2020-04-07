package com.btgame.onesdk.frame.channel;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.support.p000v4.content.ContextCompat;
import android.support.p000v4.content.PermissionChecker;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import com.btgame.google.constant.GpConfig;
import com.btgame.sdk.http.OkHttpUtil;
import com.btgame.sdk.util.BtsdkLog;
import com.facebook.places.model.PlaceFields;
import java.lang.reflect.Method;

public class ChannelData9377 {
    private static final String CHANNEL_NAME_9377 = "channel_name_9377";
    public static final String KAOPU_CLASS = "com.kaopu.supersdk.api.KPSuperSDK";
    public static final String KAOPU_DPCHANNEL_METHOD = "getDeepChannel";
    private static final String PRODUCT_SLUG_9377 = "product_slug_9377";
    private static Method getDeepChanelMethod;
    private static Class kaopuClass;
    private String channel_name;
    private String device_id;
    private String model;
    int network_code;
    private int network_type;

    /* renamed from: os */
    private String f929os;
    private int platform_id = 2;
    private String product_slug;
    private String resolution;

    private ChannelData9377() {
    }

    private String toJson(Context context) {
        try {
            return OkHttpUtil.getInstance(context).getGson().toJson((Object) this);
        } catch (Exception e) {
            e.printStackTrace();
            BtsdkLog.m1424e("Exception in gson to Json" + e.getMessage());
            return null;
        }
    }

    public static String getChannelData9377(Context context) {
        ChannelData9377 channel = new ChannelData9377();
        ApplicationInfo appInfo = null;
        try {
            appInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        Bundle metaData = appInfo.metaData;
        String productSlug = "";
        String channelName = "";
        String kpChannelSecondString = getKaopuDeepChannelId();
        String kpChannelSecondId = "";
        if (kpChannelSecondString.contains("ysdk")) {
            kpChannelSecondId = "1";
        } else if (kpChannelSecondString.contains("huawei")) {
            kpChannelSecondId = GpConfig.GPTHIRDID;
        }
        Log.d(BtsdkLog.TAG, "kpChannelSecondString == " + kpChannelSecondString);
        try {
            if (!TextUtils.isEmpty(kpChannelSecondId)) {
                channelName = metaData.getString(CHANNEL_NAME_9377) + kpChannelSecondId;
                if (TextUtils.isEmpty(channelName)) {
                    channelName = metaData.getInt(CHANNEL_NAME_9377) + kpChannelSecondId + "";
                }
            } else {
                channelName = metaData.getString(CHANNEL_NAME_9377);
                if (TextUtils.isEmpty(channelName)) {
                    channelName = metaData.getInt(CHANNEL_NAME_9377) + "";
                }
            }
            productSlug = metaData.getString(PRODUCT_SLUG_9377);
            if (TextUtils.isEmpty(productSlug)) {
                productSlug = metaData.getInt(PRODUCT_SLUG_9377) + "";
            }
        } catch (Exception e2) {
        }
        if (TextUtils.isEmpty(productSlug)) {
            productSlug = "";
        }
        channel.product_slug = productSlug;
        if (TextUtils.isEmpty(channelName)) {
            channelName = "";
        }
        channel.channel_name = channelName;
        channel.model = Build.MODEL;
        channel.network_code = getOperators(context);
        channel.network_type = getNetworkType(context);
        channel.device_id = getDevicId(context);
        channel.resolution = context.getResources().getDisplayMetrics().widthPixels + "*" + context.getResources().getDisplayMetrics().heightPixels;
        channel.f929os = "Android" + VERSION.RELEASE;
        return channel.toJson(context);
    }

    private static String getKaopuDeepChannelId() {
        try {
            kaopuClass = Class.forName(KAOPU_CLASS);
            getDeepChanelMethod = kaopuClass.getDeclaredMethod(KAOPU_DPCHANNEL_METHOD, new Class[0]);
            String deepChannelId = (String) getDeepChanelMethod.invoke(kaopuClass, new Object[0]);
            if (TextUtils.isEmpty(deepChannelId)) {
                return "";
            }
            return deepChannelId;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private static String getDevicId(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(PlaceFields.PHONE);
        String str = "";
        if (checkPermission(context, "android.permission.READ_PHONE_STATE")) {
            String deviceId = tm.getDeviceId();
            if (!TextUtils.isEmpty(deviceId)) {
                return deviceId;
            }
            String deviceId2 = tm.getSubscriberId();
            if (!TextUtils.isEmpty(deviceId2)) {
                return deviceId2;
            }
        }
        String deviceId3 = VERSION.SDK_INT >= 9 ? Build.SERIAL : null;
        if (!TextUtils.isEmpty(deviceId3)) {
            return deviceId3;
        }
        if (checkPermission(context, "android.permission.ACCESS_WIFI_STATE")) {
            String deviceId4 = ((WifiManager) context.getApplicationContext().getSystemService("wifi")).getConnectionInfo().getMacAddress();
            if (!TextUtils.isEmpty(deviceId4)) {
                return deviceId4;
            }
        }
        String deviceId5 = Secure.getString(context.getContentResolver(), "android_id");
        if (!TextUtils.isEmpty(deviceId5)) {
            return deviceId5;
        }
        return "unknown";
    }

    private static short getNetworkType(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null) {
            return 0;
        }
        if (activeNetworkInfo.getType() == 1) {
            return 5;
        }
        switch (activeNetworkInfo.getSubtype()) {
            case 1:
            case 2:
            case 4:
                return 2;
            case 3:
            case 5:
            case 6:
            case 8:
            case 12:
            case 15:
                return 3;
            case 13:
                return 4;
            default:
                return 0;
        }
    }

    private static int getOperators(Context context) {
        int OperatorsName = 0;
        String IMSI = ((TelephonyManager) context.getSystemService(PlaceFields.PHONE)).getSubscriberId();
        if (TextUtils.isEmpty(IMSI)) {
            return 0;
        }
        if (IMSI.startsWith("46000") || IMSI.startsWith("46002") || IMSI.startsWith("46007")) {
            OperatorsName = 1;
        } else if (IMSI.startsWith("46001") || IMSI.startsWith("46006")) {
            OperatorsName = 2;
        } else if (IMSI.startsWith("46003") || IMSI.startsWith("46005")) {
            OperatorsName = 3;
        }
        return OperatorsName;
    }

    private static boolean checkPermission(Context context, String permission) {
        if (VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(context, permission) == 0) {
                return true;
            }
            return false;
        } else if (PermissionChecker.checkSelfPermission(context, permission) != 0) {
            return false;
        } else {
            return true;
        }
    }
}
