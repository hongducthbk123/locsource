package com.baitian.datasdk.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class CodecUtils {
    public static final String ENCODE = "utf-8";

    public static void swapEach2Bytes(byte[] bytes) {
        int mid = bytes.length / 2;
        for (int i = 0; i < mid; i++) {
            int l = i * 2;
            int r = l + 1;
            bytes[l] = (byte) (bytes[l] ^ 237);
            bytes[r] = (byte) (bytes[r] ^ 237);
            bytes[l] = (byte) (bytes[l] ^ bytes[r]);
            bytes[r] = (byte) (bytes[r] ^ bytes[l]);
            bytes[l] = (byte) (bytes[l] ^ bytes[r]);
        }
    }

    public static String encodeString(String str) {
        if (str == null || "".equals(str)) {
            System.out.println("str == null || ");
            return null;
        }
        byte[] bytes = null;
        try {
            bytes = str.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            System.out.println("UnsupportedEncodingException----> " + str);
            e.printStackTrace();
        }
        swapEach2Bytes(bytes);
        try {
            return new String(bytes, "utf-8");
        } catch (UnsupportedEncodingException e2) {
            System.out.println("UnsupportedEncodingException2----> " + str);
            e2.printStackTrace();
            return null;
        }
    }

    public static void encodewithcompress(String str, OutputStream out) throws IOException {
        byte[] bytes = null;
        try {
            bytes = str.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            System.out.println("UnsupportedEncodingException----> " + str);
            e.printStackTrace();
        }
        swapEach2Bytes(bytes);
        compress(bytes, out);
    }

    public static void compress(byte[] bytes, OutputStream out) throws IOException {
        GZIPOutputStream gzip = new GZIPOutputStream(out);
        gzip.write(bytes);
        gzip.finish();
    }

    public static void compress(String str, OutputStream out) throws IOException {
        byte[] bytes = str.getBytes("utf-8");
        GZIPOutputStream gzip = new GZIPOutputStream(out);
        gzip.write(bytes);
        gzip.finish();
    }

    public static byte[] uncompress(InputStream in) throws IOException {
        GZIPInputStream gunzip = new GZIPInputStream(in);
        byte[] bytes = new byte[0];
        byte[] buffer = new byte[1024];
        while (true) {
            int n = gunzip.read(buffer);
            if (n < 0) {
                return bytes;
            }
            byte[] old = bytes;
            bytes = new byte[(old.length + n)];
            if (old.length > 0) {
                System.arraycopy(old, 0, bytes, 0, old.length);
            }
            System.arraycopy(buffer, 0, bytes, old.length, n);
        }
    }

    public static String decodewithUnCompress(InputStream in) throws IOException {
        GZIPInputStream gunzip = new GZIPInputStream(in);
        byte[] bytes = new byte[0];
        byte[] buffer = new byte[1024];
        while (true) {
            int n = gunzip.read(buffer);
            if (n >= 0) {
                byte[] old = bytes;
                bytes = new byte[(old.length + n)];
                if (old.length > 0) {
                    System.arraycopy(old, 0, bytes, 0, old.length);
                }
                System.arraycopy(buffer, 0, bytes, old.length, n);
            } else {
                swapEach2Bytes(bytes);
                return new String(bytes, 0, bytes.length, "utf-8");
            }
        }
    }
}
