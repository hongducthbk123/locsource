package p004cn.jpush.android.api;

import android.app.Activity;

/* renamed from: cn.jpush.android.api.InstrumentedActivity */
public class InstrumentedActivity extends Activity {
    public void onStart() {
        super.onStart();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        JPushInterface.onResume(this);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        JPushInterface.onPause(this);
    }

    public void onStop() {
        super.onStop();
    }
}
