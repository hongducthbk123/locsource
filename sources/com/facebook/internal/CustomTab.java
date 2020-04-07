package com.facebook.internal;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsIntent;
import android.support.customtabs.CustomTabsIntent.Builder;
import com.facebook.FacebookSdk;
import com.google.common.primitives.Ints;
import p004cn.jiguang.net.HttpUtils;

public class CustomTab {
    private Uri uri;

    public CustomTab(String action, Bundle parameters) {
        if (parameters == null) {
            parameters = new Bundle();
        }
        this.uri = Utility.buildUri(ServerProtocol.getDialogAuthority(), FacebookSdk.getGraphApiVersion() + HttpUtils.PATHS_SEPARATOR + ServerProtocol.DIALOG_PATH + action, parameters);
    }

    public void openCustomTab(Activity activity, String packageName) {
        CustomTabsIntent customTabsIntent = new Builder().build();
        customTabsIntent.intent.setPackage(packageName);
        customTabsIntent.intent.addFlags(Ints.MAX_POWER_OF_TWO);
        customTabsIntent.launchUrl(activity, this.uri);
    }
}
