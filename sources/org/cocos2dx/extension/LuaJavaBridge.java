package org.cocos2dx.extension;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Process;
import android.support.p000v4.app.NotificationCompat;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.places.model.PlaceFields;
import com.google.common.primitives.Ints;
import java.io.File;
import java.util.List;
import org.cocos2dx.lib.Cocos2dxActivity;
import org.cocos2dx.lib.Cocos2dxHelper;
import org.cocos2dx.lua.AppActivity;
import p004cn.jpush.android.api.JPushInterface;

public class LuaJavaBridge {
    private static String m_lastNotifyExtrInfo = "[]";

    /* access modifiers changed from: private */
    public static native void dispatchCocos2dxEvent(String str);

    public static String getPackageName() {
        try {
            String pkName = ((Activity) Cocos2dxActivity.getContext()).getPackageName();
            Log.i("luaJavaBridge:getPackageName: ", pkName);
            return pkName;
        } catch (Exception e) {
            Log.i("luaJavaBridge:getPackageName: ", "error!");
            return "";
        }
    }

    public static String getPhoneModel() {
        Log.i("luaJavaBridge:getPhoneModel: ", Build.PRODUCT);
        return Build.PRODUCT;
    }

    public static String getPhoneResolution() {
        Activity context = (Activity) Cocos2dxActivity.getContext();
        DisplayMetrics dm = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(dm);
        float height = ((float) dm.heightPixels) * dm.density;
        String ret = (((float) dm.widthPixels) * dm.density) + "*" + height;
        Log.i("luaJavaBridge:getPhoneResolution: ", ret);
        return ret;
    }

    public static String getPhoneIMEI() {
        String strIMEI = "unKnow IMEI";
        try {
            TelephonyManager tm = (TelephonyManager) Cocos2dxActivity.getContext().getSystemService(PlaceFields.PHONE);
            if (tm.getDeviceId() != null) {
                strIMEI = tm.getDeviceId();
            }
        } catch (Exception e) {
        }
        Log.i("luaJavaBridge:getPhoneIMEI: ", strIMEI);
        return strIMEI;
    }

    public static String getPhoneBrand() {
        Log.i("luaJavaBridge:getPhoneBrand: ", Build.MODEL);
        return Build.MODEL;
    }

    public static String getPackageVersion() {
        Activity context = (Activity) Cocos2dxActivity.getContext();
        String versionName = "";
        int versioncode = 0;
        try {
            PackageInfo pi = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;
            versioncode = pi.versionCode;
            if (versionName == null || versionName.length() <= 0) {
                return "";
            }
        } catch (Exception e) {
            Log.e("VersionInfo", "Exception", e);
        }
        return "name: " + versionName + "code: " + versioncode;
    }

    public static void androidLogInfo(String log) {
        Log.i("cocos Lua Log:", log);
    }

    public static String getDirectorFiles(String filePath) {
        String ret = "[";
        File[] files = new File(filePath).listFiles();
        boolean isFirst = true;
        if (files != null) {
            for (File file : files) {
                String filename = file.getName();
                if (!isFirst) {
                    ret = ret + ",";
                } else {
                    isFirst = false;
                }
                ret = ret + "\"" + filename + "\"";
            }
        }
        return ret + "]";
    }

    public static void luaPushNotification(int triggerTime, int interval, String title, String msg, String extrInfo, int notifyId) {
        PollingUtils.startPollingService(AppActivity.getContext(), PollingService.class, PollingService.ACTION, (long) triggerTime, (long) interval, title, msg, extrInfo, notifyId);
    }

    public static void luaClearAllNotification() {
        PollingUtils.stopPollingService(AppActivity.getContext(), PollingService.class, PollingService.ACTION);
    }

    public static void setLastNotiyExtrInfo(String extrInfo) {
        m_lastNotifyExtrInfo = extrInfo;
        Cocos2dxHelper.runOnGLThread(new Runnable() {
            public void run() {
                LuaJavaBridge.dispatchCocos2dxEvent("NotificationEnter");
            }
        });
    }

    public static String getLastNotifyExtrInfo() {
        return m_lastNotifyExtrInfo;
    }

    public static String getJPushRegisterId() {
        String registerId = JPushInterface.getRegistrationID(Cocos2dxActivity.getContext());
        Log.i("registerId: ", registerId);
        if (registerId == null) {
            return "";
        }
        return registerId;
    }

    public static void rebootApp(final int delayed) {
        final Activity context = (Activity) Cocos2dxActivity.getContext();
        context.runOnUiThread(new Runnable() {
            public void run() {
                ((AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM)).set(1, System.currentTimeMillis() + ((long) delayed), PendingIntent.getActivity(context.getApplicationContext(), 0, context.getBaseContext().getPackageManager().getLaunchIntentForPackage(context.getBaseContext().getPackageName()), Ints.MAX_POWER_OF_TWO));
                Process.killProcess(Process.myPid());
            }
        });
    }

    public static void gobackHome() {
        final Activity context = (Activity) Cocos2dxActivity.getContext();
        context.runOnUiThread(new Runnable() {
            public void run() {
                Intent MyIntent = new Intent("android.intent.action.MAIN");
                MyIntent.addCategory("android.intent.category.HOME");
                context.startActivity(MyIntent);
            }
        });
    }

    public static String luaGetVersionName() {
        Activity context = (Activity) Cocos2dxActivity.getContext();
        PackageManager pm = context.getPackageManager();
        String versionName = AppEventsConstants.EVENT_PARAM_VALUE_NO;
        try {
            return pm.getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return versionName;
        }
    }

    public static boolean isPackageAvilible(String packageName) {
        List<PackageInfo> pinfo = ((Activity) Cocos2dxActivity.getContext()).getPackageManager().getInstalledPackages(0);
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                if (((PackageInfo) pinfo.get(i)).packageName.equals(packageName)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean startApp(String packageName) {
        boolean isPackageAvilible = isPackageAvilible(packageName);
        if (!isPackageAvilible) {
            return isPackageAvilible;
        }
        Activity context = (Activity) Cocos2dxActivity.getContext();
        Intent intent = context.getPackageManager().getLaunchIntentForPackage(packageName);
        if (intent != null) {
            context.startActivity(intent);
        } else {
            isPackageAvilible = false;
        }
        return isPackageAvilible;
    }
}
