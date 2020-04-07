package com.btgame.seasdk.task.entity.response;

import com.btgame.seasdk.task.entity.OpType;

public class LoginResult extends OpResult {
    private String account;
    private boolean firstJoin;
    private long firstJoinDate;
    private String identity;
    private String isVistor;
    private boolean needTip;
    private String platform;
    private String thirdId;
    private String thirdUserId;
    private String token;
    private String userId;

    public static final class Builder {
        /* access modifiers changed from: private */
        public String account;
        /* access modifiers changed from: private */
        public boolean firstJoin;
        /* access modifiers changed from: private */
        public long firstJoinDate;
        /* access modifiers changed from: private */
        public String identity;
        /* access modifiers changed from: private */
        public String isVistor;
        /* access modifiers changed from: private */
        public boolean needTip;
        /* access modifiers changed from: private */
        public String platform;
        /* access modifiers changed from: private */
        public BaseResult result;
        /* access modifiers changed from: private */
        public String thirdId;
        /* access modifiers changed from: private */
        public String thirdUserId;
        /* access modifiers changed from: private */
        public String token;
        /* access modifiers changed from: private */
        public String userId;

        public Builder result(BaseResult val) {
            this.result = val;
            return this;
        }

        public Builder identity(String val) {
            this.identity = val;
            return this;
        }

        public Builder token(String val) {
            this.token = val;
            return this;
        }

        public Builder isVistor(String val) {
            this.isVistor = val;
            return this;
        }

        public Builder needTip(boolean val) {
            this.needTip = val;
            return this;
        }

        public Builder account(String val) {
            this.account = val;
            return this;
        }

        public Builder userId(String val) {
            this.userId = val;
            return this;
        }

        public Builder platform(String val) {
            this.platform = val;
            return this;
        }

        public Builder thirdId(String val) {
            this.thirdId = val;
            return this;
        }

        public Builder thirdUserId(String val) {
            this.thirdUserId = val;
            return this;
        }

        public Builder firstJoinTime(long val) {
            this.firstJoinDate = val;
            return this;
        }

        public Builder firstJoin(boolean val) {
            this.firstJoin = val;
            return this;
        }

        public LoginResult build() {
            return new LoginResult(this);
        }
    }

    public void setPlatform(String platform2) {
        this.platform = platform2;
    }

    public String getPlatform() {
        return this.platform;
    }

    public String getIdentity() {
        return this.identity;
    }

    public String getToken() {
        return this.token;
    }

    public String getIsVistor() {
        return this.isVistor;
    }

    public boolean isNeedTip() {
        return this.needTip;
    }

    public String getAccount() {
        return this.account;
    }

    public String getUserId() {
        return this.userId;
    }

    public String getThirdId() {
        return this.thirdId;
    }

    public void setThirdUserId(String thirdUserId2) {
        this.thirdUserId = thirdUserId2;
    }

    public String getThirdUserId() {
        return this.thirdUserId;
    }

    public long getFirstJoinDate() {
        return this.firstJoinDate;
    }

    public boolean isFirstJoin() {
        return this.firstJoin;
    }

    public void setOpType(OpType opType) {
        this.res.opType = opType;
    }

    public OpType getOpType() {
        return this.res.opType;
    }

    private LoginResult(Builder builder) {
        this.res = builder.result;
        this.identity = builder.identity;
        this.token = builder.token;
        this.isVistor = builder.isVistor;
        this.needTip = builder.needTip;
        this.account = builder.account;
        this.userId = builder.userId;
        this.platform = builder.platform;
        this.thirdId = builder.thirdId;
        this.thirdUserId = builder.thirdUserId;
        this.firstJoinDate = builder.firstJoinDate;
        this.firstJoin = builder.firstJoin;
    }
}
