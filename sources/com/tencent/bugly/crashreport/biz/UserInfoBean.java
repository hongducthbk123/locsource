package com.tencent.bugly.crashreport.biz;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.tencent.bugly.proguard.C2018y;
import java.util.Map;

/* compiled from: BUGLY */
public class UserInfoBean implements Parcelable {
    public static final Creator<UserInfoBean> CREATOR = new Creator<UserInfoBean>() {
        public final /* synthetic */ Object createFromParcel(Parcel parcel) {
            return new UserInfoBean(parcel);
        }

        public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
            return new UserInfoBean[i];
        }
    };

    /* renamed from: a */
    public long f1104a;

    /* renamed from: b */
    public int f1105b;

    /* renamed from: c */
    public String f1106c;

    /* renamed from: d */
    public String f1107d;

    /* renamed from: e */
    public long f1108e;

    /* renamed from: f */
    public long f1109f;

    /* renamed from: g */
    public long f1110g;

    /* renamed from: h */
    public long f1111h;

    /* renamed from: i */
    public long f1112i;

    /* renamed from: j */
    public String f1113j;

    /* renamed from: k */
    public long f1114k = 0;

    /* renamed from: l */
    public boolean f1115l = false;

    /* renamed from: m */
    public String f1116m = "unknown";

    /* renamed from: n */
    public String f1117n;

    /* renamed from: o */
    public int f1118o;

    /* renamed from: p */
    public int f1119p = -1;

    /* renamed from: q */
    public int f1120q = -1;

    /* renamed from: r */
    public Map<String, String> f1121r = null;

    /* renamed from: s */
    public Map<String, String> f1122s = null;

    public UserInfoBean() {
    }

    public UserInfoBean(Parcel parcel) {
        boolean z = true;
        this.f1105b = parcel.readInt();
        this.f1106c = parcel.readString();
        this.f1107d = parcel.readString();
        this.f1108e = parcel.readLong();
        this.f1109f = parcel.readLong();
        this.f1110g = parcel.readLong();
        this.f1111h = parcel.readLong();
        this.f1112i = parcel.readLong();
        this.f1113j = parcel.readString();
        this.f1114k = parcel.readLong();
        if (parcel.readByte() != 1) {
            z = false;
        }
        this.f1115l = z;
        this.f1116m = parcel.readString();
        this.f1119p = parcel.readInt();
        this.f1120q = parcel.readInt();
        this.f1121r = C2018y.m2169b(parcel);
        this.f1122s = C2018y.m2169b(parcel);
        this.f1117n = parcel.readString();
        this.f1118o = parcel.readInt();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f1105b);
        parcel.writeString(this.f1106c);
        parcel.writeString(this.f1107d);
        parcel.writeLong(this.f1108e);
        parcel.writeLong(this.f1109f);
        parcel.writeLong(this.f1110g);
        parcel.writeLong(this.f1111h);
        parcel.writeLong(this.f1112i);
        parcel.writeString(this.f1113j);
        parcel.writeLong(this.f1114k);
        parcel.writeByte((byte) (this.f1115l ? 1 : 0));
        parcel.writeString(this.f1116m);
        parcel.writeInt(this.f1119p);
        parcel.writeInt(this.f1120q);
        C2018y.m2171b(parcel, this.f1121r);
        C2018y.m2171b(parcel, this.f1122s);
        parcel.writeString(this.f1117n);
        parcel.writeInt(this.f1118o);
    }
}
