package com.btgame.seasdk.btcore.common.event.init;

import com.btgame.seasdk.btcore.common.constant.EventType;
import com.btgame.seasdk.btcore.common.event.SdkEvent;

public class ReqInitEvent extends SdkEvent {

    public static final class Builder {
        public ReqInitEvent build() {
            return new ReqInitEvent(this);
        }
    }

    private ReqInitEvent(Builder builder) {
        setEventType(EventType.INIT_REQ);
    }
}
