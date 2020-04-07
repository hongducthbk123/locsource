package com.facebook.share.model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;

public final class ShareMessengerURLActionButton extends ShareMessengerActionButton {
    public static final Creator<ShareMessengerURLActionButton> CREATOR = new Creator<ShareMessengerURLActionButton>() {
        public ShareMessengerURLActionButton createFromParcel(Parcel in) {
            return new ShareMessengerURLActionButton(in);
        }

        public ShareMessengerURLActionButton[] newArray(int size) {
            return new ShareMessengerURLActionButton[size];
        }
    };
    private final Uri fallbackUrl;
    private final boolean isMessengerExtensionURL;
    private final boolean shouldHideWebviewShareButton;
    private final Uri url;
    private final WebviewHeightRatio webviewHeightRatio;

    public static final class Builder extends com.facebook.share.model.ShareMessengerActionButton.Builder<ShareMessengerURLActionButton, Builder> {
        /* access modifiers changed from: private */
        public Uri fallbackUrl;
        /* access modifiers changed from: private */
        public boolean isMessengerExtensionURL;
        /* access modifiers changed from: private */
        public boolean shouldHideWebviewShareButton;
        /* access modifiers changed from: private */
        public Uri url;
        /* access modifiers changed from: private */
        public WebviewHeightRatio webviewHeightRatio;

        public Builder setUrl(@Nullable Uri url2) {
            this.url = url2;
            return this;
        }

        public Builder setIsMessengerExtensionURL(boolean isMessengerExtensionURL2) {
            this.isMessengerExtensionURL = isMessengerExtensionURL2;
            return this;
        }

        public Builder setFallbackUrl(@Nullable Uri fallbackUrl2) {
            this.fallbackUrl = fallbackUrl2;
            return this;
        }

        public Builder setWebviewHeightRatio(WebviewHeightRatio webviewHeightRatio2) {
            this.webviewHeightRatio = webviewHeightRatio2;
            return this;
        }

        public Builder setShouldHideWebviewShareButton(boolean shouldHideWebviewShareButton2) {
            this.shouldHideWebviewShareButton = shouldHideWebviewShareButton2;
            return this;
        }

        /* Debug info: failed to restart local var, previous not found, register: 2 */
        public Builder readFrom(ShareMessengerURLActionButton content) {
            if (content == null) {
                return this;
            }
            return setUrl(content.getUrl()).setIsMessengerExtensionURL(content.getIsMessengerExtensionURL()).setFallbackUrl(content.getFallbackUrl()).setWebviewHeightRatio(content.getWebviewHeightRatio()).setShouldHideWebviewShareButton(content.getShouldHideWebviewShareButton());
        }

        public ShareMessengerURLActionButton build() {
            return new ShareMessengerURLActionButton(this);
        }
    }

    public enum WebviewHeightRatio {
        WebviewHeightRatioFull,
        WebviewHeightRatioTall,
        WebviewHeightRatioCompact
    }

    private ShareMessengerURLActionButton(Builder builder) {
        super((com.facebook.share.model.ShareMessengerActionButton.Builder) builder);
        this.url = builder.url;
        this.isMessengerExtensionURL = builder.isMessengerExtensionURL;
        this.fallbackUrl = builder.fallbackUrl;
        this.webviewHeightRatio = builder.webviewHeightRatio;
        this.shouldHideWebviewShareButton = builder.shouldHideWebviewShareButton;
    }

    ShareMessengerURLActionButton(Parcel in) {
        boolean z;
        boolean z2 = true;
        super(in);
        this.url = (Uri) in.readParcelable(Uri.class.getClassLoader());
        if (in.readByte() != 0) {
            z = true;
        } else {
            z = false;
        }
        this.isMessengerExtensionURL = z;
        this.fallbackUrl = (Uri) in.readParcelable(Uri.class.getClassLoader());
        this.webviewHeightRatio = (WebviewHeightRatio) in.readSerializable();
        if (in.readByte() == 0) {
            z2 = false;
        }
        this.shouldHideWebviewShareButton = z2;
    }

    public Uri getUrl() {
        return this.url;
    }

    public boolean getIsMessengerExtensionURL() {
        return this.isMessengerExtensionURL;
    }

    @Nullable
    public Uri getFallbackUrl() {
        return this.fallbackUrl;
    }

    @Nullable
    public WebviewHeightRatio getWebviewHeightRatio() {
        return this.webviewHeightRatio;
    }

    public boolean getShouldHideWebviewShareButton() {
        return this.shouldHideWebviewShareButton;
    }
}
