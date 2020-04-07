package com.btgame.seaui.p045ui.fragment;

import android.content.Context;
import android.view.View;
import com.btgame.seaui.p045ui.view.AccountManagerView;

/* renamed from: com.btgame.seaui.ui.fragment.AccountManagerFragment */
public class AccountManagerFragment extends BaseFragment {
    private AccountManagerView accountManagerView;

    /* access modifiers changed from: protected */
    public View initViews(Context context) {
        this.accountManagerView = new AccountManagerView(context, this);
        return this.accountManagerView;
    }

    /* access modifiers changed from: protected */
    public void initDatas() {
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
