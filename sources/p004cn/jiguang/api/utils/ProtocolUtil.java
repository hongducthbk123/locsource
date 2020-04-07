package p004cn.jiguang.api.utils;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import p004cn.jiguang.api.JResponse;

/* renamed from: cn.jiguang.api.utils.ProtocolUtil */
public class ProtocolUtil {
    public static void fillIntData(byte[] bArr, int i, int i2) {
        System.arraycopy(int2ByteArray(i), 0, bArr, i2, 4);
    }

    public static void fillStringData(byte[] bArr, String str, int i) {
        byte[] bytes = str.getBytes();
        System.arraycopy(bytes, 0, bArr, i, bytes.length);
    }

    public static byte[] fixedStringToBytes(String str, int i) {
        byte[] bArr;
        if (str == null || "".equals(str)) {
            return new byte[]{0, 0, 0, 0};
        }
        byte[] bArr2 = null;
        try {
            bArr = str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            bArr = bArr2;
        }
        if (bArr == null) {
            return new byte[]{0, 0, 0, 0};
        }
        byte[] defaultByte = getDefaultByte(i);
        if (bArr.length <= i) {
            i = bArr.length;
        }
        System.arraycopy(bArr, 0, defaultByte, 0, i);
        return defaultByte;
    }

    public static byte[] getBytes(ByteBuffer byteBuffer) {
        try {
            byte[] bArr = new byte[byteBuffer.remaining()];
            byteBuffer.asReadOnlyBuffer().flip();
            byteBuffer.get(bArr);
            return bArr;
        } catch (NegativeArraySizeException e) {
            return null;
        } catch (Exception e2) {
            return null;
        }
    }

    public static byte[] getBytesConsumed(ByteBuffer byteBuffer) {
        try {
            byte[] bArr = new byte[byteBuffer.remaining()];
            byteBuffer.get(bArr);
            return bArr;
        } catch (NegativeArraySizeException e) {
            return null;
        } catch (Exception e2) {
            return null;
        }
    }

    public static byte[] getDefaultByte(int i) {
        byte[] bArr = new byte[i];
        for (int i2 = 0; i2 < i; i2++) {
            bArr[0] = 0;
        }
        return bArr;
    }

    public static String getString(ByteBuffer byteBuffer, int i) {
        byte[] bArr = new byte[i];
        byteBuffer.get(bArr);
        try {
            return new String(bArr, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    public static String getTlv2(ByteBuffer byteBuffer) {
        try {
            byte[] bArr = new byte[byteBuffer.getShort()];
            byteBuffer.get(bArr);
            return new String(bArr, "UTF-8");
        } catch (UnsupportedEncodingException | Exception e) {
            return null;
        }
    }

    public static String getTlv2(ByteBuffer byteBuffer, JResponse jResponse) {
        short s = ByteBufferUtils.getShort(byteBuffer, jResponse);
        if (s < 0) {
            return null;
        }
        byte[] bArr = new byte[s];
        ByteBufferUtils.get(byteBuffer, bArr, jResponse);
        try {
            return new String(bArr, "UTF-8");
        } catch (Throwable th) {
            return null;
        }
    }

    public static byte[] int2ByteArray(int i) {
        return new byte[]{(byte) (i >>> 24), (byte) (i >>> 16), (byte) (i >>> 8), (byte) i};
    }

    public static byte[] long2ByteArray(long j) {
        return new byte[]{(byte) ((int) (j >>> 56)), (byte) ((int) (j >>> 48)), (byte) ((int) (j >>> 40)), (byte) ((int) (j >>> 32)), (byte) ((int) (j >>> 24)), (byte) ((int) (j >>> 16)), (byte) ((int) (j >>> 8)), (byte) ((int) j)};
    }

    public static void main(String[] strArr) {
        fixedStringToBytes("ab", 4);
    }

    public static byte[] short2ByteArray(short s) {
        return new byte[]{(byte) (s >>> 8), (byte) s};
    }

    public static byte[] tlv2ToByteArray(String str) {
        byte[] bArr;
        if (str == null || "".equals(str)) {
            return new byte[]{0, 0};
        }
        byte[] bArr2 = null;
        try {
            bArr = str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            bArr = bArr2;
        }
        if (bArr == null) {
            return new byte[]{0, 0};
        }
        short length = (short) bArr.length;
        byte[] bArr3 = new byte[(length + 2)];
        System.arraycopy(short2ByteArray(length), 0, bArr3, 0, 2);
        System.arraycopy(bArr, 0, bArr3, 2, length);
        return bArr3;
    }
}
