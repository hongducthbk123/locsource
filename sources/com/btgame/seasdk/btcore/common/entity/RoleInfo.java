package com.btgame.seasdk.btcore.common.entity;

public class RoleInfo {
    private long battleStrength;
    private String guildId;
    private String guildLeaderName;
    private String guildLeaderRoleId;
    private String guildLeaderUserId;
    private int guildLevel;
    private String guildName;
    private String loginTime;
    private long roleCTime;
    private long roleExp;
    private String roleId;
    private int roleLevel;
    private String roleName;
    private String serverId;
    private String serverName;
    private String userId;
    private int vipLevel;

    public static final class Builder {
        /* access modifiers changed from: private */
        public long battleStrength;
        /* access modifiers changed from: private */
        public String guildId;
        /* access modifiers changed from: private */
        public String guildLeaderName;
        /* access modifiers changed from: private */
        public String guildLeaderRoleId;
        /* access modifiers changed from: private */
        public String guildLeaderUserId;
        /* access modifiers changed from: private */
        public int guildLevel;
        /* access modifiers changed from: private */
        public String guildName;
        /* access modifiers changed from: private */
        public String loginTime;
        /* access modifiers changed from: private */
        public long roleCTime;
        /* access modifiers changed from: private */
        public long roleExp;
        /* access modifiers changed from: private */
        public String roleId;
        /* access modifiers changed from: private */
        public int roleLevel;
        /* access modifiers changed from: private */
        public String roleName;
        /* access modifiers changed from: private */
        public String serverId;
        /* access modifiers changed from: private */
        public String serverName;
        /* access modifiers changed from: private */
        public String userId;
        /* access modifiers changed from: private */
        public int vipLevel;

        public Builder serverId(String val) {
            this.serverId = val;
            return this;
        }

        public Builder serverName(String val) {
            this.serverName = val;
            return this;
        }

        public Builder userId(String val) {
            this.userId = val;
            return this;
        }

        public Builder roleId(String val) {
            this.roleId = val;
            return this;
        }

        public Builder roleName(String val) {
            this.roleName = val;
            return this;
        }

        public Builder roleLevel(int val) {
            this.roleLevel = val;
            return this;
        }

        public Builder vipLevel(int val) {
            this.vipLevel = val;
            return this;
        }

        public Builder roleExp(long val) {
            this.roleExp = val;
            return this;
        }

        public Builder roleCTime(long val) {
            this.roleCTime = val;
            return this;
        }

        public Builder loginTime(String val) {
            this.loginTime = val;
            return this;
        }

        public Builder guildId(String val) {
            this.guildId = val;
            return this;
        }

        public Builder guildName(String val) {
            this.guildName = val;
            return this;
        }

        public Builder guildLevel(int val) {
            this.guildLevel = val;
            return this;
        }

        public Builder guildLeaderUserId(String val) {
            this.guildLeaderUserId = val;
            return this;
        }

        public Builder guildLeaderRoleId(String val) {
            this.guildLeaderRoleId = val;
            return this;
        }

        public Builder guildLeaderName(String val) {
            this.guildLeaderName = val;
            return this;
        }

        public Builder battleStrength(long val) {
            this.battleStrength = val;
            return this;
        }

        public RoleInfo build() {
            return new RoleInfo(this);
        }
    }

    private RoleInfo() {
    }

    private RoleInfo(Builder builder) {
        this.serverId = builder.serverId;
        this.serverName = builder.serverName;
        this.userId = builder.userId;
        this.roleId = builder.roleId;
        this.roleName = builder.roleName;
        this.roleLevel = builder.roleLevel;
        this.vipLevel = builder.vipLevel;
        this.roleExp = builder.roleExp;
        this.roleCTime = builder.roleCTime;
        this.loginTime = builder.loginTime;
        this.guildId = builder.guildId;
        this.guildName = builder.guildName;
        this.guildLevel = builder.guildLevel;
        this.guildLeaderUserId = builder.guildLeaderUserId;
        this.guildLeaderRoleId = builder.guildLeaderRoleId;
        this.guildLeaderName = builder.guildLeaderName;
        this.battleStrength = builder.battleStrength;
    }

    public String getServerId() {
        return this.serverId;
    }

    public String getServerName() {
        return this.serverName;
    }

    public String getUserId() {
        return this.userId;
    }

    public String getRoleId() {
        return this.roleId;
    }

    public String getRoleName() {
        return this.roleName;
    }

    public int getRoleLevel() {
        return this.roleLevel;
    }

    public int getVipLevel() {
        return this.vipLevel;
    }

    public long getRoleExp() {
        return this.roleExp;
    }

    public long getRoleCTime() {
        return this.roleCTime;
    }

    public String getLoginTime() {
        return this.loginTime;
    }

    public String getGuildId() {
        return this.guildId;
    }

    public String getGuildName() {
        return this.guildName;
    }

    public int getGuildLevel() {
        return this.guildLevel;
    }

    public String getGuildLeaderUserId() {
        return this.guildLeaderUserId;
    }

    public String getGuildLeaderRoleId() {
        return this.guildLeaderRoleId;
    }

    public String getGuildLeaderName() {
        return this.guildLeaderName;
    }

    public long getBattleStrength() {
        return this.battleStrength;
    }
}
