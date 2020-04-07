package com.btgame.seasdk.btcore.common.util.http;

import com.btgame.seasdk.btcore.common.util.BtsdkLog;
import com.btgame.seasdk.btcore.common.util.BuglyHelper;
import com.btgame.seasdk.btcore.common.util.CodecUtils;
import com.btgame.seasdk.btcore.common.util.DebugUtils;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class HttpDataUtil {
    public static byte[] getDatabytes(String postData) {
        byte[] databytes = null;
        if (postData == null || "".equals(postData)) {
            BtsdkLog.m1429d("postData = null");
            return null;
        }
        try {
            if (!DebugUtils.getInstance().isCodeFlag()) {
                databytes = postData.getBytes("utf-8");
            } else {
                byte[] contentdatas = postData.getBytes("utf-8");
                CodecUtils.swapEach2Bytes(contentdatas);
                databytes = compress(contentdatas);
            }
        } catch (UnsupportedEncodingException e) {
            BtsdkLog.m1430e(e.getMessage());
            e.printStackTrace();
        } catch (IOException e2) {
            BtsdkLog.m1430e(e2.getMessage());
            e2.printStackTrace();
        }
        return databytes;
    }

    public static String readJsonData(InputStream in) {
        String tmp = null;
        if (in == null) {
            return null;
        }
        if (!DebugUtils.getInstance().isCodeFlag()) {
            return convertStreamToString(in);
        }
        try {
            tmp = CodecUtils.decodewithUnCompress(in);
            try {
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e2) {
            BtsdkLog.m1429d("uncompress " + e2.getMessage());
            BuglyHelper.postCatchedException((Throwable) e2);
            e2.printStackTrace();
            try {
            } catch (IOException e3) {
                e3.printStackTrace();
            }
        } finally {
            try {
                in.close();
            } catch (IOException e4) {
                e4.printStackTrace();
            }
        }
        BtsdkLog.m1429d("decode respone");
        if (tmp == null || tmp.length() <= 0) {
            return null;
        }
        BtsdkLog.m1429d("respone = " + tmp);
        return tmp;
    }

    private static byte[] compress(byte[] data) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        compress(bais, baos);
        byte[] output = baos.toByteArray();
        baos.flush();
        baos.close();
        bais.close();
        return output;
    }

    private static void compress(InputStream is, OutputStream os) throws IOException {
        GZIPOutputStream gos = new GZIPOutputStream(os);
        byte[] data = new byte[1024];
        while (true) {
            int count = is.read(data, 0, data.length);
            if (count != -1) {
                gos.write(data, 0, count);
            } else {
                gos.finish();
                gos.close();
                return;
            }
        }
    }

    private static byte[] unzip(InputStream in) {
        byte[] bArr = null;
        if (in == null) {
            return bArr;
        }
        try {
            GZIPInputStream gin = new GZIPInputStream(in);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            while (true) {
                int len = gin.read(buf);
                if (len > 0) {
                    out.write(buf, 0, len);
                } else {
                    gin.close();
                    out.close();
                    return out.toByteArray();
                }
            }
        } catch (IOException e) {
            BuglyHelper.postCatchedException((Throwable) e);
            e.printStackTrace();
            return bArr;
        }
    }

    public static String convertStreamToString(InputStream content) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(content));
        StringBuilder sb = new StringBuilder();
        while (true) {
            try {
                String line = reader.readLine();
                if (line != null) {
                    sb.append(line);
                } else {
                    try {
                        break;
                    } catch (IOException e) {
                        BuglyHelper.postCatchedException((Throwable) e);
                        e.printStackTrace();
                    }
                }
            } catch (IOException e2) {
                BuglyHelper.postCatchedException((Throwable) e2);
                e2.printStackTrace();
                try {
                } catch (IOException e3) {
                    BuglyHelper.postCatchedException((Throwable) e3);
                    e3.printStackTrace();
                }
            } finally {
                try {
                    content.close();
                } catch (IOException e4) {
                    BuglyHelper.postCatchedException((Throwable) e4);
                    e4.printStackTrace();
                }
            }
        }
        return sb.toString();
    }
}
