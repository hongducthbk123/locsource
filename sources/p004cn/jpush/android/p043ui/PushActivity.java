package p004cn.jpush.android.p043ui;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import java.io.File;
import java.lang.ref.WeakReference;
import p004cn.jpush.android.api.C0560b;
import p004cn.jpush.android.data.C0589b;
import p004cn.jpush.android.data.C0594g;
import p004cn.jpush.android.p037a.C0546d;
import p004cn.jpush.android.p037a.C0550g;
import p004cn.jpush.android.p039c.C0574h;
import p004cn.jpush.android.p040d.C0577a;
import p004cn.jpush.android.p040d.C0582e;

/* renamed from: cn.jpush.android.ui.PushActivity */
public class PushActivity extends Activity {

    /* renamed from: a */
    private int f903a = 0;

    /* renamed from: b */
    private boolean f904b = false;

    /* renamed from: c */
    private String f905c;
    /* access modifiers changed from: private */

    /* renamed from: d */
    public FullScreenView f906d = null;

    /* renamed from: e */
    private Handler f907e;

    /* renamed from: cn.jpush.android.ui.PushActivity$a */
    private static class C0611a extends Handler {

        /* renamed from: a */
        private final WeakReference<PushActivity> f909a;

        public C0611a(PushActivity pushActivity) {
            this.f909a = new WeakReference<>(pushActivity);
        }

        public final void handleMessage(Message message) {
            C0589b bVar = (C0589b) message.obj;
            PushActivity pushActivity = (PushActivity) this.f909a.get();
            if (pushActivity != null) {
                switch (message.what) {
                    case 1:
                        pushActivity.setRequestedOrientation(1);
                        PushActivity.m1412a(pushActivity, bVar);
                        return;
                    case 2:
                        pushActivity.mo7019b();
                        return;
                    default:
                        return;
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f907e = new C0611a(this);
        m1413c();
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        m1413c();
    }

    /* renamed from: c */
    private void m1413c() {
        if (getIntent() != null) {
            try {
                this.f904b = getIntent().getBooleanExtra("from_way", false);
                Intent intent = getIntent();
                C0589b bVar = (C0589b) intent.getSerializableExtra("body");
                if (bVar == null) {
                    C0582e.m1302a("PushActivity", "parse entity form plugin plateform");
                    String str = null;
                    if (intent.getData() != null) {
                        str = intent.getData().toString();
                    }
                    if (TextUtils.isEmpty(str) && intent.getExtras() != null) {
                        str = intent.getExtras().getString("JMessageExtra");
                    }
                    bVar = C0574h.m1252a(this, str, "");
                }
                if (bVar != null) {
                    this.f905c = bVar.f805c;
                    if (bVar != null) {
                        switch (bVar.f819q) {
                            case 0:
                                Message message = new Message();
                                message.what = 1;
                                message.obj = bVar;
                                this.f907e.sendMessageDelayed(message, 500);
                                return;
                            default:
                                C0560b.m1193a((Context) this, bVar, 0);
                                finish();
                                return;
                        }
                    } else {
                        C0582e.m1306c("PushActivity", "Null message entity! Close PushActivity!");
                        finish();
                    }
                } else {
                    C0582e.m1306c("PushActivity", "Warning，null message entity! Close PushActivity!");
                    finish();
                }
            } catch (Exception e) {
                C0582e.m1308d("PushActivity", "Extra data is not serializable!");
                e.printStackTrace();
                finish();
            }
        } else {
            C0582e.m1306c("PushActivity", "PushActivity get NULL intent!");
            finish();
        }
    }

    /* renamed from: a */
    public final void mo7018a() {
        runOnUiThread(new Runnable() {
            public final void run() {
                if (PushActivity.this.f906d != null) {
                    PushActivity.this.f906d.showTitleBar();
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (this.f906d != null) {
            this.f906d.resume();
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        if (this.f906d != null) {
            this.f906d.pause();
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        if (this.f906d != null) {
            this.f906d.destory();
        }
        if (this.f907e.hasMessages(2)) {
            this.f907e.removeMessages(2);
        }
        super.onDestroy();
    }

    public void onBackPressed() {
        if (this.f906d == null || !this.f906d.webviewCanGoBack()) {
            C0546d.m1124a(this.f905c, 1006, null, this);
            mo7019b();
            return;
        }
        this.f906d.webviewGoBack();
    }

    /* renamed from: b */
    public final void mo7019b() {
        finish();
        if (1 == this.f903a) {
            try {
                ActivityManager activityManager = (ActivityManager) getSystemService("activity");
                ComponentName componentName = ((RunningTaskInfo) activityManager.getRunningTasks(1).get(0)).baseActivity;
                ComponentName componentName2 = ((RunningTaskInfo) activityManager.getRunningTasks(1).get(0)).topActivity;
                if (componentName != null && componentName2 != null && componentName2.toString().equals(componentName.toString())) {
                    C0577a.m1281b(this);
                }
            } catch (Exception e) {
                C0582e.m1306c("PushActivity", "Get running tasks failed.");
                C0577a.m1281b(this);
            }
        }
    }

    /* renamed from: a */
    static /* synthetic */ void m1412a(PushActivity pushActivity, C0589b bVar) {
        if (bVar == null) {
            C0582e.m1306c("PushActivity", "Null message entity! Close PushActivity!");
            pushActivity.finish();
            return;
        }
        C0594g gVar = (C0594g) bVar;
        if (gVar.f854M == 0) {
            pushActivity.f903a = gVar.f852K;
            int identifier = pushActivity.getResources().getIdentifier("jpush_webview_layout", "layout", pushActivity.getPackageName());
            if (identifier == 0) {
                C0582e.m1308d("PushActivity", "Please add layout resource jpush_webview_layout.xml to res/layout !");
                pushActivity.finish();
                return;
            }
            pushActivity.setContentView(identifier);
            String str = gVar.f859a;
            if (!C0550g.m1139a(str)) {
                C0560b.m1193a((Context) pushActivity, bVar, 0);
                pushActivity.finish();
                return;
            }
            String str2 = gVar.f858Q;
            if (gVar.f820r) {
                int identifier2 = pushActivity.getResources().getIdentifier("actionbarLayoutId", "id", pushActivity.getPackageName());
                if (identifier2 == 0) {
                    C0582e.m1308d("PushActivity", "Please use default code in jpush_webview_layout.xml!");
                    pushActivity.finish();
                    return;
                }
                pushActivity.f906d = (FullScreenView) pushActivity.findViewById(identifier2);
                pushActivity.f906d.initModule(pushActivity, bVar);
                if (TextUtils.isEmpty(str2) || !new File(str2.replace("file://", "")).exists() || pushActivity.f904b) {
                    pushActivity.f906d.loadUrl(str);
                } else {
                    pushActivity.f906d.loadUrl(str2);
                }
            }
            if (!pushActivity.f904b) {
                C0546d.m1124a(pushActivity.f905c, 1000, null, pushActivity);
            }
        }
    }
}
