package com.google.android.gms.common.data;

import com.google.android.gms.common.internal.Hide;
import java.util.ArrayList;

@Hide
public abstract class zzg<T> extends AbstractDataBuffer<T> {
    private boolean zzb = false;
    private ArrayList<Integer> zzc;

    protected zzg(DataHolder dataHolder) {
        super(dataHolder);
    }

    private final int zza(int i) {
        if (i >= 0 && i < this.zzc.size()) {
            return ((Integer) this.zzc.get(i)).intValue();
        }
        throw new IllegalArgumentException("Position " + i + " is out of bounds for this buffer");
    }

    private final void zzc() {
        synchronized (this) {
            if (!this.zzb) {
                int i = this.zza.zza;
                this.zzc = new ArrayList<>();
                if (i > 0) {
                    this.zzc.add(Integer.valueOf(0));
                    String zzb2 = zzb();
                    String zzc2 = this.zza.zzc(zzb2, 0, this.zza.zza(0));
                    int i2 = 1;
                    while (i2 < i) {
                        int zza = this.zza.zza(i2);
                        String zzc3 = this.zza.zzc(zzb2, i2, zza);
                        if (zzc3 == null) {
                            throw new NullPointerException(new StringBuilder(String.valueOf(zzb2).length() + 78).append("Missing value for markerColumn: ").append(zzb2).append(", at row: ").append(i2).append(", for window: ").append(zza).toString());
                        }
                        if (!zzc3.equals(zzc2)) {
                            this.zzc.add(Integer.valueOf(i2));
                        } else {
                            zzc3 = zzc2;
                        }
                        i2++;
                        zzc2 = zzc3;
                    }
                }
                this.zzb = true;
            }
        }
    }

    public final T get(int i) {
        int i2;
        zzc();
        int zza = zza(i);
        if (i < 0 || i == this.zzc.size()) {
            i2 = 0;
        } else {
            i2 = i == this.zzc.size() + -1 ? this.zza.zza - ((Integer) this.zzc.get(i)).intValue() : ((Integer) this.zzc.get(i + 1)).intValue() - ((Integer) this.zzc.get(i)).intValue();
            if (i2 == 1) {
                this.zza.zza(zza(i));
            }
        }
        return zza(zza, i2);
    }

    public int getCount() {
        zzc();
        return this.zzc.size();
    }

    /* access modifiers changed from: protected */
    @Hide
    public abstract T zza(int i, int i2);

    /* access modifiers changed from: protected */
    @Hide
    public abstract String zzb();
}
