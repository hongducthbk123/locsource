package p004cn.jiguang.api;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import p004cn.jiguang.p015d.C0385a;
import p004cn.jiguang.p029e.C0498a;
import p004cn.jiguang.p029e.C0499b;

/* renamed from: cn.jiguang.api.BaseLogger */
public abstract class BaseLogger {

    /* renamed from: b */
    private static C0499b f179b = new C0498a();

    /* renamed from: c */
    private static final SimpleDateFormat f180c = new SimpleDateFormat("MM.dd_HH:mm:ss_SSS", Locale.ENGLISH);

    /* renamed from: d */
    private static ArrayList<String> f181d = new ArrayList<>();

    /* renamed from: e */
    private static boolean f182e = false;

    /* renamed from: f */
    private static boolean f183f = false;

    /* renamed from: a */
    private String f184a;

    public BaseLogger() {
        this.f184a = "";
        this.f184a = "JIGUANG-" + getCommonTag();
    }

    /* renamed from: a */
    private static boolean m188a(int i) {
        return i >= 3;
    }

    public static void flushCached2File() {
    }

    public static void setDelegate(C0499b bVar) {
        f179b = bVar;
    }

    /* renamed from: _d */
    public void mo6262_d(String str, String str2, Object... objArr) {
    }

    /* renamed from: d */
    public void mo6263d(String str, String str2) {
    }

    /* renamed from: d */
    public void mo6264d(String str, String str2, Throwable th) {
    }

    /* renamed from: dd */
    public void mo6265dd(String str, String str2) {
        if (C0385a.f195b && m188a(3)) {
            f179b.mo6663b(this.f184a, "[" + str + "] " + str2);
        }
    }

    /* renamed from: dd */
    public void mo6266dd(String str, String str2, Throwable th) {
        if (C0385a.f195b && m188a(3)) {
            f179b.mo6664b(this.f184a, "[" + str + "] " + str2, th);
        }
    }

    /* renamed from: e */
    public void mo6267e(String str, String str2) {
    }

    /* renamed from: e */
    public void mo6268e(String str, String str2, Throwable th) {
    }

    /* renamed from: ee */
    public void mo6269ee(String str, String str2) {
        if (m188a(6)) {
            f179b.mo6669e(this.f184a, "[" + str + "] " + str2);
        }
    }

    /* renamed from: ee */
    public void mo6270ee(String str, String str2, Throwable th) {
        if (m188a(6)) {
            f179b.mo6670e(this.f184a, "[" + str + "] " + str2, th);
        }
    }

    public abstract String getCommonTag();

    /* renamed from: i */
    public void mo6272i(String str, String str2) {
    }

    /* renamed from: i */
    public void mo6273i(String str, String str2, Throwable th) {
    }

    /* renamed from: ii */
    public void mo6274ii(String str, String str2) {
        if (C0385a.f195b && m188a(4)) {
            f179b.mo6665c(this.f184a, "[" + str + "] " + str2);
        }
    }

    /* renamed from: ii */
    public void mo6275ii(String str, String str2, Throwable th) {
        if (C0385a.f195b && m188a(4)) {
            f179b.mo6666c(this.f184a, "[" + str + "] " + str2, th);
        }
    }

    /* renamed from: v */
    public void mo6276v(String str, String str2) {
    }

    /* renamed from: v */
    public void mo6277v(String str, String str2, Throwable th) {
    }

    /* renamed from: vv */
    public void mo6278vv(String str, String str2) {
        if (C0385a.f195b && m188a(2)) {
            f179b.mo6661a(this.f184a, "[" + str + "] " + str2);
        }
    }

    /* renamed from: vv */
    public void mo6279vv(String str, String str2, Throwable th) {
        if (C0385a.f195b && m188a(2)) {
            f179b.mo6662a(this.f184a, "[" + str + "] " + str2, th);
        }
    }

    /* renamed from: w */
    public void mo6280w(String str, String str2) {
    }

    /* renamed from: w */
    public void mo6281w(String str, String str2, Throwable th) {
    }

    /* renamed from: ww */
    public void mo6282ww(String str, String str2) {
        if (m188a(5)) {
            f179b.mo6667d(this.f184a, "[" + str + "] " + str2);
        }
    }

    /* renamed from: ww */
    public void mo6283ww(String str, String str2, Throwable th) {
        if (C0385a.f195b && m188a(5)) {
            f179b.mo6668d(this.f184a, "[" + str + "] " + str2, th);
        }
    }
}
