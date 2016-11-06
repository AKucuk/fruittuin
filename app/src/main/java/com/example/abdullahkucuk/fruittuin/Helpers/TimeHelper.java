package com.example.abdullahkucuk.fruittuin.Helpers;

/**
 * Created by abdullah.kucuk on 6-11-2016.
 */

public class TimeHelper {
    public enum TimeFormat {
        SECONDS,
        MINUTES_SECONDS,
        HOURS_MINUTES_SECONDS
    }

    public static String getTimeFormatByMilliseconds(long milliseconds) {
        return getTimeFormatByMilliseconds(milliseconds, TimeFormat.MINUTES_SECONDS);
    }
    public static String getTimeFormatByMilliseconds(long milliseconds, TimeFormat timeFormat) {
        long seconds = (milliseconds / 1000) % 60;
        if(timeFormat == TimeFormat.SECONDS) {
            return String.format("%02d", seconds);
        }

        long minutes = (milliseconds / (1000 * 60)) % 60;
        if(timeFormat == TimeFormat.MINUTES_SECONDS) {
            return String.format("%02d:%02d", minutes, seconds);
        }

        long hours = (milliseconds / (1000 * 60 * 60)) % 24;

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}
