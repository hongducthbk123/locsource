package com.btgame.onesdk.frame.p044ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface.OnCancelListener;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;
import com.btgame.sdk.util.BtsdkLog;
import com.btgame.sdk.util.ResourceUtil;

@SuppressLint({"ValidFragment"})
/* renamed from: com.btgame.onesdk.frame.ui.BtAlertDialog */
public class BtAlertDialog extends DialogFragment {
    private static final String BUNDLE_KEY_LEFT = "left";
    private static final String BUNDLE_KEY_MESSAGE = "message";
    private static final String BUNDLE_KEY_RIGHT = "right";
    private static final String BUNDLE_KEY_TITLE = "title";
    private static BtAlertDialog btAlertDialog;
    private String leftData;
    private TextView leftTextView;
    private Dialog mDialog;
    private TextView messageTextView;
    private String msgData;
    private OnCancelListener onCancelListener;
    private String rightData;
    private TextView rightTextView;
    private String titleData;
    private TextView titleTextView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            this.titleData = bundle.getString("title");
            this.msgData = bundle.getString("message");
            this.leftData = bundle.getString(BUNDLE_KEY_LEFT);
            this.rightData = bundle.getString(BUNDLE_KEY_RIGHT);
        } else if (savedInstanceState != null) {
            this.titleData = savedInstanceState.getString("title");
            this.msgData = savedInstanceState.getString("message");
            this.leftData = savedInstanceState.getString(BUNDLE_KEY_LEFT);
            this.rightData = savedInstanceState.getString(BUNDLE_KEY_RIGHT);
        }
        if (TextUtils.isEmpty(this.titleData)) {
            this.titleData = "";
        }
        if (TextUtils.isEmpty(this.msgData)) {
            this.msgData = "";
        }
        if (TextUtils.isEmpty(this.leftData)) {
            this.leftData = "";
        }
        if (TextUtils.isEmpty(this.rightData)) {
            this.rightData = "";
        }
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        setupDialog();
        super.onActivityCreated(savedInstanceState);
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return createView(getActivity());
    }

    private View createView(Activity activity) {
        View contentView = activity.getLayoutInflater().inflate(ResourceUtil.getLayoutId(activity, "bt_dialog"), null);
        this.titleTextView = (TextView) contentView.findViewById(ResourceUtil.getId(activity, "bt_dialog_tile"));
        if (this.titleData != null) {
            this.titleTextView.setText(this.titleData);
        }
        this.messageTextView = (TextView) contentView.findViewById(ResourceUtil.getId(activity, "bt_dialog_message"));
        if (this.msgData != null) {
            this.messageTextView.setText(this.msgData);
        }
        this.leftTextView = (TextView) contentView.findViewById(ResourceUtil.getId(activity, "bt_dialog_left"));
        setupView(this.leftTextView, this.leftData);
        this.rightTextView = (TextView) contentView.findViewById(ResourceUtil.getId(activity, "bt_dialog_right"));
        setupView(this.rightTextView, this.rightData);
        return contentView;
    }

    public static void showDialog(Activity activity, String title, String message, String left, String right, OnClickListener leftClick, OnClickListener rightClick, boolean cancelable, boolean outsideCancelable, OnCancelListener cancelListener) {
        try {
            hideDialog();
            if (!activity.isFinishing()) {
                btAlertDialog = new BtAlertDialog();
                btAlertDialog.setOnCancelListener(cancelListener);
                btAlertDialog.createDialog(activity);
                btAlertDialog.titleTextView.setText(title);
                btAlertDialog.messageTextView.setText(message);
                btAlertDialog.leftTextView.setText(left);
                btAlertDialog.leftTextView.setOnClickListener(leftClick);
                btAlertDialog.rightTextView.setText(right);
                btAlertDialog.rightTextView.setOnClickListener(rightClick);
                btAlertDialog.setupView(btAlertDialog.leftTextView, left);
                btAlertDialog.setupView(btAlertDialog.rightTextView, right);
                Bundle bundle = new Bundle();
                bundle.putSerializable("title", title);
                bundle.putSerializable("message", message);
                bundle.putSerializable(BUNDLE_KEY_LEFT, left);
                bundle.putSerializable(BUNDLE_KEY_RIGHT, right);
                btAlertDialog.setArguments(bundle);
                btAlertDialog.setCancelable(cancelable, outsideCancelable);
                btAlertDialog.getDialog().show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            btAlertDialog = null;
        }
    }

    public static void hideDialog() {
        BtsdkLog.m1423d("hideDialog");
        if (btAlertDialog != null) {
            try {
                if (btAlertDialog.mDialog != null) {
                    btAlertDialog.mDialog.dismiss();
                } else {
                    btAlertDialog.dismissAllowingStateLoss();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            btAlertDialog = null;
        }
    }

    private void setupView(TextView view, String txt) {
        if (TextUtils.isEmpty(txt)) {
            view.setClickable(false);
            view.setVisibility(4);
            return;
        }
        view.setText(txt);
        view.setClickable(true);
        view.setVisibility(0);
    }

    private void setCancelable(boolean cancelable, boolean outsideCancelable) {
        if (this.mDialog != null) {
            this.mDialog.setCanceledOnTouchOutside(outsideCancelable);
            this.mDialog.setCancelable(cancelable);
            return;
        }
        getDialog().setCanceledOnTouchOutside(outsideCancelable);
        super.setCancelable(cancelable);
    }

    public Dialog getDialog() {
        return this.mDialog == null ? super.getDialog() : this.mDialog;
    }

    private Dialog createDialog(Activity activity) {
        BtsdkLog.m1423d("createDialog");
        this.mDialog = new Dialog(activity, getTheme());
        setupDialog();
        this.mDialog.setContentView(createView(activity));
        return this.mDialog;
    }

    private void setOnCancelListener(OnCancelListener listener) {
        this.onCancelListener = listener;
    }

    private void setupDialog() {
        Window window = getDialog().getWindow();
        if (window != null) {
            window.setSoftInputMode(3);
            window.requestFeature(1);
        }
        if (this.onCancelListener != null) {
            getDialog().setOnCancelListener(this.onCancelListener);
        }
        setStyle(ResourceUtil.getStyleId(getDialog().getContext(), "bt_dialog_style"), 0);
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("title", this.titleTextView.getText().toString());
        outState.putString("message", this.messageTextView.getText().toString());
        outState.putString(BUNDLE_KEY_LEFT, this.leftTextView.getText().toString());
        outState.putString(BUNDLE_KEY_RIGHT, this.rightTextView.getText().toString());
    }
}
