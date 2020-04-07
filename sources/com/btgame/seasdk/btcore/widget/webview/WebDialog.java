package com.btgame.seasdk.btcore.widget.webview;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.btgame.seasdk.btcore.common.util.BTResourceUtil;
import com.btgame.seasdk.btcore.common.util.BTScreenUtil;
import com.btgame.seasdk.btcore.common.util.ContextUtil;
import com.btgame.seasdk.btcore.widget.LoadingDialog;
import com.btgame.seasdk.btcore.widget.webview.BtWebView.NetWorkUnavailableListener;

public class WebDialog extends Dialog {
    private static final String interfaceName = "btWebApi";
    private Activity activity;
    private boolean canBack;
    /* access modifiers changed from: private */
    public FrameLayout contentFrameLayout;
    private boolean isDetached = false;
    private OnCreateListener onCreateListener;
    /* access modifiers changed from: private */
    public OnPageFinishedListener onPageFinishedListener;
    private String url;
    private BtWebApi webApi;
    /* access modifiers changed from: private */
    public BtWebView webView;

    public interface OnCreateListener {
        void onCreated();
    }

    public interface OnPageFinishedListener {
        void onPageFinished();
    }

    public BtWebView getWebView() {
        return this.webView;
    }

    public void setOnCreateListener(OnCreateListener onCreateListener2) {
        this.onCreateListener = onCreateListener2;
    }

    public void setOnPageFinishedListener(OnPageFinishedListener onPageFinishedListener2) {
        this.onPageFinishedListener = onPageFinishedListener2;
    }

    public boolean isCanBack() {
        return this.canBack;
    }

    public void setCanBack(boolean canBack2) {
        this.canBack = canBack2;
    }

    public WebDialog(Activity activity2, String url2) {
        super(activity2, BTResourceUtil.findStyleIdByName("ubFullScreenStyle"));
        this.activity = activity2;
        this.url = url2;
        setCancelable(false);
        setCanceledOnTouchOutside(false);
    }

    public void dismiss() {
        if (this.webView != null) {
            try {
                this.webView.clearHistory();
                this.contentFrameLayout.removeAllViews();
                this.webView.destroy();
                getContext().deleteDatabase("webview.db");
                getContext().deleteDatabase("webviewCache.db");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        LoadingDialog.hiddenDialog();
        if (!this.isDetached) {
            super.dismiss();
        }
    }

    public void onDetachedFromWindow() {
        this.isDetached = true;
        super.onDetachedFromWindow();
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.isDetached = false;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        getWindow().setLayout(-1, -1);
        getWindow().setBackgroundDrawable(new ColorDrawable(0));
        this.contentFrameLayout = new FrameLayout(getContext());
        this.contentFrameLayout.setBackgroundColor(0);
        getWindow().setGravity(17);
        setUpWebView();
        setContentView(this.contentFrameLayout);
    }

    public void addJavascriptInterface(BtWebApi webApi2) {
        if (this.webView != null) {
            this.webView.addJavascriptInterface(webApi2, interfaceName);
        } else {
            this.webApi = webApi2;
        }
    }

    @SuppressLint({"SetJavaScriptEnabled", "NewApi"})
    private void setUpWebView() {
        LinearLayout webViewContainer = new LinearLayout(getContext());
        this.webView = new BtWebView(this.activity);
        this.webView.addJavascriptInterface(this.webApi, interfaceName);
        if (this.onCreateListener != null) {
            this.onCreateListener.onCreated();
        }
        this.webView.setOnPageFinishedListener(new OnPageFinishedListener() {
            public void onPageFinished() {
                if (WebDialog.this.onPageFinishedListener != null) {
                    WebDialog.this.onPageFinishedListener.onPageFinished();
                }
                BTScreenUtil.getInstance(ContextUtil.getCurrentActivity()).hideNavigationBar(WebDialog.this.getWindow());
                WebDialog.this.contentFrameLayout.setBackgroundColor(0);
                WebDialog.this.webView.setVisibility(0);
            }
        });
        this.webView.setNetWorkUnavailableListener(new NetWorkUnavailableListener() {
            public int handleNetWorkError() {
                return 2;
            }

            public void onNetWorkDialogClose() {
                WebDialog.this.onBackPressed();
            }
        });
        this.webView.loadUrl(this.url);
        LayoutParams layoutParams = new LayoutParams(-1, -1);
        layoutParams.gravity = 17;
        webViewContainer.addView(this.webView, layoutParams);
        this.contentFrameLayout.addView(webViewContainer);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (this.webView != null) {
            this.webView.onActivityResult(requestCode, resultCode, intent);
        }
    }

    public void show() {
        super.show();
    }

    public void onBackPressed() {
        if (!isCanBack() || this.webView == null || !this.webView.canGoBack()) {
            super.onBackPressed();
            dismiss();
            this.webView.destroy();
            this.webView = null;
            return;
        }
        this.webView.goBack();
    }
}
