package com.btgame.seasdk.btcore.common.event.pay;

import android.app.Activity;
import com.btgame.seasdk.btcore.common.constant.StatusCode;
import com.btgame.seasdk.btcore.common.entity.PaymentInfo;
import com.btgame.seasdk.btcore.common.entity.RoleInfo;

public class CreateOrderResultEvent {
    private Activity activity;
    private String des;
    private PaymentInfo paymentInfo;
    private String platform;
    private RoleInfo roleInfo;
    private StatusCode statusCode;

    public static final class Builder {
        /* access modifiers changed from: private */
        public Activity activity;
        /* access modifiers changed from: private */
        public String des;
        /* access modifiers changed from: private */
        public PaymentInfo paymentInfo;
        /* access modifiers changed from: private */
        public String platform;
        /* access modifiers changed from: private */
        public RoleInfo roleInfo;
        /* access modifiers changed from: private */
        public StatusCode statusCode;

        public Builder platform(String val) {
            this.platform = val;
            return this;
        }

        public Builder activity(Activity val) {
            this.activity = val;
            return this;
        }

        public Builder roleInfo(RoleInfo val) {
            this.roleInfo = val;
            return this;
        }

        public Builder paymentInfo(PaymentInfo val) {
            this.paymentInfo = val;
            return this;
        }

        public Builder statusCode(StatusCode val) {
            this.statusCode = val;
            return this;
        }

        public Builder des(String val) {
            this.des = val;
            return this;
        }

        public CreateOrderResultEvent build() {
            return new CreateOrderResultEvent(this);
        }
    }

    private CreateOrderResultEvent() {
    }

    private CreateOrderResultEvent(Builder builder) {
        this.platform = builder.platform;
        this.activity = builder.activity;
        this.roleInfo = builder.roleInfo;
        this.paymentInfo = builder.paymentInfo;
        this.statusCode = builder.statusCode;
        this.des = builder.des;
    }

    public String getPlatform() {
        return this.platform;
    }

    public Activity getActivity() {
        return this.activity;
    }

    public RoleInfo getRoleInfo() {
        return this.roleInfo;
    }

    public StatusCode getStatusCode() {
        return this.statusCode;
    }

    public String getDes() {
        return this.des;
    }

    public PaymentInfo getPaymentInfo() {
        return this.paymentInfo;
    }
}
