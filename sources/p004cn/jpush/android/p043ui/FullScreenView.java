package p004cn.jpush.android.p043ui;

import android.app.Activity;
import android.content.Context;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.google.api.client.googleapis.media.MediaHttpDownloader;
import p004cn.jpush.android.data.C0589b;
import p004cn.jpush.android.data.C0594g;
import p004cn.jpush.android.p040d.C0577a;
import p004cn.jpush.android.p040d.C0582e;
import p004cn.jpush.android.p040d.C0586i;
import p004cn.jpush.android.p041e.p042a.C0596a;
import p004cn.jpush.android.p041e.p042a.C0597b;
import p004cn.jpush.android.p041e.p042a.C0601f;

/* renamed from: cn.jpush.android.ui.FullScreenView */
public class FullScreenView extends LinearLayout {
    private static final String TAG = "FullScreenView";
    public static C0601f webViewHelper = null;
    private OnClickListener btnBackClickListener = new OnClickListener() {
        public final void onClick(View view) {
            if (FullScreenView.this.mContext != null) {
                ((Activity) FullScreenView.this.mContext).onBackPressed();
            }
        }
    };
    private ImageButton imgBtnBack;
    /* access modifiers changed from: private */
    public final Context mContext;
    /* access modifiers changed from: private */
    public WebView mWebView;
    private ProgressBar pushPrograssBar;
    private RelativeLayout rlTitleBar;
    private TextView tvTitle;

    public FullScreenView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
    }

    public void initModule(Context context, C0589b bVar) {
        C0594g gVar = (C0594g) bVar;
        String str = gVar.f851J;
        setFocusable(true);
        this.mWebView = (WebView) findViewById(getResources().getIdentifier("fullWebView", "id", context.getPackageName()));
        this.rlTitleBar = (RelativeLayout) findViewById(getResources().getIdentifier("rlRichpushTitleBar", "id", context.getPackageName()));
        this.tvTitle = (TextView) findViewById(getResources().getIdentifier("tvRichpushTitle", "id", context.getPackageName()));
        this.imgBtnBack = (ImageButton) findViewById(getResources().getIdentifier("imgRichpushBtnBack", "id", context.getPackageName()));
        this.pushPrograssBar = (ProgressBar) findViewById(getResources().getIdentifier("pushPrograssBar", "id", context.getPackageName()));
        if (this.mWebView == null || this.rlTitleBar == null || this.tvTitle == null || this.imgBtnBack == null) {
            C0582e.m1308d(TAG, "Please use default code in jpush_webview_layout.xml!");
            ((Activity) this.mContext).finish();
        }
        if (1 == gVar.f853L) {
            this.rlTitleBar.setVisibility(8);
            ((Activity) context).getWindow().setFlags(1024, 1024);
        } else {
            this.tvTitle.setText(str);
            this.imgBtnBack.setOnClickListener(this.btnBackClickListener);
        }
        this.mWebView.setScrollbarFadingEnabled(true);
        this.mWebView.setScrollBarStyle(MediaHttpDownloader.MAXIMUM_CHUNK_SIZE);
        WebSettings settings = this.mWebView.getSettings();
        C0577a.m1275a(settings);
        C0577a.m1276a(this.mWebView);
        settings.setSavePassword(false);
        webViewHelper = new C0601f(context, bVar);
        if (VERSION.SDK_INT >= 17) {
            C0582e.m1302a(TAG, "Android sdk version greater than or equal to 17, Java—Js interact by annotation!");
            reflectAddJsInterface();
        }
        this.mWebView.setWebChromeClient(new C0596a("JPushWeb", C0597b.class, this.pushPrograssBar, this.tvTitle));
        this.mWebView.setWebViewClient(new C0612a(bVar, context));
        C0597b.setWebViewHelper(webViewHelper);
    }

    private void reflectAddJsInterface() {
        try {
            C0586i.m1318a(this.mWebView, "addJavascriptInterface", new Class[]{Object.class, String.class}, new Object[]{webViewHelper, "JPushWeb"});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean webviewCanGoBack() {
        if (this.mWebView != null) {
            return this.mWebView.canGoBack();
        }
        return false;
    }

    public void webviewGoBack() {
        if (this.mWebView != null) {
            this.mWebView.goBack();
        }
    }

    public void loadUrl(String str) {
        if (this.mWebView != null) {
            this.mWebView.loadUrl(str);
        }
    }

    public void resume() {
        if (this.mWebView != null) {
            if (VERSION.SDK_INT >= 11) {
                this.mWebView.onResume();
            }
            C0597b.setWebViewHelper(webViewHelper);
        }
    }

    public void pause() {
        if (this.mWebView != null && VERSION.SDK_INT >= 11) {
            this.mWebView.onPause();
        }
    }

    public void destory() {
        removeAllViews();
        if (this.mWebView != null) {
            this.mWebView.removeAllViews();
            this.mWebView.clearSslPreferences();
            this.mWebView.destroy();
            this.mWebView = null;
        }
    }

    public void showTitleBar() {
        if (this.rlTitleBar != null && this.rlTitleBar.getVisibility() == 8) {
            this.rlTitleBar.setVisibility(0);
            quitFullScreen();
            this.imgBtnBack.setOnClickListener(this.btnBackClickListener);
            if (this.mWebView != null) {
                this.mWebView.postDelayed(new Runnable() {
                    public final void run() {
                        if (FullScreenView.this.mWebView != null) {
                            FullScreenView.this.mWebView.clearHistory();
                        }
                    }
                }, 1000);
            }
        }
    }

    private void quitFullScreen() {
        try {
            LayoutParams attributes = ((Activity) this.mContext).getWindow().getAttributes();
            attributes.flags &= -1025;
            ((Activity) this.mContext).getWindow().setAttributes(attributes);
            ((Activity) this.mContext).getWindow().clearFlags(512);
        } catch (Exception e) {
        }
    }
}
