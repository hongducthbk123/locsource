package com.adjust.sdk;

import android.net.Uri;
import java.lang.ref.WeakReference;
import org.json.JSONObject;

public class AttributionHandler implements IAttributionHandler {
    private static final String ATTRIBUTION_TIMER_NAME = "Attribution timer";
    /* access modifiers changed from: private */
    public WeakReference<IActivityHandler> activityHandlerWeakRef;
    private ActivityPackage attributionPackage;
    private String basePath;
    private ILogger logger = AdjustFactory.getLogger();
    private boolean paused;
    private CustomScheduledExecutor scheduledExecutor = new CustomScheduledExecutor("AttributionHandler", false);
    private TimerOnce timer = new TimerOnce(new Runnable() {
        public void run() {
            AttributionHandler.this.sendAttributionRequest();
        }
    }, ATTRIBUTION_TIMER_NAME);

    public void teardown() {
        this.logger.verbose("AttributionHandler teardown", new Object[0]);
        if (this.timer != null) {
            this.timer.teardown();
        }
        if (this.scheduledExecutor != null) {
            try {
                this.scheduledExecutor.shutdownNow();
            } catch (SecurityException e) {
            }
        }
        if (this.activityHandlerWeakRef != null) {
            this.activityHandlerWeakRef.clear();
        }
        this.scheduledExecutor = null;
        this.activityHandlerWeakRef = null;
        this.logger = null;
        this.attributionPackage = null;
        this.timer = null;
    }

    public AttributionHandler(IActivityHandler activityHandler, ActivityPackage attributionPackage2, boolean startsSending) {
        this.basePath = activityHandler.getBasePath();
        init(activityHandler, attributionPackage2, startsSending);
    }

    public void init(IActivityHandler activityHandler, ActivityPackage attributionPackage2, boolean startsSending) {
        this.activityHandlerWeakRef = new WeakReference<>(activityHandler);
        this.attributionPackage = attributionPackage2;
        this.paused = !startsSending;
    }

    public void getAttribution() {
        this.scheduledExecutor.submit(new Runnable() {
            public void run() {
                AttributionHandler.this.getAttributionI(0);
            }
        });
    }

    public void checkSessionResponse(final SessionResponseData sessionResponseData) {
        this.scheduledExecutor.submit(new Runnable() {
            public void run() {
                IActivityHandler activityHandler = (IActivityHandler) AttributionHandler.this.activityHandlerWeakRef.get();
                if (activityHandler != null) {
                    AttributionHandler.this.checkSessionResponseI(activityHandler, sessionResponseData);
                }
            }
        });
    }

    public void checkSdkClickResponse(final SdkClickResponseData sdkClickResponseData) {
        this.scheduledExecutor.submit(new Runnable() {
            public void run() {
                IActivityHandler activityHandler = (IActivityHandler) AttributionHandler.this.activityHandlerWeakRef.get();
                if (activityHandler != null) {
                    AttributionHandler.this.checkSdkClickResponseI(activityHandler, sdkClickResponseData);
                }
            }
        });
    }

    public void checkAttributionResponse(final AttributionResponseData attributionResponseData) {
        this.scheduledExecutor.submit(new Runnable() {
            public void run() {
                IActivityHandler activityHandler = (IActivityHandler) AttributionHandler.this.activityHandlerWeakRef.get();
                if (activityHandler != null) {
                    AttributionHandler.this.checkAttributionResponseI(activityHandler, attributionResponseData);
                }
            }
        });
    }

    public void pauseSending() {
        this.paused = true;
    }

    public void resumeSending() {
        this.paused = false;
    }

    public void sendAttributionRequest() {
        this.scheduledExecutor.submit(new Runnable() {
            public void run() {
                AttributionHandler.this.sendAttributionRequestI();
            }
        });
    }

    /* access modifiers changed from: private */
    public void getAttributionI(long delayInMilliseconds) {
        if (this.timer.getFireIn() <= delayInMilliseconds) {
            if (delayInMilliseconds != 0) {
                this.logger.debug("Waiting to query attribution in %s seconds", Util.SecondsDisplayFormat.format(((double) delayInMilliseconds) / 1000.0d));
            }
            this.timer.startIn(delayInMilliseconds);
        }
    }

    private void checkAttributionI(IActivityHandler activityHandler, ResponseData responseData) {
        if (responseData.jsonResponse != null) {
            long timerMilliseconds = responseData.jsonResponse.optLong("ask_in", -1);
            if (timerMilliseconds >= 0) {
                activityHandler.setAskingAttribution(true);
                getAttributionI(timerMilliseconds);
                return;
            }
            activityHandler.setAskingAttribution(false);
            responseData.attribution = AdjustAttribution.fromJson(responseData.jsonResponse.optJSONObject("attribution"), responseData.adid);
        }
    }

    /* access modifiers changed from: private */
    public void checkSessionResponseI(IActivityHandler activityHandler, SessionResponseData sessionResponseData) {
        checkAttributionI(activityHandler, sessionResponseData);
        activityHandler.launchSessionResponseTasks(sessionResponseData);
    }

    /* access modifiers changed from: private */
    public void checkSdkClickResponseI(IActivityHandler activityHandler, SdkClickResponseData sdkClickResponseData) {
        checkAttributionI(activityHandler, sdkClickResponseData);
        activityHandler.launchSdkClickResponseTasks(sdkClickResponseData);
    }

    /* access modifiers changed from: private */
    public void checkAttributionResponseI(IActivityHandler activityHandler, AttributionResponseData attributionResponseData) {
        checkAttributionI(activityHandler, attributionResponseData);
        checkDeeplinkI(attributionResponseData);
        activityHandler.launchAttributionResponseTasks(attributionResponseData);
    }

    private void checkDeeplinkI(AttributionResponseData attributionResponseData) {
        if (attributionResponseData.jsonResponse != null) {
            JSONObject attributionJson = attributionResponseData.jsonResponse.optJSONObject("attribution");
            if (attributionJson != null) {
                String deeplinkString = attributionJson.optString(Constants.DEEPLINK, null);
                if (deeplinkString != null) {
                    attributionResponseData.deeplink = Uri.parse(deeplinkString);
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void sendAttributionRequestI() {
        if (this.paused) {
            this.logger.debug("Attribution handler is paused", new Object[0]);
            return;
        }
        this.logger.verbose("%s", this.attributionPackage.getExtendedString());
        try {
            ResponseData responseData = UtilNetworking.createGETHttpsURLConnection(this.attributionPackage, this.basePath);
            if (responseData instanceof AttributionResponseData) {
                checkAttributionResponse((AttributionResponseData) responseData);
            }
        } catch (Exception e) {
            this.logger.error("Failed to get attribution (%s)", e.getMessage());
        }
    }
}
