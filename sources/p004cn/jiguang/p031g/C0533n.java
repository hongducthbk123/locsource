package p004cn.jiguang.p031g;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* renamed from: cn.jiguang.g.n */
public final class C0533n {

    /* renamed from: a */
    private long f611a = System.currentTimeMillis();

    /* renamed from: b */
    private SimpleDateFormat f612b = new SimpleDateFormat("mm:ss:SSS", Locale.ENGLISH);

    /* renamed from: a */
    public final void mo6706a(String str) {
        new StringBuilder().append(str).append(" | cost time:").append(this.f612b.format(new Date(System.currentTimeMillis() - this.f611a)));
    }
}
