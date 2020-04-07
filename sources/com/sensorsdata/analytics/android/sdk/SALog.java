package com.sensorsdata.analytics.android.sdk;

import android.util.Log;

public class SALog {
    private static SensorsDataAPI mSensorsDataAPI;

    private SALog() {
    }

    public static void init(SensorsDataAPI sensorsDataAPI) {
        mSensorsDataAPI = sensorsDataAPI;
    }

    /* renamed from: d */
    public static void m1606d(String tag, String msg) {
        try {
            if (mSensorsDataAPI.isDebugMode()) {
                Log.i(tag, msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: d */
    public static void m1607d(String tag, String msg, Throwable tr) {
        try {
            if (mSensorsDataAPI.isDebugMode()) {
                Log.i(tag, msg, tr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: i */
    public static void m1608i(String tag, String msg) {
        try {
            if (SensorsDataAPI.ENABLE_LOG.booleanValue()) {
                Log.i(tag, msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: i */
    public static void m1610i(String tag, Throwable tr) {
        try {
            if (SensorsDataAPI.ENABLE_LOG.booleanValue()) {
                Log.i(tag, "", tr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: i */
    public static void m1609i(String tag, String msg, Throwable tr) {
        try {
            if (SensorsDataAPI.ENABLE_LOG.booleanValue()) {
                Log.i(tag, msg, tr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
