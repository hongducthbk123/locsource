package org.cocos2dx.lib;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.opengl.GLSurfaceView.EGLConfigChooser;
import android.opengl.GLSurfaceView.EGLContextFactory;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Message;
import android.preference.PreferenceManager.OnActivityResultListener;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import com.chukong.cocosplay.client.CocosPlayClient;
import com.facebook.internal.ServerProtocol;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import org.cocos2dx.lib.Cocos2dxHandler.DialogMessage;
import org.cocos2dx.lib.Cocos2dxHelper.Cocos2dxHelperListener;

public abstract class Cocos2dxActivity extends Activity implements Cocos2dxHelperListener {
    private static final String TAG = Cocos2dxActivity.class.getSimpleName();
    private static Cocos2dxActivity sContext = null;
    private boolean hasFocus = false;
    private Cocos2dxEditBoxHelper mEditBoxHelper = null;
    protected ResizeLayout mFrameLayout = null;
    private int[] mGLContextAttrs = null;
    /* access modifiers changed from: private */
    public Cocos2dxGLSurfaceView mGLSurfaceView = null;
    private Cocos2dxHandler mHandler = null;
    private Cocos2dxVideoHelper mVideoHelper = null;
    private Cocos2dxWebViewHelper mWebViewHelper = null;

    private class Cocos2dxEGLConfigChooser implements EGLConfigChooser {
        private final int EGL_OPENGL_ES2_BIT = 4;
        private final int EGL_OPENGL_ES3_BIT = 64;
        private int[] mConfigAttributes;

        public Cocos2dxEGLConfigChooser(int redSize, int greenSize, int blueSize, int alphaSize, int depthSize, int stencilSize) {
            this.mConfigAttributes = new int[]{redSize, greenSize, blueSize, alphaSize, depthSize, stencilSize};
        }

        public Cocos2dxEGLConfigChooser(int[] attributes) {
            this.mConfigAttributes = attributes;
        }

        public EGLConfig chooseConfig(EGL10 egl, EGLDisplay display) {
            for (int[] eglAtribute : new int[][]{new int[]{12324, this.mConfigAttributes[0], 12323, this.mConfigAttributes[1], 12322, this.mConfigAttributes[2], 12321, this.mConfigAttributes[3], 12325, this.mConfigAttributes[4], 12326, this.mConfigAttributes[5], 12352, 4, 12344}, new int[]{12352, 4, 12344}}) {
                EGLConfig result = doChooseConfig(egl, display, eglAtribute);
                if (result != null) {
                    return result;
                }
            }
            Log.e("device_policy", "Can not select an EGLConfig for rendering.");
            return null;
        }

        private EGLConfig doChooseConfig(EGL10 egl, EGLDisplay display, int[] attributes) {
            EGLConfig[] configs = new EGLConfig[1];
            int[] matchedConfigNum = new int[1];
            if (!egl.eglChooseConfig(display, attributes, configs, 1, matchedConfigNum) || matchedConfigNum[0] <= 0) {
                return null;
            }
            return configs[0];
        }
    }

    private static class ContextFactory implements EGLContextFactory {
        private static int EGL_CONTEXT_CLIENT_VERSION = 12440;

        private ContextFactory() {
        }

        public EGLContext createContext(EGL10 egl, EGLDisplay display, EGLConfig eglConfig) {
            return egl.eglCreateContext(display, eglConfig, EGL10.EGL_NO_CONTEXT, new int[]{EGL_CONTEXT_CLIENT_VERSION, 2, 12344});
        }

        public void destroyContext(EGL10 egl, EGLDisplay display, EGLContext context) {
            egl.eglDestroyContext(display, context);
        }
    }

    private static native int[] getGLContextAttrs();

    public Cocos2dxGLSurfaceView getGLSurfaceView() {
        return this.mGLSurfaceView;
    }

    public static Context getContext() {
        return sContext;
    }

    public void setKeepScreenOn(boolean value) {
        final boolean newValue = value;
        runOnUiThread(new Runnable() {
            public void run() {
                Cocos2dxActivity.this.mGLSurfaceView.setKeepScreenOn(newValue);
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onLoadNativeLibraries() {
        try {
            System.loadLibrary(getPackageManager().getApplicationInfo(getPackageName(), 128).metaData.getString("android.app.lib_name"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CocosPlayClient.init(this, false);
        hideVirtualButton();
        onLoadNativeLibraries();
        sContext = this;
        this.mHandler = new Cocos2dxHandler(this);
        Cocos2dxHelper.init(this);
        this.mGLContextAttrs = getGLContextAttrs();
        init();
        if (this.mVideoHelper == null) {
            this.mVideoHelper = new Cocos2dxVideoHelper(this, this.mFrameLayout);
        }
        if (this.mWebViewHelper == null) {
            this.mWebViewHelper = new Cocos2dxWebViewHelper(this.mFrameLayout);
        }
        if (this.mEditBoxHelper == null) {
            this.mEditBoxHelper = new Cocos2dxEditBoxHelper(this.mFrameLayout);
        }
        getWindow().setSoftInputMode(32);
        setVolumeControlStream(3);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        Log.d(TAG, "onResume()");
        super.onResume();
        hideVirtualButton();
        resumeIfHasFocus();
    }

    public void onWindowFocusChanged(boolean hasFocus2) {
        Log.d(TAG, "onWindowFocusChanged() hasFocus=" + hasFocus2);
        super.onWindowFocusChanged(hasFocus2);
        this.hasFocus = hasFocus2;
        resumeIfHasFocus();
    }

    private void resumeIfHasFocus() {
        if (this.hasFocus) {
            hideVirtualButton();
            Cocos2dxHelper.onResume();
            this.mGLSurfaceView.onResume();
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        Log.d(TAG, "onPause()");
        super.onPause();
        Cocos2dxHelper.onPause();
        this.mGLSurfaceView.onPause();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
    }

    public void showDialog(String pTitle, String pMessage) {
        Message msg = new Message();
        msg.what = 1;
        msg.obj = new DialogMessage(pTitle, pMessage);
        this.mHandler.sendMessage(msg);
    }

    public void runOnGLThread(Runnable pRunnable) {
        this.mGLSurfaceView.queueEvent(pRunnable);
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        for (OnActivityResultListener listener : Cocos2dxHelper.getOnActivityResultListeners()) {
            listener.onActivityResult(requestCode, resultCode, data);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void init() {
        LayoutParams framelayout_params = new LayoutParams(-1, -1);
        this.mFrameLayout = new ResizeLayout(this);
        this.mFrameLayout.setLayoutParams(framelayout_params);
        LayoutParams edittext_layout_params = new LayoutParams(-1, -2);
        Cocos2dxEditBox edittext = new Cocos2dxEditBox(this);
        edittext.setLayoutParams(edittext_layout_params);
        this.mFrameLayout.addView(edittext);
        this.mGLSurfaceView = onCreateView();
        this.mFrameLayout.addView(this.mGLSurfaceView);
        if (isAndroidEmulator()) {
            this.mGLSurfaceView.setEGLConfigChooser(8, 8, 8, 8, 16, 0);
        }
        this.mGLSurfaceView.setCocos2dxRenderer(new Cocos2dxRenderer());
        this.mGLSurfaceView.setCocos2dxEditText(edittext);
        setContentView(this.mFrameLayout);
    }

    public Cocos2dxGLSurfaceView onCreateView() {
        Cocos2dxGLSurfaceView glSurfaceView = new Cocos2dxGLSurfaceView(this);
        if (this.mGLContextAttrs[3] > 0) {
            glSurfaceView.getHolder().setFormat(-3);
        }
        glSurfaceView.setEGLConfigChooser(new Cocos2dxEGLConfigChooser(this.mGLContextAttrs));
        glSurfaceView.setEGLContextFactory(new ContextFactory());
        return glSurfaceView;
    }

    /* access modifiers changed from: protected */
    public void hideVirtualButton() {
        if (VERSION.SDK_INT >= 19) {
            Class viewClass = View.class;
            try {
                int SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION = ((Integer) Cocos2dxReflectionHelper.getConstantValue(viewClass, "SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION")).intValue();
                int SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN = ((Integer) Cocos2dxReflectionHelper.getConstantValue(viewClass, "SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN")).intValue();
                int SYSTEM_UI_FLAG_HIDE_NAVIGATION = ((Integer) Cocos2dxReflectionHelper.getConstantValue(viewClass, "SYSTEM_UI_FLAG_HIDE_NAVIGATION")).intValue();
                int SYSTEM_UI_FLAG_FULLSCREEN = ((Integer) Cocos2dxReflectionHelper.getConstantValue(viewClass, "SYSTEM_UI_FLAG_FULLSCREEN")).intValue();
                Class[] clsArr = {Integer.TYPE};
                Cocos2dxReflectionHelper.invokeInstanceMethod(getWindow().getDecorView(), "setSystemUiVisibility", clsArr, new Object[]{Integer.valueOf(((Integer) Cocos2dxReflectionHelper.getConstantValue(viewClass, "SYSTEM_UI_FLAG_LAYOUT_STABLE")).intValue() | SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | SYSTEM_UI_FLAG_HIDE_NAVIGATION | SYSTEM_UI_FLAG_FULLSCREEN | ((Integer) Cocos2dxReflectionHelper.getConstantValue(viewClass, "SYSTEM_UI_FLAG_IMMERSIVE_STICKY")).intValue())});
            } catch (NullPointerException e) {
                Log.e(TAG, "hideVirtualButton", e);
            }
        }
    }

    private static boolean isAndroidEmulator() {
        Log.d(TAG, "model=" + Build.MODEL);
        String product = Build.PRODUCT;
        Log.d(TAG, "product=" + product);
        boolean isEmulator = false;
        if (product != null) {
            isEmulator = product.equals(ServerProtocol.DIALOG_PARAM_SDK_VERSION) || product.contains("_sdk") || product.contains("sdk_");
        }
        Log.d(TAG, "isEmulator=" + isEmulator);
        return isEmulator;
    }
}
