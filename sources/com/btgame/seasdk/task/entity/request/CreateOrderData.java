package com.btgame.seasdk.task.entity.request;

import com.btgame.seasdk.btcore.common.entity.DeviceProperties;

public class CreateOrderData {
    private Integer appId;
    private String callBackInfo;
    private DeviceProperties device;
    private String gameOrderId;
    private String notifyUrl;
    private Integer packageId;
    private int payChannelId;
    private String productCode;
    private String roleId;
    private String roleName;
    private String serverId;
    private String serverName;
    private String sign;
    private String userId;

    public static final class Builder {
        /* access modifiers changed from: private */
        public Integer appId;
        /* access modifiers changed from: private */
        public String callBackInfo;
        /* access modifiers changed from: private */
        public DeviceProperties device;
        /* access modifiers changed from: private */
        public String gameOrderId;
        /* access modifiers changed from: private */
        public String notifyUrl;
        /* access modifiers changed from: private */
        public Integer packageId;
        /* access modifiers changed from: private */
        public int payChannelId;
        /* access modifiers changed from: private */
        public String productCode;
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
        public String userId;

        public Builder serverId(String val) {
            this.serverId = val;
            return this;
        }

        public Builder serverName(String val) {
            this.serverName = val;
            return this;
        }

        public Builder userId(String val) {
            this.userId = val;
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

        public Builder productCode(String val) {
            this.productCode = val;
            return this;
        }

        public Builder appId(Integer val) {
            this.appId = val;
            return this;
        }

        public Builder packageId(Integer val) {
            this.packageId = val;
            return this;
        }

        public Builder callBackInfo(String val) {
            this.callBackInfo = val;
            return this;
        }

        public Builder gameOrderId(String val) {
            this.gameOrderId = val;
            return this;
        }

        public Builder device(DeviceProperties val) {
            this.device = val;
            return this;
        }

        public Builder notifyUrl(String val) {
            this.notifyUrl = val;
            return this;
        }

        public Builder payChannelId(int val) {
            this.payChannelId = val;
            return this;
        }

        public Builder sign(String val) {
            this.sign = val;
            return this;
        }

        public CreateOrderData build() {
            return new CreateOrderData(this);
        }
    }

    private CreateOrderData(Builder builder) {
        this.appId = builder.appId;
        this.packageId = builder.packageId;
        this.device = builder.device;
        this.serverId = builder.serverId;
        this.serverName = builder.serverName;
        this.userId = builder.userId;
        this.roleId = builder.roleId;
        this.roleName = builder.roleName;
        this.productCode = builder.productCode;
        this.callBackInfo = builder.callBackInfo;
        this.gameOrderId = builder.gameOrderId;
        this.notifyUrl = builder.notifyUrl;
        this.payChannelId = builder.payChannelId;
        this.sign = builder.sign;
    }

    public void setDevice(DeviceProperties device2) {
        this.device = device2;
    }

    public void setAppId(Integer appId2) {
        this.appId = appId2;
    }
}
