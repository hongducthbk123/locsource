package p004cn.jiguang.p015d.p017b;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Process;
import android.text.TextUtils;
import java.io.File;
import p004cn.jiguang.api.JCoreInterface;
import p004cn.jiguang.p015d.C0385a;
import p004cn.jiguang.p029e.C0501d;
import p004cn.jiguang.p031g.C0506a;
import p004cn.jpush.android.service.AlarmReceiver;
import p004cn.jpush.android.service.PushReceiver;
import p004cn.jpush.android.service.PushService;

/* renamed from: cn.jiguang.d.b.a */
public final class C0395a {

    /* renamed from: a */
    public static int f243a = -1;

    /* renamed from: a */
    public static void m382a(Context context) {
        try {
            new C0416c(context).start();
        } catch (Throwable th) {
        }
    }

    /* renamed from: a */
    public static void m383a(Context context, boolean z) {
        try {
            PackageManager packageManager = context.getPackageManager();
            ComponentName componentName = new ComponentName(context.getApplicationContext(), PushReceiver.class);
            ComponentName componentName2 = new ComponentName(context.getApplicationContext(), AlarmReceiver.class);
            if (!z) {
                C0501d.m903a("JCoreServiceUtils", "set Push/Alarm Receiver disabled");
                packageManager.setComponentEnabledSetting(componentName, 2, 1);
                packageManager.setComponentEnabledSetting(componentName2, 2, 1);
                C0506a.m978k(context.getApplicationContext());
                C0506a.m990r(context);
                return;
            }
            C0501d.m903a("JCoreServiceUtils", "set Push/Alarm Receiver enabled");
            packageManager.setComponentEnabledSetting(componentName, 1, 1);
            packageManager.setComponentEnabledSetting(componentName2, 1, 1);
            C0506a.m976j(context.getApplicationContext());
        } catch (Throwable th) {
        }
    }

    /* renamed from: a */
    public static boolean m384a() {
        return f243a == 0;
    }

    /* renamed from: b */
    public static void m385b() {
        if (!C0506a.m988p(C0385a.f198e)) {
            f243a = 0;
        } else if (m386b(C0385a.f198e)) {
            try {
                new C0415b().start();
            } catch (Throwable th) {
                C0501d.m907c("JCoreServiceUtils", "create checkCommonService thread error:" + th);
                f243a = 1;
            }
        } else {
            try {
                String c = m387c(C0385a.f198e);
                String h = C0506a.m973h(C0385a.f198e, PushService.class.getCanonicalName());
                if (!TextUtils.isEmpty(c) && !TextUtils.isEmpty(h) && c.equals(h)) {
                    m382a(C0385a.f198e);
                }
            } catch (Throwable th2) {
            }
        }
    }

    /* renamed from: b */
    public static boolean m386b(Context context) {
        String c = m387c(context);
        String packageName = context.getPackageName();
        return (c == null || packageName == null || !packageName.equals(c)) ? false : true;
    }

    /* renamed from: c */
    public static String m387c(Context context) {
        try {
            int myPid = Process.myPid();
            for (RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses()) {
                if (runningAppProcessInfo.pid == myPid) {
                    return runningAppProcessInfo.processName;
                }
            }
        } catch (Throwable th) {
            C0501d.m907c("JCoreServiceUtils", "#unexcepted - getCurProcessName failed:" + th.getMessage());
        }
        return "";
    }

    /* renamed from: c */
    public static boolean m388c() {
        return f243a == 1 || f243a == -1;
    }

    /* renamed from: d */
    static /* synthetic */ void m389d(Context context) {
        int i = 20;
        while (true) {
            if (i <= 0) {
                break;
            }
            try {
                if (new File(context.getFilesDir(), ".servicesaveFile").exists()) {
                    f243a = 1;
                    break;
                } else {
                    i--;
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                f243a = 1;
            } catch (Throwable th) {
                f243a = 1;
            }
        }
        if (f243a == -1) {
            f243a = 0;
            JCoreInterface.register(C0385a.f198e);
        }
    }
}
