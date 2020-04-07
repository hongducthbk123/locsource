package com.tencent.bugly.crashreport.inner;

import com.tencent.bugly.crashreport.crash.C1958d;
import com.tencent.bugly.proguard.C2014w;
import java.util.Map;

/* compiled from: BUGLY */
public class InnerApi {
    public static void postU3dCrashAsync(String str, String str2, String str3) {
        if (str == null || str2 == null || str3 == null) {
            C2014w.m2119e("post u3d fail args null", new Object[0]);
        }
        C2014w.m2113a("post u3d crash %s %s", str, str2);
        C1958d.m1831a(Thread.currentThread(), 4, str, str2, str3, null);
    }

    public static void postCocos2dxCrashAsync(int i, String str, String str2, String str3) {
        if (str == null || str2 == null || str3 == null) {
            C2014w.m2119e("post cocos2d-x fail args null", new Object[0]);
        } else if (i == 5 || i == 6) {
            C2014w.m2113a("post cocos2d-x crash %s %s", str, str2);
            C1958d.m1831a(Thread.currentThread(), i, str, str2, str3, null);
        } else {
            C2014w.m2119e("post cocos2d-x fail category illeagle: %d", Integer.valueOf(i));
        }
    }

    public static void postH5CrashAsync(Thread thread, String str, String str2, String str3, Map<String, String> map) {
        if (str == null || str2 == null || str3 == null) {
            C2014w.m2119e("post h5 fail args null", new Object[0]);
            return;
        }
        C2014w.m2113a("post h5 crash %s %s", str, str2);
        C1958d.m1831a(thread, 8, str, str2, str3, map);
    }
}
