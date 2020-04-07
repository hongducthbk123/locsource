package p004cn.jpush.android.p037a;

import android.util.SparseArray;
import org.cocos2dx.lib.GameControllerDelegate;

/* renamed from: cn.jpush.android.a.j */
public final class C0554j {

    /* renamed from: a */
    private static final SparseArray<String> f675a;

    static {
        SparseArray<String> sparseArray = new SparseArray<>();
        f675a = sparseArray;
        sparseArray.put(995, "Message JSON parsing succeed");
        f675a.put(996, "Message JSON parsing failed");
        f675a.put(997, "Message already received, give up");
        f675a.put(998, "Message already received, still process");
        f675a.put(1000, "User clicked and opened the Message");
        f675a.put(1001, "Message download succeed");
        f675a.put(1002, "Message received succeed");
        f675a.put(1003, "Message silence download succeed");
        f675a.put(1004, "Video silence downlaod succeed");
        f675a.put(1005, "User clicked video and jumped to url Message (browser)");
        f675a.put(1008, "Video is force closed by user");
        f675a.put(1007, "User clicked 'OK'");
        f675a.put(1006, "User clicked 'Cancel'");
        f675a.put(1011, "Download failed");
        f675a.put(1012, "User clicked to download again");
        f675a.put(1013, "The file already exist and same size. Don't download again.");
        f675a.put(1100, "Invalid param or unexpected result.");
        f675a.put(1014, "Failed to preload required resource");
        f675a.put(1015, "User clicked install alert on status bar after downloading finished.");
        f675a.put(1016, "User clicked the webview's url");
        f675a.put(1017, "User clicked call action");
        f675a.put(1018, "The Message show in the status bar");
        f675a.put(1019, "Click applist and show the Message");
        f675a.put(1020, "Down image failed");
        f675a.put(1021, "Down html failed");
        f675a.put(GameControllerDelegate.BUTTON_SELECT, "Down Message failed");
        f675a.put(1030, "Discard the message because it is not in the push time");
        f675a.put(1031, "Stop push service");
        f675a.put(1032, "Resume push service");
    }

    /* renamed from: a */
    public static String m1146a(int i) {
        if (f675a.get(i) == null) {
            return "";
        }
        return (String) f675a.get(i);
    }
}
