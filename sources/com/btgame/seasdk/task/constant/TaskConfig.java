package com.btgame.seasdk.task.constant;

import android.content.Context;
import android.text.TextUtils;
import com.btgame.seasdk.btcore.common.entity.DeviceProperties;
import com.btgame.seasdk.btcore.common.util.BTResourceUtil;
import com.btgame.seasdk.btcore.common.util.BtsdkLog;
import com.btgame.seasdk.btcore.common.util.DebugUtils;

public class TaskConfig {
    public static final String LOGIN_BT_FLAG = "BT";
    public static final String LOGIN_GUEST_FLAG = "GUEST";
    public static final String LOGIN_SP_GUEST_ACCOUNT = "LOGIN_SP_GUEST_ACCOUNT";
    public static final String LOGIN_SP_GUEST_IDENTITY = "LOGIN_SP_GUEST_IDENTITY";
    public static final int PAY_GOOGLE_CHANNELID = 400;
    public static Integer PAY_PACKAGEID = null;
    public static final String URL_ACCOUNTLOGIN = "/login/login";
    public static final String URL_AUTOLOGIN = "/login/autoLogin";
    public static String URL_BASE_ACCOUNT = null;
    public static String URL_BASE_PAY = null;
    public static final String URL_BINDMAIL = "/bind/mailcheck";
    public static final String URL_CHPW = "/pw/chpw";
    public static final String URL_FPWD_EMAIL = "/pw/fpw/email";
    public static final String URL_GOOGLE_CREATEORDER = "/recharge/addOrder.json";
    public static final String URL_GOOGLE_NOTIFY = "/payment/notify/google";
    public static final String URL_INIT = "/index/index";
    public static final String URL_MAILCODE = "/bind/mail";
    public static final String URL_PARAM_DEBUG_PREFIX = "?debug=1&lang=";
    public static final String URL_PARAM_PREFIX = "?lang=";
    public static final String URL_REGISTER = "/login/register";
    public static final String URL_THIRD_PART_LOGIN = "/third-part/login";
    public static final String URL_VISTORAUTO = "/login/vistorauto";

    public static void initConfig(Context context) {
        URL_BASE_ACCOUNT = BTResourceUtil.findStringByName(context, "url_server_base");
        URL_BASE_PAY = BTResourceUtil.findStringByName(context, "url_server_base_pay");
        PAY_PACKAGEID = BTResourceUtil.findIntegerByName(context, "pay_packageid");
        if (TextUtils.isEmpty(URL_BASE_ACCOUNT)) {
            BtsdkLog.m1430e("请在strings里面配置url_server_base");
        }
        if (TextUtils.isEmpty(URL_BASE_PAY)) {
            BtsdkLog.m1430e("请在strings里面配置url_server_base_pay");
        }
        if (PAY_PACKAGEID == null) {
            BtsdkLog.m1430e("请在strings里面配置pay_packageid");
        }
    }

    public static String buildUrl(String url, DeviceProperties device) {
        if (DebugUtils.getInstance().isCodeFlag()) {
            return URL_BASE_ACCOUNT + url + URL_PARAM_PREFIX + device.lang;
        }
        return URL_BASE_ACCOUNT + url + URL_PARAM_DEBUG_PREFIX + device.lang;
    }

    public static String buildPayUrl(String url, DeviceProperties device) {
        if (DebugUtils.getInstance().isCodeFlag()) {
            return URL_BASE_PAY + url + URL_PARAM_PREFIX + device.lang;
        }
        return URL_BASE_PAY + url + URL_PARAM_DEBUG_PREFIX + device.lang;
    }
}
