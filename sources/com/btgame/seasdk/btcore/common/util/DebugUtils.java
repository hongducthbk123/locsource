package com.btgame.seasdk.btcore.common.util;

import android.util.Log;
import com.facebook.internal.ServerProtocol;
import java.util.HashMap;

public class DebugUtils {
    public static String DEBUGLOG = "debugLog";
    private static HashMap<String, String> debugMap;
    public static DebugUtils instance;
    private String DEBUGSERVER = "debugServer";
    private boolean codeFlag;
    private boolean logFlag;

    private DebugUtils() {
        BtFileUtil.initLogFile(ContextUtil.getApplicationContext());
    }

    public static void setDebugMap() {
        try {
            debugMap = BtFileUtil.readFile(BtFileUtil.getLogFile());
        } catch (Exception e) {
            Log.d(BtsdkLog.TAG, "获取debug标示失败");
        }
    }

    public static DebugUtils getInstance() {
        if (instance == null) {
            instance = new DebugUtils();
        }
        return instance;
    }

    public boolean isCodeFlag() {
        return this.codeFlag;
    }

    public void setCodeFlag(boolean encodeFlag) {
        this.codeFlag = encodeFlag;
    }

    public boolean isLogFlag() {
        if (debugMap != null) {
            String debugValue = (String) debugMap.get(DEBUGLOG);
            if (debugValue != null && ServerProtocol.DIALOG_RETURN_SCOPES_TRUE.equals(debugValue)) {
                this.logFlag = true;
            }
        }
        return this.logFlag;
    }

    public void setLogFlag(boolean debug) {
        if (debugMap != null) {
            String debugValue = (String) debugMap.get(DEBUGLOG);
            if (debugValue != null && ServerProtocol.DIALOG_RETURN_SCOPES_TRUE.equals(debugValue)) {
                this.logFlag = true;
                return;
            }
        }
        this.logFlag = debug;
    }
}
