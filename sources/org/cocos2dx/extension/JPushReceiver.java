package org.cocos2dx.extension;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import java.util.Iterator;
import org.cocos2dx.lua.AppActivity;
import org.json.JSONException;
import org.json.JSONObject;
import p004cn.jpush.android.api.JPushInterface;

public class JPushReceiver extends BroadcastReceiver {
    private static final String TAG = "JPush";

    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        Log.d(TAG, "[MyReceiver] onReceive - " + intent.getAction() + ", extras: " + printBundle(bundle));
        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
            Log.d(TAG, "[MyReceiver] 接收Registration Id : " + bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID));
        } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
            Log.d(TAG, "[MyReceiver] ACTION_MESSAGE_RECEIVED " + bundle.getString(JPushInterface.EXTRA_MESSAGE));
        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
            Log.d(TAG, "[MyReceiver] ACTION_NOTIFICATION_RECEIVED");
            Log.d(TAG, "[MyReceiver] notifactionId: " + bundle.getInt(JPushInterface.EXTRA_NOTIFICATION_ID));
        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
            Log.d(TAG, "[MyReceiver] 用户点击打开了通知");
            Intent i = new Intent(context, AppActivity.class);
            i.putExtras(bundle);
            i.setFlags(335544320);
            context.startActivity(i);
        } else if (JPushInterface.ACTION_RICHPUSH_CALLBACK.equals(intent.getAction())) {
            Log.d(TAG, "[MyReceiver] 用户收到到RICH PUSH CALLBACK: " + bundle.getString(JPushInterface.EXTRA_EXTRA));
        } else if (JPushInterface.ACTION_CONNECTION_CHANGE.equals(intent.getAction())) {
            Log.w(TAG, "[MyReceiver]" + intent.getAction() + " connected state change to " + intent.getBooleanExtra(JPushInterface.EXTRA_CONNECTION_CHANGE, false));
        } else {
            Log.d(TAG, "[MyReceiver] Unhandled intent - " + intent.getAction());
        }
    }

    private static String getExtrInfo(Bundle bundle) {
        String ret = "";
        if (bundle.getString(JPushInterface.EXTRA_EXTRA).isEmpty()) {
            Log.i(TAG, "This message has no Extra data");
            return ret;
        }
        try {
            ret = new JSONObject(bundle.getString(JPushInterface.EXTRA_EXTRA)).optString("extrInfo");
            if (ret == null) {
                ret = "";
            }
        } catch (JSONException e) {
            Log.e(TAG, "Get message extra JSON error!");
        }
        return ret;
    }

    private static String printBundle(Bundle bundle) {
        StringBuilder sb = new StringBuilder();
        for (String key : bundle.keySet()) {
            if (key.equals(JPushInterface.EXTRA_NOTIFICATION_ID)) {
                sb.append("\nkey:" + key + ", value:" + bundle.getInt(key));
            } else if (key.equals(JPushInterface.EXTRA_CONNECTION_CHANGE)) {
                sb.append("\nkey:" + key + ", value:" + bundle.getBoolean(key));
            } else if (!key.equals(JPushInterface.EXTRA_EXTRA)) {
                sb.append("\nkey:" + key + ", value:" + bundle.getString(key));
            } else if (bundle.getString(JPushInterface.EXTRA_EXTRA).isEmpty()) {
                Log.i(TAG, "This message has no Extra data");
            } else {
                try {
                    JSONObject json = new JSONObject(bundle.getString(JPushInterface.EXTRA_EXTRA));
                    Iterator<String> it = json.keys();
                    while (it.hasNext()) {
                        String myKey = ((String) it.next()).toString();
                        sb.append("\nkey:" + key + ", value: [" + myKey + " - " + json.optString(myKey) + "]");
                    }
                } catch (JSONException e) {
                    Log.e(TAG, "Get message extra JSON error!");
                }
            }
        }
        return sb.toString();
    }
}
