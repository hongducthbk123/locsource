package com.btgame.onesdk.frame.eneity;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import com.btgame.sdk.http.NetworkUtils;
import com.btgame.sdk.util.BtUtils;
import com.btgame.sdk.util.BtsdkLog;
import com.facebook.appevents.AppEventsConstants;

public class DeviceProperties {
    private static final String Default_SDK_VERSION = "1.0.0";
    private static final String META_SDK_VERSION = "bt_frame_sdk_version";
    public static final String SDK_VERSION = "1.1.99.1884";
    private static DeviceProperties instance;
    public String channelId;
    public int densityDpi;
    public int displayScreenHeight;
    public int displayScreenWidth;
    public String gameVersionCode;
    public String gameVersionName;
    public String gamepackageName;
    public String imei;
    public String imsi;
    public String mac = "";
    public String networkInfo;
    public String othersdkVersion;
    public String phoneModel = Build.MODEL;
    public String sdkType;
    public String sdkVersion;
    public String sysVersion = String.valueOf(VERSION.SDK_INT);

    public DeviceProperties(Context ctx) {
        this.imei = BtUtils.getIMEI(ctx);
        this.imsi = BtUtils.getIMSI(ctx);
        this.sdkType = AppEventsConstants.EVENT_PARAM_VALUE_NO;
        WindowManager wm = (WindowManager) ctx.getSystemService("window");
        DisplayMetrics metrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(metrics);
        this.densityDpi = metrics.densityDpi;
        Log.d(BtsdkLog.TAG, "orientation----->" + ctx.getResources().getConfiguration().orientation);
        Log.d(BtsdkLog.TAG, "orientation_portrait----->1");
        if (metrics.widthPixels > metrics.heightPixels) {
            this.displayScreenWidth = metrics.heightPixels;
            this.displayScreenHeight = metrics.widthPixels;
        } else {
            this.displayScreenWidth = metrics.widthPixels;
            this.displayScreenHeight = metrics.heightPixels;
        }
        this.gamepackageName = ctx.getPackageName();
        Log.d(BtsdkLog.TAG, "应用程序包名-->" + this.gamepackageName);
        try {
            PackageInfo info = ctx.getPackageManager().getPackageInfo(ctx.getPackageName(), 0);
            this.gameVersionName = info.versionName;
            this.gameVersionCode = String.valueOf(info.versionCode);
        } catch (NameNotFoundException e1) {
            e1.printStackTrace();
        }
        this.networkInfo = NetworkUtils.getNetworkTypeName(ctx);
        if (this.networkInfo == null) {
            this.networkInfo = "unknown";
        }
        if (TextUtils.isEmpty("1.1.99.1884")) {
            this.sdkVersion = BtUtils.getNOXMeTaData(ctx, META_SDK_VERSION);
        } else {
            this.sdkVersion = "1.1.99.1884";
        }
        if (TextUtils.isEmpty(this.sdkVersion)) {
            this.sdkVersion = Default_SDK_VERSION;
        }
        Log.d(BtsdkLog.TAG, "sdkVersion = " + this.sdkVersion);
        Log.d(BtsdkLog.TAG, "phoneModel = " + this.phoneModel);
        Log.d(BtsdkLog.TAG, "sysVersion = " + this.sysVersion);
        Log.d(BtsdkLog.TAG, "networkInfo = " + this.networkInfo);
        Log.d(BtsdkLog.TAG, "otherSdkVersion = " + this.othersdkVersion);
    }

    public static DeviceProperties getInstance(Context context) {
        if (instance == null) {
            instance = new DeviceProperties(context);
        }
        return instance;
    }

    public void setothersdkVersion(String othersdkVersionstr) {
        if (othersdkVersionstr == null || "".equals(othersdkVersionstr)) {
            throw new NullPointerException("第三方平台的版本号没有设置");
        }
        this.othersdkVersion = othersdkVersionstr;
    }

    public String toString() {
        return "DeviceProperties [sdkVersion=" + this.sdkVersion + ", phoneModel=" + this.phoneModel + ", imei=" + this.imei + ", gameVersionName " + this.gameVersionName + ", gameVersionNo=" + this.gameVersionCode + ", densityDpi=" + this.densityDpi + ", displayScreenWidth=" + this.displayScreenWidth + ", displayScreenHeight=" + this.displayScreenHeight + ", networkInfo=" + this.networkInfo + ", gamepackageName=" + this.gamepackageName + ", sdkType=" + this.sdkType + ", sysVersion=" + this.sysVersion + "mac=" + this.mac + "]";
    }
}
