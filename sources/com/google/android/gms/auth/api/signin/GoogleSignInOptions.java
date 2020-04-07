package com.google.android.gms.auth.api.signin;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.auth.api.signin.internal.zzo;
import com.google.android.gms.auth.api.signin.internal.zzq;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.Api.ApiOptions.Optional;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GoogleSignInOptions extends zzbgl implements Optional, ReflectedParcelable {
    public static final Creator<GoogleSignInOptions> CREATOR = new zze();
    public static final GoogleSignInOptions DEFAULT_GAMES_SIGN_IN = new Builder().requestScopes(zzd, new Scope[0]).build();
    public static final GoogleSignInOptions DEFAULT_SIGN_IN = new Builder().requestId().requestProfile().build();
    @Hide
    public static final Scope zza = new Scope(Scopes.PROFILE);
    @Hide
    public static final Scope zzb = new Scope("email");
    @Hide
    public static final Scope zzc = new Scope("openid");
    @Hide
    public static final Scope zzd = new Scope("https://www.googleapis.com/auth/games_lite");
    @Hide
    public static final Scope zze = new Scope(Scopes.GAMES);
    private static Comparator<Scope> zzp = new zzd();
    private int zzf;
    /* access modifiers changed from: private */
    public final ArrayList<Scope> zzg;
    /* access modifiers changed from: private */
    public Account zzh;
    /* access modifiers changed from: private */
    public boolean zzi;
    /* access modifiers changed from: private */
    public final boolean zzj;
    /* access modifiers changed from: private */
    public final boolean zzk;
    /* access modifiers changed from: private */
    public String zzl;
    /* access modifiers changed from: private */
    public String zzm;
    /* access modifiers changed from: private */
    public ArrayList<zzo> zzn;
    private Map<Integer, zzo> zzo;

    public static final class Builder {
        private Set<Scope> zza = new HashSet();
        private boolean zzb;
        private boolean zzc;
        private boolean zzd;
        private String zze;
        private Account zzf;
        private String zzg;
        private Map<Integer, zzo> zzh = new HashMap();

        public Builder() {
        }

        public Builder(@NonNull GoogleSignInOptions googleSignInOptions) {
            zzbq.zza(googleSignInOptions);
            this.zza = new HashSet(googleSignInOptions.zzg);
            this.zzb = googleSignInOptions.zzj;
            this.zzc = googleSignInOptions.zzk;
            this.zzd = googleSignInOptions.zzi;
            this.zze = googleSignInOptions.zzl;
            this.zzf = googleSignInOptions.zzh;
            this.zzg = googleSignInOptions.zzm;
            this.zzh = GoogleSignInOptions.zzb((List<zzo>) googleSignInOptions.zzn);
        }

        private final String zza(String str) {
            zzbq.zza(str);
            zzbq.zzb(this.zze == null || this.zze.equals(str), "two different server client ids provided");
            return str;
        }

        public final Builder addExtension(GoogleSignInOptionsExtension googleSignInOptionsExtension) {
            if (this.zzh.containsKey(Integer.valueOf(googleSignInOptionsExtension.getExtensionType()))) {
                throw new IllegalStateException("Only one extension per type may be added");
            }
            if (googleSignInOptionsExtension.getImpliedScopes() != null) {
                this.zza.addAll(googleSignInOptionsExtension.getImpliedScopes());
            }
            this.zzh.put(Integer.valueOf(googleSignInOptionsExtension.getExtensionType()), new zzo(googleSignInOptionsExtension));
            return this;
        }

        public final GoogleSignInOptions build() {
            if (this.zza.contains(GoogleSignInOptions.zze) && this.zza.contains(GoogleSignInOptions.zzd)) {
                this.zza.remove(GoogleSignInOptions.zzd);
            }
            if (this.zzd && (this.zzf == null || !this.zza.isEmpty())) {
                requestId();
            }
            return new GoogleSignInOptions(3, new ArrayList(this.zza), this.zzf, this.zzd, this.zzb, this.zzc, this.zze, this.zzg, this.zzh, null);
        }

        public final Builder requestEmail() {
            this.zza.add(GoogleSignInOptions.zzb);
            return this;
        }

        public final Builder requestId() {
            this.zza.add(GoogleSignInOptions.zzc);
            return this;
        }

        public final Builder requestIdToken(String str) {
            this.zzd = true;
            this.zze = zza(str);
            return this;
        }

        public final Builder requestProfile() {
            this.zza.add(GoogleSignInOptions.zza);
            return this;
        }

        public final Builder requestScopes(Scope scope, Scope... scopeArr) {
            this.zza.add(scope);
            this.zza.addAll(Arrays.asList(scopeArr));
            return this;
        }

        public final Builder requestServerAuthCode(String str) {
            return requestServerAuthCode(str, false);
        }

        public final Builder requestServerAuthCode(String str, boolean z) {
            this.zzb = true;
            this.zze = zza(str);
            this.zzc = z;
            return this;
        }

        public final Builder setAccountName(String str) {
            this.zzf = new Account(zzbq.zza(str), "com.google");
            return this;
        }

        public final Builder setHostedDomain(String str) {
            this.zzg = zzbq.zza(str);
            return this;
        }
    }

    GoogleSignInOptions(int i, ArrayList<Scope> arrayList, Account account, boolean z, boolean z2, boolean z3, String str, String str2, ArrayList<zzo> arrayList2) {
        this(i, arrayList, account, z, z2, z3, str, str2, zzb((List<zzo>) arrayList2));
    }

    private GoogleSignInOptions(int i, ArrayList<Scope> arrayList, Account account, boolean z, boolean z2, boolean z3, String str, String str2, Map<Integer, zzo> map) {
        this.zzf = i;
        this.zzg = arrayList;
        this.zzh = account;
        this.zzi = z;
        this.zzj = z2;
        this.zzk = z3;
        this.zzl = str;
        this.zzm = str2;
        this.zzn = new ArrayList<>(map.values());
        this.zzo = map;
    }

    /* synthetic */ GoogleSignInOptions(int i, ArrayList arrayList, Account account, boolean z, boolean z2, boolean z3, String str, String str2, Map map, zzd zzd2) {
        this(3, arrayList, account, z, z2, z3, str, str2, map);
    }

    @Nullable
    @Hide
    public static GoogleSignInOptions zza(@Nullable String str) throws JSONException {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        JSONObject jSONObject = new JSONObject(str);
        HashSet hashSet = new HashSet();
        JSONArray jSONArray = jSONObject.getJSONArray("scopes");
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            hashSet.add(new Scope(jSONArray.getString(i)));
        }
        String optString = jSONObject.optString("accountName", null);
        return new GoogleSignInOptions(3, new ArrayList<>(hashSet), !TextUtils.isEmpty(optString) ? new Account(optString, "com.google") : null, jSONObject.getBoolean("idTokenRequested"), jSONObject.getBoolean("serverAuthRequested"), jSONObject.getBoolean("forceCodeForRefreshToken"), jSONObject.optString("serverClientId", null), jSONObject.optString("hostedDomain", null), (Map<Integer, zzo>) new HashMap<Integer,zzo>());
    }

    /* access modifiers changed from: private */
    public static Map<Integer, zzo> zzb(@Nullable List<zzo> list) {
        HashMap hashMap = new HashMap();
        if (list == null) {
            return hashMap;
        }
        for (zzo zzo2 : list) {
            hashMap.put(Integer.valueOf(zzo2.zza()), zzo2);
        }
        return hashMap;
    }

    private final JSONObject zzg() {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONArray jSONArray = new JSONArray();
            Collections.sort(this.zzg, zzp);
            ArrayList arrayList = this.zzg;
            int size = arrayList.size();
            int i = 0;
            while (i < size) {
                Object obj = arrayList.get(i);
                i++;
                jSONArray.put(((Scope) obj).zza());
            }
            jSONObject.put("scopes", jSONArray);
            if (this.zzh != null) {
                jSONObject.put("accountName", this.zzh.name);
            }
            jSONObject.put("idTokenRequested", this.zzi);
            jSONObject.put("forceCodeForRefreshToken", this.zzk);
            jSONObject.put("serverAuthRequested", this.zzj);
            if (!TextUtils.isEmpty(this.zzl)) {
                jSONObject.put("serverClientId", this.zzl);
            }
            if (!TextUtils.isEmpty(this.zzm)) {
                jSONObject.put("hostedDomain", this.zzm);
            }
            return jSONObject;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        try {
            GoogleSignInOptions googleSignInOptions = (GoogleSignInOptions) obj;
            if (this.zzn.size() > 0 || googleSignInOptions.zzn.size() > 0 || this.zzg.size() != googleSignInOptions.zza().size() || !this.zzg.containsAll(googleSignInOptions.zza())) {
                return false;
            }
            if (this.zzh == null) {
                if (googleSignInOptions.zzh != null) {
                    return false;
                }
            } else if (!this.zzh.equals(googleSignInOptions.zzh)) {
                return false;
            }
            if (TextUtils.isEmpty(this.zzl)) {
                if (!TextUtils.isEmpty(googleSignInOptions.zzl)) {
                    return false;
                }
            } else if (!this.zzl.equals(googleSignInOptions.zzl)) {
                return false;
            }
            return this.zzk == googleSignInOptions.zzk && this.zzi == googleSignInOptions.zzi && this.zzj == googleSignInOptions.zzj;
        } catch (ClassCastException e) {
            return false;
        }
    }

    public Scope[] getScopeArray() {
        return (Scope[]) this.zzg.toArray(new Scope[this.zzg.size()]);
    }

    public int hashCode() {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = this.zzg;
        int size = arrayList2.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList2.get(i);
            i++;
            arrayList.add(((Scope) obj).zza());
        }
        Collections.sort(arrayList);
        return new zzq().zza((Object) arrayList).zza((Object) this.zzh).zza((Object) this.zzl).zza(this.zzk).zza(this.zzi).zza(this.zzj).zza();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zza2 = zzbgo.zza(parcel);
        zzbgo.zza(parcel, 1, this.zzf);
        zzbgo.zzc(parcel, 2, zza(), false);
        zzbgo.zza(parcel, 3, (Parcelable) this.zzh, i, false);
        zzbgo.zza(parcel, 4, this.zzi);
        zzbgo.zza(parcel, 5, this.zzj);
        zzbgo.zza(parcel, 6, this.zzk);
        zzbgo.zza(parcel, 7, this.zzl, false);
        zzbgo.zza(parcel, 8, this.zzm, false);
        zzbgo.zzc(parcel, 9, this.zzn, false);
        zzbgo.zza(parcel, zza2);
    }

    @Hide
    public final ArrayList<Scope> zza() {
        return new ArrayList<>(this.zzg);
    }

    @Hide
    public final Account zzb() {
        return this.zzh;
    }

    @Hide
    public final boolean zzc() {
        return this.zzi;
    }

    @Hide
    public final boolean zzd() {
        return this.zzj;
    }

    @Hide
    public final String zze() {
        return this.zzl;
    }

    @Hide
    public final String zzf() {
        return zzg().toString();
    }
}
