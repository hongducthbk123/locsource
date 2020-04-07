package com.tencent.bugly.proguard;

import com.google.common.base.Ascii;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/* renamed from: com.tencent.bugly.proguard.h */
/* compiled from: BUGLY */
public final class C1991h {

    /* renamed from: a */
    private ByteBuffer f1585a;

    /* renamed from: b */
    private String f1586b = "GBK";

    /* renamed from: com.tencent.bugly.proguard.h$a */
    /* compiled from: BUGLY */
    public static class C1992a {

        /* renamed from: a */
        public byte f1587a;

        /* renamed from: b */
        public int f1588b;
    }

    public C1991h() {
    }

    public C1991h(byte[] bArr) {
        this.f1585a = ByteBuffer.wrap(bArr);
    }

    public C1991h(byte[] bArr, int i) {
        this.f1585a = ByteBuffer.wrap(bArr);
        this.f1585a.position(4);
    }

    /* renamed from: a */
    public final void mo19570a(byte[] bArr) {
        if (this.f1585a != null) {
            this.f1585a.clear();
        }
        this.f1585a = ByteBuffer.wrap(bArr);
    }

    /* renamed from: a */
    private static int m1964a(C1992a aVar, ByteBuffer byteBuffer) {
        byte b = byteBuffer.get();
        aVar.f1587a = (byte) (b & Ascii.f977SI);
        aVar.f1588b = (b & 240) >> 4;
        if (aVar.f1588b != 15) {
            return 1;
        }
        aVar.f1588b = byteBuffer.get();
        return 2;
    }

    /* renamed from: a */
    private boolean m1968a(int i) {
        try {
            C1992a aVar = new C1992a();
            while (true) {
                int a = m1964a(aVar, this.f1585a.duplicate());
                if (i > aVar.f1588b && aVar.f1587a != 11) {
                    this.f1585a.position(a + this.f1585a.position());
                    m1967a(aVar.f1587a);
                }
            }
            if (i == aVar.f1588b) {
                return true;
            }
            return false;
        } catch (C1990g | BufferUnderflowException e) {
            return false;
        }
    }

    /* renamed from: a */
    private void m1966a() {
        C1992a aVar = new C1992a();
        do {
            m1964a(aVar, this.f1585a);
            m1967a(aVar.f1587a);
        } while (aVar.f1587a != 11);
    }

    /* renamed from: a */
    private void m1967a(byte b) {
        int i = 0;
        switch (b) {
            case 0:
                this.f1585a.position(this.f1585a.position() + 1);
                return;
            case 1:
                this.f1585a.position(2 + this.f1585a.position());
                return;
            case 2:
                this.f1585a.position(this.f1585a.position() + 4);
                return;
            case 3:
                this.f1585a.position(this.f1585a.position() + 8);
                return;
            case 4:
                this.f1585a.position(this.f1585a.position() + 4);
                return;
            case 5:
                this.f1585a.position(this.f1585a.position() + 8);
                return;
            case 6:
                int i2 = this.f1585a.get();
                if (i2 < 0) {
                    i2 += 256;
                }
                this.f1585a.position(i2 + this.f1585a.position());
                return;
            case 7:
                this.f1585a.position(this.f1585a.getInt() + this.f1585a.position());
                return;
            case 8:
                int a = mo19563a(0, 0, true);
                while (i < (a << 1)) {
                    C1992a aVar = new C1992a();
                    m1964a(aVar, this.f1585a);
                    m1967a(aVar.f1587a);
                    i++;
                }
                return;
            case 9:
                int a2 = mo19563a(0, 0, true);
                while (i < a2) {
                    C1992a aVar2 = new C1992a();
                    m1964a(aVar2, this.f1585a);
                    m1967a(aVar2.f1587a);
                    i++;
                }
                return;
            case 10:
                m1966a();
                return;
            case 11:
            case 12:
                return;
            case 13:
                C1992a aVar3 = new C1992a();
                m1964a(aVar3, this.f1585a);
                if (aVar3.f1587a != 0) {
                    throw new C1990g("skipField with invalid type, type value: " + b + ", " + aVar3.f1587a);
                }
                this.f1585a.position(mo19563a(0, 0, true) + this.f1585a.position());
                return;
            default:
                throw new C1990g("invalid type.");
        }
    }

    /* renamed from: a */
    public final boolean mo19571a(int i, boolean z) {
        if (mo19562a(0, i, z) != 0) {
            return true;
        }
        return false;
    }

    /* renamed from: a */
    public final byte mo19562a(byte b, int i, boolean z) {
        if (m1968a(i)) {
            C1992a aVar = new C1992a();
            m1964a(aVar, this.f1585a);
            switch (aVar.f1587a) {
                case 0:
                    return this.f1585a.get();
                case 12:
                    return 0;
                default:
                    throw new C1990g("type mismatch.");
            }
        } else if (!z) {
            return b;
        } else {
            throw new C1990g("require field not exist.");
        }
    }

    /* renamed from: a */
    public final short mo19569a(short s, int i, boolean z) {
        if (m1968a(i)) {
            C1992a aVar = new C1992a();
            m1964a(aVar, this.f1585a);
            switch (aVar.f1587a) {
                case 0:
                    return (short) this.f1585a.get();
                case 1:
                    return this.f1585a.getShort();
                case 12:
                    return 0;
                default:
                    throw new C1990g("type mismatch.");
            }
        } else if (!z) {
            return s;
        } else {
            throw new C1990g("require field not exist.");
        }
    }

    /* renamed from: a */
    public final int mo19563a(int i, int i2, boolean z) {
        if (m1968a(i2)) {
            C1992a aVar = new C1992a();
            m1964a(aVar, this.f1585a);
            switch (aVar.f1587a) {
                case 0:
                    return this.f1585a.get();
                case 1:
                    return this.f1585a.getShort();
                case 2:
                    return this.f1585a.getInt();
                case 12:
                    return 0;
                default:
                    throw new C1990g("type mismatch.");
            }
        } else if (!z) {
            return i;
        } else {
            throw new C1990g("require field not exist.");
        }
    }

    /* renamed from: a */
    public final long mo19565a(long j, int i, boolean z) {
        if (m1968a(i)) {
            C1992a aVar = new C1992a();
            m1964a(aVar, this.f1585a);
            switch (aVar.f1587a) {
                case 0:
                    return (long) this.f1585a.get();
                case 1:
                    return (long) this.f1585a.getShort();
                case 2:
                    return (long) this.f1585a.getInt();
                case 3:
                    return this.f1585a.getLong();
                case 12:
                    return 0;
                default:
                    throw new C1990g("type mismatch.");
            }
        } else if (!z) {
            return j;
        } else {
            throw new C1990g("require field not exist.");
        }
    }

    /* renamed from: a */
    private float m1963a(float f, int i, boolean z) {
        if (m1968a(i)) {
            C1992a aVar = new C1992a();
            m1964a(aVar, this.f1585a);
            switch (aVar.f1587a) {
                case 4:
                    return this.f1585a.getFloat();
                case 12:
                    return 0.0f;
                default:
                    throw new C1990g("type mismatch.");
            }
        } else if (!z) {
            return f;
        } else {
            throw new C1990g("require field not exist.");
        }
    }

    /* renamed from: a */
    private double m1962a(double d, int i, boolean z) {
        if (m1968a(i)) {
            C1992a aVar = new C1992a();
            m1964a(aVar, this.f1585a);
            switch (aVar.f1587a) {
                case 4:
                    return (double) this.f1585a.getFloat();
                case 5:
                    return this.f1585a.getDouble();
                case 12:
                    return 0.0d;
                default:
                    throw new C1990g("type mismatch.");
            }
        } else if (!z) {
            return d;
        } else {
            throw new C1990g("require field not exist.");
        }
    }

    /* renamed from: b */
    public final String mo19572b(int i, boolean z) {
        if (m1968a(i)) {
            C1992a aVar = new C1992a();
            m1964a(aVar, this.f1585a);
            switch (aVar.f1587a) {
                case 6:
                    int i2 = this.f1585a.get();
                    if (i2 < 0) {
                        i2 += 256;
                    }
                    byte[] bArr = new byte[i2];
                    this.f1585a.get(bArr);
                    try {
                        return new String(bArr, this.f1586b);
                    } catch (UnsupportedEncodingException e) {
                        return new String(bArr);
                    }
                case 7:
                    int i3 = this.f1585a.getInt();
                    if (i3 > 104857600 || i3 < 0) {
                        throw new C1990g("String too long: " + i3);
                    }
                    byte[] bArr2 = new byte[i3];
                    this.f1585a.get(bArr2);
                    try {
                        return new String(bArr2, this.f1586b);
                    } catch (UnsupportedEncodingException e2) {
                        return new String(bArr2);
                    }
                default:
                    throw new C1990g("type mismatch.");
            }
        } else if (!z) {
            return null;
        } else {
            throw new C1990g("require field not exist.");
        }
    }

    /* renamed from: a */
    public final <K, V> HashMap<K, V> mo19568a(Map<K, V> map, int i, boolean z) {
        return (HashMap) m1965a(new HashMap(), map, i, z);
    }

    /* renamed from: a */
    private <K, V> Map<K, V> m1965a(Map<K, V> map, Map<K, V> map2, int i, boolean z) {
        if (map2 == null || map2.isEmpty()) {
            return new HashMap();
        }
        Entry entry = (Entry) map2.entrySet().iterator().next();
        Object key = entry.getKey();
        Object value = entry.getValue();
        if (m1968a(i)) {
            C1992a aVar = new C1992a();
            m1964a(aVar, this.f1585a);
            switch (aVar.f1587a) {
                case 8:
                    int a = mo19563a(0, 0, true);
                    if (a < 0) {
                        throw new C1990g("size invalid: " + a);
                    }
                    for (int i2 = 0; i2 < a; i2++) {
                        map.put(mo19567a((T) key, 0, true), mo19567a((T) value, 1, true));
                    }
                    return map;
                default:
                    throw new C1990g("type mismatch.");
            }
        } else if (!z) {
            return map;
        } else {
            throw new C1990g("require field not exist.");
        }
    }

    /* renamed from: d */
    private boolean[] m1971d(int i, boolean z) {
        boolean z2;
        if (m1968a(i)) {
            C1992a aVar = new C1992a();
            m1964a(aVar, this.f1585a);
            switch (aVar.f1587a) {
                case 9:
                    int a = mo19563a(0, 0, true);
                    if (a < 0) {
                        throw new C1990g("size invalid: " + a);
                    }
                    boolean[] zArr = new boolean[a];
                    for (int i2 = 0; i2 < a; i2++) {
                        if (mo19562a(0, 0, true) != 0) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        zArr[i2] = z2;
                    }
                    return zArr;
                default:
                    throw new C1990g("type mismatch.");
            }
        } else if (!z) {
            return null;
        } else {
            throw new C1990g("require field not exist.");
        }
    }

    /* renamed from: c */
    public final byte[] mo19573c(int i, boolean z) {
        if (m1968a(i)) {
            C1992a aVar = new C1992a();
            m1964a(aVar, this.f1585a);
            switch (aVar.f1587a) {
                case 9:
                    int a = mo19563a(0, 0, true);
                    if (a < 0) {
                        throw new C1990g("size invalid: " + a);
                    }
                    byte[] bArr = new byte[a];
                    for (int i2 = 0; i2 < a; i2++) {
                        bArr[i2] = mo19562a(bArr[0], 0, true);
                    }
                    return bArr;
                case 13:
                    C1992a aVar2 = new C1992a();
                    m1964a(aVar2, this.f1585a);
                    if (aVar2.f1587a != 0) {
                        throw new C1990g("type mismatch, tag: " + i + ", type: " + aVar.f1587a + ", " + aVar2.f1587a);
                    }
                    int a2 = mo19563a(0, 0, true);
                    if (a2 < 0) {
                        throw new C1990g("invalid size, tag: " + i + ", type: " + aVar.f1587a + ", " + aVar2.f1587a + ", size: " + a2);
                    }
                    byte[] bArr2 = new byte[a2];
                    this.f1585a.get(bArr2);
                    return bArr2;
                default:
                    throw new C1990g("type mismatch.");
            }
        } else if (!z) {
            return null;
        } else {
            throw new C1990g("require field not exist.");
        }
    }

    /* renamed from: e */
    private short[] m1972e(int i, boolean z) {
        short[] sArr = null;
        if (m1968a(i)) {
            C1992a aVar = new C1992a();
            m1964a(aVar, this.f1585a);
            switch (aVar.f1587a) {
                case 9:
                    int a = mo19563a(0, 0, true);
                    if (a >= 0) {
                        sArr = new short[a];
                        for (int i2 = 0; i2 < a; i2++) {
                            sArr[i2] = mo19569a(sArr[0], 0, true);
                        }
                        break;
                    } else {
                        throw new C1990g("size invalid: " + a);
                    }
                default:
                    throw new C1990g("type mismatch.");
            }
        } else if (z) {
            throw new C1990g("require field not exist.");
        }
        return sArr;
    }

    /* renamed from: f */
    private int[] m1973f(int i, boolean z) {
        int[] iArr = null;
        if (m1968a(i)) {
            C1992a aVar = new C1992a();
            m1964a(aVar, this.f1585a);
            switch (aVar.f1587a) {
                case 9:
                    int a = mo19563a(0, 0, true);
                    if (a >= 0) {
                        iArr = new int[a];
                        for (int i2 = 0; i2 < a; i2++) {
                            iArr[i2] = mo19563a(iArr[0], 0, true);
                        }
                        break;
                    } else {
                        throw new C1990g("size invalid: " + a);
                    }
                default:
                    throw new C1990g("type mismatch.");
            }
        } else if (z) {
            throw new C1990g("require field not exist.");
        }
        return iArr;
    }

    /* renamed from: g */
    private long[] m1974g(int i, boolean z) {
        long[] jArr = null;
        if (m1968a(i)) {
            C1992a aVar = new C1992a();
            m1964a(aVar, this.f1585a);
            switch (aVar.f1587a) {
                case 9:
                    int a = mo19563a(0, 0, true);
                    if (a >= 0) {
                        jArr = new long[a];
                        for (int i2 = 0; i2 < a; i2++) {
                            jArr[i2] = mo19565a(jArr[0], 0, true);
                        }
                        break;
                    } else {
                        throw new C1990g("size invalid: " + a);
                    }
                default:
                    throw new C1990g("type mismatch.");
            }
        } else if (z) {
            throw new C1990g("require field not exist.");
        }
        return jArr;
    }

    /* renamed from: h */
    private float[] m1975h(int i, boolean z) {
        float[] fArr = null;
        if (m1968a(i)) {
            C1992a aVar = new C1992a();
            m1964a(aVar, this.f1585a);
            switch (aVar.f1587a) {
                case 9:
                    int a = mo19563a(0, 0, true);
                    if (a >= 0) {
                        fArr = new float[a];
                        for (int i2 = 0; i2 < a; i2++) {
                            fArr[i2] = m1963a(fArr[0], 0, true);
                        }
                        break;
                    } else {
                        throw new C1990g("size invalid: " + a);
                    }
                default:
                    throw new C1990g("type mismatch.");
            }
        } else if (z) {
            throw new C1990g("require field not exist.");
        }
        return fArr;
    }

    /* renamed from: i */
    private double[] m1976i(int i, boolean z) {
        double[] dArr = null;
        if (m1968a(i)) {
            C1992a aVar = new C1992a();
            m1964a(aVar, this.f1585a);
            switch (aVar.f1587a) {
                case 9:
                    int a = mo19563a(0, 0, true);
                    if (a >= 0) {
                        dArr = new double[a];
                        for (int i2 = 0; i2 < a; i2++) {
                            dArr[i2] = m1962a(dArr[0], 0, true);
                        }
                        break;
                    } else {
                        throw new C1990g("size invalid: " + a);
                    }
                default:
                    throw new C1990g("type mismatch.");
            }
        } else if (z) {
            throw new C1990g("require field not exist.");
        }
        return dArr;
    }

    /* renamed from: a */
    private <T> T[] m1969a(T[] tArr, int i, boolean z) {
        if (tArr != null && tArr.length != 0) {
            return m1970b(tArr[0], i, z);
        }
        throw new C1990g("unable to get type of key and value.");
    }

    /* renamed from: b */
    private <T> T[] m1970b(T t, int i, boolean z) {
        if (m1968a(i)) {
            C1992a aVar = new C1992a();
            m1964a(aVar, this.f1585a);
            switch (aVar.f1587a) {
                case 9:
                    int a = mo19563a(0, 0, true);
                    if (a < 0) {
                        throw new C1990g("size invalid: " + a);
                    }
                    T[] tArr = (Object[]) Array.newInstance(t.getClass(), a);
                    for (int i2 = 0; i2 < a; i2++) {
                        tArr[i2] = mo19567a(t, 0, true);
                    }
                    return tArr;
                default:
                    throw new C1990g("type mismatch.");
            }
        } else if (!z) {
            return null;
        } else {
            throw new C1990g("require field not exist.");
        }
    }

    /* renamed from: a */
    public final C1994j mo19566a(C1994j jVar, int i, boolean z) {
        C1994j jVar2 = null;
        if (m1968a(i)) {
            try {
                jVar2 = (C1994j) jVar.getClass().newInstance();
                C1992a aVar = new C1992a();
                m1964a(aVar, this.f1585a);
                if (aVar.f1587a != 10) {
                    throw new C1990g("type mismatch.");
                }
                jVar2.mo19549a(this);
                m1966a();
            } catch (Exception e) {
                throw new C1990g(e.getMessage());
            }
        } else if (z) {
            throw new C1990g("require field not exist.");
        }
        return jVar2;
    }

    /* renamed from: a */
    public final <T> Object mo19567a(T t, int i, boolean z) {
        boolean z2 = 0;
        if (t instanceof Byte) {
            return Byte.valueOf(mo19562a(0, i, z));
        }
        if (t instanceof Boolean) {
            if (mo19562a(0, i, z) != 0) {
                z2 = 1;
            }
            return Boolean.valueOf(z2);
        } else if (t instanceof Short) {
            return Short.valueOf(mo19569a(0, i, z));
        } else {
            if (t instanceof Integer) {
                return Integer.valueOf(mo19563a(0, i, z));
            }
            if (t instanceof Long) {
                return Long.valueOf(mo19565a(0, i, z));
            }
            if (t instanceof Float) {
                return Float.valueOf(m1963a(0.0f, i, z));
            }
            if (t instanceof Double) {
                return Double.valueOf(m1962a(0.0d, i, z));
            }
            if (t instanceof String) {
                return String.valueOf(mo19572b(i, z));
            }
            if (t instanceof Map) {
                return (HashMap) m1965a(new HashMap(), (Map) t, i, z);
            } else if (t instanceof List) {
                List list = (List) t;
                if (list == null || list.isEmpty()) {
                    return new ArrayList();
                }
                Object[] b = m1970b(list.get(0), i, z);
                if (b == null) {
                    return null;
                }
                ArrayList arrayList = new ArrayList();
                for (int i2 = z2; i2 < b.length; i2++) {
                    arrayList.add(b[i2]);
                }
                return arrayList;
            } else if (t instanceof C1994j) {
                return mo19566a((C1994j) t, i, z);
            } else {
                if (!t.getClass().isArray()) {
                    throw new C1990g("read object error: unsupport type.");
                } else if ((t instanceof byte[]) || (t instanceof Byte[])) {
                    return mo19573c(i, z);
                } else {
                    if (t instanceof boolean[]) {
                        return m1971d(i, z);
                    }
                    if (t instanceof short[]) {
                        return m1972e(i, z);
                    }
                    if (t instanceof int[]) {
                        return m1973f(i, z);
                    }
                    if (t instanceof long[]) {
                        return m1974g(i, z);
                    }
                    if (t instanceof float[]) {
                        return m1975h(i, z);
                    }
                    if (t instanceof double[]) {
                        return m1976i(i, z);
                    }
                    return m1969a((T[]) (Object[]) t, i, z);
                }
            }
        }
    }

    /* renamed from: a */
    public final int mo19564a(String str) {
        this.f1586b = str;
        return 0;
    }
}
