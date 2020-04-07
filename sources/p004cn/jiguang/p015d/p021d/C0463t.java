package p004cn.jiguang.p015d.p021d;

import android.content.Context;
import com.facebook.internal.AnalyticsEvents;
import p004cn.jiguang.p029e.C0501d;

/* renamed from: cn.jiguang.d.d.t */
public final class C0463t {
    /* renamed from: a */
    public static String m727a(Context context) {
        try {
            String str = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
            if (str.length() <= 30) {
                return str;
            }
            C0501d.m909d("ServiceHelper", "The versionName is not valid, Please check your AndroidManifest.xml");
            return str.substring(0, 30);
        } catch (Throwable th) {
            return AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
        }
    }
}
