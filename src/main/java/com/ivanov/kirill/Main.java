package com.ivanov.kirill;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println(DatesToCronConverter.getImplementationInfo());
        String[] data = {
                "2022-01-25T08:00:00",
                "2022-01-25T08:30:00",
                "2022-01-25T09:00:00",
                "2022-01-25T09:30:00",
                "2022-01-26T08:00:00",
                "2022-01-26T08:30:00",
                "2022-01-26T09:00:00",
                "2022-01-26T09:30:00"
        };
        System.out.println(DatesToCronConverter.convert(Arrays.asList(data)));
    }
}