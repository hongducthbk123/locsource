package org.cocos2dx.lib.downloader;

import okhttp3.Call;

public class DownloadTaskOk {
    public long bytesReceived;
    public Call call;
    public byte[] data;
    public long totalBytesExpected;
    public long totalBytesReceived;

    public DownloadTaskOk() {
        resetStatus();
    }

    public void resetStatus() {
        this.bytesReceived = 0;
        this.totalBytesReceived = 0;
        this.totalBytesExpected = 0;
        this.data = null;
        this.call = null;
    }
}
