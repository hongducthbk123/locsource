package com.adjust.sdk;

import android.content.Context;
import com.adjust.sdk.UtilNetworking.IConnectionOptions;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.vending.expansion.downloader.Constants;
import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.apache.http.conn.ssl.SSLSocketFactory;

public class AdjustFactory {
    private static IActivityHandler activityHandler = null;
    private static IAttributionHandler attributionHandler = null;
    private static String baseUrl = Constants.BASE_URL;
    private static IConnectionOptions connectionOptions = null;
    private static HttpsURLConnection httpsURLConnection = null;
    private static ILogger logger = null;
    private static long maxDelayStart = -1;
    private static IPackageHandler packageHandler = null;
    private static BackoffStrategy packageHandlerBackoffStrategy = null;
    private static IRequestHandler requestHandler = null;
    private static BackoffStrategy sdkClickBackoffStrategy = null;
    private static ISdkClickHandler sdkClickHandler = null;
    private static long sessionInterval = -1;
    private static long subsessionInterval = -1;
    private static long timerInterval = -1;
    private static long timerStart = -1;
    private static boolean tryInstallReferrer = true;

    public static class URLGetConnection {
        HttpsURLConnection httpsURLConnection;
        URL url;

        URLGetConnection(HttpsURLConnection httpsURLConnection2, URL url2) {
            this.httpsURLConnection = httpsURLConnection2;
            this.url = url2;
        }
    }

    public static IPackageHandler getPackageHandler(IActivityHandler activityHandler2, Context context, boolean startsSending) {
        if (packageHandler == null) {
            return new PackageHandler(activityHandler2, context, startsSending);
        }
        packageHandler.init(activityHandler2, context, startsSending);
        return packageHandler;
    }

    public static IRequestHandler getRequestHandler(IPackageHandler packageHandler2) {
        if (requestHandler == null) {
            return new RequestHandler(packageHandler2);
        }
        requestHandler.init(packageHandler2);
        return requestHandler;
    }

    public static ILogger getLogger() {
        if (logger == null) {
            logger = new Logger();
        }
        return logger;
    }

    public static long getTimerInterval() {
        if (timerInterval == -1) {
            return Constants.WATCHDOG_WAKE_TIMER;
        }
        return timerInterval;
    }

    public static long getTimerStart() {
        if (timerStart == -1) {
            return Constants.WATCHDOG_WAKE_TIMER;
        }
        return timerStart;
    }

    public static long getSessionInterval() {
        if (sessionInterval == -1) {
            return 1800000;
        }
        return sessionInterval;
    }

    public static long getSubsessionInterval() {
        if (subsessionInterval == -1) {
            return 1000;
        }
        return subsessionInterval;
    }

    public static BackoffStrategy getSdkClickBackoffStrategy() {
        if (sdkClickBackoffStrategy == null) {
            return BackoffStrategy.SHORT_WAIT;
        }
        return sdkClickBackoffStrategy;
    }

    public static BackoffStrategy getPackageHandlerBackoffStrategy() {
        if (packageHandlerBackoffStrategy == null) {
            return BackoffStrategy.LONG_WAIT;
        }
        return packageHandlerBackoffStrategy;
    }

    public static IActivityHandler getActivityHandler(AdjustConfig config) {
        if (activityHandler == null) {
            return ActivityHandler.getInstance(config);
        }
        activityHandler.init(config);
        return activityHandler;
    }

    public static IAttributionHandler getAttributionHandler(IActivityHandler activityHandler2, ActivityPackage attributionPackage, boolean startsSending) {
        if (attributionHandler == null) {
            return new AttributionHandler(activityHandler2, attributionPackage, startsSending);
        }
        attributionHandler.init(activityHandler2, attributionPackage, startsSending);
        return attributionHandler;
    }

    public static HttpsURLConnection getHttpsURLConnection(URL url) throws IOException {
        if (httpsURLConnection == null) {
            return (HttpsURLConnection) url.openConnection();
        }
        return httpsURLConnection;
    }

    public static ISdkClickHandler getSdkClickHandler(IActivityHandler activityHandler2, boolean startsSending) {
        if (sdkClickHandler == null) {
            return new SdkClickHandler(activityHandler2, startsSending);
        }
        sdkClickHandler.init(activityHandler2, startsSending);
        return sdkClickHandler;
    }

    public static long getMaxDelayStart() {
        if (maxDelayStart == -1) {
            return 10000;
        }
        return maxDelayStart;
    }

    public static String getBaseUrl() {
        if (baseUrl == null) {
            return Constants.BASE_URL;
        }
        return baseUrl;
    }

    public static IConnectionOptions getConnectionOptions() {
        if (connectionOptions == null) {
            return new ConnectionOptions();
        }
        return connectionOptions;
    }

    public static boolean getTryInstallReferrer() {
        return tryInstallReferrer;
    }

    public static void setPackageHandler(IPackageHandler packageHandler2) {
        packageHandler = packageHandler2;
    }

    public static void setRequestHandler(IRequestHandler requestHandler2) {
        requestHandler = requestHandler2;
    }

    public static void setLogger(ILogger logger2) {
        logger = logger2;
    }

    public static void setTimerInterval(long timerInterval2) {
        timerInterval = timerInterval2;
    }

    public static void setTimerStart(long timerStart2) {
        timerStart = timerStart2;
    }

    public static void setSessionInterval(long sessionInterval2) {
        sessionInterval = sessionInterval2;
    }

    public static void setSubsessionInterval(long subsessionInterval2) {
        subsessionInterval = subsessionInterval2;
    }

    public static void setSdkClickBackoffStrategy(BackoffStrategy sdkClickBackoffStrategy2) {
        sdkClickBackoffStrategy = sdkClickBackoffStrategy2;
    }

    public static void setPackageHandlerBackoffStrategy(BackoffStrategy packageHandlerBackoffStrategy2) {
        packageHandlerBackoffStrategy = packageHandlerBackoffStrategy2;
    }

    public static void setActivityHandler(IActivityHandler activityHandler2) {
        activityHandler = activityHandler2;
    }

    public static void setAttributionHandler(IAttributionHandler attributionHandler2) {
        attributionHandler = attributionHandler2;
    }

    public static void setHttpsURLConnection(HttpsURLConnection httpsURLConnection2) {
        httpsURLConnection = httpsURLConnection2;
    }

    public static void setSdkClickHandler(ISdkClickHandler sdkClickHandler2) {
        sdkClickHandler = sdkClickHandler2;
    }

    public static void setBaseUrl(String baseUrl2) {
        baseUrl = baseUrl2;
    }

    public static void useTestConnectionOptions() {
        connectionOptions = new IConnectionOptions() {
            public void applyConnectionOptions(HttpsURLConnection connection, String clientSdk) {
                new ConnectionOptions().applyConnectionOptions(connection, clientSdk);
                try {
                    SSLContext sc = SSLContext.getInstance(SSLSocketFactory.TLS);
                    sc.init(null, new TrustManager[]{new X509TrustManager() {
                        public X509Certificate[] getAcceptedIssuers() {
                            AdjustFactory.getLogger().verbose("getAcceptedIssuers", new Object[0]);
                            return null;
                        }

                        public void checkClientTrusted(X509Certificate[] certs, String authType) {
                            AdjustFactory.getLogger().verbose("checkClientTrusted ", new Object[0]);
                        }

                        public void checkServerTrusted(X509Certificate[] certs, String authType) throws CertificateException {
                            AdjustFactory.getLogger().verbose("checkServerTrusted ", new Object[0]);
                            try {
                                if (!AdjustFactory.byte2HexFormatted(MessageDigest.getInstance("SHA1").digest(certs[0].getEncoded())).equalsIgnoreCase("7BCFF44099A35BC093BB48C5A6B9A516CDFDA0D1")) {
                                    throw new CertificateException();
                                }
                            } catch (NoSuchAlgorithmException e) {
                                AdjustFactory.getLogger().error("testingMode error %s", e.getMessage());
                            } catch (CertificateEncodingException e2) {
                                AdjustFactory.getLogger().error("testingMode error %s", e2.getMessage());
                            }
                        }
                    }}, new SecureRandom());
                    connection.setSSLSocketFactory(sc.getSocketFactory());
                    connection.setHostnameVerifier(new HostnameVerifier() {
                        public boolean verify(String hostname, SSLSession session) {
                            AdjustFactory.getLogger().verbose("verify hostname ", new Object[0]);
                            return true;
                        }
                    });
                } catch (Exception e) {
                    AdjustFactory.getLogger().error("testingMode error %s", e.getMessage());
                }
            }
        };
    }

    public static void setTryInstallReferrer(boolean tryInstallReferrer2) {
        tryInstallReferrer = tryInstallReferrer2;
    }

    /* access modifiers changed from: private */
    public static String byte2HexFormatted(byte[] arr) {
        StringBuilder str = new StringBuilder(arr.length * 2);
        for (byte hexString : arr) {
            String h = Integer.toHexString(hexString);
            int l = h.length();
            if (l == 1) {
                h = AppEventsConstants.EVENT_PARAM_VALUE_NO + h;
            }
            if (l > 2) {
                h = h.substring(l - 2, l);
            }
            str.append(h.toUpperCase());
        }
        return str.toString();
    }

    public static void teardown(Context context) {
        if (context != null) {
            ActivityHandler.deleteState(context);
            PackageHandler.deleteState(context);
        }
        packageHandler = null;
        requestHandler = null;
        attributionHandler = null;
        activityHandler = null;
        logger = null;
        httpsURLConnection = null;
        sdkClickHandler = null;
        timerInterval = -1;
        timerStart = -1;
        sessionInterval = -1;
        subsessionInterval = -1;
        sdkClickBackoffStrategy = null;
        packageHandlerBackoffStrategy = null;
        maxDelayStart = -1;
        baseUrl = Constants.BASE_URL;
        connectionOptions = null;
        tryInstallReferrer = true;
    }
}
