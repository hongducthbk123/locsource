package com.btgame.seasdk.btcore.common.event.pay;

import com.btgame.seasdk.btcore.common.constant.StatusCode;
import com.btgame.seasdk.btcore.common.entity.PaymentInfo;
import com.btgame.seasdk.btcore.common.entity.RoleInfo;

public class NotifyServerResultEvent {
    private String btOrderId;
    private String des;
    private String orderData;
    private Integer orderStatus;
    private PaymentInfo paymentInfo;
    private String platform;
    private RoleInfo roleInfo;
    private StatusCode statusCode;
    private String userId;

    public static final class Builder {
        /* access modifiers changed from: private */
        public String btOrderId;
        /* access modifiers changed from: private */
        public String des;
        /* access modifiers changed from: private */
        public String orderData;
        /* access modifiers changed from: private */
        public Integer orderStatus;
        /* access modifiers changed from: private */
        public PaymentInfo paymentInfo;
        /* access modifiers changed from: private */
        public String platform;
        /* access modifiers changed from: private */
        public RoleInfo roleInfo;
        /* access modifiers changed from: private */
        public StatusCode statusCode;
        /* access modifiers changed from: private */
        public String userId;

        public Builder statusCode(StatusCode val) {
            this.statusCode = val;
            return this;
        }

        public Builder platform(String val) {
            this.platform = val;
            return this;
        }

        public Builder roleInfo(RoleInfo val) {
            this.roleInfo = val;
            return this;
        }

        public Builder userId(String val) {
            this.userId = val;
            return this;
        }

        public Builder btOrderId(String val) {
            this.btOrderId = val;
            return this;
        }

        public Builder paymentInfo(PaymentInfo val) {
            this.paymentInfo = val;
            return this;
        }

        public Builder orderData(String val) {
            this.orderData = val;
            return this;
        }

        public Builder orderStatus(Integer val) {
            this.orderStatus = val;
            return this;
        }

        public Builder des(String val) {
            this.des = val;
            return this;
        }

        public NotifyServerResultEvent build() {
            return new NotifyServerResultEvent(this);
        }
    }

    private NotifyServerResultEvent(Builder builder) {
        this.statusCode = builder.statusCode;
        this.platform = builder.platform;
        this.roleInfo = builder.roleInfo;
        this.userId = builder.userId;
        this.btOrderId = builder.btOrderId;
        this.paymentInfo = builder.paymentInfo;
        this.orderData = builder.orderData;
        this.orderStatus = builder.orderStatus;
        this.des = builder.des;
    }

    public StatusCode getStatusCode() {
        return this.statusCode;
    }

    public String getPlatform() {
        return this.platform;
    }

    public RoleInfo getRoleInfo() {
        return this.roleInfo;
    }

    public String getUserId() {
        return this.userId;
    }

    public String getBtOrderId() {
        return this.btOrderId;
    }

    public PaymentInfo getPaymentInfo() {
        return this.paymentInfo;
    }

    public String getOrderData() {
        return this.orderData;
    }

    public Integer getOrderStatus() {
        return this.orderStatus;
    }

    public String getDes() {
        return this.des;
    }
}
