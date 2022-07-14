package com.ivanov.kirill.utils;

public class DateTime {
    private final int month;
    private final int dayOfMonth;
    private final int hour;
    private final int minute;
    private final int second;

    /**
     * Simple DateTime model. Parses date in "yyyy-MM-dd'T'HH:mm:ss" format.
     * @param dateTime string representation of date and time.
     */
    public DateTime(String dateTime) {
        if (!dateTime.matches("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}")) {
            month = 1000;
            dayOfMonth = 1000;
            hour = 1000;
            minute = 1000;
            second = 1000;
            return;
        }

        String[] dateTimeStringArray = dateTime
                .replaceAll("\\D", " ")
                .split(" ");
        int[] dateTimeIntArray = new int[dateTimeStringArray.length];
        for (int i = 0; i < dateTimeStringArray.length; i++) {
            dateTimeIntArray[i] = Integer.parseInt(dateTimeStringArray[i]);
        }

        month = dateTimeIntArray[1];
        dayOfMonth = dateTimeIntArray[2];
        hour = dateTimeIntArray[3];
        minute = dateTimeIntArray[4];
        second = dateTimeIntArray[5];
    }

    /**
     * Simple check if date and time are in valid bounds.
     * @return true if valid
     */
    public Boolean validate() {
        if (1 > month || month > 12)
            return false;

        if (1 > dayOfMonth || dayOfMonth > 31)
            return false;

        return hour < 24 && minute < 60 && second < 60;
    }

    public int getMonth() {
        return month;
    }

    public int getDayOfMonth() {
        return dayOfMonth;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public int getSecond() {
        return second;
    }
}
