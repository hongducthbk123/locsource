package p004cn.jpush.android.p040d;

import android.text.TextUtils;
import java.util.ArrayList;
import org.json.JSONArray;

/* renamed from: cn.jpush.android.d.b */
public final class C0578b {
    /* renamed from: a */
    public static ArrayList<String> m1293a(JSONArray jSONArray) {
        ArrayList arrayList = new ArrayList();
        if (jSONArray == null || jSONArray.length() == 0) {
            return arrayList;
        }
        for (int i = 0; i < jSONArray.length(); i++) {
            String optString = jSONArray.optString(i);
            if (!TextUtils.isEmpty(optString)) {
                arrayList.add(optString);
            }
        }
        return arrayList;
    }
}
