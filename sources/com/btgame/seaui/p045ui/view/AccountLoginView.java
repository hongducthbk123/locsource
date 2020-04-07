package com.btgame.seaui.p045ui.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.btgame.seasdk.btcore.common.util.BTResourceUtil;
import com.btgame.seasdk.btcore.common.util.BTScreenUtil;
import com.btgame.seaui.p045ui.view.widget.BtBasePageView;
import com.btgame.seaui.p045ui.view.widget.BtImageButton;

/* renamed from: com.btgame.seaui.ui.view.AccountLoginView */
public class AccountLoginView extends BtBasePageView {
    private EditText accountEt;
    private EditText pwdEt;

    public AccountLoginView(Context context, OnClickListener clickListener) {
        super(context, clickListener);
    }

    /* access modifiers changed from: protected */
    public void initLayout(Context context) {
        StateListDrawable stateListDrawable;
        addTitle(context, BTResourceUtil.findStringByName("btlogin_tv_title"));
        int rowWidth = BTScreenUtil.getInstance(context).getHorizontalPX(900.0d);
        int rowMarginTop = BTScreenUtil.getInstance(context).getVerticalPX(50.0d);
        String inputHint = BTResourceUtil.findStringByName("btlogin_et_account_hint");
        Drawable drawableLeft = BTResourceUtil.findDrawableByName("icon_account");
        int textPadding = BTScreenUtil.getInstance(context).getHorizontalPX(40.0d);
        int iconPadding = textPadding;
        int lIconWidth = BTScreenUtil.getInstance(context).getHorizontalPX(40.0d);
        int lIconHeight = lIconWidth;
        int rIconWidth = BTScreenUtil.getInstance(context).getHorizontalPX(40.0d);
        int rIconHeight = rIconWidth;
        this.accountEt = createInputRow(context, rowWidth, rowMarginTop, inputHint, drawableLeft, null, textPadding, iconPadding, lIconWidth, lIconHeight, rIconWidth, rIconHeight, false);
        this.pwdEt = createInputRow(context, rowWidth, rowMarginTop, BTResourceUtil.findStringByName("btlogin_et_pwd_hint"), BTResourceUtil.findDrawableByName("icon_pwd"), null, textPadding, iconPadding, lIconWidth, lIconHeight, rIconWidth, rIconHeight, true);
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(0);
        LayoutParams layoutParams = new LayoutParams(-2, -2);
        layoutParams.gravity = 17;
        layoutParams.setMargins(0, BTScreenUtil.getInstance(context).getVerticalPX(80.0d), 0, 0);
        linearLayout.setLayoutParams(layoutParams);
        addView(linearLayout);
        int textSize = BTScreenUtil.countTextSize(getContext(), 48.0f);
        int textPadding2 = BTScreenUtil.getInstance(context).getHorizontalPX(10.0d);
        int iconPadding2 = textPadding2;
        int iconWidth = BTScreenUtil.getInstance(context).getHorizontalPX(60.0d);
        int iconHeight = iconWidth;
        int btWidth = BTScreenUtil.getInstance(context).getHorizontalPX(438.0d);
        int btHeight = BTScreenUtil.getInstance(context).getVerticalPX(100.0d);
        StateListDrawable btBg = (StateListDrawable) BTResourceUtil.findDrawableByName("btn_noicon_img");
        for (int i = 1; i <= 2; i++) {
            String[] bt_config = BTResourceUtil.findStringArrayByName("btlogin_bt" + i + "_config");
            if (i == 1) {
                stateListDrawable = null;
            } else {
                stateListDrawable = btBg;
            }
            BtImageButton opBt = createBtIconButton(context, textSize, true, textPadding2, iconPadding2, iconWidth, iconHeight, null, stateListDrawable, bt_config);
            LayoutParams layoutParams2 = new LayoutParams(-2, -2);
            layoutParams2.gravity = 17;
            if (i != 1) {
                layoutParams2.setMargins(BTScreenUtil.getInstance(context).getHorizontalPX(20.0d), 0, 0, 0);
            }
            opBt.setLayoutParams(layoutParams2);
            opBt.setMinWidth(btWidth);
            opBt.setMinHeight(btHeight);
            linearLayout.addView(opBt);
            opBt.setOnClickListener(this.mClickListener);
        }
    }

    public EditText getAccountEt() {
        return this.accountEt;
    }

    public EditText getPwdEt() {
        return this.pwdEt;
    }

    public String[] getFormData() {
        return new String[]{this.accountEt.getText().toString(), this.pwdEt.getText().toString()};
    }
}
