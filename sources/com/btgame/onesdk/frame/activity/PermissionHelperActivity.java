package com.btgame.onesdk.frame.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.p000v4.app.ActivityCompat;
import android.support.p000v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import com.btgame.onesdk.frame.p044ui.BtAlertDialog;
import com.btgame.sdk.util.BtUtils;
import com.btgame.sdk.util.BtsdkLog;
import com.btgame.sdk.util.ResourceUtil;

public class PermissionHelperActivity extends Activity {
    private static final int AGAIN_RESULT_CODE = 901;
    private static final String BT_PERMISSION_DIALOG_MSG = "bt_permission_dialog_msg";
    private static final String BT_PERMISSION_DIALOG_SECOND_MSG = "bt_permission_dialog_second_msg";
    private static final String BT_PERMISSION_DIALOG_TITLE = "bt_permission_dialog_title";
    private static final String COMFIRM_BTN_TEXT = "bt_permission_dialog_comfirm_text";
    private static final String EXPLANATION_CONTENT_MSG = "bt_permission_explanation_msg";
    private static final String EXPLANATION_FILE_MSG = "bt_permission_explanation_file_msg";
    private static final String EXPLANATION_FILE_TITLE = "bt_permission_explanation_file_title";
    private static final String EXPLANATION_NEXTBTN_TEXT = "bt_permission_explanation_nextbtn_text";
    private static final String EXPLANATION_PHONE_MSG = "bt_permission_explanation_phone_msg";
    private static final String EXPLANATION_PHONE_TITLE = "bt_permission_explanation_phone_title";
    private static final String EXPLANATION_SETTING_MSG = "bt_permission_setting_btn_text";
    private static final String EXPLANATION_TITLE_TEXT = "bt_permission_explanation_title";
    private static final String FILE_PERMISSION = "android.permission.WRITE_EXTERNAL_STORAGE";
    private static final String GAMEACTVITY_ACTIONNAME = "gameMainActivity";
    private static final String LEAVEBTN_TEXT = "bt_permission_leavegame_btn_text";
    private static final String PHONE_PERMISSION = "android.permission.READ_PHONE_STATE";
    private static final int REQUEST_APP_SETTING = 1;
    private static final int RESULT_CODE = 900;
    private String gameActivity_Action_Name;
    private String permissionAsking;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(BtsdkLog.TAG, "PermissionHelperActivity create");
        showFilePerMissonRequestDialog();
        this.gameActivity_Action_Name = BtUtils.getNOXMeTaData(this, GAMEACTVITY_ACTIONNAME);
        if (VERSION.SDK_INT < 23) {
            startGame();
        } else if (!checkPermission(FILE_PERMISSION)) {
            showFilePerMissonRequestDialog();
        } else if (!checkPermission(PHONE_PERMISSION)) {
            showPhonePerMissonRequestDialog();
        } else {
            startGame();
        }
    }

    public void showFilePerMissonRequestDialog() {
        BtAlertDialog.showDialog(this, getResources().getString(ResourceUtil.getStringId(this, EXPLANATION_TITLE_TEXT)), String.format(getResources().getString(ResourceUtil.getStringId(this, EXPLANATION_CONTENT_MSG)), new Object[]{getResources().getString(ResourceUtil.getStringId(this, EXPLANATION_FILE_TITLE)), getResources().getString(ResourceUtil.getStringId(this, EXPLANATION_FILE_MSG))}), getResources().getString(ResourceUtil.getStringId(this, EXPLANATION_NEXTBTN_TEXT)), null, new OnClickListener() {
            public void onClick(View v) {
                ActivityCompat.requestPermissions(PermissionHelperActivity.this, new String[]{PermissionHelperActivity.FILE_PERMISSION}, PermissionHelperActivity.RESULT_CODE);
            }
        }, null, true, false, new OnCancelListener() {
            public void onCancel(DialogInterface dialog) {
                BtAlertDialog.hideDialog();
            }
        });
    }

    public void showPhonePerMissonRequestDialog() {
        BtAlertDialog.showDialog(this, getResources().getString(ResourceUtil.getStringId(this, EXPLANATION_TITLE_TEXT)), String.format(getResources().getString(ResourceUtil.getStringId(this, EXPLANATION_CONTENT_MSG)), new Object[]{getResources().getString(ResourceUtil.getStringId(this, EXPLANATION_PHONE_TITLE)), getResources().getString(ResourceUtil.getStringId(this, EXPLANATION_PHONE_MSG))}), getResources().getString(ResourceUtil.getStringId(this, EXPLANATION_NEXTBTN_TEXT)), null, new OnClickListener() {
            public void onClick(View v) {
                ActivityCompat.requestPermissions(PermissionHelperActivity.this, new String[]{PermissionHelperActivity.PHONE_PERMISSION}, PermissionHelperActivity.RESULT_CODE);
            }
        }, null, true, false, new OnCancelListener() {
            public void onCancel(DialogInterface dialog) {
                BtAlertDialog.hideDialog();
            }
        });
    }

    public boolean checkPermission(String permission) {
        if (ContextCompat.checkSelfPermission(getApplicationContext(), permission) != 0) {
            return false;
        }
        return true;
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case RESULT_CODE /*900*/:
                Log.d(BtsdkLog.TAG, permissions[0] + " result: " + grantResults[0]);
                if (grantResults[0] == 0) {
                    permissionGranted(permissions[0]);
                    return;
                } else if (ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[0])) {
                    confirmPermission(permissions[0], AGAIN_RESULT_CODE);
                    return;
                } else {
                    Log.d(BtsdkLog.TAG, "showAppSetting--->");
                    showAppSetting(permissions[0]);
                    return;
                }
            case AGAIN_RESULT_CODE /*901*/:
                Log.d(BtsdkLog.TAG, permissions[0] + " result: " + grantResults[0]);
                if (grantResults[0] == 0) {
                    permissionGranted(permissions[0]);
                    return;
                } else {
                    permissionDenied(permissions[0]);
                    return;
                }
            default:
                return;
        }
    }

    private void confirmPermission(final String permission, final int requestCode) {
        String title = getResources().getString(ResourceUtil.getStringId(this, BT_PERMISSION_DIALOG_TITLE));
        String confirmText = getResources().getString(ResourceUtil.getStringId(this, COMFIRM_BTN_TEXT));
        String tips = getResources().getString(ResourceUtil.getStringId(this, BT_PERMISSION_DIALOG_SECOND_MSG));
        Log.d(BtsdkLog.TAG, "permissionTips = " + tips);
        BtAlertDialog.showDialog(this, title, String.format(tips, new Object[]{getPermissionGroupName(permission)}), confirmText, null, new OnClickListener() {
            public void onClick(View v) {
                ActivityCompat.requestPermissions(PermissionHelperActivity.this, new String[]{permission}, requestCode);
            }
        }, null, false, false, new OnCancelListener() {
            public void onCancel(DialogInterface dialog) {
                BtAlertDialog.hideDialog();
            }
        });
    }

    private void showAppSetting(String permission) {
        this.permissionAsking = permission;
        String title = getResources().getString(ResourceUtil.getStringId(this, BT_PERMISSION_DIALOG_TITLE));
        String confirmText = getResources().getString(ResourceUtil.getStringId(this, EXPLANATION_SETTING_MSG));
        String leaveText = getResources().getString(ResourceUtil.getStringId(this, LEAVEBTN_TEXT));
        String tips = getResources().getString(ResourceUtil.getStringId(this, BT_PERMISSION_DIALOG_MSG));
        Log.d(BtsdkLog.TAG, "permissionTips = " + tips);
        BtAlertDialog.showDialog(this, title, String.format(tips, new Object[]{getPermissionGroupName(permission)}), confirmText, leaveText, new OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
                intent.setData(Uri.fromParts("package", PermissionHelperActivity.this.getPackageName(), null));
                PermissionHelperActivity.this.startActivityForResult(intent, 1);
                PermissionHelperActivity.this.finish();
            }
        }, new OnClickListener() {
            public void onClick(View v) {
                PermissionHelperActivity.this.finish();
            }
        }, false, false, new OnCancelListener() {
            public void onCancel(DialogInterface dialog) {
                BtAlertDialog.hideDialog();
            }
        });
    }

    private void startGame() {
        try {
            Intent intent = new Intent(this, Class.forName(this.gameActivity_Action_Name));
            intent.setPackage(getPackageName());
            startActivity(intent);
            finish();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            Log.e(BtsdkLog.TAG, "manifest 中的gameActivity setting wrong");
        }
    }

    private void permissionGranted(String permission) {
        if (permission.equals(FILE_PERMISSION)) {
            showPhonePerMissonRequestDialog();
        } else if (permission.equals(PHONE_PERMISSION)) {
            startGame();
        }
    }

    private void permissionDenied(String permissionAsking2) {
        showAppSetting(permissionAsking2);
    }

    private String getPermissionGroupName(String permission) {
        try {
            PackageManager pm = getPackageManager();
            return pm.getPermissionGroupInfo(pm.getPermissionInfo(permission, 0).group, 0).loadLabel(pm).toString();
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (checkPermission(this.permissionAsking)) {
                    permissionGranted(this.permissionAsking);
                    return;
                } else {
                    permissionDenied(this.permissionAsking);
                    return;
                }
            default:
                return;
        }
    }
}
