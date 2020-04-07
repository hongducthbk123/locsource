package com.btgame.seasdk.task.entity.request;

import com.btgame.seasdk.btcore.common.entity.DeviceProperties;
import com.btgame.seasdk.task.entity.OpType;

public class RegisterData extends BasePostData {
    private String account;
    private String pwd;

    public static final class Builder {
        /* access modifiers changed from: private */
        public String account;
        /* access modifiers changed from: private */
        public Integer appId;
        /* access modifiers changed from: private */
        public DeviceProperties device;
        /* access modifiers changed from: private */
        public String identity;
        /* access modifiers changed from: private */
        public String pwd;

        public Builder appId(Integer val) {
            this.appId = val;
            return this;
        }

        public Builder account(String val) {
            this.account = val;
            return this;
        }

        public Builder identity(String val) {
            this.identity = val;
            return this;
        }

        public Builder pwd(String val) {
            this.pwd = val;
            return this;
        }

        public Builder device(DeviceProperties val) {
            this.device = val;
            return this;
        }

        public RegisterData build() {
            return new RegisterData(this);
        }
    }

    private RegisterData(Builder builder) {
        this.appId = builder.appId;
        this.account = builder.account;
        this.identity = builder.identity;
        this.pwd = builder.pwd;
        this.device = builder.device;
        this.opType = OpType.OP_LOGIN_BT;
    }

    public String getAccount() {
        return this.account;
    }

    public String getPwd() {
        return this.pwd;
    }
}
