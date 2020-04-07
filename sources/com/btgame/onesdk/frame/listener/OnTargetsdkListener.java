package com.btgame.onesdk.frame.listener;

import com.btgame.onesdk.frame.eneity.Args;
import com.btgame.onesdk.frame.eneity.SdkLoginCallBack;

public interface OnTargetsdkListener {
    void onExitFail(String str, int i);

    void onExitSuccess();

    void onInitFail(String str, int i);

    void onInitSuccess();

    void onLoginFail(String str, int i);

    void onLoginSuccess(SdkLoginCallBack sdkLoginCallBack, Args args);

    void onLogoutFail(String str, int i);

    void onLogoutSuccess();

    void onPayFail(String str, int i);

    void onPaySuccess(String str);
}
