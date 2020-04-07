package p004cn.jiguang.p015d.p020c;

import android.support.p000v4.internal.view.SupportMenu;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.util.Random;
import org.apache.commons.p052io.IOUtils;

/* renamed from: cn.jiguang.d.c.g */
public final class C0430g implements Cloneable {

    /* renamed from: d */
    private static Random f346d = new Random();

    /* renamed from: a */
    private int f347a;

    /* renamed from: b */
    private int f348b;

    /* renamed from: c */
    private int[] f349c;

    public C0430g() {
        this.f348b = 256;
        m541b();
    }

    private C0430g(int i) {
        this.f348b = 256;
        m541b();
        if (i < 0 || i > 65535) {
            throw new IllegalArgumentException("DNS message ID " + i + " is out of range");
        }
        this.f347a = i;
    }

    C0430g(C0427d dVar) {
        this(dVar.mo6493g());
        this.f348b = dVar.mo6493g();
        for (int i = 0; i < this.f349c.length; i++) {
            this.f349c[i] = dVar.mo6493g();
        }
    }

    /* renamed from: b */
    private void m541b() {
        this.f349c = new int[4];
        this.f348b = 256;
        this.f347a = -1;
    }

    /* renamed from: c */
    private int m542c() {
        int i;
        if (this.f347a >= 0) {
            return this.f347a;
        }
        synchronized (this) {
            if (this.f347a < 0) {
                this.f347a = f346d.nextInt(SupportMenu.USER_MASK);
            }
            i = this.f347a;
        }
        return i;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public final int mo6504a() {
        return this.f348b;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public final void mo6505a(int i) {
        if (this.f349c[i] == 65535) {
            throw new IllegalStateException("DNS section count cannot be incremented");
        }
        int[] iArr = this.f349c;
        iArr[i] = iArr[i] + 1;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public final void mo6506a(C0428e eVar) {
        eVar.mo6503c(m542c());
        eVar.mo6503c(this.f348b);
        for (int c : this.f349c) {
            eVar.mo6503c(c);
        }
    }

    /* renamed from: b */
    public final int mo6507b(int i) {
        return this.f349c[i];
    }

    public final Object clone() {
        C0430g gVar = new C0430g();
        gVar.f347a = this.f347a;
        gVar.f348b = this.f348b;
        System.arraycopy(this.f349c, 0, gVar.f349c, 0, this.f349c.length);
        return gVar;
    }

    public final String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(";; ->>HEADER<<- ");
        stringBuffer.append(", id: " + m542c());
        stringBuffer.append(IOUtils.LINE_SEPARATOR_UNIX);
        stringBuffer.append("; ");
        for (int i = 0; i < 4; i++) {
            stringBuffer.append(C0440q.m615a(i) + ": " + this.f349c[i] + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        }
        return stringBuffer.toString();
    }
}
