package p004cn.jiguang.net;

import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;

/* renamed from: cn.jiguang.net.HttpResponse */
public class HttpResponse {

    /* renamed from: a */
    private String f628a;

    /* renamed from: b */
    private String f629b;

    /* renamed from: c */
    private Map<String, Object> f630c;

    /* renamed from: d */
    private int f631d;

    /* renamed from: e */
    private long f632e;

    /* renamed from: f */
    private boolean f633f;

    /* renamed from: g */
    private boolean f634g;

    /* renamed from: h */
    private int f635h;

    public HttpResponse() {
        this.f635h = -1;
        this.f630c = new HashMap();
    }

    public HttpResponse(String str) {
        this.f635h = -1;
        this.f628a = str;
        this.f631d = 0;
        this.f633f = false;
        this.f634g = false;
        this.f630c = new HashMap();
    }

    /* renamed from: a */
    private int m1112a() {
        try {
            String str = (String) this.f630c.get(HttpConstants.CACHE_CONTROL);
            if (!TextUtils.isEmpty(str)) {
                int indexOf = str.indexOf("max-age=");
                if (indexOf != -1) {
                    int indexOf2 = str.indexOf(",", indexOf);
                    return Integer.parseInt(indexOf2 != -1 ? str.substring(indexOf + 8, indexOf2) : str.substring(indexOf + 8));
                }
            }
            return -1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public long getExpiredTime() {
        if (this.f634g) {
            return this.f632e;
        }
        this.f634g = true;
        int a = m1112a();
        long j = a != -1 ? ((long) (a * 1000)) + System.currentTimeMillis() : !TextUtils.isEmpty(getExpiresHeader()) ? HttpUtils.parseGmtTime(getExpiresHeader()) : -1;
        this.f632e = j;
        return j;
    }

    public String getExpiresHeader() {
        try {
            if (this.f630c == null) {
                return null;
            }
            return (String) this.f630c.get("expires");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getResponseBody() {
        return this.f629b;
    }

    public int getResponseCode() {
        return this.f635h;
    }

    public int getType() {
        return this.f631d;
    }

    public String getUrl() {
        return this.f628a;
    }

    public boolean isExpired() {
        return System.currentTimeMillis() > this.f632e;
    }

    public boolean isInCache() {
        return this.f633f;
    }

    public void setExpiredTime(long j) {
        this.f634g = true;
        this.f632e = j;
    }

    public HttpResponse setInCache(boolean z) {
        this.f633f = z;
        return this;
    }

    public void setResponseBody(String str) {
        this.f629b = str;
    }

    public void setResponseCode(int i) {
        this.f635h = i;
    }

    public void setResponseHeader(String str, String str2) {
        if (this.f630c != null) {
            this.f630c.put(str, str2);
        }
    }

    public void setResponseHeaders(Map<String, Object> map) {
        this.f630c = map;
    }

    public void setType(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("The type of HttpResponse cannot be smaller than 0.");
        }
        this.f631d = i;
    }

    public void setUrl(String str) {
        this.f628a = str;
    }

    public String toString() {
        return "HttpResponse{responseBody='" + this.f629b + '\'' + ", responseCode=" + this.f635h + '}';
    }
}
