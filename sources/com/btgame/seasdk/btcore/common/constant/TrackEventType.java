package com.btgame.seasdk.btcore.common.constant;

public enum TrackEventType {
    UB_LOGIN_FAILED("500138", "帐号登录失败", "UB账号登录失败（非首次注册用户），需记录失败码"),
    UB_LOGIN_SUCCESS("500137", "帐号登录成功", "UB账号登录成功（非首次注册用户）"),
    UB_SIGN_IN("500136", "帐号登录页面成功打开", "成功打开UB账号登录页面"),
    UB_REGISTER_SUCCESS("500135", "注册成功", "通过注册流程成功注册，即成功在账号系统创建了一个账号"),
    SIGN_UP("500134", "注册页面成功打开", "注册页面成功打开"),
    GP_LOGIN_SUCCESS("500133", "GP账号登录成功", "GP授权账号登录成功"),
    GP_AUTO_LOGIN("500132", "GP账号进入自动登录", "GP授权账号成功打开自动登录页面"),
    GP_AUTHORIZE_SUCCESS("500131", "GP成功授权", "设备成功接收到了授权成功信息"),
    GP_AUTHORIZE_FAILED("500130", "GP授权失败", "授权请求没有成功，需记录失败码"),
    GP_REQUEST_AUTHORIZATION("500129", "请求GP授权", "设备向GP发送授权请求"),
    FB_LOGIN_SUCCESS("500128", "FB账号登录成功", "FB授权账号登录成功"),
    FB_AUTO_LOGIN("500127", "FB账号进入自动登录", "FB授权账号成功打开自动登录页面"),
    FB_AUTHORIZE_SUCCESS("500126", "FB成功授权", "设备成功接收到了授权成功信息"),
    FB_AUTHORIZE_FAILED("500125", "FB授权失败", "授权请求没有成功，需记录失败码"),
    FB_REQUEST_AUTHORIZATION("500124", "请求FB授权", "设备向FB发送授权请求"),
    CHECK_PERMISSION_PASS("500217", "通过权限检查", "权限检查成功通过"),
    CHECK_PERMISSION_FAIL("500218", "权限检查失败", "权限检查失败"),
    SIGN_IN("500123", "登录首页成功打开", "登录首页成功打开"),
    SDK_INIT_SUCCESS("500122", "SDK初始化成功", "设备收到SDK初始化成功信息"),
    SDK_INIT_START("500121", "SDK开始初始化", "设备向服务器发送SDK初始化请求"),
    RATE_CANCEL("500140", "不前往商店评价", "用户在前往商店评价的提示框中按了取消"),
    RATE_CONFIRM("500139", "前往商店评价", "用户前往应用商店去评价");
    
    public String eventDescription;
    public String eventId;
    public String eventName;

    private TrackEventType(String eventId2, String eventName2, String eventDescription2) {
        this.eventId = eventId2;
        this.eventName = eventName2;
        this.eventDescription = eventDescription2;
    }
}
