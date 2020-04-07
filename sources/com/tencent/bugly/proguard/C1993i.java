package com.tencent.bugly.proguard;

import com.google.common.base.Ascii;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/* renamed from: com.tencent.bugly.proguard.i */
/* compiled from: BUGLY */
public final class C1993i {

    /* renamed from: a */
    private ByteBuffer f1589a;

    /* renamed from: b */
    private String f1590b;

    public C1993i(int i) {
        this.f1590b = "GBK";
        this.f1589a = ByteBuffer.allocate(i);
    }

    public C1993i() {
        this(128);
    }

    /* renamed from: a */
    public final ByteBuffer mo19575a() {
        return this.f1589a;
    }

    /* renamed from: b */
    public final byte[] mo19587b() {
        byte[] bArr = new byte[this.f1589a.position()];
        System.arraycopy(this.f1589a.array(), 0, bArr, 0, this.f1589a.position());
        return bArr;
    }

    /* renamed from: a */
    private void m1989a(int i) {
        if (this.f1589a.remaining() < i) {
            ByteBuffer allocate = ByteBuffer.allocate((this.f1589a.capacity() + i) << 1);
            allocate.put(this.f1589a.array(), 0, this.f1589a.position());
            this.f1589a = allocate;
        }
    }

    /* renamed from: b */
    private void m1990b(byte b, int i) {
        if (i < 15) {
            this.f1589a.put((byte) ((i << 4) | b));
        } else if (i < 256) {
            this.f1589a.put((byte) (b | 240));
            this.f1589a.put((byte) i);
        } else {
            throw new C1985b("tag is too large: " + i);
        }
    }

    /* renamed from: a */
    public final void mo19585a(boolean z, int i) {
        mo19576a((byte) (z ? 1 : 0), i);
    }

    /* renamed from: a */
    public final void mo19576a(byte b, int i) {
        m1989a(3);
        if (b == 0) {
            m1990b(Ascii.f970FF, i);
            return;
        }
        m1990b(0, i);
        this.f1589a.put(b);
    }

    /* renamed from: a */
    public final void mo19584a(short s, int i) {
        m1989a(4);
        if (s < -128 || s > 127) {
            m1990b(1, i);
            this.f1589a.putShort(s);
            return;
        }
        mo19576a((byte) s, i);
    }

    /* renamed from: a */
    public final void mo19577a(int i, int i2) {
        m1989a(6);
        if (i < -32768 || i > 32767) {
            m1990b(2, i2);
            this.f1589a.putInt(i);
            return;
        }
        mo19584a((short) i, i2);
    }

    /* renamed from: a */
    public final void mo19578a(long j, int i) {
        m1989a(10);
        if (j < -2147483648L || j > 2147483647L) {
            m1990b(3, i);
            this.f1589a.putLong(j);
            return;
        }
        mo19577a((int) j, i);
    }

    /* renamed from: a */
    public final void mo19581a(String str, int i) {
        byte[] bytes;
        try {
            bytes = str.getBytes(this.f1590b);
        } catch (UnsupportedEncodingException e) {
            bytes = str.getBytes();
        }
        m1989a(bytes.length + 10);
        if (bytes.length > 255) {
            m1990b(7, i);
            this.f1589a.putInt(bytes.length);
            this.f1589a.put(bytes);
            return;
        }
        m1990b(6, i);
        this.f1589a.put((byte) bytes.length);
        this.f1589a.put(bytes);
    }

    /* renamed from: a */
    public final <K, V> void mo19583a(Map<K, V> map, int i) {
        m1989a(8);
        m1990b(8, i);
        mo19577a(map == null ? 0 : map.size(), 0);
        if (map != null) {
            for (Entry entry : map.entrySet()) {
                mo19580a(entry.getKey(), 0);
                mo19580a(entry.getValue(), 1);
            }
        }
    }

    /* renamed from: a */
    public final void mo19586a(byte[] bArr, int i) {
        m1989a(bArr.length + 8);
        m1990b(13, i);
        m1990b(0, 0);
        mo19577a(bArr.length, 0);
        this.f1589a.put(bArr);
    }

    /* renamed from: a */
    public final <T> void mo19582a(Collection<T> collection, int i) {
        m1989a(8);
        m1990b(9, i);
        mo19577a(collection == null ? 0 : collection.size(), 0);
        if (collection != null) {
            for (T a : collection) {
                mo19580a((Object) a, 0);
            }
        }
    }

    /* renamed from: a */
    public final void mo19579a(C1994j jVar, int i) {
        m1989a(2);
        m1990b(10, i);
        jVar.mo19550a(this);
        m1989a(2);
        m1990b(Ascii.f981VT, 0);
    }

    /* renamed from: a */
    public final void mo19580a(Object obj, int i) {
        int i2 = 1;
        if (obj instanceof Byte) {
            mo19576a(((Byte) obj).byteValue(), i);
        } else if (obj instanceof Boolean) {
            if (!((Boolean) obj).booleanValue()) {
                i2 = 0;
            }
            mo19576a((byte) i2, i);
        } else if (obj instanceof Short) {
            mo19584a(((Short) obj).shortValue(), i);
        } else if (obj instanceof Integer) {
            mo19577a(((Integer) obj).intValue(), i);
        } else if (obj instanceof Long) {
            mo19578a(((Long) obj).longValue(), i);
        } else if (obj instanceof Float) {
            float floatValue = ((Float) obj).floatValue();
            m1989a(6);
            m1990b(4, i);
            this.f1589a.putFloat(floatValue);
        } else if (obj instanceof Double) {
            double doubleValue = ((Double) obj).doubleValue();
            m1989a(10);
            m1990b(5, i);
            this.f1589a.putDouble(doubleValue);
        } else if (obj instanceof String) {
            mo19581a((String) obj, i);
        } else if (obj instanceof Map) {
            mo19583a((Map) obj, i);
        } else if (obj instanceof List) {
            mo19582a((Collection<T>) (List) obj, i);
        } else if (obj instanceof C1994j) {
            C1994j jVar = (C1994j) obj;
            m1989a(2);
            m1990b(10, i);
            jVar.mo19550a(this);
            m1989a(2);
            m1990b(Ascii.f981VT, 0);
        } else if (obj instanceof byte[]) {
            mo19586a((byte[]) obj, i);
        } else if (obj instanceof boolean[]) {
            boolean[] zArr = (boolean[]) obj;
            m1989a(8);
            m1990b(9, i);
            mo19577a(zArr.length, 0);
            int length = zArr.length;
            for (int i3 = 0; i3 < length; i3++) {
                mo19576a((byte) (zArr[i3] ? 1 : 0), 0);
            }
        } else if (obj instanceof short[]) {
            short[] sArr = (short[]) obj;
            m1989a(8);
            m1990b(9, i);
            mo19577a(sArr.length, 0);
            for (short a : sArr) {
                mo19584a(a, 0);
            }
        } else if (obj instanceof int[]) {
            int[] iArr = (int[]) obj;
            m1989a(8);
            m1990b(9, i);
            mo19577a(iArr.length, 0);
            for (int a2 : iArr) {
                mo19577a(a2, 0);
            }
        } else if (obj instanceof long[]) {
            long[] jArr = (long[]) obj;
            m1989a(8);
            m1990b(9, i);
            mo19577a(jArr.length, 0);
            for (long a3 : jArr) {
                mo19578a(a3, 0);
            }
        } else if (obj instanceof float[]) {
            float[] fArr = (float[]) obj;
            m1989a(8);
            m1990b(9, i);
            mo19577a(fArr.length, 0);
            for (float f : fArr) {
                m1989a(6);
                m1990b(4, 0);
                this.f1589a.putFloat(f);
            }
        } else if (obj instanceof double[]) {
            double[] dArr = (double[]) obj;
            m1989a(8);
            m1990b(9, i);
            mo19577a(dArr.length, 0);
            for (double d : dArr) {
                m1989a(10);
                m1990b(5, 0);
                this.f1589a.putDouble(d);
            }
        } else if (obj.getClass().isArray()) {
            Object[] objArr = (Object[]) obj;
            m1989a(8);
            m1990b(9, i);
            mo19577a(objArr.length, 0);
            for (Object a4 : objArr) {
                mo19580a(a4, 0);
            }
        } else if (obj instanceof Collection) {
            mo19582a((Collection) obj, i);
        } else {
            throw new C1985b("write object error: unsupport type. " + obj.getClass());
        }
    }

    /* renamed from: a */
    public final int mo19574a(String str) {
        this.f1590b = str;
        return 0;
    }
}
