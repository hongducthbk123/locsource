package com.google.android.gms.dynamic;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.zzu;
import com.google.android.gms.common.zzf;
import com.google.android.gms.dynamic.LifecycleDelegate;
import java.util.LinkedList;

@Hide
public abstract class zza<T extends LifecycleDelegate> {
    /* access modifiers changed from: private */
    public T zza;
    /* access modifiers changed from: private */
    public Bundle zzb;
    /* access modifiers changed from: private */
    public LinkedList<zzi> zzc;
    private final zzo<T> zzd = new zzb(this);

    private final void zza(int i) {
        while (!this.zzc.isEmpty() && ((zzi) this.zzc.getLast()).zza() >= i) {
            this.zzc.removeLast();
        }
    }

    private final void zza(Bundle bundle, zzi zzi) {
        if (this.zza != null) {
            zzi.zza(this.zza);
            return;
        }
        if (this.zzc == null) {
            this.zzc = new LinkedList<>();
        }
        this.zzc.add(zzi);
        if (bundle != null) {
            if (this.zzb == null) {
                this.zzb = (Bundle) bundle.clone();
            } else {
                this.zzb.putAll(bundle);
            }
        }
        zza(this.zzd);
    }

    public static void zzb(FrameLayout frameLayout) {
        GoogleApiAvailability instance = GoogleApiAvailability.getInstance();
        Context context = frameLayout.getContext();
        int isGooglePlayServicesAvailable = instance.isGooglePlayServicesAvailable(context);
        String zzc2 = zzu.zzc(context, isGooglePlayServicesAvailable);
        String zze = zzu.zze(context, isGooglePlayServicesAvailable);
        LinearLayout linearLayout = new LinearLayout(frameLayout.getContext());
        linearLayout.setOrientation(1);
        linearLayout.setLayoutParams(new LayoutParams(-2, -2));
        frameLayout.addView(linearLayout);
        TextView textView = new TextView(frameLayout.getContext());
        textView.setLayoutParams(new LayoutParams(-2, -2));
        textView.setText(zzc2);
        linearLayout.addView(textView);
        Intent zza2 = zzf.zza(context, isGooglePlayServicesAvailable, null);
        if (zza2 != null) {
            Button button = new Button(context);
            button.setId(16908313);
            button.setLayoutParams(new LayoutParams(-2, -2));
            button.setText(zze);
            linearLayout.addView(button);
            button.setOnClickListener(new zzf(context, zza2));
        }
    }

    public final View zza(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        FrameLayout frameLayout = new FrameLayout(layoutInflater.getContext());
        zza(bundle, (zzi) new zze(this, frameLayout, layoutInflater, viewGroup, bundle));
        if (this.zza == null) {
            zza(frameLayout);
        }
        return frameLayout;
    }

    public final T zza() {
        return this.zza;
    }

    public final void zza(Activity activity, Bundle bundle, Bundle bundle2) {
        zza(bundle2, (zzi) new zzc(this, activity, bundle, bundle2));
    }

    public final void zza(Bundle bundle) {
        zza(bundle, (zzi) new zzd(this, bundle));
    }

    /* access modifiers changed from: protected */
    public void zza(FrameLayout frameLayout) {
        GoogleApiAvailability instance = GoogleApiAvailability.getInstance();
        Context context = frameLayout.getContext();
        int isGooglePlayServicesAvailable = instance.isGooglePlayServicesAvailable(context);
        String zzc2 = zzu.zzc(context, isGooglePlayServicesAvailable);
        String zze = zzu.zze(context, isGooglePlayServicesAvailable);
        LinearLayout linearLayout = new LinearLayout(frameLayout.getContext());
        linearLayout.setOrientation(1);
        linearLayout.setLayoutParams(new LayoutParams(-2, -2));
        frameLayout.addView(linearLayout);
        TextView textView = new TextView(frameLayout.getContext());
        textView.setLayoutParams(new LayoutParams(-2, -2));
        textView.setText(zzc2);
        linearLayout.addView(textView);
        Intent zza2 = zzf.zza(context, isGooglePlayServicesAvailable, null);
        if (zza2 != null) {
            Button button = new Button(context);
            button.setId(16908313);
            button.setLayoutParams(new LayoutParams(-2, -2));
            button.setText(zze);
            linearLayout.addView(button);
            button.setOnClickListener(new zzf(context, zza2));
        }
    }

    /* access modifiers changed from: protected */
    public abstract void zza(zzo<T> zzo);

    public final void zzb() {
        zza((Bundle) null, (zzi) new zzg(this));
    }

    public final void zzb(Bundle bundle) {
        if (this.zza != null) {
            this.zza.onSaveInstanceState(bundle);
        } else if (this.zzb != null) {
            bundle.putAll(this.zzb);
        }
    }

    public final void zzc() {
        zza((Bundle) null, (zzi) new zzh(this));
    }

    public final void zzd() {
        if (this.zza != null) {
            this.zza.onPause();
        } else {
            zza(5);
        }
    }

    public final void zze() {
        if (this.zza != null) {
            this.zza.onStop();
        } else {
            zza(4);
        }
    }

    public final void zzf() {
        if (this.zza != null) {
            this.zza.onDestroyView();
        } else {
            zza(2);
        }
    }

    public final void zzg() {
        if (this.zza != null) {
            this.zza.onDestroy();
        } else {
            zza(1);
        }
    }

    public final void zzh() {
        if (this.zza != null) {
            this.zza.onLowMemory();
        }
    }
}
