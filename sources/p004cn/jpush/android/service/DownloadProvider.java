package p004cn.jpush.android.service;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import p004cn.jiguang.api.JCoreInterface;
import p004cn.jiguang.p015d.C0385a;
import p004cn.jiguang.p015d.p028h.C0492c;
import p004cn.jiguang.p015d.p028h.C0495f;

/* renamed from: cn.jpush.android.service.DownloadProvider */
public class DownloadProvider extends ContentProvider {
    private static final String TAG = "DownloadProvider";

    private void init() {
        try {
            if (JCoreInterface.init(getContext().getApplicationContext(), false)) {
                JCoreInterface.register(getContext());
            }
        } catch (Throwable th) {
        }
    }

    private void report(int i, boolean z, Uri uri) {
        if (C0492c.m835a(getContext())) {
            String str = "";
            String str2 = "";
            String str3 = "";
            if (uri != null) {
                try {
                    str = uri.getQueryParameter("from_package");
                    str2 = uri.getQueryParameter("from_uid");
                    str3 = uri.getQueryParameter("awake_sequence");
                } catch (Throwable th) {
                    return;
                }
            }
            C0495f.m858a().mo6655b().mo6648a(getContext(), i, z, str, str2, str3);
        }
    }

    public int delete(Uri uri, String str, String[] strArr) {
        init();
        return 0;
    }

    public String getType(Uri uri) {
        init();
        return "1.2.0";
    }

    public Uri insert(Uri uri, ContentValues contentValues) {
        init();
        return null;
    }

    public boolean onCreate() {
        return true;
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        report(4, C0385a.f205l, uri);
        return null;
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        init();
        return 0;
    }
}
