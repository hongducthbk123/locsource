package com.adjust.sdk;

import org.json.JSONObject;

public class EventResponseData extends ResponseData {
    public String eventToken;

    public EventResponseData(ActivityPackage activityPackage) {
        this.eventToken = (String) activityPackage.getParameters().get("event_token");
    }

    public AdjustEventSuccess getSuccessResponseData() {
        if (!this.success) {
            return null;
        }
        AdjustEventSuccess successResponseData = new AdjustEventSuccess();
        successResponseData.message = this.message;
        successResponseData.timestamp = this.timestamp;
        successResponseData.adid = this.adid;
        if (this.jsonResponse != null) {
            successResponseData.jsonResponse = this.jsonResponse;
        } else {
            successResponseData.jsonResponse = new JSONObject();
        }
        successResponseData.eventToken = this.eventToken;
        return successResponseData;
    }

    public AdjustEventFailure getFailureResponseData() {
        if (this.success) {
            return null;
        }
        AdjustEventFailure failureResponseData = new AdjustEventFailure();
        failureResponseData.message = this.message;
        failureResponseData.timestamp = this.timestamp;
        failureResponseData.adid = this.adid;
        failureResponseData.willRetry = this.willRetry;
        if (this.jsonResponse != null) {
            failureResponseData.jsonResponse = this.jsonResponse;
        } else {
            failureResponseData.jsonResponse = new JSONObject();
        }
        failureResponseData.eventToken = this.eventToken;
        return failureResponseData;
    }
}
