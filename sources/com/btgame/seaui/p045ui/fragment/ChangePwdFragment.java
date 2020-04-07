package com.btgame.seaui.p045ui.fragment;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import com.btgame.seasdk.btcore.common.util.BTResourceUtil;
import com.btgame.seasdk.btcore.common.util.MD5Util;
import com.btgame.seasdk.btcore.widget.BtToast;
import com.btgame.seasdk.task.entity.request.ChangePwdData.Builder;
import com.btgame.seaui.BtSeaUiManager;
import com.btgame.seaui.p045ui.constant.UIidAndtag;
import com.btgame.seaui.p045ui.view.ChangePwdView;

/* renamed from: com.btgame.seaui.ui.fragment.ChangePwdFragment */
public class ChangePwdFragment extends BaseFragment {
    private ChangePwdView changePwdView;

    /* access modifiers changed from: protected */
    public View initViews(Context context) {
        this.changePwdView = new ChangePwdView(context, this);
        return this.changePwdView;
    }

    /* access modifiers changed from: protected */
    public void initDatas() {
        if (this.changePwdView != null) {
            this.changePwdView.getAccountEt().setText(BtSeaUiManager.getInstance().getAccountInfo()[0]);
        }
    }

    /* access modifiers changed from: protected */
    public boolean validateFormData(String[] formData) {
        if (TextUtils.isEmpty(formData[0]) || TextUtils.isEmpty(formData[1]) || TextUtils.isEmpty(formData[2]) || TextUtils.isEmpty(formData[3])) {
            BtToast.showShortTimeToast(getContext(), BTResourceUtil.findStringByName("changepwd_tips_null"));
            return false;
        } else if (!formData[2].matches(BTResourceUtil.findStringByName("reg_pwd"))) {
            BtToast.showShortTimeToast(getContext(), BTResourceUtil.findStringByName("changepwd_tips_newpwd_reg"));
            return false;
        } else if (TextUtils.equals(formData[1], formData[2])) {
            BtToast.showShortTimeToast(getContext(), BTResourceUtil.findStringByName("changepwd_tips_pwd_newpwd"));
            return false;
        } else if (TextUtils.equals(formData[2], formData[3])) {
            return true;
        } else {
            BtToast.showShortTimeToast(getContext(), BTResourceUtil.findStringByName("changepwd_tips_newpwd_newpwdcf"));
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public void onShow() {
        super.onShow();
    }

    public void onClick(View view) {
        super.onClick(view);
        if ((view.getTag() + "").equals(UIidAndtag.BTN_CHANGEPWD)) {
            String[] formData = this.changePwdView.getFormData();
            if (validateFormData(formData)) {
                BtSeaUiManager.getInstance().postData(new Builder().account(formData[0]).oPw(MD5Util.md5Hex(formData[1])).nPw(MD5Util.md5Hex(formData[2])).build());
            }
        }
    }
}
