package com.baitian.datasdk.util;

import android.content.Context;

public class ContextUtil {
    private static ContextUtil instance;
    private Context mCtx;

    private ContextUtil(Context mCtx2) {
        this.mCtx = mCtx2;
    }

    public static ContextUtil getInstance() {
        return instance;
    }

    public static void init(Context mCtx2) {
        if (instance == null) {
            instance = new ContextUtil(mCtx2);
        }
    }

    public Context getmCtx() {
        return this.mCtx;
    }

    public void setmCtx(Context mCtx2) {
        this.mCtx = mCtx2;
    }

    public void destory() {
        instance = null;
        this.mCtx = null;
    }
}
