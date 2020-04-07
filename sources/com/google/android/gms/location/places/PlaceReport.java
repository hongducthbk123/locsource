package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.facebook.share.internal.ShareConstants;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.internal.zzbi;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.Arrays;

public class PlaceReport extends zzbgl implements ReflectedParcelable {
    public static final Creator<PlaceReport> CREATOR = new zzl();
    private int zza;
    private final String zzb;
    private final String zzc;
    private final String zzd;

    @Hide
    PlaceReport(int i, String str, String str2, String str3) {
        this.zza = i;
        this.zzb = str;
        this.zzc = str2;
        this.zzd = str3;
    }

    public static PlaceReport create(String str, String str2) {
        boolean z = false;
        String str3 = "unknown";
        zzbq.zza(str);
        zzbq.zza(str2);
        zzbq.zza(str3);
        char c = 65535;
        switch (str3.hashCode()) {
            case -1436706272:
                if (str3.equals("inferredGeofencing")) {
                    c = 2;
                    break;
                }
                break;
            case -1194968642:
                if (str3.equals("userReported")) {
                    c = 1;
                    break;
                }
                break;
            case -284840886:
                if (str3.equals("unknown")) {
                    c = 0;
                    break;
                }
                break;
            case -262743844:
                if (str3.equals("inferredReverseGeocoding")) {
                    c = 4;
                    break;
                }
                break;
            case 1164924125:
                if (str3.equals("inferredSnappedToRoad")) {
                    c = 5;
                    break;
                }
                break;
            case 1287171955:
                if (str3.equals("inferredRadioSignals")) {
                    c = 3;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                z = true;
                break;
        }
        zzbq.zzb(z, "Invalid source");
        return new PlaceReport(1, str, str2, str3);
    }

    @Hide
    public boolean equals(Object obj) {
        if (!(obj instanceof PlaceReport)) {
            return false;
        }
        PlaceReport placeReport = (PlaceReport) obj;
        return zzbg.zza(this.zzb, placeReport.zzb) && zzbg.zza(this.zzc, placeReport.zzc) && zzbg.zza(this.zzd, placeReport.zzd);
    }

    public String getPlaceId() {
        return this.zzb;
    }

    public String getTag() {
        return this.zzc;
    }

    @Hide
    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzb, this.zzc, this.zzd});
    }

    @Hide
    public String toString() {
        zzbi zza2 = zzbg.zza(this);
        zza2.zza("placeId", this.zzb);
        zza2.zza("tag", this.zzc);
        if (!"unknown".equals(this.zzd)) {
            zza2.zza(ShareConstants.FEED_SOURCE_PARAM, this.zzd);
        }
        return zza2.toString();
    }

    @Hide
    public void writeToParcel(Parcel parcel, int i) {
        int zza2 = zzbgo.zza(parcel);
        zzbgo.zza(parcel, 1, this.zza);
        zzbgo.zza(parcel, 2, getPlaceId(), false);
        zzbgo.zza(parcel, 3, getTag(), false);
        zzbgo.zza(parcel, 4, this.zzd, false);
        zzbgo.zza(parcel, zza2);
    }
}
