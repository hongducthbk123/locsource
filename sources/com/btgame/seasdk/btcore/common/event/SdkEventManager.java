package com.btgame.seasdk.btcore.common.event;

import android.app.Activity;
import android.app.Application;
import android.text.TextUtils;
import android.util.Log;
import com.btgame.seasdk.btcore.common.constant.LifecycleEventType;
import com.btgame.seasdk.btcore.common.util.BTResourceUtil;
import com.btgame.seasdk.btcore.common.util.BTScreenUtil;
import com.btgame.seasdk.btcore.common.util.BtUtils;
import com.btgame.seasdk.btcore.common.util.BtsdkLog;
import com.btgame.seasdk.btcore.common.util.ContextUtil;
import com.btgame.seasdk.btcore.common.util.DebugUtils;
import com.btgame.seasdk.btcore.common.util.permission.BTPermissionChecker;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class SdkEventManager {

    private static class SdkEventManagerHolder {
        /* access modifiers changed from: private */
        public static final SdkEventManager sdkEventManager = new SdkEventManager();

        private SdkEventManagerHolder() {
        }
    }

    private SdkEventManager() {
    }

    public static void register(Object subscriber) {
        EventBus.getDefault().register(subscriber);
    }

    public static void postEvent(Object object) {
        EventBus.getDefault().post(object);
    }

    public static void cancelEventDelivery(Object event) {
        EventBus.getDefault().cancelEventDelivery(event);
    }

    public static void initEventListener(Application application) {
        ContextUtil.init(BTResourceUtil.changeLocale(application, null));
        DebugUtils.getInstance().setLogFlag(BTResourceUtil.getApplicationMetaBooleanData("sdkLogConfig"));
        DebugUtils.getInstance().setCodeFlag(BTResourceUtil.getApplicationMetaBooleanData("sdkCodeConfig"));
        register(SdkEventManagerHolder.sdkEventManager);
        BTPermissionChecker.getInstance().registerToEventManager();
        registerEventListener(application);
    }

    private static void registerEventListener(Application application) {
        String[] eventListeners;
        String bt_event_listeners = BTResourceUtil.getApplicationMetaData("bt_event_listeners");
        if (TextUtils.isEmpty(bt_event_listeners)) {
            throw new RuntimeException("请设置bt_event_listeners");
        }
        for (String listener : bt_event_listeners.replaceAll("\\s*", "").split(",")) {
            if (!TextUtils.isEmpty(listener)) {
                try {
                    Class.forName(listener).getMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
                } catch (Exception e) {
                    throw new RuntimeException(e.getLocalizedMessage());
                }
            }
        }
    }

    @Subscribe(priority = Integer.MAX_VALUE, threadMode = ThreadMode.MAIN)
    public void onLifecycleEvent(LifecycleEvent lifecycleEvent) {
        LifecycleEventType lifecycleEventType = lifecycleEvent.getLifecycleEventType();
        Activity activity = lifecycleEvent.getActivity();
        switch (lifecycleEventType) {
            case onApplicationCreate:
                Log.d(BtsdkLog.TAG, "PackageName->" + lifecycleEvent.getApplication().getPackageName());
                Log.d(BtsdkLog.TAG, "SHA1->" + BtUtils.getSHA1(lifecycleEvent.getApplication()));
                Log.d(BtsdkLog.TAG, "KeyHash->" + BtUtils.getKeyHash(lifecycleEvent.getApplication()));
                Log.d(BtsdkLog.TAG, "-----------------------------------------------------------------");
                return;
            case onGameActivityCreate:
            case onSdkActivityCreate:
            case onGameActivityResume:
            case onSdkActivityResume:
                BTResourceUtil.changeLocale(activity, null);
                ContextUtil.upCurrentActivity(activity);
                return;
            case onGameWindowFocusChanged:
            case onSdkWindowFocusChanged:
                if (lifecycleEvent.isHasFocus()) {
                    BTScreenUtil.getInstance(activity).hideNavigationBar(activity.getWindow());
                    return;
                }
                return;
            case onGameExit:
                ContextUtil.destroy(lifecycleEvent.getActivity());
                return;
            case onRequestPermissionsResult:
                BTPermissionChecker.getInstance().onRequestPermissionsResult(lifecycleEvent.getRequestCode(), lifecycleEvent.getPermissions(), lifecycleEvent.getGrantResults());
                return;
            default:
                return;
        }
    }
}
