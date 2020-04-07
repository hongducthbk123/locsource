package com.adjust.sdk;

import android.util.Log;
import java.util.Arrays;
import java.util.Locale;

public class Logger implements ILogger {
    private static String formatErrorMessage = "Error formating log message: %s, with params: %s";
    private boolean isProductionEnvironment = false;
    private LogLevel logLevel;
    private boolean logLevelLocked = false;

    public Logger() {
        setLogLevel(LogLevel.INFO, this.isProductionEnvironment);
    }

    public void setLogLevel(LogLevel logLevel2, boolean isProductionEnvironment2) {
        if (!this.logLevelLocked) {
            this.logLevel = logLevel2;
            this.isProductionEnvironment = isProductionEnvironment2;
        }
    }

    public void setLogLevelString(String logLevelString, boolean isProductionEnvironment2) {
        if (logLevelString != null) {
            try {
                setLogLevel(LogLevel.valueOf(logLevelString.toUpperCase(Locale.US)), isProductionEnvironment2);
            } catch (IllegalArgumentException e) {
                error("Malformed logLevel '%s', falling back to 'info'", logLevelString);
            }
        }
    }

    public void verbose(String message, Object... parameters) {
        if (!this.isProductionEnvironment && this.logLevel.androidLogLevel <= 2) {
            try {
                Log.v("Adjust", Util.formatString(message, parameters));
            } catch (Exception e) {
                Log.e("Adjust", Util.formatString(formatErrorMessage, message, Arrays.toString(parameters)));
            }
        }
    }

    public void debug(String message, Object... parameters) {
        if (!this.isProductionEnvironment && this.logLevel.androidLogLevel <= 3) {
            try {
                Log.d("Adjust", Util.formatString(message, parameters));
            } catch (Exception e) {
                Log.e("Adjust", Util.formatString(formatErrorMessage, message, Arrays.toString(parameters)));
            }
        }
    }

    public void info(String message, Object... parameters) {
        if (!this.isProductionEnvironment && this.logLevel.androidLogLevel <= 4) {
            try {
                Log.i("Adjust", Util.formatString(message, parameters));
            } catch (Exception e) {
                Log.e("Adjust", Util.formatString(formatErrorMessage, message, Arrays.toString(parameters)));
            }
        }
    }

    public void warn(String message, Object... parameters) {
        if (!this.isProductionEnvironment && this.logLevel.androidLogLevel <= 5) {
            try {
                Log.w("Adjust", Util.formatString(message, parameters));
            } catch (Exception e) {
                Log.e("Adjust", Util.formatString(formatErrorMessage, message, Arrays.toString(parameters)));
            }
        }
    }

    public void warnInProduction(String message, Object... parameters) {
        if (this.logLevel.androidLogLevel <= 5) {
            try {
                Log.w("Adjust", Util.formatString(message, parameters));
            } catch (Exception e) {
                Log.e("Adjust", Util.formatString(formatErrorMessage, message, Arrays.toString(parameters)));
            }
        }
    }

    public void error(String message, Object... parameters) {
        if (!this.isProductionEnvironment && this.logLevel.androidLogLevel <= 6) {
            try {
                Log.e("Adjust", Util.formatString(message, parameters));
            } catch (Exception e) {
                Log.e("Adjust", Util.formatString(formatErrorMessage, message, Arrays.toString(parameters)));
            }
        }
    }

    public void Assert(String message, Object... parameters) {
        if (!this.isProductionEnvironment && this.logLevel.androidLogLevel <= 7) {
            try {
                Log.println(7, "Adjust", Util.formatString(message, parameters));
            } catch (Exception e) {
                Log.e("Adjust", Util.formatString(formatErrorMessage, message, Arrays.toString(parameters)));
            }
        }
    }

    public void lockLogLevel() {
        this.logLevelLocked = true;
    }
}
