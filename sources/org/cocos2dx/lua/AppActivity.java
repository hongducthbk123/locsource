package org.cocos2dx.lua;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Process;
import android.util.Log;
import com.btgame.onesdk.BtOneSDKManager;
import java.util.ArrayList;
import java.util.List;
import org.cocos2dx.extension.CocosOneSDKManager;
import org.cocos2dx.extension.DataSDKManager;
import org.cocos2dx.extension.LuaJavaBridge;
import org.cocos2dx.extension.NetworkStateService;
import org.cocos2dx.lib.Cocos2dxActivity;
import org.cocos2dx.lib.Cocos2dxHelper;
import org.cocos2dx.lib.Cocos2dxLuaJavaBridge;
import org.fmod.FMOD;
import p004cn.jpush.android.api.JPushInterface;

public class AppActivity extends Cocos2dxActivity {
    static String hostIPAdress = "0.0.0.0";
    public static boolean isForeground = false;
    /* access modifiers changed from: private */
    public static int m_networkChangeCallback = -1;
    /* access modifiers changed from: private */
    public static int m_networkStatus = 1;
    private NetworkChangeReceiver netWorkChangeReceiver;

    class NetworkChangeReceiver extends BroadcastReceiver {
        NetworkChangeReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            final int networkStatus = intent.getExtras().getInt("networkStatus", 1);
            Log.i("NetWorkChangeReceiver", "networkStatus:" + networkStatus);
            AppActivity.m_networkStatus = networkStatus;
            Cocos2dxHelper.runOnGLThread(new Runnable() {
                public void run() {
                    if (AppActivity.m_networkChangeCallback != -1) {
                        int temp = AppActivity.m_networkChangeCallback;
                        AppActivity.m_networkChangeCallback = -1;
                        Cocos2dxLuaJavaBridge.callLuaFunctionWithString(temp, "" + networkStatus);
                        Cocos2dxLuaJavaBridge.releaseLuaFunction(temp);
                    }
                }
            });
        }
    }

    private static native boolean nativeIsDebug();

    private static native boolean nativeIsLandScape();

    static {
        try {
            System.loadLibrary("fmod");
            System.loadLibrary("fmodstudio");
        } catch (UnsatisfiedLinkError e) {
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        CocosOneSDKManager.setContext(this);
        DataSDKManager.setContext(this);
        super.onCreate(savedInstanceState);
        CocosOneSDKManager.nativeInitSDK();
        JPushInterface.init(this);
        FMOD.init(this);
        startService(new Intent(this, NetworkStateService.class));
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(NetworkStateService.NETWORKSTATE);
        this.netWorkChangeReceiver = new NetworkChangeReceiver();
        registerReceiver(this.netWorkChangeReceiver, intentFilter);
        if (nativeIsLandScape()) {
            setRequestedOrientation(6);
        } else {
            setRequestedOrientation(7);
        }
        processExtraData();
        getWindow().setFlags(128, 128);
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService("connectivity");
        if (cm != null) {
            NetworkInfo networkInfo = cm.getActiveNetworkInfo();
            List<Integer> networkTypes = new ArrayList<>();
            networkTypes.add(Integer.valueOf(1));
            try {
                networkTypes.add(Integer.valueOf(ConnectivityManager.class.getDeclaredField("TYPE_ETHERNET").getInt(null)));
            } catch (NoSuchFieldException e) {
            } catch (IllegalAccessException iae) {
                throw new RuntimeException(iae);
            }
            if (networkInfo != null && networkTypes.contains(Integer.valueOf(networkInfo.getType()))) {
                return true;
            }
        }
        return false;
    }

    public String getHostIpAddress() {
        int ip = ((WifiManager) getSystemService("wifi")).getConnectionInfo().getIpAddress();
        int ip2 = ip >>> 8;
        int ip3 = ip2 >>> 8;
        return (ip & 255) + "." + (ip2 & 255) + "." + (ip3 & 255) + "." + ((ip3 >>> 8) & 255);
    }

    public static String getLocalIpAddress() {
        return hostIPAdress;
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        isForeground = true;
        super.onResume();
        BtOneSDKManager.getInstance().onResume(this);
        JPushInterface.onResume(this);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        isForeground = false;
        Log.i("onPause", "onPauseee");
        super.onPause();
        BtOneSDKManager.getInstance().onPause(this);
        JPushInterface.onPause(this);
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        BtOneSDKManager.getInstance().onStart(this);
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        BtOneSDKManager.getInstance().onNewIntent(this, intent);
        processExtraData();
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        BtOneSDKManager.getInstance().onStop(this);
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        BtOneSDKManager.getInstance().onActivityResult(this, requestCode, resultCode, data);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        FMOD.close();
        super.onDestroy();
        unregisterReceiver(this.netWorkChangeReceiver);
        BtOneSDKManager.getInstance().onDestroy(this);
        Process.killProcess(Process.myPid());
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        BtOneSDKManager.getInstance().onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        BtOneSDKManager.getInstance().onWindowFocusChanged(this, hasFocus);
    }

    public void onBackPressed() {
        super.onBackPressed();
        BtOneSDKManager.getInstance().onBackPressed(this);
    }

    private void processExtraData() {
        Intent intent = getIntent();
        if (intent != null) {
            String extrInfo = intent.getStringExtra("extrInfo");
            if (extrInfo != null) {
                LuaJavaBridge.setLastNotiyExtrInfo(extrInfo);
                Log.i("onNewIntent: ", extrInfo);
            }
        }
    }

    public static void luaSetNetWorkChangeCallback(int luaFunc) {
        if (m_networkChangeCallback != -1) {
            Cocos2dxLuaJavaBridge.releaseLuaFunction(m_networkChangeCallback);
        }
        m_networkChangeCallback = luaFunc;
    }

    public static int luaGetNetworkStatus() {
        return m_networkStatus;
    }
}
