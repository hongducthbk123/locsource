package com.btgame.seasdk.btcore.common.event;

import android.app.Activity;
import com.btgame.seasdk.btcore.common.constant.EventType;

public class ShareEvent extends SdkEvent {
    private Activity activity;
    private String platform;
    private String shareImagePath;
    private String shareUrl;

    public static final class Builder {
        /* access modifiers changed from: private */
        public Activity activity;
        /* access modifiers changed from: private */
        public String platform;
        /* access modifiers changed from: private */
        public String shareImagePath;
        /* access modifiers changed from: private */
        public String shareUrl;

        public Builder platform(String val) {
            this.platform = val;
            return this;
        }

        public Builder activity(Activity val) {
            this.activity = val;
            return this;
        }

        public Builder shareUrl(String val) {
            this.shareUrl = val;
            return this;
        }

        public Builder shareImagePath(String val) {
            this.shareImagePath = val;
            return this;
        }

        public ShareEvent build() {
            return new ShareEvent(this);
        }
    }

    private ShareEvent(Builder builder) {
        setEventType(EventType.SHARE_REQ);
        this.platform = builder.platform;
        this.activity = builder.activity;
        this.shareUrl = builder.shareUrl;
        this.shareImagePath = builder.shareImagePath;
    }

    public String getPlatform() {
        return this.platform;
    }

    public Activity getActivity() {
        return this.activity;
    }

    public String getShareUrl() {
        return this.shareUrl;
    }

    public String getShareImagePath() {
        return this.shareImagePath;
    }
}
