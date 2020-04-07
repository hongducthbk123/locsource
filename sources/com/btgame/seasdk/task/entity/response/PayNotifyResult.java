package com.btgame.seasdk.task.entity.response;

public class PayNotifyResult {
    private PayNotifyResultData data;
    private PayBaseResult res;

    public static final class Builder {
        /* access modifiers changed from: private */
        public PayNotifyResultData data;
        /* access modifiers changed from: private */
        public PayBaseResult res;

        public Builder res(PayBaseResult val) {
            this.res = val;
            return this;
        }

        public Builder data(PayNotifyResultData val) {
            this.data = val;
            return this;
        }

        public PayNotifyResult build() {
            return new PayNotifyResult(this);
        }
    }

    private PayNotifyResult(Builder builder) {
        this.res = builder.res;
        this.data = builder.data;
    }

    public PayBaseResult getRes() {
        return this.res;
    }

    public PayNotifyResultData getData() {
        return this.data;
    }
}
