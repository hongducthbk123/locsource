package com.btgame.seasdk.btcore.common.util;

import android.app.Activity;
import android.content.Context;
import java.lang.ref.WeakReference;

public class ContextUtil {
    private static ContextUtil instance;
    protected WeakReference<Activity> mActivityWeak;
    private Context mApplicationContext;

    private ContextUtil(Context mCtx) {
        this.mApplicationContext = mCtx;
    }

    public static void init(Context context) {
        if (instance == null) {
            if (context.getApplicationContext() != null) {
                context = context.getApplicationContext();
            }
            instance = new ContextUtil(context);
        }
    }

    public static void setCurrentActivity(Activity activity) {
        if (instance != null) {
            instance.mActivityWeak = new WeakReference<>(activity);
            return;
        }
        instance = new ContextUtil(activity.getApplication());
        instance.mActivityWeak = new WeakReference<>(activity);
    }

    public static Activity getCurrentActivity() {
        if (instance == null || instance.mActivityWeak == null) {
            return null;
        }
        return (Activity) instance.mActivityWeak.get();
    }

    public static void upCurrentActivity(Activity activity) {
        if (!activity.equals(getCurrentActivity())) {
            setCurrentActivity(activity);
        }
    }

    public static Context getApplicationContext() {
        if (instance == null) {
            return null;
        }
        return instance.mApplicationContext;
    }

    public static void removeActivity() {
        if (instance != null) {
            instance.mActivityWeak = null;
        }
    }

    public static void destroy(Activity activity) {
        if (instance != null && getCurrentActivity() == activity) {
            instance.mApplicationContext = null;
            removeActivity();
            instance = null;
        }
    }
}
