package p004cn.jiguang.net;

import android.content.Context;
import android.os.AsyncTask;
import p004cn.jiguang.net.HttpUtils.HttpListener;

/* renamed from: cn.jiguang.net.a */
final class C0535a extends AsyncTask<HttpRequest, Void, HttpResponse> {

    /* renamed from: a */
    private HttpListener f638a;

    /* renamed from: b */
    private Context f639b;

    public C0535a(Context context, HttpListener httpListener) {
        this.f638a = httpListener;
        this.f639b = context;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ Object doInBackground(Object[] objArr) {
        HttpRequest[] httpRequestArr = (HttpRequest[]) objArr;
        if (httpRequestArr == null || httpRequestArr.length == 0) {
            return null;
        }
        return HttpUtils.httpGet(this.f639b, httpRequestArr[0]);
    }

    /* access modifiers changed from: protected */
    public final /* bridge */ /* synthetic */ void onPostExecute(Object obj) {
    }

    /* access modifiers changed from: protected */
    public final void onPreExecute() {
    }
}
