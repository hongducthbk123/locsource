package com.btgame.seasdk.btcore.common.event.pay;

import android.app.Activity;
import com.btgame.seasdk.btcore.common.constant.EventType;
import com.btgame.seasdk.btcore.common.entity.PaymentInfo;
import com.btgame.seasdk.btcore.common.entity.RoleInfo;
import com.btgame.seasdk.btcore.common.event.SdkEvent;

public class SdkPayEvent extends SdkEvent {
    private Activity activity;
    private PaymentInfo paymentInfo;
    private String platform;
    private RoleInfo roleInfo;

    public static final class Builder {
        /* access modifiers changed from: private */
        public Activity activity;
        /* access modifiers changed from: private */
        public PaymentInfo paymentInfo;
        /* access modifiers changed from: private */
        public String platform;
        /* access modifiers changed from: private */
        public RoleInfo roleInfo;

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

        public SdkPayEvent build() {
            return new SdkPayEvent(this);
        }
    }

    private SdkPayEvent() {
    }

    private SdkPayEvent(Builder builder) {
        setEventType(EventType.PAY_REQ);
        this.platform = builder.platform;
        this.activity = builder.activity;
        this.roleInfo = builder.roleInfo;
        this.paymentInfo = builder.paymentInfo;
    }

    public Activity getActivity() {
        return this.activity;
    }

    public RoleInfo getRoleInfo() {
        return this.roleInfo;
    }

    public String getPlatform() {
        return this.platform;
    }

    public PaymentInfo getPaymentInfo() {
        return this.paymentInfo;
    }
}
