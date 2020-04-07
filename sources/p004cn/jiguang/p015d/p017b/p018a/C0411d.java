package p004cn.jiguang.p015d.p017b.p018a;

import android.content.Context;
import android.text.TextUtils;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map.Entry;
import org.json.JSONArray;
import p004cn.jiguang.p005a.p006a.p008b.C0349e;
import p004cn.jiguang.p005a.p006a.p008b.C0350f;
import p004cn.jiguang.p015d.C0385a;
import p004cn.jiguang.p015d.p016a.C0386a;
import p004cn.jiguang.p015d.p016a.C0389d;
import p004cn.jiguang.p015d.p017b.C0419f;
import p004cn.jiguang.p015d.p020c.C0429f;
import p004cn.jiguang.p015d.p021d.C0446c;
import p004cn.jiguang.p015d.p026g.C0489j;
import p004cn.jiguang.p015d.p026g.p027a.C0480a;
import p004cn.jiguang.p031g.C0527h;
import p004cn.jiguang.p031g.C0532m;
import p004cn.jiguang.p031g.C0534o;

/* renamed from: cn.jiguang.d.b.a.d */
public final class C0411d {

    /* renamed from: a */
    private final Context f255a;

    /* renamed from: b */
    private final C0419f f256b;

    /* renamed from: c */
    private final long f257c;

    /* renamed from: d */
    private final C0396a f258d = new C0396a();

    /* renamed from: e */
    private final C0396a f259e = new C0396a();

    /* renamed from: f */
    private byte[] f260f;

    /* renamed from: g */
    private C0414g f261g;

    /* renamed from: h */
    private C0396a f262h;

    /* renamed from: i */
    private C0410c f263i;

    /* renamed from: j */
    private final LinkedList<C0413f> f264j;

    public C0411d(Context context, C0419f fVar, long j) {
        this.f255a = context;
        this.f256b = fVar;
        this.f257c = j;
        this.f264j = C0413f.m437a(C0386a.m288o());
    }

    /* renamed from: a */
    private int m415a(C0396a aVar, DatagramSocket datagramSocket, byte[] bArr) {
        if (aVar != null) {
            Iterator b = aVar.mo6398b();
            while (b.hasNext()) {
                C0410c cVar = (C0410c) ((Entry) b.next()).getKey();
                int a = m416a(cVar.f253a, cVar.f254b, datagramSocket, bArr);
                if (a == 0) {
                    return a;
                }
            }
        }
        return -1;
    }

    /* renamed from: a */
    private static int m416a(String str, int i, DatagramSocket datagramSocket, byte[] bArr) {
        try {
            InetAddress b = C0446c.m639b(str);
            if (b == null) {
                return -1;
            }
            byte[] bArr2 = m417a(C0446c.m638a(datagramSocket, new DatagramPacket(bArr, bArr.length, b, i))).f252b;
            if (bArr2 != null && bArr2.length != 0) {
                return bArr2.length == 1 ? (short) bArr2[0] : (short) (((short) (bArr2[1] & 255)) | ((short) ((bArr2[0] & 255) << 8)));
            }
            throw new Exception("byte could not be empty");
        } catch (Throwable th) {
            return -1;
        }
    }

    /* renamed from: a */
    public static C0409b m417a(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            throw new C0412e(3, "response is empty!");
        }
        try {
            C0527h hVar = new C0527h(bArr);
            hVar.mo6702a();
            String str = new String(hVar.mo6703a(2));
            long b = hVar.mo6704b();
            int i = (int) (b >>> 24);
            long j = b & 16777215;
            hVar.mo6702a();
            byte[] c = hVar.mo6705c();
            if (j != 0) {
                try {
                    c = C0480a.m785b(C0480a.m775a(j), c);
                    if (c == null) {
                        throw new C0412e(4, "decrypt response error");
                    }
                } catch (Exception e) {
                    throw new C0412e(4, "decrypt response error");
                }
            }
            if ((i & 1) == 1) {
                try {
                    c = C0489j.m821b(c);
                } catch (IOException e2) {
                }
            }
            return new C0409b(str, c);
        } catch (C0534o e3) {
            throw new C0412e(3, "parse head error:" + e3.getMessage());
        }
    }

    /* renamed from: a */
    private static byte[] m418a(String str, String str2) {
        byte[] bArr;
        byte[] bytes = str2.getBytes("UTF-8");
        boolean z = true;
        try {
            byte[] a = C0489j.m820a(bytes);
            if (a.length < bytes.length) {
                bArr = a;
            } else {
                z = false;
                bArr = bytes;
            }
        } catch (IOException e) {
            z = false;
            bArr = bytes;
        }
        int length = bArr.length;
        int b = C0480a.m782b();
        return C0446c.m637a(str, b, C0480a.m780a(C0480a.m775a((long) b), bArr), z, length);
    }

    /* renamed from: g */
    private C0414g m419g() {
        if (this.f261g == null) {
            String i = C0389d.m338i(this.f255a);
            long d = C0389d.m331d(this.f255a);
            int a = C0532m.m1107a(this.f255a);
            String b = C0532m.m1109b(this.f255a);
            C0349e a2 = C0350f.m75a(this.f255a);
            this.f261g = new C0414g(a, i, "1.2.0", d, b, a2.mo6217b(), a2.mo6218c(), a2.mo6219d());
        }
        return this.f261g;
    }

    /* renamed from: h */
    private void m420h() {
        while (this.f264j.size() > 5) {
            this.f264j.removeFirst();
        }
        JSONArray jSONArray = new JSONArray();
        Iterator it = this.f264j.iterator();
        while (it.hasNext()) {
            jSONArray.put(((C0413f) it.next()).mo6426a());
        }
        C0386a.m271e(jSONArray.toString());
    }

    /* renamed from: a */
    public final C0396a mo6411a() {
        if (this.f262h == null) {
            this.f262h = C0396a.m391a(C0386a.m287n());
        }
        return this.f262h;
    }

    /* renamed from: a */
    public final void mo6412a(C0396a aVar) {
        if (aVar != null && !aVar.equals(this.f262h)) {
            this.f262h = aVar;
            C0386a.m268d(this.f262h.toString());
        }
    }

    /* renamed from: a */
    public final void mo6413a(String str, int i, int i2) {
        this.f258d.mo6395a(str, i, String.valueOf(i2));
    }

    /* renamed from: a */
    public final void mo6414a(String str, int i, long j, long j2, int i2) {
        if (C0410c.m414a(str, i)) {
            C0413f fVar = new C0413f();
            fVar.f266a = 1;
            fVar.f267b = new C0410c(str, i);
            fVar.f269d = j;
            fVar.f270e = j2;
            fVar.f275j = i2;
            C0414g g = m419g();
            if (g != null) {
                fVar.f271f = g.mo6427a();
                fVar.f268c = g.mo6428b();
                fVar.f272g = g.mo6429c();
                fVar.f273h = g.mo6430d();
                fVar.f274i = g.mo6431e();
            }
            this.f264j.add(fVar);
            m420h();
        }
    }

    /* renamed from: a */
    public final boolean mo6415a(C0410c cVar) {
        return this.f258d.mo6397a(cVar);
    }

    /* renamed from: b */
    public final C0396a mo6416b() {
        this.f263i = C0410c.m413a(C0386a.m289p());
        C0396a aVar = new C0396a();
        for (Entry entry : C0385a.f199f.mo6346b().entrySet()) {
            aVar.mo6395a((String) entry.getKey(), ((Integer) entry.getValue()).intValue(), "hardcode");
        }
        if (this.f263i != null) {
            aVar.mo6395a(this.f263i.f253a, this.f263i.f254b, "last_good");
        }
        return aVar;
    }

    /* renamed from: b */
    public final void mo6417b(String str, int i, int i2) {
        this.f259e.mo6395a(str, i, String.valueOf(i2));
    }

    /* renamed from: b */
    public final void mo6418b(String str, int i, long j, long j2, int i2) {
        if (C0410c.m414a(str, i)) {
            C0413f fVar = new C0413f();
            fVar.f266a = 2;
            fVar.f267b = new C0410c(str, i);
            fVar.f269d = j;
            fVar.f270e = j2;
            fVar.f275j = i2;
            fVar.f271f = C0532m.m1107a(this.f255a);
            fVar.f268c = C0389d.m331d(this.f255a);
            C0349e a = C0350f.m75a(this.f255a);
            if (a != null && a.mo6216a()) {
                fVar.f272g = a.mo6217b();
                fVar.f273h = a.mo6218c();
                fVar.f274i = a.mo6219d();
            }
            this.f264j.add(fVar);
            m420h();
        }
    }

    /* renamed from: b */
    public final boolean mo6419b(C0410c cVar) {
        return this.f259e.mo6397a(cVar);
    }

    /* renamed from: c */
    public final void mo6420c(C0410c cVar) {
        if (!cVar.equals(this.f263i)) {
            this.f263i = cVar;
            C0386a.m273f(this.f263i.toString());
        }
    }

    /* renamed from: c */
    public final byte[] mo6421c() {
        if (this.f260f == null) {
            m419g();
            try {
                this.f260f = m418a("UG", this.f261g.mo6432f().toString());
            } catch (Exception e) {
                throw new C0412e(1, "Failed to package data - " + e.getMessage());
            }
        }
        return this.f260f;
    }

    /* renamed from: d */
    public final Context mo6422d() {
        return this.f255a;
    }

    /* renamed from: e */
    public final C0419f mo6423e() {
        return this.f256b;
    }

    /* renamed from: f */
    public final void mo6424f() {
        try {
            if (C0386a.m290q()) {
                DatagramSocket datagramSocket = new DatagramSocket();
                String o = C0386a.m288o();
                if (!TextUtils.isEmpty(o)) {
                    byte[] a = m418a("DG", o);
                    int a2 = m415a(mo6416b(), datagramSocket, a);
                    if (a2 != 0) {
                        a2 = m415a(C0396a.m392a(C0429f.m540a(C0385a.f199f.mo6349e()), false), datagramSocket, a);
                    }
                    if (a2 == 0) {
                        C0386a.m271e((String) null);
                    }
                }
            }
        } catch (Throwable th) {
        }
    }
}
