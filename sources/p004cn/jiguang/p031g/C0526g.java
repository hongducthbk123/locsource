package p004cn.jiguang.p031g;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;

/* renamed from: cn.jiguang.g.g */
public final class C0526g {
    /* renamed from: a */
    public static void m1087a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /* renamed from: a */
    public static byte[] m1088a(InputStream inputStream) {
        try {
            byte[] bArr = new byte[inputStream.available()];
            inputStream.read(bArr);
            return bArr;
        } finally {
            m1087a((Closeable) inputStream);
        }
    }
}
