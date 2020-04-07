package com.btgame.onesdk.frame;

import com.btgame.onesdk.frame.eneity.AppChannelDefine;
import com.btgame.onesdk.frame.eneity.Args;
import com.btgame.onesdk.frame.eneity.ChargeData;
import com.btgame.onesdk.frame.eneity.OneUserInfo;
import com.btgame.onesdk.frame.eneity.SdkLoginCallBack;
import com.btgame.sdk.http.OkHttpCallBack;

public interface IGetDataImpl {
    void getAppChannelDefine(AppChannelDefine appChannelDefine, OkHttpCallBack okHttpCallBack);

    void getBtOrderInfo(ChargeData chargeData, OneUserInfo oneUserInfo, Args args, OkHttpCallBack okHttpCallBack);

    void getBtSession(AppChannelDefine appChannelDefine, SdkLoginCallBack sdkLoginCallBack, Args args, OkHttpCallBack okHttpCallBack);

    String getDevJson();

    void recyle();

    void submitPayInfo();
}
