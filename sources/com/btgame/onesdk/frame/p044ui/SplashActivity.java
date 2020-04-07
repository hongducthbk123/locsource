package com.btgame.onesdk.frame.p044ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.baitian.datasdk.BtDataSdkManager;
import com.baitian.datasdk.constants.TopicField;
import com.btgame.onesdk.frame.constants.Constants;
import com.btgame.onesdk.frame.utils.AssetsUtil;
import com.btgame.sdk.util.BtsdkLog;
import java.util.List;

/* renamed from: com.btgame.onesdk.frame.ui.SplashActivity */
public class SplashActivity extends Activity {
    public static final int SPLASH_MSG = 18;
    public static final int SPLASH_MSG_DISMISS = 19;
    public static final int SPLASH_MSG_STARTGAME = 20;
    @SuppressLint({"HandlerLeak"})
    Handler splashHandler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == 18) {
                BtsdkLog.m1423d("xs dimss splashlog");
                SplashActivity.this.splashHandler.sendEmptyMessageDelayed(19, 500);
            } else if (msg.what == 19) {
                if (SplashActivity.this.splashdialog.isShowing()) {
                    SplashActivity.this.splashdialog.dismiss();
                    SplashActivity.this.startGame();
                }
            } else if (msg.what == 20) {
                SplashActivity.this.finish();
            }
        }
    };
    /* access modifiers changed from: private */
    public SplashDialog splashdialog;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        int time;
        super.onCreate(savedInstanceState);
        List<String> assetspics = AssetsUtil.getSplashFile(this);
        if (assetspics == null || assetspics.size() <= 0) {
            startGame();
            return;
        }
        try {
            String splashtime = AssetsUtil.getVauleInConfig(this, Constants.SPLASH_TIME_KEY);
            if (splashtime == null || "".equals(splashtime)) {
                time = Constants.SPLASH_TIME;
            } else {
                time = Integer.parseInt(splashtime);
            }
            int totleSplashtime = time * assetspics.size();
            this.splashdialog = new SplashDialog(this, assetspics, (long) time);
            BtDataSdkManager.getInstance(this).sumbitBaseData(7, TopicField.TOPICINAME_SPLASH);
            this.splashdialog.show();
            this.splashHandler.sendEmptyMessageDelayed(19, (long) totleSplashtime);
        } catch (Exception e) {
            e.printStackTrace();
            startGame();
        }
    }

    /* access modifiers changed from: private */
    public void startGame() {
        Intent intent = new Intent("com.baitian.sdk.MAIN");
        intent.setPackage(getPackageName());
        startActivity(intent);
        this.splashHandler.sendEmptyMessageDelayed(20, 500);
    }

    public void onBackPressed() {
    }
}
