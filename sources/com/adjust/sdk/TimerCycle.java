package com.adjust.sdk;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class TimerCycle {
    /* access modifiers changed from: private */
    public Runnable command;
    private long cycleDelay;
    private CustomScheduledExecutor executor;
    private long initialDelay;
    private boolean isPaused = true;
    /* access modifiers changed from: private */
    public ILogger logger = AdjustFactory.getLogger();
    /* access modifiers changed from: private */
    public String name;
    private ScheduledFuture waitingTask;

    public TimerCycle(Runnable command2, long initialDelay2, long cycleDelay2, String name2) {
        this.executor = new CustomScheduledExecutor(name2, true);
        this.name = name2;
        this.command = command2;
        this.initialDelay = initialDelay2;
        this.cycleDelay = cycleDelay2;
        String cycleDelaySecondsString = Util.SecondsDisplayFormat.format(((double) cycleDelay2) / 1000.0d);
        this.logger.verbose("%s configured to fire after %s seconds of starting and cycles every %s seconds", name2, Util.SecondsDisplayFormat.format(((double) initialDelay2) / 1000.0d), cycleDelaySecondsString);
    }

    public void start() {
        if (!this.isPaused) {
            this.logger.verbose("%s is already started", this.name);
            return;
        }
        this.logger.verbose("%s starting", this.name);
        this.waitingTask = this.executor.scheduleWithFixedDelay(new Runnable() {
            public void run() {
                TimerCycle.this.logger.verbose("%s fired", TimerCycle.this.name);
                TimerCycle.this.command.run();
            }
        }, this.initialDelay, this.cycleDelay, TimeUnit.MILLISECONDS);
        this.isPaused = false;
    }

    public void suspend() {
        if (this.isPaused) {
            this.logger.verbose("%s is already suspended", this.name);
            return;
        }
        this.initialDelay = this.waitingTask.getDelay(TimeUnit.MILLISECONDS);
        this.waitingTask.cancel(false);
        this.logger.verbose("%s suspended with %s seconds left", this.name, Util.SecondsDisplayFormat.format(((double) this.initialDelay) / 1000.0d));
        this.isPaused = true;
    }

    private void cancel(boolean mayInterruptIfRunning) {
        if (this.waitingTask != null) {
            this.waitingTask.cancel(mayInterruptIfRunning);
        }
        this.waitingTask = null;
    }

    public void teardown() {
        cancel(true);
        if (this.executor != null) {
            try {
                this.executor.shutdownNow();
            } catch (SecurityException e) {
            }
        }
        this.executor = null;
    }
}
