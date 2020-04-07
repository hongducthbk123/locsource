package com.btgame.seaui.p045ui.fragment;

import android.content.Context;
import android.os.CountDownTimer;
import android.text.Html;
import android.view.View;
import com.btgame.seasdk.btcore.common.util.BTResourceUtil;
import com.btgame.seaui.BtSeaUiManager;
import com.btgame.seaui.p045ui.constant.UIidAndtag;
import com.btgame.seaui.p045ui.view.AutoLoginView;

/* renamed from: com.btgame.seaui.ui.fragment.AutoLoginFragment */
public class AutoLoginFragment extends BaseFragment {
    private AutoLoginView autoLoginView;
    /* access modifiers changed from: private */
    public CountDownTimer mTimer;

    /* access modifiers changed from: protected */
    public View initViews(Context context) {
        this.autoLoginView = new AutoLoginView(context, this);
        return this.autoLoginView;
    }

    public void setAccountName(String accountName) {
        this.autoLoginView.getWelcomeTipsTv().setText(Html.fromHtml(String.format(BTResourceUtil.findStringByName("autologin_tv_welcome"), new Object[]{accountName})));
    }

    public void setCountDown(int time) {
        this.autoLoginView.getProgressTipsTv().setText(Html.fromHtml(String.format(BTResourceUtil.findStringByName("autologin_tv_progress"), new Object[]{Integer.valueOf(time)})));
    }

    /* access modifiers changed from: protected */
    public void initDatas() {
        setAccountName(getArguments().getString(BtSeaUiManager.KEY_BUNDLE_AUTOLOGIN_NAME));
        setCountDown(3);
        this.mTimer = new CountDownTimer(3000, 300) {
            public void onTick(long l) {
                AutoLoginFragment.this.setCountDown((int) Math.ceil(((double) l) / 1000.0d));
            }

            public void onFinish() {
                AutoLoginFragment.this.setCountDown(0);
                AutoLoginFragment.this.mTimer = null;
                BtSeaUiManager.getInstance().doAutoLogin();
            }
        };
        this.mTimer.start();
    }

    public void onPause() {
        super.onPause();
        if (this.mTimer != null) {
            this.mTimer.cancel();
            this.mTimer = null;
            getActivity().getSupportFragmentManager().popBackStack();
        }
    }

    /* access modifiers changed from: protected */
    public boolean validateFormData(String[] formData) {
        return true;
    }

    /* access modifiers changed from: protected */
    public void onShow() {
        super.onShow();
    }

    public void onClick(View view) {
        super.onClick(view);
        if ((view.getTag() + "").equals(UIidAndtag.BTN_CHANGEACCOUNT)) {
            if (this.mTimer != null) {
                this.mTimer.cancel();
                this.mTimer = null;
            }
            BtSeaUiManager.getInstance().doLogout(true);
            BtSeaUiManager.getInstance().popBackToPage(UIidAndtag.TAG_PAGE_LOGIN_HOME);
        }
    }
}
