package com.btgame.webpay.constant;

import android.text.TextUtils;
import com.btgame.seasdk.btcore.common.util.BTResourceUtil;
import com.btgame.seasdk.btcore.common.util.BtsdkLog;
import com.facebook.appevents.AppEventsConstants;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Map.Entry;
import p004cn.jiguang.net.HttpUtils;

public class WebPayConfig {
    public static Integer PAY_PACKAGEID = null;
    public static final String PAY_WEB_FLAG = "webpay";
    private static String webPayUrl;

    public static void initConf() {
        webPayUrl = BTResourceUtil.findStringByName("url_web_pay");
        PAY_PACKAGEID = BTResourceUtil.findIntegerByName("pay_packageid");
        if (PAY_PACKAGEID == null) {
            BtsdkLog.m1430e("请在strings里面配置pay_packageid");
        }
    }

    public static String buildUrl(String urlFunc, Map<String, String> urlParams) {
        String url;
        String str;
        String url2 = TextUtils.isEmpty(urlFunc) ? webPayUrl : webPayUrl + urlFunc;
        if (!url2.contains(HttpUtils.URL_AND_PARA_SEPARATOR)) {
            url = url2 + "?timestamp=" + URLEncoder.encode(System.currentTimeMillis() + "");
        } else {
            url = url2 + "&timestamp=" + URLEncoder.encode(System.currentTimeMillis() + "");
        }
        if (urlParams == null || urlParams.isEmpty()) {
            return url;
        }
        StringBuilder paramsBd = new StringBuilder();
        String str2 = "packageId";
        if (PAY_PACKAGEID == null) {
            str = AppEventsConstants.EVENT_PARAM_VALUE_NO;
        } else {
            str = PAY_PACKAGEID + "";
        }
        urlParams.put(str2, str);
        for (Entry<String, String> kv : urlParams.entrySet()) {
            try {
                paramsBd.append(HttpUtils.PARAMETERS_SEPARATOR);
                paramsBd.append((String) kv.getKey());
                paramsBd.append(HttpUtils.EQUAL_SIGN);
                paramsBd.append(URLEncoder.encode((String) kv.getValue(), "utf-8"));
            } catch (UnsupportedEncodingException e) {
                BtsdkLog.m1430e(e.getLocalizedMessage());
            }
        }
        return url + paramsBd.toString();
    }
}
