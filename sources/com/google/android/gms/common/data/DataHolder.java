package com.google.android.gms.common.data;

import android.content.ContentValues;
import android.database.CharArrayBuffer;
import android.database.CursorIndexOutOfBoundsException;
import android.database.CursorWindow;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.internal.zzc;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

@KeepName
@Hide
public final class DataHolder extends zzbgl implements Closeable {
    public static final Creator<DataHolder> CREATOR = new zzf();
    private static final zza zzk = new zze(new String[0], null);
    int zza;
    private int zzb;
    private final String[] zzc;
    private Bundle zzd;
    private final CursorWindow[] zze;
    private final int zzf;
    private final Bundle zzg;
    private int[] zzh;
    private boolean zzi;
    private boolean zzj;

    public static class zza {
        /* access modifiers changed from: private */
        public final String[] zza;
        /* access modifiers changed from: private */
        public final ArrayList<HashMap<String, Object>> zzb;
        private final String zzc;
        private final HashMap<Object, Integer> zzd;
        private boolean zze;
        private String zzf;

        private zza(String[] strArr, String str) {
            this.zza = (String[]) zzbq.zza(strArr);
            this.zzb = new ArrayList<>();
            this.zzc = str;
            this.zzd = new HashMap<>();
            this.zze = false;
            this.zzf = null;
        }

        /* synthetic */ zza(String[] strArr, String str, zze zze2) {
            this(strArr, null);
        }

        public zza zza(ContentValues contentValues) {
            zzc.zza((Object) contentValues);
            HashMap hashMap = new HashMap(contentValues.size());
            for (Entry entry : contentValues.valueSet()) {
                hashMap.put((String) entry.getKey(), entry.getValue());
            }
            return zza(hashMap);
        }

        public zza zza(HashMap<String, Object> hashMap) {
            int intValue;
            zzc.zza((Object) hashMap);
            if (this.zzc == null) {
                intValue = -1;
            } else {
                Object obj = hashMap.get(this.zzc);
                if (obj == null) {
                    intValue = -1;
                } else {
                    Integer num = (Integer) this.zzd.get(obj);
                    if (num == null) {
                        this.zzd.put(obj, Integer.valueOf(this.zzb.size()));
                        intValue = -1;
                    } else {
                        intValue = num.intValue();
                    }
                }
            }
            if (intValue == -1) {
                this.zzb.add(hashMap);
            } else {
                this.zzb.remove(intValue);
                this.zzb.add(intValue, hashMap);
            }
            this.zze = false;
            return this;
        }

        public final DataHolder zza(int i) {
            return new DataHolder(this, 0, (Bundle) null, (zze) null);
        }
    }

    public static class zzb extends RuntimeException {
        public zzb(String str) {
            super(str);
        }
    }

    DataHolder(int i, String[] strArr, CursorWindow[] cursorWindowArr, int i2, Bundle bundle) {
        this.zzi = false;
        this.zzj = true;
        this.zzb = i;
        this.zzc = strArr;
        this.zze = cursorWindowArr;
        this.zzf = i2;
        this.zzg = bundle;
    }

    private DataHolder(zza zza2, int i, Bundle bundle) {
        this(zza2.zza, zza(zza2, -1), i, (Bundle) null);
    }

    /* synthetic */ DataHolder(zza zza2, int i, Bundle bundle, zze zze2) {
        this(zza2, 0, null);
    }

    private DataHolder(String[] strArr, CursorWindow[] cursorWindowArr, int i, Bundle bundle) {
        this.zzi = false;
        this.zzj = true;
        this.zzb = 1;
        this.zzc = (String[]) zzbq.zza(strArr);
        this.zze = (CursorWindow[]) zzbq.zza(cursorWindowArr);
        this.zzf = i;
        this.zzg = bundle;
        zza();
    }

    public static zza zza(String[] strArr) {
        return new zza(strArr, null, null);
    }

    private final void zza(String str, int i) {
        if (this.zzd == null || !this.zzd.containsKey(str)) {
            String str2 = "No such column: ";
            String valueOf = String.valueOf(str);
            throw new IllegalArgumentException(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        } else if (zze()) {
            throw new IllegalArgumentException("Buffer is closed.");
        } else if (i < 0 || i >= this.zza) {
            throw new CursorIndexOutOfBoundsException(i, this.zza);
        }
    }

    private static CursorWindow[] zza(zza zza2, int i) {
        int i2;
        boolean z;
        CursorWindow cursorWindow;
        if (zza2.zza.length == 0) {
            return new CursorWindow[0];
        }
        ArrayList zzb2 = zza2.zzb;
        int size = zzb2.size();
        CursorWindow cursorWindow2 = new CursorWindow(false);
        ArrayList arrayList = new ArrayList();
        arrayList.add(cursorWindow2);
        cursorWindow2.setNumColumns(zza2.zza.length);
        int i3 = 0;
        boolean z2 = false;
        while (i3 < size) {
            try {
                if (!cursorWindow2.allocRow()) {
                    Log.d("DataHolder", "Allocating additional cursor window for large data set (row " + i3 + ")");
                    cursorWindow2 = new CursorWindow(false);
                    cursorWindow2.setStartPosition(i3);
                    cursorWindow2.setNumColumns(zza2.zza.length);
                    arrayList.add(cursorWindow2);
                    if (!cursorWindow2.allocRow()) {
                        Log.e("DataHolder", "Unable to allocate row to hold data.");
                        arrayList.remove(cursorWindow2);
                        return (CursorWindow[]) arrayList.toArray(new CursorWindow[arrayList.size()]);
                    }
                }
                Map map = (Map) zzb2.get(i3);
                boolean z3 = true;
                for (int i4 = 0; i4 < zza2.zza.length && z3; i4++) {
                    String str = zza2.zza[i4];
                    Object obj = map.get(str);
                    if (obj == null) {
                        z3 = cursorWindow2.putNull(i3, i4);
                    } else if (obj instanceof String) {
                        z3 = cursorWindow2.putString((String) obj, i3, i4);
                    } else if (obj instanceof Long) {
                        z3 = cursorWindow2.putLong(((Long) obj).longValue(), i3, i4);
                    } else if (obj instanceof Integer) {
                        z3 = cursorWindow2.putLong((long) ((Integer) obj).intValue(), i3, i4);
                    } else if (obj instanceof Boolean) {
                        z3 = cursorWindow2.putLong(((Boolean) obj).booleanValue() ? 1 : 0, i3, i4);
                    } else if (obj instanceof byte[]) {
                        z3 = cursorWindow2.putBlob((byte[]) obj, i3, i4);
                    } else if (obj instanceof Double) {
                        z3 = cursorWindow2.putDouble(((Double) obj).doubleValue(), i3, i4);
                    } else if (obj instanceof Float) {
                        z3 = cursorWindow2.putDouble((double) ((Float) obj).floatValue(), i3, i4);
                    } else {
                        String valueOf = String.valueOf(obj);
                        throw new IllegalArgumentException(new StringBuilder(String.valueOf(str).length() + 32 + String.valueOf(valueOf).length()).append("Unsupported object for column ").append(str).append(": ").append(valueOf).toString());
                    }
                }
                if (z3) {
                    i2 = i3;
                    z = false;
                    cursorWindow = cursorWindow2;
                } else if (z2) {
                    throw new zzb("Could not add the value to a new CursorWindow. The size of value may be larger than what a CursorWindow can handle.");
                } else {
                    Log.d("DataHolder", "Couldn't populate window data for row " + i3 + " - allocating new window.");
                    cursorWindow2.freeLastRow();
                    CursorWindow cursorWindow3 = new CursorWindow(false);
                    cursorWindow3.setStartPosition(i3);
                    cursorWindow3.setNumColumns(zza2.zza.length);
                    arrayList.add(cursorWindow3);
                    i2 = i3 - 1;
                    cursorWindow = cursorWindow3;
                    z = true;
                }
                z2 = z;
                cursorWindow2 = cursorWindow;
                i3 = i2 + 1;
            } catch (RuntimeException e) {
                RuntimeException runtimeException = e;
                int size2 = arrayList.size();
                for (int i5 = 0; i5 < size2; i5++) {
                    ((CursorWindow) arrayList.get(i5)).close();
                }
                throw runtimeException;
            }
        }
        return (CursorWindow[]) arrayList.toArray(new CursorWindow[arrayList.size()]);
    }

    public static DataHolder zzb(int i) {
        return new DataHolder(zzk, i, null);
    }

    public final void close() {
        synchronized (this) {
            if (!this.zzi) {
                this.zzi = true;
                for (CursorWindow close : this.zze) {
                    close.close();
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public final void finalize() throws Throwable {
        try {
            if (this.zzj && this.zze.length > 0 && !zze()) {
                close();
                String obj = toString();
                Log.e("DataBuffer", new StringBuilder(String.valueOf(obj).length() + 178).append("Internal data leak within a DataBuffer object detected!  Be sure to explicitly call release() on all DataBuffer extending objects when you are done with them. (internal object: ").append(obj).append(")").toString());
            }
        } finally {
            super.finalize();
        }
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zza2 = zzbgo.zza(parcel);
        zzbgo.zza(parcel, 1, this.zzc, false);
        zzbgo.zza(parcel, 2, (T[]) this.zze, i, false);
        zzbgo.zza(parcel, 3, this.zzf);
        zzbgo.zza(parcel, 4, this.zzg, false);
        zzbgo.zza(parcel, 1000, this.zzb);
        zzbgo.zza(parcel, zza2);
        if ((i & 1) != 0) {
            close();
        }
    }

    @Hide
    public final int zza(int i) {
        int i2 = 0;
        zzbq.zza(i >= 0 && i < this.zza);
        while (true) {
            if (i2 >= this.zzh.length) {
                break;
            } else if (i < this.zzh[i2]) {
                i2--;
                break;
            } else {
                i2++;
            }
        }
        return i2 == this.zzh.length ? i2 - 1 : i2;
    }

    public final long zza(String str, int i, int i2) {
        zza(str, i);
        return this.zze[i2].getLong(i, this.zzd.getInt(str));
    }

    public final void zza() {
        this.zzd = new Bundle();
        for (int i = 0; i < this.zzc.length; i++) {
            this.zzd.putInt(this.zzc[i], i);
        }
        this.zzh = new int[this.zze.length];
        int i2 = 0;
        for (int i3 = 0; i3 < this.zze.length; i3++) {
            this.zzh[i3] = i2;
            i2 += this.zze[i3].getNumRows() - (i2 - this.zze[i3].getStartPosition());
        }
        this.zza = i2;
    }

    public final void zza(String str, int i, int i2, CharArrayBuffer charArrayBuffer) {
        zza(str, i);
        this.zze[i2].copyStringToBuffer(i, this.zzd.getInt(str), charArrayBuffer);
    }

    public final boolean zza(String str) {
        return this.zzd.containsKey(str);
    }

    public final int zzb() {
        return this.zzf;
    }

    public final int zzb(String str, int i, int i2) {
        zza(str, i);
        return this.zze[i2].getInt(i, this.zzd.getInt(str));
    }

    @Hide
    public final Bundle zzc() {
        return this.zzg;
    }

    public final String zzc(String str, int i, int i2) {
        zza(str, i);
        return this.zze[i2].getString(i, this.zzd.getInt(str));
    }

    public final int zzd() {
        return this.zza;
    }

    public final boolean zzd(String str, int i, int i2) {
        zza(str, i);
        return Long.valueOf(this.zze[i2].getLong(i, this.zzd.getInt(str))).longValue() == 1;
    }

    public final float zze(String str, int i, int i2) {
        zza(str, i);
        return this.zze[i2].getFloat(i, this.zzd.getInt(str));
    }

    public final boolean zze() {
        boolean z;
        synchronized (this) {
            z = this.zzi;
        }
        return z;
    }

    public final byte[] zzf(String str, int i, int i2) {
        zza(str, i);
        return this.zze[i2].getBlob(i, this.zzd.getInt(str));
    }

    public final boolean zzg(String str, int i, int i2) {
        zza(str, i);
        return this.zze[i2].isNull(i, this.zzd.getInt(str));
    }
}
