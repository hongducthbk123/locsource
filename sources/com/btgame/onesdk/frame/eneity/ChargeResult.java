package com.btgame.onesdk.frame.eneity;

public class ChargeResult {
    public String ext;
    public Args extData;
    public String goodsDesc;
    public String goodsName;
    public String notifyUrl;
    public String orderId;
    public String partnerId;
    public String produceDesc;
    public int rate;
    public String resultStr;

    public String toString() {
        return "ChargeResult info   orderId = " + this.orderId + " ,notifyUrl=" + this.notifyUrl + ",  goodsDesc=" + this.goodsDesc + ", goodsName = " + this.goodsName;
    }
}
