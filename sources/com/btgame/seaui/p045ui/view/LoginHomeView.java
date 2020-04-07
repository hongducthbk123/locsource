package com.btgame.seaui.p045ui.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.StateListDrawable;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.btgame.seasdk.btcore.common.util.BTResourceUtil;
import com.btgame.seasdk.btcore.common.util.BTScreenUtil;
import com.btgame.seaui.p045ui.constant.UIConfig;
import com.btgame.seaui.p045ui.view.widget.BtBasePageView;
import com.btgame.seaui.p045ui.view.widget.BtImageButton;
import java.util.ArrayList;

/* renamed from: com.btgame.seaui.ui.view.LoginHomeView */
public class LoginHomeView extends BtBasePageView {
    public LoginHomeView(Context context, OnClickListener clickListener) {
        super(context, clickListener);
    }

    /* access modifiers changed from: protected */
    public void initLayout(Context context) {
        addTitle(context, BTResourceUtil.findStringByName("loginhome_tv_title"));
        int textSize = BTScreenUtil.countTextSize(getContext(), 48.0f);
        int textPadding = BTScreenUtil.getInstance(context).getHorizontalPX(10.0d);
        int iconPadding = textPadding;
        int iconWidth = BTScreenUtil.getInstance(context).getHorizontalPX(60.0d);
        int iconHeight = iconWidth;
        int btWidth = BTScreenUtil.getInstance(context).getHorizontalPX(900.0d);
        int btHeight = BTScreenUtil.getInstance(context).getVerticalPX(100.0d);
        StateListDrawable btBg = (StateListDrawable) BTResourceUtil.findDrawableByName("btn_withicon_img");
        ColorStateList titleStateColor = BTResourceUtil.findColorStateListByName("bt_withicon_text_color_selector");
        for (int i = 1; i <= 2; i++) {
            String[] bt_config = BTResourceUtil.findStringArrayByName("loginhome_bt" + i + "_config");
            BtImageButton opBt = createBtIconButton(context, textSize, true, textPadding, iconPadding, iconWidth, iconHeight, titleStateColor, btBg, bt_config);
            LayoutParams layoutParams = new LayoutParams(-2, -2);
            layoutParams.gravity = 17;
            layoutParams.setMargins(0, BTScreenUtil.getInstance(context).getVerticalPX(50.0d), 0, 0);
            opBt.setLayoutParams(layoutParams);
            opBt.setMinWidth(btWidth);
            opBt.setMinHeight(btHeight);
            addView(opBt);
            opBt.setOnClickListener(this.mClickListener);
            if (!UIConfig.contains(bt_config[3])) {
                opBt.setVisibility(8);
            }
        }
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(0);
        LayoutParams layoutParams2 = new LayoutParams(-2, -2);
        layoutParams2.gravity = 17;
        layoutParams2.setMargins(0, BTScreenUtil.getInstance(context).getVerticalPX(80.0d), 0, 0);
        linearLayout.setLayoutParams(layoutParams2);
        addView(linearLayout);
        int textSize2 = BTScreenUtil.countTextSize(getContext(), 48.0f);
        int textPadding2 = BTScreenUtil.getInstance(context).getHorizontalPX(10.0d);
        int iconPadding2 = textPadding2;
        int iconWidth2 = BTScreenUtil.getInstance(context).getHorizontalPX(60.0d);
        int iconHeight2 = iconWidth2;
        int bottomBtWidth = BTScreenUtil.getInstance(context).getHorizontalPX(438.0d);
        int bottomBtHeight = BTScreenUtil.getInstance(context).getVerticalPX(100.0d);
        ArrayList<Integer> visibleIndexs = new ArrayList<>();
        int index = 0;
        for (int i2 = 3; i2 <= 5; i2++) {
            String[] bt_config2 = BTResourceUtil.findStringArrayByName("loginhome_bt" + i2 + "_config");
            BtImageButton opBt2 = createBtIconButton(context, textSize2, false, textPadding2, iconPadding2, iconWidth2, iconHeight2, null, null, bt_config2);
            LayoutParams layoutParams3 = new LayoutParams(-2, -2);
            layoutParams3.gravity = 17;
            opBt2.setLayoutParams(layoutParams3);
            opBt2.setMinWidth(bottomBtWidth);
            opBt2.setMinHeight(bottomBtHeight);
            linearLayout.addView(opBt2);
            opBt2.setOnClickListener(this.mClickListener);
            if (!UIConfig.contains(bt_config2[3])) {
                opBt2.setVisibility(8);
            } else {
                visibleIndexs.add(Integer.valueOf(index));
            }
            index++;
        }
        for (int i3 = 1; i3 < visibleIndexs.size(); i3++) {
            BtImageButton opBt3 = (BtImageButton) linearLayout.getChildAt(((Integer) visibleIndexs.get(i3)).intValue());
            LayoutParams opBtLp = (LayoutParams) opBt3.getLayoutParams();
            opBtLp.setMargins(BTScreenUtil.getInstance(context).getHorizontalPX(20.0d), 0, 0, 0);
            opBt3.setLayoutParams(opBtLp);
        }
        if (visibleIndexs.size() == 1) {
            ((BtImageButton) linearLayout.getChildAt(((Integer) visibleIndexs.get(0)).intValue())).setMinWidth(btWidth - BTScreenUtil.getInstance(context).getHorizontalPX(10.0d));
        }
    }
}
