package p004cn.jiguang.p015d.p026g.p027a;

import android.support.p000v4.view.ViewCompat;
import android.util.Base64;
import com.google.common.primitives.UnsignedBytes;
import java.lang.reflect.InvocationTargetException;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.http.protocol.HTTP;
import p004cn.jiguang.p015d.p016a.C0389d;
import p004cn.jiguang.p030f.C0503b;
import p004cn.jiguang.p031g.C0506a;

/* renamed from: cn.jiguang.d.g.a.a */
public final class C0480a {

    /* renamed from: a */
    private static String f489a = "";

    /* renamed from: b */
    private static int f490b = 0;

    /* renamed from: a */
    public static String m774a() {
        return f489a;
    }

    /* renamed from: a */
    public static String m775a(long j) {
        long j2;
        switch (((int) j) % 10) {
            case 1:
                j2 = (5 * j) + (j % 88);
                break;
            case 2:
                j2 = (23 * j) + (j % 15);
                break;
            case 3:
                j2 = (3 * j) + (j % 73);
                break;
            case 4:
                j2 = (13 * j) + (j % 96);
                break;
            case 5:
                j2 = (17 * j) + (j % 49);
                break;
            case 6:
                j2 = (7 * j) + (j % 68);
                break;
            case 7:
                j2 = (31 * j) + (j % 39);
                break;
            case 8:
                j2 = (29 * j) + (j % 41);
                break;
            case 9:
                j2 = (37 * j) + (j % 91);
                break;
            default:
                j2 = (8 * j) + (j % 74);
                break;
        }
        return C0506a.m933a("JCKP" + String.valueOf(j2));
    }

    /* renamed from: a */
    public static String m776a(String str) {
        String str2 = "DFA84B10B7ACDD25";
        try {
            if (str2.length() != 16) {
                return null;
            }
            byte[] c = m786c(str2, HTTP.ASCII);
            Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
            instance.init(1, new SecretKeySpec(c, "AES"), m778a(c));
            return Base64.encodeToString(instance.doFinal(str.getBytes()), 2);
        } catch (Exception e) {
            return "";
        }
    }

    /* renamed from: a */
    public static String m777a(String str, String str2) {
        if (str2 == null) {
            return null;
        }
        try {
            if (str2.length() != 16) {
                return null;
            }
            byte[] c = m786c(str2, HTTP.ASCII);
            Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
            instance.init(2, new SecretKeySpec(c, "AES"), m778a(c));
            try {
                return new String(instance.doFinal(Base64.decode(str, 2)));
            } catch (Exception e) {
                return null;
            }
        } catch (Exception e2) {
            return null;
        }
    }

    /* renamed from: a */
    private static IvParameterSpec m778a(byte[] bArr) {
        try {
            return (IvParameterSpec) C0503b.m918a(IvParameterSpec.class, new Object[]{bArr}, (Class<?>[]) new Class[]{byte[].class});
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
        } catch (InvocationTargetException e3) {
            e3.printStackTrace();
        } catch (InstantiationException e4) {
            e4.printStackTrace();
        }
        return null;
    }

    /* renamed from: a */
    public static void m779a(int i) {
        f490b = i;
    }

    /* renamed from: a */
    public static byte[] m780a(String str, byte[] bArr) {
        SecretKeySpec secretKeySpec = new SecretKeySpec(m786c(str, "utf-8"), "AES");
        Cipher instance = Cipher.getInstance("AES/ECB/PKCS7Padding");
        instance.init(1, secretKeySpec);
        return instance.doFinal(bArr);
    }

    /* renamed from: a */
    public static byte[] m781a(byte[] bArr, int i) {
        Exception e;
        byte[] bArr2;
        int length = bArr.length - 24;
        byte[] bArr3 = new byte[24];
        byte[] bArr4 = new byte[length];
        System.arraycopy(bArr, 0, bArr3, 0, 24);
        System.arraycopy(bArr, 24, bArr4, 0, length);
        String str = "";
        if (i == 1) {
            str = m783b(C0389d.m331d(null));
        } else if (i == 0) {
            str = m783b((long) f490b);
        }
        try {
            byte[] a = m780a(str, bArr4);
            int length2 = a.length + 24;
            bArr2 = new byte[length2];
            try {
                System.arraycopy(bArr3, 0, bArr2, 0, 24);
                System.arraycopy(a, 0, bArr2, 24, a.length);
                bArr2[0] = (byte) ((length2 >>> 8) & 255);
                bArr2[1] = (byte) (length2 & 255);
                bArr2[0] = (byte) (bArr2[0] | UnsignedBytes.MAX_POWER_OF_TWO);
            } catch (Exception e2) {
                e = e2;
                e.printStackTrace();
                return bArr2;
            }
        } catch (Exception e3) {
            Exception exc = e3;
            bArr2 = null;
            e = exc;
            e.printStackTrace();
            return bArr2;
        }
        return bArr2;
    }

    /* renamed from: b */
    public static int m782b() {
        return Math.abs(new SecureRandom().nextInt()) & ViewCompat.MEASURED_SIZE_MASK;
    }

    /* renamed from: b */
    private static String m783b(long j) {
        long j2;
        String valueOf = String.valueOf(j);
        int length = valueOf.length();
        if (length >= 2) {
            valueOf = valueOf.substring(length - 2, length);
        }
        switch (Integer.parseInt(valueOf) % 10) {
            case 1:
                j2 = (5 * j) + (j % 88);
                break;
            case 2:
                j2 = (23 * j) + (j % 15);
                break;
            case 3:
                j2 = (3 * j) + (j % 73);
                break;
            case 4:
                j2 = (13 * j) + (j % 96);
                break;
            case 5:
                j2 = (17 * j) + (j % 49);
                break;
            case 6:
                j2 = (7 * j) + (j % 68);
                break;
            case 7:
                j2 = (31 * j) + (j % 39);
                break;
            case 8:
                j2 = (29 * j) + (j % 41);
                break;
            case 9:
                j2 = (37 * j) + (j % 91);
                break;
            default:
                j2 = (8 * j) + (j % 74);
                break;
        }
        String a = C0506a.m933a("JCKP" + String.valueOf(j2));
        f489a = a;
        return a;
    }

    /* renamed from: b */
    public static String m784b(String str, String str2) {
        try {
            return m777a(str, "DFA84B10B7ACDD25");
        } catch (Exception e) {
            return str2;
        }
    }

    /* renamed from: b */
    public static byte[] m785b(String str, byte[] bArr) {
        SecretKeySpec secretKeySpec = new SecretKeySpec(m786c(str, "utf-8"), "AES");
        Cipher instance = Cipher.getInstance("AES/ECB/NoPadding");
        instance.init(2, secretKeySpec);
        return instance.doFinal(bArr);
    }

    /* renamed from: c */
    private static byte[] m786c(String str, String str2) {
        byte[] bArr = new byte[str.length()];
        byte[] bytes = str.substring(0, str.length() / 2).getBytes(str2);
        byte[] bytes2 = str.substring(str.length() / 2).getBytes(str2);
        System.arraycopy(bytes, 0, bArr, 0, bytes.length);
        System.arraycopy(bytes2, 0, bArr, bytes.length, bytes2.length);
        return bArr;
    }
}
