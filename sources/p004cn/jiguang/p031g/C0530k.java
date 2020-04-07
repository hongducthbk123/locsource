package p004cn.jiguang.p031g;

import com.adjust.sdk.Constants;
import com.google.common.base.Ascii;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;

/* renamed from: cn.jiguang.g.k */
public final class C0530k {

    /* renamed from: a */
    private static String f610a = "0123456789ABCDEF";

    /* renamed from: a */
    public static CharSequence m1098a(CharSequence charSequence, int i) {
        return (i < 0 || i >= charSequence.length()) ? charSequence : charSequence.subSequence(0, i);
    }

    /* renamed from: a */
    public static boolean m1099a(String str) {
        return str == null || str.length() == 0 || str.trim().length() == 0;
    }

    /* renamed from: a */
    public static boolean m1100a(String str, String str2) {
        return (str == null || str2 == null || !str.equals(str2)) ? false : true;
    }

    /* renamed from: b */
    public static String m1101b(String str) {
        if (str == null || "".equals(str)) {
            return null;
        }
        try {
            MessageDigest instance = MessageDigest.getInstance(Constants.MD5);
            instance.update(str.getBytes());
            byte[] digest = instance.digest();
            if (digest == null) {
                return "";
            }
            StringBuffer stringBuffer = new StringBuffer(digest.length * 2);
            for (byte b : digest) {
                stringBuffer.append("0123456789ABCDEF".charAt((b >> 4) & 15)).append("0123456789ABCDEF".charAt(b & Ascii.f977SI));
            }
            return stringBuffer.toString();
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    /* renamed from: c */
    public static String m1102c(String str) {
        return m1099a(str) ? "" : Pattern.compile("[^\\w#$@\\-一-龥]+").matcher(str).replaceAll("");
    }

    /* renamed from: d */
    public static boolean m1103d(String str) {
        if (m1099a(str)) {
            return false;
        }
        try {
            return Pattern.compile("[\\x20-\\x7E]+").matcher(str).matches();
        } catch (Throwable th) {
            return true;
        }
    }

    /* renamed from: e */
    public static boolean m1104e(String str) {
        if (m1099a(str)) {
            return false;
        }
        try {
            return Pattern.compile("([A-Fa-f0-9]{2}[-:]){5,}[A-Fa-f0-9]{2}").matcher(str).matches();
        } catch (Throwable th) {
            return true;
        }
    }
}
