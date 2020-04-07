package com.btgame.onesdk.frame.eneity.onesdk;

import android.content.Context;

public class SDKInitInfo {
    private boolean isSupportReStart;
    private Context mCtx;

    public Context getmCtx() {
        return this.mCtx;
    }

    public void setmCtx(Context mCtx2) {
        this.mCtx = mCtx2;
    }

    public boolean isSupportReStart() {
        return this.isSupportReStart;
    }

    public void setSupportReStart(boolean isSupportReStart2) {
        this.isSupportReStart = isSupportReStart2;
    }
}
