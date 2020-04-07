package com.btgame.seaui.p045ui.constant;

import java.util.ArrayList;
import java.util.List;

/* renamed from: com.btgame.seaui.ui.constant.UIConfig */
public class UIConfig {
    public static final String LOGIN_GP_FLAG = "GP";
    public static final String PT_FB_FLAG = "FB";
    private static List<String> loginList = new ArrayList();

    public static boolean contains(String str) {
        return loginList.isEmpty() || loginList.contains(str);
    }

    public static void setLoginConfig(List<String> loginConfig) {
        if (loginConfig == null || loginConfig.isEmpty()) {
            loginList.clear();
        } else {
            loginList.addAll(loginConfig);
        }
    }
}
