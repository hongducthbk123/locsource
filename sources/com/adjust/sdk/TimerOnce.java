package com.adjust.sdk;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class TimerOnce {
    /* access modifiers changed from: private */
    public Runnable command;
    private CustomScheduledExecutor executor;
    /* access modifiers changed from: private */
    public ILogger logger = AdjustFactory.getLogger();
    /* access modifiers changed from: private */
    public String name;
    /* access modifiers changed from: private */
    public ScheduledFuture waitingTask;

    public TimerOnce(Runnable command2, String name2) {
        this.name = name2;
        this.executor = new CustomScheduledExecutor(name2, true);
        this.command = command2;
    }

    public void startIn(long fireIn) {
        cancel(false);
        this.logger.verbose("%s starting. Launching in %s seconds", this.name, Util.SecondsDisplayFormat.format(((double) fireIn) / 1000.0d));
        this.waitingTask = this.executor.schedule(new Runnable() {
            public void run() {
                TimerOnce.this.logger.verbose("%s fired", TimerOnce.this.name);
                TimerOnce.this.command.run();
                TimerOnce.this.waitingTask = null;
            }
        }, fireIn, TimeUnit.MILLISECONDS);
    }

    public long getFireIn() {
        if (this.waitingTask == null) {
            return 0;
        }
        return this.waitingTask.getDelay(TimeUnit.MILLISECONDS);
    }

    private void cancel(boolean mayInterruptIfRunning) {
        if (this.waitingTask != null) {
            this.waitingTask.cancel(mayInterruptIfRunning);
        }
        this.waitingTask = null;
        this.logger.verbose("%s canceled", this.name);
    }

    public void cancel() {
        cancel(false);
    }

    public void teardown() {
        cancel(true);
        this.executor = null;
    }
}
