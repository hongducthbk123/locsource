package p004cn.jpush.android.p043ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.google.api.client.googleapis.media.MediaHttpDownloader;
import java.io.File;
import p004cn.jpush.android.data.C0589b;
import p004cn.jpush.android.data.C0594g;
import p004cn.jpush.android.p037a.C0546d;
import p004cn.jpush.android.p039c.C0574h;
import p004cn.jpush.android.p040d.C0577a;
import p004cn.jpush.android.p040d.C0582e;
import p004cn.jpush.android.p040d.C0586i;
import p004cn.jpush.android.p041e.p042a.C0596a;
import p004cn.jpush.android.p041e.p042a.C0597b;
import p004cn.jpush.android.p041e.p042a.C0601f;
import p004cn.jpush.android.service.C0605b;

/* renamed from: cn.jpush.android.ui.PopWinActivity */
public class PopWinActivity extends Activity {

    /* renamed from: a */
    public static C0601f f899a = null;

    /* renamed from: b */
    private String f900b;

    /* renamed from: c */
    private WebView f901c;

    /* renamed from: d */
    private C0589b f902d = null;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        String str;
        super.onCreate(bundle);
        if (getIntent() != null) {
            try {
                if (getIntent().getBooleanExtra("isNotification", false)) {
                    C0605b.m1389a();
                    C0605b.m1390a(getApplicationContext(), getIntent());
                    finish();
                    return;
                }
                Intent intent = getIntent();
                C0589b bVar = (C0589b) intent.getSerializableExtra("body");
                if (bVar == null) {
                    C0582e.m1302a("PopWinActivity", "parse entity form plugin plateform");
                    if (intent.getData() != null) {
                        str = intent.getData().toString();
                    } else {
                        str = null;
                    }
                    if (TextUtils.isEmpty(str) && intent.getExtras() != null) {
                        str = intent.getExtras().getString("JMessageExtra");
                    }
                    bVar = C0574h.m1252a(this, str, "");
                }
                this.f902d = bVar;
                if (this.f902d != null) {
                    this.f900b = this.f902d.f805c;
                    int identifier = getResources().getIdentifier("jpush_popwin_layout", "layout", getPackageName());
                    if (identifier == 0) {
                        C0582e.m1308d("PopWinActivity", "Please add layout resource jpush_popwin_layout.xml to res/layout !");
                        finish();
                    } else {
                        setContentView(identifier);
                        int identifier2 = getResources().getIdentifier("wvPopwin", "id", getPackageName());
                        if (identifier2 == 0) {
                            C0582e.m1308d("PopWinActivity", "Please use default code in jpush_popwin_layout.xml!");
                            finish();
                        } else {
                            this.f901c = (WebView) findViewById(identifier2);
                            if (this.f901c == null) {
                                C0582e.m1308d("PopWinActivity", "Can not get webView in layout file!");
                                finish();
                            } else {
                                this.f901c.setScrollbarFadingEnabled(true);
                                this.f901c.setScrollBarStyle(MediaHttpDownloader.MAXIMUM_CHUNK_SIZE);
                                WebSettings settings = this.f901c.getSettings();
                                settings.setDomStorageEnabled(true);
                                C0577a.m1275a(settings);
                                C0577a.m1276a(this.f901c);
                                settings.setSavePassword(false);
                                this.f901c.setBackgroundColor(0);
                                f899a = new C0601f(this, this.f902d);
                                if (VERSION.SDK_INT >= 17) {
                                    C0582e.m1302a("PopWinActivity", "Android sdk version greater than or equal to 17, Java—Js interact by annotation!");
                                    m1409a();
                                }
                                this.f901c.setWebChromeClient(new C0596a("JPushWeb", C0597b.class, null, null));
                                this.f901c.setWebViewClient(new C0612a(this.f902d, this));
                                C0597b.setWebViewHelper(f899a);
                            }
                        }
                    }
                    C0594g gVar = (C0594g) this.f902d;
                    String str2 = gVar.f858Q;
                    String str3 = gVar.f859a;
                    if (TextUtils.isEmpty(str2) || !new File(str2.replace("file://", "")).exists()) {
                        this.f901c.loadUrl(str3);
                    } else {
                        this.f901c.loadUrl(str2);
                    }
                    C0546d.m1124a(this.f900b, 1000, null, this);
                    return;
                }
                C0582e.m1306c("PopWinActivity", "Warning，null message entity! Close PopWinActivity!");
                finish();
            } catch (Exception e) {
                C0582e.m1308d("PopWinActivity", "Extra data is not serializable!");
                e.printStackTrace();
                finish();
            }
        } else {
            C0582e.m1306c("PopWinActivity", "PopWinActivity get NULL intent!");
            finish();
        }
    }

    /* renamed from: a */
    private void m1409a() {
        try {
            C0586i.m1318a(this.f901c, "addJavascriptInterface", new Class[]{Object.class, String.class}, new Object[]{f899a, "JPushWeb"});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onBackPressed() {
        super.onBackPressed();
        C0546d.m1124a(this.f900b, 1006, null, this);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (this.f901c != null) {
            if (VERSION.SDK_INT >= 11) {
                this.f901c.onResume();
            }
            C0597b.setWebViewHelper(f899a);
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        if (this.f901c != null && VERSION.SDK_INT >= 11) {
            this.f901c.onPause();
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        if (this.f901c != null) {
            this.f901c.removeAllViews();
            this.f901c.destroy();
            this.f901c = null;
        }
        super.onDestroy();
    }

    /* renamed from: a */
    public final void mo7012a(String str) {
        if (this.f902d != null && this.f901c != null && (this.f902d instanceof C0594g)) {
            if (!TextUtils.isEmpty(str)) {
                ((C0594g) this.f902d).f859a = str;
                Intent intent = new Intent(this, PushActivity.class);
                intent.putExtra("body", this.f902d);
                intent.putExtra("from_way", true);
                intent.setFlags(335544320);
                startActivity(intent);
            }
            finish();
        }
    }
}
