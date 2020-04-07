package com.baitian.datasdk.business;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import com.baitian.datasdk.constants.Constants;
import com.baitian.datasdk.constants.RequestUrl;
import com.baitian.datasdk.constants.TopicField;
import com.baitian.datasdk.eneity.AccountInfoData;
import com.baitian.datasdk.eneity.BaseDataField;
import com.baitian.datasdk.eneity.CommonResult;
import com.baitian.datasdk.eneity.DeviceBaseData;
import com.baitian.datasdk.eneity.GameRoleInfoData;
import com.baitian.datasdk.eneity.requestData.BaseRequestData;
import com.baitian.datasdk.http.NetworkUtils;
import com.baitian.datasdk.http.OkHttpCallBack;
import com.baitian.datasdk.http.OkHttpUtil;
import com.baitian.datasdk.util.ACache;
import com.baitian.datasdk.util.BtUtils;
import com.baitian.datasdk.util.BtsdkLog;
import com.baitian.datasdk.util.DebugUtils;
import com.baitian.datasdk.util.RunningType;
import com.google.gson.Gson;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import p004cn.jiguang.net.HttpUtils;

public class DataManager {
    private static DataManager instance;
    private Gson gson;
    private ACache mCache;
    /* access modifiers changed from: private */
    public Context mCtx;
    private Map<Integer, String> topicsMap;

    public static DataManager getInstance(Context context) {
        if (instance == null) {
            instance = new DataManager(context);
        }
        return instance;
    }

    private DataManager(Context ctx) {
        this.mCtx = ctx;
        this.gson = OkHttpUtil.getInstance(ctx).getGson();
        DebugUtils.getInstance().setCodeFlag(true);
        switch (BtUtils.getIntNoXMeTaData(this.mCtx, "debugconfig")) {
            case 1:
                DebugUtils.getInstance().setDebug(RunningType.YangCaoServer);
                break;
            case 2:
                DebugUtils.getInstance().setDebug(RunningType.Developer);
                break;
            default:
                DebugUtils.getInstance().setDebug(RunningType.Released);
                break;
        }
        this.mCache = ACache.get(this.mCtx);
        this.topicsMap = new TopicField().getTopicsMap();
    }

    public void sumbitData(int topicId, String topicName, AccountInfoData accountInfoData) {
        BtsdkLog.m1417d("----------------->submitData accountInfoData===");
        if (accountInfoData == null) {
            Log.e(BtsdkLog.TAG, "gameInfoData == null , Please check the data ");
        } else if (accountInfoData.getAccountId() == null || "".equals(accountInfoData.getAccountId())) {
            Log.e(BtsdkLog.TAG, "gameInfoData == null , Please check the data ");
        } else {
            accountInfoData.dataAppId = BtUtils.getIntNoXMeTaData(this.mCtx, "btgameId");
            accountInfoData.topicId = topicId;
            accountInfoData.topicName = topicName;
            getServerTimeAndUploadData(accountInfoData);
        }
    }

    public void sumbitData(int topicId, String topicName, GameRoleInfoData gameRoleInfoData) {
        if (checkTheGameInfoData(gameRoleInfoData)) {
            BtsdkLog.m1417d("----------------->sumbitData gameRoleInfoData ===");
            gameRoleInfoData.dataAppId = BtUtils.getIntNoXMeTaData(this.mCtx, "btgameId");
            gameRoleInfoData.topicId = topicId;
            gameRoleInfoData.topicName = topicName;
            getServerTimeAndUploadData(gameRoleInfoData);
        }
    }

    public void sumbitData(int topicId, String topicName) {
        BtsdkLog.m1417d("----------------->sumbitData  === topicId = " + topicId);
        DeviceBaseData baseData = new DeviceBaseData(this.mCtx);
        baseData.dataAppId = BtUtils.getIntNoXMeTaData(this.mCtx, "btgameId");
        baseData.networkInfo = NetworkUtils.getNetworkTypeName(this.mCtx);
        if (baseData.networkInfo == null) {
            baseData.networkInfo = "unknown";
        }
        baseData.topicId = topicId;
        baseData.topicName = topicName;
        if (checkTheBaseData(baseData)) {
            getServerTimeAndUploadData(baseData);
        }
    }

    private void getServerTimeAndUploadData(final BaseDataField dataField) {
        OkHttpUtil.getInstance(this.mCtx).postJsonDataAsynNormalReturnString(new StringBuilder(Constants.getInstance().getURL(this.mCtx)).append(RequestUrl.GET_SERVER_TIME).toString(), "", new OkHttpCallBack<Object>() {
            public void onFailure() {
                BtsdkLog.m1417d("getserverTime -->Fail");
                DataManager.this.getLocalTime(dataField);
            }

            public void onResponse(Object g) {
                try {
                    DataManager.this.sumbitDataToServer(DataManager.this.timeFieldAdapter(dataField.topicId, dataField, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(Long.parseLong((String) g)))));
                } catch (Exception e) {
                    DataManager.this.getLocalTime(dataField);
                }
            }
        }, CommonResult.class);
    }

    /* access modifiers changed from: private */
    public void getLocalTime(BaseDataField dataField) {
        sumbitDataToServer(timeFieldAdapter(dataField.topicId, dataField, new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(Calendar.getInstance().getTime())));
    }

    /* access modifiers changed from: private */
    public BaseDataField timeFieldAdapter(int topicId, BaseDataField dataField, String currentTime) {
        switch (topicId) {
            case 7:
                dataField.splashScreenTime = currentTime;
                break;
            case 8:
                dataField.initTime = currentTime;
                break;
            case 9:
                dataField.initSuccessTime = currentTime;
                break;
            case 10:
                dataField.preloadingTime = currentTime;
                break;
            case 11:
                dataField.inSdkLoginPageTime = currentTime;
                break;
            case 12:
                dataField.inGameLoginPageTime = currentTime;
                break;
            case 13:
                dataField.enterTime = currentTime;
                break;
            case 14:
                dataField.loadingTime = currentTime;
                break;
            case 15:
                dataField.enterTime = currentTime;
                break;
            default:
                dataField.time = currentTime;
                break;
        }
        dataField.logTime = currentTime;
        return dataField;
    }

    /* access modifiers changed from: private */
    public void sumbitDataToServer(BaseDataField dataField) {
        BtsdkLog.m1417d("----------------->sumbitDataToServer  ===   topicId = " + dataField.topicId);
        BaseRequestData bqd = new BaseRequestData();
        bqd.setData(dataField);
        try {
            bqd.setSign(getSignByData(dataField.topicId, dataField));
            String json = this.gson.toJson((Object) bqd);
            BtsdkLog.m1417d("json = " + json);
            if (!NetworkUtils.isNetworkConnected(this.mCtx)) {
                saveData(dataField.logTime, json);
                Log.i(BtsdkLog.TAG, "Network is broken");
                return;
            }
            postDataByHttp(Constants.KEY_PREFIX_DATA + dataField.logTime, dataField, json);
        } catch (JSONException e) {
            BtsdkLog.m1418e("getSign error , msg = " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void postDataByHttp(final String saveKey, BaseDataField dataField, final String json) {
        OkHttpUtil.getInstance(this.mCtx).postJsonDataAsynNormal(new StringBuilder(Constants.getInstance().getURL(this.mCtx)).append(RequestUrl.SUMBIT_DATA).toString(), json, new OkHttpCallBack<Object>() {
            public void onFailure() {
                BtsdkLog.m1417d("onFailure------");
                DataManager.this.saveData(saveKey, json);
            }

            public void onResponse(Object g) {
                BtsdkLog.m1417d("onResponse------");
                if (DebugUtils.getInstance().isLogFlag()) {
                    Toast.makeText(DataManager.this.mCtx, "数据上传成功", 1).show();
                }
                BtsdkLog.m1417d("数据上传成功------");
                DataManager.this.removeData(saveKey);
            }
        }, CommonResult.class);
    }

    public void postFaildataByHttp(final String key, final String json) {
        if (TextUtils.isEmpty(key)) {
            BtsdkLog.m1417d("key = null");
        } else if (TextUtils.isEmpty(json)) {
            BtsdkLog.m1417d("json = null");
        } else {
            OkHttpUtil.getInstance(this.mCtx).postJsonDataAsynNormal(new StringBuilder(Constants.getInstance().getURL(this.mCtx)).append(RequestUrl.SUMBIT_DATA).toString(), json, new OkHttpCallBack<Object>() {
                public void onFailure() {
                    BtsdkLog.m1417d("onFailure------");
                    DataManager.this.saveData(key, json);
                }

                public void onResponse(Object g) {
                    BtsdkLog.m1417d("onResponse------");
                    BtsdkLog.m1417d("失败数据上传成功------");
                    DataManager.this.removeData(key);
                }
            }, CommonResult.class);
        }
    }

    public String getSignByData(int topicId, BaseDataField dataField) throws JSONException {
        String dataJson = this.gson.toJson((Object) dataField);
        BtsdkLog.m1417d("dataJson = " + dataJson);
        JSONObject jsonMap = new JSONObject(dataJson);
        Iterator<String> it = jsonMap.keys();
        Map<String, Object> dataMap = new HashMap<>();
        while (it.hasNext()) {
            String key = (String) it.next();
            dataMap.put(key, jsonMap.get(key));
        }
        List<String> list = new ArrayList<>(dataMap.keySet());
        Collections.sort(list);
        StringBuilder jsonMapSB = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            jsonMapSB.append((String) list.get(i)).append(HttpUtils.EQUAL_SIGN).append(dataMap.get(list.get(i))).append("|");
        }
        StringBuilder signCode = new StringBuilder("baiao_mobile_client_");
        signCode.append(topicId);
        jsonMapSB.append(signCode);
        BtsdkLog.m1417d("jsonMapSB = " + jsonMapSB.toString());
        return BtUtils.getMD5Str(jsonMapSB.toString());
    }

    private boolean checkTheBaseData(DeviceBaseData baseData) {
        Field[] declaredFields;
        boolean isNotIlleage = true;
        if (baseData != null) {
            for (Field f : baseData.getClass().getDeclaredFields()) {
                try {
                    if (f.get(baseData) == null) {
                        Log.e(BtsdkLog.TAG, " ! 缺少 " + f.getName() + " 字段");
                        Log.e(BtsdkLog.TAG, " class = baseData");
                        isNotIlleage = false;
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    isNotIlleage = false;
                }
            }
        }
        return isNotIlleage;
    }

    private boolean checkTheGameInfoData(GameRoleInfoData gameRoleInfoData) {
        if (gameRoleInfoData == null) {
            Log.e(BtsdkLog.TAG, new NullPointerException("gameRoleInfoData == null , Please check the data ").getMessage());
            return false;
        } else if (TextUtils.isEmpty(gameRoleInfoData.getAccountId())) {
            Log.e(BtsdkLog.TAG, new NullPointerException("accountId = null").getMessage());
            return false;
        } else {
            BtsdkLog.m1417d("getVIPLevel = " + gameRoleInfoData.getVIPLevel());
            boolean isNotIlleage = true;
            Field[] declaredFields = gameRoleInfoData.getClass().getDeclaredFields();
            int length = declaredFields.length;
            int i = 0;
            while (i < length) {
                Field f = declaredFields[i];
                f.setAccessible(true);
                try {
                    if (f.get(gameRoleInfoData) == null) {
                        Log.e(BtsdkLog.TAG, " ! 缺少 " + f.getName() + " 字段");
                        isNotIlleage = false;
                    }
                    i++;
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    return false;
                }
            }
            return isNotIlleage;
        }
    }

    /* access modifiers changed from: private */
    public void saveData(String saveKey, String dataJson) {
        BtsdkLog.m1417d("savaeData- saveKey = " + saveKey);
        this.mCache.put(saveKey, dataJson, 259200000);
    }

    /* access modifiers changed from: private */
    public void removeData(String saveKey) {
        BtsdkLog.m1417d("removeData- saveKey = " + saveKey);
        this.mCache.remove(saveKey);
    }
}
