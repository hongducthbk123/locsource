package com.baitian.datasdk.loadingui;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.baitian.datasdk.util.BitmapCache;
import com.baitian.datasdk.util.DimensionUtil;

public class SDKDialog extends Dialog {
    private Context mContext;
    private TextView message_tv;

    public SDKDialog(Context context) {
        super(context);
        this.mContext = context;
        init();
    }

    private void init() {
        int padding = DimensionUtil.dip2px(this.mContext, 30);
        getWindow().requestFeature(1);
        getWindow().setBackgroundDrawable(new ColorDrawable(0));
        LinearLayout content = new LinearLayout(getContext());
        content.setOrientation(0);
        content.setGravity(17);
        content.setPadding(padding, padding, padding, padding);
        BitmapCache.getInstance();
        content.setBackgroundDrawable(BitmapCache.getStateListDrawableColor(1999844147, 1999844147, 7, 0));
        ProgressBar bar = new ProgressBar(getContext());
        this.message_tv = new TextView(getContext());
        this.message_tv.setTextSize(18.0f);
        this.message_tv.setTextColor(-1);
        content.addView(bar, new LayoutParams(DimensionUtil.dip2px(getContext(), 25), DimensionUtil.dip2px(getContext(), 25)));
        LayoutParams ls = new LayoutParams(-2, -2);
        ls.leftMargin = DimensionUtil.dip2px(getContext(), 10);
        content.addView(this.message_tv, ls);
        setContentView(content, new ViewGroup.LayoutParams(-1, -1));
        setCanceledOnTouchOutside(false);
    }

    public void showDialog(String message) {
        if (message == null || "".equals(message)) {
            this.message_tv.setVisibility(8);
        }
        this.message_tv.setText(message);
        super.show();
    }

    public void dismiss() {
        super.dismiss();
    }

    public void cancel() {
        super.cancel();
        this.mContext = null;
    }

    public void onBackPressed() {
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return keyCode != 4;
    }
}
