package org.cocos2dx.extension;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.net.Uri;
import android.provider.MediaStore.Images.Media;
import android.util.Log;
import com.baitian.datasdk.BtDataSdkManager;
import com.baitian.obb.ExpansionManager;
import com.btgame.onesdk.BtOneSDKManager;
import com.btgame.onesdk.PlatfromUtils;
import com.btgame.onesdk.frame.eneity.Args;
import com.btgame.onesdk.frame.eneity.onesdk.GameRoleInfo;
import com.btgame.onesdk.frame.eneity.onesdk.LoginReusltInfo;
import com.btgame.onesdk.frame.eneity.onesdk.SDKInitInfo;
import com.btgame.onesdk.frame.eneity.onesdk.SDKPaymentInfo;
import com.btgame.onesdk.frame.listener.OnSDKListener;
import com.btgame.onesdk.share.ShareManager;
import com.btgame.onesdk.share.ShareManager.ShareType;
import com.facebook.GraphResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import org.cocos2dx.lib.Cocos2dxActivity;
import org.cocos2dx.lib.Cocos2dxHelper;
import org.cocos2dx.lib.Cocos2dxLuaJavaBridge;
import org.json.JSONException;
import org.json.JSONObject;
import p004cn.sharesdk.framework.Platform;
import p004cn.sharesdk.framework.PlatformActionListener;

public class CocosOneSDKManager {
    private static Context m_context = null;
    /* access modifiers changed from: private */
    public static int m_exitDoneCallback = -1;
    /* access modifiers changed from: private */
    public static int m_gLoutoutCallback = -1;
    /* access modifiers changed from: private */
    public static int m_initDoneCallback = -1;
    /* access modifiers changed from: private */
    public static int m_loginDoneCallback = -1;
    /* access modifiers changed from: private */
    public static LoginReusltInfo m_loginResultInfo = null;
    /* access modifiers changed from: private */
    public static int m_logoutCallback = -1;
    /* access modifiers changed from: private */
    public static int m_payDoneCallback = -1;
    /* access modifiers changed from: private */
    public static int m_sdkInitStatus = -10086;

    public static void setContext(Context ctx) {
        m_context = ctx;
    }

    /* access modifiers changed from: private */
    public static void privateInitSDK() {
        BtOneSDKManager.sdkInit(getInitInfo(m_context), new OnSDKListener() {
            public void onPay(int statusCode) {
                CocosOneSDKManager.DoPayLuaCallback(statusCode);
            }

            public void onLogout(int statusCode) {
                Log.i("oneSDK Log", "onLogout,statusCode=" + statusCode);
                CocosOneSDKManager.DoLogoutLuaCallback(statusCode);
            }

            public void onInit(int statusCode) {
                CocosOneSDKManager.DoInitLuaCallback(statusCode);
            }

            public void onExit(int statusCode) {
                CocosOneSDKManager.DoExitLuaCallback(statusCode);
            }

            public void onLogin(LoginReusltInfo callBack, int statusCode) {
                CocosOneSDKManager.m_loginResultInfo = null;
                if (statusCode == 0) {
                    CocosOneSDKManager.m_loginResultInfo = callBack;
                    Log.i("oneSDK Log", "onLogin m_loginResultInfo.platfromId: " + CocosOneSDKManager.m_loginResultInfo.platfromId);
                } else {
                    Log.i("oneSDK Log", "登录失败, statusCode: " + statusCode);
                }
                CocosOneSDKManager.DoLoginLuaCallback(statusCode);
            }
        });
    }

    public static void InitSDK() {
        ((Activity) Cocos2dxActivity.getContext()).runOnUiThread(new Runnable() {
            @SuppressLint({"NewApi"})
            public void run() {
                CocosOneSDKManager.privateInitSDK();
            }
        });
    }

    public static void nativeInitSDK() {
        privateInitSDK();
    }

    protected static SDKInitInfo getInitInfo(Context ctx) {
        SDKInitInfo info = new SDKInitInfo();
        info.setmCtx(ctx);
        info.setSupportReStart(false);
        return info;
    }

    public static int luaGetSdkInitStatus() {
        if (m_sdkInitStatus == 0) {
            BtDataSdkManager.getInstance((Activity) Cocos2dxActivity.getContext()).submitBaseEventData("21", "GetSdkInitStatus", "", "");
        }
        return m_sdkInitStatus;
    }

    public static void luaSetInitCallback(int luaFunc) {
        if (m_initDoneCallback != -1) {
            Cocos2dxLuaJavaBridge.releaseLuaFunction(m_initDoneCallback);
        }
        m_initDoneCallback = luaFunc;
    }

    public static void DoInitLuaCallback(final int statusCode) {
        if (statusCode == 0) {
            BtDataSdkManager.getInstance((Activity) Cocos2dxActivity.getContext()).submitBaseEventData("20", "", "", "");
            Log.i("oneSDK Log", "submitBaseEventData20");
        }
        if (statusCode == 0 && m_initDoneCallback != -1) {
            BtDataSdkManager.getInstance((Activity) Cocos2dxActivity.getContext()).submitBaseEventData("21", "DoInitLuaCallback", "", "");
            Log.i("oneSDK Log", "submitBaseEventData21");
        }
        Cocos2dxHelper.runOnGLThread(new Runnable() {
            public void run() {
                CocosOneSDKManager.m_sdkInitStatus = statusCode;
                Log.i("oneSDK Log", "m_sdkInitStatus=" + CocosOneSDKManager.m_sdkInitStatus);
                if (CocosOneSDKManager.m_initDoneCallback != -1) {
                    int temp = CocosOneSDKManager.m_initDoneCallback;
                    CocosOneSDKManager.m_initDoneCallback = -1;
                    Cocos2dxLuaJavaBridge.callLuaFunctionWithString(temp, String.valueOf(statusCode));
                    Cocos2dxLuaJavaBridge.releaseLuaFunction(temp);
                }
            }
        });
    }

    public static String luaGetLoginSessionId() {
        String sessionId = "";
        if (m_loginResultInfo != null) {
            return m_loginResultInfo.btSessionId;
        }
        return sessionId;
    }

    public static String luaGetLoginDesc() {
        String desc = "";
        if (m_loginResultInfo != null) {
            return m_loginResultInfo.desc;
        }
        return desc;
    }

    public static int luaGetLoginPlatformId() {
        if (m_loginResultInfo != null) {
            return m_loginResultInfo.platfromId;
        }
        return -1;
    }

    public static void DoLoginLuaCallback(final int statusCode) {
        Cocos2dxHelper.runOnGLThread(new Runnable() {
            public void run() {
                Log.i("oneSDK Log", "DoLoginLuaCallback m_loginDoneCallback=: " + CocosOneSDKManager.m_loginDoneCallback);
                if (CocosOneSDKManager.m_loginDoneCallback != -1) {
                    int temp = CocosOneSDKManager.m_loginDoneCallback;
                    CocosOneSDKManager.m_loginDoneCallback = -1;
                    Cocos2dxLuaJavaBridge.callLuaFunctionWithString(temp, "" + statusCode);
                    Cocos2dxLuaJavaBridge.releaseLuaFunction(temp);
                }
            }
        });
    }

    public static void luaSdkLogin(int luaFunc) {
        Log.i("oneSDK Log", "luaSdkLogin=" + luaFunc);
        if (m_loginDoneCallback != -1) {
            Cocos2dxLuaJavaBridge.releaseLuaFunction(m_loginDoneCallback);
        }
        m_loginDoneCallback = luaFunc;
        m_loginResultInfo = null;
        ((Activity) Cocos2dxActivity.getContext()).runOnUiThread(new Runnable() {
            @SuppressLint({"NewApi"})
            public void run() {
                BtOneSDKManager.getInstance().sdkLogin();
            }
        });
    }

    public static void DoPayLuaCallback(final int statusCode) {
        Cocos2dxHelper.runOnGLThread(new Runnable() {
            public void run() {
                if (CocosOneSDKManager.m_payDoneCallback != -1) {
                    boolean success = statusCode == 0;
                    int temp = CocosOneSDKManager.m_payDoneCallback;
                    CocosOneSDKManager.m_payDoneCallback = -1;
                    Cocos2dxLuaJavaBridge.callLuaFunctionWithString(temp, success ? GraphResponse.SUCCESS_KEY : "" + statusCode);
                    Cocos2dxLuaJavaBridge.releaseLuaFunction(temp);
                }
            }
        });
    }

    private static void tryInvoke(Object receiver, String methodName, Object... args) {
        Method method = null;
        try {
            Method[] methods = receiver.getClass().getMethods();
            method = receiver.getClass().getMethod(methodName, new Class[0]);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        if (method != null) {
            try {
                method.invoke(receiver, args);
            } catch (IllegalAccessException e2) {
                e2.printStackTrace();
            } catch (IllegalArgumentException e3) {
                e3.printStackTrace();
            } catch (InvocationTargetException e4) {
                e4.printStackTrace();
            }
        }
    }

    public static void luaSdkPay(int payDoneCallback, final String paramTable) {
        if (m_payDoneCallback != -1) {
            Cocos2dxLuaJavaBridge.releaseLuaFunction(m_payDoneCallback);
        }
        m_payDoneCallback = payDoneCallback;
        ((Activity) Cocos2dxActivity.getContext()).runOnUiThread(new Runnable() {
            @SuppressLint({"NewApi"})
            public void run() {
                int payType = 0;
                String roleId = "";
                double money = 0.0d;
                int rate = 0;
                String productName = "";
                String gameGold = "";
                String callbackStr = "";
                String exStr = "";
                String outOrderNo = "";
                String goodsId = "";
                String androidproductId = "";
                Args args = new Args();
                try {
                    JSONObject json = new JSONObject(paramTable);
                    payType = json.optInt("payType");
                    roleId = json.optString("roleId");
                    money = json.optDouble("money");
                    rate = json.optInt("rate");
                    productName = json.optString("productName");
                    gameGold = json.optString("gameGold");
                    callbackStr = json.optString("callbackStr");
                    exStr = json.optString("exStr");
                    outOrderNo = json.optString("outOrderNo");
                    goodsId = json.optString("goodsId");
                    androidproductId = json.optString("androidproductId");
                    args.arg1 = json.optString("goodsType");
                } catch (JSONException e) {
                    Log.e("DataSDKManager", "Get jsonData JSON error!");
                }
                SDKPaymentInfo paymentInfo = new SDKPaymentInfo();
                paymentInfo.setRoleId(roleId);
                paymentInfo.setCallBackStr(callbackStr);
                paymentInfo.setMoney(money);
                paymentInfo.setPayType(payType);
                paymentInfo.setProductName(productName);
                paymentInfo.setRate(rate);
                paymentInfo.setGameGold(gameGold);
                paymentInfo.setExStr(exStr);
                paymentInfo.setOutOrderNo(outOrderNo);
                paymentInfo.setPlatformGoodsId(androidproductId);
                paymentInfo.setGoodsId(goodsId);
                paymentInfo.setArgs(args);
                BtOneSDKManager.getInstance().sdkPay(paymentInfo);
            }
        });
    }

    public static void DoExitLuaCallback(int statusCode) {
        final boolean success = statusCode == 0;
        if (success) {
            BtOneSDKManager.getInstance().sdkDestroy();
        }
        Cocos2dxHelper.runOnGLThread(new Runnable() {
            public void run() {
                if (CocosOneSDKManager.m_exitDoneCallback != -1) {
                    int temp = CocosOneSDKManager.m_exitDoneCallback;
                    CocosOneSDKManager.m_exitDoneCallback = -1;
                    Cocos2dxLuaJavaBridge.callLuaFunctionWithString(temp, success ? GraphResponse.SUCCESS_KEY : "failed");
                    Cocos2dxLuaJavaBridge.releaseLuaFunction(temp);
                }
            }
        });
    }

    public static void luaExitPlatform(int exitCallback) {
        if (m_exitDoneCallback != -1) {
            Cocos2dxLuaJavaBridge.releaseLuaFunction(m_exitDoneCallback);
        }
        m_exitDoneCallback = exitCallback;
        ((Activity) Cocos2dxActivity.getContext()).runOnUiThread(new Runnable() {
            @SuppressLint({"NewApi"})
            public void run() {
                BtOneSDKManager.getInstance().sdkExit();
            }
        });
    }

    public static void setGlobalLogoutCallback(int luaFunc) {
        if (m_gLoutoutCallback != -1) {
            Cocos2dxLuaJavaBridge.releaseLuaFunction(m_gLoutoutCallback);
        }
        m_gLoutoutCallback = luaFunc;
        Log.i("oneSDK Log", "setGlobalLogoutCallback m_gLoutoutCallback=" + m_gLoutoutCallback);
    }

    public static void DoLogoutLuaCallback(final int statusCode) {
        if (statusCode == 0) {
            TagAliasOperatorHelper.getInstance().handleSetAlias(Cocos2dxActivity.getContext(), 1, "");
        }
        Cocos2dxHelper.runOnGLThread(new Runnable() {
            public void run() {
                boolean success = statusCode == 0;
                Log.i("oneSDK Log", "DoLogoutLuaCallback statusCode=" + statusCode);
                Log.i("oneSDK Log", "DoLogoutLuaCallback m_gLoutoutCallback=" + CocosOneSDKManager.m_gLoutoutCallback);
                Log.i("oneSDK Log", "DoLogoutLuaCallback m_logoutCallback=" + CocosOneSDKManager.m_logoutCallback);
                if (CocosOneSDKManager.m_gLoutoutCallback != -1) {
                    Log.i("oneSDK Log", "m_gLoutoutCallback=" + CocosOneSDKManager.m_gLoutoutCallback);
                    int temp = CocosOneSDKManager.m_gLoutoutCallback;
                    CocosOneSDKManager.m_gLoutoutCallback = -1;
                    Cocos2dxLuaJavaBridge.callLuaFunctionWithString(temp, success ? GraphResponse.SUCCESS_KEY : "" + statusCode);
                    Cocos2dxLuaJavaBridge.releaseLuaFunction(temp);
                }
                if (CocosOneSDKManager.m_logoutCallback != -1) {
                    Log.i("oneSDK Log", "m_logoutCallback=" + CocosOneSDKManager.m_logoutCallback);
                    int temp2 = CocosOneSDKManager.m_logoutCallback;
                    CocosOneSDKManager.m_logoutCallback = -1;
                    Cocos2dxLuaJavaBridge.callLuaFunctionWithString(temp2, success ? GraphResponse.SUCCESS_KEY : "" + statusCode);
                    Cocos2dxLuaJavaBridge.releaseLuaFunction(temp2);
                }
            }
        });
    }

    public static void luaLogoutPlatform(int logoutCallback) {
        if (m_logoutCallback != -1) {
            Cocos2dxLuaJavaBridge.releaseLuaFunction(m_logoutCallback);
        }
        m_logoutCallback = logoutCallback;
        ((Activity) Cocos2dxActivity.getContext()).runOnUiThread(new Runnable() {
            @SuppressLint({"NewApi"})
            public void run() {
                BtOneSDKManager.getInstance().sdkLogout();
                TagAliasOperatorHelper.getInstance().handleSetAlias(Cocos2dxActivity.getContext(), 1, "");
            }
        });
    }

    public static void luaCreateRole(int vipLevel, String roleName, String roleId, int roleLevel, String serverName, String serverId, String roleCTime) {
        final int i = vipLevel;
        final String str = roleName;
        final String str2 = roleId;
        final int i2 = roleLevel;
        final String str3 = serverName;
        final String str4 = serverId;
        final String str5 = roleCTime;
        ((Activity) Cocos2dxActivity.getContext()).runOnUiThread(new Runnable() {
            @SuppressLint({"NewApi"})
            public void run() {
                GameRoleInfo gameInfo = new GameRoleInfo();
                gameInfo.setVipLevel(i);
                gameInfo.setRoleName(str);
                gameInfo.setRoleId(str2);
                gameInfo.setRoleLevel(i2);
                gameInfo.setServerName(str3);
                gameInfo.setServerId(str4);
                gameInfo.setRoleCTime(Long.parseLong(str5));
                BtOneSDKManager.getInstance().createRole(gameInfo);
            }
        });
    }

    public static void luaRoleEnterGame(int vipLevel, String roleName, String roleId, int roleLevel, String serverName, String serverId, String roleCTime) {
        final int i = vipLevel;
        final String str = roleName;
        final String str2 = roleId;
        final int i2 = roleLevel;
        final String str3 = serverName;
        final String str4 = serverId;
        final String str5 = roleCTime;
        ((Activity) Cocos2dxActivity.getContext()).runOnUiThread(new Runnable() {
            @SuppressLint({"NewApi"})
            public void run() {
                GameRoleInfo gameInfo = new GameRoleInfo();
                gameInfo.setVipLevel(i);
                gameInfo.setRoleName(str);
                gameInfo.setRoleId(str2);
                gameInfo.setRoleLevel(i2);
                gameInfo.setServerName(str3);
                gameInfo.setServerId(str4);
                gameInfo.setRoleCTime(Long.parseLong(str5));
                BtOneSDKManager.getInstance().enterGame(gameInfo);
                TagAliasOperatorHelper.getInstance().handleSetAlias(Cocos2dxActivity.getContext(), 1, str2);
            }
        });
    }

    public static void luaRoleUpdate(int vipLevel, String roleName, String roleId, int roleLevel, String serverName, String serverId, String roleCTime) {
        final int i = vipLevel;
        final String str = roleName;
        final String str2 = roleId;
        final int i2 = roleLevel;
        final String str3 = serverName;
        final String str4 = serverId;
        final String str5 = roleCTime;
        ((Activity) Cocos2dxActivity.getContext()).runOnUiThread(new Runnable() {
            @SuppressLint({"NewApi"})
            public void run() {
                GameRoleInfo gameInfo = new GameRoleInfo();
                gameInfo.setVipLevel(i);
                gameInfo.setRoleName(str);
                gameInfo.setRoleId(str2);
                gameInfo.setRoleLevel(i2);
                gameInfo.setServerName(str3);
                gameInfo.setServerId(str4);
                gameInfo.setRoleCTime(Long.parseLong(str5));
                BtOneSDKManager.getInstance().upgradRole(gameInfo);
            }
        });
    }

    public static void luaShowUserCenter() {
        ((Activity) Cocos2dxActivity.getContext()).runOnUiThread(new Runnable() {
            @SuppressLint({"NewApi"})
            public void run() {
                BtOneSDKManager.getInstance().showUserCenter();
            }
        });
    }

    public static int luaGetPlatformId() {
        return new PlatfromUtils().getplatformId(m_context);
    }

    public static String luaGetDevInfo() {
        return BtOneSDKManager.getInstance().getDevJson();
    }

    public static void luaShare(String content, String title, String img, String url, int shareType, final int callback) {
        ShareType sdkShareType;
        switch (shareType) {
            case 1:
                sdkShareType = ShareType.PIC;
                break;
            case 2:
                sdkShareType = ShareType.WEB;
                break;
            case 3:
                sdkShareType = ShareType.TEXT;
                break;
            default:
                Log.i("oneSDK Log", "shareType error. type = " + shareType);
                Cocos2dxHelper.runOnGLThread(new Runnable() {
                    public void run() {
                        Cocos2dxLuaJavaBridge.callLuaFunctionWithString(callback, "error");
                        Cocos2dxLuaJavaBridge.releaseLuaFunction(callback);
                    }
                });
                return;
        }
        final ShareType finalSdkShareType = sdkShareType;
        final Activity context = (Activity) Cocos2dxActivity.getContext();
        final String str = content;
        final String str2 = title;
        final String str3 = img;
        final String str4 = url;
        final int i = callback;
        context.runOnUiThread(new Runnable() {
            @SuppressLint({"NewApi"})
            public void run() {
                ShareManager.getInstance(context).share(context, str, str2, str3, str4, finalSdkShareType, new PlatformActionListener() {
                    public void onError(Platform arg0, int arg1, Throwable arg2) {
                        Log.i("oneSDK Log", "share onError");
                        Cocos2dxHelper.runOnGLThread(new Runnable() {
                            public void run() {
                                Cocos2dxLuaJavaBridge.callLuaFunctionWithString(i, "error");
                                Cocos2dxLuaJavaBridge.releaseLuaFunction(i);
                            }
                        });
                    }

                    public void onComplete(Platform arg0, int arg1, HashMap<String, Object> hashMap) {
                        Log.i("oneSDK Log", "share onComplete");
                        Cocos2dxHelper.runOnGLThread(new Runnable() {
                            public void run() {
                                Cocos2dxLuaJavaBridge.callLuaFunctionWithString(i, GraphResponse.SUCCESS_KEY);
                                Cocos2dxLuaJavaBridge.releaseLuaFunction(i);
                            }
                        });
                    }

                    public void onCancel(Platform arg0, int arg1) {
                        Log.i("oneSDK Log", "share onCancel");
                        Cocos2dxHelper.runOnGLThread(new Runnable() {
                            public void run() {
                                Cocos2dxLuaJavaBridge.callLuaFunctionWithString(i, "cancel");
                                Cocos2dxLuaJavaBridge.releaseLuaFunction(i);
                            }
                        });
                    }
                });
            }
        });
    }

    public static boolean isPackageAvilible(String packageName) {
        List<PackageInfo> pinfo = ((Activity) Cocos2dxActivity.getContext()).getPackageManager().getInstalledPackages(0);
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                if (((PackageInfo) pinfo.get(i)).packageName.equals(packageName)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean startComponent(String packageName, String componentName) {
        boolean isPackageAvilible = isPackageAvilible(packageName);
        if (isPackageAvilible) {
            Intent intent = new Intent();
            ComponentName cmp = new ComponentName(packageName, componentName);
            intent.setAction("android.intent.action.MAIN");
            intent.addCategory("android.intent.category.LAUNCHER");
            intent.addFlags(268435456);
            intent.setComponent(cmp);
            ((Activity) Cocos2dxActivity.getContext()).startActivity(intent);
        }
        return isPackageAvilible;
    }

    public static void saveImageToGallery(String fileName) {
        Activity context = (Activity) Cocos2dxActivity.getContext();
        File file0 = new File(fileName);
        try {
            Media.insertImage(context.getContentResolver(), file0.getAbsolutePath(), fileName, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        context.sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", Uri.parse("file://" + file0.getAbsoluteFile())));
    }

    public static void luaSubmitDataOnEnterMainViewNextmv(String paramTable) {
    }

    private static GameRoleInfo createGameRoleInfoByJsonString(String jsonString) {
        int vipLevel = 0;
        String roleName = "";
        String roleId = "";
        int roleLevel = 0;
        String serverName = "";
        String serverId = "";
        long roleCTime = 0;
        try {
            JSONObject json = new JSONObject(jsonString);
            vipLevel = json.optInt("vipLevel");
            roleName = json.optString("roleName");
            roleId = json.optString("roleId");
            roleLevel = json.optInt("rate");
            serverName = json.optString("serverName");
            serverId = json.optString("serverId");
            roleCTime = json.optLong("roleCTime");
        } catch (JSONException e) {
            Log.e("DataSDKManager", "Get jsonData JSON error!");
        }
        GameRoleInfo gameInfo = new GameRoleInfo();
        gameInfo.setVipLevel(vipLevel);
        gameInfo.setRoleName(roleName);
        gameInfo.setRoleId(roleId);
        gameInfo.setRoleLevel(roleLevel);
        gameInfo.setServerName(serverName);
        gameInfo.setServerId(serverId);
        gameInfo.setRoleCTime(roleCTime);
        return gameInfo;
    }

    public static void firstCharge() {
        ((Activity) Cocos2dxActivity.getContext()).runOnUiThread(new Runnable() {
            public void run() {
                BtOneSDKManager.getInstance().onObtainNewRolePack();
            }
        });
    }

    public static void finishNewerGuide() {
        ((Activity) Cocos2dxActivity.getContext()).runOnUiThread(new Runnable() {
            public void run() {
                BtOneSDKManager.getInstance().onFinishNewRoleTutorial();
            }
        });
    }

    public static void luaGoEvaluate() {
        final Activity context = (Activity) Cocos2dxActivity.getContext();
        context.runOnUiThread(new Runnable() {
            public void run() {
                BtOneSDKManager.getInstance().rateUs(context);
            }
        });
    }

    public static void luaShowTransferCustomDialog(String paramTable) {
    }

    public static void luaGetWebAccountLogin(int callback) {
    }

    public static boolean luaGetNeedCustomAccount() {
        return false;
    }

    public static void luaStatisticsGetStoreCoinsByPurchaseEventNiceplay(double money, int coinsAmount) {
    }

    public static void luaSubmitData(String id, String paramTable) {
    }

    public static boolean isObbSwitch() {
        ExpansionManager.init((Activity) Cocos2dxActivity.getContext());
        return ExpansionManager.isObbSwitch();
    }
}
