package com.sensorsdata.analytics.android.sdk;

import android.annotation.TargetApi;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.AccessibilityDelegate;
import android.widget.TextView;
import com.sensorsdata.analytics.android.sdk.Pathfinder.Accumulator;
import com.sensorsdata.analytics.android.sdk.Pathfinder.PathElement;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.WeakHashMap;

@TargetApi(16)
public abstract class ViewVisitor implements Accumulator {
    private static final String TAG = "SA.ViewVisitor";
    private final List<PathElement> mPath;
    private final Pathfinder mPathfinder = new Pathfinder();

    public static class AddAccessibilityEventVisitor extends EventTriggeringVisitor {
        /* access modifiers changed from: private */
        public final int mEventType;
        private final WeakHashMap<View, TrackingAccessibilityDelegate> mWatching = new WeakHashMap<>();

        private class TrackingAccessibilityDelegate extends AccessibilityDelegate {
            private AccessibilityDelegate mRealDelegate;

            public TrackingAccessibilityDelegate(AccessibilityDelegate realDelegate) {
                this.mRealDelegate = realDelegate;
            }

            public AccessibilityDelegate getRealDelegate() {
                return this.mRealDelegate;
            }

            public boolean willFireEvent(String eventName) {
                if (AddAccessibilityEventVisitor.this.getEventName().equals(eventName)) {
                    return true;
                }
                if (this.mRealDelegate instanceof TrackingAccessibilityDelegate) {
                    return ((TrackingAccessibilityDelegate) this.mRealDelegate).willFireEvent(eventName);
                }
                return false;
            }

            public void removeFromDelegateChain(TrackingAccessibilityDelegate other) {
                if (this.mRealDelegate == other) {
                    this.mRealDelegate = other.getRealDelegate();
                } else if (this.mRealDelegate instanceof TrackingAccessibilityDelegate) {
                    ((TrackingAccessibilityDelegate) this.mRealDelegate).removeFromDelegateChain(other);
                }
            }

            public void sendAccessibilityEvent(View host, int eventType) {
                if (eventType == AddAccessibilityEventVisitor.this.mEventType) {
                    AddAccessibilityEventVisitor.this.fireEvent(host);
                }
                if (this.mRealDelegate != null) {
                    this.mRealDelegate.sendAccessibilityEvent(host, eventType);
                }
            }
        }

        public AddAccessibilityEventVisitor(List<PathElement> path, int accessibilityEventType, EventInfo eventInfo, OnEventListener listener) {
            super(path, eventInfo, listener, false);
            this.mEventType = accessibilityEventType;
        }

        public void cleanup() {
            for (Entry<View, TrackingAccessibilityDelegate> entry : this.mWatching.entrySet()) {
                View v = (View) entry.getKey();
                TrackingAccessibilityDelegate toCleanup = (TrackingAccessibilityDelegate) entry.getValue();
                AccessibilityDelegate originalViewDelegate = getOldDelegate(v);
                if (originalViewDelegate == toCleanup) {
                    v.setAccessibilityDelegate(toCleanup.getRealDelegate());
                } else if (originalViewDelegate instanceof TrackingAccessibilityDelegate) {
                    ((TrackingAccessibilityDelegate) originalViewDelegate).removeFromDelegateChain(toCleanup);
                }
            }
            this.mWatching.clear();
        }

        public void accumulate(View found) {
            AccessibilityDelegate realDelegate = getOldDelegate(found);
            if (!(realDelegate instanceof TrackingAccessibilityDelegate) || !((TrackingAccessibilityDelegate) realDelegate).willFireEvent(getEventName())) {
                SALog.m1608i(ViewVisitor.TAG, String.format("ClickVisitor accumulated. View %s", new Object[]{found.toString()}));
                TrackingAccessibilityDelegate newDelegate = new TrackingAccessibilityDelegate(realDelegate);
                found.setAccessibilityDelegate(newDelegate);
                this.mWatching.put(found, newDelegate);
            }
        }

        /* access modifiers changed from: protected */
        public String name() {
            return getEventName() + " event when (" + this.mEventType + ")";
        }

        private AccessibilityDelegate getOldDelegate(View v) {
            AccessibilityDelegate ret = null;
            try {
                return (AccessibilityDelegate) v.getClass().getMethod("getAccessibilityDelegate", new Class[0]).invoke(v, new Object[0]);
            } catch (IllegalAccessException | NoSuchMethodException e) {
                return ret;
            } catch (InvocationTargetException e2) {
                SALog.m1609i(ViewVisitor.TAG, "getAccessibilityDelegate threw an exception when called.", e2);
                return ret;
            }
        }
    }

    public static class AddTextChangeListener extends EventTriggeringVisitor {
        private final Map<TextView, TextWatcher> mWatching = new HashMap();

        private class TrackingTextWatcher implements TextWatcher {
            private final View mBoundTo;

            public TrackingTextWatcher(View boundTo) {
                this.mBoundTo = boundTo;
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            public void afterTextChanged(Editable s) {
                AddTextChangeListener.this.fireEvent(this.mBoundTo);
            }
        }

        public AddTextChangeListener(List<PathElement> path, EventInfo eventInfo, OnEventListener listener) {
            super(path, eventInfo, listener, true);
        }

        public void cleanup() {
            for (Entry<TextView, TextWatcher> entry : this.mWatching.entrySet()) {
                ((TextView) entry.getKey()).removeTextChangedListener((TextWatcher) entry.getValue());
            }
            this.mWatching.clear();
        }

        public void accumulate(View found) {
            if (found instanceof TextView) {
                TextView foundTextView = (TextView) found;
                TextWatcher watcher = new TrackingTextWatcher(foundTextView);
                TextWatcher oldWatcher = (TextWatcher) this.mWatching.get(foundTextView);
                if (oldWatcher != null) {
                    foundTextView.removeTextChangedListener(oldWatcher);
                }
                foundTextView.addTextChangedListener(watcher);
                this.mWatching.put(foundTextView, watcher);
            }
        }

        /* access modifiers changed from: protected */
        public String name() {
            return getEventName() + " on Text Change";
        }
    }

    private static abstract class EventTriggeringVisitor extends ViewVisitor {
        private final boolean mDebounce;
        private final EventInfo mEventInfo;
        private final OnEventListener mListener;

        public EventTriggeringVisitor(List<PathElement> path, EventInfo eventInfo, OnEventListener listener, boolean debounce) {
            super(path);
            this.mListener = listener;
            this.mEventInfo = eventInfo;
            this.mDebounce = debounce;
        }

        /* access modifiers changed from: protected */
        public void fireEvent(View found) {
            this.mListener.OnEvent(found, this.mEventInfo, this.mDebounce);
        }

        /* access modifiers changed from: protected */
        public String getEventName() {
            return this.mEventInfo.mEventName;
        }
    }

    public interface OnEventListener {
        void OnEvent(View view, EventInfo eventInfo, boolean z);
    }

    public static class ViewDetectorVisitor extends EventTriggeringVisitor {
        private boolean mSeen = false;

        public ViewDetectorVisitor(List<PathElement> path, EventInfo eventInfo, OnEventListener listener) {
            super(path, eventInfo, listener, false);
        }

        public void cleanup() {
        }

        public void accumulate(View found) {
            if (found != null && !this.mSeen) {
                fireEvent(found);
            }
            this.mSeen = found != null;
        }

        /* access modifiers changed from: protected */
        public String name() {
            return getEventName() + " when Detected";
        }
    }

    public abstract void cleanup();

    /* access modifiers changed from: protected */
    public abstract String name();

    public void visit(View rootView) {
        this.mPathfinder.findTargetsInRoot(rootView, this.mPath, this);
    }

    protected ViewVisitor(List<PathElement> path) {
        this.mPath = path;
    }
}
