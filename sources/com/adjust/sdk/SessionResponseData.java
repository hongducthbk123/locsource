package com.adjust.sdk;

import org.json.JSONObject;

public class SessionResponseData extends ResponseData {
    public AdjustSessionSuccess getSuccessResponseData() {
        if (!this.success) {
            return null;
        }
        AdjustSessionSuccess successResponseData = new AdjustSessionSuccess();
        successResponseData.message = this.message;
        successResponseData.timestamp = this.timestamp;
        successResponseData.adid = this.adid;
        if (this.jsonResponse != null) {
            successResponseData.jsonResponse = this.jsonResponse;
            return successResponseData;
        }
        successResponseData.jsonResponse = new JSONObject();
        return successResponseData;
    }

    public AdjustSessionFailure getFailureResponseData() {
        if (this.success) {
            return null;
        }
        AdjustSessionFailure failureResponseData = new AdjustSessionFailure();
        failureResponseData.message = this.message;
        failureResponseData.timestamp = this.timestamp;
        failureResponseData.adid = this.adid;
        failureResponseData.willRetry = this.willRetry;
        if (this.jsonResponse != null) {
            failureResponseData.jsonResponse = this.jsonResponse;
            return failureResponseData;
        }
        failureResponseData.jsonResponse = new JSONObject();
        return failureResponseData;
    }
}
