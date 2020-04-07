package com.btgame.seaui.p045ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import com.btgame.seasdk.btcore.common.constant.TrackEventType;
import com.btgame.seasdk.btcore.common.event.SdkEventManager;
import com.btgame.seasdk.btcore.common.event.data.TrackEvent;
import com.btgame.seasdk.btcore.common.util.BTResourceUtil;
import com.btgame.seasdk.btcore.common.util.BtsdkLog;
import com.btgame.seasdk.btcore.common.util.MD5Util;
import com.btgame.seasdk.btcore.widget.BtToast;
import com.btgame.seasdk.task.entity.request.RegisterData.Builder;
import com.btgame.seaui.BtSeaUiManager;
import com.btgame.seaui.p045ui.constant.UIidAndtag;
import com.btgame.seaui.p045ui.view.RegisterView;
import com.btgame.seaui.p045ui.view.widget.HtmlTextView.OnHtmlClickListener;

/* renamed from: com.btgame.seaui.ui.fragment.RegisterFragment */
public class RegisterFragment extends BaseFragment {
    private Bundle bundle;
    private RegisterView registerView;

    /* access modifiers changed from: protected */
    public View initViews(Context context) {
        this.registerView = new RegisterView(context, this);
        return this.registerView;
    }

    /* access modifiers changed from: protected */
    public void initDatas() {
        this.bundle = getArguments();
        this.registerView.getProtocolTv().setHtml(BTResourceUtil.findStringByName("register_tv_protocol"), new OnHtmlClickListener() {
            public void onClick(String flag, String text) {
                BtSeaUiManager.getInstance().toUiPage(UIidAndtag.TAG_PAGE_PROTOCOL, null);
            }
        });
    }

    /* access modifiers changed from: protected */
    public boolean validateFormData(String[] formData) {
        if (TextUtils.isEmpty(formData[0])) {
            BtToast.showShortTimeToast(getContext(), BTResourceUtil.findStringByName("register_tips_account_null"));
            return false;
        } else if (!formData[0].matches(BTResourceUtil.findStringByName("reg_account"))) {
            BtToast.showShortTimeToast(getContext(), BTResourceUtil.findStringByName("register_tips_account_reg"));
            return false;
        } else if (TextUtils.isEmpty(formData[1])) {
            BtToast.showShortTimeToast(getContext(), BTResourceUtil.findStringByName("register_tips_pwd_null"));
            return false;
        } else if (!formData[1].matches(BTResourceUtil.findStringByName("reg_pwd"))) {
            BtToast.showShortTimeToast(getContext(), BTResourceUtil.findStringByName("register_tips_pwd_reg"));
            return false;
        } else if (TextUtils.isEmpty(formData[2])) {
            BtToast.showShortTimeToast(getContext(), BTResourceUtil.findStringByName("register_tips_pwdcf_null"));
            return false;
        } else if (TextUtils.equals(formData[1], formData[2])) {
            return true;
        } else {
            BtToast.showShortTimeToast(getContext(), BTResourceUtil.findStringByName("register_tips_pwd_pwdcf"));
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public void onShow() {
        super.onShow();
        SdkEventManager.postEvent(new TrackEvent(TrackEventType.SIGN_UP));
    }

    public void onClick(View view) {
        super.onClick(view);
        if ((view.getTag() + "").equals(UIidAndtag.BTN_REGISTER)) {
            String[] formData = this.registerView.getFormData();
            if (validateFormData(formData)) {
                Builder dataBuilder = new Builder().account(formData[0]).pwd(MD5Util.md5Hex(formData[1]));
                if (this.bundle != null && !TextUtils.isEmpty(this.bundle.getString(BtSeaUiManager.KEY_BUNDLE_BINDINFO))) {
                    String identity = this.bundle.getString(BtSeaUiManager.KEY_BUNDLE_BINDINFO);
                    BtsdkLog.m1429d("绑定注册，游客identity：" + identity);
                    dataBuilder.identity(identity);
                }
                BtSeaUiManager.getInstance().postData(dataBuilder.build());
                BtSeaUiManager.getInstance().reqSaveAccountInfo(formData[0], formData[1]);
            }
        }
    }
}
