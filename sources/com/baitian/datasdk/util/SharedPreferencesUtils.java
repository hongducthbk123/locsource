package com.baitian.datasdk.util;

import android.content.Context;
import android.text.TextUtils;

public class SharedPreferencesUtils {
    public static boolean getBoolean(Context context, String fileName, String keyName) {
        return context.getSharedPreferences(fileName, 0).getBoolean(keyName, false);
    }

    public static String getString(Context context, String fileName, String keyName) {
        return context.getSharedPreferences(fileName, 0).getString(keyName, "");
    }

    public static int getInt(Context context, String fileName, String keyName) {
        return context.getSharedPreferences(fileName, 0).getInt(keyName, 0);
    }

    public static void setBoolean(Context context, String fileName, String keyName, boolean flag) {
        context.getSharedPreferences(fileName, 0).edit().putBoolean(keyName, flag).commit();
    }

    public static void setInt(Context context, String fileName, String keyName, int value) {
        context.getSharedPreferences(fileName, 0).edit().putInt(keyName, value).commit();
    }

    public static void setString(Context context, String fileName, String keyName, String value) {
        if (!TextUtils.isEmpty(value)) {
            context.getSharedPreferences(fileName, 0).edit().putString(keyName, value).commit();
        }
    }

    public static void setLong(Context context, String fileName, String keyName, long value) {
        context.getSharedPreferences(fileName, 0).edit().putLong(keyName, value).commit();
    }

    public static long getLong(Context context, String fileName, String keyName) {
        return context.getSharedPreferences(fileName, 0).getLong(keyName, 0);
    }
}
