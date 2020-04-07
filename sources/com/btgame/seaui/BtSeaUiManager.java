package com.btgame.seaui;

import android.app.Activity;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.FragmentActivity;
import android.text.TextUtils;
import com.btgame.seasdk.btcore.common.constant.EventType;
import com.btgame.seasdk.btcore.common.constant.LifecycleEventType;
import com.btgame.seasdk.btcore.common.constant.StatusCode;
import com.btgame.seasdk.btcore.common.constant.TrackEventType;
import com.btgame.seasdk.btcore.common.event.LifecycleEvent;
import com.btgame.seasdk.btcore.common.event.SdkEventManager;
import com.btgame.seasdk.btcore.common.event.data.TrackEvent;
import com.btgame.seasdk.btcore.common.event.init.ReqInitResultEvent;
import com.btgame.seasdk.btcore.common.event.login.SdkAccountEvent;
import com.btgame.seasdk.btcore.common.event.login.SdkAccountResultEvent;
import com.btgame.seasdk.btcore.common.event.login.SdkAccountResultEvent.Builder;
import com.btgame.seasdk.btcore.common.event.login.ThirdAccountEvent;
import com.btgame.seasdk.btcore.common.event.login.ThirdAccountResultEvent;
import com.btgame.seasdk.btcore.common.util.BTResourceUtil;
import com.btgame.seasdk.btcore.common.util.ContextUtil;
import com.btgame.seasdk.btcore.common.util.MD5Util;
import com.btgame.seasdk.btcore.common.util.SharedPreferencesUtils;
import com.btgame.seasdk.btcore.common.util.http.OkHttpUtil;
import com.btgame.seasdk.btcore.widget.BtToast;
import com.btgame.seasdk.btcore.widget.LoadingDialog;
import com.btgame.seasdk.task.constant.TaskConfig;
import com.btgame.seasdk.task.entity.OpType;
import com.btgame.seasdk.task.entity.request.AutoLoginData;
import com.btgame.seasdk.task.entity.response.LoginResult;
import com.btgame.seasdk.task.entity.response.OpResult;
import com.btgame.seasdk.task.entity.response.ReqInitResult;
import com.btgame.seaui.p045ui.BtSeaLoginActivity;
import com.btgame.seaui.p045ui.constant.UIConfig;
import com.btgame.seaui.p045ui.constant.UIidAndtag;
import com.btgame.seaui.p045ui.fragment.BaseFragment;
import com.btgame.seaui.p045ui.fragment.BindMailFragment;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class BtSeaUiManager {
    public static final String KEY_BUNDLE_AUTOLOGIN_NAME = "KEY_BUNDLE_AUTOLOGIN_NAME";
    public static final String KEY_BUNDLE_BINDINFO = "KEY_BUNDLE_BINDINFO";
    private static final String LOGIN_SP_ACCOUNT = "LOGIN_SP_ACCOUNT";
    private static final String LOGIN_SP_PWD = "LOGIN_SP_PWD";
    private static final String LOGIN_SP_RESULT = "LOGIN_SP_RESULT";
    private String accountToSave;
    private BtSeaLoginActivity btSeaLoginActivity;
    private boolean mIgnoreLogoutRes;
    private SdkAccountResultEvent mLoginResultEvent;
    private String pwdToSave;

    private static class BtSeaUiManagerHolder {
        /* access modifiers changed from: private */
        public static final BtSeaUiManager btSeaSDKManager = new BtSeaUiManager();

        private BtSeaUiManagerHolder() {
        }
    }

    private BtSeaUiManager() {
        SdkEventManager.register(this);
    }

    public static BtSeaUiManager getInstance() {
        return BtSeaUiManagerHolder.btSeaSDKManager;
    }

    public void reqSaveAccountInfo(String account, String pwd) {
        this.accountToSave = account;
        this.pwdToSave = pwd;
    }

    public String[] getAccountInfo() {
        return new String[]{SharedPreferencesUtils.getString(LOGIN_SP_ACCOUNT), SharedPreferencesUtils.getString(LOGIN_SP_PWD)};
    }

    private void saveLoginInfo(LoginResult loginResult) {
        SharedPreferencesUtils.setString(LOGIN_SP_RESULT, OkHttpUtil.getInstance(ContextUtil.getApplicationContext()).getGson().toJson((Object) loginResult));
        if (OpType.OP_LOGIN_BT.equals(loginResult.getOpType())) {
            if (!TextUtils.isEmpty(this.accountToSave) && this.accountToSave.equals(loginResult.getAccount())) {
                SharedPreferencesUtils.setString(LOGIN_SP_ACCOUNT, this.accountToSave);
                if (!TextUtils.isEmpty(this.pwdToSave)) {
                    SharedPreferencesUtils.setString(LOGIN_SP_PWD, MD5Util.md5Hex(this.pwdToSave));
                }
            }
            this.pwdToSave = null;
            this.accountToSave = null;
        }
    }

    private void cleanAccountInfo(boolean keepPwd) {
        SharedPreferencesUtils.setString(LOGIN_SP_RESULT, null);
        if (!keepPwd) {
            SharedPreferencesUtils.setString(LOGIN_SP_PWD, null);
        }
    }

    private void onBtSeaLoginActivityCreate(boolean memoryReboot) {
        if (!memoryReboot && this.btSeaLoginActivity.getSupportFragmentManager().getBackStackEntryCount() == 0) {
            String loginResultJson = SharedPreferencesUtils.getString(LOGIN_SP_RESULT);
            if (!TextUtils.isEmpty(loginResultJson)) {
                LoginResult loginResult = (LoginResult) OkHttpUtil.getInstance(ContextUtil.getApplicationContext()).getGson().fromJson(loginResultJson, LoginResult.class);
                if (!OpType.OP_LOGIN_GUEST.equals(loginResult.getOpType())) {
                    Bundle bundle = new Bundle();
                    if (OpType.OP_LOGIN_BT.equals(loginResult.getOpType())) {
                        bundle.putString(KEY_BUNDLE_AUTOLOGIN_NAME, loginResult.getAccount());
                    } else {
                        String platform = loginResult.getPlatform();
                        bundle.putString(KEY_BUNDLE_AUTOLOGIN_NAME, BTResourceUtil.findStringByName("autologin_tips_p_" + (platform == null ? "" : platform.toLowerCase())));
                    }
                    startFragment(UIidAndtag.TAG_PAGE_AUTOLOGIN, bundle, true, false);
                }
            }
        }
    }

    private void onBtSeaLoginActivityDestroy() {
        this.btSeaLoginActivity = null;
    }

    private void onBtSeaLoginActivityBackPressed() {
        if (this.mLoginResultEvent == null) {
            this.mLoginResultEvent = new Builder(EventType.LOGIN_RES).statusCode(StatusCode.LOGIN_CANCEL).build();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLifecycleEvent(LifecycleEvent lifecycleEvent) {
        Activity activity = lifecycleEvent.getActivity();
        LifecycleEventType lifecycleEventType = lifecycleEvent.getLifecycleEventType();
        if (activity != null && (activity instanceof BtSeaLoginActivity)) {
            this.btSeaLoginActivity = (BtSeaLoginActivity) activity;
            switch (lifecycleEventType) {
                case onSdkActivityCreate:
                    onBtSeaLoginActivityCreate(lifecycleEvent.getSavedInstanceState() != null);
                    return;
                case onSdkActivityBackPressed:
                    onBtSeaLoginActivityBackPressed();
                    return;
                case onSdkActivityStop:
                    onBtSeaLoginActivityStop();
                    return;
                case onSdkActivityDestroy:
                    onBtSeaLoginActivityDestroy();
                    return;
                default:
                    return;
            }
        }
    }

    private void onBtSeaLoginActivityStop() {
        if (this.mLoginResultEvent != null) {
            SdkEventManager.postEvent(this.mLoginResultEvent);
            this.mLoginResultEvent = null;
        }
    }

    public void doAutoLogin() {
        if (this.btSeaLoginActivity != null) {
            showLoadingDialog();
            LoginResult result = (LoginResult) OkHttpUtil.getInstance(ContextUtil.getApplicationContext()).getGson().fromJson(SharedPreferencesUtils.getString(LOGIN_SP_RESULT), LoginResult.class);
            if (OpType.OP_LOGIN_BT.equals(result.getOpType())) {
                AutoLoginData data = new AutoLoginData.Builder().account(result.getAccount()).identity(result.getIdentity()).build();
                data.setOpType(OpType.OP_LOGIN_BT);
                postData(data);
                return;
            }
            sendThirdLoginEvent(UIidAndtag.TAG_THIRDLOGIN_PREFIX + result.getPlatform(), null, null, false);
            if ("GP".equals(result.getPlatform())) {
                SdkEventManager.postEvent(new TrackEvent(TrackEventType.GP_AUTO_LOGIN));
            } else if ("FB".equals(result.getPlatform())) {
                SdkEventManager.postEvent(new TrackEvent(TrackEventType.FB_AUTO_LOGIN));
            }
        }
    }

    public void toUiPage(String uiTag, Bundle bundle) {
        startFragment(uiTag, bundle, true, true);
    }

    public void sendThirdLoginEvent(String tag, BaseFragment fragment, String bindId, boolean ignoreCache) {
        String[] tags = tag.split(UIidAndtag.TAG_THIRDLOGIN_PREFIX);
        if (tags.length == 2) {
            showLoadingDialog();
            SdkEventManager.postEvent(new ThirdAccountEvent.Builder(EventType.LOGIN_REQ).activity(this.btSeaLoginActivity).fragment(fragment).platform(tags[1]).bindId(bindId).ignoreCache(ignoreCache).build());
        }
    }

    private void showLoadingDialog() {
        if (this.btSeaLoginActivity != null) {
            hideDialog();
            LoadingDialog.showDialog((FragmentActivity) this.btSeaLoginActivity, BTResourceUtil.findStringByName("tips_loading"), (OnCancelListener) null);
        }
    }

    private void showToast(String tips) {
        BtToast.showShortTimeToast(ContextUtil.getApplicationContext(), tips);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSdkAccountEvent(SdkAccountEvent sdkAccountEvent) {
        switch (sdkAccountEvent.getEventType()) {
            case LOGIN_REQ:
                Intent intent = new Intent();
                intent.setClass(ContextUtil.getCurrentActivity(), BtSeaLoginActivity.class);
                ContextUtil.getCurrentActivity().startActivity(intent);
                return;
            case LOGOUT_REQ:
                doLogout(false);
                return;
            default:
                return;
        }
    }

    public void doLogout(boolean ignoreLogoutRes) {
        this.mIgnoreLogoutRes = ignoreLogoutRes;
        String loginResultJson = SharedPreferencesUtils.getString(LOGIN_SP_RESULT);
        if (!TextUtils.isEmpty(loginResultJson)) {
            LoginResult loginResult = (LoginResult) OkHttpUtil.getInstance(ContextUtil.getApplicationContext()).getGson().fromJson(loginResultJson, LoginResult.class);
            cleanAccountInfo(true);
            if (loginResult.getPlatform().equals(TaskConfig.LOGIN_GUEST_FLAG) || loginResult.getPlatform().equals(TaskConfig.LOGIN_BT_FLAG)) {
                sendLogoutSuccessEvent();
            } else {
                logoutThirdAccount(ignoreLogoutRes, loginResult.getPlatform());
            }
        } else {
            sendLogoutSuccessEvent();
        }
    }

    private void logoutThirdAccount(boolean ignoreLogoutRes, String platform) {
        this.mIgnoreLogoutRes = ignoreLogoutRes;
        SdkEventManager.postEvent(new ThirdAccountEvent.Builder(EventType.LOGOUT_REQ).activity(ContextUtil.getCurrentActivity()).platform(platform).build());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onThirdLoginResultEvent(ThirdAccountResultEvent thirdAccountResultEvent) {
        switch (thirdAccountResultEvent.getStatusCode()) {
            case LOGIN_FAIL:
                hideDialog();
                BtToast.showShortTimeToast(ContextUtil.getApplicationContext(), BTResourceUtil.findStringByName("tips_thirdlogin_fail"));
                return;
            case LOGIN_CANCEL:
                hideDialog();
                return;
            case LOGOUT_SUCCESS:
                hideDialog();
                sendLogoutSuccessEvent();
                return;
            default:
                return;
        }
    }

    private void sendLogoutSuccessEvent() {
        if (!this.mIgnoreLogoutRes) {
            SdkEventManager.postEvent(new Builder(EventType.LOGOUT_RES).statusCode(StatusCode.LOGOUT_SUCCESS).build());
        }
    }

    public void postData(Object data) {
        showLoadingDialog();
        SdkEventManager.postEvent(data);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLoginResultEvent(LoginResult loginResult) {
        hideDialog();
        int code = loginResult.getRes().getCode();
        if (StatusCode.LOGIN_SUCCESS.getCode() == code) {
            saveLoginInfo(loginResult);
            if (!OpType.OP_LOGIN_GUEST.equals(loginResult.getOpType()) || !loginResult.isNeedTip()) {
                postSdkLoginResult(loginResult);
            } else {
                toNoticeBindPage(loginResult);
            }
            if ("GP".equals(loginResult.getPlatform())) {
                SdkEventManager.postEvent(new TrackEvent(TrackEventType.GP_LOGIN_SUCCESS));
            } else if ("FB".equals(loginResult.getPlatform())) {
                SdkEventManager.postEvent(new TrackEvent(TrackEventType.FB_LOGIN_SUCCESS));
            }
        } else {
            reqSaveAccountInfo(null, null);
            if (OpType.OP_LOGIN_THIRD.equals(loginResult.getOpType())) {
                logoutThirdAccount(true, loginResult.getPlatform());
            }
            if (-1 == code) {
                showToast(BTResourceUtil.findStringByName("tips_noRespond"));
            } else {
                showToast(loginResult.getRes().getMsg());
            }
            if (findFragmentByTag(UIidAndtag.TAG_PAGE_AUTOLOGIN) != null) {
                popBackToPage(UIidAndtag.TAG_PAGE_LOGIN_HOME);
            }
        }
    }

    private void toNoticeBindPage(LoginResult loginResult) {
        Bundle bundle = new Bundle();
        bundle.putString(KEY_BUNDLE_BINDINFO, loginResult.getIdentity());
        toUiPage(UIidAndtag.TAG_PAGE_GUESTNOTICE, bundle);
    }

    public void postSdkLoginResult(LoginResult loginResult) {
        if (this.btSeaLoginActivity != null) {
            if (loginResult == null) {
                loginResult = (LoginResult) OkHttpUtil.getInstance(ContextUtil.getApplicationContext()).getGson().fromJson(SharedPreferencesUtils.getString(LOGIN_SP_RESULT), LoginResult.class);
            }
            this.mLoginResultEvent = new Builder(EventType.LOGIN_RES).platform(loginResult.getPlatform()).account(loginResult.getAccount()).userId(loginResult.getUserId()).token(loginResult.getToken()).statusCode(StatusCode.LOGIN_SUCCESS).firstJoinTime(loginResult.getFirstJoinDate()).firstJoin(loginResult.isFirstJoin()).build();
            this.btSeaLoginActivity.finish();
        }
    }

    private void hideDialog() {
        LoadingDialog.hiddenDialog();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onOpResultEvent(OpResult opResult) {
        hideDialog();
        if (this.btSeaLoginActivity != null) {
            int code = opResult.getRes().getCode();
            if (-1 == code) {
                showToast(BTResourceUtil.findStringByName("tips_noRespond"));
            } else if (OpType.OP_CHANGEPWD.equals(opResult.getRes().getOpType())) {
                if (code == 0) {
                    cleanAccountInfo(false);
                    showToast(BTResourceUtil.findStringByName("changepwd_tips_changed"));
                    popBackToPage(UIidAndtag.TAG_PAGE_LOGIN_BT);
                    return;
                }
                showToast(opResult.getRes().getMsg());
            } else if (OpType.OP_RETRIEVEPWD.equals(opResult.getRes().getOpType())) {
                if (code == 0) {
                    cleanAccountInfo(false);
                    showToast(BTResourceUtil.findStringByName("retrievepwd_tips_tomail"));
                    popBackToPage(UIidAndtag.TAG_PAGE_LOGIN_BT);
                    return;
                }
                showToast(opResult.getRes().getMsg());
            } else if (OpType.OP_GETMAILCODE.equals(opResult.getRes().getOpType())) {
                if (code == 0) {
                    ((BindMailFragment) findFragmentByTag(UIidAndtag.TAG_PAGE_BINDMAIL)).resetGetMailCodeTime();
                    showToast(BTResourceUtil.findStringByName("bindmail_tips_tomail"));
                    return;
                }
                showToast(opResult.getRes().getMsg());
            } else if (!OpType.OP_BINDMAIL.equals(opResult.getRes().getOpType())) {
            } else {
                if (code == 0) {
                    showToast(BTResourceUtil.findStringByName("bindmail_tips_bindsuc"));
                    popBackToPage(UIidAndtag.TAG_PAGE_LOGIN_BT);
                    return;
                }
                showToast(opResult.getRes().getMsg());
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onReqInitResultEvent(ReqInitResult reqInitResult) {
        hideDialog();
        if (reqInitResult.getRes().getCode() != 0 || reqInitResult.getLoginList() == null) {
            UIConfig.setLoginConfig(null);
            SdkEventManager.postEvent(new ReqInitResultEvent.Builder().statusCode(StatusCode.INIT_FAIL).build());
            return;
        }
        UIConfig.setLoginConfig(reqInitResult.getLoginList());
        SdkEventManager.postEvent(new ReqInitResultEvent.Builder().statusCode(StatusCode.INIT_SUCCESS).build());
    }

    private BaseFragment findFragmentByTag(String tag) {
        if (this.btSeaLoginActivity == null) {
            return null;
        }
        return (BaseFragment) this.btSeaLoginActivity.getSupportFragmentManager().findFragmentByTag(tag);
    }

    public void popBackToPage(String pageTag) {
        if (this.btSeaLoginActivity != null) {
            if (UIidAndtag.TAG_PAGE_LOGIN_HOME.equals(pageTag)) {
                this.btSeaLoginActivity.getSupportFragmentManager().popBackStackImmediate((String) null, 1);
            } else {
                this.btSeaLoginActivity.getSupportFragmentManager().popBackStackImmediate(pageTag, 0);
            }
        }
    }

    public void startFragment(String tag, Bundle bundle, boolean addToBackStack, boolean withAnim) {
        if (this.btSeaLoginActivity != null) {
            this.btSeaLoginActivity.addFragment(tag, bundle, addToBackStack, withAnim);
        }
    }
}
