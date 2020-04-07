package com.sensorsdata.analytics.android.sdk;

import android.os.SystemClock;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

class EventTimer {
    private long eventAccumulatedDuration;
    private long startTime = SystemClock.elapsedRealtime();
    private final TimeUnit timeUnit;

    EventTimer(TimeUnit timeUnit2) {
        this.timeUnit = timeUnit2;
        this.eventAccumulatedDuration = 0;
    }

    /* access modifiers changed from: 0000 */
    public String duration() {
        float durationFloat;
        long duration = (SystemClock.elapsedRealtime() - this.startTime) + this.eventAccumulatedDuration;
        if (duration < 0 || duration > 86400000) {
            try {
                return String.valueOf(0);
            } catch (Exception e) {
                e.printStackTrace();
                return String.valueOf(0);
            }
        } else {
            if (this.timeUnit == TimeUnit.MILLISECONDS) {
                durationFloat = (float) duration;
            } else if (this.timeUnit == TimeUnit.SECONDS) {
                durationFloat = ((float) duration) / 1000.0f;
            } else if (this.timeUnit == TimeUnit.MINUTES) {
                durationFloat = (((float) duration) / 1000.0f) / 60.0f;
            } else if (this.timeUnit == TimeUnit.HOURS) {
                durationFloat = ((((float) duration) / 1000.0f) / 60.0f) / 60.0f;
            } else {
                durationFloat = (float) duration;
            }
            if (durationFloat < 0.0f) {
                return String.valueOf(0);
            }
            return String.format(Locale.CHINA, "%.3f", new Object[]{Float.valueOf(durationFloat)});
        }
    }

    public long getStartTime() {
        return this.startTime;
    }

    public long getEventAccumulatedDuration() {
        return this.eventAccumulatedDuration;
    }

    public void setStartTime(long startTime2) {
        this.startTime = startTime2;
    }

    public void setEventAccumulatedDuration(long eventAccumulatedDuration2) {
        this.eventAccumulatedDuration = eventAccumulatedDuration2;
    }
}
