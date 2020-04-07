package com.btgame.onesdk.obb;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.baitian.datasdk.BtDataSdkManager;
import com.baitian.datasdk.constants.Event;
import com.btgame.sdk.util.BtUtils;
import com.btgame.sdk.util.BtsdkLog;
import com.btgame.sdk.util.ResourceUtil;
import com.google.android.vending.expansion.downloader.Constants;
import com.google.android.vending.expansion.downloader.DownloadProgressInfo;
import com.google.android.vending.expansion.downloader.DownloaderClientMarshaller;
import com.google.android.vending.expansion.downloader.DownloaderServiceMarshaller;
import com.google.android.vending.expansion.downloader.Helpers;
import com.google.android.vending.expansion.downloader.IDownloaderClient;
import com.google.android.vending.expansion.downloader.IDownloaderService;
import com.google.android.vending.expansion.downloader.IStub;

public class OBBDownloaderActivity extends Activity implements IDownloaderClient {
    private static final String LOG_TAG = "LVLDownloader";
    private static final int MSG_DOWNLOAD_FAILED = 1;
    private static final int MSG_SUCCESS = 0;
    private static final int MSG_VALIDATE_FAILED = -2;
    private static final float SMOOTHING_FACTOR = 0.005f;
    private boolean isDownloadFinish;
    /* access modifiers changed from: private */
    public boolean isValidateFinish;
    private TextView mAverageSpeed;
    /* access modifiers changed from: private */
    public boolean mCancelValidation;
    /* access modifiers changed from: private */
    public View mCellMessage;
    /* access modifiers changed from: private */
    public View mDashboard;
    /* access modifiers changed from: private */
    @SuppressLint({"HandlerLeak"})
    public Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            Log.i(Constants.TAG, "handle message:" + msg.toString());
            switch (msg.what) {
                case -2:
                case 1:
                    AlertDialog dialog = new Builder(OBBDownloaderActivity.this).setMessage(Helpers.getDownloaderStringResourceIDFromState(msg.arg1)).setPositiveButton("Try again", new OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            OBBDownloaderActivity.this.checkAndDownloadOBB();
                        }
                    }).setNegativeButton("Cancel", new OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            OBBDownloaderActivity.this.finish();
                        }
                    }).setCancelable(false).create();
                    if (msg.what == -2) {
                        dialog.setMessage("Files validate failed");
                    }
                    dialog.show();
                    return;
                case 0:
                    if (BtUtils.getbooleanMeTaData(OBBDownloaderActivity.this, "btObbAutoSwitch")) {
                        Intent intent = new Intent("com.baitian.sdk.MAIN");
                        intent.setPackage(OBBDownloaderActivity.this.getPackageName());
                        OBBDownloaderActivity.this.startActivity(intent);
                    }
                    OBBDownloaderActivity.this.finish();
                    return;
                default:
                    return;
            }
        }
    };
    private ProgressBar mPB;
    /* access modifiers changed from: private */
    public Button mPauseButton;
    private TextView mProgressFraction;
    private TextView mProgressPercent;
    /* access modifiers changed from: private */
    public IDownloaderService mRemoteService;
    private int mState;
    /* access modifiers changed from: private */
    public boolean mStatePaused;
    /* access modifiers changed from: private */
    public TextView mStatusText;
    private TextView mTimeRemaining;
    private Button mWiFiSettingsButton;
    private boolean toDownload = false;
    /* access modifiers changed from: private */
    public XAPKFile[] xAPKS = new XAPKFile[1];

    public static class DownloaderClientProxy implements IDownloaderClient {
        private IDownloaderClient client;
        private IStub mDownloaderClientStub = DownloaderClientMarshaller.CreateStub(this, OBBDownloaderService.class);

        private static class DownloaderClientProxyHolder {
            /* access modifiers changed from: private */
            public static final DownloaderClientProxy downloaderClientProxy = new DownloaderClientProxy();

            private DownloaderClientProxyHolder() {
            }
        }

        public void onServiceConnected(Messenger messenger) {
            if (this.client != null) {
                this.client.onServiceConnected(messenger);
            }
        }

        public void onDownloadStateChanged(int i) {
            if (this.client != null) {
                this.client.onDownloadStateChanged(i);
            }
        }

        public void onDownloadProgress(DownloadProgressInfo downloadProgressInfo) {
            if (this.client != null) {
                this.client.onDownloadProgress(downloadProgressInfo);
            }
        }

        public void connect(Context context, IDownloaderClient client2) {
            this.client = client2;
            if (this.mDownloaderClientStub != null) {
                this.mDownloaderClientStub.connect(context);
            }
        }

        public void disConnect(Context context) {
            if (!(this.client == null || this.mDownloaderClientStub == null)) {
                this.mDownloaderClientStub.disconnect(context);
            }
            this.client = null;
        }

        public Messenger getMessenger() {
            return this.mDownloaderClientStub.getMessenger();
        }

        public static DownloaderClientProxy getInstance() {
            return DownloaderClientProxyHolder.downloaderClientProxy;
        }
    }

    private static class XAPKFile {
        public final long mFileSize;
        public final int mFileVersion;
        public final boolean mIsMain;

        XAPKFile(boolean isMain, int fileVersion, long fileSize) {
            this.mIsMain = isMain;
            this.mFileVersion = fileVersion;
            this.mFileSize = fileSize;
        }
    }

    private void setState(int newState) {
        if (this.mState != newState) {
            this.mState = newState;
            this.mStatusText.setText(Helpers.getDownloaderStringResourceIDFromState(newState));
        }
    }

    /* access modifiers changed from: private */
    public void setButtonPausedState(boolean paused) {
        int stringResourceID;
        this.mStatePaused = paused;
        if (paused) {
            stringResourceID = ResourceUtil.getStringId(this, "text_button_resume");
        } else {
            stringResourceID = ResourceUtil.getStringId(this, "text_button_pause");
        }
        this.mPauseButton.setText(stringResourceID);
    }

    /* access modifiers changed from: 0000 */
    public boolean expansionFilesDelivered() {
        XAPKFile[] xAPKFileArr;
        for (XAPKFile xf : this.xAPKS) {
            if (!Helpers.doesFileExist(this, Helpers.getExpansionAPKFileName(this, xf.mIsMain, xf.mFileVersion), xf.mFileSize, false)) {
                return false;
            }
        }
        return true;
    }

    /* access modifiers changed from: 0000 */
    @SuppressLint({"StaticFieldLeak"})
    public void validateXAPKZipFiles() {
        new AsyncTask<Object, DownloadProgressInfo, Boolean>() {
            /* access modifiers changed from: protected */
            public void onPreExecute() {
                OBBDownloaderActivity.this.mDashboard.setVisibility(0);
                OBBDownloaderActivity.this.mCellMessage.setVisibility(8);
                OBBDownloaderActivity.this.mStatusText.setText(ResourceUtil.getStringId(OBBDownloaderActivity.this, "text_verifying_download"));
                OBBDownloaderActivity.this.mPauseButton.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        OBBDownloaderActivity.this.mCancelValidation = true;
                    }
                });
                OBBDownloaderActivity.this.mPauseButton.setText(ResourceUtil.getStringId(OBBDownloaderActivity.this, "text_button_cancel_verify"));
                super.onPreExecute();
            }

            /* access modifiers changed from: protected */
            /* JADX WARNING: Code restructure failed: missing block: B:35:0x014a, code lost:
                r3 = java.lang.Boolean.valueOf(true);
             */
            /* JADX WARNING: Code restructure failed: missing block: B:36:0x014f, code lost:
                if (r0 == null) goto L_?;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:38:?, code lost:
                r0.close();
             */
            /* JADX WARNING: Code restructure failed: missing block: B:46:0x0175, code lost:
                android.util.Log.e(com.google.android.vending.expansion.downloader.Constants.TAG, "CRC does not match for entry: " + r19.mFileName);
                android.util.Log.e(com.google.android.vending.expansion.downloader.Constants.TAG, "In file: " + r19.getZipFileName());
                r3 = java.lang.Boolean.valueOf(false);
             */
            /* JADX WARNING: Code restructure failed: missing block: B:47:0x01b2, code lost:
                if (r0 == null) goto L_?;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:49:?, code lost:
                r0.close();
             */
            /* JADX WARNING: Code restructure failed: missing block: B:71:?, code lost:
                return r3;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:73:?, code lost:
                return r3;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:74:?, code lost:
                return r3;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:76:?, code lost:
                return r3;
             */
            /* JADX WARNING: Removed duplicated region for block: B:55:0x01c7 A[Catch:{ IOException -> 0x0156 }] */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public java.lang.Boolean doInBackground(java.lang.Object... r41) {
                /*
                    r40 = this;
                    r0 = r40
                    com.btgame.onesdk.obb.OBBDownloaderActivity r3 = com.btgame.onesdk.obb.OBBDownloaderActivity.this
                    com.btgame.onesdk.obb.OBBDownloaderActivity$XAPKFile[] r34 = r3.xAPKS
                    r0 = r34
                    int r0 = r0.length
                    r35 = r0
                    r3 = 0
                    r33 = r3
                L_0x0010:
                    r0 = r33
                    r1 = r35
                    if (r0 >= r1) goto L_0x01d1
                    r30 = r34[r33]
                    r0 = r40
                    com.btgame.onesdk.obb.OBBDownloaderActivity r3 = com.btgame.onesdk.obb.OBBDownloaderActivity.this
                    r0 = r30
                    boolean r6 = r0.mIsMain
                    r0 = r30
                    int r7 = r0.mFileVersion
                    java.lang.String r20 = com.google.android.vending.expansion.downloader.Helpers.getExpansionAPKFileName(r3, r6, r7)
                    r0 = r40
                    com.btgame.onesdk.obb.OBBDownloaderActivity r3 = com.btgame.onesdk.obb.OBBDownloaderActivity.this
                    r0 = r30
                    long r6 = r0.mFileSize
                    r32 = 0
                    r0 = r20
                    r1 = r32
                    boolean r3 = com.google.android.vending.expansion.downloader.Helpers.doesFileExist(r3, r0, r6, r1)
                    if (r3 != 0) goto L_0x0042
                    r3 = 0
                    java.lang.Boolean r3 = java.lang.Boolean.valueOf(r3)
                L_0x0041:
                    return r3
                L_0x0042:
                    r0 = r40
                    com.btgame.onesdk.obb.OBBDownloaderActivity r3 = com.btgame.onesdk.obb.OBBDownloaderActivity.this
                    r0 = r20
                    java.lang.String r20 = com.google.android.vending.expansion.downloader.Helpers.generateSaveFileName(r3, r0)
                    r3 = 262144(0x40000, float:3.67342E-40)
                    byte[] r2 = new byte[r3]
                    com.android.vending.expansion.zipfile.ZipResourceFile r31 = new com.android.vending.expansion.zipfile.ZipResourceFile     // Catch:{ IOException -> 0x0156 }
                    r0 = r31
                    r1 = r20
                    r0.<init>(r1)     // Catch:{ IOException -> 0x0156 }
                    com.android.vending.expansion.zipfile.ZipResourceFile$ZipEntryRO[] r18 = r31.getAllEntries()     // Catch:{ IOException -> 0x0156 }
                    r4 = 0
                    r0 = r18
                    int r6 = r0.length     // Catch:{ IOException -> 0x0156 }
                    r3 = 0
                L_0x0063:
                    if (r3 >= r6) goto L_0x0072
                    r19 = r18[r3]     // Catch:{ IOException -> 0x0156 }
                    r0 = r19
                    long r0 = r0.mCompressedLength     // Catch:{ IOException -> 0x0156 }
                    r36 = r0
                    long r4 = r4 + r36
                    int r3 = r3 + 1
                    goto L_0x0063
                L_0x0072:
                    r10 = 0
                    r28 = r4
                    r0 = r18
                    int r0 = r0.length     // Catch:{ IOException -> 0x0156 }
                    r36 = r0
                    r3 = 0
                    r32 = r3
                L_0x007d:
                    r0 = r32
                    r1 = r36
                    if (r0 >= r1) goto L_0x01cb
                    r19 = r18[r32]     // Catch:{ IOException -> 0x0156 }
                    r6 = -1
                    r0 = r19
                    long r0 = r0.mCRC32     // Catch:{ IOException -> 0x0156 }
                    r38 = r0
                    int r3 = (r6 > r38 ? 1 : (r6 == r38 ? 0 : -1))
                    if (r3 == 0) goto L_0x01be
                    r0 = r19
                    long r0 = r0.mUncompressedLength     // Catch:{ IOException -> 0x0156 }
                    r22 = r0
                    java.util.zip.CRC32 r11 = new java.util.zip.CRC32     // Catch:{ IOException -> 0x0156 }
                    r11.<init>()     // Catch:{ IOException -> 0x0156 }
                    r13 = 0
                    java.io.DataInputStream r16 = new java.io.DataInputStream     // Catch:{ all -> 0x01c4 }
                    r0 = r19
                    java.lang.String r3 = r0.mFileName     // Catch:{ all -> 0x01c4 }
                    r0 = r31
                    java.io.InputStream r3 = r0.getInputStream(r3)     // Catch:{ all -> 0x01c4 }
                    r0 = r16
                    r0.<init>(r3)     // Catch:{ all -> 0x01c4 }
                    long r24 = android.os.SystemClock.uptimeMillis()     // Catch:{ all -> 0x01d8 }
                L_0x00b2:
                    r6 = 0
                    int r3 = (r22 > r6 ? 1 : (r22 == r6 ? 0 : -1))
                    if (r3 <= 0) goto L_0x0167
                    int r3 = r2.length     // Catch:{ all -> 0x01d8 }
                    long r6 = (long) r3     // Catch:{ all -> 0x01d8 }
                    int r3 = (r22 > r6 ? 1 : (r22 == r6 ? 0 : -1))
                    if (r3 <= 0) goto L_0x0161
                    int r3 = r2.length     // Catch:{ all -> 0x01d8 }
                    long r6 = (long) r3     // Catch:{ all -> 0x01d8 }
                L_0x00c0:
                    int r0 = (int) r6     // Catch:{ all -> 0x01d8 }
                    r21 = r0
                    r3 = 0
                    r0 = r16
                    r1 = r21
                    r0.readFully(r2, r3, r1)     // Catch:{ all -> 0x01d8 }
                    r3 = 0
                    r0 = r21
                    r11.update(r2, r3, r0)     // Catch:{ all -> 0x01d8 }
                    r0 = r21
                    long r6 = (long) r0     // Catch:{ all -> 0x01d8 }
                    long r22 = r22 - r6
                    r0 = r21
                    long r6 = (long) r0     // Catch:{ all -> 0x01d8 }
                    long r28 = r28 - r6
                    long r14 = android.os.SystemClock.uptimeMillis()     // Catch:{ all -> 0x01d8 }
                    long r26 = r14 - r24
                    r6 = 0
                    int r3 = (r26 > r6 ? 1 : (r26 == r6 ? 0 : -1))
                    if (r3 <= 0) goto L_0x013e
                    r0 = r21
                    float r3 = (float) r0     // Catch:{ all -> 0x01d8 }
                    r0 = r26
                    float r6 = (float) r0     // Catch:{ all -> 0x01d8 }
                    float r12 = r3 / r6
                    r3 = 0
                    int r3 = (r3 > r10 ? 1 : (r3 == r10 ? 0 : -1))
                    if (r3 == 0) goto L_0x0165
                    r3 = 1000593162(0x3ba3d70a, float:0.005)
                    float r3 = r3 * r12
                    r6 = 1065269330(0x3f7eb852, float:0.995)
                    float r6 = r6 * r10
                    float r10 = r3 + r6
                L_0x00fe:
                    r0 = r28
                    float r3 = (float) r0     // Catch:{ all -> 0x01d8 }
                    float r3 = r3 / r10
                    long r8 = (long) r3     // Catch:{ all -> 0x01d8 }
                    java.lang.String r3 = "ProgressBar"
                    java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x01d8 }
                    r6.<init>()     // Catch:{ all -> 0x01d8 }
                    java.lang.String r7 = "total:"
                    java.lang.StringBuilder r6 = r6.append(r7)     // Catch:{ all -> 0x01d8 }
                    java.lang.StringBuilder r6 = r6.append(r4)     // Catch:{ all -> 0x01d8 }
                    java.lang.String r7 = ",remain:"
                    java.lang.StringBuilder r6 = r6.append(r7)     // Catch:{ all -> 0x01d8 }
                    r0 = r28
                    java.lang.StringBuilder r6 = r6.append(r0)     // Catch:{ all -> 0x01d8 }
                    java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x01d8 }
                    android.util.Log.e(r3, r6)     // Catch:{ all -> 0x01d8 }
                    r3 = 1
                    com.google.android.vending.expansion.downloader.DownloadProgressInfo[] r0 = new com.google.android.vending.expansion.downloader.DownloadProgressInfo[r3]     // Catch:{ all -> 0x01d8 }
                    r37 = r0
                    r38 = 0
                    com.google.android.vending.expansion.downloader.DownloadProgressInfo r3 = new com.google.android.vending.expansion.downloader.DownloadProgressInfo     // Catch:{ all -> 0x01d8 }
                    long r6 = r4 - r28
                    r3.<init>(r4, r6, r8, r10)     // Catch:{ all -> 0x01d8 }
                    r37[r38] = r3     // Catch:{ all -> 0x01d8 }
                    r0 = r40
                    r1 = r37
                    r0.publishProgress(r1)     // Catch:{ all -> 0x01d8 }
                L_0x013e:
                    r24 = r14
                    r0 = r40
                    com.btgame.onesdk.obb.OBBDownloaderActivity r3 = com.btgame.onesdk.obb.OBBDownloaderActivity.this     // Catch:{ all -> 0x01d8 }
                    boolean r3 = r3.mCancelValidation     // Catch:{ all -> 0x01d8 }
                    if (r3 == 0) goto L_0x00b2
                    r3 = 1
                    java.lang.Boolean r3 = java.lang.Boolean.valueOf(r3)     // Catch:{ all -> 0x01d8 }
                    if (r16 == 0) goto L_0x0041
                    r16.close()     // Catch:{ IOException -> 0x0156 }
                    goto L_0x0041
                L_0x0156:
                    r17 = move-exception
                    r17.printStackTrace()
                    r3 = 0
                    java.lang.Boolean r3 = java.lang.Boolean.valueOf(r3)
                    goto L_0x0041
                L_0x0161:
                    r6 = r22
                    goto L_0x00c0
                L_0x0165:
                    r10 = r12
                    goto L_0x00fe
                L_0x0167:
                    long r6 = r11.getValue()     // Catch:{ all -> 0x01d8 }
                    r0 = r19
                    long r0 = r0.mCRC32     // Catch:{ all -> 0x01d8 }
                    r38 = r0
                    int r3 = (r6 > r38 ? 1 : (r6 == r38 ? 0 : -1))
                    if (r3 == 0) goto L_0x01b9
                    java.lang.String r3 = "LVLDL"
                    java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x01d8 }
                    r6.<init>()     // Catch:{ all -> 0x01d8 }
                    java.lang.String r7 = "CRC does not match for entry: "
                    java.lang.StringBuilder r6 = r6.append(r7)     // Catch:{ all -> 0x01d8 }
                    r0 = r19
                    java.lang.String r7 = r0.mFileName     // Catch:{ all -> 0x01d8 }
                    java.lang.StringBuilder r6 = r6.append(r7)     // Catch:{ all -> 0x01d8 }
                    java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x01d8 }
                    android.util.Log.e(r3, r6)     // Catch:{ all -> 0x01d8 }
                    java.lang.String r3 = "LVLDL"
                    java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x01d8 }
                    r6.<init>()     // Catch:{ all -> 0x01d8 }
                    java.lang.String r7 = "In file: "
                    java.lang.StringBuilder r6 = r6.append(r7)     // Catch:{ all -> 0x01d8 }
                    java.lang.String r7 = r19.getZipFileName()     // Catch:{ all -> 0x01d8 }
                    java.lang.StringBuilder r6 = r6.append(r7)     // Catch:{ all -> 0x01d8 }
                    java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x01d8 }
                    android.util.Log.e(r3, r6)     // Catch:{ all -> 0x01d8 }
                    r3 = 0
                    java.lang.Boolean r3 = java.lang.Boolean.valueOf(r3)     // Catch:{ all -> 0x01d8 }
                    if (r16 == 0) goto L_0x0041
                    r16.close()     // Catch:{ IOException -> 0x0156 }
                    goto L_0x0041
                L_0x01b9:
                    if (r16 == 0) goto L_0x01be
                    r16.close()     // Catch:{ IOException -> 0x0156 }
                L_0x01be:
                    int r3 = r32 + 1
                    r32 = r3
                    goto L_0x007d
                L_0x01c4:
                    r3 = move-exception
                L_0x01c5:
                    if (r13 == 0) goto L_0x01ca
                    r13.close()     // Catch:{ IOException -> 0x0156 }
                L_0x01ca:
                    throw r3     // Catch:{ IOException -> 0x0156 }
                L_0x01cb:
                    int r3 = r33 + 1
                    r33 = r3
                    goto L_0x0010
                L_0x01d1:
                    r3 = 1
                    java.lang.Boolean r3 = java.lang.Boolean.valueOf(r3)
                    goto L_0x0041
                L_0x01d8:
                    r3 = move-exception
                    r13 = r16
                    goto L_0x01c5
                */
                throw new UnsupportedOperationException("Method not decompiled: com.btgame.onesdk.obb.OBBDownloaderActivity.C08091.doInBackground(java.lang.Object[]):java.lang.Boolean");
            }

            /* access modifiers changed from: protected */
            public void onProgressUpdate(DownloadProgressInfo... values) {
                OBBDownloaderActivity.this.onDownloadProgress(values[0]);
                super.onProgressUpdate(values);
            }

            /* access modifiers changed from: protected */
            public void onPostExecute(Boolean result) {
                if (result.booleanValue()) {
                    OBBDownloaderActivity.this.mDashboard.setVisibility(0);
                    OBBDownloaderActivity.this.mCellMessage.setVisibility(8);
                    OBBDownloaderActivity.this.mStatusText.setText(ResourceUtil.getStringId(OBBDownloaderActivity.this, "text_validation_complete"));
                    OBBDownloaderActivity.this.mPauseButton.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            OBBDownloaderActivity.this.mHandler.sendEmptyMessage(0);
                        }
                    });
                    OBBDownloaderActivity.this.mPauseButton.setText(17039370);
                    if (!OBBDownloaderActivity.this.isValidateFinish) {
                        OBBDownloaderActivity.this.isValidateFinish = true;
                        BtDataSdkManager.getInstance(OBBDownloaderActivity.this).submitBaseEventData(Event.EVENT_ID_OBB_VALIDATE_FINISH, "", "", "");
                    }
                } else {
                    OBBDownloaderActivity.this.mDashboard.setVisibility(0);
                    OBBDownloaderActivity.this.mCellMessage.setVisibility(8);
                    OBBDownloaderActivity.this.mStatusText.setText(ResourceUtil.getStringId(OBBDownloaderActivity.this, "text_validation_failed"));
                    OBBDownloaderActivity.this.mPauseButton.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            OBBDownloaderActivity.this.mHandler.sendEmptyMessage(-2);
                        }
                    });
                    OBBDownloaderActivity.this.mPauseButton.setText(17039360);
                }
                super.onPostExecute(result);
            }
        }.execute(new Object[]{new Object()});
    }

    private void initializeDownloadUI() {
        this.toDownload = true;
        setContentView(ResourceUtil.getLayoutId(this, "main"));
        this.mPB = (ProgressBar) findViewById(ResourceUtil.getId(this, "progressBar"));
        this.mStatusText = (TextView) findViewById(ResourceUtil.getId(this, "statusText"));
        this.mProgressFraction = (TextView) findViewById(ResourceUtil.getId(this, "progressAsFraction"));
        this.mProgressPercent = (TextView) findViewById(ResourceUtil.getId(this, "progressAsPercentage"));
        this.mAverageSpeed = (TextView) findViewById(ResourceUtil.getId(this, "progressAverageSpeed"));
        this.mTimeRemaining = (TextView) findViewById(ResourceUtil.getId(this, "progressTimeRemaining"));
        this.mDashboard = findViewById(ResourceUtil.getId(this, "downloaderDashboard"));
        this.mCellMessage = findViewById(ResourceUtil.getId(this, "approveCellular"));
        this.mPauseButton = (Button) findViewById(ResourceUtil.getId(this, "pauseButton"));
        this.mWiFiSettingsButton = (Button) findViewById(ResourceUtil.getId(this, "wifiSettingsButton"));
        this.mPauseButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (OBBDownloaderActivity.this.mStatePaused) {
                    OBBDownloaderActivity.this.mRemoteService.requestContinueDownload();
                    BtDataSdkManager.getInstance(OBBDownloaderActivity.this).submitBaseEventData(Event.EVENT_ID_OBB_DOWNLOAD_RESUME, "", "", "");
                } else {
                    OBBDownloaderActivity.this.mRemoteService.requestPauseDownload();
                    BtDataSdkManager.getInstance(OBBDownloaderActivity.this).submitBaseEventData(Event.EVENT_ID_OBB_DOWNLOAD_PAUSE, "", "", "");
                }
                OBBDownloaderActivity.this.setButtonPausedState(!OBBDownloaderActivity.this.mStatePaused);
            }
        });
        this.mWiFiSettingsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                OBBDownloaderActivity.this.startActivity(new Intent("android.settings.WIFI_SETTINGS"));
            }
        });
        ((Button) findViewById(ResourceUtil.getId(this, "resumeOverCellular"))).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                OBBDownloaderActivity.this.mRemoteService.setDownloadFlags(1);
                OBBDownloaderActivity.this.mRemoteService.requestContinueDownload();
                OBBDownloaderActivity.this.mCellMessage.setVisibility(8);
            }
        });
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.isDownloadFinish = false;
        this.isValidateFinish = false;
        checkAndDownloadOBB();
    }

    /* access modifiers changed from: private */
    public void checkAndDownloadOBB() {
        int obbVersion = 0;
        try {
            obbVersion = getPackageManager().getPackageInfo(getPackageName(), 0).versionCode;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        long obbSize = Long.valueOf(BtUtils.getMeTaData(this, "btObbSize")).longValue();
        this.xAPKS[0] = new XAPKFile(true, obbVersion, obbSize);
        Log.d(BtsdkLog.TAG, "obbVersion:" + obbVersion);
        Log.d(BtsdkLog.TAG, "obbSize:" + obbSize);
        if (!expansionFilesDelivered()) {
            initializeDownloadUI();
            Log.e("tag", "expansion files delivered false");
            try {
                Intent launchIntent = getIntent();
                Intent intentToLaunchThisActivityFromNotification = new Intent(this, getClass());
                intentToLaunchThisActivityFromNotification.setFlags(335544320);
                intentToLaunchThisActivityFromNotification.setAction(launchIntent.getAction());
                if (launchIntent.getCategories() != null) {
                    for (String category : launchIntent.getCategories()) {
                        intentToLaunchThisActivityFromNotification.addCategory(category);
                    }
                }
                if (DownloaderClientMarshaller.startDownloadServiceIfRequired((Context) this, PendingIntent.getActivity(this, 0, intentToLaunchThisActivityFromNotification, 134217728), OBBDownloaderService.class) != 0) {
                    initializeDownloadUI();
                    BtDataSdkManager.getInstance(this).submitBaseEventData(Event.EVENT_ID_OBB_DOWNLOAD_BEGIN, "", "", "");
                }
            } catch (NameNotFoundException e2) {
                Log.e(LOG_TAG, "Cannot find own package! MAYDAY!");
                e2.printStackTrace();
            }
        } else {
            Log.e("tag", "expansion files delivered true");
            this.mHandler.sendEmptyMessage(0);
        }
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        if (this.toDownload) {
            DownloaderClientProxy.getInstance().connect(this, this);
        }
        super.onStart();
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        DownloaderClientProxy.getInstance().disConnect(this);
        super.onStop();
    }

    public void onServiceConnected(Messenger m) {
        this.mRemoteService = DownloaderServiceMarshaller.CreateProxy(m);
        this.mRemoteService.onClientUpdated(DownloaderClientProxy.getInstance().getMessenger());
    }

    public void onDownloadStateChanged(int newState) {
        boolean paused;
        boolean indeterminate;
        int newDashboardVisibility;
        int cellMessageVisibility;
        setState(newState);
        boolean showDashboard = true;
        boolean showCellMessage = false;
        switch (newState) {
            case 1:
                paused = false;
                indeterminate = true;
                break;
            case 2:
            case 3:
                showDashboard = true;
                paused = false;
                indeterminate = true;
                break;
            case 4:
                paused = false;
                showDashboard = true;
                indeterminate = false;
                break;
            case 5:
                if (!this.isDownloadFinish) {
                    this.isDownloadFinish = true;
                    BtDataSdkManager.getInstance(this).submitBaseEventData(Event.EVENT_ID_OBB_DOWNLOAD_FINISH, "", "", "");
                    validateXAPKZipFiles();
                    return;
                }
                return;
            case 7:
                paused = true;
                indeterminate = false;
                break;
            case 8:
            case 9:
                showDashboard = false;
                paused = true;
                indeterminate = false;
                showCellMessage = true;
                this.mHandler.sendMessage(getMessage(1, newState));
                break;
            case 12:
            case 14:
                paused = true;
                indeterminate = false;
                this.mHandler.sendMessage(getMessage(1, newState));
                break;
            case 15:
            case 16:
            case 18:
            case 19:
                paused = true;
                showDashboard = false;
                indeterminate = false;
                this.mHandler.sendMessage(getMessage(1, newState));
                break;
            default:
                paused = true;
                indeterminate = true;
                showDashboard = true;
                break;
        }
        if (showDashboard) {
            newDashboardVisibility = 0;
        } else {
            newDashboardVisibility = 8;
        }
        if (this.mDashboard.getVisibility() != newDashboardVisibility) {
            this.mDashboard.setVisibility(newDashboardVisibility);
        }
        if (showCellMessage) {
            cellMessageVisibility = 0;
        } else {
            cellMessageVisibility = 8;
        }
        if (this.mCellMessage.getVisibility() != cellMessageVisibility) {
            this.mCellMessage.setVisibility(cellMessageVisibility);
        }
        this.mPB.setIndeterminate(indeterminate);
        setButtonPausedState(paused);
    }

    public void onDownloadProgress(DownloadProgressInfo progress) {
        this.mAverageSpeed.setText(getString(ResourceUtil.getStringId(this, "kilobytes_per_second"), new Object[]{Helpers.getSpeedString(progress.mCurrentSpeed)}));
        this.mTimeRemaining.setText(getString(ResourceUtil.getStringId(this, "time_remaining"), new Object[]{Helpers.getTimeRemaining(progress.mTimeRemaining)}));
        progress.mOverallTotal = progress.mOverallTotal;
        this.mPB.setMax((int) (progress.mOverallTotal >> 8));
        this.mPB.setProgress((int) (progress.mOverallProgress >> 8));
        this.mProgressPercent.setText(Long.toString((progress.mOverallProgress * 100) / progress.mOverallTotal) + "%");
        this.mProgressFraction.setText(Helpers.getDownloadProgressString(progress.mOverallProgress, progress.mOverallTotal));
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        this.mCancelValidation = true;
        BtsdkLog.m1423d("onDestroy");
        super.onDestroy();
    }

    private Message getMessage(int what, int arg1) {
        Message message = Message.obtain();
        message.what = what;
        message.arg1 = arg1;
        return message;
    }
}
