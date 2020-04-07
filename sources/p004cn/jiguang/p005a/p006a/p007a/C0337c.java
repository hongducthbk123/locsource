package p004cn.jiguang.p005a.p006a.p007a;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.text.TextUtils;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.commons.p052io.FilenameUtils;
import p004cn.jiguang.p015d.p016a.C0389d;
import p004cn.jiguang.p031g.C0506a;
import p004cn.jiguang.p031g.C0530k;

/* renamed from: cn.jiguang.a.a.a.c */
public final class C0337c {

    /* renamed from: a */
    private static AtomicInteger f34a = new AtomicInteger(2);

    /* renamed from: a */
    static /* synthetic */ String m22a(int i) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(i & 255);
        stringBuffer.append(FilenameUtils.EXTENSION_SEPARATOR);
        stringBuffer.append((i >> 8) & 255);
        stringBuffer.append(FilenameUtils.EXTENSION_SEPARATOR);
        stringBuffer.append((i >> 16) & 255);
        stringBuffer.append(FilenameUtils.EXTENSION_SEPARATOR);
        stringBuffer.append((i >> 24) & 255);
        return stringBuffer.toString();
    }

    /* renamed from: a */
    static /* synthetic */ String m23a(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(bArr[0] & 255);
        stringBuffer.append(FilenameUtils.EXTENSION_SEPARATOR);
        stringBuffer.append(bArr[1] & 255);
        stringBuffer.append(FilenameUtils.EXTENSION_SEPARATOR);
        stringBuffer.append(bArr[2] & 255);
        stringBuffer.append(FilenameUtils.EXTENSION_SEPARATOR);
        stringBuffer.append(bArr[3] & 255);
        return stringBuffer.toString();
    }

    /* renamed from: a */
    public static void m24a(Context context) {
        if (f34a.get() == 2) {
            m28c(1);
            if (context == null) {
                m28c(2);
            } else if (!C0506a.m959d(context).toUpperCase().startsWith("WIFI")) {
                m28c(2);
            } else if (!((Boolean) C0389d.m320b(context, "arpinfo_report_enable", Boolean.valueOf(false))).booleanValue()) {
                m28c(2);
            } else if (!C0389d.m334e(context)) {
                m28c(2);
            } else {
                WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService("wifi");
                if (wifiManager == null) {
                    m28c(2);
                } else if (C0506a.m944a(context, "android.permission.ACCESS_WIFI_STATE")) {
                    WifiInfo connectionInfo = wifiManager.getConnectionInfo();
                    String str = "";
                    String str2 = "";
                    if (connectionInfo != null) {
                        str2 = C0530k.m1102c(connectionInfo.getSSID());
                        str = connectionInfo.getBSSID();
                    }
                    String str3 = TextUtils.isEmpty(str) ? "" : str;
                    String str4 = TextUtils.isEmpty(str2) ? "" : str2;
                    String str5 = TextUtils.isEmpty(str3) ? str4 : str3;
                    boolean b = C0335a.m10a().mo6188b(context, str5);
                    C0335a.m10a();
                    C0335a.m9a(context);
                    if (!b) {
                        m28c(2);
                    } else {
                        new C0342h(wifiManager, str5, str3, str4, context, 300, 2, 0).start();
                    }
                } else {
                    m28c(2);
                }
            }
        }
    }

    /* renamed from: a */
    static /* synthetic */ byte[] m25a(long j) {
        return new byte[]{(byte) ((int) (255 & j)), (byte) ((int) ((j >> 8) & 255)), (byte) ((int) ((j >> 16) & 255)), (byte) ((int) ((j >> 24) & 255))};
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public static Process m26b(String str) {
        Process process = null;
        if (!TextUtils.isEmpty(str)) {
            try {
                process = Runtime.getRuntime().exec(str);
                try {
                    process.waitFor();
                } catch (InterruptedException e) {
                }
            } catch (IOException e2) {
            }
        }
        return process;
    }

    /* access modifiers changed from: private */
    /* renamed from: c */
    public static void m28c(int i) {
        f34a.getAndSet(i);
    }
}
