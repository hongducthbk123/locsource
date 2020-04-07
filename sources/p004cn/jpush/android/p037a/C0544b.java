package p004cn.jpush.android.p037a;

import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.text.TextUtils;
import com.facebook.share.internal.ShareConstants;
import java.nio.ByteBuffer;
import p004cn.jiguang.api.JAction;
import p004cn.jiguang.api.JResponse;
import p004cn.jpush.android.C0541a;
import p004cn.jpush.android.api.JPushInterface;
import p004cn.jpush.android.api.JPushInterface.C0558a;
import p004cn.jpush.android.data.C0588a;
import p004cn.jpush.android.p039c.C0571f;
import p004cn.jpush.android.p039c.C0573g;
import p004cn.jpush.android.p040d.C0582e;
import p004cn.jpush.android.service.C0605b;
import p004cn.jpush.android.service.C0606c;
import p004cn.jpush.android.service.ServiceInterface;
import p004cn.jpush.p036a.C0537a;
import p004cn.jpush.p036a.C0538b;
import p004cn.jpush.p036a.C0540d;

/* renamed from: cn.jpush.android.a.b */
public class C0544b implements JAction {
    public long dispatchMessage(Context context, long j, int i, Object obj, ByteBuffer byteBuffer) {
        JResponse aVar;
        if (!C0541a.m1120a(context)) {
            return -1;
        }
        switch (i) {
            case 3:
                aVar = new C0538b(obj, byteBuffer);
                break;
            case 10:
            case 28:
            case 29:
                aVar = new C0540d(obj, byteBuffer);
                break;
            case 26:
            case 27:
                aVar = new C0537a(obj, byteBuffer);
                break;
            default:
                aVar = null;
                break;
        }
        if (ServiceInterface.m1372c(context) && aVar != null && (aVar instanceof C0538b) && ((C0538b) aVar).mo6769a() != 20) {
            return -1;
        }
        if (aVar != null) {
            C0582e.m1302a("JPushDataAction", "response:" + aVar.toString());
            switch (aVar.getCommand()) {
                case 3:
                    return C0552h.m1140a(C0541a.f653e, j, aVar);
                case 26:
                    C0548f.m1129a().mo6778a(context, aVar.getRid().longValue(), aVar.code);
                    break;
                case 27:
                    if (aVar.code != 0) {
                        C0571f.m1232a().mo6842a(context, aVar.getRid().longValue(), aVar.code);
                        break;
                    } else {
                        C0571f.m1232a().mo6841a(context, aVar.getRid().longValue());
                        break;
                    }
                case 28:
                case 29:
                    return C0555k.m1147a(context, ((C0540d) aVar).mo6773a(), aVar.getCommand() == 28 ? 1 : 2, aVar.getRid().longValue());
            }
        }
        if (aVar != null) {
            return aVar.getRid().longValue();
        }
        return -1;
    }

    public void onActionRun(Context context, long j, Bundle bundle, Object obj) {
        C0582e.m1302a("JPushDataAction", "Action - onActionRun");
        if (C0541a.m1120a(context)) {
            C0606c.m1391a(context).mo6996a(bundle, (Handler) obj);
        }
    }

    public boolean isSupportedCMD(int i) {
        return i == 3 || i == 10 || i == 27 || i == 28 || i == 29 || i == 26;
    }

    public void onEvent(Context context, long j, int i) {
        if (C0541a.m1120a(context)) {
            switch (i) {
                case 1:
                    C0573g.m1238a().mo6851d(context);
                    return;
                case 19:
                    C0606c.m1391a(context).mo6995a();
                    return;
                default:
                    return;
            }
        }
    }

    public void handleMessage(Context context, long j, Object obj) {
        if (!C0541a.m1120a(context) || obj == null) {
            return;
        }
        if (obj instanceof Bundle) {
            C0606c.m1391a(context);
            Bundle bundle = (Bundle) obj;
            if (bundle != null) {
                bundle.getInt("what");
            }
        } else if (obj instanceof Intent) {
            C0605b.m1389a();
            Intent intent = (Intent) obj;
            if (intent == null) {
                C0582e.m1306c("PushReceiverCore", "Received null intent broadcast. Give up processing.");
                return;
            }
            try {
                String action = intent.getAction();
                C0582e.m1302a("PushReceiverCore", "onReceive - " + action);
                if (C0541a.m1120a(context.getApplicationContext()) && action != null) {
                    if ("cn.jpush.android.intent.plugin.platform.REFRESSH_REGID".equals(action)) {
                        C0573g.m1238a().mo6852e(context);
                    } else if (action.startsWith(JPushInterface.ACTION_NOTIFICATION_RECEIVED_PROXY)) {
                        try {
                            if (!ServiceInterface.m1373d(context)) {
                                intent.getIntExtra("notificaion_type", 0);
                                String stringExtra = intent.getStringExtra(ShareConstants.WEB_DIALOG_PARAM_MESSAGE);
                                if (TextUtils.isEmpty(stringExtra)) {
                                    C0582e.m1306c("PushReceiverCore", "Got an empty notification, don't show it!");
                                    return;
                                }
                                C0588a a = C0550g.m1134a(context, stringExtra, intent.getStringExtra("appId"), intent.getStringExtra("senderId"), intent.getStringExtra("msg_id"));
                                if (a != null) {
                                    a.f811i = true;
                                    C0550g.m1137a(context, a);
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else if (action.equals("android.intent.action.PACKAGE_ADDED")) {
                    } else {
                        if (action.equalsIgnoreCase("android.net.conn.CONNECTIVITY_CHANGE")) {
                            NetworkInfo networkInfo = (NetworkInfo) intent.getParcelableExtra("networkInfo");
                            if (networkInfo != null && 2 != networkInfo.getType() && 3 != networkInfo.getType() && !intent.getBooleanExtra("noConnectivity", false) && State.CONNECTED != networkInfo.getState()) {
                                State state = State.DISCONNECTED;
                                networkInfo.getState();
                            }
                        } else if (action.startsWith("cn.jpush.android.intent.NOTIFICATION_OPENED_PROXY")) {
                            C0605b.m1390a(context, intent);
                        } else if (action.startsWith("cn.jpush.android.intent.NOTIFICATION_CLICK_ACTION_PROXY")) {
                            try {
                                Intent intent2 = new Intent(JPushInterface.ACTION_NOTIFICATION_CLICK_ACTION);
                                intent2.putExtras(intent.getExtras());
                                intent2.addCategory(context.getPackageName());
                                intent2.setPackage(context.getPackageName());
                                context.sendBroadcast(intent2, context.getPackageName() + ".permission.JPUSH_MESSAGE");
                            } catch (Throwable th) {
                                C0582e.m1306c("PushReceiverCore", "Click notification sendBroadcast :" + th.getMessage());
                            }
                        }
                    }
                }
            } catch (NullPointerException e2) {
                C0582e.m1306c("PushReceiverCore", "Received no action intent broadcast. Give up processing.");
            }
        } else {
            C0582e.m1302a("JPushDataAction", "handleMessage unknown object ");
        }
    }

    public IBinder getBinderByType(String str) {
        return null;
    }

    public String getSdkVersion() {
        return "3.1.2";
    }

    public void dispatchTimeOutMessage(Context context, long j, long j2, int i) {
        if (C0541a.m1120a(context)) {
            switch (i) {
                case 10:
                case 28:
                case 29:
                    C0547e.m1126a(context).mo6776a(j2);
                    return;
                case 26:
                    C0548f.m1129a().mo6778a(context, j2, C0558a.f697c);
                    return;
                case 27:
                    C0571f.m1232a().mo6844b(context, j2);
                    return;
                default:
                    return;
            }
        }
    }
}
