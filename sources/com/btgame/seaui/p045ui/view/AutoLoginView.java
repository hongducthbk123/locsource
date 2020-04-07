package com.btgame.seaui.p045ui.view;

import android.content.Context;
import android.view.View.OnClickListener;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.btgame.seasdk.btcore.common.util.BTResourceUtil;
import com.btgame.seasdk.btcore.common.util.BTScreenUtil;
import com.btgame.seaui.p045ui.view.widget.BtBasePageView;
import com.btgame.seaui.p045ui.view.widget.BtImageButton;

/* renamed from: com.btgame.seaui.ui.view.AutoLoginView */
public class AutoLoginView extends BtBasePageView {
    private TextView progressTipsTv;
    private TextView welcomeTipsTv;

    public AutoLoginView(Context context, OnClickListener clickListener) {
        super(context, clickListener);
    }

    /* access modifiers changed from: protected */
    public void initLayout(Context context) {
        addTitle(context, BTResourceUtil.findStringByName("autologin_tv_title"));
        this.welcomeTipsTv = new TextView(context);
        LayoutParams welcomeTipsTvLp = new LayoutParams(-2, -2);
        welcomeTipsTvLp.topMargin = BTScreenUtil.getInstance(context).getVerticalPX(100.0d);
        this.welcomeTipsTv.setLayoutParams(welcomeTipsTvLp);
        this.welcomeTipsTv.setTextSize(0, (float) BTScreenUtil.countTextSize(getContext(), 48.0f));
        addView(this.welcomeTipsTv);
        this.progressTipsTv = new TextView(context);
        LayoutParams progressTipsTvLp = new LayoutParams(-2, -2);
        progressTipsTvLp.topMargin = BTScreenUtil.getInstance(context).getVerticalPX(30.0d);
        this.progressTipsTv.setLayoutParams(progressTipsTvLp);
        this.progressTipsTv.setTextSize(0, (float) BTScreenUtil.countTextSize(getContext(), 48.0f));
        addView(this.progressTipsTv);
        BtImageButton opBt = createDefaultOpButton(context, BTResourceUtil.findStringArrayByName("autologin_bt_config"));
        LayoutParams opBtLp = (LayoutParams) opBt.getLayoutParams();
        opBtLp.topMargin = welcomeTipsTvLp.topMargin;
        opBt.setLayoutParams(opBtLp);
        addView(opBt);
        opBt.setOnClickListener(this.mClickListener);
    }

    public TextView getWelcomeTipsTv() {
        return this.welcomeTipsTv;
    }

    public TextView getProgressTipsTv() {
        return this.progressTipsTv;
    }
}
