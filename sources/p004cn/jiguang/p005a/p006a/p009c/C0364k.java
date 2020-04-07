package p004cn.jiguang.p005a.p006a.p009c;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.ApplicationInfo.DisplayNameComparator;
import android.content.pm.PackageManager;
import android.os.SystemClock;
import com.facebook.internal.NativeProtocol;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p004cn.jiguang.p015d.p021d.C0460q;
import p004cn.jiguang.p029e.C0501d;

/* renamed from: cn.jiguang.a.a.c.k */
public class C0364k {

    /* renamed from: a */
    private static final String f139a = C0364k.class.getSimpleName();

    /* renamed from: a */
    public static String m140a(String str, int i) {
        if (str == null) {
            return str;
        }
        String replaceAll = Pattern.compile("\n|\r|\r\n|\n\r|\t").matcher(str).replaceAll("");
        try {
            byte[] bytes = replaceAll.getBytes();
            return bytes.length > 30 ? replaceAll.substring(0, new String(bytes, 0, 30, "UTF-8").length()) : replaceAll;
        } catch (UnsupportedEncodingException e) {
            return replaceAll;
        }
    }

    /* renamed from: a */
    private static Set<String> m141a(ActivityManager activityManager) {
        HashSet hashSet = new HashSet();
        for (RunningAppProcessInfo runningAppProcessInfo : activityManager.getRunningAppProcesses()) {
            Collections.addAll(hashSet, runningAppProcessInfo.pkgList);
        }
        return hashSet;
    }

    /* renamed from: a */
    private static JSONArray m142a(ActivityManager activityManager, PackageManager packageManager) {
        JSONArray jSONArray = new JSONArray();
        try {
            Set a = m141a(activityManager);
            List<ApplicationInfo> installedApplications = packageManager.getInstalledApplications(8192);
            List<RunningServiceInfo> runningServices = activityManager.getRunningServices(200);
            Collections.sort(installedApplications, new DisplayNameComparator(packageManager));
            long elapsedRealtime = SystemClock.elapsedRealtime();
            for (ApplicationInfo applicationInfo : installedApplications) {
                String a2 = m140a(applicationInfo.loadLabel(packageManager).toString(), 30);
                if (a.contains(applicationInfo.packageName)) {
                    JSONObject jSONObject = new JSONObject();
                    JSONArray jSONArray2 = new JSONArray();
                    for (RunningServiceInfo runningServiceInfo : runningServices) {
                        if (runningServiceInfo.service.getPackageName().equals(applicationInfo.packageName)) {
                            JSONObject jSONObject2 = new JSONObject();
                            long round = (long) Math.round((float) ((elapsedRealtime - runningServiceInfo.activeSince) / 1000));
                            jSONObject2.put("class_name", runningServiceInfo.service.getShortClassName());
                            jSONObject2.put("live_seconds", round);
                            jSONArray2.put(jSONObject2);
                        }
                    }
                    try {
                        jSONObject.put(NativeProtocol.BRIDGE_ARG_APP_NAME_STRING, a2);
                        jSONObject.put("pkg_name", applicationInfo.packageName);
                        jSONObject.put("service_list", jSONArray2);
                        jSONArray.put(jSONObject);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        } catch (Throwable th) {
            C0501d.m903a(f139a, "getRunningApps error:" + th.getMessage());
        }
        return jSONArray;
    }

    /* renamed from: a */
    public static void m143a(Context context) {
        JSONArray a = m142a((ActivityManager) context.getSystemService("activity"), context.getPackageManager());
        if (a != null && a.length() != 0) {
            C0460q.m711a(context, a);
        }
    }
}
