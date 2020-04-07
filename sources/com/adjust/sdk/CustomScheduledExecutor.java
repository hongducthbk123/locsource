package com.adjust.sdk;

import com.google.android.vending.expansion.downloader.Constants;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public final class CustomScheduledExecutor extends ScheduledThreadPoolExecutor {

    private static class RunnableWrapper implements Runnable {
        private Runnable runnable;

        RunnableWrapper(Runnable runnable2) {
            this.runnable = runnable2;
        }

        public void run() {
            try {
                this.runnable.run();
            } catch (Throwable t) {
                AdjustFactory.getLogger().error("Runnable error [%s] of type [%s]", t.getMessage(), t.getClass().getCanonicalName());
            }
        }
    }

    public CustomScheduledExecutor(final String source, boolean doKeepAlive) {
        super(1, new ThreadFactory() {
            public Thread newThread(Runnable runnable) {
                Thread thread = Executors.defaultThreadFactory().newThread(runnable);
                thread.setPriority(1);
                thread.setName(Constants.THREAD_PREFIX + thread.getName() + Constants.FILENAME_SEQUENCE_SEPARATOR + source);
                thread.setDaemon(true);
                thread.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
                    public void uncaughtException(Thread th, Throwable tr) {
                        AdjustFactory.getLogger().error("Thread [%s] with error [%s]", th.getName(), tr.getMessage());
                    }
                });
                return thread;
            }
        }, new RejectedExecutionHandler() {
            public void rejectedExecution(Runnable runnable, ThreadPoolExecutor executor) {
                AdjustFactory.getLogger().warn("Runnable [%s] rejected from [%s] ", runnable.toString(), source);
            }
        });
        if (!doKeepAlive) {
            setKeepAliveTime(10, TimeUnit.MILLISECONDS);
            allowCoreThreadTimeOut(true);
        }
    }

    public Future<?> submit(Runnable task) {
        return super.submit(new RunnableWrapper(task));
    }

    public ScheduledFuture<?> scheduleWithFixedDelay(Runnable command, long initialDelay, long delay, TimeUnit unit) {
        return super.scheduleWithFixedDelay(new RunnableWrapper(command), initialDelay, delay, unit);
    }

    public ScheduledFuture<?> schedule(Runnable command, long delay, TimeUnit unit) {
        return super.schedule(new RunnableWrapper(command), delay, unit);
    }
}
