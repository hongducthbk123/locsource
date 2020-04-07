package com.btgame.seasdk.task.entity.response;

public class OrderResultData {
    private String extArgs;
    private String orderId;
    private String payChannelId;
    private String redirectUrl;

    public String getOrderId() {
        return this.orderId;
    }

    public String getPayChannelId() {
        return this.payChannelId;
    }

    public String getRedirectUrl() {
        return this.redirectUrl;
    }

    public String getExtArgs() {
        return this.extArgs;
    }
}
