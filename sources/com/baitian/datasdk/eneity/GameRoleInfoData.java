package com.baitian.datasdk.eneity;

import android.content.Context;

public class GameRoleInfoData extends AccountInfoData {
    private int VIPLevel;
    private int areaId;
    private String areaName;
    private long basicAccount;
    private long charId;
    private int isVIP;
    private long presentAccount;
    private String roleRegTime;
    private String userBirthday;
    private int userGender;
    private long userId;
    private int userLevel;
    private String userName;

    public GameRoleInfoData(Context ctx) {
        super(ctx);
    }

    public int getAreaId() {
        return this.areaId;
    }

    public void setAreaId(int areaId2) {
        this.areaId = areaId2;
    }

    public String getAreaName() {
        return this.areaName;
    }

    public void setAreaName(String areaName2) {
        this.areaName = areaName2;
    }

    public long getUserId() {
        return this.userId;
    }

    public void setUserId(long userId2) {
        this.userId = userId2;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName2) {
        this.userName = userName2;
    }

    public long getCharId() {
        return this.charId;
    }

    public void setCharId(long charId2) {
        this.charId = charId2;
    }

    public String getUserBirthday() {
        return this.userBirthday;
    }

    public void setUserBirthday(String userBirthday2) {
        this.userBirthday = userBirthday2;
    }

    public int getUserGender() {
        return this.userGender;
    }

    public void setUserGender(int userGender2) {
        this.userGender = userGender2;
    }

    public int getUserLevel() {
        return this.userLevel;
    }

    public void setUserLevel(int userLevel2) {
        this.userLevel = userLevel2;
    }

    public String getRoleRegTime() {
        return this.roleRegTime;
    }

    public void setRoleRegTime(String roleRegTime2) {
        this.roleRegTime = roleRegTime2;
    }

    public int getVIPLevel() {
        return this.VIPLevel;
    }

    public void setVIPLevel(int VIPLevel2) {
        this.VIPLevel = VIPLevel2;
    }

    public int getIsVIP() {
        return this.isVIP;
    }

    public void setIsVIP(int isVIP2) {
        this.isVIP = isVIP2;
    }

    public long getBasicAccount() {
        return this.basicAccount;
    }

    public void setBasicAccount(long basicAccount2) {
        this.basicAccount = basicAccount2;
    }

    public long getPresentAccount() {
        return this.presentAccount;
    }

    public void setPresentAccount(long presentAccount2) {
        this.presentAccount = presentAccount2;
    }
}
