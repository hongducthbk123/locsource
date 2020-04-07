package com.baitian.datasdk.constants;

import java.util.HashMap;
import java.util.Map;

public class TopicField {
    public static final int TOPICID_ENTER_GAME = 15;
    public static final int TOPICID_ENTER_SERVER = 13;
    public static final int TOPICID_GAME_LOGIN_PAGE = 12;
    public static final int TOPICID_PLATFORM_INIT = 8;
    public static final int TOPICID_PLATFORM_INIT_SUCCESS = 9;
    public static final int TOPICID_PRELOADING = 10;
    public static final int TOPICID_SDK_LOGIN_PAGE = 11;
    public static final int TOPICID_SPLASH = 7;
    public static final int TOPICID_START_LOADING = 14;
    public static final String TOPICINAME_ENTER_GAME = "ods_enter_game";
    public static final String TOPICINAME_ENTER_SERVER = "ods_enter_server";
    public static final String TOPICINAME_GAME_LOGIN_PAGE = "ods_game_login_page";
    public static final String TOPICINAME_PLATFORM_INIT = "ods_platform_init";
    public static final String TOPICINAME_PLATFORM_INIT_SUCCESS = "ods_platform_init_success";
    public static final String TOPICINAME_PRELOADING = "ods_preloading";
    public static final String TOPICINAME_SDK_LOGIN_PAGE = "ods_sdk_login_page";
    public static final String TOPICINAME_SPLASH = "ods_splash_screen";
    public static final String TOPICINAME_START_LOADING = "ods_loading";
    private static final int[] ids = {7, 8, 9, 10, 11, 12, 13, 14, 15};
    private static final String[] topicNames = {TOPICINAME_SPLASH, TOPICINAME_PLATFORM_INIT, TOPICINAME_PLATFORM_INIT_SUCCESS, TOPICINAME_PRELOADING, TOPICINAME_SDK_LOGIN_PAGE, TOPICINAME_GAME_LOGIN_PAGE, TOPICINAME_ENTER_SERVER, TOPICINAME_START_LOADING, TOPICINAME_ENTER_GAME};

    public Map<Integer, String> getTopicsMap() {
        Map<Integer, String> topicsMap = new HashMap<>();
        for (int i = 0; i < ids.length; i++) {
            topicsMap.put(Integer.valueOf(ids[i]), topicNames[i]);
        }
        return topicsMap;
    }
}
