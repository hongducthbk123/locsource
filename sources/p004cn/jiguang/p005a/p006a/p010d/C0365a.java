package p004cn.jiguang.p005a.p006a.p010d;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.os.Bundle;
import p004cn.jiguang.api.JCoreInterface;
import p004cn.jiguang.p005a.C0334a;
import p004cn.jiguang.p015d.C0385a;
import p004cn.jiguang.p015d.p021d.C0450g;
import p004cn.jiguang.p015d.p021d.C0460q;

@TargetApi(14)
/* renamed from: cn.jiguang.a.a.d.a */
public final class C0365a implements ActivityLifecycleCallbacks {

    /* renamed from: a */
    private static boolean f140a = false;

    /* renamed from: b */
    private static int f141b = 0;

    public final void onActivityCreated(Activity activity, Bundle bundle) {
        if (C0334a.f23a != null) {
            C0334a.f23a.dispatchStatus(activity, "onCreate");
        }
    }

    public final void onActivityDestroyed(Activity activity) {
    }

    public final void onActivityPaused(Activity activity) {
        if (C0334a.f23a != null) {
            C0334a.f23a.dispatchPause(activity);
        }
        if (!C0334a.f24b) {
            C0366b.m144a().mo6250b(activity);
            Context context = activity != null ? activity.getApplicationContext() : C0385a.f198e;
            C0460q.m709a(context);
            C0460q.m718b(context);
        }
    }

    public final void onActivityResumed(Activity activity) {
        if (C0334a.f23a != null) {
            C0334a.f23a.dispatchResume(activity);
        }
        if (!C0334a.f24b) {
            C0366b.m144a().mo6248a((Context) activity);
        }
    }

    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public final void onActivityStarted(Activity activity) {
        if (f141b == 0 && activity != null) {
            C0450g.m659a();
            C0450g.m661b(activity.getApplicationContext());
            JCoreInterface.triggerSceneCheck(activity.getApplicationContext(), 1);
        }
        f141b++;
        if (C0334a.f23a != null) {
            C0334a.f23a.dispatchStatus(activity, "onStart");
        }
    }

    public final void onActivityStopped(Activity activity) {
        f141b--;
    }
}
