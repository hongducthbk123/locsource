package com.tencent.bugly.crashreport.crash;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.tencent.bugly.crashreport.common.info.PlugInBean;
import com.tencent.bugly.proguard.C2018y;
import java.util.Map;
import java.util.UUID;

/* compiled from: BUGLY */
public class CrashDetailBean implements Parcelable, Comparable<CrashDetailBean> {
    public static final Creator<CrashDetailBean> CREATOR = new Creator<CrashDetailBean>() {
        public final /* synthetic */ Object createFromParcel(Parcel parcel) {
            return new CrashDetailBean(parcel);
        }

        public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
            return new CrashDetailBean[i];
        }
    };

    /* renamed from: A */
    public String f1265A = "";

    /* renamed from: B */
    public long f1266B = -1;

    /* renamed from: C */
    public long f1267C = -1;

    /* renamed from: D */
    public long f1268D = -1;

    /* renamed from: E */
    public long f1269E = -1;

    /* renamed from: F */
    public long f1270F = -1;

    /* renamed from: G */
    public long f1271G = -1;

    /* renamed from: H */
    public String f1272H = "";

    /* renamed from: I */
    public String f1273I = "";

    /* renamed from: J */
    public String f1274J = "";

    /* renamed from: K */
    public String f1275K = "";

    /* renamed from: L */
    public long f1276L = -1;

    /* renamed from: M */
    public boolean f1277M = false;

    /* renamed from: N */
    public Map<String, String> f1278N = null;

    /* renamed from: O */
    public int f1279O = -1;

    /* renamed from: P */
    public int f1280P = -1;

    /* renamed from: Q */
    public Map<String, String> f1281Q = null;

    /* renamed from: R */
    public Map<String, String> f1282R = null;

    /* renamed from: S */
    public byte[] f1283S = null;

    /* renamed from: T */
    public String f1284T = null;

    /* renamed from: U */
    public String f1285U = null;

    /* renamed from: V */
    private String f1286V = "";

    /* renamed from: a */
    public long f1287a = -1;

    /* renamed from: b */
    public int f1288b = 0;

    /* renamed from: c */
    public String f1289c = UUID.randomUUID().toString();

    /* renamed from: d */
    public boolean f1290d = false;

    /* renamed from: e */
    public String f1291e = "";

    /* renamed from: f */
    public String f1292f = "";

    /* renamed from: g */
    public String f1293g = "";

    /* renamed from: h */
    public Map<String, PlugInBean> f1294h = null;

    /* renamed from: i */
    public Map<String, PlugInBean> f1295i = null;

    /* renamed from: j */
    public boolean f1296j = false;

    /* renamed from: k */
    public boolean f1297k = false;

    /* renamed from: l */
    public int f1298l = 0;

    /* renamed from: m */
    public String f1299m = "";

    /* renamed from: n */
    public String f1300n = "";

    /* renamed from: o */
    public String f1301o = "";

    /* renamed from: p */
    public String f1302p = "";

    /* renamed from: q */
    public String f1303q = "";

    /* renamed from: r */
    public long f1304r = -1;

    /* renamed from: s */
    public String f1305s = null;

    /* renamed from: t */
    public int f1306t = 0;

    /* renamed from: u */
    public String f1307u = "";

    /* renamed from: v */
    public String f1308v = "";

    /* renamed from: w */
    public String f1309w = null;

    /* renamed from: x */
    public byte[] f1310x = null;

    /* renamed from: y */
    public Map<String, String> f1311y = null;

    /* renamed from: z */
    public String f1312z = "";

    public /* bridge */ /* synthetic */ int compareTo(Object obj) {
        CrashDetailBean crashDetailBean = (CrashDetailBean) obj;
        if (crashDetailBean != null) {
            long j = this.f1304r - crashDetailBean.f1304r;
            if (j <= 0) {
                return j < 0 ? -1 : 0;
            }
        }
        return 1;
    }

    public CrashDetailBean() {
    }

    public CrashDetailBean(Parcel parcel) {
        boolean z;
        boolean z2;
        boolean z3 = true;
        this.f1288b = parcel.readInt();
        this.f1289c = parcel.readString();
        this.f1290d = parcel.readByte() == 1;
        this.f1291e = parcel.readString();
        this.f1292f = parcel.readString();
        this.f1293g = parcel.readString();
        if (parcel.readByte() == 1) {
            z = true;
        } else {
            z = false;
        }
        this.f1296j = z;
        if (parcel.readByte() == 1) {
            z2 = true;
        } else {
            z2 = false;
        }
        this.f1297k = z2;
        this.f1298l = parcel.readInt();
        this.f1299m = parcel.readString();
        this.f1300n = parcel.readString();
        this.f1301o = parcel.readString();
        this.f1302p = parcel.readString();
        this.f1303q = parcel.readString();
        this.f1304r = parcel.readLong();
        this.f1305s = parcel.readString();
        this.f1306t = parcel.readInt();
        this.f1307u = parcel.readString();
        this.f1308v = parcel.readString();
        this.f1309w = parcel.readString();
        this.f1311y = C2018y.m2169b(parcel);
        this.f1312z = parcel.readString();
        this.f1265A = parcel.readString();
        this.f1266B = parcel.readLong();
        this.f1267C = parcel.readLong();
        this.f1268D = parcel.readLong();
        this.f1269E = parcel.readLong();
        this.f1270F = parcel.readLong();
        this.f1271G = parcel.readLong();
        this.f1272H = parcel.readString();
        this.f1286V = parcel.readString();
        this.f1273I = parcel.readString();
        this.f1274J = parcel.readString();
        this.f1275K = parcel.readString();
        this.f1276L = parcel.readLong();
        if (parcel.readByte() != 1) {
            z3 = false;
        }
        this.f1277M = z3;
        this.f1278N = C2018y.m2169b(parcel);
        this.f1294h = C2018y.m2153a(parcel);
        this.f1295i = C2018y.m2153a(parcel);
        this.f1279O = parcel.readInt();
        this.f1280P = parcel.readInt();
        this.f1281Q = C2018y.m2169b(parcel);
        this.f1282R = C2018y.m2169b(parcel);
        this.f1283S = parcel.createByteArray();
        this.f1310x = parcel.createByteArray();
        this.f1284T = parcel.readString();
        this.f1285U = parcel.readString();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int i2;
        int i3;
        int i4 = 1;
        parcel.writeInt(this.f1288b);
        parcel.writeString(this.f1289c);
        parcel.writeByte((byte) (this.f1290d ? 1 : 0));
        parcel.writeString(this.f1291e);
        parcel.writeString(this.f1292f);
        parcel.writeString(this.f1293g);
        if (this.f1296j) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeByte((byte) i2);
        if (this.f1297k) {
            i3 = 1;
        } else {
            i3 = 0;
        }
        parcel.writeByte((byte) i3);
        parcel.writeInt(this.f1298l);
        parcel.writeString(this.f1299m);
        parcel.writeString(this.f1300n);
        parcel.writeString(this.f1301o);
        parcel.writeString(this.f1302p);
        parcel.writeString(this.f1303q);
        parcel.writeLong(this.f1304r);
        parcel.writeString(this.f1305s);
        parcel.writeInt(this.f1306t);
        parcel.writeString(this.f1307u);
        parcel.writeString(this.f1308v);
        parcel.writeString(this.f1309w);
        C2018y.m2171b(parcel, this.f1311y);
        parcel.writeString(this.f1312z);
        parcel.writeString(this.f1265A);
        parcel.writeLong(this.f1266B);
        parcel.writeLong(this.f1267C);
        parcel.writeLong(this.f1268D);
        parcel.writeLong(this.f1269E);
        parcel.writeLong(this.f1270F);
        parcel.writeLong(this.f1271G);
        parcel.writeString(this.f1272H);
        parcel.writeString(this.f1286V);
        parcel.writeString(this.f1273I);
        parcel.writeString(this.f1274J);
        parcel.writeString(this.f1275K);
        parcel.writeLong(this.f1276L);
        if (!this.f1277M) {
            i4 = 0;
        }
        parcel.writeByte((byte) i4);
        C2018y.m2171b(parcel, this.f1278N);
        C2018y.m2154a(parcel, this.f1294h);
        C2018y.m2154a(parcel, this.f1295i);
        parcel.writeInt(this.f1279O);
        parcel.writeInt(this.f1280P);
        C2018y.m2171b(parcel, this.f1281Q);
        C2018y.m2171b(parcel, this.f1282R);
        parcel.writeByteArray(this.f1283S);
        parcel.writeByteArray(this.f1310x);
        parcel.writeString(this.f1284T);
        parcel.writeString(this.f1285U);
    }
}
