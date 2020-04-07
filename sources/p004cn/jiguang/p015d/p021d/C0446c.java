package p004cn.jiguang.p015d.p021d;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.regex.Pattern;
import org.json.JSONException;
import org.json.JSONObject;
import p004cn.jiguang.api.utils.OutputDataUtil;
import p004cn.jiguang.p015d.C0385a;
import p004cn.jiguang.p015d.p016a.C0386a;
import p004cn.jiguang.p015d.p016a.C0389d;
import p004cn.jiguang.p015d.p017b.C0422i;
import p004cn.jiguang.p029e.C0501d;
import p004cn.jiguang.p031g.C0506a;
import p004cn.jiguang.p031g.C0528i;
import p004cn.jpush.android.api.JPushInterface;

/* renamed from: cn.jiguang.d.d.c */
public final class C0446c {
    /* renamed from: a */
    public static C0422i m632a(String str) {
        try {
            C0422i iVar = new C0422i();
            iVar.mo6471a(str);
            iVar.mo6473b(str);
            if (!iVar.mo6476e()) {
                return iVar;
            }
        } catch (Exception e) {
            C0501d.m904a("ConnectingHelper", "parseSisInfo crash:", e);
        }
        return null;
    }

    /* renamed from: a */
    public static void m633a(Context context, int i, boolean z) {
        if (z) {
            C0501d.m909d("ConnectingHelper", "Register Failed with server error - code:" + i);
            String a = C0528i.m1094a(i);
            if (!TextUtils.isEmpty(a)) {
                C0501d.m907c("ConnectingHelper", "Local error description: " + a);
            }
        }
        String i2 = C0389d.m338i(context);
        if (1006 == i) {
            C0506a.m940a(context, "包名: " + C0385a.f196c + " 不存在", -1);
            C0386a.m284k();
        } else if (1007 == i) {
        } else {
            if (1005 == i) {
                C0506a.m940a(context, "包名: " + C0385a.f196c + " 与 AppKey:" + i2 + "不匹配", -1);
                C0386a.m284k();
            } else if (1009 == i) {
                C0506a.m940a(context, " AppKey:" + i2 + " 非android AppKey", -1);
                C0386a.m284k();
            } else if (1008 == i) {
                C0506a.m940a(context, " AppKey:" + i2 + " 是无效的AppKey,请确认与JIGUANG web端的AppKey一致", -1);
                C0386a.m284k();
            } else if (10001 == i) {
                C0506a.m940a(context, " 未在manifest中配置AppKey", -1);
            } else if (1012 == i) {
                C0386a.m263c();
            }
        }
    }

    /* renamed from: a */
    public static void m634a(Context context, long j) {
        C0501d.m903a("ConnectingHelper", "Action - sendServerTimer");
        try {
            Bundle bundle = new Bundle();
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("push_login_server_time", j);
            jSONObject.put("push_login_local_time", System.currentTimeMillis());
            bundle.putString("push_to_im_data", jSONObject.toString());
            C0506a.m941a(context, "cn.jpush.im.android.action.IM_RESPONSE", bundle);
        } catch (JSONException e) {
        }
    }

    /* renamed from: a */
    public static void m635a(Context context, boolean z) {
        C0501d.m903a("ConnectingHelper", "Action - sendConnectionChanged");
        Bundle bundle = new Bundle();
        bundle.putBoolean(JPushInterface.EXTRA_CONNECTION_CHANGE, z);
        C0506a.m941a(context, JPushInterface.ACTION_CONNECTION_CHANGE, bundle);
    }

    /* renamed from: a */
    public static void m636a(Message message, long j) {
        Bundle bundle = new Bundle();
        bundle.putLong("connection", j);
        message.setData(bundle);
        message.sendToTarget();
    }

    /* renamed from: a */
    public static synchronized byte[] m637a(String str, int i, byte[] bArr, boolean z, int i2) {
        byte[] byteArray;
        int i3 = 0;
        synchronized (C0446c.class) {
            if (TextUtils.isEmpty(str) || str.length() != 2 || bArr == null || bArr.length == 0) {
                throw new IllegalArgumentException("flag or body length error");
            }
            OutputDataUtil outputDataUtil = new OutputDataUtil(300);
            outputDataUtil.writeU16(0);
            outputDataUtil.writeByteArray(str.getBytes());
            outputDataUtil.writeU32((long) i);
            outputDataUtil.writeU16(i2);
            outputDataUtil.writeByteArray(bArr);
            outputDataUtil.writeU16At(outputDataUtil.current(), 0);
            if (z) {
                i3 = 1;
            }
            outputDataUtil.writeU8At(i3, 4);
            byteArray = outputDataUtil.toByteArray();
        }
        return byteArray;
    }

    /* renamed from: a */
    public static byte[] m638a(DatagramSocket datagramSocket, DatagramPacket datagramPacket) {
        datagramSocket.setSoTimeout(6000);
        datagramSocket.send(datagramPacket);
        DatagramPacket datagramPacket2 = new DatagramPacket(new byte[1024], 1024);
        C0501d.m903a("ConnectingHelper", "SIS Receiving...");
        datagramSocket.receive(datagramPacket2);
        byte[] bArr = new byte[datagramPacket2.getLength()];
        System.arraycopy(datagramPacket2.getData(), 0, bArr, 0, bArr.length);
        return bArr;
    }

    /* renamed from: b */
    public static InetAddress m639b(String str) {
        InetAddress inetAddress = null;
        if (TextUtils.isEmpty(str)) {
            return inetAddress;
        }
        if (Pattern.compile("((2[0-4]\\d|25[0-5]|[01]?\\d{1,2})\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d{1,2})").matcher(str).matches()) {
            try {
                return InetAddress.getByName(str);
            } catch (UnknownHostException e) {
                return inetAddress;
            }
        } else {
            C0447d dVar = new C0447d(str);
            try {
                dVar.start();
                dVar.join(3000);
                return dVar.mo6554a();
            } catch (Exception | InterruptedException e2) {
                return inetAddress;
            }
        }
    }
}
