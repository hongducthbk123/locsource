package com.baitian.datasdk.http;

public interface OkHttpCallBack<T> {
    void onFailure();

    void onResponse(T t);
}
