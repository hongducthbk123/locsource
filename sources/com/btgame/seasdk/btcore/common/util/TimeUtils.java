package com.btgame.seasdk.btcore.common.util;

import com.facebook.appevents.AppEventsConstants;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.google.android.vending.expansion.downloader.Constants;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TimeUtils {
    private static SimpleDateFormat defaultSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String long2String(long time) {
        int sec = ((int) time) / 1000;
        int min = sec / 60;
        int sec2 = sec % 60;
        if (min < 10) {
            if (sec2 < 10) {
                return AppEventsConstants.EVENT_PARAM_VALUE_NO + min + ":0" + sec2;
            }
            return AppEventsConstants.EVENT_PARAM_VALUE_NO + min + ":" + sec2;
        } else if (sec2 < 10) {
            return min + ":0" + sec2;
        } else {
            return min + ":" + sec2;
        }
    }

    public static String getCurrentTime() {
        return new SimpleDateFormat("yyyyMMddHHmmss").format(Long.valueOf(System.currentTimeMillis()));
    }

    public static String getTime(long time) {
        return defaultSdf.format(Long.valueOf(time));
    }

    public static String getDate(long time) {
        return new SimpleDateFormat("yyyy-MM-dd").format(Long.valueOf(time));
    }

    public static boolean isDate(String str) {
        try {
            new SimpleDateFormat("yyyy-MM-dd").parse(str);
            return true;
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static int daysBetween(String smDate, String maxDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(sdf.parse(smDate));
            long time1 = cal.getTimeInMillis();
            cal.setTime(sdf.parse(maxDate));
            return (int) ((cal.getTimeInMillis() - time1) / 86400000);
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static int daysBetween(long smDate, long maxDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(sdf.parse(sdf.format(Long.valueOf(smDate))));
            long time1 = cal.getTimeInMillis();
            cal.setTime(sdf.parse(sdf.format(Long.valueOf(maxDate))));
            return (int) ((cal.getTimeInMillis() - time1) / 86400000);
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static String getHour(long time) {
        return new SimpleDateFormat("HH").format(Long.valueOf(time));
    }

    public static String getBeautifulCurTime() {
        return defaultSdf.format(Long.valueOf(System.currentTimeMillis()));
    }

    public static String getTime(String time, String before) {
        String show_time = null;
        if (before != null) {
            try {
                long l = defaultSdf.parse(time).getTime() - defaultSdf.parse(before).getTime();
                long day = l / 86400000;
                if (((l / Constants.WATCHDOG_WAKE_TIMER) - ((24 * day) * 60)) - (60 * ((l / 3600000) - (24 * day))) >= 1) {
                    show_time = time.substring(11);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            show_time = time.substring(11);
        }
        String getDay = getDay(time);
        if (show_time == null || getDay == null) {
            return show_time;
        }
        return getDay + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + show_time;
    }

    public static String getDay(String time) {
        try {
            long day = (defaultSdf.parse(getBeautifulCurTime()).getTime() - defaultSdf.parse(time).getTime()) / 86400000;
            if (day >= 365) {
                return time.substring(0, 10);
            }
            if (day < 1 || day >= 365) {
                return null;
            }
            return time.substring(5, 10);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
