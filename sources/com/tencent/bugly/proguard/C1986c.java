package com.tencent.bugly.proguard;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/* renamed from: com.tencent.bugly.proguard.c */
/* compiled from: BUGLY */
public class C1986c extends C1967a {

    /* renamed from: d */
    protected HashMap<String, byte[]> f1565d = null;

    /* renamed from: e */
    private HashMap<String, Object> f1566e = new HashMap<>();

    /* renamed from: f */
    private C1991h f1567f = new C1991h();

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ void mo19540a(String str) {
        super.mo19540a(str);
    }

    /* renamed from: b */
    public void mo19556b() {
        this.f1565d = new HashMap<>();
    }

    /* renamed from: a */
    public <T> void mo19541a(String str, T t) {
        if (this.f1565d == null) {
            super.mo19541a(str, t);
        } else if (str == null) {
            throw new IllegalArgumentException("put key can not is null");
        } else if (t == null) {
            throw new IllegalArgumentException("put value can not is null");
        } else if (t instanceof Set) {
            throw new IllegalArgumentException("can not support Set");
        } else {
            C1993i iVar = new C1993i();
            iVar.mo19574a(this.f1448b);
            iVar.mo19580a((Object) t, 0);
            this.f1565d.put(str, C1995k.m2012a(iVar.mo19575a()));
        }
    }

    /* renamed from: b */
    public final <T> T mo19555b(String str, T t) throws C1985b {
        byte[] bArr;
        if (this.f1565d != null) {
            if (!this.f1565d.containsKey(str)) {
                return null;
            }
            if (this.f1566e.containsKey(str)) {
                return this.f1566e.get(str);
            }
            try {
                this.f1567f.mo19570a((byte[]) this.f1565d.get(str));
                this.f1567f.mo19564a(this.f1448b);
                T a = this.f1567f.mo19567a(t, 0, true);
                if (a == null) {
                    return a;
                }
                this.f1566e.put(str, a);
                return a;
            } catch (Exception e) {
                throw new C1985b(e);
            }
        } else if (!this.f1447a.containsKey(str)) {
            return null;
        } else {
            if (this.f1566e.containsKey(str)) {
                return this.f1566e.get(str);
            }
            byte[] bArr2 = new byte[0];
            Iterator it = ((HashMap) this.f1447a.get(str)).entrySet().iterator();
            if (it.hasNext()) {
                Entry entry = (Entry) it.next();
                entry.getKey();
                bArr = (byte[]) entry.getValue();
            } else {
                bArr = bArr2;
            }
            try {
                this.f1567f.mo19570a(bArr);
                this.f1567f.mo19564a(this.f1448b);
                T a2 = this.f1567f.mo19567a(t, 0, true);
                this.f1566e.put(str, a2);
                return a2;
            } catch (Exception e2) {
                throw new C1985b(e2);
            }
        }
    }

    /* renamed from: a */
    public byte[] mo19543a() {
        if (this.f1565d == null) {
            return super.mo19543a();
        }
        C1993i iVar = new C1993i(0);
        iVar.mo19574a(this.f1448b);
        iVar.mo19583a((Map<K, V>) this.f1565d, 0);
        return C1995k.m2012a(iVar.mo19575a());
    }

    /* renamed from: a */
    public void mo19542a(byte[] bArr) {
        try {
            super.mo19542a(bArr);
        } catch (Exception e) {
            this.f1567f.mo19570a(bArr);
            this.f1567f.mo19564a(this.f1448b);
            HashMap hashMap = new HashMap(1);
            hashMap.put("", new byte[0]);
            this.f1565d = this.f1567f.mo19568a((Map<K, V>) hashMap, 0, false);
        }
    }
}
