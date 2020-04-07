package com.sensorsdata.analytics.android.sdk;

import android.content.Context;
import com.sensorsdata.analytics.android.sdk.util.SensorsDataUtils;

class ConfigurationChecker {
    ConfigurationChecker() {
    }

    public static boolean checkBasicConfiguration(Context context) {
        return SensorsDataUtils.checkHasPermission(context, "android.permission.INTERNET");
    }
}
