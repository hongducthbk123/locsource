package com.btgame.seasdk.btcore.common.event.data;

import com.btgame.seasdk.btcore.common.constant.TrackEventType;

public class TrackEvent {
    public Integer code;
    public String description;
    protected TrackEventType eventType;

    public static class Builder {
        private TrackEvent trackEvent = new TrackEvent();

        public Builder eventType(TrackEventType eventType) {
            this.trackEvent.eventType = eventType;
            return this;
        }

        public Builder code(Integer code) {
            this.trackEvent.code = code;
            return this;
        }

        public Builder description(String description) {
            this.trackEvent.description = description;
            return this;
        }

        public TrackEvent build() {
            return this.trackEvent;
        }
    }

    public TrackEvent() {
    }

    public TrackEvent(TrackEventType eventType2) {
        this.eventType = eventType2;
    }

    public TrackEventType getEventType() {
        return this.eventType;
    }

    public void setEventType(TrackEventType eventType2) {
        this.eventType = eventType2;
    }
}
