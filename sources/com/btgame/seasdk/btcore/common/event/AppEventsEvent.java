package com.btgame.seasdk.btcore.common.event;

import android.content.Context;
import java.util.Map;

public class AppEventsEvent {
    private String amount;
    private String channelId;
    private Context context;
    private String currencyCode;
    private String eventName;
    private String eventToken;
    private Map<String, String> extendParams;
    private String orderId;
    private String platforms;
    private String roleId;
    private String userId;

    public static final class Builder {
        /* access modifiers changed from: private */
        public String amount;
        /* access modifiers changed from: private */
        public String channelId;
        /* access modifiers changed from: private */
        public Context context;
        /* access modifiers changed from: private */
        public String currencyCode;
        /* access modifiers changed from: private */
        public String eventName;
        /* access modifiers changed from: private */
        public String eventToken;
        /* access modifiers changed from: private */
        public Map<String, String> extendParams;
        /* access modifiers changed from: private */
        public String orderId;
        /* access modifiers changed from: private */
        public String platforms;
        /* access modifiers changed from: private */
        public String roleId;
        /* access modifiers changed from: private */
        public String userId;

        public Builder context(Context val) {
            this.context = val;
            return this;
        }

        public Builder platforms(String val) {
            this.platforms = val;
            return this;
        }

        public Builder channelId(String val) {
            this.channelId = val;
            return this;
        }

        public Builder userId(String val) {
            this.userId = val;
            return this;
        }

        public Builder roleId(String val) {
            this.roleId = val;
            return this;
        }

        public Builder eventName(String val) {
            this.eventName = val;
            return this;
        }

        public Builder eventToken(String val) {
            this.eventToken = val;
            return this;
        }

        public Builder orderId(String val) {
            this.orderId = val;
            return this;
        }

        public Builder amount(String val) {
            this.amount = val;
            return this;
        }

        public Builder currencyCode(String val) {
            this.currencyCode = val;
            return this;
        }

        public Builder extendParams(Map<String, String> val) {
            this.extendParams = val;
            return this;
        }

        public AppEventsEvent build() {
            return new AppEventsEvent(this);
        }
    }

    private AppEventsEvent(Builder builder) {
        this.context = builder.context;
        this.platforms = builder.platforms;
        this.channelId = builder.channelId;
        this.userId = builder.userId;
        this.roleId = builder.roleId;
        this.eventName = builder.eventName;
        this.eventToken = builder.eventToken;
        this.orderId = builder.orderId;
        this.amount = builder.amount;
        this.currencyCode = builder.currencyCode;
        this.extendParams = builder.extendParams;
    }

    public Context getContext() {
        return this.context;
    }

    public String getPlatforms() {
        return this.platforms;
    }

    public String getChannelId() {
        return this.channelId;
    }

    public String getUserId() {
        return this.userId;
    }

    public String getRoleId() {
        return this.roleId;
    }

    public String getEventName() {
        return this.eventName;
    }

    public String getEventToken() {
        return this.eventToken;
    }

    public String getOrderId() {
        return this.orderId;
    }

    public String getAmount() {
        return this.amount;
    }

    public String getCurrencyCode() {
        return this.currencyCode;
    }

    public Map<String, String> getExtendParams() {
        return this.extendParams;
    }
}
