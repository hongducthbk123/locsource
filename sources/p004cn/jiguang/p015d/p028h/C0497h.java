package p004cn.jiguang.p015d.p028h;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build.VERSION;
import android.support.p000v4.provider.FontsContractCompat.Columns;
import android.text.TextUtils;
import android.util.Base64;
import com.facebook.GraphResponse;
import com.google.android.vending.expansion.downloader.Constants;
import com.google.common.net.HttpHeaders;
import java.io.File;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.crypto.Cipher;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p004cn.jiguang.net.HttpRequest;
import p004cn.jiguang.net.HttpResponse;
import p004cn.jiguang.net.HttpUtils;
import p004cn.jiguang.p015d.p016a.C0386a;
import p004cn.jiguang.p015d.p016a.C0389d;
import p004cn.jiguang.p015d.p021d.C0444a;
import p004cn.jiguang.p015d.p026g.p027a.C0480a;
import p004cn.jiguang.p031g.C0506a;
import p004cn.jiguang.p031g.C0530k;

/* renamed from: cn.jiguang.d.h.h */
public final class C0497h extends C0490a {

    /* renamed from: g */
    private static final Object f547g = new Object();

    /* renamed from: e */
    private String f548e = null;

    /* renamed from: f */
    private ConcurrentLinkedQueue<C0444a> f549f = new ConcurrentLinkedQueue<>();

    /* renamed from: h */
    private boolean f550h = false;

    /* renamed from: i */
    private AtomicBoolean f551i = new AtomicBoolean(false);

    /* renamed from: a */
    private ArrayList<C0444a> m864a(Context context, String str) {
        if (str == null) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.optInt(Columns.RESULT_CODE) == 0) {
                return m865a(context, jSONObject.optString("pk_md"), jSONObject.optString("pk_list"));
            }
            return null;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    private static ArrayList<C0444a> m865a(Context context, String str, String str2) {
        try {
            RSAPublicKey d = m873d("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCvoyg9qkT/mHa4Av/zMWpzV0lsZoEA7eCtzI0TgRmF3QsPuiZI3zyThkVxyJMyWWH3/hnaJoqJYNIDM/oTRtiyICBeG/0L+BpZYtlv1/FVRPkS6OL3T7e2Xv79T1gCVr948X370lHebKbEzYv6sWlz5SwgMs/rrKSq9bPJqnmCnwIDAQAB");
            Cipher instance = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            instance.init(2, d);
            String str3 = new String(instance.doFinal(Base64.decode(str.getBytes(), 2)));
            String a = C0480a.m777a(str2, "DFA84B10B7ACDD25");
            String b = C0530k.m1101b(a);
            if (a == null || TextUtils.isEmpty(b)) {
                return null;
            }
            if (str3.compareToIgnoreCase(b) != 0) {
                return null;
            }
            ArrayList<C0444a> arrayList = new ArrayList<>();
            JSONArray jSONArray = new JSONArray(a);
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                C0444a aVar = new C0444a();
                aVar.f392a = jSONObject.optString("pk_name");
                aVar.f393b = jSONObject.optString("sv_name");
                if (TextUtils.isEmpty(aVar.f393b)) {
                    aVar.f393b = "cn.jpush.android.service.DaemonService";
                }
                ApplicationInfo g = C0506a.m970g(context, aVar.f392a);
                if (g != null) {
                    aVar.f394c = g.targetSdkVersion;
                }
                arrayList.add(aVar);
            }
            return arrayList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    private static JSONObject m866a(String str, ComponentName componentName, boolean z) {
        JSONObject jSONObject = new JSONObject();
        if (componentName == null) {
            return null;
        }
        try {
            jSONObject.put("awake_from", str);
            jSONObject.put("awake_to", componentName.getPackageName());
            jSONObject.put("awake_class", componentName.getClassName());
            jSONObject.put("awake_count", 1);
            jSONObject.put(GraphResponse.SUCCESS_KEY, z);
            return jSONObject;
        } catch (Throwable th) {
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x0028 A[SYNTHETIC, Splitter:B:21:0x0028] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x003a A[SYNTHETIC, Splitter:B:30:0x003a] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0049 A[SYNTHETIC, Splitter:B:37:0x0049] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized <T> void m867a(android.content.Context r5, java.lang.String r6, java.util.ArrayList<T> r7) {
        /*
            r4 = this;
            monitor-enter(r4)
            if (r5 != 0) goto L_0x0005
        L_0x0003:
            monitor-exit(r4)
            return
        L_0x0005:
            if (r7 == 0) goto L_0x0003
            r1 = 0
            java.io.ObjectOutputStream r0 = new java.io.ObjectOutputStream     // Catch:{ FileNotFoundException -> 0x0024, Exception -> 0x0034 }
            r2 = 0
            java.io.FileOutputStream r2 = r5.openFileOutput(r6, r2)     // Catch:{ FileNotFoundException -> 0x0024, Exception -> 0x0034 }
            r0.<init>(r2)     // Catch:{ FileNotFoundException -> 0x0024, Exception -> 0x0034 }
            r0.writeObject(r7)     // Catch:{ FileNotFoundException -> 0x005f, Exception -> 0x005a, all -> 0x0055 }
            r0.flush()     // Catch:{ Exception -> 0x001c }
            r0.close()     // Catch:{ Exception -> 0x001c }
            goto L_0x0003
        L_0x001c:
            r0 = move-exception
            r0.printStackTrace()     // Catch:{ all -> 0x0021 }
            goto L_0x0003
        L_0x0021:
            r0 = move-exception
            monitor-exit(r4)
            throw r0
        L_0x0024:
            r0 = move-exception
            r0 = r1
        L_0x0026:
            if (r0 == 0) goto L_0x0003
            r0.flush()     // Catch:{ Exception -> 0x002f }
            r0.close()     // Catch:{ Exception -> 0x002f }
            goto L_0x0003
        L_0x002f:
            r0 = move-exception
            r0.printStackTrace()     // Catch:{ all -> 0x0021 }
            goto L_0x0003
        L_0x0034:
            r0 = move-exception
        L_0x0035:
            r0.printStackTrace()     // Catch:{ all -> 0x0046 }
            if (r1 == 0) goto L_0x0003
            r1.flush()     // Catch:{ Exception -> 0x0041 }
            r1.close()     // Catch:{ Exception -> 0x0041 }
            goto L_0x0003
        L_0x0041:
            r0 = move-exception
            r0.printStackTrace()     // Catch:{ all -> 0x0021 }
            goto L_0x0003
        L_0x0046:
            r0 = move-exception
        L_0x0047:
            if (r1 == 0) goto L_0x004f
            r1.flush()     // Catch:{ Exception -> 0x0050 }
            r1.close()     // Catch:{ Exception -> 0x0050 }
        L_0x004f:
            throw r0     // Catch:{ all -> 0x0021 }
        L_0x0050:
            r1 = move-exception
            r1.printStackTrace()     // Catch:{ all -> 0x0021 }
            goto L_0x004f
        L_0x0055:
            r1 = move-exception
            r3 = r1
            r1 = r0
            r0 = r3
            goto L_0x0047
        L_0x005a:
            r1 = move-exception
            r3 = r1
            r1 = r0
            r0 = r3
            goto L_0x0035
        L_0x005f:
            r1 = move-exception
            goto L_0x0026
        */
        throw new UnsupportedOperationException("Method not decompiled: p004cn.jiguang.p015d.p028h.C0497h.m867a(android.content.Context, java.lang.String, java.util.ArrayList):void");
    }

    /* renamed from: a */
    private void m868a(Context context, ArrayList<C0493d> arrayList) {
        mo6631a(context, "app_awake", m872b(context, arrayList));
    }

    /* renamed from: a */
    private static boolean m869a(C0493d dVar) {
        if (dVar == null) {
            return false;
        }
        HashMap b = dVar.mo6646b();
        if (b == null || b.isEmpty()) {
            return false;
        }
        for (Integer intValue : b.keySet()) {
            if (((Boolean) b.get(Integer.valueOf(intValue.intValue()))).booleanValue()) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: b */
    private String m870b() {
        JSONObject jSONObject = new JSONObject();
        if (this.f527b == null) {
            this.f527b = "";
        }
        try {
            jSONObject.put("app_key", this.f527b);
            jSONObject.put("sdk_ver", this.f529d);
            jSONObject.put("os", VERSION.RELEASE + "," + Integer.toString(VERSION.SDK_INT));
            jSONObject.put("post_type", 1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject.toString();
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x0040 A[SYNTHETIC, Splitter:B:26:0x0040] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0050 A[SYNTHETIC, Splitter:B:36:0x0050] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x005d  */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized java.util.ArrayList<p004cn.jiguang.p015d.p021d.C0444a> m871b(android.content.Context r6, java.lang.String r7) {
        /*
            r5 = this;
            r0 = 0
            monitor-enter(r5)
            if (r6 != 0) goto L_0x0006
        L_0x0004:
            monitor-exit(r5)
            return r0
        L_0x0006:
            java.io.File r1 = new java.io.File     // Catch:{ all -> 0x0034 }
            java.io.File r2 = r6.getFilesDir()     // Catch:{ all -> 0x0034 }
            java.lang.String r3 = "Jpush_awake_file_list"
            r1.<init>(r2, r3)     // Catch:{ all -> 0x0034 }
            boolean r1 = r1.exists()     // Catch:{ all -> 0x0034 }
            if (r1 == 0) goto L_0x0004
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ all -> 0x0034 }
            r1.<init>()     // Catch:{ all -> 0x0034 }
            java.io.ObjectInputStream r2 = new java.io.ObjectInputStream     // Catch:{ Exception -> 0x0037, all -> 0x004b }
            java.io.FileInputStream r3 = r6.openFileInput(r7)     // Catch:{ Exception -> 0x0037, all -> 0x004b }
            r2.<init>(r3)     // Catch:{ Exception -> 0x0037, all -> 0x004b }
            java.lang.Object r0 = r2.readObject()     // Catch:{ Exception -> 0x005b }
            java.util.ArrayList r0 = (java.util.ArrayList) r0     // Catch:{ Exception -> 0x005b }
            r2.close()     // Catch:{ Exception -> 0x002f }
            goto L_0x0004
        L_0x002f:
            r1 = move-exception
            r1.printStackTrace()     // Catch:{ all -> 0x0034 }
            goto L_0x0004
        L_0x0034:
            r0 = move-exception
            monitor-exit(r5)
            throw r0
        L_0x0037:
            r2 = move-exception
            r4 = r2
            r2 = r0
            r0 = r4
        L_0x003b:
            r0.printStackTrace()     // Catch:{ all -> 0x0059 }
            if (r2 == 0) goto L_0x005d
            r2.close()     // Catch:{ Exception -> 0x0045 }
            r0 = r1
            goto L_0x0004
        L_0x0045:
            r0 = move-exception
            r0.printStackTrace()     // Catch:{ all -> 0x0034 }
            r0 = r1
            goto L_0x0004
        L_0x004b:
            r1 = move-exception
            r2 = r0
            r0 = r1
        L_0x004e:
            if (r2 == 0) goto L_0x0053
            r2.close()     // Catch:{ Exception -> 0x0054 }
        L_0x0053:
            throw r0     // Catch:{ all -> 0x0034 }
        L_0x0054:
            r1 = move-exception
            r1.printStackTrace()     // Catch:{ all -> 0x0034 }
            goto L_0x0053
        L_0x0059:
            r0 = move-exception
            goto L_0x004e
        L_0x005b:
            r0 = move-exception
            goto L_0x003b
        L_0x005d:
            r0 = r1
            goto L_0x0004
        */
        throw new UnsupportedOperationException("Method not decompiled: p004cn.jiguang.p015d.p028h.C0497h.m871b(android.content.Context, java.lang.String):java.util.ArrayList");
    }

    /* renamed from: b */
    private JSONObject m872b(Context context, ArrayList<C0493d> arrayList) {
        if (arrayList != null) {
            try {
                if (!arrayList.isEmpty()) {
                    String packageName = context.getApplicationContext().getPackageName();
                    JSONObject jSONObject = new JSONObject();
                    JSONArray jSONArray = new JSONArray();
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        C0493d dVar = (C0493d) it.next();
                        if (dVar != null) {
                            JSONObject a = m866a(packageName, dVar.mo6643a(), m869a(dVar));
                            if (a != null) {
                                jSONArray.put(a);
                            }
                        }
                    }
                    jSONObject.put("awake_path", jSONArray);
                    return jSONObject;
                }
            } catch (Throwable th) {
                return null;
            }
        }
        return null;
    }

    /* renamed from: d */
    private static RSAPublicKey m873d(String str) {
        try {
            return (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.decode(str, 2)));
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("无此算法");
        } catch (InvalidKeySpecException e2) {
            throw new Exception("公钥非法");
        } catch (Exception e3) {
            throw new Exception("公钥数据为空");
        }
    }

    /* renamed from: e */
    private List<C0444a> m874e(Context context) {
        String str = this.f548e;
        String b = m870b();
        try {
            HttpRequest httpRequest = new HttpRequest(str);
            httpRequest.setRequestProperty("Connection", HTTP.CONN_CLOSE);
            httpRequest.setRequestProperty(HttpHeaders.ACCEPT_ENCODING, HTTP.IDENTITY_CODING);
            httpRequest.setRequestProperty("Content-Type", "application/json");
            httpRequest.setBody(b);
            HttpResponse httpPost = HttpUtils.httpPost(context, httpRequest);
            if (httpPost != null && httpPost.getResponseCode() == 200) {
                C0386a.m249a("jpush_awake_app_pk", System.currentTimeMillis() / 1000);
                return m864a(context, httpPost.getResponseBody());
            }
        } catch (Throwable th) {
        }
        return null;
    }

    /* renamed from: f */
    private void m875f(Context context) {
        if (this.f550h) {
            ArrayList arrayList = new ArrayList();
            arrayList.addAll(this.f549f);
            m867a(context, "Jpush_awake_file_list", arrayList);
            this.f550h = false;
        }
    }

    /* renamed from: a */
    public final void mo6658a(Context context, C0444a aVar) {
        int i = 1;
        try {
            if (!this.f549f.contains(aVar)) {
                this.f549f.add(aVar);
                this.f550h = true;
            }
        } catch (Throwable th) {
        }
        m875f(context);
        if (!this.f551i.get()) {
            ApplicationInfo g = C0506a.m970g(context, aVar.f392a);
            if (g != null) {
                aVar.f394c = g.targetSdkVersion;
            }
            if (VERSION.SDK_INT >= 26 && aVar.f394c >= 26) {
                i = 2;
            }
            ArrayList arrayList = new ArrayList();
            C0493d a = C0492c.m834a(context, 2, i, aVar, null);
            if (a != null) {
                arrayList.add(a);
            }
            m868a(context, arrayList);
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final boolean mo6635a(Context context) {
        if (context == null) {
            return true;
        }
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        long l = C0389d.m341l(context);
        return -1 == l || Math.abs(currentTimeMillis - l) > this.f526a;
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public final void mo6637b(Context context) {
        C0389d.m323b(context, System.currentTimeMillis() / 1000);
    }

    /* renamed from: b */
    public final void mo6659b(Context context, C0444a aVar) {
        if (this.f549f.contains(aVar)) {
            try {
                this.f549f.remove(aVar);
                this.f550h = true;
            } catch (Throwable th) {
            }
        }
        m875f(context);
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public final ArrayList<C0444a> mo6639c(Context context) {
        ArrayList<C0444a> arrayList = (ArrayList) m874e(context);
        if (arrayList != null && arrayList.size() > 0) {
            if (arrayList.size() != 1 || !C0530k.m1099a(((C0444a) arrayList.get(0)).f392a)) {
                m867a(context, "Jpush_awake_file_list", arrayList);
                return arrayList;
            }
            File file = new File(context.getFilesDir(), "Jpush_awake_file_list");
            if (file.exists()) {
                file.delete();
            }
        }
        return null;
    }

    /* renamed from: c */
    public final void mo6660c(String str) {
        this.f548e = str;
    }

    /* renamed from: d */
    public final void mo6640d(Context context) {
        this.f551i.set(true);
        try {
            Thread.sleep(Constants.ACTIVE_THREAD_WATCHDOG);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (Math.abs((System.currentTimeMillis() / 1000) - C0386a.m283k("jpush_awake_app_pk")) > 86400) {
            ArrayList c = mo6639c(context);
            if (c != null) {
                this.f549f.clear();
                this.f549f.addAll(c);
            }
        } else if (this.f549f.isEmpty()) {
            ArrayList b = m871b(context, "Jpush_awake_file_list");
            if (b != null) {
                this.f549f.clear();
                this.f549f.addAll(b);
            }
        }
        ArrayList arrayList = new ArrayList();
        Iterator it = this.f549f.iterator();
        while (it.hasNext()) {
            C0444a aVar = (C0444a) it.next();
            C0493d a = C0492c.m834a(context, 2, (VERSION.SDK_INT < 26 || aVar.f394c < 26) ? 1 : 2, aVar, null);
            if (a != null) {
                arrayList.add(a);
            }
        }
        this.f551i.set(false);
        m868a(context, arrayList);
    }
}
