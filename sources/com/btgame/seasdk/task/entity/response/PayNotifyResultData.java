package com.btgame.seasdk.task.entity.response;

public class PayNotifyResultData {
    private String appId;
    private String orderId;
    private String useId;

    public static final class Builder {
        /* access modifiers changed from: private */
        public String appId;
        /* access modifiers changed from: private */
        public String orderId;
        /* access modifiers changed from: private */
        public String useId;

        public Builder appId(String val) {
            this.appId = val;
            return this;
        }

        public Builder useId(String val) {
            this.useId = val;
            return this;
        }

        public Builder orderId(String val) {
            this.orderId = val;
            return this;
        }

        public PayNotifyResultData build() {
            return new PayNotifyResultData(this);
        }
    }

    private PayNotifyResultData(Builder builder) {
        this.appId = builder.appId;
        this.useId = builder.useId;
        this.orderId = builder.orderId;
    }

    public String getAppId() {
        return this.appId;
    }

    public String getUseId() {
        return this.useId;
    }

    public String getOrderId() {
        return this.orderId;
    }
}
