package com.sensorsdata.analytics.android.sdk;

import org.json.JSONArray;

public class DecideMessages {
    private static final String LOGTAG = "SA.DecideMessages";
    private final VTrack mVTrack;

    public DecideMessages(VTrack vTrack) {
        this.mVTrack = vTrack;
    }

    public synchronized void setEventBindings(JSONArray eventBindings) {
        this.mVTrack.setEventBindings(eventBindings);
    }

    public synchronized void setVTrackServer(String vtrackServer) {
        this.mVTrack.setVTrackServer(vtrackServer);
    }
}
