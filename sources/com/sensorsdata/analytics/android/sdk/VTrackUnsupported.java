package com.sensorsdata.analytics.android.sdk;

import org.json.JSONArray;
import org.json.JSONObject;

class VTrackUnsupported implements VTrack, DebugTracking {
    public void startUpdates() {
    }

    public void setEventBindings(JSONArray bindings) {
    }

    public void setVTrackServer(String serverUrl) {
    }

    public void enableEditingVTrack() {
    }

    public void disableActivity(String canonicalName) {
    }

    public void reportTrack(JSONObject eventJson) {
    }
}
