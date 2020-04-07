package com.btgame.onesdk.share;

import android.app.Activity;
import com.btgame.seasdk.BtSeaSDKManager;
import p004cn.sharesdk.framework.PlatformActionListener;

public class ShareManager {
    private static ShareManager instance;
    public static PlatformActionListener listener;

    public enum ShareType {
        WEB,
        PIC,
        TEXT,
        APP
    }

    private ShareManager(Activity act) {
    }

    public static ShareManager getInstance(Activity act) {
        if (instance == null) {
            instance = new ShareManager(act);
        }
        return instance;
    }

    public void share(Activity activity, String content, String title, String img, String url, ShareType shareType, PlatformActionListener listener2) {
        listener = listener2;
        BtSeaSDKManager.getInstance().share(activity, url, img);
    }
}
