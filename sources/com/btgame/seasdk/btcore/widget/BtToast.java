package com.btgame.seasdk.btcore.widget;

import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;
import com.btgame.seasdk.btcore.common.util.BTResourceUtil;
import com.btgame.seasdk.btcore.common.util.BTScreenUtil;

public class BtToast {
    private static Toast toast;
    private static TextView toastView;

    public static void showShortTimeToast(Context context, CharSequence tips) {
        showToast(context, tips, 0);
    }

    public static void showLongTimeToast(Context context, CharSequence tips) {
        showToast(context, tips, 1);
    }

    public static void showToast(Context context, CharSequence tips, int duration) {
        if (toast == null) {
            toast = new Toast(context);
            toastView = new TextView(context);
            toastView.setBackgroundDrawable(BTResourceUtil.findDrawableByName("bg_widget"));
            toastView.setMinimumWidth(BTScreenUtil.getInstance(context).getHorizontalPX(360.0d));
            toastView.setMinimumHeight(BTScreenUtil.getInstance(context).getHorizontalPX(120.0d));
            toastView.setTextSize(0, (float) BTScreenUtil.countTextSize(context, 48.0f));
            toastView.setTextColor(-1);
            toastView.setGravity(17);
            int padding = BTScreenUtil.getInstance(context).getHorizontalPX(30.0d);
            toastView.setPadding(toastView.getPaddingLeft() + padding, toastView.getPaddingTop() + padding, toastView.getPaddingRight() + padding, toastView.getPaddingBottom() + padding);
            toast.setView(toastView);
            toast.setGravity(17, 0, 0);
        }
        toast.setDuration(duration);
        toastView.setText(tips);
        toast.show();
    }
}
