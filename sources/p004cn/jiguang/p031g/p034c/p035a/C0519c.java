package p004cn.jiguang.p031g.p034c.p035a;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.facebook.places.model.PlaceFields;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import p004cn.jiguang.p031g.p034c.C0516a;

/* renamed from: cn.jiguang.g.c.a.c */
public class C0519c extends C0517a {
    /* renamed from: a */
    private static ArrayList<Integer> m1056a(TelephonyManager telephonyManager) {
        Field[] declaredFields;
        ArrayList<Integer> arrayList = new ArrayList<>();
        try {
            int i = 0;
            for (Field field : TelephonyManager.class.getDeclaredFields()) {
                field.setAccessible(true);
                if (TextUtils.equals(field.getType().getName(), "com.android.internal.telephony.ITelephonyRegistry") && field.get(telephonyManager) != null) {
                    arrayList.add(Integer.valueOf(i));
                    i++;
                }
            }
        } catch (Throwable th) {
            arrayList.clear();
            arrayList.add(Integer.valueOf(0));
            arrayList.add(Integer.valueOf(1));
        }
        return arrayList;
    }

    /* renamed from: a */
    public final ArrayList<C0516a> mo6695a(Context context) {
        ArrayList arrayList = new ArrayList();
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(PlaceFields.PHONE);
            Method declaredMethod = TelephonyManager.class.getDeclaredMethod("getSubscriberIdGemini", new Class[]{Integer.TYPE});
            Method declaredMethod2 = TelephonyManager.class.getDeclaredMethod("getDeviceIdGemini", new Class[]{Integer.TYPE});
            Method declaredMethod3 = TelephonyManager.class.getDeclaredMethod("getPhoneTypeGemini", new Class[]{Integer.TYPE});
            Method declaredMethod4 = TelephonyManager.class.getDeclaredMethod("getNetworkOperatorNameGemini", new Class[]{Integer.TYPE});
            Method declaredMethod5 = TelephonyManager.class.getDeclaredMethod("getSimSerialNumberGemini", new Class[]{Integer.TYPE});
            Method declaredMethod6 = TelephonyManager.class.getDeclaredMethod("getSimOperatorNameGemini", new Class[]{Integer.TYPE});
            Method declaredMethod7 = TelephonyManager.class.getDeclaredMethod("getDeviceIdGemini", new Class[]{Integer.TYPE});
            TelephonyManager.class.getDeclaredMethod("getLine1NumberGemini", new Class[]{Integer.TYPE});
            ArrayList a = m1056a(telephonyManager);
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= a.size()) {
                    return arrayList;
                }
                C0516a aVar = new C0516a();
                try {
                    int intValue = ((Integer) a.get(i2)).intValue();
                    aVar.f591c = (String) declaredMethod.invoke(telephonyManager, new Object[]{Integer.valueOf(intValue)});
                    aVar.f590b = (String) declaredMethod2.invoke(telephonyManager, new Object[]{Integer.valueOf(intValue)});
                    aVar.f592d = ((Integer) declaredMethod3.invoke(telephonyManager, new Object[]{Integer.valueOf(intValue)})).intValue();
                    aVar.f595g = (String) declaredMethod4.invoke(telephonyManager, new Object[]{Integer.valueOf(intValue)});
                    aVar.f594f = (String) declaredMethod6.invoke(telephonyManager, new Object[]{Integer.valueOf(intValue)});
                    aVar.f593e = (String) declaredMethod5.invoke(telephonyManager, new Object[]{Integer.valueOf(intValue)});
                    aVar.f589a = (String) declaredMethod7.invoke(telephonyManager, new Object[]{Integer.valueOf(intValue)});
                } catch (Throwable th) {
                }
                arrayList.add(aVar);
                i = i2 + 1;
            }
        } catch (Throwable th2) {
            return null;
        }
    }

    /* renamed from: b */
    public final boolean mo6696b(Context context) {
        try {
            m1056a((TelephonyManager) context.getSystemService(PlaceFields.PHONE));
            Method declaredMethod = TelephonyManager.class.getDeclaredMethod("getSubscriberIdGemini", new Class[]{Integer.TYPE});
            Method declaredMethod2 = TelephonyManager.class.getDeclaredMethod("getDeviceIdGemini", new Class[]{Integer.TYPE});
            Method declaredMethod3 = TelephonyManager.class.getDeclaredMethod("getPhoneTypeGemini", new Class[]{Integer.TYPE});
            Field declaredField = TelephonyManager.class.getDeclaredField("mtkGeminiSupport");
            if (!(declaredMethod == null || declaredMethod2 == null || declaredMethod3 == null || declaredField == null)) {
                declaredField.setAccessible(true);
                if (((Boolean) declaredField.get(null)).booleanValue()) {
                    return true;
                }
            }
            return false;
        } catch (Throwable th) {
            return false;
        }
    }
}
