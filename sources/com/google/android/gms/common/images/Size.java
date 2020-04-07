package com.google.android.gms.common.images;

public final class Size {
    private final int zza;
    private final int zzb;

    public Size(int i, int i2) {
        this.zza = i;
        this.zzb = i2;
    }

    public static Size parseSize(String str) throws NumberFormatException {
        if (str == null) {
            throw new IllegalArgumentException("string must not be null");
        }
        int indexOf = str.indexOf(42);
        if (indexOf < 0) {
            indexOf = str.indexOf(120);
        }
        if (indexOf < 0) {
            throw zza(str);
        }
        try {
            return new Size(Integer.parseInt(str.substring(0, indexOf)), Integer.parseInt(str.substring(indexOf + 1)));
        } catch (NumberFormatException e) {
            throw zza(str);
        }
    }

    private static NumberFormatException zza(String str) {
        throw new NumberFormatException(new StringBuilder(String.valueOf(str).length() + 16).append("Invalid Size: \"").append(str).append("\"").toString());
    }

    public final boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Size)) {
            return false;
        }
        Size size = (Size) obj;
        return this.zza == size.zza && this.zzb == size.zzb;
    }

    public final int getHeight() {
        return this.zzb;
    }

    public final int getWidth() {
        return this.zza;
    }

    public final int hashCode() {
        return this.zzb ^ ((this.zza << 16) | (this.zza >>> 16));
    }

    public final String toString() {
        int i = this.zza;
        return i + "x" + this.zzb;
    }
}
