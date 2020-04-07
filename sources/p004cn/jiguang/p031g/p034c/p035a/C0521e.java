package p004cn.jiguang.p031g.p034c.p035a;

import android.content.Context;
import android.telephony.TelephonyManager;
import com.facebook.places.model.PlaceFields;
import java.lang.reflect.Method;
import java.util.ArrayList;
import p004cn.jiguang.p031g.p034c.C0516a;

/* renamed from: cn.jiguang.g.c.a.e */
public class C0521e extends C0517a {
    /* renamed from: a */
    public final ArrayList<C0516a> mo6695a(Context context) {
        ArrayList arrayList = new ArrayList();
        Class cls = Class.forName("com.android.internal.telephony.PhoneFactory");
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService((String) cls.getMethod("getServiceName", new Class[]{String.class, Integer.TYPE}).invoke(cls, new Object[]{PlaceFields.PHONE, Integer.valueOf(1)}));
        TelephonyManager telephonyManager2 = (TelephonyManager) context.getSystemService(PlaceFields.PHONE);
        C0516a aVar = new C0516a();
        try {
            aVar.f591c = telephonyManager2.getSubscriberId();
            aVar.f590b = telephonyManager2.getDeviceId();
            aVar.f593e = telephonyManager2.getSimSerialNumber();
            aVar.f592d = telephonyManager2.getPhoneType();
        } catch (Throwable th) {
        }
        try {
            arrayList.add(aVar);
            C0516a aVar2 = new C0516a();
            try {
                aVar2.f591c = telephonyManager.getSubscriberId();
                aVar2.f590b = telephonyManager.getDeviceId();
                aVar2.f593e = telephonyManager.getSimSerialNumber();
                aVar2.f592d = telephonyManager.getPhoneType();
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
            Class cls = Class.forName("com.android.internal.telephony.PhoneFactory");
            Method method = cls.getMethod("getServiceName", new Class[]{String.class, Integer.TYPE});
            String str = (String) method.invoke(cls, new Object[]{PlaceFields.PHONE, Integer.valueOf(1)});
            return (method == null || str == null || ((TelephonyManager) context.getSystemService(str)) == null) ? false : true;
        } catch (Throwable th) {
            return false;
        }
    }
}
