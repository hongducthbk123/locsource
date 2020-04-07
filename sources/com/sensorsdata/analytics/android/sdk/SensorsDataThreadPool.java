package com.sensorsdata.analytics.android.sdk;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class SensorsDataThreadPool {
    private static Executor mExecutor;
    private static SensorsDataThreadPool singleton;

    public static SensorsDataThreadPool getInstance() {
        if (singleton == null) {
            synchronized (SensorsDataThreadPool.class) {
                if (singleton == null) {
                    singleton = new SensorsDataThreadPool();
                    mExecutor = Executors.newFixedThreadPool(5);
                }
            }
        }
        return singleton;
    }

    public void execute(Runnable runnable) {
        if (runnable != null) {
            try {
                mExecutor.execute(runnable);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
