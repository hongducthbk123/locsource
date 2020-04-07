package com.btgame.sdk.http;

public interface OkHttpCallBack<T> {
    void onFailure();

    void onResponse(T t);
}
