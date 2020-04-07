package com.btgame.seasdk.btcore.common.event.login;

import android.app.Activity;
import android.support.p000v4.app.Fragment;
import com.btgame.seasdk.btcore.common.constant.EventType;

public class ThirdAccountEvent {
    private Activity activity;
    private String bindId;
    private EventType eventType;
    private Fragment fragment;
    private boolean ignoreCache;
    private String platform;
    private String thirdId;

    public static final class Builder {
        /* access modifiers changed from: private */
        public Activity activity;
        /* access modifiers changed from: private */
        public String bindId;
        /* access modifiers changed from: private */
        public EventType eventType;
        /* access modifiers changed from: private */
        public Fragment fragment;
        public boolean ignoreCache;
        /* access modifiers changed from: private */
        public String platform;
        /* access modifiers changed from: private */
        public String thirdId;

        public Builder(EventType eventType2) {
            this.eventType = eventType2;
        }

        public Builder activity(Activity val) {
            this.activity = val;
            return this;
        }

        public Builder fragment(Fragment val) {
            this.fragment = val;
            return this;
        }

        public Builder platform(String val) {
            this.platform = val;
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

        public Builder ignoreCache(boolean val) {
            this.ignoreCache = val;
            return this;
        }

        public ThirdAccountEvent build() {
            return new ThirdAccountEvent(this);
        }
    }

    private ThirdAccountEvent(Builder builder) {
        this.eventType = builder.eventType;
        this.activity = builder.activity;
        this.fragment = builder.fragment;
        this.platform = builder.platform;
        this.bindId = builder.bindId;
        this.thirdId = builder.thirdId;
        this.ignoreCache = builder.ignoreCache;
    }

    public EventType getEventType() {
        return this.eventType;
    }

    public Activity getActivity() {
        return this.activity;
    }

    public Fragment getFragment() {
        return this.fragment;
    }

    public String getPlatform() {
        return this.platform;
    }

    public String getBindId() {
        return this.bindId;
    }

    public String getThirdId() {
        return this.thirdId;
    }

    public boolean isIgnoreCache() {
        return this.ignoreCache;
    }
}
