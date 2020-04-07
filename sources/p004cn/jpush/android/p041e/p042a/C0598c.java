package p004cn.jpush.android.p041e.p042a;

import android.os.Build.VERSION;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import p004cn.jpush.android.p040d.C0582e;

/* renamed from: cn.jpush.android.e.a.c */
public class C0598c extends WebChromeClient {

    /* renamed from: a */
    private final String f865a = "InjectedChromeClient";

    /* renamed from: b */
    private C0599d f866b;

    /* renamed from: c */
    private boolean f867c;

    public C0598c(String str, Class cls) {
        this.f866b = new C0599d(str, cls);
    }

    public boolean onJsAlert(WebView webView, String str, String str2, JsResult jsResult) {
        jsResult.confirm();
        return true;
    }

    public void onProgressChanged(WebView webView, int i) {
        webView.getSettings().setSavePassword(false);
        if (VERSION.SDK_INT < 17) {
            if (i <= 25) {
                this.f867c = false;
            } else if (!this.f867c) {
                C0582e.m1302a("InjectedChromeClient", "Android sdk version lesser than 17, Java—Js interact by injection!");
                webView.loadUrl(this.f866b.mo6914a());
                this.f867c = true;
            }
        }
        super.onProgressChanged(webView, i);
    }

    public boolean onJsPrompt(WebView webView, String str, String str2, String str3, JsPromptResult jsPromptResult) {
        if (VERSION.SDK_INT < 17) {
            jsPromptResult.confirm(this.f866b.mo6915a(webView, str2));
        }
        return true;
    }
}
