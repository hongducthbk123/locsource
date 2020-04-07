package com.btgame.seasdk.btcore.common.event;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import com.btgame.seasdk.btcore.common.constant.LifecycleEventType;

public class LifecycleEvent {
    private Activity activity;
    private Application application;
    private Intent data;
    private int[] grantResults;
    private boolean hasFocus;
    private LifecycleEventType lifecycleEventType;
    private String[] permissions;
    private int requestCode;
    private int resultCode;
    private Bundle savedInstanceState;

    public static final class Builder {
        /* access modifiers changed from: private */
        public Activity activity;
        /* access modifiers changed from: private */
        public Application application;
        /* access modifiers changed from: private */
        public Intent data;
        /* access modifiers changed from: private */
        public int[] grantResults;
        /* access modifiers changed from: private */
        public boolean hasFocus;
        /* access modifiers changed from: private */
        public LifecycleEventType lifecycleEventType;
        /* access modifiers changed from: private */
        public String[] permissions;
        /* access modifiers changed from: private */
        public int requestCode;
        /* access modifiers changed from: private */
        public int resultCode;
        /* access modifiers changed from: private */
        public Bundle savedInstanceState;

        public Builder lifecycleEventType(LifecycleEventType val) {
            this.lifecycleEventType = val;
            return this;
        }

        public Builder application(Application val) {
            this.application = val;
            return this;
        }

        public Builder activity(Activity val) {
            this.activity = val;
            return this;
        }

        public Builder hasFocus(boolean val) {
            this.hasFocus = val;
            return this;
        }

        public Builder savedInstanceState(Bundle val) {
            this.savedInstanceState = val;
            return this;
        }

        public Builder requestCode(int val) {
            this.requestCode = val;
            return this;
        }

        public Builder resultCode(int val) {
            this.resultCode = val;
            return this;
        }

        public Builder data(Intent val) {
            this.data = val;
            return this;
        }

        public Builder permissions(String[] val) {
            this.permissions = val;
            return this;
        }

        public Builder grantResults(int[] val) {
            this.grantResults = val;
            return this;
        }

        public LifecycleEvent build() {
            return new LifecycleEvent(this);
        }
    }

    private LifecycleEvent(Builder builder) {
        this.lifecycleEventType = builder.lifecycleEventType;
        this.application = builder.application;
        this.activity = builder.activity;
        this.hasFocus = builder.hasFocus;
        this.savedInstanceState = builder.savedInstanceState;
        this.requestCode = builder.requestCode;
        this.resultCode = builder.resultCode;
        this.data = builder.data;
        this.permissions = builder.permissions;
        this.grantResults = builder.grantResults;
    }

    public LifecycleEventType getLifecycleEventType() {
        return this.lifecycleEventType;
    }

    public Application getApplication() {
        return this.application;
    }

    public Activity getActivity() {
        return this.activity;
    }

    public boolean isHasFocus() {
        return this.hasFocus;
    }

    public Bundle getSavedInstanceState() {
        return this.savedInstanceState;
    }

    public int getRequestCode() {
        return this.requestCode;
    }

    public int getResultCode() {
        return this.resultCode;
    }

    public Intent getData() {
        return this.data;
    }

    public String[] getPermissions() {
        return this.permissions;
    }

    public int[] getGrantResults() {
        return this.grantResults;
    }
}
