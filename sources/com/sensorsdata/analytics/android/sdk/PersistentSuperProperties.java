package com.sensorsdata.analytics.android.sdk;

import android.content.SharedPreferences;
import android.util.Log;
import java.util.concurrent.Future;
import org.json.JSONException;
import org.json.JSONObject;

class PersistentSuperProperties extends PersistentIdentity<JSONObject> {
    PersistentSuperProperties(Future<SharedPreferences> loadStoredPreferences) {
        super(loadStoredPreferences, "super_properties", new PersistentSerializer<JSONObject>() {
            public JSONObject load(String value) {
                try {
                    return new JSONObject(value);
                } catch (JSONException e) {
                    Log.e("Persistent", "failed to load SuperProperties from SharedPreferences.", e);
                    return null;
                }
            }

            public String save(JSONObject item) {
                return item.toString();
            }

            public JSONObject create() {
                return new JSONObject();
            }
        });
    }
}
