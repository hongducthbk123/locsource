package p004cn.jiguang.p031g;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.facebook.places.model.PlaceFields;
import java.lang.reflect.InvocationTargetException;
import p004cn.jiguang.p030f.C0503b;

/* renamed from: cn.jiguang.g.m */
public final class C0532m {
    /* renamed from: a */
    public static int m1107a(Context context) {
        String c = m1110c(context);
        if (!TextUtils.isEmpty(c)) {
            if ("wifi".equals(c)) {
                return 1;
            }
            if ("2g".equals(c)) {
                return 2;
            }
            if ("3g".equals(c)) {
                return 3;
            }
            if ("4g".equals(c)) {
                return 4;
            }
        }
        return 0;
    }

    /* renamed from: a */
    public static String m1108a(Context context, int i) {
        String c = m1110c(context);
        if (TextUtils.isEmpty(c)) {
            try {
                Object a = C0503b.m917a(TelephonyManager.class, "getNetworkClass", Integer.valueOf(i));
                if (((Integer) a).intValue() == 0) {
                    c = "unknown";
                } else if (((Integer) a).intValue() == 1) {
                    c = "2g";
                } else if (((Integer) a).intValue() == 2) {
                    c = "3g";
                } else {
                    c = ((Integer) a).intValue() == 3 ? "4g" : c;
                }
            } catch (Exception | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            }
        }
        return TextUtils.isEmpty(c) ? "unknown" : c;
    }

    /* renamed from: b */
    public static String m1109b(Context context) {
        try {
            return ((TelephonyManager) context.getSystemService(PlaceFields.PHONE)).getNetworkOperator();
        } catch (Exception e) {
            return "";
        }
    }

    /* renamed from: c */
    public static String m1110c(Context context) {
        String str = "";
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return "unknown";
            }
            if (activeNetworkInfo.getType() == 1) {
                return "wifi";
            }
            if (activeNetworkInfo.getType() == 0) {
                int subtype = activeNetworkInfo.getSubtype();
                switch (subtype) {
                    case 1:
                    case 2:
                    case 4:
                    case 7:
                    case 11:
                        return "2g";
                    case 3:
                    case 5:
                    case 6:
                    case 8:
                    case 9:
                    case 10:
                    case 12:
                    case 14:
                    case 15:
                        return "3g";
                    case 13:
                        return "4g";
                    default:
                        return subtype == 16 ? "2g" : subtype == 17 ? "3g" : subtype == 18 ? "4g" : "unknown";
                }
            }
            return str;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
