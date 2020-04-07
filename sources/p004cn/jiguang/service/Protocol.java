package p004cn.jiguang.service;

/* renamed from: cn.jiguang.service.Protocol */
public class Protocol {
    static {
        try {
            System.loadLibrary("jcore120");
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static native int GetSdkVersion();

    public static native String getCerTificate();
}
