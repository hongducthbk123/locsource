package com.btgame.data.constants;

import android.text.TextUtils;
import com.btgame.seasdk.btcore.common.util.BTResourceUtil;

public class Constants {
    public static final String BAIAOKEY = "baiao_mobile_client_";
    public static final String KEY_URL_DEV_HOST = "url_satrack_dev_host";
    public static final String KEY_URL_LOCAL_HOST = "url_satrack_local_host";
    public static final String KEY_URL_RELEASE_HOST = "url_satrack_release_host";
    public static final String URL_DEV_HOST = "http://10.17.1.180";
    public static final String URL_LOCAL_HOST = "http://10.18.6.128:8084";
    public static final String URL_RELEASE_HOST = "http://realtimedata.100bt.com";
    public static Constants instance;
    private String url_dev_host = BTResourceUtil.findStringByName(KEY_URL_DEV_HOST);
    private String url_local_host;
    private String url_release_host;

    private Constants() {
        if (TextUtils.isEmpty(this.url_dev_host)) {
            this.url_dev_host = URL_DEV_HOST;
        }
        this.url_release_host = BTResourceUtil.findStringByName(KEY_URL_RELEASE_HOST);
        if (TextUtils.isEmpty(this.url_release_host)) {
            this.url_release_host = URL_RELEASE_HOST;
        }
        this.url_local_host = BTResourceUtil.findStringByName(KEY_URL_LOCAL_HOST);
        if (TextUtils.isEmpty(this.url_local_host)) {
            this.url_local_host = URL_LOCAL_HOST;
        }
    }

    public static Constants getInstance() {
        if (instance == null) {
            instance = new Constants();
        }
        return instance;
    }

    public String getReleaseURL() {
        return this.url_release_host;
    }
}
