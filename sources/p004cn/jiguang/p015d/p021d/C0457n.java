package p004cn.jiguang.p015d.p021d;

import android.content.Context;
import android.text.TextUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.DiscardPolicy;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;
import p004cn.jiguang.p015d.p017b.C0395a;
import p004cn.jiguang.p015d.p026g.C0484e;
import p004cn.jiguang.p015d.p026g.C0487h;
import p004cn.jiguang.p031g.C0525f;

/* renamed from: cn.jiguang.d.d.n */
public final class C0457n {

    /* renamed from: a */
    private static String f431a = null;

    /* renamed from: b */
    private static ConcurrentHashMap<File, Boolean> f432b = new ConcurrentHashMap<>();

    /* renamed from: c */
    private static ExecutorService f433c = new ThreadPoolExecutor(1, 1, 0, TimeUnit.SECONDS, new LinkedBlockingDeque(1), new DiscardPolicy());

    /* renamed from: a */
    public static File m691a(Context context, String str, JSONObject jSONObject) {
        File file = new File(m700e(context) + str + File.separator + UUID.randomUUID().toString());
        f432b.put(file, Boolean.valueOf(true));
        C0484e.m801a(file, jSONObject.toString());
        return file;
    }

    /* renamed from: a */
    private static List<C0455l> m692a(String str, Set<String> set, JSONObject jSONObject) {
        File[] a = C0484e.m803a(str, false);
        if (a == null || a.length == 0) {
            return null;
        }
        boolean z = !C0487h.m816b(jSONObject);
        ArrayList arrayList = new ArrayList();
        for (File file : a) {
            Boolean bool = (Boolean) f432b.get(file);
            if (bool == null || !bool.booleanValue()) {
                C0455l a2 = C0456m.m687a(file, set);
                if (a2 == null) {
                    C0484e.m800a(file);
                } else if (!C0487h.m816b(a2.mo6580e())) {
                    arrayList.add(a2);
                } else if (z) {
                    a2.mo6576b(jSONObject);
                    a2.mo6577b(true);
                    a2.mo6574a(true);
                    arrayList.add(a2);
                }
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    public static void m693a(Context context) {
        File[] a;
        for (File file : C0484e.m802a(context.getFilesDir(), true)) {
            if (file.getName().startsWith("jpush_stat_history")) {
                C0525f.m1085a(file.getAbsolutePath());
            }
        }
    }

    /* renamed from: a */
    static /* synthetic */ void m694a(Context context, File file) {
        HashSet hashSet = new HashSet();
        hashSet.add("uid");
        hashSet.add("app_key");
        hashSet.add("sdk_ver");
        hashSet.add("core_sdk_ver");
        hashSet.add("share_sdk_ver");
        hashSet.add("statistics_sdk_ver");
        hashSet.add("channel");
        hashSet.add("app_version");
        JSONObject d = C0460q.m722d(context);
        String absolutePath = file.getAbsolutePath();
        String str = absolutePath + File.separator + "tmp";
        ArrayList arrayList = new ArrayList();
        List a = m692a(str, (Set<String>) hashSet, d);
        if ((a != null ? a.size() : 0) > 0) {
            arrayList.addAll(a);
        }
        if (!C0487h.m816b(d)) {
            List a2 = m692a(absolutePath + File.separator + "nowrap", null, d);
            if ((a2 != null ? a2.size() : 0) > 0) {
                arrayList.addAll(a2);
            }
        }
        File[] a3 = C0484e.m802a(file, false);
        File a4 = C0484e.m799a(a3);
        ArrayList<C0455l> arrayList2 = new ArrayList<>();
        if ((a3 != null ? a3.length : 0) > 0) {
            for (File file2 : a3) {
                if (!file2.equals(a4)) {
                    arrayList2.add(C0456m.m686a(file2));
                }
            }
        }
        C0455l a5 = C0456m.m687a(a4, (Set<String>) hashSet);
        if (a5 != null) {
            arrayList.add(0, a5);
        }
        if (arrayList.size() >= 2) {
            C0456m.m689a((List<C0455l>) arrayList, 40960);
        }
        arrayList2.addAll(C0456m.m688a((List<C0455l>) arrayList, file));
        m696a((List<C0455l>) arrayList2, C0460q.m721d(absolutePath));
        for (C0455l lVar : arrayList2) {
            if (C0460q.m701a(context, lVar.mo6579d(), lVar.mo6578c()) == -2) {
                return;
            }
        }
    }

    /* renamed from: a */
    public static void m695a(File file) {
        f432b.remove(file);
    }

    /* renamed from: a */
    private static void m696a(List<C0455l> list, long j) {
        long j2 = 0;
        for (C0455l a : list) {
            j2 = a.mo6571a() + j2;
        }
        long j3 = j2 - j;
        while (j3 > 0) {
            C0455l lVar = (C0455l) Collections.min(list, new C0459p());
            j3 -= lVar.mo6571a();
            list.remove(lVar);
            C0484e.m800a(lVar.mo6578c());
        }
    }

    /* renamed from: b */
    public static void m697b(Context context) {
        f433c.submit(new C0458o(context));
    }

    /* renamed from: d */
    private static synchronized void m699d(Context context) {
        synchronized (C0457n.class) {
            if (f431a == null) {
                String c = C0395a.m387c(context);
                if (TextUtils.isEmpty(c)) {
                    f431a = null;
                } else if (c.equals(context.getPackageName())) {
                    f431a = "";
                } else {
                    f431a = c.replaceFirst(context.getPackageName() + ":", "_");
                }
            }
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: e */
    public static String m700e(Context context) {
        m699d(context);
        return context.getFilesDir().getPath() + File.separator + "jpush_stat_history" + (f431a != null ? f431a : "");
    }
}
