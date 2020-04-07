package p004cn.jiguang.p031g;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/* renamed from: cn.jiguang.g.j */
public final class C0529j {
    /* renamed from: a */
    public static byte[] m1097a(InputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                byteArrayOutputStream.write(bArr, 0, read);
            } else {
                inputStream.close();
                return byteArrayOutputStream.toByteArray();
            }
        }
    }
}
