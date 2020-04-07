package com.sensorsdata.analytics.android.sdk;

public interface ResourceIds {
    int idFromName(String str);

    boolean knownIdName(String str);

    String nameForId(int i);
}
