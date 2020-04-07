package p004cn.jiguang.p015d.p026g;

import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: cn.jiguang.d.g.h */
public final class C0487h {
    /* renamed from: a */
    public static int m813a(JSONObject jSONObject) {
        if (jSONObject != null) {
            try {
                return jSONObject.toString().getBytes("utf-8").length;
            } catch (UnsupportedEncodingException e) {
            }
        }
        return 0;
    }

    /* renamed from: a */
    public static JSONObject m814a(JSONObject jSONObject, Set<String> set) {
        JSONObject jSONObject2 = new JSONObject();
        if (set == null || set.isEmpty()) {
            return jSONObject2;
        }
        for (String str : set) {
            try {
                jSONObject2.put(str, jSONObject.opt(str));
            } catch (JSONException e) {
            }
        }
        return jSONObject2;
    }

    /* renamed from: a */
    public static void m815a(JSONObject jSONObject, JSONObject jSONObject2) {
        if (jSONObject2 != null && jSONObject2.length() != 0) {
            if (jSONObject == null) {
                jSONObject = new JSONObject();
            }
            Iterator keys = jSONObject2.keys();
            while (keys.hasNext()) {
                String str = (String) keys.next();
                try {
                    jSONObject.put(str, jSONObject2.get(str));
                } catch (JSONException e) {
                }
            }
        }
    }

    /* renamed from: b */
    public static boolean m816b(JSONObject jSONObject) {
        return jSONObject == null || jSONObject.length() == 0;
    }
}
