package com.google.android.gms.common.data;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.ParcelFileDescriptor.AutoCloseInputStream;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.Log;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

@Hide
public class BitmapTeleporter extends zzbgl implements ReflectedParcelable {
    public static final Creator<BitmapTeleporter> CREATOR = new zza();
    private int zza;
    private ParcelFileDescriptor zzb;
    private int zzc;
    private Bitmap zzd;
    private boolean zze;
    private File zzf;

    BitmapTeleporter(int i, ParcelFileDescriptor parcelFileDescriptor, int i2) {
        this.zza = i;
        this.zzb = parcelFileDescriptor;
        this.zzc = i2;
        this.zzd = null;
        this.zze = false;
    }

    public BitmapTeleporter(Bitmap bitmap) {
        this.zza = 1;
        this.zzb = null;
        this.zzc = 0;
        this.zzd = bitmap;
        this.zze = true;
    }

    private static void zza(Closeable closeable) {
        try {
            closeable.close();
        } catch (IOException e) {
            Log.w("BitmapTeleporter", "Could not close stream", e);
        }
    }

    private final FileOutputStream zzc() {
        if (this.zzf == null) {
            throw new IllegalStateException("setTempDir() must be called before writing this object to a parcel");
        }
        try {
            File createTempFile = File.createTempFile("teleporter", ".tmp", this.zzf);
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(createTempFile);
                this.zzb = ParcelFileDescriptor.open(createTempFile, 268435456);
                createTempFile.delete();
                return fileOutputStream;
            } catch (FileNotFoundException e) {
                throw new IllegalStateException("Temporary file is somehow already deleted");
            }
        } catch (IOException e2) {
            throw new IllegalStateException("Could not create temporary file", e2);
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (this.zzb == null) {
            Bitmap bitmap = this.zzd;
            ByteBuffer allocate = ByteBuffer.allocate(bitmap.getRowBytes() * bitmap.getHeight());
            bitmap.copyPixelsToBuffer(allocate);
            byte[] array = allocate.array();
            DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(zzc()));
            try {
                dataOutputStream.writeInt(array.length);
                dataOutputStream.writeInt(bitmap.getWidth());
                dataOutputStream.writeInt(bitmap.getHeight());
                dataOutputStream.writeUTF(bitmap.getConfig().toString());
                dataOutputStream.write(array);
                zza((Closeable) dataOutputStream);
            } catch (IOException e) {
                throw new IllegalStateException("Could not write into unlinked file", e);
            } catch (Throwable th) {
                zza((Closeable) dataOutputStream);
                throw th;
            }
        }
        int i2 = i | 1;
        int zza2 = zzbgo.zza(parcel);
        zzbgo.zza(parcel, 1, this.zza);
        zzbgo.zza(parcel, 2, (Parcelable) this.zzb, i2, false);
        zzbgo.zza(parcel, 3, this.zzc);
        zzbgo.zza(parcel, zza2);
        this.zzb = null;
    }

    public final Bitmap zza() {
        if (!this.zze) {
            DataInputStream dataInputStream = new DataInputStream(new AutoCloseInputStream(this.zzb));
            try {
                byte[] bArr = new byte[dataInputStream.readInt()];
                int readInt = dataInputStream.readInt();
                int readInt2 = dataInputStream.readInt();
                Config valueOf = Config.valueOf(dataInputStream.readUTF());
                dataInputStream.read(bArr);
                zza((Closeable) dataInputStream);
                ByteBuffer wrap = ByteBuffer.wrap(bArr);
                Bitmap createBitmap = Bitmap.createBitmap(readInt, readInt2, valueOf);
                createBitmap.copyPixelsFromBuffer(wrap);
                this.zzd = createBitmap;
                this.zze = true;
            } catch (IOException e) {
                throw new IllegalStateException("Could not read from parcel file descriptor", e);
            } catch (Throwable th) {
                zza((Closeable) dataInputStream);
                throw th;
            }
        }
        return this.zzd;
    }

    public final void zza(File file) {
        if (file == null) {
            throw new NullPointerException("Cannot set null temp directory");
        }
        this.zzf = file;
    }

    public final void zzb() {
        if (!this.zze) {
            try {
                this.zzb.close();
            } catch (IOException e) {
                Log.w("BitmapTeleporter", "Could not close PFD", e);
            }
        }
    }
}
