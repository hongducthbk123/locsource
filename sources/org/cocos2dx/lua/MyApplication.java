package org.cocos2dx.lua;

import android.content.pm.PackageManager.NameNotFoundException;
import com.btgame.onesdk.BtsdkApplication;
import com.tencent.bugly.crashreport.CrashReport;

public class MyApplication extends BtsdkApplication {
    public void onCreate() {
        super.onCreate();
        try {
            if (!getPackageManager().getApplicationInfo(getPackageName(), 128).metaData.getBoolean("BUGLY_INVALID")) {
                CrashReport.initCrashReport(getApplicationContext());
            }
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
    }
}
