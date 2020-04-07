package com.adjust.sdk;

public enum BackoffStrategy {
    LONG_WAIT(1, 120000, 86400000, 0.5d, 1.0d),
    SHORT_WAIT(1, 200, 3600000, 0.5d, 1.0d),
    TEST_WAIT(1, 200, 1000, 0.5d, 1.0d),
    NO_WAIT(100, 1, 1000, 1.0d, 1.0d);
    
    double maxRange;
    long maxWait;
    long milliSecondMultiplier;
    double minRange;
    int minRetries;

    private BackoffStrategy(int minRetries2, long milliSecondMultiplier2, long maxWait2, double minRange2, double maxRange2) {
        this.minRetries = minRetries2;
        this.milliSecondMultiplier = milliSecondMultiplier2;
        this.maxWait = maxWait2;
        this.minRange = minRange2;
        this.maxRange = maxRange2;
    }
}
