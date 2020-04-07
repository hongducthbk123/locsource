package com.btgame.onesdk.frame.listener;

import com.btgame.onesdk.frame.eneity.onesdk.LoginReusltInfo;

public interface OnSDKListener {
    void onExit(int i);

    void onInit(int i);

    void onLogin(LoginReusltInfo loginReusltInfo, int i);

    void onLogout(int i);

    void onPay(int i);
}
