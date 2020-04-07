package com.baitian.util;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import p004cn.jiguang.net.HttpUtils;

public class FileUtil {
    public static final String LOG_FILE = "Debug";
    public static final String LOG_FILE_NAME = "debug.txt";
    public static final String SDK_DIR_NAME = "Btsdkgame";
    private static boolean isPermissionComplete;
    private static Context mCtx;
    private static File mGameDir;
    private static File mLogDir;
    private static File mRootDir;

    public static void init(Context context) {
        mCtx = context;
        initDirector();
    }

    private static void initDirector() {
        if (!isSDKIn()) {
            Log.d("BtSensors", "SD card is not in This phone");
            return;
        }
        try {
            mRootDir = new File(Environment.getExternalStorageDirectory(), "Btsdkgame");
            if ((!mRootDir.exists() || mRootDir.isFile()) && !mRootDir.mkdir()) {
                Log.d("BtSensors", "创建btsdk目录失败");
                return;
            }
            try {
                mGameDir = new File(mRootDir, mCtx.getPackageName());
                if ((!mGameDir.exists() || mGameDir.isFile()) && !mGameDir.mkdir()) {
                    Log.d("BtSensors", "创建mGameDir目录失败");
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.d("BtSensors", "创建mGameDir目录异常");
            }
            mLogDir = new File(mGameDir, "Debug");
            if ((!mLogDir.exists() || mLogDir.isFile()) && !mLogDir.mkdir()) {
                Log.d("BtSensors", "创建Log目录失败");
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static boolean isSDKIn() {
        return Environment.getExternalStorageState().equals("mounted");
    }

    public static String getLogFile() throws IOException {
        File logfile = new File(mLogDir, "debug.txt");
        if (logfile.isFile() && logfile.exists()) {
            return logfile.getAbsolutePath();
        }
        if (!logfile.createNewFile()) {
            Log.d("BtSensors", "创建Log.txt失败");
            return null;
        }
        Log.d("BtSensors", "创建Log.txt成功");
        return logfile.getAbsolutePath();
    }

    public static HashMap<String, String> readFile(String filepath) {
        byte[] bArr = new byte[1024];
        String str = "";
        HashMap<String, String> debugmap = new HashMap<>();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filepath)));
            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    return debugmap;
                }
                Log.d("BtSensors", line);
                String[] strings = line.split(HttpUtils.EQUAL_SIGN);
                debugmap.put(strings[0], strings[1]);
            }
        } catch (Exception e) {
            return null;
        }
    }

    public static File makedirInGamedir(String dirName) {
        File custmerFile = new File(mGameDir, dirName);
        if ((custmerFile.exists() && !custmerFile.isFile()) || custmerFile.mkdir()) {
            return custmerFile;
        }
        Log.d("BtSensors", "创建" + dirName + "目录失败");
        return null;
    }

    public static File makeFileInGamedir(String fileName) {
        File custmerFile = new File(mGameDir, fileName);
        try {
            if ((custmerFile.exists() && !custmerFile.isFile()) || custmerFile.createNewFile()) {
                return custmerFile;
            }
            Log.d("BtSensors", "创建" + fileName + "文件失败");
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return custmerFile;
        }
    }

    public static File getGameDir() {
        try {
            mGameDir = new File(mRootDir, mCtx.getPackageName());
            if ((!mGameDir.exists() || mGameDir.isFile()) && !mGameDir.mkdir()) {
                Log.d("BtSensors", "创建mGameDir目录失败");
                return mGameDir;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("BtSensors", "创建mGameDir目录异常");
        }
        return mGameDir;
    }

    public static void destory() {
        mCtx = null;
    }
}
