package com.btgame.onesdk.frame.eneity.onesdk;

public class GameRoleInfo {
    private long expGain;
    private String gameRoleBalance;
    private String guildId;
    private String guildLeader;
    private int guildLevel;
    private String guildName;
    private String lastLogoutTime;
    private String lastOperation;
    private String loginTime;
    private String logoutTime;
    private String onlineTime;
    private long power;
    private long registerTime;
    private long roleCTime;
    private long roleExp;
    private String roleId;
    private int roleLevel;
    private long roleLevelMTime;
    private String roleName;
    private String scene;
    private String serverId;
    private String serverName;
    private int type;
    private int vipLevel;

    public long getRegisterTime() {
        return this.registerTime;
    }

    public void setRegisterTime(long registerTime2) {
        this.registerTime = registerTime2;
    }

    public String getOnlineTime() {
        return this.onlineTime;
    }

    public void setOnlineTime(String onlineTime2) {
        this.onlineTime = onlineTime2;
    }

    public long getExpGain() {
        return this.expGain;
    }

    public void setExpGain(long expGain2) {
        this.expGain = expGain2;
    }

    public String getLoginTime() {
        return this.loginTime;
    }

    public void setLoginTime(String loginTime2) {
        this.loginTime = loginTime2;
    }

    public String getLogoutTime() {
        return this.logoutTime;
    }

    public void setLogoutTime(String logoutTime2) {
        this.logoutTime = logoutTime2;
    }

    public String getLastLogoutTime() {
        return this.lastLogoutTime;
    }

    public void setLastLogoutTime(String lastLogoutTime2) {
        this.lastLogoutTime = lastLogoutTime2;
    }

    public long getRoleExp() {
        return this.roleExp;
    }

    public void setRoleExp(long roleExp2) {
        this.roleExp = roleExp2;
    }

    public String getScene() {
        return this.scene;
    }

    public void setScene(String scene2) {
        this.scene = scene2;
    }

    public String getLastOperation() {
        return this.lastOperation;
    }

    public void setLastOperation(String lastOperation2) {
        this.lastOperation = lastOperation2;
    }

    public long getRoleCTime() {
        return this.roleCTime;
    }

    public void setRoleCTime(long roleCTime2) {
        this.roleCTime = roleCTime2;
    }

    public int getVipLevel() {
        return this.vipLevel;
    }

    public void setVipLevel(int vipLevel2) {
        this.vipLevel = vipLevel2;
    }

    public String getRoleName() {
        return this.roleName;
    }

    public void setRoleName(String roleName2) {
        this.roleName = roleName2;
    }

    public String getRoleId() {
        return this.roleId;
    }

    public void setRoleId(String roleId2) {
        this.roleId = roleId2;
    }

    public int getRoleLevel() {
        return this.roleLevel;
    }

    public void setRoleLevel(int roleLevel2) {
        this.roleLevel = roleLevel2;
    }

    public String getServerName() {
        return this.serverName;
    }

    public void setServerName(String serverName2) {
        this.serverName = serverName2;
    }

    public String getServerId() {
        return this.serverId;
    }

    public void setServerId(String serverId2) {
        this.serverId = serverId2;
    }

    public long getRoleLevelMTime() {
        return this.roleLevelMTime;
    }

    public void setRoleLevelMTime(long roleLevelMTime2) {
        this.roleLevelMTime = roleLevelMTime2;
    }

    public long getPower() {
        return this.power;
    }

    public void setPower(long power2) {
        this.power = power2;
    }

    public String getGameRoleBalance() {
        return this.gameRoleBalance;
    }

    public void setGameRoleBalance(String gameRoleBalance2) {
        this.gameRoleBalance = gameRoleBalance2;
    }

    public String getGuildId() {
        return this.guildId;
    }

    public void setGuildId(String guildId2) {
        this.guildId = guildId2;
    }

    public String getGuildName() {
        return this.guildName;
    }

    public void setGuildName(String guildName2) {
        this.guildName = guildName2;
    }

    public int getGuildLevel() {
        return this.guildLevel;
    }

    public void setGuildLevel(int guildLevel2) {
        this.guildLevel = guildLevel2;
    }

    public String getGuildLeader() {
        return this.guildLeader;
    }

    public void setGuildLeader(String guildLeader2) {
        this.guildLeader = guildLeader2;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type2) {
        this.type = type2;
    }

    public String toString() {
        return "roleCTime = " + this.roleCTime + " serverId =" + this.serverId + " serverName = " + this.serverName + " roleName=" + this.roleName + " roleId=" + this.roleId + " roleLevel=" + this.roleLevel;
    }
}
