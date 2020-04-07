package p004cn.jiguang.p015d.p028h;

import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.TextUtils;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;
import p004cn.jiguang.p015d.p021d.C0444a;

/* renamed from: cn.jiguang.d.h.c */
public final class C0492c {

    /* renamed from: a */
    private static Boolean f533a;

    /* renamed from: a */
    private static Bundle m833a(HashMap<String, String> hashMap) {
        if (hashMap == null || hashMap.isEmpty()) {
            return null;
        }
        Bundle bundle = new Bundle();
        for (String str : hashMap.keySet()) {
            bundle.putString(str, (String) hashMap.get(str));
        }
        return bundle;
    }

    /* renamed from: a */
    public static C0493d m834a(Context context, int i, int i2, C0444a aVar, HashMap<String, String> hashMap) {
        if (aVar == null || context == null) {
            return null;
        }
        Intent intent = new Intent();
        ComponentName componentName = new ComponentName(aVar.f392a, aVar.f393b);
        intent.setComponent(componentName);
        if (VERSION.SDK_INT >= 12) {
            intent.setFlags(32);
        }
        Bundle a = m833a(hashMap);
        if (a != null) {
            intent.putExtras(a);
        }
        C0493d dVar = new C0493d();
        dVar.mo6645a(componentName);
        if ((i2 & 2) != 0) {
            try {
                C0491b bVar = new C0491b(context, i == 1);
                if (context.getApplicationContext().bindService(intent, bVar, 1)) {
                    C0491b.f530a.put(aVar.f392a + aVar.f393b, new WeakReference(bVar));
                    if (i == 2) {
                        dVar.mo6644a(2, true);
                    }
                } else {
                    dVar.mo6644a(2, false);
                }
            } catch (Throwable th) {
                dVar.mo6644a(2, false);
            }
        }
        if ((i2 & 1) != 0) {
            try {
                if (context.startService(intent) != null) {
                    dVar.mo6644a(1, true);
                } else {
                    dVar.mo6644a(1, false);
                }
            } catch (Throwable th2) {
                dVar.mo6644a(1, false);
            }
        }
        if ((i2 & 4) != 0) {
            try {
                if (!TextUtils.isEmpty(aVar.f395d)) {
                    ContentResolver contentResolver = context.getApplicationContext().getContentResolver();
                    String str = aVar.f395d;
                    if (!str.startsWith("content://")) {
                        str = "content://" + str;
                    }
                    String b = m836b(hashMap);
                    if (!TextUtils.isEmpty(b)) {
                        str = str + b;
                    }
                    contentResolver.query(Uri.parse(str), null, null, null, null);
                    dVar.mo6644a(4, true);
                }
            } catch (Throwable th3) {
                dVar.mo6644a(4, false);
            }
        }
        return dVar;
    }

    /* renamed from: a */
    public static boolean m835a(Context context) {
        if (f533a != null) {
            return f533a.booleanValue();
        }
        if (context == null) {
            return true;
        }
        try {
            Intent intent = new Intent();
            intent.setPackage(context.getPackageName());
            intent.setAction("cn.jpush.android.WAKED_NOT_REPORT");
            List queryIntentServices = context.getPackageManager().queryIntentServices(intent, 0);
            if (queryIntentServices == null || queryIntentServices.isEmpty()) {
                f533a = Boolean.valueOf(true);
            } else {
                f533a = Boolean.valueOf(false);
            }
            return f533a.booleanValue();
        } catch (Throwable th) {
            return true;
        }
    }

    /* renamed from: b */
    private static String m836b(HashMap<String, String> hashMap) {
        if (hashMap == null || hashMap.isEmpty()) {
            return null;
        }
        Builder builder = new Builder();
        for (String str : hashMap.keySet()) {
            builder.appendQueryParameter(str, (String) hashMap.get(str));
        }
        return builder.toString();
    }
}
