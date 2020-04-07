package p004cn.jiguang.p031g;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Build.VERSION;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;
import p004cn.jiguang.p005a.p006a.p009c.C0361h;
import p004cn.jiguang.p005a.p006a.p009c.C0364k;
import p004cn.jiguang.p015d.p021d.C0460q;

/* renamed from: cn.jiguang.g.d */
public final class C0523d {
    /* renamed from: a */
    public static int m1066a(ApplicationInfo applicationInfo) {
        boolean z = false;
        if (applicationInfo == null) {
            return -1;
        }
        try {
            if ((applicationInfo.flags & 1) != 0) {
                if ((applicationInfo.flags & 128) != 0) {
                    z = true;
                }
                return z ? 2 : 1;
            }
            String str = applicationInfo.sourceDir;
            if (TextUtils.isEmpty(str)) {
                return -1;
            }
            if (str.startsWith("/system/")) {
                return 3;
            }
            return !applicationInfo.sourceDir.contains(applicationInfo.packageName) ? 3 : 0;
        } catch (Throwable th) {
            return -1;
        }
    }

    /* renamed from: a */
    public static ApplicationInfo m1067a(Context context, String str) {
        try {
            return context.getPackageManager().getApplicationInfo(str, 0);
        } catch (Throwable th) {
            return null;
        }
    }

    /* renamed from: a */
    private static List<C0524e> m1068a(int i) {
        boolean z;
        ArrayList<String> a = C0506a.m935a(new String[]{"ps"});
        ArrayList arrayList = new ArrayList();
        if (a == null || a.isEmpty()) {
            return arrayList;
        }
        HashSet hashSet = new HashSet();
        Map a2 = C0524e.m1078a((String) a.remove(0));
        for (String a3 : a) {
            C0524e a4 = C0524e.m1076a(a3, a2);
            if (a4 != null) {
                if (i == 3 || !a4.mo6699a()) {
                    if (!"ps".equals(a4.f601a)) {
                        arrayList.add(a4);
                    }
                } else if (a4.mo6700b()) {
                    hashSet.add(a4.f603c);
                }
            }
        }
        if (i != 1 || hashSet.isEmpty()) {
            return arrayList;
        }
        ArrayList arrayList2 = new ArrayList();
        boolean z2 = false;
        while (!z2) {
            Iterator it = arrayList.iterator();
            z2 = true;
            while (it.hasNext()) {
                C0524e eVar = (C0524e) it.next();
                if (hashSet.contains(eVar.f604d)) {
                    arrayList2.add(eVar);
                    hashSet.add(eVar.f603c);
                    it.remove();
                    z = false;
                } else {
                    z = z2;
                }
                z2 = z;
            }
        }
        return arrayList2;
    }

    /* renamed from: a */
    private static List<C0361h> m1069a(Context context) {
        HashMap hashMap = new HashMap();
        for (C0524e eVar : m1068a(1)) {
            C0361h b = m1072b(context, eVar.f601a);
            if (b != null) {
                hashMap.put(b.f122b, b);
            }
        }
        return new ArrayList(hashMap.values());
    }

    /* renamed from: a */
    private static JSONArray m1070a(List<C0361h> list) {
        JSONArray jSONArray = new JSONArray();
        for (C0361h hVar : list) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("name", hVar.f121a);
                jSONObject.put("pkg", C0530k.m1098a((CharSequence) hVar.f122b, 128));
                jSONObject.put("ver_name", hVar.f123c);
                jSONObject.put("ver_code", hVar.f124d);
                jSONObject.put("install_type", hVar.f125e);
                jSONArray.put(jSONObject);
            } catch (Throwable th) {
            }
        }
        return jSONArray;
    }

    /* renamed from: a */
    public static void m1071a(Context context, int i, int i2) {
        JSONArray jSONArray;
        JSONArray jSONArray2;
        if (i2 > 0) {
            try {
                List<C0524e> a = m1068a(i2);
                jSONArray = new JSONArray();
                for (C0524e a2 : a) {
                    JSONObject a3 = a2.mo6698a(128);
                    if (a3 != null) {
                        jSONArray.put(a3);
                    }
                }
            } catch (Throwable th) {
                return;
            }
        } else {
            jSONArray = null;
        }
        if (i > 0) {
            jSONArray2 = m1070a(VERSION.SDK_INT < 21 ? m1073b(context) : m1069a(context));
        } else {
            jSONArray2 = null;
        }
        JSONObject jSONObject = new JSONObject();
        if (jSONArray2 != null && jSONArray2.length() > 0) {
            jSONObject.put("app", jSONArray2);
        }
        if (jSONArray != null && jSONArray.length() > 0) {
            jSONObject.put("process", jSONArray);
        }
        if (jSONObject.length() > 0) {
            C0460q.m707a(context, jSONObject, "app_running");
            C0460q.m714a(context, jSONObject);
        }
    }

    /* renamed from: b */
    private static C0361h m1072b(Context context, String str) {
        try {
            PackageInfo c = m1074c(context, str);
            if (c == null) {
                return null;
            }
            String charSequence = c.applicationInfo.loadLabel(context.getPackageManager()).toString();
            C0361h hVar = new C0361h();
            hVar.f121a = C0364k.m140a(charSequence, 30);
            hVar.f122b = c.packageName;
            hVar.f124d = c.versionCode;
            hVar.f123c = c.versionName;
            hVar.f125e = m1066a(c.applicationInfo);
            return hVar;
        } catch (Throwable th) {
            return null;
        }
    }

    /* renamed from: b */
    private static List<C0361h> m1073b(Context context) {
        ArrayList arrayList = new ArrayList();
        List<RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
        HashSet hashSet = new HashSet();
        for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            String[] strArr = runningAppProcessInfo.pkgList;
            if (strArr != null && strArr.length > 0) {
                Collections.addAll(hashSet, strArr);
            }
        }
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            C0361h b = m1072b(context, (String) it.next());
            if (b != null) {
                arrayList.add(b);
            }
        }
        return arrayList;
    }

    /* renamed from: c */
    private static PackageInfo m1074c(Context context, String str) {
        boolean z = false;
        try {
            return context.getPackageManager().getPackageInfo(str, 0);
        } catch (Throwable th) {
            return z;
        }
    }
}
