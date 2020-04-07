package p004cn.jiguang.p005a.p006a.p007a;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;

/* renamed from: cn.jiguang.a.a.a.d */
final class C0338d {

    /* renamed from: a */
    HandlerThread f35a = null;

    /* renamed from: b */
    Handler f36b = null;

    /* renamed from: c */
    private int f37c;

    /* renamed from: d */
    private String f38d;

    public C0338d(String str, int i) {
        this.f37c = i;
        this.f38d = str;
        this.f35a = new HandlerThread("ping timer");
        this.f35a.start();
        this.f36b = new Handler(this.f35a.getLooper(), new C0339e(this));
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public void m30b(byte[] bArr, int i, int i2) {
        boolean z = null;
        byte[] bArr2 = {bArr[0], bArr[1], bArr[2], 0};
        Thread currentThread = Thread.currentThread();
        while (i < i2) {
            bArr2[3] = (byte) i;
            if (bArr2[3] != bArr[3]) {
                try {
                    String a = C0337c.m23a(bArr2);
                    z = a.equalsIgnoreCase(this.f38d);
                    if (!z) {
                        this.f36b.removeCallbacksAndMessages(null);
                        Message obtainMessage = this.f36b.obtainMessage(1);
                        obtainMessage.obj = currentThread;
                        Bundle bundle = new Bundle();
                        bundle.putString("ip", a);
                        obtainMessage.setData(bundle);
                        this.f36b.sendMessageDelayed(obtainMessage, (long) this.f37c);
                        z = C0337c.m26b("ping -c 1 -w 1 " + a);
                        if (z != null) {
                            try {
                                z.exitValue();
                            } catch (Exception e) {
                                try {
                                    z.destroy();
                                } catch (Exception e2) {
                                }
                            }
                        }
                    } else if (z != null) {
                        try {
                            z.exitValue();
                        } catch (Exception e3) {
                            try {
                                z.destroy();
                            } catch (Exception e4) {
                            }
                        }
                    }
                } finally {
                    if (z != null) {
                        try {
                            z.exitValue();
                        } catch (Exception e5) {
                            try {
                                z.destroy();
                            } catch (Exception e6) {
                            }
                        }
                    }
                }
            }
            i++;
        }
    }

    /* renamed from: a */
    public final void mo6197a(byte[] bArr, int i, int i2) {
        m30b(bArr, 0, 255);
        this.f35a.quit();
    }
}
