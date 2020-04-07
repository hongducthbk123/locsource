package com.btgame.onesdk.frame.constants;

import com.btgame.sdk.util.DebugUtils;

public class Constants {
    public static final String ASSETS_FILE_NAME = "btgame";
    public static final String ERROR_MSG_NO_LOGIN = "该用户尚未登录";
    public static final String ERROR_MSG_NO_THIS_INTERFACE = "该接口没有实现";
    public static final String SPLASH_FLAG = "bt_splash";
    public static final int SPLASH_TIME = 2500;
    public static final String SPLASH_TIME_KEY = "splashtime";
    public static final String TEXT_LOADING_INIT_BEGIN = "";
    public static final String TEXT_LOADING_LOGIN_BEGIN = "登录中...";
    public static final String TEXT_LOADING_PAY_BEGIN = "";
    public static final String TEXT_LOADING_TOKEN_CHECKING = "验证中...";
    private static final String URL_COPYRIGHTCODE = "http://oneserver.online.100bt.com:8099/";
    private static final String URL_RELEASE = "https://pe-oneserver.100bt.com";
    private static final String URL_SEREV = "https://te-oneserver.100bt.com";
    private static final String URL_TEST_SEREV = "http://10.18.6.142:8080";
    public static String URL_YANGCAO_SERVER = URL_TEST_SEREV;
    public static Constants instance;

    private Constants() {
    }

    public static Constants getInstance() {
        if (instance == null) {
            instance = new Constants();
        }
        return instance;
    }

    public String getURL() {
        switch (DebugUtils.getInstance().getRunningType()) {
            case 1:
            case 2:
                return URL_TEST_SEREV;
            case 3:
                return URL_SEREV;
            case 4:
                return URL_YANGCAO_SERVER;
            case 6:
                return URL_COPYRIGHTCODE;
            default:
                return URL_RELEASE;
        }
    }

    public void destory() {
        instance = null;
    }
}
