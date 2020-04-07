package com.btgame.onesdk.frame.eneity.onesdk;

import com.btgame.onesdk.frame.eneity.Args;

public class SDKPaymentInfo {
    public Args args;
    private String callBackStr;
    public int coinsAmount;
    private String exStr;
    private String gameGold;
    public String goodsId;
    private double money;
    private int moreCharge;
    public String outOrderNo;
    private int payType;
    public String platformGoodsId;
    private String productName;
    private int rate;
    private String roleId;

    public int getCoinsAmount() {
        return this.coinsAmount;
    }

    public void setCoinsAmount(int coinsAmount2) {
        this.coinsAmount = coinsAmount2;
    }

    public String getOutOrderNo() {
        return this.outOrderNo;
    }

    public void setOutOrderNo(String outOrderNo2) {
        this.outOrderNo = outOrderNo2;
    }

    public String getGoodsId() {
        return this.goodsId;
    }

    public void setGoodsId(String goodsID) {
        this.goodsId = goodsID;
    }

    public String getRoleId() {
        return this.roleId;
    }

    public void setRoleId(String roleId2) {
        this.roleId = roleId2;
    }

    public String getCallBackStr() {
        return this.callBackStr;
    }

    public void setCallBackStr(String callBackStr2) {
        this.callBackStr = callBackStr2;
    }

    public double getMoney() {
        return this.money;
    }

    public void setMoney(double money2) {
        this.money = money2;
    }

    public int getPayType() {
        return this.payType;
    }

    public void setPayType(int payType2) {
        this.payType = payType2;
    }

    public int getMoreCharge() {
        return this.moreCharge;
    }

    public void setMoreCharge(int moreCharge2) {
        this.moreCharge = moreCharge2;
    }

    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String productName2) {
        this.productName = productName2;
    }

    public int getRate() {
        return this.rate;
    }

    public void setRate(int rate2) {
        this.rate = rate2;
    }

    public String getGameGold() {
        return this.gameGold;
    }

    public void setGameGold(String gameGold2) {
        this.gameGold = gameGold2;
    }

    public String getExStr() {
        return this.exStr;
    }

    public void setExStr(String exStr2) {
        this.exStr = exStr2;
    }

    public String getPlatformGoodsId() {
        return this.platformGoodsId;
    }

    public void setPlatformGoodsId(String platformGoodsId2) {
        this.platformGoodsId = platformGoodsId2;
    }

    public Args getArgs() {
        return this.args;
    }

    public void setArgs(Args args2) {
        this.args = args2;
    }

    public String toString() {
        return "SDKPaymentInfo{roleId='" + this.roleId + '\'' + ", callBackStr='" + this.callBackStr + '\'' + ", money=" + this.money + ", payType=" + this.payType + ", moreCharge=" + this.moreCharge + ", productName='" + this.productName + '\'' + ", rate=" + this.rate + ", gameGold='" + this.gameGold + '\'' + ", exStr='" + this.exStr + '\'' + ", goodsId='" + this.goodsId + '\'' + ", outOrderNo='" + this.outOrderNo + '\'' + ", platformGoodsId='" + this.platformGoodsId + '\'' + ", coinsAmount = " + this.coinsAmount + '}';
    }
}
