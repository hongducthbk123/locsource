package com.btgame.seasdk.btcore.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface.OnCancelListener;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.p000v4.app.DialogFragment;
import android.support.p000v4.app.FragmentActivity;
import android.support.p000v4.app.NotificationCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.btgame.seasdk.btcore.common.util.BTResourceUtil;
import com.btgame.seasdk.btcore.common.util.BTScreenUtil;

public class LoadingDialog extends DialogFragment {
    private static LoadingDialog loadingDialog;
    private AnimationDrawable animation;
    private Dialog mDialog;
    private TextView message_tv;
    private String msg;
    private OnCancelListener onCancelListener;
    private ImageView progressBar;

    public void setOnCancelListener(OnCancelListener onCancelListener2) {
        this.onCancelListener = onCancelListener2;
    }

    public static void showDialog(FragmentActivity activity, String msg2, OnCancelListener onCancelListener2) {
        try {
            hiddenDialog();
            if (!activity.isFinishing()) {
                loadingDialog = new LoadingDialog();
                loadingDialog.setOnCancelListener(onCancelListener2);
                Bundle bundle = new Bundle();
                bundle.putSerializable(NotificationCompat.CATEGORY_MESSAGE, msg2);
                loadingDialog.setArguments(bundle);
                loadingDialog.show(activity.getSupportFragmentManager().beginTransaction(), (String) null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            loadingDialog = null;
        }
    }

    public static void showDialog(Activity activity, String msg2, OnCancelListener onCancelListener2) {
        try {
            hiddenDialog();
            if (activity.isFinishing()) {
                return;
            }
            if (activity instanceof FragmentActivity) {
                showDialog((FragmentActivity) activity, msg2, onCancelListener2);
                return;
            }
            loadingDialog = new LoadingDialog();
            loadingDialog.setOnCancelListener(onCancelListener2);
            loadingDialog.createDialog(activity);
            if (TextUtils.isEmpty(msg2)) {
                loadingDialog.message_tv.setVisibility(8);
            } else {
                loadingDialog.message_tv.setVisibility(0);
                loadingDialog.message_tv.setText(msg2);
            }
            loadingDialog.getDialog().show();
        } catch (Exception e) {
            e.printStackTrace();
            loadingDialog = null;
        }
    }

    public static void hiddenDialog() {
        if (loadingDialog != null) {
            try {
                if (loadingDialog.animation != null) {
                    loadingDialog.animation.stop();
                    loadingDialog.progressBar.clearAnimation();
                    loadingDialog.animation = null;
                    loadingDialog.progressBar = null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (loadingDialog.mDialog != null) {
                    loadingDialog.mDialog.dismiss();
                } else {
                    loadingDialog.dismissAllowingStateLoss();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            loadingDialog = null;
        }
    }

    public Dialog getDialog() {
        return this.mDialog == null ? super.getDialog() : this.mDialog;
    }

    public Dialog createDialog(Activity activity) {
        this.mDialog = new Dialog(activity, getTheme());
        setupDialog();
        this.mDialog.setContentView(createView(activity));
        return this.mDialog;
    }

    public void setCancelable(boolean cancelable) {
        if (this.mDialog != null) {
            this.mDialog.setCanceledOnTouchOutside(false);
            this.mDialog.setCancelable(cancelable);
            return;
        }
        getDialog().setCanceledOnTouchOutside(false);
        super.setCancelable(cancelable);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            this.msg = bundle.getString(NotificationCompat.CATEGORY_MESSAGE);
        } else if (savedInstanceState != null) {
            this.msg = savedInstanceState.getString(NotificationCompat.CATEGORY_MESSAGE);
        }
        if (TextUtils.isEmpty(this.msg)) {
            this.msg = "";
        }
    }

    private View createView(Activity activity) {
        LinearLayout content = new LinearLayout(activity);
        content.setOrientation(0);
        content.setGravity(17);
        content.setMinimumWidth(BTScreenUtil.getInstance(activity).getHorizontalPX(460.0d));
        content.setMinimumHeight(BTScreenUtil.getInstance(activity).getVerticalPX(180.0d));
        content.setBackgroundDrawable(BTResourceUtil.findDrawableByName("bg_widget"));
        int padding = BTScreenUtil.getInstance(activity).getHorizontalPX(30.0d);
        content.setPadding(padding, padding, padding, padding);
        this.progressBar = new ImageView(activity);
        this.progressBar.setImageDrawable(BTResourceUtil.findDrawableByName("progress_loading_img"));
        this.animation = (AnimationDrawable) this.progressBar.getDrawable();
        this.progressBar.setLayoutParams(new LayoutParams(BTScreenUtil.getInstance(activity).getHorizontalPX(100.0d), BTScreenUtil.getInstance(activity).getHorizontalPX(100.0d)));
        content.addView(this.progressBar);
        this.message_tv = new TextView(activity);
        this.message_tv.setTextSize(0, (float) BTScreenUtil.countTextSize(activity, 48.0f));
        this.message_tv.setGravity(17);
        this.message_tv.setTextColor(-1);
        this.message_tv.setIncludeFontPadding(false);
        this.message_tv.setPadding(0, 0, 0, 0);
        LayoutParams ls = new LayoutParams(-2, -2);
        ls.setMargins(padding, 0, 0, 0);
        this.message_tv.setLayoutParams(ls);
        content.addView(this.message_tv);
        if (TextUtils.isEmpty(this.msg)) {
            this.message_tv.setVisibility(8);
        } else {
            this.message_tv.setVisibility(0);
            this.message_tv.setText(this.msg);
        }
        this.animation.start();
        return content;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return createView(getActivity());
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        setupDialog();
        super.onActivityCreated(savedInstanceState);
    }

    private void setupDialog() {
        Window window = getDialog().getWindow();
        window.requestFeature(1);
        window.setLayout(-1, -1);
        window.setBackgroundDrawable(new ColorDrawable(0));
        BTScreenUtil.getInstance(getActivity()).hideNavigationBar(window);
        setCancelable(true);
        if (this.onCancelListener != null) {
            getDialog().setOnCancelListener(this.onCancelListener);
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(NotificationCompat.CATEGORY_MESSAGE, this.msg);
    }
}
