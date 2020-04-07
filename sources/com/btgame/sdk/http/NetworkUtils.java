package com.btgame.sdk.http;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.text.TextUtils;
import com.btgame.sdk.util.SharedPreferencesUtils;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.apache.http.conn.ssl.SSLSocketFactory;

public class NetworkUtils {
    public static final String MAC_FILE = "mac_file";
    public static final String MAC_key = "mac_key";

    public static class MySSLSocketFactory extends SSLSocketFactory {
        SSLContext sslContext = SSLContext.getInstance(SSLSocketFactory.TLS);

        public MySSLSocketFactory(KeyStore truststore) throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException {
            super(truststore);
            TrustManager tm = new X509TrustManager() {
                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                }

                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                }

                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            };
            this.sslContext.init(null, new TrustManager[]{tm}, new SecureRandom());
        }

        public Socket createSocket(Socket socket, String host, int port, boolean autoClose) throws IOException, UnknownHostException {
            return this.sslContext.getSocketFactory().createSocket(socket, host, port, autoClose);
        }

        public Socket createSocket() throws IOException {
            return this.sslContext.getSocketFactory().createSocket();
        }
    }

    private static boolean isCmwapType(Context ctx) {
        String extraInfo = ((ConnectivityManager) ctx.getSystemService("connectivity")).getActiveNetworkInfo().getExtraInfo();
        if (extraInfo == null) {
            return false;
        }
        if ("cmwap".equalsIgnoreCase(extraInfo) || "3gwap".equalsIgnoreCase(extraInfo) || "uniwap".equalsIgnoreCase(extraInfo)) {
            return true;
        }
        return false;
    }

    public static String getNetworkTypeName(Context ctx) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) ctx.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null) {
            return null;
        }
        if (isWifiNetWork(ctx)) {
            return "WIFI";
        }
        switch (activeNetworkInfo.getSubtype()) {
            case 1:
                return "2G";
            case 2:
                return "2G";
            case 3:
                return "3G";
            case 4:
                return "2G";
            case 5:
                return "3G";
            case 6:
                return "3G";
            case 8:
                return "3G";
            case 12:
                return "3G";
            case 13:
                return "4G";
            case 15:
                return "3G";
            default:
                return null;
        }
    }

    public static boolean isNetworkConnected(Context context) {
        NetworkInfo ni = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return ni != null && ni.isConnectedOrConnecting();
    }

    public static boolean isWifiNetWork(Context context) {
        NetworkInfo info = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (info != null) {
            if (info.getType() == 1) {
                return true;
            }
            if (info.getType() == 0) {
                return false;
            }
        }
        return false;
    }

    public static String getPhoneMac(Context context) {
        String macAddress = SharedPreferencesUtils.getString(context, MAC_FILE, "mac_key");
        if (!TextUtils.isEmpty(macAddress)) {
            return macAddress;
        }
        String macAddress2 = getMacFromDevice(context, 2);
        SharedPreferencesUtils.setString(context, MAC_FILE, "mac_key", macAddress2);
        return macAddress2;
    }

    private static String getMacFromDevice(Context context, int internal) {
        WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
        String mac = tryGetMAC(wifiManager);
        if (!TextUtils.isEmpty(mac)) {
            return mac;
        }
        boolean isOkWifi = tryOpenMAC(wifiManager);
        for (int index = 0; index < internal; index++) {
            if (index != 0) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            mac = tryGetMAC(wifiManager);
            if (!TextUtils.isEmpty(mac)) {
                break;
            }
        }
        if (isOkWifi) {
            tryCloseMAC(wifiManager);
        }
        return mac;
    }

    private static boolean tryOpenMAC(WifiManager manager) {
        int state = manager.getWifiState();
        if (state == 3 || state == 2) {
            return false;
        }
        manager.setWifiEnabled(true);
        return true;
    }

    private static void tryCloseMAC(WifiManager manager) {
        manager.setWifiEnabled(false);
    }

    private static String tryGetMAC(WifiManager manager) {
        WifiInfo wifiInfo = manager.getConnectionInfo();
        if (wifiInfo == null) {
            return null;
        }
        String mac = wifiInfo.getMacAddress();
        if (TextUtils.isEmpty(mac)) {
            return null;
        }
        return mac;
    }
}
