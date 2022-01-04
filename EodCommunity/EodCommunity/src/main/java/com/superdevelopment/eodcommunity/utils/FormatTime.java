package com.superdevelopment.eodcommunity.utils;

public class FormatTime {
    public static String formatTime(long secs) {
        if(secs == 0) {
            return "None!";
        }
        long remainder = secs % 86400;

        long days 	= secs / 86400;
        long hours 	= remainder / 3600;
        long minutes	= (remainder / 60) - (hours * 60);
        long seconds	= (remainder % 3600) - (minutes * 60);

        if(days > 0) {
            String fDays = (days > 0 ? " " + days + " day" + (days > 1 ? "s" : "") : "");
            return new StringBuilder().append(fDays).toString();
        }
        if (hours > 0) {
            String fHours = (hours > 0 ? " " + hours + " hour" + (hours > 1 ? "s" : "") : "");
            return new StringBuilder().append(fHours).toString();
        }
        if(minutes > 0) {
            String fMinutes = (minutes > 0 ? " " + minutes + " minute" + (minutes > 1 ? "s" : "") : "");
            return new StringBuilder().append(fMinutes).toString();
        }
        String fSeconds = (seconds > 0 	? " " + seconds + " second"	+ (seconds > 1 ? "s" : "") 	: "");
        return new StringBuilder().append(fSeconds).toString();
    }
}
