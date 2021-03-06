package com.ivanov.kirill.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class CronGenerator {
    private final Set<Integer> seconds = new HashSet<>();
    private final Set<Integer> minutes = new HashSet<>();
    private final Set<Integer> hours = new HashSet<>();
    private final Set<Integer> days = new HashSet<>();
    private final Set<Integer> months = new HashSet<>();
    private final int amountOfDates;

    /**
     * Generates cron from given dates. Aimed to produce readable cron.
     * @param dates given dates.
     */
    public CronGenerator(ArrayList<DateTime> dates) {
        dates.forEach(dateTime -> {
            seconds.add(dateTime.getSecond());
            minutes.add(dateTime.getMinute());
            hours.add(dateTime.getHour());
            days.add(dateTime.getDayOfMonth());
            months.add(dateTime.getMonth());
        });
        amountOfDates = dates.size();
    }

    /**
     * Produces cron expression.
     * @return cron expression
     */
    public String generate() {
        return String.join(
                " ",
                optimizeSet(seconds, 0, 59),
                optimizeSet(minutes, 0, 59),
                optimizeSet(hours, 0, 23),
                optimizeSet(days, 1, 31),
                optimizeSet(months, 1, 12),
                "?"
        );
    }

    private static String optimizeSet(Set<Integer> values, int min, int max) {
        ArrayList<Integer> sortedValues = new ArrayList<>(values);
        Collections.sort(sortedValues);

        if (sortedValues.size() < 3)
            return sortedValues.stream().map(String::valueOf).collect(Collectors.joining(","));


        if (sortedValues.size() == 60)
            return "*";

        /*
         * Checking if it is possible to wrap content into range with step
         */
        int start = sortedValues.get(0);
        int end = sortedValues.get(sortedValues.size() - 1);
        int step = sortedValues.get(1) - sortedValues.get(0);

        if (!checkStep(sortedValues, step))
            return sortedValues.stream().map(String::valueOf).collect(Collectors.joining(","));

        return optimizeRange(start, end, step, min, max);
    }

    private static String optimizeRange(int start, int end, int step, int min, int max) {
        String result = "";
        if (start == min && end + step > max)
            result += '*';
        else if (end + step > max && step > 1)
            result += String.valueOf(start);
        else
            result += String.valueOf(start) + '-' + end;

        if (step > 1)
            result += '/' + String.valueOf(step);
        return result;
    }

    private static Boolean checkStep(ArrayList<Integer> values, int step) {
        for (int i = 0; i < values.size() - 1; i++) {
            if (values.get(i + 1) - values.get(i) != step)
                return false;
        }
        return true;
    }

    /**
     * Checks if it is possible to generate cron expression.
     * @return true if it is possible to generate cron expression.
     */
    public Boolean validate() {
        return seconds.size() * minutes.size() * hours.size() * days.size() * months.size() == amountOfDates;
    }
}
