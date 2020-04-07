package com.btgame.seasdk.task.entity.response;

public class OpResult {
    protected BaseResult res;

    public OpResult() {
    }

    public OpResult(BaseResult res2) {
        this.res = res2;
    }

    public BaseResult getRes() {
        return this.res;
    }

    public void setRes(BaseResult res2) {
        this.res = res2;
    }
}
