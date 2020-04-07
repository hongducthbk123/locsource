package p004cn.jiguang.p015d.p017b;

/* renamed from: cn.jiguang.d.b.j */
final class C0423j {

    /* renamed from: a */
    String f332a;

    /* renamed from: b */
    int f333b;

    public C0423j(String str) {
        int indexOf = str.indexOf(58);
        if (indexOf == -1) {
            throw new Exception("Port is needed for a valid address, split by :");
        }
        this.f332a = str.substring(0, indexOf);
        if (!C0422i.m502c(this.f332a)) {
            throw new Exception("Invalid ip - " + this.f332a);
        }
        String substring = str.substring(indexOf + 1);
        try {
            this.f333b = Integer.parseInt(substring);
            if (this.f333b == 0) {
                throw new Exception("Invalid port - 0");
            }
        } catch (Exception e) {
            throw new Exception("Invalid port - " + substring);
        }
    }

    public final String toString() {
        return this.f332a + ":" + this.f333b;
    }
}
