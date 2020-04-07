package com.btgame.onesdk.frame;

import android.content.Context;
import android.widget.Toast;
import com.btgame.onesdk.PlatfromUtils;
import com.btgame.onesdk.frame.constants.Constants;
import com.btgame.onesdk.frame.constants.RequestUrl;
import com.btgame.onesdk.frame.eneity.AppChannelDefine;
import com.btgame.onesdk.frame.eneity.AppChannelDefineReqData;
import com.btgame.onesdk.frame.eneity.AppChannelDefineRespData;
import com.btgame.onesdk.frame.eneity.Args;
import com.btgame.onesdk.frame.eneity.ChargeData;
import com.btgame.onesdk.frame.eneity.DeviceProperties;
import com.btgame.onesdk.frame.eneity.OneUserInfo;
import com.btgame.onesdk.frame.eneity.OrderReqData;
import com.btgame.onesdk.frame.eneity.OrderRespData;
import com.btgame.onesdk.frame.eneity.SdkLoginCallBack;
import com.btgame.onesdk.frame.eneity.SessionReqData;
import com.btgame.onesdk.frame.eneity.SessionRespData;
import com.btgame.onesdk.frame.utils.BuglyHelper;
import com.btgame.sdk.http.OkHttpCallBack;
import com.btgame.sdk.http.OkHttpUtil;
import com.btgame.sdk.util.BtUtils;
import com.btgame.sdk.util.BtsdkLog;
import com.btgame.sdk.util.DebugUtils;
import com.btgame.sdk.util.FileUtil;
import com.google.gson.Gson;

public class GetDataImpl implements IGetDataImpl {
    private static GetDataImpl instance;
    private DeviceProperties channeldev;
    private DebugUtils debugUtils = DebugUtils.getInstance();
    private Context mCtx;

    private GetDataImpl(Context ctx) {
        this.mCtx = ctx;
    }

    public static GetDataImpl getIntance(Context ctx) {
        if (instance == null) {
            instance = new GetDataImpl(ctx);
        }
        return instance;
    }

    public void setPlatFromVersion(String platfromVersion) {
        BtsdkLog.m1427p("platfromVersion", platfromVersion);
        DeviceProperties.getInstance(this.mCtx).setothersdkVersion(platfromVersion);
    }

    public void getAppChannelDefine(AppChannelDefine channelDefine, OkHttpCallBack ohcb) {
        if (this.debugUtils.isLogFlag()) {
            Toast.makeText(this.mCtx, "尚未关闭Log,请勿上线", 1).show();
        }
        String url = getUrl(Constants.getInstance().getURL(), RequestUrl.APPCHANNELDEFINE);
        String postData = getChannelDefineAndDevicesPropertiesJson(channelDefine);
        BtsdkLog.m1423d("getAppChannelDefine url = " + url);
        BtsdkLog.m1423d("getAppChannelDefine json = " + postData);
        OkHttpUtil.getInstance(this.mCtx).postJsonDataAsyn(url, postData, ohcb, AppChannelDefineRespData.class);
    }

    private String getChannelDefineAndDevicesPropertiesJson(AppChannelDefine channelDefine) {
        AppChannelDefineReqData appChannelDefineReqData = new AppChannelDefineReqData();
        appChannelDefineReqData.oneId = channelDefine.oneId;
        appChannelDefineReqData.platformIdSecond = Integer.valueOf(channelDefine.appExt1).intValue();
        appChannelDefineReqData.deviceProperties = DeviceProperties.getInstance(this.mCtx);
        return OkHttpUtil.getInstance(this.mCtx).getGson().toJson((Object) appChannelDefineReqData);
    }

    public void getBtSession(AppChannelDefine channelDefine, SdkLoginCallBack callBack, Args args, OkHttpCallBack httpResultListener) {
        SessionReqData reqData = new SessionReqData();
        reqData.deviceProperties = DeviceProperties.getInstance(this.mCtx);
        reqData.token = callBack.token;
        reqData.userId = callBack.userId;
        reqData.extArgs = args;
        reqData.oneId = channelDefine.oneId;
        int btChannelId = BtUtils.getBtChannelId(this.mCtx);
        if (btChannelId == -1) {
            btChannelId = 0;
        }
        reqData.platformIdSecond = btChannelId;
        String url = getUrl(Constants.getInstance().getURL(), RequestUrl.SESSION);
        String postData = getsessionReqdataJson(reqData).toString();
        BtsdkLog.m1423d("getBtSession url = " + url);
        BtsdkLog.m1423d("getBtSession postData = " + postData);
        OkHttpUtil.getInstance(this.mCtx).postJsonDataAsyn(url, postData, httpResultListener, SessionRespData.class);
    }

    private Object getsessionReqdataJson(SessionReqData reqData) {
        try {
            return OkHttpUtil.getInstance(this.mCtx).getGson().toJson((Object) reqData);
        } catch (Exception e) {
            e.printStackTrace();
            BtsdkLog.m1424e("Exception in gson to Json" + e.getMessage());
            return null;
        }
    }

    public void getBtOrderInfo(ChargeData chargeData, OneUserInfo info, Args args, OkHttpCallBack frameHttpResultListener) {
        OrderReqData orderReqData = new OrderReqData();
        orderReqData.chargeData = chargeData;
        orderReqData.oneId = info.oneId;
        orderReqData.extArgs = args;
        orderReqData.deviceProperties = DeviceProperties.getInstance(this.mCtx);
        String url = getUrl(Constants.getInstance().getURL(), RequestUrl.ORDERINFO);
        String postData = getOrderReqdataJson(orderReqData).toString();
        BtsdkLog.m1423d("getBtOrderInfo url = " + url);
        BtsdkLog.m1423d("getBtOrderInfo json = " + postData);
        OkHttpUtil.getInstance(this.mCtx).postJsonDataAsyn(url, postData, frameHttpResultListener, OrderRespData.class);
    }

    private Object getOrderReqdataJson(OrderReqData orderReqData) {
        try {
            return OkHttpUtil.getInstance(this.mCtx).getGson().toJson((Object) orderReqData);
        } catch (Exception e) {
            BuglyHelper.postCatchedException((Throwable) e);
            BtsdkLog.m1424e("Exception in gson to Json" + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public void submitPayInfo() {
    }

    public String getUrl(String url, String requestextc) {
        StringBuilder urlBuilder = new StringBuilder(url);
        urlBuilder.append(requestextc);
        if (this.debugUtils.isCodeFlag()) {
            urlBuilder.append("?debug");
        }
        return urlBuilder.toString();
    }

    public void recyle() {
        BtsdkLog.m1423d("recyle");
        FileUtil.destory();
        Constants.getInstance().destory();
        instance = null;
    }

    public String getTargetPlatformId() {
        return String.valueOf(BtUtils.getIntNoXMeTaData(this.mCtx, PlatfromUtils.PLATFROMID_KEY));
    }

    public String getDevJson() {
        if (this.channeldev == null) {
            this.channeldev = new DeviceProperties(this.mCtx);
            int channelId = BtUtils.getBtChannelId(this.mCtx);
            if (channelId != -1) {
                this.channeldev.channelId = "" + channelId;
            }
        }
        BtsdkLog.m1423d("mDeviceProperties.channelId = " + this.channeldev.channelId);
        return getMgson().toJson((Object) this.channeldev);
    }

    public Gson getMgson() {
        return OkHttpUtil.getInstance(this.mCtx).getGson();
    }
}
