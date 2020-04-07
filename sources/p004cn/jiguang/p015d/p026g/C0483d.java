package p004cn.jiguang.p015d.p026g;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import p004cn.jiguang.api.SdkType;
import p004cn.jiguang.p015d.p016a.C0386a;
import p004cn.jiguang.p015d.p016a.C0389d;
import p004cn.jiguang.p015d.p021d.C0445b;
import p004cn.jiguang.p015d.p021d.C0448e;
import p004cn.jiguang.p015d.p021d.C0463t;
import p004cn.jiguang.p029e.C0501d;
import p004cn.jiguang.p031g.C0506a;
import p004cn.jiguang.p031g.C0530k;
import p004cn.jiguang.p031g.C0531l;

/* renamed from: cn.jiguang.d.g.d */
public final class C0483d {

    /* renamed from: v */
    private static volatile C0483d f497v;

    /* renamed from: w */
    private static final Object f498w = new Object();

    /* renamed from: a */
    public String f499a;

    /* renamed from: b */
    public String f500b;

    /* renamed from: c */
    public String f501c;

    /* renamed from: d */
    public String f502d;

    /* renamed from: e */
    public String f503e;

    /* renamed from: f */
    public String f504f;

    /* renamed from: g */
    public String f505g;

    /* renamed from: h */
    public String f506h;

    /* renamed from: i */
    public String f507i;

    /* renamed from: j */
    public String f508j;

    /* renamed from: k */
    public int f509k;

    /* renamed from: l */
    public String f510l;

    /* renamed from: m */
    public short f511m;

    /* renamed from: n */
    public int f512n;

    /* renamed from: o */
    public String f513o;

    /* renamed from: p */
    public String f514p;

    /* renamed from: q */
    public String f515q;

    /* renamed from: r */
    public String f516r;

    /* renamed from: s */
    private transient AtomicBoolean f517s = new AtomicBoolean(false);

    /* renamed from: t */
    private String f518t;

    /* renamed from: u */
    private String f519u;

    private C0483d() {
    }

    /* renamed from: a */
    private static String m790a(String str, String str2) {
        return !C0530k.m1099a(str) ? str : str2;
    }

    /* renamed from: c */
    public static byte m791c(Context context) {
        ArrayList a = C0445b.m618a().mo6552a(context, SdkType.JPUSH.name(), 20, "platformtype", 1);
        Object obj = a.size() > 0 ? a.get(0) : null;
        if (obj == null || !(obj instanceof Byte)) {
            return 0;
        }
        return ((Byte) obj).byteValue();
    }

    /* renamed from: d */
    public static C0483d m792d() {
        if (f497v == null) {
            synchronized (f498w) {
                if (f497v == null) {
                    f497v = new C0483d();
                }
            }
        }
        return f497v;
    }

    /* renamed from: d */
    public static String m793d(Context context) {
        String str = "";
        ArrayList a = C0445b.m618a().mo6552a(context, SdkType.JPUSH.name(), 20, "platformregid", 1);
        Object obj = a.size() > 0 ? a.get(0) : null;
        return (obj == null || !(obj instanceof String)) ? str : (String) obj;
    }

    /* renamed from: a */
    public final String mo6623a() {
        return this.f518t;
    }

    /* renamed from: a */
    public final void mo6624a(Context context) {
        if (!this.f517s.get() && context != null) {
            mo6626b(context);
            this.f517s.set(true);
        }
    }

    /* renamed from: b */
    public final String mo6625b() {
        return m790a(this.f500b, MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR) + "$$" + m790a(this.f501c, MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR) + "$$" + m790a(this.f502d, MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR) + "$$" + m790a(this.f503e, MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR) + "$$" + m790a(this.f508j, MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR) + "$$" + (this.f504f + "|" + this.f505g + "|" + this.f506h + "|" + this.f507i) + "$$" + this.f509k + "$$" + this.f510l;
    }

    /* renamed from: b */
    public final void mo6626b(Context context) {
        if (context == null) {
            C0501d.m907c("DeviceInfo", "context is null");
            return;
        }
        String f = C0506a.m968f(context);
        String e = C0506a.m964e(context, "");
        if (TextUtils.isEmpty(f)) {
            f = MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR;
        }
        if (TextUtils.isEmpty(e)) {
            e = MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR;
        }
        this.f518t = f + "$$" + e + "$$" + context.getPackageName() + "$$" + C0389d.m338i(context);
        this.f499a = C0463t.m727a(context);
        this.f511m = C0448e.m641a().mo6561c();
        this.f500b = VERSION.RELEASE + "," + Integer.toString(VERSION.SDK_INT);
        this.f501c = Build.MODEL;
        this.f502d = C0531l.m1106a(context, "gsm.version.baseband", "baseband");
        this.f503e = Build.DEVICE;
        this.f508j = C0386a.m275g("");
        C0445b.m618a();
        this.f507i = C0445b.m629d(SdkType.JCORE.name(), "");
        C0445b.m618a();
        this.f505g = C0445b.m629d(SdkType.JANALYTICS.name(), "");
        C0445b.m618a();
        this.f506h = C0445b.m629d(SdkType.JSHARE.name(), "");
        C0445b.m618a();
        this.f504f = C0445b.m629d(SdkType.JPUSH.name(), "");
        this.f509k = C0506a.m974i(context) ? 1 : 0;
        this.f510l = C0506a.m932a(context);
        this.f519u = C0506a.m960d(context, this.f519u);
        this.f513o = C0506a.m972h(context);
        this.f512n = C0506a.f560a;
        this.f514p = C0506a.m971g(context);
        this.f515q = C0506a.m949b(context, MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        if (!C0530k.m1104e(this.f515q)) {
            this.f515q = MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR;
        }
        this.f519u = C0506a.m960d(context, MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        this.f516r = Build.SERIAL;
        C0386a.m250a(this.f519u, this.f514p, this.f515q);
    }

    /* renamed from: c */
    public final String mo6627c() {
        return this.f512n + "$$" + m790a(this.f513o, MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR) + "$$" + m790a(this.f519u, MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR) + "$$" + m790a(this.f514p, MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR) + "$$" + m790a(this.f515q, MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR) + "$$" + m790a("unknown".equalsIgnoreCase(this.f516r) ? MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR : this.f516r, MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
    }
}
