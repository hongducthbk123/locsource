package com.btgame.seasdk.btcore.common.entity;

public class SdkPaymentInfo {
    private String amount;
    private String callBackInfo;
    private String currencyCode;
    private transient String extraInfo;
    private String gameOrderId;
    private transient String goodsId;
    private String notifyUrl;
    private String sign;
    private String sku;
    private String skuName;

    public static final class Builder {
        /* access modifiers changed from: private */
        public String amount;
        /* access modifiers changed from: private */
        public String callBackInfo;
        /* access modifiers changed from: private */
        public String currencyCode;
        /* access modifiers changed from: private */
        public String extraInfo;
        /* access modifiers changed from: private */
        public String gameOrderId;
        /* access modifiers changed from: private */
        public String goodsId;
        /* access modifiers changed from: private */
        public String notifyUrl;
        /* access modifiers changed from: private */
        public String sign;
        /* access modifiers changed from: private */
        public String sku;
        /* access modifiers changed from: private */
        public String skuName;

        public Builder goodsId(String val) {
            this.goodsId = val;
            return this;
        }

        public Builder sku(String val) {
            this.sku = val;
            return this;
        }

        public Builder skuName(String val) {
            this.skuName = val;
            return this;
        }

        public Builder extraInfo(String val) {
            this.extraInfo = val;
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

        public Builder callBackInfo(String val) {
            this.callBackInfo = val;
            return this;
        }

        public Builder notifyUrl(String val) {
            this.notifyUrl = val;
            return this;
        }

        public Builder gameOrderId(String val) {
            this.gameOrderId = val;
            return this;
        }

        public Builder sign(String val) {
            this.sign = val;
            return this;
        }

        public SdkPaymentInfo build() {
            return new SdkPaymentInfo(this);
        }
    }

    private SdkPaymentInfo() {
    }

    private SdkPaymentInfo(Builder builder) {
        this.goodsId = builder.goodsId;
        this.sku = builder.sku;
        this.skuName = builder.skuName;
        this.extraInfo = builder.extraInfo;
        this.amount = builder.amount;
        this.currencyCode = builder.currencyCode;
        this.callBackInfo = builder.callBackInfo;
        this.notifyUrl = builder.notifyUrl;
        this.gameOrderId = builder.gameOrderId;
        this.sign = builder.sign;
    }

    public String getGoodsId() {
        return this.goodsId;
    }

    public String getSku() {
        return this.sku;
    }

    public String getSkuName() {
        return this.skuName;
    }

    public String getExtraInfo() {
        return this.extraInfo;
    }

    public String getAmount() {
        return this.amount;
    }

    public String getCurrencyCode() {
        return this.currencyCode;
    }

    public String getCallBackInfo() {
        return this.callBackInfo;
    }

    public String getNotifyUrl() {
        return this.notifyUrl;
    }

    public String getGameOrderId() {
        return this.gameOrderId;
    }

    public String getSign() {
        return this.sign;
    }
}
