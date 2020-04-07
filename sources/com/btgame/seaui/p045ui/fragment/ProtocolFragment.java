package com.btgame.seaui.p045ui.fragment;

import android.content.Context;
import android.view.View;
import com.btgame.seasdk.btcore.common.util.BTResourceUtil;
import com.btgame.seasdk.btcore.widget.webview.BtWebView;
import com.btgame.seasdk.btcore.widget.webview.BtWebView.NetWorkUnavailableListener;
import com.btgame.seaui.p045ui.view.ProtocolView;

/* renamed from: com.btgame.seaui.ui.fragment.ProtocolFragment */
public class ProtocolFragment extends BaseFragment {
    private ProtocolView protocolView;

    /* access modifiers changed from: protected */
    public View initViews(Context context) {
        this.protocolView = new ProtocolView(context, this);
        return this.protocolView;
    }

    /* access modifiers changed from: protected */
    public void initDatas() {
        BtWebView btWebView = this.protocolView.getBtWebView();
        btWebView.setNetWorkUnavailableListener(new NetWorkUnavailableListener() {
            public int handleNetWorkError() {
                return 1;
            }

            public void onNetWorkDialogClose() {
                ProtocolFragment.this.getActivity().onBackPressed();
            }
        });
        btWebView.loadUrl(BTResourceUtil.findStringByName("protocol_url"));
    }

    /* access modifiers changed from: protected */
    public boolean validateFormData(String[] formData) {
        return true;
    }

    /* access modifiers changed from: protected */
    public void onShow() {
        super.onShow();
    }

    public void onClick(View view) {
        super.onClick(view);
    }
}
