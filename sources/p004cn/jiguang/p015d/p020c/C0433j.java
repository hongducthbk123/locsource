package p004cn.jiguang.p015d.p020c;

import com.google.android.vending.expansion.downloader.impl.DownloaderService;
import java.io.Serializable;
import java.text.DecimalFormat;
import org.apache.commons.p052io.FilenameUtils;
import org.apache.commons.p052io.IOUtils;

/* renamed from: cn.jiguang.d.c.j */
public final class C0433j implements Serializable, Comparable {

    /* renamed from: a */
    public static final C0433j f363a;

    /* renamed from: b */
    public static final C0433j f364b;

    /* renamed from: f */
    private static final byte[] f365f = {0};

    /* renamed from: g */
    private static final byte[] f366g = {1, 42};

    /* renamed from: h */
    private static final DecimalFormat f367h = new DecimalFormat();

    /* renamed from: i */
    private static final byte[] f368i = new byte[256];

    /* renamed from: j */
    private static final C0433j f369j;

    /* renamed from: c */
    private byte[] f370c;

    /* renamed from: d */
    private long f371d;

    /* renamed from: e */
    private int f372e;

    static {
        f367h.setMinimumIntegerDigits(3);
        for (int i = 0; i < f368i.length; i++) {
            if (i < 65 || i > 90) {
                f368i[i] = (byte) i;
            } else {
                f368i[i] = (byte) ((i - 65) + 97);
            }
        }
        C0433j jVar = new C0433j();
        f363a = jVar;
        jVar.m568b(f365f, 0, 1);
        C0433j jVar2 = new C0433j();
        f364b = jVar2;
        jVar2.f370c = new byte[0];
        C0433j jVar3 = new C0433j();
        f369j = jVar3;
        jVar3.m568b(f366g, 0, 1);
    }

    private C0433j() {
    }

    public C0433j(C0427d dVar) {
        byte[] bArr = new byte[64];
        boolean z = false;
        boolean z2 = false;
        while (!z2) {
            int f = dVar.mo6492f();
            switch (f & DownloaderService.STATUS_RUNNING) {
                case 0:
                    if (m569c() < 128) {
                        if (f != 0) {
                            bArr[0] = (byte) f;
                            dVar.mo6486a(bArr, 1, f);
                            m565a(bArr, 0, 1);
                            break;
                        } else {
                            m565a(f365f, 0, 1);
                            z2 = true;
                            break;
                        }
                    } else {
                        throw new C0443t("too many labels");
                    }
                case DownloaderService.STATUS_RUNNING /*192*/:
                    int f2 = ((f & -193) << 8) + dVar.mo6492f();
                    if (f2 < dVar.mo6484a() - 2) {
                        if (!z) {
                            dVar.mo6490d();
                            z = true;
                        }
                        dVar.mo6488b(f2);
                        break;
                    } else {
                        throw new C0443t("bad compression");
                    }
                default:
                    throw new C0443t("bad label type");
            }
        }
        if (z) {
            dVar.mo6491e();
        }
    }

    private C0433j(C0433j jVar, int i) {
        int c = jVar.m569c();
        if (i > c) {
            throw new IllegalArgumentException("attempted to remove too many labels");
        }
        this.f370c = jVar.f370c;
        m566b(c - i);
        int i2 = 0;
        while (i2 < 7 && i2 < c - i) {
            m563a(i2, jVar.m557a(i2 + i));
            i2++;
        }
    }

    private C0433j(String str, C0433j jVar) {
        boolean z;
        byte b;
        if (str.equals("")) {
            throw m561a(str, "empty name");
        } else if (str.equals("@")) {
            if (jVar == null) {
                m567b(f364b, this);
            } else {
                m567b(jVar, this);
            }
        } else if (str.equals(".")) {
            m567b(f363a, this);
        } else {
            int i = -1;
            int i2 = 1;
            byte[] bArr = new byte[64];
            boolean z2 = false;
            int i3 = 0;
            int i4 = 0;
            for (int i5 = 0; i5 < str.length(); i5++) {
                byte charAt = (byte) str.charAt(i5);
                if (z2) {
                    if (charAt >= 48 && charAt <= 57 && i3 < 3) {
                        i3++;
                        i4 = (i4 * 10) + (charAt - 48);
                        if (i4 > 255) {
                            throw m561a(str, "bad escape");
                        } else if (i3 >= 3) {
                            b = (byte) i4;
                        } else {
                            continue;
                        }
                    } else if (i3 <= 0 || i3 >= 3) {
                        b = charAt;
                    } else {
                        throw m561a(str, "bad escape");
                    }
                    if (i2 > 63) {
                        throw m561a(str, "label too long");
                    }
                    int i6 = i2 + 1;
                    bArr[i2] = b;
                    z2 = false;
                    int i7 = i6;
                    i = i2;
                    i2 = i7;
                } else if (charAt == 92) {
                    z2 = true;
                    i3 = 0;
                    i4 = 0;
                } else if (charAt != 46) {
                    int i8 = i == -1 ? i5 : i;
                    if (i2 > 63) {
                        throw m561a(str, "label too long");
                    }
                    int i9 = i2 + 1;
                    bArr[i2] = charAt;
                    i2 = i9;
                    i = i8;
                } else if (i == -1) {
                    throw m561a(str, "invalid empty label");
                } else {
                    bArr[0] = (byte) (i2 - 1);
                    m564a(str, bArr, 0, 1);
                    i = -1;
                    i2 = 1;
                }
            }
            if (i3 > 0 && i3 < 3) {
                throw m561a(str, "bad escape");
            } else if (z2) {
                throw m561a(str, "bad escape");
            } else {
                if (i == -1) {
                    m564a(str, f365f, 0, 1);
                    z = true;
                } else {
                    bArr[0] = (byte) (i2 - 1);
                    m564a(str, bArr, 0, 1);
                    z = false;
                }
                if (jVar != null && !z) {
                    m564a(str, jVar.f370c, jVar.m557a(0), jVar.m569c());
                }
            }
        }
    }

    /* renamed from: a */
    private final int m557a(int i) {
        if (i == 0 && m569c() == 0) {
            return 0;
        }
        if (i < 0 || i >= m569c()) {
            throw new IllegalArgumentException("label out of range");
        } else if (i < 7) {
            return ((int) (this.f371d >>> ((7 - i) * 8))) & 255;
        } else {
            int a = m557a(6);
            int i2 = 6;
            while (i2 < i) {
                i2++;
                a = this.f370c[a] + 1 + a;
            }
            return a;
        }
    }

    /* renamed from: a */
    public static C0433j m558a(C0433j jVar, C0433j jVar2) {
        if (jVar.mo6521a()) {
            return jVar;
        }
        C0433j jVar3 = new C0433j();
        m567b(jVar, jVar3);
        jVar3.m565a(jVar2.f370c, jVar2.m557a(0), jVar2.m569c());
        return jVar3;
    }

    /* renamed from: a */
    public static C0433j m559a(String str) {
        return m560a(str, (C0433j) null);
    }

    /* renamed from: a */
    public static C0433j m560a(String str, C0433j jVar) {
        return (!str.equals("@") || jVar == null) ? str.equals(".") ? f363a : new C0433j(str, jVar) : jVar;
    }

    /* renamed from: a */
    private static C0442s m561a(String str, String str2) {
        return new C0442s("'" + str + "': " + str2);
    }

    /* renamed from: a */
    private static String m562a(byte[] bArr, int i) {
        StringBuffer stringBuffer = new StringBuffer();
        int i2 = i + 1;
        byte b = bArr[i];
        for (int i3 = i2; i3 < i2 + b; i3++) {
            byte b2 = bArr[i3] & 255;
            if (b2 <= 32 || b2 >= Byte.MAX_VALUE) {
                stringBuffer.append(IOUtils.DIR_SEPARATOR_WINDOWS);
                stringBuffer.append(f367h.format((long) b2));
            } else if (b2 == 34 || b2 == 40 || b2 == 41 || b2 == 46 || b2 == 59 || b2 == 92 || b2 == 64 || b2 == 36) {
                stringBuffer.append(IOUtils.DIR_SEPARATOR_WINDOWS);
                stringBuffer.append((char) b2);
            } else {
                stringBuffer.append((char) b2);
            }
        }
        return stringBuffer.toString();
    }

    /* renamed from: a */
    private final void m563a(int i, int i2) {
        if (i < 7) {
            int i3 = (7 - i) * 8;
            this.f371d &= (255 << i3) ^ -1;
            this.f371d = (((long) i2) << i3) | this.f371d;
        }
    }

    /* renamed from: a */
    private final void m564a(String str, byte[] bArr, int i, int i2) {
        try {
            m565a(bArr, i, i2);
        } catch (C0434k e) {
            throw m561a(str, "Name too long");
        }
    }

    /* renamed from: a */
    private final void m565a(byte[] bArr, int i, int i2) {
        int length = this.f370c == null ? 0 : this.f370c.length - m557a(0);
        int i3 = i;
        int i4 = 0;
        for (int i5 = 0; i5 < i2; i5++) {
            byte b = bArr[i3];
            if (b > 63) {
                throw new IllegalStateException("invalid label");
            }
            int i6 = b + 1;
            i3 += i6;
            i4 += i6;
        }
        int i7 = length + i4;
        if (i7 > 255) {
            throw new C0434k();
        }
        int c = m569c();
        int i8 = c + i2;
        if (i8 > 128) {
            throw new IllegalStateException("too many labels");
        }
        byte[] bArr2 = new byte[i7];
        if (length != 0) {
            System.arraycopy(this.f370c, m557a(0), bArr2, 0, length);
        }
        System.arraycopy(bArr, i, bArr2, length, i4);
        this.f370c = bArr2;
        for (int i9 = 0; i9 < i2; i9++) {
            m563a(c + i9, length);
            length += bArr2[length] + 1;
        }
        m566b(i8);
    }

    /* renamed from: b */
    private final void m566b(int i) {
        this.f371d &= -256;
        this.f371d |= (long) i;
    }

    /* renamed from: b */
    private static final void m567b(C0433j jVar, C0433j jVar2) {
        int i = 0;
        if (jVar.m557a(0) == 0) {
            jVar2.f370c = jVar.f370c;
            jVar2.f371d = jVar.f371d;
            return;
        }
        int a = jVar.m557a(0);
        int length = jVar.f370c.length - a;
        int c = jVar.m569c();
        jVar2.f370c = new byte[length];
        System.arraycopy(jVar.f370c, a, jVar2.f370c, 0, length);
        while (i < c && i < 7) {
            jVar2.m563a(i, jVar.m557a(i) - a);
            i++;
        }
        jVar2.m566b(c);
    }

    /* renamed from: b */
    private final void m568b(byte[] bArr, int i, int i2) {
        try {
            m565a(bArr, 0, 1);
        } catch (C0434k e) {
        }
    }

    /* renamed from: c */
    private final int m569c() {
        return (int) (this.f371d & 255);
    }

    /* renamed from: a */
    public final void mo6518a(C0428e eVar) {
        byte[] bArr;
        int c = m569c();
        if (c == 0) {
            bArr = new byte[0];
        } else {
            bArr = new byte[(this.f370c.length - m557a(0))];
            int a = m557a(0);
            int i = 0;
            for (int i2 = 0; i2 < c; i2++) {
                byte b = this.f370c[a];
                if (b > 63) {
                    throw new IllegalStateException("invalid label");
                }
                int i3 = i + 1;
                int i4 = a + 1;
                bArr[i] = this.f370c[a];
                a = i4;
                int i5 = i3;
                int i6 = 0;
                while (i6 < b) {
                    int i7 = i5 + 1;
                    int i8 = a + 1;
                    bArr[i5] = f368i[this.f370c[a] & 255];
                    i6++;
                    i5 = i7;
                    a = i8;
                }
                i = i5;
            }
        }
        eVar.mo6499a(bArr);
    }

    /* renamed from: a */
    public final void mo6519a(C0428e eVar, C0425b bVar) {
        int c = m569c();
        int i = 0;
        while (i < c - 1) {
            C0433j jVar = i == 0 ? this : new C0433j(this, i);
            int i2 = -1;
            if (bVar != null) {
                i2 = bVar.mo6482a(jVar);
            }
            if (i2 >= 0) {
                eVar.mo6503c(i2 | 49152);
                return;
            }
            if (bVar != null) {
                bVar.mo6483a(eVar.mo6495a(), jVar);
            }
            int a = m557a(i);
            eVar.mo6500a(this.f370c, a, this.f370c[a] + 1);
            i++;
        }
        eVar.mo6501b(0);
    }

    /* renamed from: a */
    public final void mo6520a(C0428e eVar, C0425b bVar, boolean z) {
        if (z) {
            mo6518a(eVar);
        } else {
            mo6519a(eVar, (C0425b) null);
        }
    }

    /* renamed from: a */
    public final boolean mo6521a() {
        int c = m569c();
        return c != 0 && this.f370c[m557a(c + -1)] == 0;
    }

    /* renamed from: b */
    public final int mo6522b() {
        return m569c();
    }

    /* JADX WARNING: type inference failed for: r4v9, types: [int] */
    /* JADX WARNING: type inference failed for: r5v2 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int compareTo(java.lang.Object r15) {
        /*
            r14 = this;
            r3 = 0
            cn.jiguang.d.c.j r15 = (p004cn.jiguang.p015d.p020c.C0433j) r15
            if (r14 != r15) goto L_0x0007
            r0 = r3
        L_0x0006:
            return r0
        L_0x0007:
            int r2 = r14.m569c()
            int r1 = r15.m569c()
            if (r2 <= r1) goto L_0x0050
            r0 = r1
        L_0x0012:
            r4 = 1
            r6 = r4
        L_0x0014:
            if (r6 > r0) goto L_0x005f
            int r4 = r2 - r6
            int r7 = r14.m557a(r4)
            int r4 = r1 - r6
            int r8 = r15.m557a(r4)
            byte[] r4 = r14.f370c
            byte r9 = r4[r7]
            byte[] r4 = r15.f370c
            byte r10 = r4[r8]
            r5 = r3
        L_0x002b:
            if (r5 >= r9) goto L_0x0056
            if (r5 >= r10) goto L_0x0056
            byte[] r4 = f368i
            byte[] r11 = r14.f370c
            int r12 = r5 + r7
            int r12 = r12 + 1
            byte r11 = r11[r12]
            r11 = r11 & 255(0xff, float:3.57E-43)
            byte r4 = r4[r11]
            byte[] r11 = f368i
            byte[] r12 = r15.f370c
            int r13 = r5 + r8
            int r13 = r13 + 1
            byte r12 = r12[r13]
            r12 = r12 & 255(0xff, float:3.57E-43)
            byte r11 = r11[r12]
            int r4 = r4 - r11
            if (r4 == 0) goto L_0x0052
            r0 = r4
            goto L_0x0006
        L_0x0050:
            r0 = r2
            goto L_0x0012
        L_0x0052:
            int r4 = r5 + 1
            r5 = r4
            goto L_0x002b
        L_0x0056:
            if (r9 == r10) goto L_0x005b
            int r0 = r9 - r10
            goto L_0x0006
        L_0x005b:
            int r4 = r6 + 1
            r6 = r4
            goto L_0x0014
        L_0x005f:
            int r0 = r2 - r1
            goto L_0x0006
        */
        throw new UnsupportedOperationException("Method not decompiled: p004cn.jiguang.p015d.p020c.C0433j.compareTo(java.lang.Object):int");
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || !(obj instanceof C0433j)) {
            return false;
        }
        C0433j jVar = (C0433j) obj;
        if (jVar.f372e == 0) {
            jVar.hashCode();
        }
        if (this.f372e == 0) {
            hashCode();
        }
        if (jVar.f372e != this.f372e) {
            return false;
        }
        if (jVar.m569c() != m569c()) {
            return false;
        }
        byte[] bArr = jVar.f370c;
        int a = jVar.m557a(0);
        int c = m569c();
        int a2 = m557a(0);
        for (int i = 0; i < c; i++) {
            if (this.f370c[a2] != bArr[a]) {
                return false;
            }
            int i2 = a2 + 1;
            byte b = this.f370c[a2];
            int i3 = a + 1;
            if (b > 63) {
                throw new IllegalStateException("invalid label");
            }
            a = i3;
            a2 = i2;
            int i4 = 0;
            while (i4 < b) {
                int i5 = a2 + 1;
                byte b2 = f368i[this.f370c[a2] & 255];
                int i6 = a + 1;
                if (b2 != f368i[bArr[a] & 255]) {
                    return false;
                }
                i4++;
                a = i6;
                a2 = i5;
            }
        }
        return true;
    }

    public final int hashCode() {
        int i = 0;
        if (this.f372e != 0) {
            return this.f372e;
        }
        for (int a = m557a(0); a < this.f370c.length; a++) {
            i += (i << 3) + f368i[this.f370c[a] & 255];
        }
        this.f372e = i;
        return this.f372e;
    }

    public final String toString() {
        int i = 0;
        int c = m569c();
        if (c == 0) {
            return "@";
        }
        if (c == 1 && this.f370c[m557a(0)] == 0) {
            return ".";
        }
        StringBuffer stringBuffer = new StringBuffer();
        int a = m557a(0);
        while (true) {
            if (i >= c) {
                break;
            }
            byte b = this.f370c[a];
            if (b > 63) {
                throw new IllegalStateException("invalid label");
            } else if (b == 0) {
                stringBuffer.append(FilenameUtils.EXTENSION_SEPARATOR);
                break;
            } else {
                if (i > 0) {
                    stringBuffer.append(FilenameUtils.EXTENSION_SEPARATOR);
                }
                stringBuffer.append(m562a(this.f370c, a));
                a += b + 1;
                i++;
            }
        }
        return stringBuffer.toString();
    }
}
