package com.btgame.sdk.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build.VERSION;
import android.support.p000v4.content.ContextCompat;
import android.support.p000v4.content.PermissionChecker;
import android.text.TextUtils;
import android.util.Log;
import com.adjust.sdk.Constants;
import com.facebook.appevents.AppEventsConstants;
import java.io.File;
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

    /* JADX WARNING: Removed duplicated region for block: B:49:0x0089  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x00a7 A[SYNTHETIC, Splitter:B:60:0x00a7] */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x00ac A[SYNTHETIC, Splitter:B:63:0x00ac] */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x00b1 A[SYNTHETIC, Splitter:B:66:0x00b1] */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x00c7 A[SYNTHETIC, Splitter:B:76:0x00c7] */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x00cc A[SYNTHETIC, Splitter:B:79:0x00cc] */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x00d1 A[SYNTHETIC, Splitter:B:82:0x00d1] */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x00e4  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getIMEI(android.content.Context r14) {
        /*
            r3 = 0
            r5 = 0
            java.lang.String r12 = "android.permission.READ_EXTERNAL_STORAGE"
            boolean r12 = checkPermission(r14, r12)
            if (r12 == 0) goto L_0x007b
            java.io.File r5 = new java.io.File
            java.io.File r12 = com.btgame.sdk.util.FileUtil.getGameDir(r14)
            java.lang.String r13 = "imei"
            r5.<init>(r12, r13)
            boolean r12 = r5.exists()
            if (r12 != 0) goto L_0x0021
            java.lang.String r12 = "imei"
            java.io.File r5 = com.btgame.sdk.util.FileUtil.makeFileInGamedir(r12)
        L_0x0021:
            if (r5 == 0) goto L_0x007b
            boolean r12 = r5.exists()
            if (r12 == 0) goto L_0x007b
            boolean r12 = r5.isFile()
            if (r12 == 0) goto L_0x007b
            r1 = 0
            r8 = 0
            r6 = 0
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ IOException -> 0x009e }
            r2.<init>(r5)     // Catch:{ IOException -> 0x009e }
            java.io.InputStreamReader r7 = new java.io.InputStreamReader     // Catch:{ IOException -> 0x0117, all -> 0x010b }
            r7.<init>(r2)     // Catch:{ IOException -> 0x0117, all -> 0x010b }
            java.io.BufferedReader r9 = new java.io.BufferedReader     // Catch:{ IOException -> 0x011a, all -> 0x010e }
            r9.<init>(r7)     // Catch:{ IOException -> 0x011a, all -> 0x010e }
            r10 = 0
            java.lang.String r10 = r9.readLine()     // Catch:{ IOException -> 0x011e, all -> 0x0112 }
            if (r10 == 0) goto L_0x006c
            r3 = r10
            writeIMEI2XML(r14, r3)     // Catch:{ IOException -> 0x011e, all -> 0x0112 }
            if (r9 == 0) goto L_0x0051
            r9.close()     // Catch:{ IOException -> 0x005d }
        L_0x0051:
            if (r7 == 0) goto L_0x0056
            r7.close()     // Catch:{ IOException -> 0x0062 }
        L_0x0056:
            if (r2 == 0) goto L_0x005b
            r2.close()     // Catch:{ IOException -> 0x0067 }
        L_0x005b:
            r4 = r3
        L_0x005c:
            return r4
        L_0x005d:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0051
        L_0x0062:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0056
        L_0x0067:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x005b
        L_0x006c:
            if (r9 == 0) goto L_0x0071
            r9.close()     // Catch:{ IOException -> 0x008f }
        L_0x0071:
            if (r7 == 0) goto L_0x0076
            r7.close()     // Catch:{ IOException -> 0x0094 }
        L_0x0076:
            if (r2 == 0) goto L_0x007b
            r2.close()     // Catch:{ IOException -> 0x0099 }
        L_0x007b:
            java.lang.String r12 = "GAME_SP"
            java.lang.String r13 = "sp_imei"
            java.lang.String r10 = com.btgame.sdk.util.SharedPreferencesUtils.getString(r14, r12, r13)
            boolean r12 = android.text.TextUtils.isEmpty(r10)
            if (r12 != 0) goto L_0x00e4
            r3 = r10
            writeParamters2File(r14, r5, r3)
            r4 = r3
            goto L_0x005c
        L_0x008f:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0071
        L_0x0094:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0076
        L_0x0099:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x007b
        L_0x009e:
            r0 = move-exception
        L_0x009f:
            com.btgame.onesdk.frame.utils.BuglyHelper.postCatchedException(r0)     // Catch:{ all -> 0x00c4 }
            r0.printStackTrace()     // Catch:{ all -> 0x00c4 }
            if (r8 == 0) goto L_0x00aa
            r8.close()     // Catch:{ IOException -> 0x00ba }
        L_0x00aa:
            if (r6 == 0) goto L_0x00af
            r6.close()     // Catch:{ IOException -> 0x00bf }
        L_0x00af:
            if (r1 == 0) goto L_0x007b
            r1.close()     // Catch:{ IOException -> 0x00b5 }
            goto L_0x007b
        L_0x00b5:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x007b
        L_0x00ba:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x00aa
        L_0x00bf:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x00af
        L_0x00c4:
            r12 = move-exception
        L_0x00c5:
            if (r8 == 0) goto L_0x00ca
            r8.close()     // Catch:{ IOException -> 0x00d5 }
        L_0x00ca:
            if (r6 == 0) goto L_0x00cf
            r6.close()     // Catch:{ IOException -> 0x00da }
        L_0x00cf:
            if (r1 == 0) goto L_0x00d4
            r1.close()     // Catch:{ IOException -> 0x00df }
        L_0x00d4:
            throw r12
        L_0x00d5:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x00ca
        L_0x00da:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x00cf
        L_0x00df:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x00d4
        L_0x00e4:
            java.lang.String r12 = "android.permission.READ_PHONE_STATE"
            boolean r12 = checkPermission(r14, r12)
            if (r12 == 0) goto L_0x00f8
            java.lang.String r12 = "phone"
            java.lang.Object r11 = r14.getSystemService(r12)
            android.telephony.TelephonyManager r11 = (android.telephony.TelephonyManager) r11
            java.lang.String r3 = r11.getDeviceId()
        L_0x00f8:
            boolean r12 = android.text.TextUtils.isEmpty(r3)
            if (r12 == 0) goto L_0x0102
            java.lang.String r3 = getUUID()
        L_0x0102:
            writeIMEI2XML(r14, r3)
            writeParamters2File(r14, r5, r3)
            r4 = r3
            goto L_0x005c
        L_0x010b:
            r12 = move-exception
            r1 = r2
            goto L_0x00c5
        L_0x010e:
            r12 = move-exception
            r6 = r7
            r1 = r2
            goto L_0x00c5
        L_0x0112:
            r12 = move-exception
            r6 = r7
            r8 = r9
            r1 = r2
            goto L_0x00c5
        L_0x0117:
            r0 = move-exception
            r1 = r2
            goto L_0x009f
        L_0x011a:
            r0 = move-exception
            r6 = r7
            r1 = r2
            goto L_0x009f
        L_0x011e:
            r0 = move-exception
            r6 = r7
            r8 = r9
            r1 = r2
            goto L_0x009f
        */
        throw new UnsupportedOperationException("Method not decompiled: com.btgame.sdk.util.BtUtils.getIMEI(android.content.Context):java.lang.String");
    }

    public static void setDefaultImeiTocache(Context ctx, String defaultImsi) {
        if (!TextUtils.isEmpty(defaultImsi)) {
            File imeiFile = null;
            if (checkPermission(ctx, "android.permission.READ_EXTERNAL_STORAGE")) {
                imeiFile = new File(FileUtil.getGameDir(ctx), IMEI_FILENAME);
                if (!imeiFile.exists()) {
                    imeiFile = FileUtil.makeFileInGamedir(IMEI_FILENAME);
                }
            }
            writeIMEI2XML(ctx, defaultImsi);
            writeParamters2File(ctx, imeiFile, defaultImsi);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:43:0x0071 A[SYNTHETIC, Splitter:B:43:0x0071] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x0076 A[Catch:{ IOException -> 0x007f }] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x007b A[Catch:{ IOException -> 0x007f }] */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x0087 A[SYNTHETIC, Splitter:B:53:0x0087] */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x008c A[Catch:{ IOException -> 0x0095 }] */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x0091 A[Catch:{ IOException -> 0x0095 }] */
    /* JADX WARNING: Removed duplicated region for block: B:81:? A[RETURN, SYNTHETIC] */
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
            java.io.File r5 = r10.getParentFile()     // Catch:{ IOException -> 0x006b }
            if (r5 != 0) goto L_0x002f
            if (r6 == 0) goto L_0x001f
            r6.close()     // Catch:{ IOException -> 0x002a }
        L_0x001f:
            if (r3 == 0) goto L_0x0024
            r3.close()     // Catch:{ IOException -> 0x002a }
        L_0x0024:
            if (r1 == 0) goto L_0x0008
            r1.close()     // Catch:{ IOException -> 0x002a }
            goto L_0x0008
        L_0x002a:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0008
        L_0x002f:
            boolean r8 = r5.exists()     // Catch:{ IOException -> 0x006b }
            if (r8 != 0) goto L_0x0038
            r5.mkdirs()     // Catch:{ IOException -> 0x006b }
        L_0x0038:
            r10.createNewFile()     // Catch:{ IOException -> 0x006b }
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x006b }
            r2.<init>(r10)     // Catch:{ IOException -> 0x006b }
            java.io.OutputStreamWriter r4 = new java.io.OutputStreamWriter     // Catch:{ IOException -> 0x00a6, all -> 0x009a }
            r4.<init>(r2)     // Catch:{ IOException -> 0x00a6, all -> 0x009a }
            java.io.BufferedWriter r7 = new java.io.BufferedWriter     // Catch:{ IOException -> 0x00a9, all -> 0x009d }
            r7.<init>(r4)     // Catch:{ IOException -> 0x00a9, all -> 0x009d }
            r7.write(r11)     // Catch:{ IOException -> 0x00ad, all -> 0x00a1 }
            r7.flush()     // Catch:{ IOException -> 0x00ad, all -> 0x00a1 }
            if (r7 == 0) goto L_0x0055
            r7.close()     // Catch:{ IOException -> 0x0063 }
        L_0x0055:
            if (r4 == 0) goto L_0x005a
            r4.close()     // Catch:{ IOException -> 0x0063 }
        L_0x005a:
            if (r2 == 0) goto L_0x005f
            r2.close()     // Catch:{ IOException -> 0x0063 }
        L_0x005f:
            r6 = r7
            r3 = r4
            r1 = r2
            goto L_0x0008
        L_0x0063:
            r0 = move-exception
            r0.printStackTrace()
            r6 = r7
            r3 = r4
            r1 = r2
            goto L_0x0008
        L_0x006b:
            r0 = move-exception
        L_0x006c:
            r0.printStackTrace()     // Catch:{ all -> 0x0084 }
            if (r6 == 0) goto L_0x0074
            r6.close()     // Catch:{ IOException -> 0x007f }
        L_0x0074:
            if (r3 == 0) goto L_0x0079
            r3.close()     // Catch:{ IOException -> 0x007f }
        L_0x0079:
            if (r1 == 0) goto L_0x0008
            r1.close()     // Catch:{ IOException -> 0x007f }
            goto L_0x0008
        L_0x007f:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0008
        L_0x0084:
            r8 = move-exception
        L_0x0085:
            if (r6 == 0) goto L_0x008a
            r6.close()     // Catch:{ IOException -> 0x0095 }
        L_0x008a:
            if (r3 == 0) goto L_0x008f
            r3.close()     // Catch:{ IOException -> 0x0095 }
        L_0x008f:
            if (r1 == 0) goto L_0x0094
            r1.close()     // Catch:{ IOException -> 0x0095 }
        L_0x0094:
            throw r8
        L_0x0095:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0094
        L_0x009a:
            r8 = move-exception
            r1 = r2
            goto L_0x0085
        L_0x009d:
            r8 = move-exception
            r3 = r4
            r1 = r2
            goto L_0x0085
        L_0x00a1:
            r8 = move-exception
            r6 = r7
            r3 = r4
            r1 = r2
            goto L_0x0085
        L_0x00a6:
            r0 = move-exception
            r1 = r2
            goto L_0x006c
        L_0x00a9:
            r0 = move-exception
            r3 = r4
            r1 = r2
            goto L_0x006c
        L_0x00ad:
            r0 = move-exception
            r6 = r7
            r3 = r4
            r1 = r2
            goto L_0x006c
        */
        throw new UnsupportedOperationException("Method not decompiled: com.btgame.sdk.util.BtUtils.writeParamters2File(android.content.Context, java.io.File, java.lang.String):void");
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

    /* JADX WARNING: Removed duplicated region for block: B:41:0x007f  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x0093 A[SYNTHETIC, Splitter:B:48:0x0093] */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x0098 A[Catch:{ IOException -> 0x00a1 }] */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x009d A[Catch:{ IOException -> 0x00a1 }] */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x00a9 A[SYNTHETIC, Splitter:B:58:0x00a9] */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x00ae A[Catch:{ IOException -> 0x00b7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x00b3 A[Catch:{ IOException -> 0x00b7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x00bc  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getIMSI(android.content.Context r14) {
        /*
            r3 = 0
            r5 = 0
            java.lang.String r12 = "android.permission.READ_EXTERNAL_STORAGE"
            boolean r12 = checkPermission(r14, r12)
            if (r12 == 0) goto L_0x0071
            java.io.File r5 = new java.io.File
            java.io.File r12 = com.btgame.sdk.util.FileUtil.getGameDir(r14)
            java.lang.String r13 = "imsi"
            r5.<init>(r12, r13)
            boolean r12 = r5.exists()
            if (r12 != 0) goto L_0x0021
            java.lang.String r12 = "imsi"
            java.io.File r5 = com.btgame.sdk.util.FileUtil.makeFileInGamedir(r12)
        L_0x0021:
            if (r5 == 0) goto L_0x0071
            boolean r12 = r5.exists()
            if (r12 == 0) goto L_0x0071
            boolean r12 = r5.isFile()
            if (r12 == 0) goto L_0x0071
            r1 = 0
            r8 = 0
            r6 = 0
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ IOException -> 0x008a }
            r2.<init>(r5)     // Catch:{ IOException -> 0x008a }
            java.io.InputStreamReader r7 = new java.io.InputStreamReader     // Catch:{ IOException -> 0x00ef, all -> 0x00e3 }
            r7.<init>(r2)     // Catch:{ IOException -> 0x00ef, all -> 0x00e3 }
            java.io.BufferedReader r9 = new java.io.BufferedReader     // Catch:{ IOException -> 0x00f2, all -> 0x00e6 }
            r9.<init>(r7)     // Catch:{ IOException -> 0x00f2, all -> 0x00e6 }
            r10 = 0
            java.lang.String r10 = r9.readLine()     // Catch:{ IOException -> 0x00f6, all -> 0x00ea }
            if (r10 == 0) goto L_0x0062
            r3 = r10
            writeIMSI2XML(r14, r3)     // Catch:{ IOException -> 0x00f6, all -> 0x00ea }
            if (r9 == 0) goto L_0x0051
            r9.close()     // Catch:{ IOException -> 0x005d }
        L_0x0051:
            if (r7 == 0) goto L_0x0056
            r7.close()     // Catch:{ IOException -> 0x005d }
        L_0x0056:
            if (r2 == 0) goto L_0x005b
            r2.close()     // Catch:{ IOException -> 0x005d }
        L_0x005b:
            r4 = r3
        L_0x005c:
            return r4
        L_0x005d:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x005b
        L_0x0062:
            if (r9 == 0) goto L_0x0067
            r9.close()     // Catch:{ IOException -> 0x0085 }
        L_0x0067:
            if (r7 == 0) goto L_0x006c
            r7.close()     // Catch:{ IOException -> 0x0085 }
        L_0x006c:
            if (r2 == 0) goto L_0x0071
            r2.close()     // Catch:{ IOException -> 0x0085 }
        L_0x0071:
            java.lang.String r12 = "GAME_SP"
            java.lang.String r13 = "sp_imsi"
            java.lang.String r10 = com.btgame.sdk.util.SharedPreferencesUtils.getString(r14, r12, r13)
            boolean r12 = android.text.TextUtils.isEmpty(r10)
            if (r12 != 0) goto L_0x00bc
            r3 = r10
            writeParamters2File(r14, r5, r3)
            r4 = r3
            goto L_0x005c
        L_0x0085:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0071
        L_0x008a:
            r0 = move-exception
        L_0x008b:
            com.btgame.onesdk.frame.utils.BuglyHelper.postCatchedException(r0)     // Catch:{ all -> 0x00a6 }
            r0.printStackTrace()     // Catch:{ all -> 0x00a6 }
            if (r8 == 0) goto L_0x0096
            r8.close()     // Catch:{ IOException -> 0x00a1 }
        L_0x0096:
            if (r6 == 0) goto L_0x009b
            r6.close()     // Catch:{ IOException -> 0x00a1 }
        L_0x009b:
            if (r1 == 0) goto L_0x0071
            r1.close()     // Catch:{ IOException -> 0x00a1 }
            goto L_0x0071
        L_0x00a1:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0071
        L_0x00a6:
            r12 = move-exception
        L_0x00a7:
            if (r8 == 0) goto L_0x00ac
            r8.close()     // Catch:{ IOException -> 0x00b7 }
        L_0x00ac:
            if (r6 == 0) goto L_0x00b1
            r6.close()     // Catch:{ IOException -> 0x00b7 }
        L_0x00b1:
            if (r1 == 0) goto L_0x00b6
            r1.close()     // Catch:{ IOException -> 0x00b7 }
        L_0x00b6:
            throw r12
        L_0x00b7:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x00b6
        L_0x00bc:
            java.lang.String r12 = "android.permission.READ_PHONE_STATE"
            boolean r12 = checkPermission(r14, r12)
            if (r12 == 0) goto L_0x00d0
            java.lang.String r12 = "phone"
            java.lang.Object r11 = r14.getSystemService(r12)
            android.telephony.TelephonyManager r11 = (android.telephony.TelephonyManager) r11
            java.lang.String r3 = r11.getSubscriberId()
        L_0x00d0:
            boolean r12 = android.text.TextUtils.isEmpty(r3)
            if (r12 == 0) goto L_0x00da
            java.lang.String r3 = getUUID()
        L_0x00da:
            writeIMSI2XML(r14, r3)
            writeParamters2File(r14, r5, r3)
            r4 = r3
            goto L_0x005c
        L_0x00e3:
            r12 = move-exception
            r1 = r2
            goto L_0x00a7
        L_0x00e6:
            r12 = move-exception
            r6 = r7
            r1 = r2
            goto L_0x00a7
        L_0x00ea:
            r12 = move-exception
            r6 = r7
            r8 = r9
            r1 = r2
            goto L_0x00a7
        L_0x00ef:
            r0 = move-exception
            r1 = r2
            goto L_0x008b
        L_0x00f2:
            r0 = move-exception
            r6 = r7
            r1 = r2
            goto L_0x008b
        L_0x00f6:
            r0 = move-exception
            r6 = r7
            r8 = r9
            r1 = r2
            goto L_0x008b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.btgame.sdk.util.BtUtils.getIMSI(android.content.Context):java.lang.String");
    }

    public static String getMeTaData(Context ctx, String matchmsg) {
        if (TextUtils.isEmpty(matchmsg)) {
            return "-1";
        }
        try {
            String channel = ctx.getPackageManager().getApplicationInfo(ctx.getPackageName(), 128).metaData.getString(matchmsg);
            String channelID = channel.substring(2, channel.length());
            Log.d(BtsdkLog.TAG, "Meta-Data：" + matchmsg + " = " + channel);
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
        boolean z = true;
        try {
            return ctx.getPackageManager().getApplicationInfo(ctx.getPackageName(), 128).metaData.getBoolean(matchmsg, true);
        } catch (Exception e) {
            e.printStackTrace();
            return z;
        }
    }

    public static boolean getbooleanMeTaDataFalse(Context ctx, String matchmsg) {
        if (TextUtils.isEmpty(matchmsg)) {
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

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0058  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x009f A[SYNTHETIC, Splitter:B:28:0x009f] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00ab A[SYNTHETIC, Splitter:B:34:0x00ab] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int getBtChannelId(android.content.Context r13) {
        /*
            r1 = -1
            android.content.pm.ApplicationInfo r0 = r13.getApplicationInfo()
            java.lang.String r5 = r0.sourceDir
            r8 = 0
            boolean r10 = android.text.TextUtils.isEmpty(r5)
            if (r10 != 0) goto L_0x0055
            java.util.zip.ZipFile r9 = new java.util.zip.ZipFile     // Catch:{ IOException -> 0x007d }
            r9.<init>(r5)     // Catch:{ IOException -> 0x007d }
            java.util.Enumeration r3 = r9.entries()     // Catch:{ IOException -> 0x00b7, all -> 0x00b4 }
        L_0x0017:
            boolean r10 = r3.hasMoreElements()     // Catch:{ IOException -> 0x00b7, all -> 0x00b4 }
            if (r10 == 0) goto L_0x004f
            java.lang.Object r6 = r3.nextElement()     // Catch:{ IOException -> 0x00b7, all -> 0x00b4 }
            java.util.zip.ZipEntry r6 = (java.util.zip.ZipEntry) r6     // Catch:{ IOException -> 0x00b7, all -> 0x00b4 }
            java.lang.String r7 = r6.getName()     // Catch:{ IOException -> 0x00b7, all -> 0x00b4 }
            java.lang.String r10 = "META-INF/"
            boolean r10 = r7.startsWith(r10)     // Catch:{ IOException -> 0x00b7, all -> 0x00b4 }
            if (r10 == 0) goto L_0x0017
            java.lang.String r10 = "META-INF/"
            java.lang.String r11 = ""
            java.lang.String r4 = r7.replace(r10, r11)     // Catch:{ IOException -> 0x00b7, all -> 0x00b4 }
            java.lang.String r10 = "btChannel_"
            boolean r10 = r4.startsWith(r10)     // Catch:{ IOException -> 0x00b7, all -> 0x00b4 }
            if (r10 == 0) goto L_0x0017
            java.lang.String r10 = "btChannel_"
            java.lang.String r11 = ""
            java.lang.String r10 = r4.replace(r10, r11)     // Catch:{ IOException -> 0x00b7, all -> 0x00b4 }
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)     // Catch:{ IOException -> 0x00b7, all -> 0x00b4 }
            int r1 = r10.intValue()     // Catch:{ IOException -> 0x00b7, all -> 0x00b4 }
        L_0x004f:
            if (r9 == 0) goto L_0x00ba
            r9.close()     // Catch:{ IOException -> 0x0077 }
            r8 = r9
        L_0x0055:
            r10 = -1
            if (r1 != r10) goto L_0x005e
            java.lang.String r10 = "btchannelId"
            int r1 = getIntNoXMeTaData(r13, r10)
        L_0x005e:
            java.lang.String r10 = "BtOneSDK"
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            java.lang.String r12 = "BtChannelId ="
            java.lang.StringBuilder r11 = r11.append(r12)
            java.lang.StringBuilder r11 = r11.append(r1)
            java.lang.String r11 = r11.toString()
            android.util.Log.d(r10, r11)
            return r1
        L_0x0077:
            r2 = move-exception
            r2.printStackTrace()
            r8 = r9
            goto L_0x0055
        L_0x007d:
            r2 = move-exception
        L_0x007e:
            r2.printStackTrace()     // Catch:{ all -> 0x00a8 }
            java.lang.String r10 = "BtOneSDK"
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ all -> 0x00a8 }
            r11.<init>()     // Catch:{ all -> 0x00a8 }
            java.lang.String r12 = "META-INF中获取渠道信息异常："
            java.lang.StringBuilder r11 = r11.append(r12)     // Catch:{ all -> 0x00a8 }
            java.lang.String r12 = r2.getMessage()     // Catch:{ all -> 0x00a8 }
            java.lang.StringBuilder r11 = r11.append(r12)     // Catch:{ all -> 0x00a8 }
            java.lang.String r11 = r11.toString()     // Catch:{ all -> 0x00a8 }
            android.util.Log.d(r10, r11)     // Catch:{ all -> 0x00a8 }
            if (r8 == 0) goto L_0x0055
            r8.close()     // Catch:{ IOException -> 0x00a3 }
            goto L_0x0055
        L_0x00a3:
            r2 = move-exception
            r2.printStackTrace()
            goto L_0x0055
        L_0x00a8:
            r10 = move-exception
        L_0x00a9:
            if (r8 == 0) goto L_0x00ae
            r8.close()     // Catch:{ IOException -> 0x00af }
        L_0x00ae:
            throw r10
        L_0x00af:
            r2 = move-exception
            r2.printStackTrace()
            goto L_0x00ae
        L_0x00b4:
            r10 = move-exception
            r8 = r9
            goto L_0x00a9
        L_0x00b7:
            r2 = move-exception
            r8 = r9
            goto L_0x007e
        L_0x00ba:
            r8 = r9
            goto L_0x0055
        */
        throw new UnsupportedOperationException("Method not decompiled: com.btgame.sdk.util.BtUtils.getBtChannelId(android.content.Context):int");
    }
}
