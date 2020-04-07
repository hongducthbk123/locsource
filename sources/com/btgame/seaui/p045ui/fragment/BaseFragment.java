package com.btgame.seaui.p045ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.btgame.seasdk.btcore.common.constant.LifecycleEventType;
import com.btgame.seasdk.btcore.common.event.LifecycleEvent.Builder;
import com.btgame.seasdk.btcore.common.event.SdkEventManager;
import com.btgame.seasdk.btcore.common.util.BtsdkLog;
import com.btgame.seaui.BtSeaUiManager;
import com.btgame.seaui.p045ui.BtSeaLoginActivity;
import com.btgame.seaui.p045ui.constant.UIidAndtag;

/* renamed from: com.btgame.seaui.ui.fragment.BaseFragment */
public abstract class BaseFragment extends Fragment implements OnClickListener {
    private static final String STATE_SAVE_IS_HIDDEN = "STATE_SAVE_IS_HIDDEN";
    private View contentView;
    protected Context mContext;
    private OnHiddenChangedListener onHiddenChangedListener;

    /* renamed from: com.btgame.seaui.ui.fragment.BaseFragment$OnHiddenChangedListener */
    public interface OnHiddenChangedListener {
        void onHiddenChanged(BaseFragment baseFragment, boolean z);
    }

    /* access modifiers changed from: protected */
    public abstract void initDatas();

    /* access modifiers changed from: protected */
    public abstract View initViews(Context context);

    /* access modifiers changed from: protected */
    public abstract boolean validateFormData(String[] strArr);

    /* access modifiers changed from: protected */
    public View getContentView() {
        return this.contentView;
    }

    /* access modifiers changed from: protected */
    public void onShow() {
    }

    public void setOnHiddenChangedListener(OnHiddenChangedListener onHiddenChangedListener2) {
        this.onHiddenChangedListener = onHiddenChangedListener2;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(STATE_SAVE_IS_HIDDEN, isHidden());
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.contentView = initViews(this.mContext);
        return this.contentView;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setOnHiddenChangedListener((BtSeaLoginActivity) getActivity());
        if (savedInstanceState != null) {
            boolean isSupportHidden = savedInstanceState.getBoolean(STATE_SAVE_IS_HIDDEN);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            if (isSupportHidden) {
                ft.hide(this);
                onDisplayStatusChange(true);
            } else {
                ft.show(this);
                onDisplayStatusChange(false);
            }
            ft.commit();
        }
        initDatas();
    }

    public void onClick(View view) {
        String tag = view.getTag() + "";
        BtsdkLog.m1429d(" tag:" + tag);
        if (tag.startsWith("TAG_PAGE_")) {
            BtSeaUiManager.getInstance().toUiPage(tag, null);
        } else if (tag.startsWith(UIidAndtag.TAG_THIRDLOGIN_PREFIX)) {
            Bundle bundle = getArguments();
            String identity = null;
            if (bundle != null && !TextUtils.isEmpty(bundle.getString(BtSeaUiManager.KEY_BUNDLE_BINDINFO))) {
                identity = bundle.getString(BtSeaUiManager.KEY_BUNDLE_BINDINFO);
                BtsdkLog.m1429d("绑定注册，游客identity：" + identity);
            }
            BtSeaUiManager.getInstance().sendThirdLoginEvent(tag, this, identity, false);
        }
    }

    public void onResume() {
        super.onResume();
    }

    public void onPause() {
        super.onPause();
    }

    public Context getContext() {
        return this.mContext;
    }

    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        onDisplayStatusChange(hidden);
    }

    public void onDisplayStatusChange(boolean hidden) {
        if (!hidden) {
            onShow();
        }
        if (this.onHiddenChangedListener != null) {
            this.onHiddenChangedListener.onHiddenChanged(this, hidden);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        SdkEventManager.postEvent(new Builder().lifecycleEventType(LifecycleEventType.onSdkActivityResult).activity(getActivity()).requestCode(requestCode).resultCode(resultCode).data(data).build());
    }

    public boolean onBackPressed() {
        return false;
    }
}
