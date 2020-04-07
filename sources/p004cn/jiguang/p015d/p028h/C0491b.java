package p004cn.jiguang.p015d.p028h;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.text.TextUtils;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONObject;

/* renamed from: cn.jiguang.d.h.b */
public final class C0491b implements ServiceConnection {

    /* renamed from: a */
    public static HashMap<String, WeakReference<ServiceConnection>> f530a = new HashMap<>();

    /* renamed from: b */
    public Context f531b;

    /* renamed from: c */
    private boolean f532c = false;

    public C0491b(Context context, boolean z) {
        this.f531b = context;
        this.f532c = z;
    }

    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        String str = null;
        if (componentName != null) {
            try {
                str = componentName.getPackageName() + componentName.getClassName();
            } catch (Throwable th) {
            }
        }
        if (!TextUtils.isEmpty(str)) {
            f530a.remove(str);
        }
        this.f531b.getApplicationContext().unbindService(this);
        try {
            if (this.f532c) {
                ArrayList arrayList = new ArrayList();
                C0493d dVar = new C0493d();
                dVar.mo6645a(componentName);
                if (componentName != null) {
                    dVar.mo6644a(2, true);
                } else {
                    dVar.mo6644a(2, false);
                }
                arrayList.add(dVar);
                JSONObject a = C0495f.m858a().mo6655b().mo6647a(this.f531b.getPackageName(), arrayList);
                if (a != null) {
                    C0494e.m844b(this.f531b, "android_awake", a);
                }
            }
        } catch (Throwable th2) {
        }
    }

    public final void onServiceDisconnected(ComponentName componentName) {
    }
}
