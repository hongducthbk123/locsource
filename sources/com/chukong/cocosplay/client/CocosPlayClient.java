package com.chukong.cocosplay.client;

import android.app.Activity;

public class CocosPlayClient {
    public static native String[] getSearchPaths();

    public static boolean init(Activity activity, boolean isDemo) {
        return false;
    }

    public static boolean isEnabled() {
        return false;
    }

    public static boolean isDemo() {
        return false;
    }

    public static boolean isNotifyFileLoadedEnabled() {
        return false;
    }

    public static void notifyFileLoaded(String filePath) {
    }

    public static void updateAssets(String filePath) {
    }

    public static String getGameRoot() {
        return "";
    }
}
