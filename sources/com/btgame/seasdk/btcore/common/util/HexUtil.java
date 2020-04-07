package com.btgame.seasdk.btcore.common.util;

import com.google.common.base.Ascii;

public class HexUtil {
    private static final String HEX_CHARS = "0123456789abcdef";

    private HexUtil() {
    }

    public static String toHexString(byte[] b) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            sb.append(HEX_CHARS.charAt((b[i] >>> 4) & 15));
            sb.append(HEX_CHARS.charAt(b[i] & Ascii.f977SI));
        }
        return sb.toString();
    }

    public static byte[] toByteArray(String s) {
        byte[] buf = new byte[(s.length() / 2)];
        int j = 0;
        for (int i = 0; i < buf.length; i++) {
            int j2 = j + 1;
            j = j2 + 1;
            buf[i] = (byte) ((Character.digit(s.charAt(j), 16) << 4) | Character.digit(s.charAt(j2), 16));
        }
        return buf;
    }
}
