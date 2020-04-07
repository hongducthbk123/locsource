package com.baitian.datasdk;

import android.content.Context;
import android.text.TextUtils;
import com.baitian.datasdk.business.SaTrackManager;
import com.baitian.datasdk.eneity.AccountInfoData;
import com.baitian.datasdk.eneity.GameRoleInfoData;
import com.baitian.datasdk.util.ACache;
import com.baitian.datasdk.util.BtUtils;
import com.baitian.datasdk.util.BtsdkLog;
import com.baitian.datasdk.util.ContextUtil;
import com.baitian.datasdk.util.FileUtil;
import java.util.HashMap;
import java.util.Map;

public class BtDataSdkManager {
    private static final String UPDATA_FLAG_KEY = "bt_upload_data";
    private static BtDataSdkManager instance;
    private ACache mCache = ACache.get(this.mCtx);
    private Context mCtx;
    private SaTrackManager saTrackManager = SaTrackManager.getInstance(this.mCtx);

    private BtDataSdkManager(Context ctx) {
        this.mCtx = ctx;
        ContextUtil.init(ctx);
        FileUtil.init(ctx);
    }

    public static BtDataSdkManager getInstance(Context ctx) {
        if (instance == null) {
            instance = new BtDataSdkManager(ctx);
        }
        return instance;
    }

    public void sumbitBaseData(int topicId, String topicName) {
        if (!BtUtils.getbooleanMeTaData(this.mCtx, UPDATA_FLAG_KEY)) {
        }
    }

    public void sumbitAccountData(int topicId, String topicName, AccountInfoData accountInfoData) {
        if (!BtUtils.getbooleanMeTaData(this.mCtx, UPDATA_FLAG_KEY)) {
        }
    }

    public void sumbitGameInfoData(int topicId, String topicName, GameRoleInfoData gameRoleInfoData) {
        if (!BtUtils.getbooleanMeTaData(this.mCtx, UPDATA_FLAG_KEY)) {
        }
    }

    public void submitBaseEventData(String eventId, String colA, String colB, String colC) {
        if (BtUtils.getbooleanMeTaData(this.mCtx, UPDATA_FLAG_KEY)) {
            BtsdkLog.m1417d("eventId = " + eventId);
            Map<String, String> stringMap = new HashMap<>();
            if (!TextUtils.isEmpty(colA)) {
                stringMap.put("colA", colA);
            }
            if (!TextUtils.isEmpty(colB)) {
                stringMap.put("colB", colB);
            }
            if (!TextUtils.isEmpty(colB)) {
                stringMap.put("colB", colB);
            }
            this.saTrackManager.saTrackEvent(eventId, stringMap, true);
        }
    }

    public void submitAccountEventData(String eventId, String accountId, String colA, String colB, String colC) {
        BtsdkLog.m1417d("eventId = " + eventId);
        if (BtUtils.getbooleanMeTaData(this.mCtx, UPDATA_FLAG_KEY)) {
            this.saTrackManager.saTrackEvent(eventId, true);
        }
    }

    public void submitRoleEventData(String eventId, String accountId, String serverId, String serverName, String roleId, String roleLevel, String colA, String colB, String colC) {
        BtsdkLog.m1417d("eventId = " + eventId);
        if (BtUtils.getbooleanMeTaData(this.mCtx, UPDATA_FLAG_KEY)) {
            this.saTrackManager.saTrackEvent(eventId, true);
        }
    }

    public void destory() {
        this.mCtx = null;
        instance = null;
    }
}
