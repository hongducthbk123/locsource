package com.sensorsdata.analytics.android.sdk;

import org.json.JSONArray;

public interface VTrack {
    void disableActivity(String str);

    void enableEditingVTrack();

    void setEventBindings(JSONArray jSONArray);

    void setVTrackServer(String str);

    void startUpdates();
}
