package p004cn.jiguang.p015d.p021d;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.URLUtil;
import com.adjust.sdk.Constants;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p004cn.jiguang.api.C0378a;
import p004cn.jiguang.api.JCoreInterface;
import p004cn.jiguang.p015d.C0385a;
import p004cn.jiguang.p015d.p016a.C0386a;
import p004cn.jiguang.p015d.p016a.C0389d;
import p004cn.jiguang.p015d.p026g.C0484e;
import p004cn.jiguang.p015d.p026g.C0487h;
import p004cn.jiguang.p029e.C0501d;
import p004cn.jiguang.p031g.C0506a;
import p004cn.jiguang.p031g.C0526g;
import p004cn.jiguang.p031g.C0530k;
import p004cn.jpush.android.service.PushReceiver;

/* renamed from: cn.jiguang.d.d.q */
public final class C0460q {

    /* renamed from: a */
    public static JSONObject f435a = null;

    /* renamed from: b */
    private static String f436b = "";

    /* renamed from: c */
    private static String f437c = "/v1/report";

    /* renamed from: d */
    private static String f438d = "/v2/report";

    /* renamed from: e */
    private static ExecutorService f439e = Executors.newSingleThreadExecutor();

    /* renamed from: f */
    private static final Object f440f = new Object();

    /* renamed from: a */
    public static int m701a(Context context, JSONObject jSONObject, File file) {
        boolean z;
        if (jSONObject == null || jSONObject.length() == 0) {
            return -1;
        }
        if (!C0506a.m957c(context)) {
            return -2;
        }
        String a = m703a(2);
        if (C0530k.m1099a(a)) {
            z = false;
        } else {
            f436b = m702a();
            z = (C0530k.m1099a(a) || C0530k.m1099a(f436b)) ? false : a.equals(f436b) ? URLUtil.isHttpUrl(a) : URLUtil.isHttpsUrl(a);
        }
        if (!z) {
            return -2;
        }
        switch (C0452i.m664a(a, jSONObject.toString(), context, true, 3).mo6586a()) {
            case -3:
                C0457n.m693a(context);
                return -2;
            case -1:
                return -2;
            case 0:
                C0484e.m800a(file);
                return 0;
            default:
                return -1;
        }
    }

    /* renamed from: a */
    private static String m702a() {
        if (C0530k.m1099a(f436b) && "CN".equals(C0385a.f199f.mo6345a())) {
            m704a(C0389d.m327c());
        }
        return f436b;
    }

    /* renamed from: a */
    private static String m703a(int i) {
        String f = C0385a.f199f.mo6350f();
        try {
            InetAddress.getByName(f);
            return ("https://" + f) + f438d;
        } catch (Exception e) {
            return m702a();
        }
    }

    /* renamed from: a */
    public static String m704a(String str) {
        if (C0530k.m1099a(str)) {
            return "";
        }
        if (!str.startsWith("http://")) {
            str = "http://" + str;
        }
        if (!str.endsWith(f438d)) {
            str = str + f438d;
        }
        f436b = str;
        return str;
    }

    /* renamed from: a */
    private static ArrayList<JSONArray> m705a(JSONArray jSONArray, boolean z, int i, int i2) {
        int i3;
        int i4;
        int i5;
        JSONArray jSONArray2;
        int i6 = 0;
        ArrayList arrayList = new ArrayList();
        if (jSONArray == null || jSONArray.length() == 0) {
            return arrayList;
        }
        JSONArray jSONArray3 = new JSONArray();
        int length = jSONArray.length() - 1;
        int i7 = 0;
        while (length >= 0) {
            JSONObject optJSONObject = jSONArray.optJSONObject(length);
            if (!(optJSONObject == null || optJSONObject.length() == 0)) {
                if (z) {
                    try {
                        int a = C0487h.m813a(optJSONObject);
                        if (i6 + a > 204800) {
                            break;
                        }
                        if (i7 + a > 40960) {
                            if (jSONArray3.length() > 0) {
                                arrayList.add(jSONArray3);
                            }
                            jSONArray2 = new JSONArray();
                            try {
                                jSONArray2.put(optJSONObject);
                                i5 = a;
                            } catch (Exception e) {
                                jSONArray3 = jSONArray2;
                                i3 = i6;
                                i4 = i7;
                                length--;
                                i7 = i4;
                                i6 = i3;
                            }
                        } else {
                            jSONArray3.put(optJSONObject);
                            JSONArray jSONArray4 = jSONArray3;
                            i5 = i7 + a;
                            jSONArray2 = jSONArray4;
                        }
                        JSONArray jSONArray5 = jSONArray2;
                        i3 = a + i6;
                        i4 = i5;
                        jSONArray3 = jSONArray5;
                        length--;
                        i7 = i4;
                        i6 = i3;
                    } catch (Exception e2) {
                        i3 = i6;
                        i4 = i7;
                        length--;
                        i7 = i4;
                        i6 = i3;
                    }
                } else {
                    jSONArray3.put(optJSONObject);
                }
            }
            i3 = i6;
            i4 = i7;
            length--;
            i7 = i4;
            i6 = i3;
        }
        if (jSONArray3.length() > 0) {
            arrayList.add(jSONArray3);
        }
        return arrayList;
    }

    /* renamed from: a */
    public static JSONObject m706a(Context context, String str) {
        FileInputStream fileInputStream;
        Throwable th;
        if (str.length() <= 0) {
            return null;
        }
        m723e(str);
        if (context == null) {
            return null;
        }
        try {
            fileInputStream = context.openFileInput(str);
            try {
                byte[] bArr = new byte[(fileInputStream.available() + 1)];
                fileInputStream.read(bArr);
                C0526g.m1087a((Closeable) fileInputStream);
                try {
                    String str2 = new String(bArr, "UTF-8");
                    if (!C0530k.m1099a(str2)) {
                        return new JSONObject(str2);
                    }
                    return null;
                } catch (UnsupportedEncodingException | JSONException e) {
                    return null;
                }
            } catch (FileNotFoundException e2) {
                C0526g.m1087a((Closeable) fileInputStream);
                return null;
            } catch (IOException e3) {
                C0526g.m1087a((Closeable) fileInputStream);
                return null;
            } catch (Throwable th2) {
                th = th2;
                C0526g.m1087a((Closeable) fileInputStream);
                throw th;
            }
        } catch (FileNotFoundException e4) {
            fileInputStream = null;
        } catch (IOException e5) {
            fileInputStream = null;
            C0526g.m1087a((Closeable) fileInputStream);
            return null;
        } catch (Throwable th3) {
            Throwable th4 = th3;
            fileInputStream = null;
            th = th4;
            C0526g.m1087a((Closeable) fileInputStream);
            throw th;
        }
    }

    /* renamed from: a */
    public static JSONObject m707a(Context context, JSONObject jSONObject, String str) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        try {
            jSONObject.put("itime", JCoreInterface.getReportTime());
            jSONObject.put("type", str);
            jSONObject.put("account_id", C0389d.m343n(context));
        } catch (JSONException e) {
        }
        return jSONObject;
    }

    /* renamed from: a */
    private static JSONObject m708a(JSONArray jSONArray, JSONObject jSONObject) {
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("content", jSONArray);
        } catch (JSONException e) {
        }
        C0487h.m815a(jSONObject2, jSONObject);
        return jSONObject2;
    }

    /* renamed from: a */
    public static void m709a(Context context) {
        C0457n.m697b(context);
    }

    /* renamed from: a */
    public static void m710a(Context context, int i) {
        int i2;
        JSONObject jSONObject;
        int i3 = 0;
        if (f435a != null) {
            JSONObject jSONObject2 = f435a;
            if (i >= 204800) {
                m725f(context);
                return;
            }
            try {
                i2 = jSONObject2.toString().getBytes("utf-8").length;
            } catch (UnsupportedEncodingException e) {
                i2 = 0;
            }
            int i4 = (i2 + i) - 204800;
            if (i4 > 0) {
                JSONArray optJSONArray = jSONObject2.optJSONArray("content");
                if (optJSONArray != null && optJSONArray.length() > 0) {
                    try {
                        JSONArray jSONArray = new JSONArray();
                        for (int i5 = 0; i5 < optJSONArray.length(); i5++) {
                            JSONObject jSONObject3 = optJSONArray.getJSONObject(i5);
                            if (jSONObject3 != null) {
                                if (i3 >= i4) {
                                    jSONArray.put(jSONObject3);
                                }
                                i3 += jSONObject3.toString().getBytes("utf-8").length;
                            }
                        }
                        if (jSONArray.length() > 0) {
                            jSONObject2.put("content", jSONArray);
                            jSONObject = jSONObject2;
                        } else {
                            jSONObject = null;
                        }
                        f435a = jSONObject;
                        m716a(context, "jpush_stat_cache_history.json", jSONObject);
                    } catch (UnsupportedEncodingException | JSONException e2) {
                    }
                }
            }
        }
    }

    /* renamed from: a */
    public static void m711a(Context context, JSONArray jSONArray) {
        m713a(context, jSONArray, false, true);
    }

    /* renamed from: a */
    static /* synthetic */ void m712a(Context context, JSONArray jSONArray, String str, boolean z) {
        File a;
        boolean z2;
        if (jSONArray != null) {
            try {
                if (jSONArray.length() != 0) {
                    JSONArray jSONArray2 = new JSONArray();
                    for (int i = 0; i < jSONArray.length(); i++) {
                        Object opt = jSONArray.opt(i);
                        if ((opt instanceof JSONObject) && ((JSONObject) opt).length() > 0) {
                            jSONArray2.put(opt);
                        }
                    }
                    jSONArray = jSONArray2;
                }
            } catch (Exception e) {
            } catch (Throwable th) {
                C0457n.m695a(a);
                throw th;
            }
        }
        if (jSONArray != null && jSONArray.length() > 0) {
            JSONObject d = m722d(context);
            ArrayList a2 = m705a(jSONArray, z, 40960, 204800);
            boolean z3 = d == null;
            Iterator it = a2.iterator();
            while (it.hasNext()) {
                JSONObject a3 = m708a((JSONArray) it.next(), d);
                a = C0457n.m691a(context, File.separator + str + File.separator + (d != null ? "tmp" : "nowrap"), a3);
                if (!z3) {
                    if (m701a(context, a3, a) == -2) {
                        z2 = true;
                        C0457n.m695a(a);
                        z3 = z2;
                    }
                }
                z2 = z3;
                C0457n.m695a(a);
                z3 = z2;
            }
        }
        C0457n.m697b(context);
    }

    /* renamed from: a */
    public static void m713a(Context context, JSONArray jSONArray, boolean z, boolean z2) {
        f439e.execute(new C0461r(context, jSONArray, z, z2));
    }

    /* renamed from: a */
    public static void m714a(Context context, JSONObject jSONObject) {
        m713a(context, new JSONArray().put(jSONObject), false, false);
    }

    /* renamed from: a */
    public static void m715a(Context context, JSONObject jSONObject, C0378a aVar) {
        if (jSONObject != null) {
            try {
                if (jSONObject.length() > 0) {
                    JSONObject d = m722d(context);
                    if (d != null) {
                        int a = m701a(context, m708a(new JSONArray().put(jSONObject), d), (File) null);
                        if (aVar != null) {
                            aVar.mo6202a(a);
                        }
                    } else if (aVar != null) {
                        aVar.mo6202a(-1);
                    }
                }
            } catch (Exception e) {
            }
        }
        C0457n.m697b(context);
    }

    /* JADX INFO: used method not loaded: cn.jiguang.g.g.a(java.io.Closeable):null, types can be incorrect */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x003e, code lost:
        p004cn.jiguang.p031g.C0526g.m1087a((java.io.Closeable) r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0045, code lost:
        p004cn.jiguang.p031g.C0526g.m1087a((java.io.Closeable) r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x004c, code lost:
        p004cn.jiguang.p031g.C0526g.m1087a((java.io.Closeable) r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0052, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0053, code lost:
        p004cn.jiguang.p031g.C0526g.m1087a((java.io.Closeable) r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0056, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:?, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:?, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:?, code lost:
        return false;
     */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x003d A[Catch:{ FileNotFoundException -> 0x005a, UnsupportedEncodingException -> 0x003d, IOException -> 0x0044, NullPointerException -> 0x004b, all -> 0x0052, FileNotFoundException -> 0x0035, UnsupportedEncodingException -> 0x003d, IOException -> 0x0044, NullPointerException -> 0x004b, all -> 0x0052 }, ExcHandler: UnsupportedEncodingException (e java.io.UnsupportedEncodingException), Splitter:B:13:0x0022] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0044 A[Catch:{ FileNotFoundException -> 0x005a, UnsupportedEncodingException -> 0x003d, IOException -> 0x0044, NullPointerException -> 0x004b, all -> 0x0052, FileNotFoundException -> 0x0035, UnsupportedEncodingException -> 0x003d, IOException -> 0x0044, NullPointerException -> 0x004b, all -> 0x0052 }, ExcHandler: IOException (e java.io.IOException), Splitter:B:13:0x0022] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x004b A[Catch:{ FileNotFoundException -> 0x005a, UnsupportedEncodingException -> 0x003d, IOException -> 0x0044, NullPointerException -> 0x004b, all -> 0x0052, FileNotFoundException -> 0x0035, UnsupportedEncodingException -> 0x003d, IOException -> 0x0044, NullPointerException -> 0x004b, all -> 0x0052 }, ExcHandler: NullPointerException (e java.lang.NullPointerException), Splitter:B:13:0x0022] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0052 A[Catch:{ FileNotFoundException -> 0x005a, UnsupportedEncodingException -> 0x003d, IOException -> 0x0044, NullPointerException -> 0x004b, all -> 0x0052, FileNotFoundException -> 0x0035, UnsupportedEncodingException -> 0x003d, IOException -> 0x0044, NullPointerException -> 0x004b, all -> 0x0052 }, ExcHandler: all (r0v4 'th' java.lang.Throwable A[CUSTOM_DECLARE, Catch:{  }]), Splitter:B:13:0x0022] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean m716a(android.content.Context r5, java.lang.String r6, org.json.JSONObject r7) {
        /*
            r1 = 0
            boolean r0 = p004cn.jiguang.p031g.C0530k.m1099a(r6)
            if (r0 == 0) goto L_0x0009
            r0 = r1
        L_0x0008:
            return r0
        L_0x0009:
            m723e(r6)
            if (r5 != 0) goto L_0x0010
            r0 = r1
            goto L_0x0008
        L_0x0010:
            java.lang.Object r3 = f440f
            monitor-enter(r3)
            java.lang.String r0 = ""
            if (r7 == 0) goto L_0x0020
            java.lang.String r0 = r7.toString()     // Catch:{ all -> 0x0057 }
            java.lang.String r2 = "jpush_stat_cache_history.json"
            r6.equals(r2)     // Catch:{ all -> 0x0057 }
        L_0x0020:
            r2 = 0
            r4 = 0
            java.io.FileOutputStream r2 = r5.openFileOutput(r6, r4)     // Catch:{ FileNotFoundException -> 0x0035, UnsupportedEncodingException -> 0x003d, IOException -> 0x0044, NullPointerException -> 0x004b, all -> 0x0052 }
            java.lang.String r4 = "UTF-8"
            byte[] r0 = r0.getBytes(r4)     // Catch:{ FileNotFoundException -> 0x005a, UnsupportedEncodingException -> 0x003d, IOException -> 0x0044, NullPointerException -> 0x004b, all -> 0x0052 }
            r2.write(r0)     // Catch:{ FileNotFoundException -> 0x005a, UnsupportedEncodingException -> 0x003d, IOException -> 0x0044, NullPointerException -> 0x004b, all -> 0x0052 }
            p004cn.jiguang.p031g.C0526g.m1087a(r2)     // Catch:{ all -> 0x0057 }
            monitor-exit(r3)     // Catch:{ all -> 0x0057 }
            r0 = 1
            goto L_0x0008
        L_0x0035:
            r0 = move-exception
            r0 = r2
        L_0x0037:
            p004cn.jiguang.p031g.C0526g.m1087a(r0)     // Catch:{ all -> 0x0057 }
            monitor-exit(r3)     // Catch:{ all -> 0x0057 }
            r0 = r1
            goto L_0x0008
        L_0x003d:
            r0 = move-exception
            p004cn.jiguang.p031g.C0526g.m1087a(r2)     // Catch:{ all -> 0x0057 }
            monitor-exit(r3)     // Catch:{ all -> 0x0057 }
            r0 = r1
            goto L_0x0008
        L_0x0044:
            r0 = move-exception
            p004cn.jiguang.p031g.C0526g.m1087a(r2)     // Catch:{ all -> 0x0057 }
            monitor-exit(r3)     // Catch:{ all -> 0x0057 }
            r0 = r1
            goto L_0x0008
        L_0x004b:
            r0 = move-exception
            p004cn.jiguang.p031g.C0526g.m1087a(r2)     // Catch:{ all -> 0x0057 }
            monitor-exit(r3)     // Catch:{ all -> 0x0057 }
            r0 = r1
            goto L_0x0008
        L_0x0052:
            r0 = move-exception
            p004cn.jiguang.p031g.C0526g.m1087a(r2)     // Catch:{ all -> 0x0057 }
            throw r0     // Catch:{ all -> 0x0057 }
        L_0x0057:
            r0 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x0057 }
            throw r0
        L_0x005a:
            r0 = move-exception
            r0 = r2
            goto L_0x0037
        */
        throw new UnsupportedOperationException("Method not decompiled: p004cn.jiguang.p015d.p021d.C0460q.m716a(android.content.Context, java.lang.String, org.json.JSONObject):boolean");
    }

    /* renamed from: b */
    public static String m717b(String str) {
        String f = C0385a.f199f.mo6350f();
        try {
            InetAddress.getByName(f);
            return "https://" + f + str;
        } catch (Exception e) {
            return m702a();
        }
    }

    /* renamed from: b */
    public static void m718b(Context context) {
        try {
            C0453j.m665a().mo6568b(context, "report_history", new Bundle());
        } catch (Throwable th) {
        }
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [android.content.Context, java.lang.String] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r0v0, types: [android.content.Context, java.lang.String]
      assigns: [?[int, float, boolean, short, byte, char, OBJECT, ARRAY]]
      uses: [java.lang.String, android.content.Context]
      mth insns count: 30
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
    	at jadx.core.ProcessClass.process(ProcessClass.java:35)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* renamed from: c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String m719c(java.lang.String r6) {
        /*
            r0 = 0
            boolean r1 = p004cn.jiguang.p031g.C0530k.m1099a(r6)
            if (r1 == 0) goto L_0x0008
        L_0x0007:
            return r0
        L_0x0008:
            long r2 = p004cn.jiguang.p015d.p016a.C0389d.m331d(r0)
            r4 = 0
            int r1 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r1 == 0) goto L_0x0007
            java.lang.String r1 = p004cn.jiguang.p015d.p016a.C0389d.m335f(r0)
            java.lang.String r1 = p004cn.jiguang.p031g.C0506a.m950b(r1)
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.StringBuilder r4 = r4.append(r2)
            java.lang.StringBuilder r1 = r4.append(r1)
            java.lang.StringBuilder r1 = r1.append(r6)
            java.lang.String r1 = r1.toString()
            java.lang.String r1 = p004cn.jiguang.p031g.C0506a.m950b(r1)
            boolean r4 = p004cn.jiguang.p031g.C0530k.m1099a(r1)
            if (r4 != 0) goto L_0x0007
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.StringBuilder r2 = r4.append(r2)
            java.lang.String r3 = ":"
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.StringBuilder r1 = r2.append(r1)
            java.lang.String r1 = r1.toString()
            byte[] r1 = r1.getBytes()     // Catch:{ Exception -> 0x005b }
            r2 = 10
            java.lang.String r0 = android.util.Base64.encodeToString(r1, r2)     // Catch:{ Exception -> 0x005b }
            goto L_0x0007
        L_0x005b:
            r1 = move-exception
            goto L_0x0007
        */
        throw new UnsupportedOperationException("Method not decompiled: p004cn.jiguang.p015d.p021d.C0460q.m719c(java.lang.String):java.lang.String");
    }

    /* renamed from: c */
    public static void m720c(Context context) {
        try {
            Intent intent = new Intent(context, PushReceiver.class);
            intent.setAction("cn.jpush.android.intent.ACTION_REPORT_HISTORY");
            String packageName = context.getPackageName();
            intent.addCategory(packageName);
            intent.setPackage(packageName);
            context.sendBroadcast(intent);
        } catch (Throwable th) {
        }
    }

    /* renamed from: d */
    public static long m721d(String str) {
        if (str.endsWith("prior")) {
            return 512000;
        }
        if (str.endsWith(Constants.NORMAL)) {
        }
        return 1048576;
    }

    /* renamed from: d */
    public static JSONObject m722d(Context context) {
        boolean z = false;
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("platform", "a");
            long d = C0389d.m331d(context);
            if (d != 0) {
                jSONObject.put("uid", d);
                String i = C0389d.m338i(context);
                if (!C0530k.m1099a(i)) {
                    jSONObject.put("app_key", i);
                    C0445b.m618a().mo6553a(jSONObject);
                    jSONObject.put("core_sdk_ver", "1.2.0");
                    String g = C0386a.m275g("");
                    if (!C0530k.m1099a(g)) {
                        jSONObject.put("channel", g);
                    } else {
                        C0501d.m907c("ReportUtils", "miss channel when wrap container info,but continue report...");
                    }
                    if (!C0530k.m1099a(C0385a.f202i)) {
                        jSONObject.put("app_version", C0385a.f202i);
                    } else {
                        C0501d.m907c("ReportUtils", "miss app version when wrap container info,but continue report...");
                    }
                    z = true;
                }
            }
            if (z) {
                return jSONObject;
            }
        } catch (Exception e) {
        }
        return null;
    }

    /* renamed from: e */
    private static String m723e(String str) {
        if (C0530k.m1099a(str)) {
            return null;
        }
        return str.equals("jpush_stat_cache_history.json") ? "history_file" : "current_session_file";
    }

    /* renamed from: e */
    public static void m724e(Context context) {
        m716a(context, "jpush_stat_cache.json", (JSONObject) null);
        m725f(context);
        m716a(context, "jpush_stat_cache_history.json", (JSONObject) null);
    }

    /* renamed from: f */
    private static void m725f(Context context) {
        f435a = null;
        if (!m716a(context, "jpush_stat_cache_history.json", (JSONObject) null)) {
            try {
                context.deleteFile(m723e("jpush_stat_cache_history.json"));
            } catch (Exception | IllegalArgumentException e) {
            }
        }
    }
}
