package org.cocos2dx.lib.downloader;

import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Interceptor.Chain;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import org.cocos2dx.lib.downloader.ProgressResponseBody.ProgressListener;

public class ProgressHelper {
    public static OkHttpClient addProgressResponseListener(final int taskId, OkHttpClient client, final ProgressListener progressListener) {
        return client.newBuilder().addInterceptor(new Interceptor() {
            public Response intercept(Chain chain) throws IOException {
                Response originalResponse = chain.proceed(chain.request());
                return originalResponse.newBuilder().body(new ProgressResponseBody(taskId, originalResponse.body(), progressListener)).build();
            }
        }).build();
    }
}
