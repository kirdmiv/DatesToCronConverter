package com.ivanov.kirill;

import com.ivanov.kirill.exceptions.DatesToCronConvertException;

import java.util.ArrayList;


public class DatesToCronConverter {
    DatesToCronConverter() {
    }

    public static String convert(Iterable<String> dates) throws DatesToCronConvertException {
        ArrayList<DateTime> dateTimeArray = new ArrayList<>();

        dates.forEach(date -> {
            DateTime dateTime = new DateTime(date);
            if (!dateTime.validate())
                throw new DatesToCronConvertException("Invalid date format: " + date);
            dateTimeArray.add(dateTime);
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
}
