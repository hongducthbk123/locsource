package p004cn.jpush.android.data;

import android.text.TextUtils;
import java.io.Serializable;

/* renamed from: cn.jpush.android.data.c */
public final class C0590c implements Serializable {
    private static final long serialVersionUID = -942487107643335186L;

    /* renamed from: a */
    public String f829a;

    /* renamed from: b */
    public String f830b;

    public C0590c(C0589b bVar) {
        this.f829a = bVar.f805c;
        this.f830b = bVar.f806d;
    }

    public C0590c() {
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof C0590c)) {
            return false;
        }
        C0590c cVar = (C0590c) obj;
        if (TextUtils.isEmpty(this.f829a) || TextUtils.isEmpty(cVar.f829a) || !TextUtils.equals(this.f829a, cVar.f829a)) {
            return false;
        }
        if (TextUtils.isEmpty(this.f830b) && TextUtils.isEmpty(cVar.f830b)) {
            return true;
        }
        if (TextUtils.isEmpty(this.f830b) || TextUtils.isEmpty(cVar.f830b) || !TextUtils.equals(this.f830b, cVar.f830b)) {
            return false;
        }
        return true;
    }

    public final String toString() {
        return "msg_id = " + this.f829a + ",  override_msg_id = " + this.f830b;
    }
}
