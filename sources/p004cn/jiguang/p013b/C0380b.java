package p004cn.jiguang.p013b;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* renamed from: cn.jiguang.b.b */
public abstract class C0380b extends Binder implements C0379a {
    public C0380b() {
        attachInterface(this, "cn.jiguang.android.IDataShare");
    }

    /* renamed from: a */
    public static C0379a m216a(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("cn.jiguang.android.IDataShare");
        return (queryLocalInterface == null || !(queryLocalInterface instanceof C0379a)) ? new C0381c(iBinder) : (C0379a) queryLocalInterface;
    }

    public IBinder asBinder() {
        return this;
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        switch (i) {
            case 1:
                parcel.enforceInterface("cn.jiguang.android.IDataShare");
                boolean a = mo6341a();
                parcel2.writeNoException();
                parcel2.writeInt(a ? 1 : 0);
                return true;
            case 2:
                parcel.enforceInterface("cn.jiguang.android.IDataShare");
                IBinder a2 = mo6339a(parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                parcel2.writeStrongBinder(a2);
                return true;
            case 3:
                parcel.enforceInterface("cn.jiguang.android.IDataShare");
                mo6340a(parcel.readString(), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                parcel2.writeNoException();
                return true;
            case 1598968902:
                parcel2.writeString("cn.jiguang.android.IDataShare");
                return true;
            default:
                return super.onTransact(i, parcel, parcel2, i2);
        }
    }
}
