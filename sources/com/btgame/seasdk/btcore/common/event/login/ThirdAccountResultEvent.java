package com.btgame.seasdk.btcore.common.event.login;

import com.btgame.seasdk.btcore.common.constant.EventType;
import com.btgame.seasdk.btcore.common.constant.StatusCode;

public class ThirdAccountResultEvent {
    private String bindId;
    private String des;
    private EventType eventType;
    private String platform;
    private String platformToken;
    private String platformUid;
    private StatusCode statusCode;
    private String thirdId;

    public static final class Builder {
        /* access modifiers changed from: private */
        public String bindId;
        /* access modifiers changed from: private */
        public String des;
        /* access modifiers changed from: private */
        public EventType eventType;
        /* access modifiers changed from: private */
        public String platform;
        /* access modifiers changed from: private */
        public String platformToken;
        /* access modifiers changed from: private */
        public String platformUid;
        /* access modifiers changed from: private */
        public StatusCode statusCode;
        /* access modifiers changed from: private */
        public String thirdId;

        public Builder(EventType eventType2) {
            this.eventType = eventType2;
        }

        public Builder platform(String val) {
            this.platform = val;
            return this;
        }

        public Builder platformUid(String val) {
            this.platformUid = val;
            return this;
        }

        public Builder platformToken(String val) {
            this.platformToken = val;
            return this;
        }

        public Builder bindId(String val) {
            this.bindId = val;
            return this;
        }

        public Builder thirdId(String val) {
            this.thirdId = val;
            return this;
        }

        public Builder statusCode(StatusCode val) {
            this.statusCode = val;
            return this;
        }

        public Builder des(String val) {
            this.des = val;
            return this;
        }

        public ThirdAccountResultEvent build() {
            return new ThirdAccountResultEvent(this);
        }
    }

    private ThirdAccountResultEvent(Builder builder) {
        this.eventType = builder.eventType;
        this.platform = builder.platform;
        this.platformUid = builder.platformUid;
        this.platformToken = builder.platformToken;
        this.bindId = builder.bindId;
        this.thirdId = builder.thirdId;
        this.statusCode = builder.statusCode;
        this.des = builder.des;
    }

    public EventType getEventType() {
        return this.eventType;
    }

    public String getPlatform() {
        return this.platform;
    }

    public String getPlatformUid() {
        return this.platformUid;
    }

    public String getPlatformToken() {
        return this.platformToken;
    }

    public String getBindId() {
        return this.bindId;
    }

    public String getThirdId() {
        return this.thirdId;
    }

    public StatusCode getStatusCode() {
        return this.statusCode;
    }

    public String getDes() {
        return this.des;
    }
}
