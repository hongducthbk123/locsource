package com.btgame.seaui.p045ui.fragment;

import android.content.Context;
import android.view.View;
import com.btgame.seasdk.btcore.common.constant.TrackEventType;
import com.btgame.seasdk.btcore.common.event.SdkEventManager;
import com.btgame.seasdk.btcore.common.event.data.TrackEvent;
import com.btgame.seaui.p045ui.view.LoginHomeView;

/* renamed from: com.btgame.seaui.ui.fragment.LoginHomeFragment */
public class LoginHomeFragment extends BaseFragment {
    private LoginHomeView loginHomeView;

    /* access modifiers changed from: protected */
    public View initViews(Context context) {
        this.loginHomeView = new LoginHomeView(context, this);
        return this.loginHomeView;
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
        SdkEventManager.postEvent(new TrackEvent(TrackEventType.SIGN_IN));
    }

    public void onClick(View view) {
        super.onClick(view);
    }
}
