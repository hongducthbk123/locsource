package p004cn.jiguang.p015d.p022e.p023a.p024a;

import android.content.Context;
import com.google.common.primitives.UnsignedBytes;
import java.nio.ByteBuffer;
import p004cn.jiguang.api.JResponse;
import p004cn.jiguang.p015d.p021d.C0445b;
import p004cn.jiguang.p015d.p022e.p023a.C0465a;
import p004cn.jiguang.p015d.p022e.p023a.C0469b;
import p004cn.jiguang.p015d.p022e.p023a.C0470c;
import p004cn.jiguang.p015d.p022e.p023a.C0471d;
import p004cn.jiguang.p015d.p022e.p023a.C0472e;
import p004cn.jiguang.p015d.p022e.p023a.C0473f;
import p004cn.jiguang.p015d.p026g.p027a.C0480a;
import p004cn.jiguang.p029e.C0501d;

/* renamed from: cn.jiguang.d.e.a.a.a */
public final class C0466a {
    /* renamed from: a */
    public static JResponse m731a(C0468c cVar, ByteBuffer byteBuffer) {
        if (cVar == null) {
            return null;
        }
        switch (cVar.f453c) {
            case 0:
                return new C0472e(cVar, byteBuffer);
            case 1:
                return new C0471d(cVar, byteBuffer);
            case 19:
                return new C0465a(cVar, byteBuffer);
            case 25:
                return new C0469b(cVar, byteBuffer);
            case 26:
                return new C0473f(cVar, byteBuffer);
            default:
                return null;
        }
    }

    /* renamed from: a */
    public static JResponse m732a(byte[] bArr) {
        C0470c b = m734b(bArr);
        if (b != null) {
            return m731a(b.mo6602a(), b.mo6603b());
        }
        return null;
    }

    /* renamed from: a */
    public static boolean m733a(Context context, byte[] bArr) {
        try {
            C0470c b = m734b(bArr);
            if (b != null) {
                C0445b.m618a();
                C0445b.m621a(context, b.mo6602a(), b.mo6603b());
                return true;
            }
        } catch (Throwable th) {
            C0501d.m907c("JCommands", "dispatchMessage error:" + th.getMessage());
        }
        return false;
    }

    /* renamed from: b */
    private static C0470c m734b(byte[] bArr) {
        ByteBuffer wrap;
        C0470c cVar = null;
        if (bArr.length < 20) {
            C0501d.m907c("JCommands", "Error: received body-length short than common head-length, return null");
            return cVar;
        }
        byte[] bArr2 = new byte[20];
        System.arraycopy(bArr, 0, bArr2, 0, 20);
        byte b = bArr2[0] & UnsignedBytes.MAX_POWER_OF_TWO;
        C0468c cVar2 = new C0468c(false, bArr2);
        int i = cVar2.f451a - 20;
        if (i < 0 || bArr.length < 20) {
            C0501d.m907c("JCommands", "Error: totalBytes length error with no encrypted, return null");
            return cVar;
        }
        byte[] bArr3 = new byte[i];
        System.arraycopy(bArr, 20, bArr3, 0, i);
        if (b > 0) {
            try {
                wrap = ByteBuffer.wrap(C0480a.m785b(C0480a.m774a(), bArr3));
            } catch (Exception e) {
                return cVar;
            }
        } else {
            System.arraycopy(bArr, 20, bArr3, 0, i);
            wrap = ByteBuffer.wrap(bArr3);
        }
        return new C0470c(cVar2, wrap);
    }
}
