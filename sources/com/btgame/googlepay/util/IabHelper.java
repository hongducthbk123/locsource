package com.btgame.googlepay.util;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.content.ServiceConnection;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import com.android.vending.billing.IInAppBillingService;
import com.android.vending.billing.IInAppBillingService.Stub;
import com.btgame.seasdk.btcore.common.util.BtsdkLog;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import p004cn.jiguang.net.HttpUtils;

public class IabHelper {
    public static final int BILLING_RESPONSE_RESULT_BILLING_UNAVAILABLE = 3;
    public static final int BILLING_RESPONSE_RESULT_DEVELOPER_ERROR = 5;
    public static final int BILLING_RESPONSE_RESULT_ERROR = 6;
    public static final int BILLING_RESPONSE_RESULT_ITEM_ALREADY_OWNED = 7;
    public static final int BILLING_RESPONSE_RESULT_ITEM_NOT_OWNED = 8;
    public static final int BILLING_RESPONSE_RESULT_ITEM_UNAVAILABLE = 4;
    public static final int BILLING_RESPONSE_RESULT_OK = 0;
    public static final int BILLING_RESPONSE_RESULT_SERVICE_UNAVAILABLE = 2;
    public static final int BILLING_RESPONSE_RESULT_USER_CANCELED = 1;
    public static final String GET_SKU_DETAILS_ITEM_LIST = "ITEM_ID_LIST";
    public static final String GET_SKU_DETAILS_ITEM_TYPE_LIST = "ITEM_TYPE_LIST";
    public static final int IABHELPER_BAD_RESPONSE = -1002;
    public static final int IABHELPER_ERROR_BASE = -1000;
    public static final int IABHELPER_INVALID_CONSUMPTION = -1010;
    public static final int IABHELPER_MISSING_TOKEN = -1007;
    public static final int IABHELPER_REMOTE_EXCEPTION = -1001;
    public static final int IABHELPER_SEND_INTENT_FAILED = -1004;
    public static final int IABHELPER_SUBSCRIPTIONS_NOT_AVAILABLE = -1009;
    public static final int IABHELPER_SUBSCRIPTION_UPDATE_NOT_AVAILABLE = -1011;
    public static final int IABHELPER_UNKNOWN_ERROR = -1008;
    public static final int IABHELPER_UNKNOWN_PURCHASE_RESPONSE = -1006;
    public static final int IABHELPER_USER_CANCELLED = -1005;
    public static final int IABHELPER_VERIFICATION_FAILED = -1003;
    public static final String INAPP_CONTINUATION_TOKEN = "INAPP_CONTINUATION_TOKEN";
    public static final String ITEM_TYPE_INAPP = "inapp";
    public static final String ITEM_TYPE_SUBS = "subs";
    public static final String RESPONSE_BUY_INTENT = "BUY_INTENT";
    public static final String RESPONSE_CODE = "RESPONSE_CODE";
    public static final String RESPONSE_GET_SKU_DETAILS_LIST = "DETAILS_LIST";
    public static final String RESPONSE_INAPP_ITEM_LIST = "INAPP_PURCHASE_ITEM_LIST";
    public static final String RESPONSE_INAPP_PURCHASE_DATA = "INAPP_PURCHASE_DATA";
    public static final String RESPONSE_INAPP_PURCHASE_DATA_LIST = "INAPP_PURCHASE_DATA_LIST";
    public static final String RESPONSE_INAPP_SIGNATURE = "INAPP_DATA_SIGNATURE";
    public static final String RESPONSE_INAPP_SIGNATURE_LIST = "INAPP_DATA_SIGNATURE_LIST";
    private boolean hasBindService;
    boolean mAsyncInProgress = false;
    private final Object mAsyncInProgressLock = new Object();
    String mAsyncOperation = "";
    Context mContext;
    boolean mDebugLog = false;
    String mDebugTag = "IabHelper";
    boolean mDisposeAfterAsync = false;
    boolean mDisposed = false;
    OnIabPurchaseFinishedListener mPurchaseListener;
    String mPurchasingItemType;
    int mRequestCode;
    IInAppBillingService mService;
    ServiceConnection mServiceConn;
    boolean mSetupDone = false;
    boolean mSubscriptionUpdateSupported = false;
    boolean mSubscriptionsSupported = false;

    public static class IabAsyncInProgressException extends Exception {
        public IabAsyncInProgressException(String message) {
            super(message);
        }
    }

    public interface OnConsumeFinishedListener {
        void onConsumeFinished(Purchase purchase, IabResult iabResult);
    }

    public interface OnConsumeMultiFinishedListener {
        void onConsumeMultiFinished(List<Purchase> list, List<IabResult> list2);
    }

    public interface OnIabPurchaseFinishedListener {
        void onIabPurchaseFinished(IabResult iabResult, Purchase purchase);
    }

    public interface OnIabSetupFinishedListener {
        void onIabSetupFinished(IabResult iabResult);
    }

    public interface QueryInventoryFinishedListener {
        void onQueryInventoryFinished(IabResult iabResult, Inventory inventory);
    }

    public IabHelper(Context ctx) {
        this.mContext = ctx.getApplicationContext();
        logDebug("IAB helper created.");
    }

    public static String getResponseDesc(int code) {
        String[] iab_msgs = "0:OK/1:User Canceled/2:Unknown/3:Billing Unavailable/4:Item unavailable/5:Developer Error/6:Error/7:Item Already Owned/8:Item not owned".split(HttpUtils.PATHS_SEPARATOR);
        String[] iabhelper_msgs = "0:OK/-1001:Remote exception during initialization/-1002:Bad response received/-1003:Purchase signature verification failed/-1004:Send intent failed/-1005:User cancelled/-1006:Unknown purchase response/-1007:Missing token/-1008:Unknown error/-1009:Subscriptions not available/-1010:Invalid consumption attempt".split(HttpUtils.PATHS_SEPARATOR);
        if (code <= -1000) {
            int index = -1000 - code;
            if (index < 0 || index >= iabhelper_msgs.length) {
                return String.valueOf(code) + ":Unknown IAB Helper Error";
            }
            return iabhelper_msgs[index];
        } else if (code < 0 || code >= iab_msgs.length) {
            return String.valueOf(code) + ":Unknown";
        } else {
            return iab_msgs[code];
        }
    }

    public void enableDebugLogging(boolean enable, String tag) {
        checkNotDisposed();
        this.mDebugLog = enable;
        this.mDebugTag = tag;
    }

    public void enableDebugLogging(boolean enable) {
        checkNotDisposed();
        this.mDebugLog = enable;
    }

    public void startSetup(final OnIabSetupFinishedListener listener) {
        checkNotDisposed();
        if (this.mSetupDone) {
            throw new IllegalStateException("IAB helper is already set up.");
        }
        logDebug("Starting in-app billing setup.");
        this.mServiceConn = new ServiceConnection() {
            public void onServiceDisconnected(ComponentName name) {
                IabHelper.this.logDebug("Billing service disconnected.");
                IabHelper.this.mService = null;
            }

            public void onServiceConnected(ComponentName name, IBinder service) {
                if (!IabHelper.this.mDisposed) {
                    IabHelper.this.logDebug("Billing service connected.");
                    IabHelper.this.mService = Stub.asInterface(service);
                    String packageName = IabHelper.this.mContext.getPackageName();
                    try {
                        IabHelper.this.logDebug("Checking for in-app billing 3 support.");
                        int response = IabHelper.this.mService.isBillingSupported(3, packageName, IabHelper.ITEM_TYPE_INAPP);
                        if (response != 0) {
                            if (listener != null) {
                                listener.onIabSetupFinished(new IabResult(response, "Error checking for billing v3 support."));
                            }
                            IabHelper.this.mSubscriptionsSupported = false;
                            IabHelper.this.mSubscriptionUpdateSupported = false;
                            return;
                        }
                        IabHelper.this.logDebug("In-app billing version 3 supported for " + packageName);
                        if (IabHelper.this.mService.isBillingSupported(5, packageName, IabHelper.ITEM_TYPE_SUBS) == 0) {
                            IabHelper.this.logDebug("Subscription re-signup AVAILABLE.");
                            IabHelper.this.mSubscriptionUpdateSupported = true;
                        } else {
                            IabHelper.this.logDebug("Subscription re-signup not available.");
                            IabHelper.this.mSubscriptionUpdateSupported = false;
                        }
                        if (IabHelper.this.mSubscriptionUpdateSupported) {
                            IabHelper.this.mSubscriptionsSupported = true;
                        } else {
                            int response2 = IabHelper.this.mService.isBillingSupported(3, packageName, IabHelper.ITEM_TYPE_SUBS);
                            if (response2 == 0) {
                                IabHelper.this.logDebug("Subscriptions AVAILABLE.");
                                IabHelper.this.mSubscriptionsSupported = true;
                            } else {
                                IabHelper.this.logDebug("Subscriptions NOT AVAILABLE. Response: " + response2);
                                IabHelper.this.mSubscriptionsSupported = false;
                                IabHelper.this.mSubscriptionUpdateSupported = false;
                            }
                        }
                        IabHelper.this.mSetupDone = true;
                        if (listener != null) {
                            listener.onIabSetupFinished(new IabResult(0, "Setup successful."));
                        }
                    } catch (RemoteException e) {
                        if (listener != null) {
                            listener.onIabSetupFinished(new IabResult(IabHelper.IABHELPER_REMOTE_EXCEPTION, "RemoteException while setting up in-app billing."));
                        }
                        e.printStackTrace();
                    }
                }
            }
        };
        Intent serviceIntent = new Intent("com.android.vending.billing.InAppBillingService.BIND");
        serviceIntent.setPackage("com.android.vending");
        List<ResolveInfo> intentServices = this.mContext.getPackageManager().queryIntentServices(serviceIntent, 0);
        if (intentServices != null && !intentServices.isEmpty()) {
            this.hasBindService = true;
            this.mContext.bindService(serviceIntent, this.mServiceConn, 1);
        } else if (listener != null) {
            listener.onIabSetupFinished(new IabResult(3, "Billing service unavailable on device."));
        }
    }

    public void dispose() throws IabAsyncInProgressException {
        synchronized (this.mAsyncInProgressLock) {
            if (this.mAsyncInProgress) {
                throw new IabAsyncInProgressException("Can't dispose because an async operation (" + this.mAsyncOperation + ") is in progress.");
            }
        }
        logDebug("Disposing.");
        this.mSetupDone = false;
        if (this.mServiceConn != null) {
            logDebug("Unbinding from service.");
            if (this.hasBindService && this.mContext != null) {
                this.mContext.unbindService(this.mServiceConn);
            }
        }
        this.mDisposed = true;
        this.mContext = null;
        this.mServiceConn = null;
        this.mService = null;
        this.mPurchaseListener = null;
    }

    public void disposeWhenFinished() {
        synchronized (this.mAsyncInProgressLock) {
            if (this.mAsyncInProgress) {
                logDebug("Will dispose after async operation finishes.");
                this.mDisposeAfterAsync = true;
            } else {
                try {
                    dispose();
                } catch (IabAsyncInProgressException e) {
                }
            }
        }
    }

    private void checkNotDisposed() {
        if (this.mDisposed) {
            throw new IllegalStateException("IabHelper was disposed of, so it cannot be used.");
        }
    }

    public boolean subscriptionsSupported() {
        checkNotDisposed();
        return this.mSubscriptionsSupported;
    }

    public void launchPurchaseFlow(Activity act, String sku, int requestCode, OnIabPurchaseFinishedListener listener) throws IabAsyncInProgressException {
        launchPurchaseFlow(act, sku, requestCode, listener, "");
    }

    public void launchPurchaseFlow(Activity act, String sku, int requestCode, OnIabPurchaseFinishedListener listener, String extraData) throws IabAsyncInProgressException {
        launchPurchaseFlow(act, sku, ITEM_TYPE_INAPP, null, requestCode, listener, extraData);
    }

    public void launchSubscriptionPurchaseFlow(Activity act, String sku, int requestCode, OnIabPurchaseFinishedListener listener) throws IabAsyncInProgressException {
        launchSubscriptionPurchaseFlow(act, sku, requestCode, listener, "");
    }

    public void launchSubscriptionPurchaseFlow(Activity act, String sku, int requestCode, OnIabPurchaseFinishedListener listener, String extraData) throws IabAsyncInProgressException {
        launchPurchaseFlow(act, sku, ITEM_TYPE_SUBS, null, requestCode, listener, extraData);
    }

    public void launchPurchaseFlow(Activity act, String sku, String itemType, List<String> oldSkus, int requestCode, OnIabPurchaseFinishedListener listener, String extraData) throws IabAsyncInProgressException {
        Bundle buyIntentBundle;
        checkNotDisposed();
        checkSetupDone("launchPurchaseFlow");
        flagStartAsync("launchPurchaseFlow");
        if (!itemType.equals(ITEM_TYPE_SUBS) || this.mSubscriptionsSupported) {
            try {
                logDebug("Constructing buy intent for " + sku + ", item type: " + itemType);
                if (oldSkus == null || oldSkus.isEmpty()) {
                    buyIntentBundle = this.mService.getBuyIntent(3, this.mContext.getPackageName(), sku, itemType, extraData);
                } else if (!this.mSubscriptionUpdateSupported) {
                    IabResult r = new IabResult(IABHELPER_SUBSCRIPTION_UPDATE_NOT_AVAILABLE, "Subscription updates are not available.");
                    flagEndAsync();
                    if (listener != null) {
                        listener.onIabPurchaseFinished(r, null);
                        return;
                    }
                    return;
                } else {
                    buyIntentBundle = this.mService.getBuyIntentToReplaceSkus(5, this.mContext.getPackageName(), oldSkus, sku, itemType, extraData);
                }
                int response = getResponseCodeFromBundle(buyIntentBundle);
                if (response != 0) {
                    logError("Unable to buy item, Error response: " + getResponseDesc(response));
                    flagEndAsync();
                    IabResult result = new IabResult(response, "Unable to buy item");
                    if (listener != null) {
                        listener.onIabPurchaseFinished(result, null);
                        return;
                    }
                    return;
                }
                PendingIntent pendingIntent = (PendingIntent) buyIntentBundle.getParcelable(RESPONSE_BUY_INTENT);
                logDebug("Launching buy intent for " + sku + ". Request code: " + requestCode);
                this.mRequestCode = requestCode;
                this.mPurchaseListener = listener;
                this.mPurchasingItemType = itemType;
                act.startIntentSenderForResult(pendingIntent.getIntentSender(), requestCode, new Intent(), Integer.valueOf(0).intValue(), Integer.valueOf(0).intValue(), Integer.valueOf(0).intValue());
            } catch (SendIntentException e) {
                logError("SendIntentException while launching purchase flow for sku " + sku);
                e.printStackTrace();
                flagEndAsync();
                IabResult result2 = new IabResult(IABHELPER_SEND_INTENT_FAILED, "Failed to send intent.");
                if (listener != null) {
                    listener.onIabPurchaseFinished(result2, null);
                }
            } catch (RemoteException e2) {
                logError("RemoteException while launching purchase flow for sku " + sku);
                e2.printStackTrace();
                flagEndAsync();
                IabResult result3 = new IabResult(IABHELPER_REMOTE_EXCEPTION, "Remote exception while starting purchase flow");
                if (listener != null) {
                    listener.onIabPurchaseFinished(result3, null);
                }
            }
        } else {
            IabResult r2 = new IabResult(IABHELPER_SUBSCRIPTIONS_NOT_AVAILABLE, "Subscriptions are not available.");
            flagEndAsync();
            if (listener != null) {
                listener.onIabPurchaseFinished(r2, null);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x0117  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean handleActivityResult(int r12, int r13, android.content.Intent r14) {
        /*
            r11 = this;
            int r7 = r11.mRequestCode
            if (r12 == r7) goto L_0x0006
            r7 = 0
        L_0x0005:
            return r7
        L_0x0006:
            r11.checkNotDisposed()
            java.lang.String r7 = "handleActivityResult"
            r11.checkSetupDone(r7)
            r11.flagEndAsync()
            if (r14 != 0) goto L_0x002d
            java.lang.String r7 = "Null data in IAB activity result."
            r11.logError(r7)
            com.btgame.googlepay.util.IabResult r6 = new com.btgame.googlepay.util.IabResult
            r7 = -1002(0xfffffffffffffc16, float:NaN)
            java.lang.String r8 = "Null data in IAB result"
            r6.<init>(r7, r8)
            com.btgame.googlepay.util.IabHelper$OnIabPurchaseFinishedListener r7 = r11.mPurchaseListener
            if (r7 == 0) goto L_0x002b
            com.btgame.googlepay.util.IabHelper$OnIabPurchaseFinishedListener r7 = r11.mPurchaseListener
            r8 = 0
            r7.onIabPurchaseFinished(r6, r8)
        L_0x002b:
            r7 = 1
            goto L_0x0005
        L_0x002d:
            int r5 = r11.getResponseCodeFromIntent(r14)
            java.lang.String r7 = "INAPP_PURCHASE_DATA"
            java.lang.String r4 = r14.getStringExtra(r7)
            java.lang.String r7 = "INAPP_DATA_SIGNATURE"
            java.lang.String r0 = r14.getStringExtra(r7)
            r7 = -1
            if (r13 != r7) goto L_0x0120
            if (r5 != 0) goto L_0x0120
            java.lang.String r7 = "Successful resultcode from purchase activity."
            r11.logDebug(r7)
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r8 = "Purchase data: "
            java.lang.StringBuilder r7 = r7.append(r8)
            java.lang.StringBuilder r7 = r7.append(r4)
            java.lang.String r7 = r7.toString()
            r11.logDebug(r7)
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r8 = "Data signature: "
            java.lang.StringBuilder r7 = r7.append(r8)
            java.lang.StringBuilder r7 = r7.append(r0)
            java.lang.String r7 = r7.toString()
            r11.logDebug(r7)
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r8 = "Extras: "
            java.lang.StringBuilder r7 = r7.append(r8)
            android.os.Bundle r8 = r14.getExtras()
            java.lang.StringBuilder r7 = r7.append(r8)
            java.lang.String r7 = r7.toString()
            r11.logDebug(r7)
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r8 = "Expected item type: "
            java.lang.StringBuilder r7 = r7.append(r8)
            java.lang.String r8 = r11.mPurchasingItemType
            java.lang.StringBuilder r7 = r7.append(r8)
            java.lang.String r7 = r7.toString()
            r11.logDebug(r7)
            if (r4 == 0) goto L_0x00a9
            if (r0 != 0) goto L_0x00e2
        L_0x00a9:
            java.lang.String r7 = "BUG: either purchaseData or dataSignature is null."
            r11.logError(r7)
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r8 = "Extras: "
            java.lang.StringBuilder r7 = r7.append(r8)
            android.os.Bundle r8 = r14.getExtras()
            java.lang.String r8 = r8.toString()
            java.lang.StringBuilder r7 = r7.append(r8)
            java.lang.String r7 = r7.toString()
            r11.logDebug(r7)
            com.btgame.googlepay.util.IabResult r6 = new com.btgame.googlepay.util.IabResult
            r7 = -1008(0xfffffffffffffc10, float:NaN)
            java.lang.String r8 = "IAB returned null purchaseData or dataSignature"
            r6.<init>(r7, r8)
            com.btgame.googlepay.util.IabHelper$OnIabPurchaseFinishedListener r7 = r11.mPurchaseListener
            if (r7 == 0) goto L_0x00df
            com.btgame.googlepay.util.IabHelper$OnIabPurchaseFinishedListener r7 = r11.mPurchaseListener
            r8 = 0
            r7.onIabPurchaseFinished(r6, r8)
        L_0x00df:
            r7 = 1
            goto L_0x0005
        L_0x00e2:
            r2 = 0
            com.btgame.googlepay.util.Purchase r3 = new com.btgame.googlepay.util.Purchase     // Catch:{ JSONException -> 0x0101 }
            java.lang.String r7 = r11.mPurchasingItemType     // Catch:{ JSONException -> 0x0101 }
            r3.<init>(r7, r4, r0)     // Catch:{ JSONException -> 0x0101 }
            r3.getSku()     // Catch:{ JSONException -> 0x01bc }
            com.btgame.googlepay.util.IabHelper$OnIabPurchaseFinishedListener r7 = r11.mPurchaseListener
            if (r7 == 0) goto L_0x00fe
            com.btgame.googlepay.util.IabHelper$OnIabPurchaseFinishedListener r7 = r11.mPurchaseListener
            com.btgame.googlepay.util.IabResult r8 = new com.btgame.googlepay.util.IabResult
            r9 = 0
            java.lang.String r10 = "Success"
            r8.<init>(r9, r10)
            r7.onIabPurchaseFinished(r8, r3)
        L_0x00fe:
            r7 = 1
            goto L_0x0005
        L_0x0101:
            r1 = move-exception
        L_0x0102:
            java.lang.String r7 = "Failed to parse purchase data."
            r11.logError(r7)
            r1.printStackTrace()
            com.btgame.googlepay.util.IabResult r6 = new com.btgame.googlepay.util.IabResult
            r7 = -1002(0xfffffffffffffc16, float:NaN)
            java.lang.String r8 = "Failed to parse purchase data."
            r6.<init>(r7, r8)
            com.btgame.googlepay.util.IabHelper$OnIabPurchaseFinishedListener r7 = r11.mPurchaseListener
            if (r7 == 0) goto L_0x011d
            com.btgame.googlepay.util.IabHelper$OnIabPurchaseFinishedListener r7 = r11.mPurchaseListener
            r8 = 0
            r7.onIabPurchaseFinished(r6, r8)
        L_0x011d:
            r7 = 1
            goto L_0x0005
        L_0x0120:
            r7 = -1
            if (r13 != r7) goto L_0x014f
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r8 = "Result code was OK but in-app billing response was not OK: "
            java.lang.StringBuilder r7 = r7.append(r8)
            java.lang.String r8 = getResponseDesc(r5)
            java.lang.StringBuilder r7 = r7.append(r8)
            java.lang.String r7 = r7.toString()
            r11.logDebug(r7)
            com.btgame.googlepay.util.IabHelper$OnIabPurchaseFinishedListener r7 = r11.mPurchaseListener
            if (r7 == 0) goto L_0x00fe
            com.btgame.googlepay.util.IabResult r6 = new com.btgame.googlepay.util.IabResult
            java.lang.String r7 = "Problem purchashing item."
            r6.<init>(r5, r7)
            com.btgame.googlepay.util.IabHelper$OnIabPurchaseFinishedListener r7 = r11.mPurchaseListener
            r8 = 0
            r7.onIabPurchaseFinished(r6, r8)
            goto L_0x00fe
        L_0x014f:
            if (r13 != 0) goto L_0x017f
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r8 = "Purchase canceled - Response: "
            java.lang.StringBuilder r7 = r7.append(r8)
            java.lang.String r8 = getResponseDesc(r5)
            java.lang.StringBuilder r7 = r7.append(r8)
            java.lang.String r7 = r7.toString()
            r11.logDebug(r7)
            com.btgame.googlepay.util.IabResult r6 = new com.btgame.googlepay.util.IabResult
            r7 = -1005(0xfffffffffffffc13, float:NaN)
            java.lang.String r8 = "User canceled."
            r6.<init>(r7, r8)
            com.btgame.googlepay.util.IabHelper$OnIabPurchaseFinishedListener r7 = r11.mPurchaseListener
            if (r7 == 0) goto L_0x00fe
            com.btgame.googlepay.util.IabHelper$OnIabPurchaseFinishedListener r7 = r11.mPurchaseListener
            r8 = 0
            r7.onIabPurchaseFinished(r6, r8)
            goto L_0x00fe
        L_0x017f:
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r8 = "Purchase failed. Result code: "
            java.lang.StringBuilder r7 = r7.append(r8)
            java.lang.String r8 = java.lang.Integer.toString(r13)
            java.lang.StringBuilder r7 = r7.append(r8)
            java.lang.String r8 = ". Response: "
            java.lang.StringBuilder r7 = r7.append(r8)
            java.lang.String r8 = getResponseDesc(r5)
            java.lang.StringBuilder r7 = r7.append(r8)
            java.lang.String r7 = r7.toString()
            r11.logError(r7)
            com.btgame.googlepay.util.IabResult r6 = new com.btgame.googlepay.util.IabResult
            r7 = -1006(0xfffffffffffffc12, float:NaN)
            java.lang.String r8 = "Unknown purchase response."
            r6.<init>(r7, r8)
            com.btgame.googlepay.util.IabHelper$OnIabPurchaseFinishedListener r7 = r11.mPurchaseListener
            if (r7 == 0) goto L_0x00fe
            com.btgame.googlepay.util.IabHelper$OnIabPurchaseFinishedListener r7 = r11.mPurchaseListener
            r8 = 0
            r7.onIabPurchaseFinished(r6, r8)
            goto L_0x00fe
        L_0x01bc:
            r1 = move-exception
            r2 = r3
            goto L_0x0102
        */
        throw new UnsupportedOperationException("Method not decompiled: com.btgame.googlepay.util.IabHelper.handleActivityResult(int, int, android.content.Intent):boolean");
    }

    public Inventory queryInventory() throws IabException {
        return queryInventory(false, null, null);
    }

    public Inventory queryInventory(boolean querySkuDetails, List<String> moreItemSkus, List<String> moreSubsSkus) throws IabException {
        checkNotDisposed();
        checkSetupDone("queryInventory");
        try {
            Inventory inv = new Inventory();
            int r = queryPurchases(inv, ITEM_TYPE_INAPP);
            if (r != 0) {
                throw new IabException(r, "Error refreshing inventory (querying owned items).");
            }
            if (querySkuDetails) {
                int r2 = querySkuDetails(ITEM_TYPE_INAPP, inv, moreItemSkus);
                if (r2 != 0) {
                    throw new IabException(r2, "Error refreshing inventory (querying prices of items).");
                }
            }
            if (this.mSubscriptionsSupported) {
                int r3 = queryPurchases(inv, ITEM_TYPE_SUBS);
                if (r3 != 0) {
                    throw new IabException(r3, "Error refreshing inventory (querying owned subscriptions).");
                } else if (querySkuDetails) {
                    int r4 = querySkuDetails(ITEM_TYPE_SUBS, inv, moreSubsSkus);
                    if (r4 != 0) {
                        throw new IabException(r4, "Error refreshing inventory (querying prices of subscriptions).");
                    }
                }
            }
            return inv;
        } catch (RemoteException e) {
            throw new IabException(IABHELPER_REMOTE_EXCEPTION, "Remote exception while refreshing inventory.", e);
        } catch (JSONException e2) {
            throw new IabException(IABHELPER_BAD_RESPONSE, "Error parsing JSON response while refreshing inventory.", e2);
        }
    }

    public void queryInventoryAsync(boolean querySkuDetails, List<String> moreItemSkus, List<String> moreSubsSkus, QueryInventoryFinishedListener listener) throws IabAsyncInProgressException {
        final Handler handler = new Handler();
        checkNotDisposed();
        checkSetupDone("queryInventory");
        flagStartAsync("refresh inventory");
        final boolean z = querySkuDetails;
        final List<String> list = moreItemSkus;
        final List<String> list2 = moreSubsSkus;
        final QueryInventoryFinishedListener queryInventoryFinishedListener = listener;
        new Thread(new Runnable() {
            public void run() {
                IabResult result = new IabResult(0, "Inventory refresh successful.");
                Inventory inv = null;
                try {
                    inv = IabHelper.this.queryInventory(z, list, list2);
                } catch (IabException ex) {
                    result = ex.getResult();
                }
                IabHelper.this.flagEndAsync();
                final IabResult result_f = result;
                final Inventory inv_f = inv;
                if (!IabHelper.this.mDisposed && queryInventoryFinishedListener != null) {
                    handler.post(new Runnable() {
                        public void run() {
                            queryInventoryFinishedListener.onQueryInventoryFinished(result_f, inv_f);
                        }
                    });
                }
            }
        }).start();
    }

    public void queryInventoryAsync(QueryInventoryFinishedListener listener) throws IabAsyncInProgressException {
        queryInventoryAsync(true, null, null, listener);
    }

    /* access modifiers changed from: 0000 */
    public void consume(Purchase itemInfo) throws IabException {
        checkNotDisposed();
        checkSetupDone("consume");
        if (itemInfo == null || itemInfo.mItemType == null) {
            BtsdkLog.m1430e("消费的时候itemInfo为空");
            throw new IabException((int) IABHELPER_INVALID_CONSUMPTION, "Items is null !!!");
        } else if (!itemInfo.mItemType.equals(ITEM_TYPE_INAPP)) {
            throw new IabException((int) IABHELPER_INVALID_CONSUMPTION, "Items of type '" + itemInfo.mItemType + "' can't be consumed.");
        } else {
            try {
                String token = itemInfo.getToken();
                String sku = itemInfo.getSku();
                if (token == null || token.equals("")) {
                    logError("Can't consume " + sku + ". No token.");
                    throw new IabException((int) IABHELPER_MISSING_TOKEN, "PurchaseInfo is missing token for sku: " + sku + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + itemInfo);
                }
                logDebug("Consuming sku: " + sku + ", token: " + token);
                int response = this.mService.consumePurchase(3, this.mContext.getPackageName(), token);
                if (response == 0) {
                    logDebug("Successfully consumed sku: " + sku);
                } else {
                    logDebug("Error consuming consuming sku " + sku + ". " + getResponseDesc(response));
                    throw new IabException(response, "Error consuming sku " + sku);
                }
            } catch (RemoteException e) {
                throw new IabException(IABHELPER_REMOTE_EXCEPTION, "Remote exception while consuming. PurchaseInfo: " + itemInfo, e);
            }
        }
    }

    public void consumeAsync(Purchase purchase, OnConsumeFinishedListener listener) throws IabAsyncInProgressException {
        checkNotDisposed();
        checkSetupDone("consume");
        List<Purchase> purchases = new ArrayList<>();
        purchases.add(purchase);
        consumeAsyncInternal(purchases, listener, null);
    }

    public void consumeAsync(List<Purchase> purchases, OnConsumeMultiFinishedListener listener) throws IabAsyncInProgressException {
        checkNotDisposed();
        checkSetupDone("consume");
        consumeAsyncInternal(purchases, null, listener);
    }

    /* access modifiers changed from: 0000 */
    public void checkSetupDone(String operation) {
        if (!this.mSetupDone) {
            logError("Illegal state for operation (" + operation + "): IAB helper is not set up.");
            throw new IllegalStateException("IAB helper is not set up. Can't perform operation: " + operation);
        }
    }

    /* access modifiers changed from: 0000 */
    public int getResponseCodeFromBundle(Bundle b) {
        Object o = b.get(RESPONSE_CODE);
        if (o == null) {
            logDebug("Bundle with null response code, assuming OK (known issue)");
            return 0;
        } else if (o instanceof Integer) {
            return ((Integer) o).intValue();
        } else {
            if (o instanceof Long) {
                return (int) ((Long) o).longValue();
            }
            logError("Unexpected type for bundle response code.");
            logError(o.getClass().getName());
            throw new RuntimeException("Unexpected type for bundle response code: " + o.getClass().getName());
        }
    }

    /* access modifiers changed from: 0000 */
    public int getResponseCodeFromIntent(Intent i) {
        Object o = i.getExtras().get(RESPONSE_CODE);
        if (o == null) {
            logError("Intent with no response code, assuming OK (known issue)");
            return 0;
        } else if (o instanceof Integer) {
            return ((Integer) o).intValue();
        } else {
            if (o instanceof Long) {
                return (int) ((Long) o).longValue();
            }
            logError("Unexpected type for intent response code.");
            logError(o.getClass().getName());
            throw new RuntimeException("Unexpected type for intent response code: " + o.getClass().getName());
        }
    }

    /* access modifiers changed from: 0000 */
    public void flagStartAsync(String operation) throws IabAsyncInProgressException {
        synchronized (this.mAsyncInProgressLock) {
            if (this.mAsyncInProgress) {
                throw new IabAsyncInProgressException("Can't start async operation (" + operation + ") because another async operation (" + this.mAsyncOperation + ") is in progress.");
            }
            this.mAsyncOperation = operation;
            this.mAsyncInProgress = true;
            logDebug("Starting async operation: " + operation);
        }
    }

    /* access modifiers changed from: 0000 */
    public void flagEndAsync() {
        synchronized (this.mAsyncInProgressLock) {
            logDebug("Ending async operation: " + this.mAsyncOperation);
            this.mAsyncOperation = "";
            this.mAsyncInProgress = false;
            if (this.mDisposeAfterAsync) {
                try {
                    dispose();
                } catch (IabAsyncInProgressException e) {
                }
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public int queryPurchases(Inventory inv, String itemType) throws JSONException, RemoteException {
        logDebug("Querying owned items, item type: " + itemType);
        logDebug("Package name: " + this.mContext.getPackageName());
        String continueToken = null;
        do {
            logDebug("Calling getPurchases with continuation token: " + continueToken);
            try {
                Bundle ownedItems = this.mService.getPurchases(3, this.mContext.getPackageName(), itemType, continueToken);
                if (ownedItems == null) {
                    logDebug("queryPurchases: ownedItems is null");
                    return -1000;
                }
                int response = getResponseCodeFromBundle(ownedItems);
                logDebug("Owned items response: " + String.valueOf(response));
                if (response != 0) {
                    logDebug("getPurchases() failed: " + getResponseDesc(response));
                    return response;
                } else if (!ownedItems.containsKey(RESPONSE_INAPP_ITEM_LIST) || !ownedItems.containsKey(RESPONSE_INAPP_PURCHASE_DATA_LIST) || !ownedItems.containsKey(RESPONSE_INAPP_SIGNATURE_LIST)) {
                    logError("Bundle returned from getPurchases() doesn't contain required fields.");
                    return IABHELPER_BAD_RESPONSE;
                } else {
                    ArrayList<String> ownedSkus = ownedItems.getStringArrayList(RESPONSE_INAPP_ITEM_LIST);
                    ArrayList<String> purchaseDataList = ownedItems.getStringArrayList(RESPONSE_INAPP_PURCHASE_DATA_LIST);
                    ArrayList<String> signatureList = ownedItems.getStringArrayList(RESPONSE_INAPP_SIGNATURE_LIST);
                    for (int i = 0; i < purchaseDataList.size(); i++) {
                        String purchaseData = (String) purchaseDataList.get(i);
                        String signature = (String) signatureList.get(i);
                        logDebug("Sku is owned: " + ((String) ownedSkus.get(i)));
                        Purchase purchase = new Purchase(itemType, purchaseData, signature);
                        if (TextUtils.isEmpty(purchase.getToken())) {
                            logWarn("BUG: empty/null token!");
                            logDebug("Purchase data: " + purchaseData);
                        }
                        inv.addPurchase(purchase);
                    }
                    continueToken = ownedItems.getString(INAPP_CONTINUATION_TOKEN);
                    logDebug("Continuation token: " + continueToken);
                }
            } catch (Exception e) {
                e.printStackTrace();
                logDebug("queryPurchases: getPurchases Exception " + e.getLocalizedMessage());
                return -1000;
            }
        } while (!TextUtils.isEmpty(continueToken));
        return 0 != 0 ? IABHELPER_VERIFICATION_FAILED : 0;
    }

    /* access modifiers changed from: 0000 */
    public int querySkuDetails(String itemType, Inventory inv, List<String> moreSkus) throws RemoteException, JSONException {
        logDebug("Querying SKU details.");
        ArrayList<String> skuList = new ArrayList<>();
        skuList.addAll(inv.getAllOwnedSkus(itemType));
        if (moreSkus != null) {
            for (String sku : moreSkus) {
                if (!skuList.contains(sku)) {
                    skuList.add(sku);
                }
            }
        }
        if (skuList.size() == 0) {
            logDebug("queryPrices: nothing to do because there are no SKUs.");
            return 0;
        }
        ArrayList<ArrayList<String>> packs = new ArrayList<>();
        int n = skuList.size() / 20;
        int mod = skuList.size() % 20;
        for (int i = 0; i < n; i++) {
            ArrayList<String> tempList = new ArrayList<>();
            for (String s : skuList.subList(i * 20, (i * 20) + 20)) {
                tempList.add(s);
            }
            packs.add(tempList);
        }
        if (mod != 0) {
            ArrayList<String> tempList2 = new ArrayList<>();
            for (String s2 : skuList.subList(n * 20, (n * 20) + mod)) {
                tempList2.add(s2);
            }
            packs.add(tempList2);
        }
        Iterator it = packs.iterator();
        while (it.hasNext()) {
            ArrayList<String> skuPartList = (ArrayList) it.next();
            Bundle querySkus = new Bundle();
            querySkus.putStringArrayList(GET_SKU_DETAILS_ITEM_LIST, skuPartList);
            Bundle skuDetails = this.mService.getSkuDetails(3, this.mContext.getPackageName(), itemType, querySkus);
            if (!skuDetails.containsKey(RESPONSE_GET_SKU_DETAILS_LIST)) {
                int response = getResponseCodeFromBundle(skuDetails);
                if (response != 0) {
                    logDebug("getSkuDetails() failed: " + getResponseDesc(response));
                    return response;
                }
                logError("getSkuDetails() returned a bundle with neither an error nor a detail list.");
                return IABHELPER_BAD_RESPONSE;
            }
            Iterator it2 = skuDetails.getStringArrayList(RESPONSE_GET_SKU_DETAILS_LIST).iterator();
            while (it2.hasNext()) {
                SkuDetails d = new SkuDetails(itemType, (String) it2.next());
                logDebug("Got sku details: " + d);
                inv.addSkuDetails(d);
            }
        }
        return 0;
    }

    /* access modifiers changed from: 0000 */
    public void consumeAsyncInternal(List<Purchase> purchases, OnConsumeFinishedListener singleListener, OnConsumeMultiFinishedListener multiListener) throws IabAsyncInProgressException {
        final Handler handler = new Handler();
        flagStartAsync("consume");
        final List<Purchase> list = purchases;
        final OnConsumeFinishedListener onConsumeFinishedListener = singleListener;
        final OnConsumeMultiFinishedListener onConsumeMultiFinishedListener = multiListener;
        new Thread(new Runnable() {
            public void run() {
                final List<IabResult> results = new ArrayList<>();
                for (Purchase purchase : list) {
                    try {
                        IabHelper.this.consume(purchase);
                        results.add(new IabResult(0, "Successful consume of sku " + purchase.getSku()));
                    } catch (IabException ex) {
                        results.add(ex.getResult());
                    }
                }
                IabHelper.this.flagEndAsync();
                if (!IabHelper.this.mDisposed && onConsumeFinishedListener != null) {
                    handler.post(new Runnable() {
                        public void run() {
                            onConsumeFinishedListener.onConsumeFinished((Purchase) list.get(0), (IabResult) results.get(0));
                        }
                    });
                }
                if (!IabHelper.this.mDisposed && onConsumeMultiFinishedListener != null) {
                    handler.post(new Runnable() {
                        public void run() {
                            onConsumeMultiFinishedListener.onConsumeMultiFinished(list, results);
                        }
                    });
                }
            }
        }).start();
    }

    /* access modifiers changed from: 0000 */
    public void logDebug(String msg) {
        if (this.mDebugLog) {
            Log.d(this.mDebugTag, msg);
        }
    }

    /* access modifiers changed from: 0000 */
    public void logError(String msg) {
        Log.e(this.mDebugTag, "In-app billing error: " + msg);
    }

    /* access modifiers changed from: 0000 */
    public void logWarn(String msg) {
        Log.w(this.mDebugTag, "In-app billing warning: " + msg);
    }
}
