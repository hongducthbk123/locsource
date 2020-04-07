package p004cn.jpush.android.p041e.p042a;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.ImageButton;
import p004cn.jpush.android.api.C0562c;
import p004cn.jpush.android.api.JPushInterface;
import p004cn.jpush.android.p040d.C0582e;

/* renamed from: cn.jpush.android.e.a.e */
public final class C0600e {

    /* renamed from: a */
    private Context f871a;

    /* renamed from: b */
    private WindowManager f872b;

    /* renamed from: c */
    private WebView f873c;

    /* renamed from: d */
    private ImageButton f874d;

    /* renamed from: a */
    public final void mo6916a(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            C0582e.m1308d("SystemAlertWebViewCallback", "The activity name is null or empty, Give up..");
        }
        if (this.f871a != null) {
            try {
                Class cls = Class.forName(str);
                if (cls != null) {
                    Intent intent = new Intent(this.f871a, cls);
                    intent.putExtra(JPushInterface.EXTRA_ACTIVITY_PARAM, str2);
                    intent.setFlags(268435456);
                    this.f871a.startActivity(intent);
                    C0562c.m1201a(this.f872b, this.f873c, this.f874d);
                }
            } catch (Exception e) {
                C0582e.m1308d("SystemAlertWebViewCallback", "The activity name is invalid, Give up..");
            }
        }
    }
}
