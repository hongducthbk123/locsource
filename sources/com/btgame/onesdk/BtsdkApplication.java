package com.btgame.onesdk;

import com.baitian.datasdk.util.ContextUtil;
import com.baitian.util.FileUtil;
import com.btgame.seasdk.GameApplication;

public class BtsdkApplication extends GameApplication {
    public void onCreate() {
        ContextUtil.init(this);
        com.btgame.seasdk.btcore.common.util.ContextUtil.init(this);
        FileUtil.init(this);
        super.onCreate();
    }
}
