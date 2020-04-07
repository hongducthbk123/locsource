package p004cn.jpush.android.p037a;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p004cn.jiguang.api.JCoreInterface;
import p004cn.jpush.android.C0541a;
import p004cn.jpush.android.api.C0559a;
import p004cn.jpush.android.api.JPushInterface.C0558a;
import p004cn.jpush.android.api.TagAliasCallback;
import p004cn.jpush.android.p040d.C0582e;
import p004cn.jpush.android.p040d.C0584g;
import p004cn.jpush.android.service.C0607d;
import p004cn.jpush.android.service.ServiceInterface;
import p004cn.jpush.p036a.C0539c;

/* renamed from: cn.jpush.android.a.k */
public final class C0555k {

    /* renamed from: a */
    private static ConcurrentLinkedQueue<Long> f676a = new ConcurrentLinkedQueue<>();

    /* renamed from: a */
    private static void m1159a(Context context, String str, Set<String> set, C0559a aVar) {
        Context context2;
        long nextRid = JCoreInterface.getNextRid();
        if (aVar != null) {
            C0607d.m1397a().mo7001a(context, Long.valueOf(nextRid), aVar);
        }
        if (ServiceInterface.m1373d(context)) {
            C0607d.m1397a().mo6999a(context, C0558a.f707m, nextRid, aVar);
            return;
        }
        if (!(context instanceof Application)) {
            context2 = context.getApplicationContext();
        } else {
            context2 = context;
        }
        if (!C0541a.m1120a(context2)) {
            C0607d.m1397a().mo6999a(context2, C0558a.f704j, nextRid, aVar);
            return;
        }
        if (aVar.f733e == 0) {
            C0607d.m1397a().mo6998a(context2);
        }
        ServiceInterface.m1367a(context2, str, set, nextRid, aVar);
    }

    /* renamed from: a */
    public static Set<String> m1151a(Set<String> set) {
        if (set == null) {
            return null;
        }
        if (set.isEmpty()) {
            return set;
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        int i = 0;
        Iterator it = set.iterator();
        while (true) {
            int i2 = i;
            if (!it.hasNext()) {
                return linkedHashSet;
            }
            String str = (String) it.next();
            if (TextUtils.isEmpty(str) || !C0584g.m1314a(str)) {
                C0582e.m1308d("TagAliasHelper", "Invalid tag : " + str);
                i = i2;
            } else {
                linkedHashSet.add(str);
                i = i2 + 1;
                if (i >= 1000) {
                    C0582e.m1306c("TagAliasHelper", "The lenght of tags maybe more than 1000.");
                    return linkedHashSet;
                }
            }
        }
    }

    /* renamed from: b */
    public static String m1162b(Set<String> set) {
        String str;
        int i;
        String str2 = null;
        if (set == null) {
            return null;
        }
        if (set.isEmpty()) {
            return "";
        }
        int i2 = 0;
        for (String str3 : set) {
            if (TextUtils.isEmpty(str3) || !C0584g.m1314a(str3)) {
                C0582e.m1308d("TagAliasHelper", "Invalid tag: " + str3);
                i = i2;
            } else {
                if (str == null) {
                    str = str3;
                } else {
                    str = str + "," + str3;
                }
                i = i2 + 1;
                if (i >= 1000) {
                    return str;
                }
            }
            str2 = str;
            i2 = i;
        }
        return str;
    }

    /* renamed from: a */
    public static void m1158a(Context context, String str, Set<String> set, TagAliasCallback tagAliasCallback, int i, int i2) {
        if (context == null) {
            throw new IllegalArgumentException("NULL context");
        }
        m1159a(context, str, set, new C0559a(str, set, tagAliasCallback, System.currentTimeMillis(), 0, 0));
    }

    /* renamed from: a */
    public static void m1154a(Context context, int i, String str, int i2, int i3) {
        if (context == null) {
            throw new IllegalArgumentException("NULL context");
        }
        m1159a(context, str, null, new C0559a(i, str, System.currentTimeMillis(), 2, i3));
    }

    /* renamed from: a */
    public static void m1155a(Context context, int i, Set<String> set, int i2, int i3) {
        if (context == null) {
            throw new IllegalArgumentException("NULL context");
        }
        m1159a(context, (String) null, set, new C0559a(i, set, System.currentTimeMillis(), 1, i3));
    }

    /* renamed from: a */
    public static void m1156a(Context context, Bundle bundle) {
        char c;
        int i;
        String string = bundle.getString("alias");
        ArrayList stringArrayList = bundle.getStringArrayList("tags");
        long j = bundle.getLong("seq_id", 0);
        int i2 = 0;
        int i3 = 0;
        try {
            i2 = Integer.parseInt(bundle.getString("proto_type"));
        } catch (Throwable th) {
            m1153a(context, 0, C0558a.f704j, j);
        }
        try {
            i3 = Integer.parseInt(bundle.getString("protoaction_type"));
        } catch (Throwable th2) {
            m1153a(context, i2, C0558a.f704j, j);
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (f676a.size() < 10) {
            f676a.offer(Long.valueOf(currentTimeMillis));
            c = 0;
        } else {
            long longValue = currentTimeMillis - ((Long) f676a.element()).longValue();
            if (longValue < 0) {
                f676a.clear();
                c = 2;
            } else if (longValue > 10000) {
                while (f676a.size() >= 10) {
                    f676a.poll();
                }
                f676a.offer(Long.valueOf(currentTimeMillis));
                c = 0;
            } else {
                c = 1;
            }
        }
        if (c != 0) {
            if (c == 1) {
                i = C0558a.f706l;
            } else {
                i = C0558a.f708n;
            }
            m1153a(context, i2, i, j);
            return;
        }
        C0556l.m1165a();
        if (C0556l.m1166a(context, i2, j)) {
            m1153a(context, i2, C0558a.f715u, j);
            return;
        }
        C0539c cVar = null;
        if (i2 == 0) {
            cVar = m1150a(context, (List<String>) stringArrayList, string, j);
        } else if (i2 == 1) {
            cVar = m1149a(context, (List<String>) stringArrayList, j, i3, -1);
        } else if (i2 == 2) {
            cVar = m1148a(context, string, j, i3);
        }
        if (cVar != null && (i2 == 1 || i2 == 2)) {
            if (C0556l.m1165a().mo6785a(i2)) {
                C0556l.m1165a().mo6784a(i2, i3, j, stringArrayList, string);
            } else {
                m1153a(context, i2, i2 == 1 ? C0558a.f716v : C0558a.f717w, j);
                return;
            }
        }
        m1157a(context, cVar);
    }

    /* renamed from: a */
    protected static void m1157a(Context context, C0539c cVar) {
        C0582e.m1302a("TagAliasHelper", "tagalias:" + cVar);
        if (cVar != null) {
            C0547e.m1126a(context).mo6777a(cVar, 20000);
        }
    }

    /* renamed from: a */
    protected static void m1153a(Context context, int i, int i2, long j) {
        try {
            Intent intent = new Intent();
            intent.addCategory(context.getPackageName());
            intent.setPackage(context.getPackageName());
            if (i == 0) {
                intent.setAction("cn.jpush.android.intent.TAG_ALIAS_CALLBACK");
            } else {
                intent.setAction("cn.jpush.android.intent.RECEIVE_MESSAGE");
                if (i == 1) {
                    intent.putExtra("message_type", 1);
                } else {
                    intent.putExtra("message_type", 2);
                }
            }
            intent.putExtra("tagalias_errorcode", i2);
            intent.putExtra("tagalias_seqid", j);
            context.sendBroadcast(intent);
        } catch (Throwable th) {
            C0582e.m1306c("TagAliasHelper", "NotifyTagAliasError:" + th.getMessage());
        }
    }

    /* renamed from: a */
    private static boolean m1160a(Context context, int i, String str, long j) {
        int b = C0584g.m1315b(str);
        if (b == 0) {
            return true;
        }
        C0582e.m1302a("TagAliasHelper", "Invalid alias: " + str + ", will not set alias this time.");
        m1153a(context, i, b, j);
        return false;
    }

    /* renamed from: a */
    private static boolean m1161a(Context context, int i, Set<String> set, long j) {
        int a = C0584g.m1313a(set);
        if (a == 0) {
            return true;
        }
        C0582e.m1302a("TagAliasHelper", "Invalid tags, will not set tags this time.");
        m1153a(context, i, a, j);
        return false;
    }

    /* renamed from: b */
    private static boolean m1163b(Context context, String str, long j, int i) {
        boolean z;
        int i2;
        if (str != null) {
            String replaceAll = str.replaceAll(",", "");
            if (i != 0) {
                z = true;
            } else {
                z = false;
            }
            if (!TextUtils.isEmpty(replaceAll)) {
                i2 = replaceAll.getBytes().length + 0;
            } else {
                i2 = 0;
            }
            boolean z2 = z ? i2 <= 5000 : i2 <= 7000;
            if (!z2) {
                m1153a(context, i, C0558a.f703i, j);
                C0582e.m1306c("TagAliasHelper", "The length of tags should be less than " + (i != 0 ? 5000 : 7000) + " bytes.");
                return false;
            }
        }
        return true;
    }

    /* renamed from: a */
    private static C0539c m1150a(Context context, List<String> list, String str, long j) {
        HashSet hashSet;
        if (list != null) {
            hashSet = new HashSet(list);
        } else {
            hashSet = null;
        }
        if (str != null && !m1160a(context, 0, str, j)) {
            return null;
        }
        if (hashSet != null && !m1161a(context, 0, (Set<String>) hashSet, j)) {
            return null;
        }
        String b = m1162b(hashSet);
        if (!m1163b(context, b, j, 0)) {
            return null;
        }
        if (b == null && str == null) {
            C0582e.m1308d("TagAliasHelper", "NULL alias and tags. Give up action.");
            m1153a(context, 0, C0558a.f696b, j);
            return null;
        }
        C0582e.m1302a("TagAliasHelper", "action:setAliasAndTags - alias:" + str + ", tags:" + b);
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("platform", "a");
            if (str != null) {
                jSONObject.put("alias", str);
            }
            if (hashSet != null) {
                jSONObject.put("tags", b);
            }
            String jSONObject2 = jSONObject.toString();
            if (!TextUtils.isEmpty(jSONObject2)) {
                return new C0539c(4, 10, j, JCoreInterface.getAppKey(), jSONObject2);
            }
        } catch (Throwable th) {
            m1153a(context, 0, C0558a.f704j, j);
        }
        return null;
    }

    /* renamed from: a */
    protected static C0539c m1149a(Context context, List<String> list, long j, int i, int i2) {
        boolean z;
        if (i != 0) {
            try {
                JSONObject a = m1152a(i);
                if (i == 1 || i == 2 || i == 3 || i == 6) {
                    z = true;
                } else {
                    z = false;
                }
                if (z) {
                    if (list == null || list.isEmpty()) {
                        C0582e.m1308d("TagAliasHelper", "tags was empty. Give up action.");
                        m1153a(context, 1, C0558a.f696b, j);
                        return null;
                    }
                    HashSet hashSet = new HashSet(list);
                    if (!m1161a(context, 1, (Set<String>) hashSet, j)) {
                        return null;
                    }
                    String b = m1162b(hashSet);
                    if (!m1163b(context, b, j, 1)) {
                        return null;
                    }
                    if (i != 6) {
                        JSONArray jSONArray = new JSONArray();
                        for (String put : list) {
                            jSONArray.put(put);
                        }
                        a.put("tags", jSONArray);
                    } else if (TextUtils.isEmpty(b)) {
                        C0582e.m1308d("TagAliasHelper", "stags was empty. Give up action.");
                        m1153a(context, 1, C0558a.f696b, j);
                        return null;
                    } else {
                        a.put("tags", b);
                    }
                }
                if (i == 5) {
                    String str = "curr";
                    if (i2 == -1) {
                        i2 = 1;
                    }
                    a.put(str, i2);
                }
                String jSONObject = a.toString();
                if (!TextUtils.isEmpty(jSONObject)) {
                    return new C0539c(1, 28, j, JCoreInterface.getAppKey(), jSONObject);
                }
            } catch (Throwable th) {
            }
            return null;
        }
        m1153a(context, 1, C0558a.f704j, j);
        return null;
    }

    /* renamed from: a */
    protected static C0539c m1148a(Context context, String str, long j, int i) {
        boolean z;
        boolean z2 = true;
        if (i == 2 || i == 3 || i == 5) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            try {
                JSONObject a = m1152a(i);
                if (i != 2) {
                    z2 = false;
                }
                if (z2) {
                    if (TextUtils.isEmpty(str)) {
                        C0582e.m1308d("TagAliasHelper", "alias was empty. Give up action.");
                        m1153a(context, 2, C0558a.f696b, j);
                        return null;
                    } else if (!m1160a(context, 2, str, j)) {
                        return null;
                    } else {
                        a.put("alias", str);
                    }
                }
                String jSONObject = a.toString();
                if (!TextUtils.isEmpty(jSONObject)) {
                    return new C0539c(1, 29, j, JCoreInterface.getAppKey(), jSONObject);
                }
            } catch (Throwable th) {
            }
            return null;
        }
        m1153a(context, 2, C0558a.f704j, j);
        return null;
    }

    /* renamed from: a */
    private static JSONObject m1152a(int i) throws JSONException {
        String str;
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("platform", "a");
        String str2 = "op";
        switch (i) {
            case 1:
                str = "add";
                break;
            case 2:
                str = "set";
                break;
            case 3:
                str = "del";
                break;
            case 4:
                str = "clean";
                break;
            case 5:
                str = "get";
                break;
            case 6:
                str = "valid";
                break;
            default:
                str = null;
                break;
        }
        jSONObject.put(str2, str);
        return jSONObject;
    }

    /* renamed from: a */
    protected static long m1147a(Context context, String str, int i, long j) {
        long j2;
        try {
            JSONObject jSONObject = new JSONObject(str);
            int optInt = jSONObject.optInt("code", C0558a.f704j);
            if (i == 0) {
                j2 = jSONObject.optLong("sequence");
            } else {
                j2 = j;
            }
            Intent intent = new Intent();
            intent.addCategory(C0541a.f651c);
            intent.putExtra("proto_type", i);
            intent.setPackage(context.getPackageName());
            if (i == 0) {
                intent.setAction("cn.jpush.android.intent.TAG_ALIAS_CALLBACK");
            } else {
                intent.setAction("cn.jpush.android.intent.RECEIVE_MESSAGE");
                if (i == 1) {
                    intent.putExtra("message_type", 1);
                } else {
                    intent.putExtra("message_type", 2);
                }
            }
            intent.putExtra("tagalias_errorcode", optInt);
            intent.putExtra("tagalias_seqid", j2);
            Intent a = C0556l.m1165a().mo6783a(context, j2, optInt, jSONObject, intent);
            if (a == null) {
                return j2;
            }
            context.sendBroadcast(a);
            return j2;
        } catch (Throwable th) {
            return -1;
        }
    }
}
