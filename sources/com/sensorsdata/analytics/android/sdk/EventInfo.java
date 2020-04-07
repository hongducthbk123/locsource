package com.sensorsdata.analytics.android.sdk;

public class EventInfo {
    public final String mEventName;
    public final String mEventType;
    public final boolean mIsDeployed;
    public final String mPath;
    public final int mTriggerId;

    public EventInfo(String eventName, String eventType, String path, int triggerId, boolean isDeployed) {
        this.mEventName = eventName;
        this.mEventType = eventType;
        this.mPath = path;
        this.mTriggerId = triggerId;
        this.mIsDeployed = isDeployed;
    }

    public String toString() {
        return "EventInfo { EventName: " + this.mEventName + ", EventType: " + this.mEventType + ", Path: " + this.mPath + ", TriggerId: " + this.mTriggerId + ", IsDeployed:" + this.mIsDeployed + "}";
    }
}
