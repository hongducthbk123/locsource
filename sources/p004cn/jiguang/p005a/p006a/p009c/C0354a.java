package p004cn.jiguang.p005a.p006a.p009c;

import android.content.Context;
import java.io.Closeable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;
import p004cn.jiguang.p015d.p016a.C0389d;
import p004cn.jiguang.p015d.p021d.C0460q;
import p004cn.jiguang.p031g.C0526g;
import p004cn.jiguang.p031g.C0528i;
import p004cn.jiguang.p031g.C0530k;

/* renamed from: cn.jiguang.a.a.c.a */
public final class C0354a extends Thread {

    /* renamed from: c */
    private static final Object f108c = new Object();

    /* renamed from: d */
    private static final Object f109d = new Object();

    /* renamed from: a */
    private String f110a;

    /* renamed from: b */
    private Context f111b;

    public C0354a(Context context, String str) {
        this.f111b = context;
        this.f110a = str;
    }

    /* renamed from: a */
    public static String m93a(Context context) {
        FileInputStream fileInputStream;
        Throwable th;
        if (context == null) {
            return null;
        }
        try {
            fileInputStream = context.openFileInput("appPackageNames_v2");
            try {
                byte[] bArr = new byte[(fileInputStream.available() + 1)];
                fileInputStream.read(bArr);
                C0526g.m1087a((Closeable) fileInputStream);
                try {
                    String str = new String(bArr, "UTF-8");
                    if (!C0530k.m1099a(str)) {
                        return str;
                    }
                    return null;
                } catch (UnsupportedEncodingException e) {
                    return null;
                }
            } catch (FileNotFoundException e2) {
                C0526g.m1087a((Closeable) fileInputStream);
                return null;
            } catch (IOException e3) {
                C0526g.m1087a((Closeable) fileInputStream);
                return null;
            } catch (Throwable th2) {
                th = th2;
                C0526g.m1087a((Closeable) fileInputStream);
                throw th;
            }
        } catch (FileNotFoundException e4) {
            fileInputStream = null;
        } catch (IOException e5) {
            fileInputStream = null;
            C0526g.m1087a((Closeable) fileInputStream);
            return null;
        } catch (Throwable th3) {
            Throwable th4 = th3;
            fileInputStream = null;
            th = th4;
            C0526g.m1087a((Closeable) fileInputStream);
            throw th;
        }
    }

    /* renamed from: a */
    public static void m94a(Context context, ArrayList<C0361h> arrayList) {
        if (arrayList != null && !arrayList.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 < arrayList.size()) {
                    sb.append(((C0361h) arrayList.get(i2)).f122b);
                    if (i2 != arrayList.size() - 1) {
                        sb.append("&&");
                    }
                    i = i2 + 1;
                } else {
                    m96a(context, sb.toString());
                    return;
                }
            }
        }
    }

    /* renamed from: a */
    private void m95a(HashSet<String> hashSet) {
        if (this.f111b != null && hashSet != null && !hashSet.isEmpty()) {
            JSONArray jSONArray = new JSONArray();
            ArrayList a = C0357d.m105a(this.f111b, true);
            ArrayList arrayList = new ArrayList();
            arrayList.addAll(a);
            Iterator it = a.iterator();
            while (it.hasNext()) {
                C0361h hVar = (C0361h) it.next();
                if (hashSet.remove(hVar.f122b)) {
                    arrayList.remove(hVar);
                }
                if (!C0530k.m1099a(this.f110a) && hVar.f122b.equals(this.f110a)) {
                    arrayList.remove(hVar);
                }
            }
            if (!C0530k.m1099a(this.f110a)) {
                hashSet.remove(this.f110a);
            }
            Iterator it2 = hashSet.iterator();
            while (it2.hasNext()) {
                JSONObject a2 = C0528i.m1095a((String) it2.next());
                if (a2 != null) {
                    jSONArray.put(a2);
                }
            }
            Iterator it3 = arrayList.iterator();
            while (it3.hasNext()) {
                C0361h hVar2 = (C0361h) it3.next();
                JSONObject a3 = C0528i.m1096a(hVar2.f122b, hVar2.f125e);
                if (a3 != null) {
                    jSONArray.put(a3);
                }
            }
            if (jSONArray.length() > 0) {
                C0460q.m711a(this.f111b, jSONArray);
            }
            if (jSONArray.length() > 0 || !C0530k.m1099a(this.f110a)) {
                m94a(this.f111b, a);
            }
        }
    }

    /* renamed from: a */
    private static boolean m96a(Context context, String str) {
        if (context == null) {
            return false;
        }
        synchronized (f109d) {
            if (str == null) {
                return false;
            }
            FileOutputStream fileOutputStream = null;
            try {
                fileOutputStream = context.openFileOutput("appPackageNames_v2", 0);
                fileOutputStream.write(str.getBytes("UTF-8"));
                return true;
            } catch (FileNotFoundException e) {
                return false;
            } catch (UnsupportedEncodingException e2) {
                return false;
            } catch (IOException e3) {
                return false;
            } catch (NullPointerException e4) {
                return false;
            } finally {
                C0526g.m1087a((Closeable) fileOutputStream);
            }
        }
    }

    public final void run() {
        HashSet hashSet;
        try {
            synchronized (f108c) {
                if (this.f111b != null) {
                    String a = m93a(this.f111b);
                    if (a != null) {
                        C0389d.m313a(this.f111b, "last_check_userapp_status", Long.valueOf(System.currentTimeMillis()));
                        if (a == null) {
                            hashSet = null;
                        } else {
                            String[] split = a.replace("\u0000", "").split("&&");
                            hashSet = new HashSet();
                            Collections.addAll(hashSet, split);
                        }
                        m95a(hashSet);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
