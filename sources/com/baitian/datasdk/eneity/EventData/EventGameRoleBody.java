package com.baitian.datasdk.eneity.EventData;

public class EventGameRoleBody extends EventAccountBody {
    public EventGameRoleBody(String accountId, String serverId, String serverName, String roleId, String roleLevel) {
        super(accountId);
        this.areaId = serverId;
        this.areaName = serverName;
        this.charId = roleId;
        this.userLevel = roleLevel;
    }
}
