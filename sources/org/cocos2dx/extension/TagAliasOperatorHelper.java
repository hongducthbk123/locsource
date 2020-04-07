package org.cocos2dx.extension;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.google.android.vending.expansion.downloader.Constants;
import p004cn.jpush.android.api.JPushInterface;
import p004cn.jpush.android.api.JPushMessage;

public class TagAliasOperatorHelper {
    private static final String JPUSHALIAS = "JPUSHALIAS";
    private static final String JPUSHPreferences = "JPUSHPreferences";
    private static final int MSG_SET_ALIAS = 1001;
    public static final int RETRY_MAX = 3;
    private static final String TAG = "JIGUANG-TagAliasHelper";
    private static TagAliasOperatorHelper mInstance;
    private int aliasRetryNum = 0;
    /* access modifiers changed from: private */
    public Context context;
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1001:
                    JPushInterface.setAlias((Context) (Activity) TagAliasOperatorHelper.this.context, 1, (String) msg.obj);
                    return;
                default:
                    Log.i(TagAliasOperatorHelper.TAG, "Unhandled msg - " + msg.what);
                    return;
            }
        }
    };

    private TagAliasOperatorHelper() {
    }

    public static TagAliasOperatorHelper getInstance() {
        if (mInstance == null) {
            synchronized (TagAliasOperatorHelper.class) {
                if (mInstance == null) {
                    mInstance = new TagAliasOperatorHelper();
                }
            }
        }
        return mInstance;
    }

    public void init(Context context2) {
        if (context2 != null) {
            this.context = context2;
        }
    }

    public void handleSetAlias(Context context2, int sequence, String alias) {
        init(context2);
        if (!context2.getSharedPreferences(JPUSHPreferences, 0).getString(JPUSHALIAS, "impossibleAlias").equals(alias)) {
            this.mHandler.sendMessage(this.mHandler.obtainMessage(1001, alias));
        }
    }

    private boolean retryActionIfNeeded(int errorCode, String alias) {
        if (errorCode != 6002 && errorCode != 6014 && 3 >= this.aliasRetryNum) {
            return false;
        }
        this.aliasRetryNum++;
        Message message = new Message();
        message.what = 1001;
        message.obj = alias;
        this.mHandler.sendMessageDelayed(message, Constants.WATCHDOG_WAKE_TIMER);
        Log.d(TAG, "need retry");
        return true;
    }

    public void onAliasOperatorResult(Context context2, JPushMessage jPushMessage) {
        if (jPushMessage.getErrorCode() == 0) {
            Editor editor = context2.getSharedPreferences(JPUSHPreferences, 0).edit();
            editor.putString(JPUSHALIAS, jPushMessage.getAlias());
            editor.commit();
            Log.d(TAG, "setAlias is success" + jPushMessage.getAlias());
            return;
        }
        int errorCode = jPushMessage.getErrorCode();
        Log.e(TAG, "Failed to setAlias, errorCode:" + errorCode);
        retryActionIfNeeded(errorCode, jPushMessage.getAlias());
    }
}
