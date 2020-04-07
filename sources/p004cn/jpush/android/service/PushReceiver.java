package p004cn.jpush.android.service;

import android.content.BroadcastReceiver;

/* renamed from: cn.jpush.android.service.PushReceiver */
public class PushReceiver extends BroadcastReceiver {
    private static final String TAG = "PushReceiver";

    /* JADX WARNING: Unknown top exception splitter block from list: {B:32:0x00a1=Splitter:B:32:0x00a1, B:28:0x0082=Splitter:B:28:0x0082} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onReceive(android.content.Context r8, android.content.Intent r9) {
        /*
            r7 = this;
            r6 = 8
            r1 = 1
            r0 = 0
            if (r9 != 0) goto L_0x000e
            java.lang.String r0 = "PushReceiver"
            java.lang.String r1 = "Received null intent broadcast. Give up processing."
            p004cn.jiguang.p029e.C0501d.m907c(r0, r1)
        L_0x000d:
            return
        L_0x000e:
            java.lang.String r2 = r9.getAction()     // Catch:{ NullPointerException -> 0x0034 }
            java.lang.String r3 = "PushReceiver"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ NullPointerException -> 0x0034 }
            java.lang.String r5 = "onReceive - "
            r4.<init>(r5)     // Catch:{ NullPointerException -> 0x0034 }
            java.lang.StringBuilder r4 = r4.append(r2)     // Catch:{ NullPointerException -> 0x0034 }
            java.lang.String r4 = r4.toString()     // Catch:{ NullPointerException -> 0x0034 }
            p004cn.jiguang.p029e.C0501d.m903a(r3, r4)     // Catch:{ NullPointerException -> 0x0034 }
            boolean r3 = android.text.TextUtils.isEmpty(r2)
            if (r3 == 0) goto L_0x003d
            java.lang.String r0 = "PushReceiver"
            java.lang.String r1 = "Received action is null"
            p004cn.jiguang.p029e.C0501d.m907c(r0, r1)
            goto L_0x000d
        L_0x0034:
            r0 = move-exception
            java.lang.String r0 = "PushReceiver"
            java.lang.String r1 = "Received no action intent broadcast. Give up processing."
            p004cn.jiguang.p029e.C0501d.m907c(r0, r1)
            goto L_0x000d
        L_0x003d:
            java.lang.String r3 = "cn.jpush.android.intent.NOTIFICATION_OPENED_PROXY"
            boolean r3 = r2.equals(r3)     // Catch:{ Exception -> 0x0086 }
            if (r3 == 0) goto L_0x00a1
            java.lang.String r3 = "debug_notification"
            r4 = 0
            boolean r3 = r9.getBooleanExtra(r3, r4)     // Catch:{ Exception -> 0x0086 }
            if (r3 == 0) goto L_0x00a1
            java.lang.String r3 = "toastText"
            java.lang.String r3 = r9.getStringExtra(r3)     // Catch:{ Exception -> 0x0086 }
            boolean r4 = android.text.TextUtils.isEmpty(r3)     // Catch:{ Exception -> 0x0086 }
            if (r4 != 0) goto L_0x00a1
            r0 = 0
            android.widget.Toast r1 = android.widget.Toast.makeText(r8, r3, r0)     // Catch:{ Exception -> 0x0086 }
            android.view.View r0 = r1.getView()     // Catch:{ Exception -> 0x0274 }
            boolean r2 = r0 instanceof android.widget.LinearLayout     // Catch:{ Exception -> 0x0274 }
            if (r2 == 0) goto L_0x0082
            android.widget.LinearLayout r0 = (android.widget.LinearLayout) r0     // Catch:{ Exception -> 0x0274 }
            r2 = 0
            android.view.View r0 = r0.getChildAt(r2)     // Catch:{ Exception -> 0x0274 }
            boolean r2 = r0 instanceof android.widget.TextView     // Catch:{ Exception -> 0x0274 }
            if (r2 == 0) goto L_0x0082
            android.widget.TextView r0 = (android.widget.TextView) r0     // Catch:{ Exception -> 0x0274 }
            boolean r2 = p004cn.jiguang.p031g.C0530k.m1099a(r3)     // Catch:{ Exception -> 0x0274 }
            if (r2 != 0) goto L_0x007d
            r0.setText(r3)     // Catch:{ Exception -> 0x0274 }
        L_0x007d:
            r2 = 1095761920(0x41500000, float:13.0)
            r0.setTextSize(r2)     // Catch:{ Exception -> 0x0274 }
        L_0x0082:
            r1.show()     // Catch:{ Exception -> 0x0086 }
            goto L_0x000d
        L_0x0086:
            r0 = move-exception
            java.lang.String r1 = "PushReceiver"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "onReceiver error:"
            r2.<init>(r3)
            java.lang.String r0 = r0.getMessage()
            java.lang.StringBuilder r0 = r2.append(r0)
            java.lang.String r0 = r0.toString()
            p004cn.jiguang.p029e.C0501d.m907c(r1, r0)
            goto L_0x000d
        L_0x00a1:
            android.content.Context r3 = r8.getApplicationContext()     // Catch:{ Exception -> 0x0086 }
            r4 = 0
            boolean r3 = p004cn.jiguang.api.JCoreInterface.init(r3, r4)     // Catch:{ Exception -> 0x0086 }
            if (r3 != 0) goto L_0x00b5
            java.lang.String r0 = "PushReceiver"
            java.lang.String r1 = "onReceive :JCoreInterface init failed"
            p004cn.jiguang.p029e.C0501d.m907c(r0, r1)     // Catch:{ Exception -> 0x0086 }
            goto L_0x000d
        L_0x00b5:
            java.lang.String r3 = "android.intent.action.BOOT_COMPLETED"
            boolean r3 = r2.equals(r3)     // Catch:{ Exception -> 0x0086 }
            if (r3 != 0) goto L_0x00c5
            java.lang.String r3 = "android.intent.action.USER_PRESENT"
            boolean r3 = r2.equals(r3)     // Catch:{ Exception -> 0x0086 }
            if (r3 == 0) goto L_0x00f7
        L_0x00c5:
            r1 = 500(0x1f4, float:7.0E-43)
            java.lang.String r3 = "android.intent.action.USER_PRESENT"
            boolean r2 = r2.equals(r3)     // Catch:{ Exception -> 0x0086 }
            if (r2 == 0) goto L_0x0279
        L_0x00cf:
            android.os.Bundle r1 = new android.os.Bundle     // Catch:{ Exception -> 0x0086 }
            r1.<init>()     // Catch:{ Exception -> 0x0086 }
            java.lang.String r2 = "rtc_delay"
            r1.putInt(r2, r0)     // Catch:{ Exception -> 0x0086 }
            java.lang.String r0 = "rtc"
            java.lang.String r2 = "rtc"
            r1.putString(r0, r2)     // Catch:{ Exception -> 0x0086 }
            cn.jiguang.d.d.j r0 = p004cn.jiguang.p015d.p021d.C0453j.m665a()     // Catch:{ Exception -> 0x0086 }
            java.lang.String r2 = "intent.RTC"
            r0.mo6568b(r8, r2, r1)     // Catch:{ Exception -> 0x0086 }
        L_0x00e9:
            p004cn.jiguang.p015d.p021d.C0445b.m618a()     // Catch:{ Exception -> 0x0086 }
            java.lang.String r0 = "sdktype"
            java.lang.String r0 = r9.getStringExtra(r0)     // Catch:{ Exception -> 0x0086 }
            p004cn.jiguang.p015d.p021d.C0445b.m623a(r8, r0, r9)     // Catch:{ Exception -> 0x0086 }
            goto L_0x000d
        L_0x00f7:
            java.lang.String r0 = "android.intent.action.PACKAGE_ADDED"
            boolean r0 = r2.equals(r0)     // Catch:{ Exception -> 0x0086 }
            if (r0 != 0) goto L_0x0107
            java.lang.String r0 = "android.intent.action.PACKAGE_REMOVED"
            boolean r0 = r2.equals(r0)     // Catch:{ Exception -> 0x0086 }
            if (r0 == 0) goto L_0x0182
        L_0x0107:
            java.lang.String r0 = r9.getDataString()     // Catch:{ Exception -> 0x0086 }
            if (r0 == 0) goto L_0x0156
            java.lang.String r1 = r0.trim()     // Catch:{ Exception -> 0x0086 }
            int r1 = r1.length()     // Catch:{ Exception -> 0x0086 }
            if (r1 <= r6) goto L_0x0140
            java.lang.String r1 = "package:"
            boolean r1 = r0.startsWith(r1)     // Catch:{ Exception -> 0x0086 }
            if (r1 == 0) goto L_0x0140
            r1 = 8
            java.lang.String r0 = r0.substring(r1)     // Catch:{ Exception -> 0x0086 }
            java.lang.String r1 = "android.intent.action.PACKAGE_ADDED"
            boolean r1 = r2.equals(r1)     // Catch:{ Exception -> 0x0086 }
            if (r1 == 0) goto L_0x0170
            android.content.pm.ApplicationInfo r1 = p004cn.jiguang.p031g.C0523d.m1067a(r8, r0)     // Catch:{ Exception -> 0x0086 }
            int r1 = p004cn.jiguang.p031g.C0523d.m1066a(r1)     // Catch:{ Exception -> 0x0086 }
            org.json.JSONObject r1 = p004cn.jiguang.p031g.C0528i.m1096a(r0, r1)     // Catch:{ Exception -> 0x0086 }
            p004cn.jiguang.p005a.p012c.C0377c.m183a(r8, r1)     // Catch:{ Exception -> 0x0086 }
        L_0x013c:
            p004cn.jiguang.p005a.p012c.C0377c.m182a(r8, r0)     // Catch:{ Exception -> 0x0086 }
            goto L_0x00e9
        L_0x0140:
            java.lang.String r1 = "PushReceiver"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0086 }
            java.lang.String r3 = "Get wrong data string from intent: "
            r2.<init>(r3)     // Catch:{ Exception -> 0x0086 }
            java.lang.StringBuilder r0 = r2.append(r0)     // Catch:{ Exception -> 0x0086 }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x0086 }
            p004cn.jiguang.p029e.C0501d.m907c(r1, r0)     // Catch:{ Exception -> 0x0086 }
            goto L_0x000d
        L_0x0156:
            java.lang.String r0 = "PushReceiver"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0086 }
            r1.<init>()     // Catch:{ Exception -> 0x0086 }
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ Exception -> 0x0086 }
            java.lang.String r2 = ": Get no data from intent."
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ Exception -> 0x0086 }
            java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x0086 }
            p004cn.jiguang.p029e.C0501d.m907c(r0, r1)     // Catch:{ Exception -> 0x0086 }
            goto L_0x000d
        L_0x0170:
            android.content.Context r1 = r8.getApplicationContext()     // Catch:{ Exception -> 0x0086 }
            boolean r1 = p004cn.jiguang.p031g.C0506a.m975i(r1, r0)     // Catch:{ Exception -> 0x0086 }
            if (r1 != 0) goto L_0x013c
            org.json.JSONObject r1 = p004cn.jiguang.p031g.C0528i.m1095a(r0)     // Catch:{ Exception -> 0x0086 }
            p004cn.jiguang.p005a.p012c.C0377c.m183a(r8, r1)     // Catch:{ Exception -> 0x0086 }
            goto L_0x013c
        L_0x0182:
            java.lang.String r0 = "android.net.conn.CONNECTIVITY_CHANGE"
            boolean r0 = r2.equalsIgnoreCase(r0)     // Catch:{ Exception -> 0x0086 }
            if (r0 == 0) goto L_0x01e1
            java.lang.String r0 = "networkInfo"
            android.os.Parcelable r0 = r9.getParcelableExtra(r0)     // Catch:{ Exception -> 0x0086 }
            android.net.NetworkInfo r0 = (android.net.NetworkInfo) r0     // Catch:{ Exception -> 0x0086 }
            if (r0 == 0) goto L_0x000d
            r1 = 2
            int r2 = r0.getType()     // Catch:{ Exception -> 0x0086 }
            if (r1 == r2) goto L_0x000d
            r1 = 3
            int r2 = r0.getType()     // Catch:{ Exception -> 0x0086 }
            if (r1 == r2) goto L_0x000d
            java.lang.String r1 = "noConnectivity"
            r2 = 0
            boolean r1 = r9.getBooleanExtra(r1, r2)     // Catch:{ Exception -> 0x0086 }
            android.os.Bundle r2 = r9.getExtras()     // Catch:{ Exception -> 0x0086 }
            if (r1 == 0) goto L_0x01c0
            java.lang.String r0 = "connection-state"
            r1 = 0
            r2.putBoolean(r0, r1)     // Catch:{ Exception -> 0x0086 }
        L_0x01b5:
            cn.jiguang.d.d.j r0 = p004cn.jiguang.p015d.p021d.C0453j.m665a()     // Catch:{ Exception -> 0x0086 }
            java.lang.String r1 = "intent.CONNECTIVITY_CHANGE"
            r0.mo6568b(r8, r1, r2)     // Catch:{ Exception -> 0x0086 }
            goto L_0x00e9
        L_0x01c0:
            android.net.NetworkInfo$State r1 = android.net.NetworkInfo.State.CONNECTED     // Catch:{ Exception -> 0x0086 }
            android.net.NetworkInfo$State r3 = r0.getState()     // Catch:{ Exception -> 0x0086 }
            if (r1 != r3) goto L_0x01d2
            java.lang.String r0 = "connection-state"
            r1 = 1
            r2.putBoolean(r0, r1)     // Catch:{ Exception -> 0x0086 }
            p004cn.jiguang.p015d.p021d.C0460q.m709a(r8)     // Catch:{ Exception -> 0x0086 }
            goto L_0x01b5
        L_0x01d2:
            android.net.NetworkInfo$State r1 = android.net.NetworkInfo.State.DISCONNECTED     // Catch:{ Exception -> 0x0086 }
            android.net.NetworkInfo$State r0 = r0.getState()     // Catch:{ Exception -> 0x0086 }
            if (r1 != r0) goto L_0x01b5
            java.lang.String r0 = "connection-state"
            r1 = 0
            r2.putBoolean(r0, r1)     // Catch:{ Exception -> 0x0086 }
            goto L_0x01b5
        L_0x01e1:
            java.lang.String r0 = "android.os.action.DEVICE_IDLE_MODE_CHANGED"
            boolean r0 = r2.equals(r0)     // Catch:{ Exception -> 0x0086 }
            if (r0 != 0) goto L_0x01f1
            java.lang.String r0 = "android.os.action.POWER_SAVE_MODE_CHANGED"
            boolean r0 = r2.equals(r0)     // Catch:{ Exception -> 0x0086 }
            if (r0 == 0) goto L_0x0267
        L_0x01f1:
            java.lang.String r0 = "power"
            java.lang.Object r0 = r8.getSystemService(r0)     // Catch:{ Exception -> 0x0086 }
            android.os.PowerManager r0 = (android.os.PowerManager) r0     // Catch:{ Exception -> 0x0086 }
            if (r0 == 0) goto L_0x00e9
            java.lang.String r3 = "android.os.PowerManager"
            java.lang.Class r3 = java.lang.Class.forName(r3)     // Catch:{ Throwable -> 0x0243 }
            if (r3 == 0) goto L_0x00e9
            java.lang.String r4 = "android.os.action.DEVICE_IDLE_MODE_CHANGED"
            boolean r4 = r2.equals(r4)     // Catch:{ Throwable -> 0x0243 }
            if (r4 == 0) goto L_0x0246
            java.lang.String r2 = "isDeviceIdleMode"
            r4 = 0
            java.lang.Class[] r4 = new java.lang.Class[r4]     // Catch:{ Throwable -> 0x0243 }
            java.lang.reflect.Method r2 = r3.getDeclaredMethod(r2, r4)     // Catch:{ Throwable -> 0x0243 }
            if (r2 == 0) goto L_0x0277
            r1 = 0
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ Throwable -> 0x0243 }
            java.lang.Object r0 = r2.invoke(r0, r1)     // Catch:{ Throwable -> 0x0243 }
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ Throwable -> 0x0243 }
            boolean r0 = r0.booleanValue()     // Catch:{ Throwable -> 0x0243 }
        L_0x0223:
            r1 = r0
        L_0x0224:
            if (r1 != 0) goto L_0x00e9
            android.os.Bundle r0 = new android.os.Bundle     // Catch:{ Throwable -> 0x0243 }
            r0.<init>()     // Catch:{ Throwable -> 0x0243 }
            java.lang.String r1 = "rtc_delay"
            r2 = 0
            r0.putInt(r1, r2)     // Catch:{ Throwable -> 0x0243 }
            java.lang.String r1 = "rtc"
            java.lang.String r2 = "rtc"
            r0.putString(r1, r2)     // Catch:{ Throwable -> 0x0243 }
            cn.jiguang.d.d.j r1 = p004cn.jiguang.p015d.p021d.C0453j.m665a()     // Catch:{ Throwable -> 0x0243 }
            java.lang.String r2 = "intent.RTC"
            r1.mo6568b(r8, r2, r0)     // Catch:{ Throwable -> 0x0243 }
            goto L_0x00e9
        L_0x0243:
            r0 = move-exception
            goto L_0x00e9
        L_0x0246:
            java.lang.String r4 = "android.os.action.POWER_SAVE_MODE_CHANGED"
            boolean r2 = r2.equals(r4)     // Catch:{ Throwable -> 0x0243 }
            if (r2 == 0) goto L_0x0224
            java.lang.String r2 = "isPowerSaveMode"
            r4 = 0
            java.lang.Class[] r4 = new java.lang.Class[r4]     // Catch:{ Throwable -> 0x0243 }
            java.lang.reflect.Method r2 = r3.getDeclaredMethod(r2, r4)     // Catch:{ Throwable -> 0x0243 }
            if (r2 == 0) goto L_0x0224
            r1 = 0
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ Throwable -> 0x0243 }
            java.lang.Object r0 = r2.invoke(r0, r1)     // Catch:{ Throwable -> 0x0243 }
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ Throwable -> 0x0243 }
            boolean r1 = r0.booleanValue()     // Catch:{ Throwable -> 0x0243 }
            goto L_0x0224
        L_0x0267:
            java.lang.String r0 = "cn.jpush.android.intent.ACTION_REPORT_HISTORY"
            boolean r0 = r2.equals(r0)     // Catch:{ Exception -> 0x0086 }
            if (r0 == 0) goto L_0x00e9
            p004cn.jiguang.p015d.p021d.C0460q.m709a(r8)     // Catch:{ Exception -> 0x0086 }
            goto L_0x00e9
        L_0x0274:
            r0 = move-exception
            goto L_0x0082
        L_0x0277:
            r0 = r1
            goto L_0x0223
        L_0x0279:
            r0 = r1
            goto L_0x00cf
        */
        throw new UnsupportedOperationException("Method not decompiled: p004cn.jpush.android.service.PushReceiver.onReceive(android.content.Context, android.content.Intent):void");
    }
}
