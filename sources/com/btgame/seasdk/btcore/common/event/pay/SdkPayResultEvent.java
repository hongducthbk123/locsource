package com.btgame.seasdk.btcore.common.event.pay;

import com.btgame.seasdk.btcore.common.constant.EventType;
import com.btgame.seasdk.btcore.common.constant.StatusCode;
import com.btgame.seasdk.btcore.common.entity.PaymentInfo;
import com.btgame.seasdk.btcore.common.event.SdkEvent;

public class SdkPayResultEvent extends SdkEvent {
    private String btOrderId;
    private String orderData;
    private PaymentInfo paymentInfo;
    private String platform;
    private StatusCode statusCode;
    private String userId;

    public static final class Builder {
        /* access modifiers changed from: private */
        public String btOrderId;
        /* access modifiers changed from: private */
        public String orderData;
        /* access modifiers changed from: private */
        public PaymentInfo paymentInfo;
        /* access modifiers changed from: private */
        public String platform;
        /* access modifiers changed from: private */
        public StatusCode statusCode;
        /* access modifiers changed from: private */
        public String userId;

        public Builder platform(String val) {
            this.platform = val;
            return this;
        }

        public Builder statusCode(StatusCode val) {
            this.statusCode = val;
            return this;
        }

        public Builder userId(String val) {
            this.userId = val;
            return this;
        }

        public Builder paymentInfo(PaymentInfo val) {
            this.paymentInfo = val;
            return this;
        }

        public Builder btOrderId(String val) {
            this.btOrderId = val;
            return this;
        }

        public Builder orderData(String val) {
            this.orderData = val;
            return this;
        }

        public SdkPayResultEvent build() {
            return new SdkPayResultEvent(this);
        }
    }

    private SdkPayResultEvent(Builder builder) {
        setEventType(EventType.PAY_RES);
        this.platform = builder.platform;
        this.statusCode = builder.statusCode;
        this.userId = builder.userId;
        this.paymentInfo = builder.paymentInfo;
        this.btOrderId = builder.btOrderId;
        this.orderData = builder.orderData;
    }

    public String getPlatform() {
        return this.platform;
    }

    public StatusCode getStatusCode() {
        return this.statusCode;
    }

    public String getUserId() {
        return this.userId;
    }

    public PaymentInfo getPaymentInfo() {
        return this.paymentInfo;
    }

    public String getBtOrderId() {
        return this.btOrderId;
    }

    public String getOrderData() {
        return this.orderData;
    }
}
