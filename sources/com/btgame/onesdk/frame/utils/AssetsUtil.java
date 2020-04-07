package com.btgame.onesdk.frame.utils;

import android.content.Context;
import com.btgame.onesdk.frame.constants.Constants;
import com.btgame.sdk.util.BtsdkLog;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class AssetsUtil {
    private static String[] assetsXXwanfileNames;
    private static String configName;
    private static List<String> splashNames = new ArrayList();

    public static void getAllfile(Context mCtx) {
        try {
            assetsXXwanfileNames = mCtx.getAssets().list(Constants.ASSETS_FILE_NAME);
            classifyfileNames();
        } catch (Exception e) {
            e.printStackTrace();
            BtsdkLog.m1423d("There is Exception  in getAllfile (assets/btgame)");
        }
    }

    private static void classifyfileNames() {
        if (assetsXXwanfileNames.length == 0) {
            BtsdkLog.m1423d("There is no file in the assets");
            return;
        }
        for (int i = 0; i < assetsXXwanfileNames.length; i++) {
            if (assetsXXwanfileNames[i].startsWith("splash_")) {
                splashNames.add(assetsXXwanfileNames[i]);
            }
            if (assetsXXwanfileNames[i].equals("config")) {
                configName = assetsXXwanfileNames[i];
            }
        }
    }

    private static String getConfigFile(Context mCtx) {
        if (assetsXXwanfileNames == null) {
            getAllfile(mCtx);
            if (assetsXXwanfileNames == null || assetsXXwanfileNames.length == 0) {
                return null;
            }
        }
        if (configName == null || "".equals(configName)) {
            return null;
        }
        return "btgame/" + configName;
    }

    public static String getVauleInConfig(Context mCtx, String key) {
        return (String) getFromAssets(mCtx, getConfigFile(mCtx)).get(key);
    }

    public static List<String> getSplashFile(Context mCtx) {
        if (assetsXXwanfileNames == null) {
            getAllfile(mCtx);
            if (assetsXXwanfileNames == null || assetsXXwanfileNames.length == 0) {
                return null;
            }
        }
        return splashNames;
    }

    public static Properties getFromAssets(Context mctx, String fileName) {
        Properties properties = new Properties();
        if (fileName != null) {
            try {
                properties.load(mctx.getResources().getAssets().open(fileName));
                BtsdkLog.m1427p("line count", "" + properties.size());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return properties;
    }
}
