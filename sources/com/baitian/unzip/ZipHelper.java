package com.baitian.unzip;

import com.btgame.sdk.util.BtsdkLog;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import org.apache.commons.p052io.IOUtils;

public class ZipHelper {
    private static String errorInfo = "";
    private static boolean zipError = false;

    public static boolean isZipError() {
        return zipError;
    }

    public static String getErrorInfo() {
        return errorInfo;
    }

    private static void setZipError(boolean zipError2) {
        zipError = zipError2;
    }

    private static void setErrorInfo(String errorInfo2) {
        errorInfo = errorInfo2;
    }

    public static void unzip(String archive, File outputDir) {
        try {
            BtsdkLog.m1423d("ZipHelper.unzip() - File: " + archive);
            ZipFile zipfile = new ZipFile(archive);
            Enumeration<? extends ZipEntry> e = zipfile.entries();
            while (e.hasMoreElements()) {
                unzipEntry(zipfile, (ZipEntry) e.nextElement(), outputDir);
            }
        } catch (Exception e2) {
            BtsdkLog.m1423d("ZipHelper.unzip() - Error extracting file " + archive + ": " + e2.getMessage());
            setZipError(true);
            setErrorInfo(e2.toString());
        }
    }

    private static void unzipEntry(ZipFile zipfile, ZipEntry entry, File outputDir) throws IOException {
        if (entry.isDirectory()) {
            createDirectory(new File(outputDir, entry.getName()));
            return;
        }
        File outputFile = new File(outputDir, entry.getName());
        if (!outputFile.getParentFile().exists()) {
            createDirectory(outputFile.getParentFile());
        }
        BtsdkLog.m1423d("ZipHelper.unzipEntry() - Extracting: " + entry.getName());
        BufferedInputStream inputStream = new BufferedInputStream(zipfile.getInputStream(entry));
        BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(outputFile));
        try {
            IOUtils.copy((InputStream) inputStream, (OutputStream) outputStream);
        } catch (Exception e) {
            BtsdkLog.m1423d("ZipHelper.unzipEntry() - Error: " + e.getMessage());
            setZipError(true);
            setErrorInfo(e.toString());
        } finally {
            outputStream.close();
            inputStream.close();
        }
    }

    private static void createDirectory(File dir) {
        BtsdkLog.m1423d("ZipHelper.createDir() - Creating directory: " + dir.getName());
        if (dir.exists()) {
            BtsdkLog.m1423d("ZipHelper.createDir() - Exists directory: " + dir.getName());
        } else if (!dir.mkdirs()) {
            throw new RuntimeException("Can't create directory " + dir);
        }
    }
}
