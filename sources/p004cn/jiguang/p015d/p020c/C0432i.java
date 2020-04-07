package p004cn.jiguang.p015d.p020c;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.util.HashMap;

/* renamed from: cn.jiguang.d.c.i */
final class C0432i {

    /* renamed from: a */
    private static Integer[] f355a = new Integer[64];

    /* renamed from: b */
    private HashMap f356b = new HashMap();

    /* renamed from: c */
    private HashMap f357c = new HashMap();

    /* renamed from: d */
    private String f358d;

    /* renamed from: e */
    private int f359e = 3;

    /* renamed from: f */
    private String f360f;

    /* renamed from: g */
    private int f361g = ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;

    /* renamed from: h */
    private boolean f362h;

    static {
        for (int i = 0; i < f355a.length; i++) {
            f355a[i] = Integer.valueOf(i);
        }
    }

    public C0432i(String str, int i) {
        this.f358d = str;
    }

    /* renamed from: c */
    private static Integer m551c(int i) {
        return (i < 0 || i >= f355a.length) ? Integer.valueOf(i) : f355a[i];
    }

    /* renamed from: d */
    private void m552d(int i) {
        if (i < 0 || i > this.f361g) {
            throw new IllegalArgumentException(this.f358d + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + i + "is out of range");
        }
    }

    /* renamed from: a */
    public final void mo6514a(int i) {
        this.f361g = 3;
    }

    /* renamed from: a */
    public final void mo6515a(int i, String str) {
        m552d(i);
        Integer c = m551c(i);
        if (this.f359e == 2) {
            str = str.toUpperCase();
        } else if (this.f359e == 3) {
            str = str.toLowerCase();
        }
        this.f356b.put(str, c);
        this.f357c.put(c, str);
    }

    /* renamed from: a */
    public final void mo6516a(boolean z) {
        this.f362h = true;
    }

    /* renamed from: b */
    public final String mo6517b(int i) {
        m552d(i);
        String str = (String) this.f357c.get(m551c(i));
        if (str != null) {
            return str;
        }
        String num = Integer.toString(i);
        return this.f360f != null ? this.f360f + num : num;
    }
}
