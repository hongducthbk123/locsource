package p004cn.jpush.android.api;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.Notification.Builder;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p004cn.jiguang.api.JCoreInterface;
import p004cn.jpush.android.C0541a;
import p004cn.jpush.android.p040d.C0582e;
import p004cn.jpush.android.p043ui.PopWinActivity;
import p004cn.jpush.android.service.PushReceiver;

/* renamed from: cn.jpush.android.api.MultiActionsNotificationBuilder */
public class MultiActionsNotificationBuilder extends DefaultPushNotificationBuilder {
    private static final String NOTI_ACT_EXTRA_STR = "notification_action_extra_string";
    private static final String NOTI_ACT_RES_ID = "notification_action_res_id";
    private static final String NOTI_ACT_TEXT = "notification_action_text";
    private static final String TAG = "MultiActionsNotificationBuilder";
    private JSONArray actionJSONArray = new JSONArray();
    protected Context mContext;

    public MultiActionsNotificationBuilder(Context context) {
        this.mContext = context;
    }

    public void addJPushAction(int i, String str, String str2) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(NOTI_ACT_RES_ID, i);
            jSONObject.put(NOTI_ACT_TEXT, str);
            jSONObject.put(NOTI_ACT_EXTRA_STR, str2);
            this.actionJSONArray.put(jSONObject);
        } catch (JSONException e) {
            C0582e.m1306c(TAG, "Construct action failed!");
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: 0000 */
    @TargetApi(11)
    public Notification getNotification(Builder builder) {
        PendingIntent broadcast;
        if (VERSION.SDK_INT < 16) {
            return builder.getNotification();
        }
        for (int i = 0; i < this.actionJSONArray.length(); i++) {
            try {
                JSONObject jSONObject = this.actionJSONArray.getJSONObject(i);
                Intent intent = new Intent("cn.jpush.android.intent.NOTIFICATION_CLICK_ACTION_PROXY");
                intent.putExtra(JPushInterface.EXTRA_NOTIFICATION_ACTION_EXTRA, jSONObject.getString(NOTI_ACT_EXTRA_STR));
                if (JCoreInterface.getRuningFlag()) {
                    intent.setClass(this.mContext, PopWinActivity.class);
                    intent.putExtra("isNotification", true);
                    broadcast = PendingIntent.getActivity(this.mContext, i, intent, 134217728);
                } else {
                    intent.setClass(this.mContext, PushReceiver.class);
                    broadcast = PendingIntent.getBroadcast(this.mContext, i, intent, 134217728);
                }
                builder.addAction(jSONObject.getInt(NOTI_ACT_RES_ID), jSONObject.getString(NOTI_ACT_TEXT), broadcast).setAutoCancel(true);
            } catch (JSONException e) {
                C0582e.m1306c(TAG, "Parse Action from preference preference failed!");
                e.printStackTrace();
            }
        }
        return builder.build();
    }

    static PushNotificationBuilder parseFromPreference(String str) {
        MultiActionsNotificationBuilder multiActionsNotificationBuilder = new MultiActionsNotificationBuilder(C0541a.f653e);
        try {
            multiActionsNotificationBuilder.actionJSONArray = new JSONArray(str);
        } catch (JSONException e) {
            C0582e.m1306c(TAG, "Parse builder from preference failed!");
            e.printStackTrace();
        }
        return multiActionsNotificationBuilder;
    }

    public String toString() {
        return this.actionJSONArray.toString();
    }
}
