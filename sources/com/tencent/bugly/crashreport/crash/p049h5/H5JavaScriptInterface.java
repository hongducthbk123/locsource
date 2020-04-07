package com.tencent.bugly.crashreport.crash.p049h5;

import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import com.facebook.places.model.PlaceFields;
import com.tencent.bugly.crashreport.inner.InnerApi;
import com.tencent.bugly.proguard.C2014w;
import com.tencent.bugly.proguard.C2018y;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.commons.p052io.IOUtils;
import org.json.JSONObject;

/* renamed from: com.tencent.bugly.crashreport.crash.h5.H5JavaScriptInterface */
/* compiled from: BUGLY */
public class H5JavaScriptInterface {

    /* renamed from: a */
    private static HashSet<Integer> f1407a = new HashSet<>();

    /* renamed from: b */
    private String f1408b = null;

    /* renamed from: c */
    private Thread f1409c = null;

    /* renamed from: d */
    private String f1410d = null;

    /* renamed from: e */
    private Map<String, String> f1411e = null;

    private H5JavaScriptInterface() {
    }

    public static H5JavaScriptInterface getInstance(WebView webView) {
        String str = null;
        if (webView == null || f1407a.contains(Integer.valueOf(webView.hashCode()))) {
            return null;
        }
        H5JavaScriptInterface h5JavaScriptInterface = new H5JavaScriptInterface();
        f1407a.add(Integer.valueOf(webView.hashCode()));
        h5JavaScriptInterface.f1409c = Thread.currentThread();
        Thread thread = h5JavaScriptInterface.f1409c;
        if (thread != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(IOUtils.LINE_SEPARATOR_UNIX);
            for (int i = 2; i < thread.getStackTrace().length; i++) {
                StackTraceElement stackTraceElement = thread.getStackTrace()[i];
                if (!stackTraceElement.toString().contains("crashreport")) {
                    sb.append(stackTraceElement.toString()).append(IOUtils.LINE_SEPARATOR_UNIX);
                }
            }
            str = sb.toString();
        }
        h5JavaScriptInterface.f1410d = str;
        HashMap hashMap = new HashMap();
        hashMap.put("[WebView] ContentDescription", webView.getContentDescription());
        h5JavaScriptInterface.f1411e = hashMap;
        return h5JavaScriptInterface;
    }

    /* renamed from: a */
    private static C1962a m1842a(String str) {
        if (str == null || str.length() <= 0) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            C1962a aVar = new C1962a();
            aVar.f1412a = jSONObject.getString("projectRoot");
            if (aVar.f1412a == null) {
                return null;
            }
            aVar.f1413b = jSONObject.getString(PlaceFields.CONTEXT);
            if (aVar.f1413b == null) {
                return null;
            }
            aVar.f1414c = jSONObject.getString("url");
            if (aVar.f1414c == null) {
                return null;
            }
            aVar.f1415d = jSONObject.getString("userAgent");
            if (aVar.f1415d == null) {
                return null;
            }
            aVar.f1416e = jSONObject.getString("language");
            if (aVar.f1416e == null) {
                return null;
            }
            aVar.f1417f = jSONObject.getString("name");
            if (aVar.f1417f == null || aVar.f1417f.equals("null")) {
                return null;
            }
            String string = jSONObject.getString("stacktrace");
            if (string == null) {
                return null;
            }
            int indexOf = string.indexOf(IOUtils.LINE_SEPARATOR_UNIX);
            if (indexOf < 0) {
                C2014w.m2118d("H5 crash stack's format is wrong!", new Object[0]);
                return null;
            }
            aVar.f1419h = string.substring(indexOf + 1);
            aVar.f1418g = string.substring(0, indexOf);
            int indexOf2 = aVar.f1418g.indexOf(":");
            if (indexOf2 > 0) {
                aVar.f1418g = aVar.f1418g.substring(indexOf2 + 1);
            }
            aVar.f1420i = jSONObject.getString("file");
            if (aVar.f1417f == null) {
                return null;
            }
            aVar.f1421j = jSONObject.getLong("lineNumber");
            if (aVar.f1421j < 0) {
                return null;
            }
            aVar.f1422k = jSONObject.getLong("columnNumber");
            if (aVar.f1422k < 0) {
                return null;
            }
            C2014w.m2113a("H5 crash information is following: ", new Object[0]);
            C2014w.m2113a("[projectRoot]: " + aVar.f1412a, new Object[0]);
            C2014w.m2113a("[context]: " + aVar.f1413b, new Object[0]);
            C2014w.m2113a("[url]: " + aVar.f1414c, new Object[0]);
            C2014w.m2113a("[userAgent]: " + aVar.f1415d, new Object[0]);
            C2014w.m2113a("[language]: " + aVar.f1416e, new Object[0]);
            C2014w.m2113a("[name]: " + aVar.f1417f, new Object[0]);
            C2014w.m2113a("[message]: " + aVar.f1418g, new Object[0]);
            C2014w.m2113a("[stacktrace]: \n" + aVar.f1419h, new Object[0]);
            C2014w.m2113a("[file]: " + aVar.f1420i, new Object[0]);
            C2014w.m2113a("[lineNumber]: " + aVar.f1421j, new Object[0]);
            C2014w.m2113a("[columnNumber]: " + aVar.f1422k, new Object[0]);
            return aVar;
        } catch (Throwable th) {
            if (C2014w.m2114a(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }

    @JavascriptInterface
    public void printLog(String str) {
        C2014w.m2118d("Log from js: %s", str);
    }

    @JavascriptInterface
    public void reportJSException(String str) {
        if (str == null) {
            C2014w.m2118d("Payload from JS is null.", new Object[0]);
            return;
        }
        String b = C2018y.m2168b(str.getBytes());
        if (this.f1408b == null || !this.f1408b.equals(b)) {
            this.f1408b = b;
            C2014w.m2118d("Handling JS exception ...", new Object[0]);
            C1962a a = m1842a(str);
            if (a == null) {
                C2014w.m2118d("Failed to parse payload.", new Object[0]);
                return;
            }
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            LinkedHashMap linkedHashMap2 = new LinkedHashMap();
            if (a.f1412a != null) {
                linkedHashMap2.put("[JS] projectRoot", a.f1412a);
            }
            if (a.f1413b != null) {
                linkedHashMap2.put("[JS] context", a.f1413b);
            }
            if (a.f1414c != null) {
                linkedHashMap2.put("[JS] url", a.f1414c);
            }
            if (a.f1415d != null) {
                linkedHashMap2.put("[JS] userAgent", a.f1415d);
            }
            if (a.f1420i != null) {
                linkedHashMap2.put("[JS] file", a.f1420i);
            }
            if (a.f1421j != 0) {
                linkedHashMap2.put("[JS] lineNumber", Long.toString(a.f1421j));
            }
            linkedHashMap.putAll(linkedHashMap2);
            linkedHashMap.putAll(this.f1411e);
            linkedHashMap.put("Java Stack", this.f1410d);
            Thread thread = this.f1409c;
            if (a != null) {
                InnerApi.postH5CrashAsync(thread, a.f1417f, a.f1418g, a.f1419h, linkedHashMap);
                return;
            }
            return;
        }
        C2014w.m2118d("Same payload from js. Please check whether you've injected bugly.js more than one times.", new Object[0]);
    }
}
