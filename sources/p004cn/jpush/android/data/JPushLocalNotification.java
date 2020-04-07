package p004cn.jpush.android.data;

import android.text.TextUtils;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;
import p004cn.jpush.android.p040d.C0582e;

/* renamed from: cn.jpush.android.data.JPushLocalNotification */
public class JPushLocalNotification implements Serializable {
    private static final long serialVersionUID = 1472982106750878137L;

    /* renamed from: a */
    private int f779a = 1;

    /* renamed from: b */
    private String f780b = "";

    /* renamed from: c */
    private String f781c = "00";

    /* renamed from: d */
    private String f782d = "00";

    /* renamed from: e */
    private long f783e = 0;

    /* renamed from: f */
    private String f784f;

    /* renamed from: g */
    private String f785g;

    /* renamed from: h */
    private String f786h;

    /* renamed from: i */
    private long f787i;

    /* renamed from: j */
    private long f788j = 1;

    /* renamed from: k */
    private int f789k = 1;

    /* renamed from: l */
    private String f790l = "";

    /* renamed from: m */
    private String f791m = "";

    public String toJSON() {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject();
            if (!TextUtils.isEmpty(this.f786h)) {
                jSONObject2.put("n_extras", new JSONObject(this.f786h));
            }
            m1320a("n_content", this.f784f, jSONObject2);
            m1320a("n_title", this.f785g, jSONObject2);
            m1320a("n_content", this.f784f, jSONObject2);
            jSONObject2.put("ad_t", 0);
            jSONObject.put("m_content", jSONObject2);
            m1320a("msg_id", this.f788j, jSONObject);
            m1320a("content_type", this.f791m, jSONObject);
            m1320a("override_msg_id", this.f790l, jSONObject);
            jSONObject.put("n_only", this.f789k);
            jSONObject.put("n_builder_id", this.f787i);
            jSONObject.put("show_type", 3);
            jSONObject.put("notificaion_type", 1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject.toString();
    }

    /* renamed from: a */
    private static void m1320a(String str, String str2, JSONObject jSONObject) throws JSONException {
        if (!TextUtils.isEmpty(str2)) {
            jSONObject.put(str, str2);
        }
    }

    public void setNotificationId(long j) {
        this.f788j = (long) ((int) j);
    }

    public long getNotificationId() {
        return this.f788j;
    }

    public void setBroadcastTime(long j) {
        this.f783e = j;
    }

    public void setBroadcastTime(Date date) {
        this.f783e = date.getTime();
    }

    public void setBroadcastTime(int i, int i2, int i3, int i4, int i5, int i6) {
        if (i < 0 || i2 <= 0 || i2 > 12 || i3 <= 0 || i3 > 31 || i4 < 0 || i4 > 23 || i5 < 0 || i5 > 59 || i6 < 0 || i6 > 59) {
            C0582e.m1308d("JPushLocalNotification", "Set time fail! Please check your args!");
            return;
        }
        Calendar instance = Calendar.getInstance();
        instance.set(i, i2 - 1, i3, i4, i5, i6);
        Date time = instance.getTime();
        long currentTimeMillis = System.currentTimeMillis();
        if (time.getTime() < currentTimeMillis) {
            this.f783e = currentTimeMillis;
        } else {
            this.f783e = time.getTime();
        }
    }

    public long getBroadcastTime() {
        return this.f783e;
    }

    public void setExtras(String str) {
        this.f786h = str;
    }

    public String getExtras() {
        return this.f786h;
    }

    public String getTitle() {
        return this.f785g;
    }

    public void setTitle(String str) {
        this.f785g = str;
    }

    public String getContent() {
        return this.f784f;
    }

    public void setContent(String str) {
        this.f784f = str;
    }

    public long getBuilderId() {
        return this.f787i;
    }

    public void setBuilderId(long j) {
        this.f787i = j;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        if (this.f788j != ((JPushLocalNotification) obj).f788j) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (int) (this.f788j ^ (this.f788j >>> 32));
    }
}
