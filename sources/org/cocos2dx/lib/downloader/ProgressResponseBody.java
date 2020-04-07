package org.cocos2dx.lib.downloader;

import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

public class ProgressResponseBody extends ResponseBody {
    private BufferedSource bufferedSource;
    /* access modifiers changed from: private */
    public final ProgressListener progressListener;
    /* access modifiers changed from: private */
    public final ResponseBody responseBody;
    private final int taskId;

    public interface ProgressListener {
        void update(int i, long j, long j2, long j3, boolean z);
    }

    public ProgressResponseBody(int taskId2, ResponseBody responseBody2, ProgressListener progressListener2) {
        this.taskId = taskId2;
        this.responseBody = responseBody2;
        this.progressListener = progressListener2;
    }

    public MediaType contentType() {
        return this.responseBody.contentType();
    }

    public long contentLength() {
        return this.responseBody.contentLength();
    }

    public BufferedSource source() {
        if (this.bufferedSource == null) {
            this.bufferedSource = Okio.buffer(source(this.taskId, this.responseBody.source()));
        }
        return this.bufferedSource;
    }

    private Source source(final int taskId2, Source source) {
        return new ForwardingSource(source) {
            long totalBytesRead = 0;

            public long read(Buffer sink, long byteCount) throws IOException {
                long j;
                long byteReadFix;
                long bytesRead = super.read(sink, byteCount);
                long j2 = this.totalBytesRead;
                if (bytesRead != -1) {
                    j = bytesRead;
                } else {
                    j = 0;
                }
                this.totalBytesRead = j + j2;
                if (bytesRead != -1) {
                    byteReadFix = bytesRead;
                } else {
                    byteReadFix = 0;
                }
                ProgressResponseBody.this.progressListener.update(taskId2, byteReadFix, this.totalBytesRead, ProgressResponseBody.this.responseBody.contentLength(), bytesRead == -1);
                return bytesRead;
            }
        };
    }
}
