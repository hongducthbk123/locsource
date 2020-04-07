package p004cn.jpush.android.p040d;

import android.content.Context;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import p004cn.jpush.android.data.C0590c;

/* renamed from: cn.jpush.android.d.f */
public final class C0583f {

    /* renamed from: a */
    public static Queue<C0590c> f768a = null;

    /* renamed from: a */
    private static synchronized <T> void m1310a(Context context, String str, ArrayList<T> arrayList) {
        synchronized (C0583f.class) {
            if (!(context == null || arrayList == null)) {
                try {
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(context.openFileOutput(str, 0));
                    objectOutputStream.writeObject(arrayList);
                    objectOutputStream.close();
                } catch (FileNotFoundException e) {
                    C0582e.m1306c("MsgQueueUtils", "save Objects FileNotFoundException error:" + e.getMessage());
                } catch (IOException e2) {
                    C0582e.m1306c("MsgQueueUtils", "save Objects IOException error:" + e2.getMessage());
                }
            }
        }
        return;
    }

    /* renamed from: a */
    private static synchronized void m1309a(Context context, String str) {
        synchronized (C0583f.class) {
            if (context != null) {
                File file = new File(context.getFilesDir(), str);
                if (file.exists()) {
                    file.delete();
                }
            }
        }
    }

    /* JADX WARNING: type inference failed for: r0v0 */
    /* JADX WARNING: type inference failed for: r1v0, types: [java.util.ArrayList] */
    /* JADX WARNING: type inference failed for: r2v0, types: [java.io.ObjectInputStream] */
    /* JADX WARNING: type inference failed for: r2v1 */
    /* JADX WARNING: type inference failed for: r0v4, types: [java.io.ObjectInputStream] */
    /* JADX WARNING: type inference failed for: r2v2 */
    /* JADX WARNING: type inference failed for: r0v6 */
    /* JADX WARNING: type inference failed for: r0v8 */
    /* JADX WARNING: type inference failed for: r0v9 */
    /* JADX WARNING: type inference failed for: r2v4, types: [java.io.ObjectInputStream] */
    /* JADX WARNING: type inference failed for: r0v11 */
    /* JADX WARNING: type inference failed for: r0v14, types: [java.util.ArrayList] */
    /* JADX WARNING: type inference failed for: r0v15, types: [java.util.ArrayList<T>] */
    /* JADX WARNING: type inference failed for: r2v5 */
    /* JADX WARNING: type inference failed for: r0v16 */
    /* JADX WARNING: type inference failed for: r0v17 */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r0v0
      assigns: [?[int, float, boolean, short, byte, char, OBJECT, ARRAY], ?[OBJECT, ARRAY], java.util.ArrayList]
      uses: [java.util.ArrayList<T>, ?[OBJECT, ARRAY], ?[int, boolean, OBJECT, ARRAY, byte, short, char], java.io.ObjectInputStream]
      mth insns count: 39
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
    	at jadx.core.ProcessClass.process(ProcessClass.java:35)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0028 A[SYNTHETIC, Splitter:B:18:0x0028] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0035 A[SYNTHETIC, Splitter:B:26:0x0035] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0047  */
    /* JADX WARNING: Unknown variable types count: 5 */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static synchronized <T> java.util.ArrayList<T> m1312b(android.content.Context r5, java.lang.String r6) {
        /*
            r0 = 0
            java.lang.Class<cn.jpush.android.d.f> r3 = p004cn.jpush.android.p040d.C0583f.class
            monitor-enter(r3)
            if (r5 != 0) goto L_0x0008
        L_0x0006:
            monitor-exit(r3)
            return r0
        L_0x0008:
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ all -> 0x0039 }
            r1.<init>()     // Catch:{ all -> 0x0039 }
            java.io.ObjectInputStream r2 = new java.io.ObjectInputStream     // Catch:{ Exception -> 0x0022, all -> 0x0030 }
            java.io.FileInputStream r4 = r5.openFileInput(r6)     // Catch:{ Exception -> 0x0022, all -> 0x0030 }
            r2.<init>(r4)     // Catch:{ Exception -> 0x0022, all -> 0x0030 }
            java.lang.Object r0 = r2.readObject()     // Catch:{ Exception -> 0x0044, all -> 0x003e }
            java.util.ArrayList r0 = (java.util.ArrayList) r0     // Catch:{ Exception -> 0x0044, all -> 0x003e }
            r2.close()     // Catch:{ IOException -> 0x0020 }
            goto L_0x0006
        L_0x0020:
            r1 = move-exception
            goto L_0x0006
        L_0x0022:
            r2 = move-exception
        L_0x0023:
            m1309a(r5, r6)     // Catch:{ all -> 0x0040 }
            if (r0 == 0) goto L_0x0047
            r0.close()     // Catch:{ IOException -> 0x002d }
            r0 = r1
            goto L_0x0006
        L_0x002d:
            r0 = move-exception
            r0 = r1
            goto L_0x0006
        L_0x0030:
            r1 = move-exception
            r2 = r0
            r0 = r1
        L_0x0033:
            if (r2 == 0) goto L_0x0038
            r2.close()     // Catch:{ IOException -> 0x003c }
        L_0x0038:
            throw r0     // Catch:{ all -> 0x0039 }
        L_0x0039:
            r0 = move-exception
            monitor-exit(r3)
            throw r0
        L_0x003c:
            r1 = move-exception
            goto L_0x0038
        L_0x003e:
            r0 = move-exception
            goto L_0x0033
        L_0x0040:
            r1 = move-exception
            r2 = r0
            r0 = r1
            goto L_0x0033
        L_0x0044:
            r0 = move-exception
            r0 = r2
            goto L_0x0023
        L_0x0047:
            r0 = r1
            goto L_0x0006
        */
        throw new UnsupportedOperationException("Method not decompiled: p004cn.jpush.android.p040d.C0583f.m1312b(android.content.Context, java.lang.String):java.util.ArrayList");
    }

    /* renamed from: a */
    public static boolean m1311a(Context context, C0590c cVar) {
        if (f768a == null) {
            f768a = new ConcurrentLinkedQueue();
            try {
                ArrayList b = m1312b(context, "msg_queue");
                if (b != null && !b.isEmpty()) {
                    Iterator it = b.iterator();
                    while (it.hasNext()) {
                        f768a.offer((C0590c) it.next());
                    }
                }
            } catch (Exception e) {
                C0582e.m1306c("MsgQueueUtils", "init lastMsgQueue failed:" + e.getMessage());
            }
        }
        if (context == null) {
            C0582e.m1306c("MsgQueueUtils", "#unexcepted - context was null");
            return false;
        }
        if (cVar == null) {
            C0582e.m1306c("MsgQueueUtils", "#unexcepted - entityKey was null");
        }
        if (f768a.contains(cVar)) {
            return true;
        }
        if (f768a.size() >= 200) {
            f768a.poll();
        }
        f768a.offer(cVar);
        try {
            ArrayList b2 = m1312b(context, "msg_queue");
            if (b2 == null) {
                b2 = new ArrayList();
            }
            if (b2.size() >= 50) {
                b2.remove(0);
            }
            b2.add(cVar);
            m1310a(context, "msg_queue", b2);
        } catch (Exception e2) {
            C0582e.m1306c("MsgQueueUtils", "msg save in sp failed:" + e2.getMessage());
        }
        return false;
    }
}
