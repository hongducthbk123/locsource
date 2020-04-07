package p004cn.jiguang.p015d.p028h;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.ComponentInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.facebook.GraphResponse;
import com.facebook.internal.ServerProtocol;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p004cn.jiguang.api.JCoreInterface;
import p004cn.jiguang.api.SdkType;
import p004cn.jiguang.p015d.p016a.C0389d;
import p004cn.jiguang.p015d.p021d.C0444a;
import p004cn.jiguang.p015d.p021d.C0460q;
import p004cn.jiguang.p029e.C0501d;
import p004cn.jiguang.p031g.C0506a;
import p004cn.jiguang.p031g.C0530k;

/* renamed from: cn.jiguang.d.h.e */
public final class C0494e extends C0490a {

    /* renamed from: e */
    private boolean f536e = false;

    /* renamed from: f */
    private String f537f = null;

    /* renamed from: g */
    private Class<?> f538g = null;

    /* renamed from: h */
    private String f539h = null;

    /* renamed from: a */
    private C0444a m841a(Context context, PackageManager packageManager, String str, String str2) {
        boolean z = false;
        if (packageManager == null || TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            int checkPermission = packageManager.checkPermission(str + ".permission.JPUSH_MESSAGE", str);
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(str, 128);
            if (!(applicationInfo == null || applicationInfo.metaData == null)) {
                String string = applicationInfo.metaData.getString("JPUSH_APPKEY");
                Intent intent = new Intent();
                intent.setClassName(str, this.f539h);
                List queryIntentServices = packageManager.queryIntentServices(intent, 0);
                if (!(queryIntentServices == null || queryIntentServices.size() == 0)) {
                    z = true;
                }
                if (checkPermission == 0 && z && !TextUtils.isEmpty(string) && string.length() == 24) {
                    C0444a aVar = new C0444a(str, str2, applicationInfo.targetSdkVersion);
                    ComponentInfo a = C0506a.m930a(context, str, this.f538g);
                    if (a != null && (a instanceof ProviderInfo)) {
                        ProviderInfo providerInfo = (ProviderInfo) a;
                        if (providerInfo.exported && providerInfo.enabled && providerInfo.authority != null && TextUtils.equals(str + ".DownloadProvider", providerInfo.authority)) {
                            aVar.f395d = providerInfo.authority;
                        }
                    }
                    return aVar;
                }
            }
            return null;
        } catch (NameNotFoundException e) {
            return null;
        } catch (Throwable th) {
            return null;
        }
    }

    /* renamed from: a */
    private static String m842a(String str, String str2) {
        return C0530k.m1101b(System.currentTimeMillis() + str + str2);
    }

    /* renamed from: a */
    private static JSONObject m843a(int i, String str, String str2, String str3, boolean z) {
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = "";
        }
        if (str3 == null) {
            str3 = "";
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("awake_type", i);
            jSONObject.put("from_package", str);
            jSONObject.put("from_uid", str2);
            jSONObject.put("awake_sequence", str3);
            jSONObject.put("app_alive", z);
            return jSONObject;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: b */
    public static void m844b(Context context, String str, JSONObject jSONObject) {
        JSONObject a = C0460q.m707a(context, jSONObject, str);
        JSONObject a2 = C0460q.m706a(context, "wakeup_cache.json");
        if (a2 == null) {
            a2 = new JSONObject();
        }
        JSONArray optJSONArray = a2.optJSONArray("content");
        if (optJSONArray == null) {
            optJSONArray = new JSONArray();
        }
        try {
            optJSONArray.put(a);
            a2.put("content", optJSONArray);
            C0460q.m716a(context, "wakeup_cache.json", a2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public final JSONObject mo6647a(String str, ArrayList<C0493d> arrayList) {
        if (arrayList.size() == 0) {
            return null;
        }
        JSONArray jSONArray = new JSONArray();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            C0493d dVar = (C0493d) it.next();
            try {
                JSONObject jSONObject = new JSONObject();
                String packageName = dVar.mo6643a().getPackageName();
                String a = m842a(this.f527b, packageName);
                jSONObject.put("target_package", packageName);
                jSONObject.put("awake_sequence", a);
                JSONArray jSONArray2 = new JSONArray();
                HashMap b = dVar.mo6646b();
                for (Integer intValue : b.keySet()) {
                    int intValue2 = intValue.intValue();
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("awake_type", intValue2);
                    jSONObject2.put(GraphResponse.SUCCESS_KEY, b.get(Integer.valueOf(intValue2)));
                    jSONArray2.put(jSONObject2);
                }
                jSONObject.put("awake", jSONArray2);
                jSONArray.put(jSONObject);
            } catch (Throwable th) {
            }
        }
        JSONObject jSONObject3 = new JSONObject();
        try {
            jSONObject3.put("package", str);
            jSONObject3.put("target", jSONArray);
            jSONObject3.put("device", Build.MODEL);
            jSONObject3.put("os", VERSION.RELEASE);
            return jSONObject3;
        } catch (JSONException e) {
            e.printStackTrace();
            return jSONObject3;
        }
    }

    /* renamed from: a */
    public final void mo6648a(Context context, int i, boolean z, String str, String str2, String str3) {
        try {
            m844b(context, "android_awake_target", m843a(i, str, str2, str3, z));
        } catch (Throwable th) {
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void mo6631a(Context context, String str, JSONObject jSONObject) {
        if (jSONObject != null) {
            JSONObject a = C0460q.m707a(context, jSONObject, str);
            JSONObject a2 = C0460q.m706a(context, "wakeup_cache.json");
            if (a2 == null) {
                a2 = new JSONObject();
            }
            JSONArray optJSONArray = a2.optJSONArray("content");
            if (optJSONArray == null) {
                optJSONArray = new JSONArray();
            }
            optJSONArray.put(a);
            JCoreInterface.reportHttpData(context, optJSONArray, SdkType.JCORE.name());
            C0460q.m716a(context, "wakeup_cache.json", (JSONObject) null);
        }
    }

    /* renamed from: a */
    public final void mo6649a(Class<?> cls) {
        this.f538g = cls;
    }

    /* renamed from: a */
    public final void mo6650a(boolean z) {
        this.f536e = z;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final boolean mo6634a() {
        if (!TextUtils.isEmpty(this.f539h) || this.f538g != null) {
            return this.f536e;
        }
        return false;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final boolean mo6635a(Context context) {
        if (context == null) {
            return true;
        }
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        long k = C0389d.m340k(context);
        return -1 == k || Math.abs(currentTimeMillis - k) > this.f526a;
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public final void mo6637b(Context context) {
        C0389d.m309a(context, System.currentTimeMillis() / 1000);
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public final ArrayList<C0444a> mo6639c(Context context) {
        if (context == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        try {
            PackageManager packageManager = context.getPackageManager();
            Intent intent = new Intent();
            intent.setAction(this.f537f);
            List queryIntentServices = packageManager.queryIntentServices(intent, 0);
            if (queryIntentServices == null || queryIntentServices.size() == 0) {
                return null;
            }
            for (int i = 0; i < queryIntentServices.size(); i++) {
                ServiceInfo serviceInfo = ((ResolveInfo) queryIntentServices.get(i)).serviceInfo;
                String str = serviceInfo.name;
                String str2 = serviceInfo.packageName;
                if (str != null && str2 != null && !TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && serviceInfo.exported && serviceInfo.enabled && !context.getPackageName().equals(str2)) {
                    C0444a a = m841a(context, packageManager, str2, str);
                    if (a != null) {
                        arrayList.add(a);
                    }
                }
            }
            return arrayList;
        } catch (Throwable th) {
            C0501d.m903a("WakeUpJiGuangSdkManager", "filterAllDaemonService error:" + th.getMessage());
        }
    }

    /* renamed from: c */
    public final void mo6651c(String str) {
        this.f537f = str;
    }

    /* renamed from: d */
    public final void mo6640d(Context context) {
        HashMap hashMap = new HashMap();
        String packageName = context.getPackageName();
        long j = this.f528c;
        hashMap.put("from_package", packageName);
        hashMap.put("from_uid", String.valueOf(j));
        hashMap.put("need_report", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
        ArrayList c = mo6639c(context);
        ArrayList arrayList = new ArrayList();
        int size = c.size();
        for (int i = 0; i < size; i++) {
            C0444a aVar = (C0444a) c.get(i);
            hashMap.put("awake_sequence", m842a(this.f527b, aVar.f392a));
            int i2 = 2;
            if (VERSION.SDK_INT < 26 || aVar.f394c < 26) {
                i2 = 3;
            }
            if (!TextUtils.isEmpty(aVar.f395d)) {
                i2 |= 4;
            }
            C0493d a = C0492c.m834a(context, 1, i2, aVar, hashMap);
            if (a != null) {
                arrayList.add(a);
            }
        }
        if (C0492c.m835a(context)) {
            JSONObject a2 = mo6647a(packageName, arrayList);
            if (a2 != null) {
                mo6631a(context, "android_awake", a2);
            }
        }
    }

    /* renamed from: d */
    public final void mo6652d(String str) {
        this.f539h = str;
    }
}
