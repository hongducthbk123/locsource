package p004cn.jiguang.p031g.p034c.p035a;

import android.content.Context;
import android.telephony.TelephonyManager;
import com.facebook.places.model.PlaceFields;
import java.lang.reflect.Method;
import java.util.ArrayList;
import p004cn.jiguang.p031g.p034c.C0516a;

/* renamed from: cn.jiguang.g.c.a.b */
public final class C0518b extends C0517a {
    /* renamed from: a */
    private static int m1050a(int i) {
        try {
            Method declaredMethod = Class.forName("android.telephony.SubscriptionManager").getDeclaredMethod("getSubId", new Class[]{Integer.TYPE});
            declaredMethod.setAccessible(true);
            int[] iArr = (int[]) declaredMethod.invoke(null, new Object[]{Integer.valueOf(i)});
            return iArr.length > 0 ? iArr[0] : i;
        } catch (Throwable th) {
            return i;
        }
    }

    /* renamed from: a */
    private static int m1051a(int i, int i2) {
        try {
            Method declaredMethod = Class.forName("android.telephony.SubscriptionManager").getDeclaredMethod("getPhoneId", new Class[]{Integer.TYPE});
            declaredMethod.setAccessible(true);
            return ((Integer) declaredMethod.invoke(null, new Object[]{Integer.valueOf(i2)})).intValue();
        } catch (Throwable th) {
            return i;
        }
    }

    /* renamed from: a */
    private static C0516a m1052a(TelephonyManager telephonyManager) {
        if (telephonyManager == null) {
            return null;
        }
        C0516a aVar = new C0516a();
        try {
            aVar.f590b = telephonyManager.getDeviceId();
            aVar.f591c = telephonyManager.getSubscriberId();
            aVar.f595g = telephonyManager.getNetworkOperatorName();
            aVar.f594f = telephonyManager.getSimOperatorName();
            aVar.f592d = telephonyManager.getPhoneType();
            aVar.f593e = telephonyManager.getSimSerialNumber();
            return aVar;
        } catch (Throwable th) {
            return aVar;
        }
    }

    /* renamed from: b */
    private static int m1053b(TelephonyManager telephonyManager) {
        try {
            return ((Integer) telephonyManager.getClass().getMethod("getSimCount", new Class[0]).invoke(telephonyManager, new Object[0])).intValue();
        } catch (Throwable th) {
            return -1;
        }
    }

    /* renamed from: a */
    public final ArrayList<C0516a> mo6695a(Context context) {
        TelephonyManager telephonyManager;
        ArrayList<C0516a> arrayList = new ArrayList<>();
        try {
            telephonyManager = (TelephonyManager) context.getSystemService(PlaceFields.PHONE);
            int b = m1053b(telephonyManager);
            if (b > 0) {
                Method declaredMethod = telephonyManager.getClass().getDeclaredMethod("getDeviceId", new Class[]{Integer.TYPE});
                declaredMethod.setAccessible(true);
                Method declaredMethod2 = telephonyManager.getClass().getDeclaredMethod("getImei", new Class[]{Integer.TYPE});
                declaredMethod2.setAccessible(true);
                Method declaredMethod3 = telephonyManager.getClass().getDeclaredMethod("getNetworkOperatorName", new Class[]{Integer.TYPE});
                declaredMethod3.setAccessible(true);
                Method declaredMethod4 = telephonyManager.getClass().getDeclaredMethod("getSimOperatorNameForPhone", new Class[]{Integer.TYPE});
                declaredMethod4.setAccessible(true);
                Method declaredMethod5 = telephonyManager.getClass().getDeclaredMethod("getSimSerialNumber", new Class[]{Integer.TYPE});
                declaredMethod5.setAccessible(true);
                Method declaredMethod6 = telephonyManager.getClass().getDeclaredMethod("getSubscriberId", new Class[]{Integer.TYPE});
                declaredMethod6.setAccessible(true);
                Method declaredMethod7 = telephonyManager.getClass().getDeclaredMethod("getCurrentPhoneType", new Class[]{Integer.TYPE});
                declaredMethod7.setAccessible(true);
                for (int i = 0; i < b; i++) {
                    int a = m1050a(i);
                    int a2 = m1051a(i, a);
                    C0516a aVar = new C0516a();
                    try {
                        aVar.f589a = (String) declaredMethod.invoke(telephonyManager, new Object[]{Integer.valueOf(i)});
                        aVar.f590b = (String) declaredMethod2.invoke(telephonyManager, new Object[]{Integer.valueOf(i)});
                        aVar.f593e = (String) declaredMethod5.invoke(telephonyManager, new Object[]{Integer.valueOf(a)});
                        aVar.f595g = (String) declaredMethod3.invoke(telephonyManager, new Object[]{Integer.valueOf(a)});
                        aVar.f594f = (String) declaredMethod4.invoke(telephonyManager, new Object[]{Integer.valueOf(a2)});
                        aVar.f592d = ((Integer) declaredMethod7.invoke(telephonyManager, new Object[]{Integer.valueOf(a)})).intValue();
                        aVar.f591c = (String) declaredMethod6.invoke(telephonyManager, new Object[]{Integer.valueOf(a)});
                    } catch (Throwable th) {
                    }
                    arrayList.add(aVar);
                }
            } else {
                arrayList.add(m1052a(telephonyManager));
            }
        } catch (Throwable th2) {
            arrayList.clear();
        }
        return arrayList;
    }

    /* renamed from: b */
    public final boolean mo6696b(Context context) {
        try {
            return TelephonyManager.class.getMethod("getSimCount", new Class[0]) != null;
        } catch (Throwable th) {
            return false;
        }
    }
}
