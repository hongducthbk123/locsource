package com.tencent.bugly.proguard;

import com.tencent.bugly.C1925b;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/* renamed from: com.tencent.bugly.proguard.v */
/* compiled from: BUGLY */
public final class C2012v {

    /* renamed from: a */
    private static C2012v f1690a;

    /* renamed from: b */
    private ScheduledExecutorService f1691b;

    protected C2012v() {
        this.f1691b = null;
        this.f1691b = Executors.newScheduledThreadPool(3, new ThreadFactory(this) {
            public final Thread newThread(Runnable runnable) {
                Thread thread = new Thread(runnable);
                thread.setName("BUGLY_THREAD");
                return thread;
            }
        });
        if (this.f1691b == null || this.f1691b.isShutdown()) {
            C2014w.m2118d("[AsyncTaskHandler] ScheduledExecutorService is not valiable!", new Object[0]);
        }
    }

    /* renamed from: a */
    public static synchronized C2012v m2106a() {
        C2012v vVar;
        synchronized (C2012v.class) {
            if (f1690a == null) {
                f1690a = new C2012v();
            }
            vVar = f1690a;
        }
        return vVar;
    }

    /* renamed from: a */
    public final synchronized boolean mo19637a(Runnable runnable, long j) {
        boolean z = false;
        synchronized (this) {
            if (!mo19639c()) {
                C2014w.m2118d("[AsyncTaskHandler] Async handler was closed, should not post task.", new Object[0]);
            } else if (runnable == null) {
                C2014w.m2118d("[AsyncTaskHandler] Task input is null.", new Object[0]);
            } else {
                if (j <= 0) {
                    j = 0;
                }
                C2014w.m2117c("[AsyncTaskHandler] Post a delay(time: %dms) task: %s", Long.valueOf(j), runnable.getClass().getName());
                try {
                    this.f1691b.schedule(runnable, j, TimeUnit.MILLISECONDS);
                    z = true;
                } catch (Throwable th) {
                    if (C1925b.f1097c) {
                        th.printStackTrace();
                    }
                }
            }
        }
        return z;
    }

    /* renamed from: a */
    public final synchronized boolean mo19636a(Runnable runnable) {
        boolean z = false;
        synchronized (this) {
            if (!mo19639c()) {
                C2014w.m2118d("[AsyncTaskHandler] Async handler was closed, should not post task.", new Object[0]);
            } else if (runnable == null) {
                C2014w.m2118d("[AsyncTaskHandler] Task input is null.", new Object[0]);
            } else {
                C2014w.m2117c("[AsyncTaskHandler] Post a normal task: %s", runnable.getClass().getName());
                try {
                    this.f1691b.execute(runnable);
                    z = true;
                } catch (Throwable th) {
                    if (C1925b.f1097c) {
                        th.printStackTrace();
                    }
                }
            }
        }
        return z;
    }

    /* renamed from: b */
    public final synchronized void mo19638b() {
        if (this.f1691b != null && !this.f1691b.isShutdown()) {
            C2014w.m2117c("[AsyncTaskHandler] Close async handler.", new Object[0]);
            this.f1691b.shutdownNow();
        }
    }

    /* renamed from: c */
    public final synchronized boolean mo19639c() {
        return this.f1691b != null && !this.f1691b.isShutdown();
    }
}
