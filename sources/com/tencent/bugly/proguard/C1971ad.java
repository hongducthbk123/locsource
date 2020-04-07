package com.tencent.bugly.proguard;

import com.facebook.appevents.AppEventsConstants;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* renamed from: com.tencent.bugly.proguard.ad */
/* compiled from: BUGLY */
public final class C1971ad implements C1973af {

    /* renamed from: a */
    private String f1451a = null;

    /* renamed from: a */
    public final byte[] mo19547a(byte[] bArr) throws Exception {
        if (this.f1451a == null || bArr == null) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        int length = bArr.length;
        for (int i = 0; i < length; i++) {
            stringBuffer.append(bArr[i] + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        }
        SecretKeySpec secretKeySpec = new SecretKeySpec(this.f1451a.getBytes(), "AES");
        Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
        instance.init(2, secretKeySpec, new IvParameterSpec(this.f1451a.getBytes()));
        byte[] doFinal = instance.doFinal(bArr);
        StringBuffer stringBuffer2 = new StringBuffer();
        int length2 = doFinal.length;
        for (int i2 = 0; i2 < length2; i2++) {
            stringBuffer2.append(doFinal[i2] + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        }
        return doFinal;
    }

    /* renamed from: b */
    public final byte[] mo19548b(byte[] bArr) throws Exception, NoSuchAlgorithmException {
        if (this.f1451a == null || bArr == null) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        int length = bArr.length;
        for (int i = 0; i < length; i++) {
            stringBuffer.append(bArr[i] + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        }
        SecretKeySpec secretKeySpec = new SecretKeySpec(this.f1451a.getBytes(), "AES");
        Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
        instance.init(1, secretKeySpec, new IvParameterSpec(this.f1451a.getBytes()));
        byte[] doFinal = instance.doFinal(bArr);
        StringBuffer stringBuffer2 = new StringBuffer();
        int length2 = doFinal.length;
        for (int i2 = 0; i2 < length2; i2++) {
            stringBuffer2.append(doFinal[i2] + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        }
        return doFinal;
    }

    /* renamed from: a */
    public final void mo19546a(String str) {
        if (str != null) {
            for (int length = str.length(); length < 16; length++) {
                str = str + AppEventsConstants.EVENT_PARAM_VALUE_NO;
            }
            this.f1451a = str.substring(0, 16);
        }
    }
}
