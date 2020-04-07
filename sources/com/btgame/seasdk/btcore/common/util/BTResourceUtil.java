package com.btgame.seasdk.btcore.common.util;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ServiceInfo;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources.NotFoundException;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import com.facebook.internal.AnalyticsEvents;
import com.google.android.vending.expansion.downloader.Constants;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

public class BTResourceUtil {
    private static final String SP_LOCALENAME = "SP_LOCALENAME";
    private static final String TAG = "BTResourceUtil";

    public static int getResourcesIdByName(String defType, String resourcesName) {
        int id = ContextUtil.getApplicationContext().getResources().getIdentifier(resourcesName, defType, ContextUtil.getApplicationContext().getPackageName());
        if (id == 0) {
            Log.e(TAG, "资源文件读取不到 resourcesName:" + resourcesName);
        }
        return id;
    }

    public static int findLayoutIdByName(String resourcesName) {
        return getResourcesIdByName("layout", resourcesName);
    }

    public static ColorStateList findColorStateListByName(String resourcesName) {
        return ContextUtil.getApplicationContext().getResources().getColorStateList(getResourcesIdByName("color", resourcesName));
    }

    public static int findColorByName(String resourcesName) {
        try {
            return ContextUtil.getApplicationContext().getResources().getColor(getResourcesIdByName("color", resourcesName));
        } catch (NotFoundException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static String[] findStringArrayByName(String resourcesName) {
        return ContextUtil.getApplicationContext().getResources().getStringArray(getResourcesIdByName("array", resourcesName));
    }

    public static int findArrayIdByName(String resourcesName) {
        return getResourcesIdByName("array", resourcesName);
    }

    public static int findStyleableByName(String resourcesName) {
        return getResourcesIdByName("styleable", resourcesName);
    }

    public static int findStringIdByName(String resourcesName) {
        return getResourcesIdByName("string", resourcesName);
    }

    public static String findStringByName(String resourcesName) {
        return findStringByName(ContextUtil.getApplicationContext(), resourcesName);
    }

    public static Integer findIntegerByName(String resourcesName) {
        return findIntegerByName(ContextUtil.getApplicationContext(), resourcesName);
    }

    public static boolean findBoolByName(String resourcesName) {
        return ContextUtil.getApplicationContext().getResources().getBoolean(getResourcesIdByName("bool", resourcesName));
    }

    public static String findStringByName(Context context, String resourcesName) {
        String res = "";
        try {
            return context.getResources().getString(getResourcesIdByName("string", resourcesName));
        } catch (Exception e) {
            Log.e(TAG, "resourcesName:" + resourcesName);
            return res;
        }
    }

    public static Integer findIntegerByName(Context context, String resourcesName) {
        Integer res = null;
        try {
            return Integer.valueOf(context.getResources().getInteger(getResourcesIdByName("integer", resourcesName)));
        } catch (Exception e) {
            Log.e(TAG, "resourcesName:" + resourcesName);
            return res;
        }
    }

    public static int findViewIdByName(String resourcesName) {
        return getResourcesIdByName("id", resourcesName);
    }

    public static int findDrawableIdByName(String resourcesName) {
        try {
            return getResourcesIdByName("drawable", resourcesName);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static Drawable findDrawableByName(String resourcesName) {
        try {
            return ContextUtil.getApplicationContext().getResources().getDrawable(findDrawableIdByName(resourcesName));
        } catch (NotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int findMipmapIdByName(String resourcesName) {
        return getResourcesIdByName("mipmap", resourcesName);
    }

    public static int findAnimIdByName(String resourcesName) {
        return getResourcesIdByName("anim", resourcesName);
    }

    public static int findStyleIdByName(String resourcesName) {
        return getResourcesIdByName(AnalyticsEvents.PARAMETER_LIKE_VIEW_STYLE, resourcesName);
    }

    public static float findDimenByName(String resourcesName) {
        return ContextUtil.getApplicationContext().getResources().getDimension(getResourcesIdByName("dimen", resourcesName));
    }

    public static int[] findStyleableIntArray(String name) {
        Field[] fields2;
        try {
            for (Field f : Class.forName(ContextUtil.getApplicationContext().getPackageName() + ".R$styleable").getFields()) {
                if (f.getName().equals(name)) {
                    return (int[]) f.get(null);
                }
            }
        } catch (Throwable th) {
        }
        return new int[0];
    }

    public static int findFieldValue(String name) {
        Field[] fields2;
        try {
            for (Field f : Class.forName(ContextUtil.getApplicationContext().getPackageName() + ".R$styleable").getFields()) {
                if (f.getName().equals(name)) {
                    return ((Integer) f.get(null)).intValue();
                }
            }
            return 0;
        } catch (Throwable t) {
            t.printStackTrace();
            return 0;
        }
    }

    public static String getStringFromAssets(String filaName, String format) {
        try {
            InputStream is = ContextUtil.getApplicationContext().getAssets().open(new StringBuilder(String.valueOf(filaName)).append(".").append(format).toString());
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            is.close();
            return new String(buffer).trim();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static JSONObject getApplicationMetaData(String[] metadata) {
        JSONObject dataObject = null;
        try {
            ApplicationInfo appInfo = ContextUtil.getApplicationContext().getPackageManager().getApplicationInfo(ContextUtil.getApplicationContext().getPackageName(), 128);
            if (appInfo == null) {
                return null;
            }
            JSONObject dataObject2 = new JSONObject();
            int i = 0;
            while (i < metadata.length) {
                try {
                    dataObject2.put(metadata[i], appInfo.metaData.getString(metadata[i]));
                    i++;
                } catch (NameNotFoundException e) {
                    e = e;
                    dataObject = dataObject2;
                    e.printStackTrace();
                    return dataObject;
                } catch (JSONException e2) {
                    e = e2;
                    dataObject = dataObject2;
                    e.printStackTrace();
                    return dataObject;
                }
            }
            return dataObject2;
        } catch (NameNotFoundException e3) {
            e = e3;
            e.printStackTrace();
            return dataObject;
        } catch (JSONException e4) {
            e = e4;
            e.printStackTrace();
            return dataObject;
        }
    }

    public static String getApplicationMetaData(String metadata) {
        String data;
        ApplicationInfo appInfo = null;
        try {
            appInfo = ContextUtil.getApplicationContext().getPackageManager().getApplicationInfo(ContextUtil.getApplicationContext().getPackageName(), 128);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        if (appInfo == null) {
            return null;
        }
        try {
            data = appInfo.metaData.getString(metadata);
        } catch (Exception e2) {
            data = appInfo.metaData.getInt(metadata) + "";
        }
        if (data == null) {
            return appInfo.metaData.getInt(metadata) + "";
        }
        return data;
    }

    public static int getApplicationMetaIntData(String metadata) {
        ApplicationInfo appInfo = null;
        try {
            appInfo = ContextUtil.getApplicationContext().getPackageManager().getApplicationInfo(ContextUtil.getApplicationContext().getPackageName(), 128);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        if (appInfo != null) {
            return appInfo.metaData.getInt(metadata);
        }
        return 0;
    }

    public static boolean getApplicationMetaBooleanData(String metadata) {
        ApplicationInfo appInfo = null;
        try {
            appInfo = ContextUtil.getApplicationContext().getPackageManager().getApplicationInfo(ContextUtil.getApplicationContext().getPackageName(), 128);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        if (appInfo != null) {
            return appInfo.metaData.getBoolean(metadata);
        }
        return true;
    }

    public static String getActivityMetaData(Activity activity, String metadata) {
        ActivityInfo info = null;
        try {
            info = ContextUtil.getApplicationContext().getPackageManager().getActivityInfo(activity.getComponentName(), 128);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        if (info != null) {
            return info.metaData.getString(metadata);
        }
        return null;
    }

    public static String getActivityMetaData(Activity activity, String metadata, Class clazz) {
        ActivityInfo info = null;
        try {
            info = ContextUtil.getApplicationContext().getPackageManager().getActivityInfo(new ComponentName(activity, clazz), 128);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        if (info != null) {
            return info.metaData.getString(metadata);
        }
        return null;
    }

    public static JSONObject getActivityMetaData(Activity activity, String[] metadata) {
        JSONObject dataObject = null;
        try {
            ActivityInfo info = ContextUtil.getApplicationContext().getPackageManager().getActivityInfo(activity.getComponentName(), 128);
            if (info == null) {
                return null;
            }
            JSONObject dataObject2 = new JSONObject();
            int i = 0;
            while (i < metadata.length) {
                try {
                    dataObject2.put(metadata[i], info.metaData.getString(metadata[i]));
                    i++;
                } catch (NameNotFoundException e) {
                    e = e;
                    dataObject = dataObject2;
                    e.printStackTrace();
                    return dataObject;
                } catch (JSONException e2) {
                    e = e2;
                    dataObject = dataObject2;
                    e.printStackTrace();
                    return dataObject;
                }
            }
            return dataObject2;
        } catch (NameNotFoundException e3) {
            e = e3;
            e.printStackTrace();
            return dataObject;
        } catch (JSONException e4) {
            e = e4;
            e.printStackTrace();
            return dataObject;
        }
    }

    public static String getServiceMetaData(String metadata, Class serviceClass) {
        ServiceInfo info = null;
        try {
            info = ContextUtil.getApplicationContext().getPackageManager().getServiceInfo(new ComponentName(ContextUtil.getApplicationContext(), serviceClass), 128);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        if (info != null) {
            return info.metaData.getString(metadata);
        }
        return null;
    }

    public static String getReceiverMetaData(String metadata, Class serviceClass) {
        ActivityInfo info = null;
        try {
            info = ContextUtil.getApplicationContext().getPackageManager().getReceiverInfo(new ComponentName(ContextUtil.getApplicationContext(), serviceClass), 128);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        if (info != null) {
            return info.metaData.getString(metadata);
        }
        return null;
    }

    public static String getLocalAppName() {
        PackageManager pm = ContextUtil.getApplicationContext().getPackageManager();
        try {
            return pm.getApplicationInfo(ContextUtil.getApplicationContext().getPackageName(), 128).loadLabel(pm).toString();
        } catch (Exception e) {
            BtsdkLog.m1429d(e.getLocalizedMessage());
            return "";
        }
    }

    public static String getAppName() {
        try {
            return ContextUtil.getApplicationContext().getResources().getString(ContextUtil.getApplicationContext().getPackageManager().getApplicationInfo(ContextUtil.getApplicationContext().getPackageName(), 128).labelRes);
        } catch (Exception e) {
            BtsdkLog.m1429d(e.getLocalizedMessage());
            return "";
        }
    }

    public static int parseColor(String colorStr) {
        colorStr.replace("0x", "#");
        colorStr.replace("0X", "#");
        Integer color = Integer.valueOf(0);
        try {
            color = Integer.valueOf(Color.parseColor(colorStr));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return color.intValue();
    }

    public static Context changeLocale(Context context, String defaultLocale) {
        Locale locale;
        if (TextUtils.isEmpty(defaultLocale)) {
            String preLocale = getSaveLocale(context);
            if (TextUtils.isEmpty(preLocale)) {
                defaultLocale = findStringByName(context, "default_locale");
            } else {
                defaultLocale = preLocale;
            }
        }
        if (!TextUtils.isEmpty(defaultLocale)) {
            if (defaultLocale.contains("_")) {
                String[] strs = defaultLocale.split("_");
                locale = new Locale(strs[0], strs[1]);
            } else {
                locale = new Locale(defaultLocale);
            }
            Configuration config = context.getResources().getConfiguration();
            DisplayMetrics metrics = context.getResources().getDisplayMetrics();
            if (VERSION.SDK_INT >= 17) {
                config.setLocale(locale);
                context.getResources().updateConfiguration(config, metrics);
                context = context.createConfigurationContext(config);
            } else {
                config.locale = locale;
                context.getResources().updateConfiguration(config, metrics);
            }
            if (ContextUtil.getApplicationContext() != null) {
                ContextUtil.getApplicationContext().getResources().updateConfiguration(config, metrics);
            }
            saveLocale(context, defaultLocale);
        }
        return context;
    }

    public static String getSaveLocale(Context context) {
        return SharedPreferencesUtils.getString(context, SP_LOCALENAME);
    }

    public static String getAppLocale(Context context) {
        String lang = getSaveLocale(context);
        if (!TextUtils.isEmpty(lang)) {
            return lang;
        }
        Locale locale = Locale.getDefault();
        if (TextUtils.isEmpty(locale.getCountry())) {
            return locale.getLanguage();
        }
        return locale.getLanguage() + Constants.FILENAME_SEQUENCE_SEPARATOR + locale.getCountry();
    }

    private static void saveLocale(Context context, String locale) {
        SharedPreferencesUtils.setString(context, SP_LOCALENAME, locale);
    }
}
