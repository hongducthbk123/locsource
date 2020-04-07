package com.google.common.base;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import javax.annotation.CheckReturnValue;

@GwtCompatible
public final class Ascii {
    public static final byte ACK = 6;
    public static final byte BEL = 7;

    /* renamed from: BS */
    public static final byte f967BS = 8;
    public static final byte CAN = 24;

    /* renamed from: CR */
    public static final byte f968CR = 13;
    public static final byte DC1 = 17;
    public static final byte DC2 = 18;
    public static final byte DC3 = 19;
    public static final byte DC4 = 20;
    public static final byte DEL = Byte.MAX_VALUE;
    public static final byte DLE = 16;

    /* renamed from: EM */
    public static final byte f969EM = 25;
    public static final byte ENQ = 5;
    public static final byte EOT = 4;
    public static final byte ESC = 27;
    public static final byte ETB = 23;
    public static final byte ETX = 3;

    /* renamed from: FF */
    public static final byte f970FF = 12;

    /* renamed from: FS */
    public static final byte f971FS = 28;

    /* renamed from: GS */
    public static final byte f972GS = 29;

    /* renamed from: HT */
    public static final byte f973HT = 9;

    /* renamed from: LF */
    public static final byte f974LF = 10;
    public static final char MAX = '';
    public static final char MIN = '\u0000';
    public static final byte NAK = 21;

    /* renamed from: NL */
    public static final byte f975NL = 10;
    public static final byte NUL = 0;

    /* renamed from: RS */
    public static final byte f976RS = 30;

    /* renamed from: SI */
    public static final byte f977SI = 15;

    /* renamed from: SO */
    public static final byte f978SO = 14;
    public static final byte SOH = 1;

    /* renamed from: SP */
    public static final byte f979SP = 32;
    public static final byte SPACE = 32;
    public static final byte STX = 2;
    public static final byte SUB = 26;
    public static final byte SYN = 22;

    /* renamed from: US */
    public static final byte f980US = 31;

    /* renamed from: VT */
    public static final byte f981VT = 11;
    public static final byte XOFF = 19;
    public static final byte XON = 17;

    private Ascii() {
    }

    public static String toLowerCase(String string) {
        int length = string.length();
        int i = 0;
        while (i < length) {
            if (isUpperCase(string.charAt(i))) {
                char[] chars = string.toCharArray();
                while (i < length) {
                    char c = chars[i];
                    if (isUpperCase(c)) {
                        chars[i] = (char) (c ^ ' ');
                    }
                    i++;
                }
                return String.valueOf(chars);
            }
            i++;
        }
        return string;
    }

    public static String toLowerCase(CharSequence chars) {
        if (chars instanceof String) {
            return toLowerCase((String) chars);
        }
        int length = chars.length();
        StringBuilder builder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            builder.append(toLowerCase(chars.charAt(i)));
        }
        return builder.toString();
    }

    public static char toLowerCase(char c) {
        return isUpperCase(c) ? (char) (c ^ ' ') : c;
    }

    public static String toUpperCase(String string) {
        int length = string.length();
        int i = 0;
        while (i < length) {
            if (isLowerCase(string.charAt(i))) {
                char[] chars = string.toCharArray();
                while (i < length) {
                    char c = chars[i];
                    if (isLowerCase(c)) {
                        chars[i] = (char) (c & '_');
                    }
                    i++;
                }
                return String.valueOf(chars);
            }
            i++;
        }
        return string;
    }

    public static String toUpperCase(CharSequence chars) {
        if (chars instanceof String) {
            return toUpperCase((String) chars);
        }
        int length = chars.length();
        StringBuilder builder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            builder.append(toUpperCase(chars.charAt(i)));
        }
        return builder.toString();
    }

    public static char toUpperCase(char c) {
        return isLowerCase(c) ? (char) (c & '_') : c;
    }

    public static boolean isLowerCase(char c) {
        return c >= 'a' && c <= 'z';
    }

    public static boolean isUpperCase(char c) {
        return c >= 'A' && c <= 'Z';
    }

    @CheckReturnValue
    @Beta
    public static String truncate(CharSequence seq, int maxLength, String truncationIndicator) {
        Preconditions.checkNotNull(seq);
        int truncationLength = maxLength - truncationIndicator.length();
        Preconditions.checkArgument(truncationLength >= 0, "maxLength (%s) must be >= length of the truncation indicator (%s)", Integer.valueOf(maxLength), Integer.valueOf(truncationIndicator.length()));
        if (seq.length() <= maxLength) {
            String string = seq.toString();
            if (string.length() <= maxLength) {
                return string;
            }
            seq = string;
        }
        return seq + truncationIndicator;
    }

    @Beta
    public static boolean equalsIgnoreCase(CharSequence s1, CharSequence s2) {
        int length = s1.length();
        if (s1 == s2) {
            return true;
        }
        if (length != s2.length()) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            char c1 = s1.charAt(i);
            char c2 = s2.charAt(i);
            if (c1 != c2) {
                int alphaIndex = getAlphaIndex(c1);
                if (alphaIndex >= 26 || alphaIndex != getAlphaIndex(c2)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static int getAlphaIndex(char c) {
        return (char) ((c | ' ') - 'a');
    }
}
