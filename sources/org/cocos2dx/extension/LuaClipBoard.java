package org.cocos2dx.extension;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import org.cocos2dx.lib.Cocos2dxActivity;

public class LuaClipBoard {
    public static boolean setClipBoardContent(final String str) {
        final Activity context = (Activity) Cocos2dxActivity.getContext();
        context.runOnUiThread(new Runnable() {
            @SuppressLint({"NewApi"})
            public void run() {
                System.out.println("run setClipboard");
                ((ClipboardManager) context.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText(null, str));
            }
        });
        return true;
    }
}
