package com.adjust.sdk;

import android.content.Context;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class PackageHandler implements IPackageHandler {
    private static final String PACKAGE_QUEUE_FILENAME = "AdjustIoPackageQueue";
    private static final String PACKAGE_QUEUE_NAME = "Package queue";
    private WeakReference<IActivityHandler> activityHandlerWeakRef;
    private BackoffStrategy backoffStrategy = AdjustFactory.getPackageHandlerBackoffStrategy();
    private String basePath;
    private Context context;
    /* access modifiers changed from: private */
    public AtomicBoolean isSending;
    /* access modifiers changed from: private */
    public ILogger logger = AdjustFactory.getLogger();
    private List<ActivityPackage> packageQueue;
    private boolean paused;
    private IRequestHandler requestHandler;
    private CustomScheduledExecutor scheduledExecutor = new CustomScheduledExecutor("PackageHandler", false);

    public void teardown() {
        this.logger.verbose("PackageHandler teardown", new Object[0]);
        if (this.scheduledExecutor != null) {
            try {
                this.scheduledExecutor.shutdownNow();
            } catch (SecurityException e) {
            }
        }
        if (this.activityHandlerWeakRef != null) {
            this.activityHandlerWeakRef.clear();
        }
        if (this.requestHandler != null) {
            this.requestHandler.teardown();
        }
        if (this.packageQueue != null) {
            this.packageQueue.clear();
        }
        this.scheduledExecutor = null;
        this.requestHandler = null;
        this.activityHandlerWeakRef = null;
        this.packageQueue = null;
        this.isSending = null;
        this.context = null;
        this.logger = null;
        this.backoffStrategy = null;
    }

    static void deleteState(Context context2) {
        deletePackageQueue(context2);
    }

    public PackageHandler(IActivityHandler activityHandler, Context context2, boolean startsSending) {
        init(activityHandler, context2, startsSending);
        this.scheduledExecutor.submit(new Runnable() {
            public void run() {
                PackageHandler.this.initI();
            }
        });
    }

    public void init(IActivityHandler activityHandler, Context context2, boolean startsSending) {
        this.activityHandlerWeakRef = new WeakReference<>(activityHandler);
        this.context = context2;
        this.paused = !startsSending;
        this.basePath = activityHandler.getBasePath();
    }

    public void addPackage(final ActivityPackage activityPackage) {
        this.scheduledExecutor.submit(new Runnable() {
            public void run() {
                PackageHandler.this.addI(activityPackage);
            }
        });
    }

    public void sendFirstPackage() {
        this.scheduledExecutor.submit(new Runnable() {
            public void run() {
                PackageHandler.this.sendFirstI();
            }
        });
    }

    public void sendNextPackage(ResponseData responseData) {
        this.scheduledExecutor.submit(new Runnable() {
            public void run() {
                PackageHandler.this.sendNextI();
            }
        });
        IActivityHandler activityHandler = (IActivityHandler) this.activityHandlerWeakRef.get();
        if (activityHandler != null) {
            activityHandler.finishedTrackingActivity(responseData);
        }
    }

    public void closeFirstPackage(ResponseData responseData, ActivityPackage activityPackage) {
        responseData.willRetry = true;
        IActivityHandler activityHandler = (IActivityHandler) this.activityHandlerWeakRef.get();
        if (activityHandler != null) {
            activityHandler.finishedTrackingActivity(responseData);
        }
        Runnable runnable = new Runnable() {
            public void run() {
                PackageHandler.this.logger.verbose("Package handler can send", new Object[0]);
                PackageHandler.this.isSending.set(false);
                PackageHandler.this.sendFirstPackage();
            }
        };
        if (activityPackage == null) {
            runnable.run();
            return;
        }
        int retries = activityPackage.increaseRetries();
        long waitTimeMilliSeconds = Util.getWaitingTime(retries, this.backoffStrategy);
        this.logger.verbose("Waiting for %s seconds before retrying the %d time", Util.SecondsDisplayFormat.format(((double) waitTimeMilliSeconds) / 1000.0d), Integer.valueOf(retries));
        this.scheduledExecutor.schedule(runnable, waitTimeMilliSeconds, TimeUnit.MILLISECONDS);
    }

    public void pauseSending() {
        this.paused = true;
    }

    public void resumeSending() {
        this.paused = false;
    }

    public void updatePackages(SessionParameters sessionParameters) {
        final SessionParameters sessionParametersCopy;
        if (sessionParameters != null) {
            sessionParametersCopy = sessionParameters.deepCopy();
        } else {
            sessionParametersCopy = null;
        }
        this.scheduledExecutor.submit(new Runnable() {
            public void run() {
                PackageHandler.this.updatePackagesI(sessionParametersCopy);
            }
        });
    }

    public String getBasePath() {
        return this.basePath;
    }

    /* access modifiers changed from: private */
    public void initI() {
        this.requestHandler = AdjustFactory.getRequestHandler(this);
        this.isSending = new AtomicBoolean();
        readPackageQueueI();
    }

    /* access modifiers changed from: private */
    public void addI(ActivityPackage newPackage) {
        this.packageQueue.add(newPackage);
        this.logger.debug("Added package %d (%s)", Integer.valueOf(this.packageQueue.size()), newPackage);
        this.logger.verbose("%s", newPackage.getExtendedString());
        writePackageQueueI();
    }

    /* access modifiers changed from: private */
    public void sendFirstI() {
        if (!this.packageQueue.isEmpty()) {
            if (this.paused) {
                this.logger.debug("Package handler is paused", new Object[0]);
            } else if (this.isSending.getAndSet(true)) {
                this.logger.verbose("Package handler is already sending", new Object[0]);
            } else {
                this.requestHandler.sendPackage((ActivityPackage) this.packageQueue.get(0), this.packageQueue.size() - 1);
            }
        }
    }

    /* access modifiers changed from: private */
    public void sendNextI() {
        this.packageQueue.remove(0);
        writePackageQueueI();
        this.isSending.set(false);
        this.logger.verbose("Package handler can send", new Object[0]);
        sendFirstI();
    }

    public void updatePackagesI(SessionParameters sessionParameters) {
        if (sessionParameters != null) {
            this.logger.debug("Updating package handler queue", new Object[0]);
            this.logger.verbose("Session callback parameters: %s", sessionParameters.callbackParameters);
            this.logger.verbose("Session partner parameters: %s", sessionParameters.partnerParameters);
            for (ActivityPackage activityPackage : this.packageQueue) {
                Map<String, String> parameters = activityPackage.getParameters();
                PackageBuilder.addMapJson(parameters, Constants.CALLBACK_PARAMETERS, Util.mergeParameters(sessionParameters.callbackParameters, activityPackage.getCallbackParameters(), "Callback"));
                PackageBuilder.addMapJson(parameters, Constants.PARTNER_PARAMETERS, Util.mergeParameters(sessionParameters.partnerParameters, activityPackage.getPartnerParameters(), "Partner"));
            }
            writePackageQueueI();
        }
    }

    private void readPackageQueueI() {
        try {
            this.packageQueue = (List) Util.readObject(this.context, PACKAGE_QUEUE_FILENAME, PACKAGE_QUEUE_NAME, List.class);
        } catch (Exception e) {
            this.logger.error("Failed to read %s file (%s)", PACKAGE_QUEUE_NAME, e.getMessage());
            this.packageQueue = null;
        }
        if (this.packageQueue != null) {
            this.logger.debug("Package handler read %d packages", Integer.valueOf(this.packageQueue.size()));
            return;
        }
        this.packageQueue = new ArrayList();
    }

    private void writePackageQueueI() {
        Util.writeObject(this.packageQueue, this.context, PACKAGE_QUEUE_FILENAME, PACKAGE_QUEUE_NAME);
        this.logger.debug("Package handler wrote %d packages", Integer.valueOf(this.packageQueue.size()));
    }

    public static Boolean deletePackageQueue(Context context2) {
        return Boolean.valueOf(context2.deleteFile(PACKAGE_QUEUE_FILENAME));
    }
}
