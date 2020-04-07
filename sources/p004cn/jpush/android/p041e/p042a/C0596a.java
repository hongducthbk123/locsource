package p004cn.jpush.android.p041e.p042a;

import android.text.TextUtils.TruncateAt;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.TextView;

/* renamed from: cn.jpush.android.e.a.a */
public final class C0596a extends C0598c {

    /* renamed from: a */
    private ProgressBar f863a;

    /* renamed from: b */
    private TextView f864b;

    public C0596a(String str, Class cls, ProgressBar progressBar, TextView textView) {
        super(str, cls);
        this.f863a = progressBar;
        this.f864b = textView;
        if (this.f863a != null) {
            this.f863a.setMax(100);
        }
        if (this.f864b != null) {
            this.f864b.setSingleLine(true);
            this.f864b.setEllipsize(TruncateAt.END);
        }
    }

    public final boolean onJsAlert(WebView webView, String str, String str2, JsResult jsResult) {
        return super.onJsAlert(webView, str, str2, jsResult);
    }

    public final void onProgressChanged(WebView webView, int i) {
        super.onProgressChanged(webView, i);
        if (this.f863a != null) {
            if (100 == i) {
                this.f863a.setVisibility(8);
            } else {
                this.f863a.setVisibility(0);
                this.f863a.setProgress(i);
            }
        }
        if (this.f864b != null && webView.getTitle() != null && !webView.getTitle().equals(this.f864b.getText())) {
            this.f864b.setText(webView.getTitle());
        }
    }

    public final boolean onJsPrompt(WebView webView, String str, String str2, String str3, JsPromptResult jsPromptResult) {
        return super.onJsPrompt(webView, str, str2, str3, jsPromptResult);
    }
}
