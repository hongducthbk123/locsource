package com.adjust.sdk;

import java.util.LinkedHashMap;
import java.util.Map;

public class AdjustEvent {
    private static ILogger logger = AdjustFactory.getLogger();
    Map<String, String> callbackParameters;
    String currency;
    String eventToken;
    String orderId;
    Map<String, String> partnerParameters;
    Double revenue;

    public AdjustEvent(String eventToken2) {
        if (checkEventToken(eventToken2, logger)) {
            this.eventToken = eventToken2;
        }
    }

    public void setRevenue(double revenue2, String currency2) {
        if (checkRevenue(Double.valueOf(revenue2), currency2)) {
            this.revenue = Double.valueOf(revenue2);
            this.currency = currency2;
        }
    }

    public void addCallbackParameter(String key, String value) {
        if (Util.isValidParameter(key, "key", "Callback") && Util.isValidParameter(value, "value", "Callback")) {
            if (this.callbackParameters == null) {
                this.callbackParameters = new LinkedHashMap();
            }
            if (((String) this.callbackParameters.put(key, value)) != null) {
                logger.warn("Key %s was overwritten", key);
            }
        }
    }

    public void addPartnerParameter(String key, String value) {
        if (Util.isValidParameter(key, "key", "Partner") && Util.isValidParameter(value, "value", "Partner")) {
            if (this.partnerParameters == null) {
                this.partnerParameters = new LinkedHashMap();
            }
            if (((String) this.partnerParameters.put(key, value)) != null) {
                logger.warn("Key %s was overwritten", key);
            }
        }
    }

    public void setOrderId(String orderId2) {
        this.orderId = orderId2;
    }

    public boolean isValid() {
        return this.eventToken != null;
    }

    private static boolean checkEventToken(String eventToken2, ILogger logger2) {
        if (eventToken2 == null) {
            logger2.error("Missing Event Token", new Object[0]);
            return false;
        } else if (eventToken2.length() == 6) {
            return true;
        } else {
            logger2.error("Malformed Event Token '%s'", eventToken2);
            return false;
        }
    }

    private boolean checkRevenue(Double revenue2, String currency2) {
        if (revenue2 != null) {
            if (revenue2.doubleValue() < 0.0d) {
                logger.error("Invalid amount %.5f", revenue2);
                return false;
            } else if (currency2 == null) {
                logger.error("Currency must be set with revenue", new Object[0]);
                return false;
            } else if (currency2.equals("")) {
                logger.error("Currency is empty", new Object[0]);
                return false;
            }
        } else if (currency2 != null) {
            logger.error("Revenue must be set with currency", new Object[0]);
            return false;
        }
        return true;
    }
}
