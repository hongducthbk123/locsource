package com.baitian.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build.VERSION;
import android.support.p000v4.content.ContextCompat;
import android.support.p000v4.content.PermissionChecker;
import android.text.TextUtils;
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

    /* JADX WARNING: Removed duplicated region for block: B:105:0x00f6  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x007b  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x0096 A[SYNTHETIC, Splitter:B:56:0x0096] */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x009b A[SYNTHETIC, Splitter:B:59:0x009b] */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x00a0 A[SYNTHETIC, Splitter:B:62:0x00a0] */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x00b9 A[SYNTHETIC, Splitter:B:74:0x00b9] */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x00be A[SYNTHETIC, Splitter:B:77:0x00be] */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x00c3 A[SYNTHETIC, Splitter:B:80:0x00c3] */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x00d9 A[SYNTHETIC, Splitter:B:90:0x00d9] */
    /* JADX WARNING: Removed duplicated region for block: B:93:0x00de A[SYNTHETIC, Splitter:B:93:0x00de] */
    /* JADX WARNING: Removed duplicated region for block: B:96:0x00e3 A[SYNTHETIC, Splitter:B:96:0x00e3] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:53:0x0091=Splitter:B:53:0x0091, B:71:0x00b4=Splitter:B:71:0x00b4} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getIMEI(android.content.Context r14) {
        /*
            r3 = 0
            r5 = 0
            java.lang.String r12 = "android.permission.READ_EXTERNAL_STORAGE"
            boolean r12 = checkPermission(r14, r12)
            if (r12 == 0) goto L_0x006d
            java.io.File r5 = new java.io.File
            java.io.File r12 = r14.getCacheDir()
            java.lang.String r13 = "imei"
            r5.<init>(r12, r13)
            boolean r12 = r5.exists()
            if (r12 == 0) goto L_0x006d
            boolean r12 = r5.isFile()
            if (r12 == 0) goto L_0x006d
            r1 = 0
            r8 = 0
            r6 = 0
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ FileNotFoundException -> 0x0090, IOException -> 0x00b3 }
            r2.<init>(r5)     // Catch:{ FileNotFoundException -> 0x0090, IOException -> 0x00b3 }
            java.io.InputStreamReader r7 = new java.io.InputStreamReader     // Catch:{ FileNotFoundException -> 0x0135, IOException -> 0x0129, all -> 0x011d }
            r7.<init>(r2)     // Catch:{ FileNotFoundException -> 0x0135, IOException -> 0x0129, all -> 0x011d }
            java.io.BufferedReader r9 = new java.io.BufferedReader     // Catch:{ FileNotFoundException -> 0x0139, IOException -> 0x012c, all -> 0x0120 }
            r9.<init>(r7)     // Catch:{ FileNotFoundException -> 0x0139, IOException -> 0x012c, all -> 0x0120 }
            r10 = 0
            java.lang.String r10 = r9.readLine()     // Catch:{ FileNotFoundException -> 0x013e, IOException -> 0x0130, all -> 0x0124 }
            if (r10 == 0) goto L_0x005e
            r3 = r10
            writeIMEI2XML(r14, r3)     // Catch:{ FileNotFoundException -> 0x013e, IOException -> 0x0130, all -> 0x0124 }
            if (r9 == 0) goto L_0x0043
            r9.close()     // Catch:{ IOException -> 0x004f }
        L_0x0043:
            if (r7 == 0) goto L_0x0048
            r7.close()     // Catch:{ IOException -> 0x0054 }
        L_0x0048:
            if (r2 == 0) goto L_0x004d
            r2.close()     // Catch:{ IOException -> 0x0059 }
        L_0x004d:
            r4 = r3
        L_0x004e:
            return r4
        L_0x004f:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0043
        L_0x0054:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0048
        L_0x0059:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x004d
        L_0x005e:
            if (r9 == 0) goto L_0x0063
            r9.close()     // Catch:{ IOException -> 0x0081 }
        L_0x0063:
            if (r7 == 0) goto L_0x0068
            r7.close()     // Catch:{ IOException -> 0x0086 }
        L_0x0068:
            if (r2 == 0) goto L_0x006d
            r2.close()     // Catch:{ IOException -> 0x008b }
        L_0x006d:
            java.lang.String r12 = "GAME_SP"
            java.lang.String r13 = "sp_imei"
            java.lang.String r10 = com.baitian.util.SharedPreferencesUtils.getString(r14, r12, r13)
            boolean r12 = android.text.TextUtils.isEmpty(r10)
            if (r12 != 0) goto L_0x00f6
            r3 = r10
            writeParamters2File(r14, r5, r3)
            r4 = r3
            goto L_0x004e
        L_0x0081:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0063
        L_0x0086:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0068
        L_0x008b:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x006d
        L_0x0090:
            r0 = move-exception
        L_0x0091:
            r0.printStackTrace()     // Catch:{ all -> 0x00d6 }
            if (r8 == 0) goto L_0x0099
            r8.close()     // Catch:{ IOException -> 0x00a9 }
        L_0x0099:
            if (r6 == 0) goto L_0x009e
            r6.close()     // Catch:{ IOException -> 0x00ae }
        L_0x009e:
            if (r1 == 0) goto L_0x006d
            r1.close()     // Catch:{ IOException -> 0x00a4 }
            goto L_0x006d
        L_0x00a4:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x006d
        L_0x00a9:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0099
        L_0x00ae:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x009e
        L_0x00b3:
            r0 = move-exception
        L_0x00b4:
            r0.printStackTrace()     // Catch:{ all -> 0x00d6 }
            if (r8 == 0) goto L_0x00bc
            r8.close()     // Catch:{ IOException -> 0x00cc }
        L_0x00bc:
            if (r6 == 0) goto L_0x00c1
            r6.close()     // Catch:{ IOException -> 0x00d1 }
        L_0x00c1:
            if (r1 == 0) goto L_0x006d
            r1.close()     // Catch:{ IOException -> 0x00c7 }
            goto L_0x006d
        L_0x00c7:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x006d
        L_0x00cc:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x00bc
        L_0x00d1:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x00c1
        L_0x00d6:
            r12 = move-exception
        L_0x00d7:
            if (r8 == 0) goto L_0x00dc
            r8.close()     // Catch:{ IOException -> 0x00e7 }
        L_0x00dc:
            if (r6 == 0) goto L_0x00e1
            r6.close()     // Catch:{ IOException -> 0x00ec }
        L_0x00e1:
            if (r1 == 0) goto L_0x00e6
            r1.close()     // Catch:{ IOException -> 0x00f1 }
        L_0x00e6:
            throw r12
        L_0x00e7:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x00dc
        L_0x00ec:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x00e1
        L_0x00f1:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x00e6
        L_0x00f6:
            java.lang.String r12 = "android.permission.READ_PHONE_STATE"
            boolean r12 = checkPermission(r14, r12)
            if (r12 == 0) goto L_0x010a
            java.lang.String r12 = "phone"
            java.lang.Object r11 = r14.getSystemService(r12)
            android.telephony.TelephonyManager r11 = (android.telephony.TelephonyManager) r11
            java.lang.String r3 = r11.getDeviceId()
        L_0x010a:
            boolean r12 = android.text.TextUtils.isEmpty(r3)
            if (r12 == 0) goto L_0x0114
            java.lang.String r3 = getUUID()
        L_0x0114:
            writeIMEI2XML(r14, r3)
            writeParamters2File(r14, r5, r3)
            r4 = r3
            goto L_0x004e
        L_0x011d:
            r12 = move-exception
            r1 = r2
            goto L_0x00d7
        L_0x0120:
            r12 = move-exception
            r6 = r7
            r1 = r2
            goto L_0x00d7
        L_0x0124:
            r12 = move-exception
            r6 = r7
            r8 = r9
            r1 = r2
            goto L_0x00d7
        L_0x0129:
            r0 = move-exception
            r1 = r2
            goto L_0x00b4
        L_0x012c:
            r0 = move-exception
            r6 = r7
            r1 = r2
            goto L_0x00b4
        L_0x0130:
            r0 = move-exception
            r6 = r7
            r8 = r9
            r1 = r2
            goto L_0x00b4
        L_0x0135:
            r0 = move-exception
            r1 = r2
            goto L_0x0091
        L_0x0139:
            r0 = move-exception
            r6 = r7
            r1 = r2
            goto L_0x0091
        L_0x013e:
            r0 = move-exception
            r6 = r7
            r8 = r9
            r1 = r2
            goto L_0x0091
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baitian.util.BtUtils.getIMEI(android.content.Context):java.lang.String");
    }

    /* JADX WARNING: Removed duplicated region for block: B:37:0x0064 A[SYNTHETIC, Splitter:B:37:0x0064] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0069 A[SYNTHETIC, Splitter:B:40:0x0069] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x006e A[SYNTHETIC, Splitter:B:43:0x006e] */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x0084 A[SYNTHETIC, Splitter:B:53:0x0084] */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x0089 A[SYNTHETIC, Splitter:B:56:0x0089] */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x008e A[SYNTHETIC, Splitter:B:59:0x008e] */
    /* JADX WARNING: Removed duplicated region for block: B:86:? A[RETURN, SYNTHETIC] */
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
            java.io.File r5 = r10.getParentFile()     // Catch:{ IOException -> 0x005e }
            boolean r8 = r5.exists()     // Catch:{ IOException -> 0x005e }
            if (r8 != 0) goto L_0x0021
            r5.mkdirs()     // Catch:{ IOException -> 0x005e }
        L_0x0021:
            r10.createNewFile()     // Catch:{ IOException -> 0x005e }
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x005e }
            r2.<init>(r10)     // Catch:{ IOException -> 0x005e }
            java.io.OutputStreamWriter r4 = new java.io.OutputStreamWriter     // Catch:{ IOException -> 0x00ad, all -> 0x00a1 }
            r4.<init>(r2)     // Catch:{ IOException -> 0x00ad, all -> 0x00a1 }
            java.io.BufferedWriter r7 = new java.io.BufferedWriter     // Catch:{ IOException -> 0x00b0, all -> 0x00a4 }
            r7.<init>(r4)     // Catch:{ IOException -> 0x00b0, all -> 0x00a4 }
            r7.write(r11)     // Catch:{ IOException -> 0x00b4, all -> 0x00a8 }
            r7.flush()     // Catch:{ IOException -> 0x00b4, all -> 0x00a8 }
            if (r7 == 0) goto L_0x003e
            r7.close()     // Catch:{ IOException -> 0x004c }
        L_0x003e:
            if (r4 == 0) goto L_0x0043
            r4.close()     // Catch:{ IOException -> 0x0051 }
        L_0x0043:
            if (r2 == 0) goto L_0x00b9
            r2.close()     // Catch:{ IOException -> 0x0056 }
            r6 = r7
            r3 = r4
            r1 = r2
            goto L_0x0008
        L_0x004c:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x003e
        L_0x0051:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0043
        L_0x0056:
            r0 = move-exception
            r0.printStackTrace()
            r6 = r7
            r3 = r4
            r1 = r2
            goto L_0x0008
        L_0x005e:
            r0 = move-exception
        L_0x005f:
            r0.printStackTrace()     // Catch:{ all -> 0x0081 }
            if (r6 == 0) goto L_0x0067
            r6.close()     // Catch:{ IOException -> 0x0077 }
        L_0x0067:
            if (r3 == 0) goto L_0x006c
            r3.close()     // Catch:{ IOException -> 0x007c }
        L_0x006c:
            if (r1 == 0) goto L_0x0008
            r1.close()     // Catch:{ IOException -> 0x0072 }
            goto L_0x0008
        L_0x0072:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0008
        L_0x0077:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0067
        L_0x007c:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x006c
        L_0x0081:
            r8 = move-exception
        L_0x0082:
            if (r6 == 0) goto L_0x0087
            r6.close()     // Catch:{ IOException -> 0x0092 }
        L_0x0087:
            if (r3 == 0) goto L_0x008c
            r3.close()     // Catch:{ IOException -> 0x0097 }
        L_0x008c:
            if (r1 == 0) goto L_0x0091
            r1.close()     // Catch:{ IOException -> 0x009c }
        L_0x0091:
            throw r8
        L_0x0092:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0087
        L_0x0097:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x008c
        L_0x009c:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0091
        L_0x00a1:
            r8 = move-exception
            r1 = r2
            goto L_0x0082
        L_0x00a4:
            r8 = move-exception
            r3 = r4
            r1 = r2
            goto L_0x0082
        L_0x00a8:
            r8 = move-exception
            r6 = r7
            r3 = r4
            r1 = r2
            goto L_0x0082
        L_0x00ad:
            r0 = move-exception
            r1 = r2
            goto L_0x005f
        L_0x00b0:
            r0 = move-exception
            r3 = r4
            r1 = r2
            goto L_0x005f
        L_0x00b4:
            r0 = move-exception
            r6 = r7
            r3 = r4
            r1 = r2
            goto L_0x005f
        L_0x00b9:
            r6 = r7
            r3 = r4
            r1 = r2
            goto L_0x0008
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baitian.util.BtUtils.writeParamters2File(android.content.Context, java.io.File, java.lang.String):void");
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

    /* JADX WARNING: Removed duplicated region for block: B:105:0x00f6  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x007b  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x0096 A[SYNTHETIC, Splitter:B:56:0x0096] */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x009b A[SYNTHETIC, Splitter:B:59:0x009b] */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x00a0 A[SYNTHETIC, Splitter:B:62:0x00a0] */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x00b9 A[SYNTHETIC, Splitter:B:74:0x00b9] */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x00be A[SYNTHETIC, Splitter:B:77:0x00be] */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x00c3 A[SYNTHETIC, Splitter:B:80:0x00c3] */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x00d9 A[SYNTHETIC, Splitter:B:90:0x00d9] */
    /* JADX WARNING: Removed duplicated region for block: B:93:0x00de A[SYNTHETIC, Splitter:B:93:0x00de] */
    /* JADX WARNING: Removed duplicated region for block: B:96:0x00e3 A[SYNTHETIC, Splitter:B:96:0x00e3] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:53:0x0091=Splitter:B:53:0x0091, B:71:0x00b4=Splitter:B:71:0x00b4} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getIMSI(android.content.Context r14) {
        /*
            r3 = 0
            r5 = 0
            java.lang.String r12 = "android.permission.READ_EXTERNAL_STORAGE"
            boolean r12 = checkPermission(r14, r12)
            if (r12 == 0) goto L_0x006d
            java.io.File r5 = new java.io.File
            java.io.File r12 = r14.getCacheDir()
            java.lang.String r13 = "imsi"
            r5.<init>(r12, r13)
            boolean r12 = r5.exists()
            if (r12 == 0) goto L_0x006d
            boolean r12 = r5.isFile()
            if (r12 == 0) goto L_0x006d
            r1 = 0
            r8 = 0
            r6 = 0
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ FileNotFoundException -> 0x0090, IOException -> 0x00b3 }
            r2.<init>(r5)     // Catch:{ FileNotFoundException -> 0x0090, IOException -> 0x00b3 }
            java.io.InputStreamReader r7 = new java.io.InputStreamReader     // Catch:{ FileNotFoundException -> 0x0135, IOException -> 0x0129, all -> 0x011d }
            r7.<init>(r2)     // Catch:{ FileNotFoundException -> 0x0135, IOException -> 0x0129, all -> 0x011d }
            java.io.BufferedReader r9 = new java.io.BufferedReader     // Catch:{ FileNotFoundException -> 0x0139, IOException -> 0x012c, all -> 0x0120 }
            r9.<init>(r7)     // Catch:{ FileNotFoundException -> 0x0139, IOException -> 0x012c, all -> 0x0120 }
            r10 = 0
            java.lang.String r10 = r9.readLine()     // Catch:{ FileNotFoundException -> 0x013e, IOException -> 0x0130, all -> 0x0124 }
            if (r10 == 0) goto L_0x005e
            r3 = r10
            writeIMSI2XML(r14, r3)     // Catch:{ FileNotFoundException -> 0x013e, IOException -> 0x0130, all -> 0x0124 }
            if (r9 == 0) goto L_0x0043
            r9.close()     // Catch:{ IOException -> 0x004f }
        L_0x0043:
            if (r7 == 0) goto L_0x0048
            r7.close()     // Catch:{ IOException -> 0x0054 }
        L_0x0048:
            if (r2 == 0) goto L_0x004d
            r2.close()     // Catch:{ IOException -> 0x0059 }
        L_0x004d:
            r4 = r3
        L_0x004e:
            return r4
        L_0x004f:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0043
        L_0x0054:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0048
        L_0x0059:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x004d
        L_0x005e:
            if (r9 == 0) goto L_0x0063
            r9.close()     // Catch:{ IOException -> 0x0081 }
        L_0x0063:
            if (r7 == 0) goto L_0x0068
            r7.close()     // Catch:{ IOException -> 0x0086 }
        L_0x0068:
            if (r2 == 0) goto L_0x006d
            r2.close()     // Catch:{ IOException -> 0x008b }
        L_0x006d:
            java.lang.String r12 = "GAME_SP"
            java.lang.String r13 = "sp_imsi"
            java.lang.String r10 = com.baitian.util.SharedPreferencesUtils.getString(r14, r12, r13)
            boolean r12 = android.text.TextUtils.isEmpty(r10)
            if (r12 != 0) goto L_0x00f6
            r3 = r10
            writeParamters2File(r14, r5, r3)
            r4 = r3
            goto L_0x004e
        L_0x0081:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0063
        L_0x0086:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0068
        L_0x008b:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x006d
        L_0x0090:
            r0 = move-exception
        L_0x0091:
            r0.printStackTrace()     // Catch:{ all -> 0x00d6 }
            if (r8 == 0) goto L_0x0099
            r8.close()     // Catch:{ IOException -> 0x00a9 }
        L_0x0099:
            if (r6 == 0) goto L_0x009e
            r6.close()     // Catch:{ IOException -> 0x00ae }
        L_0x009e:
            if (r1 == 0) goto L_0x006d
            r1.close()     // Catch:{ IOException -> 0x00a4 }
            goto L_0x006d
        L_0x00a4:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x006d
        L_0x00a9:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0099
        L_0x00ae:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x009e
        L_0x00b3:
            r0 = move-exception
        L_0x00b4:
            r0.printStackTrace()     // Catch:{ all -> 0x00d6 }
            if (r8 == 0) goto L_0x00bc
            r8.close()     // Catch:{ IOException -> 0x00cc }
        L_0x00bc:
            if (r6 == 0) goto L_0x00c1
            r6.close()     // Catch:{ IOException -> 0x00d1 }
        L_0x00c1:
            if (r1 == 0) goto L_0x006d
            r1.close()     // Catch:{ IOException -> 0x00c7 }
            goto L_0x006d
        L_0x00c7:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x006d
        L_0x00cc:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x00bc
        L_0x00d1:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x00c1
        L_0x00d6:
            r12 = move-exception
        L_0x00d7:
            if (r8 == 0) goto L_0x00dc
            r8.close()     // Catch:{ IOException -> 0x00e7 }
        L_0x00dc:
            if (r6 == 0) goto L_0x00e1
            r6.close()     // Catch:{ IOException -> 0x00ec }
        L_0x00e1:
            if (r1 == 0) goto L_0x00e6
            r1.close()     // Catch:{ IOException -> 0x00f1 }
        L_0x00e6:
            throw r12
        L_0x00e7:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x00dc
        L_0x00ec:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x00e1
        L_0x00f1:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x00e6
        L_0x00f6:
            java.lang.String r12 = "android.permission.READ_PHONE_STATE"
            boolean r12 = checkPermission(r14, r12)
            if (r12 == 0) goto L_0x010a
            java.lang.String r12 = "phone"
            java.lang.Object r11 = r14.getSystemService(r12)
            android.telephony.TelephonyManager r11 = (android.telephony.TelephonyManager) r11
            java.lang.String r3 = r11.getSubscriberId()
        L_0x010a:
            boolean r12 = android.text.TextUtils.isEmpty(r3)
            if (r12 == 0) goto L_0x0114
            java.lang.String r3 = getUUID()
        L_0x0114:
            writeIMSI2XML(r14, r3)
            writeParamters2File(r14, r5, r3)
            r4 = r3
            goto L_0x004e
        L_0x011d:
            r12 = move-exception
            r1 = r2
            goto L_0x00d7
        L_0x0120:
            r12 = move-exception
            r6 = r7
            r1 = r2
            goto L_0x00d7
        L_0x0124:
            r12 = move-exception
            r6 = r7
            r8 = r9
            r1 = r2
            goto L_0x00d7
        L_0x0129:
            r0 = move-exception
            r1 = r2
            goto L_0x00b4
        L_0x012c:
            r0 = move-exception
            r6 = r7
            r1 = r2
            goto L_0x00b4
        L_0x0130:
            r0 = move-exception
            r6 = r7
            r8 = r9
            r1 = r2
            goto L_0x00b4
        L_0x0135:
            r0 = move-exception
            r1 = r2
            goto L_0x0091
        L_0x0139:
            r0 = move-exception
            r6 = r7
            r1 = r2
            goto L_0x0091
        L_0x013e:
            r0 = move-exception
            r6 = r7
            r8 = r9
            r1 = r2
            goto L_0x0091
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baitian.util.BtUtils.getIMSI(android.content.Context):java.lang.String");
    }

    public static boolean getbooleanMeTaDataFalse(Context ctx, String matchmsg) {
        if (matchmsg == null || "".equals(matchmsg)) {
            return false;
        }
        boolean z = false;
        try {
            return ctx.getPackageManager().getApplicationInfo(ctx.getPackageName(), 128).metaData.getBoolean(matchmsg, false);
        } catch (Exception e) {
            e.printStackTrace();
            return z;
        }
    }

    public static String getMeTaData(Context ctx, String matchmsg) {
        if (matchmsg == null || "".equals(matchmsg)) {
            return "-1";
        }
        try {
            String channel = ctx.getPackageManager().getApplicationInfo(ctx.getPackageName(), 128).metaData.getString(matchmsg);
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
        if (matchmsg == null || "".equals(matchmsg)) {
            return true;
        }
        boolean z = true;
        try {
            return ctx.getPackageManager().getApplicationInfo(ctx.getPackageName(), 128).metaData.getBoolean(matchmsg, true);
        } catch (Exception e) {
            e.printStackTrace();
            return z;
        }
    }

    public static String getNOXMeTaData(Context ctx, String matchmsg) {
        if (matchmsg == null || "".equals(matchmsg)) {
            return "-1";
        }
        try {
            String meta = ctx.getPackageManager().getApplicationInfo(ctx.getPackageName(), 128).metaData.getString(matchmsg);
            if (!TextUtils.isEmpty(meta)) {
                return meta;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "-1";
    }

    public static int getIntNoXMeTaData(Context ctx, String matchmsg) {
        if (matchmsg == null || "".equals(matchmsg)) {
            return -1;
        }
        try {
            int meta = ctx.getPackageManager().getApplicationInfo(ctx.getPackageName(), 128).metaData.getInt(matchmsg);
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
