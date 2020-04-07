package com.btgame.seaui.p045ui.fragment;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import com.btgame.seasdk.btcore.common.util.BTResourceUtil;
import com.btgame.seasdk.btcore.common.util.MD5Util;
import com.btgame.seasdk.btcore.widget.BtToast;
import com.btgame.seasdk.task.entity.request.BindMailData;
import com.btgame.seasdk.task.entity.request.GetMailCodeData.Builder;
import com.btgame.seaui.BtSeaUiManager;
import com.btgame.seaui.p045ui.constant.UIidAndtag;
import com.btgame.seaui.p045ui.view.BindMailView;
import com.google.android.vending.expansion.downloader.Constants;
import java.util.Arrays;

/* renamed from: com.btgame.seaui.ui.fragment.BindMailFragment */
public class BindMailFragment extends BaseFragment {

    /* renamed from: cd */
    private static final int f938cd = 60;
    private BindMailView bindMailView;
    private long lastGetMailTime;

    /* access modifiers changed from: protected */
    public View initViews(Context context) {
        this.bindMailView = new BindMailView(context, this);
        return this.bindMailView;
    }

    /* access modifiers changed from: protected */
    public void initDatas() {
        if (this.bindMailView != null) {
            this.bindMailView.getAccountEt().setText(BtSeaUiManager.getInstance().getAccountInfo()[0]);
        }
    }

    /* access modifiers changed from: protected */
    public boolean validateFormData(String[] formData) {
        if (TextUtils.isEmpty(formData[0]) || TextUtils.isEmpty(formData[1]) || TextUtils.isEmpty(formData[2])) {
            BtToast.showShortTimeToast(getContext(), BTResourceUtil.findStringByName("bindmail_tips_null"));
            return false;
        } else if (!formData[2].matches(BTResourceUtil.findStringByName("reg_mail"))) {
            BtToast.showShortTimeToast(getContext(), BTResourceUtil.findStringByName("bindmail_tips_mail"));
            return false;
        } else if (formData.length != 4 || !TextUtils.isEmpty(formData[3])) {
            return true;
        } else {
            BtToast.showShortTimeToast(getContext(), BTResourceUtil.findStringByName("bindmail_tips_mailCode"));
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public void onShow() {
        super.onShow();
    }

    public void onClick(View view) {
        super.onClick(view);
        String tag = view.getTag() + "";
        if (tag.equals(UIidAndtag.BTN_GETMAILCODE)) {
            String[] formData = this.bindMailView.getFormData();
            String[] formData2 = (String[]) Arrays.copyOf(formData, formData.length - 1);
            if (validateFormData(formData2)) {
                long curTime = System.currentTimeMillis();
                if (curTime - this.lastGetMailTime < Constants.WATCHDOG_WAKE_TIMER) {
                    String tips = BTResourceUtil.findStringByName("bindmail_tips_cd");
                    BtToast.showShortTimeToast(getContext(), String.format(tips, new Object[]{Long.valueOf(60 - ((curTime - this.lastGetMailTime) / 1000))}));
                    return;
                }
                BtSeaUiManager.getInstance().postData(new Builder().account(formData2[0]).pwd(MD5Util.md5Hex(formData2[1])).email(formData2[2]).build());
            }
        }
        if (tag.equals(UIidAndtag.BTN_BINDMAIL)) {
            String[] formData3 = this.bindMailView.getFormData();
            if (validateFormData(formData3)) {
                BtSeaUiManager.getInstance().postData(new BindMailData.Builder().account(formData3[0]).pwd(MD5Util.md5Hex(formData3[1])).email(formData3[2]).code(formData3[3]).build());
            }
        }
    }

    public void resetGetMailCodeTime() {
        this.lastGetMailTime = System.currentTimeMillis();
    }
}
