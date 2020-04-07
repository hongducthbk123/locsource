package com.baitian.datasdk.constants;

import android.content.Context;
import android.content.res.Resources.NotFoundException;
import com.baitian.datasdk.util.BtUtils;
import com.baitian.datasdk.util.DebugUtils;
import com.baitian.datasdk.util.ResourceUtil;

public class Constants {
    public static final String BAIAOKEY = "baiao_mobile_client_";
    public static final String KEY_PREFIX_DATA = "Data";
    public static final String KEY_PREFIX_EVENT = "Event";
    private static final String KEY_URL_DEV_HOST = "url_dev_host";
    private static final String KEY_URL_LOCAL_HOST = "url_local_host";
    private static final String KEY_URL_RELEASE_HOST = "url_release_host";
    private static final String URL_DEV_HOST = "http://10.17.1.180";
    private static final String URL_LOCAL_HOST = "http://10.18.6.128:8084";
    private static final String URL_RELEASE_HOST = "http://realtimedata.100bt.com";
    public static Constants instance;

    private Constants() {
    }

    public static Constants getInstance() {
        if (instance == null) {
            instance = new Constants();
        }
        return instance;
    }

    public String getURL(Context context) {
        String url_local_host;
        String url_dev_host;
        String url_release_host;
        try {
            url_local_host = context.getResources().getString(ResourceUtil.getStringId(context, KEY_URL_LOCAL_HOST));
        } catch (NotFoundException e) {
            url_local_host = "http://10.18.6.128:8084";
        }
        String sdkSaServerUrl = BtUtils.getNOXMeTaData(context, "sdkSaServerUrl");
        if ("-1".equals(sdkSaServerUrl)) {
            try {
                url_dev_host = context.getResources().getString(ResourceUtil.getStringId(context, KEY_URL_DEV_HOST));
            } catch (NotFoundException e2) {
                url_dev_host = "http://10.17.1.180";
            }
        } else {
            url_dev_host = sdkSaServerUrl;
        }
        if ("-1".equals(sdkSaServerUrl)) {
            try {
                url_release_host = context.getResources().getString(ResourceUtil.getStringId(context, KEY_URL_RELEASE_HOST));
            } catch (NotFoundException e3) {
                url_release_host = "http://realtimedata.100bt.com";
            }
        } else {
            url_release_host = sdkSaServerUrl;
        }
        switch (DebugUtils.getInstance().getRunningType()) {
            case 1:
                return url_dev_host;
            case 3:
                return url_local_host;
            default:
                return url_release_host;
        }
    }
}
