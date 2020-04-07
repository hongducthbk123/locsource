package com.btgame.seasdk.task.entity.request;

import com.btgame.seasdk.btcore.common.entity.DeviceProperties;
import com.btgame.seasdk.task.entity.OpType;

public class ThirdLoginData extends BasePostData {
    private String extArgs;
    protected transient String platform;
    private String thirdId;
    private String thirdToken;
    private String thirdUserId;

    public static final class Builder {
        /* access modifiers changed from: private */
        public Integer appId;
        /* access modifiers changed from: private */
        public DeviceProperties device;
        /* access modifiers changed from: private */
        public String extArgs;
        /* access modifiers changed from: private */
        public String identity;
        /* access modifiers changed from: private */
        public String platform;
        /* access modifiers changed from: private */
        public String thirdId;
        /* access modifiers changed from: private */
        public String thirdToken;
        /* access modifiers changed from: private */
        public String thirdUserId;

        public Builder platform(String val) {
            this.platform = val;
            return this;
        }

        public Builder thirdId(String val) {
            this.thirdId = val;
            return this;
        }

        public Builder appId(Integer val) {
            this.appId = val;
            return this;
        }

        public Builder thirdUserId(String val) {
            this.thirdUserId = val;
            return this;
        }

        public Builder identity(String val) {
            this.identity = val;
            return this;
        }

        public Builder thirdToken(String val) {
            this.thirdToken = val;
            return this;
        }

        public Builder device(DeviceProperties val) {
            this.device = val;
            return this;
        }

        public Builder extArgs(String val) {
            this.extArgs = val;
            return this;
        }

        public ThirdLoginData build() {
            return new ThirdLoginData(this);
        }
    }

    private ThirdLoginData(Builder builder) {
        setOpType(OpType.OP_LOGIN_THIRD);
        this.thirdId = builder.thirdId;
        setAppId(builder.appId);
        this.thirdUserId = builder.thirdUserId;
        setIdentity(builder.identity);
        this.thirdToken = builder.thirdToken;
        setDevice(builder.device);
        this.extArgs = builder.extArgs;
        this.platform = builder.platform;
    }

    public String getPlatform() {
        return this.platform;
    }

    public String getThirdUserId() {
        return this.thirdUserId;
    }
}
