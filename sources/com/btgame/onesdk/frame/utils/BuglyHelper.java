package com.btgame.onesdk.frame.utils;

import android.content.Context;
import android.util.Log;
import com.btgame.onesdk.frame.exception.OneSdkLogException;
import com.btgame.sdk.util.BtUtils;
import com.btgame.sdk.util.BtsdkLog;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BuglyHelper {
    public static final String BUGLY_CLASS = "com.tencent.bugly.crashreport.CrashReport";
    public static final String BUGLY_ENABLE = "bt_bugly_enable";
    public static final String BUGLY_INIT_METHOD = "initCrashReport";
    public static final String BUGLY_POST_METHOD = "postCatchedException";
    private static Class buglyClass;
    private static Method initBugly;
    private static Method postExcptionMethod;

    static {
        try {
            buglyClass = Class.forName("com.tencent.bugly.crashreport.CrashReport");
            initBugly = buglyClass.getMethod("initCrashReport", new Class[]{Context.class});
            postExcptionMethod = buglyClass.getMethod("postCatchedException", new Class[]{Throwable.class});
        } catch (ClassNotFoundException e) {
            Log.d(BtsdkLog.TAG, "this is not buglyClass here!");
            e.printStackTrace();
        } catch (IllegalArgumentException e1) {
            Log.d(BtsdkLog.TAG, "this is illlegalArgument  !");
            e1.printStackTrace();
        } catch (NoSuchMethodException e2) {
            Log.d(BtsdkLog.TAG, "there is no this method in the bugly");
            e2.printStackTrace();
        }
    }

    public static void initBugly(Context context) {
        if (!BtUtils.getbooleanMeTaData(context, BUGLY_ENABLE)) {
            Log.d(BtsdkLog.TAG, "Cannot upload the log info to Bugly");
        } else if (buglyClass == null) {
            Log.d(BtsdkLog.TAG, "Cannot find the buglyClass");
        } else if (initBugly == null) {
            Log.d(BtsdkLog.TAG, "Cannot find the initBugly in the buglyclass");
        } else if (context == null) {
            Log.d(BtsdkLog.TAG, "context = null");
        } else {
            try {
                initBugly.invoke(buglyClass, new Object[]{context});
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e2) {
                e2.printStackTrace();
            }
        }
    }

    public static void postCatchedException(String msg) {
        if (buglyClass == null) {
            Log.d(BtsdkLog.TAG, "Cannot find the buglyClass or the bugly enable is false");
        } else if (postExcptionMethod == null) {
            Log.d(BtsdkLog.TAG, "Cannot find the postExcptionMethod in the buglyclass");
        } else {
            if (msg == null) {
                msg = "logExceotion in the Onesdk";
            }
            try {
                OneSdkLogException oneSdkLogException = new OneSdkLogException(msg);
                postExcptionMethod.invoke(buglyClass, new Object[]{oneSdkLogException});
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e2) {
                e2.printStackTrace();
            }
        }
    }

    public static void postCatchedException(Throwable throwable) {
        if (buglyClass == null) {
            Log.d(BtsdkLog.TAG, "Cannot find the buglyClass");
        } else if (postExcptionMethod == null) {
            Log.d(BtsdkLog.TAG, "Cannot find the postExcptionMethod in the buglyclass");
        } else if (throwable == null) {
            Log.d(BtsdkLog.TAG, "throwable is = null");
        } else {
            try {
                postExcptionMethod.invoke(buglyClass, new Object[]{throwable});
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e2) {
                e2.printStackTrace();
            }
        }
    }
}
