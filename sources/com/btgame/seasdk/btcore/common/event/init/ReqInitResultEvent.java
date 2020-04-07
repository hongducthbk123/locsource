package com.btgame.seasdk.btcore.common.event.init;

import com.btgame.seasdk.btcore.common.constant.EventType;
import com.btgame.seasdk.btcore.common.constant.StatusCode;
import com.btgame.seasdk.btcore.common.event.SdkEvent;

public class ReqInitResultEvent extends SdkEvent {
    private StatusCode statusCode;

    public static final class Builder {
        /* access modifiers changed from: private */
        public StatusCode statusCode;

        public Builder statusCode(StatusCode val) {
            this.statusCode = val;
            return this;
        }

        public ReqInitResultEvent build() {
            return new ReqInitResultEvent(this);
        }
    }

    private ReqInitResultEvent(Builder builder) {
        setEventType(EventType.INIT_RES);
        this.statusCode = builder.statusCode;
    }

    public StatusCode getStatusCode() {
        return this.statusCode;
    }
}
