package com.tencent.bugly.crashreport.common.strategy;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.tencent.bugly.proguard.C2018y;
import java.util.Map;

/* compiled from: BUGLY */
public class StrategyBean implements Parcelable {
    public static final Creator<StrategyBean> CREATOR = new Creator<StrategyBean>() {
        public final /* synthetic */ Object createFromParcel(Parcel parcel) {
            return new StrategyBean(parcel);
        }

        public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
            return new StrategyBean[i];
        }
    };

    /* renamed from: a */
    public static String f1227a = "http://rqd.uu.qq.com/rqd/sync";

    /* renamed from: b */
    public static String f1228b = "http://android.bugly.qq.com/rqd/async";

    /* renamed from: c */
    public static String f1229c = "http://android.bugly.qq.com/rqd/async";

    /* renamed from: d */
    public static String f1230d;

    /* renamed from: e */
    public long f1231e;

    /* renamed from: f */
    public long f1232f;

    /* renamed from: g */
    public boolean f1233g;

    /* renamed from: h */
    public boolean f1234h;

    /* renamed from: i */
    public boolean f1235i;

    /* renamed from: j */
    public boolean f1236j;

    /* renamed from: k */
    public boolean f1237k;

    /* renamed from: l */
    public boolean f1238l;

    /* renamed from: m */
    public boolean f1239m;

    /* renamed from: n */
    public boolean f1240n;

    /* renamed from: o */
    public boolean f1241o;

    /* renamed from: p */
    public long f1242p;

    /* renamed from: q */
    public long f1243q;

    /* renamed from: r */
    public String f1244r;

    /* renamed from: s */
    public String f1245s;

    /* renamed from: t */
    public String f1246t;

    /* renamed from: u */
    public String f1247u;

    /* renamed from: v */
    public Map<String, String> f1248v;

    /* renamed from: w */
    public int f1249w;

    /* renamed from: x */
    public long f1250x;

    /* renamed from: y */
    public long f1251y;

    public StrategyBean() {
        this.f1231e = -1;
        this.f1232f = -1;
        this.f1233g = true;
        this.f1234h = true;
        this.f1235i = true;
        this.f1236j = true;
        this.f1237k = false;
        this.f1238l = true;
        this.f1239m = true;
        this.f1240n = true;
        this.f1241o = true;
        this.f1243q = 30000;
        this.f1244r = f1228b;
        this.f1245s = f1229c;
        this.f1246t = f1227a;
        this.f1249w = 10;
        this.f1250x = 300000;
        this.f1251y = -1;
        this.f1232f = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        sb.append("S(@L@L").append("@)");
        f1230d = sb.toString();
        sb.setLength(0);
        sb.append("*^@K#K").append("@!");
        this.f1247u = sb.toString();
    }

    public StrategyBean(Parcel parcel) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        boolean z6;
        boolean z7;
        boolean z8 = true;
        this.f1231e = -1;
        this.f1232f = -1;
        this.f1233g = true;
        this.f1234h = true;
        this.f1235i = true;
        this.f1236j = true;
        this.f1237k = false;
        this.f1238l = true;
        this.f1239m = true;
        this.f1240n = true;
        this.f1241o = true;
        this.f1243q = 30000;
        this.f1244r = f1228b;
        this.f1245s = f1229c;
        this.f1246t = f1227a;
        this.f1249w = 10;
        this.f1250x = 300000;
        this.f1251y = -1;
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("S(@L@L").append("@)");
            f1230d = sb.toString();
            this.f1232f = parcel.readLong();
            this.f1233g = parcel.readByte() == 1;
            if (parcel.readByte() == 1) {
                z = true;
            } else {
                z = false;
            }
            this.f1234h = z;
            if (parcel.readByte() == 1) {
                z2 = true;
            } else {
                z2 = false;
            }
            this.f1235i = z2;
            this.f1244r = parcel.readString();
            this.f1245s = parcel.readString();
            this.f1247u = parcel.readString();
            this.f1248v = C2018y.m2169b(parcel);
            if (parcel.readByte() == 1) {
                z3 = true;
            } else {
                z3 = false;
            }
            this.f1236j = z3;
            if (parcel.readByte() == 1) {
                z4 = true;
            } else {
                z4 = false;
            }
            this.f1237k = z4;
            if (parcel.readByte() == 1) {
                z5 = true;
            } else {
                z5 = false;
            }
            this.f1240n = z5;
            if (parcel.readByte() == 1) {
                z6 = true;
            } else {
                z6 = false;
            }
            this.f1241o = z6;
            this.f1243q = parcel.readLong();
            if (parcel.readByte() == 1) {
                z7 = true;
            } else {
                z7 = false;
            }
            this.f1238l = z7;
            if (parcel.readByte() != 1) {
                z8 = false;
            }
            this.f1239m = z8;
            this.f1242p = parcel.readLong();
            this.f1249w = parcel.readInt();
            this.f1250x = parcel.readLong();
            this.f1251y = parcel.readLong();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9 = 1;
        parcel.writeLong(this.f1232f);
        parcel.writeByte((byte) (this.f1233g ? 1 : 0));
        if (this.f1234h) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeByte((byte) i2);
        if (this.f1235i) {
            i3 = 1;
        } else {
            i3 = 0;
        }
        parcel.writeByte((byte) i3);
        parcel.writeString(this.f1244r);
        parcel.writeString(this.f1245s);
        parcel.writeString(this.f1247u);
        C2018y.m2171b(parcel, this.f1248v);
        if (this.f1236j) {
            i4 = 1;
        } else {
            i4 = 0;
        }
        parcel.writeByte((byte) i4);
        if (this.f1237k) {
            i5 = 1;
        } else {
            i5 = 0;
        }
        parcel.writeByte((byte) i5);
        if (this.f1240n) {
            i6 = 1;
        } else {
            i6 = 0;
        }
        parcel.writeByte((byte) i6);
        if (this.f1241o) {
            i7 = 1;
        } else {
            i7 = 0;
        }
        parcel.writeByte((byte) i7);
        parcel.writeLong(this.f1243q);
        if (this.f1238l) {
            i8 = 1;
        } else {
            i8 = 0;
        }
        parcel.writeByte((byte) i8);
        if (!this.f1239m) {
            i9 = 0;
        }
        parcel.writeByte((byte) i9);
        parcel.writeLong(this.f1242p);
        parcel.writeInt(this.f1249w);
        parcel.writeLong(this.f1250x);
        parcel.writeLong(this.f1251y);
    }
}
