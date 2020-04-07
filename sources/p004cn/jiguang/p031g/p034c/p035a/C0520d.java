package p004cn.jiguang.p031g.p034c.p035a;

import android.content.Context;
import android.telephony.TelephonyManager;
import com.facebook.places.model.PlaceFields;
import java.lang.reflect.Method;
import java.util.ArrayList;
import p004cn.jiguang.p031g.p034c.C0516a;

/* renamed from: cn.jiguang.g.c.a.d */
public class C0520d extends C0517a {
    /* renamed from: a */
    public final ArrayList<C0516a> mo6695a(Context context) {
        ArrayList arrayList = new ArrayList();
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(PlaceFields.PHONE);
        Class cls = Class.forName("android.telephony.MSimTelephonyManager");
        Object systemService = context.getSystemService("phone_msim");
        Method method = cls.getMethod("getDataState", new Class[0]);
        Method method2 = cls.getMethod("getDeviceId", new Class[]{Integer.TYPE});
        Method method3 = cls.getMethod("getSubscriberId", new Class[]{Integer.TYPE});
        C0516a aVar = new C0516a();
        try {
            aVar.f590b = (String) method2.invoke(systemService, new Object[]{Integer.valueOf(0)});
            aVar.f591c = (String) method3.invoke(systemService, new Object[]{Integer.valueOf(0)});
            aVar.f592d = telephonyManager.getDataState();
        } catch (Throwable th) {
        }
        try {
            arrayList.add(aVar);
            C0516a aVar2 = new C0516a();
            try {
                aVar2.f590b = (String) method2.invoke(systemService, new Object[]{Integer.valueOf(1)});
                aVar2.f591c = (String) method3.invoke(systemService, new Object[]{Integer.valueOf(1)});
                aVar2.f592d = ((Integer) method.invoke(systemService, new Object[0])).intValue();
            } catch (Throwable th2) {
            }
            arrayList.add(aVar2);
            return arrayList;
        } catch (Throwable th3) {
            return null;
        }
    }

    /* renamed from: b */
    public final boolean mo6696b(Context context) {
        try {
            Class cls = Class.forName("android.telephony.MSimTelephonyManager");
            Object systemService = context.getSystemService("phone_msim");
            cls.getMethod("getDataState", new Class[0]);
            return (systemService == null || cls.getMethod("getDeviceId", new Class[]{Integer.TYPE}) == null || cls.getMethod("getSubscriberId", new Class[]{Integer.TYPE}) == null) ? false : true;
        } catch (Throwable th) {
            return false;
        }
    }
}
