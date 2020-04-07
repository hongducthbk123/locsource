package com.btgame.seasdk.btcore.common.entity;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Build.VERSION;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import com.btgame.onesdk.btadjust.AdjustImpl;
import com.btgame.seasdk.btcore.common.util.BTResourceUtil;
import com.btgame.seasdk.btcore.common.util.BtFileUtil;
import com.btgame.seasdk.btcore.common.util.BtUtils;
import com.btgame.seasdk.btcore.common.util.BtsdkLog;
import com.btgame.seasdk.btcore.common.util.NetworkUtils;
import com.facebook.appevents.AppEventsConstants;

public class DeviceProperties {
    public String adjustAdId;
    public int density;
    public int displayScreenHeight;
    public int displayScreenWidth;
    public String gameVersionCode;
    public String gameVersionName;
    public String gamepackageName;
    public String imei;
    public String imsi;
    public String lang;
    public String mac = "";
    public int miniChannelId;
    public String networkInfo;
    public Integer packageId;
    public String phoneModel = Build.MODEL;
    public String sdkType;
    public String sdkVersion = "1.0";
    public String sysVersion = String.valueOf(VERSION.SDK_INT);

    public DeviceProperties(Context ctx) {
        this.imei = BtUtils.getIMEI(ctx, BtFileUtil.getGameDir(ctx));
        this.imsi = BtUtils.getIMSI(ctx, BtFileUtil.getGameDir(ctx));
        this.sdkType = AppEventsConstants.EVENT_PARAM_VALUE_NO;
        Log.d(BtsdkLog.TAG, "SDK版本  -->" + this.sdkVersion);
        WindowManager wm = (WindowManager) ctx.getSystemService("window");
        DisplayMetrics metrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(metrics);
        this.density = metrics.densityDpi;
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
        this.lang = BTResourceUtil.getAppLocale(ctx);
        Log.d(BtsdkLog.TAG, "应用程序包名-->" + this.gamepackageName);
        try {
            PackageInfo info = ctx.getPackageManager().getPackageInfo(ctx.getPackageName(), 0);
            this.gameVersionName = info.versionName;
            this.gameVersionCode = String.valueOf(info.versionCode);
        } catch (NameNotFoundException e1) {
            e1.printStackTrace();
        }
        this.mac = NetworkUtils.getPhoneMac();
        this.networkInfo = NetworkUtils.getNetworkTypeName(ctx);
        if (this.networkInfo == null) {
            this.networkInfo = "unknown";
        }
        this.miniChannelId = BtUtils.getIntNoXMeTaData(ctx, "btMiniChannelId");
        this.packageId = BTResourceUtil.findIntegerByName("pay_packageid");
        try {
            this.adjustAdId = AdjustImpl.getInstance().getAdjustAdId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        BtsdkLog.m1433p("sdkVersion", this.sdkVersion);
        BtsdkLog.m1433p("phoneModel", this.phoneModel);
        BtsdkLog.m1433p("sysVersion", this.sysVersion);
        BtsdkLog.m1433p("networkInfo", this.networkInfo);
    }

    public String toString() {
        return "DeviceProperties [sdkVersion=" + this.sdkVersion + ", phoneModel=" + this.phoneModel + ", imei=" + this.imei + ", gameVersionName " + this.gameVersionName + ", gameVersionNo=" + this.gameVersionCode + ", density=" + this.density + ", displayScreenWidth=" + this.displayScreenWidth + ", displayScreenHeight=" + this.displayScreenHeight + ", networkInfo=" + this.networkInfo + ", gamepackageName=" + this.gamepackageName + ", sdkType=" + this.sdkType + ", sysVersion=" + this.sysVersion + "mac=" + this.mac + "]";
    }
}
