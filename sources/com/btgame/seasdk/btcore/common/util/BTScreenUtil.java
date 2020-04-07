package com.btgame.seasdk.btcore.common.util;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;

public class BTScreenUtil {
    private static volatile boolean hasInited;
    public Resources res;
    private int screenHeight;
    private int screenWidth;

    private static class ScreenUtilHolder {
        /* access modifiers changed from: private */
        public static final BTScreenUtil screenUtil = new BTScreenUtil();

        private ScreenUtilHolder() {
        }
    }

    private BTScreenUtil() {
    }

    public static BTScreenUtil getInstance(Context context) {
        BTScreenUtil screenUtil = ScreenUtilHolder.screenUtil;
        if (context != null && !hasInited) {
            screenUtil.init(context);
        }
        return ScreenUtilHolder.screenUtil;
    }

    public static int countTextSize(Context context, float pxValue) {
        return getInstance(context).getVerticalPX((double) pxValue);
    }

    private void init(Context context) {
        hasInited = true;
        this.res = context.getResources();
        try {
            DisplayMetrics displaymetrics = context.getApplicationContext().getResources().getDisplayMetrics();
            int width = displaymetrics.widthPixels;
            int height = displaymetrics.heightPixels;
            if (width > height) {
                this.screenWidth = width;
                this.screenHeight = height;
                return;
            }
            this.screenWidth = height;
            this.screenHeight = width;
        } catch (Exception e) {
            e.printStackTrace();
            this.screenWidth = 1920;
            this.screenHeight = 1080;
        }
    }

    public float getSizePX(int unit, float value) {
        return TypedValue.applyDimension(unit, value, this.res.getDisplayMetrics());
    }

    public int convertDPToPX(float valueDP) {
        return (int) TypedValue.applyDimension(1, valueDP, this.res.getDisplayMetrics());
    }

    public int getHorizontalPX(double valuePX) {
        return Double.valueOf(((double) getScreenHeight()) * (valuePX / 1080.0d)).intValue();
    }

    public int getVerticalPX(double valuePX) {
        return Double.valueOf(((double) getScreenHeight()) * (valuePX / 1080.0d)).intValue();
    }

    public int convertPXToDPToScalePX(int valuePX) {
        return (int) TypedValue.applyDimension(1, ((float) valuePX) / 1.6f, this.res.getDisplayMetrics());
    }

    public int convertPXToSPToScalePX(float valuePX) {
        return (int) TypedValue.applyDimension(2, valuePX / 1.4375f, this.res.getDisplayMetrics());
    }

    public float dpToPx(float dp) {
        return this.res.getDisplayMetrics().density * dp;
    }

    public float pxToDp(float px) {
        return px / this.res.getDisplayMetrics().density;
    }

    public int getScreenWidth() {
        return this.screenWidth;
    }

    public int getScreenHeight() {
        return this.screenHeight;
    }

    public int dip2px(float dpValue) {
        return (int) ((dpValue * this.res.getDisplayMetrics().density) + 0.5f);
    }

    public int px2dip(float pxValue) {
        return (int) ((pxValue / this.res.getDisplayMetrics().density) + 0.5f);
    }

    public void hideNavigationBar(Window window) {
        View decorView = window.getDecorView();
        int uiOptions = -1;
        if (VERSION.SDK_INT >= 19) {
            uiOptions = 5894;
        }
        if (VERSION.SDK_INT >= 21) {
            window.setNavigationBarColor(0);
            window.setStatusBarColor(0);
        }
        if (uiOptions > -1) {
            decorView.setSystemUiVisibility(uiOptions);
        }
    }
}
