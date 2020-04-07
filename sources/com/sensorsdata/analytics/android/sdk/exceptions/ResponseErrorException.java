package com.sensorsdata.analytics.android.sdk.exceptions;

public class ResponseErrorException extends Exception {
    public ResponseErrorException(String error) {
        super(error);
    }

    public ResponseErrorException(Throwable throwable) {
        super(throwable);
    }
}
