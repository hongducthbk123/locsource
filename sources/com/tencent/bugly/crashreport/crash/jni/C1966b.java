package com.tencent.bugly.crashreport.crash.jni;

import android.content.Context;
import com.facebook.internal.ServerProtocol;
import com.tencent.bugly.crashreport.common.info.C1938a;
import com.tencent.bugly.crashreport.crash.CrashDetailBean;
import com.tencent.bugly.proguard.C1985b;
import com.tencent.bugly.proguard.C1994j;
import com.tencent.bugly.proguard.C2014w;
import com.tencent.bugly.proguard.C2018y;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.p052io.IOUtils;
import p004cn.jiguang.net.HttpUtils;

/* renamed from: com.tencent.bugly.crashreport.crash.jni.b */
/* compiled from: BUGLY */
public class C1966b {

    /* renamed from: a */
    private StringBuilder f1445a;

    /* renamed from: b */
    private int f1446b = 0;

    /* renamed from: d */
    private void m1866d(String str) {
        for (int i = 0; i < this.f1446b; i++) {
            this.f1445a.append(9);
        }
        if (str != null) {
            this.f1445a.append(str).append(": ");
        }
    }

    public C1966b(StringBuilder sb, int i) {
        this.f1445a = sb;
        this.f1446b = i;
    }

    /* renamed from: a */
    public C1966b mo19531a(boolean z, String str) {
        m1866d(str);
        this.f1445a.append(z ? 'T' : 'F').append(10);
        return this;
    }

    /* renamed from: c */
    private static Map<String, Integer> m1864c(String str) {
        String[] split;
        if (str == null) {
            return null;
        }
        try {
            HashMap hashMap = new HashMap();
            for (String str2 : str.split(",")) {
                String[] split2 = str2.split(":");
                if (split2.length != 2) {
                    C2014w.m2119e("error format at %s", str2);
                    return null;
                }
                hashMap.put(split2[0], Integer.valueOf(Integer.parseInt(split2[1])));
            }
            return hashMap;
        } catch (Exception e) {
            C2014w.m2119e("error format intStateStr %s", str);
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public C1966b mo19520a(byte b, String str) {
        m1866d(str);
        this.f1445a.append(b).append(10);
        return this;
    }

    /* renamed from: a */
    public C1966b mo19521a(char c, String str) {
        m1866d(str);
        this.f1445a.append(c).append(10);
        return this;
    }

    /* renamed from: a */
    public C1966b mo19530a(short s, String str) {
        m1866d(str);
        this.f1445a.append(s).append(10);
        return this;
    }

    /* renamed from: a */
    public C1966b mo19524a(int i, String str) {
        m1866d(str);
        this.f1445a.append(i).append(10);
        return this;
    }

    /* renamed from: a */
    protected static String m1860a(String str) {
        if (str == null) {
            return "";
        }
        String[] split = str.split(IOUtils.LINE_SEPARATOR_UNIX);
        if (split == null || split.length == 0) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        for (String str2 : split) {
            if (!str2.contains("java.lang.Thread.getStackTrace(")) {
                sb.append(str2).append(IOUtils.LINE_SEPARATOR_UNIX);
            }
        }
        return sb.toString();
    }

    /* renamed from: a */
    public C1966b mo19525a(long j, String str) {
        m1866d(str);
        this.f1445a.append(j).append(10);
        return this;
    }

    /* renamed from: a */
    public C1966b mo19523a(float f, String str) {
        m1866d(str);
        this.f1445a.append(f).append(10);
        return this;
    }

    /* renamed from: a */
    public C1966b mo19522a(double d, String str) {
        m1866d(str);
        this.f1445a.append(d).append(10);
        return this;
    }

    /* renamed from: a */
    private static CrashDetailBean m1858a(Context context, Map<String, String> map, NativeExceptionHandler nativeExceptionHandler) {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        if (map == null) {
            return null;
        }
        if (C1938a.m1667a(context) == null) {
            C2014w.m2119e("abnormal com info not created", new Object[0]);
            return null;
        }
        String str8 = (String) map.get("intStateStr");
        if (str8 == null || str8.trim().length() <= 0) {
            C2014w.m2119e("no intStateStr", new Object[0]);
            return null;
        }
        Map c = m1864c(str8);
        if (c == null) {
            C2014w.m2119e("parse intSateMap fail", Integer.valueOf(map.size()));
            return null;
        }
        try {
            ((Integer) c.get("sino")).intValue();
            ((Integer) c.get("sud")).intValue();
            String str9 = (String) map.get("soVersion");
            if (str9 == null) {
                C2014w.m2119e("error format at version", new Object[0]);
                return null;
            }
            String str10 = (String) map.get("errorAddr");
            String str11 = str10 == null ? "unknown" : str10;
            String str12 = (String) map.get("codeMsg");
            if (str12 == null) {
                str = "unknown";
            } else {
                str = str12;
            }
            String str13 = (String) map.get("tombPath");
            if (str13 == null) {
                str2 = "unknown";
            } else {
                str2 = str13;
            }
            String str14 = (String) map.get("signalName");
            if (str14 == null) {
                str3 = "unknown";
            } else {
                str3 = str14;
            }
            map.get("errnoMsg");
            String str15 = (String) map.get("stack");
            if (str15 == null) {
                str4 = "unknown";
            } else {
                str4 = str15;
            }
            String str16 = (String) map.get("jstack");
            if (str16 != null) {
                str4 = str4 + "java:\n" + str16;
            }
            Integer num = (Integer) c.get("sico");
            if (num != null && num.intValue() > 0) {
                str3 = str3 + "(" + str + ")";
                str = "KERNEL";
            }
            String str17 = (String) map.get("nativeLog");
            byte[] bArr = null;
            if (str17 != null && !str17.isEmpty()) {
                bArr = C2018y.m2162a((File) null, str17);
            }
            String str18 = (String) map.get("sendingProcess");
            if (str18 == null) {
                str5 = "unknown";
            } else {
                str5 = str18;
            }
            Integer num2 = (Integer) c.get("spd");
            if (num2 != null) {
                str5 = str5 + "(" + num2 + ")";
            }
            String str19 = (String) map.get("threadName");
            if (str19 == null) {
                str6 = "unknown";
            } else {
                str6 = str19;
            }
            Integer num3 = (Integer) c.get("et");
            if (num3 != null) {
                str6 = str6 + "(" + num3 + ")";
            }
            String str20 = (String) map.get("processName");
            if (str20 == null) {
                str7 = "unknown";
            } else {
                str7 = str20;
            }
            Integer num4 = (Integer) c.get("ep");
            if (num4 != null) {
                str7 = str7 + "(" + num4 + ")";
            }
            HashMap hashMap = null;
            String str21 = (String) map.get("key-value");
            if (str21 != null) {
                hashMap = new HashMap();
                for (String split : str21.split(IOUtils.LINE_SEPARATOR_UNIX)) {
                    String[] split2 = split.split(HttpUtils.EQUAL_SIGN);
                    if (split2.length == 2) {
                        hashMap.put(split2[0], split2[1]);
                    }
                }
            }
            CrashDetailBean packageCrashDatas = nativeExceptionHandler.packageCrashDatas(str7, str6, (((long) ((Integer) c.get("etms")).intValue()) / 1000) + (((long) ((Integer) c.get("ets")).intValue()) * 1000), str3, str11, m1860a(str4), str, str5, str2, str9, bArr, hashMap, false);
            if (packageCrashDatas != null) {
                String str22 = (String) map.get("userId");
                if (str22 != null) {
                    C2014w.m2117c("[Native record info] userId: %s", str22);
                    packageCrashDatas.f1299m = str22;
                }
                String str23 = (String) map.get("sysLog");
                if (str23 != null) {
                    packageCrashDatas.f1309w = str23;
                }
                String str24 = (String) map.get("appVersion");
                if (str24 != null) {
                    C2014w.m2117c("[Native record info] appVersion: %s", str24);
                    packageCrashDatas.f1292f = str24;
                }
                String str25 = (String) map.get("isAppForeground");
                if (str25 != null) {
                    C2014w.m2117c("[Native record info] isAppForeground: %s", str25);
                    packageCrashDatas.f1277M = str25.equalsIgnoreCase(ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
                }
                String str26 = (String) map.get("launchTime");
                if (str26 != null) {
                    C2014w.m2117c("[Native record info] launchTime: %s", str26);
                    packageCrashDatas.f1276L = Long.parseLong(str26);
                }
                packageCrashDatas.f1311y = null;
                packageCrashDatas.f1297k = true;
            }
            return packageCrashDatas;
        } catch (NumberFormatException e) {
            if (!C2014w.m2114a(e)) {
                e.printStackTrace();
            }
        } catch (Throwable th) {
            C2014w.m2119e("error format", new Object[0]);
            th.printStackTrace();
            return null;
        }
    }

    /* renamed from: b */
    public C1966b mo19539b(String str, String str2) {
        m1866d(str2);
        if (str == null) {
            this.f1445a.append("null\n");
        } else {
            this.f1445a.append(str).append(10);
        }
        return this;
    }

    /* renamed from: a */
    public C1966b mo19532a(byte[] bArr, String str) {
        m1866d(str);
        if (bArr == null) {
            this.f1445a.append("null\n");
        } else if (bArr.length == 0) {
            this.f1445a.append(bArr.length).append(", []\n");
        } else {
            this.f1445a.append(bArr.length).append(", [\n");
            C1966b bVar = new C1966b(this.f1445a, this.f1446b + 1);
            for (byte a : bArr) {
                bVar.mo19520a(a, (String) null);
            }
            mo19521a(']', (String) null);
        }
        return this;
    }

    /* renamed from: a */
    public C1966b mo19538a(short[] sArr, String str) {
        m1866d(str);
        if (sArr == null) {
            this.f1445a.append("null\n");
        } else if (sArr.length == 0) {
            this.f1445a.append(sArr.length).append(", []\n");
        } else {
            this.f1445a.append(sArr.length).append(", [\n");
            C1966b bVar = new C1966b(this.f1445a, this.f1446b + 1);
            for (short a : sArr) {
                bVar.mo19530a(a, (String) null);
            }
            mo19521a(']', (String) null);
        }
        return this;
    }

    /* renamed from: a */
    public C1966b mo19535a(int[] iArr, String str) {
        m1866d(str);
        if (iArr == null) {
            this.f1445a.append("null\n");
        } else if (iArr.length == 0) {
            this.f1445a.append(iArr.length).append(", []\n");
        } else {
            this.f1445a.append(iArr.length).append(", [\n");
            C1966b bVar = new C1966b(this.f1445a, this.f1446b + 1);
            for (int a : iArr) {
                bVar.mo19524a(a, (String) null);
            }
            mo19521a(']', (String) null);
        }
        return this;
    }

    /* renamed from: a */
    public C1966b mo19536a(long[] jArr, String str) {
        m1866d(str);
        if (jArr == null) {
            this.f1445a.append("null\n");
        } else if (jArr.length == 0) {
            this.f1445a.append(jArr.length).append(", []\n");
        } else {
            this.f1445a.append(jArr.length).append(", [\n");
            C1966b bVar = new C1966b(this.f1445a, this.f1446b + 1);
            for (long a : jArr) {
                bVar.mo19525a(a, (String) null);
            }
            mo19521a(']', (String) null);
        }
        return this;
    }

    /* renamed from: a */
    public C1966b mo19534a(float[] fArr, String str) {
        m1866d(str);
        if (fArr == null) {
            this.f1445a.append("null\n");
        } else if (fArr.length == 0) {
            this.f1445a.append(fArr.length).append(", []\n");
        } else {
            this.f1445a.append(fArr.length).append(", [\n");
            C1966b bVar = new C1966b(this.f1445a, this.f1446b + 1);
            for (float a : fArr) {
                bVar.mo19523a(a, (String) null);
            }
            mo19521a(']', (String) null);
        }
        return this;
    }

    /* renamed from: a */
    public C1966b mo19533a(double[] dArr, String str) {
        m1866d(str);
        if (dArr == null) {
            this.f1445a.append("null\n");
        } else if (dArr.length == 0) {
            this.f1445a.append(dArr.length).append(", []\n");
        } else {
            this.f1445a.append(dArr.length).append(", [\n");
            C1966b bVar = new C1966b(this.f1445a, this.f1446b + 1);
            for (double a : dArr) {
                bVar.mo19522a(a, (String) null);
            }
            mo19521a(']', (String) null);
        }
        return this;
    }

    /* renamed from: a */
    public <K, V> C1966b mo19529a(Map<K, V> map, String str) {
        m1866d(str);
        if (map == null) {
            this.f1445a.append("null\n");
        } else if (map.isEmpty()) {
            this.f1445a.append(map.size()).append(", {}\n");
        } else {
            this.f1445a.append(map.size()).append(", {\n");
            C1966b bVar = new C1966b(this.f1445a, this.f1446b + 1);
            C1966b bVar2 = new C1966b(this.f1445a, this.f1446b + 2);
            for (Entry entry : map.entrySet()) {
                bVar.mo19521a('(', (String) null);
                bVar2.mo19527a((T) entry.getKey(), (String) null);
                bVar2.mo19527a((T) entry.getValue(), (String) null);
                bVar.mo19521a(')', (String) null);
            }
            mo19521a('}', (String) null);
        }
        return this;
    }

    /* renamed from: a */
    private static String m1859a(BufferedInputStream bufferedInputStream) throws IOException {
        if (bufferedInputStream == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        while (true) {
            int read = bufferedInputStream.read();
            if (read == -1) {
                return null;
            }
            if (read == 0) {
                return sb.toString();
            }
            sb.append((char) read);
        }
    }

    /* renamed from: a */
    public <T> C1966b mo19537a(T[] tArr, String str) {
        m1866d(str);
        if (tArr == null) {
            this.f1445a.append("null\n");
        } else if (tArr.length == 0) {
            this.f1445a.append(tArr.length).append(", []\n");
        } else {
            this.f1445a.append(tArr.length).append(", [\n");
            C1966b bVar = new C1966b(this.f1445a, this.f1446b + 1);
            for (T a : tArr) {
                bVar.mo19527a(a, (String) null);
            }
            mo19521a(']', (String) null);
        }
        return this;
    }

    /* renamed from: a */
    public <T> C1966b mo19528a(Collection<T> collection, String str) {
        if (collection != null) {
            return mo19537a((T[]) collection.toArray(), str);
        }
        m1866d(str);
        this.f1445a.append("null\t");
        return this;
    }

    /* JADX WARNING: type inference failed for: r0v0 */
    /* JADX WARNING: type inference failed for: r0v1, types: [com.tencent.bugly.crashreport.crash.CrashDetailBean] */
    /* JADX WARNING: type inference failed for: r2v5, types: [java.io.BufferedInputStream] */
    /* JADX WARNING: type inference failed for: r2v6 */
    /* JADX WARNING: type inference failed for: r2v7, types: [java.io.BufferedInputStream] */
    /* JADX WARNING: type inference failed for: r2v8 */
    /* JADX WARNING: type inference failed for: r2v9, types: [java.io.BufferedInputStream] */
    /* JADX WARNING: type inference failed for: r1v10 */
    /* JADX WARNING: type inference failed for: r1v11, types: [java.lang.Object] */
    /* JADX WARNING: type inference failed for: r3v4, types: [java.lang.Object, java.lang.String] */
    /* JADX WARNING: type inference failed for: r0v7, types: [com.tencent.bugly.crashreport.crash.CrashDetailBean] */
    /* JADX WARNING: type inference failed for: r4v4, types: [java.lang.Object[]] */
    /* JADX WARNING: type inference failed for: r1v14 */
    /* JADX WARNING: type inference failed for: r1v15 */
    /* JADX WARNING: type inference failed for: r1v16 */
    /* JADX WARNING: type inference failed for: r0v8 */
    /* JADX WARNING: type inference failed for: r2v10 */
    /* JADX WARNING: type inference failed for: r2v11 */
    /* JADX WARNING: type inference failed for: r0v9 */
    /* JADX WARNING: type inference failed for: r0v10 */
    /* JADX WARNING: type inference failed for: r1v17 */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r1v14
      assigns: []
      uses: []
      mth insns count: 73
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
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x009e A[SYNTHETIC, Splitter:B:53:0x009e] */
    /* JADX WARNING: Unknown variable types count: 9 */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.tencent.bugly.crashreport.crash.CrashDetailBean m1857a(android.content.Context r6, java.lang.String r7, com.tencent.bugly.crashreport.crash.jni.NativeExceptionHandler r8) {
        /*
            r2 = 0
            r0 = 0
            if (r6 == 0) goto L_0x0008
            if (r7 == 0) goto L_0x0008
            if (r8 != 0) goto L_0x0010
        L_0x0008:
            java.lang.String r1 = "get eup record file args error"
            java.lang.Object[] r2 = new java.lang.Object[r2]
            com.tencent.bugly.proguard.C2014w.m2119e(r1, r2)
        L_0x000f:
            return r0
        L_0x0010:
            java.io.File r1 = new java.io.File
            java.lang.String r2 = "rqd_record.eup"
            r1.<init>(r7, r2)
            boolean r2 = r1.exists()
            if (r2 == 0) goto L_0x000f
            boolean r2 = r1.canRead()
            if (r2 == 0) goto L_0x000f
            java.io.BufferedInputStream r2 = new java.io.BufferedInputStream     // Catch:{ IOException -> 0x0087, all -> 0x0099 }
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch:{ IOException -> 0x0087, all -> 0x0099 }
            r3.<init>(r1)     // Catch:{ IOException -> 0x0087, all -> 0x0099 }
            r2.<init>(r3)     // Catch:{ IOException -> 0x0087, all -> 0x0099 }
            java.lang.String r1 = m1859a(r2)     // Catch:{ IOException -> 0x00a9 }
            if (r1 == 0) goto L_0x003b
            java.lang.String r3 = "NATIVE_RQD_REPORT"
            boolean r3 = r1.equals(r3)     // Catch:{ IOException -> 0x00a9 }
            if (r3 != 0) goto L_0x004f
        L_0x003b:
            java.lang.String r3 = "record read fail! %s"
            r4 = 1
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch:{ IOException -> 0x00a9 }
            r5 = 0
            r4[r5] = r1     // Catch:{ IOException -> 0x00a9 }
            com.tencent.bugly.proguard.C2014w.m2119e(r3, r4)     // Catch:{ IOException -> 0x00a9 }
            r2.close()     // Catch:{ IOException -> 0x004a }
            goto L_0x000f
        L_0x004a:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x000f
        L_0x004f:
            java.util.HashMap r4 = new java.util.HashMap     // Catch:{ IOException -> 0x00a9 }
            r4.<init>()     // Catch:{ IOException -> 0x00a9 }
            r1 = r0
        L_0x0055:
            java.lang.String r3 = m1859a(r2)     // Catch:{ IOException -> 0x00a9 }
            if (r3 == 0) goto L_0x0064
            if (r1 != 0) goto L_0x005f
            r1 = r3
            goto L_0x0055
        L_0x005f:
            r4.put(r1, r3)     // Catch:{ IOException -> 0x00a9 }
            r1 = r0
            goto L_0x0055
        L_0x0064:
            if (r1 == 0) goto L_0x007a
            java.lang.String r3 = "record not pair! drop! %s"
            r4 = 1
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch:{ IOException -> 0x00a9 }
            r5 = 0
            r4[r5] = r1     // Catch:{ IOException -> 0x00a9 }
            com.tencent.bugly.proguard.C2014w.m2119e(r3, r4)     // Catch:{ IOException -> 0x00a9 }
            r2.close()     // Catch:{ IOException -> 0x0075 }
            goto L_0x000f
        L_0x0075:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x000f
        L_0x007a:
            com.tencent.bugly.crashreport.crash.CrashDetailBean r0 = m1858a(r6, r4, r8)     // Catch:{ IOException -> 0x00a9 }
            r2.close()     // Catch:{ IOException -> 0x0082 }
            goto L_0x000f
        L_0x0082:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x000f
        L_0x0087:
            r1 = move-exception
            r2 = r0
        L_0x0089:
            r1.printStackTrace()     // Catch:{ all -> 0x00a7 }
            if (r2 == 0) goto L_0x000f
            r2.close()     // Catch:{ IOException -> 0x0093 }
            goto L_0x000f
        L_0x0093:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x000f
        L_0x0099:
            r1 = move-exception
            r2 = r0
            r0 = r1
        L_0x009c:
            if (r2 == 0) goto L_0x00a1
            r2.close()     // Catch:{ IOException -> 0x00a2 }
        L_0x00a1:
            throw r0
        L_0x00a2:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x00a1
        L_0x00a7:
            r0 = move-exception
            goto L_0x009c
        L_0x00a9:
            r1 = move-exception
            goto L_0x0089
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.crash.jni.C1966b.m1857a(android.content.Context, java.lang.String, com.tencent.bugly.crashreport.crash.jni.NativeExceptionHandler):com.tencent.bugly.crashreport.crash.CrashDetailBean");
    }

    /* renamed from: a */
    public <T> C1966b mo19527a(T t, String str) {
        if (t == null) {
            this.f1445a.append("null\n");
        } else if (t instanceof Byte) {
            mo19520a(((Byte) t).byteValue(), str);
        } else if (t instanceof Boolean) {
            mo19531a(((Boolean) t).booleanValue(), str);
        } else if (t instanceof Short) {
            mo19530a(((Short) t).shortValue(), str);
        } else if (t instanceof Integer) {
            mo19524a(((Integer) t).intValue(), str);
        } else if (t instanceof Long) {
            mo19525a(((Long) t).longValue(), str);
        } else if (t instanceof Float) {
            mo19523a(((Float) t).floatValue(), str);
        } else if (t instanceof Double) {
            mo19522a(((Double) t).doubleValue(), str);
        } else if (t instanceof String) {
            mo19539b((String) t, str);
        } else if (t instanceof Map) {
            mo19529a((Map) t, str);
        } else if (t instanceof List) {
            mo19528a((Collection<T>) (List) t, str);
        } else if (t instanceof C1994j) {
            mo19526a((C1994j) t, str);
        } else if (t instanceof byte[]) {
            mo19532a((byte[]) t, str);
        } else if (t instanceof boolean[]) {
            mo19527a((T) (boolean[]) t, str);
        } else if (t instanceof short[]) {
            mo19538a((short[]) t, str);
        } else if (t instanceof int[]) {
            mo19535a((int[]) t, str);
        } else if (t instanceof long[]) {
            mo19536a((long[]) t, str);
        } else if (t instanceof float[]) {
            mo19534a((float[]) t, str);
        } else if (t instanceof double[]) {
            mo19533a((double[]) t, str);
        } else if (t.getClass().isArray()) {
            mo19537a((T[]) (Object[]) t, str);
        } else {
            throw new C1985b("write object error: unsupport type.");
        }
        return this;
    }

    /* renamed from: c */
    private static String m1863c(String str, String str2) {
        String str3 = null;
        String a = C2018y.m2140a(str, "reg_record.txt");
        if (a != null) {
            try {
                StringBuilder sb = new StringBuilder();
                a = a.readLine();
                if (a != null && a.startsWith(str2)) {
                    String str4 = "                ";
                    int i = 0;
                    int i2 = 18;
                    int i3 = 0;
                    while (true) {
                        String readLine = a.readLine();
                        if (readLine == null) {
                            break;
                        }
                        if (i3 % 4 == 0) {
                            if (i3 > 0) {
                                sb.append(IOUtils.LINE_SEPARATOR_UNIX);
                            }
                            sb.append("  ");
                        } else {
                            if (readLine.length() > 16) {
                                i2 = 28;
                            }
                            sb.append(str4.substring(0, i2 - i));
                        }
                        i = readLine.length();
                        sb.append(readLine);
                        i3++;
                    }
                    sb.append(IOUtils.LINE_SEPARATOR_UNIX);
                    str3 = sb.toString();
                    if (a != null) {
                        try {
                            a.close();
                        } catch (Exception e) {
                            C2014w.m2114a(e);
                        }
                    }
                } else if (a != null) {
                    try {
                        a.close();
                    } catch (Exception e2) {
                        C2014w.m2114a(e2);
                    }
                }
            } catch (Throwable th) {
                C2014w.m2114a(th);
                if (a != null) {
                    try {
                        a.close();
                    } catch (Exception e3) {
                        C2014w.m2114a(e3);
                    }
                }
            } finally {
                if (a != null) {
                    try {
                        a.close();
                    } catch (Exception e4) {
                        C2014w.m2114a(e4);
                    }
                }
            }
        }
        return str3;
    }

    /* renamed from: a */
    public C1966b mo19526a(C1994j jVar, String str) {
        mo19521a('{', str);
        if (jVar == null) {
            this.f1445a.append(9).append("null");
        } else {
            jVar.mo19551a(this.f1445a, this.f1446b + 1);
        }
        mo19521a('}', (String) null);
        return this;
    }

    /* renamed from: d */
    private static String m1865d(String str, String str2) {
        String str3 = null;
        String a = C2018y.m2140a(str, "map_record.txt");
        if (a != null) {
            try {
                StringBuilder sb = new StringBuilder();
                a = a.readLine();
                if (a != null && a.startsWith(str2)) {
                    while (true) {
                        String readLine = a.readLine();
                        if (readLine == null) {
                            break;
                        }
                        sb.append("  ");
                        sb.append(readLine);
                        sb.append(IOUtils.LINE_SEPARATOR_UNIX);
                    }
                    str3 = sb.toString();
                    if (a != null) {
                        try {
                            a.close();
                        } catch (Exception e) {
                            C2014w.m2114a(e);
                        }
                    }
                } else if (a != null) {
                    try {
                        a.close();
                    } catch (Exception e2) {
                        C2014w.m2114a(e2);
                    }
                }
            } catch (Throwable th) {
                C2014w.m2114a(th);
                if (a != null) {
                    try {
                        a.close();
                    } catch (Exception e3) {
                        C2014w.m2114a(e3);
                    }
                }
            } finally {
                if (a != null) {
                    try {
                        a.close();
                    } catch (Exception e4) {
                        C2014w.m2114a(e4);
                    }
                }
            }
        }
        return str3;
    }

    /* renamed from: a */
    public static String m1861a(String str, String str2) {
        if (str == null || str2 == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        String c = m1863c(str, str2);
        if (c != null && !c.isEmpty()) {
            sb.append("Register infos:\n");
            sb.append(c);
        }
        String d = m1865d(str, str2);
        if (d != null && !d.isEmpty()) {
            if (sb.length() > 0) {
                sb.append(IOUtils.LINE_SEPARATOR_UNIX);
            }
            sb.append("System SO infos:\n");
            sb.append(d);
        }
        return sb.toString();
    }

    /* renamed from: b */
    public static void m1862b(String str) {
        File file = new File(str, "rqd_record.eup");
        if (file.exists() && file.canWrite()) {
            file.delete();
            C2014w.m2117c("delete record file %s", file.getAbsoluteFile());
        }
        File file2 = new File(str, "reg_record.txt");
        if (file2.exists() && file2.canWrite()) {
            file2.delete();
            C2014w.m2117c("delete record file %s", file2.getAbsoluteFile());
        }
        File file3 = new File(str, "map_record.txt");
        if (file3.exists() && file3.canWrite()) {
            file3.delete();
            C2014w.m2117c("delete record file %s", file3.getAbsoluteFile());
        }
        File file4 = new File(str, "backup_record.txt");
        if (file4.exists() && file4.canWrite()) {
            file4.delete();
            C2014w.m2117c("delete record file %s", file4.getAbsoluteFile());
        }
    }
}
