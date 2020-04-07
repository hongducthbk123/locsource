package com.btgame.seasdk.btcore.common.event.login;

import com.btgame.seasdk.btcore.common.constant.EventType;
import com.btgame.seasdk.btcore.common.constant.StatusCode;
import com.btgame.seasdk.btcore.common.event.SdkEvent;

public class SdkAccountResultEvent extends SdkEvent {
    private String account;
    private boolean firstJoin;
    private long firstJoinDate;
    private String platform;
    private StatusCode statusCode;
    private String token;
    private String userId;

    public static final class Builder {
        /* access modifiers changed from: private */
        public String account;
        /* access modifiers changed from: private */
        public EventType eventType;
        /* access modifiers changed from: private */
        public boolean firstJoin;
        /* access modifiers changed from: private */
        public long firstJoinDate;
        /* access modifiers changed from: private */
        public String platform;
        /* access modifiers changed from: private */
        public StatusCode statusCode;
        /* access modifiers changed from: private */
        public String token;
        /* access modifiers changed from: private */
        public String userId;

        public Builder(EventType eventType2) {
            this.eventType = eventType2;
        }

        public Builder account(String val) {
            this.account = val;
            return this;
        }

        public Builder userId(String val) {
            this.userId = val;
            return this;
        }

        public Builder token(String val) {
            this.token = val;
            return this;
        }

        public Builder statusCode(StatusCode val) {
            this.statusCode = val;
            return this;
        }

        public Builder platform(String val) {
            this.platform = val;
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

        public SdkAccountResultEvent build() {
            return new SdkAccountResultEvent(this);
        }
    }

    public SdkAccountResultEvent(EventType eventType) {
        super(eventType);
    }

    private SdkAccountResultEvent(Builder builder) {
        this(builder.eventType);
        this.account = builder.account;
        this.userId = builder.userId;
        this.token = builder.token;
        this.statusCode = builder.statusCode;
        this.platform = builder.platform;
        this.firstJoinDate = builder.firstJoinDate;
        this.firstJoin = builder.firstJoin;
    }

    public String getAccount() {
        return this.account;
    }

    public String getUserId() {
        return this.userId;
    }

    public String getToken() {
        return this.token;
    }

    public StatusCode getStatusCode() {
        return this.statusCode;
    }

    public String getPlatform() {
        return this.platform;
    }

    public long getFirstJoinDate() {
        return this.firstJoinDate;
    }

    public boolean isFirstJoin() {
        return this.firstJoin;
    }
}
