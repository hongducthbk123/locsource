package com.btgame.seasdk.btcore.common.util.http;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.btgame.seasdk.btcore.common.util.BtsdkLog;
import com.btgame.seasdk.btcore.common.util.BuglyHelper;
import com.btgame.seasdk.btcore.common.util.NetworkUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpUtil {
    private static OkHttpUtil mInstance;
    /* access modifiers changed from: private */
    public Context context;
    /* access modifiers changed from: private */
    public Gson gson;
    private Handler mDelivery = new Handler(Looper.getMainLooper());
    private OkHttpClient mOkHttpClient = new Builder().connectTimeout(15, TimeUnit.SECONDS).cookieJar(new CookieJar() {
        private final HashMap<HttpUrl, List<Cookie>> cookieStore = new HashMap<>();

        public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
            this.cookieStore.put(url, cookies);
        }

        public List<Cookie> loadForRequest(HttpUrl url) {
            List<Cookie> cookies = (List) this.cookieStore.get(url);
            return cookies != null ? cookies : new ArrayList<>();
        }
    }).retryOnConnectionFailure(true).build();

    @SuppressLint({"NewApi"})
    private OkHttpUtil(Context ctx) {
        this.context = ctx.getApplicationContext();
        GsonBuilder gb = new GsonBuilder();
        gb.disableHtmlEscaping();
        this.gson = gb.create();
    }

    public static OkHttpUtil getInstance(Context context2) {
        if (mInstance == null) {
            synchronized (OkHttpUtil.class) {
                if (mInstance == null) {
                    mInstance = new OkHttpUtil(context2);
                }
            }
        }
        return mInstance;
    }

    public Handler getDelivery() {
        return this.mDelivery;
    }

    public OkHttpClient getOkHttpClient() {
        return this.mOkHttpClient;
    }

    public Gson getGson() {
        return this.gson;
    }

    public void postJsonDataAsynNormal(String url, String json, final OkHttpCallBack ocb, final Class resultClass) {
        MediaType jsonReq = MediaType.parse("application/json; charset=utf-8");
        BtsdkLog.m1429d("请求url:" + url);
        BtsdkLog.m1429d("请求json:" + json);
        final Request request = new Request.Builder().url(url).post(RequestBody.create(jsonReq, json)).header("Connection", "close").build();
        this.mOkHttpClient.newCall(request).enqueue(new Callback() {
            public void onFailure(Call call, IOException e) {
                BuglyHelper.postCatchedException(request.toString() + " , Exception Msg = " + e.toString());
                BtsdkLog.m1430e(e.getMessage());
                OkHttpUtil.this.sendFailResultCallback(ocb);
            }

            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    BtsdkLog.m1429d("okhttp respone  isSuccessful Thread name = " + Thread.currentThread().getName());
                    String responseJson = HttpDataUtil.convertStreamToString(response.body().byteStream());
                    BtsdkLog.m1429d("responseJson：" + responseJson);
                    OkHttpUtil.this.sendSuccessResultCallback(OkHttpUtil.this.gson.fromJson(responseJson, resultClass), ocb);
                    return;
                }
                BuglyHelper.postCatchedException("Response message : " + response.toString());
                OkHttpUtil.this.sendFailResultCallback(ocb);
                BtsdkLog.m1429d("okhttp respone  is fail " + response.toString());
            }
        });
    }

    public void postdateformSumbitAsynNorma(String url, Map<String, String> params, final OkHttpCallBack<Object> ocb, final Class resultClass) {
        MediaType parse = MediaType.parse("application/x-www-form-urlencoded;charset=utf-8");
        FormBody.Builder builder = new FormBody.Builder();
        for (String key : params.keySet()) {
            builder.add(key, (String) params.get(key));
        }
        final Request request = new Request.Builder().url(url).post(builder.build()).header("Connection", "close").build();
        this.mOkHttpClient.newCall(request).enqueue(new Callback() {
            public void onFailure(Call call, IOException e) {
                BuglyHelper.postCatchedException(request.toString() + " , Exception Msg = " + e.toString() + "networkname : " + NetworkUtils.getNetworkTypeName(OkHttpUtil.this.context));
                BtsdkLog.m1430e(e.getMessage());
                OkHttpUtil.this.sendFailResultCallback(ocb);
            }

            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    BtsdkLog.m1429d("okhttp respone  isSuccessful Thread name = " + Thread.currentThread().getName());
                    OkHttpUtil.this.sendSuccessResultCallback(OkHttpUtil.this.gson.fromJson(HttpDataUtil.readJsonData(response.body().byteStream()), resultClass), ocb);
                    return;
                }
                BuglyHelper.postCatchedException("Response message : " + response.toString() + "networkname : " + NetworkUtils.getNetworkTypeName(OkHttpUtil.this.context));
                BtsdkLog.m1429d("okhttp respone  isfail  ");
                OkHttpUtil.this.sendFailResultCallback(ocb);
            }
        });
    }

    public void postJsonDataAsyn(String url, String json, final OkHttpCallBack ocb, final Class resultClass) {
        BtsdkLog.m1429d("-----" + url);
        BtsdkLog.m1429d("-----" + json);
        byte[] bytes = HttpDataUtil.getDatabytes(json);
        BtsdkLog.m1429d(new String(bytes));
        final Request request = new Request.Builder().url(url).post(RequestBody.create(MediaType.parse("text/plain; charset=utf-8"), bytes)).header("Connection", "close").build();
        this.mOkHttpClient.newCall(request).enqueue(new Callback() {
            public void onFailure(Call call, IOException e) {
                BuglyHelper.postCatchedException(request.toString() + " , Exception Msg = " + e.toString() + "networkname : " + NetworkUtils.getNetworkTypeName(OkHttpUtil.this.context));
                BtsdkLog.m1430e(e.getMessage());
                OkHttpUtil.this.sendFailResultCallback(ocb);
            }

            public void onResponse(Call call, Response response) throws IOException {
                BtsdkLog.m1429d("response code --->" + response.code());
                BtsdkLog.m1429d("response message --->" + response.message());
                if (response.isSuccessful()) {
                    String responseJson = HttpDataUtil.readJsonData(response.body().byteStream());
                    BtsdkLog.m1429d("response responseJson --->" + responseJson);
                    OkHttpUtil.this.sendSuccessResultCallback(OkHttpUtil.this.gson.fromJson(responseJson, resultClass), ocb);
                    return;
                }
                BtsdkLog.m1429d("okhttp respone  isfail  ");
                BuglyHelper.postCatchedException("Response message : " + response.toString() + "networkname : " + NetworkUtils.getNetworkTypeName(OkHttpUtil.this.context));
                OkHttpUtil.this.sendFailResultCallback(ocb);
            }
        });
    }

    public void postJsonDataAsynUncode(String url, Map<String, String> params, final OkHttpCallBack<Object> ocb, final Class resultClass) {
        MediaType parse = MediaType.parse("application/x-www-form-urlencoded;charset=utf-8");
        FormBody.Builder builder = new FormBody.Builder();
        for (String key : params.keySet()) {
            builder.add(key, (String) params.get(key));
        }
        final Request request = new Request.Builder().url(url).post(builder.build()).header("Connection", "close").build();
        this.mOkHttpClient.newCall(request).enqueue(new Callback() {
            public void onFailure(Call call, IOException e) {
                BuglyHelper.postCatchedException(request.toString() + " , Exception Msg = " + e.toString() + "networkname : " + NetworkUtils.getNetworkTypeName(OkHttpUtil.this.context));
                BtsdkLog.m1430e(e.getMessage());
                OkHttpUtil.this.sendFailResultCallback(ocb);
            }

            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseJson = HttpDataUtil.convertStreamToString(response.body().byteStream());
                    BtsdkLog.m1429d("okhttp respone  isSuccessful responseJson = " + responseJson);
                    OkHttpUtil.this.sendSuccessResultCallback(OkHttpUtil.this.gson.fromJson(responseJson, resultClass), ocb);
                    return;
                }
                BuglyHelper.postCatchedException("Response message : " + response.toString() + "networkname : " + NetworkUtils.getNetworkTypeName(OkHttpUtil.this.context));
                BtsdkLog.m1429d("okhttp respone  isfail  ");
                OkHttpUtil.this.sendFailResultCallback(ocb);
            }
        });
    }

    /* access modifiers changed from: private */
    public void sendFailResultCallback(final OkHttpCallBack<Object> callback) {
        if (callback != null) {
            this.mDelivery.post(new Runnable() {
                public void run() {
                    callback.onFailure();
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void sendSuccessResultCallback(final Object object, final OkHttpCallBack<Object> callback) {
        if (callback != null) {
            this.mDelivery.post(new Runnable() {
                public void run() {
                    callback.onResponse(object);
                }
            });
        }
    }

    public void destory() {
        this.gson = null;
        this.context = null;
        mInstance = null;
    }
}
