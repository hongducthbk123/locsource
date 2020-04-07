package com.baitian.util;

import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.support.p003v7.app.AlertDialog.Builder;

public class DisplayUtil {
    public static void showMessage(Context context, String title, String content) {
        new Builder(context).setTitle((CharSequence) title).setMessage((CharSequence) content).setPositiveButton((CharSequence) "OK", (OnClickListener) null).show();
    }
}
