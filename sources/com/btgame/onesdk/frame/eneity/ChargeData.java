package com.btgame.onesdk.frame.eneity;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;

public class ChargeData {
    public String callBackInfo;
    public String filterParam;
    public String goodsDesc;
    public String goodsId;
    public int money;
    public String outOrderNo;
    public String roleId;
    public int roleLevel;
    public String roleName;
    public String serverId;
    public String serverName;
    public String userId;

    public String toString() {
        return "[ roleId = " + this.roleId + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + this.roleName + " roleLevel=" + this.roleLevel + " serverId = " + this.serverId + " serverName =" + this.serverName + " money =" + this.money + " callBackInfo=" + this.callBackInfo + " goodsDesc=" + this.goodsDesc + " goodsId=" + this.goodsId + " ]";
    }
}
