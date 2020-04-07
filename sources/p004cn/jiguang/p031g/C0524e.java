package p004cn.jiguang.p031g;

import android.text.TextUtils;
import android.util.Log;
import com.btgame.google.constant.GpConfig;
import com.facebook.appevents.AppEventsConstants;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: cn.jiguang.g.e */
public final class C0524e {

    /* renamed from: e */
    private static final Pattern f599e = Pattern.compile("^[A-Za-z][A-Za-z0-9_]*[.]+([A-Za-z][A-Za-z0-9_:.]*)*$");

    /* renamed from: f */
    private static final Pattern f600f = Pattern.compile("^zygote[0-9]*$");

    /* renamed from: a */
    public String f601a;

    /* renamed from: b */
    private String f602b;
    /* access modifiers changed from: private */

    /* renamed from: c */
    public String f603c;
    /* access modifiers changed from: private */

    /* renamed from: d */
    public String f604d;

    /* renamed from: a */
    private static int m1075a(LinkedList<String> linkedList, String str, int i) {
        int indexOf = linkedList.indexOf(str);
        if (indexOf == -1) {
            indexOf = linkedList.indexOf(str.toLowerCase(Locale.ENGLISH));
        }
        return indexOf == -1 ? i : indexOf;
    }

    /* renamed from: a */
    public static C0524e m1076a(String str, Map<String, Integer> map) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String[] split = str.split("\\s+");
        int length = split.length;
        if (length < 3) {
            return null;
        }
        try {
            C0524e eVar = new C0524e();
            if (map == null || map.isEmpty()) {
                eVar.f602b = split[0];
                eVar.f603c = split[1];
                eVar.f604d = split[2];
                eVar.f601a = split[length - 1];
            } else {
                eVar.f602b = split[((Integer) map.get("USER")).intValue()];
                eVar.f603c = split[((Integer) map.get("PID")).intValue()];
                eVar.f604d = split[((Integer) map.get("PPID")).intValue()];
                eVar.f601a = split[((Integer) map.get("NAME")).intValue()];
            }
            return eVar;
        } catch (Throwable th) {
            Log.w("AppStatUtils", "parse ps printString err, " + th.getMessage());
            return null;
        }
    }

    /* renamed from: a */
    public static Map<String, Integer> m1078a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        LinkedList linkedList = new LinkedList();
        Collections.addAll(linkedList, str.split("\\s+"));
        int size = linkedList.size() - 1;
        HashMap hashMap = new HashMap();
        int a = m1075a(linkedList, "USER", 0);
        int a2 = m1075a(linkedList, "PID", 1);
        int a3 = m1075a(linkedList, "PPID", 2);
        int a4 = m1075a(linkedList, "NAME", size);
        if (a == 0 && a2 == 1 && a3 == 2 && a4 == size) {
            return null;
        }
        return hashMap;
    }

    /* renamed from: a */
    public final JSONObject mo6698a(int i) {
        try {
            return new JSONObject().put("uid", this.f602b).put("pid", this.f603c).put("ppid", this.f604d).put("proc_name", C0530k.m1098a((CharSequence) this.f601a, 128));
        } catch (JSONException e) {
            return null;
        }
    }

    /* renamed from: a */
    public final boolean mo6699a() {
        return this.f604d.equals(AppEventsConstants.EVENT_PARAM_VALUE_NO) || this.f604d.equals("1") || this.f604d.equals(GpConfig.GPTHIRDID);
    }

    /* renamed from: b */
    public final boolean mo6700b() {
        return f600f.matcher(this.f601a).matches();
    }

    public final String toString() {
        return "ProcessInfo{user='" + this.f602b + '\'' + ", pid='" + this.f603c + '\'' + ", ppid='" + this.f604d + '\'' + ", procName='" + this.f601a + '\'' + '}';
    }
}
