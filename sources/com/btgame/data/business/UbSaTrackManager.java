package com.btgame.data.business;

import android.content.Context;
import com.baitian.util.FileUtil;
import com.btgame.data.constants.Constants;
import com.btgame.seasdk.btcore.common.util.BTResourceUtil;
import com.btgame.seasdk.btcore.common.util.BtUtils;
import com.btgame.seasdk.btcore.common.util.BtsdkLog;
import com.sensorsdata.analytics.android.sdk.SensorsDataAPI;
import com.sensorsdata.analytics.android.sdk.SensorsDataAPI.DebugMode;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class UbSaTrackManager {
    private static final String SA_CONFIGURE_URL = "";
    private static final String SA_DEV_ENABLE = "sa_dev_enable";
    public static final String SA_SUBMIT_EVENT_DATA_REQUESTID = "/mobilePusher.action";
    private static UbSaTrackManager instance;
    private static Context mContext;
    private DebugMode SA_DEBUG_MODE = DebugMode.DEBUG_OFF;

    public static UbSaTrackManager getInstance(Context context) {
        if (instance == null) {
            mContext = context;
            instance = new UbSaTrackManager(context);
        }
        return instance;
    }

    private UbSaTrackManager(Context ctx) {
        FileUtil.init(ctx);
        initSensorsDataAPI(ctx);
    }

    private void initSensorsDataAPI(Context ctx) {
        String sa_serverUrl;
        String str = "";
        if (BtUtils.getbooleanMeTaDataFalse(ctx, SA_DEV_ENABLE)) {
            sa_serverUrl = new StringBuilder(Constants.URL_DEV_HOST).append(SA_SUBMIT_EVENT_DATA_REQUESTID).toString();
        } else {
            sa_serverUrl = new StringBuilder(Constants.getInstance().getReleaseURL()).append(SA_SUBMIT_EVENT_DATA_REQUESTID).toString();
        }
        BtsdkLog.m1429d("sa_serverUrl = " + sa_serverUrl);
        SensorsDataAPI.sharedInstance(ctx, sa_serverUrl, "", this.SA_DEBUG_MODE);
    }

    public void saTrackEvent(String eventId, boolean isFlush) {
        try {
            int saEvent = Integer.parseInt(eventId);
            BtsdkLog.m1429d("saEvent Id  = " + saEvent);
            SensorsDataAPI.sharedInstance().track(String.valueOf(saEvent), isFlush);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saTrackEvent(String eventId, Map<String, String> extMap, boolean isFlush) {
        try {
            int saEvent = Integer.parseInt(eventId);
            BtsdkLog.m1429d("saEvent Id  = " + saEvent);
            if (extMap == null || extMap.size() <= 0) {
                SensorsDataAPI.sharedInstance().track(String.valueOf(saEvent), isFlush);
            } else {
                SensorsDataAPI.sharedInstance().track(String.valueOf(saEvent), (Map) extMap, isFlush);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void sensorsTrack(String eventId, boolean isFlush) {
        BtsdkLog.m1429d("sensorsTrack Id  = " + eventId);
        HashMap<String, String> map = new HashMap<>();
        map.put("dataAppId", String.valueOf(BTResourceUtil.findIntegerByName(mContext, "btDataAppId")));
        map.put("platformId", String.valueOf(BTResourceUtil.findIntegerByName(mContext, "btDataPlatformId")));
        map.put("platformIdSecond", String.valueOf(BTResourceUtil.findIntegerByName(mContext, "pay_packageid")));
        SensorsDataAPI.sharedInstance().track(eventId, (Map) map, isFlush);
    }

    public static void sensorsTrack(String eventId, Map properties, boolean isFlush) {
        BtsdkLog.m1429d("sensorsTrack Id  = " + eventId);
        if (properties == null) {
            properties = new HashMap();
        }
        if (!properties.containsKey("dataAppId")) {
            properties.put("dataAppId", String.valueOf(BTResourceUtil.findIntegerByName(mContext, "btDataAppId")));
        }
        if (!properties.containsKey("platformId")) {
            properties.put("platformId", String.valueOf(BTResourceUtil.findIntegerByName(mContext, "btDataPlatformId")));
        }
        if (!properties.containsKey("platformIdSecond")) {
            properties.put("platformIdSecond", String.valueOf(BTResourceUtil.findIntegerByName(mContext, "pay_packageid")));
        }
        SensorsDataAPI.sharedInstance().track(eventId, properties, isFlush);
    }

    public static void sensorsTrack(String eventId, String properties, boolean isFlush) {
        BtsdkLog.m1429d("sensorsTrack Id  = " + eventId);
        SensorsDataAPI.sharedInstance().track(eventId, properties, isFlush);
    }

    public static void sensorsTrack(String eventId, JSONObject properties, boolean isFlush) {
        BtsdkLog.m1429d("sensorsTrack Id  = " + eventId);
        SensorsDataAPI.sharedInstance().track(eventId, properties, isFlush);
    }

    public static void trackEvent(String eventId, Map<String, String> properties) {
        sensorsTrack(eventId, (Map) properties, true);
    }
}
