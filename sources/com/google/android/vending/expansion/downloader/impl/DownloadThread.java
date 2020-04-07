package com.google.android.vending.expansion.downloader.impl;

import android.content.Context;
import android.net.Proxy;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.os.Process;
import android.util.Log;
import com.google.android.vending.expansion.downloader.Constants;
import com.google.android.vending.expansion.downloader.Helpers;
import com.google.android.vending.expansion.downloader.impl.DownloaderService.GenerateSaveFileError;
import com.google.common.net.HttpHeaders;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Locale;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.params.ConnRouteParams;
import org.apache.http.protocol.HTTP;
import p004cn.jiguang.net.HttpUtils;

public class DownloadThread {
    private Context mContext;
    private final DownloadsDB mDB;
    private DownloadInfo mInfo;
    private final DownloadNotification mNotification;
    private DownloaderService mService;
    private String mUserAgent;

    private static class InnerState {
        public int mBytesNotified;
        public int mBytesSoFar;
        public int mBytesThisSession;
        public boolean mContinuingDownload;
        public String mHeaderContentDisposition;
        public String mHeaderContentLength;
        public String mHeaderContentLocation;
        public String mHeaderETag;
        public long mTimeLastNotification;

        private InnerState() {
            this.mBytesSoFar = 0;
            this.mBytesThisSession = 0;
            this.mContinuingDownload = false;
            this.mBytesNotified = 0;
            this.mTimeLastNotification = 0;
        }
    }

    private class RetryDownload extends Throwable {
        private static final long serialVersionUID = 6196036036517540229L;

        private RetryDownload() {
        }
    }

    private static class State {
        public boolean mCountRetry = false;
        public String mFilename;
        public boolean mGotData = false;
        public String mNewUri;
        public int mRedirectCount = 0;
        public String mRequestUri;
        public int mRetryAfter = 0;
        public FileOutputStream mStream;

        public State(DownloadInfo info, DownloaderService service) {
            this.mRedirectCount = info.mRedirectCount;
            this.mRequestUri = info.mUri;
            this.mFilename = service.generateTempSaveFileName(info.mFileName);
        }
    }

    private class StopRequest extends Throwable {
        private static final long serialVersionUID = 6338592678988347973L;
        public int mFinalStatus;

        public StopRequest(int finalStatus, String message) {
            super(message);
            this.mFinalStatus = finalStatus;
        }

        public StopRequest(int finalStatus, String message, Throwable throwable) {
            super(message, throwable);
            this.mFinalStatus = finalStatus;
        }
    }

    public DownloadThread(DownloadInfo info, DownloaderService service, DownloadNotification notification) {
        this.mContext = service;
        this.mInfo = info;
        this.mService = service;
        this.mNotification = notification;
        this.mDB = DownloadsDB.getDB(service);
        this.mUserAgent = "APKXDL (Linux; U; Android " + VERSION.RELEASE + ";" + Locale.getDefault().toString() + "; " + Build.DEVICE + HttpUtils.PATHS_SEPARATOR + Build.ID + ")" + service.getPackageName();
    }

    private String userAgent() {
        return this.mUserAgent;
    }

    public HttpHost getPreferredHttpHost(Context context, String url) {
        if (!isLocalHost(url) && !this.mService.isWiFi()) {
            String proxyHost = Proxy.getHost(context);
            if (proxyHost != null) {
                return new HttpHost(proxyHost, Proxy.getPort(context), HttpHost.DEFAULT_SCHEME_NAME);
            }
        }
        return null;
    }

    private static final boolean isLocalHost(String url) {
        if (url == null) {
            return false;
        }
        try {
            String host = URI.create(url).getHost();
            if (host == null) {
                return false;
            }
            if (host.equalsIgnoreCase("localhost") || host.equals("127.0.0.1") || host.equals("[::1]")) {
                return true;
            }
            return false;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    /* JADX INFO: finally extract failed */
    public void run() {
        HttpGet request;
        Process.setThreadPriority(10);
        State state = new State(this.mInfo, this.mService);
        AndroidHttpClient client = null;
        WakeLock wakeLock = null;
        int finalStatus = 491;
        try {
            wakeLock = ((PowerManager) this.mContext.getSystemService("power")).newWakeLock(1, Constants.TAG);
            wakeLock.acquire();
            client = AndroidHttpClient.newInstance(userAgent(), this.mContext);
            boolean finished = false;
            while (!finished) {
                ConnRouteParams.setDefaultProxy(client.getParams(), getPreferredHttpHost(this.mContext, state.mRequestUri));
                request = new HttpGet(state.mRequestUri);
                executeDownload(state, client, request);
                finished = true;
                request.abort();
            }
            finalizeDestinationFile(state);
            if (wakeLock != null) {
                wakeLock.release();
            }
            if (client != null) {
                client.close();
            }
            cleanupDestination(state, 200);
            notifyDownloadCompleted(200, state.mCountRetry, state.mRetryAfter, state.mRedirectCount, state.mGotData, state.mFilename);
        } catch (RetryDownload e) {
            request.abort();
        } catch (StopRequest error) {
            try {
                Log.w(Constants.TAG, "Aborting request for download " + this.mInfo.mFileName + ": " + error.getMessage());
                error.printStackTrace();
                int finalStatus2 = error.mFinalStatus;
                if (wakeLock != null) {
                    wakeLock.release();
                }
                if (client != null) {
                    client.close();
                }
                cleanupDestination(state, finalStatus2);
                notifyDownloadCompleted(finalStatus2, state.mCountRetry, state.mRetryAfter, state.mRedirectCount, state.mGotData, state.mFilename);
            } catch (Throwable th) {
                Throwable th2 = th;
                if (wakeLock != null) {
                    wakeLock.release();
                }
                if (client != null) {
                    client.close();
                }
                cleanupDestination(state, finalStatus);
                notifyDownloadCompleted(finalStatus, state.mCountRetry, state.mRetryAfter, state.mRedirectCount, state.mGotData, state.mFilename);
                throw th2;
            }
        } catch (Throwable ex) {
            Log.w(Constants.TAG, "Exception for " + this.mInfo.mFileName + ": " + ex);
            if (wakeLock != null) {
                wakeLock.release();
            }
            if (client != null) {
                client.close();
            }
            cleanupDestination(state, 491);
            notifyDownloadCompleted(491, state.mCountRetry, state.mRetryAfter, state.mRedirectCount, state.mGotData, state.mFilename);
        }
    }

    private void executeDownload(State state, AndroidHttpClient client, HttpGet request) throws StopRequest, RetryDownload {
        InnerState innerState = new InnerState();
        byte[] data = new byte[4096];
        checkPausedOrCanceled(state);
        setupDestinationFile(state, innerState);
        addRequestHeaders(innerState, request);
        checkConnectivity(state);
        this.mNotification.onDownloadStateChanged(3);
        HttpResponse response = sendRequest(state, client, request);
        handleExceptionalStatus(state, innerState, response);
        processResponseHeaders(state, innerState, response);
        InputStream entityStream = openResponseEntity(state, response);
        this.mNotification.onDownloadStateChanged(4);
        transferData(state, innerState, data, entityStream);
    }

    private void checkConnectivity(State state) throws StopRequest {
        switch (this.mService.getNetworkAvailabilityState(this.mDB)) {
            case 2:
                throw new StopRequest(DownloaderService.STATUS_WAITING_FOR_NETWORK, "waiting for network to return");
            case 3:
                throw new StopRequest(DownloaderService.STATUS_QUEUED_FOR_WIFI, "waiting for wifi");
            case 5:
                throw new StopRequest(DownloaderService.STATUS_WAITING_FOR_NETWORK, "roaming is not allowed");
            case 6:
                throw new StopRequest(DownloaderService.STATUS_QUEUED_FOR_WIFI_OR_CELLULAR_PERMISSION, "waiting for wifi or for download over cellular to be authorized");
            default:
                return;
        }
    }

    private void transferData(State state, InnerState innerState, byte[] data, InputStream entityStream) throws StopRequest {
        while (true) {
            int bytesRead = readFromResponse(state, innerState, data, entityStream);
            if (bytesRead == -1) {
                handleEndOfStream(state, innerState);
                return;
            }
            state.mGotData = true;
            writeDataToDestination(state, data, bytesRead);
            innerState.mBytesSoFar += bytesRead;
            innerState.mBytesThisSession += bytesRead;
            reportProgress(state, innerState);
            checkPausedOrCanceled(state);
        }
    }

    private void finalizeDestinationFile(State state) throws StopRequest {
        syncDestination(state);
        String tempFilename = state.mFilename;
        String finalFilename = Helpers.generateSaveFileName(this.mService, this.mInfo.mFileName);
        if (!state.mFilename.equals(finalFilename)) {
            File startFile = new File(tempFilename);
            File destFile = new File(finalFilename);
            if (this.mInfo.mTotalBytes == -1 || this.mInfo.mCurrentBytes != this.mInfo.mTotalBytes) {
                throw new StopRequest(DownloaderService.STATUS_FILE_DELIVERED_INCORRECTLY, "file delivered with incorrect size. probably due to network not browser configured");
            } else if (!startFile.renameTo(destFile)) {
                throw new StopRequest(492, "unable to finalize destination file");
            }
        }
    }

    private void cleanupDestination(State state, int finalStatus) {
        closeDestination(state);
        if (state.mFilename != null && DownloaderService.isStatusError(finalStatus)) {
            new File(state.mFilename).delete();
            state.mFilename = null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0052 A[SYNTHETIC, Splitter:B:18:0x0052] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x008f A[SYNTHETIC, Splitter:B:29:0x008f] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00ce A[SYNTHETIC, Splitter:B:40:0x00ce] */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00f1 A[SYNTHETIC, Splitter:B:51:0x00f1] */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x010d A[SYNTHETIC, Splitter:B:59:0x010d] */
    /* JADX WARNING: Removed duplicated region for block: B:80:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:83:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:86:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:89:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void syncDestination(com.google.android.vending.expansion.downloader.impl.DownloadThread.State r7) {
        /*
            r6 = this;
            r0 = 0
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ FileNotFoundException -> 0x002b, SyncFailedException -> 0x0068, IOException -> 0x00a7, RuntimeException -> 0x00e7 }
            java.lang.String r3 = r7.mFilename     // Catch:{ FileNotFoundException -> 0x002b, SyncFailedException -> 0x0068, IOException -> 0x00a7, RuntimeException -> 0x00e7 }
            r4 = 1
            r1.<init>(r3, r4)     // Catch:{ FileNotFoundException -> 0x002b, SyncFailedException -> 0x0068, IOException -> 0x00a7, RuntimeException -> 0x00e7 }
            java.io.FileDescriptor r3 = r1.getFD()     // Catch:{ FileNotFoundException -> 0x0131, SyncFailedException -> 0x012d, IOException -> 0x0129, RuntimeException -> 0x0126, all -> 0x0123 }
            r3.sync()     // Catch:{ FileNotFoundException -> 0x0131, SyncFailedException -> 0x012d, IOException -> 0x0129, RuntimeException -> 0x0126, all -> 0x0123 }
            if (r1 == 0) goto L_0x0135
            r1.close()     // Catch:{ IOException -> 0x0017, RuntimeException -> 0x0021 }
            r0 = r1
        L_0x0016:
            return
        L_0x0017:
            r2 = move-exception
            java.lang.String r3 = "LVLDL"
            java.lang.String r4 = "IOException while closing synced file: "
            android.util.Log.w(r3, r4, r2)
            r0 = r1
            goto L_0x0016
        L_0x0021:
            r2 = move-exception
            java.lang.String r3 = "LVLDL"
            java.lang.String r4 = "exception while closing file: "
            android.util.Log.w(r3, r4, r2)
            r0 = r1
            goto L_0x0016
        L_0x002b:
            r2 = move-exception
        L_0x002c:
            java.lang.String r3 = "LVLDL"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x010a }
            r4.<init>()     // Catch:{ all -> 0x010a }
            java.lang.String r5 = "file "
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ all -> 0x010a }
            java.lang.String r5 = r7.mFilename     // Catch:{ all -> 0x010a }
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ all -> 0x010a }
            java.lang.String r5 = " not found: "
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ all -> 0x010a }
            java.lang.StringBuilder r4 = r4.append(r2)     // Catch:{ all -> 0x010a }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x010a }
            android.util.Log.w(r3, r4)     // Catch:{ all -> 0x010a }
            if (r0 == 0) goto L_0x0016
            r0.close()     // Catch:{ IOException -> 0x0056, RuntimeException -> 0x005f }
            goto L_0x0016
        L_0x0056:
            r2 = move-exception
            java.lang.String r3 = "LVLDL"
            java.lang.String r4 = "IOException while closing synced file: "
            android.util.Log.w(r3, r4, r2)
            goto L_0x0016
        L_0x005f:
            r2 = move-exception
            java.lang.String r3 = "LVLDL"
            java.lang.String r4 = "exception while closing file: "
            android.util.Log.w(r3, r4, r2)
            goto L_0x0016
        L_0x0068:
            r2 = move-exception
        L_0x0069:
            java.lang.String r3 = "LVLDL"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x010a }
            r4.<init>()     // Catch:{ all -> 0x010a }
            java.lang.String r5 = "file "
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ all -> 0x010a }
            java.lang.String r5 = r7.mFilename     // Catch:{ all -> 0x010a }
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ all -> 0x010a }
            java.lang.String r5 = " sync failed: "
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ all -> 0x010a }
            java.lang.StringBuilder r4 = r4.append(r2)     // Catch:{ all -> 0x010a }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x010a }
            android.util.Log.w(r3, r4)     // Catch:{ all -> 0x010a }
            if (r0 == 0) goto L_0x0016
            r0.close()     // Catch:{ IOException -> 0x0093, RuntimeException -> 0x009d }
            goto L_0x0016
        L_0x0093:
            r2 = move-exception
            java.lang.String r3 = "LVLDL"
            java.lang.String r4 = "IOException while closing synced file: "
            android.util.Log.w(r3, r4, r2)
            goto L_0x0016
        L_0x009d:
            r2 = move-exception
            java.lang.String r3 = "LVLDL"
            java.lang.String r4 = "exception while closing file: "
            android.util.Log.w(r3, r4, r2)
            goto L_0x0016
        L_0x00a7:
            r2 = move-exception
        L_0x00a8:
            java.lang.String r3 = "LVLDL"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x010a }
            r4.<init>()     // Catch:{ all -> 0x010a }
            java.lang.String r5 = "IOException trying to sync "
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ all -> 0x010a }
            java.lang.String r5 = r7.mFilename     // Catch:{ all -> 0x010a }
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ all -> 0x010a }
            java.lang.String r5 = ": "
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ all -> 0x010a }
            java.lang.StringBuilder r4 = r4.append(r2)     // Catch:{ all -> 0x010a }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x010a }
            android.util.Log.w(r3, r4)     // Catch:{ all -> 0x010a }
            if (r0 == 0) goto L_0x0016
            r0.close()     // Catch:{ IOException -> 0x00d3, RuntimeException -> 0x00dd }
            goto L_0x0016
        L_0x00d3:
            r2 = move-exception
            java.lang.String r3 = "LVLDL"
            java.lang.String r4 = "IOException while closing synced file: "
            android.util.Log.w(r3, r4, r2)
            goto L_0x0016
        L_0x00dd:
            r2 = move-exception
            java.lang.String r3 = "LVLDL"
            java.lang.String r4 = "exception while closing file: "
            android.util.Log.w(r3, r4, r2)
            goto L_0x0016
        L_0x00e7:
            r2 = move-exception
        L_0x00e8:
            java.lang.String r3 = "LVLDL"
            java.lang.String r4 = "exception while syncing file: "
            android.util.Log.w(r3, r4, r2)     // Catch:{ all -> 0x010a }
            if (r0 == 0) goto L_0x0016
            r0.close()     // Catch:{ IOException -> 0x00f6, RuntimeException -> 0x0100 }
            goto L_0x0016
        L_0x00f6:
            r2 = move-exception
            java.lang.String r3 = "LVLDL"
            java.lang.String r4 = "IOException while closing synced file: "
            android.util.Log.w(r3, r4, r2)
            goto L_0x0016
        L_0x0100:
            r2 = move-exception
            java.lang.String r3 = "LVLDL"
            java.lang.String r4 = "exception while closing file: "
            android.util.Log.w(r3, r4, r2)
            goto L_0x0016
        L_0x010a:
            r3 = move-exception
        L_0x010b:
            if (r0 == 0) goto L_0x0110
            r0.close()     // Catch:{ IOException -> 0x0111, RuntimeException -> 0x011a }
        L_0x0110:
            throw r3
        L_0x0111:
            r2 = move-exception
            java.lang.String r4 = "LVLDL"
            java.lang.String r5 = "IOException while closing synced file: "
            android.util.Log.w(r4, r5, r2)
            goto L_0x0110
        L_0x011a:
            r2 = move-exception
            java.lang.String r4 = "LVLDL"
            java.lang.String r5 = "exception while closing file: "
            android.util.Log.w(r4, r5, r2)
            goto L_0x0110
        L_0x0123:
            r3 = move-exception
            r0 = r1
            goto L_0x010b
        L_0x0126:
            r2 = move-exception
            r0 = r1
            goto L_0x00e8
        L_0x0129:
            r2 = move-exception
            r0 = r1
            goto L_0x00a8
        L_0x012d:
            r2 = move-exception
            r0 = r1
            goto L_0x0069
        L_0x0131:
            r2 = move-exception
            r0 = r1
            goto L_0x002c
        L_0x0135:
            r0 = r1
            goto L_0x0016
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.vending.expansion.downloader.impl.DownloadThread.syncDestination(com.google.android.vending.expansion.downloader.impl.DownloadThread$State):void");
    }

    private void closeDestination(State state) {
        try {
            if (state.mStream != null) {
                state.mStream.close();
                state.mStream = null;
            }
        } catch (IOException e) {
        }
    }

    private void checkPausedOrCanceled(State state) throws StopRequest {
        if (this.mService.getControl() == 1) {
            switch (this.mService.getStatus()) {
                case DownloaderService.STATUS_PAUSED_BY_APP /*193*/:
                    throw new StopRequest(this.mService.getStatus(), "download paused");
                default:
                    return;
            }
        }
    }

    private void reportProgress(State state, InnerState innerState) {
        long now = System.currentTimeMillis();
        if (innerState.mBytesSoFar - innerState.mBytesNotified > 4096 && now - innerState.mTimeLastNotification > 1000) {
            this.mInfo.mCurrentBytes = (long) innerState.mBytesSoFar;
            this.mDB.updateDownloadCurrentBytes(this.mInfo);
            innerState.mBytesNotified = innerState.mBytesSoFar;
            innerState.mTimeLastNotification = now;
            this.mService.notifyUpdateBytes(((long) innerState.mBytesThisSession) + this.mService.mBytesSoFar);
        }
    }

    private void writeDataToDestination(State state, byte[] data, int bytesRead) throws StopRequest {
        try {
            if (state.mStream == null) {
                state.mStream = new FileOutputStream(state.mFilename, true);
            }
            state.mStream.write(data, 0, bytesRead);
            closeDestination(state);
        } catch (IOException ex) {
            if (!Helpers.isExternalMediaMounted()) {
                throw new StopRequest(499, "external media not mounted while writing destination file");
            } else if (Helpers.getAvailableBytes(Helpers.getFilesystemRoot(state.mFilename)) < ((long) bytesRead)) {
                throw new StopRequest(498, "insufficient space while writing destination file", ex);
            } else {
                throw new StopRequest(492, "while writing destination file: " + ex.toString(), ex);
            }
        }
    }

    private void handleEndOfStream(State state, InnerState innerState) throws StopRequest {
        this.mInfo.mCurrentBytes = (long) innerState.mBytesSoFar;
        this.mDB.updateDownload(this.mInfo);
        if (!((innerState.mHeaderContentLength == null || innerState.mBytesSoFar == Integer.parseInt(innerState.mHeaderContentLength)) ? false : true)) {
            return;
        }
        if (cannotResume(innerState)) {
            throw new StopRequest(489, "mismatched content length");
        }
        throw new StopRequest(getFinalStatusForHttpError(state), "closed socket before end of file");
    }

    private boolean cannotResume(InnerState innerState) {
        return innerState.mBytesSoFar > 0 && innerState.mHeaderETag == null;
    }

    private int readFromResponse(State state, InnerState innerState, byte[] data, InputStream entityStream) throws StopRequest {
        try {
            return entityStream.read(data);
        } catch (IOException ex) {
            logNetworkState();
            this.mInfo.mCurrentBytes = (long) innerState.mBytesSoFar;
            this.mDB.updateDownload(this.mInfo);
            if (cannotResume(innerState)) {
                throw new StopRequest(489, "while reading response: " + ex.toString() + ", can't resume interrupted download with no ETag", ex);
            }
            throw new StopRequest(getFinalStatusForHttpError(state), "while reading response: " + ex.toString(), ex);
        }
    }

    private InputStream openResponseEntity(State state, HttpResponse response) throws StopRequest {
        try {
            return response.getEntity().getContent();
        } catch (IOException ex) {
            logNetworkState();
            throw new StopRequest(getFinalStatusForHttpError(state), "while getting entity: " + ex.toString(), ex);
        }
    }

    private void logNetworkState() {
        Log.i(Constants.TAG, "Net " + (this.mService.getNetworkAvailabilityState(this.mDB) == 1 ? "Up" : "Down"));
    }

    private void processResponseHeaders(State state, InnerState innerState, HttpResponse response) throws StopRequest {
        if (!innerState.mContinuingDownload) {
            readResponseHeaders(state, innerState, response);
            try {
                state.mFilename = this.mService.generateSaveFile(this.mInfo.mFileName, this.mInfo.mTotalBytes);
                try {
                    state.mStream = new FileOutputStream(state.mFilename);
                } catch (FileNotFoundException exc) {
                    try {
                        if (new File(Helpers.getSaveFilePath(this.mService)).mkdirs()) {
                            state.mStream = new FileOutputStream(state.mFilename);
                        }
                    } catch (Exception e) {
                        throw new StopRequest(492, "while opening destination file: " + exc.toString(), exc);
                    }
                }
                updateDatabaseFromHeaders(state, innerState);
                checkConnectivity(state);
            } catch (GenerateSaveFileError exc2) {
                throw new StopRequest(exc2.mStatus, exc2.mMessage);
            }
        }
    }

    private void updateDatabaseFromHeaders(State state, InnerState innerState) {
        this.mInfo.mETag = innerState.mHeaderETag;
        this.mDB.updateDownload(this.mInfo);
    }

    private void readResponseHeaders(State state, InnerState innerState, HttpResponse response) throws StopRequest {
        Header header = response.getFirstHeader(HttpHeaders.CONTENT_DISPOSITION);
        if (header != null) {
            innerState.mHeaderContentDisposition = header.getValue();
        }
        Header header2 = response.getFirstHeader(HttpHeaders.CONTENT_LOCATION);
        if (header2 != null) {
            innerState.mHeaderContentLocation = header2.getValue();
        }
        Header header3 = response.getFirstHeader(HttpHeaders.ETAG);
        if (header3 != null) {
            innerState.mHeaderETag = header3.getValue();
        }
        String headerTransferEncoding = null;
        Header header4 = response.getFirstHeader("Transfer-Encoding");
        if (header4 != null) {
            headerTransferEncoding = header4.getValue();
        }
        Header header5 = response.getFirstHeader("Content-Type");
        if (header5 == null || header5.getValue().equals("application/vnd.android.obb")) {
            if (headerTransferEncoding == null) {
                Header header6 = response.getFirstHeader("Content-Length");
                if (header6 != null) {
                    innerState.mHeaderContentLength = header6.getValue();
                    long contentLength = Long.parseLong(innerState.mHeaderContentLength);
                    if (!(contentLength == -1 || contentLength == this.mInfo.mTotalBytes)) {
                        Log.e(Constants.TAG, "Incorrect file size delivered.");
                    }
                }
            }
            if (innerState.mHeaderContentLength == null && (headerTransferEncoding == null || !headerTransferEncoding.equalsIgnoreCase(HTTP.CHUNK_CODING))) {
                throw new StopRequest(495, "can't know size of download, giving up");
            }
            return;
        }
        throw new StopRequest(DownloaderService.STATUS_FILE_DELIVERED_INCORRECTLY, "file delivered with incorrect Mime type");
    }

    private void handleExceptionalStatus(State state, InnerState innerState, HttpResponse response) throws StopRequest, RetryDownload {
        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode == 503 && this.mInfo.mNumFailed < 5) {
            handleServiceUnavailable(state, response);
        }
        if (statusCode == 301 || statusCode == 302 || statusCode == 303 || statusCode == 307) {
            handleRedirect(state, response, statusCode);
        }
        if (statusCode != (innerState.mContinuingDownload ? HttpStatus.SC_PARTIAL_CONTENT : 200)) {
            handleOtherStatus(state, innerState, statusCode);
        } else {
            state.mRedirectCount = 0;
        }
    }

    private void handleOtherStatus(State state, InnerState innerState, int statusCode) throws StopRequest {
        int finalStatus;
        if (DownloaderService.isStatusError(statusCode)) {
            finalStatus = statusCode;
        } else if (statusCode >= 300 && statusCode < 400) {
            finalStatus = 493;
        } else if (!innerState.mContinuingDownload || statusCode != 200) {
            finalStatus = 494;
        } else {
            finalStatus = 489;
        }
        throw new StopRequest(finalStatus, "http error " + statusCode);
    }

    private void handleRedirect(State state, HttpResponse response, int statusCode) throws StopRequest, RetryDownload {
        if (state.mRedirectCount >= 5) {
            throw new StopRequest(497, "too many redirects");
        }
        Header header = response.getFirstHeader(HttpHeaders.LOCATION);
        if (header != null) {
            try {
                String newUri = new URI(this.mInfo.mUri).resolve(new URI(header.getValue())).toString();
                state.mRedirectCount++;
                state.mRequestUri = newUri;
                if (statusCode == 301 || statusCode == 303) {
                    state.mNewUri = newUri;
                }
                throw new RetryDownload();
            } catch (URISyntaxException e) {
                throw new StopRequest(495, "Couldn't resolve redirect URI");
            }
        }
    }

    private void addRequestHeaders(InnerState innerState, HttpGet request) {
        if (innerState.mContinuingDownload) {
            if (innerState.mHeaderETag != null) {
                request.addHeader(HttpHeaders.IF_MATCH, innerState.mHeaderETag);
            }
            request.addHeader(HttpHeaders.RANGE, "bytes=" + innerState.mBytesSoFar + Constants.FILENAME_SEQUENCE_SEPARATOR);
        }
    }

    private void handleServiceUnavailable(State state, HttpResponse response) throws StopRequest {
        state.mCountRetry = true;
        Header header = response.getFirstHeader(HttpHeaders.RETRY_AFTER);
        if (header != null) {
            try {
                state.mRetryAfter = Integer.parseInt(header.getValue());
                if (state.mRetryAfter < 0) {
                    state.mRetryAfter = 0;
                } else {
                    if (state.mRetryAfter < 30) {
                        state.mRetryAfter = 30;
                    } else if (state.mRetryAfter > 86400) {
                        state.mRetryAfter = Constants.MAX_RETRY_AFTER;
                    }
                    state.mRetryAfter += Helpers.sRandom.nextInt(31);
                    state.mRetryAfter *= 1000;
                }
            } catch (NumberFormatException e) {
            }
        }
        throw new StopRequest(DownloaderService.STATUS_WAITING_TO_RETRY, "got 503 Service Unavailable, will retry later");
    }

    private HttpResponse sendRequest(State state, AndroidHttpClient client, HttpGet request) throws StopRequest {
        try {
            return client.execute(request);
        } catch (IllegalArgumentException ex) {
            throw new StopRequest(495, "while trying to execute request: " + ex.toString(), ex);
        } catch (IOException ex2) {
            logNetworkState();
            throw new StopRequest(getFinalStatusForHttpError(state), "while trying to execute request: " + ex2.toString(), ex2);
        }
    }

    private int getFinalStatusForHttpError(State state) {
        if (this.mService.getNetworkAvailabilityState(this.mDB) != 1) {
            return DownloaderService.STATUS_WAITING_FOR_NETWORK;
        }
        if (this.mInfo.mNumFailed < 5) {
            state.mCountRetry = true;
            return DownloaderService.STATUS_WAITING_TO_RETRY;
        }
        Log.w(Constants.TAG, "reached max retries for " + this.mInfo.mNumFailed);
        return 495;
    }

    private void setupDestinationFile(State state, InnerState innerState) throws StopRequest {
        if (state.mFilename != null) {
            if (!Helpers.isFilenameValid(state.mFilename)) {
                throw new StopRequest(492, "found invalid internal destination filename");
            }
            File f = new File(state.mFilename);
            if (f.exists()) {
                long fileLength = f.length();
                if (fileLength == 0) {
                    f.delete();
                    state.mFilename = null;
                } else if (this.mInfo.mETag == null) {
                    f.delete();
                    throw new StopRequest(489, "Trying to resume a download that can't be resumed");
                } else {
                    try {
                        state.mStream = new FileOutputStream(state.mFilename, true);
                        innerState.mBytesSoFar = (int) fileLength;
                        if (this.mInfo.mTotalBytes != -1) {
                            innerState.mHeaderContentLength = Long.toString(this.mInfo.mTotalBytes);
                        }
                        innerState.mHeaderETag = this.mInfo.mETag;
                        innerState.mContinuingDownload = true;
                    } catch (FileNotFoundException exc) {
                        throw new StopRequest(492, "while opening destination for resuming: " + exc.toString(), exc);
                    }
                }
            }
        }
        if (state.mStream != null) {
            closeDestination(state);
        }
    }

    private void notifyDownloadCompleted(int status, boolean countRetry, int retryAfter, int redirectCount, boolean gotData, String filename) {
        updateDownloadDatabase(status, countRetry, retryAfter, redirectCount, gotData, filename);
        if (DownloaderService.isStatusCompleted(status)) {
        }
    }

    private void updateDownloadDatabase(int status, boolean countRetry, int retryAfter, int redirectCount, boolean gotData, String filename) {
        this.mInfo.mStatus = status;
        this.mInfo.mRetryAfter = retryAfter;
        this.mInfo.mRedirectCount = redirectCount;
        this.mInfo.mLastMod = System.currentTimeMillis();
        if (!countRetry) {
            this.mInfo.mNumFailed = 0;
        } else if (gotData) {
            this.mInfo.mNumFailed = 1;
        } else {
            this.mInfo.mNumFailed++;
        }
        this.mDB.updateDownload(this.mInfo);
    }
}
