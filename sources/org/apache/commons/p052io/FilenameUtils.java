package org.apache.commons.p052io;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Stack;
import p004cn.jiguang.net.HttpUtils;

/* renamed from: org.apache.commons.io.FilenameUtils */
public class FilenameUtils {
    public static final char EXTENSION_SEPARATOR = '.';
    public static final String EXTENSION_SEPARATOR_STR = Character.toString(EXTENSION_SEPARATOR);
    private static final int NOT_FOUND = -1;
    private static final char OTHER_SEPARATOR;
    private static final char SYSTEM_SEPARATOR = File.separatorChar;
    private static final char UNIX_SEPARATOR = '/';
    private static final char WINDOWS_SEPARATOR = '\\';

    static {
        if (isSystemWindows()) {
            OTHER_SEPARATOR = '/';
        } else {
            OTHER_SEPARATOR = '\\';
        }
    }

    static boolean isSystemWindows() {
        return SYSTEM_SEPARATOR == '\\';
    }

    private static boolean isSeparator(char ch) {
        return ch == '/' || ch == '\\';
    }

    public static String normalize(String filename) {
        return doNormalize(filename, SYSTEM_SEPARATOR, true);
    }

    public static String normalize(String filename, boolean unixSeparator) {
        return doNormalize(filename, unixSeparator ? '/' : '\\', true);
    }

    public static String normalizeNoEndSeparator(String filename) {
        return doNormalize(filename, SYSTEM_SEPARATOR, false);
    }

    public static String normalizeNoEndSeparator(String filename, boolean unixSeparator) {
        return doNormalize(filename, unixSeparator ? '/' : '\\', false);
    }

    private static String doNormalize(String filename, char separator, boolean keepSeparator) {
        if (filename == null) {
            return null;
        }
        failIfNullBytePresent(filename);
        int size = filename.length();
        if (size == 0) {
            return filename;
        }
        int prefix = getPrefixLength(filename);
        if (prefix < 0) {
            return null;
        }
        char[] array = new char[(size + 2)];
        filename.getChars(0, filename.length(), array, 0);
        char otherSeparator = separator == SYSTEM_SEPARATOR ? OTHER_SEPARATOR : SYSTEM_SEPARATOR;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == otherSeparator) {
                array[i] = separator;
            }
        }
        boolean lastIsDirectory = true;
        if (array[size - 1] != separator) {
            int size2 = size + 1;
            array[size] = separator;
            lastIsDirectory = false;
            size = size2;
        }
        int i2 = prefix + 1;
        while (i2 < size) {
            if (array[i2] == separator && array[i2 - 1] == separator) {
                System.arraycopy(array, i2, array, i2 - 1, size - i2);
                size--;
                i2--;
            }
            i2++;
        }
        int i3 = prefix + 1;
        while (i3 < size) {
            if (array[i3] == separator && array[i3 - 1] == '.' && (i3 == prefix + 1 || array[i3 - 2] == separator)) {
                if (i3 == size - 1) {
                    lastIsDirectory = true;
                }
                System.arraycopy(array, i3 + 1, array, i3 - 1, size - i3);
                size -= 2;
                i3--;
            }
            i3++;
        }
        int i4 = prefix + 2;
        while (i4 < size) {
            if (array[i4] == separator && array[i4 - 1] == '.' && array[i4 - 2] == '.' && (i4 == prefix + 2 || array[i4 - 3] == separator)) {
                if (i4 != prefix + 2) {
                    if (i4 == size - 1) {
                        lastIsDirectory = true;
                    }
                    int j = i4 - 4;
                    while (true) {
                        if (j < prefix) {
                            System.arraycopy(array, i4 + 1, array, prefix, size - i4);
                            size -= (i4 + 1) - prefix;
                            i4 = prefix + 1;
                            break;
                        } else if (array[j] == separator) {
                            System.arraycopy(array, i4 + 1, array, j + 1, size - i4);
                            size -= i4 - j;
                            i4 = j + 1;
                            break;
                        } else {
                            j--;
                        }
                    }
                } else {
                    return null;
                }
            }
            i4++;
        }
        if (size <= 0) {
            return "";
        }
        if (size <= prefix) {
            return new String(array, 0, size);
        }
        if (!lastIsDirectory || !keepSeparator) {
            return new String(array, 0, size - 1);
        }
        return new String(array, 0, size);
    }

    public static String concat(String basePath, String fullFilenameToAdd) {
        int prefix = getPrefixLength(fullFilenameToAdd);
        if (prefix < 0) {
            return null;
        }
        if (prefix > 0) {
            return normalize(fullFilenameToAdd);
        }
        if (basePath == null) {
            return null;
        }
        int len = basePath.length();
        if (len == 0) {
            return normalize(fullFilenameToAdd);
        }
        if (isSeparator(basePath.charAt(len - 1))) {
            return normalize(basePath + fullFilenameToAdd);
        }
        return normalize(basePath + '/' + fullFilenameToAdd);
    }

    public static boolean directoryContains(String canonicalParent, String canonicalChild) throws IOException {
        if (canonicalParent == null) {
            throw new IllegalArgumentException("Directory must not be null");
        } else if (canonicalChild != null && !IOCase.SYSTEM.checkEquals(canonicalParent, canonicalChild)) {
            return IOCase.SYSTEM.checkStartsWith(canonicalChild, canonicalParent);
        } else {
            return false;
        }
    }

    public static String separatorsToUnix(String path) {
        return (path == null || path.indexOf(92) == -1) ? path : path.replace('\\', '/');
    }

    public static String separatorsToWindows(String path) {
        return (path == null || path.indexOf(47) == -1) ? path : path.replace('/', '\\');
    }

    public static String separatorsToSystem(String path) {
        if (path == null) {
            return null;
        }
        if (isSystemWindows()) {
            return separatorsToWindows(path);
        }
        return separatorsToUnix(path);
    }

    public static int getPrefixLength(String filename) {
        if (filename == null) {
            return -1;
        }
        int len = filename.length();
        if (len == 0) {
            return 0;
        }
        char ch0 = filename.charAt(0);
        if (ch0 == ':') {
            return -1;
        }
        if (len == 1) {
            if (ch0 == '~') {
                return 2;
            }
            if (!isSeparator(ch0)) {
                return 0;
            }
            return 1;
        } else if (ch0 == '~') {
            int posUnix = filename.indexOf(47, 1);
            int posWin = filename.indexOf(92, 1);
            if (posUnix == -1 && posWin == -1) {
                return len + 1;
            }
            if (posUnix == -1) {
                posUnix = posWin;
            }
            if (posWin == -1) {
                posWin = posUnix;
            }
            return Math.min(posUnix, posWin) + 1;
        } else {
            char ch1 = filename.charAt(1);
            if (ch1 == ':') {
                char ch02 = Character.toUpperCase(ch0);
                if (ch02 < 'A' || ch02 > 'Z') {
                    return -1;
                }
                if (len == 2 || !isSeparator(filename.charAt(2))) {
                    return 2;
                }
                return 3;
            } else if (isSeparator(ch0) && isSeparator(ch1)) {
                int posUnix2 = filename.indexOf(47, 2);
                int posWin2 = filename.indexOf(92, 2);
                if ((posUnix2 == -1 && posWin2 == -1) || posUnix2 == 2 || posWin2 == 2) {
                    return -1;
                }
                if (posUnix2 == -1) {
                    posUnix2 = posWin2;
                }
                if (posWin2 == -1) {
                    posWin2 = posUnix2;
                }
                return Math.min(posUnix2, posWin2) + 1;
            } else if (!isSeparator(ch0)) {
                return 0;
            } else {
                return 1;
            }
        }
    }

    public static int indexOfLastSeparator(String filename) {
        if (filename == null) {
            return -1;
        }
        return Math.max(filename.lastIndexOf(47), filename.lastIndexOf(92));
    }

    public static int indexOfExtension(String filename) {
        if (filename == null) {
            return -1;
        }
        int extensionPos = filename.lastIndexOf(46);
        if (indexOfLastSeparator(filename) > extensionPos) {
            extensionPos = -1;
        }
        return extensionPos;
    }

    public static String getPrefix(String filename) {
        if (filename == null) {
            return null;
        }
        int len = getPrefixLength(filename);
        if (len < 0) {
            return null;
        }
        if (len > filename.length()) {
            failIfNullBytePresent(filename + '/');
            return filename + '/';
        }
        String path = filename.substring(0, len);
        failIfNullBytePresent(path);
        return path;
    }

    public static String getPath(String filename) {
        return doGetPath(filename, 1);
    }

    public static String getPathNoEndSeparator(String filename) {
        return doGetPath(filename, 0);
    }

    private static String doGetPath(String filename, int separatorAdd) {
        if (filename == null) {
            return null;
        }
        int prefix = getPrefixLength(filename);
        if (prefix < 0) {
            return null;
        }
        int index = indexOfLastSeparator(filename);
        int endIndex = index + separatorAdd;
        if (prefix >= filename.length() || index < 0 || prefix >= endIndex) {
            return "";
        }
        String path = filename.substring(prefix, endIndex);
        failIfNullBytePresent(path);
        return path;
    }

    public static String getFullPath(String filename) {
        return doGetFullPath(filename, true);
    }

    public static String getFullPathNoEndSeparator(String filename) {
        return doGetFullPath(filename, false);
    }

    private static String doGetFullPath(String filename, boolean includeSeparator) {
        int i;
        if (filename == null) {
            return null;
        }
        int prefix = getPrefixLength(filename);
        if (prefix < 0) {
            return null;
        }
        if (prefix < filename.length()) {
            int index = indexOfLastSeparator(filename);
            if (index < 0) {
                return filename.substring(0, prefix);
            }
            if (includeSeparator) {
                i = 1;
            } else {
                i = 0;
            }
            int end = index + i;
            if (end == 0) {
                end++;
            }
            return filename.substring(0, end);
        } else if (includeSeparator) {
            return getPrefix(filename);
        } else {
            return filename;
        }
    }

    public static String getName(String filename) {
        if (filename == null) {
            return null;
        }
        failIfNullBytePresent(filename);
        return filename.substring(indexOfLastSeparator(filename) + 1);
    }

    private static void failIfNullBytePresent(String path) {
        int len = path.length();
        for (int i = 0; i < len; i++) {
            if (path.charAt(i) == 0) {
                throw new IllegalArgumentException("Null byte present in file/path name. There are no known legitimate use cases for such data, but several injection attacks may use it");
            }
        }
    }

    public static String getBaseName(String filename) {
        return removeExtension(getName(filename));
    }

    public static String getExtension(String filename) {
        if (filename == null) {
            return null;
        }
        int index = indexOfExtension(filename);
        if (index == -1) {
            return "";
        }
        return filename.substring(index + 1);
    }

    public static String removeExtension(String filename) {
        if (filename == null) {
            return null;
        }
        failIfNullBytePresent(filename);
        int index = indexOfExtension(filename);
        return index != -1 ? filename.substring(0, index) : filename;
    }

    public static boolean equals(String filename1, String filename2) {
        return equals(filename1, filename2, false, IOCase.SENSITIVE);
    }

    public static boolean equalsOnSystem(String filename1, String filename2) {
        return equals(filename1, filename2, false, IOCase.SYSTEM);
    }

    public static boolean equalsNormalized(String filename1, String filename2) {
        return equals(filename1, filename2, true, IOCase.SENSITIVE);
    }

    public static boolean equalsNormalizedOnSystem(String filename1, String filename2) {
        return equals(filename1, filename2, true, IOCase.SYSTEM);
    }

    public static boolean equals(String filename1, String filename2, boolean normalized, IOCase caseSensitivity) {
        if (filename1 != null && filename2 != null) {
            if (normalized) {
                filename1 = normalize(filename1);
                filename2 = normalize(filename2);
                if (filename1 == null || filename2 == null) {
                    throw new NullPointerException("Error normalizing one or both of the file names");
                }
            }
            if (caseSensitivity == null) {
                caseSensitivity = IOCase.SENSITIVE;
            }
            return caseSensitivity.checkEquals(filename1, filename2);
        } else if (filename1 == null && filename2 == null) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isExtension(String filename, String extension) {
        if (filename == null) {
            return false;
        }
        failIfNullBytePresent(filename);
        if (extension != null && !extension.isEmpty()) {
            return getExtension(filename).equals(extension);
        }
        if (indexOfExtension(filename) == -1) {
            return true;
        }
        return false;
    }

    public static boolean isExtension(String filename, String[] extensions) {
        boolean z = true;
        if (filename == null) {
            return false;
        }
        failIfNullBytePresent(filename);
        if (extensions == null || extensions.length == 0) {
            if (indexOfExtension(filename) != -1) {
                z = false;
            }
            return z;
        }
        String fileExt = getExtension(filename);
        for (String extension : extensions) {
            if (fileExt.equals(extension)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isExtension(String filename, Collection<String> extensions) {
        boolean z = true;
        if (filename == null) {
            return false;
        }
        failIfNullBytePresent(filename);
        if (extensions == null || extensions.isEmpty()) {
            if (indexOfExtension(filename) != -1) {
                z = false;
            }
            return z;
        }
        String fileExt = getExtension(filename);
        for (String extension : extensions) {
            if (fileExt.equals(extension)) {
                return true;
            }
        }
        return false;
    }

    public static boolean wildcardMatch(String filename, String wildcardMatcher) {
        return wildcardMatch(filename, wildcardMatcher, IOCase.SENSITIVE);
    }

    public static boolean wildcardMatchOnSystem(String filename, String wildcardMatcher) {
        return wildcardMatch(filename, wildcardMatcher, IOCase.SYSTEM);
    }

    public static boolean wildcardMatch(String filename, String wildcardMatcher, IOCase caseSensitivity) {
        if (filename == null && wildcardMatcher == null) {
            return true;
        }
        if (filename == null || wildcardMatcher == null) {
            return false;
        }
        if (caseSensitivity == null) {
            caseSensitivity = IOCase.SENSITIVE;
        }
        String[] wcs = splitOnTokens(wildcardMatcher);
        boolean anyChars = false;
        int textIdx = 0;
        int wcsIdx = 0;
        Stack<int[]> backtrack = new Stack<>();
        do {
            if (backtrack.size() > 0) {
                int[] array = (int[]) backtrack.pop();
                wcsIdx = array[0];
                textIdx = array[1];
                anyChars = true;
            }
            while (wcsIdx < wcs.length) {
                if (wcs[wcsIdx].equals(HttpUtils.URL_AND_PARA_SEPARATOR)) {
                    textIdx++;
                    if (textIdx > filename.length()) {
                        break;
                    }
                    anyChars = false;
                } else if (wcs[wcsIdx].equals("*")) {
                    anyChars = true;
                    if (wcsIdx == wcs.length - 1) {
                        textIdx = filename.length();
                    }
                } else {
                    if (!anyChars) {
                        if (!caseSensitivity.checkRegionMatches(filename, textIdx, wcs[wcsIdx])) {
                            break;
                        }
                    } else {
                        textIdx = caseSensitivity.checkIndexOf(filename, textIdx, wcs[wcsIdx]);
                        if (textIdx == -1) {
                            break;
                        }
                        int repeat = caseSensitivity.checkIndexOf(filename, textIdx + 1, wcs[wcsIdx]);
                        if (repeat >= 0) {
                            backtrack.push(new int[]{wcsIdx, repeat});
                        }
                    }
                    textIdx += wcs[wcsIdx].length();
                    anyChars = false;
                }
                wcsIdx++;
            }
            if (wcsIdx == wcs.length && textIdx == filename.length()) {
                return true;
            }
        } while (backtrack.size() > 0);
        return false;
    }

    static String[] splitOnTokens(String text) {
        char[] arr$;
        if (text.indexOf(63) == -1 && text.indexOf(42) == -1) {
            return new String[]{text};
        }
        char[] array = text.toCharArray();
        ArrayList<String> list = new ArrayList<>();
        StringBuilder buffer = new StringBuilder();
        char prevChar = 0;
        for (char ch : array) {
            if (ch == '?' || ch == '*') {
                if (buffer.length() != 0) {
                    list.add(buffer.toString());
                    buffer.setLength(0);
                }
                if (ch == '?') {
                    list.add(HttpUtils.URL_AND_PARA_SEPARATOR);
                } else if (prevChar != '*') {
                    list.add("*");
                }
            } else {
                buffer.append(ch);
            }
            prevChar = ch;
        }
        if (buffer.length() != 0) {
            list.add(buffer.toString());
        }
        return (String[]) list.toArray(new String[list.size()]);
    }
}
