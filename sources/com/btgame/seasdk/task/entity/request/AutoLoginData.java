package com.btgame.seasdk.task.entity.request;

import com.btgame.seasdk.btcore.common.entity.DeviceProperties;

public class AutoLoginData extends BasePostData {
    private String account;

    public static final class Builder {
        /* access modifiers changed from: private */
        public String account;
        /* access modifiers changed from: private */
        public Integer appId;
        /* access modifiers changed from: private */
        public DeviceProperties device;
        /* access modifiers changed from: private */
        public String identity;

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

        public Builder device(DeviceProperties val) {
            this.device = val;
            return this;
        }

        public AutoLoginData build() {
            return new AutoLoginData(this);
        }
    }

    private AutoLoginData(Builder builder) {
        this.appId = builder.appId;
        this.account = builder.account;
        this.identity = builder.identity;
        this.device = builder.device;
    }

    public String getAccount() {
        return this.account;
    }
}
