package com.sensorsdata.analytics.android.sdk;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.p000v4.app.NotificationCompat;
import android.support.p003v7.app.AlertDialog;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Toast;
import com.baitian.util.BtUtils;
import com.btgame.onesdk.PlatfromUtils;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.places.model.PlaceFields;
import com.sensorsdata.analytics.android.sdk.exceptions.InvalidDataException;
import com.sensorsdata.analytics.android.sdk.util.JSONUtils;
import com.sensorsdata.analytics.android.sdk.util.SensorsDataUtils;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SensorsDataAPI {
    static Boolean ENABLE_LOG = Boolean.valueOf(false);
    private static final Pattern EVENT_PATTERN = Pattern.compile("^[0-9]*$", 2);
    private static final Pattern KEY_PATTERN = Pattern.compile("^(?:distinct_id|original_id|time|properties|id|first_id|second_id|users|events|event|user_id|date|datetime|dataAppId|terminal|platformId|platformIdSecond|phoneModel|phoneSysVersion|imsi|imei|densityDpi|displayScreenWidth|displayScreenHeight|gameVersionName|gameVersionCode|staticsSdkVersion|packageName|networkInfo|logTime|manufacturer|isFirstDay|carrier|model|eventId)$", 2);
    static Boolean SHOW_DEBUG_INFO_VIEW = Boolean.valueOf(true);
    private static final String TAG = "SA.SensorsDataAPI";
    static final String VERSION = "1.8.8";
    static final int VTRACK_SUPPORTED_MIN_API = 16;
    private static final SimpleDateFormat mIsFirstDayDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    private static final Map<Context, SensorsDataAPI> sInstanceMap = new HashMap();
    private static final SharedPreferencesLoader sPrefsLoader = new SharedPreferencesLoader();
    /* access modifiers changed from: private */
    public boolean mAutoTrack;
    /* access modifiers changed from: private */
    public List<AutoTrackEventType> mAutoTrackEventTypeList;
    private List<Integer> mAutoTrackIgnoredActivities;
    private final String mConfigureUrl;
    /* access modifiers changed from: private */
    public final Context mContext;
    /* access modifiers changed from: private */
    public Map<String, Object> mCustomProperties;
    private final DebugMode mDebugMode;
    /* access modifiers changed from: private */
    public final Map<String, Object> mDeviceInfo;
    private final PersistentDistinctId mDistinctId;
    private boolean mEnableAndroidId;
    private boolean mEnableButterknifeOnClick;
    private boolean mEnableReactNativeAutoTrack;
    private boolean mEnableVTrack;
    private final PersistentFirstDay mFirstDay;
    private final PersistentFirstStart mFirstStart;
    private final PersistentFirstTrackInstallation mFirstTrackInstallation;
    private int mFlushBulkSize;
    private int mFlushInterval;
    private int mFlushNetworkPolicy = 15;
    private List<Class> mIgnoredViewTypeList = new ArrayList();
    private JSONObject mLastScreenTrackProperties;
    private String mLastScreenUrl;
    private final PersistentLoginId mLoginId;
    private final String mMainProcessName;
    private long mMaxCacheSize = 33554432;
    /* access modifiers changed from: private */
    public final AnalyticsMessages mMessages;
    private final String mServerUrl;
    /* access modifiers changed from: private */
    public final PersistentSuperProperties mSuperProperties;
    private boolean mTrackFragmentAppViewScreen;
    /* access modifiers changed from: private */
    public final Map<String, EventTimer> mTrackTimer;
    /* access modifiers changed from: private */
    public final VTrack mVTrack;

    public enum AutoTrackEventType {
        APP_START("$AppStart"),
        APP_END("$AppEnd"),
        APP_CLICK(AopConstants.APP_CLICK_EVENT_NAME),
        APP_VIEW_SCREEN("$AppViewScreen");
        
        private final String eventName;

        public static AutoTrackEventType autoTrackEventTypeFromEventName(String eventName2) {
            if (TextUtils.isEmpty(eventName2)) {
                return null;
            }
            if ("$AppStart".equals(eventName2)) {
                return APP_START;
            }
            if ("$AppEnd".equals(eventName2)) {
                return APP_END;
            }
            if (AopConstants.APP_CLICK_EVENT_NAME.equals(eventName2)) {
                return APP_CLICK;
            }
            if ("$AppViewScreen".equals(eventName2)) {
                return APP_VIEW_SCREEN;
            }
            return null;
        }

        private AutoTrackEventType(String eventName2) {
            this.eventName = eventName2;
        }

        /* access modifiers changed from: 0000 */
        public String getEventName() {
            return this.eventName;
        }
    }

    public enum DebugMode {
        DEBUG_OFF(false, false),
        DEBUG_ONLY(true, false),
        DEBUG_AND_TRACK(true, true);
        
        private final boolean debugMode;
        private final boolean debugWriteData;

        private DebugMode(boolean debugMode2, boolean debugWriteData2) {
            this.debugMode = debugMode2;
            this.debugWriteData = debugWriteData2;
        }

        /* access modifiers changed from: 0000 */
        public boolean isDebugMode() {
            return this.debugMode;
        }

        /* access modifiers changed from: 0000 */
        public boolean isDebugWriteData() {
            return this.debugWriteData;
        }
    }

    public final class NetworkType {
        public static final int TYPE_2G = 1;
        public static final int TYPE_3G = 2;
        public static final int TYPE_4G = 4;
        public static final int TYPE_ALL = 255;
        public static final int TYPE_NONE = 0;
        public static final int TYPE_WIFI = 8;

        public NetworkType() {
        }
    }

    /* access modifiers changed from: protected */
    public boolean isShouldFlush(String networkType) {
        return (toNetworkType(networkType) & this.mFlushNetworkPolicy) != 0;
    }

    private int toNetworkType(String networkType) {
        if ("NULL".equals(networkType)) {
            return 255;
        }
        if ("WIFI".equals(networkType)) {
            return 8;
        }
        if ("2G".equals(networkType)) {
            return 1;
        }
        if ("3G".equals(networkType)) {
            return 2;
        }
        if ("4G".equals(networkType)) {
            return 4;
        }
        return 255;
    }

    SensorsDataAPI(Context context, String serverURL, String configureURL, String vtrackServerURL, DebugMode debugMode) {
        this.mContext = context;
        String packageName = context.getApplicationContext().getPackageName();
        this.mAutoTrackIgnoredActivities = new ArrayList();
        this.mCustomProperties = new HashMap();
        try {
            SensorsDataUtils.cleanUserAgent(this.mContext);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            SALog.init(this);
            Bundle configBundle = context.getApplicationContext().getPackageManager().getApplicationInfo(packageName, 128).metaData;
            if (configBundle == null) {
                configBundle = new Bundle();
            }
            this.mServerUrl = serverURL;
            this.mConfigureUrl = configureURL;
            if (debugMode == DebugMode.DEBUG_OFF) {
                ENABLE_LOG = Boolean.valueOf(configBundle.getBoolean("com.sensorsdata.analytics.android.EnableLogging", false));
            } else {
                ENABLE_LOG = Boolean.valueOf(configBundle.getBoolean("com.sensorsdata.analytics.android.EnableLogging", true));
            }
            SHOW_DEBUG_INFO_VIEW = Boolean.valueOf(configBundle.getBoolean("com.sensorsdata.analytics.android.ShowDebugInfoView", true));
            this.mFlushInterval = configBundle.getInt("com.sensorsdata.analytics.android.FlushInterval", 15000);
            this.mFlushBulkSize = configBundle.getInt("com.sensorsdata.analytics.android.FlushBulkSize", 100);
            this.mAutoTrack = configBundle.getBoolean("com.sensorsdata.analytics.android.AutoTrack", false);
            this.mEnableVTrack = configBundle.getBoolean("com.sensorsdata.analytics.android.VTrack", false);
            this.mEnableAndroidId = configBundle.getBoolean("com.sensorsdata.analytics.android.AndroidId", false);
            this.mEnableButterknifeOnClick = configBundle.getBoolean("com.sensorsdata.analytics.android.ButterknifeOnClick", false);
            this.mMainProcessName = configBundle.getString("com.sensorsdata.analytics.android.MainProcessName");
            this.mDebugMode = debugMode;
            if (this.mDebugMode == DebugMode.DEBUG_OFF || !SensorsDataUtils.isMainProcess(this.mContext.getApplicationContext(), this.mMainProcessName)) {
                SHOW_DEBUG_INFO_VIEW = Boolean.valueOf(false);
            } else {
                showDebugModeWarning();
            }
            if (VERSION.SDK_INT < 16 || !this.mEnableVTrack) {
                SALog.m1606d(TAG, "VTrack is not supported on this Android OS Version");
                this.mVTrack = new VTrackUnsupported();
            } else {
                String resourcePackageName = configBundle.getString("com.sensorsdata.analytics.android.ResourcePackageName");
                if (resourcePackageName == null) {
                    resourcePackageName = context.getPackageName();
                }
                ViewCrawler viewCrawler = new ViewCrawler(this.mContext, resourcePackageName);
                this.mVTrack = viewCrawler;
            }
            if (vtrackServerURL != null) {
                this.mVTrack.setVTrackServer(vtrackServerURL);
            }
            this.mMessages = AnalyticsMessages.getInstance(this.mContext, packageName);
            String str = "com.sensorsdata.analytics.android.sdk.SensorsDataAPI";
            Future<SharedPreferences> storedPreferences = sPrefsLoader.loadPreferences(context, "com.sensorsdata.analytics.android.sdk.SensorsDataAPI", new OnPrefsLoadedListener() {
                public void onPrefsLoaded(SharedPreferences preferences) {
                }
            });
            PersistentDistinctId persistentDistinctId = new PersistentDistinctId(storedPreferences);
            this.mDistinctId = persistentDistinctId;
            if (this.mEnableAndroidId) {
                try {
                    String androidId = SensorsDataUtils.getAndroidID(this.mContext);
                    if (SensorsDataUtils.isValidAndroidId(androidId)) {
                        identify(androidId);
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            PersistentLoginId persistentLoginId = new PersistentLoginId(storedPreferences);
            this.mLoginId = persistentLoginId;
            PersistentSuperProperties persistentSuperProperties = new PersistentSuperProperties(storedPreferences);
            this.mSuperProperties = persistentSuperProperties;
            PersistentFirstStart persistentFirstStart = new PersistentFirstStart(storedPreferences);
            this.mFirstStart = persistentFirstStart;
            PersistentFirstTrackInstallation persistentFirstTrackInstallation = new PersistentFirstTrackInstallation(storedPreferences);
            this.mFirstTrackInstallation = persistentFirstTrackInstallation;
            PersistentFirstDay persistentFirstDay = new PersistentFirstDay(storedPreferences);
            this.mFirstDay = persistentFirstDay;
            if (this.mFirstDay.get() == null) {
                this.mFirstDay.commit(mIsFirstDayDateFormat.format(Long.valueOf(System.currentTimeMillis())));
            }
            if (VERSION.SDK_INT >= 14) {
                Application app = (Application) context.getApplicationContext();
                SensorsDataActivityLifecycleCallbacks sensorsDataActivityLifecycleCallbacks = new SensorsDataActivityLifecycleCallbacks(this, this.mFirstStart, this.mMainProcessName);
                app.registerActivityLifecycleCallbacks(sensorsDataActivityLifecycleCallbacks);
            }
            SALog.m1606d(TAG, String.format(Locale.CHINA, "Initialized the instance of Sensors Analytics SDK with server url '%s', configure url '%s' flush interval %d ms, debugMode: %s", new Object[]{this.mServerUrl, this.mConfigureUrl, Integer.valueOf(this.mFlushInterval), debugMode}));
            this.mAutoTrackEventTypeList = new ArrayList();
            Map<String, Object> deviceInfo = new HashMap<>();
            deviceInfo.put("terminal", AppEventsConstants.EVENT_PARAM_VALUE_NO);
            deviceInfo.put("staticsSdkVersion", VERSION);
            deviceInfo.put("phoneSysVersion", VERSION.RELEASE == null ? "UNKNOWN" : VERSION.RELEASE);
            deviceInfo.put("manufacturer", Build.MANUFACTURER == null ? "UNKNOWN" : Build.MANUFACTURER);
            deviceInfo.put("model", Build.MODEL == null ? "UNKNOWN" : Build.MODEL);
            deviceInfo.put("phoneModel", Build.MODEL);
            try {
                PackageInfo info = this.mContext.getPackageManager().getPackageInfo(this.mContext.getPackageName(), 0);
                deviceInfo.put("gameVersionName", info.versionName);
                deviceInfo.put("gameVersionCode", Integer.valueOf(info.versionCode));
                deviceInfo.put("packageName", info.packageName);
            } catch (Exception e3) {
                SALog.m1607d(TAG, "Exception getting app version name", e3);
            }
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            deviceInfo.put("displayScreenHeight", Integer.valueOf(displayMetrics.heightPixels));
            deviceInfo.put("displayScreenWidth", Integer.valueOf(displayMetrics.widthPixels));
            deviceInfo.put("densityDpi", Integer.valueOf(displayMetrics.densityDpi));
            int dataAppId = BtUtils.getIntNoXMeTaData(this.mContext, "btgameId");
            int platformId = BtUtils.getIntNoXMeTaData(this.mContext, PlatfromUtils.PLATFROMID_KEY);
            String platformIdSecond = String.valueOf(BtUtils.getIntNoXMeTaData(this.mContext, "btchannelId"));
            deviceInfo.put("dataAppId", Integer.valueOf(dataAppId));
            deviceInfo.put("platformId", Integer.valueOf(platformId));
            deviceInfo.put("platformIdSecond", platformIdSecond);
            deviceInfo.put("imei", BtUtils.getIMEI(this.mContext));
            deviceInfo.put("imsi", BtUtils.getIMSI(this.mContext));
            if (SensorsDataUtils.checkHasPermission(context, "android.permission.READ_PHONE_STATE")) {
                try {
                    String operatorString = ((TelephonyManager) this.mContext.getSystemService(PlaceFields.PHONE)).getSubscriberId();
                    if (!TextUtils.isEmpty(operatorString)) {
                        deviceInfo.put("carrier", SensorsDataUtils.operatorToCarrier(operatorString));
                    } else {
                        deviceInfo.put("carrier", "NULL");
                    }
                } catch (Exception e4) {
                    e4.printStackTrace();
                }
            }
            this.mDeviceInfo = Collections.unmodifiableMap(deviceInfo);
            this.mTrackTimer = new HashMap();
            this.mVTrack.startUpdates();
            if (this.mEnableVTrack) {
                this.mMessages.checkConfigure(new DecideMessages(this.mVTrack));
            }
        } catch (NameNotFoundException e5) {
            RuntimeException runtimeException = new RuntimeException("Can't configure SensorsDataAPI with package name " + packageName, e5);
            throw runtimeException;
        }
    }

    public static SensorsDataAPI sharedInstance(Context context) {
        SensorsDataAPI instance;
        if (context == null) {
            return null;
        }
        synchronized (sInstanceMap) {
            instance = (SensorsDataAPI) sInstanceMap.get(context.getApplicationContext());
            if (instance == null) {
                SALog.m1606d(TAG, "The static method sharedInstance(context, serverURL, configureURL, vtrackServerURL, debugMode) should be called before calling sharedInstance()");
            }
        }
        return instance;
    }

    public static SensorsDataAPI sharedInstance(Context context, String serverURL, String configureUrl, DebugMode debugMode) {
        SensorsDataAPI instance = null;
        if (context != null) {
            synchronized (sInstanceMap) {
                Context appContext = context.getApplicationContext();
                instance = (SensorsDataAPI) sInstanceMap.get(appContext);
                if (instance == null && ConfigurationChecker.checkBasicConfiguration(appContext)) {
                    instance = new SensorsDataAPI(appContext, serverURL, configureUrl, null, debugMode);
                    sInstanceMap.put(appContext, instance);
                }
            }
        }
        return instance;
    }

    public static SensorsDataAPI sharedInstance(Context context, String serverURL, String configureURL, String vtrackServerURL, DebugMode debugMode) {
        SensorsDataAPI instance;
        if (context == null) {
            return null;
        }
        synchronized (sInstanceMap) {
            Context appContext = context.getApplicationContext();
            instance = (SensorsDataAPI) sInstanceMap.get(appContext);
            if (instance == null && ConfigurationChecker.checkBasicConfiguration(appContext)) {
                instance = new SensorsDataAPI(appContext, serverURL, configureURL, vtrackServerURL, debugMode);
                sInstanceMap.put(appContext, instance);
            }
        }
        return instance;
    }

    public static SensorsDataAPI sharedInstance() {
        SensorsDataAPI sensorsDataAPI;
        synchronized (sInstanceMap) {
            if (sInstanceMap.size() > 0) {
                Iterator<SensorsDataAPI> iterator = sInstanceMap.values().iterator();
                if (iterator.hasNext()) {
                    sensorsDataAPI = (SensorsDataAPI) iterator.next();
                }
            }
            sensorsDataAPI = null;
        }
        return sensorsDataAPI;
    }

    public void enableLog(boolean enable) {
        ENABLE_LOG = Boolean.valueOf(enable);
    }

    public long getMaxCacheSize() {
        return this.mMaxCacheSize;
    }

    public void setMaxCacheSize(long maxCacheSize) {
        if (maxCacheSize > 0) {
            this.mMaxCacheSize = maxCacheSize;
        }
    }

    public void setFlushNetworkPolicy(int networkType) {
        this.mFlushNetworkPolicy = networkType;
    }

    public int getFlushInterval() {
        return this.mFlushInterval;
    }

    public void setFlushInterval(int flushInterval) {
        this.mFlushInterval = flushInterval;
    }

    public int getFlushBulkSize() {
        return this.mFlushBulkSize;
    }

    public void setFlushBulkSize(int flushBulkSize) {
        this.mFlushBulkSize = flushBulkSize;
    }

    public void setFixedProperty(String key, Object value) {
        this.mCustomProperties.put(key, value);
    }

    /* access modifiers changed from: protected */
    public void enableEditingVTrack() {
        this.mVTrack.enableEditingVTrack();
    }

    /* access modifiers changed from: protected */
    public void disableActivityForVTrack(String canonicalName) {
        if (canonicalName != null) {
            this.mVTrack.disableActivity(canonicalName);
        }
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public void enableAutoTrack() {
        List<AutoTrackEventType> eventTypeList = new ArrayList<>();
        eventTypeList.add(AutoTrackEventType.APP_START);
        eventTypeList.add(AutoTrackEventType.APP_END);
        eventTypeList.add(AutoTrackEventType.APP_VIEW_SCREEN);
        enableAutoTrack(eventTypeList);
    }

    /* access modifiers changed from: protected */
    public void enableAutoTrack(List<AutoTrackEventType> eventTypeList) {
        this.mAutoTrack = true;
        if (eventTypeList != null && eventTypeList.size() != 0) {
            this.mAutoTrackEventTypeList.clear();
            this.mAutoTrackEventTypeList.addAll(eventTypeList);
        }
    }

    /* access modifiers changed from: protected */
    public void disableAutoTrack(List<AutoTrackEventType> eventTypeList) {
        if (eventTypeList != null && eventTypeList.size() != 0 && this.mAutoTrackEventTypeList != null) {
            for (AutoTrackEventType autoTrackEventType : eventTypeList) {
                if (autoTrackEventType != null && this.mAutoTrackEventTypeList.contains(autoTrackEventType)) {
                    this.mAutoTrackEventTypeList.remove(autoTrackEventType);
                }
            }
            if (this.mAutoTrackEventTypeList.size() == 0) {
                this.mAutoTrack = false;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void disableAutoTrack(AutoTrackEventType autoTrackEventType) {
        if (autoTrackEventType != null && this.mAutoTrackEventTypeList != null) {
            if (this.mAutoTrackEventTypeList.contains(autoTrackEventType)) {
                this.mAutoTrackEventTypeList.remove(autoTrackEventType);
            }
            if (this.mAutoTrackEventTypeList.size() == 0) {
                this.mAutoTrack = false;
            }
        }
    }

    public boolean isAutoTrackEnabled() {
        return this.mAutoTrack;
    }

    /* access modifiers changed from: protected */
    public boolean isButterknifeOnClickEnabled() {
        return this.mEnableButterknifeOnClick;
    }

    /* access modifiers changed from: protected */
    public void trackFragmentAppViewScreen() {
        this.mTrackFragmentAppViewScreen = true;
    }

    /* access modifiers changed from: protected */
    public boolean isTrackFragmentAppViewScreenEnabled() {
        return this.mTrackFragmentAppViewScreen;
    }

    /* access modifiers changed from: protected */
    public void enableReactNativeAutoTrack() {
        this.mEnableReactNativeAutoTrack = true;
    }

    public boolean isReactNativeAutoTrackEnabled() {
        return this.mEnableReactNativeAutoTrack;
    }

    /* access modifiers changed from: protected */
    @SuppressLint({"SetJavaScriptEnabled", "addJavascriptInterface"})
    public void showUpWebView(WebView webView, boolean isSupportJellyBean) {
        showUpWebView(webView, isSupportJellyBean, null);
    }

    /* access modifiers changed from: protected */
    @SuppressLint({"SetJavaScriptEnabled", "addJavascriptInterface"})
    public void showUpWebView(WebView webView, boolean isSupportJellyBean, JSONObject properties) {
        if (VERSION.SDK_INT < 17 && !isSupportJellyBean) {
            SALog.m1606d(TAG, "For applications targeted to API level JELLY_BEAN or below, this feature NOT SUPPORTED");
        } else if (webView != null) {
            webView.getSettings().setJavaScriptEnabled(true);
            webView.addJavascriptInterface(new AppWebViewInterface(this.mContext, properties), "SensorsData_APP_JS_Bridge");
        }
    }

    /* access modifiers changed from: protected */
    public void showUpX5WebView(Object x5WebView) {
        if (x5WebView != null) {
            try {
                Method addJavascriptInterface = x5WebView.getClass().getDeclaredMethod("addJavascriptInterface", new Class[]{Object.class, String.class});
                if (addJavascriptInterface != null) {
                    addJavascriptInterface.invoke(x5WebView, new Object[]{new AppWebViewInterface(this.mContext, null), "SensorsData_APP_JS_Bridge"});
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void ignoreAutoTrackActivities(List<Class<?>> activitiesList) {
        if (activitiesList != null && activitiesList.size() != 0) {
            if (this.mAutoTrackIgnoredActivities == null) {
                this.mAutoTrackIgnoredActivities = new ArrayList();
            }
            for (Class<?> activity : activitiesList) {
                if (activity != null && !this.mAutoTrackIgnoredActivities.contains(Integer.valueOf(activity.hashCode()))) {
                    this.mAutoTrackIgnoredActivities.add(Integer.valueOf(activity.hashCode()));
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void ignoreAutoTrackActivity(Class<?> activity) {
        if (activity != null) {
            if (this.mAutoTrackIgnoredActivities == null) {
                this.mAutoTrackIgnoredActivities = new ArrayList();
            }
            if (!this.mAutoTrackIgnoredActivities.contains(Integer.valueOf(activity.hashCode()))) {
                this.mAutoTrackIgnoredActivities.add(Integer.valueOf(activity.hashCode()));
            }
        }
    }

    /* access modifiers changed from: protected */
    public boolean isActivityAutoTrackAppViewScreenIgnored(Class<?> activity) {
        if (activity == null) {
            return false;
        }
        if (this.mAutoTrackIgnoredActivities != null && this.mAutoTrackIgnoredActivities.contains(Integer.valueOf(activity.hashCode()))) {
            return true;
        }
        if (activity.getAnnotation(SensorsDataIgnoreTrackAppViewScreenAndAppClick.class) != null) {
            return true;
        }
        if (activity.getAnnotation(SensorsDataIgnoreTrackAppViewScreen.class) != null) {
            return true;
        }
        return false;
    }

    public boolean isActivityAutoTrackAppClickIgnored(Class<?> activity) {
        if (activity == null) {
            return false;
        }
        if (this.mAutoTrackIgnoredActivities != null && this.mAutoTrackIgnoredActivities.contains(Integer.valueOf(activity.hashCode()))) {
            return true;
        }
        if (activity.getAnnotation(SensorsDataIgnoreTrackAppViewScreenAndAppClick.class) != null) {
            return true;
        }
        if (activity.getAnnotation(SensorsDataIgnoreTrackAppClick.class) != null) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public void ignoreAutoTrackEventType(AutoTrackEventType autoTrackEventType) {
        if (autoTrackEventType != null && this.mAutoTrackEventTypeList.contains(autoTrackEventType)) {
            this.mAutoTrackEventTypeList.remove(autoTrackEventType);
        }
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public void ignoreAutoTrackEventType(List<AutoTrackEventType> eventTypeList) {
        if (eventTypeList != null) {
            for (AutoTrackEventType eventType : eventTypeList) {
                if (eventType != null && this.mAutoTrackEventTypeList.contains(eventType)) {
                    this.mAutoTrackEventTypeList.remove(eventType);
                }
            }
        }
    }

    public boolean isAutoTrackEventTypeIgnored(AutoTrackEventType eventType) {
        if (eventType == null || this.mAutoTrackEventTypeList.contains(eventType)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void setViewID(View view, String viewID) {
        if (view != null && !TextUtils.isEmpty(viewID)) {
            view.setTag(R.id.sensors_analytics_tag_view_id, viewID);
        }
    }

    /* access modifiers changed from: protected */
    public void setViewID(Dialog view, String viewID) {
        if (view != null) {
            try {
                if (!TextUtils.isEmpty(viewID) && view.getWindow() != null) {
                    view.getWindow().getDecorView().setTag(R.id.sensors_analytics_tag_view_id, viewID);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void setViewID(AlertDialog view, String viewID) {
        if (view != null) {
            try {
                if (!TextUtils.isEmpty(viewID) && view.getWindow() != null) {
                    view.getWindow().getDecorView().setTag(R.id.sensors_analytics_tag_view_id, viewID);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void setViewActivity(View view, Activity activity) {
        if (view != null && activity != null) {
            try {
                view.setTag(R.id.sensors_analytics_tag_view_activity, activity);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void setViewFragmentName(View view, String fragmentName) {
        if (view != null) {
            try {
                if (!TextUtils.isEmpty(fragmentName)) {
                    view.setTag(R.id.sensors_analytics_tag_view_fragment_name2, fragmentName);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void ignoreView(View view) {
        if (view != null) {
            view.setTag(R.id.sensors_analytics_tag_view_ignored, "1");
        }
    }

    /* access modifiers changed from: protected */
    public void setViewProperties(View view, JSONObject properties) {
        if (view != null && properties != null) {
            view.setTag(R.id.sensors_analytics_tag_view_properties, properties);
        }
    }

    public List<Class> getIgnoredViewTypeList() {
        if (this.mIgnoredViewTypeList == null) {
            this.mIgnoredViewTypeList = new ArrayList();
        }
        return this.mIgnoredViewTypeList;
    }

    /* access modifiers changed from: protected */
    public void ignoreViewType(Class viewType) {
        if (viewType != null) {
            if (this.mIgnoredViewTypeList == null) {
                this.mIgnoredViewTypeList = new ArrayList();
            }
            if (!this.mIgnoredViewTypeList.contains(viewType)) {
                this.mIgnoredViewTypeList.add(viewType);
            }
        }
    }

    @Deprecated
    public String getDistinctId() {
        String str;
        synchronized (this.mDistinctId) {
            str = (String) this.mDistinctId.get();
        }
        return str;
    }

    public String getAnonymousId() {
        String str;
        synchronized (this.mDistinctId) {
            str = (String) this.mDistinctId.get();
        }
        return str;
    }

    public void resetAnonymousId() {
        synchronized (this.mDistinctId) {
            if (this.mEnableAndroidId) {
                String androidId = SensorsDataUtils.getAndroidID(this.mContext);
                if (SensorsDataUtils.isValidAndroidId(androidId)) {
                    this.mDistinctId.commit(androidId);
                    return;
                }
            }
            this.mDistinctId.commit(UUID.randomUUID().toString());
        }
    }

    public String getLoginId() {
        String str;
        synchronized (this.mLoginId) {
            str = (String) this.mLoginId.get();
        }
        return str;
    }

    public void identify(String distinctId) {
        try {
            assertDistinctId(distinctId);
            synchronized (this.mDistinctId) {
                this.mDistinctId.commit(distinctId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void login(String loginId) {
        try {
            assertDistinctId(loginId);
            synchronized (this.mLoginId) {
                if (!loginId.equals(this.mLoginId.get())) {
                    this.mLoginId.commit(loginId);
                    if (!loginId.equals(getAnonymousId())) {
                        trackEvent(EventType.TRACK_SIGNUP, "$SignUp", null, getAnonymousId());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void logout() {
        synchronized (this.mLoginId) {
            this.mLoginId.commit(null);
        }
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public void trackSignUp(String newDistinctId, JSONObject properties) {
        try {
            String originalDistinctId = getDistinctId();
            identify(newDistinctId);
            trackEvent(EventType.TRACK_SIGNUP, "$SignUp", properties, originalDistinctId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public void trackSignUp(String newDistinctId) {
        try {
            String originalDistinctId = getDistinctId();
            identify(newDistinctId);
            trackEvent(EventType.TRACK_SIGNUP, "$SignUp", null, originalDistinctId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: protected */
    public void trackInstallation(String eventName, JSONObject properties) {
        try {
            if (((Boolean) this.mFirstTrackInstallation.get()).booleanValue()) {
                if (properties == null) {
                    try {
                        properties = new JSONObject();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (!SensorsDataUtils.hasUtmProperties(properties)) {
                    Map<String, String> utmMap = new HashMap<>();
                    utmMap.put("SENSORS_ANALYTICS_UTM_SOURCE", "$utm_source");
                    utmMap.put("SENSORS_ANALYTICS_UTM_MEDIUM", "$utm_medium");
                    utmMap.put("SENSORS_ANALYTICS_UTM_TERM", "$utm_term");
                    utmMap.put("SENSORS_ANALYTICS_UTM_CONTENT", "$utm_content");
                    utmMap.put("SENSORS_ANALYTICS_UTM_CAMPAIGN", "$utm_campaign");
                    for (Entry<String, String> entry : utmMap.entrySet()) {
                        if (entry != null) {
                            String utmValue = SensorsDataUtils.getApplicationMetaData(this.mContext, (String) entry.getKey());
                            if (!TextUtils.isEmpty(utmValue)) {
                                properties.put((String) entry.getValue(), utmValue);
                            }
                        }
                    }
                }
                if (!SensorsDataUtils.hasUtmProperties(properties)) {
                    properties.put("$ios_install_source", String.format("android_id=%s##imei=%s##mac=%s", new Object[]{SensorsDataUtils.getAndroidID(this.mContext), SensorsDataUtils.getIMEI(this.mContext), SensorsDataUtils.getMacAddress(this.mContext)}));
                }
                trackEvent(EventType.TRACK, eventName, properties, null);
                trackEvent(EventType.PROFILE_SET_ONCE, null, properties, null);
                this.mFirstTrackInstallation.commit(Boolean.valueOf(false));
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void track(String eventName, JSONObject properties) {
        track(eventName, properties, false);
    }

    public void track(String eventName, JSONObject properties, boolean isFlush) {
        try {
            trackEvent(EventType.TRACK, eventName, properties, null, isFlush);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void track(String eventName, Map properties, boolean isFlush) {
        JSONObject propertiesObject = null;
        if (properties != null) {
            try {
                propertiesObject = new JSONObject(properties);
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        track(eventName, propertiesObject, isFlush);
    }

    public void track(String eventName, String properties, boolean isFlush) {
        JSONObject propertiesObject = null;
        if (properties != null) {
            try {
                propertiesObject = new JSONObject(properties);
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        track(eventName, propertiesObject, isFlush);
    }

    public void track(String eventName) {
        track(eventName, false);
    }

    public void track(String eventName, boolean isFlush) {
        try {
            trackEvent(EventType.TRACK, eventName, null, null, isFlush);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: protected */
    public void trackTimer(String eventName) {
        try {
            trackTimer(eventName, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: protected */
    public void trackTimer(String eventName, TimeUnit timeUnit) {
        try {
            assertEvent(eventName);
            synchronized (this.mTrackTimer) {
                this.mTrackTimer.put(eventName, new EventTimer(timeUnit));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: protected */
    public void trackTimerBegin(String eventName) {
        trackTimer(eventName);
    }

    /* access modifiers changed from: protected */
    public void trackTimerBegin(String eventName, TimeUnit timeUnit) {
        trackTimer(eventName, timeUnit);
    }

    /* access modifiers changed from: protected */
    public void trackTimerEnd(String eventName, JSONObject properties) {
        track(eventName, properties);
    }

    /* access modifiers changed from: protected */
    public void trackTimerEnd(String eventName) {
        track(eventName);
    }

    /* access modifiers changed from: protected */
    public void clearTrackTimer() {
        synchronized (this.mTrackTimer) {
            this.mTrackTimer.clear();
        }
    }

    /* access modifiers changed from: protected */
    public String getLastScreenUrl() {
        return this.mLastScreenUrl;
    }

    /* access modifiers changed from: protected */
    public String getMainProcessName() {
        return this.mMainProcessName;
    }

    public JSONObject getLastScreenTrackProperties() {
        return this.mLastScreenTrackProperties;
    }

    /* access modifiers changed from: protected */
    public void trackViewScreen(String url, JSONObject properties) {
        try {
            if (!TextUtils.isEmpty(url) || properties != null) {
                JSONObject trackProperties = new JSONObject();
                this.mLastScreenTrackProperties = properties;
                if (!TextUtils.isEmpty(this.mLastScreenUrl)) {
                    trackProperties.put("$referrer", this.mLastScreenUrl);
                }
                trackProperties.put("$url", url);
                this.mLastScreenUrl = url;
                if (properties != null) {
                    SensorsDataUtils.mergeJSONObject(properties, trackProperties);
                }
                track("$AppViewScreen", trackProperties);
            }
        } catch (JSONException e) {
            SALog.m1608i(TAG, "trackViewScreen:" + e);
        }
    }

    /* access modifiers changed from: protected */
    public void trackViewScreen(Activity activity) {
        if (activity != null) {
            try {
                JSONObject properties = new JSONObject();
                properties.put(AopConstants.SCREEN_NAME, activity.getClass().getCanonicalName());
                SensorsDataUtils.getScreenNameAndTitleFromActivity(properties, activity);
                if (activity instanceof ScreenAutoTracker) {
                    ScreenAutoTracker screenAutoTracker = (ScreenAutoTracker) activity;
                    String screenUrl = screenAutoTracker.getScreenUrl();
                    JSONObject otherProperties = screenAutoTracker.getTrackProperties();
                    if (otherProperties != null) {
                        SensorsDataUtils.mergeJSONObject(otherProperties, properties);
                    }
                    trackViewScreen(screenUrl, properties);
                    return;
                }
                track("$AppViewScreen", properties);
            } catch (Exception e) {
                SALog.m1608i(TAG, "trackViewScreen:" + e);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void trackViewScreen(Fragment fragment) {
        if (fragment != null) {
            try {
                JSONObject properties = new JSONObject();
                String fragmentName = fragment.getClass().getCanonicalName();
                String screenName = fragmentName;
                if (VERSION.SDK_INT >= 11) {
                    Activity activity = fragment.getActivity();
                    if (activity != null) {
                        String activityTitle = SensorsDataUtils.getActivityTitle(activity);
                        if (!TextUtils.isEmpty(activityTitle)) {
                            properties.put(AopConstants.TITLE, activityTitle);
                        }
                        screenName = String.format(Locale.CHINA, "%s|%s", new Object[]{activity.getClass().getCanonicalName(), fragmentName});
                    }
                }
                properties.put(AopConstants.SCREEN_NAME, screenName);
                track("$AppViewScreen", properties);
            } catch (Exception e) {
                SALog.m1608i(TAG, "trackViewScreen:" + e);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void trackViewScreen(android.support.p000v4.app.Fragment fragment) {
        if (fragment != null) {
            try {
                JSONObject properties = new JSONObject();
                String fragmentName = fragment.getClass().getCanonicalName();
                String screenName = fragmentName;
                Activity activity = fragment.getActivity();
                if (activity != null) {
                    String activityTitle = SensorsDataUtils.getActivityTitle(activity);
                    if (!TextUtils.isEmpty(activityTitle)) {
                        properties.put(AopConstants.TITLE, activityTitle);
                    }
                    screenName = String.format(Locale.CHINA, "%s|%s", new Object[]{activity.getClass().getCanonicalName(), fragmentName});
                }
                properties.put(AopConstants.SCREEN_NAME, screenName);
                track("$AppViewScreen", properties);
            } catch (Exception e) {
                SALog.m1608i(TAG, "trackViewScreen:" + e);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void appEnterBackground() {
        synchronized (this.mTrackTimer) {
            try {
                for (Entry entry : this.mTrackTimer.entrySet()) {
                    if (entry != null && !"$AppEnd".equals(entry.getKey().toString())) {
                        EventTimer eventTimer = (EventTimer) entry.getValue();
                        if (eventTimer != null) {
                            eventTimer.setEventAccumulatedDuration((eventTimer.getEventAccumulatedDuration() + SystemClock.elapsedRealtime()) - eventTimer.getStartTime());
                            eventTimer.setStartTime(SystemClock.elapsedRealtime());
                        }
                    }
                }
            } catch (Exception e) {
                SALog.m1608i(TAG, "appEnterBackground error:" + e.getMessage());
            }
        }
    }

    /* access modifiers changed from: protected */
    public void appBecomeActive() {
        synchronized (this.mTrackTimer) {
            try {
                for (Entry entry : this.mTrackTimer.entrySet()) {
                    if (entry != null) {
                        EventTimer eventTimer = (EventTimer) entry.getValue();
                        if (eventTimer != null) {
                            eventTimer.setStartTime(SystemClock.elapsedRealtime());
                        }
                    }
                }
            } catch (Exception e) {
                SALog.m1608i(TAG, "appBecomeActive error:" + e.getMessage());
            }
        }
    }

    public void flush() {
        SALog.m1608i(TAG, "SensorsDataAPI flush");
        this.mMessages.flush();
    }

    public void flushSync() {
        this.mMessages.sendData();
    }

    /* access modifiers changed from: protected */
    public JSONObject getSuperProperties() {
        JSONObject jSONObject;
        synchronized (this.mSuperProperties) {
            jSONObject = (JSONObject) this.mSuperProperties.get();
        }
        return jSONObject;
    }

    /* access modifiers changed from: protected */
    public void registerSuperProperties(JSONObject superProperties) {
        if (superProperties != null) {
            try {
                assertPropertyTypes(EventType.REGISTER_SUPER_PROPERTIES, superProperties);
                synchronized (this.mSuperProperties) {
                    JSONObject properties = (JSONObject) this.mSuperProperties.get();
                    SensorsDataUtils.mergeJSONObject(superProperties, properties);
                    this.mSuperProperties.commit(properties);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void unregisterSuperProperty(String superPropertyName) {
        try {
            synchronized (this.mSuperProperties) {
                JSONObject superProperties = (JSONObject) this.mSuperProperties.get();
                superProperties.remove(superPropertyName);
                this.mSuperProperties.commit(superProperties);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: protected */
    public void clearSuperProperties() {
        synchronized (this.mSuperProperties) {
            this.mSuperProperties.commit(new JSONObject());
        }
    }

    /* access modifiers changed from: protected */
    public void profileSet(JSONObject properties) {
        try {
            trackEvent(EventType.PROFILE_SET, null, properties, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: protected */
    public void profileSet(String property, Object value) {
        try {
            trackEvent(EventType.PROFILE_SET, null, new JSONObject().put(property, value), null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: protected */
    public void profileSetOnce(JSONObject properties) {
        try {
            trackEvent(EventType.PROFILE_SET_ONCE, null, properties, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: protected */
    public void profileSetOnce(String property, Object value) {
        try {
            trackEvent(EventType.PROFILE_SET_ONCE, null, new JSONObject().put(property, value), null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: protected */
    public void profileIncrement(Map<String, ? extends Number> properties) {
        try {
            trackEvent(EventType.PROFILE_INCREMENT, null, new JSONObject(properties), null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: protected */
    public void profileIncrement(String property, Number value) {
        try {
            trackEvent(EventType.PROFILE_INCREMENT, null, new JSONObject().put(property, value), null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: protected */
    public void profileAppend(String property, String value) {
        try {
            JSONArray append_values = new JSONArray();
            append_values.put(value);
            JSONObject properties = new JSONObject();
            properties.put(property, append_values);
            trackEvent(EventType.PROFILE_APPEND, null, properties, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: protected */
    public void profileAppend(String property, Set<String> values) {
        try {
            JSONArray append_values = new JSONArray();
            for (String value : values) {
                append_values.put(value);
            }
            JSONObject properties = new JSONObject();
            properties.put(property, append_values);
            trackEvent(EventType.PROFILE_APPEND, null, properties, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: protected */
    public void profileUnset(String property) {
        try {
            trackEvent(EventType.PROFILE_UNSET, null, new JSONObject().put(property, true), null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: protected */
    public void profileDelete() {
        try {
            trackEvent(EventType.PROFILE_DELETE, null, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean isDebugMode() {
        return this.mDebugMode.isDebugMode();
    }

    /* access modifiers changed from: 0000 */
    public boolean isDebugWriteData() {
        return this.mDebugMode.isDebugWriteData();
    }

    /* access modifiers changed from: 0000 */
    public String getServerUrl() {
        return this.mServerUrl;
    }

    /* access modifiers changed from: 0000 */
    public String getConfigureUrl() {
        return this.mConfigureUrl;
    }

    private void showDebugModeWarning() {
        try {
            if (this.mDebugMode != DebugMode.DEBUG_OFF) {
                String info = null;
                if (this.mDebugMode == DebugMode.DEBUG_ONLY) {
                    info = "现在您打开了神策SDK的'DEBUG_ONLY'模式，此模式下只校验数据但不导入数据，数据出错时会以 Toast 的方式提示开发者，请上线前一定使用 DEBUG_OFF 模式。";
                } else if (this.mDebugMode == DebugMode.DEBUG_AND_TRACK) {
                    info = "现在您打开了神策SDK的'DEBUG_AND_TRACK'模式，此模式下校验数据并且导入数据，数据出错时会以 Toast 的方式提示开发者，请上线前一定使用 DEBUG_OFF 模式。";
                }
                Toast.makeText(this.mContext, info, 1).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void trackEventFromH5(java.lang.String r19) {
        /*
            r18 = this;
            boolean r15 = android.text.TextUtils.isEmpty(r19)     // Catch:{ Exception -> 0x00f8 }
            if (r15 == 0) goto L_0x0007
        L_0x0006:
            return
        L_0x0007:
            org.json.JSONObject r5 = new org.json.JSONObject     // Catch:{ Exception -> 0x00f8 }
            r0 = r19
            r5.<init>(r0)     // Catch:{ Exception -> 0x00f8 }
            java.lang.String r15 = "type"
            java.lang.String r14 = r5.getString(r15)     // Catch:{ Exception -> 0x00f8 }
            java.lang.String r15 = r14.toUpperCase()     // Catch:{ Exception -> 0x00f8 }
            com.sensorsdata.analytics.android.sdk.EventType r6 = com.sensorsdata.analytics.android.sdk.EventType.valueOf(r15)     // Catch:{ Exception -> 0x00f8 }
            java.lang.String r2 = "distinct_id"
            com.sensorsdata.analytics.android.sdk.EventType r15 = com.sensorsdata.analytics.android.sdk.EventType.TRACK_SIGNUP     // Catch:{ Exception -> 0x00f8 }
            if (r6 != r15) goto L_0x0025
            java.lang.String r2 = "original_id"
        L_0x0025:
            java.lang.String r15 = r18.getLoginId()     // Catch:{ Exception -> 0x00f8 }
            boolean r15 = android.text.TextUtils.isEmpty(r15)     // Catch:{ Exception -> 0x00f8 }
            if (r15 != 0) goto L_0x00fe
            java.lang.String r15 = r18.getLoginId()     // Catch:{ Exception -> 0x00f8 }
            r5.put(r2, r15)     // Catch:{ Exception -> 0x00f8 }
        L_0x0036:
            java.lang.String r15 = "time"
            long r16 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x00f8 }
            r0 = r16
            r5.put(r15, r0)     // Catch:{ Exception -> 0x00f8 }
            java.util.Random r12 = new java.util.Random     // Catch:{ Exception -> 0x019d }
            r12.<init>()     // Catch:{ Exception -> 0x019d }
            java.lang.String r15 = "_track_id"
            int r16 = r12.nextInt()     // Catch:{ Exception -> 0x019d }
            r0 = r16
            r5.put(r15, r0)     // Catch:{ Exception -> 0x019d }
        L_0x0051:
            java.lang.String r15 = "properties"
            org.json.JSONObject r11 = r5.optJSONObject(r15)     // Catch:{ Exception -> 0x00f8 }
            if (r11 != 0) goto L_0x005e
            org.json.JSONObject r11 = new org.json.JSONObject     // Catch:{ Exception -> 0x00f8 }
            r11.<init>()     // Catch:{ Exception -> 0x00f8 }
        L_0x005e:
            java.lang.String r15 = "lib"
            org.json.JSONObject r8 = r5.optJSONObject(r15)     // Catch:{ Exception -> 0x00f8 }
            if (r8 == 0) goto L_0x00a8
            r0 = r18
            java.util.Map<java.lang.String, java.lang.Object> r15 = r0.mDeviceInfo     // Catch:{ Exception -> 0x00f8 }
            java.lang.String r16 = "$app_version"
            boolean r15 = r15.containsKey(r16)     // Catch:{ Exception -> 0x00f8 }
            if (r15 == 0) goto L_0x0085
            java.lang.String r15 = "$app_version"
            r0 = r18
            java.util.Map<java.lang.String, java.lang.Object> r0 = r0.mDeviceInfo     // Catch:{ Exception -> 0x00f8 }
            r16 = r0
            java.lang.String r17 = "$app_version"
            java.lang.Object r16 = r16.get(r17)     // Catch:{ Exception -> 0x00f8 }
            r0 = r16
            r8.put(r15, r0)     // Catch:{ Exception -> 0x00f8 }
        L_0x0085:
            r0 = r18
            com.sensorsdata.analytics.android.sdk.PersistentSuperProperties r15 = r0.mSuperProperties     // Catch:{ Exception -> 0x00f8 }
            java.lang.Object r13 = r15.get()     // Catch:{ Exception -> 0x00f8 }
            org.json.JSONObject r13 = (org.json.JSONObject) r13     // Catch:{ Exception -> 0x00f8 }
            if (r13 == 0) goto L_0x00a8
            java.lang.String r15 = "$app_version"
            boolean r15 = r13.has(r15)     // Catch:{ Exception -> 0x00f8 }
            if (r15 == 0) goto L_0x00a8
            java.lang.String r15 = "$app_version"
            java.lang.String r16 = "$app_version"
            r0 = r16
            java.lang.Object r16 = r13.get(r0)     // Catch:{ Exception -> 0x00f8 }
            r0 = r16
            r8.put(r15, r0)     // Catch:{ Exception -> 0x00f8 }
        L_0x00a8:
            boolean r15 = r6.isTrack()     // Catch:{ Exception -> 0x00f8 }
            if (r15 == 0) goto L_0x0154
            r0 = r18
            java.util.Map<java.lang.String, java.lang.Object> r15 = r0.mDeviceInfo     // Catch:{ Exception -> 0x00f8 }
            if (r15 == 0) goto L_0x0107
            r0 = r18
            java.util.Map<java.lang.String, java.lang.Object> r15 = r0.mDeviceInfo     // Catch:{ Exception -> 0x00f8 }
            java.util.Set r15 = r15.entrySet()     // Catch:{ Exception -> 0x00f8 }
            java.util.Iterator r16 = r15.iterator()     // Catch:{ Exception -> 0x00f8 }
        L_0x00c0:
            boolean r15 = r16.hasNext()     // Catch:{ Exception -> 0x00f8 }
            if (r15 == 0) goto L_0x0107
            java.lang.Object r4 = r16.next()     // Catch:{ Exception -> 0x00f8 }
            java.util.Map$Entry r4 = (java.util.Map.Entry) r4     // Catch:{ Exception -> 0x00f8 }
            java.lang.Object r7 = r4.getKey()     // Catch:{ Exception -> 0x00f8 }
            java.lang.String r7 = (java.lang.String) r7     // Catch:{ Exception -> 0x00f8 }
            boolean r15 = android.text.TextUtils.isEmpty(r7)     // Catch:{ Exception -> 0x00f8 }
            if (r15 != 0) goto L_0x00c0
            java.lang.String r15 = "$lib"
            boolean r15 = r15.equals(r7)     // Catch:{ Exception -> 0x00f8 }
            if (r15 != 0) goto L_0x00c0
            java.lang.String r15 = "$lib_version"
            boolean r15 = r15.equals(r7)     // Catch:{ Exception -> 0x00f8 }
            if (r15 != 0) goto L_0x00c0
            java.lang.Object r15 = r4.getKey()     // Catch:{ Exception -> 0x00f8 }
            java.lang.String r15 = (java.lang.String) r15     // Catch:{ Exception -> 0x00f8 }
            java.lang.Object r17 = r4.getValue()     // Catch:{ Exception -> 0x00f8 }
            r0 = r17
            r11.put(r15, r0)     // Catch:{ Exception -> 0x00f8 }
            goto L_0x00c0
        L_0x00f8:
            r3 = move-exception
            r3.printStackTrace()
            goto L_0x0006
        L_0x00fe:
            java.lang.String r15 = r18.getAnonymousId()     // Catch:{ Exception -> 0x00f8 }
            r5.put(r2, r15)     // Catch:{ Exception -> 0x00f8 }
            goto L_0x0036
        L_0x0107:
            r0 = r18
            android.content.Context r15 = r0.mContext     // Catch:{ Exception -> 0x00f8 }
            java.lang.String r10 = com.sensorsdata.analytics.android.sdk.util.SensorsDataUtils.networkType(r15)     // Catch:{ Exception -> 0x00f8 }
            java.lang.String r15 = "networkInfo"
            r11.put(r15, r10)     // Catch:{ Exception -> 0x00f8 }
            r0 = r18
            com.sensorsdata.analytics.android.sdk.PersistentSuperProperties r0 = r0.mSuperProperties     // Catch:{ Exception -> 0x00f8 }
            r16 = r0
            monitor-enter(r16)     // Catch:{ Exception -> 0x00f8 }
            r0 = r18
            com.sensorsdata.analytics.android.sdk.PersistentSuperProperties r15 = r0.mSuperProperties     // Catch:{ all -> 0x0191 }
            java.lang.Object r13 = r15.get()     // Catch:{ all -> 0x0191 }
            org.json.JSONObject r13 = (org.json.JSONObject) r13     // Catch:{ all -> 0x0191 }
            com.sensorsdata.analytics.android.sdk.util.SensorsDataUtils.mergeJSONObject(r13, r11)     // Catch:{ all -> 0x0191 }
            monitor-exit(r16)     // Catch:{ all -> 0x0191 }
            boolean r15 = r6.isTrack()     // Catch:{ Exception -> 0x00f8 }
            if (r15 == 0) goto L_0x013a
            java.lang.String r15 = "$is_first_day"
            boolean r16 = r18.isFirstDay()     // Catch:{ Exception -> 0x00f8 }
            r0 = r16
            r11.put(r15, r0)     // Catch:{ Exception -> 0x00f8 }
        L_0x013a:
            java.lang.String r15 = "$is_first_time"
            boolean r15 = r11.has(r15)     // Catch:{ Exception -> 0x00f8 }
            if (r15 == 0) goto L_0x0147
            java.lang.String r15 = "$is_first_time"
            r11.remove(r15)     // Catch:{ Exception -> 0x00f8 }
        L_0x0147:
            java.lang.String r15 = "_nocache"
            boolean r15 = r11.has(r15)     // Catch:{ Exception -> 0x00f8 }
            if (r15 == 0) goto L_0x0154
            java.lang.String r15 = "_nocache"
            r11.remove(r15)     // Catch:{ Exception -> 0x00f8 }
        L_0x0154:
            com.sensorsdata.analytics.android.sdk.EventType r15 = com.sensorsdata.analytics.android.sdk.EventType.TRACK_SIGNUP     // Catch:{ Exception -> 0x00f8 }
            if (r6 != r15) goto L_0x0194
            java.lang.String r15 = "distinct_id"
            java.lang.String r9 = r5.getString(r15)     // Catch:{ Exception -> 0x00f8 }
            r0 = r18
            com.sensorsdata.analytics.android.sdk.PersistentLoginId r0 = r0.mLoginId     // Catch:{ Exception -> 0x00f8 }
            r16 = r0
            monitor-enter(r16)     // Catch:{ Exception -> 0x00f8 }
            r0 = r18
            com.sensorsdata.analytics.android.sdk.PersistentLoginId r15 = r0.mLoginId     // Catch:{ all -> 0x018e }
            java.lang.Object r15 = r15.get()     // Catch:{ all -> 0x018e }
            boolean r15 = r9.equals(r15)     // Catch:{ all -> 0x018e }
            if (r15 != 0) goto L_0x018b
            r0 = r18
            com.sensorsdata.analytics.android.sdk.PersistentLoginId r15 = r0.mLoginId     // Catch:{ all -> 0x018e }
            r15.commit(r9)     // Catch:{ all -> 0x018e }
            java.lang.String r15 = r18.getAnonymousId()     // Catch:{ all -> 0x018e }
            boolean r15 = r9.equals(r15)     // Catch:{ all -> 0x018e }
            if (r15 != 0) goto L_0x018b
            r0 = r18
            com.sensorsdata.analytics.android.sdk.AnalyticsMessages r15 = r0.mMessages     // Catch:{ all -> 0x018e }
            r15.enqueueEventMessage(r14, r5)     // Catch:{ all -> 0x018e }
        L_0x018b:
            monitor-exit(r16)     // Catch:{ all -> 0x018e }
            goto L_0x0006
        L_0x018e:
            r15 = move-exception
            monitor-exit(r16)     // Catch:{ all -> 0x018e }
            throw r15     // Catch:{ Exception -> 0x00f8 }
        L_0x0191:
            r15 = move-exception
            monitor-exit(r16)     // Catch:{ all -> 0x0191 }
            throw r15     // Catch:{ Exception -> 0x00f8 }
        L_0x0194:
            r0 = r18
            com.sensorsdata.analytics.android.sdk.AnalyticsMessages r15 = r0.mMessages     // Catch:{ Exception -> 0x00f8 }
            r15.enqueueEventMessage(r14, r5)     // Catch:{ Exception -> 0x00f8 }
            goto L_0x0006
        L_0x019d:
            r15 = move-exception
            goto L_0x0051
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sensorsdata.analytics.android.sdk.SensorsDataAPI.trackEventFromH5(java.lang.String):void");
    }

    private void trackEvent(EventType eventType, String eventName, JSONObject properties, String originalDistinctId) throws InvalidDataException {
        trackEvent(eventType, eventName, properties, originalDistinctId, false);
    }

    private void trackEvent(EventType eventType, String eventName, JSONObject properties, String originalDistinctId, boolean isFlush) throws InvalidDataException {
        Log.i(TAG, "trackEvent begin");
        final EventType eventType2 = eventType;
        final String str = eventName;
        final JSONObject jSONObject = properties;
        final String str2 = originalDistinctId;
        final boolean z = isFlush;
        SensorsDataThreadPool.getInstance().execute(new Runnable() {
            public void run() {
                EventTimer eventTimer;
                JSONObject sendProperties;
                try {
                    if (eventType2.isTrack()) {
                        SensorsDataAPI.this.assertEvent(str);
                    }
                    SensorsDataAPI.this.assertPropertyTypes(eventType2, jSONObject);
                    long now = System.currentTimeMillis();
                    if (str != null) {
                        synchronized (SensorsDataAPI.this.mTrackTimer) {
                            eventTimer = (EventTimer) SensorsDataAPI.this.mTrackTimer.get(str);
                            SensorsDataAPI.this.mTrackTimer.remove(str);
                        }
                    } else {
                        eventTimer = null;
                    }
                    try {
                        if (eventType2.isTrack()) {
                            JSONObject jSONObject = new JSONObject(SensorsDataAPI.this.mDeviceInfo);
                            try {
                                if (SensorsDataAPI.this.mCustomProperties != null) {
                                    SensorsDataUtils.mergeJSONObject(new JSONObject(SensorsDataAPI.this.mCustomProperties), jSONObject);
                                }
                                synchronized (SensorsDataAPI.this.mSuperProperties) {
                                    SensorsDataUtils.mergeJSONObject((JSONObject) SensorsDataAPI.this.mSuperProperties.get(), jSONObject);
                                }
                                jSONObject.put("networkInfo", SensorsDataUtils.networkType(SensorsDataAPI.this.mContext));
                                sendProperties = jSONObject;
                            } catch (JSONException e) {
                                JSONObject jSONObject2 = jSONObject;
                            }
                        } else if (eventType2.isProfile()) {
                            sendProperties = new JSONObject();
                        } else {
                            return;
                        }
                        if (jSONObject != null) {
                            SensorsDataUtils.mergeJSONObject(jSONObject, sendProperties);
                        }
                        if (eventTimer != null) {
                            try {
                                Double duration = Double.valueOf(eventTimer.duration());
                                if (duration.doubleValue() > 0.0d) {
                                    sendProperties.put("event_duration", duration);
                                }
                            } catch (Exception e2) {
                                e2.printStackTrace();
                            }
                        }
                        JSONObject libProperties = new JSONObject();
                        libProperties.put("$lib", "Android");
                        libProperties.put("$lib_version", SensorsDataAPI.VERSION);
                        if (SensorsDataAPI.this.mDeviceInfo.containsKey("$app_version")) {
                            libProperties.put("$app_version", SensorsDataAPI.this.mDeviceInfo.get("$app_version"));
                        }
                        JSONObject superProperties = (JSONObject) SensorsDataAPI.this.mSuperProperties.get();
                        if (superProperties != null && superProperties.has("$app_version")) {
                            libProperties.put("$app_version", superProperties.get("$app_version"));
                        }
                        JSONObject dataObj = new JSONObject();
                        try {
                            dataObj.put("_track_id", new Random().nextInt());
                        } catch (Exception e3) {
                        }
                        String str = "eventId";
                        sendProperties.put(str, str);
                        sendProperties.put("logTime", now);
                        dataObj.put("time", now);
                        dataObj.put("type", eventType2.getEventType());
                        dataObj.put("properties", sendProperties);
                        if (!TextUtils.isEmpty(SensorsDataAPI.this.getLoginId())) {
                            dataObj.put("distinct_id", SensorsDataAPI.this.getLoginId());
                        } else {
                            dataObj.put("distinct_id", SensorsDataAPI.this.getAnonymousId());
                        }
                        dataObj.put("lib", libProperties);
                        if (eventType2 == EventType.TRACK) {
                            dataObj.put(NotificationCompat.CATEGORY_EVENT, str);
                            sendProperties.put("isFirstDay", SensorsDataAPI.this.isFirstDay());
                        } else {
                            if (eventType2 == EventType.TRACK_SIGNUP) {
                                dataObj.put(NotificationCompat.CATEGORY_EVENT, str);
                                dataObj.put("original_id", str2);
                            }
                        }
                        boolean isDepolyed = sendProperties.optBoolean("$binding_depolyed", true);
                        if (sendProperties.has("$binding_depolyed")) {
                            libProperties.put("$lib_method", "vtrack");
                            libProperties.put("$lib_detail", sendProperties.get("$binding_trigger_id").toString());
                            if (SensorsDataAPI.this.mVTrack instanceof DebugTracking) {
                                ((DebugTracking) SensorsDataAPI.this.mVTrack).reportTrack(new JSONObject(dataObj.toString()));
                            }
                            sendProperties.remove("$binding_path");
                            sendProperties.remove("$binding_depolyed");
                            sendProperties.remove("$binding_trigger_id");
                        } else {
                            libProperties.put("$lib_method", "code");
                            String libDetail = null;
                            if (SensorsDataAPI.this.mAutoTrack && jSONObject != null && (AutoTrackEventType.APP_VIEW_SCREEN.getEventName().equals(str) || AutoTrackEventType.APP_CLICK.getEventName().equals(str) || AutoTrackEventType.APP_START.getEventName().equals(str) || AutoTrackEventType.APP_END.getEventName().equals(str))) {
                                AutoTrackEventType trackEventType = AutoTrackEventType.autoTrackEventTypeFromEventName(str);
                                if (trackEventType != null && SensorsDataAPI.this.mAutoTrackEventTypeList.contains(trackEventType) && jSONObject.has(AopConstants.SCREEN_NAME)) {
                                    String screenName = jSONObject.getString(AopConstants.SCREEN_NAME);
                                    if (!TextUtils.isEmpty(screenName)) {
                                        String[] screenNameArray = screenName.split("\\|");
                                        if (screenNameArray.length > 0) {
                                            libDetail = String.format("%s##%s##%s##%s", new Object[]{screenNameArray[0], "", "", ""});
                                        }
                                    }
                                }
                            }
                            if (TextUtils.isEmpty(libDetail)) {
                                StackTraceElement[] trace = new Exception().getStackTrace();
                                if (trace.length > 2) {
                                    StackTraceElement traceElement = trace[2];
                                    libDetail = String.format("%s##%s##%s##%s", new Object[]{traceElement.getClassName(), traceElement.getMethodName(), traceElement.getFileName(), Integer.valueOf(traceElement.getLineNumber())});
                                }
                            }
                            libProperties.put("$lib_detail", libDetail);
                        }
                        if (isDepolyed) {
                            SensorsDataAPI.this.mMessages.enqueueEventMessage(eventType2.getEventType(), dataObj);
                            SALog.m1608i(SensorsDataAPI.TAG, "track event:\n" + JSONUtils.formatJson(dataObj.toString()));
                            if (z) {
                                SensorsDataAPI.this.flush();
                            }
                        }
                    } catch (JSONException e4) {
                        throw new InvalidDataException("Unexpected property");
                    }
                } catch (Exception e5) {
                    e5.printStackTrace();
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public boolean isFirstDay() {
        return ((String) this.mFirstDay.get()).equals(mIsFirstDayDateFormat.format(Long.valueOf(System.currentTimeMillis())));
    }

    /* access modifiers changed from: private */
    public void assertPropertyTypes(EventType eventType, JSONObject properties) throws InvalidDataException {
        if (properties != null) {
            Iterator iterator = properties.keys();
            while (iterator.hasNext()) {
                String key = (String) iterator.next();
                assertKey(key);
                try {
                    Object value = properties.get(key);
                    if (!(value instanceof String) && !(value instanceof Number) && !(value instanceof JSONArray) && !(value instanceof Boolean) && !(value instanceof Date)) {
                        throw new InvalidDataException("The property value must be an instance of String/Number/Boolean/JSONArray. [key='" + key + "', value='" + value.toString() + "']");
                    } else if ((value instanceof String) && !key.startsWith("$") && ((String) value).length() > 8191) {
                        SALog.m1606d(TAG, "The property value is too long. [key='" + key + "', value='" + value.toString() + "']");
                    }
                } catch (JSONException e) {
                    throw new InvalidDataException("Unexpected property key. [key='" + key + "']");
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void assertEvent(String eventName) throws InvalidDataException {
        if (eventName == null || eventName.length() < 1) {
            throw new InvalidDataException("Event name is empty.");
        } else if (!EVENT_PATTERN.matcher(eventName).matches()) {
            throw new InvalidDataException("Event name '" + eventName + "' is invalid.");
        }
    }

    private void assertKey(String key) throws InvalidDataException {
        if (key == null || key.length() < 1) {
            throw new InvalidDataException("The key is empty.");
        }
    }

    private void assertDistinctId(String key) throws InvalidDataException {
        if (key == null || key.length() < 1) {
            throw new InvalidDataException("The distinct_id or original_id or login_id is empty.");
        } else if (key.length() > 255) {
            throw new InvalidDataException("The max length of distinct_id or original_id or login_id is 255.");
        }
    }
}
