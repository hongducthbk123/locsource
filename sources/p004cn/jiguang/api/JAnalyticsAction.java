package p004cn.jiguang.api;

import android.content.Context;

/* renamed from: cn.jiguang.api.JAnalyticsAction */
public interface JAnalyticsAction {
    void dispatchPause(Context context);

    void dispatchResume(Context context);

    void dispatchStatus(Context context, String str);
}
