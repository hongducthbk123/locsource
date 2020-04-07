package com.btgame.seasdk.task.entity.request;

import com.btgame.seasdk.btcore.common.entity.DeviceProperties;
import com.btgame.seasdk.task.entity.OpType;

public class ChangePwdData extends BasePostData {
    private String account;
    private String nPw;
    private String oPw;

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
        public String nPw;
        /* access modifiers changed from: private */
        public String oPw;

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

        public Builder oPw(String val) {
            this.oPw = val;
            return this;
        }

        public Builder nPw(String val) {
            this.nPw = val;
            return this;
        }

        public Builder device(DeviceProperties val) {
            this.device = val;
            return this;
        }

        public ChangePwdData build() {
            return new ChangePwdData(this);
        }
    }

    private ChangePwdData(Builder builder) {
        this.appId = builder.appId;
        this.account = builder.account;
        this.identity = builder.identity;
        this.oPw = builder.oPw;
        this.nPw = builder.nPw;
        this.device = builder.device;
        this.opType = OpType.OP_CHANGEPWD;
    }

    public String getAccount() {
        return this.account;
    }

    public String getoPw() {
        return this.oPw;
    }

    public String getnPw() {
        return this.nPw;
    }
}
