package com.sensorsdata.analytics.android.sdk;

import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.sensorsdata.analytics.android.sdk.ViewVisitor.OnEventListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONException;
import org.json.JSONObject;

class DynamicEventTracker implements OnEventListener {
    private static final int DEBOUNCE_TIME_MILLIS = 3000;
    private static final int MAX_PROPERTY_LENGTH = 128;
    private static String TAG = "SA.DynamicEventTracker";
    /* access modifiers changed from: private */
    public final Context mContext;
    /* access modifiers changed from: private */
    public final Map<Signature, UnsentEvent> mDebouncedEvents = new HashMap();
    /* access modifiers changed from: private */
    public final Handler mHandler;
    private final Runnable mTask = new SendDebouncedTask();

    private final class SendDebouncedTask implements Runnable {
        private SendDebouncedTask() {
        }

        public void run() {
            long now = System.currentTimeMillis();
            synchronized (DynamicEventTracker.this.mDebouncedEvents) {
                Iterator<Entry<Signature, UnsentEvent>> iter = DynamicEventTracker.this.mDebouncedEvents.entrySet().iterator();
                while (iter.hasNext()) {
                    UnsentEvent val = (UnsentEvent) ((Entry) iter.next()).getValue();
                    if (now - val.timeSentMillis > 3000) {
                        SensorsDataAPI.sharedInstance(DynamicEventTracker.this.mContext).track(val.eventInfo.mEventName, val.properties);
                        iter.remove();
                    }
                }
                if (!DynamicEventTracker.this.mDebouncedEvents.isEmpty()) {
                    DynamicEventTracker.this.mHandler.postDelayed(this, 1500);
                }
            }
        }
    }

    private static class Signature {
        private final int mHashCode;

        public Signature(View view, EventInfo eventInfo) {
            this.mHashCode = (view.hashCode() ^ eventInfo.mEventName.hashCode()) ^ String.valueOf(eventInfo.mTriggerId).hashCode();
        }

        public boolean equals(Object o) {
            if (!(o instanceof Signature) || this.mHashCode != o.hashCode()) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return this.mHashCode;
        }
    }

    private static class UnsentEvent {
        public final EventInfo eventInfo;
        public final JSONObject properties;
        public final long timeSentMillis;

        public UnsentEvent(EventInfo event, JSONObject props, long timeSent) {
            this.eventInfo = event;
            this.properties = props;
            this.timeSentMillis = timeSent;
        }
    }

    public DynamicEventTracker(Context context, Handler homeHandler) {
        this.mContext = context;
        this.mHandler = homeHandler;
    }

    public void OnEvent(View v, EventInfo eventInfo, boolean debounce) {
        long moment = System.currentTimeMillis();
        JSONObject properties = new JSONObject();
        try {
            properties.put("$from_vtrack", String.valueOf(eventInfo.mTriggerId));
            properties.put("$binding_trigger_id", eventInfo.mTriggerId);
            properties.put("$binding_path", eventInfo.mPath);
            properties.put("$binding_depolyed", eventInfo.mIsDeployed);
        } catch (JSONException e) {
            SALog.m1609i(TAG, "Can't format properties from view due to JSON issue", e);
        }
        if (debounce) {
            Signature eventSignature = new Signature(v, eventInfo);
            UnsentEvent event = new UnsentEvent(eventInfo, properties, moment);
            synchronized (this.mDebouncedEvents) {
                boolean needsRestart = this.mDebouncedEvents.isEmpty();
                this.mDebouncedEvents.put(eventSignature, event);
                if (needsRestart) {
                    this.mHandler.postDelayed(this.mTask, 3000);
                }
            }
            return;
        }
        SensorsDataAPI.sharedInstance(this.mContext).track(eventInfo.mEventName, properties);
    }

    private static String textPropertyFromView(View v) {
        if (v instanceof TextView) {
            CharSequence retSequence = ((TextView) v).getText();
            if (retSequence != null) {
                return retSequence.toString();
            }
            return null;
        } else if (!(v instanceof ViewGroup)) {
            return null;
        } else {
            StringBuilder builder = new StringBuilder();
            ViewGroup vGroup = (ViewGroup) v;
            int childCount = vGroup.getChildCount();
            boolean textSeen = false;
            for (int i = 0; i < childCount && builder.length() < 128; i++) {
                String childText = textPropertyFromView(vGroup.getChildAt(i));
                if (childText != null && childText.length() > 0) {
                    if (textSeen) {
                        builder.append(", ");
                    }
                    builder.append(childText);
                    textSeen = true;
                }
            }
            if (builder.length() > 128) {
                return builder.substring(0, 128);
            }
            if (textSeen) {
                return builder.toString();
            }
            return null;
        }
    }
}
