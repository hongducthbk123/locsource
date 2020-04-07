package com.baitian.datasdk.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import com.facebook.internal.ServerProtocol;
import java.util.HashMap;

@SuppressLint({"WrongConstant"})
public class DebugUtils {
    private static String DEBUG_LOG = "debugLog";
    public static final int Developer_Type = 1;
    public static final int Local_SERVER_Type = 3;
    private static final String META_DATA_LOG_FLAG = "sdkLogConfig";
    public static final int Released_Type = 2;
    public static DebugUtils instance;
    private String DEBUGSERVER = "debugServer";
    private boolean LogFlag = false;
    private boolean codeFlag;
    private HashMap<String, String> debugMap;
    private int runningType;

    private DebugUtils() {
        try {
            this.debugMap = FileUtil.readFile(FileUtil.getLogFile());
        } catch (Exception e) {
            Log.d(BtsdkLog.TAG, "获取debugMap标识失败");
        }
        if (this.debugMap != null) {
            String debugValue = (String) this.debugMap.get(DEBUG_LOG);
            if (debugValue != null && ServerProtocol.DIALOG_RETURN_SCOPES_TRUE.equals(debugValue)) {
                this.LogFlag = true;
            }
        }
        if (!this.LogFlag) {
            if (ContextUtil.getInstance() == null) {
                this.LogFlag = true;
            } else {
                Context context = ContextUtil.getInstance().getmCtx();
                try {
                    this.LogFlag = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData.getBoolean(META_DATA_LOG_FLAG);
                } catch (Exception e2) {
                    e2.printStackTrace();
                    Log.d(BtsdkLog.TAG, "获取sdkLogConfig异常：" + e2.getMessage());
                }
            }
        }
        Log.d(BtsdkLog.TAG, "获取日志开关：" + this.LogFlag);
    }

    public static DebugUtils getInstance() {
        if (instance == null) {
            instance = new DebugUtils();
        }
        return instance;
    }

    public void setCodeFlag(boolean encodeFlag) {
        this.codeFlag = encodeFlag;
    }

    public boolean isCodeFlag() {
        return this.codeFlag;
    }

    public void setDebug(RunningType runningtype) {
        if (this.debugMap != null) {
            String debugValue = (String) this.debugMap.get(this.DEBUGSERVER);
            if (debugValue != null && ServerProtocol.DIALOG_RETURN_SCOPES_TRUE.equals(debugValue)) {
                this.runningType = 1;
                return;
            }
        }
        switch (runningtype) {
            case Developer:
                this.runningType = 1;
                return;
            case YangCaoServer:
                this.runningType = 3;
                return;
            default:
                this.runningType = 2;
                return;
        }
    }

    public int getRunningType() {
        return this.runningType;
    }

    public void setLogFalg(boolean debug) {
    }

    public boolean isLogFlag() {
        if (this.debugMap != null) {
            String debugValue = (String) this.debugMap.get(DEBUG_LOG);
            if (debugValue != null && ServerProtocol.DIALOG_RETURN_SCOPES_TRUE.equals(debugValue)) {
                this.LogFlag = true;
            }
        }
        return this.LogFlag;
    }
}
