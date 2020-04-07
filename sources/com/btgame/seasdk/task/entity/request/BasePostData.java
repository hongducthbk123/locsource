package com.btgame.seasdk.task.entity.request;

import com.btgame.seasdk.btcore.common.entity.DeviceProperties;
import com.btgame.seasdk.task.entity.OpType;

public class BasePostData {
    protected Integer appId;
    protected DeviceProperties device;
    protected String identity;
    protected transient OpType opType;

    public OpType getOpType() {
        return this.opType;
    }

    public void setOpType(OpType opType2) {
        this.opType = opType2;
    }

    public Integer getAppId() {
        return this.appId;
    }

    public void setAppId(Integer appId2) {
        this.appId = appId2;
    }

    public String getIdentity() {
        return this.identity;
    }

    public void setIdentity(String identity2) {
        this.identity = identity2;
    }

    public DeviceProperties getDevice() {
        return this.device;
    }

    public void setDevice(DeviceProperties device2) {
        this.device = device2;
    }
}
