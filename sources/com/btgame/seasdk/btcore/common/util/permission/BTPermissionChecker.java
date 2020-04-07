package com.btgame.seasdk.btcore.common.util.permission;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.p000v4.app.ActivityCompat;
import android.support.p000v4.content.PermissionChecker;
import android.support.p000v4.util.ArrayMap;
import android.text.Html;
import android.text.Spanned;
import com.btgame.seasdk.btcore.common.constant.EventType;
import com.btgame.seasdk.btcore.common.event.SdkCheckPermisionEvent;
import com.btgame.seasdk.btcore.common.event.SdkEventManager;
import com.btgame.seasdk.btcore.common.util.BTResourceUtil;
import com.btgame.seasdk.btcore.common.util.BtsdkLog;
import com.btgame.seasdk.btcore.common.util.ContextUtil;
import com.btgame.seasdk.btcore.common.util.SharedPreferencesUtils;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class BTPermissionChecker {
    public static final int CODE_ALLPERMISSIONDENIED = -1;
    public static final int CODE_ALLPERMISSIONGRANTED = 0;
    public static final int CODE_SOMEPERMISSIONDENIED = 1;
    public static final String KEY_PERMISSION_HASREQ = "key_permission_hasreq";
    private boolean permissionForceAgree = BTResourceUtil.findBoolByName("permission_force_agree");
    private ArrayMap<Integer, WeakReference<PermissionListener>> requesters = new ArrayMap<>();
    /* access modifiers changed from: private */
    public boolean toSetting = false;

    private static class BTPermissionCheckerHolder {
        /* access modifiers changed from: private */
        public static BTPermissionChecker btPermissionChecker = new BTPermissionChecker();

        private BTPermissionCheckerHolder() {
        }
    }

    public interface PermissionListener {
        void onCheckCallback(String[] strArr, boolean[] zArr);
    }

    public static BTPermissionChecker getInstance() {
        return BTPermissionCheckerHolder.btPermissionChecker;
    }

    public boolean isPermissionForceAgree() {
        return this.permissionForceAgree;
    }

    public void registerToEventManager() {
        SdkEventManager.register(this);
    }

    public boolean check(Context context, String permission) {
        return PermissionChecker.checkSelfPermission(context, permission) == 0;
    }

    public void requestPermission(Activity activity, String[] permissions, PermissionListener requester) {
        int code = requester.hashCode() % 65536;
        this.requesters.put(Integer.valueOf(code), new WeakReference(requester));
        ActivityCompat.requestPermissions(activity, permissions, code);
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (!this.requesters.containsKey(Integer.valueOf(requestCode))) {
            return;
        }
        if (((WeakReference) this.requesters.get(Integer.valueOf(requestCode))).get() == null) {
            this.requesters.remove(Integer.valueOf(requestCode));
            return;
        }
        boolean[] results = new boolean[grantResults.length];
        for (int i = 0; i < permissions.length; i++) {
            results[i] = grantResults[i] == 0;
        }
        ((PermissionListener) ((WeakReference) this.requesters.get(Integer.valueOf(requestCode))).get()).onCheckCallback(permissions, results);
        this.requesters.remove(Integer.valueOf(requestCode));
    }

    @TargetApi(23)
    public String[] findDeniedPermissions(Context context) {
        String[] strArr;
        List<String> denyPermissions = new ArrayList<>();
        PackageManager pm = context.getPackageManager();
        try {
            PackageInfo packageInfo = pm.getPackageInfo(context.getPackageName(), 4096);
            if (packageInfo.requestedPermissions != null) {
                for (String permission : packageInfo.requestedPermissions) {
                    try {
                        switch (pm.getPermissionInfo(permission, 0).protectionLevel & 15) {
                            case 1:
                                if (check(context, permission)) {
                                    break;
                                } else {
                                    denyPermissions.add(permission);
                                    break;
                                }
                        }
                    } catch (NameNotFoundException e) {
                        BtsdkLog.m1429d("ignored unknown permission");
                    }
                }
            }
        } catch (Exception e2) {
            BtsdkLog.m1430e(e2.getLocalizedMessage());
        }
        return (String[]) denyPermissions.toArray(new String[denyPermissions.size()]);
    }

    public boolean shouldShowRequestPermissionRationale(Activity activity, String[] denyPermissions) {
        for (String denyPermission : denyPermissions) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, denyPermission)) {
                return true;
            }
        }
        return false;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSdkCheckPermisionEvent(SdkCheckPermisionEvent sdkCheckPermisionEvent) {
        if (EventType.CHECK_PERMISSIONS_REQ.equals(sdkCheckPermisionEvent.getEventType())) {
            checkPermission(sdkCheckPermisionEvent.getActivity());
        }
    }

    /* access modifiers changed from: private */
    public void checkPermission(final Activity activity) {
        final String[] permissions = getInstance().findDeniedPermissions(ContextUtil.getApplicationContext());
        if (permissions.length > 0) {
            if (!SharedPreferencesUtils.getBoolean(KEY_PERMISSION_HASREQ)) {
                this.toSetting = false;
            } else if (getInstance().shouldShowRequestPermissionRationale(activity, permissions)) {
                this.toSetting = false;
            } else if (!isPermissionForceAgree()) {
                onSomePermissionDenied(activity);
                return;
            } else {
                this.toSetting = true;
            }
            String gameName = BTResourceUtil.getAppName();
            Spanned reqTitle = Html.fromHtml(String.format(BTResourceUtil.findStringByName("dl_req_permission_title"), new Object[]{gameName}));
            Spanned btnCancel = Html.fromHtml(BTResourceUtil.findStringByName("dl_btn_permission_cancel"));
            Spanned btnOk = Html.fromHtml(BTResourceUtil.findStringByName("dl_btn_permission_ok"));
            String reqPath = String.format(BTResourceUtil.findStringByName("dl_req_permission_path"), new Object[]{gameName});
            StringBuilder sb = new StringBuilder();
            for (String permission : permissions) {
                if (sb.length() > 0) {
                    sb.append("<br />");
                }
                sb.append(BTResourceUtil.findStringByName(permission + "_name"));
                if (!this.toSetting) {
                    sb.append(BTResourceUtil.findStringByName(permission + "_des"));
                }
            }
            if (this.toSetting) {
                sb.append("<br />");
                sb.append(reqPath);
            }
            AlertDialog alertDialog = new Builder(activity).setTitle(reqTitle).setMessage(Html.fromHtml(sb.toString())).setNegativeButton(btnCancel, new OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    if (!BTPermissionChecker.this.isPermissionForceAgree()) {
                        BTPermissionChecker.this.onSomePermissionDenied(activity);
                    } else {
                        BTPermissionChecker.this.onAllPermissionDenied(activity);
                    }
                }
            }).setPositiveButton(btnOk, new OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    if (BTPermissionChecker.this.toSetting) {
                        Intent intent = new Intent();
                        intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                        intent.addCategory("android.intent.category.DEFAULT");
                        intent.setData(Uri.parse("package:" + activity.getPackageName()));
                        activity.startActivity(intent);
                        BTPermissionChecker.this.onAllPermissionDenied(activity);
                        return;
                    }
                    SharedPreferencesUtils.setBoolean(BTPermissionChecker.KEY_PERMISSION_HASREQ, true);
                    BTPermissionChecker.getInstance().requestPermission(activity, permissions, new PermissionListener() {
                        public void onCheckCallback(String[] permissions, boolean[] results) {
                            int length = results.length;
                            int i = 0;
                            while (i < length) {
                                if (results[i]) {
                                    i++;
                                } else if (!BTPermissionChecker.this.isPermissionForceAgree()) {
                                    BTPermissionChecker.this.onSomePermissionDenied(activity);
                                    return;
                                } else {
                                    BTPermissionChecker.this.checkPermission(activity);
                                    return;
                                }
                            }
                            BTPermissionChecker.this.onAllPermissionGranted(activity);
                        }
                    });
                }
            }).create();
            alertDialog.setCancelable(false);
            alertDialog.show();
            return;
        }
        onAllPermissionGranted(activity);
    }

    /* access modifiers changed from: private */
    public void onAllPermissionGranted(Activity activity) {
        sendSdkCheckPermisionEvent(activity, 0, "All requested permissions are granted");
    }

    /* access modifiers changed from: private */
    public void onSomePermissionDenied(Activity activity) {
        sendSdkCheckPermisionEvent(activity, 1, "Some permissions of requests are denied");
    }

    /* access modifiers changed from: private */
    public void onAllPermissionDenied(Activity activity) {
        sendSdkCheckPermisionEvent(activity, -1, "User rejected the necessary permissions!");
    }

    private void showRejectDialog(final Activity activity) {
        String gameName = BTResourceUtil.getAppName();
        Spanned btnCancel = Html.fromHtml(BTResourceUtil.findStringByName("dl_btn_permission_cancel"));
        AlertDialog alertDialog = new Builder(activity).setMessage(Html.fromHtml(String.format(BTResourceUtil.findStringByName("dl_reject_permission_des"), new Object[]{gameName}))).setNegativeButton(btnCancel, new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                BTPermissionChecker.this.checkPermission(activity);
            }
        }).setPositiveButton(Html.fromHtml(BTResourceUtil.findStringByName("dl_btn_permission_ok")), new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                BTPermissionChecker.this.onAllPermissionDenied(activity);
            }
        }).create();
        alertDialog.setCancelable(false);
        alertDialog.show();
    }

    private void sendSdkCheckPermisionEvent(Activity activity, int statusCode, String des) {
        SdkEventManager.postEvent(new SdkCheckPermisionEvent.Builder(EventType.CHECK_PERMISSIONS_RES).activity(activity).statusCode(statusCode).des(des).build());
    }
}
