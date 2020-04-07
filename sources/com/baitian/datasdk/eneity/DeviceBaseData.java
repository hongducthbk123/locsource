package com.baitian.datasdk.eneity;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Build.VERSION;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import com.baitian.datasdk.http.NetworkUtils;
import com.baitian.datasdk.util.BtUtils;
import com.baitian.datasdk.util.BtsdkLog;
import com.btgame.onesdk.PlatfromUtils;
import com.facebook.appevents.AppEventsConstants;

public class DeviceBaseData extends BaseDataField {

    /* renamed from: IP */
    public String f918IP;
    public int densityDpi;
    public int displayScreenHeight;
    public int displayScreenWidth;
    public String gameVersionCode;
    public String gameVersionName;
    public String gamepackageName;
    public String imei;
    public String imsi;
    public String networkInfo;
    public String phoneModel = Build.MODEL;
    public String phoneSysVersion = String.valueOf(VERSION.SDK_INT);
    public int platformId;
    public String platformIdSecond;
    public String terminal;

    public DeviceBaseData(Context ctx) {
        this.imei = BtUtils.getIMEI(ctx);
        this.imsi = BtUtils.getIMSI(ctx);
        this.terminal = AppEventsConstants.EVENT_PARAM_VALUE_NO;
        this.f918IP = "";
        WindowManager wm = (WindowManager) ctx.getSystemService("window");
        DisplayMetrics metrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(metrics);
        this.densityDpi = metrics.densityDpi;
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
        this.platformId = BtUtils.getIntNoXMeTaData(ctx, PlatfromUtils.PLATFROMID_KEY);
        this.platformIdSecond = "" + BtUtils.getIntNoXMeTaData(ctx, "btchannelId");
    }

    public String toString() {
        return "DeviceProperties [  phoneModel= " + this.phoneModel + ", imei=" + this.imei + ", gameVersionName " + this.gameVersionName + ", gameVersionNo=" + this.gameVersionCode + ", densityDpi=" + this.densityDpi + ", displayScreenWidth=" + this.displayScreenWidth + ", displayScreenHeight=" + this.displayScreenHeight + ", networkInfo=" + this.networkInfo + ", gamepackageName=" + this.gamepackageName + ", sdkType=" + this.terminal + ", sysVersion=" + this.phoneSysVersion + ", platformId = " + this.platformId + ", platformIdSecond = " + this.platformIdSecond + "]";
    }
}
