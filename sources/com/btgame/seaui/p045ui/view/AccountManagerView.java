package com.btgame.seaui.p045ui.view;

import android.content.Context;
import android.view.View.OnClickListener;
import android.widget.LinearLayout.LayoutParams;
import com.btgame.seasdk.btcore.common.util.BTResourceUtil;
import com.btgame.seasdk.btcore.common.util.BTScreenUtil;
import com.btgame.seaui.p045ui.view.widget.BtBasePageView;
import com.btgame.seaui.p045ui.view.widget.BtImageButton;

/* renamed from: com.btgame.seaui.ui.view.AccountManagerView */
public class AccountManagerView extends BtBasePageView {
    public AccountManagerView(Context context, OnClickListener clickListener) {
        super(context, clickListener);
    }

    /* access modifiers changed from: protected */
    public void initLayout(Context context) {
        addTitle(context, BTResourceUtil.findStringByName("accountmanager_tv_title"));
        int textSize = BTScreenUtil.countTextSize(getContext(), 48.0f);
        int textPadding = BTScreenUtil.getInstance(context).getHorizontalPX(10.0d);
        int iconPadding = textPadding;
        int iconWidth = BTScreenUtil.getInstance(context).getHorizontalPX(38.0d);
        int iconHeight = iconWidth;
        int btWidth = BTScreenUtil.getInstance(context).getHorizontalPX(900.0d);
        int btHeight = BTScreenUtil.getInstance(context).getVerticalPX(100.0d);
        for (int i = 1; i <= 3; i++) {
            BtImageButton opBt = createBtIconButton(context, textSize, true, textPadding, iconPadding, iconWidth, iconHeight, null, null, BTResourceUtil.findStringArrayByName("accountmanager_bt" + i + "_config"));
            LayoutParams layoutParams = new LayoutParams(-2, -2);
            layoutParams.gravity = 17;
            layoutParams.setMargins(0, BTScreenUtil.getInstance(context).getVerticalPX(50.0d), 0, 0);
            opBt.setLayoutParams(layoutParams);
            opBt.setMinWidth(btWidth);
            opBt.setMinHeight(btHeight);
            addView(opBt);
            opBt.setOnClickListener(this.mClickListener);
        }
    }
}
