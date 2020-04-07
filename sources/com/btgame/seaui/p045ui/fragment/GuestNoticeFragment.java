package com.btgame.seaui.p045ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.btgame.seaui.BtSeaUiManager;
import com.btgame.seaui.p045ui.constant.UIidAndtag;
import com.btgame.seaui.p045ui.view.GuestNoticeView;

/* renamed from: com.btgame.seaui.ui.fragment.GuestNoticeFragment */
public class GuestNoticeFragment extends BaseFragment {
    private Bundle bundle;
    private GuestNoticeView noticeView;

    /* access modifiers changed from: protected */
    public View initViews(Context context) {
        this.noticeView = new GuestNoticeView(context, this);
        return this.noticeView;
    }

    /* access modifiers changed from: protected */
    public void initDatas() {
        this.bundle = getArguments();
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
        if ((view.getTag() + "").equals(UIidAndtag.BTN_NOTICE)) {
            BtSeaUiManager.getInstance().popBackToPage(UIidAndtag.TAG_PAGE_LOGIN_HOME);
            BtSeaUiManager.getInstance().toUiPage(UIidAndtag.TAG_PAGE_REGISTER, this.bundle);
        }
    }

    public boolean onBackPressed() {
        BtSeaUiManager.getInstance().postSdkLoginResult(null);
        return true;
    }
}
