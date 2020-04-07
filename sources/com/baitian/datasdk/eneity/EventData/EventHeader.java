package com.baitian.datasdk.eneity.EventData;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Build.VERSION;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.baitian.datasdk.util.BtUtils;
import com.btgame.onesdk.PlatfromUtils;
import com.facebook.appevents.AppEventsConstants;

public class EventHeader {
    private static EventHeader instance;
    public String city;
    public String country;
    public String dataAppId;
    public String densityDpi;
    public String displayScreenHeight;
    public String displayScreenWidth;
    public String ext;
    public String gameVersionCode;
    public String imei;
    public String imsi;
    public String phoneModel;
    public String phoneSysVersion;
    public String platformId;
    public String platformIdSecond;
    public String province;
    public String terminal;

    public static EventHeader getInstance(Context mCtx) {
        if (instance == null) {
            instance = new EventHeader(mCtx);
        }
        return instance;
    }

    private EventHeader(Context mCtx) {
        this.dataAppId = String.valueOf(BtUtils.getIntNoXMeTaData(mCtx, "btgameId"));
        this.platformId = String.valueOf(BtUtils.getIntNoXMeTaData(mCtx, PlatfromUtils.PLATFROMID_KEY));
        this.platformIdSecond = String.valueOf(BtUtils.getIntNoXMeTaData(mCtx, "btchannelId"));
        if (!"-1".equals(this.dataAppId) && !"-1".equals(this.platformId)) {
            if ("-1".equals(this.platformIdSecond)) {
                this.platformIdSecond = AppEventsConstants.EVENT_PARAM_VALUE_NO;
            }
            this.terminal = AppEventsConstants.EVENT_PARAM_VALUE_NO;
            this.phoneModel = Build.MODEL;
            this.phoneSysVersion = String.valueOf(VERSION.SDK_INT);
            try {
                this.gameVersionCode = String.valueOf(mCtx.getPackageManager().getPackageInfo(mCtx.getPackageName(), 0).versionCode);
            } catch (NameNotFoundException e) {
                e.printStackTrace();
            }
            this.imsi = BtUtils.getIMSI(mCtx);
            this.imei = BtUtils.getIMEI(mCtx);
            WindowManager wm = (WindowManager) mCtx.getSystemService("window");
            DisplayMetrics metrics = new DisplayMetrics();
            wm.getDefaultDisplay().getMetrics(metrics);
            this.densityDpi = metrics.densityDpi + "";
            if (metrics.widthPixels > metrics.heightPixels) {
                this.displayScreenWidth = metrics.heightPixels + "";
                this.displayScreenHeight = metrics.widthPixels + "";
            } else {
                this.displayScreenWidth = metrics.widthPixels + "";
                this.displayScreenHeight = metrics.heightPixels + "";
            }
            this.country = "";
            this.province = "";
            this.city = "";
            this.ext = "";
        }
    }

    public String toString() {
        return "dataAppId=" + this.dataAppId + '&' + "terminal=" + this.terminal + '&' + "platformId=" + this.platformId + '&' + "platformIdSecond=" + this.platformIdSecond + '&' + "phoneModel=" + this.phoneModel + '&' + "phoneSysVersion=" + this.phoneSysVersion + '&' + "gameVersionCode=" + this.gameVersionCode + '&' + "imsi=" + this.imsi + '&' + "imei=" + this.imei + '&' + "country=" + this.country + '&' + "province=" + this.province + '&' + "city=" + this.city + '&' + "ext=" + this.ext + '&' + "displayScreenHeight=" + this.displayScreenHeight + '&' + "displayScreenWidth=" + this.displayScreenWidth + '&' + "densityDpi=" + this.densityDpi;
    }
}
