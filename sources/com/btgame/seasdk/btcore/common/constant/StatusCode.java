package com.btgame.seasdk.btcore.common.constant;

public enum StatusCode {
    LOGIN_SUCCESS(0),
    LOGIN_CANCEL(1),
    LOGIN_FAIL(2),
    LOGIN_REAUTH(5002),
    LOGOUT_SUCCESS(3),
    LOGOUT_FAIL(4),
    PAY_CREATRORDER_FAIL(5),
    PAY_CREATRORDER_SUCCESS(6),
    PAY_NOTIFYSERVER_FAIL(7),
    PAY_NOTIFYSERVER_SUCCESS(8),
    PAY_SUCCESS(9),
    PAY_CANCEL(10),
    PAY_FAIL(11),
    PAY_FINISH(12),
    SHARE_SUCCESS(13),
    SHARE_CANCEL(14),
    SHARE_FAIL(15),
    INIT_SUCCESS(16),
    INIT_FAIL(17);
    
    private int code;

    private StatusCode(int code2) {
        this.code = -1;
        this.code = code2;
    }

    public int getCode() {
        return this.code;
    }
}
