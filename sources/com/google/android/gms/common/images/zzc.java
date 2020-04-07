package com.google.android.gms.common.images;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.internal.zzbge;
import com.google.android.gms.internal.zzbgj;
import java.lang.ref.WeakReference;

public final class zzc extends zza {
    private WeakReference<ImageView> zzc;

    public zzc(ImageView imageView, int i) {
        super(null, i);
        com.google.android.gms.common.internal.zzc.zza((Object) imageView);
        this.zzc = new WeakReference<>(imageView);
    }

    public zzc(ImageView imageView, Uri uri) {
        super(uri, 0);
        com.google.android.gms.common.internal.zzc.zza((Object) imageView);
        this.zzc = new WeakReference<>(imageView);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzc)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        ImageView imageView = (ImageView) this.zzc.get();
        ImageView imageView2 = (ImageView) ((zzc) obj).zzc.get();
        return (imageView2 == null || imageView == null || !zzbg.zza(imageView2, imageView)) ? false : true;
    }

    public final int hashCode() {
        return 0;
    }

    /* access modifiers changed from: protected */
    public final void zza(Drawable drawable, boolean z, boolean z2, boolean z3) {
        Drawable drawable2;
        Uri uri = null;
        ImageView imageView = (ImageView) this.zzc.get();
        if (imageView != null) {
            boolean z4 = !z2 && !z3;
            if (z4 && (imageView instanceof zzbgj)) {
                int zza = zzbgj.zza();
                if (this.zzb != 0 && zza == this.zzb) {
                    return;
                }
            }
            boolean zza2 = zza(z, z2);
            if (zza2) {
                Drawable drawable3 = imageView.getDrawable();
                if (drawable3 == null) {
                    drawable3 = null;
                } else if (drawable3 instanceof zzbge) {
                    drawable3 = ((zzbge) drawable3).zza();
                }
                drawable2 = new zzbge(drawable3, drawable);
            } else {
                drawable2 = drawable;
            }
            imageView.setImageDrawable(drawable2);
            if (imageView instanceof zzbgj) {
                if (z3) {
                    uri = this.zza.zza;
                }
                zzbgj.zza(uri);
                zzbgj.zza(z4 ? this.zzb : 0);
            }
            if (zza2) {
                ((zzbge) drawable2).zza(250);
            }
        }
    }
}
