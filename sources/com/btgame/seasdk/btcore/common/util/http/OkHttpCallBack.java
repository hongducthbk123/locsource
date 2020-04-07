package com.btgame.seasdk.btcore.common.util.http;

public interface OkHttpCallBack<T> {
    void onFailure();

    void onResponse(T t);
}
