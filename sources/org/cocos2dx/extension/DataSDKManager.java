package org.cocos2dx.extension;

import android.content.Context;
import android.util.Log;
import com.baitian.datasdk.BtDataSdkManager;
import com.baitian.datasdk.eneity.AccountInfoData;
import com.baitian.datasdk.eneity.GameRoleInfoData;
import org.json.JSONException;
import org.json.JSONObject;

public class DataSDKManager {
    private static String m_accountId = "";
    private static Context m_context = null;

    public static void setContext(Context ctx) {
        m_context = ctx;
    }

    public static void setAccountId(String accountId) {
        m_accountId = accountId;
    }

    public static void sumbitBaseData(int topicId, String topicName) {
        BtDataSdkManager.getInstance(m_context).sumbitBaseData(topicId, topicName);
    }

    public static void sumbitAccountData(int topicId, String topicName) {
        AccountInfoData accountInfoData = new AccountInfoData(m_context);
        accountInfoData.setAccountId(m_accountId);
        BtDataSdkManager.getInstance(m_context).sumbitAccountData(topicId, topicName, accountInfoData);
    }

    public static void sumbitGameInfoData(int topicId, String topicName, String jsonData) {
        int areaId = 0;
        String areaName = null;
        int VIPLevel = 0;
        long charId = 0;
        int basicAccount = 0;
        int presentAccount = 0;
        long userId = 0;
        int userGender = 0;
        int userLevel = 0;
        String userName = null;
        String roleRegTime = null;
        String userBirthday = null;
        try {
            JSONObject json = new JSONObject(jsonData);
            areaId = json.optInt("areaId");
            areaName = json.optString("areaName");
            VIPLevel = json.optInt("VIPLevel");
            charId = json.optLong("charId");
            basicAccount = json.optInt("basicAccount");
            presentAccount = json.optInt("presentAccount");
            userId = json.optLong("userId");
            userGender = json.optInt("userGender");
            userLevel = json.optInt("userLevel");
            userName = json.optString("userName");
            roleRegTime = json.optString("roleRegTime");
            userBirthday = json.optString("userBirthday");
        } catch (JSONException e) {
            Log.e("DataSDKManager", "Get jsonData JSON error!");
        }
        GameRoleInfoData gameRoleInfoData = new GameRoleInfoData(m_context);
        gameRoleInfoData.setAccountId(m_accountId);
        gameRoleInfoData.setAreaId(areaId);
        gameRoleInfoData.setAreaName(areaName);
        if (VIPLevel > 0) {
            gameRoleInfoData.setIsVIP(1);
        } else {
            gameRoleInfoData.setIsVIP(0);
        }
        gameRoleInfoData.setVIPLevel(VIPLevel);
        gameRoleInfoData.setCharId(charId);
        gameRoleInfoData.setBasicAccount((long) basicAccount);
        gameRoleInfoData.setPresentAccount((long) presentAccount);
        gameRoleInfoData.setUserId(userId);
        gameRoleInfoData.setUserGender(userGender);
        gameRoleInfoData.setUserLevel(userLevel);
        gameRoleInfoData.setUserName(userName);
        gameRoleInfoData.setRoleRegTime(roleRegTime);
        gameRoleInfoData.setUserBirthday(userBirthday);
        BtDataSdkManager.getInstance(m_context).sumbitGameInfoData(topicId, topicName, gameRoleInfoData);
    }
}
