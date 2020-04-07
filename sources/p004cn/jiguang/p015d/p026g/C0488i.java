package p004cn.jiguang.p015d.p026g;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import p004cn.jiguang.p029e.C0501d;

/* renamed from: cn.jiguang.d.g.i */
public final class C0488i {

    /* renamed from: e */
    private static C0488i f521e = new C0488i();

    /* renamed from: a */
    private ArrayList<WeakReference<ScheduledFuture<?>>> f522a = new ArrayList<>();

    /* renamed from: b */
    private ExecutorService f523b = Executors.newSingleThreadExecutor();

    /* renamed from: c */
    private ScheduledExecutorService f524c = Executors.newSingleThreadScheduledExecutor();

    /* renamed from: d */
    private long f525d = 5;

    private C0488i() {
    }

    /* renamed from: a */
    public static C0488i m817a() {
        return f521e;
    }

    /* renamed from: a */
    public static void m818a(ExecutorService executorService) {
        if (executorService == null) {
            C0501d.m907c("ThreadUtil", "executor was null");
            return;
        }
        try {
            executorService.shutdown();
            if (!executorService.awaitTermination(100, TimeUnit.MILLISECONDS)) {
                executorService.shutdownNow();
                executorService.awaitTermination(100, TimeUnit.MILLISECONDS);
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    /* renamed from: a */
    public final void mo6629a(Runnable runnable) {
        if (this.f523b.isShutdown()) {
            this.f523b = Executors.newSingleThreadExecutor();
        }
        try {
            this.f523b.execute(runnable);
        } catch (RejectedExecutionException e) {
        }
    }
}
