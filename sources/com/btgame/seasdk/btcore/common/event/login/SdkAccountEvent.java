package com.btgame.seasdk.btcore.common.event.login;

import com.btgame.seasdk.btcore.common.constant.EventType;
import com.btgame.seasdk.btcore.common.event.SdkEvent;

public class SdkAccountEvent extends SdkEvent {
    public SdkAccountEvent(EventType eventType) {
        super(eventType);
    }
}
