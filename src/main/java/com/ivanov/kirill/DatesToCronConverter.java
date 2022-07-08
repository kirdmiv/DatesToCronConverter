package com.ivanov.kirill;

import com.ivanov.kirill.exceptions.DatesToCronConvertException;

import java.util.Iterator;

public class DatesToCronConverter {
    DatesToCronConverter() {
    }

    public static String convert(Iterable<String> dates) throws DatesToCronConvertException {
        dates.forEach(date -> {
            if (!validateDateTime(date))
                throw new DatesToCronConvertException("Invalid date format: " + date);
        });
        return "";
    }

    public static String getImplementationInfo() {
        String fio = "Ivanov Kirill Dmitrievich";
        String className = DatesToCronConverter.class.getName();
        String packageName = DatesToCronConverter.class.getPackageName();
        String githubLink = "https://github.com/kirdmiv";
        return String.join("\n", fio, className, packageName, githubLink);
    }

    private static Boolean validateDateTime(String dateTime) {
        if (!dateTime.matches("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}"))
            return false;

        String[] dateTimeStringArray = dateTime
                .replaceAll("\\D", " ")
                .split(" ");
        int[] dateTimeIntArray = new int[dateTimeStringArray.length];
        for (int i = 0; i < dateTimeStringArray.length; i++) {
            dateTimeIntArray[i] = Integer.parseInt(dateTimeStringArray[i]);
        }

        if (1 > dateTimeIntArray[1] || dateTimeIntArray[1] > 12)
            return false;

        if (1 > dateTimeIntArray[2] || dateTimeIntArray[2] > 31)
            return false;

        return dateTimeIntArray[3] < 24 && dateTimeIntArray[4] < 60 && dateTimeIntArray[5] < 60;
    }
}
