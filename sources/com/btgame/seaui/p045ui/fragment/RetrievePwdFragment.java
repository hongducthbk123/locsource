package com.btgame.seaui.p045ui.fragment;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import com.btgame.seasdk.btcore.common.util.BTResourceUtil;
import com.btgame.seasdk.btcore.widget.BtToast;
import com.btgame.seasdk.task.entity.request.RetrievePwdData.Builder;
import com.btgame.seaui.BtSeaUiManager;
import com.btgame.seaui.p045ui.constant.UIidAndtag;
import com.btgame.seaui.p045ui.view.RetrievePwdView;

/* renamed from: com.btgame.seaui.ui.fragment.RetrievePwdFragment */
public class RetrievePwdFragment extends BaseFragment {
    private RetrievePwdView retrievePwdView;

    /* access modifiers changed from: protected */
    public View initViews(Context context) {
        this.retrievePwdView = new RetrievePwdView(context, this);
        return this.retrievePwdView;
    }

    /* access modifiers changed from: protected */
    public void initDatas() {
        if (this.retrievePwdView != null) {
            this.retrievePwdView.getAccountEt().setText(BtSeaUiManager.getInstance().getAccountInfo()[0]);
        }
    }

    /* access modifiers changed from: protected */
    public boolean validateFormData(String[] formData) {
        if (TextUtils.isEmpty(formData[0]) || TextUtils.isEmpty(formData[1])) {
            BtToast.showShortTimeToast(getContext(), BTResourceUtil.findStringByName("retrievepwd_tips_null"));
            return false;
        } else if (formData[1].matches(BTResourceUtil.findStringByName("reg_mail"))) {
            return true;
        } else {
            BtToast.showShortTimeToast(getContext(), BTResourceUtil.findStringByName("retrievepwd_tips_mail"));
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public void onShow() {
        super.onShow();
    }

    public void onClick(View view) {
        super.onClick(view);
        if ((view.getTag() + "").equals(UIidAndtag.BTN_RETRIEVEPWD)) {
            String[] formData = this.retrievePwdView.getFormData();
            if (validateFormData(formData)) {
                BtSeaUiManager.getInstance().postData(new Builder().account(formData[0]).email(formData[1]).build());
            }
        }
    }
}
