package com.btgame.seaui.p045ui.view;

import android.content.Context;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.btgame.seasdk.btcore.common.util.BTResourceUtil;
import com.btgame.seasdk.btcore.common.util.ContextUtil;
import com.btgame.seasdk.btcore.widget.webview.BtWebApi;
import com.btgame.seasdk.btcore.widget.webview.BtWebView;
import com.btgame.seaui.p045ui.view.widget.BtBasePageView;

/* renamed from: com.btgame.seaui.ui.view.ProtocolView */
public class ProtocolView extends BtBasePageView {
    private BtWebView btWebView;

    public ProtocolView(Context context, OnClickListener clickListener) {
        super(context, clickListener);
    }

    /* access modifiers changed from: protected */
    public void initLayout(Context context) {
        addTitle(context, BTResourceUtil.findStringByName("protocol_tv_title"));
        LinearLayout protocolLl = new LinearLayout(context);
        protocolLl.setOrientation(1);
        protocolLl.setLayoutParams(new LayoutParams(-1, -1));
        addView(protocolLl);
        this.btWebView = new BtWebView(ContextUtil.getCurrentActivity());
        this.btWebView.setLayoutParams(new LayoutParams(-1, -1));
        this.btWebView.setBackgroundColor(BTResourceUtil.findColorByName("bg_page_color"));
        this.btWebView.addJavascriptInterface(new BtWebApi(ContextUtil.getCurrentActivity(), this.btWebView, null), "btWebApi");
        protocolLl.addView(this.btWebView);
        this.btWebView.setVisibility(0);
    }

    public BtWebView getBtWebView() {
        return this.btWebView;
    }
}
