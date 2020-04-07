package p004cn.jpush.android.api;

import android.content.Context;
import android.widget.RemoteViews;
import p004cn.jpush.android.C0541a;

/* renamed from: cn.jpush.android.api.CustomPushNotificationBuilder */
public class CustomPushNotificationBuilder extends BasicPushNotificationBuilder {

    /* renamed from: b */
    private int f691b;
    public int layout;
    public int layoutContentId;
    public int layoutIconDrawable = C0541a.f650b;
    public int layoutIconId;
    public int layoutTitleId;

    CustomPushNotificationBuilder(Context context) {
        super(context);
    }

    public CustomPushNotificationBuilder(Context context, int i, int i2, int i3, int i4) {
        super(context);
        this.layout = i;
        this.layoutIconId = i2;
        this.layoutTitleId = i3;
        this.layoutContentId = i4;
    }

    /* access modifiers changed from: 0000 */
    public RemoteViews buildContentView(String str, String str2) {
        RemoteViews remoteViews = new RemoteViews(this.f690a.getPackageName(), this.layout);
        remoteViews.setTextViewText(this.layoutTitleId, str2);
        remoteViews.setImageViewResource(this.layoutIconId, this.layoutIconDrawable);
        remoteViews.setTextViewText(this.layoutContentId, str);
        if (this.f691b != 0) {
            remoteViews.setLong(this.f691b, "setTime", System.currentTimeMillis());
        }
        return remoteViews;
    }

    public String toString() {
        return "custom_____" + mo6787a();
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public final String mo6787a() {
        return super.mo6787a() + "_____" + this.layout + "_____" + this.layoutIconId + "_____" + this.layoutTitleId + "_____" + this.layoutContentId + "_____" + this.layoutIconDrawable + "_____" + this.f691b;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public final void mo6788a(String[] strArr) throws NumberFormatException {
        super.mo6788a(strArr);
        this.layout = Integer.parseInt(strArr[5]);
        this.layoutIconId = Integer.parseInt(strArr[6]);
        this.layoutTitleId = Integer.parseInt(strArr[7]);
        this.layoutContentId = Integer.parseInt(strArr[8]);
        this.layoutIconDrawable = Integer.parseInt(strArr[9]);
        if (strArr.length == 11) {
            this.f691b = Integer.parseInt(strArr[10]);
        }
    }
}
