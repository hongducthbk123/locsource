package com.btgame.seasdk.btcore.common.event;

import com.btgame.seasdk.btcore.common.constant.EventType;
import com.btgame.seasdk.btcore.common.constant.StatusCode;

public class ShareResultEvent extends SdkEvent {
    private String platform;
    private StatusCode statusCode;

    public static final class Builder {
        private EventType eventType;
        /* access modifiers changed from: private */
        public String platform;
        /* access modifiers changed from: private */
        public StatusCode statusCode;

        public Builder platform(String val) {
            this.platform = val;
            return this;
        }

        public Builder statusCode(StatusCode val) {
            this.statusCode = val;
            return this;
        }

        public ShareResultEvent build() {
            return new ShareResultEvent(this);
        }
    }

    private ShareResultEvent(Builder builder) {
        setEventType(EventType.SHARE_RES);
        this.platform = builder.platform;
        this.statusCode = builder.statusCode;
    }

    public String getPlatform() {
        return this.platform;
    }

    public StatusCode getStatusCode() {
        return this.statusCode;
    }
}
