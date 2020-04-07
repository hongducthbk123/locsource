package com.btgame.seasdk.task.entity.request;

import com.btgame.seasdk.btcore.common.entity.DeviceProperties;
import com.btgame.seasdk.task.entity.OpType;

public class BindMailData extends BasePostData {
    private String account;
    private String code;
    private String email;
    private String pwd;

    public static final class Builder {
        /* access modifiers changed from: private */
        public String account;
        /* access modifiers changed from: private */
        public Integer appId;
        /* access modifiers changed from: private */
        public String code;
        /* access modifiers changed from: private */
        public DeviceProperties device;
        /* access modifiers changed from: private */
        public String email;
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

        public Builder pwd(String val) {
            this.pwd = val;
            return this;
        }

        public Builder identity(String val) {
            this.identity = val;
            return this;
        }

        public Builder email(String val) {
            this.email = val;
            return this;
        }

        public Builder code(String val) {
            this.code = val;
            return this;
        }

        public Builder device(DeviceProperties val) {
            this.device = val;
            return this;
        }

        public BindMailData build() {
            return new BindMailData(this);
        }
    }

    private BindMailData(Builder builder) {
        this.appId = builder.appId;
        this.account = builder.account;
        this.pwd = builder.pwd;
        this.identity = builder.identity;
        this.email = builder.email;
        this.code = builder.code;
        this.device = builder.device;
        this.opType = OpType.OP_BINDMAIL;
    }

    public String getAccount() {
        return this.account;
    }

    public String getEmail() {
        return this.email;
    }
}
