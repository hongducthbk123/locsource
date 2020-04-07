package p004cn.jiguang.p005a.p006a.p008b;

import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import org.json.JSONArray;

/* renamed from: cn.jiguang.a.a.b.b */
final class C0346b extends PhoneStateListener {

    /* renamed from: a */
    final /* synthetic */ C0345a f69a;

    public C0346b(C0345a aVar) {
        this.f69a = aVar;
    }

    public final void onSignalStrengthsChanged(SignalStrength signalStrength) {
        super.onSignalStrengthsChanged(signalStrength);
        this.f69a.f65h = signalStrength.getGsmSignalStrength();
        C0345a.m47a(this.f69a, new JSONArray());
        this.f69a.mo6206d();
    }
}
