package com.btgame.seaui.p045ui.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import com.btgame.seasdk.btcore.common.util.BTResourceUtil;
import com.btgame.seasdk.btcore.common.util.BTScreenUtil;
import com.btgame.seaui.p045ui.constant.UIidAndtag;
import com.btgame.seaui.p045ui.view.widget.BtBasePageView;
import com.btgame.seaui.p045ui.view.widget.BtImageButton;

/* renamed from: com.btgame.seaui.ui.view.BindMailView */
public class BindMailView extends BtBasePageView {
    private EditText accountEt;
    private EditText emailCodeEt;
    private EditText emailEt;
    private EditText pwdEt;

    public BindMailView(Context context, OnClickListener clickListener) {
        super(context, clickListener);
    }

    /* access modifiers changed from: protected */
    public void initLayout(Context context) {
        addTitle(context, BTResourceUtil.findStringByName("bindmail_tv_title"));
        int rowWidth = BTScreenUtil.getInstance(context).getHorizontalPX(900.0d);
        int rowMarginTop = BTScreenUtil.getInstance(context).getVerticalPX(30.0d);
        String inputHint = BTResourceUtil.findStringByName("bindmail_et_account_hint");
        Drawable drawableLeft = BTResourceUtil.findDrawableByName("icon_account");
        int textPadding = BTScreenUtil.getInstance(context).getHorizontalPX(40.0d);
        int iconPadding = textPadding;
        int lIconWidth = BTScreenUtil.getInstance(context).getHorizontalPX(40.0d);
        int lIconHeight = lIconWidth;
        int rIconWidth = BTScreenUtil.getInstance(context).getHorizontalPX(40.0d);
        int rIconHeight = rIconWidth;
        this.accountEt = createInputRow(context, rowWidth, rowMarginTop, inputHint, drawableLeft, null, textPadding, iconPadding, lIconWidth, lIconHeight, rIconWidth, rIconHeight, false);
        this.pwdEt = createInputRow(context, rowWidth, rowMarginTop, BTResourceUtil.findStringByName("bindmail_et_pwd_hint"), BTResourceUtil.findDrawableByName("icon_pwd"), new Drawable[]{BTResourceUtil.findDrawableByName("icon_pwd_unsee"), BTResourceUtil.findDrawableByName("icon_pwd_see")}, textPadding, iconPadding, lIconWidth, lIconHeight, rIconWidth, rIconHeight, true);
        this.emailEt = createInputRow(context, rowWidth, rowMarginTop, BTResourceUtil.findStringByName("bindmail_et_email_hint"), BTResourceUtil.findDrawableByName("icon_mail"), null, textPadding, iconPadding, lIconWidth, lIconHeight, rIconWidth, rIconHeight, false);
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setGravity(16);
        LayoutParams layoutParams = new LayoutParams(-2, -2);
        layoutParams.topMargin = rowMarginTop;
        linearLayout.setLayoutParams(layoutParams);
        addView(linearLayout);
        RelativeLayout relativeLayout = new RelativeLayout(context);
        relativeLayout.setGravity(16);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(rowWidth, -2);
        relativeLayout.setLayoutParams(layoutParams2);
        linearLayout.addView(relativeLayout);
        String inputHint2 = BTResourceUtil.findStringByName("bindmail_tips_mailCode");
        Drawable drawableLeft2 = BTResourceUtil.findDrawableByName("icon_code");
        int emailWidth = BTScreenUtil.getInstance(context).getHorizontalPX(520.0d);
        this.emailCodeEt = createInputRow(context, emailWidth, rowMarginTop, inputHint2, drawableLeft2, null, textPadding, iconPadding, lIconWidth, lIconHeight, rIconWidth, rIconHeight, false);
        LayoutParams params = (LayoutParams) this.emailCodeEt.getLayoutParams();
        params.topMargin = 0;
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(params);
        layoutParams3.addRule(9);
        this.emailCodeEt.setId(UIidAndtag.ID_EMAILCODE);
        if (this.emailCodeEt.getParent() != null) {
            ((LinearLayout) this.emailCodeEt.getParent()).removeView(this.emailCodeEt);
            relativeLayout.addView(this.emailCodeEt);
        }
        StateListDrawable btBg = (StateListDrawable) BTResourceUtil.findDrawableByName("btn_mailcode_img");
        Context context2 = context;
        BtImageButton opBt = createBtCenterNoIconButton(context2, (rowWidth - emailWidth) - BTScreenUtil.getInstance(context).getHorizontalPX(20.0d), -2, 0, BTScreenUtil.countTextSize(getContext(), 48.0f), 0, BTResourceUtil.findColorStateListByName("bt_mailcode_text_color_selector"), btBg, BTResourceUtil.findStringArrayByName("bindmail_bt_getcode_config"));
        RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams((LayoutParams) opBt.getLayoutParams());
        layoutParams4.addRule(11, -1);
        layoutParams4.addRule(6, UIidAndtag.ID_EMAILCODE);
        layoutParams4.addRule(8, UIidAndtag.ID_EMAILCODE);
        opBt.setLayoutParams(layoutParams4);
        opBt.setPadding(opBt.getPaddingLeft(), 0, opBt.getPaddingRight(), 0);
        relativeLayout.addView(opBt);
        opBt.setOnClickListener(this.mClickListener);
        BtImageButton opBt2 = createDefaultOpButton(context, BTResourceUtil.findStringArrayByName("bindmail_bt_config"));
        addView(opBt2);
        opBt2.setOnClickListener(this.mClickListener);
    }

    public EditText getAccountEt() {
        return this.accountEt;
    }

    public String[] getFormData() {
        return new String[]{this.accountEt.getText().toString(), this.pwdEt.getText().toString(), this.emailEt.getText().toString(), this.emailCodeEt.getText().toString()};
    }
}
