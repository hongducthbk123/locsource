package com.btgame.seaui.p045ui.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.btgame.seasdk.btcore.common.util.BTResourceUtil;
import com.btgame.seasdk.btcore.common.util.BTScreenUtil;
import com.btgame.seaui.p045ui.constant.UIConfig;
import com.btgame.seaui.p045ui.view.widget.BtBasePageView;
import com.btgame.seaui.p045ui.view.widget.BtImageButton;
import com.btgame.seaui.p045ui.view.widget.HtmlTextView;
import java.util.ArrayList;

/* renamed from: com.btgame.seaui.ui.view.RegisterView */
public class RegisterView extends BtBasePageView {
    private EditText accountEt;
    private HtmlTextView protocolTv;
    private EditText pwdCfEt;
    private EditText pwdEt;

    public RegisterView(Context context, OnClickListener clickListener) {
        super(context, clickListener);
    }

    /* access modifiers changed from: protected */
    public void initLayout(Context context) {
        addTitle(context, BTResourceUtil.findStringByName("register_tv_title"));
        int rowWidth = BTScreenUtil.getInstance(context).getHorizontalPX(900.0d);
        int rowMarginTop = BTScreenUtil.getInstance(context).getVerticalPX(30.0d);
        String inputHint = BTResourceUtil.findStringByName("register_et_account_hint");
        Drawable drawableLeft = BTResourceUtil.findDrawableByName("icon_account");
        int textPadding = BTScreenUtil.getInstance(context).getHorizontalPX(40.0d);
        int iconPadding = textPadding;
        int lIconWidth = BTScreenUtil.getInstance(context).getHorizontalPX(40.0d);
        int lIconHeight = lIconWidth;
        int rIconWidth = BTScreenUtil.getInstance(context).getHorizontalPX(40.0d);
        int rIconHeight = rIconWidth;
        this.accountEt = createInputRow(context, rowWidth, rowMarginTop, inputHint, drawableLeft, null, textPadding, iconPadding, lIconWidth, lIconHeight, rIconWidth, rIconHeight, false);
        String inputHint2 = BTResourceUtil.findStringByName("register_et_pwd_hint");
        Drawable drawableLeft2 = BTResourceUtil.findDrawableByName("icon_pwd");
        Drawable[] drawableRights = {BTResourceUtil.findDrawableByName("icon_pwd_unsee"), BTResourceUtil.findDrawableByName("icon_pwd_see")};
        int rowMarginTop2 = BTScreenUtil.getInstance(context).getVerticalPX(30.0d);
        this.pwdEt = createInputRow(context, rowWidth, rowMarginTop2, inputHint2, drawableLeft2, drawableRights, textPadding, iconPadding, lIconWidth, lIconHeight, rIconWidth, rIconHeight, true);
        this.pwdCfEt = createInputRow(context, rowWidth, rowMarginTop2, BTResourceUtil.findStringByName("register_et_pwdcf_hint"), drawableLeft2, drawableRights, textPadding, iconPadding, lIconWidth, lIconHeight, rIconWidth, rIconHeight, true);
        this.protocolTv = new HtmlTextView(context);
        LayoutParams layoutParams = new LayoutParams(-2, -2);
        layoutParams.gravity = 17;
        layoutParams.setMargins(0, rowMarginTop2, 0, 0);
        this.protocolTv.setLayoutParams(layoutParams);
        this.protocolTv.setTextSize(0, (float) BTScreenUtil.countTextSize(context, 32.0f));
        addView(this.protocolTv);
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(0);
        LayoutParams layoutParams2 = new LayoutParams(-2, -2);
        layoutParams2.gravity = 17;
        layoutParams2.setMargins(0, BTScreenUtil.getInstance(context).getVerticalPX(50.0d), 0, 0);
        linearLayout.setLayoutParams(layoutParams2);
        addView(linearLayout);
        int textSize = BTScreenUtil.countTextSize(getContext(), 48.0f);
        int textPadding2 = BTScreenUtil.getInstance(context).getHorizontalPX(10.0d);
        int iconPadding2 = textPadding2;
        int iconWidth = BTScreenUtil.getInstance(context).getHorizontalPX(60.0d);
        int iconHeight = iconWidth;
        int bottomBtWidth = BTScreenUtil.getInstance(context).getHorizontalPX(438.0d);
        int bottomBtHeight = BTScreenUtil.getInstance(context).getVerticalPX(100.0d);
        StateListDrawable btBg = (StateListDrawable) BTResourceUtil.findDrawableByName("btn_withicon_img");
        ColorStateList titleStateColor = BTResourceUtil.findColorStateListByName("bt_withicon_text_color_selector");
        ArrayList<Integer> visibleIndexs = new ArrayList<>();
        int index = 0;
        for (int i = 1; i <= 3; i++) {
            String[] bt_config = BTResourceUtil.findStringArrayByName("register_bt" + i + "_config");
            BtImageButton opBt = createBtIconButton(context, textSize, true, textPadding2, iconPadding2, iconWidth, iconHeight, titleStateColor, btBg, bt_config);
            LayoutParams layoutParams3 = new LayoutParams(-2, -2);
            layoutParams3.gravity = 17;
            opBt.setLayoutParams(layoutParams3);
            opBt.setMinWidth(bottomBtWidth);
            opBt.setMinHeight(bottomBtHeight);
            linearLayout.addView(opBt);
            opBt.setOnClickListener(this.mClickListener);
            if (!UIConfig.contains(bt_config[3])) {
                opBt.setVisibility(8);
            } else {
                visibleIndexs.add(Integer.valueOf(index));
            }
            index++;
        }
        for (int i2 = 1; i2 < visibleIndexs.size(); i2++) {
            BtImageButton opBt2 = (BtImageButton) linearLayout.getChildAt(((Integer) visibleIndexs.get(i2)).intValue());
            LayoutParams opBtLp = (LayoutParams) opBt2.getLayoutParams();
            opBtLp.setMargins(BTScreenUtil.getInstance(context).getHorizontalPX(20.0d), 0, 0, 0);
            opBt2.setLayoutParams(opBtLp);
        }
    }

    public HtmlTextView getProtocolTv() {
        return this.protocolTv;
    }

    public String[] getFormData() {
        return new String[]{this.accountEt.getText().toString(), this.pwdEt.getText().toString(), this.pwdCfEt.getText().toString()};
    }
}
