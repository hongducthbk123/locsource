package p004cn.jiguang.p005a.p006a.p008b;

import android.content.Context;
import android.os.Build.VERSION;
import android.telephony.CellIdentityCdma;
import android.telephony.CellIdentityGsm;
import android.telephony.CellIdentityLte;
import android.telephony.CellIdentityWcdma;
import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellInfoWcdma;
import android.telephony.CellLocation;
import android.telephony.CellSignalStrengthCdma;
import android.telephony.CellSignalStrengthGsm;
import android.telephony.CellSignalStrengthLte;
import android.telephony.CellSignalStrengthWcdma;
import android.telephony.NeighboringCellInfo;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import com.facebook.places.model.PlaceFields;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p004cn.jiguang.p015d.p016a.C0386a;
import p004cn.jiguang.p031g.C0532m;

/* renamed from: cn.jiguang.a.a.b.a */
public final class C0345a {

    /* renamed from: a */
    private int f58a = -1;

    /* renamed from: b */
    private int f59b = -1;

    /* renamed from: c */
    private String f60c = "";

    /* renamed from: d */
    private String f61d = "";

    /* renamed from: e */
    private String f62e = "";

    /* renamed from: f */
    private TelephonyManager f63f = null;

    /* renamed from: g */
    private Context f64g = null;
    /* access modifiers changed from: private */

    /* renamed from: h */
    public int f65h = 0;

    /* renamed from: i */
    private C0346b f66i;

    /* renamed from: j */
    private C0350f f67j;

    /* renamed from: k */
    private JSONArray f68k = null;

    public C0345a(Context context, C0350f fVar) {
        this.f64g = context;
        try {
            this.f63f = (TelephonyManager) context.getSystemService(PlaceFields.PHONE);
            this.f67j = fVar;
        } catch (Exception e) {
        }
    }

    /* renamed from: a */
    private static int m44a(String str) {
        try {
            if (str.length() <= 6) {
                return Integer.parseInt(str.substring(3, str.length()));
            }
            return -1;
        } catch (Exception e) {
            return -1;
        }
    }

    /* renamed from: a */
    private JSONObject m45a(int i, int i2, int i3) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("mobile_network_code", this.f59b);
            jSONObject.put("cell_id", i2);
            jSONObject.put("radio_type", this.f60c);
            jSONObject.put("signal_strength", i);
            jSONObject.put("mobile_country_code", this.f58a);
            jSONObject.put("carrier", this.f62e);
            jSONObject.put("location_area_code", i3);
            jSONObject.put("generation", this.f61d);
            jSONObject.put("itime", C0386a.m293t());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    /* renamed from: a */
    private JSONObject m46a(int i, int i2, int i3, int i4) {
        if (i2 < 268435455 && (i4 == 0 || i4 == 3)) {
            return m45a(i, i2, i3);
        }
        if (i2 >= 65535 || (i4 != 1 && i4 != 2)) {
            return null;
        }
        return m45a(i, i2, i3);
    }

    /* renamed from: a */
    static /* synthetic */ void m47a(C0345a aVar, JSONArray jSONArray) {
        CellLocation cellLocation;
        try {
            cellLocation = aVar.f63f.getCellLocation();
        } catch (Exception e) {
            e.printStackTrace();
            cellLocation = null;
        }
        if (cellLocation != null) {
            try {
                if (cellLocation instanceof GsmCellLocation) {
                    GsmCellLocation gsmCellLocation = (GsmCellLocation) cellLocation;
                    jSONArray.put(aVar.m45a(aVar.f65h, gsmCellLocation.getCid(), gsmCellLocation.getLac()));
                } else if (cellLocation instanceof CdmaCellLocation) {
                    CdmaCellLocation cdmaCellLocation = (CdmaCellLocation) cellLocation;
                    jSONArray.put(aVar.m45a(aVar.f65h, cdmaCellLocation.getBaseStationId(), cdmaCellLocation.getNetworkId()));
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            List<NeighboringCellInfo> neighboringCellInfo = aVar.f63f.getNeighboringCellInfo();
            if (neighboringCellInfo != null) {
                for (NeighboringCellInfo neighboringCellInfo2 : neighboringCellInfo) {
                    int rssi = (neighboringCellInfo2.getRssi() * 2) - 113;
                    int cid = neighboringCellInfo2.getCid();
                    int lac = neighboringCellInfo2.getLac();
                    if (cid < 65535) {
                        jSONArray.put(aVar.m45a(rssi, cid, lac));
                    }
                }
            }
            aVar.m48a(jSONArray);
            aVar.m49e();
        }
    }

    /* renamed from: a */
    private void m48a(JSONArray jSONArray) {
        if (jSONArray != null) {
            JSONArray jSONArray2 = new JSONArray();
            int i = 0;
            while (i < jSONArray.length()) {
                try {
                    JSONObject jSONObject = jSONArray.getJSONObject(i);
                    if (!jSONArray2.toString().contains(jSONObject.toString())) {
                        jSONArray2.put(jSONObject);
                    }
                    i++;
                } catch (JSONException e) {
                    e.printStackTrace();
                    return;
                }
            }
            this.f68k = jSONArray2;
        }
    }

    /* renamed from: e */
    private void m49e() {
        if (this.f67j != null) {
            this.f67j.mo6222a();
        }
    }

    /* renamed from: f */
    private void m50f() {
        try {
            this.f66i = new C0346b(this);
            this.f63f.listen(this.f66i, 256);
        } catch (Exception e) {
            m49e();
        }
    }

    /* renamed from: a */
    public final void mo6203a() {
        CellLocation cellLocation;
        List<CellInfo> list;
        if (this.f67j == null) {
            m49e();
            return;
        }
        try {
            cellLocation = this.f63f.getCellLocation();
        } catch (Exception e) {
            cellLocation = null;
        }
        if (cellLocation == null) {
            m49e();
            return;
        }
        this.f58a = -1;
        this.f59b = -1;
        this.f60c = "";
        this.f61d = "";
        this.f62e = "";
        this.f62e = this.f63f.getNetworkOperatorName();
        int networkType = this.f63f.getNetworkType();
        String str = (networkType == 4 || networkType == 7 || networkType == 5 || networkType == 6 || networkType == 12 || networkType == 14) ? "cdma" : networkType == 13 ? "lte" : "gsm";
        this.f60c = str;
        this.f61d = C0532m.m1108a(this.f64g, this.f63f.getNetworkType());
        try {
            String networkOperator = this.f63f.getNetworkOperator();
            if (networkOperator.length() > 3) {
                this.f58a = Integer.parseInt(networkOperator.substring(0, 3));
                this.f59b = m44a(networkOperator);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        JSONArray jSONArray = new JSONArray();
        if (VERSION.SDK_INT > 17) {
            try {
                list = this.f63f.getAllCellInfo();
            } catch (Exception e3) {
                list = null;
            }
            if (list == null || list.size() == 0) {
                m50f();
                return;
            }
            for (CellInfo cellInfo : list) {
                if (cellInfo != null) {
                    if (cellInfo instanceof CellInfoLte) {
                        CellInfoLte cellInfoLte = (CellInfoLte) cellInfo;
                        if (VERSION.SDK_INT > 17) {
                            CellSignalStrengthLte cellSignalStrength = cellInfoLte.getCellSignalStrength();
                            CellIdentityLte cellIdentity = cellInfoLte.getCellIdentity();
                            JSONObject a = m46a(cellSignalStrength.getDbm(), cellIdentity.getCi(), cellIdentity.getTac(), 0);
                            if (a != null) {
                                jSONArray.put(a);
                            }
                        }
                    } else if (cellInfo instanceof CellInfoGsm) {
                        CellInfoGsm cellInfoGsm = (CellInfoGsm) cellInfo;
                        if (VERSION.SDK_INT > 17) {
                            CellSignalStrengthGsm cellSignalStrength2 = cellInfoGsm.getCellSignalStrength();
                            CellIdentityGsm cellIdentity2 = cellInfoGsm.getCellIdentity();
                            JSONObject a2 = m46a(cellSignalStrength2.getDbm(), cellIdentity2.getCid(), cellIdentity2.getLac(), 1);
                            if (a2 != null) {
                                jSONArray.put(a2);
                            }
                        }
                    } else if (cellInfo instanceof CellInfoCdma) {
                        CellInfoCdma cellInfoCdma = (CellInfoCdma) cellInfo;
                        if (VERSION.SDK_INT > 17) {
                            CellSignalStrengthCdma cellSignalStrength3 = cellInfoCdma.getCellSignalStrength();
                            CellIdentityCdma cellIdentity3 = cellInfoCdma.getCellIdentity();
                            JSONObject a3 = m46a(cellSignalStrength3.getDbm(), cellIdentity3.getBasestationId(), cellIdentity3.getNetworkId(), 2);
                            if (a3 != null) {
                                jSONArray.put(a3);
                            }
                        }
                    } else if (cellInfo instanceof CellInfoWcdma) {
                        CellInfoWcdma cellInfoWcdma = (CellInfoWcdma) cellInfo;
                        if (VERSION.SDK_INT > 17) {
                            CellSignalStrengthWcdma cellSignalStrength4 = cellInfoWcdma.getCellSignalStrength();
                            CellIdentityWcdma cellIdentity4 = cellInfoWcdma.getCellIdentity();
                            JSONObject a4 = m46a(cellSignalStrength4.getDbm(), cellIdentity4.getCid(), cellIdentity4.getLac(), 3);
                            if (a4 != null) {
                                jSONArray.put(a4);
                            }
                        }
                    }
                }
            }
            m48a(jSONArray);
            m49e();
            return;
        }
        m50f();
    }

    /* renamed from: b */
    public final JSONArray mo6204b() {
        return this.f68k;
    }

    /* renamed from: c */
    public final void mo6205c() {
        this.f68k = null;
    }

    /* renamed from: d */
    public final void mo6206d() {
        try {
            if (this.f63f != null && this.f66i != null) {
                this.f63f.listen(this.f66i, 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
