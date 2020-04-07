package com.btgame.seasdk.btcore.common.event;

import android.app.Activity;
import com.btgame.seasdk.btcore.common.constant.EventType;

public class SdkCheckPermisionEvent extends SdkEvent {
    private Activity activity;
    private String des;
    private int statusCode;

    public static final class Builder {
        /* access modifiers changed from: private */
        public Activity activity;
        /* access modifiers changed from: private */
        public String des;
        /* access modifiers changed from: private */
        public EventType eventType;
        /* access modifiers changed from: private */
        public int statusCode;

        public Builder(EventType eventType2) {
            this.eventType = eventType2;
        }

        public Builder activity(Activity val) {
            this.activity = val;
            return this;
        }

        public Builder statusCode(int val) {
            this.statusCode = val;
            return this;
        }

        public Builder des(String val) {
            this.des = val;
            return this;
        }

        public SdkCheckPermisionEvent build() {
            return new SdkCheckPermisionEvent(this);
        }
    }

    private SdkCheckPermisionEvent() {
        this.statusCode = -1;
    }

    private SdkCheckPermisionEvent(Builder builder) {
        super(builder.eventType);
        this.statusCode = -1;
        this.activity = builder.activity;
        this.statusCode = builder.statusCode;
        this.des = builder.des;
    }

    public Activity getActivity() {
        return this.activity;
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public void setStatusCode(int statusCode2) {
        this.statusCode = statusCode2;
    }

    public String getDes() {
        return this.des;
    }

    public void setDes(String des2) {
        this.des = des2;
    }
}
