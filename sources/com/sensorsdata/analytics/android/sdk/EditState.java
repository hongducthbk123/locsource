package com.sensorsdata.analytics.android.sdk;

import android.app.Activity;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import com.google.android.vending.expansion.downloader.Constants;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class EditState extends UIThreadSet<Activity> {
    private static final String LOGTAG = "SA.EditState";
    private final Map<Activity, Set<EditBinding>> mCurrentEdits = new HashMap();
    private final Map<String, List<ViewVisitor>> mIntendedEdits = new HashMap();
    private final Handler mUiThreadHandler = new Handler(Looper.getMainLooper());

    private static class EditBinding implements OnGlobalLayoutListener, Runnable {
        private boolean mAlive = true;
        private volatile boolean mDying = false;
        private final ViewVisitor mEdit;
        private final Handler mHandler;
        private final WeakReference<View> mViewRoot;

        public EditBinding(View viewRoot, ViewVisitor edit, Handler uiThreadHandler) {
            this.mEdit = edit;
            this.mViewRoot = new WeakReference<>(viewRoot);
            this.mHandler = uiThreadHandler;
            ViewTreeObserver observer = viewRoot.getViewTreeObserver();
            if (observer.isAlive()) {
                observer.addOnGlobalLayoutListener(this);
            }
            run();
        }

        public void onGlobalLayout() {
            run();
        }

        public void run() {
            if (this.mAlive) {
                View viewRoot = (View) this.mViewRoot.get();
                if (viewRoot == null || this.mDying) {
                    cleanUp();
                    return;
                }
                this.mEdit.visit(viewRoot);
                this.mHandler.removeCallbacks(this);
                this.mHandler.postDelayed(this, Constants.ACTIVE_THREAD_WATCHDOG);
            }
        }

        public void kill() {
            this.mDying = true;
            this.mHandler.post(this);
        }

        private void cleanUp() {
            if (this.mAlive) {
                View viewRoot = (View) this.mViewRoot.get();
                if (viewRoot != null) {
                    ViewTreeObserver observer = viewRoot.getViewTreeObserver();
                    if (observer.isAlive()) {
                        if (VERSION.SDK_INT < 16) {
                            observer.removeGlobalOnLayoutListener(this);
                        } else {
                            observer.removeOnGlobalLayoutListener(this);
                        }
                    }
                }
                this.mEdit.cleanup();
            }
            this.mAlive = false;
        }
    }

    public void add(Activity newOne) {
        super.add(newOne);
        applyEditsOnActivity(newOne);
    }

    public void remove(Activity oldOne) {
        super.remove(oldOne);
        removeChangesOnActivity(oldOne);
    }

    public void setEdits(Map<String, List<ViewVisitor>> newEdits) {
        synchronized (this.mCurrentEdits) {
            for (Entry<Activity, Set<EditBinding>> entry : this.mCurrentEdits.entrySet()) {
                for (EditBinding binding : (Set) entry.getValue()) {
                    binding.kill();
                }
            }
            this.mCurrentEdits.clear();
        }
        synchronized (this.mIntendedEdits) {
            this.mIntendedEdits.clear();
            this.mIntendedEdits.putAll(newEdits);
        }
        applyEditsOnUiThread();
    }

    private void applyEditsOnUiThread() {
        if (Thread.currentThread() == this.mUiThreadHandler.getLooper().getThread()) {
            applyIntendedEdits();
        } else {
            this.mUiThreadHandler.post(new Runnable() {
                public void run() {
                    EditState.this.applyIntendedEdits();
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void applyIntendedEdits() {
        for (Activity activity : getAll()) {
            applyEditsOnActivity(activity);
        }
    }

    private void applyEditsOnActivity(Activity activity) {
        List<ViewVisitor> specificChanges;
        List<ViewVisitor> wildcardChanges;
        String activityName = activity.getClass().getCanonicalName();
        View rootView = activity.getWindow().getDecorView().getRootView();
        synchronized (this.mIntendedEdits) {
            specificChanges = (List) this.mIntendedEdits.get(activityName);
            wildcardChanges = (List) this.mIntendedEdits.get(null);
        }
        if (specificChanges != null) {
            applyChangesFromList(activity, rootView, specificChanges);
        }
        if (wildcardChanges != null) {
            applyChangesFromList(activity, rootView, wildcardChanges);
        }
    }

    private void applyChangesFromList(Activity activity, View rootView, List<ViewVisitor> changes) {
        synchronized (this.mCurrentEdits) {
            if (!this.mCurrentEdits.containsKey(activity)) {
                this.mCurrentEdits.put(activity, new HashSet());
            }
            int size = changes.size();
            for (int i = 0; i < size; i++) {
                ((Set) this.mCurrentEdits.get(activity)).add(new EditBinding(rootView, (ViewVisitor) changes.get(i), this.mUiThreadHandler));
            }
        }
    }

    private void removeChangesOnActivity(Activity activity) {
        synchronized (this.mCurrentEdits) {
            Set<EditBinding> bindingSet = (Set) this.mCurrentEdits.get(activity);
            if (bindingSet != null) {
                for (EditBinding binding : bindingSet) {
                    binding.kill();
                }
                this.mCurrentEdits.remove(activity);
            }
        }
    }
}
