package com.sensorsdata.analytics.android.sdk.exceptions;

public class QueueLimitExceededException extends Exception {
    public QueueLimitExceededException(String error) {
        super(error);
    }

    public QueueLimitExceededException(Throwable throwable) {
        super(throwable);
    }
}
