package com.btgame.sdk.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import com.baitian.datasdk.util.ContextUtil;
import com.btgame.onesdk.frame.constants.Constants;
import com.facebook.internal.ServerProtocol;
import java.util.HashMap;

public class DebugUtils {
    public static final int CopyRightCode_type = 6;
    private static String DEBUG_LOG = "debugLog";
    public static final int Developer_Type = 1;
    private static final String META_DATA_LOG_FLAG = "sdkLogConfig";
    public static final int Official_Type = 3;
    public static final int Released_Type = 5;
    public static final int Test_Type = 2;
    public static final int YANGCAO_SERVER_Type = 4;
    private static DebugUtils instance;
    private String DEBUG_SERVER = "debugServer";
    private boolean LogFlag = false;
    private boolean codeFlag;
    private HashMap<String, String> debugMap;
    private int runningType;

    @SuppressLint({"WrongConstant"})
    private DebugUtils() {
        try {
            this.debugMap = FileUtil.readFile(FileUtil.getLogFile());
        } catch (Exception e) {
            Log.d(BtsdkLog.TAG, "获取debugMap标识失败");
        }
        if (this.debugMap != null) {
            String debugValue = (String) this.debugMap.get(DEBUG_LOG);
            if (debugValue != null && ServerProtocol.DIALOG_RETURN_SCOPES_TRUE.equals(debugValue)) {
                Log.d(BtsdkLog.TAG, "debugMap中开启了debugFlag");
                this.LogFlag = true;
            }
        }
        if (!this.LogFlag) {
            if (ContextUtil.getInstance() == null) {
                Log.d(BtsdkLog.TAG, "没实例化前输出日志");
                this.LogFlag = true;
            } else {
                Context context = ContextUtil.getInstance().getmCtx();
                try {
                    this.LogFlag = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData.getBoolean(META_DATA_LOG_FLAG);
                    Log.d(BtsdkLog.TAG, "AndroidManifest.xml配置为：" + this.LogFlag);
                } catch (Exception e2) {
                    Log.d(BtsdkLog.TAG, "获取sdkLogConfig异常：" + e2.getMessage());
                    e2.printStackTrace();
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
            String debugValue = (String) this.debugMap.get(this.DEBUG_SERVER);
            if (debugValue != null && ServerProtocol.DIALOG_RETURN_SCOPES_TRUE.equals(debugValue)) {
                this.runningType = 2;
                return;
            }
        }
        switch (runningtype) {
            case YangCaoServer:
                this.runningType = 4;
                return;
            case Developer:
                this.runningType = 1;
                return;
            case Test:
                this.runningType = 2;
                return;
            case Official:
                this.runningType = 3;
                return;
            case CopyRightCode:
                this.runningType = 6;
                return;
            default:
                this.runningType = 5;
                return;
        }
    }

    public int getRunningType() {
        return this.runningType;
    }

    public void setLogFalg(boolean debug) {
    }

    public boolean isLogFlag() {
        return this.LogFlag;
    }

    public void setCustomDomain(String domainUrl) {
        Constants.URL_YANGCAO_SERVER = domainUrl;
    }
}
