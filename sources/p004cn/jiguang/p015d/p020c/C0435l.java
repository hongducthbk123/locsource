package p004cn.jiguang.p015d.p020c;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* renamed from: cn.jiguang.d.c.l */
public final class C0435l implements Serializable {

    /* renamed from: a */
    private List f373a;

    /* renamed from: b */
    private short f374b;

    /* renamed from: c */
    private short f375c;

    public C0435l() {
        this.f373a = new ArrayList(1);
        this.f374b = 0;
        this.f375c = 0;
    }

    public C0435l(C0436m mVar) {
        this();
        m577b(mVar);
    }

    /* renamed from: a */
    private static String m575a(Iterator it) {
        StringBuffer stringBuffer = new StringBuffer();
        while (it.hasNext()) {
            C0436m mVar = (C0436m) it.next();
            stringBuffer.append("[");
            stringBuffer.append(mVar.mo6531a());
            stringBuffer.append("]");
            if (it.hasNext()) {
                stringBuffer.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
            }
        }
        return stringBuffer.toString();
    }

    /* renamed from: a */
    private synchronized Iterator m576a(boolean z, boolean z2) {
        Iterator it;
        int i = 0;
        synchronized (this) {
            int size = this.f373a.size();
            int i2 = z ? size - this.f374b : this.f374b;
            if (i2 == 0) {
                it = Collections.EMPTY_LIST.iterator();
            } else {
                if (!z) {
                    i = size - this.f374b;
                } else if (z2) {
                    if (this.f375c >= i2) {
                        this.f375c = 0;
                    }
                    i = this.f375c;
                    this.f375c = (short) (i + 1);
                }
                ArrayList arrayList = new ArrayList(i2);
                if (z) {
                    arrayList.addAll(this.f373a.subList(i, i2));
                    if (i != 0) {
                        arrayList.addAll(this.f373a.subList(0, i));
                    }
                } else {
                    arrayList.addAll(this.f373a.subList(i, size));
                }
                it = arrayList.iterator();
            }
        }
        return it;
    }

    /* renamed from: b */
    private void m577b(C0436m mVar) {
        if (this.f374b == 0) {
            this.f373a.add(mVar);
        } else {
            this.f373a.add(this.f373a.size() - this.f374b, mVar);
        }
    }

    /* renamed from: c */
    private synchronized long m578c() {
        return mo6529b().mo6543f();
    }

    /* renamed from: a */
    public final synchronized Iterator mo6527a() {
        return m576a(true, true);
    }

    /* renamed from: a */
    public final synchronized void mo6528a(C0436m mVar) {
        if (this.f373a.size() == 0) {
            m577b(mVar);
        } else {
            C0436m b = mo6529b();
            if (!mVar.mo6536a(b)) {
                throw new IllegalArgumentException("record does not match rrset");
            }
            if (mVar.mo6543f() != b.mo6543f()) {
                if (mVar.mo6543f() <= b.mo6543f()) {
                    int i = 0;
                    while (true) {
                        int i2 = i;
                        if (i2 >= this.f373a.size()) {
                            break;
                        }
                        C0436m g = ((C0436m) this.f373a.get(i2)).mo6544g();
                        g.mo6532a(mVar.mo6543f());
                        this.f373a.set(i2, g);
                        i = i2 + 1;
                    }
                } else {
                    mVar = mVar.mo6544g();
                    mVar.mo6532a(b.mo6543f());
                }
            }
            if (!this.f373a.contains(mVar)) {
                m577b(mVar);
            }
        }
    }

    /* renamed from: b */
    public final synchronized C0436m mo6529b() {
        if (this.f373a.size() == 0) {
            throw new IllegalStateException("rrset is empty");
        }
        return (C0436m) this.f373a.get(0);
    }

    public final String toString() {
        if (this.f373a.size() == 0) {
            return "{empty}";
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("{ ");
        stringBuffer.append(mo6529b().mo6537b() + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        stringBuffer.append(m578c() + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        stringBuffer.append(m575a(m576a(true, false)));
        if (this.f374b > 0) {
            stringBuffer.append(" sigs: ");
            stringBuffer.append(m575a(m576a(false, false)));
        }
        stringBuffer.append(" }");
        return stringBuffer.toString();
    }
}
