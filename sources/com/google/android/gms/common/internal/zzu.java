package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p000v4.util.SimpleArrayMap;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.C1209R;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.util.zzj;
import com.google.android.gms.internal.zzbih;

public final class zzu {
    private static final SimpleArrayMap<String, String> zza = new SimpleArrayMap<>();

    public static String zza(Context context) {
        return context.getResources().getString(C1209R.string.common_google_play_services_notification_channel_name);
    }

    @Nullable
    public static String zza(Context context, int i) {
        Resources resources = context.getResources();
        switch (i) {
            case 1:
                return resources.getString(C1209R.string.common_google_play_services_install_title);
            case 2:
                return resources.getString(C1209R.string.common_google_play_services_update_title);
            case 3:
                return resources.getString(C1209R.string.common_google_play_services_enable_title);
            case 4:
            case 6:
            case 18:
                return null;
            case 5:
                Log.e("GoogleApiAvailability", "An invalid account was specified when connecting. Please provide a valid account.");
                return zza(context, "common_google_play_services_invalid_account_title");
            case 7:
                Log.e("GoogleApiAvailability", "Network error occurred. Please retry request later.");
                return zza(context, "common_google_play_services_network_error_title");
            case 8:
                Log.e("GoogleApiAvailability", "Internal error occurred. Please see logs for detailed information");
                return null;
            case 9:
                Log.e("GoogleApiAvailability", "Google Play services is invalid. Cannot recover.");
                return null;
            case 10:
                Log.e("GoogleApiAvailability", "Developer error occurred. Please see logs for detailed information");
                return null;
            case 11:
                Log.e("GoogleApiAvailability", "The application is not licensed to the user.");
                return null;
            case 16:
                Log.e("GoogleApiAvailability", "One of the API components you attempted to connect to is not available.");
                return null;
            case 17:
                Log.e("GoogleApiAvailability", "The specified account could not be signed in.");
                return zza(context, "common_google_play_services_sign_in_failed_title");
            case 20:
                Log.e("GoogleApiAvailability", "The current user profile is restricted and could not use authenticated features.");
                return zza(context, "common_google_play_services_restricted_profile_title");
            default:
                Log.e("GoogleApiAvailability", "Unexpected error code " + i);
                return null;
        }
    }

    @Nullable
    private static String zza(Context context, String str) {
        synchronized (zza) {
            String str2 = (String) zza.get(str);
            if (str2 != null) {
                return str2;
            }
            Resources remoteResource = GooglePlayServicesUtil.getRemoteResource(context);
            if (remoteResource == null) {
                return null;
            }
            int identifier = remoteResource.getIdentifier(str, "string", "com.google.android.gms");
            if (identifier == 0) {
                String str3 = "GoogleApiAvailability";
                String str4 = "Missing resource: ";
                String valueOf = String.valueOf(str);
                Log.w(str3, valueOf.length() != 0 ? str4.concat(valueOf) : new String(str4));
                return null;
            }
            String string = remoteResource.getString(identifier);
            if (TextUtils.isEmpty(string)) {
                String str5 = "GoogleApiAvailability";
                String str6 = "Got empty resource: ";
                String valueOf2 = String.valueOf(str);
                Log.w(str5, valueOf2.length() != 0 ? str6.concat(valueOf2) : new String(str6));
                return null;
            }
            zza.put(str, string);
            return string;
        }
    }

    private static String zza(Context context, String str, String str2) {
        Resources resources = context.getResources();
        String zza2 = zza(context, str);
        if (zza2 == null) {
            zza2 = resources.getString(C1209R.string.common_google_play_services_unknown_issue);
        }
        return String.format(resources.getConfiguration().locale, zza2, new Object[]{str2});
    }

    private static String zzb(Context context) {
        String packageName = context.getPackageName();
        try {
            return zzbih.zza(context).zzb(packageName).toString();
        } catch (NameNotFoundException | NullPointerException e) {
            String str = context.getApplicationInfo().name;
            return !TextUtils.isEmpty(str) ? str : packageName;
        }
    }

    @NonNull
    public static String zzb(Context context, int i) {
        String zza2 = i == 6 ? zza(context, "common_google_play_services_resolution_required_title") : zza(context, i);
        return zza2 == null ? context.getResources().getString(C1209R.string.common_google_play_services_notification_ticker) : zza2;
    }

    @NonNull
    public static String zzc(Context context, int i) {
        Resources resources = context.getResources();
        String zzb = zzb(context);
        switch (i) {
            case 1:
                return resources.getString(C1209R.string.common_google_play_services_install_text, new Object[]{zzb});
            case 2:
                if (zzj.zzb(context)) {
                    return resources.getString(C1209R.string.common_google_play_services_wear_update_text);
                }
                return resources.getString(C1209R.string.common_google_play_services_update_text, new Object[]{zzb});
            case 3:
                return resources.getString(C1209R.string.common_google_play_services_enable_text, new Object[]{zzb});
            case 5:
                return zza(context, "common_google_play_services_invalid_account_text", zzb);
            case 7:
                return zza(context, "common_google_play_services_network_error_text", zzb);
            case 9:
                return resources.getString(C1209R.string.common_google_play_services_unsupported_text, new Object[]{zzb});
            case 16:
                return zza(context, "common_google_play_services_api_unavailable_text", zzb);
            case 17:
                return zza(context, "common_google_play_services_sign_in_failed_text", zzb);
            case 18:
                return resources.getString(C1209R.string.common_google_play_services_updating_text, new Object[]{zzb});
            case 20:
                return zza(context, "common_google_play_services_restricted_profile_text", zzb);
            default:
                return resources.getString(C1209R.string.common_google_play_services_unknown_issue, new Object[]{zzb});
        }
    }

    @NonNull
    public static String zzd(Context context, int i) {
        return i == 6 ? zza(context, "common_google_play_services_resolution_required_text", zzb(context)) : zzc(context, i);
    }

    @NonNull
    public static String zze(Context context, int i) {
        Resources resources = context.getResources();
        switch (i) {
            case 1:
                return resources.getString(C1209R.string.common_google_play_services_install_button);
            case 2:
                return resources.getString(C1209R.string.common_google_play_services_update_button);
            case 3:
                return resources.getString(C1209R.string.common_google_play_services_enable_button);
            default:
                return resources.getString(17039370);
        }
    }
}
