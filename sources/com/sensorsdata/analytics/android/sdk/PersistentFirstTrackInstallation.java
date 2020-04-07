package com.sensorsdata.analytics.android.sdk;

import android.content.SharedPreferences;
import java.util.concurrent.Future;

class PersistentFirstTrackInstallation extends PersistentIdentity<Boolean> {
    PersistentFirstTrackInstallation(Future<SharedPreferences> loadStoredPreferences) {
        super(loadStoredPreferences, "first_track_installation", new PersistentSerializer<Boolean>() {
            public Boolean load(String value) {
                return Boolean.valueOf(false);
            }

            public String save(Boolean item) {
                return String.valueOf(true);
            }

            public Boolean create() {
                return Boolean.valueOf(true);
            }
        });
    }
}
