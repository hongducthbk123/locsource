package p004cn.jpush.android.p037a;

import android.content.Context;
import android.text.TextUtils;
import com.google.android.vending.expansion.downloader.Constants;
import org.json.JSONException;
import org.json.JSONObject;
import p004cn.jiguang.api.JCoreInterface;
import p004cn.jpush.android.C0541a;

/* renamed from: cn.jpush.android.a.d */
public final class C0546d {
    /* renamed from: a */
    public static void m1124a(String str, int i, String str2, Context context) {
        if (JCoreInterface.isValidRegistered() && context != null) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("action:reportActionResult - messageId: " + str + ", code: " + i + Constants.FILENAME_SEQUENCE_SEPARATOR + C0554j.m1146a(i));
            if (!TextUtils.isEmpty(str2)) {
                stringBuffer.append(" report content: " + str2);
            }
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("msg_id", str);
                jSONObject.put("result", i);
                if (!TextUtils.isEmpty(str2)) {
                    jSONObject.put("data", str2);
                }
                JCoreInterface.fillBaseReport(jSONObject, "msg_status");
                JCoreInterface.reportHttpData(context, jSONObject, C0541a.f649a);
            } catch (JSONException e) {
            }
        }
    }

    /* renamed from: a */
    public static void m1125a(String str, String str2, byte b, int i, Context context) {
        if (JCoreInterface.isValidRegistered() && context != null) {
            new StringBuffer().append("action:reportThirdSDKMsgActionResult - messageId: " + str + ", code: " + i);
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("msg_id", str);
                jSONObject.put("tmsg_id", str2);
                jSONObject.put("result", i);
                jSONObject.put("sdk_type", b);
                JCoreInterface.fillBaseReport(jSONObject, "third_msg_status");
                JCoreInterface.reportHttpData(context, jSONObject, C0541a.f649a);
            } catch (JSONException e) {
            }
        }
    }
}
