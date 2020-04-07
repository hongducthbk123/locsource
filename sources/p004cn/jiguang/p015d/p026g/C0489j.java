package p004cn.jiguang.p015d.p026g;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import p004cn.jiguang.p031g.C0526g;

/* renamed from: cn.jiguang.d.g.j */
public final class C0489j {
    /* renamed from: a */
    public static byte[] m820a(byte[] bArr) {
        GZIPOutputStream gZIPOutputStream;
        if (!(bArr == null || bArr.length == 0)) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
                try {
                    gZIPOutputStream.write(bArr);
                    gZIPOutputStream.close();
                    bArr = byteArrayOutputStream.toByteArray();
                    C0526g.m1087a((Closeable) byteArrayOutputStream);
                    C0526g.m1087a((Closeable) gZIPOutputStream);
                } catch (Throwable th) {
                    th = th;
                    C0526g.m1087a((Closeable) byteArrayOutputStream);
                    C0526g.m1087a((Closeable) gZIPOutputStream);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                gZIPOutputStream = null;
                C0526g.m1087a((Closeable) byteArrayOutputStream);
                C0526g.m1087a((Closeable) gZIPOutputStream);
                throw th;
            }
        }
        return bArr;
    }

    /* renamed from: b */
    public static byte[] m821b(byte[] bArr) {
        GZIPInputStream gZIPInputStream;
        if (!(bArr == null || bArr.length == 0)) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
            try {
                gZIPInputStream = new GZIPInputStream(byteArrayInputStream);
                try {
                    byte[] bArr2 = new byte[256];
                    while (true) {
                        int read = gZIPInputStream.read(bArr2);
                        if (read < 0) {
                            break;
                        }
                        byteArrayOutputStream.write(bArr2, 0, read);
                    }
                    bArr = byteArrayOutputStream.toByteArray();
                    C0526g.m1087a((Closeable) byteArrayOutputStream);
                    C0526g.m1087a((Closeable) byteArrayInputStream);
                    C0526g.m1087a((Closeable) gZIPInputStream);
                } catch (Throwable th) {
                    th = th;
                    C0526g.m1087a((Closeable) byteArrayOutputStream);
                    C0526g.m1087a((Closeable) byteArrayInputStream);
                    C0526g.m1087a((Closeable) gZIPInputStream);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                gZIPInputStream = null;
                C0526g.m1087a((Closeable) byteArrayOutputStream);
                C0526g.m1087a((Closeable) byteArrayInputStream);
                C0526g.m1087a((Closeable) gZIPInputStream);
                throw th;
            }
        }
        return bArr;
    }
}
