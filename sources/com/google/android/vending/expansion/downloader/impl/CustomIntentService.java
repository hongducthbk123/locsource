package com.google.android.vending.expansion.downloader.impl;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

public abstract class CustomIntentService extends Service {
    private static final String LOG_TAG = "CancellableIntentService";
    private static final int WHAT_MESSAGE = -10;
    private String mName;
    private boolean mRedelivery;
    private volatile ServiceHandler mServiceHandler;
    private volatile Looper mServiceLooper;

    private final class ServiceHandler extends Handler {
        public ServiceHandler(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message paramMessage) {
            CustomIntentService.this.onHandleIntent((Intent) paramMessage.obj);
            if (CustomIntentService.this.shouldStop()) {
                Log.d(CustomIntentService.LOG_TAG, "stopSelf");
                CustomIntentService.this.stopSelf(paramMessage.arg1);
                Log.d(CustomIntentService.LOG_TAG, "afterStopSelf");
            }
        }
    }

    /* access modifiers changed from: protected */
    public abstract void onHandleIntent(Intent intent);

    /* access modifiers changed from: protected */
    public abstract boolean shouldStop();

    public CustomIntentService(String paramString) {
        this.mName = paramString;
    }

    public IBinder onBind(Intent paramIntent) {
        return null;
    }

    public void onCreate() {
        super.onCreate();
        HandlerThread localHandlerThread = new HandlerThread("IntentService[" + this.mName + "]");
        localHandlerThread.start();
        this.mServiceLooper = localHandlerThread.getLooper();
        this.mServiceHandler = new ServiceHandler(this.mServiceLooper);
    }

    public void onDestroy() {
        Thread localThread = this.mServiceLooper.getThread();
        if (localThread != null && localThread.isAlive()) {
            localThread.interrupt();
        }
        this.mServiceLooper.quit();
        Log.d(LOG_TAG, "onDestroy");
    }

    public void onStart(Intent paramIntent, int startId) {
        if (!this.mServiceHandler.hasMessages(-10)) {
            Message localMessage = this.mServiceHandler.obtainMessage();
            localMessage.arg1 = startId;
            localMessage.obj = paramIntent;
            localMessage.what = -10;
            this.mServiceHandler.sendMessage(localMessage);
        }
    }

    public int onStartCommand(Intent paramIntent, int flags, int startId) {
        onStart(paramIntent, startId);
        return this.mRedelivery ? 3 : 2;
    }

    public void setIntentRedelivery(boolean enabled) {
        this.mRedelivery = enabled;
    }
}
