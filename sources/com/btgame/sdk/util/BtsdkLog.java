package com.btgame.sdk.util;

import android.util.Log;
import java.util.Locale;
import p004cn.jiguang.net.HttpUtils;

public class BtsdkLog {
    public static final String TAG = "BtOneSDK";

    /* renamed from: v */
    public static void m1428v(String format, Object... args) {
        if (DebugUtils.getInstance().isLogFlag()) {
            Log.v(TAG, buildMessage(format, args));
        }
    }

    /* renamed from: d */
    public static void m1423d(String format) {
        if (DebugUtils.getInstance().isLogFlag()) {
            Log.d(TAG, buildMessage(format, new Object[0]));
        }
    }

    private static void markLogin2File(String msgLog) {
    }

    /* renamed from: e */
    public static void m1425e(String format, Object... args) {
        Log.e(TAG, buildMessage(format, args));
    }

    /* renamed from: e */
    public static void m1426e(Throwable tr, String format, Object... args) {
        if (DebugUtils.getInstance().isLogFlag()) {
            Log.e(TAG, buildMessage(format, args), tr);
        }
    }

    /* renamed from: e */
    public static void m1424e(String message) {
        if (DebugUtils.getInstance().isLogFlag()) {
            Log.e(TAG, buildMessage(message, new Object[0]));
        }
    }

    /* renamed from: p */
    public static void m1427p(String key, String value) {
        if (DebugUtils.getInstance().isLogFlag()) {
            Log.d(TAG, "" + buildMessage(key, value));
            return;
        }
        Log.d(TAG, key + HttpUtils.EQUAL_SIGN + value);
    }

    private static String buildMessage(String key, String value) {
        if (value == null) {
            String str = key;
        } else {
            String msg = "null";
        }
        StackTraceElement[] trace = new Throwable().fillInStackTrace().getStackTrace();
        String caller = "<unknown>";
        int i = 2;
        while (true) {
            if (i >= trace.length) {
                break;
            } else if (!trace[i].getClass().equals(BtsdkLog.class)) {
                String callingClass = trace[i].getClassName();
                String callingClass2 = callingClass.substring(callingClass.lastIndexOf(46) + 1);
                caller = callingClass2.substring(callingClass2.lastIndexOf(36) + 1) + "." + trace[i].getMethodName();
                break;
            } else {
                i++;
            }
        }
        String returnMsg = "";
        String msgHelper = HttpUtils.EQUAL_SIGN;
        try {
            StringBuilder msgResult = new StringBuilder("-------------->" + key);
            msgResult.append(msgHelper);
            msgResult.append(value);
            return String.format(Locale.US, "[%s] %s: %s", new Object[]{"" + Thread.currentThread().getId(), caller, msgResult.toString()});
        } catch (Exception e) {
            e.printStackTrace();
            return returnMsg;
        }
    }

    private static String buildMessage(String format, Object... args) {
        String msg;
        if (format == null) {
            msg = "null";
        } else {
            msg = format;
        }
        StackTraceElement[] trace = new Throwable().fillInStackTrace().getStackTrace();
        String caller = "<unknown>";
        int i = 2;
        while (true) {
            if (i >= trace.length) {
                break;
            } else if (!trace[i].getClass().equals(BtsdkLog.class)) {
                String callingClass = trace[i].getClassName();
                String callingClass2 = callingClass.substring(callingClass.lastIndexOf(46) + 1);
                caller = callingClass2.substring(callingClass2.lastIndexOf(36) + 1) + "." + trace[i].getMethodName();
                break;
            } else {
                i++;
            }
        }
        StringBuilder msgResult = new StringBuilder("-------------> ");
        String returnMsg = "";
        try {
            msgResult.append(msg);
            return String.format(Locale.US, "[%s] <%s>: %s", new Object[]{String.valueOf(Thread.currentThread().getId()), caller, msgResult.toString()});
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, e.getMessage());
            return returnMsg;
        }
    }
}
