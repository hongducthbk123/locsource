package p004cn.jpush.android.api;

import java.io.Serializable;
import java.util.Set;

/* renamed from: cn.jpush.android.api.JPushMessage */
public class JPushMessage implements Serializable {
    private static final long serialVersionUID = 1;

    /* renamed from: a */
    private String f721a;

    /* renamed from: b */
    private Set<String> f722b;

    /* renamed from: c */
    private String f723c;

    /* renamed from: d */
    private int f724d;

    /* renamed from: e */
    private boolean f725e;

    /* renamed from: f */
    private boolean f726f;

    /* renamed from: g */
    private int f727g;

    /* renamed from: h */
    private String f728h;

    public String getAlias() {
        return this.f721a;
    }

    public void setAlias(String str) {
        this.f721a = str;
    }

    public Set<String> getTags() {
        return this.f722b;
    }

    public void setTags(Set<String> set) {
        this.f722b = set;
    }

    public int getErrorCode() {
        return this.f724d;
    }

    public void setErrorCode(int i) {
        this.f724d = i;
    }

    public int getSequence() {
        return this.f727g;
    }

    public void setSequence(int i) {
        this.f727g = i;
    }

    public String getCheckTag() {
        return this.f723c;
    }

    public void setCheckTag(String str) {
        this.f723c = str;
    }

    public boolean getTagCheckStateResult() {
        return this.f725e;
    }

    public void setTagCheckStateResult(boolean z) {
        this.f725e = z;
    }

    public boolean isTagCheckOperator() {
        return this.f726f;
    }

    public void setTagCheckOperator(boolean z) {
        this.f726f = z;
    }

    public String getMobileNumber() {
        return this.f728h;
    }

    public void setMobileNumber(String str) {
        this.f728h = str;
    }

    public String toString() {
        return "JPushMessage{alias='" + this.f721a + '\'' + ", tags=" + this.f722b + ", checkTag='" + this.f723c + '\'' + ", errorCode=" + this.f724d + ", tagCheckStateResult=" + this.f725e + ", isTagCheckOperator=" + this.f726f + ", sequence=" + this.f727g + ", mobileNumber=" + this.f728h + '}';
    }
}
