package com.baitian.datasdk.business;

import android.content.Context;
import android.text.TextUtils;
import com.baitian.datasdk.constants.Constants;
import com.baitian.datasdk.constants.Event;
import com.baitian.datasdk.constants.RequestUrl;
import com.baitian.datasdk.eneity.CommonResult;
import com.baitian.datasdk.eneity.EventData.EventAccountBody;
import com.baitian.datasdk.eneity.EventData.EventBaseBody;
import com.baitian.datasdk.eneity.EventData.EventGameRoleBody;
import com.baitian.datasdk.eneity.EventData.EventHeader;
import com.baitian.datasdk.http.NetworkUtils;
import com.baitian.datasdk.http.OkHttpCallBack;
import com.baitian.datasdk.http.OkHttpUtil;
import com.baitian.datasdk.util.ACache;
import com.baitian.datasdk.util.BtUtils;
import com.baitian.datasdk.util.BtsdkLog;
import com.baitian.datasdk.util.DebugUtils;
import com.baitian.datasdk.util.RunningType;
import com.google.gson.Gson;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import p004cn.jiguang.net.HttpUtils;

public class EventManager {
    private static final String TOPIC_ID = "17";
    private static final String TOPIC_NAME = "ods_event_record";
    private static EventManager instance;
    private ACache mCache;
    private Context mCtx;
    private Gson mGson;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINESE);
    private long timeDifference = Long.MIN_VALUE;
    private TimeZone timeZone = TimeZone.getTimeZone("GMT+8:00");

    public static EventManager getInstance(Context context) {
        if (instance == null) {
            instance = new EventManager(context);
        }
        return instance;
    }

    private EventManager(Context context) {
        this.mCtx = context;
        this.mGson = new Gson();
        this.mCache = ACache.get(this.mCtx);
        DebugUtils.getInstance().setCodeFlag(true);
        switch (BtUtils.getIntNoXMeTaData(this.mCtx, "debugconfig")) {
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

    private void submitEvent(final EventBaseBody eventBaseBody) {
        if (eventBaseBody.eventId.equals(Event.EVENT_ID_SDK_INIT) || eventBaseBody.eventId.equals(Event.EVENT_ID_INIT_SUCCEED)) {
            eventBaseBody.logTime = getLocalTime();
            eventBaseBody.enterTime = eventBaseBody.logTime;
            submitEventDataToServer(eventBaseBody);
        } else if (this.timeDifference != Long.MIN_VALUE) {
            eventBaseBody.logTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis() - this.timeDifference));
            eventBaseBody.enterTime = eventBaseBody.logTime;
            submitEventDataToServer(eventBaseBody);
        } else {
            OkHttpUtil.getInstance(this.mCtx).postJsonDataAsynNormalReturnString(new StringBuilder(Constants.getInstance().getURL(this.mCtx)).append(RequestUrl.GET_SERVER_TIME).toString(), "", new OkHttpCallBack<Object>() {
                public void onFailure() {
                    BtsdkLog.m1417d("get server time failed");
                    eventBaseBody.logTime = EventManager.this.getLocalTime();
                    eventBaseBody.enterTime = eventBaseBody.logTime;
                    EventManager.this.submitEventDataToServer(eventBaseBody);
                }

                public void onResponse(Object g) {
                    try {
                        long time = Long.parseLong((String) g);
                        EventManager.this.compareTime(time);
                        Date serverTime = new Date(time);
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        eventBaseBody.logTime = dateFormat.format(serverTime);
                        eventBaseBody.enterTime = eventBaseBody.logTime;
                    } catch (Exception e) {
                        e.printStackTrace();
                        eventBaseBody.logTime = EventManager.this.getLocalTime();
                        eventBaseBody.enterTime = eventBaseBody.logTime;
                    }
                    EventManager.this.submitEventDataToServer(eventBaseBody);
                }
            }, CommonResult.class);
        }
    }

    /* access modifiers changed from: private */
    public void compareTime(long time) {
        this.timeDifference = System.currentTimeMillis() - time;
    }

    /* access modifiers changed from: private */
    public void submitEventDataToServer(EventBaseBody eventBaseBody) {
        submitEventDataToServer(Constants.KEY_PREFIX_EVENT + eventBaseBody.logTime, this.mGson.toJson((Object) new EventBaseBody[]{eventBaseBody}), false);
    }

    private void submitEventDataToServer(final String saveKey, final String reqJson, final boolean isSendFailedData) {
        StringBuilder urlBuild = new StringBuilder();
        urlBuild.append(Constants.getInstance().getURL(this.mCtx));
        urlBuild.append(RequestUrl.SUBMIT_EVENT_DATA);
        urlBuild.append(HttpUtils.URL_AND_PARA_SEPARATOR);
        urlBuild.append(EventHeader.getInstance(this.mCtx).toString());
        String url = urlBuild.toString();
        BtsdkLog.m1417d("submit event url:" + url);
        BtsdkLog.m1417d("reqJson:" + reqJson);
        OkHttpUtil.getInstance(this.mCtx).postJsonDataAsynNormal(url, reqJson, new OkHttpCallBack<Object>() {
            public void onFailure() {
                BtsdkLog.m1417d("event submit failed");
                EventManager.this.saveData(saveKey, reqJson);
            }

            public void onResponse(Object g) {
                BtsdkLog.m1417d("event submit succeed");
                if (isSendFailedData) {
                    EventManager.this.removeData(saveKey);
                }
            }
        }, CommonResult.class);
    }

    /* access modifiers changed from: private */
    public String getLocalTime() {
        this.simpleDateFormat.setTimeZone(this.timeZone);
        return this.simpleDateFormat.format(new Date());
    }

    private EventBaseBody generateEvent(EventBaseBody eventBaseBody, String eventId, String colA, String colB, String colC) {
        if (eventBaseBody == null) {
            eventBaseBody = new EventBaseBody();
        }
        eventBaseBody.topicId = TOPIC_ID;
        eventBaseBody.topicName = TOPIC_NAME;
        eventBaseBody.eventId = eventId;
        String networkInfo = NetworkUtils.getNetworkTypeName(this.mCtx);
        if (networkInfo == null) {
            networkInfo = "unknown";
        }
        eventBaseBody.networkInfo = networkInfo;
        if (colA != null) {
            eventBaseBody.colA = colA;
        }
        if (colB != null) {
            eventBaseBody.colB = colB;
        }
        if (colC != null) {
            eventBaseBody.colC = colC;
        }
        return eventBaseBody;
    }

    public void submitBaseEvent(String eventId, String colA, String colB, String colC) {
        submitEvent(generateEvent(new EventBaseBody(), eventId, colA, colB, colC));
    }

    public void submitAccountEvent(String eventId, String accountId, String colA, String colB, String colC) {
        submitEvent(generateEvent(new EventAccountBody(accountId), eventId, colA, colB, colC));
    }

    public void submitGameRoleEvent(String eventId, String accountId, String serverId, String serverName, String roleId, String roleLevel, String colA, String colB, String colC) {
        submitEvent(generateEvent(new EventGameRoleBody(accountId, serverId, serverName, roleId, roleLevel), eventId, colA, colB, colC));
    }

    public void postFaildataByHttp(String key, String json) {
        if (TextUtils.isEmpty(key)) {
            BtsdkLog.m1417d("key = null");
        } else if (!TextUtils.isEmpty(json)) {
            submitEventDataToServer(key, json, true);
        } else {
            BtsdkLog.m1417d("json = null");
        }
    }

    /* access modifiers changed from: private */
    public void saveData(String saveKey, String datajson) {
        BtsdkLog.m1417d("savaeData- saveKey = " + saveKey);
        this.mCache.put(saveKey, datajson, 259200000);
    }

    /* access modifiers changed from: private */
    public void removeData(String saveKey) {
        BtsdkLog.m1417d("removeData- saveKey = " + saveKey);
        this.mCache.remove(saveKey);
    }
}
