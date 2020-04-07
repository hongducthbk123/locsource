package p004cn.jiguang.p015d.p021d;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.text.TextUtils;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import org.json.JSONException;
import org.json.JSONObject;
import p004cn.jiguang.api.JAction;
import p004cn.jiguang.api.JActionExtra;
import p004cn.jiguang.api.SdkType;
import p004cn.jiguang.p015d.C0385a;
import p004cn.jiguang.p015d.p017b.C0417d;
import p004cn.jiguang.p015d.p017b.C0419f;
import p004cn.jiguang.p015d.p022e.p023a.p024a.C0468c;
import p004cn.jiguang.p029e.C0501d;
import p004cn.jiguang.p031g.C0530k;

/* renamed from: cn.jiguang.d.d.b */
public final class C0445b {

    /* renamed from: a */
    public static HashMap<String, JAction> f396a = new HashMap<>();

    /* renamed from: b */
    public static HashMap<String, JActionExtra> f397b = new HashMap<>();

    /* renamed from: c */
    private static volatile C0445b f398c;

    /* renamed from: d */
    private static final Object f399d = new Object();

    static {
        m624a(C0385a.f194a, C0449f.class.getName());
    }

    private C0445b() {
    }

    /* renamed from: a */
    public static C0445b m618a() {
        if (f398c == null) {
            synchronized (f399d) {
                if (f398c == null) {
                    f398c = new C0445b();
                }
            }
        }
        return f398c;
    }

    /* renamed from: a */
    private static Object m619a(JActionExtra jActionExtra, Context context, int i, String str, int i2) {
        if (jActionExtra == null) {
            return null;
        }
        if (i2 == 0) {
            try {
                return jActionExtra.beforRegister(context, i, str);
            } catch (Throwable th) {
                C0501d.m907c("ActionManager", "#unexcepted- invoke method error:" + th);
                return null;
            }
        } else if (i2 == 1) {
            return jActionExtra.beforLogin(context, i, str);
        } else {
            return null;
        }
    }

    /* renamed from: a */
    public static void m620a(Context context, long j, int i) {
        for (Entry value : f396a.entrySet()) {
            JAction jAction = (JAction) value.getValue();
            if (jAction != null) {
                jAction.onEvent(context, j, i);
            }
        }
    }

    /* renamed from: a */
    public static void m621a(Context context, C0468c cVar, ByteBuffer byteBuffer) {
        if (cVar != null) {
            for (Entry entry : f396a.entrySet()) {
                JAction jAction = (JAction) entry.getValue();
                if (jAction != null && jAction.isSupportedCMD(cVar.mo6588a())) {
                    cVar.mo6591a(Long.valueOf(jAction.dispatchMessage(context, C0419f.f300a.get(), cVar.mo6588a(), cVar, byteBuffer)));
                    C0417d.m446a().mo6441a((String) entry.getKey(), (Object) cVar);
                    byteBuffer.clear();
                }
            }
        }
    }

    /* renamed from: a */
    public static void m622a(Context context, String str, long j, Bundle bundle, Handler handler) {
        JAction jAction = (JAction) f396a.get(str);
        if (jAction != null) {
            jAction.onActionRun(context, j, bundle, handler);
        }
    }

    /* renamed from: a */
    public static void m623a(Context context, String str, Object obj) {
        if (C0530k.m1099a(str)) {
            for (Entry value : f396a.entrySet()) {
                JAction jAction = (JAction) value.getValue();
                C0417d.m446a();
                jAction.handleMessage(context, C0417d.m458g(), obj);
            }
            return;
        }
        JAction jAction2 = (JAction) f396a.get(str);
        if (jAction2 != null) {
            C0417d.m446a();
            jAction2.handleMessage(context, C0417d.m458g(), obj);
        }
    }

    /* renamed from: a */
    public static void m624a(String str, String str2) {
        if (!TextUtils.isEmpty(str2) && !f396a.containsKey(str)) {
            try {
                Object newInstance = Class.forName(str2).newInstance();
                if (newInstance instanceof JAction) {
                    f396a.put(str, (JAction) newInstance);
                }
            } catch (Throwable th) {
                C0501d.m907c("ActionManager", "#unexcepted - instance " + str2 + " class failed:" + th);
            }
        }
    }

    /* renamed from: a */
    public static boolean m625a(int i) {
        for (Entry entry : f397b.entrySet()) {
            JActionExtra jActionExtra = (JActionExtra) entry.getValue();
            if (jActionExtra != null) {
                try {
                    C0501d.m903a("ActionManager", "isAllowAction actionType:" + 0 + ",sdktype:" + ((String) entry.getKey()) + ",action:" + jActionExtra.checkAction(0));
                    if (!jActionExtra.checkAction(0)) {
                        return false;
                    }
                } catch (Throwable th) {
                }
            }
        }
        return true;
    }

    /* renamed from: b */
    public static void m626b(Context context, long j, int i) {
        for (Entry value : f396a.entrySet()) {
            JAction jAction = (JAction) value.getValue();
            if (jAction != null) {
                jAction.dispatchTimeOutMessage(context, C0419f.f300a.get(), j, i);
            }
        }
    }

    /* renamed from: b */
    public static void m627b(String str, String str2) {
        if (!f397b.containsKey(str)) {
            try {
                Object newInstance = Class.forName(str2).newInstance();
                if (newInstance instanceof JActionExtra) {
                    f397b.put(str, (JActionExtra) newInstance);
                }
            } catch (Throwable th) {
                C0501d.m907c("ActionManager", "#unexcepted - instance " + str2 + " class failed:" + th);
            }
        }
    }

    /* renamed from: c */
    public static IBinder m628c(String str, String str2) {
        JAction jAction = (JAction) f396a.get(str);
        if (jAction != null) {
            return jAction.getBinderByType(str2);
        }
        return null;
    }

    /* renamed from: d */
    public static String m629d(String str, String str2) {
        if (!f396a.containsKey(str)) {
            return str2;
        }
        JAction jAction = (JAction) f396a.get(str);
        return (jAction == null || TextUtils.isEmpty(jAction.getSdkVersion())) ? str2 : jAction.getSdkVersion();
    }

    /* renamed from: a */
    public final ArrayList<Object> mo6552a(Context context, String str, int i, String str2, int i2) {
        ArrayList<Object> arrayList = new ArrayList<>();
        if (C0530k.m1099a(str)) {
            for (Entry value : f397b.entrySet()) {
                Object a = m619a((JActionExtra) value.getValue(), context, 20, str2, 1);
                if (a != null) {
                    arrayList.add(a);
                }
            }
        } else {
            Object a2 = m619a((JActionExtra) f397b.get(str), context, 20, str2, 1);
            if (a2 != null) {
                arrayList.add(a2);
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    public final boolean mo6553a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return false;
        }
        try {
            jSONObject.put("sdk_ver", m629d(SdkType.JPUSH.name(), ""));
            jSONObject.put("core_sdk_ver", m629d(SdkType.JCORE.name(), ""));
            jSONObject.put("share_sdk_ver", m629d(SdkType.JSHARE.name(), ""));
            jSONObject.put("statistics_sdk_ver", m629d(SdkType.JANALYTICS.name(), ""));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return true;
    }
}
