package com.adjust.sdk;

public enum LogLevel {
    VERBOSE(2),
    DEBUG(3),
    INFO(4),
    WARN(5),
    ERROR(6),
    ASSERT(7),
    SUPRESS(8);
    
    final int androidLogLevel;

    private LogLevel(int androidLogLevel2) {
        this.androidLogLevel = androidLogLevel2;
    }

    public int getAndroidLogLevel() {
        return this.androidLogLevel;
    }
}
