package com.btgame.seasdk.task.entity;

public enum OpType {
    OP_LOGIN_BT(1),
    OP_LOGIN_GUEST(2),
    OP_LOGIN_THIRD(3),
    OP_CHANGEPWD(4),
    OP_RETRIEVEPWD(5),
    OP_GETMAILCODE(6),
    OP_BINDMAIL(7),
    OP_INIT(8);
    
    private int type;

    private OpType(int type2) {
        this.type = type2;
    }
}
