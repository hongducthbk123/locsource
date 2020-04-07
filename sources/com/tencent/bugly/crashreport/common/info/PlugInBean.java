package com.tencent.bugly.crashreport.common.info;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

/* compiled from: BUGLY */
public class PlugInBean implements Parcelable {
    public static final Creator<PlugInBean> CREATOR = new Creator<PlugInBean>() {
        public final /* synthetic */ Object createFromParcel(Parcel parcel) {
            return new PlugInBean(parcel);
        }

        public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
            return new PlugInBean[i];
        }
    };

    /* renamed from: a */
    public final String f1152a;

    /* renamed from: b */
    public final String f1153b;

    /* renamed from: c */
    public final String f1154c;

    public PlugInBean(String str, String str2, String str3) {
        this.f1152a = str;
        this.f1153b = str2;
        this.f1154c = str3;
    }

    public String toString() {
        return "plid:" + this.f1152a + " plV:" + this.f1153b + " plUUID:" + this.f1154c;
    }

    public PlugInBean(Parcel parcel) {
        this.f1152a = parcel.readString();
        this.f1153b = parcel.readString();
        this.f1154c = parcel.readString();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f1152a);
        parcel.writeString(this.f1153b);
        parcel.writeString(this.f1154c);
    }
}
