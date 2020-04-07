package com.btgame.google;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.btgame.google.constant.GpConfig;
import com.btgame.seasdk.btcore.common.constant.EventType;
import com.btgame.seasdk.btcore.common.constant.StatusCode;
import com.btgame.seasdk.btcore.common.constant.TrackEventType;
import com.btgame.seasdk.btcore.common.event.LifecycleEvent;
import com.btgame.seasdk.btcore.common.event.SdkEventManager;
import com.btgame.seasdk.btcore.common.event.data.TrackEvent;
import com.btgame.seasdk.btcore.common.event.login.ThirdAccountEvent;
import com.btgame.seasdk.btcore.common.event.login.ThirdAccountResultEvent;
import com.btgame.seasdk.btcore.common.util.BTResourceUtil;
import com.btgame.seasdk.btcore.common.util.BtsdkLog;
import com.btgame.seasdk.btcore.common.util.SharedPreferencesUtils;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions.Builder;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class BtGoogleManager {
    private String bindId;
    private String gamesAppId;
    private volatile GoogleSignInAccount googleSignInAccount;
    private volatile GoogleSignInClient googleSignInClient;
    private String webClientId;

    private static class BtGoogleManagerHolder {
        /* access modifiers changed from: private */
        public static final BtGoogleManager btGoogleManager = new BtGoogleManager();

        private BtGoogleManagerHolder() {
        }
    }

    private BtGoogleManager() {
        this.gamesAppId = BTResourceUtil.getApplicationMetaData("com.google.android.gms.games.APP_ID");
        this.webClientId = BTResourceUtil.findStringByName("default_web_client_id");
        SdkEventManager.register(this);
        BtPlayGamesManager.getInstance();
    }

    public static BtGoogleManager getInstance() {
        return BtGoogleManagerHolder.btGoogleManager;
    }

    public GoogleSignInAccount getGamesSignInAccount() {
        if (TextUtils.isEmpty(this.gamesAppId)) {
            return null;
        }
        return this.googleSignInAccount;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLifecycleEvent(LifecycleEvent lifecycleEvent) {
        switch (lifecycleEvent.getLifecycleEventType()) {
            case onSdkActivityResult:
                onActivityResult(lifecycleEvent.getActivity(), lifecycleEvent.getRequestCode(), lifecycleEvent.getResultCode(), lifecycleEvent.getData());
                return;
            default:
                return;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onThirdAccountEvent(ThirdAccountEvent loginEvent) {
        if (loginEvent.getPlatform().equalsIgnoreCase("GP")) {
            switch (loginEvent.getEventType()) {
                case LOGIN_REQ:
                    login(loginEvent.getActivity(), loginEvent.getBindId());
                    return;
                case LOGOUT_REQ:
                    logout(loginEvent.getActivity());
                    return;
                default:
                    return;
            }
        }
    }

    /* access modifiers changed from: private */
    public GoogleSignInClient getGoogleSignInClient(Activity activity) {
        GoogleSignInOptions options;
        if (this.googleSignInClient == null) {
            if (TextUtils.isEmpty(this.gamesAppId)) {
                options = GoogleSignInOptions.DEFAULT_SIGN_IN;
            } else {
                options = GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN;
            }
            this.googleSignInClient = GoogleSignIn.getClient(activity, new Builder(options).requestEmail().requestId().requestIdToken(this.webClientId).build());
        }
        return this.googleSignInClient;
    }

    private void login(final Activity activity, String bindId2) {
        this.bindId = bindId2;
        SdkEventManager.postEvent(new TrackEvent(TrackEventType.GP_REQUEST_AUTHORIZATION));
        getGoogleSignInClient(activity).silentSignIn().addOnCompleteListener(new OnCompleteListener<GoogleSignInAccount>() {
            public void onComplete(@NonNull Task<GoogleSignInAccount> task) {
                if (task.isSuccessful()) {
                    BtGoogleManager.this.handleSignIn((GoogleSignInAccount) task.getResult(), null);
                    return;
                }
                activity.startActivityForResult(BtGoogleManager.this.getGoogleSignInClient(activity).getSignInIntent(), GpConfig.RC_SIGN_IN);
            }
        });
    }

    private void onActivityResult(Activity activity, int requestCode, int resultCode, Intent data) {
        if (requestCode == 9001) {
            try {
                handleSignIn((GoogleSignInAccount) GoogleSignIn.getSignedInAccountFromIntent(data).getResult(ApiException.class), null);
            } catch (ApiException e) {
                BtsdkLog.m1430e("signInResult:failed code=" + e.getStatusCode());
                handleSignIn(null, e);
            }
        }
    }

    /* access modifiers changed from: private */
    public void handleSignIn(GoogleSignInAccount account, ApiException e) {
        int statusCode;
        String message;
        String str;
        this.googleSignInAccount = account;
        BtPlayGamesManager.getInstance().setGamesSignInAccount(getGamesSignInAccount());
        if (account == null) {
            BtsdkLog.m1429d("Gp登录失败");
            TrackEvent.Builder eventType = new TrackEvent.Builder().eventType(TrackEventType.GP_AUTHORIZE_FAILED);
            if (e == null) {
                statusCode = -1;
            } else {
                statusCode = e.getStatusCode();
            }
            TrackEvent.Builder code = eventType.code(Integer.valueOf(statusCode));
            if (e == null) {
                message = "";
            } else {
                message = e.getMessage();
            }
            SdkEventManager.postEvent(code.description(message).build());
            ThirdAccountResultEvent.Builder statusCode2 = new ThirdAccountResultEvent.Builder(EventType.LOGIN_RES).statusCode(StatusCode.LOGIN_FAIL);
            if (e == null) {
                str = "";
            } else {
                str = "code=" + e.getStatusCode();
            }
            SdkEventManager.postEvent(statusCode2.des(str).platform("GP").bindId(this.bindId).build());
        } else {
            SharedPreferencesUtils.setBoolean(GpConfig.FLAG_LOGIN, true);
            BtsdkLog.m1429d("Gp登录成功,UserId:" + account.getId() + " accessToken:" + account.getIdToken());
            SdkEventManager.postEvent(new TrackEvent(TrackEventType.GP_AUTHORIZE_SUCCESS));
            SdkEventManager.postEvent(new ThirdAccountResultEvent.Builder(EventType.LOGIN_RES).statusCode(StatusCode.LOGIN_SUCCESS).thirdId(GpConfig.GPTHIRDID).platform("GP").platformUid(account.getId()).platformToken(account.getIdToken()).bindId(this.bindId).build());
        }
        this.bindId = null;
    }

    private void logout(Activity activity) {
        BtsdkLog.m1429d("执行GP登出");
        SharedPreferencesUtils.setBoolean(GpConfig.FLAG_LOGIN, false);
        getGoogleSignInClient(activity).revokeAccess().addOnCompleteListener(activity, (OnCompleteListener<TResult>) new OnCompleteListener<Void>() {
            public void onComplete(@NonNull Task<Void> task) {
            }
        });
        SdkEventManager.postEvent(new ThirdAccountResultEvent.Builder(EventType.LOGOUT_RES).statusCode(StatusCode.LOGOUT_SUCCESS).platform("GP").build());
        clean();
    }

    private void clean() {
        BtPlayGamesManager.getInstance().clean();
        this.googleSignInClient = null;
        this.googleSignInAccount = null;
        this.bindId = null;
    }
}
