package com.btgame.seaui.p045ui.view;

import android.content.Context;
import android.view.View.OnClickListener;
import android.widget.LinearLayout.LayoutParams;
import com.btgame.seasdk.btcore.common.util.BTResourceUtil;
import com.btgame.seasdk.btcore.common.util.BTScreenUtil;
import com.btgame.seaui.p045ui.view.widget.BtBasePageView;
import com.btgame.seaui.p045ui.view.widget.BtImageButton;
import com.btgame.seaui.p045ui.view.widget.HtmlTextView;

/* renamed from: com.btgame.seaui.ui.view.GuestNoticeView */
public class GuestNoticeView extends BtBasePageView {
    public GuestNoticeView(Context context, OnClickListener clickListener) {
        super(context, clickListener);
    }

    /* access modifiers changed from: protected */
    public void initLayout(Context context) {
        addTitle(context, BTResourceUtil.findStringByName("notice_tv_title"));
        HtmlTextView notice = new HtmlTextView(context);
        LayoutParams noticeLp = new LayoutParams(BTScreenUtil.getInstance(context).getHorizontalPX(1080.0d), -2);
        noticeLp.setMargins(0, BTScreenUtil.getInstance(context).getVerticalPX(100.0d), 0, 0);
        notice.setLayoutParams(noticeLp);
        notice.setTextColor(BTResourceUtil.findColorByName("tv_notice_color"));
        notice.setTextSize(0, (float) BTScreenUtil.countTextSize(getContext(), 36.0f));
        notice.setHtml(BTResourceUtil.findStringByName("notice_tv_tips"));
        addView(notice);
        BtImageButton opBt = createDefaultOpButton(context, BTResourceUtil.findStringArrayByName("notice_bt_config"));
        LayoutParams layoutParams = (LayoutParams) opBt.getLayoutParams();
        layoutParams.topMargin = BTScreenUtil.getInstance(context).getVerticalPX(100.0d);
        opBt.setLayoutParams(layoutParams);
        addView(opBt);
        opBt.setOnClickListener(this.mClickListener);
    }
}
