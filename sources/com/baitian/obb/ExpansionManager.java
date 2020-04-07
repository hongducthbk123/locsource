package com.baitian.obb;

import android.content.Context;
import android.content.Intent;
import com.android.vending.expansion.zipfile.APKExpansionSupport;
import com.android.vending.expansion.zipfile.ZipResourceFile.ZipEntryRO;
import com.baitian.unzip.ZipHelper;
import com.btgame.onesdk.obb.OBBDownloaderActivity;
import com.btgame.sdk.util.BtUtils;
import com.btgame.sdk.util.BtsdkLog;
import com.google.android.vending.expansion.downloader.Helpers;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import p004cn.jiguang.net.HttpUtils;

public class ExpansionManager {
    private static final String VERSION_FILE = "version.txt";
    private static boolean isLatestVersion = false;
    private static Context mContext = null;

    public static void init(Context context) {
        mContext = context;
    }

    public static boolean isObbSwitch() {
        boolean isSwitch = BtUtils.getbooleanMeTaData(mContext, "btObbSwitch");
        if (!isSwitch) {
            isLatestVersion = true;
        }
        BtsdkLog.m1423d("isObbSwitch: " + String.valueOf(isSwitch));
        return isSwitch;
    }

    public static boolean checkObbState() {
        boolean z = true;
        try {
            if (!isObbSwitch()) {
                BtsdkLog.m1423d("checkObbState isObbSwitch false");
                return true;
            }
            Context context = mContext;
            int packageObbVersion = BtUtils.getIntNoXMeTaData(context, "btObbVersion");
            int existObbVersion = 0;
            File obbVersionFile = new File((context.getFilesDir().getAbsolutePath() + "/obb") + HttpUtils.PATHS_SEPARATOR + VERSION_FILE);
            if (obbVersionFile.isFile() && obbVersionFile.exists()) {
                InputStreamReader streamReader = new InputStreamReader(new FileInputStream(obbVersionFile), "UTF-8");
                existObbVersion = Integer.valueOf(new BufferedReader(streamReader).readLine()).intValue();
                streamReader.close();
            }
            BtsdkLog.m1423d("packageObbVersion: " + packageObbVersion);
            BtsdkLog.m1423d("existObbVersion: " + existObbVersion);
            if (packageObbVersion <= 0 || existObbVersion < packageObbVersion) {
                z = false;
            }
            isLatestVersion = z;
            BtsdkLog.m1423d(isLatestVersion ? "is latest version" : "not latest obb version");
            return isLatestVersion;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isObbExtracted() {
        if (!isObbSwitch()) {
            BtsdkLog.m1423d("isObbExtracted isObbSwitch false");
            return true;
        }
        BtsdkLog.m1423d("isObbExtracted: " + String.valueOf(isLatestVersion));
        return isLatestVersion;
    }

    public static boolean isObbDelivered() {
        if (!isObbSwitch()) {
            BtsdkLog.m1423d("isObbDelivered isObbSwitch false");
            return true;
        }
        Context context = mContext;
        boolean isDelivered = false;
        int versionCode = BtUtils.getIntNoXMeTaData(context, "btObbVersion");
        if (versionCode <= 0) {
            BtsdkLog.m1424e("isObbDelivered Invalid AndroidManifest ObbVersion");
            return false;
        }
        if (Helpers.doesFileExist(context, Helpers.getExpansionAPKFileName(context, true, versionCode), Long.valueOf(BtUtils.getMeTaData(context, "btObbSize")).longValue(), false)) {
            isDelivered = true;
        }
        BtsdkLog.m1423d("isObbDelivered: " + String.valueOf(isDelivered));
        return isDelivered;
    }

    public static String unzipApkExFile() {
        String result;
        String result2 = "unzip";
        try {
            if (!isObbSwitch()) {
                BtsdkLog.m1423d("unzipApkExFile isObbSwitch false");
                if (result2 == null || "".equals(result2)) {
                    result2 = "unzip fail";
                }
                BtsdkLog.m1423d("unzip result: " + result);
            } else {
                Context context = mContext;
                int versionCode = BtUtils.getIntNoXMeTaData(context, "btObbVersion");
                BtsdkLog.m1423d("unzip versionCode: " + versionCode);
                if (versionCode <= 0) {
                    BtsdkLog.m1424e("unzipApkExFile Invalid AndroidManifest ObbVersion");
                    if (result2 == null || "".equals(result2)) {
                        result2 = "unzip fail";
                    }
                    BtsdkLog.m1423d("unzip result: " + result);
                } else if (!isObbDelivered()) {
                    BtsdkLog.m1424e("unzipApkExFile Obb NOT Delivered");
                    if (result2 == null || "".equals(result2)) {
                        result2 = "unzip fail";
                    }
                    BtsdkLog.m1423d("unzip result: " + result);
                } else {
                    ZipEntryRO[] zip = APKExpansionSupport.getAPKExpansionZipFile(context, versionCode, 0).getAllEntries();
                    BtsdkLog.m1423d("unzip file: " + zip[0].mZipFileName);
                    File file = new File(context.getFilesDir().getAbsolutePath() + "/obb");
                    BtsdkLog.m1423d("unzip output: " + file.getAbsolutePath());
                    ZipHelper.unzip(zip[0].mZipFileName, file);
                    if (file.exists()) {
                        BtsdkLog.m1423d("unzip finish: " + file.getAbsolutePath());
                    }
                    if (ZipHelper.isZipError()) {
                        result = ZipHelper.getErrorInfo();
                    } else {
                        result = recordObbVersion();
                    }
                    if (result == null || "".equals(result)) {
                        result = "unzip fail";
                    }
                    BtsdkLog.m1423d("unzip result: " + result);
                }
            }
        } catch (Exception e) {
            result2 = e.getMessage();
            e.printStackTrace();
            if (result2 == null || "".equals(result2)) {
                result2 = "unzip fail";
            }
            BtsdkLog.m1423d("unzip result: " + result);
        } catch (Throwable th) {
            if (result2 == null || "".equals(result2)) {
                result2 = "unzip fail";
            }
            BtsdkLog.m1423d("unzip result: " + result);
        }
        return result;
    }

    public static void startDownloader() {
        Context context = mContext;
        context.startActivity(new Intent(context, OBBDownloaderActivity.class));
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x00c7 A[SYNTHETIC, Splitter:B:21:0x00c7] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00ee A[SYNTHETIC, Splitter:B:28:0x00ee] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String recordObbVersion() {
        /*
            java.lang.String r6 = "record"
            android.content.Context r1 = mContext
            java.lang.String r10 = "btObbVersion"
            int r9 = com.btgame.sdk.util.BtUtils.getIntNoXMeTaData(r1, r10)
            if (r9 > 0) goto L_0x0025
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r11 = "recordObbVersion invalid: "
            java.lang.StringBuilder r10 = r10.append(r11)
            java.lang.StringBuilder r10 = r10.append(r9)
            java.lang.String r10 = r10.toString()
            com.btgame.sdk.util.BtsdkLog.m1423d(r10)
            java.lang.String r6 = "record version invalid"
        L_0x0024:
            return r6
        L_0x0025:
            java.lang.String r3 = java.lang.String.valueOf(r9)
            r7 = 0
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x00bd }
            r10.<init>()     // Catch:{ IOException -> 0x00bd }
            java.io.File r11 = r1.getFilesDir()     // Catch:{ IOException -> 0x00bd }
            java.lang.String r11 = r11.getAbsolutePath()     // Catch:{ IOException -> 0x00bd }
            java.lang.StringBuilder r10 = r10.append(r11)     // Catch:{ IOException -> 0x00bd }
            java.lang.String r11 = "/obb"
            java.lang.StringBuilder r10 = r10.append(r11)     // Catch:{ IOException -> 0x00bd }
            java.lang.String r5 = r10.toString()     // Catch:{ IOException -> 0x00bd }
            java.io.File r4 = new java.io.File     // Catch:{ IOException -> 0x00bd }
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x00bd }
            r10.<init>()     // Catch:{ IOException -> 0x00bd }
            java.lang.StringBuilder r10 = r10.append(r5)     // Catch:{ IOException -> 0x00bd }
            java.lang.String r11 = "/"
            java.lang.StringBuilder r10 = r10.append(r11)     // Catch:{ IOException -> 0x00bd }
            java.lang.String r11 = "version.txt"
            java.lang.StringBuilder r10 = r10.append(r11)     // Catch:{ IOException -> 0x00bd }
            java.lang.String r10 = r10.toString()     // Catch:{ IOException -> 0x00bd }
            r4.<init>(r10)     // Catch:{ IOException -> 0x00bd }
            java.io.FileOutputStream r8 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x00bd }
            r8.<init>(r4)     // Catch:{ IOException -> 0x00bd }
            boolean r10 = r4.exists()     // Catch:{ IOException -> 0x0115, all -> 0x0112 }
            if (r10 != 0) goto L_0x0072
            r4.createNewFile()     // Catch:{ IOException -> 0x0115, all -> 0x0112 }
        L_0x0072:
            byte[] r0 = r3.getBytes()     // Catch:{ IOException -> 0x0115, all -> 0x0112 }
            r8.write(r0)     // Catch:{ IOException -> 0x0115, all -> 0x0112 }
            r8.flush()     // Catch:{ IOException -> 0x0115, all -> 0x0112 }
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0115, all -> 0x0112 }
            r10.<init>()     // Catch:{ IOException -> 0x0115, all -> 0x0112 }
            java.lang.String r11 = "recordObbVersion success: "
            java.lang.StringBuilder r10 = r10.append(r11)     // Catch:{ IOException -> 0x0115, all -> 0x0112 }
            java.lang.StringBuilder r10 = r10.append(r3)     // Catch:{ IOException -> 0x0115, all -> 0x0112 }
            java.lang.String r10 = r10.toString()     // Catch:{ IOException -> 0x0115, all -> 0x0112 }
            com.btgame.sdk.util.BtsdkLog.m1423d(r10)     // Catch:{ IOException -> 0x0115, all -> 0x0112 }
            r10 = 1
            isLatestVersion = r10     // Catch:{ IOException -> 0x0115, all -> 0x0112 }
            java.lang.String r6 = "success"
            if (r8 == 0) goto L_0x009c
            r8.close()     // Catch:{ IOException -> 0x00b4 }
        L_0x009c:
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r11 = "record result: "
            java.lang.StringBuilder r10 = r10.append(r11)
            java.lang.StringBuilder r10 = r10.append(r6)
            java.lang.String r10 = r10.toString()
            com.btgame.sdk.util.BtsdkLog.m1423d(r10)
            goto L_0x0024
        L_0x00b4:
            r2 = move-exception
            java.lang.String r6 = r2.getMessage()
            r2.printStackTrace()
            goto L_0x009c
        L_0x00bd:
            r2 = move-exception
        L_0x00be:
            java.lang.String r6 = r2.getMessage()     // Catch:{ all -> 0x00eb }
            r2.printStackTrace()     // Catch:{ all -> 0x00eb }
            if (r7 == 0) goto L_0x00ca
            r7.close()     // Catch:{ IOException -> 0x00e2 }
        L_0x00ca:
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r11 = "record result: "
            java.lang.StringBuilder r10 = r10.append(r11)
            java.lang.StringBuilder r10 = r10.append(r6)
            java.lang.String r10 = r10.toString()
            com.btgame.sdk.util.BtsdkLog.m1423d(r10)
            goto L_0x0024
        L_0x00e2:
            r2 = move-exception
            java.lang.String r6 = r2.getMessage()
            r2.printStackTrace()
            goto L_0x00ca
        L_0x00eb:
            r10 = move-exception
        L_0x00ec:
            if (r7 == 0) goto L_0x00f1
            r7.close()     // Catch:{ IOException -> 0x0109 }
        L_0x00f1:
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r11 = "record result: "
            java.lang.StringBuilder r10 = r10.append(r11)
            java.lang.StringBuilder r10 = r10.append(r6)
            java.lang.String r10 = r10.toString()
            com.btgame.sdk.util.BtsdkLog.m1423d(r10)
            goto L_0x0024
        L_0x0109:
            r2 = move-exception
            java.lang.String r6 = r2.getMessage()
            r2.printStackTrace()
            goto L_0x00f1
        L_0x0112:
            r10 = move-exception
            r7 = r8
            goto L_0x00ec
        L_0x0115:
            r2 = move-exception
            r7 = r8
            goto L_0x00be
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baitian.obb.ExpansionManager.recordObbVersion():java.lang.String");
    }
}
