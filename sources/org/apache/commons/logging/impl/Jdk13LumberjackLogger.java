package org.apache.commons.logging.impl;

import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import org.apache.commons.logging.Log;
import org.apache.commons.p052io.IOUtils;

public class Jdk13LumberjackLogger implements Log, Serializable {
    protected static final Level dummyLevel = Level.FINE;
    private boolean classAndMethodFound = false;
    protected transient Logger logger = null;
    protected String name = null;
    private String sourceClassName = "unknown";
    private String sourceMethodName = "unknown";

    public Jdk13LumberjackLogger(String name2) {
        this.name = name2;
        this.logger = getLogger();
    }

    private void log(Level level, String msg, Throwable ex) {
        if (getLogger().isLoggable(level)) {
            LogRecord record = new LogRecord(level, msg);
            if (!this.classAndMethodFound) {
                getClassAndMethod();
            }
            record.setSourceClassName(this.sourceClassName);
            record.setSourceMethodName(this.sourceMethodName);
            if (ex != null) {
                record.setThrown(ex);
            }
            getLogger().log(record);
        }
    }

    private void getClassAndMethod() {
        try {
            Throwable throwable = new Throwable();
            throwable.fillInStackTrace();
            StringWriter stringWriter = new StringWriter();
            throwable.printStackTrace(new PrintWriter(stringWriter));
            StringTokenizer tokenizer = new StringTokenizer(stringWriter.getBuffer().toString(), IOUtils.LINE_SEPARATOR_UNIX);
            tokenizer.nextToken();
            String line = tokenizer.nextToken();
            while (line.indexOf(getClass().getName()) == -1) {
                line = tokenizer.nextToken();
            }
            while (line.indexOf(getClass().getName()) >= 0) {
                line = tokenizer.nextToken();
            }
            String temp = line.substring(line.indexOf("at ") + 3, line.indexOf(40));
            int lastPeriod = temp.lastIndexOf(46);
            this.sourceClassName = temp.substring(0, lastPeriod);
            this.sourceMethodName = temp.substring(lastPeriod + 1);
        } catch (Exception e) {
        }
        this.classAndMethodFound = true;
    }

    public void debug(Object message) {
        log(Level.FINE, String.valueOf(message), null);
    }

    public void debug(Object message, Throwable exception) {
        log(Level.FINE, String.valueOf(message), exception);
    }

    public void error(Object message) {
        log(Level.SEVERE, String.valueOf(message), null);
    }

    public void error(Object message, Throwable exception) {
        log(Level.SEVERE, String.valueOf(message), exception);
    }

    public void fatal(Object message) {
        log(Level.SEVERE, String.valueOf(message), null);
    }

    public void fatal(Object message, Throwable exception) {
        log(Level.SEVERE, String.valueOf(message), exception);
    }

    public Logger getLogger() {
        if (this.logger == null) {
            this.logger = Logger.getLogger(this.name);
        }
        return this.logger;
    }

    public void info(Object message) {
        log(Level.INFO, String.valueOf(message), null);
    }

    public void info(Object message, Throwable exception) {
        log(Level.INFO, String.valueOf(message), exception);
    }

    public boolean isDebugEnabled() {
        return getLogger().isLoggable(Level.FINE);
    }

    public boolean isErrorEnabled() {
        return getLogger().isLoggable(Level.SEVERE);
    }

    public boolean isFatalEnabled() {
        return getLogger().isLoggable(Level.SEVERE);
    }

    public boolean isInfoEnabled() {
        return getLogger().isLoggable(Level.INFO);
    }

    public boolean isTraceEnabled() {
        return getLogger().isLoggable(Level.FINEST);
    }

    public boolean isWarnEnabled() {
        return getLogger().isLoggable(Level.WARNING);
    }

    public void trace(Object message) {
        log(Level.FINEST, String.valueOf(message), null);
    }

    public void trace(Object message, Throwable exception) {
        log(Level.FINEST, String.valueOf(message), exception);
    }

    public void warn(Object message) {
        log(Level.WARNING, String.valueOf(message), null);
    }

    public void warn(Object message, Throwable exception) {
        log(Level.WARNING, String.valueOf(message), exception);
    }
}
