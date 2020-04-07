package com.tencent.bugly.proguard;

import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

/* renamed from: com.tencent.bugly.proguard.ae */
/* compiled from: BUGLY */
public final class C1972ae implements C1973af {

    /* renamed from: a */
    private String f1452a = null;

    /* renamed from: a */
    public final byte[] mo19547a(byte[] bArr) throws Exception {
        if (this.f1452a == null || bArr == null) {
            return null;
        }
        Cipher instance = Cipher.getInstance("DES/CBC/PKCS5Padding");
        instance.init(2, SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(this.f1452a.getBytes("UTF-8"))), new IvParameterSpec(this.f1452a.getBytes("UTF-8")));
        return instance.doFinal(bArr);
    }

    /* renamed from: b */
    public final byte[] mo19548b(byte[] bArr) throws Exception, NoSuchAlgorithmException {
        if (this.f1452a == null || bArr == null) {
            return null;
        }
        Cipher instance = Cipher.getInstance("DES/CBC/PKCS5Padding");
        instance.init(1, SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(this.f1452a.getBytes("UTF-8"))), new IvParameterSpec(this.f1452a.getBytes("UTF-8")));
        return instance.doFinal(bArr);
    }

    /* renamed from: a */
    public final void mo19546a(String str) {
        if (str != null) {
            this.f1452a = str;
        }
    }
}
