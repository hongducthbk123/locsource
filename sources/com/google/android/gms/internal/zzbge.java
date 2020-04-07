package com.google.android.gms.internal;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.Callback;
import android.graphics.drawable.Drawable.ConstantState;
import android.os.SystemClock;

public final class zzbge extends Drawable implements Callback {
    private int zza;
    private long zzb;
    private int zzc;
    private int zzd;
    private int zze;
    private int zzf;
    private int zzg;
    private boolean zzh;
    private boolean zzi;
    private zzbgi zzj;
    private Drawable zzk;
    private Drawable zzl;
    private boolean zzm;
    private boolean zzn;
    private boolean zzo;
    private int zzp;

    public zzbge(Drawable drawable, Drawable drawable2) {
        this(null);
        if (drawable == null) {
            drawable = zzbgg.zza;
        }
        this.zzk = drawable;
        drawable.setCallback(this);
        this.zzj.zzb |= drawable.getChangingConfigurations();
        if (drawable2 == null) {
            drawable2 = zzbgg.zza;
        }
        this.zzl = drawable2;
        drawable2.setCallback(this);
        this.zzj.zzb |= drawable2.getChangingConfigurations();
    }

    zzbge(zzbgi zzbgi) {
        this.zza = 0;
        this.zze = 255;
        this.zzg = 0;
        this.zzh = true;
        this.zzj = new zzbgi(zzbgi);
    }

    private final boolean zzb() {
        if (!this.zzm) {
            this.zzn = (this.zzk.getConstantState() == null || this.zzl.getConstantState() == null) ? false : true;
            this.zzm = true;
        }
        return this.zzn;
    }

    public final void draw(Canvas canvas) {
        boolean z = true;
        boolean z2 = false;
        switch (this.zza) {
            case 1:
                this.zzb = SystemClock.uptimeMillis();
                this.zza = 2;
                break;
            case 2:
                if (this.zzb >= 0) {
                    float uptimeMillis = ((float) (SystemClock.uptimeMillis() - this.zzb)) / ((float) this.zzf);
                    if (uptimeMillis < 1.0f) {
                        z = false;
                    }
                    if (z) {
                        this.zza = 0;
                    }
                    this.zzg = (int) ((Math.min(uptimeMillis, 1.0f) * ((float) this.zzd)) + 0.0f);
                    break;
                }
                break;
        }
        z2 = z;
        int i = this.zzg;
        boolean z3 = this.zzh;
        Drawable drawable = this.zzk;
        Drawable drawable2 = this.zzl;
        if (z2) {
            if (!z3 || i == 0) {
                drawable.draw(canvas);
            }
            if (i == this.zze) {
                drawable2.setAlpha(this.zze);
                drawable2.draw(canvas);
                return;
            }
            return;
        }
        if (z3) {
            drawable.setAlpha(this.zze - i);
        }
        drawable.draw(canvas);
        if (z3) {
            drawable.setAlpha(this.zze);
        }
        if (i > 0) {
            drawable2.setAlpha(i);
            drawable2.draw(canvas);
            drawable2.setAlpha(this.zze);
        }
        invalidateSelf();
    }

    public final int getChangingConfigurations() {
        return super.getChangingConfigurations() | this.zzj.zza | this.zzj.zzb;
    }

    public final ConstantState getConstantState() {
        if (!zzb()) {
            return null;
        }
        this.zzj.zza = getChangingConfigurations();
        return this.zzj;
    }

    public final int getIntrinsicHeight() {
        return Math.max(this.zzk.getIntrinsicHeight(), this.zzl.getIntrinsicHeight());
    }

    public final int getIntrinsicWidth() {
        return Math.max(this.zzk.getIntrinsicWidth(), this.zzl.getIntrinsicWidth());
    }

    public final int getOpacity() {
        if (!this.zzo) {
            this.zzp = Drawable.resolveOpacity(this.zzk.getOpacity(), this.zzl.getOpacity());
            this.zzo = true;
        }
        return this.zzp;
    }

    public final void invalidateDrawable(Drawable drawable) {
        Callback callback = getCallback();
        if (callback != null) {
            callback.invalidateDrawable(this);
        }
    }

    public final Drawable mutate() {
        if (!this.zzi && super.mutate() == this) {
            if (!zzb()) {
                throw new IllegalStateException("One or more children of this LayerDrawable does not have constant state; this drawable cannot be mutated.");
            }
            this.zzk.mutate();
            this.zzl.mutate();
            this.zzi = true;
        }
        return this;
    }

    /* access modifiers changed from: protected */
    public final void onBoundsChange(Rect rect) {
        this.zzk.setBounds(rect);
        this.zzl.setBounds(rect);
    }

    public final void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        Callback callback = getCallback();
        if (callback != null) {
            callback.scheduleDrawable(this, runnable, j);
        }
    }

    public final void setAlpha(int i) {
        if (this.zzg == this.zze) {
            this.zzg = i;
        }
        this.zze = i;
        invalidateSelf();
    }

    public final void setColorFilter(ColorFilter colorFilter) {
        this.zzk.setColorFilter(colorFilter);
        this.zzl.setColorFilter(colorFilter);
    }

    public final void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        Callback callback = getCallback();
        if (callback != null) {
            callback.unscheduleDrawable(this, runnable);
        }
    }

    public final Drawable zza() {
        return this.zzl;
    }

    public final void zza(int i) {
        this.zzc = 0;
        this.zzd = this.zze;
        this.zzg = 0;
        this.zzf = 250;
        this.zza = 1;
        invalidateSelf();
    }
}
