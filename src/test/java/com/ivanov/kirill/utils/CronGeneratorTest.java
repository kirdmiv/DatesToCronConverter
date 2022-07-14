package com.ivanov.kirill.utils;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class CronGeneratorTest {

    // sample 1
    DateTime[] data1 = {
            new DateTime("2022-01-25T08:00:00"),
            new DateTime("2022-01-25T08:30:00"),
            new DateTime("2022-01-25T09:00:00"),
            new DateTime("2022-01-25T09:30:00"),
            new DateTime("2022-01-26T08:00:00"),
            new DateTime("2022-01-26T08:30:00"),
            new DateTime("2022-01-26T09:00:00"),
            new DateTime("2022-01-26T09:30:00")
    };

    @Test
    void generateOneDate() {
        DateTime[] data = { new DateTime("2022-01-25T08:10:00") };
        assertEquals("0 10 8 25 1 ?", new CronGenerator(new ArrayList<>(Arrays.asList(data))).generate());
    }

    @Test
    void generateRangeStep1() {
        DateTime[] data = {
                new DateTime("2022-01-25T08:10:00"),
                new DateTime("2022-01-25T08:10:01"),
                new DateTime("2022-01-25T08:10:02"),
                new DateTime("2022-01-25T08:10:03"),
                new DateTime("2022-01-25T08:10:04")
        };
        assertEquals("0-4 10 8 25 1 ?", new CronGenerator(new ArrayList<>(Arrays.asList(data))).generate());
    }

    @Test
    void generateRangeStep2() {
        DateTime[] data = {
                new DateTime("2022-01-25T08:10:00"),
                new DateTime("2022-01-27T08:10:00"),
                new DateTime("2022-01-29T08:10:00"),
                new DateTime("2022-01-31T08:10:00")
        };
        assertEquals("0 10 8 25/2 1 ?", new CronGenerator(new ArrayList<>(Arrays.asList(data))).generate());
    }

    @Test
    void generateSample1() {
        assertEquals("0 0,30 8,9 25,26 1 ?", new CronGenerator(new ArrayList<>(Arrays.asList(data1))).generate());
    }

    @Test
    void validate() {
        // correct data
        assertTrue(new CronGenerator(new ArrayList<>(Arrays.asList(data1))).validate(8));

        // incorrect data
        DateTime[] data2 = {
                new DateTime("2022-01-25T08:00:00"),
                new DateTime("2022-01-25T08:30:00"),
                new DateTime("2022-01-25T09:30:00"),
                new DateTime("2022-01-26T08:00:00"),
                new DateTime("2022-01-26T08:30:00"),
                new DateTime("2022-01-26T09:30:00")
        };
        assertFalse(new CronGenerator(new ArrayList<>(Arrays.asList(data2))).validate(6));
    }
}