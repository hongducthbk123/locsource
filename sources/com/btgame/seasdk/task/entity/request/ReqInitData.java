package com.btgame.seasdk.task.entity.request;

import com.btgame.seasdk.btcore.common.entity.DeviceProperties;
import com.btgame.seasdk.task.entity.OpType;

public class ReqInitData extends BasePostData {
    private Integer packageId;

    public static final class Builder {
        /* access modifiers changed from: private */
        public Integer appId;
        /* access modifiers changed from: private */
        public DeviceProperties device;
        /* access modifiers changed from: private */
        public String identity;
        /* access modifiers changed from: private */
        public Integer packageId;

        public Builder packageId(Integer val) {
            this.packageId = val;
            return this;
        }

        public Builder appId(Integer val) {
            this.appId = val;
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

        public ReqInitData build() {
            return new ReqInitData(this);
        }
    }

    private ReqInitData(Builder builder) {
        this.packageId = builder.packageId;
        this.appId = builder.appId;
        this.identity = builder.identity;
        this.device = builder.device;
        this.opType = OpType.OP_INIT;
    }

    public Integer getPackageId() {
        return this.packageId;
    }
}
