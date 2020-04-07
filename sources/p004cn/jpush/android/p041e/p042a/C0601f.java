package p004cn.jpush.android.p041e.p042a;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.Intent.ShortcutIconResource;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import android.widget.Toast;
import java.lang.ref.WeakReference;
import p004cn.jiguang.api.JCoreInterface;
import p004cn.jpush.android.api.C0560b;
import p004cn.jpush.android.api.JPushInterface;
import p004cn.jpush.android.data.C0589b;
import p004cn.jpush.android.p037a.C0546d;
import p004cn.jpush.android.p037a.C0550g;
import p004cn.jpush.android.p040d.C0577a;
import p004cn.jpush.android.p040d.C0582e;
import p004cn.jpush.android.p043ui.PopWinActivity;
import p004cn.jpush.android.p043ui.PushActivity;

/* renamed from: cn.jpush.android.e.a.f */
public final class C0601f {

    /* renamed from: a */
    private final WeakReference<Activity> f875a;

    /* renamed from: b */
    private final C0589b f876b;

    public C0601f(Context context, C0589b bVar) {
        this.f875a = new WeakReference<>((Activity) context);
        this.f876b = bVar;
    }

    @JavascriptInterface
    public final void createShortcut(String str, String str2, String str3) {
        int i;
        int i2 = 0;
        try {
            i = Integer.parseInt(str3);
        } catch (Exception e) {
            i = i2;
        }
        if (this.f875a.get() != null) {
            Context context = (Context) this.f875a.get();
            int a = C0560b.m1183a(i);
            Uri parse = Uri.parse(str2);
            if (parse != null) {
                Intent intent = new Intent("android.intent.action.VIEW", parse);
                intent.setFlags(335544320);
                try {
                    ShortcutIconResource fromContext = ShortcutIconResource.fromContext(context, a);
                    Intent intent2 = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
                    intent2.putExtra("duplicate", false);
                    intent2.putExtra("android.intent.extra.shortcut.NAME", str);
                    intent2.putExtra("android.intent.extra.shortcut.INTENT", intent);
                    intent2.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", fromContext);
                    intent2.setPackage(context.getPackageName());
                    context.sendBroadcast(intent2);
                } catch (Throwable th) {
                    C0582e.m1306c("AndroidUtil", "createShortCut error:" + th.getMessage());
                }
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0028  */
    /* JADX WARNING: Removed duplicated region for block: B:18:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0019  */
    @android.webkit.JavascriptInterface
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void click(java.lang.String r6, java.lang.String r7, java.lang.String r8) {
        /*
            r5 = this;
            r1 = 0
            java.lang.ref.WeakReference<android.app.Activity> r0 = r5.f875a
            java.lang.Object r0 = r0.get()
            if (r0 == 0) goto L_0x0033
            r5.userClick(r6)
            boolean r0 = java.lang.Boolean.parseBoolean(r7)     // Catch:{ Exception -> 0x0034 }
            boolean r2 = java.lang.Boolean.parseBoolean(r8)     // Catch:{ Exception -> 0x0039 }
            r4 = r2
            r2 = r0
            r0 = r4
        L_0x0017:
            if (r0 == 0) goto L_0x0026
            java.lang.ref.WeakReference<android.app.Activity> r0 = r5.f875a
            java.lang.Object r0 = r0.get()
            android.content.Context r0 = (android.content.Context) r0
            cn.jpush.android.data.b r3 = r5.f876b
            p004cn.jpush.android.api.C0560b.m1193a(r0, r3, r1)
        L_0x0026:
            if (r2 == 0) goto L_0x0033
            java.lang.ref.WeakReference<android.app.Activity> r0 = r5.f875a
            java.lang.Object r0 = r0.get()
            android.app.Activity r0 = (android.app.Activity) r0
            r0.finish()
        L_0x0033:
            return
        L_0x0034:
            r0 = move-exception
            r0 = r1
        L_0x0036:
            r2 = r0
            r0 = r1
            goto L_0x0017
        L_0x0039:
            r2 = move-exception
            goto L_0x0036
        */
        throw new UnsupportedOperationException("Method not decompiled: p004cn.jpush.android.p041e.p042a.C0601f.click(java.lang.String, java.lang.String, java.lang.String):void");
    }

    @JavascriptInterface
    private void userClick(String str) {
        int i;
        int i2 = 1100;
        try {
            i = Integer.parseInt(str);
        } catch (Exception e) {
            i = i2;
        }
        C0546d.m1124a(this.f876b.f805c, i, null, (Context) this.f875a.get());
    }

    @JavascriptInterface
    public final void download(String str, String str2, String str3) {
        download(str, str2);
    }

    @JavascriptInterface
    public final void startActivityByName(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            C0582e.m1308d("WebViewHelper", "The activity name is null or empty, Give up..");
        }
        Context context = (Context) this.f875a.get();
        if (context != null) {
            try {
                Class cls = Class.forName(str);
                if (cls != null) {
                    Intent intent = new Intent(context, cls);
                    intent.putExtra(JPushInterface.EXTRA_ACTIVITY_PARAM, str2);
                    intent.setFlags(268435456);
                    context.startActivity(intent);
                }
            } catch (Exception e) {
                C0582e.m1308d("WebViewHelper", "The activity name is invalid, Give up..");
            }
        }
    }

    @JavascriptInterface
    public final void startActivityByIntent(String str, String str2) {
        Context context = (Context) this.f875a.get();
        if (context != null) {
            try {
                Intent intent = new Intent(str);
                intent.addCategory(context.getPackageName());
                intent.putExtra(JPushInterface.EXTRA_EXTRA, str2);
                intent.setFlags(268435456);
                context.startActivity(intent);
            } catch (Exception e) {
                C0582e.m1308d("WebViewHelper", "Unhandle intent : " + str);
            }
        }
    }

    @JavascriptInterface
    public final void triggerNativeAction(String str) {
        Context context = (Context) this.f875a.get();
        if (context != null) {
            String str2 = JPushInterface.ACTION_RICHPUSH_CALLBACK;
            String str3 = JPushInterface.EXTRA_EXTRA;
            Bundle bundle = new Bundle();
            if (str3 != null) {
                bundle.putString(str3, str);
            }
            C0577a.m1274a(context, str2, bundle);
        }
    }

    @JavascriptInterface
    public final void startMainActivity(String str) {
        Context context = (Context) this.f875a.get();
        if (context != null) {
            try {
                ((Activity) context).finish();
                C0577a.m1281b(context);
            } catch (Exception e) {
                C0582e.m1308d("WebViewHelper", "startMainActivity failed");
            }
        }
    }

    @JavascriptInterface
    public final void download(String str, String str2) {
        if (this.f875a.get() != null) {
            userClick(str);
            download(str2);
            C0560b.m1193a((Context) this.f875a.get(), this.f876b, 0);
            ((Activity) this.f875a.get()).finish();
        }
    }

    @JavascriptInterface
    public final void download(String str) {
        if (this.f875a.get() == null) {
        }
    }

    @JavascriptInterface
    public final void close() {
        if (this.f875a.get() != null) {
            ((Activity) this.f875a.get()).finish();
        }
    }

    @JavascriptInterface
    public final void showToast(String str) {
        if (this.f875a.get() != null) {
            Toast.makeText((Context) this.f875a.get(), str, 0).show();
        }
    }

    @JavascriptInterface
    public final void executeMsgMessage(String str) {
        if (JCoreInterface.getDebugMode() && this.f875a.get() != null) {
            C0550g.m1138a((Context) this.f875a.get(), str);
        }
    }

    @JavascriptInterface
    public final void showTitleBar() {
        if (this.f875a.get() != null && (this.f875a.get() instanceof PushActivity)) {
            ((PushActivity) this.f875a.get()).mo7018a();
        }
    }

    @JavascriptInterface
    public final void startPushActivity(String str) {
        if (this.f875a.get() != null && (this.f875a.get() instanceof PopWinActivity)) {
            ((PopWinActivity) this.f875a.get()).mo7012a(str);
        }
    }
}
