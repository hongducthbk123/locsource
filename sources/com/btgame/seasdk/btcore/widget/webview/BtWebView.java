package com.btgame.seasdk.btcore.widget.webview;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnKeyListener;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.btgame.seasdk.btcore.common.util.BTResourceUtil;
import com.btgame.seasdk.btcore.common.util.BtsdkLog;
import com.btgame.seasdk.btcore.common.util.ContextUtil;
import com.btgame.seasdk.btcore.common.util.NetworkUtils;
import com.btgame.seasdk.btcore.widget.LoadingDialog;
import com.btgame.seasdk.btcore.widget.webview.WebDialog.OnPageFinishedListener;
import java.util.Map;

public class BtWebView extends WebView {
    BtWebChromeClient chromeClient;
    /* access modifiers changed from: private */
    public NetWorkUnavailableListener netWorkUnavailableListener;
    /* access modifiers changed from: private */
    public OnPageFinishedListener onPageFinishedListener;

    private class DialogWebViewClient extends WebViewClient {
        private DialogWebViewClient() {
        }

        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            BtsdkLog.m1429d("url:" + url);
            if (TextUtils.isEmpty(url)) {
                return false;
            }
            if (url.startsWith("http:") || url.startsWith("https:")) {
                view.loadUrl(url);
            } else {
                try {
                    ContextUtil.getCurrentActivity().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(url)));
                } catch (Exception e) {
                    BtsdkLog.m1430e(e.getLocalizedMessage());
                }
            }
            return true;
        }

        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            try {
                LoadingDialog.showDialog(ContextUtil.getCurrentActivity(), "", (OnCancelListener) new OnCancelListener() {
                    public void onCancel(DialogInterface dialogInterface) {
                        dialogInterface.dismiss();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            try {
                LoadingDialog.hiddenDialog();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (BtWebView.this.onPageFinishedListener != null) {
                BtWebView.this.onPageFinishedListener.onPageFinished();
            }
        }

        public void onLoadResource(WebView view, String url) {
            super.onLoadResource(view, url);
        }

        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            BtsdkLog.m1430e("webview onReceivedError errorCode：" + errorCode + " description:" + description + " failingUrl:" + failingUrl);
            if (BtWebView.this.netWorkUnavailableListener == null) {
                BtWebView.this.showNetWorkDialog();
                return;
            }
            BtWebView.this.loadUrl("about:blank");
            int op = BtWebView.this.netWorkUnavailableListener.handleNetWorkError();
            String lang = BTResourceUtil.getSaveLocale(ContextUtil.getApplicationContext());
            switch (op) {
                case 1:
                    BtWebView.this.loadUrl("file:///android_asset/appErrorPage/redirecPage.html?gameLang=" + lang + "&redirectUrl=" + failingUrl);
                    return;
                case 2:
                    BtWebView.this.loadUrl("file:///android_asset/appErrorPage/returnPage.html?gameLang=" + lang);
                    return;
                default:
                    BtWebView.this.showNetWorkDialog();
                    return;
            }
        }

        public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
            super.onReceivedHttpError(view, request, errorResponse);
        }
    }

    public interface NetWorkUnavailableListener {
        int handleNetWorkError();

        void onNetWorkDialogClose();
    }

    public BtWebView(Context context) {
        super(context);
        setUpWebOptions();
        initJavaScript();
    }

    @SuppressLint({"NewApi"})
    public BtWebView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setUpWebOptions();
        initJavaScript();
    }

    public BtWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setUpWebOptions();
        initJavaScript();
    }

    public BtWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setUpWebOptions();
        initJavaScript();
    }

    public void executeJavascript(String scriptName) {
        loadUrl("javascript:" + scriptName);
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    private void initJavaScript() {
        this.chromeClient = new BtWebChromeClient((Activity) getContext());
        setWebChromeClient(this.chromeClient);
        getSettings().setJavaScriptEnabled(true);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (this.chromeClient != null) {
            this.chromeClient.onActivityResult(requestCode, resultCode, intent);
        }
    }

    private void setUpWebOptions() {
        setVerticalScrollBarEnabled(false);
        setHorizontalScrollBarEnabled(false);
        setWebViewClient(new DialogWebViewClient());
        setVisibility(4);
        setVerticalScrollbarOverlay(true);
        if (NetworkUtils.isNetworkAvaiable(ContextUtil.getCurrentActivity())) {
            getSettings().setCacheMode(-1);
        } else {
            getSettings().setCacheMode(1);
        }
        getSettings().setJavaScriptEnabled(true);
        getSettings().setUseWideViewPort(true);
        getSettings().setDomStorageEnabled(true);
        getSettings().setUseWideViewPort(false);
        getSettings().setLoadWithOverviewMode(false);
        getSettings().setBuiltInZoomControls(false);
        getSettings().setSupportZoom(false);
    }

    public void setOnPageFinishedListener(OnPageFinishedListener onPageFinishedListener2) {
        this.onPageFinishedListener = onPageFinishedListener2;
    }

    /* access modifiers changed from: private */
    public void showNetWorkDialog() {
        String msg;
        String tips_network_unavailable = BTResourceUtil.findStringByName("tips_network_unavailable");
        if (TextUtils.isEmpty(tips_network_unavailable)) {
            msg = "network is unavailable！";
        } else {
            msg = tips_network_unavailable;
        }
        Builder builder = new Builder(ContextUtil.getCurrentActivity());
        builder.setCancelable(false);
        builder.setMessage(msg).setPositiveButton("OK", new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if (BtWebView.this.netWorkUnavailableListener != null) {
                    BtWebView.this.netWorkUnavailableListener.onNetWorkDialogClose();
                }
            }
        });
        builder.setOnKeyListener(new OnKeyListener() {
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                Log.v("onJsPrompt", "keyCode==" + keyCode + "event=" + event);
                return true;
            }
        });
        builder.create().show();
    }

    public void setNetWorkUnavailableListener(NetWorkUnavailableListener netWorkUnavailableListener2) {
        this.netWorkUnavailableListener = netWorkUnavailableListener2;
    }

    public void loadUrl(String url) {
        super.loadUrl(url);
    }

    public void loadUrl(String url, Map<String, String> additionalHttpHeaders) {
        super.loadUrl(url, additionalHttpHeaders);
    }

    public void loadData(String data, String mimeType, String encoding) {
        super.loadData(data, mimeType, encoding);
    }
}
