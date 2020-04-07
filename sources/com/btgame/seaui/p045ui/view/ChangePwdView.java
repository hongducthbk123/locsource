package com.btgame.seaui.p045ui.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View.OnClickListener;
import android.widget.EditText;
import com.btgame.seasdk.btcore.common.util.BTResourceUtil;
import com.btgame.seasdk.btcore.common.util.BTScreenUtil;
import com.btgame.seaui.p045ui.view.widget.BtBasePageView;
import com.btgame.seaui.p045ui.view.widget.BtImageButton;

/* renamed from: com.btgame.seaui.ui.view.ChangePwdView */
public class ChangePwdView extends BtBasePageView {
    private EditText accountEt;
    private EditText newPwdCfEt;
    private EditText newPwdEt;
    private EditText oldPwdEt;

    public ChangePwdView(Context context, OnClickListener clickListener) {
        super(context, clickListener);
    }

    /* access modifiers changed from: protected */
    public void initLayout(Context context) {
        addTitle(context, BTResourceUtil.findStringByName("changepwd_tv_title"));
        int rowWidth = BTScreenUtil.getInstance(context).getHorizontalPX(900.0d);
        int rowMarginTop = BTScreenUtil.getInstance(context).getVerticalPX(30.0d);
        String inputHint = BTResourceUtil.findStringByName("changepwd_et_account_hint");
        Drawable drawableLeft = BTResourceUtil.findDrawableByName("icon_account");
        int textPadding = BTScreenUtil.getInstance(context).getHorizontalPX(40.0d);
        int iconPadding = textPadding;
        int lIconWidth = BTScreenUtil.getInstance(context).getHorizontalPX(40.0d);
        int lIconHeight = lIconWidth;
        int rIconWidth = BTScreenUtil.getInstance(context).getHorizontalPX(40.0d);
        int rIconHeight = rIconWidth;
        this.accountEt = createInputRow(context, rowWidth, rowMarginTop, inputHint, drawableLeft, null, textPadding, iconPadding, lIconWidth, lIconHeight, rIconWidth, rIconHeight, false);
        Drawable[] drawableRights = {BTResourceUtil.findDrawableByName("icon_pwd_unsee"), BTResourceUtil.findDrawableByName("icon_pwd_see")};
        this.oldPwdEt = createInputRow(context, rowWidth, rowMarginTop, BTResourceUtil.findStringByName("changepwd_et_oldpwd_hint"), BTResourceUtil.findDrawableByName("icon_oldpwd"), drawableRights, textPadding, iconPadding, lIconWidth, lIconHeight, rIconWidth, rIconHeight, true);
        String inputHint2 = BTResourceUtil.findStringByName("changepwd_et_newpwd1_hint");
        Drawable drawableLeft2 = BTResourceUtil.findDrawableByName("icon_pwd");
        this.newPwdEt = createInputRow(context, rowWidth, rowMarginTop, inputHint2, drawableLeft2, drawableRights, textPadding, iconPadding, lIconWidth, lIconHeight, rIconWidth, rIconHeight, true);
        this.newPwdCfEt = createInputRow(context, rowWidth, rowMarginTop, BTResourceUtil.findStringByName("changepwd_et_newpwd2_hint"), drawableLeft2, drawableRights, textPadding, iconPadding, lIconWidth, lIconHeight, rIconWidth, rIconHeight, true);
        BtImageButton opBt = createDefaultOpButton(context, BTResourceUtil.findStringArrayByName("changepwd_bt_config"));
        addView(opBt);
        opBt.setOnClickListener(this.mClickListener);
    }

    public EditText getAccountEt() {
        return this.accountEt;
    }

    public String[] getFormData() {
        return new String[]{this.accountEt.getText().toString(), this.oldPwdEt.getText().toString(), this.newPwdEt.getText().toString(), this.newPwdCfEt.getText().toString()};
    }
}
