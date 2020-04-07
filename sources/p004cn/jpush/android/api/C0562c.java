package p004cn.jpush.android.api;

import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.ImageButton;
import p004cn.jpush.android.p041e.p042a.C0600e;

/* renamed from: cn.jpush.android.api.c */
public final class C0562c {

    /* renamed from: a */
    public static C0600e f739a = null;

    /* renamed from: b */
    private static int f740b = 400;

    /* renamed from: c */
    private static int f741c = 600;

    /* renamed from: a */
    public static void m1201a(WindowManager windowManager, WebView webView, ImageButton imageButton) {
        windowManager.removeView(webView);
        windowManager.removeView(imageButton);
    }
}
