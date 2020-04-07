package p004cn.jiguang.net;

import android.content.Context;
import android.os.AsyncTask;
import p004cn.jiguang.net.HttpUtils.HttpListener;

/* renamed from: cn.jiguang.net.b */
final class C0536b extends AsyncTask<String, Void, HttpResponse> {

    /* renamed from: a */
    private HttpListener f640a;

    /* renamed from: b */
    private Context f641b;

    public C0536b(HttpListener httpListener, Context context) {
        this.f640a = httpListener;
        this.f641b = context;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ Object doInBackground(Object[] objArr) {
        String[] strArr = (String[]) objArr;
        if (strArr == null || strArr.length == 0) {
            return null;
        }
        return HttpUtils.httpGet(this.f641b, strArr[0]);
    }

    /* access modifiers changed from: protected */
    public final /* bridge */ /* synthetic */ void onPostExecute(Object obj) {
    }

    /* access modifiers changed from: protected */
    public final void onPreExecute() {
    }
}
