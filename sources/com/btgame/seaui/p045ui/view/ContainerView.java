package com.btgame.seaui.p045ui.view;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import com.btgame.seasdk.btcore.common.util.BTResourceUtil;
import com.btgame.seasdk.btcore.common.util.BTScreenUtil;
import com.btgame.seaui.p045ui.constant.UIidAndtag;

/* renamed from: com.btgame.seaui.ui.view.ContainerView */
public class ContainerView extends LinearLayout {
    private ImageButton btnBack;
    private LinearLayout centerLayout;
    private OnClickListener mClickListener;
    private FrameLayout rootFrameLayout;

    public ContainerView(Context context, OnClickListener clickListener) {
        super(context);
        this.mClickListener = clickListener;
        initLayout(context);
    }

    private void initLayout(Context context) {
        setLayoutParams(new LayoutParams(-1, -1));
        setOrientation(1);
        setBackgroundColor(BTResourceUtil.findColorByName("bg_page_color"));
        RelativeLayout containerLayout = new RelativeLayout(context);
        containerLayout.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        addView(containerLayout);
        this.btnBack = new ImageButton(context);
        int paddingX = BTScreenUtil.getInstance(context).getHorizontalPX(45.0d);
        int paddingY = BTScreenUtil.getInstance(context).getVerticalPX(40.0d);
        this.btnBack.setPadding(paddingX, paddingY, paddingX, paddingY);
        this.btnBack.setScaleType(ScaleType.FIT_CENTER);
        this.btnBack.setImageDrawable(BTResourceUtil.findDrawableByName("btn_back_img"));
        this.btnBack.setBackgroundDrawable(new ColorDrawable());
        RelativeLayout.LayoutParams btnBackLp = new RelativeLayout.LayoutParams(BTScreenUtil.getInstance(context).getHorizontalPX(140.0d), BTScreenUtil.getInstance(context).getVerticalPX(140.0d));
        btnBackLp.addRule(9);
        btnBackLp.addRule(10);
        this.btnBack.setLayoutParams(btnBackLp);
        containerLayout.addView(this.btnBack);
        this.btnBack.setOnClickListener(this.mClickListener);
        this.btnBack.setVisibility(8);
        this.centerLayout = new LinearLayout(context);
        this.centerLayout.setOrientation(1);
        this.centerLayout.setGravity(17);
        this.centerLayout.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        containerLayout.addView(this.centerLayout);
        this.rootFrameLayout = new FrameLayout(context);
        this.rootFrameLayout.setId(UIidAndtag.ID_ROOTFL);
        this.centerLayout.addView(this.rootFrameLayout, new LayoutParams(-1, -1));
    }

    public ImageButton getBtnBack() {
        return this.btnBack;
    }

    public FrameLayout getRootFrameLayout() {
        return this.rootFrameLayout;
    }
}
