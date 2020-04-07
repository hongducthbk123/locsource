package com.android.vending.expansion.downloader;

import com.facebook.internal.ServerProtocol;

public final class BuildConfig {
    public static final String APPLICATION_ID = "com.android.vending.expansion.downloader";
    public static final String BUILD_TYPE = "debug";
    public static final boolean DEBUG = Boolean.parseBoolean(ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
    public static final String FLAVOR = "";
    public static final int VERSION_CODE = 1;
    public static final String VERSION_NAME = "1.0";
}
