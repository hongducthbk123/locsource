package com.btgame.seasdk.task.entity.response;

public class CreateOrderResult {
    private OrderResultData data;
    private PayBaseResult res;

    public PayBaseResult getRes() {
        return this.res;
    }

    public void setRes(PayBaseResult res2) {
        this.res = res2;
    }

    public OrderResultData getData() {
        return this.data;
    }

    public void setData(OrderResultData data2) {
        this.data = data2;
    }
}
