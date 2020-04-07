package p004cn.jpush.android.service;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import p004cn.jpush.android.C0541a;
import p004cn.jpush.android.api.C0559a;
import p004cn.jpush.android.api.JPushInterface.C0558a;
import p004cn.jpush.android.api.JPushMessage;
import p004cn.jpush.android.p040d.C0582e;

/* renamed from: cn.jpush.android.service.d */
public final class C0607d {

    /* renamed from: a */
    private static volatile C0607d f892a;

    /* renamed from: d */
    private static final Object f893d = new Object();

    /* renamed from: b */
    private TagAliasReceiver f894b;

    /* renamed from: c */
    private ConcurrentHashMap<Long, C0559a> f895c = new ConcurrentHashMap<>();

    /* renamed from: e */
    private AtomicBoolean f896e = new AtomicBoolean(false);

    /* renamed from: a */
    public static C0607d m1397a() {
        if (f892a == null) {
            synchronized (f893d) {
                if (f892a == null) {
                    f892a = new C0607d();
                }
            }
        }
        return f892a;
    }

    private C0607d() {
    }

    /* renamed from: a */
    public final void mo7001a(Context context, Long l, C0559a aVar) {
        m1402b(context);
        this.f895c.put(l, aVar);
    }

    /* renamed from: a */
    private C0559a m1396a(long j) {
        return (C0559a) this.f895c.get(Long.valueOf(j));
    }

    /* renamed from: b */
    private void m1401b(long j) {
        this.f895c.remove(Long.valueOf(j));
    }

    /* renamed from: b */
    private void m1402b(Context context) {
        if (this.f895c != null && !this.f895c.isEmpty()) {
            ArrayList arrayList = new ArrayList();
            for (Entry entry : this.f895c.entrySet()) {
                if (((C0559a) entry.getValue()).mo6826a(20000)) {
                    arrayList.add(entry.getKey());
                }
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                m1399a(context, ((Long) it.next()).longValue());
            }
        }
    }

    /* renamed from: a */
    public final synchronized void mo6998a(Context context) {
        if (!this.f896e.get()) {
            try {
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addCategory(C0541a.f651c);
                intentFilter.addAction("cn.jpush.android.intent.TAG_ALIAS_TIMEOUT");
                intentFilter.addAction("cn.jpush.android.intent.TAG_ALIAS_CALLBACK");
                if (this.f894b == null) {
                    this.f894b = new TagAliasReceiver();
                }
                context.registerReceiver(this.f894b, intentFilter);
                this.f896e.set(true);
            } catch (Exception e) {
            }
        }
    }

    /* renamed from: c */
    private synchronized void m1403c(Context context) {
        m1402b(context);
        if (this.f896e.get() && this.f895c != null && this.f895c.isEmpty()) {
            try {
                if (this.f894b != null) {
                    context.unregisterReceiver(this.f894b);
                    this.f894b = null;
                }
            } catch (IllegalArgumentException e) {
                C0582e.m1305b("TagAliasOperator", "Receiver not registered, cannot call unregisterReceiver", e);
            } catch (Exception e2) {
                C0582e.m1305b("TagAliasOperator", "other exception", e2);
            }
            this.f896e.set(false);
        }
        return;
    }

    /* renamed from: a */
    public final void mo7000a(Context context, long j, int i, Intent intent) {
        if ("cn.jpush.android.intent.TAG_ALIAS_TIMEOUT".equals(intent.getAction())) {
            m1399a(context, j);
        } else {
            C0559a a = m1396a(j);
            if (a != null) {
                m1397a().m1401b(j);
                if (intent != null) {
                    try {
                        if (a.f734f == 5) {
                            if (a.f733e == 1) {
                                ArrayList stringArrayListExtra = intent.getStringArrayListExtra("tags");
                                if (stringArrayListExtra != null) {
                                    a.f730b = new HashSet(stringArrayListExtra);
                                }
                            } else if (a.f733e == 2) {
                                a.f729a = intent.getStringExtra("alias");
                            }
                        } else if (a.f734f == 6) {
                            intent.getBooleanExtra("validated", false);
                        }
                    } catch (Throwable th) {
                    }
                }
                m1400a(a, i);
            }
        }
        m1403c(context);
    }

    /* renamed from: a */
    public final JPushMessage mo6997a(Intent intent) {
        boolean z = false;
        long longExtra = intent.getLongExtra("tagalias_seqid", -1);
        int intExtra = intent.getIntExtra("tagalias_errorcode", 0);
        C0559a a = m1396a(longExtra);
        if (a == null) {
            return null;
        }
        m1397a().m1401b(longExtra);
        if (intExtra == 0) {
            try {
                if (a.f734f == 5) {
                    if (a.f733e == 1) {
                        ArrayList stringArrayListExtra = intent.getStringArrayListExtra("tags");
                        if (stringArrayListExtra != null) {
                            a.f730b = new HashSet(stringArrayListExtra);
                        }
                    } else if (a.f733e == 2) {
                        a.f729a = intent.getStringExtra("alias");
                    }
                } else if (a.f734f == 6) {
                    z = intent.getBooleanExtra("validated", false);
                }
            } catch (Throwable th) {
            }
        }
        JPushMessage jPushMessage = new JPushMessage();
        jPushMessage.setErrorCode(intExtra);
        jPushMessage.setSequence(a.f732d);
        if (a.f733e != 1) {
            jPushMessage.setAlias(a.f729a);
        } else if (a.f734f == 6) {
            jPushMessage.setCheckTag(m1398a(a));
            jPushMessage.setTagCheckStateResult(z);
            jPushMessage.setTagCheckOperator(true);
        } else {
            jPushMessage.setTags(a.f730b);
        }
        return jPushMessage;
    }

    /* renamed from: a */
    private void m1399a(Context context, long j) {
        C0559a a = m1396a(j);
        if (a != null) {
            m1400a(a, C0558a.f697c);
            m1401b(j);
        }
    }

    /* renamed from: a */
    private static void m1400a(C0559a aVar, int i) {
        if (aVar.f733e == 0 && aVar.f731c != null) {
            aVar.f731c.gotResult(i, aVar.f729a, aVar.f730b);
        }
    }

    /* renamed from: a */
    private static String m1398a(C0559a aVar) {
        if (aVar == null) {
            return null;
        }
        try {
            if (aVar.f730b != null && aVar.f730b.size() > 0) {
                return (String) aVar.f730b.toArray()[0];
            }
        } catch (Throwable th) {
        }
        return null;
    }

    /* renamed from: a */
    public final void mo6999a(Context context, int i, long j, C0559a aVar) {
        if (aVar == null) {
            return;
        }
        if (aVar.f733e != 0) {
            try {
                Intent intent = new Intent();
                intent.addCategory(C0541a.f651c);
                intent.setAction("cn.jpush.android.intent.RECEIVE_MESSAGE");
                intent.setPackage(context.getPackageName());
                if (aVar.f733e == 1) {
                    intent.putExtra("message_type", 1);
                } else {
                    intent.putExtra("message_type", 2);
                }
                intent.putExtra("tagalias_errorcode", i);
                intent.putExtra("tagalias_seqid", j);
                context.sendBroadcast(intent);
            } catch (Throwable th) {
                C0582e.m1306c("TagAliasOperator", "onTagaliasTimeout error:" + th.getMessage());
            }
        } else if (aVar.f731c != null) {
            aVar.f731c.gotResult(i, aVar.f729a, aVar.f730b);
            m1401b(j);
        }
    }
}
