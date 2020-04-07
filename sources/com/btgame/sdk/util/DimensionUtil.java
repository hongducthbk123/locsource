package com.btgame.sdk.util;

import android.content.Context;
import java.text.DecimalFormat;

public class DimensionUtil {
    public static int dip2px(Context ctx, int dpValue) {
        return (int) ((((float) dpValue) * ctx.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int px2dip(Context ctx, int pxValue) {
        return (int) ((((float) pxValue) / ctx.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int compatibleToWidth(Context context, int width) {
        int widthPixels = context.getResources().getDisplayMetrics().widthPixels;
        int heightPixels = context.getResources().getDisplayMetrics().heightPixels;
        if (widthPixels > heightPixels) {
            return (heightPixels * width) / 480;
        }
        return (widthPixels * width) / 480;
    }

    public static int compatibleToHeight(Context context, int height) {
        int widthPixels = context.getResources().getDisplayMetrics().widthPixels;
        int heightPixels = context.getResources().getDisplayMetrics().heightPixels;
        if (widthPixels > heightPixels) {
            return (widthPixels * height) / 800;
        }
        return (heightPixels * height) / 800;
    }

    public static String convertSize2String(int size) {
        int M = 1024 * 1024;
        int G = M * 1024;
        DecimalFormat fmt = new DecimalFormat("#.##");
        if (size / 1024 < 1) {
            return size + "K";
        }
        if (size / M < 1) {
            return fmt.format((((double) size) * 1.0d) / ((double) 1024)) + "M";
        }
        if (size / G < 1) {
            return fmt.format((((double) size) * 1.0d) / ((double) M)) + "G";
        }
        return fmt.format(((((double) size) * 1.0d) * ((double) 1024)) / ((double) G)) + "G";
    }
}
