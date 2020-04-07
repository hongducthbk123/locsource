package com.btgame.seaui.p045ui.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.btgame.seasdk.btcore.common.util.BTResourceUtil;
import com.btgame.seasdk.btcore.common.util.BTScreenUtil;
import com.btgame.seaui.p045ui.view.widget.BtBasePageView;
import com.btgame.seaui.p045ui.view.widget.BtImageButton;

/* renamed from: com.btgame.seaui.ui.view.RetrievePwdView */
public class RetrievePwdView extends BtBasePageView {
    private EditText accountEt;
    private EditText emailEt;

    public RetrievePwdView(Context context, OnClickListener clickListener) {
        super(context, clickListener);
    }

    /* access modifiers changed from: protected */
    public void initLayout(Context context) {
        addTitle(context, BTResourceUtil.findStringByName("retrievepwd_tv_title"));
        int rowWidth = BTScreenUtil.getInstance(context).getHorizontalPX(900.0d);
        int rowMarginTop = BTScreenUtil.getInstance(context).getVerticalPX(30.0d);
        String inputHint = BTResourceUtil.findStringByName("retrievepwd_et_account_hint");
        Drawable drawableLeft = BTResourceUtil.findDrawableByName("icon_account");
        int textPadding = BTScreenUtil.getInstance(context).getHorizontalPX(40.0d);
        int iconPadding = textPadding;
        int lIconWidth = BTScreenUtil.getInstance(context).getHorizontalPX(40.0d);
        int lIconHeight = lIconWidth;
        int rIconWidth = BTScreenUtil.getInstance(context).getHorizontalPX(48.0d);
        int rIconHeight = BTScreenUtil.getInstance(context).getVerticalPX(32.0d);
        this.accountEt = createInputRow(context, rowWidth, rowMarginTop, inputHint, drawableLeft, null, textPadding, iconPadding, lIconWidth, lIconHeight, rIconWidth, rIconHeight, false);
        this.emailEt = createInputRow(context, rowWidth, rowMarginTop, BTResourceUtil.findStringByName("retrievepwd_et_email_hint"), BTResourceUtil.findDrawableByName("icon_mail"), null, textPadding, iconPadding, lIconWidth, lIconHeight, rIconWidth, rIconHeight, false);
        TextView textView = new TextView(context);
        LayoutParams layoutParams = new LayoutParams(BTScreenUtil.getInstance(context).getHorizontalPX(1200.0d), -2);
        layoutParams.setMargins(0, rowMarginTop, 0, 0);
        textView.setLayoutParams(layoutParams);
        textView.setTextColor(BTResourceUtil.findColorByName("tv_notice_color"));
        textView.setTextSize(0, (float) BTScreenUtil.countTextSize(getContext(), 34.0f));
        textView.setLineSpacing(0.0f, 1.3f);
        textView.setText(Html.fromHtml(BTResourceUtil.findStringByName("retrievepwd_tv_notice")));
        addView(textView);
        BtImageButton opBt = createDefaultOpButton(context, BTResourceUtil.findStringArrayByName("retrievepwd_bt_config"));
        addView(opBt);
        opBt.setOnClickListener(this.mClickListener);
    }

    public EditText getAccountEt() {
        return this.accountEt;
    }

    public String[] getFormData() {
        return new String[]{this.accountEt.getText().toString(), this.emailEt.getText().toString()};
    }
}
