package com.adjust.sdk;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Looper;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.facebook.places.model.PlaceFields;
import java.io.ObjectInputStream.GetField;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Pattern;
import org.apache.commons.p052io.IOUtils;

public class Util {
    private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'Z";
    public static final DecimalFormat SecondsDisplayFormat = newLocalDecimalFormat();
    public static final SimpleDateFormat dateFormatter = new SimpleDateFormat(DATE_FORMAT, Locale.US);
    private static final String fieldReadErrorMessage = "Unable to read '%s' field in migration device with message (%s)";

    private static ILogger getLogger() {
        return AdjustFactory.getLogger();
    }

    protected static String createUuid() {
        return UUID.randomUUID().toString();
    }

    private static DecimalFormat newLocalDecimalFormat() {
        return new DecimalFormat("0.0", new DecimalFormatSymbols(Locale.US));
    }

    public static String quote(String string) {
        if (string == null) {
            return null;
        }
        if (!Pattern.compile("\\s").matcher(string).find()) {
            return string;
        }
        return formatString("'%s'", string);
    }

    public static String getPlayAdId(Context context) {
        return Reflection.getPlayAdId(context);
    }

    public static void runInBackground(Runnable command) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            command.run();
            return;
        }
        new AsyncTask<Object, Void, Void>() {
            /* access modifiers changed from: protected */
            public Void doInBackground(Object... params) {
                params[0].run();
                return null;
            }
        }.execute(new Object[]{command});
    }

    public static void getGoogleAdId(Context context, final OnDeviceIdsRead onDeviceIdRead) {
        ILogger logger = AdjustFactory.getLogger();
        if (Looper.myLooper() != Looper.getMainLooper()) {
            logger.debug("GoogleAdId being read in the background", new Object[0]);
            String GoogleAdId = getPlayAdId(context);
            logger.debug("GoogleAdId read " + GoogleAdId, new Object[0]);
            onDeviceIdRead.onGoogleAdIdRead(GoogleAdId);
            return;
        }
        logger.debug("GoogleAdId being read in the foreground", new Object[0]);
        new AsyncTask<Context, Void, String>() {
            /* access modifiers changed from: protected */
            public String doInBackground(Context... params) {
                ILogger logger = AdjustFactory.getLogger();
                String innerResult = Util.getPlayAdId(params[0]);
                logger.debug("GoogleAdId read " + innerResult, new Object[0]);
                return innerResult;
            }

            /* access modifiers changed from: protected */
            public void onPostExecute(String playAdiId) {
                ILogger logger = AdjustFactory.getLogger();
                onDeviceIdRead.onGoogleAdIdRead(playAdiId);
            }
        }.execute(new Context[]{context});
    }

    public static Boolean isPlayTrackingEnabled(Context context) {
        return Reflection.isPlayTrackingEnabled(context);
    }

    public static String getMacAddress(Context context) {
        return Reflection.getMacAddress(context);
    }

    public static Map<String, String> getPluginKeys(Context context) {
        return Reflection.getPluginKeys(context);
    }

    public static String getAndroidId(Context context) {
        return Reflection.getAndroidId(context);
    }

    public static String getTelephonyId(TelephonyManager telephonyManager) {
        return Reflection.getTelephonyId(telephonyManager);
    }

    public static String getIMEI(TelephonyManager telephonyManager) {
        return Reflection.getImei(telephonyManager);
    }

    public static String getMEID(TelephonyManager telephonyManager) {
        return Reflection.getMeid(telephonyManager);
    }

    public static String getIMEI(TelephonyManager telephonyManager, int index) {
        return Reflection.getImei(telephonyManager, index);
    }

    public static String getMEID(TelephonyManager telephonyManager, int index) {
        return Reflection.getMeid(telephonyManager, index);
    }

    public static String getTelephonyId(TelephonyManager telephonyManager, int index) {
        return Reflection.getTelephonyId(telephonyManager, index);
    }

    public static boolean tryAddToStringList(List<String> list, String value) {
        if (value != null && !list.contains(value)) {
            return list.add(value);
        }
        return false;
    }

    public static String getTelephonyIds(TelephonyManager telephonyManager) {
        List<String> telephonyIdList = new ArrayList<>();
        tryAddToStringList(telephonyIdList, getTelephonyId(telephonyManager, 0));
        int i = 1;
        while (i < 10 && tryAddToStringList(telephonyIdList, getTelephonyId(telephonyManager, i))) {
            i++;
        }
        tryAddToStringList(telephonyIdList, getTelephonyId(telephonyManager, ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED));
        return TextUtils.join(",", telephonyIdList);
    }

    public static String getIMEIs(TelephonyManager telephonyManager) {
        List<String> imeiList = new ArrayList<>();
        tryAddToStringList(imeiList, getIMEI(telephonyManager, 0));
        int i = 1;
        while (i < 10 && tryAddToStringList(imeiList, getIMEI(telephonyManager, i))) {
            i++;
        }
        tryAddToStringList(imeiList, getIMEI(telephonyManager, ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED));
        return TextUtils.join(",", imeiList);
    }

    public static String getMEIDs(TelephonyManager telephonyManager) {
        List<String> meidList = new ArrayList<>();
        tryAddToStringList(meidList, getMEID(telephonyManager, 0));
        int i = 1;
        while (i < 10 && tryAddToStringList(meidList, getMEID(telephonyManager, i))) {
            i++;
        }
        tryAddToStringList(meidList, getMEID(telephonyManager, ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED));
        return TextUtils.join(",", meidList);
    }

    /* JADX WARNING: type inference failed for: r1v0 */
    /* JADX WARNING: type inference failed for: r1v1 */
    /* JADX WARNING: type inference failed for: r1v2 */
    /* JADX WARNING: type inference failed for: r1v3, types: [java.io.Closeable] */
    /* JADX WARNING: type inference failed for: r0v0, types: [java.io.BufferedInputStream, java.io.InputStream] */
    /* JADX WARNING: type inference failed for: r1v5 */
    /* JADX WARNING: type inference failed for: r5v0, types: [java.io.ObjectInputStream] */
    /* JADX WARNING: type inference failed for: r1v6 */
    /* JADX WARNING: type inference failed for: r1v7 */
    /* JADX WARNING: type inference failed for: r1v8 */
    /* JADX WARNING: type inference failed for: r1v9 */
    /* JADX WARNING: type inference failed for: r1v10 */
    /* JADX WARNING: type inference failed for: r1v11 */
    /* JADX WARNING: type inference failed for: r1v12 */
    /* JADX WARNING: type inference failed for: r1v13 */
    /* JADX WARNING: type inference failed for: r1v14 */
    /* JADX WARNING: type inference failed for: r1v15 */
    /* JADX WARNING: type inference failed for: r1v16 */
    /* JADX WARNING: type inference failed for: r1v17 */
    /* JADX WARNING: type inference failed for: r1v18 */
    /* JADX WARNING: type inference failed for: r1v19 */
    /* JADX WARNING: type inference failed for: r1v20 */
    /* JADX WARNING: type inference failed for: r1v21 */
    /* JADX WARNING: type inference failed for: r1v22 */
    /* JADX WARNING: type inference failed for: r1v23 */
    /* JADX WARNING: type inference failed for: r1v24 */
    /* JADX WARNING: type inference failed for: r1v25 */
    /* JADX WARNING: type inference failed for: r1v26 */
    /* JADX WARNING: type inference failed for: r1v27 */
    /* JADX WARNING: type inference failed for: r1v28 */
    /* JADX WARNING: type inference failed for: r1v29 */
    /* JADX WARNING: type inference failed for: r1v30 */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x004c, code lost:
        getLogger().debug("%s file not found", r13);
        r1 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0074, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0075, code lost:
        getLogger().error("Failed to open %s file for reading (%s)", r13, r2);
        r1 = r1;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r1v1
      assigns: []
      uses: []
      mth insns count: 87
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
    	at jadx.core.ProcessClass.process(ProcessClass.java:35)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x004b A[ExcHandler: FileNotFoundException (e java.io.FileNotFoundException), PHI: r1 r4 
      PHI: (r1v2 ?) = (r1v7 ?), (r1v11 ?), (r1v13 ?), (r1v14 ?), (r1v16 ?), (r1v19 ?), (r1v21 ?), (r1v24 ?), (r1v26 ?), (r1v30 ?) binds: [B:1:0x0002, B:2:?, B:4:0x0013, B:21:0x0089, B:22:?, B:16:0x005d, B:17:?, B:11:0x0034, B:12:?, B:5:?] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r4v2 'object' T) = (r4v0 'object' T), (r4v0 'object' T), (r4v0 'object' T), (r4v4 'object' T), (r4v4 'object' T), (r4v5 'object' T), (r4v5 'object' T), (r4v6 'object' T), (r4v6 'object' T), (r4v7 'object' T) binds: [B:1:0x0002, B:2:?, B:4:0x0013, B:21:0x0089, B:22:?, B:16:0x005d, B:17:?, B:11:0x0034, B:12:?, B:5:?] A[DONT_GENERATE, DONT_INLINE], Splitter:B:1:0x0002] */
    /* JADX WARNING: Unknown variable types count: 8 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static <T> T readObject(android.content.Context r11, java.lang.String r12, java.lang.String r13, java.lang.Class<T> r14) {
        /*
            r1 = 0
            r4 = 0
            java.io.FileInputStream r3 = r11.openFileInput(r12)     // Catch:{ FileNotFoundException -> 0x004b, Exception -> 0x0074 }
            r1 = r3
            java.io.BufferedInputStream r0 = new java.io.BufferedInputStream     // Catch:{ FileNotFoundException -> 0x004b, Exception -> 0x0074 }
            r0.<init>(r3)     // Catch:{ FileNotFoundException -> 0x004b, Exception -> 0x0074 }
            r1 = r0
            java.io.ObjectInputStream r5 = new java.io.ObjectInputStream     // Catch:{ FileNotFoundException -> 0x004b, Exception -> 0x0074 }
            r5.<init>(r0)     // Catch:{ FileNotFoundException -> 0x004b, Exception -> 0x0074 }
            r1 = r5
            java.lang.Object r6 = r5.readObject()     // Catch:{ ClassNotFoundException -> 0x0033, ClassCastException -> 0x005c, Exception -> 0x0088, FileNotFoundException -> 0x004b }
            java.lang.Object r4 = r14.cast(r6)     // Catch:{ ClassNotFoundException -> 0x0033, ClassCastException -> 0x005c, Exception -> 0x0088, FileNotFoundException -> 0x004b }
            com.adjust.sdk.ILogger r6 = getLogger()     // Catch:{ ClassNotFoundException -> 0x0033, ClassCastException -> 0x005c, Exception -> 0x0088, FileNotFoundException -> 0x004b }
            java.lang.String r7 = "Read %s: %s"
            r8 = 2
            java.lang.Object[] r8 = new java.lang.Object[r8]     // Catch:{ ClassNotFoundException -> 0x0033, ClassCastException -> 0x005c, Exception -> 0x0088, FileNotFoundException -> 0x004b }
            r9 = 0
            r8[r9] = r13     // Catch:{ ClassNotFoundException -> 0x0033, ClassCastException -> 0x005c, Exception -> 0x0088, FileNotFoundException -> 0x004b }
            r9 = 1
            r8[r9] = r4     // Catch:{ ClassNotFoundException -> 0x0033, ClassCastException -> 0x005c, Exception -> 0x0088, FileNotFoundException -> 0x004b }
            r6.debug(r7, r8)     // Catch:{ ClassNotFoundException -> 0x0033, ClassCastException -> 0x005c, Exception -> 0x0088, FileNotFoundException -> 0x004b }
        L_0x002d:
            if (r1 == 0) goto L_0x0032
            r1.close()     // Catch:{ Exception -> 0x00a0 }
        L_0x0032:
            return r4
        L_0x0033:
            r2 = move-exception
            com.adjust.sdk.ILogger r6 = getLogger()     // Catch:{ FileNotFoundException -> 0x004b, Exception -> 0x0074 }
            java.lang.String r7 = "Failed to find %s class (%s)"
            r8 = 2
            java.lang.Object[] r8 = new java.lang.Object[r8]     // Catch:{ FileNotFoundException -> 0x004b, Exception -> 0x0074 }
            r9 = 0
            r8[r9] = r13     // Catch:{ FileNotFoundException -> 0x004b, Exception -> 0x0074 }
            r9 = 1
            java.lang.String r10 = r2.getMessage()     // Catch:{ FileNotFoundException -> 0x004b, Exception -> 0x0074 }
            r8[r9] = r10     // Catch:{ FileNotFoundException -> 0x004b, Exception -> 0x0074 }
            r6.error(r7, r8)     // Catch:{ FileNotFoundException -> 0x004b, Exception -> 0x0074 }
            goto L_0x002d
        L_0x004b:
            r2 = move-exception
            com.adjust.sdk.ILogger r6 = getLogger()
            java.lang.String r7 = "%s file not found"
            r8 = 1
            java.lang.Object[] r8 = new java.lang.Object[r8]
            r9 = 0
            r8[r9] = r13
            r6.debug(r7, r8)
            goto L_0x002d
        L_0x005c:
            r2 = move-exception
            com.adjust.sdk.ILogger r6 = getLogger()     // Catch:{ FileNotFoundException -> 0x004b, Exception -> 0x0074 }
            java.lang.String r7 = "Failed to cast %s object (%s)"
            r8 = 2
            java.lang.Object[] r8 = new java.lang.Object[r8]     // Catch:{ FileNotFoundException -> 0x004b, Exception -> 0x0074 }
            r9 = 0
            r8[r9] = r13     // Catch:{ FileNotFoundException -> 0x004b, Exception -> 0x0074 }
            r9 = 1
            java.lang.String r10 = r2.getMessage()     // Catch:{ FileNotFoundException -> 0x004b, Exception -> 0x0074 }
            r8[r9] = r10     // Catch:{ FileNotFoundException -> 0x004b, Exception -> 0x0074 }
            r6.error(r7, r8)     // Catch:{ FileNotFoundException -> 0x004b, Exception -> 0x0074 }
            goto L_0x002d
        L_0x0074:
            r2 = move-exception
            com.adjust.sdk.ILogger r6 = getLogger()
            java.lang.String r7 = "Failed to open %s file for reading (%s)"
            r8 = 2
            java.lang.Object[] r8 = new java.lang.Object[r8]
            r9 = 0
            r8[r9] = r13
            r9 = 1
            r8[r9] = r2
            r6.error(r7, r8)
            goto L_0x002d
        L_0x0088:
            r2 = move-exception
            com.adjust.sdk.ILogger r6 = getLogger()     // Catch:{ FileNotFoundException -> 0x004b, Exception -> 0x0074 }
            java.lang.String r7 = "Failed to read %s object (%s)"
            r8 = 2
            java.lang.Object[] r8 = new java.lang.Object[r8]     // Catch:{ FileNotFoundException -> 0x004b, Exception -> 0x0074 }
            r9 = 0
            r8[r9] = r13     // Catch:{ FileNotFoundException -> 0x004b, Exception -> 0x0074 }
            r9 = 1
            java.lang.String r10 = r2.getMessage()     // Catch:{ FileNotFoundException -> 0x004b, Exception -> 0x0074 }
            r8[r9] = r10     // Catch:{ FileNotFoundException -> 0x004b, Exception -> 0x0074 }
            r6.error(r7, r8)     // Catch:{ FileNotFoundException -> 0x004b, Exception -> 0x0074 }
            goto L_0x002d
        L_0x00a0:
            r2 = move-exception
            com.adjust.sdk.ILogger r6 = getLogger()
            java.lang.String r7 = "Failed to close %s file for reading (%s)"
            r8 = 2
            java.lang.Object[] r8 = new java.lang.Object[r8]
            r9 = 0
            r8[r9] = r13
            r9 = 1
            r8[r9] = r2
            r6.error(r7, r8)
            goto L_0x0032
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adjust.sdk.Util.readObject(android.content.Context, java.lang.String, java.lang.String, java.lang.Class):java.lang.Object");
    }

    /* JADX WARNING: type inference failed for: r1v0 */
    /* JADX WARNING: type inference failed for: r1v1 */
    /* JADX WARNING: type inference failed for: r1v2, types: [java.io.Closeable] */
    /* JADX WARNING: type inference failed for: r0v0, types: [java.io.OutputStream, java.io.BufferedOutputStream] */
    /* JADX WARNING: type inference failed for: r1v4 */
    /* JADX WARNING: type inference failed for: r3v0, types: [java.io.ObjectOutputStream] */
    /* JADX WARNING: type inference failed for: r1v5 */
    /* JADX WARNING: type inference failed for: r1v6 */
    /* JADX WARNING: type inference failed for: r1v7 */
    /* JADX WARNING: type inference failed for: r1v8 */
    /* JADX WARNING: type inference failed for: r1v9 */
    /* JADX WARNING: type inference failed for: r1v10 */
    /* JADX WARNING: type inference failed for: r1v11 */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r1v1
      assigns: []
      uses: []
      mth insns count: 45
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
    	at jadx.core.ProcessClass.process(ProcessClass.java:35)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Unknown variable types count: 6 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static <T> void writeObject(T r12, android.content.Context r13, java.lang.String r14, java.lang.String r15) {
        /*
            r11 = 2
            r10 = 1
            r9 = 0
            r1 = 0
            r5 = 0
            java.io.FileOutputStream r4 = r13.openFileOutput(r14, r5)     // Catch:{ Exception -> 0x0042 }
            r1 = r4
            java.io.BufferedOutputStream r0 = new java.io.BufferedOutputStream     // Catch:{ Exception -> 0x0042 }
            r0.<init>(r4)     // Catch:{ Exception -> 0x0042 }
            r1 = r0
            java.io.ObjectOutputStream r3 = new java.io.ObjectOutputStream     // Catch:{ Exception -> 0x0042 }
            r3.<init>(r0)     // Catch:{ Exception -> 0x0042 }
            r1 = r3
            r3.writeObject(r12)     // Catch:{ NotSerializableException -> 0x0031 }
            com.adjust.sdk.ILogger r5 = getLogger()     // Catch:{ NotSerializableException -> 0x0031 }
            java.lang.String r6 = "Wrote %s: %s"
            r7 = 2
            java.lang.Object[] r7 = new java.lang.Object[r7]     // Catch:{ NotSerializableException -> 0x0031 }
            r8 = 0
            r7[r8] = r15     // Catch:{ NotSerializableException -> 0x0031 }
            r8 = 1
            r7[r8] = r12     // Catch:{ NotSerializableException -> 0x0031 }
            r5.debug(r6, r7)     // Catch:{ NotSerializableException -> 0x0031 }
        L_0x002b:
            if (r1 == 0) goto L_0x0030
            r1.close()     // Catch:{ Exception -> 0x0053 }
        L_0x0030:
            return
        L_0x0031:
            r2 = move-exception
            com.adjust.sdk.ILogger r5 = getLogger()     // Catch:{ Exception -> 0x0042 }
            java.lang.String r6 = "Failed to serialize %s"
            r7 = 1
            java.lang.Object[] r7 = new java.lang.Object[r7]     // Catch:{ Exception -> 0x0042 }
            r8 = 0
            r7[r8] = r15     // Catch:{ Exception -> 0x0042 }
            r5.error(r6, r7)     // Catch:{ Exception -> 0x0042 }
            goto L_0x002b
        L_0x0042:
            r2 = move-exception
            com.adjust.sdk.ILogger r5 = getLogger()
            java.lang.String r6 = "Failed to open %s for writing (%s)"
            java.lang.Object[] r7 = new java.lang.Object[r11]
            r7[r9] = r15
            r7[r10] = r2
            r5.error(r6, r7)
            goto L_0x002b
        L_0x0053:
            r2 = move-exception
            com.adjust.sdk.ILogger r5 = getLogger()
            java.lang.String r6 = "Failed to close %s file for writing (%s)"
            java.lang.Object[] r7 = new java.lang.Object[r11]
            r7[r9] = r15
            r7[r10] = r2
            r5.error(r6, r7)
            goto L_0x0030
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adjust.sdk.Util.writeObject(java.lang.Object, android.content.Context, java.lang.String, java.lang.String):void");
    }

    public static boolean checkPermission(Context context, String permission) {
        return context.checkCallingOrSelfPermission(permission) == 0;
    }

    public static String readStringField(GetField fields, String name, String defaultValue) {
        return (String) readObjectField(fields, name, defaultValue);
    }

    public static <T> T readObjectField(GetField fields, String name, T defaultValue) {
        try {
            return fields.get(name, defaultValue);
        } catch (Exception e) {
            getLogger().debug(fieldReadErrorMessage, name, e.getMessage());
            return defaultValue;
        }
    }

    public static boolean readBooleanField(GetField fields, String name, boolean defaultValue) {
        try {
            return fields.get(name, defaultValue);
        } catch (Exception e) {
            getLogger().debug(fieldReadErrorMessage, name, e.getMessage());
            return defaultValue;
        }
    }

    public static int readIntField(GetField fields, String name, int defaultValue) {
        try {
            return fields.get(name, defaultValue);
        } catch (Exception e) {
            getLogger().debug(fieldReadErrorMessage, name, e.getMessage());
            return defaultValue;
        }
    }

    public static long readLongField(GetField fields, String name, long defaultValue) {
        try {
            return fields.get(name, defaultValue);
        } catch (Exception e) {
            getLogger().debug(fieldReadErrorMessage, name, e.getMessage());
            return defaultValue;
        }
    }

    public static boolean equalObject(Object first, Object second) {
        if (first == null || second == null) {
            return first == null && second == null;
        }
        return first.equals(second);
    }

    public static boolean equalsDouble(Double first, Double second) {
        if (first == null || second == null) {
            if (first == null && second == null) {
                return true;
            }
            return false;
        } else if (Double.doubleToLongBits(first.doubleValue()) != Double.doubleToLongBits(second.doubleValue())) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean equalString(String first, String second) {
        return equalObject(first, second);
    }

    public static boolean equalEnum(Enum first, Enum second) {
        return equalObject(first, second);
    }

    public static boolean equalLong(Long first, Long second) {
        return equalObject(first, second);
    }

    public static boolean equalInt(Integer first, Integer second) {
        return equalObject(first, second);
    }

    public static boolean equalBoolean(Boolean first, Boolean second) {
        return equalObject(first, second);
    }

    public static int hashBoolean(Boolean value) {
        if (value == null) {
            return 0;
        }
        return value.hashCode();
    }

    public static int hashLong(Long value) {
        if (value == null) {
            return 0;
        }
        return value.hashCode();
    }

    public static int hashString(String value) {
        if (value == null) {
            return 0;
        }
        return value.hashCode();
    }

    public static int hashEnum(Enum value) {
        if (value == null) {
            return 0;
        }
        return value.hashCode();
    }

    public static int hashObject(Object value) {
        if (value == null) {
            return 0;
        }
        return value.hashCode();
    }

    public static String sha1(String text) {
        return hash(text, Constants.SHA1);
    }

    public static String sha256(String text) {
        return hash(text, Constants.SHA256);
    }

    public static String md5(String text) {
        return hash(text, Constants.MD5);
    }

    public static String hash(String text, String method) {
        String hashString = null;
        try {
            byte[] bytes = text.getBytes("UTF-8");
            MessageDigest mesd = MessageDigest.getInstance(method);
            mesd.update(bytes, 0, bytes.length);
            return convertToHex(mesd.digest());
        } catch (Exception e) {
            return hashString;
        }
    }

    public static String convertToHex(byte[] bytes) {
        BigInteger bigInt = new BigInteger(1, bytes);
        return formatString("%0" + (bytes.length << 1) + "x", bigInt);
    }

    public static String[] getSupportedAbis() {
        return Reflection.getSupportedAbis();
    }

    public static String getCpuAbi() {
        return Reflection.getCpuAbi();
    }

    public static String getReasonString(String message, Throwable throwable) {
        if (throwable != null) {
            return formatString("%s: %s", message, throwable);
        }
        return formatString("%s", message);
    }

    public static long getWaitingTime(int retries, BackoffStrategy backoffStrategy) {
        if (retries < backoffStrategy.minRetries) {
            return 0;
        }
        long ceilingTime = Math.min(((long) Math.pow(2.0d, (double) (retries - backoffStrategy.minRetries))) * backoffStrategy.milliSecondMultiplier, backoffStrategy.maxWait);
        return (long) (((double) ceilingTime) * randomInRange(backoffStrategy.minRange, backoffStrategy.maxRange));
    }

    private static double randomInRange(double minRange, double maxRange) {
        return (new Random().nextDouble() * (maxRange - minRange)) + minRange;
    }

    public static boolean isValidParameter(String attribute, String attributeType, String parameterName) {
        if (attribute == null) {
            getLogger().error("%s parameter %s is missing", parameterName, attributeType);
            return false;
        } else if (!attribute.equals("")) {
            return true;
        } else {
            getLogger().error("%s parameter %s is empty", parameterName, attributeType);
            return false;
        }
    }

    public static Map<String, String> mergeParameters(Map<String, String> target, Map<String, String> source, String parameterName) {
        if (target == null) {
            return source;
        }
        if (source == null) {
            return target;
        }
        Map<String, String> hashMap = new HashMap<>(target);
        ILogger logger = getLogger();
        for (Entry<String, String> parameterSourceEntry : source.entrySet()) {
            String oldValue = (String) hashMap.put(parameterSourceEntry.getKey(), parameterSourceEntry.getValue());
            if (oldValue != null) {
                logger.warn("Key %s with value %s from %s parameter was replaced by value %s", parameterSourceEntry.getKey(), oldValue, parameterName, parameterSourceEntry.getValue());
            }
        }
        return hashMap;
    }

    public static String getVmInstructionSet() {
        return Reflection.getVmInstructionSet();
    }

    public static Locale getLocale(Configuration configuration) {
        Locale locale = Reflection.getLocaleFromLocaleList(configuration);
        return locale != null ? locale : Reflection.getLocaleFromField(configuration);
    }

    public static String getFireAdvertisingId(ContentResolver contentResolver) {
        String str = null;
        if (contentResolver == null) {
            return str;
        }
        try {
            return Secure.getString(contentResolver, "advertising_id");
        } catch (Exception e) {
            return str;
        }
    }

    public static Boolean getFireTrackingEnabled(ContentResolver contentResolver) {
        try {
            return Boolean.valueOf(Secure.getInt(contentResolver, "limit_ad_tracking") == 0);
        } catch (Exception e) {
            return null;
        }
    }

    public static int getConnectivityType(Context context) {
        int connectivityType = -1;
        try {
            return ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo().getType();
        } catch (Exception e) {
            getLogger().warn("Couldn't read connectivity type (%s)", e.getMessage());
            return connectivityType;
        }
    }

    public static int getNetworkType(Context context) {
        int networkType = -1;
        try {
            return ((TelephonyManager) context.getSystemService(PlaceFields.PHONE)).getNetworkType();
        } catch (Exception e) {
            getLogger().warn("Couldn't read network type (%s)", e.getMessage());
            return networkType;
        }
    }

    public static String getMcc(Context context) {
        try {
            String networkOperator = ((TelephonyManager) context.getSystemService(PlaceFields.PHONE)).getNetworkOperator();
            if (!TextUtils.isEmpty(networkOperator)) {
                return networkOperator.substring(0, 3);
            }
            AdjustFactory.getLogger().warn("Couldn't receive networkOperator string to read MCC", new Object[0]);
            return null;
        } catch (Exception e) {
            AdjustFactory.getLogger().warn("Couldn't return mcc", new Object[0]);
            return null;
        }
    }

    public static String getMnc(Context context) {
        try {
            String networkOperator = ((TelephonyManager) context.getSystemService(PlaceFields.PHONE)).getNetworkOperator();
            if (!TextUtils.isEmpty(networkOperator)) {
                return networkOperator.substring(3);
            }
            AdjustFactory.getLogger().warn("Couldn't receive networkOperator string to read MNC", new Object[0]);
            return null;
        } catch (Exception e) {
            AdjustFactory.getLogger().warn("Couldn't return mnc", new Object[0]);
            return null;
        }
    }

    public static String formatString(String format, Object... args) {
        return String.format(Locale.US, format, args);
    }

    public static boolean hasRootCause(Exception ex) {
        StringWriter sw = new StringWriter();
        ex.printStackTrace(new PrintWriter(sw));
        return sw.toString().contains("Caused by:");
    }

    public static String getRootCause(Exception ex) {
        if (!hasRootCause(ex)) {
            return null;
        }
        StringWriter sw = new StringWriter();
        ex.printStackTrace(new PrintWriter(sw));
        String sStackTrace = sw.toString();
        int startOccuranceOfRootCause = sStackTrace.indexOf("Caused by:");
        return sStackTrace.substring(startOccuranceOfRootCause, sStackTrace.indexOf(IOUtils.LINE_SEPARATOR_UNIX, startOccuranceOfRootCause));
    }
}
