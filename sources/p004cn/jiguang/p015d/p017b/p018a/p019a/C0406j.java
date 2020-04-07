package p004cn.jiguang.p015d.p017b.p018a.p019a;

import android.text.TextUtils;
import java.util.Iterator;
import java.util.Map.Entry;
import p004cn.jiguang.p015d.p016a.C0386a;
import p004cn.jiguang.p015d.p017b.C0419f;
import p004cn.jiguang.p015d.p017b.p018a.C0396a;
import p004cn.jiguang.p015d.p017b.p018a.C0410c;
import p004cn.jiguang.p015d.p017b.p018a.C0411d;
import p004cn.jiguang.p015d.p025f.C0477d;
import p004cn.jiguang.p029e.C0501d;

/* renamed from: cn.jiguang.d.b.a.a.j */
public abstract class C0406j {

    /* renamed from: a */
    final C0411d f250a;

    public C0406j(C0411d dVar) {
        this.f250a = dVar;
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x006c  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x013b  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private p004cn.jiguang.p015d.p017b.p018a.C0396a m407a(java.net.InetAddress r17, int r18, java.lang.String r19, java.net.DatagramSocket r20) {
        /*
            r16 = this;
            java.lang.String r4 = r17.getHostAddress()
            r6 = 0
            long r2 = java.lang.System.currentTimeMillis()
            long r8 = p004cn.jiguang.p015d.p016a.C0386a.m294u()
            long r8 = r8 + r2
            r3 = 0
            r0 = r16
            cn.jiguang.d.b.a.d r2 = r0.f250a     // Catch:{ Exception -> 0x007f }
            byte[] r2 = r2.mo6421c()     // Catch:{ Exception -> 0x007f }
            java.net.DatagramPacket r5 = new java.net.DatagramPacket     // Catch:{ e -> 0x0060, all -> 0x009a }
            int r10 = r2.length     // Catch:{ e -> 0x0060, all -> 0x009a }
            r0 = r17
            r1 = r18
            r5.<init>(r2, r10, r0, r1)     // Catch:{ e -> 0x0060, all -> 0x009a }
            long r8 = java.lang.System.currentTimeMillis()     // Catch:{ e -> 0x0060, all -> 0x009a }
            r0 = r20
            byte[] r2 = p004cn.jiguang.p015d.p021d.C0446c.m638a(r0, r5)     // Catch:{ Exception -> 0x00a0 }
            long r10 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x00a0 }
            long r10 = r10 - r8
            r12 = 1000(0x3e8, double:4.94E-321)
            long r6 = r10 / r12
            long r8 = p004cn.jiguang.p015d.p016a.C0386a.m242a(r8)     // Catch:{ Exception -> 0x00a0 }
            cn.jiguang.d.b.a.b r2 = p004cn.jiguang.p015d.p017b.p018a.C0411d.m417a(r2)     // Catch:{ e -> 0x0060, all -> 0x009a }
            java.lang.String r5 = new java.lang.String     // Catch:{ e -> 0x0060, all -> 0x009a }
            byte[] r2 = r2.f252b     // Catch:{ e -> 0x0060, all -> 0x009a }
            r5.<init>(r2)     // Catch:{ e -> 0x0060, all -> 0x009a }
            cn.jiguang.d.b.i r2 = p004cn.jiguang.p015d.p021d.C0446c.m632a(r5)     // Catch:{ e -> 0x0060, all -> 0x009a }
            if (r2 != 0) goto L_0x00d4
            cn.jiguang.d.b.a.e r2 = new cn.jiguang.d.b.a.e     // Catch:{ e -> 0x0060, all -> 0x009a }
            r5 = 5
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ e -> 0x0060, all -> 0x009a }
            java.lang.String r11 = "Can not parse sis info from host: "
            r10.<init>(r11)     // Catch:{ e -> 0x0060, all -> 0x009a }
            java.lang.StringBuilder r10 = r10.append(r4)     // Catch:{ e -> 0x0060, all -> 0x009a }
            java.lang.String r10 = r10.toString()     // Catch:{ e -> 0x0060, all -> 0x009a }
            r2.<init>(r5, r10)     // Catch:{ e -> 0x0060, all -> 0x009a }
            throw r2     // Catch:{ e -> 0x0060, all -> 0x009a }
        L_0x0060:
            r2 = move-exception
            r14 = r8
            r8 = r6
            r6 = r14
        L_0x0064:
            int r10 = r2.mo6425a()     // Catch:{ all -> 0x0156 }
            throw r2     // Catch:{ all -> 0x0069 }
        L_0x0069:
            r2 = move-exception
        L_0x006a:
            if (r10 == 0) goto L_0x013b
            r0 = r16
            cn.jiguang.d.b.a.d r3 = r0.f250a
            r5 = r18
            r3.mo6414a(r4, r5, r6, r8, r10)
            r0 = r16
            cn.jiguang.d.b.a.d r3 = r0.f250a
            r0 = r18
            r3.mo6413a(r4, r0, r10)
        L_0x007e:
            throw r2
        L_0x007f:
            r2 = move-exception
            cn.jiguang.d.b.a.e r5 = new cn.jiguang.d.b.a.e     // Catch:{ e -> 0x0060, all -> 0x009a }
            r10 = 1
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ e -> 0x0060, all -> 0x009a }
            java.lang.String r12 = "Failed to package data - "
            r11.<init>(r12)     // Catch:{ e -> 0x0060, all -> 0x009a }
            java.lang.String r2 = r2.getMessage()     // Catch:{ e -> 0x0060, all -> 0x009a }
            java.lang.StringBuilder r2 = r11.append(r2)     // Catch:{ e -> 0x0060, all -> 0x009a }
            java.lang.String r2 = r2.toString()     // Catch:{ e -> 0x0060, all -> 0x009a }
            r5.<init>(r10, r2)     // Catch:{ e -> 0x0060, all -> 0x009a }
            throw r5     // Catch:{ e -> 0x0060, all -> 0x009a }
        L_0x009a:
            r2 = move-exception
            r10 = r3
            r14 = r8
            r8 = r6
            r6 = r14
            goto L_0x006a
        L_0x00a0:
            r2 = move-exception
            long r10 = java.lang.System.currentTimeMillis()     // Catch:{ e -> 0x0060, all -> 0x009a }
            long r10 = r10 - r8
            r12 = 1000(0x3e8, double:4.94E-321)
            long r10 = r10 / r12
            long r6 = p004cn.jiguang.p015d.p016a.C0386a.m242a(r8)     // Catch:{ e -> 0x015a, all -> 0x014b }
            cn.jiguang.d.b.a.e r5 = new cn.jiguang.d.b.a.e     // Catch:{ e -> 0x00d1, all -> 0x0151 }
            r8 = 2
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ e -> 0x00d1, all -> 0x0151 }
            java.lang.String r12 = "Can not get sis response from host: - "
            r9.<init>(r12)     // Catch:{ e -> 0x00d1, all -> 0x0151 }
            java.lang.StringBuilder r9 = r9.append(r4)     // Catch:{ e -> 0x00d1, all -> 0x0151 }
            java.lang.String r12 = " - "
            java.lang.StringBuilder r9 = r9.append(r12)     // Catch:{ e -> 0x00d1, all -> 0x0151 }
            java.lang.String r2 = r2.getMessage()     // Catch:{ e -> 0x00d1, all -> 0x0151 }
            java.lang.StringBuilder r2 = r9.append(r2)     // Catch:{ e -> 0x00d1, all -> 0x0151 }
            java.lang.String r2 = r2.toString()     // Catch:{ e -> 0x00d1, all -> 0x0151 }
            r5.<init>(r8, r2)     // Catch:{ e -> 0x00d1, all -> 0x0151 }
            throw r5     // Catch:{ e -> 0x00d1, all -> 0x0151 }
        L_0x00d1:
            r2 = move-exception
            r8 = r10
            goto L_0x0064
        L_0x00d4:
            r2.mo6479h()     // Catch:{ e -> 0x0060, all -> 0x009a }
            java.lang.String r5 = "SisPolicy"
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ e -> 0x0060, all -> 0x009a }
            java.lang.String r11 = "Get sis info succeed with host: "
            r10.<init>(r11)     // Catch:{ e -> 0x0060, all -> 0x009a }
            java.lang.StringBuilder r10 = r10.append(r4)     // Catch:{ e -> 0x0060, all -> 0x009a }
            java.lang.String r11 = " type:"
            java.lang.StringBuilder r10 = r10.append(r11)     // Catch:{ e -> 0x0060, all -> 0x009a }
            r0 = r19
            java.lang.StringBuilder r10 = r10.append(r0)     // Catch:{ e -> 0x0060, all -> 0x009a }
            java.lang.String r10 = r10.toString()     // Catch:{ e -> 0x0060, all -> 0x009a }
            p004cn.jiguang.p029e.C0501d.m905b(r5, r10)     // Catch:{ e -> 0x0060, all -> 0x009a }
            p004cn.jiguang.p015d.p016a.C0386a.m276g()     // Catch:{ e -> 0x0060, all -> 0x009a }
            boolean r5 = r2.mo6478g()     // Catch:{ e -> 0x0060, all -> 0x009a }
            p004cn.jiguang.p015d.p016a.C0386a.m251a(r5)     // Catch:{ e -> 0x0060, all -> 0x009a }
            r0 = r16
            cn.jiguang.d.b.a.d r5 = r0.f250a     // Catch:{ e -> 0x0060, all -> 0x009a }
            cn.jiguang.d.b.a.a r10 = p004cn.jiguang.p015d.p017b.p018a.C0396a.m390a(r2)     // Catch:{ e -> 0x0060, all -> 0x009a }
            r5.mo6412a(r10)     // Catch:{ e -> 0x0060, all -> 0x009a }
            cn.jiguang.d.b.a.a r2 = p004cn.jiguang.p015d.p017b.p018a.C0396a.m393b(r2)     // Catch:{ e -> 0x0060, all -> 0x009a }
            boolean r5 = r2.mo6396a()     // Catch:{ e -> 0x0060, all -> 0x009a }
            if (r5 == 0) goto L_0x012c
            cn.jiguang.d.b.a.e r2 = new cn.jiguang.d.b.a.e     // Catch:{ e -> 0x0060, all -> 0x009a }
            r5 = 5
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ e -> 0x0060, all -> 0x009a }
            java.lang.String r11 = "sis address is Empty from host:"
            r10.<init>(r11)     // Catch:{ e -> 0x0060, all -> 0x009a }
            java.lang.StringBuilder r10 = r10.append(r4)     // Catch:{ e -> 0x0060, all -> 0x009a }
            java.lang.String r10 = r10.toString()     // Catch:{ e -> 0x0060, all -> 0x009a }
            r2.<init>(r5, r10)     // Catch:{ e -> 0x0060, all -> 0x009a }
            throw r2     // Catch:{ e -> 0x0060, all -> 0x009a }
        L_0x012c:
            r0 = r16
            cn.jiguang.d.b.a.d r3 = r0.f250a
            cn.jiguang.d.b.a.c r5 = new cn.jiguang.d.b.a.c
            r0 = r18
            r5.<init>(r4, r0)
            r3.mo6420c(r5)
            return r2
        L_0x013b:
            r0 = r16
            cn.jiguang.d.b.a.d r3 = r0.f250a
            cn.jiguang.d.b.a.c r5 = new cn.jiguang.d.b.a.c
            r0 = r18
            r5.<init>(r4, r0)
            r3.mo6420c(r5)
            goto L_0x007e
        L_0x014b:
            r2 = move-exception
            r6 = r8
            r8 = r10
            r10 = r3
            goto L_0x006a
        L_0x0151:
            r2 = move-exception
            r8 = r10
            r10 = r3
            goto L_0x006a
        L_0x0156:
            r2 = move-exception
            r10 = r3
            goto L_0x006a
        L_0x015a:
            r2 = move-exception
            r6 = r8
            r8 = r10
            goto L_0x0064
        */
        throw new UnsupportedOperationException("Method not decompiled: p004cn.jiguang.p015d.p017b.p018a.p019a.C0406j.m407a(java.net.InetAddress, int, java.lang.String, java.net.DatagramSocket):cn.jiguang.d.b.a.a");
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public abstract int mo6402a();

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x007c, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        p004cn.jiguang.p029e.C0501d.m907c("SisPolicy", "singleSendSis failed, error:" + r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:?, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00b7, code lost:
        r0 = th;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00af A[SYNTHETIC, Splitter:B:41:0x00af] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00b7 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:7:0x0010] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final p004cn.jiguang.p015d.p017b.p018a.C0396a mo6406a(p004cn.jiguang.p015d.p017b.p018a.C0396a r10) {
        /*
            r9 = this;
            r2 = 0
            if (r10 == 0) goto L_0x0009
            boolean r0 = r10.mo6396a()
            if (r0 == 0) goto L_0x000b
        L_0x0009:
            r0 = r2
        L_0x000a:
            return r0
        L_0x000b:
            java.net.DatagramSocket r3 = new java.net.DatagramSocket     // Catch:{ Throwable -> 0x00bc, all -> 0x00ab }
            r3.<init>()     // Catch:{ Throwable -> 0x00bc, all -> 0x00ab }
            java.util.Iterator r4 = r10.mo6398b()     // Catch:{ Throwable -> 0x0092, all -> 0x00b7 }
        L_0x0014:
            boolean r0 = r4.hasNext()     // Catch:{ Throwable -> 0x0092, all -> 0x00b7 }
            if (r0 == 0) goto L_0x00a3
            java.lang.Object r0 = r4.next()     // Catch:{ Throwable -> 0x0092, all -> 0x00b7 }
            java.util.Map$Entry r0 = (java.util.Map.Entry) r0     // Catch:{ Throwable -> 0x0092, all -> 0x00b7 }
            java.lang.Object r1 = r0.getKey()     // Catch:{ Throwable -> 0x0092, all -> 0x00b7 }
            cn.jiguang.d.b.a.c r1 = (p004cn.jiguang.p015d.p017b.p018a.C0410c) r1     // Catch:{ Throwable -> 0x0092, all -> 0x00b7 }
            java.lang.Object r0 = r0.getValue()     // Catch:{ Throwable -> 0x0092, all -> 0x00b7 }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ Throwable -> 0x0092, all -> 0x00b7 }
            java.lang.String r5 = r1.f253a     // Catch:{ Throwable -> 0x0092, all -> 0x00b7 }
            java.net.InetAddress r5 = p004cn.jiguang.p015d.p021d.C0446c.m639b(r5)     // Catch:{ Throwable -> 0x0092, all -> 0x00b7 }
            if (r5 == 0) goto L_0x0014
            java.lang.String r6 = r5.getHostAddress()     // Catch:{ Throwable -> 0x0092, all -> 0x00b7 }
            r1.f253a = r6     // Catch:{ Throwable -> 0x0092, all -> 0x00b7 }
            java.lang.String r6 = "SisPolicy"
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0092, all -> 0x00b7 }
            java.lang.String r8 = "To get sis - host:"
            r7.<init>(r8)     // Catch:{ Throwable -> 0x0092, all -> 0x00b7 }
            java.lang.String r8 = r1.f253a     // Catch:{ Throwable -> 0x0092, all -> 0x00b7 }
            java.lang.StringBuilder r7 = r7.append(r8)     // Catch:{ Throwable -> 0x0092, all -> 0x00b7 }
            java.lang.String r8 = ", port:"
            java.lang.StringBuilder r7 = r7.append(r8)     // Catch:{ Throwable -> 0x0092, all -> 0x00b7 }
            int r8 = r1.f254b     // Catch:{ Throwable -> 0x0092, all -> 0x00b7 }
            java.lang.StringBuilder r7 = r7.append(r8)     // Catch:{ Throwable -> 0x0092, all -> 0x00b7 }
            java.lang.String r8 = " ,type:"
            java.lang.StringBuilder r7 = r7.append(r8)     // Catch:{ Throwable -> 0x0092, all -> 0x00b7 }
            java.lang.StringBuilder r7 = r7.append(r0)     // Catch:{ Throwable -> 0x0092, all -> 0x00b7 }
            java.lang.String r7 = r7.toString()     // Catch:{ Throwable -> 0x0092, all -> 0x00b7 }
            p004cn.jiguang.p029e.C0501d.m903a(r6, r7)     // Catch:{ Throwable -> 0x0092, all -> 0x00b7 }
            cn.jiguang.d.b.a.d r6 = r9.f250a     // Catch:{ Throwable -> 0x0092, all -> 0x00b7 }
            boolean r6 = r6.mo6415a(r1)     // Catch:{ Throwable -> 0x0092, all -> 0x00b7 }
            if (r6 != 0) goto L_0x0014
            int r1 = r1.f254b     // Catch:{ Throwable -> 0x007c, all -> 0x00b7 }
            cn.jiguang.d.b.a.a r0 = r9.m407a(r5, r1, r0, r3)     // Catch:{ Throwable -> 0x007c, all -> 0x00b7 }
            if (r3 == 0) goto L_0x000a
            r3.close()     // Catch:{ Throwable -> 0x007a }
            goto L_0x000a
        L_0x007a:
            r1 = move-exception
            goto L_0x000a
        L_0x007c:
            r0 = move-exception
            java.lang.String r1 = "SisPolicy"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0092, all -> 0x00b7 }
            java.lang.String r6 = "singleSendSis failed, error:"
            r5.<init>(r6)     // Catch:{ Throwable -> 0x0092, all -> 0x00b7 }
            java.lang.StringBuilder r0 = r5.append(r0)     // Catch:{ Throwable -> 0x0092, all -> 0x00b7 }
            java.lang.String r0 = r0.toString()     // Catch:{ Throwable -> 0x0092, all -> 0x00b7 }
            p004cn.jiguang.p029e.C0501d.m907c(r1, r0)     // Catch:{ Throwable -> 0x0092, all -> 0x00b7 }
            goto L_0x0014
        L_0x0092:
            r0 = move-exception
            r1 = r3
        L_0x0094:
            java.lang.String r3 = "SisPolicy"
            java.lang.String r4 = "Get sis info error :"
            p004cn.jiguang.p029e.C0501d.m904a(r3, r4, r0)     // Catch:{ all -> 0x00b9 }
            if (r1 == 0) goto L_0x00a0
            r1.close()     // Catch:{ Throwable -> 0x00b3 }
        L_0x00a0:
            r0 = r2
            goto L_0x000a
        L_0x00a3:
            if (r3 == 0) goto L_0x00a0
            r3.close()     // Catch:{ Throwable -> 0x00a9 }
            goto L_0x00a0
        L_0x00a9:
            r0 = move-exception
            goto L_0x00a0
        L_0x00ab:
            r0 = move-exception
            r3 = r2
        L_0x00ad:
            if (r3 == 0) goto L_0x00b2
            r3.close()     // Catch:{ Throwable -> 0x00b5 }
        L_0x00b2:
            throw r0
        L_0x00b3:
            r0 = move-exception
            goto L_0x00a0
        L_0x00b5:
            r1 = move-exception
            goto L_0x00b2
        L_0x00b7:
            r0 = move-exception
            goto L_0x00ad
        L_0x00b9:
            r0 = move-exception
            r3 = r1
            goto L_0x00ad
        L_0x00bc:
            r0 = move-exception
            r1 = r2
            goto L_0x0094
        */
        throw new UnsupportedOperationException("Method not decompiled: p004cn.jiguang.p015d.p017b.p018a.p019a.C0406j.mo6406a(cn.jiguang.d.b.a.a):cn.jiguang.d.b.a.a");
    }

    /* renamed from: b */
    public final int mo6407b(C0396a aVar) {
        int a;
        if (aVar == null || aVar.mo6396a()) {
            return -1;
        }
        Iterator b = aVar.mo6398b();
        while (b.hasNext()) {
            if (this.f250a.mo6423e().mo6454d()) {
                return 2;
            }
            Entry entry = (Entry) b.next();
            C0410c cVar = (C0410c) entry.getKey();
            String str = (String) entry.getValue();
            if (!(!TextUtils.isEmpty(cVar.f253a) && cVar.f254b > 0)) {
                a = 1;
                continue;
            } else {
                String str2 = cVar.f253a;
                int i = cVar.f254b;
                if (this.f250a.mo6419b(cVar)) {
                    a = 1;
                    continue;
                } else {
                    C0501d.m903a("SisPolicy", "Open connection with " + str + " - ip:" + str2 + ", port:" + i);
                    long currentTimeMillis = System.currentTimeMillis();
                    C0419f e = this.f250a.mo6423e();
                    if (e.mo6454d()) {
                        a = -991;
                    } else {
                        a = C0477d.m767a().mo6616b().mo6608a(str2, i);
                        if (a != 0 && !e.mo6454d()) {
                            C0501d.m903a("ConnectingHelper", "Open connection failed - ret:" + a);
                        }
                    }
                    if (a != 0) {
                        this.f250a.mo6418b(str2, i, C0386a.m242a(currentTimeMillis), (System.currentTimeMillis() - currentTimeMillis) / 1000, a);
                        this.f250a.mo6417b(str2, i, a);
                        continue;
                    } else {
                        continue;
                    }
                }
            }
            if (a == 0) {
                C0386a.m248a(cVar.f253a, cVar.f254b);
                C0501d.m905b("SisPolicy", "Succeed to open connection - ip:" + cVar.f253a + ", port:" + cVar.f254b);
                return 0;
            }
        }
        return -1;
    }
}
