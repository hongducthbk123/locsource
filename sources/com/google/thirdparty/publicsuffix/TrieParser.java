package com.google.thirdparty.publicsuffix;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;
import com.google.common.collect.Lists;

@GwtCompatible
class TrieParser {
    private static final Joiner PREFIX_JOINER = Joiner.m1486on("");

    TrieParser() {
    }

    static ImmutableMap<String, PublicSuffixType> parseTrie(CharSequence encoded) {
        Builder<String, PublicSuffixType> builder = ImmutableMap.builder();
        int encodedLen = encoded.length();
        int idx = 0;
        while (idx < encodedLen) {
            idx += doParseTrieToBuilder(Lists.newLinkedList(), encoded.subSequence(idx, encodedLen), builder);
        }
        return builder.build();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0064, code lost:
        r3 = r3 + 1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int doParseTrieToBuilder(java.util.List<java.lang.CharSequence> r10, java.lang.CharSequence r11, com.google.common.collect.ImmutableMap.Builder<java.lang.String, com.google.thirdparty.publicsuffix.PublicSuffixType> r12) {
        /*
            r9 = 58
            r8 = 33
            r7 = 0
            r6 = 63
            r5 = 44
            int r2 = r11.length()
            r3 = 0
            r0 = 0
        L_0x000f:
            if (r3 >= r2) goto L_0x0021
            char r0 = r11.charAt(r3)
            r4 = 38
            if (r0 == r4) goto L_0x0021
            if (r0 == r6) goto L_0x0021
            if (r0 == r8) goto L_0x0021
            if (r0 == r9) goto L_0x0021
            if (r0 != r5) goto L_0x006a
        L_0x0021:
            java.lang.CharSequence r4 = r11.subSequence(r7, r3)
            java.lang.CharSequence r4 = reverse(r4)
            r10.add(r7, r4)
            if (r0 == r8) goto L_0x0034
            if (r0 == r6) goto L_0x0034
            if (r0 == r9) goto L_0x0034
            if (r0 != r5) goto L_0x0047
        L_0x0034:
            com.google.common.base.Joiner r4 = PREFIX_JOINER
            java.lang.String r1 = r4.join(r10)
            int r4 = r1.length()
            if (r4 <= 0) goto L_0x0047
            com.google.thirdparty.publicsuffix.PublicSuffixType r4 = com.google.thirdparty.publicsuffix.PublicSuffixType.fromCode(r0)
            r12.put(r1, r4)
        L_0x0047:
            int r3 = r3 + 1
            if (r0 == r6) goto L_0x0066
            if (r0 == r5) goto L_0x0066
        L_0x004d:
            if (r3 >= r2) goto L_0x0066
            java.lang.CharSequence r4 = r11.subSequence(r3, r2)
            int r4 = doParseTrieToBuilder(r10, r4, r12)
            int r3 = r3 + r4
            char r4 = r11.charAt(r3)
            if (r4 == r6) goto L_0x0064
            char r4 = r11.charAt(r3)
            if (r4 != r5) goto L_0x004d
        L_0x0064:
            int r3 = r3 + 1
        L_0x0066:
            r10.remove(r7)
            return r3
        L_0x006a:
            int r3 = r3 + 1
            goto L_0x000f
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.thirdparty.publicsuffix.TrieParser.doParseTrieToBuilder(java.util.List, java.lang.CharSequence, com.google.common.collect.ImmutableMap$Builder):int");
    }

    private static CharSequence reverse(CharSequence s) {
        int length = s.length();
        if (length <= 1) {
            return s;
        }
        char[] buffer = new char[length];
        buffer[0] = s.charAt(length - 1);
        for (int i = 1; i < length; i++) {
            buffer[i] = s.charAt((length - 1) - i);
            if (Character.isSurrogatePair(buffer[i], buffer[i - 1])) {
                swap(buffer, i - 1, i);
            }
        }
        return new String(buffer);
    }

    private static void swap(char[] buffer, int f, int s) {
        char tmp = buffer[f];
        buffer[f] = buffer[s];
        buffer[s] = tmp;
    }
}
