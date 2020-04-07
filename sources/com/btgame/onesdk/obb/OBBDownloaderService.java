package com.btgame.onesdk.obb;

import com.btgame.sdk.util.ResourceUtil;
import com.google.android.vending.expansion.downloader.impl.DownloaderService;

public class OBBDownloaderService extends DownloaderService {
    private static final String BASE64_PUBLIC_KEY = "";
    private static final byte[] SALT = {1, 43, -12, -1, 54, 98, -100, -12, 43, 2, -8, -4, 9, 5, -106, -108, -33, 45, -1, 84};

    public String getPublicKey() {
        return getString(ResourceUtil.getStringId(this, "base64_public_key"));
    }

    public byte[] getSALT() {
        return SALT;
    }

    public String getAlarmReceiverClassName() {
        return OBBAlarmReceiver.class.getName();
    }
}
