package com.btgame.seasdk.task.entity.response;

import com.btgame.seasdk.task.entity.OpType;

public class BaseResult {
    protected int code;
    protected String msg;
    protected OpType opType;

    public BaseResult(OpType opType2, int code2, String msg2) {
        this.opType = opType2;
        this.code = code2;
        this.msg = msg2;
    }

    public OpType getOpType() {
        return this.opType;
    }

    public void setOpType(OpType opType2) {
        this.opType = opType2;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code2) {
        this.code = code2;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg2) {
        this.msg = msg2;
    }
}
