package org.cocos2dx.lib;

import android.util.Log;
import com.google.common.net.HttpHeaders;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.zip.GZIPInputStream;
import java.util.zip.InflaterInputStream;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import org.apache.commons.p052io.IOUtils;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.cookie.ClientCookie;
import org.apache.http.protocol.HTTP;
import p004cn.jiguang.net.HttpUtils;

public class Cocos2dxHttpURLConnection {
    private static final String POST_METHOD = "POST";
    private static final String PUT_METHOD = "PUT";

    static HttpURLConnection createHttpURLConnection(String linkURL) {
        try {
            HttpURLConnection urlConnection = (HttpURLConnection) new URL(linkURL).openConnection();
            urlConnection.setRequestProperty(HttpHeaders.ACCEPT_ENCODING, HTTP.IDENTITY_CODING);
            urlConnection.setDoInput(true);
            return urlConnection;
        } catch (Exception e) {
            Log.e("URLConnection exception", e.toString());
            return null;
        }
    }

    static void setReadAndConnectTimeout(HttpURLConnection urlConnection, int readMiliseconds, int connectMiliseconds) {
        urlConnection.setReadTimeout(readMiliseconds);
        urlConnection.setConnectTimeout(connectMiliseconds);
    }

    static void setRequestMethod(HttpURLConnection urlConnection, String method) {
        try {
            urlConnection.setRequestMethod(method);
            if (method.equalsIgnoreCase("POST") || method.equalsIgnoreCase("PUT")) {
                urlConnection.setDoOutput(true);
            }
        } catch (ProtocolException e) {
            Log.e("URLConnection exception", e.toString());
        }
    }

    static void setVerifySSL(HttpURLConnection urlConnection, String sslFilename) {
        InputStream caInput;
        if (urlConnection instanceof HttpsURLConnection) {
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) urlConnection;
            try {
                if (sslFilename.startsWith(HttpUtils.PATHS_SEPARATOR)) {
                    caInput = new BufferedInputStream(new FileInputStream(sslFilename));
                } else {
                    caInput = new BufferedInputStream(Cocos2dxHelper.getActivity().getAssets().open(sslFilename.substring("assets/".length())));
                }
                Certificate ca = CertificateFactory.getInstance("X.509").generateCertificate(caInput);
                System.out.println("ca=" + ((X509Certificate) ca).getSubjectDN());
                caInput.close();
                KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
                keyStore.load(null, null);
                keyStore.setCertificateEntry("ca", ca);
                TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
                tmf.init(keyStore);
                SSLContext context = SSLContext.getInstance(SSLSocketFactory.TLS);
                context.init(null, tmf.getTrustManagers(), null);
                httpsURLConnection.setSSLSocketFactory(context.getSocketFactory());
            } catch (Exception e) {
                Log.e("URLConnection exception", e.toString());
            }
        }
    }

    static void addRequestHeader(HttpURLConnection urlConnection, String key, String value) {
        urlConnection.setRequestProperty(key, value);
    }

    static int connect(HttpURLConnection http) {
        try {
            http.connect();
            return 0;
        } catch (IOException e) {
            Log.e("cocos2d-x debug info", "come in connect");
            Log.e("cocos2d-x debug info", e.toString());
            return 1;
        }
    }

    static void disconnect(HttpURLConnection http) {
        http.disconnect();
    }

    static void sendRequest(HttpURLConnection http, byte[] byteArray) {
        try {
            OutputStream out = http.getOutputStream();
            if (byteArray != null) {
                out.write(byteArray);
                out.flush();
            }
            out.close();
        } catch (IOException e) {
            Log.e("URLConnection exception", e.toString());
        }
    }

    static String getResponseHeaders(HttpURLConnection http) {
        Map<String, List<String>> headers = http.getHeaderFields();
        if (headers == null) {
            return null;
        }
        String header = "";
        for (Entry<String, List<String>> entry : headers.entrySet()) {
            String key = (String) entry.getKey();
            if (key == null) {
                header = header + listToString((List) entry.getValue(), ",") + IOUtils.LINE_SEPARATOR_UNIX;
            } else {
                header = header + key + ":" + listToString((List) entry.getValue(), ",") + IOUtils.LINE_SEPARATOR_UNIX;
            }
        }
        return header;
    }

    static String getResponseHeaderByIdx(HttpURLConnection http, int idx) {
        Map<String, List<String>> headers = http.getHeaderFields();
        if (headers == null) {
            return null;
        }
        int counter = 0;
        for (Entry<String, List<String>> entry : headers.entrySet()) {
            if (counter == idx) {
                String key = (String) entry.getKey();
                if (key == null) {
                    return listToString((List) entry.getValue(), ",") + IOUtils.LINE_SEPARATOR_UNIX;
                }
                return key + ":" + listToString((List) entry.getValue(), ",") + IOUtils.LINE_SEPARATOR_UNIX;
            }
            counter++;
        }
        return null;
    }

    static String getResponseHeaderByKey(HttpURLConnection http, String key) {
        if (key == null) {
            return null;
        }
        Map<String, List<String>> headers = http.getHeaderFields();
        if (headers == null) {
            return null;
        }
        for (Entry<String, List<String>> entry : headers.entrySet()) {
            if (key.equalsIgnoreCase((String) entry.getKey())) {
                if ("set-cookie".equalsIgnoreCase(key)) {
                    return combinCookies((List) entry.getValue(), http.getURL().getHost());
                }
                return listToString((List) entry.getValue(), ",");
            }
        }
        return null;
    }

    static int getResponseHeaderByKeyInt(HttpURLConnection http, String key) {
        String value = http.getHeaderField(key);
        if (value == null) {
            return 0;
        }
        return Integer.parseInt(value);
    }

    static byte[] getResponseContent(HttpURLConnection http) {
        InputStream in;
        try {
            in = http.getInputStream();
            String contentEncoding = http.getContentEncoding();
            if (contentEncoding != null) {
                if (contentEncoding.equalsIgnoreCase("gzip")) {
                    in = new GZIPInputStream(http.getInputStream());
                } else if (contentEncoding.equalsIgnoreCase("deflate")) {
                    in = new InflaterInputStream(http.getInputStream());
                }
            }
        } catch (IOException e) {
            in = http.getErrorStream();
        } catch (Exception e2) {
            Log.e("URLConnection exception", e2.toString());
            return null;
        }
        try {
            byte[] buffer = new byte[1024];
            ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
            while (true) {
                int size = in.read(buffer, 0, 1024);
                if (size != -1) {
                    bytestream.write(buffer, 0, size);
                } else {
                    byte[] byteArray = bytestream.toByteArray();
                    bytestream.close();
                    return byteArray;
                }
            }
        } catch (Exception e3) {
            Log.e("URLConnection exception", e3.toString());
            return null;
        }
    }

    static int getResponseCode(HttpURLConnection http) {
        int code = 0;
        try {
            return http.getResponseCode();
        } catch (IOException e) {
            Log.e("URLConnection exception", e.toString());
            return code;
        }
    }

    static String getResponseMessage(HttpURLConnection http) {
        try {
            return http.getResponseMessage();
        } catch (IOException e) {
            String msg = e.toString();
            Log.e("URLConnection exception", msg);
            return msg;
        }
    }

    public static String listToString(List<String> list, String strInterVal) {
        if (list == null) {
            return null;
        }
        StringBuilder result = new StringBuilder();
        boolean flag = false;
        for (String str : list) {
            if (flag) {
                result.append(strInterVal);
            }
            if (str == null) {
                str = "";
            }
            result.append(str);
            flag = true;
        }
        return result.toString();
    }

    public static String combinCookies(List<String> list, String hostDomain) {
        String[] parts;
        StringBuilder sbCookies = new StringBuilder();
        String domain = hostDomain;
        String tailmatch = "FALSE";
        String path = HttpUtils.PATHS_SEPARATOR;
        String secure = "FALSE";
        String key = null;
        String value = null;
        String expires = null;
        for (String str : list) {
            for (String part : str.split(";")) {
                int firstIndex = part.indexOf(HttpUtils.EQUAL_SIGN);
                if (-1 != firstIndex) {
                    String[] item = {part.substring(0, firstIndex), part.substring(firstIndex + 1)};
                    if ("expires".equalsIgnoreCase(item[0].trim())) {
                        expires = str2Seconds(item[1].trim());
                    } else if (ClientCookie.PATH_ATTR.equalsIgnoreCase(item[0].trim())) {
                        path = item[1];
                    } else if (ClientCookie.SECURE_ATTR.equalsIgnoreCase(item[0].trim())) {
                        secure = item[1];
                    } else if (ClientCookie.DOMAIN_ATTR.equalsIgnoreCase(item[0].trim())) {
                        domain = item[1];
                    } else if (!"version".equalsIgnoreCase(item[0].trim()) && !ClientCookie.MAX_AGE_ATTR.equalsIgnoreCase(item[0].trim())) {
                        key = item[0];
                        value = item[1];
                    }
                }
            }
            if (domain == null) {
                domain = "none";
            }
            sbCookies.append(domain);
            sbCookies.append(9);
            sbCookies.append(tailmatch);
            sbCookies.append(9);
            sbCookies.append(path);
            sbCookies.append(9);
            sbCookies.append(secure);
            sbCookies.append(9);
            sbCookies.append(expires);
            sbCookies.append("\t");
            sbCookies.append(key);
            sbCookies.append("\t");
            sbCookies.append(value);
            sbCookies.append(10);
        }
        return sbCookies.toString();
    }

    private static String str2Seconds(String strTime) {
        Calendar c = Calendar.getInstance();
        long milliseconds = 0;
        try {
            c.setTime(new SimpleDateFormat("EEE, dd-MMM-yy hh:mm:ss zzz", Locale.US).parse(strTime));
            milliseconds = c.getTimeInMillis() / 1000;
        } catch (ParseException e) {
            Log.e("URLConnection exception", e.toString());
        }
        return Long.toString(milliseconds);
    }
}
