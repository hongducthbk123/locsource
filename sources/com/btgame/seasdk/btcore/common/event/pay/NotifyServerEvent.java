package com.btgame.seasdk.btcore.common.event.pay;

import com.btgame.seasdk.btcore.common.entity.PaymentInfo;
import com.btgame.seasdk.btcore.common.entity.RoleInfo;

public class NotifyServerEvent {
    private String btOrderId;
    private String orderData;
    private String orderDesc;
    private Integer orderStatus;
    private PaymentInfo paymentInfo;
    private String platform;
    private String roleId;
    private RoleInfo roleInfo;
    private String roleName;
    private String serverId;
    private String serverName;
    private String sign;
    private String type;
    private String userId;

    public static final class Builder {
        /* access modifiers changed from: private */
        public String btOrderId;
        /* access modifiers changed from: private */
        public String orderData;
        /* access modifiers changed from: private */
        public String orderDesc;
        /* access modifiers changed from: private */
        public Integer orderStatus;
        /* access modifiers changed from: private */
        public PaymentInfo paymentInfo;
        /* access modifiers changed from: private */
        public String platform;
        /* access modifiers changed from: private */
        public String roleId;
        /* access modifiers changed from: private */
        public RoleInfo roleInfo;
        /* access modifiers changed from: private */
        public String roleName;
        /* access modifiers changed from: private */
        public String serverId;
        /* access modifiers changed from: private */
        public String serverName;
        /* access modifiers changed from: private */
        public String sign;
        /* access modifiers changed from: private */
        public String type;
        /* access modifiers changed from: private */
        public String userId;

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

        public Builder type(String val) {
            this.type = val;
            return this;
        }

        public Builder serverId(String val) {
            this.serverId = val;
            return this;
        }

        public Builder serverName(String val) {
            this.serverName = val;
            return this;
        }

        public Builder roleId(String val) {
            this.roleId = val;
            return this;
        }

        public Builder roleName(String val) {
            this.roleName = val;
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

        public Builder orderDesc(String val) {
            this.orderDesc = val;
            return this;
        }

        public Builder sign(String val) {
            this.sign = val;
            return this;
        }

        public NotifyServerEvent build() {
            return new NotifyServerEvent(this);
        }
    }

    private NotifyServerEvent(Builder builder) {
        this.platform = builder.platform;
        this.roleInfo = builder.roleInfo;
        this.userId = builder.userId;
        this.type = builder.type;
        this.serverId = builder.serverId;
        this.serverName = builder.serverName;
        this.roleId = builder.roleId;
        this.roleName = builder.roleName;
        this.btOrderId = builder.btOrderId;
        this.paymentInfo = builder.paymentInfo;
        this.orderData = builder.orderData;
        this.orderStatus = builder.orderStatus;
        this.orderDesc = builder.orderDesc;
        this.sign = builder.sign;
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

    public String getType() {
        return this.type;
    }

    public String getServerId() {
        return this.serverId;
    }

    public String getServerName() {
        return this.serverName;
    }

    public String getRoleId() {
        return this.roleId;
    }

    public String getRoleName() {
        return this.roleName;
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

    public String getOrderDesc() {
        return this.orderDesc;
    }

    public String getSign() {
        return this.sign;
    }
}
