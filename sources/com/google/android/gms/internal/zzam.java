package com.google.android.gms.internal;

import android.os.SystemClock;
import android.text.TextUtils;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public final class zzam implements zzb {
    private final Map<String, zzan> zza;
    private long zzb;
    private final File zzc;
    private final int zzd;

    public zzam(File file) {
        this(file, 5242880);
    }

    private zzam(File file, int i) {
        this.zza = new LinkedHashMap(16, 0.75f, true);
        this.zzb = 0;
        this.zzc = file;
        this.zzd = 5242880;
    }

    static int zza(InputStream inputStream) throws IOException {
        return zzc(inputStream) | 0 | (zzc(inputStream) << 8) | (zzc(inputStream) << 16) | (zzc(inputStream) << 24);
    }

    private static InputStream zza(File file) throws FileNotFoundException {
        return new FileInputStream(file);
    }

    static String zza(zzao zzao) throws IOException {
        return new String(zza(zzao, zzb((InputStream) zzao)), "UTF-8");
    }

    static void zza(OutputStream outputStream, int i) throws IOException {
        outputStream.write(i & 255);
        outputStream.write((i >> 8) & 255);
        outputStream.write((i >> 16) & 255);
        outputStream.write(i >>> 24);
    }

    static void zza(OutputStream outputStream, long j) throws IOException {
        outputStream.write((byte) ((int) j));
        outputStream.write((byte) ((int) (j >>> 8)));
        outputStream.write((byte) ((int) (j >>> 16)));
        outputStream.write((byte) ((int) (j >>> 24)));
        outputStream.write((byte) ((int) (j >>> 32)));
        outputStream.write((byte) ((int) (j >>> 40)));
        outputStream.write((byte) ((int) (j >>> 48)));
        outputStream.write((byte) ((int) (j >>> 56)));
    }

    static void zza(OutputStream outputStream, String str) throws IOException {
        byte[] bytes = str.getBytes("UTF-8");
        zza(outputStream, (long) bytes.length);
        outputStream.write(bytes, 0, bytes.length);
    }

    private final void zza(String str, zzan zzan) {
        if (!this.zza.containsKey(str)) {
            this.zzb += zzan.zza;
        } else {
            zzan zzan2 = (zzan) this.zza.get(str);
            this.zzb = (zzan.zza - zzan2.zza) + this.zzb;
        }
        this.zza.put(str, zzan);
    }

    private static byte[] zza(zzao zzao, long j) throws IOException {
        long zza2 = zzao.zza();
        if (j < 0 || j > zza2 || ((long) ((int) j)) != j) {
            throw new IOException("streamToBytes length=" + j + ", maxLength=" + zza2);
        }
        byte[] bArr = new byte[((int) j)];
        new DataInputStream(zzao).readFully(bArr);
        return bArr;
    }

    static long zzb(InputStream inputStream) throws IOException {
        return 0 | (((long) zzc(inputStream)) & 255) | ((((long) zzc(inputStream)) & 255) << 8) | ((((long) zzc(inputStream)) & 255) << 16) | ((((long) zzc(inputStream)) & 255) << 24) | ((((long) zzc(inputStream)) & 255) << 32) | ((((long) zzc(inputStream)) & 255) << 40) | ((((long) zzc(inputStream)) & 255) << 48) | ((((long) zzc(inputStream)) & 255) << 56);
    }

    static List<zzl> zzb(zzao zzao) throws IOException {
        int zza2 = zza((InputStream) zzao);
        List<zzl> arrayList = zza2 == 0 ? Collections.emptyList() : new ArrayList<>(zza2);
        for (int i = 0; i < zza2; i++) {
            arrayList.add(new zzl(zza(zzao).intern(), zza(zzao).intern()));
        }
        return arrayList;
    }

    private final synchronized void zzb(String str) {
        boolean delete = zzd(str).delete();
        zze(str);
        if (!delete) {
            zzaf.zzb("Could not delete cache entry for key=%s, filename=%s", str, zzc(str));
        }
    }

    private static int zzc(InputStream inputStream) throws IOException {
        int read = inputStream.read();
        if (read != -1) {
            return read;
        }
        throw new EOFException();
    }

    private static String zzc(String str) {
        int length = str.length() / 2;
        String valueOf = String.valueOf(String.valueOf(str.substring(0, length).hashCode()));
        String valueOf2 = String.valueOf(String.valueOf(str.substring(length).hashCode()));
        return valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
    }

    private final File zzd(String str) {
        return new File(this.zzc, zzc(str));
    }

    private final void zze(String str) {
        zzan zzan = (zzan) this.zza.remove(str);
        if (zzan != null) {
            this.zzb -= zzan.zza;
        }
    }

    public final synchronized zzc zza(String str) {
        zzc zzc2;
        zzao zzao;
        zzan zzan = (zzan) this.zza.get(str);
        if (zzan == null) {
            zzc2 = null;
        } else {
            File zzd2 = zzd(str);
            try {
                zzao = new zzao(new BufferedInputStream(zza(zzd2)), zzd2.length());
                zzan zza2 = zzan.zza(zzao);
                if (!TextUtils.equals(str, zza2.zzb)) {
                    zzaf.zzb("%s: key=%s, found=%s", zzd2.getAbsolutePath(), str, zza2.zzb);
                    zze(str);
                    zzao.close();
                    zzc2 = null;
                } else {
                    byte[] zza3 = zza(zzao, zzao.zza());
                    zzc zzc3 = new zzc();
                    zzc3.zza = zza3;
                    zzc3.zzb = zzan.zzc;
                    zzc3.zzc = zzan.zzd;
                    zzc3.zzd = zzan.zze;
                    zzc3.zze = zzan.zzf;
                    zzc3.zzf = zzan.zzg;
                    zzc3.zzg = zzap.zza(zzan.zzh);
                    zzc3.zzh = Collections.unmodifiableList(zzan.zzh);
                    zzao.close();
                    zzc2 = zzc3;
                }
            } catch (IOException e) {
                zzaf.zzb("%s: %s", zzd2.getAbsolutePath(), e.toString());
                zzb(str);
                zzc2 = null;
            } catch (Throwable th) {
                zzao.close();
                throw th;
            }
        }
        return zzc2;
    }

    public final synchronized void zza() {
        zzao zzao;
        if (this.zzc.exists()) {
            File[] listFiles = this.zzc.listFiles();
            if (listFiles != null) {
                for (File file : listFiles) {
                    try {
                        long length = file.length();
                        zzao = new zzao(new BufferedInputStream(zza(file)), length);
                        zzan zza2 = zzan.zza(zzao);
                        zza2.zza = length;
                        zza(zza2.zzb, zza2);
                        zzao.close();
                    } catch (IOException e) {
                        file.delete();
                    } catch (Throwable th) {
                        zzao.close();
                        throw th;
                    }
                }
            }
        } else if (!this.zzc.mkdirs()) {
            zzaf.zzc("Unable to create cache dir %s", this.zzc.getAbsolutePath());
        }
    }

    public final synchronized void zza(String str, zzc zzc2) {
        int i;
        int i2 = 0;
        synchronized (this) {
            int length = zzc2.zza.length;
            if (this.zzb + ((long) length) >= ((long) this.zzd)) {
                if (zzaf.zza) {
                    zzaf.zza("Pruning old cache entries.", new Object[0]);
                }
                long j = this.zzb;
                long elapsedRealtime = SystemClock.elapsedRealtime();
                Iterator it = this.zza.entrySet().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        i = i2;
                        break;
                    }
                    zzan zzan = (zzan) ((Entry) it.next()).getValue();
                    if (zzd(zzan.zzb).delete()) {
                        this.zzb -= zzan.zza;
                    } else {
                        zzaf.zzb("Could not delete cache entry for key=%s, filename=%s", zzan.zzb, zzc(zzan.zzb));
                    }
                    it.remove();
                    i = i2 + 1;
                    if (((float) (this.zzb + ((long) length))) < ((float) this.zzd) * 0.9f) {
                        break;
                    }
                    i2 = i;
                }
                if (zzaf.zza) {
                    zzaf.zza("pruned %d files, %d bytes, %d ms", Integer.valueOf(i), Long.valueOf(this.zzb - j), Long.valueOf(SystemClock.elapsedRealtime() - elapsedRealtime));
                }
            }
            File zzd2 = zzd(str);
            try {
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(zzd2));
                zzan zzan2 = new zzan(str, zzc2);
                if (!zzan2.zza((OutputStream) bufferedOutputStream)) {
                    bufferedOutputStream.close();
                    zzaf.zzb("Failed to write header for %s", zzd2.getAbsolutePath());
                    throw new IOException();
                }
                bufferedOutputStream.write(zzc2.zza);
                bufferedOutputStream.close();
                zza(str, zzan2);
            } catch (IOException e) {
                if (!zzd2.delete()) {
                    zzaf.zzb("Could not clean up file %s", zzd2.getAbsolutePath());
                }
            }
        }
    }
}
