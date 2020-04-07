package p004cn.jiguang.p015d.p020c;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/* renamed from: cn.jiguang.d.c.h */
public final class C0431h implements Cloneable {

    /* renamed from: d */
    private static C0436m[] f350d = new C0436m[0];

    /* renamed from: e */
    private static C0435l[] f351e = new C0435l[0];

    /* renamed from: a */
    private C0430g f352a;

    /* renamed from: b */
    private List[] f353b;

    /* renamed from: c */
    private int f354c;

    public C0431h() {
        this(new C0430g());
    }

    private C0431h(C0427d dVar) {
        this(new C0430g(dVar));
        int i = 0;
        while (i < 4) {
            try {
                int b = this.f352a.mo6507b(i);
                if (b > 0) {
                    this.f353b[i] = new ArrayList(b);
                }
                for (int i2 = 0; i2 < b; i2++) {
                    this.f353b[i].add(C0436m.m582a(dVar, i));
                }
                i++;
            } catch (C0443t e) {
                throw e;
            }
        }
        this.f354c = dVar.mo6484a();
    }

    private C0431h(C0430g gVar) {
        this.f353b = new List[4];
        this.f352a = gVar;
    }

    public C0431h(byte[] bArr) {
        this(new C0427d(bArr));
    }

    /* renamed from: a */
    public static C0431h m547a(C0436m mVar) {
        C0431h hVar = new C0431h();
        if (hVar.f353b[0] == null) {
            hVar.f353b[0] = new LinkedList();
        }
        hVar.f352a.mo6505a(0);
        hVar.f353b[0].add(mVar);
        return hVar;
    }

    /* renamed from: a */
    public final C0436m mo6510a() {
        List list = this.f353b[0];
        if (list == null || list.size() == 0) {
            return null;
        }
        return (C0436m) list.get(0);
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x0073  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0080 A[SYNTHETIC] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final p004cn.jiguang.p015d.p020c.C0435l[] mo6511a(int r12) {
        /*
            r11 = this;
            r3 = 0
            r4 = 1
            java.util.List[] r0 = r11.f353b
            r0 = r0[r4]
            if (r0 != 0) goto L_0x000b
            cn.jiguang.d.c.l[] r0 = f351e
        L_0x000a:
            return r0
        L_0x000b:
            java.util.LinkedList r6 = new java.util.LinkedList
            r6.<init>()
            java.util.List[] r0 = r11.f353b
            r0 = r0[r4]
            if (r0 != 0) goto L_0x0084
            cn.jiguang.d.c.m[] r0 = f350d
            r1 = r0
        L_0x0019:
            java.util.HashSet r7 = new java.util.HashSet
            r7.<init>()
            r2 = r3
        L_0x001f:
            int r0 = r1.length
            if (r2 >= r0) goto L_0x009a
            r0 = r1[r2]
            cn.jiguang.d.c.j r8 = r0.mo6537b()
            boolean r0 = r7.contains(r8)
            if (r0 == 0) goto L_0x00a8
            int r0 = r6.size()
            int r0 = r0 + -1
            r5 = r0
        L_0x0035:
            if (r5 < 0) goto L_0x00a8
            java.lang.Object r0 = r6.get(r5)
            cn.jiguang.d.c.l r0 = (p004cn.jiguang.p015d.p020c.C0435l) r0
            cn.jiguang.d.c.m r9 = r0.mo6529b()
            int r9 = r9.mo6540d()
            r10 = r1[r2]
            int r10 = r10.mo6540d()
            if (r9 != r10) goto L_0x0096
            cn.jiguang.d.c.m r9 = r0.mo6529b()
            int r9 = r9.mo6541e()
            r10 = r1[r2]
            int r10 = r10.mo6541e()
            if (r9 != r10) goto L_0x0096
            cn.jiguang.d.c.m r9 = r0.mo6529b()
            cn.jiguang.d.c.j r9 = r9.mo6537b()
            boolean r9 = r9.equals(r8)
            if (r9 == 0) goto L_0x0096
            r5 = r1[r2]
            r0.mo6528a(r5)
            r0 = r3
        L_0x0071:
            if (r0 == 0) goto L_0x0080
            cn.jiguang.d.c.l r0 = new cn.jiguang.d.c.l
            r5 = r1[r2]
            r0.<init>(r5)
            r6.add(r0)
            r7.add(r8)
        L_0x0080:
            int r0 = r2 + 1
            r2 = r0
            goto L_0x001f
        L_0x0084:
            java.util.List[] r0 = r11.f353b
            r0 = r0[r4]
            int r1 = r0.size()
            cn.jiguang.d.c.m[] r1 = new p004cn.jiguang.p015d.p020c.C0436m[r1]
            java.lang.Object[] r0 = r0.toArray(r1)
            cn.jiguang.d.c.m[] r0 = (p004cn.jiguang.p015d.p020c.C0436m[]) r0
            r1 = r0
            goto L_0x0019
        L_0x0096:
            int r0 = r5 + -1
            r5 = r0
            goto L_0x0035
        L_0x009a:
            int r0 = r6.size()
            cn.jiguang.d.c.l[] r0 = new p004cn.jiguang.p015d.p020c.C0435l[r0]
            java.lang.Object[] r0 = r6.toArray(r0)
            cn.jiguang.d.c.l[] r0 = (p004cn.jiguang.p015d.p020c.C0435l[]) r0
            goto L_0x000a
        L_0x00a8:
            r0 = r4
            goto L_0x0071
        */
        throw new UnsupportedOperationException("Method not decompiled: p004cn.jiguang.p015d.p020c.C0431h.mo6511a(int):cn.jiguang.d.c.l[]");
    }

    /* renamed from: b */
    public final byte[] mo6512b(int i) {
        int i2;
        int i3;
        int i4;
        C0428e eVar = new C0428e();
        this.f352a.mo6506a(eVar);
        C0425b bVar = new C0425b();
        this.f352a.mo6504a();
        int i5 = 0;
        while (true) {
            int i6 = i5;
            if (i6 < 4) {
                if (this.f353b[i6] != null) {
                    int size = this.f353b[i6].size();
                    int a = eVar.mo6495a();
                    int i7 = 0;
                    int i8 = 0;
                    C0436m mVar = null;
                    int i9 = 0;
                    while (true) {
                        if (i9 >= size) {
                            break;
                        }
                        C0436m mVar2 = (C0436m) this.f353b[i6].get(i9);
                        if (i6 == 3) {
                            C0436m mVar3 = mVar;
                            i2 = a;
                            i3 = i7;
                            i4 = i8 + 1;
                            mVar2 = mVar3;
                        } else {
                            if (mVar != null) {
                                if (!(mVar2.mo6540d() == mVar.mo6540d() && mVar2.mo6541e() == mVar.mo6541e() && mVar2.mo6537b().equals(mVar.mo6537b()))) {
                                    a = eVar.mo6495a();
                                    i7 = i9;
                                }
                            }
                            mVar2.mo6534a(eVar, bVar);
                            if (eVar.mo6495a() > 65535) {
                                eVar.mo6496a(a);
                                int i10 = (size - i7) + i8;
                                break;
                            }
                            i2 = a;
                            i3 = i7;
                            i4 = i8;
                        }
                        i9++;
                        i8 = i4;
                        i7 = i3;
                        a = i2;
                        mVar = mVar2;
                    }
                }
                i5 = i6 + 1;
            } else {
                this.f354c = eVar.mo6495a();
                return eVar.mo6502b();
            }
        }
    }

    public final Object clone() {
        C0431h hVar = new C0431h();
        for (int i = 0; i < this.f353b.length; i++) {
            if (this.f353b[i] != null) {
                hVar.f353b[i] = new LinkedList(this.f353b[i]);
            }
        }
        hVar.f352a = (C0430g) this.f352a.clone();
        hVar.f354c = this.f354c;
        return hVar;
    }
}
