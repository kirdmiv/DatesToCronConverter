package com.ivanov.kirill;

public class DateTime {
    private int year;
    private int month;
    private int dayOfMonth;
    private int hour;
    private int minute;
    private int second;

    DateTime(String dateTime) {
        String[] dateTimeStringArray = dateTime
                .replaceAll("\\D", " ")
                .split(" ");
        int[] dateTimeIntArray = new int[dateTimeStringArray.length];
        for (int i = 0; i < dateTimeStringArray.length; i++) {
            dateTimeIntArray[i] = Integer.parseInt(dateTimeStringArray[i]);
        }

        year = dateTimeIntArray[0];
        month = dateTimeIntArray[1];
        dayOfMonth = dateTimeIntArray[2];
        hour = dateTimeIntArray[3];
        minute = dateTimeIntArray[4];
        second = dateTimeIntArray[5];
    }

    public Boolean validate() {
        if (1 > month || month > 12)
            return false;

        if (1 > dayOfMonth || dayOfMonth > 31)
            return false;

        return hour < 24 && minute < 60 && second < 60;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDayOfMonth() {
        return dayOfMonth;
    }

    public void setDayOfMonth(int dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }
}
