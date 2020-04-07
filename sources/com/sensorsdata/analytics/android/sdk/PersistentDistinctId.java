package com.sensorsdata.analytics.android.sdk;

import android.content.SharedPreferences;
import java.util.UUID;
import java.util.concurrent.Future;

class PersistentDistinctId extends PersistentIdentity<String> {
    PersistentDistinctId(Future<SharedPreferences> loadStoredPreferences) {
        super(loadStoredPreferences, "events_distinct_id", new PersistentSerializer<String>() {
            public String load(String value) {
                return value;
            }

            public String save(String item) {
                return item;
            }

            public String create() {
                return UUID.randomUUID().toString();
            }
        });
    }
}
