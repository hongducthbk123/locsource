package p004cn.jpush.android.p037a;

import android.content.Context;
import android.text.TextUtils;
import com.google.common.primitives.SignedBytes;
import com.google.common.primitives.UnsignedBytes;
import p004cn.jiguang.api.JActionExtra;
import p004cn.jpush.android.C0563b;
import p004cn.jpush.android.p039c.C0573g;
import p004cn.jpush.android.p039c.C0575i;

/* renamed from: cn.jpush.android.a.c */
public class C0545c extends JActionExtra {
    public Object beforLogin(Context context, int i, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            return null;
        }
        if ("platformtype".equals(str)) {
            if (i < 16) {
                return null;
            }
            byte a = C0575i.m1255a(context);
            if (a != 0) {
                String a2 = C0563b.m1203a(context, a);
                boolean b = C0563b.m1211b(context, a);
                if (a == 2) {
                    a = (byte) (a | SignedBytes.MAX_POWER_OF_TWO);
                }
                if (!b || TextUtils.isEmpty(a2)) {
                    a = (byte) (a | UnsignedBytes.MAX_POWER_OF_TWO);
                }
            }
            if (C0573g.m1238a().mo6854g(context)) {
                a = (byte) (a | 8);
                String a3 = C0563b.m1203a(context, 8);
                if (C0563b.m1211b(context, 8) && !TextUtils.isEmpty(a3)) {
                    a = (byte) (a | 32);
                }
            }
            return Byte.valueOf(a);
        } else if ("platformregid".equals(str)) {
            return C0563b.m1203a(context, C0575i.m1255a(context));
        } else {
            return null;
        }
    }
}
