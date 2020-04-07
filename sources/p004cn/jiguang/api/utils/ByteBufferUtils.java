package p004cn.jiguang.api.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import p004cn.jiguang.api.JResponse;
import p004cn.jiguang.p029e.C0501d;

/* renamed from: cn.jiguang.api.utils.ByteBufferUtils */
public class ByteBufferUtils {
    public static final int ERROR_CODE = 10000;

    /* renamed from: a */
    private static String m211a(Throwable th, JResponse jResponse, ByteBuffer byteBuffer) {
        StringBuilder sb = new StringBuilder();
        if (jResponse != null) {
            sb.append(jResponse.toString());
            sb.append("|bytebuffer:" + (byteBuffer == null ? "byteBuffer is null" : byteBuffer.toString()));
        }
        C0501d.m909d("ByteBufferUtils", "byteBuffer info:" + sb.toString());
        try {
            StringWriter stringWriter = new StringWriter();
            th.printStackTrace(new PrintWriter(stringWriter));
            C0501d.m909d("ByteBufferUtils", "parse data error stackTrace:" + stringWriter.toString());
        } catch (Exception e) {
        }
        return sb.toString();
    }

    public static Byte get(ByteBuffer byteBuffer, JResponse jResponse) {
        try {
            return Byte.valueOf(byteBuffer.get());
        } catch (BufferUnderflowException e) {
            m211a(e.fillInStackTrace(), jResponse, byteBuffer);
        } catch (BufferOverflowException e2) {
            m211a(e2.fillInStackTrace(), jResponse, byteBuffer);
        } catch (Exception e3) {
            m211a(e3.fillInStackTrace(), jResponse, byteBuffer);
        }
        if (jResponse != null) {
            jResponse.code = ERROR_CODE;
        }
        return null;
    }

    public static ByteBuffer get(ByteBuffer byteBuffer, byte[] bArr, JResponse jResponse) {
        try {
            return byteBuffer.get(bArr);
        } catch (BufferUnderflowException e) {
            m211a(e.fillInStackTrace(), jResponse, byteBuffer);
        } catch (BufferOverflowException e2) {
            m211a(e2.fillInStackTrace(), jResponse, byteBuffer);
        } catch (Exception e3) {
            m211a(e3.fillInStackTrace(), jResponse, byteBuffer);
        }
        if (jResponse != null) {
            jResponse.code = ERROR_CODE;
        }
        return null;
    }

    public static int getInt(ByteBuffer byteBuffer, JResponse jResponse) {
        try {
            return byteBuffer.getInt();
        } catch (BufferUnderflowException e) {
            m211a(e.fillInStackTrace(), jResponse, byteBuffer);
        } catch (BufferOverflowException e2) {
            m211a(e2.fillInStackTrace(), jResponse, byteBuffer);
        } catch (Exception e3) {
            m211a(e3.fillInStackTrace(), jResponse, byteBuffer);
        }
        if (jResponse != null) {
            jResponse.code = ERROR_CODE;
        }
        return -1;
    }

    public static long getLong(ByteBuffer byteBuffer, JResponse jResponse) {
        try {
            return byteBuffer.getLong();
        } catch (BufferUnderflowException e) {
            m211a(e.fillInStackTrace(), jResponse, byteBuffer);
        } catch (BufferOverflowException e2) {
            m211a(e2.fillInStackTrace(), jResponse, byteBuffer);
        } catch (Exception e3) {
            m211a(e3.fillInStackTrace(), jResponse, byteBuffer);
        }
        if (jResponse != null) {
            jResponse.code = ERROR_CODE;
        }
        return 0;
    }

    public static short getShort(ByteBuffer byteBuffer, JResponse jResponse) {
        try {
            return byteBuffer.getShort();
        } catch (BufferUnderflowException e) {
            m211a(e.fillInStackTrace(), jResponse, byteBuffer);
        } catch (BufferOverflowException e2) {
            m211a(e2.fillInStackTrace(), jResponse, byteBuffer);
        } catch (Exception e3) {
            m211a(e3.fillInStackTrace(), jResponse, byteBuffer);
        }
        if (jResponse != null) {
            jResponse.code = ERROR_CODE;
        }
        return -1;
    }
}
