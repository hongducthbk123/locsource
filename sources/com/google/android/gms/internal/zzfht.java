package com.google.android.gms.internal;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

final class zzfht implements zzfjb {
    private static final zzfht zza = new zzfht();
    private final Map<Class<?>, Method> zzb = new HashMap();

    private zzfht() {
    }

    public static zzfht zza() {
        return zza;
    }

    public final boolean zza(Class<?> cls) {
        return zzfhu.class.isAssignableFrom(cls);
    }

    public final zzfja zzb(Class<?> cls) {
        if (!zzfhu.class.isAssignableFrom(cls)) {
            String str = "Unsupported message type: ";
            String valueOf = String.valueOf(cls.getName());
            throw new IllegalArgumentException(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        }
        try {
            Method method = (Method) this.zzb.get(cls);
            if (method == null) {
                method = cls.getDeclaredMethod("buildMessageInfo", new Class[0]);
                method.setAccessible(true);
                this.zzb.put(cls, method);
            }
            return (zzfja) method.invoke(null, new Object[0]);
        } catch (Exception e) {
            Exception exc = e;
            String str2 = "Unable to get message info for ";
            String valueOf2 = String.valueOf(cls.getName());
            throw new RuntimeException(valueOf2.length() != 0 ? str2.concat(valueOf2) : new String(str2), exc);
        }
    }
}
