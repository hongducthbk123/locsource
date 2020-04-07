package p004cn.jiguang.p005a.p006a.p009c;

import android.content.Context;
import android.content.pm.PackageInfo;
import com.facebook.devicerequests.internal.DeviceRequestsHelper;
import com.facebook.share.internal.ShareConstants;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.regex.PatternSyntaxException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p004cn.jiguang.p015d.p016a.C0386a;
import p004cn.jiguang.p015d.p016a.C0389d;
import p004cn.jiguang.p015d.p021d.C0460q;
import p004cn.jiguang.p031g.C0506a;
import p004cn.jiguang.p031g.C0530k;

/* renamed from: cn.jiguang.a.a.c.e */
public final class C0358e implements UncaughtExceptionHandler {

    /* renamed from: b */
    private static C0358e f115b = new C0358e();

    /* renamed from: c */
    private static int f116c = 1048576;

    /* renamed from: a */
    public boolean f117a;

    /* renamed from: d */
    private UncaughtExceptionHandler f118d;
    /* access modifiers changed from: private */

    /* renamed from: e */
    public Context f119e;

    private C0358e() {
        this.f118d = null;
        this.f117a = true;
        this.f117a = C0389d.m326b();
    }

    /* renamed from: a */
    public static C0358e m109a() {
        return f115b;
    }

    /* renamed from: a */
    private static String m110a(Throwable th, String str) {
        String str2 = str + th.toString();
        try {
            String[] split = str2.split(":");
            if (split.length <= 1) {
                return str2;
            }
            for (int length = split.length - 1; length >= 0; length--) {
                if (split[length].endsWith("Exception") || split[length].endsWith("Error")) {
                    return split[length];
                }
            }
            return str2;
        } catch (NullPointerException | PatternSyntaxException e) {
            return str2;
        }
    }

    /* renamed from: a */
    private JSONArray m111a(Context context, JSONArray jSONArray, Throwable th, String str) {
        JSONObject jSONObject;
        int i;
        StringWriter stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter));
        String stringWriter2 = stringWriter.toString();
        if (jSONArray == null) {
            jSONArray = new JSONArray();
        }
        try {
            long u = C0386a.m294u();
            int i2 = 0;
            while (true) {
                if (i2 >= jSONArray.length()) {
                    jSONObject = null;
                    i = 0;
                    break;
                }
                jSONObject = jSONArray.optJSONObject(i2);
                if (jSONObject != null && stringWriter2.equals(jSONObject.getString("stacktrace"))) {
                    jSONObject.put("count", jSONObject.getInt("count") + 1);
                    jSONObject.put("crashtime", System.currentTimeMillis() + u);
                    i = i2;
                    break;
                }
                i2++;
            }
            if (jSONObject != null) {
                JSONArray a = m112a(jSONArray, i);
                a.put(jSONObject);
                return a;
            }
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("crashtime", System.currentTimeMillis() + u);
            jSONObject2.put("stacktrace", stringWriter2);
            jSONObject2.put(ShareConstants.WEB_DIALOG_PARAM_MESSAGE, m110a(th, str));
            jSONObject2.put("count", 1);
            if (!(this.f119e == null && context == null)) {
                jSONObject2.put("networktype", C0506a.m959d(context));
            }
            if (this.f119e != null) {
                PackageInfo packageInfo = this.f119e.getPackageManager().getPackageInfo(this.f119e.getPackageName(), 1);
                if (packageInfo != null) {
                    String str2 = packageInfo.versionName == null ? "null" : packageInfo.versionName;
                    String str3 = packageInfo.versionCode;
                    jSONObject2.put("versionname", str2);
                    jSONObject2.put("versioncode", str3);
                }
            }
            jSONArray.put(jSONObject2);
            return jSONArray;
        } catch (Throwable th2) {
            return jSONArray;
        }
    }

    /* renamed from: a */
    private static JSONArray m112a(JSONArray jSONArray, int i) {
        if (jSONArray == null) {
            return null;
        }
        JSONArray jSONArray2 = new JSONArray();
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            if (i2 != i) {
                try {
                    jSONArray2.put(jSONArray.get(i2));
                } catch (JSONException e) {
                }
            }
        }
        return jSONArray2;
    }

    /* renamed from: a */
    public static void m113a(Context context, String str) {
        if (!C0530k.m1099a(str) && str.equals("crash_log")) {
            C0358e eVar = f115b;
            if (context != null && C0389d.m334e(context)) {
                JSONObject e = m116e(context);
                if (e != null) {
                    C0460q.m714a(context, e);
                    m115d(context);
                }
            }
        }
    }

    /* renamed from: a */
    private static void m114a(Context context, JSONArray jSONArray) {
        String jSONArray2 = jSONArray.toString();
        if (jSONArray2 != null && jSONArray2.length() > 0 && context != null) {
            try {
                FileOutputStream openFileOutput = context.openFileOutput("jpush_uncaughtexception_file", 0);
                openFileOutput.write(jSONArray2.getBytes());
                openFileOutput.flush();
                openFileOutput.close();
            } catch (IOException e) {
            }
        }
    }

    /* renamed from: d */
    public static void m115d(Context context) {
        if (context != null) {
            File file = new File(context.getFilesDir(), "jpush_uncaughtexception_file");
            if (file.exists()) {
                file.delete();
            }
        }
    }

    /* renamed from: e */
    public static JSONObject m116e(Context context) {
        JSONArray f = m117f(context);
        if (f == null) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("crashlogs", f);
            jSONObject.put("network_type", C0506a.m959d(context));
            C0460q.m707a(context, jSONObject, "crash_log");
            JSONObject a = C0355b.m97a(context);
            if (a == null || a.length() <= 0) {
                return jSONObject;
            }
            jSONObject.put(DeviceRequestsHelper.DEVICE_INFO_PARAM, a);
            return jSONObject;
        } catch (Exception e) {
            return jSONObject;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0039 A[SYNTHETIC, Splitter:B:16:0x0039] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0066 A[SYNTHETIC, Splitter:B:33:0x0066] */
    /* renamed from: f */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static org.json.JSONArray m117f(android.content.Context r7) {
        /*
            r0 = 0
            java.io.File r1 = new java.io.File
            java.io.File r2 = r7.getFilesDir()
            java.lang.String r3 = "jpush_uncaughtexception_file"
            r1.<init>(r2, r3)
            boolean r1 = r1.exists()
            if (r1 != 0) goto L_0x0013
        L_0x0012:
            return r0
        L_0x0013:
            java.lang.String r1 = "jpush_uncaughtexception_file"
            java.io.FileInputStream r2 = r7.openFileInput(r1)     // Catch:{ Exception -> 0x0071, all -> 0x0061 }
            r1 = 1024(0x400, float:1.435E-42)
            byte[] r1 = new byte[r1]     // Catch:{ Exception -> 0x0033 }
            java.lang.StringBuffer r3 = new java.lang.StringBuffer     // Catch:{ Exception -> 0x0033 }
            r3.<init>()     // Catch:{ Exception -> 0x0033 }
        L_0x0022:
            int r4 = r2.read(r1)     // Catch:{ Exception -> 0x0033 }
            r5 = -1
            if (r4 == r5) goto L_0x0042
            java.lang.String r5 = new java.lang.String     // Catch:{ Exception -> 0x0033 }
            r6 = 0
            r5.<init>(r1, r6, r4)     // Catch:{ Exception -> 0x0033 }
            r3.append(r5)     // Catch:{ Exception -> 0x0033 }
            goto L_0x0022
        L_0x0033:
            r1 = move-exception
        L_0x0034:
            r1.printStackTrace()     // Catch:{ all -> 0x006f }
            if (r2 == 0) goto L_0x0012
            r2.close()     // Catch:{ IOException -> 0x003d }
            goto L_0x0012
        L_0x003d:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x0012
        L_0x0042:
            java.lang.String r1 = r3.toString()     // Catch:{ Exception -> 0x0033 }
            int r1 = r1.length()     // Catch:{ Exception -> 0x0033 }
            if (r1 <= 0) goto L_0x0056
            org.json.JSONArray r1 = new org.json.JSONArray     // Catch:{ Exception -> 0x0033 }
            java.lang.String r3 = r3.toString()     // Catch:{ Exception -> 0x0033 }
            r1.<init>(r3)     // Catch:{ Exception -> 0x0033 }
            r0 = r1
        L_0x0056:
            if (r2 == 0) goto L_0x0012
            r2.close()     // Catch:{ IOException -> 0x005c }
            goto L_0x0012
        L_0x005c:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x0012
        L_0x0061:
            r1 = move-exception
            r2 = r0
            r0 = r1
        L_0x0064:
            if (r2 == 0) goto L_0x0069
            r2.close()     // Catch:{ IOException -> 0x006a }
        L_0x0069:
            throw r0
        L_0x006a:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x0069
        L_0x006f:
            r0 = move-exception
            goto L_0x0064
        L_0x0071:
            r1 = move-exception
            r2 = r0
            goto L_0x0034
        */
        throw new UnsupportedOperationException("Method not decompiled: p004cn.jiguang.p005a.p006a.p009c.C0358e.m117f(android.content.Context):org.json.JSONArray");
    }

    /* renamed from: a */
    public final void mo6234a(Context context) {
        this.f119e = context;
        if (this.f118d == null) {
            this.f118d = Thread.getDefaultUncaughtExceptionHandler();
        }
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /* renamed from: b */
    public final void mo6235b(Context context) {
        this.f119e = context;
        if (!this.f117a) {
            this.f117a = true;
            C0389d.m316a(context, true);
        }
    }

    /* renamed from: c */
    public final void mo6236c(Context context) {
        this.f119e = context;
        if (this.f117a) {
            this.f117a = false;
            C0389d.m316a(context, false);
        }
    }

    public final void uncaughtException(Thread thread, Throwable th) {
        if (this.f117a) {
            Context context = this.f119e;
            JSONArray a = m111a(context, m117f(context), th, "");
            m115d(this.f119e);
            m114a(this.f119e, a);
            C0359f fVar = new C0359f(this);
            fVar.start();
            try {
                fVar.join(2000);
            } catch (InterruptedException e) {
            }
        }
        if (this.f118d != this) {
            this.f118d.uncaughtException(thread, th);
        }
        throw new RuntimeException(th);
    }
}
