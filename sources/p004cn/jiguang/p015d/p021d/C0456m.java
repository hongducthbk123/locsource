package p004cn.jiguang.p015d.p021d;

import android.text.TextUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p004cn.jiguang.p015d.p026g.C0484e;
import p004cn.jiguang.p015d.p026g.C0487h;

/* renamed from: cn.jiguang.d.d.m */
public final class C0456m {
    /* renamed from: a */
    public static C0455l m686a(File file) {
        if (file == null || !file.exists() || file.isDirectory()) {
            return null;
        }
        String b = C0484e.m804b(file);
        if (TextUtils.isEmpty(b)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(b);
            jSONObject.getJSONArray("content");
            return new C0455l(file, jSONObject);
        } catch (JSONException e) {
            return null;
        }
    }

    /* renamed from: a */
    public static C0455l m687a(File file, Set<String> set) {
        C0455l a = m686a(file);
        if (a != null) {
            a.mo6576b(C0487h.m814a(a.mo6579d(), set));
        }
        return a;
    }

    /* renamed from: a */
    public static List<C0455l> m688a(List<C0455l> list, File file) {
        if (list.isEmpty()) {
            return list;
        }
        ArrayList arrayList = new ArrayList();
        for (C0455l lVar : list) {
            File c = lVar.mo6578c();
            if (C0487h.m816b(lVar.mo6579d())) {
                C0484e.m800a(c);
            } else {
                if (lVar.mo6582g()) {
                    C0487h.m815a(lVar.mo6579d(), lVar.mo6580e());
                    lVar.mo6577b(false);
                }
                if (lVar.mo6581f()) {
                    C0484e.m801a(lVar.mo6578c(), lVar.mo6579d().toString());
                    lVar.mo6575b();
                }
                if (!file.equals(c.getParentFile())) {
                    lVar.mo6578c().getAbsolutePath();
                    File file2 = new File(file.getAbsolutePath() + File.separator + c.getName());
                    lVar.mo6578c().renameTo(file2);
                    lVar.mo6572a(file2);
                }
                lVar.mo6574a(false);
                arrayList.add(lVar);
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    public static void m689a(List<C0455l> list, int i) {
        if (!list.isEmpty()) {
            HashMap hashMap = new HashMap();
            for (C0455l lVar : list) {
                JSONObject e = lVar.mo6580e();
                if (!C0487h.m816b(e)) {
                    String jSONObject = e.toString();
                    List list2 = (List) hashMap.get(jSONObject);
                    if (list2 == null) {
                        list2 = new ArrayList();
                        hashMap.put(jSONObject, list2);
                    }
                    list2.add(lVar);
                }
            }
            for (List list3 : hashMap.values()) {
                int i2 = 0;
                int i3 = 1;
                while (true) {
                    int i4 = i3;
                    int i5 = i2;
                    if (i4 < list3.size()) {
                        i2 = !m690a((C0455l) list3.get(i5), (C0455l) list3.get(i4), 40960) ? i5 + 1 : i5;
                        i3 = i4 + 1;
                    }
                }
            }
        }
    }

    /* renamed from: a */
    private static boolean m690a(C0455l lVar, C0455l lVar2, int i) {
        boolean z = false;
        if (lVar.mo6571a() + lVar2.mo6571a() <= ((long) i)) {
            try {
                JSONArray jSONArray = lVar.mo6579d().getJSONArray("content");
                JSONArray jSONArray2 = lVar2.mo6579d().getJSONArray("content");
                int i2 = 0;
                while (i2 < jSONArray2.length()) {
                    jSONArray.put(jSONArray2.getJSONObject(i2));
                    i2++;
                    z = true;
                }
            } catch (JSONException e) {
            }
            if (z) {
                lVar.mo6574a(true);
                lVar2.mo6573a((JSONObject) null);
                lVar2.mo6574a(true);
            }
        }
        return z;
    }
}
