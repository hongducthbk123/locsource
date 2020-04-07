package com.sensorsdata.analytics.android.sdk;

import android.content.SharedPreferences;
import java.util.concurrent.Future;

class PersistentLoginId extends PersistentIdentity<String> {
    PersistentLoginId(Future<SharedPreferences> loadStoredPreferences) {
        super(loadStoredPreferences, "events_login_id", new PersistentSerializer<String>() {
            public String load(String value) {
                return value;
            }

            public String save(String item) {
                return item;
            }

            public String create() {
                return null;
            }
        });
    }
}
