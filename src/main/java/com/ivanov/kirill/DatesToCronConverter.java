package com.ivanov.kirill;

import com.ivanov.kirill.exceptions.DatesToCronConvertException;
import com.ivanov.kirill.utils.CronGenerator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


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

        int maxDatesInCron = 0;
        ArrayList<Integer> dateTimeIndexes = null;
        for (int mask = 1; mask < (1 << dateTimeArray.size()); mask++){
            ArrayList<Integer> indexes = extractIndexesFromMask(mask, dateTimeArray.size());
            if (indexes.size() > maxDatesInCron && checkDates(dateTimeArray, indexes)) {
                maxDatesInCron = indexes.size();
                dateTimeIndexes = indexes;
            }
        }

        if (maxDatesInCron * 2 < dateTimeArray.size() || dateTimeIndexes == null)
            throw new DatesToCronConvertException("Cannot find enough dates to generate cron");

        ArrayList<DateTime> chosenDates = new ArrayList<>();
        dateTimeIndexes.forEach(i -> chosenDates.add(dateTimeArray.get(i)));

        return new CronGenerator(chosenDates).generate();
    }

    private static Boolean checkDates(ArrayList<DateTime> dateTimeArray, ArrayList<Integer> indexes) {
        Set<Integer> seconds = new HashSet<>();
        Set<Integer> minutes = new HashSet<>();
        Set<Integer> hours = new HashSet<>();
        Set<Integer> days = new HashSet<>();
        Set<Integer> months = new HashSet<>();

        indexes.forEach(i -> {
            DateTime dateTime = dateTimeArray.get(i);
            seconds.add(dateTime.getSecond());
            minutes.add(dateTime.getMinute());
            hours.add(dateTime.getHour());
            days.add(dateTime.getDayOfMonth());
            months.add(dateTime.getMonth());
        });

        int totalCronTabs = seconds.size() * minutes.size() * hours.size() * days.size() * months.size();

        return totalCronTabs == indexes.size();
    }

    private static Boolean check(int mask, int position) {
        return ((mask >> position) & 1) == 1;
    }

    private static ArrayList<Integer> extractIndexesFromMask(int mask, int length) {
        ArrayList<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            if (check(mask, i))
                indexes.add(i);
        }
        return indexes;
    }

    public static String getImplementationInfo() {
        String fio = "Ivanov Kirill Dmitrievich";
        String className = DatesToCronConverter.class.getName();
        String packageName = DatesToCronConverter.class.getPackageName();
        String githubLink = "https://github.com/kirdmiv";
        return String.join("\n", fio, className, packageName, githubLink);
    }
}
