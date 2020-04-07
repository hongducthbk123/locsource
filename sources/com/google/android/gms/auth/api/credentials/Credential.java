package com.google.android.gms.auth.api.credentials;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.Arrays;
import java.util.List;

public class Credential extends zzbgl implements ReflectedParcelable {
    public static final Creator<Credential> CREATOR = new zza();
    public static final String EXTRA_KEY = "com.google.android.gms.credentials.Credential";
    /* access modifiers changed from: private */
    public final String zza;
    /* access modifiers changed from: private */
    @Nullable
    public final String zzb;
    /* access modifiers changed from: private */
    @Nullable
    public final Uri zzc;
    /* access modifiers changed from: private */
    public final List<IdToken> zzd;
    /* access modifiers changed from: private */
    @Nullable
    public final String zze;
    /* access modifiers changed from: private */
    @Nullable
    public final String zzf;
    /* access modifiers changed from: private */
    @Nullable
    public final String zzg;
    /* access modifiers changed from: private */
    @Nullable
    public final String zzh;
    /* access modifiers changed from: private */
    @Nullable
    public final String zzi;
    /* access modifiers changed from: private */
    @Nullable
    public final String zzj;

    public static class Builder {
        private final String zza;
        private String zzb;
        private Uri zzc;
        private List<IdToken> zzd;
        private String zze;
        private String zzf;
        private String zzg;
        private String zzh;
        private String zzi;
        private String zzj;

        public Builder(Credential credential) {
            this.zza = credential.zza;
            this.zzb = credential.zzb;
            this.zzc = credential.zzc;
            this.zzd = credential.zzd;
            this.zze = credential.zze;
            this.zzf = credential.zzf;
            this.zzg = credential.zzg;
            this.zzh = credential.zzh;
            this.zzi = credential.zzi;
            this.zzj = credential.zzj;
        }

        public Builder(String str) {
            this.zza = str;
        }

        public Credential build() {
            return new Credential(this.zza, this.zzb, this.zzc, this.zzd, this.zze, this.zzf, this.zzg, this.zzh, this.zzi, this.zzj);
        }

        public Builder setAccountType(String str) {
            this.zzf = str;
            return this;
        }

        public Builder setName(String str) {
            this.zzb = str;
            return this;
        }

        public Builder setPassword(String str) {
            this.zze = str;
            return this;
        }

        public Builder setProfilePictureUri(Uri uri) {
            this.zzc = uri;
            return this;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x005c  */
    @com.google.android.gms.common.internal.Hide
    /* Code decompiled incorrectly, please refer to instructions dump. */
    Credential(java.lang.String r6, java.lang.String r7, android.net.Uri r8, java.util.List<com.google.android.gms.auth.api.credentials.IdToken> r9, java.lang.String r10, java.lang.String r11, java.lang.String r12, java.lang.String r13, java.lang.String r14, java.lang.String r15) {
        /*
            r5 = this;
            r1 = 0
            r5.<init>()
            java.lang.String r0 = "credential identifier cannot be null"
            java.lang.Object r0 = com.google.android.gms.common.internal.zzbq.zza(r6, r0)
            java.lang.String r0 = (java.lang.String) r0
            java.lang.String r2 = r0.trim()
            java.lang.String r0 = "credential identifier cannot be empty"
            com.google.android.gms.common.internal.zzbq.zza(r2, r0)
            if (r10 == 0) goto L_0x0025
            boolean r0 = android.text.TextUtils.isEmpty(r10)
            if (r0 == 0) goto L_0x0025
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "Password must not be empty if set"
            r0.<init>(r1)
            throw r0
        L_0x0025:
            if (r11 == 0) goto L_0x0080
            boolean r0 = android.text.TextUtils.isEmpty(r11)
            if (r0 != 0) goto L_0x007e
            android.net.Uri r0 = android.net.Uri.parse(r11)
            boolean r3 = r0.isAbsolute()
            if (r3 == 0) goto L_0x0051
            boolean r3 = r0.isHierarchical()
            if (r3 == 0) goto L_0x0051
            java.lang.String r3 = r0.getScheme()
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 != 0) goto L_0x0051
            java.lang.String r3 = r0.getAuthority()
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 == 0) goto L_0x0064
        L_0x0051:
            r0 = r1
        L_0x0052:
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
            boolean r0 = r0.booleanValue()
            if (r0 != 0) goto L_0x0080
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "Account type must be a valid Http/Https URI"
            r0.<init>(r1)
            throw r0
        L_0x0064:
            java.lang.String r3 = "http"
            java.lang.String r4 = r0.getScheme()
            boolean r3 = r3.equalsIgnoreCase(r4)
            if (r3 != 0) goto L_0x007c
            java.lang.String r3 = "https"
            java.lang.String r0 = r0.getScheme()
            boolean r0 = r3.equalsIgnoreCase(r0)
            if (r0 == 0) goto L_0x007e
        L_0x007c:
            r0 = 1
            goto L_0x0052
        L_0x007e:
            r0 = r1
            goto L_0x0052
        L_0x0080:
            boolean r0 = android.text.TextUtils.isEmpty(r11)
            if (r0 != 0) goto L_0x0094
            boolean r0 = android.text.TextUtils.isEmpty(r10)
            if (r0 != 0) goto L_0x0094
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "Password and AccountType are mutually exclusive"
            r0.<init>(r1)
            throw r0
        L_0x0094:
            if (r7 == 0) goto L_0x00a1
            java.lang.String r0 = r7.trim()
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 == 0) goto L_0x00a1
            r7 = 0
        L_0x00a1:
            r5.zzb = r7
            r5.zzc = r8
            if (r9 != 0) goto L_0x00bc
            java.util.List r0 = java.util.Collections.emptyList()
        L_0x00ab:
            r5.zzd = r0
            r5.zza = r2
            r5.zze = r10
            r5.zzf = r11
            r5.zzg = r12
            r5.zzh = r13
            r5.zzi = r14
            r5.zzj = r15
            return
        L_0x00bc:
            java.util.List r0 = java.util.Collections.unmodifiableList(r9)
            goto L_0x00ab
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.auth.api.credentials.Credential.<init>(java.lang.String, java.lang.String, android.net.Uri, java.util.List, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String):void");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Credential)) {
            return false;
        }
        Credential credential = (Credential) obj;
        return TextUtils.equals(this.zza, credential.zza) && TextUtils.equals(this.zzb, credential.zzb) && zzbg.zza(this.zzc, credential.zzc) && TextUtils.equals(this.zze, credential.zze) && TextUtils.equals(this.zzf, credential.zzf) && TextUtils.equals(this.zzg, credential.zzg);
    }

    @Nullable
    public String getAccountType() {
        return this.zzf;
    }

    @Nullable
    public String getFamilyName() {
        return this.zzj;
    }

    @Nullable
    public String getGeneratedPassword() {
        return this.zzg;
    }

    @Nullable
    public String getGivenName() {
        return this.zzi;
    }

    public String getId() {
        return this.zza;
    }

    public List<IdToken> getIdTokens() {
        return this.zzd;
    }

    @Nullable
    public String getName() {
        return this.zzb;
    }

    @Nullable
    public String getPassword() {
        return this.zze;
    }

    @Nullable
    public Uri getProfilePictureUri() {
        return this.zzc;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.zza, this.zzb, this.zzc, this.zze, this.zzf, this.zzg});
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zza2 = zzbgo.zza(parcel);
        zzbgo.zza(parcel, 1, getId(), false);
        zzbgo.zza(parcel, 2, getName(), false);
        zzbgo.zza(parcel, 3, (Parcelable) getProfilePictureUri(), i, false);
        zzbgo.zzc(parcel, 4, getIdTokens(), false);
        zzbgo.zza(parcel, 5, getPassword(), false);
        zzbgo.zza(parcel, 6, getAccountType(), false);
        zzbgo.zza(parcel, 7, getGeneratedPassword(), false);
        zzbgo.zza(parcel, 8, this.zzh, false);
        zzbgo.zza(parcel, 9, getGivenName(), false);
        zzbgo.zza(parcel, 10, getFamilyName(), false);
        zzbgo.zza(parcel, zza2);
    }
}
