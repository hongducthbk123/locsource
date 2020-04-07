package com.btgame.googlepay.util;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.btgame.googlepay.BtGooglePayManager;
import com.btgame.googlepay.util.IabBroadcastReceiver.IabBroadcastListener;
import com.btgame.googlepay.util.IabHelper.IabAsyncInProgressException;
import com.btgame.googlepay.util.IabHelper.OnConsumeFinishedListener;
import com.btgame.googlepay.util.IabHelper.OnConsumeMultiFinishedListener;
import com.btgame.googlepay.util.IabHelper.OnIabPurchaseFinishedListener;
import com.btgame.googlepay.util.IabHelper.OnIabSetupFinishedListener;
import com.btgame.googlepay.util.IabHelper.QueryInventoryFinishedListener;
import com.btgame.seasdk.btcore.common.constant.StatusCode;
import com.btgame.seasdk.btcore.common.entity.PaymentInfo;
import com.btgame.seasdk.btcore.common.entity.RoleInfo;
import com.btgame.seasdk.btcore.common.util.BTResourceUtil;
import com.btgame.seasdk.btcore.common.util.BtsdkLog;
import com.btgame.seasdk.btcore.common.util.http.OkHttpUtil;
import java.util.ArrayList;
import java.util.List;

public class InAppPurchaseProxy implements IabBroadcastListener {
    static final int RC_REQUEST = 10001;
    protected static final int RS_PAYFAILURE = 103;
    /* access modifiers changed from: private */
    public boolean isConsuming;
    /* access modifiers changed from: private */
    public boolean isSupport;
    /* access modifiers changed from: private */
    public IabBroadcastReceiver mBroadcastReceiver;
    /* access modifiers changed from: private */
    public Context mContext;
    private QueryInventoryFinishedListener mGotInventoryListener;
    /* access modifiers changed from: private */
    public IabHelper myIabHelper;
    /* access modifiers changed from: private */
    public volatile boolean purchasing;

    static void alert(Context context, String message) {
        new Builder(context).setCancelable(false).setMessage(message).setPositiveButton("OK", null).create().show();
    }

    public IabHelper getTabHelper() {
        return this.myIabHelper;
    }

    public void onCreate(Context context) {
        this.mContext = context;
        this.myIabHelper = new IabHelper(context);
        this.myIabHelper.enableDebugLogging(true);
        initGotInventoryListener();
        this.myIabHelper.startSetup(new OnIabSetupFinishedListener() {
            public void onIabSetupFinished(IabResult result) {
                BtsdkLog.m1429d("Google pay Setup finished.");
                if (!result.isSuccess()) {
                    InAppPurchaseProxy.this.isSupport = false;
                    BtsdkLog.m1429d("Problem setting up in-app billing: " + result);
                } else if (InAppPurchaseProxy.this.myIabHelper != null) {
                    InAppPurchaseProxy.this.isSupport = true;
                    InAppPurchaseProxy.this.mBroadcastReceiver = new IabBroadcastReceiver(InAppPurchaseProxy.this);
                    InAppPurchaseProxy.this.mContext.registerReceiver(InAppPurchaseProxy.this.mBroadcastReceiver, new IntentFilter(IabBroadcastReceiver.ACTION));
                    BtsdkLog.m1429d("Setup successful. Querying inventory.");
                    InAppPurchaseProxy.this.queryInventoryAsync();
                }
            }
        });
    }

    public void startPay(Activity activity, final RoleInfo roleInfo, final PaymentInfo paymentInfo) {
        String payload = OkHttpUtil.getInstance(activity).getGson().toJson((Object) paymentInfo);
        BtsdkLog.m1429d("sku:" + paymentInfo.getSku() + " payload:" + payload);
        if (!this.isSupport) {
            complain(activity, BTResourceUtil.findStringByName("tips_notsupport_googlepay"));
            BtGooglePayManager.getInstance().sendNotifyServerEvent(StatusCode.PAY_FAIL, roleInfo, paymentInfo, null);
        } else if (this.purchasing || this.myIabHelper == null) {
            BtGooglePayManager.getInstance().sendNotifyServerEvent(StatusCode.PAY_FAIL, roleInfo, paymentInfo, null);
        } else {
            this.purchasing = true;
            queryInventory();
            try {
                this.myIabHelper.launchPurchaseFlow(activity, paymentInfo.getSku(), 10001, new OnIabPurchaseFinishedListener() {
                    public void onIabPurchaseFinished(IabResult result, Purchase purchase) {
                        BtsdkLog.m1429d("Purchase finished: " + result + ", purchase: " + purchase);
                        InAppPurchaseProxy.this.purchasing = false;
                        if (!result.isFailure()) {
                            List<Purchase> purchases = new ArrayList<>();
                            purchases.add(purchase);
                            BtGooglePayManager.getInstance().sendNotifyServerEvent(StatusCode.PAY_SUCCESS, roleInfo, paymentInfo, purchases);
                        } else if (result.getResponse() == -1005) {
                            BtGooglePayManager.getInstance().sendNotifyServerEvent(StatusCode.PAY_CANCEL, roleInfo, paymentInfo, null);
                        } else {
                            BtsdkLog.m1430e("Error purchasing: " + result);
                            BtGooglePayManager.getInstance().sendNotifyServerEvent(StatusCode.PAY_FAIL, roleInfo, paymentInfo, null);
                        }
                    }
                }, payload);
            } catch (IabAsyncInProgressException e) {
                BtsdkLog.m1430e("Error IabAsyncInProgressException: " + e.getLocalizedMessage());
                this.purchasing = false;
                BtGooglePayManager.getInstance().sendNotifyServerEvent(StatusCode.PAY_FAIL, roleInfo, paymentInfo, null);
            }
        }
    }

    private void initGotInventoryListener() {
        if (this.mGotInventoryListener == null) {
            this.mGotInventoryListener = new QueryInventoryFinishedListener() {
                public void onQueryInventoryFinished(IabResult result, Inventory inventory) {
                    BtsdkLog.m1429d("Query inventory finished.");
                    if (InAppPurchaseProxy.this.myIabHelper != null) {
                        if (result.isFailure()) {
                            BtsdkLog.m1429d("Failed to query inventory: " + result);
                            return;
                        }
                        BtsdkLog.m1429d("Query inventory was successful.");
                        List<Purchase> purchases = inventory.getAllPurchases();
                        if (purchases != null) {
                            int size = purchases.size();
                            BtsdkLog.m1429d("purchases size：" + size);
                            if (size > 0) {
                                BtGooglePayManager.getInstance().sendNotifyServerEvent(StatusCode.PAY_SUCCESS, null, null, purchases);
                            }
                        }
                        BtsdkLog.m1429d("Initial inventory query finished; enabling main UI.");
                    }
                }
            };
        }
    }

    public void consumeAsync(Purchase purchase) {
        BtsdkLog.m1429d("start consumeAsync------>" + purchase);
        if (this.myIabHelper != null) {
            try {
                this.isConsuming = true;
                this.myIabHelper.consumeAsync(purchase, (OnConsumeFinishedListener) new OnConsumeFinishedListener() {
                    public void onConsumeFinished(Purchase purchase, IabResult result) {
                        InAppPurchaseProxy.this.isConsuming = false;
                        BtsdkLog.m1429d("finish consumeAsync------>" + result);
                    }
                });
            } catch (IabAsyncInProgressException e) {
                e.printStackTrace();
            }
        }
    }

    public void consumeAsync(List<Purchase> purchases) {
        BtsdkLog.m1429d("start consumesAsync------>");
        if (this.myIabHelper != null) {
            try {
                this.isConsuming = true;
                this.myIabHelper.consumeAsync(purchases, (OnConsumeMultiFinishedListener) new OnConsumeMultiFinishedListener() {
                    public void onConsumeMultiFinished(List<Purchase> purchases, List<IabResult> results) {
                        InAppPurchaseProxy.this.isConsuming = false;
                        for (int i = 0; i < purchases.size(); i++) {
                            BtsdkLog.m1429d("finish consumesAsync------>" + purchases.get(i) + results.get(i));
                        }
                    }
                });
            } catch (IabAsyncInProgressException e) {
                e.printStackTrace();
            }
        }
    }

    public void onResume() {
        if (!this.isConsuming && this.isSupport) {
            queryInventoryAsync();
        }
    }

    public void onDestroy(Context context) {
        if (this.mBroadcastReceiver != null) {
            context.unregisterReceiver(this.mBroadcastReceiver);
        }
        if (this.myIabHelper != null) {
            try {
                this.myIabHelper.dispose();
            } catch (IabAsyncInProgressException e) {
                e.printStackTrace();
            }
            this.myIabHelper = null;
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        BtsdkLog.m1429d("onActivityResult(" + requestCode + "," + resultCode + "," + data);
        if (this.myIabHelper != null && this.myIabHelper.handleActivityResult(requestCode, resultCode, data)) {
            BtsdkLog.m1429d("onActivityResult handled by IABUtil.");
        }
    }

    /* access modifiers changed from: 0000 */
    public void complain(Context context, String message) {
        alert(context, message);
    }

    private void queryInventory() {
        if (this.isSupport && this.myIabHelper != null) {
            try {
                Inventory inventory = this.myIabHelper.queryInventory();
                this.mGotInventoryListener.onQueryInventoryFinished(new IabResult(0, ""), inventory);
            } catch (IabException e) {
                BtsdkLog.m1430e("Error querying inventory. Another async operation in progress.");
            }
        }
    }

    /* access modifiers changed from: private */
    public void queryInventoryAsync() {
        if (this.isSupport && this.myIabHelper != null) {
            try {
                this.myIabHelper.queryInventoryAsync(this.mGotInventoryListener);
            } catch (IabAsyncInProgressException e) {
                BtsdkLog.m1430e("Error querying inventory. Another async operation in progress.");
            }
        }
    }

    public void receivedBroadcast() {
        BtsdkLog.m1429d("Received broadcast notification. Querying inventory.");
        queryInventoryAsync();
    }
}
