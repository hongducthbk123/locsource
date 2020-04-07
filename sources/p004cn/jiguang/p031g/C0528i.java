package p004cn.jiguang.p031g;

import android.content.Context;
import android.text.TextUtils;
import android.util.SparseArray;
import com.btgame.googlepay.util.IabHelper;
import com.facebook.internal.NativeProtocol;
import org.json.JSONException;
import org.json.JSONObject;
import p004cn.jiguang.api.utils.ByteBufferUtils;
import p004cn.jiguang.p015d.p021d.C0460q;

/* renamed from: cn.jiguang.g.i */
public final class C0528i {

    /* renamed from: a */
    private static final SparseArray<String> f608a;

    /* renamed from: b */
    private static long f609b = 0;

    static {
        SparseArray<String> sparseArray = new SparseArray<>();
        f608a = sparseArray;
        sparseArray.put(0, "OK");
        f608a.put(IabHelper.IABHELPER_REMOTE_EXCEPTION, "Exceed buffer size. Please contact support.");
        f608a.put(-1000, "Connection failed. Please check your connection and retry later!");
        f608a.put(-998, "Sending failed or timeout. Please Retry later!");
        f608a.put(-997, "Receiving failed or timeout. Please Retry later!");
        f608a.put(-996, "Connection is closed. Please Retry later!");
        f608a.put(-994, "Response timeout. Please Retry later!");
        f608a.put(-993, "Invalid socket. Please Retry later!");
        f608a.put(11, "Failed to register!");
        f608a.put(1005, "Your appKey and android package name are not matched. Please double check them according to Application you created on Portal.");
        f608a.put(1006, "You android package name is not exist, Please register your pacakge name in Portal.");
        f608a.put(1007, "Invalid Imei, Register again.");
        f608a.put(1008, "Invalid appKey, Please get your Appkey from JIGUANG web console!");
        f608a.put(1009, "Your appKey is related to a non-Android App.Please use your Android App's appKey, or add an Android app for this appKey.");
        f608a.put(ByteBufferUtils.ERROR_CODE, "Receiver data parse error");
        f608a.put(102, "102 - Incorrect password");
        f608a.put(108, "108 - Incorrect uid");
        f608a.put(1012, "Server reject this connection, will clear cache and restart connect.");
    }

    /* renamed from: a */
    public static String m1094a(int i) {
        if (f608a.get(i) == null) {
            return null;
        }
        return (String) f608a.get(i);
    }

    /* renamed from: a */
    public static JSONObject m1095a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(NativeProtocol.WEB_DIALOG_ACTION, "rmv");
            jSONObject.put("appid", str);
            C0460q.m707a((Context) null, jSONObject, "app_add_rmv");
            return jSONObject;
        } catch (JSONException e) {
            return null;
        }
    }

    /* renamed from: a */
    public static JSONObject m1096a(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(NativeProtocol.WEB_DIALOG_ACTION, "add");
            jSONObject.put("appid", str);
            C0460q.m707a((Context) null, jSONObject, "app_add_rmv");
            jSONObject.put("install_type", i);
            return jSONObject;
        } catch (JSONException e) {
            return null;
        }
    }
}
