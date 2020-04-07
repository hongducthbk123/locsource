package p004cn.jpush.android.p037a;

import android.content.Context;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONArray;
import org.json.JSONObject;
import p004cn.jiguang.api.MultiSpHelper;
import p004cn.jpush.android.api.JPushInterface.C0558a;
import p004cn.jpush.p036a.C0539c;

/* renamed from: cn.jpush.android.a.l */
public final class C0556l {

    /* renamed from: a */
    private static volatile C0556l f677a;

    /* renamed from: b */
    private static final Object f678b = new Object();

    /* renamed from: c */
    private ConcurrentHashMap<Long, C0557a> f679c = new ConcurrentHashMap<>();

    /* renamed from: cn.jpush.android.a.l$a */
    private class C0557a {

        /* renamed from: a */
        public int f680a;

        /* renamed from: b */
        public int f681b;

        /* renamed from: c */
        public long f682c;

        /* renamed from: d */
        public ArrayList<String> f683d;

        /* renamed from: e */
        public String f684e;

        /* renamed from: f */
        public int f685f = -1;

        /* renamed from: g */
        public int f686g = -1;

        /* renamed from: h */
        public int f687h = 0;

        /* renamed from: i */
        public int f688i = 0;

        public C0557a(int i, int i2, long j, ArrayList<String> arrayList, String str) {
            this.f680a = i;
            this.f681b = i2;
            this.f682c = j;
            this.f683d = arrayList;
            if (i == 1 && this.f683d == null) {
                this.f683d = new ArrayList<>();
            }
            this.f684e = str;
            this.f688i = 1;
        }

        public final String toString() {
            return "TagAliasCacheBean{protoType=" + this.f680a + ", actionType=" + this.f681b + ", seqID=" + this.f682c + ", tags=" + this.f683d + ", alias='" + this.f684e + '\'' + ", totalPage=" + this.f685f + ", currPage=" + this.f686g + ", retryCount=" + this.f687h + '}';
        }
    }

    /* renamed from: a */
    public static C0556l m1165a() {
        if (f677a == null) {
            synchronized (f678b) {
                if (f677a == null) {
                    f677a = new C0556l();
                }
            }
        }
        return f677a;
    }

    /* renamed from: a */
    public final void mo6784a(int i, int i2, long j, ArrayList<String> arrayList, String str) {
        this.f679c.put(Long.valueOf(j), new C0557a(i, i2, j, arrayList, str));
    }

    /* renamed from: a */
    public final boolean mo6785a(int i) {
        if (this.f679c != null && !this.f679c.isEmpty()) {
            for (Entry value : this.f679c.entrySet()) {
                C0557a aVar = (C0557a) value.getValue();
                if (aVar != null && aVar.f680a == i) {
                    return false;
                }
            }
        }
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0032  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x003e  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.content.Intent mo6783a(android.content.Context r7, long r8, int r10, org.json.JSONObject r11, android.content.Intent r12) {
        /*
            r6 = this;
            java.util.concurrent.ConcurrentHashMap<java.lang.Long, cn.jpush.android.a.l$a> r0 = r6.f679c
            java.lang.Long r1 = java.lang.Long.valueOf(r8)
            java.lang.Object r0 = r0.get(r1)
            cn.jpush.android.a.l$a r0 = (p004cn.jpush.android.p037a.C0556l.C0557a) r0
            java.util.concurrent.ConcurrentHashMap<java.lang.Long, cn.jpush.android.a.l$a> r1 = r6.f679c
            java.lang.Long r2 = java.lang.Long.valueOf(r8)
            r1.remove(r2)
            if (r0 != 0) goto L_0x0018
        L_0x0017:
            return r12
        L_0x0018:
            r1 = 1
            if (r10 != r1) goto L_0x003c
            int r1 = r0.f687h
            if (r1 != 0) goto L_0x003c
            int r1 = r0.f687h
            int r1 = r1 + 1
            r0.f687h = r1
            int r1 = r0.f680a
            long r2 = r0.f682c
            boolean r1 = m1166a(r7, r1, r2)
            if (r1 == 0) goto L_0x0034
            r1 = 1
        L_0x0030:
            if (r1 == 0) goto L_0x003e
            r12 = 0
            goto L_0x0017
        L_0x0034:
            boolean r1 = r6.m1167a(r7, r0)
            if (r1 == 0) goto L_0x003c
            r1 = 1
            goto L_0x0030
        L_0x003c:
            r1 = 0
            goto L_0x0030
        L_0x003e:
            if (r10 == 0) goto L_0x00af
            r1 = 100
            if (r10 != r1) goto L_0x008c
            java.lang.String r1 = "wait"
            r2 = -1
            long r2 = r11.optLong(r1, r2)
            java.lang.String r1 = "TagAliasNewProtoRetryHelper"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "set tag/alias action will freeze "
            r4.<init>(r5)
            java.lang.StringBuilder r4 = r4.append(r2)
            java.lang.String r5 = " seconds"
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r4 = r4.toString()
            p004cn.jpush.android.p040d.C0582e.m1306c(r1, r4)
            r4 = 0
            int r1 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r1 <= 0) goto L_0x008c
            r4 = 0
            int r1 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r1 < 0) goto L_0x008c
            r4 = 1800(0x708, double:8.893E-321)
            int r1 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r1 <= 0) goto L_0x007b
            r2 = 1800(0x708, double:8.893E-321)
        L_0x007b:
            java.lang.String r1 = "TAFreezeSetTime"
            long r4 = java.lang.System.currentTimeMillis()
            p004cn.jiguang.api.MultiSpHelper.commitLong(r7, r1, r4)
            java.lang.String r1 = "TAFreezeEndTime"
            r4 = 1000(0x3e8, double:4.94E-321)
            long r2 = r2 * r4
            p004cn.jiguang.api.MultiSpHelper.commitLong(r7, r1, r2)
        L_0x008c:
            int r0 = r0.f680a
            if (r0 == 0) goto L_0x0093
            switch(r10) {
                case 0: goto L_0x0093;
                case 1: goto L_0x009a;
                case 2: goto L_0x009a;
                case 3: goto L_0x009d;
                case 4: goto L_0x00a0;
                case 5: goto L_0x00a3;
                case 6: goto L_0x00a6;
                case 7: goto L_0x00a9;
                case 8: goto L_0x00a9;
                case 100: goto L_0x00ac;
                default: goto L_0x0093;
            }
        L_0x0093:
            java.lang.String r0 = "tagalias_errorcode"
            r12.putExtra(r0, r10)
            goto L_0x0017
        L_0x009a:
            int r10 = p004cn.jpush.android.api.JPushInterface.C0558a.f709o
            goto L_0x0093
        L_0x009d:
            int r10 = p004cn.jpush.android.api.JPushInterface.C0558a.f710p
            goto L_0x0093
        L_0x00a0:
            int r10 = p004cn.jpush.android.api.JPushInterface.C0558a.f711q
            goto L_0x0093
        L_0x00a3:
            int r10 = p004cn.jpush.android.api.JPushInterface.C0558a.f712r
            goto L_0x0093
        L_0x00a6:
            int r10 = p004cn.jpush.android.api.JPushInterface.C0558a.f713s
            goto L_0x0093
        L_0x00a9:
            int r10 = p004cn.jpush.android.api.JPushInterface.C0558a.f714t
            goto L_0x0093
        L_0x00ac:
            int r10 = p004cn.jpush.android.api.JPushInterface.C0558a.f715u
            goto L_0x0093
        L_0x00af:
            r1 = 0
            r0.f687h = r1
            int r1 = r0.f681b
            r2 = 5
            if (r1 != r2) goto L_0x00cc
            java.lang.String r1 = "total"
            r2 = -1
            int r1 = r11.optInt(r1, r2)
            r0.f685f = r1
            java.lang.String r1 = "curr"
            r2 = -1
            int r1 = r11.optInt(r1, r2)
            r0.f686g = r1
            m1164a(r11, r0)
        L_0x00cc:
            if (r0 != 0) goto L_0x00e4
            r1 = 0
        L_0x00cf:
            if (r1 == 0) goto L_0x00f7
            int r1 = r0.f686g
            int r1 = r1 + 1
            r0.f686g = r1
            int r1 = r0.f680a
            long r2 = r0.f682c
            boolean r1 = m1166a(r7, r1, r2)
            if (r1 == 0) goto L_0x00ee
            r12 = 0
            goto L_0x0017
        L_0x00e4:
            int r1 = r0.f686g
            int r2 = r0.f685f
            if (r1 < r2) goto L_0x00ec
            r1 = 0
            goto L_0x00cf
        L_0x00ec:
            r1 = 1
            goto L_0x00cf
        L_0x00ee:
            boolean r1 = r6.m1167a(r7, r0)
            if (r1 == 0) goto L_0x00f7
            r12 = 0
            goto L_0x0017
        L_0x00f7:
            int r1 = r0.f681b
            r2 = 5
            if (r1 != r2) goto L_0x0124
            int r1 = r0.f680a
            r2 = 1
            if (r1 != r2) goto L_0x0112
            java.util.ArrayList<java.lang.String> r1 = r0.f683d
            int r1 = r1.size()
            if (r1 <= 0) goto L_0x0017
            java.lang.String r1 = "tags"
            java.util.ArrayList<java.lang.String> r0 = r0.f683d
            r12.putStringArrayListExtra(r1, r0)
            goto L_0x0017
        L_0x0112:
            int r1 = r0.f680a
            r2 = 2
            if (r1 != r2) goto L_0x0017
            java.lang.String r1 = r0.f684e
            if (r1 == 0) goto L_0x0017
            java.lang.String r1 = "alias"
            java.lang.String r0 = r0.f684e
            r12.putExtra(r1, r0)
            goto L_0x0017
        L_0x0124:
            int r1 = r0.f681b
            r2 = 6
            if (r1 != r2) goto L_0x0017
            int r0 = r0.f680a
            r1 = 1
            if (r0 != r1) goto L_0x0017
            java.lang.String r0 = "validated"
            java.lang.String r1 = "validated"
            r2 = 0
            boolean r1 = r11.optBoolean(r1, r2)
            r12.putExtra(r0, r1)
            goto L_0x0017
        */
        throw new UnsupportedOperationException("Method not decompiled: p004cn.jpush.android.p037a.C0556l.mo6783a(android.content.Context, long, int, org.json.JSONObject, android.content.Intent):android.content.Intent");
    }

    /* renamed from: a */
    private static C0557a m1164a(JSONObject jSONObject, C0557a aVar) {
        if (aVar == null) {
            return null;
        }
        if (!TextUtils.equals(jSONObject.optString("op"), "get")) {
            return aVar;
        }
        if (aVar.f680a == 1) {
            try {
                JSONArray optJSONArray = jSONObject.optJSONArray("tags");
                if (optJSONArray == null || optJSONArray.length() == 0) {
                    return aVar;
                }
                ArrayList arrayList = new ArrayList();
                for (int i = 0; i < optJSONArray.length(); i++) {
                    arrayList.add(optJSONArray.getString(i));
                }
                if (arrayList.size() <= 0) {
                    return aVar;
                }
                aVar.f683d.addAll(arrayList);
                return aVar;
            } catch (Throwable th) {
                return aVar;
            }
        } else {
            String optString = jSONObject.optString("alias");
            if (optString == null) {
                return aVar;
            }
            aVar.f684e = optString;
            return aVar;
        }
    }

    /* renamed from: a */
    public final int mo6782a(long j) {
        C0557a aVar = (C0557a) this.f679c.remove(Long.valueOf(j));
        if (aVar != null) {
            return aVar.f680a;
        }
        return 0;
    }

    /* renamed from: a */
    private boolean m1167a(Context context, C0557a aVar) {
        C0539c a;
        if (aVar == null) {
            return false;
        }
        if (aVar.f680a == 1) {
            a = C0555k.m1149a(context, (List<String>) aVar.f683d, aVar.f682c, aVar.f681b, aVar.f686g);
        } else if (aVar.f680a != 2) {
            return false;
        } else {
            a = C0555k.m1148a(context, aVar.f684e, aVar.f682c, aVar.f680a);
        }
        if (a == null) {
            return false;
        }
        if (aVar.f687h > 200) {
            this.f679c.remove(Long.valueOf(aVar.f682c));
            C0555k.m1153a(context, aVar.f680a, C0558a.f709o, aVar.f682c);
        } else {
            C0555k.m1157a(context, a);
            aVar.f687h++;
            this.f679c.put(Long.valueOf(aVar.f682c), aVar);
        }
        return true;
    }

    /* renamed from: a */
    protected static boolean m1166a(Context context, int i, long j) {
        boolean z;
        if (i == 1 || i == 2) {
            long j2 = MultiSpHelper.getLong(context, "TAFreezeEndTime", -1);
            if (j2 > 1800) {
                j2 = 0;
            }
            long j3 = MultiSpHelper.getLong(context, "TAFreezeSetTime", -1);
            if (j2 == -1 || j3 == -1) {
                z = false;
            } else if (System.currentTimeMillis() - j3 < 0 || System.currentTimeMillis() - j3 > j2) {
                MultiSpHelper.commitLong(context, "TAFreezeSetTime", -1);
                MultiSpHelper.commitLong(context, "TAFreezeEndTime", -1);
                z = true;
            } else {
                z = false;
            }
            if (z) {
                C0555k.m1153a(context, i, C0558a.f715u, j);
                return true;
            }
        }
        return false;
    }
}
