package p004cn.jiguang.p015d.p026g;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* renamed from: cn.jiguang.d.g.c */
public final class C0482c {

    /* renamed from: a */
    public static String f496a = "yyyyMMdd_HHmm";

    /* renamed from: a */
    public static String m788a() {
        return new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss", Locale.ENGLISH).format(new Date());
    }

    /* renamed from: b */
    public static String m789b() {
        return new SimpleDateFormat("yyyyMMddHHmmss", Locale.ENGLISH).format(new Date());
    }
}
