package p004cn.jpush.android.p043ui;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import java.util.Locale;
import org.apache.http.HttpHost;
import p004cn.jiguang.net.HttpUtils;
import p004cn.jpush.android.C0541a;
import p004cn.jpush.android.data.C0589b;
import p004cn.jpush.android.p037a.C0546d;
import p004cn.jpush.android.p040d.C0577a;

/* renamed from: cn.jpush.android.ui.a */
public final class C0612a extends WebViewClient {

    /* renamed from: a */
    private final C0589b f910a;

    /* renamed from: b */
    private final Context f911b;
    /* access modifiers changed from: private */

    /* renamed from: c */
    public boolean f912c = false;

    public C0612a(C0589b bVar, Context context) {
        this.f910a = bVar;
        this.f911b = context;
    }

    public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
        Context context = webView.getContext();
        try {
            webView.getSettings().setSavePassword(false);
            C0577a.m1276a(webView);
            String format = String.format(Locale.ENGLISH, "{\"url\":\"%s\"}", new Object[]{str});
            if (this.f910a.f799F) {
                context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                C0546d.m1124a(this.f910a.f805c, 1016, format, C0541a.f653e);
                return true;
            } else if (str.endsWith(".mp3")) {
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setDataAndType(Uri.parse(str), "audio/*");
                webView.getContext().startActivity(intent);
                return true;
            } else if (str.endsWith(".mp4") || str.endsWith(".3gp")) {
                Intent intent2 = new Intent("android.intent.action.VIEW");
                intent2.setDataAndType(Uri.parse(str), "video/*");
                webView.getContext().startActivity(intent2);
                return true;
            } else if (str.endsWith(".apk")) {
                webView.getContext().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                return true;
            } else {
                if (str.startsWith(HttpHost.DEFAULT_SCHEME_NAME)) {
                    C0546d.m1124a(this.f910a.f805c, 1016, format, C0541a.f653e);
                } else if (str.startsWith("mailto")) {
                    if (str.lastIndexOf("direct=") < 0 && !str.startsWith("mailto")) {
                        if (str.indexOf(HttpUtils.URL_AND_PARA_SEPARATOR) > 0) {
                            str = str + "&direct=false";
                        } else {
                            str = str + "?direct=false";
                        }
                        str.lastIndexOf("direct=");
                    }
                    int indexOf = str.indexOf(HttpUtils.URL_AND_PARA_SEPARATOR);
                    String substring = str.substring(0, indexOf);
                    String substring2 = str.substring(indexOf);
                    Intent intent3 = null;
                    if (substring.startsWith("mailto")) {
                        String[] split = substring.split(":");
                        if (split.length == 2) {
                            int indexOf2 = substring2.indexOf("title=") + 6;
                            int indexOf3 = substring2.indexOf("&content=");
                            String substring3 = substring2.substring(indexOf2, indexOf3);
                            String substring4 = substring2.substring(indexOf3 + 9);
                            String[] strArr = {split[1]};
                            intent3 = new Intent("android.intent.action.SEND");
                            intent3.setType("plain/text");
                            intent3.putExtra("android.intent.extra.EMAIL", strArr);
                            intent3.putExtra("android.intent.extra.SUBJECT", substring3);
                            intent3.putExtra("android.intent.extra.TEXT", substring4);
                        }
                    }
                    if (intent3 != null) {
                        context.startActivity(intent3);
                    }
                    C0546d.m1124a(this.f910a.f805c, 1016, format, C0541a.f653e);
                    return true;
                }
                return false;
            }
        } catch (Exception e) {
            return true;
        }
    }

    public final void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        super.onPageStarted(webView, str, bitmap);
    }

    public final void onPageFinished(WebView webView, String str) {
        super.onPageFinished(webView, str);
    }

    public final void onLoadResource(WebView webView, String str) {
        super.onLoadResource(webView, str);
    }

    public final void onReceivedSslError(WebView webView, final SslErrorHandler sslErrorHandler, SslError sslError) {
        if (this.f912c) {
            sslErrorHandler.proceed();
        } else if (this.f911b == null || this.f911b.getClass().isAssignableFrom(Activity.class)) {
            sslErrorHandler.cancel();
        } else {
            try {
                Builder builder = new Builder(this.f911b);
                builder.setTitle("提示");
                builder.setMessage("SSL 证书异常，是否继续加载？");
                builder.setNegativeButton("否", new OnClickListener() {
                    public final void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        sslErrorHandler.cancel();
                    }
                });
                builder.setPositiveButton("是", new OnClickListener() {
                    public final void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        C0612a.this.f912c = true;
                        sslErrorHandler.proceed();
                    }
                });
                builder.setCancelable(false);
                builder.create().show();
            } catch (Throwable th) {
                sslErrorHandler.cancel();
            }
        }
    }
}
