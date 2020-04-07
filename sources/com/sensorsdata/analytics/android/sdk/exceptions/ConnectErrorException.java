package com.sensorsdata.analytics.android.sdk.exceptions;

import com.tencent.bugly.BuglyStrategy.C1910a;

public class ConnectErrorException extends Exception {
    private int mRetryAfter;

    public ConnectErrorException(String message) {
        super(message);
        this.mRetryAfter = C1910a.MAX_USERDATA_VALUE_LENGTH;
    }

    public ConnectErrorException(String message, String strRetryAfter) {
        super(message);
        try {
            this.mRetryAfter = Integer.parseInt(strRetryAfter);
        } catch (NumberFormatException e) {
            this.mRetryAfter = 0;
        }
    }

    public ConnectErrorException(Throwable throwable) {
        super(throwable);
    }

    public int getRetryAfter() {
        return this.mRetryAfter;
    }
}
