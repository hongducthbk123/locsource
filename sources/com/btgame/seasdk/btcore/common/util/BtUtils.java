package com.btgame.seasdk.btcore.common.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.support.p000v4.content.ContextCompat;
import android.support.p000v4.content.PermissionChecker;
import android.text.TextUtils;
import android.util.Base64;
import com.adjust.sdk.Constants;
import com.facebook.appevents.AppEventsConstants;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.regex.Pattern;
import p004cn.jiguang.net.HttpUtils;

public class BtUtils {
    private static final String IMEI_FILENAME = "imei";
    private static final String IMSI_FILENAME = "imsi";
    private static final String SP_IMEI_KEY_NAME = "sp_imei";
    private static final String SP_IMSI_KEY_NAME = "sp_imsi";
    private static final List<String> codeTables = new ArrayList();

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

    /* JADX WARNING: Removed duplicated region for block: B:49:0x007c  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x009a A[SYNTHETIC, Splitter:B:60:0x009a] */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x009f A[SYNTHETIC, Splitter:B:63:0x009f] */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x00a4 A[SYNTHETIC, Splitter:B:66:0x00a4] */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x00ba A[SYNTHETIC, Splitter:B:76:0x00ba] */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x00bf A[SYNTHETIC, Splitter:B:79:0x00bf] */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x00c4 A[SYNTHETIC, Splitter:B:82:0x00c4] */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x00d7  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getIMEI(android.content.Context r14, java.io.File r15) {
        /*
            r3 = 0
            java.io.File r5 = new java.io.File
            java.lang.String r12 = "imei"
            r5.<init>(r15, r12)
            if (r5 == 0) goto L_0x0016
            boolean r12 = r5.exists()
            if (r12 != 0) goto L_0x0016
            java.lang.String r12 = "imei"
            java.io.File r5 = com.btgame.seasdk.btcore.common.util.BtFileUtil.makeFileInGamedir(r12)
        L_0x0016:
            if (r5 == 0) goto L_0x0070
            boolean r12 = r5.exists()
            if (r12 == 0) goto L_0x0070
            boolean r12 = r5.isFile()
            if (r12 == 0) goto L_0x0070
            r1 = 0
            r8 = 0
            r6 = 0
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ Exception -> 0x0091 }
            r2.<init>(r5)     // Catch:{ Exception -> 0x0091 }
            java.io.InputStreamReader r7 = new java.io.InputStreamReader     // Catch:{ Exception -> 0x0119, all -> 0x010d }
            r7.<init>(r2)     // Catch:{ Exception -> 0x0119, all -> 0x010d }
            java.io.BufferedReader r9 = new java.io.BufferedReader     // Catch:{ Exception -> 0x011d, all -> 0x0110 }
            r9.<init>(r7)     // Catch:{ Exception -> 0x011d, all -> 0x0110 }
            r10 = 0
            java.lang.String r10 = r9.readLine()     // Catch:{ Exception -> 0x0122, all -> 0x0114 }
            if (r10 == 0) goto L_0x0061
            r3 = r10
            writeIMEI2XML(r14, r3)     // Catch:{ Exception -> 0x0122, all -> 0x0114 }
            if (r9 == 0) goto L_0x0046
            r9.close()     // Catch:{ Exception -> 0x0052 }
        L_0x0046:
            if (r7 == 0) goto L_0x004b
            r7.close()     // Catch:{ Exception -> 0x0057 }
        L_0x004b:
            if (r2 == 0) goto L_0x0050
            r2.close()     // Catch:{ Exception -> 0x005c }
        L_0x0050:
            r4 = r3
        L_0x0051:
            return r4
        L_0x0052:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0046
        L_0x0057:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x004b
        L_0x005c:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0050
        L_0x0061:
            if (r9 == 0) goto L_0x0066
            r9.close()     // Catch:{ Exception -> 0x0082 }
        L_0x0066:
            if (r7 == 0) goto L_0x006b
            r7.close()     // Catch:{ Exception -> 0x0087 }
        L_0x006b:
            if (r2 == 0) goto L_0x0070
            r2.close()     // Catch:{ Exception -> 0x008c }
        L_0x0070:
            java.lang.String r12 = "sp_imei"
            java.lang.String r10 = com.btgame.seasdk.btcore.common.util.SharedPreferencesUtils.getString(r12)
            boolean r12 = android.text.TextUtils.isEmpty(r10)
            if (r12 != 0) goto L_0x00d7
            r3 = r10
            writeParamters2File(r14, r5, r3)
            r4 = r3
            goto L_0x0051
        L_0x0082:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0066
        L_0x0087:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x006b
        L_0x008c:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0070
        L_0x0091:
            r0 = move-exception
        L_0x0092:
            com.btgame.seasdk.btcore.common.util.BuglyHelper.postCatchedException(r0)     // Catch:{ all -> 0x00b7 }
            r0.printStackTrace()     // Catch:{ all -> 0x00b7 }
            if (r8 == 0) goto L_0x009d
            r8.close()     // Catch:{ Exception -> 0x00ad }
        L_0x009d:
            if (r6 == 0) goto L_0x00a2
            r6.close()     // Catch:{ Exception -> 0x00b2 }
        L_0x00a2:
            if (r1 == 0) goto L_0x0070
            r1.close()     // Catch:{ Exception -> 0x00a8 }
            goto L_0x0070
        L_0x00a8:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0070
        L_0x00ad:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x009d
        L_0x00b2:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x00a2
        L_0x00b7:
            r12 = move-exception
        L_0x00b8:
            if (r8 == 0) goto L_0x00bd
            r8.close()     // Catch:{ Exception -> 0x00c8 }
        L_0x00bd:
            if (r6 == 0) goto L_0x00c2
            r6.close()     // Catch:{ Exception -> 0x00cd }
        L_0x00c2:
            if (r1 == 0) goto L_0x00c7
            r1.close()     // Catch:{ Exception -> 0x00d2 }
        L_0x00c7:
            throw r12
        L_0x00c8:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x00bd
        L_0x00cd:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x00c2
        L_0x00d2:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x00c7
        L_0x00d7:
            java.lang.String r12 = "android.permission.READ_PHONE_STATE"
            boolean r12 = checkPermission(r14, r12)
            if (r12 == 0) goto L_0x0102
            java.lang.String r12 = "phone"
            java.lang.Object r11 = r14.getSystemService(r12)
            android.telephony.TelephonyManager r11 = (android.telephony.TelephonyManager) r11
            java.lang.String r3 = r11.getDeviceId()
        L_0x00eb:
            if (r3 == 0) goto L_0x00f5
            java.lang.String r12 = ""
            boolean r12 = r12.equals(r3)
            if (r12 == 0) goto L_0x00f9
        L_0x00f5:
            java.lang.String r3 = getUUID()
        L_0x00f9:
            writeIMEI2XML(r14, r3)
            writeParamters2File(r14, r5, r3)
            r4 = r3
            goto L_0x0051
        L_0x0102:
            android.content.ContentResolver r12 = r14.getContentResolver()
            java.lang.String r13 = "android_id"
            java.lang.String r3 = android.provider.Settings.Secure.getString(r12, r13)
            goto L_0x00eb
        L_0x010d:
            r12 = move-exception
            r1 = r2
            goto L_0x00b8
        L_0x0110:
            r12 = move-exception
            r6 = r7
            r1 = r2
            goto L_0x00b8
        L_0x0114:
            r12 = move-exception
            r6 = r7
            r8 = r9
            r1 = r2
            goto L_0x00b8
        L_0x0119:
            r0 = move-exception
            r1 = r2
            goto L_0x0092
        L_0x011d:
            r0 = move-exception
            r6 = r7
            r1 = r2
            goto L_0x0092
        L_0x0122:
            r0 = move-exception
            r6 = r7
            r8 = r9
            r1 = r2
            goto L_0x0092
        */
        throw new UnsupportedOperationException("Method not decompiled: com.btgame.seasdk.btcore.common.util.BtUtils.getIMEI(android.content.Context, java.io.File):java.lang.String");
    }

    /* JADX WARNING: Removed duplicated region for block: B:43:0x006f A[SYNTHETIC, Splitter:B:43:0x006f] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x0074 A[Catch:{ Exception -> 0x007d }] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x0079 A[Catch:{ Exception -> 0x007d }] */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x0085 A[SYNTHETIC, Splitter:B:53:0x0085] */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x008a A[Catch:{ Exception -> 0x0093 }] */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x008f A[Catch:{ Exception -> 0x0093 }] */
    /* JADX WARNING: Removed duplicated region for block: B:80:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void writeParamters2File(android.content.Context r9, java.io.File r10, java.lang.String r11) {
        /*
            if (r10 == 0) goto L_0x0008
            boolean r8 = android.text.TextUtils.isEmpty(r11)
            if (r8 == 0) goto L_0x0009
        L_0x0008:
            return
        L_0x0009:
            r1 = 0
            r3 = 0
            r6 = 0
            java.io.File r5 = r10.getParentFile()     // Catch:{ Exception -> 0x0069 }
            if (r5 != 0) goto L_0x0027
            if (r6 == 0) goto L_0x0017
            r6.close()     // Catch:{ Exception -> 0x0022 }
        L_0x0017:
            if (r3 == 0) goto L_0x001c
            r3.close()     // Catch:{ Exception -> 0x0022 }
        L_0x001c:
            if (r1 == 0) goto L_0x0008
            r1.close()     // Catch:{ Exception -> 0x0022 }
            goto L_0x0008
        L_0x0022:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0008
        L_0x0027:
            boolean r8 = r5.exists()     // Catch:{ Exception -> 0x0069 }
            if (r8 == 0) goto L_0x0033
            boolean r8 = r5.isDirectory()     // Catch:{ Exception -> 0x0069 }
            if (r8 != 0) goto L_0x0036
        L_0x0033:
            r5.mkdirs()     // Catch:{ Exception -> 0x0069 }
        L_0x0036:
            r10.createNewFile()     // Catch:{ Exception -> 0x0069 }
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x0069 }
            r2.<init>(r10)     // Catch:{ Exception -> 0x0069 }
            java.io.OutputStreamWriter r4 = new java.io.OutputStreamWriter     // Catch:{ Exception -> 0x00a4, all -> 0x0098 }
            r4.<init>(r2)     // Catch:{ Exception -> 0x00a4, all -> 0x0098 }
            java.io.BufferedWriter r7 = new java.io.BufferedWriter     // Catch:{ Exception -> 0x00a7, all -> 0x009b }
            r7.<init>(r4)     // Catch:{ Exception -> 0x00a7, all -> 0x009b }
            r7.write(r11)     // Catch:{ Exception -> 0x00ab, all -> 0x009f }
            r7.flush()     // Catch:{ Exception -> 0x00ab, all -> 0x009f }
            if (r7 == 0) goto L_0x0053
            r7.close()     // Catch:{ Exception -> 0x0061 }
        L_0x0053:
            if (r4 == 0) goto L_0x0058
            r4.close()     // Catch:{ Exception -> 0x0061 }
        L_0x0058:
            if (r2 == 0) goto L_0x005d
            r2.close()     // Catch:{ Exception -> 0x0061 }
        L_0x005d:
            r6 = r7
            r3 = r4
            r1 = r2
            goto L_0x0008
        L_0x0061:
            r0 = move-exception
            r0.printStackTrace()
            r6 = r7
            r3 = r4
            r1 = r2
            goto L_0x0008
        L_0x0069:
            r0 = move-exception
        L_0x006a:
            r0.printStackTrace()     // Catch:{ all -> 0x0082 }
            if (r6 == 0) goto L_0x0072
            r6.close()     // Catch:{ Exception -> 0x007d }
        L_0x0072:
            if (r3 == 0) goto L_0x0077
            r3.close()     // Catch:{ Exception -> 0x007d }
        L_0x0077:
            if (r1 == 0) goto L_0x0008
            r1.close()     // Catch:{ Exception -> 0x007d }
            goto L_0x0008
        L_0x007d:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0008
        L_0x0082:
            r8 = move-exception
        L_0x0083:
            if (r6 == 0) goto L_0x0088
            r6.close()     // Catch:{ Exception -> 0x0093 }
        L_0x0088:
            if (r3 == 0) goto L_0x008d
            r3.close()     // Catch:{ Exception -> 0x0093 }
        L_0x008d:
            if (r1 == 0) goto L_0x0092
            r1.close()     // Catch:{ Exception -> 0x0093 }
        L_0x0092:
            throw r8
        L_0x0093:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0092
        L_0x0098:
            r8 = move-exception
            r1 = r2
            goto L_0x0083
        L_0x009b:
            r8 = move-exception
            r3 = r4
            r1 = r2
            goto L_0x0083
        L_0x009f:
            r8 = move-exception
            r6 = r7
            r3 = r4
            r1 = r2
            goto L_0x0083
        L_0x00a4:
            r0 = move-exception
            r1 = r2
            goto L_0x006a
        L_0x00a7:
            r0 = move-exception
            r3 = r4
            r1 = r2
            goto L_0x006a
        L_0x00ab:
            r0 = move-exception
            r6 = r7
            r3 = r4
            r1 = r2
            goto L_0x006a
        */
        throw new UnsupportedOperationException("Method not decompiled: com.btgame.seasdk.btcore.common.util.BtUtils.writeParamters2File(android.content.Context, java.io.File, java.lang.String):void");
    }

    private static void writeIMEI2XML(Context ctx, String imei) {
        if (!TextUtils.isEmpty(imei)) {
            SharedPreferencesUtils.setString(SP_IMEI_KEY_NAME, imei);
        }
    }

    private static void writeIMSI2XML(Context ctx, String imsi) {
        if (!TextUtils.isEmpty(imsi)) {
            SharedPreferencesUtils.setString(SP_IMSI_KEY_NAME, imsi);
        }
    }

    public static String getUUID() {
        String str = UUID.randomUUID().toString();
        return str.substring(0, 8) + str.substring(9, 13) + str.substring(14, 18) + str.substring(19, 23) + str.substring(24);
    }

    /* JADX WARNING: Removed duplicated region for block: B:39:0x0070  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x0084 A[SYNTHETIC, Splitter:B:46:0x0084] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0089 A[Catch:{ IOException -> 0x0092 }] */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x008e A[Catch:{ IOException -> 0x0092 }] */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x009a A[SYNTHETIC, Splitter:B:56:0x009a] */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x009f A[Catch:{ IOException -> 0x00a8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x00a4 A[Catch:{ IOException -> 0x00a8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x00ad  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getIMSI(android.content.Context r14, java.io.File r15) {
        /*
            r3 = 0
            java.io.File r5 = new java.io.File
            java.lang.String r12 = "imsi"
            r5.<init>(r15, r12)
            boolean r12 = r5.exists()
            if (r12 != 0) goto L_0x0014
            java.lang.String r12 = "imsi"
            java.io.File r5 = com.btgame.seasdk.btcore.common.util.BtFileUtil.makeFileInGamedir(r12)
        L_0x0014:
            if (r5 == 0) goto L_0x0064
            boolean r12 = r5.exists()
            if (r12 == 0) goto L_0x0064
            boolean r12 = r5.isFile()
            if (r12 == 0) goto L_0x0064
            r1 = 0
            r8 = 0
            r6 = 0
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ IOException -> 0x007b }
            r2.<init>(r5)     // Catch:{ IOException -> 0x007b }
            java.io.InputStreamReader r7 = new java.io.InputStreamReader     // Catch:{ IOException -> 0x00ef, all -> 0x00e3 }
            r7.<init>(r2)     // Catch:{ IOException -> 0x00ef, all -> 0x00e3 }
            java.io.BufferedReader r9 = new java.io.BufferedReader     // Catch:{ IOException -> 0x00f2, all -> 0x00e6 }
            r9.<init>(r7)     // Catch:{ IOException -> 0x00f2, all -> 0x00e6 }
            r10 = 0
            java.lang.String r10 = r9.readLine()     // Catch:{ IOException -> 0x00f6, all -> 0x00ea }
            if (r10 == 0) goto L_0x0055
            r3 = r10
            writeIMSI2XML(r14, r3)     // Catch:{ IOException -> 0x00f6, all -> 0x00ea }
            if (r9 == 0) goto L_0x0044
            r9.close()     // Catch:{ IOException -> 0x0050 }
        L_0x0044:
            if (r7 == 0) goto L_0x0049
            r7.close()     // Catch:{ IOException -> 0x0050 }
        L_0x0049:
            if (r2 == 0) goto L_0x004e
            r2.close()     // Catch:{ IOException -> 0x0050 }
        L_0x004e:
            r4 = r3
        L_0x004f:
            return r4
        L_0x0050:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x004e
        L_0x0055:
            if (r9 == 0) goto L_0x005a
            r9.close()     // Catch:{ IOException -> 0x0076 }
        L_0x005a:
            if (r7 == 0) goto L_0x005f
            r7.close()     // Catch:{ IOException -> 0x0076 }
        L_0x005f:
            if (r2 == 0) goto L_0x0064
            r2.close()     // Catch:{ IOException -> 0x0076 }
        L_0x0064:
            java.lang.String r12 = "sp_imsi"
            java.lang.String r10 = com.btgame.seasdk.btcore.common.util.SharedPreferencesUtils.getString(r12)
            boolean r12 = android.text.TextUtils.isEmpty(r10)
            if (r12 != 0) goto L_0x00ad
            r3 = r10
            writeParamters2File(r14, r5, r3)
            r4 = r3
            goto L_0x004f
        L_0x0076:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0064
        L_0x007b:
            r0 = move-exception
        L_0x007c:
            com.btgame.seasdk.btcore.common.util.BuglyHelper.postCatchedException(r0)     // Catch:{ all -> 0x0097 }
            r0.printStackTrace()     // Catch:{ all -> 0x0097 }
            if (r8 == 0) goto L_0x0087
            r8.close()     // Catch:{ IOException -> 0x0092 }
        L_0x0087:
            if (r6 == 0) goto L_0x008c
            r6.close()     // Catch:{ IOException -> 0x0092 }
        L_0x008c:
            if (r1 == 0) goto L_0x0064
            r1.close()     // Catch:{ IOException -> 0x0092 }
            goto L_0x0064
        L_0x0092:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0064
        L_0x0097:
            r12 = move-exception
        L_0x0098:
            if (r8 == 0) goto L_0x009d
            r8.close()     // Catch:{ IOException -> 0x00a8 }
        L_0x009d:
            if (r6 == 0) goto L_0x00a2
            r6.close()     // Catch:{ IOException -> 0x00a8 }
        L_0x00a2:
            if (r1 == 0) goto L_0x00a7
            r1.close()     // Catch:{ IOException -> 0x00a8 }
        L_0x00a7:
            throw r12
        L_0x00a8:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x00a7
        L_0x00ad:
            java.lang.String r12 = "android.permission.READ_PHONE_STATE"
            boolean r12 = checkPermission(r14, r12)
            if (r12 == 0) goto L_0x00d8
            java.lang.String r12 = "phone"
            java.lang.Object r11 = r14.getSystemService(r12)
            android.telephony.TelephonyManager r11 = (android.telephony.TelephonyManager) r11
            java.lang.String r3 = r11.getSubscriberId()
        L_0x00c1:
            if (r3 == 0) goto L_0x00cb
            java.lang.String r12 = ""
            boolean r12 = r12.equals(r3)
            if (r12 == 0) goto L_0x00cf
        L_0x00cb:
            java.lang.String r3 = getUUID()
        L_0x00cf:
            writeIMSI2XML(r14, r3)
            writeParamters2File(r14, r5, r3)
            r4 = r3
            goto L_0x004f
        L_0x00d8:
            android.content.ContentResolver r12 = r14.getContentResolver()
            java.lang.String r13 = "android_id"
            java.lang.String r3 = android.provider.Settings.Secure.getString(r12, r13)
            goto L_0x00c1
        L_0x00e3:
            r12 = move-exception
            r1 = r2
            goto L_0x0098
        L_0x00e6:
            r12 = move-exception
            r6 = r7
            r1 = r2
            goto L_0x0098
        L_0x00ea:
            r12 = move-exception
            r6 = r7
            r8 = r9
            r1 = r2
            goto L_0x0098
        L_0x00ef:
            r0 = move-exception
            r1 = r2
            goto L_0x007c
        L_0x00f2:
            r0 = move-exception
            r6 = r7
            r1 = r2
            goto L_0x007c
        L_0x00f6:
            r0 = move-exception
            r6 = r7
            r8 = r9
            r1 = r2
            goto L_0x007c
        */
        throw new UnsupportedOperationException("Method not decompiled: com.btgame.seasdk.btcore.common.util.BtUtils.getIMSI(android.content.Context, java.io.File):java.lang.String");
    }

    public static String getMeTaData(Context ctx, String matchmsg) {
        if (matchmsg == null || "".equals(matchmsg)) {
            return "-1";
        }
        try {
            String channel = ctx.getPackageManager().getApplicationInfo(ctx.getPackageName(), 128).metaData.getString(matchmsg);
            String channelID = channel.substring(2, channel.length());
            BtsdkLog.m1433p("MeTaData", channel);
            if (!TextUtils.isEmpty(channelID)) {
                return channelID;
            }
        } catch (Exception e) {
            if (DebugUtils.getInstance().isLogFlag()) {
                e.printStackTrace();
            }
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
            if (!DebugUtils.getInstance().isLogFlag()) {
                return z;
            }
            e.printStackTrace();
            return z;
        }
    }

    public static boolean getbooleanMeTaDataFalse(Context ctx, String matchmsg) {
        if (matchmsg == null || "".equals(matchmsg)) {
            return true;
        }
        boolean z = true;
        try {
            return ctx.getPackageManager().getApplicationInfo(ctx.getPackageName(), 128).metaData.getBoolean(matchmsg, false);
        } catch (Exception e) {
            if (!DebugUtils.getInstance().isLogFlag()) {
                return z;
            }
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
            BtsdkLog.m1433p("MeTaData", meta);
            if (!TextUtils.isEmpty(meta)) {
                return meta;
            }
        } catch (Exception e) {
            if (DebugUtils.getInstance().isLogFlag()) {
                e.printStackTrace();
            }
        }
        return "-1";
    }

    public static int getIntNoXMeTaData(Context ctx, String matchmsg) {
        if (matchmsg == null || "".equals(matchmsg)) {
            return -1;
        }
        try {
            int meta = ctx.getPackageManager().getApplicationInfo(ctx.getPackageName(), 128).metaData.getInt(matchmsg);
            BtsdkLog.m1433p("MeTaData", String.valueOf(meta));
            if (meta != 0) {
                return meta;
            }
        } catch (Exception e) {
            if (DebugUtils.getInstance().isLogFlag()) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public static String buildUrl(String urlhost, HashMap<String, String> map) {
        StringBuilder sb = new StringBuilder(urlhost);
        for (Entry entry : map.entrySet()) {
            try {
                sb.append(URLEncoder.encode((String) entry.getKey(), "utf8")).append(HttpUtils.EQUAL_SIGN).append(URLEncoder.encode((String) entry.getValue(), "utf8")).append(HttpUtils.PARAMETERS_SEPARATOR);
            } catch (UnsupportedEncodingException e) {
                BtsdkLog.m1430e("UnsupportedEncodingException:" + e.getLocalizedMessage());
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

    public static String getAndroidId(Context context) {
        if (context != null) {
            return Secure.getString(context.getContentResolver(), "android_id");
        }
        return null;
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

    public static void toGooglePlay(Activity activity, String packageName) {
        if (TextUtils.isEmpty(packageName)) {
            packageName = activity.getPackageName();
        }
        String mAddress = "market://details?id=" + packageName;
        Intent marketIntent = new Intent("android.intent.action.VIEW");
        marketIntent.setData(Uri.parse(mAddress));
        activity.startActivity(marketIntent);
    }

    public static String getSHA1(Context context) {
        try {
            byte[] publicKey = MessageDigest.getInstance("SHA1").digest(context.getPackageManager().getPackageInfo(context.getPackageName(), 64).signatures[0].toByteArray());
            StringBuffer hexString = new StringBuffer();
            for (byte b : publicKey) {
                String appendString = Integer.toHexString(b & 255).toUpperCase(Locale.US);
                if (appendString.length() == 1) {
                    hexString.append(AppEventsConstants.EVENT_PARAM_VALUE_NO);
                }
                hexString.append(appendString);
                hexString.append(":");
            }
            String result = hexString.toString();
            return result.substring(0, result.length() - 1);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return "";
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static String getKeyHash(Context context) {
        Signature[] signatureArr;
        try {
            PackageInfo pi = context.getPackageManager().getPackageInfo(context.getPackageName(), 64);
            StringBuilder sb = new StringBuilder();
            for (Signature sign : pi.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(sign.toByteArray());
                sb.append(Base64.encodeToString(md.digest(), 0));
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
