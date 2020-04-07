package com.baitian.datasdk.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build.VERSION;
import android.support.p000v4.content.ContextCompat;
import android.support.p000v4.content.PermissionChecker;
import android.text.TextUtils;
import android.util.Log;
import com.adjust.sdk.Constants;
import com.facebook.appevents.AppEventsConstants;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.regex.Pattern;
import p004cn.jiguang.net.HttpUtils;

@SuppressLint({"WrongConstant"})
public class BtUtils {
    private static final String IMEI_FILENAME = "imei";
    private static final String IMSI_FILENAME = "imsi";
    private static final String SP_IMEI_KEY_NAME = "sp_imei";
    private static final String SP_IMSI_KEY_NAME = "sp_imsi";
    private static final String SP_NAME = "GAME_SP";
    private static final List<String> codeTables = new ArrayList();

    /* JADX WARNING: Removed duplicated region for block: B:104:0x00f5  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x007a  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x0095 A[SYNTHETIC, Splitter:B:55:0x0095] */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x009a A[SYNTHETIC, Splitter:B:58:0x009a] */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x009f A[SYNTHETIC, Splitter:B:61:0x009f] */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x00b8 A[SYNTHETIC, Splitter:B:73:0x00b8] */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x00bd A[SYNTHETIC, Splitter:B:76:0x00bd] */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x00c2 A[SYNTHETIC, Splitter:B:79:0x00c2] */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x00d8 A[SYNTHETIC, Splitter:B:89:0x00d8] */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x00dd A[SYNTHETIC, Splitter:B:92:0x00dd] */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x00e2 A[SYNTHETIC, Splitter:B:95:0x00e2] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:52:0x0090=Splitter:B:52:0x0090, B:70:0x00b3=Splitter:B:70:0x00b3} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getIMEI(android.content.Context r14) {
        /*
            r3 = 0
            r5 = 0
            java.lang.String r12 = "android.permission.READ_EXTERNAL_STORAGE"
            boolean r12 = checkPermission(r14, r12)
            if (r12 == 0) goto L_0x006c
            java.io.File r5 = new java.io.File
            java.io.File r12 = r14.getCacheDir()
            java.lang.String r13 = "imei"
            r5.<init>(r12, r13)
            boolean r12 = r5.exists()
            if (r12 == 0) goto L_0x006c
            boolean r12 = r5.isFile()
            if (r12 == 0) goto L_0x006c
            r1 = 0
            r8 = 0
            r6 = 0
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ FileNotFoundException -> 0x008f, IOException -> 0x00b2 }
            r2.<init>(r5)     // Catch:{ FileNotFoundException -> 0x008f, IOException -> 0x00b2 }
            java.io.InputStreamReader r7 = new java.io.InputStreamReader     // Catch:{ FileNotFoundException -> 0x0134, IOException -> 0x0128, all -> 0x011c }
            r7.<init>(r2)     // Catch:{ FileNotFoundException -> 0x0134, IOException -> 0x0128, all -> 0x011c }
            java.io.BufferedReader r9 = new java.io.BufferedReader     // Catch:{ FileNotFoundException -> 0x0138, IOException -> 0x012b, all -> 0x011f }
            r9.<init>(r7)     // Catch:{ FileNotFoundException -> 0x0138, IOException -> 0x012b, all -> 0x011f }
            java.lang.String r10 = r9.readLine()     // Catch:{ FileNotFoundException -> 0x013d, IOException -> 0x012f, all -> 0x0123 }
            if (r10 == 0) goto L_0x005d
            r3 = r10
            writeIMEI2XML(r14, r3)     // Catch:{ FileNotFoundException -> 0x013d, IOException -> 0x012f, all -> 0x0123 }
            if (r9 == 0) goto L_0x0042
            r9.close()     // Catch:{ IOException -> 0x004e }
        L_0x0042:
            if (r7 == 0) goto L_0x0047
            r7.close()     // Catch:{ IOException -> 0x0053 }
        L_0x0047:
            if (r2 == 0) goto L_0x004c
            r2.close()     // Catch:{ IOException -> 0x0058 }
        L_0x004c:
            r4 = r3
        L_0x004d:
            return r4
        L_0x004e:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0042
        L_0x0053:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0047
        L_0x0058:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x004c
        L_0x005d:
            if (r9 == 0) goto L_0x0062
            r9.close()     // Catch:{ IOException -> 0x0080 }
        L_0x0062:
            if (r7 == 0) goto L_0x0067
            r7.close()     // Catch:{ IOException -> 0x0085 }
        L_0x0067:
            if (r2 == 0) goto L_0x006c
            r2.close()     // Catch:{ IOException -> 0x008a }
        L_0x006c:
            java.lang.String r12 = "GAME_SP"
            java.lang.String r13 = "sp_imei"
            java.lang.String r10 = com.baitian.datasdk.util.SharedPreferencesUtils.getString(r14, r12, r13)
            boolean r12 = android.text.TextUtils.isEmpty(r10)
            if (r12 != 0) goto L_0x00f5
            r3 = r10
            writeParamters2File(r14, r5, r3)
            r4 = r3
            goto L_0x004d
        L_0x0080:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0062
        L_0x0085:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0067
        L_0x008a:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x006c
        L_0x008f:
            r0 = move-exception
        L_0x0090:
            r0.printStackTrace()     // Catch:{ all -> 0x00d5 }
            if (r8 == 0) goto L_0x0098
            r8.close()     // Catch:{ IOException -> 0x00a8 }
        L_0x0098:
            if (r6 == 0) goto L_0x009d
            r6.close()     // Catch:{ IOException -> 0x00ad }
        L_0x009d:
            if (r1 == 0) goto L_0x006c
            r1.close()     // Catch:{ IOException -> 0x00a3 }
            goto L_0x006c
        L_0x00a3:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x006c
        L_0x00a8:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0098
        L_0x00ad:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x009d
        L_0x00b2:
            r0 = move-exception
        L_0x00b3:
            r0.printStackTrace()     // Catch:{ all -> 0x00d5 }
            if (r8 == 0) goto L_0x00bb
            r8.close()     // Catch:{ IOException -> 0x00cb }
        L_0x00bb:
            if (r6 == 0) goto L_0x00c0
            r6.close()     // Catch:{ IOException -> 0x00d0 }
        L_0x00c0:
            if (r1 == 0) goto L_0x006c
            r1.close()     // Catch:{ IOException -> 0x00c6 }
            goto L_0x006c
        L_0x00c6:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x006c
        L_0x00cb:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x00bb
        L_0x00d0:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x00c0
        L_0x00d5:
            r12 = move-exception
        L_0x00d6:
            if (r8 == 0) goto L_0x00db
            r8.close()     // Catch:{ IOException -> 0x00e6 }
        L_0x00db:
            if (r6 == 0) goto L_0x00e0
            r6.close()     // Catch:{ IOException -> 0x00eb }
        L_0x00e0:
            if (r1 == 0) goto L_0x00e5
            r1.close()     // Catch:{ IOException -> 0x00f0 }
        L_0x00e5:
            throw r12
        L_0x00e6:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x00db
        L_0x00eb:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x00e0
        L_0x00f0:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x00e5
        L_0x00f5:
            java.lang.String r12 = "android.permission.READ_PHONE_STATE"
            boolean r12 = checkPermission(r14, r12)
            if (r12 == 0) goto L_0x0109
            java.lang.String r12 = "phone"
            java.lang.Object r11 = r14.getSystemService(r12)
            android.telephony.TelephonyManager r11 = (android.telephony.TelephonyManager) r11
            java.lang.String r3 = r11.getDeviceId()
        L_0x0109:
            boolean r12 = android.text.TextUtils.isEmpty(r3)
            if (r12 == 0) goto L_0x0113
            java.lang.String r3 = getUUID()
        L_0x0113:
            writeIMEI2XML(r14, r3)
            writeParamters2File(r14, r5, r3)
            r4 = r3
            goto L_0x004d
        L_0x011c:
            r12 = move-exception
            r1 = r2
            goto L_0x00d6
        L_0x011f:
            r12 = move-exception
            r6 = r7
            r1 = r2
            goto L_0x00d6
        L_0x0123:
            r12 = move-exception
            r6 = r7
            r8 = r9
            r1 = r2
            goto L_0x00d6
        L_0x0128:
            r0 = move-exception
            r1 = r2
            goto L_0x00b3
        L_0x012b:
            r0 = move-exception
            r6 = r7
            r1 = r2
            goto L_0x00b3
        L_0x012f:
            r0 = move-exception
            r6 = r7
            r8 = r9
            r1 = r2
            goto L_0x00b3
        L_0x0134:
            r0 = move-exception
            r1 = r2
            goto L_0x0090
        L_0x0138:
            r0 = move-exception
            r6 = r7
            r1 = r2
            goto L_0x0090
        L_0x013d:
            r0 = move-exception
            r6 = r7
            r8 = r9
            r1 = r2
            goto L_0x0090
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baitian.datasdk.util.BtUtils.getIMEI(android.content.Context):java.lang.String");
    }

    /* JADX WARNING: Removed duplicated region for block: B:106:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x0085 A[SYNTHETIC, Splitter:B:55:0x0085] */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x008a A[SYNTHETIC, Splitter:B:58:0x008a] */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x008f A[SYNTHETIC, Splitter:B:61:0x008f] */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x00a7 A[SYNTHETIC, Splitter:B:71:0x00a7] */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x00ac A[SYNTHETIC, Splitter:B:74:0x00ac] */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x00b1 A[SYNTHETIC, Splitter:B:77:0x00b1] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void writeParamters2File(android.content.Context r9, java.io.File r10, java.lang.String r11) {
        /*
            java.lang.String r8 = "android.permission.WRITE_EXTERNAL_STORAGE"
            boolean r8 = checkPermission(r9, r8)
            if (r8 != 0) goto L_0x0009
        L_0x0008:
            return
        L_0x0009:
            if (r10 == 0) goto L_0x0008
            boolean r8 = android.text.TextUtils.isEmpty(r11)
            if (r8 != 0) goto L_0x0008
            r1 = 0
            r3 = 0
            r6 = 0
            java.io.File r5 = r10.getParentFile()     // Catch:{ IOException -> 0x007f }
            if (r5 != 0) goto L_0x0039
            if (r6 == 0) goto L_0x001f
            r6.close()     // Catch:{ IOException -> 0x002f }
        L_0x001f:
            if (r3 == 0) goto L_0x0024
            r3.close()     // Catch:{ IOException -> 0x0034 }
        L_0x0024:
            if (r1 == 0) goto L_0x0008
            r1.close()     // Catch:{ IOException -> 0x002a }
            goto L_0x0008
        L_0x002a:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0008
        L_0x002f:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x001f
        L_0x0034:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0024
        L_0x0039:
            boolean r8 = r5.exists()     // Catch:{ IOException -> 0x007f }
            if (r8 != 0) goto L_0x0042
            r5.mkdirs()     // Catch:{ IOException -> 0x007f }
        L_0x0042:
            r10.createNewFile()     // Catch:{ IOException -> 0x007f }
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x007f }
            r2.<init>(r10)     // Catch:{ IOException -> 0x007f }
            java.io.OutputStreamWriter r4 = new java.io.OutputStreamWriter     // Catch:{ IOException -> 0x00d0, all -> 0x00c4 }
            r4.<init>(r2)     // Catch:{ IOException -> 0x00d0, all -> 0x00c4 }
            java.io.BufferedWriter r7 = new java.io.BufferedWriter     // Catch:{ IOException -> 0x00d3, all -> 0x00c7 }
            r7.<init>(r4)     // Catch:{ IOException -> 0x00d3, all -> 0x00c7 }
            r7.write(r11)     // Catch:{ IOException -> 0x00d7, all -> 0x00cb }
            r7.flush()     // Catch:{ IOException -> 0x00d7, all -> 0x00cb }
            if (r7 == 0) goto L_0x005f
            r7.close()     // Catch:{ IOException -> 0x006d }
        L_0x005f:
            if (r4 == 0) goto L_0x0064
            r4.close()     // Catch:{ IOException -> 0x0072 }
        L_0x0064:
            if (r2 == 0) goto L_0x00dc
            r2.close()     // Catch:{ IOException -> 0x0077 }
            r6 = r7
            r3 = r4
            r1 = r2
            goto L_0x0008
        L_0x006d:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x005f
        L_0x0072:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0064
        L_0x0077:
            r0 = move-exception
            r0.printStackTrace()
            r6 = r7
            r3 = r4
            r1 = r2
            goto L_0x0008
        L_0x007f:
            r0 = move-exception
        L_0x0080:
            r0.printStackTrace()     // Catch:{ all -> 0x00a4 }
            if (r6 == 0) goto L_0x0088
            r6.close()     // Catch:{ IOException -> 0x009a }
        L_0x0088:
            if (r3 == 0) goto L_0x008d
            r3.close()     // Catch:{ IOException -> 0x009f }
        L_0x008d:
            if (r1 == 0) goto L_0x0008
            r1.close()     // Catch:{ IOException -> 0x0094 }
            goto L_0x0008
        L_0x0094:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0008
        L_0x009a:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0088
        L_0x009f:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x008d
        L_0x00a4:
            r8 = move-exception
        L_0x00a5:
            if (r6 == 0) goto L_0x00aa
            r6.close()     // Catch:{ IOException -> 0x00b5 }
        L_0x00aa:
            if (r3 == 0) goto L_0x00af
            r3.close()     // Catch:{ IOException -> 0x00ba }
        L_0x00af:
            if (r1 == 0) goto L_0x00b4
            r1.close()     // Catch:{ IOException -> 0x00bf }
        L_0x00b4:
            throw r8
        L_0x00b5:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x00aa
        L_0x00ba:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x00af
        L_0x00bf:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x00b4
        L_0x00c4:
            r8 = move-exception
            r1 = r2
            goto L_0x00a5
        L_0x00c7:
            r8 = move-exception
            r3 = r4
            r1 = r2
            goto L_0x00a5
        L_0x00cb:
            r8 = move-exception
            r6 = r7
            r3 = r4
            r1 = r2
            goto L_0x00a5
        L_0x00d0:
            r0 = move-exception
            r1 = r2
            goto L_0x0080
        L_0x00d3:
            r0 = move-exception
            r3 = r4
            r1 = r2
            goto L_0x0080
        L_0x00d7:
            r0 = move-exception
            r6 = r7
            r3 = r4
            r1 = r2
            goto L_0x0080
        L_0x00dc:
            r6 = r7
            r3 = r4
            r1 = r2
            goto L_0x0008
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baitian.datasdk.util.BtUtils.writeParamters2File(android.content.Context, java.io.File, java.lang.String):void");
    }

    private static void writeIMEI2XML(Context ctx, String imei) {
        if (!TextUtils.isEmpty(imei)) {
            SharedPreferencesUtils.setString(ctx, SP_NAME, SP_IMEI_KEY_NAME, imei);
        }
    }

    private static void writeIMSI2XML(Context ctx, String imsi) {
        if (!TextUtils.isEmpty(imsi)) {
            SharedPreferencesUtils.setString(ctx, SP_NAME, SP_IMSI_KEY_NAME, imsi);
        }
    }

    public static String getUUID() {
        String str = UUID.randomUUID().toString();
        return str.substring(0, 8) + str.substring(9, 13) + str.substring(14, 18) + str.substring(19, 23) + str.substring(24);
    }

    /* JADX WARNING: Removed duplicated region for block: B:104:0x00f5  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x007a  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x0095 A[SYNTHETIC, Splitter:B:55:0x0095] */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x009a A[SYNTHETIC, Splitter:B:58:0x009a] */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x009f A[SYNTHETIC, Splitter:B:61:0x009f] */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x00b8 A[SYNTHETIC, Splitter:B:73:0x00b8] */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x00bd A[SYNTHETIC, Splitter:B:76:0x00bd] */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x00c2 A[SYNTHETIC, Splitter:B:79:0x00c2] */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x00d8 A[SYNTHETIC, Splitter:B:89:0x00d8] */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x00dd A[SYNTHETIC, Splitter:B:92:0x00dd] */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x00e2 A[SYNTHETIC, Splitter:B:95:0x00e2] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:52:0x0090=Splitter:B:52:0x0090, B:70:0x00b3=Splitter:B:70:0x00b3} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getIMSI(android.content.Context r14) {
        /*
            r3 = 0
            r5 = 0
            java.lang.String r12 = "android.permission.READ_EXTERNAL_STORAGE"
            boolean r12 = checkPermission(r14, r12)
            if (r12 == 0) goto L_0x006c
            java.io.File r5 = new java.io.File
            java.io.File r12 = r14.getCacheDir()
            java.lang.String r13 = "imsi"
            r5.<init>(r12, r13)
            boolean r12 = r5.exists()
            if (r12 == 0) goto L_0x006c
            boolean r12 = r5.isFile()
            if (r12 == 0) goto L_0x006c
            r1 = 0
            r8 = 0
            r6 = 0
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ FileNotFoundException -> 0x008f, IOException -> 0x00b2 }
            r2.<init>(r5)     // Catch:{ FileNotFoundException -> 0x008f, IOException -> 0x00b2 }
            java.io.InputStreamReader r7 = new java.io.InputStreamReader     // Catch:{ FileNotFoundException -> 0x0134, IOException -> 0x0128, all -> 0x011c }
            r7.<init>(r2)     // Catch:{ FileNotFoundException -> 0x0134, IOException -> 0x0128, all -> 0x011c }
            java.io.BufferedReader r9 = new java.io.BufferedReader     // Catch:{ FileNotFoundException -> 0x0138, IOException -> 0x012b, all -> 0x011f }
            r9.<init>(r7)     // Catch:{ FileNotFoundException -> 0x0138, IOException -> 0x012b, all -> 0x011f }
            java.lang.String r10 = r9.readLine()     // Catch:{ FileNotFoundException -> 0x013d, IOException -> 0x012f, all -> 0x0123 }
            if (r10 == 0) goto L_0x005d
            r3 = r10
            writeIMSI2XML(r14, r3)     // Catch:{ FileNotFoundException -> 0x013d, IOException -> 0x012f, all -> 0x0123 }
            if (r9 == 0) goto L_0x0042
            r9.close()     // Catch:{ IOException -> 0x004e }
        L_0x0042:
            if (r7 == 0) goto L_0x0047
            r7.close()     // Catch:{ IOException -> 0x0053 }
        L_0x0047:
            if (r2 == 0) goto L_0x004c
            r2.close()     // Catch:{ IOException -> 0x0058 }
        L_0x004c:
            r4 = r3
        L_0x004d:
            return r4
        L_0x004e:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0042
        L_0x0053:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0047
        L_0x0058:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x004c
        L_0x005d:
            if (r9 == 0) goto L_0x0062
            r9.close()     // Catch:{ IOException -> 0x0080 }
        L_0x0062:
            if (r7 == 0) goto L_0x0067
            r7.close()     // Catch:{ IOException -> 0x0085 }
        L_0x0067:
            if (r2 == 0) goto L_0x006c
            r2.close()     // Catch:{ IOException -> 0x008a }
        L_0x006c:
            java.lang.String r12 = "GAME_SP"
            java.lang.String r13 = "sp_imsi"
            java.lang.String r10 = com.baitian.datasdk.util.SharedPreferencesUtils.getString(r14, r12, r13)
            boolean r12 = android.text.TextUtils.isEmpty(r10)
            if (r12 != 0) goto L_0x00f5
            r3 = r10
            writeParamters2File(r14, r5, r3)
            r4 = r3
            goto L_0x004d
        L_0x0080:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0062
        L_0x0085:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0067
        L_0x008a:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x006c
        L_0x008f:
            r0 = move-exception
        L_0x0090:
            r0.printStackTrace()     // Catch:{ all -> 0x00d5 }
            if (r8 == 0) goto L_0x0098
            r8.close()     // Catch:{ IOException -> 0x00a8 }
        L_0x0098:
            if (r6 == 0) goto L_0x009d
            r6.close()     // Catch:{ IOException -> 0x00ad }
        L_0x009d:
            if (r1 == 0) goto L_0x006c
            r1.close()     // Catch:{ IOException -> 0x00a3 }
            goto L_0x006c
        L_0x00a3:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x006c
        L_0x00a8:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0098
        L_0x00ad:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x009d
        L_0x00b2:
            r0 = move-exception
        L_0x00b3:
            r0.printStackTrace()     // Catch:{ all -> 0x00d5 }
            if (r8 == 0) goto L_0x00bb
            r8.close()     // Catch:{ IOException -> 0x00cb }
        L_0x00bb:
            if (r6 == 0) goto L_0x00c0
            r6.close()     // Catch:{ IOException -> 0x00d0 }
        L_0x00c0:
            if (r1 == 0) goto L_0x006c
            r1.close()     // Catch:{ IOException -> 0x00c6 }
            goto L_0x006c
        L_0x00c6:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x006c
        L_0x00cb:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x00bb
        L_0x00d0:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x00c0
        L_0x00d5:
            r12 = move-exception
        L_0x00d6:
            if (r8 == 0) goto L_0x00db
            r8.close()     // Catch:{ IOException -> 0x00e6 }
        L_0x00db:
            if (r6 == 0) goto L_0x00e0
            r6.close()     // Catch:{ IOException -> 0x00eb }
        L_0x00e0:
            if (r1 == 0) goto L_0x00e5
            r1.close()     // Catch:{ IOException -> 0x00f0 }
        L_0x00e5:
            throw r12
        L_0x00e6:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x00db
        L_0x00eb:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x00e0
        L_0x00f0:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x00e5
        L_0x00f5:
            java.lang.String r12 = "android.permission.READ_PHONE_STATE"
            boolean r12 = checkPermission(r14, r12)
            if (r12 == 0) goto L_0x0109
            java.lang.String r12 = "phone"
            java.lang.Object r11 = r14.getSystemService(r12)
            android.telephony.TelephonyManager r11 = (android.telephony.TelephonyManager) r11
            java.lang.String r3 = r11.getSubscriberId()
        L_0x0109:
            boolean r12 = android.text.TextUtils.isEmpty(r3)
            if (r12 == 0) goto L_0x0113
            java.lang.String r3 = getUUID()
        L_0x0113:
            writeIMSI2XML(r14, r3)
            writeParamters2File(r14, r5, r3)
            r4 = r3
            goto L_0x004d
        L_0x011c:
            r12 = move-exception
            r1 = r2
            goto L_0x00d6
        L_0x011f:
            r12 = move-exception
            r6 = r7
            r1 = r2
            goto L_0x00d6
        L_0x0123:
            r12 = move-exception
            r6 = r7
            r8 = r9
            r1 = r2
            goto L_0x00d6
        L_0x0128:
            r0 = move-exception
            r1 = r2
            goto L_0x00b3
        L_0x012b:
            r0 = move-exception
            r6 = r7
            r1 = r2
            goto L_0x00b3
        L_0x012f:
            r0 = move-exception
            r6 = r7
            r8 = r9
            r1 = r2
            goto L_0x00b3
        L_0x0134:
            r0 = move-exception
            r1 = r2
            goto L_0x0090
        L_0x0138:
            r0 = move-exception
            r6 = r7
            r1 = r2
            goto L_0x0090
        L_0x013d:
            r0 = move-exception
            r6 = r7
            r8 = r9
            r1 = r2
            goto L_0x0090
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baitian.datasdk.util.BtUtils.getIMSI(android.content.Context):java.lang.String");
    }

    public static boolean getbooleanMeTaDataFalse(Context ctx, String matchmsg) {
        if (TextUtils.isEmpty(matchmsg)) {
            return false;
        }
        boolean bool = false;
        try {
            bool = ctx.getPackageManager().getApplicationInfo(ctx.getPackageName(), 128).metaData.getBoolean(matchmsg, false);
            Log.d(BtsdkLog.TAG, "Meta-Data：" + matchmsg + " = " + bool);
            return bool;
        } catch (Exception e) {
            e.printStackTrace();
            return bool;
        }
    }

    public static String getMeTaData(Context ctx, String matchmsg) {
        if (TextUtils.isEmpty(matchmsg)) {
            return "-1";
        }
        try {
            String channel = ctx.getPackageManager().getApplicationInfo(ctx.getPackageName(), 128).metaData.getString(matchmsg);
            Log.d(BtsdkLog.TAG, "Meta-Data：" + matchmsg + " = " + channel);
            String channelID = channel.substring(2, channel.length());
            if (!TextUtils.isEmpty(channelID)) {
                return channelID;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "-1";
    }

    public static boolean getbooleanMeTaData(Context ctx, String matchmsg) {
        if (TextUtils.isEmpty(matchmsg)) {
            return true;
        }
        boolean bool = true;
        try {
            bool = ctx.getPackageManager().getApplicationInfo(ctx.getPackageName(), 128).metaData.getBoolean(matchmsg, true);
            Log.d(BtsdkLog.TAG, "Meta-Data：" + matchmsg + " = " + bool);
            return bool;
        } catch (Exception e) {
            e.printStackTrace();
            return bool;
        }
    }

    public static String getNOXMeTaData(Context ctx, String matchmsg) {
        if (TextUtils.isEmpty(matchmsg)) {
            return "-1";
        }
        try {
            String meta = ctx.getPackageManager().getApplicationInfo(ctx.getPackageName(), 128).metaData.getString(matchmsg);
            Log.d(BtsdkLog.TAG, "Meta-Data：" + matchmsg + " = " + meta);
            if (!TextUtils.isEmpty(meta)) {
                return meta;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "-1";
    }

    public static int getIntNoXMeTaData(Context ctx, String matchmsg) {
        if (TextUtils.isEmpty(matchmsg)) {
            return -1;
        }
        try {
            int meta = ctx.getPackageManager().getApplicationInfo(ctx.getPackageName(), 128).metaData.getInt(matchmsg);
            Log.d(BtsdkLog.TAG, "Meta-Data：" + matchmsg + " = " + meta);
            if (meta != 0) {
                return meta;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static String buildUrl(String urlhost, HashMap<String, String> map) {
        StringBuilder sb = new StringBuilder(urlhost);
        for (Entry entry : map.entrySet()) {
            try {
                sb.append(URLEncoder.encode((String) entry.getKey(), "utf8")).append(HttpUtils.EQUAL_SIGN).append(URLEncoder.encode((String) entry.getValue(), "utf8")).append(HttpUtils.PARAMETERS_SEPARATOR);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    public static String getMD5Str(String str) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance(Constants.MD5);
            messageDigest.reset();
            messageDigest.update(str.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            System.out.println("NoSuchAlgorithmException caught!");
            System.exit(-1);
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
        }
        byte[] byteArray = messageDigest.digest();
        StringBuffer md5StrBuff = new StringBuffer();
        for (int i = 0; i < byteArray.length; i++) {
            if (Integer.toHexString(byteArray[i] & 255).length() == 1) {
                md5StrBuff.append(AppEventsConstants.EVENT_PARAM_VALUE_NO).append(Integer.toHexString(byteArray[i] & 255));
            } else {
                md5StrBuff.append(Integer.toHexString(byteArray[i] & 255));
            }
        }
        return md5StrBuff.toString();
    }

    static {
        codeTables.add("trGFGlaxIbzVimDr57GUUe6quDqiqTnhUuFSd8hv9fG8TMOUscG5gFqlXXDA1GUC");
        codeTables.add("UorehIsx6zY2g9se4hSt4ur8jMrS3R1ETE9Hk0e53akoSK37aCDYMDcFlBLrwca6");
        codeTables.add("R4DPNzqlkOtvoeksWtt6GPyGqXao1akDSaOep8O4TABtPkG9rhzO7Q8bijOK6lFJ");
        codeTables.add("Etv25Jl3a9UiJ8KM185UGi5OfdmPVWE7NlH5rz2A97hLa02m9gr4at4eJTffz4mx");
        codeTables.add("Qm1ApY3Qad3OQK497KCJ4R961qOjwjTzDHJWUMCRwu9j7MF14vYFCADWB2VVWonC");
        codeTables.add("9JeSOPhFS9SfHjz5zhhagd2dp5f1XERQqNrphPyXFMGtWrdi7FvcxXXxLj6eSHKr");
        codeTables.add("ucoLREq7V3T26KFwAWu0fcWBsxfqi1kM54gMiyXfrrJMFce5Y4uKFXEh9JMrhOFJ");
        codeTables.add("4Pw74fO9c6yrxRQeaMAu2htM4BejmEF8cxb8tEV1hmVv803Lpjbea9IUmwL4D9lr");
        codeTables.add("umRwejmOBd5LhdQrFqYIKwk7r9wQWiHqluyUD3soqYTt4Fb3jHeMEeV3GW2w1gsE");
        codeTables.add("IPB7eg7kPmBiVCvQtaDfqjBgHRqiekeYx5i5N4OcmxBYTKKchQoo5bIWWrLdo38O");
    }

    public static String makeSecurityCode(String key) {
        int codeTable;
        Pattern p = Pattern.compile("^[0-9]{5,8}$");
        if (TextUtils.isEmpty(key) || key.length() > 8 || key.length() < 5 || !p.matcher(key).matches()) {
            return null;
        }
        int keyLength = key.length();
        int codeTable2 = Integer.valueOf(key.substring(keyLength - 1)).intValue();
        StringBuilder securityCode = new StringBuilder();
        int i = 0;
        while (i < keyLength - 1) {
            int j = 0;
            while (true) {
                codeTable = codeTable2;
                if (i + j >= keyLength - 1) {
                    break;
                }
                codeTable2 = codeTable + 1;
                securityCode.append(((String) codeTables.get(codeTable % 10)).charAt(Integer.valueOf(key.substring(i, (i + j) + 1)).intValue() % 64));
                j++;
            }
            i++;
            codeTable2 = codeTable;
        }
        return securityCode.toString();
    }

    private static boolean checkPermission(Context context, String permission) {
        if (VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(context, permission) == 0) {
                return true;
            }
            return false;
        } else if (PermissionChecker.checkSelfPermission(context, permission) != 0) {
            return false;
        } else {
            return true;
        }
    }
}
