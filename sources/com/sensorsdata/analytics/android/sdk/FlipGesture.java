package com.sensorsdata.analytics.android.sdk;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

public class FlipGesture implements SensorEventListener {
    private static final float ACCELEROMETER_SMOOTHING = 0.7f;
    private static final String LOGTAG = "SA.FlipGesture";
    private static final long MINIMUM_CANCEL_DURATION = 1000000000;
    private static final long MINIMUM_TRIGGER_DURATION = 250000000;
    private static final int MINIMUM_TRIGGER_SPEED = 300;
    private static final int TRIGGER_STATE_BEGIN = 0;
    private static final int TRIGGER_STATE_NONE = -1;
    private static final int TRIGGER_STATE_OK = 1;
    private long mLastFlipTime = -1;
    private final OnFlipGestureListener mListener;
    private int mTriggerState = -1;

    public interface OnFlipGestureListener {
        void onFlipGesture();
    }

    public FlipGesture(OnFlipGestureListener listener) {
        this.mListener = listener;
    }

    public void onSensorChanged(SensorEvent event) {
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];
        if ((x * x) + (y * y) + (z * z) > 300.0f) {
            switch (this.mTriggerState) {
                case -1:
                    this.mLastFlipTime = event.timestamp;
                    this.mTriggerState = 0;
                    return;
                case 0:
                    if (event.timestamp - this.mLastFlipTime > MINIMUM_TRIGGER_DURATION) {
                        this.mListener.onFlipGesture();
                        this.mTriggerState = 1;
                        return;
                    }
                    return;
                default:
                    return;
            }
        } else {
            this.mTriggerState = -1;
        }
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
}
