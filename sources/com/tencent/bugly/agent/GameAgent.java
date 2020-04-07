package com.tencent.bugly.agent;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Locale;
import org.apache.commons.p052io.IOUtils;

public class GameAgent {
    private static final String CLASS_COCOS_ACTIVITY = "org.cocos2dx.lib.Cocos2dxActivity";
    private static final String CLASS_UNITY_PLAYER = "com.unity3d.player.UnityPlayer";
    private static final String CRASH_REPORT_CLASS_SUFFIX = "crashreport.CrashReport";
    private static final int GAME_TYPE_COCOS = 1;
    private static final int GAME_TYPE_UNITY = 2;
    private static final int LOG_LEVEL_DEBUG = 1;
    private static final int LOG_LEVEL_ERROR = 4;
    private static final int LOG_LEVEL_INFO = 2;
    private static final int LOG_LEVEL_VERBOSE = 0;
    private static final int LOG_LEVEL_WARN = 3;
    private static final String LOG_TAG = "CrashReport-GameAgent";
    private static final String OLD_STRATEGY_CLASS_SUFFIX = "crashreport.CrashReport$UserStrategy";
    private static final String STRATEGY_CLASS_SUFFIX = "BuglyStrategy";
    private static final int TYPE_COCOS2DX_JS_CRASH = 5;
    private static final int TYPE_COCOS2DX_LUA_CRASH = 6;
    private static final int TYPE_U3D_CRASH = 4;
    private static final String VERSION = "2.0";
    private static String sAppChannel = null;
    private static String sAppVersion = null;
    private static WeakReference<Activity> sContext = null;
    private static int sGameType = 0;
    private static Handler sHandler = null;
    /* access modifiers changed from: private */
    public static boolean sIsDebug = false;
    private static String sUserId = null;
    private static String sdkPackageName = "com.tencent.bugly";

    private static class Reflection {
        private Reflection() {
        }

        /* access modifiers changed from: private */
        public static Object getStaticField(String str, String str2, Object obj) {
            try {
                Field declaredField = Class.forName(str).getDeclaredField(str2);
                declaredField.setAccessible(true);
                return declaredField.get(obj);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e2) {
                e2.printStackTrace();
            } catch (IllegalAccessException e3) {
                e3.printStackTrace();
            }
            return null;
        }

        /* access modifiers changed from: private */
        public static Object invokeStaticMethod(String str, String str2, Object[] objArr, Class<?>... clsArr) {
            boolean z = false;
            try {
                Method declaredMethod = Class.forName(str).getDeclaredMethod(str2, clsArr);
                declaredMethod.setAccessible(true);
                return declaredMethod.invoke(null, objArr);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                return z;
            } catch (NoSuchMethodException e2) {
                e2.printStackTrace();
                return z;
            } catch (InvocationTargetException e3) {
                e3.printStackTrace();
                return z;
            } catch (IllegalAccessException e4) {
                e4.printStackTrace();
                return z;
            } catch (Exception e5) {
                e5.printStackTrace();
                return z;
            }
        }

        /* access modifiers changed from: private */
        public static Object newInstance(String str, Object[] objArr, Class<?>... clsArr) {
            try {
                Class cls = Class.forName(str);
                if (objArr == null) {
                    return cls.newInstance();
                }
                return cls.getConstructor(clsArr).newInstance(objArr);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                return null;
            } catch (NoSuchMethodException e2) {
                e2.printStackTrace();
                return null;
            } catch (InstantiationException e3) {
                e3.printStackTrace();
                return null;
            } catch (IllegalAccessException e4) {
                e4.printStackTrace();
                return null;
            } catch (InvocationTargetException e5) {
                e5.printStackTrace();
                return null;
            } catch (Exception e6) {
                e6.printStackTrace();
                return null;
            }
        }
    }

    public static String getVersion() {
        return VERSION;
    }

    public static void printLog(String str) {
        if (!TextUtils.isEmpty(str)) {
            if (str.startsWith("<Log>")) {
                printLog(2, str);
            } else if (str.startsWith("<LogDebug>")) {
                printLog(1, str);
            } else if (str.startsWith("<LogInfo>")) {
                printLog(2, str);
            } else if (str.startsWith("<LogWarning>")) {
                printLog(3, str);
            } else if (str.startsWith("<LogAssert>")) {
                printLog(3, str);
            } else if (str.startsWith("<LogError>")) {
                printLog(4, str);
            } else if (str.startsWith("<LogException>")) {
                printLog(4, str);
            } else {
                printLog(0, str);
            }
        }
    }

    private static void printLog(int i, String str) {
        setLog(i, LOG_TAG, str);
    }

    public static Activity getUnityActivity() {
        try {
            Object access$000 = Reflection.getStaticField(CLASS_UNITY_PLAYER, "currentActivity", null);
            if (access$000 != null && (access$000 instanceof Activity)) {
                return (Activity) access$000;
            }
        } catch (Exception e) {
            Log.w(LOG_TAG, "Failed to get the current activity from UnityPlayer");
            e.printStackTrace();
        }
        return null;
    }

    public static Activity getCocosActivity() {
        try {
            Object access$100 = Reflection.invokeStaticMethod(CLASS_COCOS_ACTIVITY, "getContext", null, new Class[0]);
            if (access$100 != null && (access$100 instanceof Activity)) {
                return (Activity) access$100;
            }
        } catch (Exception e) {
            Log.w(LOG_TAG, "Failed to get the current activity from UnityPlayer");
            e.printStackTrace();
        }
        return null;
    }

    private static Activity getActivity() {
        if (sContext != null && sContext.get() != null) {
            return (Activity) sContext.get();
        }
        switch (sGameType) {
            case 1:
                return getCocosActivity();
            case 2:
                return getUnityActivity();
            default:
                Log.w(LOG_TAG, "Game type has not been set.");
                return null;
        }
    }

    /* access modifiers changed from: private */
    public static Context getApplicationContext() {
        Activity activity = getActivity();
        if (activity != null) {
            return activity.getApplicationContext();
        }
        return null;
    }

    private static void runTaskInUiThread(Runnable runnable) {
        Activity activity = getActivity();
        if (activity != null) {
            activity.runOnUiThread(runnable);
        } else {
            new Thread(runnable).start();
        }
    }

    /* access modifiers changed from: private */
    public static void exitApplication() {
        int myPid = Process.myPid();
        printLog(3, String.format(Locale.US, "Exit application by kill process[%d]", new Object[]{Integer.valueOf(myPid)}));
        Process.killProcess(myPid);
    }

    /* access modifiers changed from: private */
    public static void delayExit(long j) {
        long max = Math.max(0, j);
        if (sHandler != null) {
            sHandler.postDelayed(new Runnable() {
                public void run() {
                    GameAgent.exitApplication();
                }
            }, max);
            return;
        }
        try {
            Thread.sleep(max);
            exitApplication();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: private */
    public static String convertToCanonicalName(String str) {
        StringBuilder sb = new StringBuilder();
        if (sdkPackageName == null) {
            sdkPackageName = "com.tencent.bugly";
        }
        sb.append(sdkPackageName);
        sb.append(".");
        sb.append(str);
        return sb.toString();
    }

    public static void setSdkPackageName(String str) {
        if (!TextUtils.isEmpty(str)) {
            sdkPackageName = str;
        }
    }

    public static void setGameType(int i) {
        sGameType = i;
    }

    public static void setLogEnable(boolean z) {
        sIsDebug = z;
    }

    private static Object newStrategy(Context context, String str, String str2, long j) {
        if (context == null || (TextUtils.isEmpty(str) && TextUtils.isEmpty(str2))) {
            return null;
        }
        Object access$300 = Reflection.newInstance(convertToCanonicalName(OLD_STRATEGY_CLASS_SUFFIX), new Object[]{context}, Context.class);
        if (access$300 != null) {
            try {
                Class cls = Class.forName(convertToCanonicalName(STRATEGY_CLASS_SUFFIX));
                cls.getDeclaredMethod("setAppChannel", new Class[]{String.class}).invoke(access$300, new Object[]{str});
                cls.getDeclaredMethod("setAppVersion", new Class[]{String.class}).invoke(access$300, new Object[]{str2});
                cls.getDeclaredMethod("setAppReportDelay", new Class[]{Long.TYPE}).invoke(access$300, new Object[]{Long.valueOf(j)});
                return access$300;
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e2) {
                e2.printStackTrace();
            } catch (IllegalArgumentException e3) {
                e3.printStackTrace();
            } catch (InvocationTargetException e4) {
                e4.printStackTrace();
            } catch (Exception e5) {
                e5.printStackTrace();
            }
        }
        return null;
    }

    public static void initCrashReport(String str, boolean z) {
        setLogEnable(z);
        initCrashReport(str, sAppChannel, sAppVersion, sUserId, 0);
    }

    private static void initCrashReport(final String str, String str2, String str3, final String str4, long j) {
        final Context applicationContext = getApplicationContext();
        if (applicationContext == null) {
            printLog(4, "Context is null. bugly initialize terminated.");
        } else if (TextUtils.isEmpty(str)) {
            printLog(4, "Please input appid when initCrashReport.");
        } else {
            sHandler = new Handler(Looper.getMainLooper());
            final Object newStrategy = newStrategy(applicationContext, str2, str3, j);
            runTaskInUiThread(new Runnable() {
                /* JADX WARNING: Removed duplicated region for block: B:8:0x004c  */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public void run() {
                    /*
                        r11 = this;
                        r8 = 4
                        r10 = 3
                        r9 = 2
                        r1 = 1
                        r2 = 0
                        boolean r4 = com.tencent.bugly.agent.GameAgent.sIsDebug
                        java.lang.Object r0 = r1
                        if (r0 == 0) goto L_0x0087
                        r3 = 0
                        java.lang.String r0 = "crashreport.CrashReport$UserStrategy"
                        java.lang.String r0 = com.tencent.bugly.agent.GameAgent.convertToCanonicalName(r0)     // Catch:{ ClassNotFoundException -> 0x007b, Exception -> 0x0081 }
                        java.lang.Class r0 = java.lang.Class.forName(r0)     // Catch:{ ClassNotFoundException -> 0x007b, Exception -> 0x0081 }
                    L_0x0018:
                        if (r0 == 0) goto L_0x0087
                        java.lang.String r3 = "crashreport.CrashReport"
                        java.lang.String r3 = com.tencent.bugly.agent.GameAgent.convertToCanonicalName(r3)
                        java.lang.String r5 = "initCrashReport"
                        java.lang.Object[] r6 = new java.lang.Object[r8]
                        android.content.Context r7 = r0
                        r6[r2] = r7
                        java.lang.String r7 = r4
                        r6[r1] = r7
                        java.lang.Boolean r7 = java.lang.Boolean.valueOf(r4)
                        r6[r9] = r7
                        java.lang.Object r7 = r1
                        r6[r10] = r7
                        java.lang.Class[] r7 = new java.lang.Class[r8]
                        java.lang.Class<android.content.Context> r8 = android.content.Context.class
                        r7[r2] = r8
                        java.lang.Class<java.lang.String> r8 = java.lang.String.class
                        r7[r1] = r8
                        java.lang.Class r8 = java.lang.Boolean.TYPE
                        r7[r9] = r8
                        r7[r10] = r0
                        com.tencent.bugly.agent.GameAgent.Reflection.invokeStaticMethod(r3, r5, r6, r7)
                        r0 = r1
                    L_0x004a:
                        if (r0 != 0) goto L_0x0075
                        java.lang.String r0 = "crashreport.CrashReport"
                        java.lang.String r0 = com.tencent.bugly.agent.GameAgent.convertToCanonicalName(r0)
                        java.lang.String r3 = "initCrashReport"
                        java.lang.Object[] r5 = new java.lang.Object[r10]
                        android.content.Context r6 = r0
                        r5[r2] = r6
                        java.lang.String r6 = r4
                        r5[r1] = r6
                        java.lang.Boolean r4 = java.lang.Boolean.valueOf(r4)
                        r5[r9] = r4
                        java.lang.Class[] r4 = new java.lang.Class[r10]
                        java.lang.Class<android.content.Context> r6 = android.content.Context.class
                        r4[r2] = r6
                        java.lang.Class<java.lang.String> r2 = java.lang.String.class
                        r4[r1] = r2
                        java.lang.Class r1 = java.lang.Boolean.TYPE
                        r4[r9] = r1
                        com.tencent.bugly.agent.GameAgent.Reflection.invokeStaticMethod(r0, r3, r5, r4)
                    L_0x0075:
                        java.lang.String r0 = r7
                        com.tencent.bugly.agent.GameAgent.setUserId(r0)
                        return
                    L_0x007b:
                        r0 = move-exception
                        r0.printStackTrace()
                        r0 = r3
                        goto L_0x0018
                    L_0x0081:
                        r0 = move-exception
                        r0.printStackTrace()
                        r0 = r3
                        goto L_0x0018
                    L_0x0087:
                        r0 = r2
                        goto L_0x004a
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.agent.GameAgent.C19172.run():void");
                }
            });
        }
    }

    public static void setAppVersion(final String str) {
        if (!TextUtils.isEmpty(str)) {
            sAppVersion = str;
            runTaskInUiThread(new Runnable() {
                public void run() {
                    Reflection.invokeStaticMethod(GameAgent.convertToCanonicalName(GameAgent.CRASH_REPORT_CLASS_SUFFIX), "setAppVersion", new Object[]{GameAgent.getApplicationContext(), str}, Context.class, String.class);
                }
            });
        }
    }

    public static void setAppChannel(final String str) {
        if (!TextUtils.isEmpty(str)) {
            sAppChannel = str;
            runTaskInUiThread(new Runnable() {
                public void run() {
                    Reflection.invokeStaticMethod(GameAgent.convertToCanonicalName(GameAgent.CRASH_REPORT_CLASS_SUFFIX), "setAppChannel", new Object[]{GameAgent.getApplicationContext(), str}, Context.class, String.class);
                }
            });
        }
    }

    public static void setUserId(final String str) {
        if (!TextUtils.isEmpty(str)) {
            sUserId = str;
            runTaskInUiThread(new Runnable() {
                public void run() {
                    Reflection.invokeStaticMethod(GameAgent.convertToCanonicalName(GameAgent.CRASH_REPORT_CLASS_SUFFIX), "setUserId", new Object[]{GameAgent.getApplicationContext(), str}, Context.class, String.class);
                }
            });
        }
    }

    public static void setUserSceneTag(final int i) {
        runTaskInUiThread(new Runnable() {
            public void run() {
                Reflection.invokeStaticMethod(GameAgent.convertToCanonicalName(GameAgent.CRASH_REPORT_CLASS_SUFFIX), "setUserSceneTag", new Object[]{GameAgent.getApplicationContext(), Integer.valueOf(i)}, Context.class, Integer.TYPE);
            }
        });
    }

    public static void putUserData(final String str, final String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            runTaskInUiThread(new Runnable() {
                public void run() {
                    Reflection.invokeStaticMethod(GameAgent.convertToCanonicalName(GameAgent.CRASH_REPORT_CLASS_SUFFIX), "putUserData", new Object[]{GameAgent.getApplicationContext(), str, str2}, Context.class, String.class, String.class);
                }
            });
        }
    }

    public static void removeUserData(final String str) {
        if (!TextUtils.isEmpty(str)) {
            runTaskInUiThread(new Runnable() {
                public void run() {
                    Reflection.invokeStaticMethod(GameAgent.convertToCanonicalName(GameAgent.CRASH_REPORT_CLASS_SUFFIX), "removeUserData", new Object[]{GameAgent.getApplicationContext(), str}, Context.class, String.class);
                }
            });
        }
    }

    public static void setSdkConfig(final String str, final String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            runTaskInUiThread(new Runnable() {
                public void run() {
                    Reflection.invokeStaticMethod(GameAgent.convertToCanonicalName(GameAgent.CRASH_REPORT_CLASS_SUFFIX), "putSdkData", new Object[]{GameAgent.getApplicationContext(), "SDK_" + str, str2}, Context.class, String.class, String.class);
                }
            });
        }
    }

    public static void setLog(int i, final String str, final String str2) {
        final String str3;
        if (!TextUtils.isEmpty(str)) {
            switch (i) {
                case 0:
                    str3 = "v";
                    break;
                case 1:
                    str3 = "d";
                    break;
                case 2:
                    str3 = "i";
                    break;
                case 3:
                    str3 = "w";
                    break;
                case 4:
                    str3 = "e";
                    break;
                default:
                    str3 = null;
                    break;
            }
            if (str3 != null) {
                runTaskInUiThread(new Runnable() {
                    public void run() {
                        Reflection.invokeStaticMethod(GameAgent.convertToCanonicalName("crashreport.BuglyLog"), str3, new Object[]{str, str2}, String.class, String.class);
                    }
                });
            }
        }
    }

    private static void postCocosLuaException(int i, String str, String str2, String str3, boolean z) {
        final String str4;
        final String str5;
        String str6;
        try {
            if (str3.startsWith("stack traceback")) {
                str4 = str3.substring(str3.indexOf(IOUtils.LINE_SEPARATOR_UNIX) + 1, str3.length()).trim();
            } else {
                str4 = str3;
            }
            try {
                int indexOf = str4.indexOf(IOUtils.LINE_SEPARATOR_UNIX);
                if (indexOf > 0) {
                    str4 = str4.substring(indexOf + 1, str4.length());
                }
                int indexOf2 = str4.indexOf(IOUtils.LINE_SEPARATOR_UNIX);
                if (indexOf2 > 0) {
                    str6 = str4.substring(0, indexOf2);
                } else {
                    str6 = str4;
                }
                int indexOf3 = str6.indexOf("]:");
                if (str == null || str.length() == 0) {
                    if (indexOf3 != -1) {
                        str = str6.substring(0, indexOf3 + 1);
                    } else {
                        str = str2;
                    }
                }
                str5 = str;
            } catch (Throwable th) {
                if (str != null || str.length() == 0) {
                    str5 = str2;
                } else {
                    str5 = str;
                }
                final int i2 = i;
                final String str7 = str2;
                final boolean z2 = z;
                runTaskInUiThread(new Runnable() {
                    public void run() {
                        Reflection.invokeStaticMethod(GameAgent.convertToCanonicalName("crashreport.inner.InnerApi"), "postCocos2dxCrashAsync", new Object[]{Integer.valueOf(i2), str5, str7, str4}, Integer.TYPE, String.class, String.class, String.class);
                        if (z2) {
                            GameAgent.delayExit(3000);
                        }
                    }
                });
            }
        } catch (Throwable th2) {
            str4 = str3;
            if (str != null) {
            }
            str5 = str2;
            final int i22 = i;
            final String str72 = str2;
            final boolean z22 = z;
            runTaskInUiThread(new Runnable() {
                public void run() {
                    Reflection.invokeStaticMethod(GameAgent.convertToCanonicalName("crashreport.inner.InnerApi"), "postCocos2dxCrashAsync", new Object[]{Integer.valueOf(i22), str5, str72, str4}, Integer.TYPE, String.class, String.class, String.class);
                    if (z22) {
                        GameAgent.delayExit(3000);
                    }
                }
            });
        }
        final int i222 = i;
        final String str722 = str2;
        final boolean z222 = z;
        runTaskInUiThread(new Runnable() {
            public void run() {
                Reflection.invokeStaticMethod(GameAgent.convertToCanonicalName("crashreport.inner.InnerApi"), "postCocos2dxCrashAsync", new Object[]{Integer.valueOf(i222), str5, str722, str4}, Integer.TYPE, String.class, String.class, String.class);
                if (z222) {
                    GameAgent.delayExit(3000);
                }
            }
        });
    }

    private static void postCocosJsException(int i, String str, String str2, String str3, boolean z) {
        final int i2 = i;
        final String str4 = str;
        final String str5 = str2;
        final String str6 = str3;
        final boolean z2 = z;
        runTaskInUiThread(new Runnable() {
            public void run() {
                Reflection.invokeStaticMethod(GameAgent.convertToCanonicalName("crashreport.inner.InnerApi"), "postCocos2dxCrashAsync", new Object[]{Integer.valueOf(i2), str4, str5, str6}, Integer.TYPE, String.class, String.class, String.class);
                if (z2) {
                    GameAgent.delayExit(3000);
                }
            }
        });
    }

    private static void postUnityException(final String str, final String str2, final String str3, final boolean z) {
        runTaskInUiThread(new Runnable() {
            public void run() {
                Reflection.invokeStaticMethod(GameAgent.convertToCanonicalName("crashreport.inner.InnerApi"), "postU3dCrashAsync", new Object[]{str, str2, str3}, String.class, String.class, String.class);
                if (z) {
                    GameAgent.delayExit(3000);
                }
            }
        });
    }

    public static void postException(int i, String str, String str2, String str3, boolean z) {
        switch (i) {
            case 4:
                postUnityException(str, str2, str3, z);
                return;
            case 5:
                postCocosJsException(i, str, str2, str3, z);
                return;
            case 6:
                postCocosLuaException(i, str, str2, str3, z);
                return;
            default:
                printLog(4, "The category of exception posted is unknown: " + String.valueOf(i));
                return;
        }
    }
}
