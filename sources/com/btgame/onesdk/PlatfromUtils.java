package com.btgame.onesdk;

import android.content.Context;
import android.util.Log;
import com.btgame.sdk.util.BtsdkLog;

public class PlatfromUtils {
    public static final String PLATFROMID_KEY = "btplatformId";
    public int platfromId = 28;

    public int getplatformId(Context context) {
        try {
            int id = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData.getInt(PLATFROMID_KEY);
            Log.e(BtsdkLog.TAG, "platfromId =" + String.valueOf(id));
            if (id != 0) {
                return id;
            }
            return this.platfromId;
        } catch (Exception e) {
            e.printStackTrace();
            return this.platfromId;
        }
    }
}
