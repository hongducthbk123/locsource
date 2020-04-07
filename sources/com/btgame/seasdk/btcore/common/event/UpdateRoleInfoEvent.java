package com.btgame.seasdk.btcore.common.event;

import com.btgame.seasdk.btcore.common.entity.RoleInfo;

public class UpdateRoleInfoEvent {
    private RoleInfo roleInfo;

    public UpdateRoleInfoEvent(RoleInfo roleInfo2) {
        this.roleInfo = roleInfo2;
    }

    public RoleInfo getRoleInfo() {
        return this.roleInfo;
    }

    public void setRoleInfo(RoleInfo roleInfo2) {
        this.roleInfo = roleInfo2;
    }
}
