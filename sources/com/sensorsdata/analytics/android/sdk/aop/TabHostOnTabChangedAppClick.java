package com.sensorsdata.analytics.android.sdk.aop;

public class TabHostOnTabChangedAppClick {
    private static final String TAG = "TabHostOnTabChangedAppClick";

    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0087, code lost:
        r1.put(com.sensorsdata.analytics.android.sdk.AopConstants.SCREEN_NAME, r3[1]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x008f, code lost:
        r1.put(com.sensorsdata.analytics.android.sdk.AopConstants.ELEMENT_CONTENT, r3[0]);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void onAppClick(org.aspectj.lang.JoinPoint r7) {
        /*
            r6 = 1
            com.sensorsdata.analytics.android.sdk.SensorsDataAPI r4 = com.sensorsdata.analytics.android.sdk.SensorsDataAPI.sharedInstance()     // Catch:{ Exception -> 0x005e }
            boolean r4 = r4.isAutoTrackEnabled()     // Catch:{ Exception -> 0x005e }
            if (r4 != 0) goto L_0x000c
        L_0x000b:
            return
        L_0x000c:
            com.sensorsdata.analytics.android.sdk.SensorsDataAPI r4 = com.sensorsdata.analytics.android.sdk.SensorsDataAPI.sharedInstance()     // Catch:{ Exception -> 0x005e }
            com.sensorsdata.analytics.android.sdk.SensorsDataAPI$AutoTrackEventType r5 = com.sensorsdata.analytics.android.sdk.SensorsDataAPI.AutoTrackEventType.APP_CLICK     // Catch:{ Exception -> 0x005e }
            boolean r4 = r4.isAutoTrackEventTypeIgnored(r5)     // Catch:{ Exception -> 0x005e }
            if (r4 != 0) goto L_0x000b
            if (r7 == 0) goto L_0x000b
            java.lang.Object[] r4 = r7.getArgs()     // Catch:{ Exception -> 0x005e }
            if (r4 == 0) goto L_0x000b
            java.lang.Object[] r4 = r7.getArgs()     // Catch:{ Exception -> 0x005e }
            int r4 = r4.length     // Catch:{ Exception -> 0x005e }
            if (r4 != r6) goto L_0x000b
            java.lang.Class<android.widget.TabHost> r4 = android.widget.TabHost.class
            boolean r4 = com.sensorsdata.analytics.android.sdk.util.AopUtil.isViewIgnored(r4)     // Catch:{ Exception -> 0x005e }
            if (r4 != 0) goto L_0x000b
            java.lang.Object[] r4 = r7.getArgs()     // Catch:{ Exception -> 0x005e }
            r5 = 0
            r2 = r4[r5]     // Catch:{ Exception -> 0x005e }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ Exception -> 0x005e }
            org.json.JSONObject r1 = new org.json.JSONObject     // Catch:{ Exception -> 0x005e }
            r1.<init>()     // Catch:{ Exception -> 0x005e }
            boolean r4 = android.text.TextUtils.isEmpty(r2)     // Catch:{ Exception -> 0x0098 }
            if (r4 != 0) goto L_0x004d
            java.lang.String r4 = "##"
            java.lang.String[] r3 = r2.split(r4)     // Catch:{ Exception -> 0x0098 }
            int r4 = r3.length     // Catch:{ Exception -> 0x0098 }
            switch(r4) {
                case 1: goto L_0x008f;
                case 2: goto L_0x0087;
                case 3: goto L_0x007f;
                default: goto L_0x004d;
            }
        L_0x004d:
            java.lang.String r4 = "$element_type"
            java.lang.String r5 = "TabHost"
            r1.put(r4, r5)     // Catch:{ Exception -> 0x005e }
            com.sensorsdata.analytics.android.sdk.SensorsDataAPI r4 = com.sensorsdata.analytics.android.sdk.SensorsDataAPI.sharedInstance()     // Catch:{ Exception -> 0x005e }
            java.lang.String r5 = "$AppClick"
            r4.track(r5, r1)     // Catch:{ Exception -> 0x005e }
            goto L_0x000b
        L_0x005e:
            r0 = move-exception
            r0.printStackTrace()
            java.lang.String r4 = "TabHostOnTabChangedAppClick"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = " onTabChanged AOP ERROR: "
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.String r6 = r0.getMessage()
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.String r5 = r5.toString()
            com.sensorsdata.analytics.android.sdk.SALog.m1608i(r4, r5)
            goto L_0x000b
        L_0x007f:
            java.lang.String r4 = "$title"
            r5 = 2
            r5 = r3[r5]     // Catch:{ Exception -> 0x0098 }
            r1.put(r4, r5)     // Catch:{ Exception -> 0x0098 }
        L_0x0087:
            java.lang.String r4 = "$screen_name"
            r5 = 1
            r5 = r3[r5]     // Catch:{ Exception -> 0x0098 }
            r1.put(r4, r5)     // Catch:{ Exception -> 0x0098 }
        L_0x008f:
            java.lang.String r4 = "$element_content"
            r5 = 0
            r5 = r3[r5]     // Catch:{ Exception -> 0x0098 }
            r1.put(r4, r5)     // Catch:{ Exception -> 0x0098 }
            goto L_0x004d
        L_0x0098:
            r0 = move-exception
            java.lang.String r4 = "$element_content"
            r1.put(r4, r2)     // Catch:{ Exception -> 0x005e }
            r0.printStackTrace()     // Catch:{ Exception -> 0x005e }
            goto L_0x004d
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sensorsdata.analytics.android.sdk.aop.TabHostOnTabChangedAppClick.onAppClick(org.aspectj.lang.JoinPoint):void");
    }
}
