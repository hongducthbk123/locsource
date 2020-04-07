package com.baitian.datasdk.business;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.baitian.datasdk.constants.Constants;
import com.baitian.datasdk.util.BtUtils;
import com.baitian.datasdk.util.BtsdkLog;
import com.baitian.datasdk.util.ContextUtil;
import com.baitian.datasdk.util.DebugUtils;
import com.baitian.datasdk.util.RunningType;
import com.sensorsdata.analytics.android.sdk.SensorsDataAPI;
import com.sensorsdata.analytics.android.sdk.SensorsDataAPI.DebugMode;
import java.util.Map;
import org.json.JSONObject;

public class SaTrackManager {
    private static final int DEV_RUNNING_TYPE = 1;
    private static final int RELEASE_RUNNING_TYPE = 3;
    private static final String SA_SUBMIT_EVENT_DATA_REQUESTID = "/mobilePusher.action";
    private static final int SDK_EVENTID_MAX = 200000;
    private static final int SDK_EVENTID_MIN = 100000;
    private static final int SDK_SA_ADDING_NUMBER = 0;
    private static final int TEST_RUNNING_TYPE = 2;
    private static SaTrackManager instance;

    public static SaTrackManager getInstance(Context context) {
        if (instance == null) {
            instance = new SaTrackManager(context);
        }
        return instance;
    }

    private SaTrackManager(Context ctx) {
        ContextUtil.init(ctx);
        intRunningType(ctx);
        initSensorsDataAPI(ctx);
    }

    private void intRunningType(Context ctx) {
        DebugUtils.getInstance().setCodeFlag(true);
        switch (BtUtils.getIntNoXMeTaData(ctx, "debugconfig")) {
            case 1:
                DebugUtils.getInstance().setDebug(RunningType.YangCaoServer);
                return;
            case 2:
                DebugUtils.getInstance().setDebug(RunningType.Developer);
                return;
            default:
                DebugUtils.getInstance().setDebug(RunningType.Released);
                return;
        }
    }

    private void initSensorsDataAPI(Context ctx) {
        String saServerUrl = Constants.getInstance().getURL(ctx) + "/mobilePusher.action";
        Log.d(BtsdkLog.TAG, "sa_server_Url = " + saServerUrl);
        SensorsDataAPI.sharedInstance(ctx, saServerUrl, "", DebugMode.DEBUG_OFF);
    }

    public void saTrackEvent(String eventId, boolean isFlush) {
        try {
            int saEvent = Integer.parseInt(eventId);
            if (SDK_EVENTID_MIN <= saEvent && saEvent < SDK_EVENTID_MAX) {
                saEvent += 0;
            }
            BtsdkLog.m1417d("saEvent Id  = " + saEvent);
            SensorsDataAPI.sharedInstance().track(String.valueOf(saEvent), isFlush);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saTrackEvent(String eventId, Map<String, String> extMap, boolean isFlush) {
        try {
            int saEvent = Integer.parseInt(eventId);
            if (SDK_EVENTID_MIN <= saEvent && saEvent < SDK_EVENTID_MAX) {
                saEvent += 0;
            }
            BtsdkLog.m1417d("saEvent Id  = " + saEvent);
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
        if (!TextUtils.isEmpty(eventId)) {
            BtsdkLog.m1417d("sensorsTrack Id  = " + eventId);
            SensorsDataAPI.sharedInstance().track(eventId, isFlush);
        }
    }

    public static void sensorsTrack(String eventId, Map properties, boolean isFlush) {
        if (!TextUtils.isEmpty(eventId)) {
            BtsdkLog.m1417d("sensorsTrack Id  = " + eventId);
            SensorsDataAPI.sharedInstance().track(eventId, properties, isFlush);
        }
    }

    public static void sensorsTrack(String eventId, String properties, boolean isFlush) {
        if (!TextUtils.isEmpty(eventId)) {
            BtsdkLog.m1417d("sensorsTrack Id  = " + eventId);
            SensorsDataAPI.sharedInstance().track(eventId, properties, isFlush);
        }
    }

    public static void sensorsTrack(String eventId, JSONObject properties, boolean isFlush) {
        if (!TextUtils.isEmpty(eventId)) {
            BtsdkLog.m1417d("sensorsTrack Id  = " + eventId);
            SensorsDataAPI.sharedInstance().track(eventId, properties, isFlush);
        }
    }
}
