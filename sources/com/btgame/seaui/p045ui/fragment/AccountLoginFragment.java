package com.btgame.seaui.p045ui.fragment;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import com.btgame.seasdk.btcore.common.constant.TrackEventType;
import com.btgame.seasdk.btcore.common.event.SdkEventManager;
import com.btgame.seasdk.btcore.common.event.data.TrackEvent;
import com.btgame.seasdk.btcore.common.util.BTResourceUtil;
import com.btgame.seasdk.btcore.common.util.MD5Util;
import com.btgame.seasdk.btcore.widget.BtToast;
import com.btgame.seasdk.task.entity.request.AccountLoginData.Builder;
import com.btgame.seaui.BtSeaUiManager;
import com.btgame.seaui.p045ui.constant.UIidAndtag;
import com.btgame.seaui.p045ui.view.AccountLoginView;

/* renamed from: com.btgame.seaui.ui.fragment.AccountLoginFragment */
public class AccountLoginFragment extends BaseFragment {
    /* access modifiers changed from: private */
    public AccountLoginView accountLoginView;
    private String md5PwdRec;

    /* access modifiers changed from: protected */
    public View initViews(Context context) {
        this.accountLoginView = new AccountLoginView(context, this);
        return this.accountLoginView;
    }

    /* access modifiers changed from: protected */
    public void initDatas() {
        if (this.accountLoginView != null) {
            String[] info = BtSeaUiManager.getInstance().getAccountInfo();
            this.md5PwdRec = info[1];
            this.accountLoginView.getAccountEt().setText(info[0]);
            this.accountLoginView.getPwdEt().setText(this.md5PwdRec);
            this.accountLoginView.getPwdEt().setOnFocusChangeListener(new OnFocusChangeListener() {
                public void onFocusChange(View view, boolean b) {
                    if (b) {
                        AccountLoginFragment.this.accountLoginView.getPwdEt().setText("");
                    }
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public boolean validateFormData(String[] formData) {
        if (TextUtils.isEmpty(formData[0])) {
            BtToast.showShortTimeToast(getContext(), BTResourceUtil.findStringByName("btlogin_et_account_null"));
            return false;
        } else if (!TextUtils.isEmpty(formData[1])) {
            return true;
        } else {
            BtToast.showShortTimeToast(getContext(), BTResourceUtil.findStringByName("btlogin_et_pwd_null"));
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public void onShow() {
        super.onShow();
        initDatas();
        SdkEventManager.postEvent(new TrackEvent(TrackEventType.UB_SIGN_IN));
    }

    public void onClick(View view) {
        String md5Pwd;
        super.onClick(view);
        if ((view.getTag() + "").equals(UIidAndtag.BTN_BTLOGIN)) {
            String[] formData = this.accountLoginView.getFormData();
            if (validateFormData(formData)) {
                if (!formData[1].equals(this.md5PwdRec)) {
                    md5Pwd = MD5Util.md5Hex(formData[1]);
                    BtSeaUiManager.getInstance().reqSaveAccountInfo(formData[0], formData[1]);
                } else {
                    md5Pwd = this.md5PwdRec;
                }
                BtSeaUiManager.getInstance().postData(new Builder().account(formData[0]).pwd(md5Pwd).build());
            }
        }
    }
}
