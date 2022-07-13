package com.ivanov.kirill;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        DatesToCronConverter converter = new DatesToCronConverter();
        System.out.println(DatesToCronConverter.getImplementationInfo());
        List<String> dates = List.of(
                "2022-01-25T08:00:00",
                "2022-01-25T08:30:00",
                "2022-01-25T09:00:00",
                "2022-01-25T09:30:00",
                "2022-01-26T08:00:00",
                "2022-01-26T08:30:00",
                "2022-01-26T09:00:00",
                "2022-01-26T09:30:00"
        );
        System.out.println(DatesToCronConverter.convert(dates));
    }
}