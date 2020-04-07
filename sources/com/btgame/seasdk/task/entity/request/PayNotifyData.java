package com.btgame.seasdk.task.entity.request;

import com.btgame.seasdk.btcore.common.entity.DeviceProperties;

public class PayNotifyData {
    private Integer appId;
    private DeviceProperties device;
    private String orderData;
    private String orderDesc;
    private String orderId;
    private Integer orderStatus;
    private Integer packageId;
    private String roleId;
    private String roleName;
    private String serverId;
    private String serverName;
    private String sign;
    private String type;
    private String userId;

    public static final class Builder {
        /* access modifiers changed from: private */
        public Integer appId;
        /* access modifiers changed from: private */
        public DeviceProperties device;
        /* access modifiers changed from: private */
        public String orderData;
        /* access modifiers changed from: private */
        public String orderDesc;
        /* access modifiers changed from: private */
        public String orderId;
        /* access modifiers changed from: private */
        public Integer orderStatus;
        /* access modifiers changed from: private */
        public Integer packageId;
        /* access modifiers changed from: private */
        public String roleId;
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

        public Builder appId(Integer val) {
            this.appId = val;
            return this;
        }

        public Builder packageId(Integer val) {
            this.packageId = val;
            return this;
        }

        public Builder device(DeviceProperties val) {
            this.device = val;
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

        public Builder orderId(String val) {
            this.orderId = val;
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

        public PayNotifyData build() {
            return new PayNotifyData(this);
        }
    }

    private PayNotifyData(Builder builder) {
        this.appId = builder.appId;
        this.packageId = builder.packageId;
        this.device = builder.device;
        this.userId = builder.userId;
        this.type = builder.type;
        this.serverId = builder.serverId;
        this.serverName = builder.serverName;
        this.roleId = builder.roleId;
        this.roleName = builder.roleName;
        this.orderId = builder.orderId;
        this.orderData = builder.orderData;
        this.orderStatus = builder.orderStatus;
        this.orderDesc = builder.orderDesc;
        this.sign = builder.sign;
    }

    public Integer getAppId() {
        return this.appId;
    }

    public Integer getPackageId() {
        return this.packageId;
    }

    public DeviceProperties getDevice() {
        return this.device;
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

    public String getOrderId() {
        return this.orderId;
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
