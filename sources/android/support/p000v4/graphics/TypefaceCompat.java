package android.support.p000v4.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Build.VERSION;
import android.os.CancellationSignal;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.p000v4.content.res.FontResourcesParserCompat.FamilyResourceEntry;
import android.support.p000v4.content.res.FontResourcesParserCompat.FontFamilyFilesResourceEntry;
import android.support.p000v4.content.res.FontResourcesParserCompat.ProviderResourceEntry;
import android.support.p000v4.provider.FontsContractCompat;
import android.support.p000v4.provider.FontsContractCompat.FontInfo;
import android.support.p000v4.util.LruCache;
import android.widget.TextView;
import com.google.android.vending.expansion.downloader.Constants;

@RestrictTo({Scope.LIBRARY_GROUP})
/* renamed from: android.support.v4.graphics.TypefaceCompat */
public class TypefaceCompat {
    private static final String TAG = "TypefaceCompat";
    private static final LruCache<String, Typeface> sTypefaceCache = new LruCache<>(16);
    private static final TypefaceCompatImpl sTypefaceCompatImpl;

    /* renamed from: android.support.v4.graphics.TypefaceCompat$TypefaceCompatImpl */
    interface TypefaceCompatImpl {
        Typeface createFromFontFamilyFilesResourceEntry(Context context, FontFamilyFilesResourceEntry fontFamilyFilesResourceEntry, Resources resources, int i);

        Typeface createFromFontInfo(Context context, @Nullable CancellationSignal cancellationSignal, @NonNull FontInfo[] fontInfoArr, int i);

        Typeface createFromResourcesFontFile(Context context, Resources resources, int i, String str, int i2);
    }

    static {
        if (VERSION.SDK_INT >= 26) {
            sTypefaceCompatImpl = new TypefaceCompatApi26Impl();
        } else if (VERSION.SDK_INT >= 24 && TypefaceCompatApi24Impl.isUsable()) {
            sTypefaceCompatImpl = new TypefaceCompatApi24Impl();
        } else if (VERSION.SDK_INT >= 21) {
            sTypefaceCompatImpl = new TypefaceCompatApi21Impl();
        } else {
            sTypefaceCompatImpl = new TypefaceCompatBaseImpl();
        }
    }

    private TypefaceCompat() {
    }

    public static Typeface findFromCache(Resources resources, int id, int style) {
        return (Typeface) sTypefaceCache.get(createResourceUid(resources, id, style));
    }

    private static String createResourceUid(Resources resources, int id, int style) {
        return resources.getResourcePackageName(id) + Constants.FILENAME_SEQUENCE_SEPARATOR + id + Constants.FILENAME_SEQUENCE_SEPARATOR + style;
    }

    public static Typeface createFromResourcesFamilyXml(Context context, FamilyResourceEntry entry, Resources resources, int id, int style, @Nullable TextView targetView) {
        Typeface typeface;
        if (entry instanceof ProviderResourceEntry) {
            ProviderResourceEntry providerEntry = (ProviderResourceEntry) entry;
            typeface = FontsContractCompat.getFontSync(context, providerEntry.getRequest(), targetView, providerEntry.getFetchStrategy(), providerEntry.getTimeout(), style);
        } else {
            typeface = sTypefaceCompatImpl.createFromFontFamilyFilesResourceEntry(context, (FontFamilyFilesResourceEntry) entry, resources, style);
        }
        if (typeface != null) {
            sTypefaceCache.put(createResourceUid(resources, id, style), typeface);
        }
        return typeface;
    }

    @Nullable
    public static Typeface createFromResourcesFontFile(Context context, Resources resources, int id, String path, int style) {
        Typeface typeface = sTypefaceCompatImpl.createFromResourcesFontFile(context, resources, id, path, style);
        if (typeface != null) {
            sTypefaceCache.put(createResourceUid(resources, id, style), typeface);
        }
        return typeface;
    }

    public static Typeface createFromFontInfo(Context context, @Nullable CancellationSignal cancellationSignal, @NonNull FontInfo[] fonts, int style) {
        return sTypefaceCompatImpl.createFromFontInfo(context, cancellationSignal, fonts, style);
    }
}
