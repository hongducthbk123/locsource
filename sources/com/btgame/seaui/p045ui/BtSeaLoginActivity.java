package com.btgame.seaui.p045ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.FragmentActivity;
import android.support.p000v4.app.FragmentManager;
import android.support.p000v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import com.btgame.seasdk.btcore.common.constant.LifecycleEventType;
import com.btgame.seasdk.btcore.common.event.LifecycleEvent.Builder;
import com.btgame.seasdk.btcore.common.event.SdkEventManager;
import com.btgame.seasdk.btcore.common.util.BTResourceUtil;
import com.btgame.seaui.p045ui.constant.UIidAndtag;
import com.btgame.seaui.p045ui.fragment.AccountLoginFragment;
import com.btgame.seaui.p045ui.fragment.AccountManagerFragment;
import com.btgame.seaui.p045ui.fragment.AutoLoginFragment;
import com.btgame.seaui.p045ui.fragment.BaseFragment;
import com.btgame.seaui.p045ui.fragment.BaseFragment.OnHiddenChangedListener;
import com.btgame.seaui.p045ui.fragment.BindMailFragment;
import com.btgame.seaui.p045ui.fragment.ChangePwdFragment;
import com.btgame.seaui.p045ui.fragment.GuestNoticeFragment;
import com.btgame.seaui.p045ui.fragment.LoginHomeFragment;
import com.btgame.seaui.p045ui.fragment.ProtocolFragment;
import com.btgame.seaui.p045ui.fragment.RegisterFragment;
import com.btgame.seaui.p045ui.fragment.RetrievePwdFragment;
import com.btgame.seaui.p045ui.view.ContainerView;

/* renamed from: com.btgame.seaui.ui.BtSeaLoginActivity */
public class BtSeaLoginActivity extends FragmentActivity implements OnClickListener, OnHiddenChangedListener {
    private ContainerView containerView;
    private BaseFragment curFragment;
    private FragmentManager manager;
    private FrameLayout rootFrameLayout;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addRootFrameLayout();
        this.manager = getSupportFragmentManager();
        if (this.manager.findFragmentByTag(UIidAndtag.TAG_PAGE_LOGIN_HOME) == null) {
            FragmentTransaction ft = this.manager.beginTransaction();
            LoginHomeFragment loginFragment = new LoginHomeFragment();
            this.curFragment = loginFragment;
            ft.add(UIidAndtag.ID_ROOTFL, loginFragment, UIidAndtag.TAG_PAGE_LOGIN_HOME);
            ft.show(loginFragment);
            loginFragment.onDisplayStatusChange(false);
            ft.commit();
        }
        SdkEventManager.postEvent(new Builder().lifecycleEventType(LifecycleEventType.onSdkActivityCreate).activity(this).savedInstanceState(savedInstanceState).build());
    }

    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        SdkEventManager.postEvent(new Builder().lifecycleEventType(LifecycleEventType.onSdkWindowFocusChanged).activity(this).hasFocus(hasFocus).build());
    }

    private void addRootFrameLayout() {
        this.containerView = new ContainerView(this, this);
        this.rootFrameLayout = this.containerView.getRootFrameLayout();
        setContentView(this.containerView);
    }

    public void addFragment(String tag, Bundle bundle, boolean addToBackStack, boolean withAnim) {
        if (this.curFragment == null || !TextUtils.equals(tag, this.curFragment.getTag())) {
            BaseFragment fragment = (BaseFragment) this.manager.findFragmentByTag(tag);
            FragmentTransaction ft = this.manager.beginTransaction();
            if (withAnim) {
                ft.setCustomAnimations(BTResourceUtil.findAnimIdByName("fragment_slide_left_in"), BTResourceUtil.findAnimIdByName("fragment_slide_left_out"), BTResourceUtil.findAnimIdByName("fragment_slide_right_in"), BTResourceUtil.findAnimIdByName("fragment_slide_right_out"));
            }
            if (addToBackStack) {
                ft.addToBackStack(tag);
            }
            if (fragment == null) {
                if (TextUtils.equals(tag, UIidAndtag.TAG_PAGE_LOGIN_HOME)) {
                    fragment = new LoginHomeFragment();
                } else if (TextUtils.equals(tag, UIidAndtag.TAG_PAGE_LOGIN_BT)) {
                    fragment = new AccountLoginFragment();
                } else if (TextUtils.equals(tag, UIidAndtag.TAG_PAGE_REGISTER)) {
                    fragment = new RegisterFragment();
                } else if (TextUtils.equals(tag, UIidAndtag.TAG_PAGE_ACCOUNTMANAGER)) {
                    fragment = new AccountManagerFragment();
                } else if (TextUtils.equals(tag, UIidAndtag.TAG_PAGE_BINDMAIL)) {
                    fragment = new BindMailFragment();
                } else if (TextUtils.equals(tag, UIidAndtag.TAG_PAGE_CHANGEPWD)) {
                    fragment = new ChangePwdFragment();
                } else if (TextUtils.equals(tag, UIidAndtag.TAG_PAGE_RETRIEVEPWD)) {
                    fragment = new RetrievePwdFragment();
                } else if (TextUtils.equals(tag, UIidAndtag.TAG_PAGE_AUTOLOGIN)) {
                    fragment = new AutoLoginFragment();
                } else if (TextUtils.equals(tag, UIidAndtag.TAG_PAGE_GUESTNOTICE)) {
                    fragment = new GuestNoticeFragment();
                } else if (TextUtils.equals(tag, UIidAndtag.TAG_PAGE_PROTOCOL)) {
                    fragment = new ProtocolFragment();
                }
                fragment.setArguments(bundle);
                ft.add(UIidAndtag.ID_ROOTFL, fragment, tag);
            }
            if (this.curFragment != null) {
                ft.hide(this.curFragment);
            }
            this.curFragment = fragment;
            ft.show(fragment);
            fragment.onDisplayStatusChange(false);
            ft.commitAllowingStateLoss();
        }
    }

    public void onClick(View view) {
        if (view == this.containerView.getBtnBack()) {
            onBackPressed();
        }
    }

    public void onHiddenChanged(BaseFragment fragment, boolean hidden) {
        if (!hidden) {
            this.curFragment = fragment;
        } else {
            this.containerView.getBtnBack().setVisibility(this.manager.getBackStackEntryCount() > 0 ? 0 : 8);
        }
    }

    public void onBackPressed() {
        if (this.curFragment == null || !this.curFragment.onBackPressed()) {
            try {
                if (!getSupportFragmentManager().popBackStackImmediate()) {
                    super.onBackPressed();
                    SdkEventManager.postEvent(new Builder().lifecycleEventType(LifecycleEventType.onSdkActivityBackPressed).activity(this).build());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (getCurrentFocus() != null) {
            return ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
        return super.onTouchEvent(event);
    }

    public void onStart() {
        super.onStart();
        SdkEventManager.postEvent(new Builder().lifecycleEventType(LifecycleEventType.onSdkActivityStart).activity(this).build());
    }

    public void onRestart() {
        super.onRestart();
        SdkEventManager.postEvent(new Builder().lifecycleEventType(LifecycleEventType.onSdkActivityRestart).activity(this).build());
    }

    public void onResume() {
        super.onResume();
        SdkEventManager.postEvent(new Builder().lifecycleEventType(LifecycleEventType.onSdkActivityResume).activity(this).build());
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        SdkEventManager.postEvent(new Builder().lifecycleEventType(LifecycleEventType.onSdkActivityResult).activity(this).requestCode(requestCode).resultCode(resultCode).data(data).build());
    }

    public void onPause() {
        super.onPause();
        SdkEventManager.postEvent(new Builder().lifecycleEventType(LifecycleEventType.onSdkActivityPause).activity(this).build());
    }

    public void onStop() {
        super.onStop();
        SdkEventManager.postEvent(new Builder().lifecycleEventType(LifecycleEventType.onSdkActivityStop).activity(this).build());
    }

    public void onDestroy() {
        super.onDestroy();
        SdkEventManager.postEvent(new Builder().lifecycleEventType(LifecycleEventType.onSdkActivityDestroy).activity(this).build());
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        SdkEventManager.postEvent(new Builder().lifecycleEventType(LifecycleEventType.onRequestPermissionsResult).activity(this).requestCode(requestCode).permissions(permissions).grantResults(grantResults).build());
    }

    /* access modifiers changed from: protected */
    public void attachBaseContext(Context newBase) {
        super.attachBaseContext(BTResourceUtil.changeLocale(newBase, null));
    }
}
