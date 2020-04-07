package com.btgame.onesdk.btadjust;

import android.content.Context;

public interface IAdjustImpl {
    String getAdjustAdId();

    void onActiveNew(int i, String str, String str2, float f, String str3);

    void onApplicationCreate(Context context, String str, boolean z);

    void onEventAction(int i, String str);

    void onPause();

    void onResume();

    void setDefaultTracker(String str);

    void setIMEI(String str);
}
