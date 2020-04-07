package com.btgame.facebook;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.text.TextUtils;
import com.btgame.appevents.constant.AppEventsConfig;
import com.btgame.facebook.constant.FbConfig;
import com.btgame.seasdk.btcore.common.constant.EventType;
import com.btgame.seasdk.btcore.common.constant.StatusCode;
import com.btgame.seasdk.btcore.common.constant.TrackEventType;
import com.btgame.seasdk.btcore.common.event.AppEventsEvent;
import com.btgame.seasdk.btcore.common.event.LifecycleEvent;
import com.btgame.seasdk.btcore.common.event.SdkEventManager;
import com.btgame.seasdk.btcore.common.event.ShareEvent;
import com.btgame.seasdk.btcore.common.event.ShareResultEvent;
import com.btgame.seasdk.btcore.common.event.data.TrackEvent;
import com.btgame.seasdk.btcore.common.event.login.ThirdAccountEvent;
import com.btgame.seasdk.btcore.common.event.login.ThirdAccountResultEvent.Builder;
import com.btgame.seasdk.btcore.common.util.BtsdkLog;
import com.btgame.seasdk.btcore.common.util.ContextUtil;
import com.btgame.seasdk.btcore.common.util.SharedPreferencesUtils;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.CallbackManager.Factory;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.share.Sharer.Result;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;
import java.io.File;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.Currency;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class BtFacebookManager {
    private CallbackManager callbackManager;
    private ShareDialog shareDialog;

    private static class BtFacebookHelperHolder {
        /* access modifiers changed from: private */
        public static final BtFacebookManager BT_FACEBOOK_MANAGER = new BtFacebookManager();

        private BtFacebookHelperHolder() {
        }
    }

    private BtFacebookManager() {
        SdkEventManager.register(this);
    }

    public static BtFacebookManager getInstance() {
        return BtFacebookHelperHolder.BT_FACEBOOK_MANAGER;
    }

    private CallbackManager getCallbackManager() {
        if (this.callbackManager == null) {
            synchronized (BtFacebookManager.class) {
                this.callbackManager = Factory.create();
            }
        }
        return this.callbackManager;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLifecycleEvent(LifecycleEvent lifecycleEvent) {
        switch (lifecycleEvent.getLifecycleEventType()) {
            case onApplicationCreate:
                onApplicationCreate(lifecycleEvent.getApplication());
                return;
            case onGameActivityResult:
            case onSdkActivityResult:
                onActivityResult(lifecycleEvent.getActivity(), lifecycleEvent.getRequestCode(), lifecycleEvent.getResultCode(), lifecycleEvent.getData());
                return;
            default:
                return;
        }
    }

    private void onApplicationCreate(Application application) {
        FacebookSdk.sdkInitialize(application);
    }

    private void onActivityResult(Activity activity, int requestCode, int resultCode, Intent data) {
        getCallbackManager().onActivityResult(requestCode, resultCode, data);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onThirdAccountEvent(ThirdAccountEvent loginEvent) {
        if (loginEvent.getPlatform().equalsIgnoreCase("FB")) {
            switch (loginEvent.getEventType()) {
                case LOGIN_REQ:
                    login(loginEvent.getActivity(), loginEvent.getFragment(), loginEvent.getBindId(), loginEvent.isIgnoreCache());
                    return;
                case LOGOUT_REQ:
                    logout();
                    return;
                default:
                    return;
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onShareEvent(ShareEvent shareEvent) {
        if (shareEvent.getPlatform().equalsIgnoreCase("FB")) {
            Activity activity = shareEvent.getActivity();
            String shareUrl = shareEvent.getShareUrl();
            String shareImagePath = shareEvent.getShareImagePath();
            if (!TextUtils.isEmpty(shareUrl)) {
                shareUrl(activity, shareUrl);
            } else if (!TextUtils.isEmpty(shareImagePath)) {
                shareImage(activity, shareImagePath);
            }
        }
    }

    public void login(Activity activity, Fragment fragment, final String bindId, boolean ignoreCache) {
        FacebookCallback<LoginResult> facebookCallback = new FacebookCallback<LoginResult>() {
            public void onSuccess(LoginResult loginResult) {
                AccessToken accessToken = loginResult.getAccessToken();
                if (accessToken != null) {
                    SharedPreferencesUtils.setBoolean(FbConfig.FLAG_LOGIN, true);
                    BtsdkLog.m1429d("FB登录成功,UserId:" + accessToken.getUserId() + " accessToken:" + accessToken.getToken());
                    SdkEventManager.postEvent(new TrackEvent(TrackEventType.FB_AUTHORIZE_SUCCESS));
                    SdkEventManager.postEvent(new Builder(EventType.LOGIN_RES).statusCode(StatusCode.LOGIN_SUCCESS).thirdId("1").platform("FB").platformUid(accessToken.getUserId()).platformToken(accessToken.getToken()).bindId(bindId).build());
                }
            }

            public void onCancel() {
                SharedPreferencesUtils.setBoolean(FbConfig.FLAG_LOGIN, false);
                SdkEventManager.postEvent(new TrackEvent.Builder().eventType(TrackEventType.FB_AUTHORIZE_FAILED).code(Integer.valueOf(StatusCode.LOGIN_CANCEL.getCode())).description("FB登录取消").build());
                SdkEventManager.postEvent(new Builder(EventType.LOGIN_RES).statusCode(StatusCode.LOGIN_CANCEL).platform("FB").bindId(bindId).build());
            }

            public void onError(FacebookException exception) {
                SharedPreferencesUtils.setBoolean(FbConfig.FLAG_LOGIN, false);
                BtsdkLog.m1429d("FB登录失败,exception:" + exception.getLocalizedMessage());
                SdkEventManager.postEvent(new TrackEvent.Builder().eventType(TrackEventType.FB_AUTHORIZE_FAILED).code(Integer.valueOf(StatusCode.LOGIN_FAIL.getCode())).description(exception.getLocalizedMessage()).build());
                SdkEventManager.postEvent(new Builder(EventType.LOGIN_RES).statusCode(StatusCode.LOGIN_FAIL).des(exception.getLocalizedMessage()).platform("FB").bindId(bindId).build());
            }
        };
        LoginManager.getInstance().registerCallback(getCallbackManager(), facebookCallback);
        List<String> readPermissions = Arrays.asList(new String[]{"public_profile"});
        AccessToken accessToken = null;
        if (!ignoreCache && SharedPreferencesUtils.getBoolean(FbConfig.FLAG_LOGIN)) {
            accessToken = AccessToken.getCurrentAccessToken();
        }
        if (ignoreCache || accessToken == null || accessToken.isExpired()) {
            SdkEventManager.postEvent(new TrackEvent(TrackEventType.FB_REQUEST_AUTHORIZATION));
            if (fragment == null) {
                LoginManager.getInstance().logInWithReadPermissions(activity, (Collection<String>) readPermissions);
            } else {
                LoginManager.getInstance().logInWithReadPermissions(fragment, (Collection<String>) readPermissions);
            }
        } else {
            facebookCallback.onSuccess(new LoginResult(accessToken, null, null));
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onAppEventsEvent(AppEventsEvent appEventsEvent) {
        Context context = appEventsEvent.getContext();
        String platforms = appEventsEvent.getPlatforms();
        String channelId = appEventsEvent.getChannelId();
        String userId = appEventsEvent.getUserId();
        String roleId = appEventsEvent.getRoleId();
        String eventName = appEventsEvent.getEventName();
        String eventToken = appEventsEvent.getEventToken();
        String orderId = appEventsEvent.getOrderId();
        String amount = appEventsEvent.getAmount();
        String currencyCode = appEventsEvent.getCurrencyCode();
        Map<String, String> extendParams = appEventsEvent.getExtendParams();
        if (platforms.contains("FB")) {
            Bundle params = new Bundle();
            Double valToSum = null;
            if (!TextUtils.isEmpty(channelId)) {
                params.putString("channelId", channelId);
            }
            if (!TextUtils.isEmpty(userId)) {
                params.putString("userId", userId);
            }
            if (!TextUtils.isEmpty(roleId)) {
                params.putString("roleId", roleId);
            }
            if (!TextUtils.isEmpty(orderId)) {
                params.putString("orderId", orderId);
            }
            if (!TextUtils.isEmpty(amount)) {
                params.putString("amount", amount);
                valToSum = Double.valueOf(Double.parseDouble(amount));
            }
            if (!TextUtils.isEmpty(currencyCode)) {
                params.putString("currencyCode", currencyCode);
            }
            if (extendParams != null && !extendParams.isEmpty()) {
                StringBuilder stringBuilder = new StringBuilder();
                for (Entry<String, String> extendParamEntry : extendParams.entrySet()) {
                    stringBuilder.append((String) extendParamEntry.getKey()).append(":").append((String) extendParamEntry.getValue());
                    params.putString((String) extendParamEntry.getKey(), (String) extendParamEntry.getValue());
                }
                BtsdkLog.m1429d(stringBuilder.toString());
            }
            if (AppEventsConfig.ev_activateApp.equals(eventName)) {
                AppEventsLogger.activateApp(ContextUtil.getApplicationContext());
            } else {
                logEvent(context, eventName, valToSum, params);
            }
        }
    }

    private void logEvent(Context context, String eventName, Double valToSum, Bundle params) {
        try {
            AppEventsLogger logger = AppEventsLogger.newLogger(context);
            if (valToSum == null) {
                logger.logEvent(eventName, params);
            } else {
                logger.logEvent(eventName, valToSum.doubleValue(), params);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void logPurchase(Context context, Double valToSum, String currencyCode, Bundle params) {
        try {
            AppEventsLogger.newLogger(context).logPurchase(BigDecimal.valueOf(valToSum.doubleValue()), Currency.getInstance(currencyCode.toUpperCase()), params);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void logout() {
        SharedPreferencesUtils.setBoolean(FbConfig.FLAG_LOGIN, false);
        LoginManager.getInstance().logOut();
        SdkEventManager.postEvent(new Builder(EventType.LOGOUT_RES).statusCode(StatusCode.LOGOUT_SUCCESS).platform("FB").build());
    }

    private void initShare(Activity activity) {
        this.shareDialog = new ShareDialog(activity);
        this.shareDialog.registerCallback(getCallbackManager(), new FacebookCallback<Result>() {
            public void onSuccess(Result result) {
                BtsdkLog.m1429d("Facebook分享成功");
                BtFacebookManager.this.postShareResultEvent(StatusCode.SHARE_SUCCESS);
            }

            public void onCancel() {
                BtsdkLog.m1429d("Facebook分享取消");
                BtFacebookManager.this.postShareResultEvent(StatusCode.SHARE_CANCEL);
            }

            public void onError(FacebookException e) {
                BtsdkLog.m1430e("Facebook分享异常:" + e.getLocalizedMessage());
                BtFacebookManager.this.postShareResultEvent(StatusCode.SHARE_FAIL);
            }
        });
    }

    /* access modifiers changed from: private */
    public void postShareResultEvent(StatusCode statusCode) {
        SdkEventManager.postEvent(new ShareResultEvent.Builder().platform("FB").statusCode(statusCode).build());
    }

    public void shareUrl(Activity activity, String url) {
        if (this.shareDialog == null) {
            initShare(activity);
        }
        if (ShareDialog.canShow(ShareLinkContent.class)) {
            this.shareDialog.show(((ShareLinkContent.Builder) new ShareLinkContent.Builder().setContentUrl(Uri.parse(url))).build());
        }
    }

    public void shareImage(Activity activity, String path) {
        if (this.shareDialog == null) {
            initShare(activity);
        }
        if (ShareDialog.canShow(ShareLinkContent.class)) {
            String imagePath = path;
            if (new File(imagePath).exists()) {
                this.shareDialog.show(new SharePhotoContent.Builder().addPhoto(new SharePhoto.Builder().setBitmap(BitmapFactory.decodeFile(imagePath)).build()).build());
            }
        }
    }
}
