package com.btgame.appevents.constant;

import android.support.p000v4.util.ArrayMap;
import com.btgame.onesdk.PlatfromUtils;
import com.btgame.seasdk.btcore.common.util.BTResourceUtil;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AppEventsConfig {
    public static final String PT_ADJUST_FLAG = "Adjust";
    public static int btplatformId = 0;
    public static final String ev_ActiveNew = "ActiveNew";
    public static final String ev_Registration = "Registration";
    public static final String ev_activateApp = "activateApp";
    public static ArrayMap<String, String> ev_events_tokens = null;
    public static final String ev_guestLogin = "guest_login";
    public static final String ev_init_ub_sdk = "init_ub_sdk";
    public static final String ev_newRole_pack = "newRole_pack";
    public static final String ev_newRole_tutorial = "newRole_tutorial";
    public static boolean isLogEvent;
    private static String lvPrefix = "lv";

    public static void initConfig() {
        isLogEvent = BTResourceUtil.findBoolByName("logevent_flag");
        btplatformId = BTResourceUtil.getApplicationMetaIntData(PlatfromUtils.PLATFROMID_KEY);
        String[] lv_events = BTResourceUtil.findStringArrayByName("ev_lv_events");
        ev_events_tokens = new ArrayMap<>();
        if (lv_events.length != 0) {
            Matcher matcher = Pattern.compile("\\d+").matcher(lv_events[0]);
            if (matcher.find()) {
                lvPrefix = lv_events[0].substring(0, matcher.start());
            }
            for (int i = 0; i < lv_events.length; i += 2) {
                ev_events_tokens.put(lv_events[i], lv_events[i + 1]);
            }
        }
        String[] ev_n_days_login = BTResourceUtil.findStringArrayByName("ev_n_days_login");
        if (ev_n_days_login.length != 0) {
            for (int i2 = 0; i2 < ev_n_days_login.length; i2 += 2) {
                ev_events_tokens.put(ev_n_days_login[i2], ev_n_days_login[i2 + 1]);
            }
        }
        ev_events_tokens.put(ev_Registration, BTResourceUtil.findStringByName("ev_Registration"));
        ev_events_tokens.put(ev_newRole_tutorial, BTResourceUtil.findStringByName("ev_newRole_tutorial"));
        ev_events_tokens.put(ev_newRole_pack, BTResourceUtil.findStringByName("ev_newRole_pack"));
        ev_events_tokens.put(ev_ActiveNew, BTResourceUtil.findStringByName("ev_ActiveNew"));
        ev_events_tokens.put(ev_activateApp, BTResourceUtil.findStringByName("adjust_app_token"));
        ev_events_tokens.put(ev_guestLogin, BTResourceUtil.findStringByName("ev_guest_login"));
        ev_events_tokens.put(ev_init_ub_sdk, BTResourceUtil.findStringByName("ev_init_ub_sdk"));
    }

    public static String buildLvEventName(int lv) {
        return lvPrefix + lv;
    }

    public static String buildNDaysLoginEventName(int day) {
        return 1 == day ? "next_day_login" : day + "_days_login";
    }

    public static String getEventToken(String eventName) {
        return (String) ev_events_tokens.get(eventName);
    }
}
