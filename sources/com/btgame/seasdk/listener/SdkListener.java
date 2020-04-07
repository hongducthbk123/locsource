package com.btgame.seasdk.listener;

public interface SdkListener {
    void onExit(int i);

    void onInitFail(int i, String str);

    void onInitSuccess();

    void onLogin(String str, String str2, String str3);

    void onLoginFail();

    void onLogoutFail();

    void onLogoutSuccess();

    void onPayFail(String str, String str2);

    void onPaySuccess(String str);

    void onShareCancel();

    void onShareError();

    void onShareSuccess();
}
