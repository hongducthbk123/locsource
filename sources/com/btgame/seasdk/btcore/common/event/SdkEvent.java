package com.btgame.seasdk.btcore.common.event;

import com.btgame.seasdk.btcore.common.constant.EventType;

public class SdkEvent {
    protected EventType eventType;

    protected SdkEvent() {
    }

    public SdkEvent(EventType eventType2) {
        this.eventType = eventType2;
    }

    public EventType getEventType() {
        return this.eventType;
    }

    public void setEventType(EventType eventType2) {
        this.eventType = eventType2;
    }
}
