package org.cocos2dx.lib;

import android.annotation.SuppressLint;
import android.util.Log;
import com.google.android.vending.expansion.downloader.Constants;
import com.google.common.net.HttpHeaders;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import okhttp3.Request;
import okhttp3.Response;
import org.cocos2dx.lib.downloader.DownloadTaskOk;
import org.cocos2dx.lib.downloader.ProgressHelper;
import org.cocos2dx.lib.downloader.ProgressResponseBody.ProgressListener;

public class Cocos2dxDownloader {
    /* access modifiers changed from: private */
    public static HashMap<String, Boolean> _resumingSupport = new HashMap<>();
    private int _countOfMaxProcessingTasks;
    /* access modifiers changed from: private */
    public OkHttpClient _httpClient = null;
    /* access modifiers changed from: private */
    public int _id;
    private int _runningTaskCount = 0;
    /* access modifiers changed from: private */
    @SuppressLint({"UseSparseArrays"})
    public HashMap<Integer, DownloadTaskOk> _taskMap = new HashMap<>();
    private Queue<Runnable> _taskQueue = new LinkedList();
    /* access modifiers changed from: private */
    public String _tempFileNameSufix;

    /* access modifiers changed from: 0000 */
    public native void nativeOnFinish(int i, int i2, int i3, String str, byte[] bArr);

    /* access modifiers changed from: 0000 */
    public native void nativeOnProgress(int i, int i2, long j, long j2, long j3);

    /* access modifiers changed from: 0000 */
    public void onProgress(int id, long downloadNowThis, long downloadNow, long downloadTotal) {
        DownloadTaskOk task = (DownloadTaskOk) this._taskMap.get(Integer.valueOf(id));
        if (task != null) {
            task.bytesReceived = downloadNowThis;
            task.totalBytesReceived = downloadNow;
            task.totalBytesExpected = downloadTotal;
        }
        final int i = id;
        final long j = downloadNowThis;
        final long j2 = downloadNow;
        final long j3 = downloadTotal;
        Cocos2dxHelper.runOnGLThread(new Runnable() {
            public void run() {
                Cocos2dxDownloader.this.nativeOnProgress(Cocos2dxDownloader.this._id, i, j, j2, j3);
            }
        });
    }

    public void onStart(int id) {
        DownloadTaskOk task = (DownloadTaskOk) this._taskMap.get(Integer.valueOf(id));
        if (task != null) {
            task.resetStatus();
        }
    }

    public void onFinish(int id, int errCode, String errStr, byte[] data) {
        if (((DownloadTaskOk) this._taskMap.get(Integer.valueOf(id))) != null) {
            this._taskMap.remove(Integer.valueOf(id));
            final int i = id;
            final int i2 = errCode;
            final String str = errStr;
            final byte[] bArr = data;
            Cocos2dxHelper.runOnGLThread(new Runnable() {
                public void run() {
                    Cocos2dxDownloader.this.nativeOnFinish(Cocos2dxDownloader.this._id, i, i2, str, bArr);
                }
            });
            runNextTaskIfExists();
        }
    }

    public static void setResumingSupport(String host, Boolean support) {
        _resumingSupport.put(host, support);
    }

    public static Cocos2dxDownloader createDownloader(int id, int timeoutInSeconds, String tempFileNameSufix, int countOfMaxProcessingTasks) {
        Cocos2dxDownloader downloader = new Cocos2dxDownloader();
        downloader._id = id;
        if (timeoutInSeconds > 0) {
            int connectTimeoutInSeconds = timeoutInSeconds / 3;
            if (connectTimeoutInSeconds < 5) {
                connectTimeoutInSeconds = 5;
            }
            downloader._httpClient = new Builder().readTimeout((long) timeoutInSeconds, TimeUnit.SECONDS).writeTimeout((long) timeoutInSeconds, TimeUnit.SECONDS).connectTimeout((long) connectTimeoutInSeconds, TimeUnit.SECONDS).build();
        } else {
            downloader._httpClient = new Builder().build();
        }
        downloader._tempFileNameSufix = tempFileNameSufix;
        downloader._countOfMaxProcessingTasks = countOfMaxProcessingTasks;
        return downloader;
    }

    public static void createTask(final Cocos2dxDownloader downloader, int id_, String url_, String path_) {
        final int id = id_;
        final String url = url_;
        final String path = path_;
        downloader.enqueueTask(new Runnable() {
            public void run() {
                final String host;
                DownloadTaskOk task = new DownloadTaskOk();
                try {
                    String domain = new URI(url).getHost();
                    if (domain.startsWith("www.")) {
                        host = domain.substring(4);
                    } else {
                        host = domain;
                    }
                    Boolean supportResuming = Boolean.valueOf(false);
                    Boolean requestHeader = Boolean.valueOf(true);
                    if (Cocos2dxDownloader._resumingSupport.containsKey(host)) {
                        supportResuming = (Boolean) Cocos2dxDownloader._resumingSupport.get(host);
                        requestHeader = Boolean.valueOf(false);
                    }
                    if (requestHeader.booleanValue()) {
                        Call call = downloader._httpClient.newCall(new Request.Builder().url(url).build());
                        task.call = call;
                        C21401 r0 = new Callback() {
                            public void onFailure(Call call, IOException e) {
                                downloader.onFinish(id, 0, "onFailure" + url + e.getMessage(), null);
                            }

                            public void onResponse(Call call, Response response) throws IOException {
                                System.out.println(String.format("Received response%s", new Object[]{response.headers()}));
                                Headers headers = response.headers();
                                for (int i = 0; i < headers.size(); i++) {
                                    System.out.println("----header-----" + headers.name(i) + ": " + headers.value(i));
                                }
                                String acceptRangesValue = response.header(HttpHeaders.ACCEPT_RANGES);
                                Log.d("Cocos2dxDownloaderOk", "acceptRangesValue:" + acceptRangesValue);
                                System.out.println("acceptRangesValue:" + acceptRangesValue);
                                Boolean acceptRanges = Boolean.valueOf(false);
                                if (acceptRangesValue != null) {
                                    acceptRanges = Boolean.valueOf(acceptRangesValue.equals("bytes"));
                                }
                                Cocos2dxDownloader.setResumingSupport(host, acceptRanges);
                                Cocos2dxDownloader.createTask(downloader, id, url, path);
                                downloader.runNextTaskIfExists();
                            }
                        };
                        call.enqueue(r0);
                    } else {
                        File file = new File(path + downloader._tempFileNameSufix);
                        if (!file.isDirectory()) {
                            File parent = file.getParentFile();
                            if ((parent.isDirectory() || parent.mkdirs()) && !new File(path).isDirectory()) {
                                final long fileLen = file.length();
                                Request.Builder requestBuilder = new Request.Builder().url(url);
                                if (supportResuming.booleanValue()) {
                                    requestBuilder.addHeader(HttpHeaders.RANGE, "bytes=" + fileLen + Constants.FILENAME_SEQUENCE_SEPARATOR);
                                }
                                Request request = requestBuilder.build();
                                ProgressListener listener = new ProgressListener() {
                                    public void update(int taskId, long bytesReadThis, long bytesRead, long contentLength, boolean done) {
                                        System.out.println("Cocos2dxDownloaderOk下载进度：" + ((int) ((100 * bytesRead) / contentLength)) + "%, bytesReadThis" + bytesReadThis + ", bytesRead" + bytesRead + ", contentLength" + contentLength);
                                        downloader.onProgress(taskId, bytesReadThis, bytesRead + fileLen, contentLength + fileLen);
                                    }
                                };
                                Call call2 = ProgressHelper.addProgressResponseListener(id, downloader._httpClient, listener).newCall(request);
                                task.call = call2;
                                final File file2 = file;
                                C21423 r02 = new Callback() {
                                    public void onFailure(Call call, IOException e) {
                                        System.out.println("----file task onFailure-----" + url);
                                        downloader.onFinish(id, 0, "onFailure for " + url + e.getMessage(), null);
                                    }

                                    /* JADX WARNING: Removed duplicated region for block: B:22:0x00ec A[SYNTHETIC, Splitter:B:22:0x00ec] */
                                    /* JADX WARNING: Removed duplicated region for block: B:41:0x0173 A[SYNTHETIC, Splitter:B:41:0x0173] */
                                    /* Code decompiled incorrectly, please refer to instructions dump. */
                                    public void onResponse(okhttp3.Call r16, okhttp3.Response r17) {
                                        /*
                                            r15 = this;
                                            java.io.PrintStream r10 = java.lang.System.out
                                            java.lang.StringBuilder r11 = new java.lang.StringBuilder
                                            r11.<init>()
                                            java.lang.String r12 = "----file task onResponse-----"
                                            java.lang.StringBuilder r11 = r11.append(r12)
                                            org.cocos2dx.lib.Cocos2dxDownloader$3 r12 = org.cocos2dx.lib.Cocos2dxDownloader.C21393.this
                                            java.lang.String r12 = r3
                                            java.lang.StringBuilder r11 = r11.append(r12)
                                            java.lang.String r11 = r11.toString()
                                            r10.println(r11)
                                            org.cocos2dx.lib.Cocos2dxDownloader$3 r10 = org.cocos2dx.lib.Cocos2dxDownloader.C21393.this
                                            java.lang.String r10 = r1
                                            int r10 = r10.length()
                                            if (r10 != 0) goto L_0x006e
                                            okhttp3.ResponseBody r10 = r17.body()     // Catch:{ IOException -> 0x0040 }
                                            byte[] r2 = r10.bytes()     // Catch:{ IOException -> 0x0040 }
                                            java.lang.String r4 = "0 == path.length()"
                                            org.cocos2dx.lib.Cocos2dxDownloader$3 r10 = org.cocos2dx.lib.Cocos2dxDownloader.C21393.this
                                            org.cocos2dx.lib.Cocos2dxDownloader r10 = r4
                                            org.cocos2dx.lib.Cocos2dxDownloader$3 r11 = org.cocos2dx.lib.Cocos2dxDownloader.C21393.this
                                            int r11 = r0
                                            r12 = 0
                                            java.lang.String r13 = "0 == path.length()"
                                            r14 = 0
                                            r10.onFinish(r11, r12, r13, r14)
                                        L_0x003f:
                                            return
                                        L_0x0040:
                                            r3 = move-exception
                                            java.lang.StringBuilder r10 = new java.lang.StringBuilder
                                            r10.<init>()
                                            java.lang.String r11 = "IOException get bytes for"
                                            java.lang.StringBuilder r10 = r10.append(r11)
                                            org.cocos2dx.lib.Cocos2dxDownloader$3 r11 = org.cocos2dx.lib.Cocos2dxDownloader.C21393.this
                                            java.lang.String r11 = r3
                                            java.lang.StringBuilder r10 = r10.append(r11)
                                            java.lang.String r11 = r3.getMessage()
                                            java.lang.StringBuilder r10 = r10.append(r11)
                                            java.lang.String r4 = r10.toString()
                                            org.cocos2dx.lib.Cocos2dxDownloader$3 r10 = org.cocos2dx.lib.Cocos2dxDownloader.C21393.this
                                            org.cocos2dx.lib.Cocos2dxDownloader r10 = r4
                                            org.cocos2dx.lib.Cocos2dxDownloader$3 r11 = org.cocos2dx.lib.Cocos2dxDownloader.C21393.this
                                            int r11 = r0
                                            r12 = 0
                                            r13 = 0
                                            r10.onFinish(r11, r12, r4, r13)
                                            goto L_0x003f
                                        L_0x006e:
                                            okhttp3.ResponseBody r10 = r17.body()
                                            java.io.InputStream r7 = r10.byteStream()
                                            r5 = 0
                                            r9 = 0
                                            java.io.FileOutputStream r6 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x01a9 }
                                            java.io.File r10 = r2     // Catch:{ IOException -> 0x01a9 }
                                            r11 = 1
                                            r6.<init>(r10, r11)     // Catch:{ IOException -> 0x01a9 }
                                            r10 = 4096(0x1000, float:5.74E-42)
                                            byte[] r1 = new byte[r10]     // Catch:{ IOException -> 0x00b2, all -> 0x01a6 }
                                            r8 = 0
                                        L_0x0085:
                                            int r8 = r7.read(r1)     // Catch:{ IOException -> 0x00b2, all -> 0x01a6 }
                                            r10 = -1
                                            if (r8 == r10) goto L_0x00fd
                                            r10 = 0
                                            r6.write(r1, r10, r8)     // Catch:{ IOException -> 0x00b2, all -> 0x01a6 }
                                            int r9 = r9 + r8
                                            java.io.PrintStream r10 = java.lang.System.out     // Catch:{ IOException -> 0x00b2, all -> 0x01a6 }
                                            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x00b2, all -> 0x01a6 }
                                            r11.<init>()     // Catch:{ IOException -> 0x00b2, all -> 0x01a6 }
                                            java.lang.String r12 = "----file task onResponse-----len:"
                                            java.lang.StringBuilder r11 = r11.append(r12)     // Catch:{ IOException -> 0x00b2, all -> 0x01a6 }
                                            java.lang.StringBuilder r11 = r11.append(r8)     // Catch:{ IOException -> 0x00b2, all -> 0x01a6 }
                                            org.cocos2dx.lib.Cocos2dxDownloader$3 r12 = org.cocos2dx.lib.Cocos2dxDownloader.C21393.this     // Catch:{ IOException -> 0x00b2, all -> 0x01a6 }
                                            java.lang.String r12 = r3     // Catch:{ IOException -> 0x00b2, all -> 0x01a6 }
                                            java.lang.StringBuilder r11 = r11.append(r12)     // Catch:{ IOException -> 0x00b2, all -> 0x01a6 }
                                            java.lang.String r11 = r11.toString()     // Catch:{ IOException -> 0x00b2, all -> 0x01a6 }
                                            r10.println(r11)     // Catch:{ IOException -> 0x00b2, all -> 0x01a6 }
                                            goto L_0x0085
                                        L_0x00b2:
                                            r3 = move-exception
                                            r5 = r6
                                        L_0x00b4:
                                            java.lang.String r10 = "Cocos2dxDownloaderOk"
                                            java.lang.String r11 = "IOException"
                                            android.util.Log.i(r10, r11)     // Catch:{ all -> 0x0170 }
                                            r3.printStackTrace()     // Catch:{ all -> 0x0170 }
                                            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ all -> 0x0170 }
                                            r10.<init>()     // Catch:{ all -> 0x0170 }
                                            java.lang.String r11 = "IOException get bytes for"
                                            java.lang.StringBuilder r10 = r10.append(r11)     // Catch:{ all -> 0x0170 }
                                            org.cocos2dx.lib.Cocos2dxDownloader$3 r11 = org.cocos2dx.lib.Cocos2dxDownloader.C21393.this     // Catch:{ all -> 0x0170 }
                                            java.lang.String r11 = r3     // Catch:{ all -> 0x0170 }
                                            java.lang.StringBuilder r10 = r10.append(r11)     // Catch:{ all -> 0x0170 }
                                            java.lang.String r11 = r3.getMessage()     // Catch:{ all -> 0x0170 }
                                            java.lang.StringBuilder r10 = r10.append(r11)     // Catch:{ all -> 0x0170 }
                                            java.lang.String r4 = r10.toString()     // Catch:{ all -> 0x0170 }
                                            org.cocos2dx.lib.Cocos2dxDownloader$3 r10 = org.cocos2dx.lib.Cocos2dxDownloader.C21393.this     // Catch:{ all -> 0x0170 }
                                            org.cocos2dx.lib.Cocos2dxDownloader r10 = r4     // Catch:{ all -> 0x0170 }
                                            org.cocos2dx.lib.Cocos2dxDownloader$3 r11 = org.cocos2dx.lib.Cocos2dxDownloader.C21393.this     // Catch:{ all -> 0x0170 }
                                            int r11 = r0     // Catch:{ all -> 0x0170 }
                                            r12 = 0
                                            r13 = 0
                                            r10.onFinish(r11, r12, r4, r13)     // Catch:{ all -> 0x0170 }
                                            if (r5 == 0) goto L_0x00f2
                                            r5.flush()     // Catch:{ IOException -> 0x00f7 }
                                            r5.close()     // Catch:{ IOException -> 0x00f7 }
                                        L_0x00f2:
                                            r7.close()     // Catch:{ IOException -> 0x00f7 }
                                            goto L_0x003f
                                        L_0x00f7:
                                            r3 = move-exception
                                            r3.printStackTrace()
                                            goto L_0x003f
                                        L_0x00fd:
                                            if (r6 == 0) goto L_0x0105
                                            r6.flush()     // Catch:{ IOException -> 0x016b }
                                            r6.close()     // Catch:{ IOException -> 0x016b }
                                        L_0x0105:
                                            r7.close()     // Catch:{ IOException -> 0x016b }
                                        L_0x0108:
                                            java.io.PrintStream r10 = java.lang.System.out
                                            java.lang.StringBuilder r11 = new java.lang.StringBuilder
                                            r11.<init>()
                                            java.lang.String r12 = "----file task onResponse-----totalLen:"
                                            java.lang.StringBuilder r11 = r11.append(r12)
                                            java.lang.StringBuilder r11 = r11.append(r9)
                                            org.cocos2dx.lib.Cocos2dxDownloader$3 r12 = org.cocos2dx.lib.Cocos2dxDownloader.C21393.this
                                            java.lang.String r12 = r3
                                            java.lang.StringBuilder r11 = r11.append(r12)
                                            java.lang.String r11 = r11.toString()
                                            r10.println(r11)
                                            java.lang.String r10 = "Cocos2dxDownloaderOk"
                                            java.lang.String r11 = "文件下载成功...."
                                            android.util.Log.d(r10, r11)
                                            java.io.File r0 = new java.io.File
                                            org.cocos2dx.lib.Cocos2dxDownloader$3 r10 = org.cocos2dx.lib.Cocos2dxDownloader.C21393.this
                                            java.lang.String r10 = r1
                                            r0.<init>(r10)
                                            r4 = 0
                                            boolean r10 = r0.exists()
                                            if (r10 == 0) goto L_0x01a0
                                            boolean r10 = r0.isDirectory()
                                            if (r10 == 0) goto L_0x0182
                                            java.lang.StringBuilder r10 = new java.lang.StringBuilder
                                            r10.<init>()
                                            java.lang.String r11 = "Dest file is directory:"
                                            java.lang.StringBuilder r10 = r10.append(r11)
                                            java.lang.String r11 = r0.getAbsolutePath()
                                            java.lang.StringBuilder r10 = r10.append(r11)
                                            java.lang.String r4 = r10.toString()
                                        L_0x015c:
                                            org.cocos2dx.lib.Cocos2dxDownloader$3 r10 = org.cocos2dx.lib.Cocos2dxDownloader.C21393.this
                                            org.cocos2dx.lib.Cocos2dxDownloader r10 = r4
                                            org.cocos2dx.lib.Cocos2dxDownloader$3 r11 = org.cocos2dx.lib.Cocos2dxDownloader.C21393.this
                                            int r11 = r0
                                            r12 = 0
                                            r13 = 0
                                            r10.onFinish(r11, r12, r4, r13)
                                            goto L_0x003f
                                        L_0x016b:
                                            r3 = move-exception
                                            r3.printStackTrace()
                                            goto L_0x0108
                                        L_0x0170:
                                            r10 = move-exception
                                        L_0x0171:
                                            if (r5 == 0) goto L_0x0179
                                            r5.flush()     // Catch:{ IOException -> 0x017d }
                                            r5.close()     // Catch:{ IOException -> 0x017d }
                                        L_0x0179:
                                            r7.close()     // Catch:{ IOException -> 0x017d }
                                        L_0x017c:
                                            throw r10
                                        L_0x017d:
                                            r3 = move-exception
                                            r3.printStackTrace()
                                            goto L_0x017c
                                        L_0x0182:
                                            boolean r10 = r0.delete()
                                            if (r10 != 0) goto L_0x01a0
                                            java.lang.StringBuilder r10 = new java.lang.StringBuilder
                                            r10.<init>()
                                            java.lang.String r11 = "Can't remove old file:"
                                            java.lang.StringBuilder r10 = r10.append(r11)
                                            java.lang.String r11 = r0.getAbsolutePath()
                                            java.lang.StringBuilder r10 = r10.append(r11)
                                            java.lang.String r4 = r10.toString()
                                            goto L_0x015c
                                        L_0x01a0:
                                            java.io.File r10 = r2
                                            r10.renameTo(r0)
                                            goto L_0x015c
                                        L_0x01a6:
                                            r10 = move-exception
                                            r5 = r6
                                            goto L_0x0171
                                        L_0x01a9:
                                            r3 = move-exception
                                            goto L_0x00b4
                                        */
                                        throw new UnsupportedOperationException("Method not decompiled: org.cocos2dx.lib.Cocos2dxDownloader.C21393.C21423.onResponse(okhttp3.Call, okhttp3.Response):void");
                                    }
                                };
                                call2.enqueue(r02);
                            }
                        }
                    }
                } catch (URISyntaxException e) {
                }
                if (task.call == null) {
                    final String errStr = "Can't create DownloadTask for " + url;
                    C21434 r03 = new Runnable() {
                        public void run() {
                            downloader.nativeOnFinish(downloader._id, id, 0, errStr, null);
                        }
                    };
                    Cocos2dxHelper.runOnGLThread(r03);
                } else {
                    downloader._taskMap.put(Integer.valueOf(id), task);
                }
                downloader._taskMap.put(Integer.valueOf(id), task);
            }
        });
    }

    public static void cancelAllRequests(Cocos2dxDownloader downloader) {
        Cocos2dxHelper.getActivity().runOnUiThread(new Runnable(downloader) {
            final /* synthetic */ Cocos2dxDownloader val$downloader;

            {
                this.val$downloader = r1;
            }

            public void run() {
                for (Entry entry : this.val$downloader._taskMap.entrySet()) {
                    DownloadTaskOk task = (DownloadTaskOk) entry.getValue();
                    if (task.call != null) {
                        task.call.cancel();
                    }
                }
            }
        });
    }

    public void enqueueTask(Runnable taskRunnable) {
        synchronized (this._taskQueue) {
            if (this._runningTaskCount < this._countOfMaxProcessingTasks) {
                Cocos2dxHelper.getActivity().runOnUiThread(taskRunnable);
                this._runningTaskCount++;
            } else {
                this._taskQueue.add(taskRunnable);
            }
        }
    }

    public void runNextTaskIfExists() {
        synchronized (this._taskQueue) {
            Runnable taskRunnable = (Runnable) this._taskQueue.poll();
            if (taskRunnable != null) {
                Cocos2dxHelper.getActivity().runOnUiThread(taskRunnable);
            } else {
                this._runningTaskCount--;
            }
        }
    }
}
