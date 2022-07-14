package com.ivanov.kirill;

import com.ivanov.kirill.exceptions.DatesToCronConvertException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class DatesToCronConverterTest {

    @Test
    void convertSample1() {
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
        assertEquals("0 0,30 8,9 25,26 1 ?", DatesToCronConverter.convert(Arrays.asList(data)));
    }

    @Test
    void convertSample2() {
        String[] data = {
                "2022-01-24T19:53:00",
                "2022-01-24T19:54:00",
                "2022-01-24T19:55:00",
                "2022-01-24T19:56:00",
                "2022-01-24T19:57:00",
                "2022-01-24T19:58:00",
                "2022-01-24T19:59:00",
                "2022-01-24T20:00:00",
                "2022-01-24T20:01:00",
                "2022-01-24T20:02:00"
        };
        assertEquals("0 53-59 19 24 1 ?", DatesToCronConverter.convert(Arrays.asList(data)));
    }

    @Test
    void convert() {
        String[] data = {
                "2022-01-24T19:53:00",
                "2022-01-24T19:53:30",
                "2022-01-24T19:53:45",
                "2022-01-24T19:54:00",
                "2022-01-24T19:54:30",
                "2022-01-24T19:55:00",
                "2022-01-24T19:55:30",
                "2022-01-24T19:56:00",
                "2022-01-24T19:56:30",
                "2022-01-24T19:56:10",
                "2022-01-25T19:57:00",
        };
        assertEquals("0,30 53-56 19 24 1 ?", DatesToCronConverter.convert(Arrays.asList(data)));
    }

    @Test
    void convertInvalidFormat() {
        String[] data = {
                "2022-01-24T19:53:00",
                "2022-01-24T19:53:30",
                "2022-01-24T81:53:45",
                "2022-01-24T19:54:00",
                "22-01-24T19:70:30",
                "2022-01-2419:55:00",
                "2022-01-24T19:55:30",
                "2022-01-24T19:56:00",
                "202-01-60T19:56:30",
                "2022-1-24T19:56:10",
                "2022-01-25T19:57:00",
        };
        Throwable exception = assertThrows(
                DatesToCronConvertException.class,
                () -> DatesToCronConverter.convert(Arrays.asList(data))
        );
        assertTrue(exception.getMessage().contains("format"));
    }

    @Test
    void convertNotEnoughDates() {
        String[] data = {
                "2022-01-24T19:53:00",
                "2022-02-24T19:53:30",
                "2022-03-24T19:53:45",
                "2022-01-24T19:54:00",
                "2022-01-24T19:54:30",
                "2022-01-24T19:57:00",
                "2022-01-24T19:55:30",
                "2022-01-27T19:59:00",
                "2022-01-26T19:56:30",
                "2022-01-24T19:56:10",
                "2022-01-25T19:57:00",
        };
        Throwable exception = assertThrows(
                DatesToCronConvertException.class,
                () -> DatesToCronConverter.convert(Arrays.asList(data))
        );
        assertTrue(exception.getMessage().contains("enough"));
    }

    @Test
    void structureCheck() {
        try {
            DatesToCronConverter.class.getConstructor();
            DatesToCronConverter.class.getMethod("convert", Iterable.class);
            DatesToCronConverter.class.getMethod("getImplementationInfo");
        } catch (NoSuchMethodException e) {
            fail();
        }

    }
}