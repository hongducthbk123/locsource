package com.btgame.seasdk.btcore.common.util;

import android.content.Context;

public class SharedPreferencesUtils {
    private static final String SP_FILE_NAME = "GAME_SP";

    public static boolean getBoolean(String keyName) {
        return ContextUtil.getApplicationContext().getSharedPreferences(SP_FILE_NAME, 0).getBoolean(keyName, false);
    }

    public static String getString(String keyName) {
        return getString(ContextUtil.getApplicationContext(), keyName);
    }

    public static String getString(Context context, String keyName) {
        return context.getSharedPreferences(SP_FILE_NAME, 0).getString(keyName, "");
    }

    public static int getInt(String keyName) {
        return ContextUtil.getApplicationContext().getSharedPreferences(SP_FILE_NAME, 0).getInt(keyName, 0);
    }

    public static void setBoolean(String keyName, boolean flag) {
        ContextUtil.getApplicationContext().getSharedPreferences(SP_FILE_NAME, 0).edit().putBoolean(keyName, flag).commit();
    }

    public static void setInt(String keyName, int value) {
        ContextUtil.getApplicationContext().getSharedPreferences(SP_FILE_NAME, 0).edit().putInt(keyName, value).commit();
    }

    public static void setString(String keyName, String value) {
        setString(ContextUtil.getApplicationContext(), keyName, value);
    }

    public static void setString(Context context, String keyName, String value) {
        context.getSharedPreferences(SP_FILE_NAME, 0).edit().putString(keyName, value).commit();
    }

    public static void setLong(String keyName, long value) {
        ContextUtil.getApplicationContext().getSharedPreferences(SP_FILE_NAME, 0).edit().putLong(keyName, value).commit();
    }

    public static long getLong(String keyName) {
        return ContextUtil.getApplicationContext().getSharedPreferences(SP_FILE_NAME, 0).getLong(keyName, 0);
    }
}
