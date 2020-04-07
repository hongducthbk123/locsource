package com.sensorsdata.analytics.android.sdk;

import android.content.SharedPreferences;
import java.util.concurrent.Future;

class PersistentFirstDay extends PersistentIdentity<String> {
    PersistentFirstDay(Future<SharedPreferences> loadStoredPreferences) {
        super(loadStoredPreferences, "first_day", new PersistentSerializer<String>() {
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
