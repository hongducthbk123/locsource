package com.btgame.seasdk.btcore.widget.webview;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient.FileChooserParams;
import android.widget.Toast;

public class UploadHandler {
    private static final String TAG = "UploadHandler";
    private Activity activity;
    private boolean mCaughtActivityNotFoundException;
    ValueCallback<Uri[]> mFilePathCallback;
    private ValueCallback<Uri> mUploadMessage;

    public UploadHandler(Activity activity2) {
        this.activity = activity2;
    }

    public void onResult(int requestCode, int resultCode, Intent intent) {
        Log.d(TAG, "onActivityResult: requestCode-->" + requestCode + ",resultCode-->" + resultCode);
        if (resultCode != 0 || this.mCaughtActivityNotFoundException) {
            if (resultCode == -1 && intent != null) {
                Uri result = intent.getData();
                if (requestCode == 4) {
                    Log.d(TAG, "requestCode == FILE_SELECTED");
                    if (this.mUploadMessage != null) {
                        this.mUploadMessage.onReceiveValue(result);
                    } else if (this.mFilePathCallback != null) {
                        this.mFilePathCallback.onReceiveValue(new Uri[]{result});
                    } else {
                        Log.d(TAG, "mUploadMessage and mFilePathCallback is null");
                    }
                }
            }
            this.mCaughtActivityNotFoundException = false;
            this.mUploadMessage = null;
            this.mFilePathCallback = null;
            return;
        }
        Log.d(TAG, "Activity.RESULT_CANCELED");
        this.mCaughtActivityNotFoundException = false;
        if (this.mUploadMessage != null) {
            this.mUploadMessage.onReceiveValue(null);
        } else if (this.mFilePathCallback != null) {
            this.mFilePathCallback.onReceiveValue(null);
        }
        this.mUploadMessage = null;
        this.mFilePathCallback = null;
    }

    public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
        if (this.mUploadMessage != null) {
            this.mUploadMessage.onReceiveValue(null);
            this.mUploadMessage = null;
        }
        Log.d(TAG, "acceptType:" + acceptType + " ,capture:" + capture);
        this.mUploadMessage = uploadMsg;
        startActivity(createDefaultOpenableIntent());
    }

    private void startActivity(Intent intent) {
        try {
            this.activity.startActivityForResult(intent, 4);
        } catch (ActivityNotFoundException e) {
            this.mCaughtActivityNotFoundException = true;
            Toast.makeText(this.activity, "File uploads are disabled.", 0).show();
        }
    }

    private Intent createDefaultOpenableIntent() {
        Intent i = new Intent("android.intent.action.GET_CONTENT");
        i.addCategory("android.intent.category.OPENABLE");
        i.setType("*/*");
        Intent chooser = createChooserIntent(new Intent[0]);
        chooser.putExtra("android.intent.extra.INTENT", i);
        return chooser;
    }

    private Intent createChooserIntent(Intent... intents) {
        Intent chooser = new Intent("android.intent.action.CHOOSER");
        chooser.putExtra("android.intent.extra.INITIAL_INTENTS", intents);
        chooser.putExtra("android.intent.extra.TITLE", "Choose file for upload");
        return chooser;
    }

    public void onShowFileChooser(ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
        if (this.mFilePathCallback != null) {
            this.mFilePathCallback.onReceiveValue(null);
            this.mFilePathCallback = null;
        }
        this.mFilePathCallback = filePathCallback;
        startActivity(createDefaultOpenableIntent());
    }
}
