package com.btgame.onesdk.frame.p044ui;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.btgame.sdk.util.BtsdkLog;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* renamed from: com.btgame.onesdk.frame.ui.SplashDialog */
public class SplashDialog extends Dialog {

    /* renamed from: am */
    private AssetManager f930am;
    /* access modifiers changed from: private */
    public Handler changeImageHandler;
    /* access modifiers changed from: private */
    public Bitmap imgBitmap;
    /* access modifiers changed from: private */
    public int index = 0;
    private Context mCtx;
    /* access modifiers changed from: private */
    public List<String> picNames;
    /* access modifiers changed from: private */
    public ScheduledExecutorService schedul;
    /* access modifiers changed from: private */
    public ImageView splash;
    private long time;

    /* renamed from: com.btgame.onesdk.frame.ui.SplashDialog$ComparatorPic */
    public class ComparatorPic implements Comparator {
        public ComparatorPic() {
        }

        public int compare(Object arg0, Object arg1) {
            String splash1 = (String) arg0;
            String splash2 = (String) arg1;
            String counter1 = splash1.substring(splash1.lastIndexOf("_") + 1, splash1.lastIndexOf("."));
            BtsdkLog.m1427p("counter1", counter1);
            String counter2 = splash2.substring(splash2.lastIndexOf("_") + 1, splash2.lastIndexOf("."));
            BtsdkLog.m1427p("counter2", counter2);
            try {
                if (Integer.parseInt(counter1) > Integer.parseInt(counter2)) {
                    return 1;
                }
                return -1;
            } catch (Exception e) {
                BtsdkLog.m1423d("比較出錯");
                return 1;
            }
        }
    }

    public SplashDialog(Context context, List<String> picName, long time2) {
        super(context, 16973838);
        this.mCtx = context;
        this.time = time2;
        this.picNames = picName;
        this.f930am = context.getAssets();
        Collections.sort(picName, new ComparatorPic());
        BtsdkLog.m1423d("new SplashDialog");
        LinearLayout layout = new LinearLayout(this.mCtx);
        LayoutParams lp = new LayoutParams(-1, -1);
        this.splash = new ImageView(this.mCtx);
        this.splash.setScaleType(ScaleType.FIT_XY);
        layout.addView(this.splash, lp);
        setContentView(layout, lp);
        initView();
    }

    @SuppressLint({"HandlerLeak"})
    private void initView() {
        setCancelable(false);
        this.changeImageHandler = new Handler() {
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what != 1) {
                    return;
                }
                if (SplashDialog.this.index < SplashDialog.this.picNames.size()) {
                    BitmapDrawable bd = (BitmapDrawable) SplashDialog.this.splash.getDrawable();
                    if (bd != null && !bd.getBitmap().isRecycled()) {
                        bd.getBitmap().recycle();
                    }
                    SplashDialog.this.imgBitmap = SplashDialog.this.getBitmapAt(SplashDialog.this.index);
                    SplashDialog.this.splash.setImageBitmap(SplashDialog.this.imgBitmap);
                    SplashDialog.this.index = SplashDialog.this.index + 1;
                } else if (SplashDialog.this.schedul != null) {
                    BtsdkLog.m1423d("SplashDialog is dismiss");
                    SplashDialog.this.schedul.shutdownNow();
                    SplashDialog.this.schedul = null;
                }
            }
        };
    }

    public void show() {
        super.show();
        BtsdkLog.m1423d("SplashDialog begin to show");
        this.schedul = Executors.newScheduledThreadPool(1);
        this.schedul.scheduleAtFixedRate(new Runnable() {
            public void run() {
                SplashDialog.this.changeImageHandler.sendEmptyMessage(1);
            }
        }, 0, this.time, TimeUnit.MILLISECONDS);
    }

    /* access modifiers changed from: private */
    public Bitmap getBitmapAt(int index2) {
        try {
            return BitmapFactory.decodeStream(this.f930am.open("btgame/" + ((String) this.picNames.get(index2))));
        } catch (IOException e) {
            BtsdkLog.m1423d("獲取閃屏頁的bitmap出錯");
            e.printStackTrace();
            dismiss();
            cancel();
            return null;
        }
    }
}
