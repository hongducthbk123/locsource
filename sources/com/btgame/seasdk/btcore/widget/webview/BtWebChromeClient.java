package com.btgame.seasdk.btcore.widget.webview;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnKeyListener;
import android.content.Intent;
import android.net.Uri;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.ConsoleMessage;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebChromeClient.FileChooserParams;
import android.webkit.WebView;
import android.widget.EditText;
import com.btgame.seasdk.btcore.common.util.BtsdkLog;
import com.btgame.seasdk.btcore.common.util.DebugUtils;
import com.btgame.seasdk.btcore.widget.BtToast;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;

public class BtWebChromeClient extends WebChromeClient {
    Activity activity;
    UploadHandler handler;

    public BtWebChromeClient(Activity activity2) {
        this.activity = activity2;
        this.handler = new UploadHandler(activity2);
    }

    public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
        BtsdkLog.m1429d("onShowFileChooser");
        this.handler.onShowFileChooser(filePathCallback, fileChooserParams);
        return true;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        this.handler.onResult(requestCode, resultCode, intent);
    }

    public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
        if (DebugUtils.getInstance().isLogFlag()) {
            return onJsConfirm(view, url, message, result);
        }
        if (!TextUtils.isEmpty(message)) {
            BtToast.showShortTimeToast(view.getContext(), Html.fromHtml(message));
        }
        result.confirm();
        return true;
    }

    public boolean onJsConfirm(WebView view, String url, String message, final JsResult result) {
        BtsdkLog.m1429d("onJsConfirm");
        Builder builder = new Builder(view.getContext());
        builder.setMessage(Html.fromHtml(message)).setPositiveButton("Yes", new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                result.confirm();
            }
        }).setNeutralButton("No", new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                result.cancel();
            }
        });
        builder.setOnCancelListener(new OnCancelListener() {
            public void onCancel(DialogInterface dialog) {
                result.cancel();
            }
        });
        builder.setOnKeyListener(new OnKeyListener() {
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                Log.v("onJsConfirm", "keyCode==" + keyCode + "event=" + event);
                return true;
            }
        });
        builder.create().show();
        return true;
    }

    public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, final JsPromptResult result) {
        Builder builder = new Builder(view.getContext());
        builder.setMessage(Html.fromHtml(message));
        final EditText et = new EditText(view.getContext());
        et.setSingleLine();
        et.setText(defaultValue);
        builder.setView(et).setPositiveButton("Yes", new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                result.confirm(et.getText().toString());
            }
        }).setNeutralButton("No", new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                result.cancel();
            }
        });
        builder.setOnKeyListener(new OnKeyListener() {
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                BtsdkLog.m1429d("keyCode==" + keyCode + "event=" + event);
                return true;
            }
        });
        builder.create().show();
        return true;
    }

    public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
        BtsdkLog.m1429d("Console: " + consoleMessage.message() + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + consoleMessage.sourceId() + ":" + consoleMessage.lineNumber());
        return true;
    }
}
