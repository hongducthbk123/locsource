package p004cn.jpush.android.p037a;

import android.content.Context;
import android.text.TextUtils;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.StringReader;
import p004cn.jiguang.api.JCoreInterface;
import p004cn.jiguang.api.JResponse;
import p004cn.jiguang.api.utils.OutputDataUtil;
import p004cn.jpush.android.C0541a;
import p004cn.jpush.android.data.C0588a;
import p004cn.jpush.android.data.C0589b;
import p004cn.jpush.android.data.C0590c;
import p004cn.jpush.android.p040d.C0577a;
import p004cn.jpush.android.p040d.C0583f;
import p004cn.jpush.android.p040d.C0587j;
import p004cn.jpush.android.service.ServiceInterface;
import p004cn.jpush.p036a.C0538b;

/* renamed from: cn.jpush.android.a.h */
public final class C0552h {
    /* renamed from: a */
    public static long m1140a(Context context, long j, JResponse jResponse) {
        String str;
        C0538b bVar = (C0538b) jResponse;
        int a = bVar.mo6769a();
        long b = bVar.mo6770b();
        long longValue = bVar.getRid().longValue();
        long uid = JCoreInterface.getUid();
        byte b2 = (byte) a;
        int sid = JCoreInterface.getSid();
        OutputDataUtil outputDataUtil = new OutputDataUtil(20480);
        outputDataUtil.writeU16(0);
        outputDataUtil.writeU8(1);
        outputDataUtil.writeU8(4);
        outputDataUtil.writeU64(longValue);
        outputDataUtil.writeU32((long) sid);
        outputDataUtil.writeU64(uid);
        outputDataUtil.writeU16(0);
        outputDataUtil.writeU8(b2);
        outputDataUtil.writeU64(b);
        outputDataUtil.writeU16At(outputDataUtil.current(), 0);
        JCoreInterface.sendData(C0541a.f653e, C0541a.f649a, 4, outputDataUtil.toByteArray());
        long b3 = bVar.mo6770b();
        int a2 = bVar.mo6769a();
        String c = bVar.mo6771c();
        LineNumberReader lineNumberReader = new LineNumberReader(new StringReader(c));
        try {
            String readLine = lineNumberReader.readLine();
            if (readLine == null) {
                return -1;
            }
            String readLine2 = lineNumberReader.readLine();
            if (readLine2 == null) {
                return -1;
            }
            int length = readLine.length() + readLine2.length() + 2;
            if (c.length() > length + 1) {
                str = c.substring(length);
            } else {
                str = "";
            }
            switch (a2) {
                case 0:
                case 2:
                    try {
                        C0587j jVar = new C0587j("PushMessageProcessor", "Time to process received msg.");
                        if (!TextUtils.isEmpty(readLine) && !TextUtils.isEmpty(readLine2) && !TextUtils.isEmpty(str)) {
                            m1141a(context, readLine, readLine2, str, b3, 0);
                        }
                        jVar.mo6856a();
                        break;
                    } catch (Exception e) {
                        break;
                    }
                case 20:
                    return C0555k.m1147a(context, str, 0, -1);
                default:
                    JCoreInterface.processCtrlReport(a2);
                    break;
            }
            return jResponse.getRid().longValue();
        } catch (IOException e2) {
            return -1;
        }
    }

    /* renamed from: a */
    public static void m1141a(Context context, String str, String str2, String str3, long j, byte b) {
        C0588a a = C0550g.m1134a(context, str3, str, str2, j);
        if (a != null && !C0583f.m1311a(context, new C0590c(a))) {
            a.f807e = b;
            char c = 0;
            if (str2.equalsIgnoreCase("7fef6a7d76c782b1f0eda446b2c6c40a")) {
                C0550g.m1137a(context, a);
            } else {
                c = a.f808f ? a.f804b == 4 ? (char) 3 : 1 : 2;
            }
            new StringBuilder().append(j);
            if ((c & 1) != 0) {
                if (!ServiceInterface.m1373d(context) && C0577a.m1284c(context)) {
                    a.f811i = true;
                    C0550g.m1137a(context, a);
                } else {
                    return;
                }
            }
            if ((c & 2) == 0) {
                return;
            }
            if (!TextUtils.isEmpty(a.f812j) || !TextUtils.isEmpty(a.f816n)) {
                C0577a.m1273a(context, (C0589b) a);
            }
        }
    }
}
