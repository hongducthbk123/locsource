package com.google.android.vending.licensing;

import com.google.android.vending.licensing.util.Base64;
import com.google.android.vending.licensing.util.Base64DecoderException;
import com.google.common.base.Ascii;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class AESObfuscator implements Obfuscator {
    private static final String CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";

    /* renamed from: IV */
    private static final byte[] f958IV = {Ascii.DLE, 74, 71, -80, 32, 101, -47, 72, 117, -14, 0, -29, 70, 65, -12, 74};
    private static final String KEYGEN_ALGORITHM = "PBEWITHSHAAND256BITAES-CBC-BC";
    private static final String UTF8 = "UTF-8";
    private static final String header = "com.android.vending.licensing.AESObfuscator-1|";
    private Cipher mDecryptor;
    private Cipher mEncryptor;

    public AESObfuscator(byte[] salt, String applicationId, String deviceId) {
        try {
            SecretKeySpec secret = new SecretKeySpec(SecretKeyFactory.getInstance(KEYGEN_ALGORITHM).generateSecret(new PBEKeySpec((applicationId + deviceId).toCharArray(), salt, 1024, 256)).getEncoded(), "AES");
            this.mEncryptor = Cipher.getInstance(CIPHER_ALGORITHM);
            this.mEncryptor.init(1, secret, new IvParameterSpec(f958IV));
            this.mDecryptor = Cipher.getInstance(CIPHER_ALGORITHM);
            this.mDecryptor.init(2, secret, new IvParameterSpec(f958IV));
        } catch (GeneralSecurityException var8) {
            throw new RuntimeException("Invalid environment", var8);
        }
    }

    public String obfuscate(String original, String key) {
        if (original == null) {
            return null;
        }
        try {
            return Base64.encode(this.mEncryptor.doFinal((header + key + original).getBytes("UTF-8")));
        } catch (UnsupportedEncodingException var4) {
            throw new RuntimeException("Invalid environment", var4);
        } catch (GeneralSecurityException var5) {
            throw new RuntimeException("Invalid environment", var5);
        }
    }

    public String unobfuscate(String obfuscated, String key) throws ValidationException {
        if (obfuscated == null) {
            return null;
        }
        try {
            String e = new String(this.mDecryptor.doFinal(Base64.decode(obfuscated)), "UTF-8");
            if (e.indexOf(header + key) == 0) {
                return e.substring(header.length() + key.length(), e.length());
            }
            throw new ValidationException("Header not found (invalid data or key):" + obfuscated);
        } catch (Base64DecoderException var5) {
            throw new ValidationException(var5.getMessage() + ":" + obfuscated);
        } catch (IllegalBlockSizeException var6) {
            throw new ValidationException(var6.getMessage() + ":" + obfuscated);
        } catch (BadPaddingException var7) {
            throw new ValidationException(var7.getMessage() + ":" + obfuscated);
        } catch (UnsupportedEncodingException var8) {
            throw new RuntimeException("Invalid environment", var8);
        }
    }
}
