package com.btgame.seasdk.btcore.widget.webview;

import android.app.Activity;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import com.btgame.seasdk.btcore.common.entity.DeviceProperties;
import com.btgame.seasdk.btcore.common.util.BtsdkLog;
import com.google.gson.Gson;

public class BtWebApi {
    private Activity activity;
    /* access modifiers changed from: private */
    public WebDialog webDialog;
    /* access modifiers changed from: private */
    public WebView webView;

    public BtWebApi(Activity activity2, WebView webView2, WebDialog webDialog2) {
        this.activity = activity2;
        this.webView = webView2;
        this.webDialog = webDialog2;
    }

    @JavascriptInterface
    public void closeWindow() {
        this.activity.runOnUiThread(new Runnable() {
            public void run() {
                if (BtWebApi.this.webDialog != null) {
                    BtWebApi.this.webDialog.dismiss();
                    BtWebApi.this.webDialog = null;
                }
                if (BtWebApi.this.webView != null) {
                    BtWebApi.this.webView.destroy();
                    BtWebApi.this.webView = null;
                }
            }
        });
    }

    @JavascriptInterface
    public String getDeviceProperties() {
        String json = new Gson().toJson((Object) new DeviceProperties(this.activity.getApplication()));
        BtsdkLog.m1429d(json);
        return json;
    }
}
