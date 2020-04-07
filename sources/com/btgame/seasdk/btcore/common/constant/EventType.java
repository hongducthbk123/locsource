package com.btgame.seasdk.btcore.common.constant;

public enum EventType {
    INIT_REQ("INIT_RES"),
    INIT_RES("INIT_RES"),
    CHECK_PERMISSIONS_REQ("CHECK_PERMISSIONS_REQ"),
    CHECK_PERMISSIONS_RES("CHECK_PERMISSIONS_RES"),
    LOGIN_REQ("LOGIN_REQ"),
    LOGIN_RES("LOGIN_RES"),
    LOGOUT_REQ("LOGOUT_REQ"),
    LOGOUT_RES("LOGOUT_RES"),
    EXIT("EXIT"),
    PAY_REQ("PAY_REQ"),
    PAY_RES("PAY_RES"),
    SHARE_REQ("SHARE_REQ"),
    SHARE_RES("SHARE_RES");
    
    private String typeName;

    private EventType(String typeName2) {
        this.typeName = typeName2;
    }

    public String getTypeName() {
        return this.typeName;
    }
}
