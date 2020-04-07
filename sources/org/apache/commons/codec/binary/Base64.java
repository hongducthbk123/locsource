package org.apache.commons.codec.binary;

import com.google.android.vending.expansion.downloader.impl.DownloaderService;
import com.google.common.base.Ascii;
import com.google.common.primitives.UnsignedBytes;
import org.apache.commons.codec.BinaryDecoder;
import org.apache.commons.codec.BinaryEncoder;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.p052io.IOUtils;

public class Base64 implements BinaryEncoder, BinaryDecoder {
    static final int BASELENGTH = 255;
    static final byte[] CHUNK_SEPARATOR = IOUtils.LINE_SEPARATOR_WINDOWS.getBytes();
    static final int CHUNK_SIZE = 76;
    static final int EIGHTBIT = 8;
    static final int FOURBYTE = 4;
    static final int LOOKUPLENGTH = 64;
    static final byte PAD = 61;
    static final int SIGN = -128;
    static final int SIXTEENBIT = 16;
    static final int TWENTYFOURBITGROUP = 24;
    private static byte[] base64Alphabet = new byte[255];
    private static byte[] lookUpBase64Alphabet = new byte[64];

    static {
        for (int i = 0; i < 255; i++) {
            base64Alphabet[i] = -1;
        }
        for (int i2 = 90; i2 >= 65; i2--) {
            base64Alphabet[i2] = (byte) (i2 - 65);
        }
        for (int i3 = 122; i3 >= 97; i3--) {
            base64Alphabet[i3] = (byte) ((i3 - 97) + 26);
        }
        for (int i4 = 57; i4 >= 48; i4--) {
            base64Alphabet[i4] = (byte) ((i4 - 48) + 52);
        }
        base64Alphabet[43] = 62;
        base64Alphabet[47] = 63;
        for (int i5 = 0; i5 <= 25; i5++) {
            lookUpBase64Alphabet[i5] = (byte) (i5 + 65);
        }
        int i6 = 26;
        int j = 0;
        while (i6 <= 51) {
            lookUpBase64Alphabet[i6] = (byte) (j + 97);
            i6++;
            j++;
        }
        int i7 = 52;
        int j2 = 0;
        while (i7 <= 61) {
            lookUpBase64Alphabet[i7] = (byte) (j2 + 48);
            i7++;
            j2++;
        }
        lookUpBase64Alphabet[62] = 43;
        lookUpBase64Alphabet[63] = 47;
    }

    private static boolean isBase64(byte octect) {
        if (octect != 61 && base64Alphabet[octect] == -1) {
            return false;
        }
        return true;
    }

    public static boolean isArrayByteBase64(byte[] arrayOctect) {
        if (length == 0) {
            return true;
        }
        for (byte isBase64 : discardWhitespace(arrayOctect)) {
            if (!isBase64(isBase64)) {
                return false;
            }
        }
        return true;
    }

    public static byte[] encodeBase64(byte[] binaryData) {
        return encodeBase64(binaryData, false);
    }

    public static byte[] encodeBase64Chunked(byte[] binaryData) {
        return encodeBase64(binaryData, true);
    }

    public Object decode(Object pObject) throws DecoderException {
        if (pObject instanceof byte[]) {
            return decode((byte[]) pObject);
        }
        throw new DecoderException("Parameter supplied to Base64 decode is not a byte[]");
    }

    public byte[] decode(byte[] pArray) {
        return decodeBase64(pArray);
    }

    public static byte[] encodeBase64(byte[] binaryData, boolean isChunked) {
        int encodedDataLength;
        byte val3;
        int lengthDataBits = binaryData.length * 8;
        int fewerThan24bits = lengthDataBits % 24;
        int numberTriplets = lengthDataBits / 24;
        int nbrChunks = 0;
        if (fewerThan24bits != 0) {
            encodedDataLength = (numberTriplets + 1) * 4;
        } else {
            encodedDataLength = numberTriplets * 4;
        }
        if (isChunked) {
            if (CHUNK_SEPARATOR.length == 0) {
                nbrChunks = 0;
            } else {
                nbrChunks = (int) Math.ceil((double) (((float) encodedDataLength) / 76.0f));
            }
            encodedDataLength += CHUNK_SEPARATOR.length * nbrChunks;
        }
        byte[] encodedData = new byte[encodedDataLength];
        int encodedIndex = 0;
        int nextSeparatorIndex = 76;
        int chunksSoFar = 0;
        int i = 0;
        while (i < numberTriplets) {
            int dataIndex = i * 3;
            byte b1 = binaryData[dataIndex];
            byte b2 = binaryData[dataIndex + 1];
            byte b3 = binaryData[dataIndex + 2];
            byte l = (byte) (b2 & Ascii.f977SI);
            byte k = (byte) (b1 & 3);
            byte val1 = (b1 & UnsignedBytes.MAX_POWER_OF_TWO) == 0 ? (byte) (b1 >> 2) : (byte) ((b1 >> 2) ^ DownloaderService.STATUS_RUNNING);
            byte val2 = (b2 & UnsignedBytes.MAX_POWER_OF_TWO) == 0 ? (byte) (b2 >> 4) : (byte) ((b2 >> 4) ^ 240);
            if ((b3 & UnsignedBytes.MAX_POWER_OF_TWO) == 0) {
                val3 = (byte) (b3 >> 6);
            } else {
                val3 = (byte) ((b3 >> 6) ^ 252);
            }
            encodedData[encodedIndex] = lookUpBase64Alphabet[val1];
            encodedData[encodedIndex + 1] = lookUpBase64Alphabet[(k << 4) | val2];
            encodedData[encodedIndex + 2] = lookUpBase64Alphabet[(l << 2) | val3];
            encodedData[encodedIndex + 3] = lookUpBase64Alphabet[b3 & 63];
            encodedIndex += 4;
            if (isChunked && encodedIndex == nextSeparatorIndex) {
                System.arraycopy(CHUNK_SEPARATOR, 0, encodedData, encodedIndex, CHUNK_SEPARATOR.length);
                chunksSoFar++;
                nextSeparatorIndex = ((chunksSoFar + 1) * 76) + (CHUNK_SEPARATOR.length * chunksSoFar);
                encodedIndex += CHUNK_SEPARATOR.length;
            }
            i++;
        }
        int dataIndex2 = i * 3;
        if (fewerThan24bits == 8) {
            byte b12 = binaryData[dataIndex2];
            byte k2 = (byte) (b12 & 3);
            encodedData[encodedIndex] = lookUpBase64Alphabet[(b12 & UnsignedBytes.MAX_POWER_OF_TWO) == 0 ? (byte) (b12 >> 2) : (byte) ((b12 >> 2) ^ DownloaderService.STATUS_RUNNING)];
            encodedData[encodedIndex + 1] = lookUpBase64Alphabet[k2 << 4];
            encodedData[encodedIndex + 2] = PAD;
            encodedData[encodedIndex + 3] = PAD;
        } else if (fewerThan24bits == 16) {
            byte b13 = binaryData[dataIndex2];
            byte b22 = binaryData[dataIndex2 + 1];
            byte l2 = (byte) (b22 & Ascii.f977SI);
            byte k3 = (byte) (b13 & 3);
            byte val12 = (b13 & UnsignedBytes.MAX_POWER_OF_TWO) == 0 ? (byte) (b13 >> 2) : (byte) ((b13 >> 2) ^ DownloaderService.STATUS_RUNNING);
            byte val22 = (b22 & UnsignedBytes.MAX_POWER_OF_TWO) == 0 ? (byte) (b22 >> 4) : (byte) ((b22 >> 4) ^ 240);
            encodedData[encodedIndex] = lookUpBase64Alphabet[val12];
            encodedData[encodedIndex + 1] = lookUpBase64Alphabet[(k3 << 4) | val22];
            encodedData[encodedIndex + 2] = lookUpBase64Alphabet[l2 << 2];
            encodedData[encodedIndex + 3] = PAD;
        }
        if (isChunked && chunksSoFar < nbrChunks) {
            System.arraycopy(CHUNK_SEPARATOR, 0, encodedData, encodedDataLength - CHUNK_SEPARATOR.length, CHUNK_SEPARATOR.length);
        }
        return encodedData;
    }

    public static byte[] decodeBase64(byte[] base64Data) {
        byte[] base64Data2 = discardNonBase64(base64Data);
        if (base64Data2.length == 0) {
            return new byte[0];
        }
        int numberQuadruple = base64Data2.length / 4;
        int encodedIndex = 0;
        int lastData = base64Data2.length;
        while (base64Data2[lastData - 1] == 61) {
            lastData--;
            if (lastData == 0) {
                return new byte[0];
            }
        }
        byte[] decodedData = new byte[(lastData - numberQuadruple)];
        for (int i = 0; i < numberQuadruple; i++) {
            int dataIndex = i * 4;
            byte marker0 = base64Data2[dataIndex + 2];
            byte marker1 = base64Data2[dataIndex + 3];
            byte b1 = base64Alphabet[base64Data2[dataIndex]];
            byte b2 = base64Alphabet[base64Data2[dataIndex + 1]];
            if (marker0 != 61 && marker1 != 61) {
                byte b3 = base64Alphabet[marker0];
                byte b4 = base64Alphabet[marker1];
                decodedData[encodedIndex] = (byte) ((b1 << 2) | (b2 >> 4));
                decodedData[encodedIndex + 1] = (byte) (((b2 & Ascii.f977SI) << 4) | ((b3 >> 2) & 15));
                decodedData[encodedIndex + 2] = (byte) ((b3 << 6) | b4);
            } else if (marker0 == 61) {
                decodedData[encodedIndex] = (byte) ((b1 << 2) | (b2 >> 4));
            } else if (marker1 == 61) {
                byte b32 = base64Alphabet[marker0];
                decodedData[encodedIndex] = (byte) ((b1 << 2) | (b2 >> 4));
                decodedData[encodedIndex + 1] = (byte) (((b2 & Ascii.f977SI) << 4) | ((b32 >> 2) & 15));
            }
            encodedIndex += 3;
        }
        return decodedData;
    }

    static byte[] discardWhitespace(byte[] data) {
        byte[] groomedData = new byte[data.length];
        int bytesCopied = 0;
        for (int i = 0; i < data.length; i++) {
            switch (data[i]) {
                case 9:
                case 10:
                case 13:
                case 32:
                    break;
                default:
                    int bytesCopied2 = bytesCopied + 1;
                    groomedData[bytesCopied] = data[i];
                    bytesCopied = bytesCopied2;
                    break;
            }
        }
        byte[] packedData = new byte[bytesCopied];
        System.arraycopy(groomedData, 0, packedData, 0, bytesCopied);
        return packedData;
    }

    static byte[] discardNonBase64(byte[] data) {
        byte[] groomedData = new byte[data.length];
        int bytesCopied = 0;
        for (int i = 0; i < data.length; i++) {
            if (isBase64(data[i])) {
                int bytesCopied2 = bytesCopied + 1;
                groomedData[bytesCopied] = data[i];
                bytesCopied = bytesCopied2;
            }
        }
        byte[] packedData = new byte[bytesCopied];
        System.arraycopy(groomedData, 0, packedData, 0, bytesCopied);
        return packedData;
    }

    public Object encode(Object pObject) throws EncoderException {
        if (pObject instanceof byte[]) {
            return encode((byte[]) pObject);
        }
        throw new EncoderException("Parameter supplied to Base64 encode is not a byte[]");
    }

    public byte[] encode(byte[] pArray) {
        return encodeBase64(pArray, false);
    }
}
