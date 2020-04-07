package p004cn.jiguang.p030f;

import java.util.Locale;

/* renamed from: cn.jiguang.f.d */
final class C0505d {
    /* renamed from: a */
    static void m927a(boolean z, String str, Object... objArr) {
        if (!z) {
            throw new IllegalArgumentException(String.format(Locale.ENGLISH, str, objArr));
        }
    }
}
