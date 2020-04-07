package com.btgame.seasdk.task.entity.response;

import java.util.List;

public class ReqInitResult extends OpResult {
    private List<String> loginList;

    public static final class Builder {
        /* access modifiers changed from: private */
        public List<String> loginList;
        /* access modifiers changed from: private */
        public BaseResult res;

        public Builder res(BaseResult val) {
            this.res = val;
            return this;
        }

        public Builder loginList(List<String> val) {
            this.loginList = val;
            return this;
        }

        public ReqInitResult build() {
            return new ReqInitResult(this);
        }
    }

    private ReqInitResult(Builder builder) {
        setRes(builder.res);
        this.loginList = builder.loginList;
    }

    public List<String> getLoginList() {
        return this.loginList;
    }
}
