package p004cn.jiguang.net;

import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;
import javax.net.ssl.HostnameVerifier;

/* renamed from: cn.jiguang.net.HttpRequest */
public class HttpRequest {

    /* renamed from: a */
    private String f614a;

    /* renamed from: b */
    private int f615b;

    /* renamed from: c */
    private int f616c;

    /* renamed from: d */
    private Map<String, String> f617d;

    /* renamed from: e */
    private Map<String, String> f618e;

    /* renamed from: f */
    private Object f619f;

    /* renamed from: g */
    private boolean f620g;

    /* renamed from: h */
    private boolean f621h;

    /* renamed from: i */
    private boolean f622i;

    /* renamed from: j */
    private boolean f623j = true;

    /* renamed from: k */
    private boolean f624k = false;

    /* renamed from: l */
    private SSLTrustManager f625l;

    /* renamed from: m */
    private boolean f626m;

    /* renamed from: n */
    private HostnameVerifier f627n;

    public HttpRequest(String str) {
        this.f614a = str;
        this.f615b = -1;
        this.f616c = -1;
        this.f618e = new HashMap();
    }

    public HttpRequest(String str, Map<String, String> map) {
        this.f614a = str;
        this.f617d = map;
        this.f615b = -1;
        this.f616c = -1;
        this.f618e = new HashMap();
    }

    public Object getBody() {
        return this.f619f;
    }

    public int getConnectTimeout() {
        return this.f615b;
    }

    public HostnameVerifier getHostnameVerifier() {
        return this.f627n;
    }

    public byte[] getParas() {
        if (this.f619f != null) {
            if (this.f619f instanceof String) {
                if (!TextUtils.isEmpty((CharSequence) this.f619f)) {
                    return ((String) this.f619f).getBytes();
                }
            } else if (this.f619f instanceof byte[]) {
                return (byte[]) this.f619f;
            }
        }
        String joinParasWithEncodedValue = HttpUtils.joinParasWithEncodedValue(this.f617d);
        if (!TextUtils.isEmpty(joinParasWithEncodedValue)) {
            return joinParasWithEncodedValue.getBytes();
        }
        return null;
    }

    public Map<String, String> getParasMap() {
        return this.f617d;
    }

    public int getReadTimeout() {
        return this.f616c;
    }

    public Map<String, String> getRequestProperties() {
        return this.f618e;
    }

    public String getRequestProperty(String str) {
        return (String) this.f618e.get(str);
    }

    public SSLTrustManager getSslTrustManager() {
        return this.f625l;
    }

    public String getUrl() {
        return this.f614a;
    }

    public boolean isDoInPut() {
        return this.f621h;
    }

    public boolean isDoOutPut() {
        return this.f620g;
    }

    public boolean isHaveRspData() {
        return this.f623j;
    }

    public boolean isNeedErrorInput() {
        return this.f624k;
    }

    public boolean isNeedRetryIfHttpsFailed() {
        return this.f626m;
    }

    public boolean isUseCaches() {
        return this.f622i;
    }

    public void setBody(Object obj) {
        this.f619f = obj;
    }

    public void setConnectTimeout(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("timeout can not be negative");
        }
        this.f615b = i;
    }

    public void setDoInPut(boolean z) {
        this.f621h = z;
    }

    public void setDoOutPut(boolean z) {
        this.f620g = z;
    }

    public void setHaveRspData(boolean z) {
        this.f623j = z;
    }

    public void setHostnameVerifier(HostnameVerifier hostnameVerifier) {
        this.f627n = hostnameVerifier;
    }

    public void setNeedErrorInput(boolean z) {
        this.f624k = z;
    }

    public void setNeedRetryIfHttpsFailed(boolean z) {
        this.f626m = z;
    }

    public void setParasMap(Map<String, String> map) {
        this.f617d = map;
    }

    public void setReadTimeout(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("timeout can not be negative");
        }
        this.f616c = i;
    }

    public void setRequestProperties(Map<String, String> map) {
        this.f618e = map;
    }

    public void setRequestProperty(String str, String str2) {
        this.f618e.put(str, str2);
    }

    public void setSslTrustManager(SSLTrustManager sSLTrustManager) {
        this.f625l = sSLTrustManager;
    }

    public void setUrl(String str) {
        this.f614a = str;
    }

    public void setUseCaches(boolean z) {
        this.f622i = z;
    }

    public void setUserAgent(String str) {
        this.f618e.put("User-Agent", str);
    }
}
