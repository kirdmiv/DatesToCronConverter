package com.ivanov.kirill.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateTimeTest {

    @Test
    void testMonthValidation() {
        // incorrect month
        DateTime badMonth = new DateTime("2022-14-07T08:00:00");
        assertFalse(badMonth.validate());
        DateTime badMonth2 = new DateTime("2022-00-07T08:00:00");
        assertFalse(badMonth2.validate());

        // correct month
        for (int month = 1; month < 13; month++) {
            DateTime goodMonth = new DateTime("2022-" + String.format("%02d", month) + "-07T08:00:00");
            assertTrue(goodMonth.validate());
        }
    }

    @Test
    void testDayValidation() {
        // incorrect day
        DateTime badDay = new DateTime("2022-11-54T08:00:00");
        assertFalse(badDay.validate());
        DateTime badDay2 = new DateTime("2022-11-00T08:00:00");
        assertFalse(badDay2.validate());

        // correct day
        for (int day = 1; day < 32; day++) {
            DateTime goodDay = new DateTime("2022-11-" + String.format("%02d", day) + "T08:00:00");
            assertTrue(goodDay.validate());
        }
    }

    @Test
    void testTimeValidation() {
        // incorrect hours
        DateTime badHour = new DateTime("2022-09-23T36:00:00");
        assertFalse(badHour.validate());
        // incorrect minute
        DateTime badMinute = new DateTime("2022-09-23T22:99:00");
        assertFalse(badMinute.validate());
        // incorrect second
        DateTime badSecond = new DateTime("2022-09-23T22:09:60");
        assertFalse(badSecond.validate());

        // correct time
        DateTime goodTime = new DateTime("2022-09-23T00:00:00");
        assertTrue(goodTime.validate());
    }

    @Test
    void testValidate() {
        // incorrect date and time
        DateTime badDateTime = new DateTime("2022-15-23T22:99:00");
        assertFalse(badDateTime.validate());

        // correct date and time
        DateTime goodDateTime = new DateTime("2022-11-23T22:30:00");
        assertTrue(goodDateTime.validate());
    }

    @Test
    void testValidateFormat() {
        DateTime badDateTime = new DateTime("2022-15-2322:99:00");
        assertFalse(badDateTime.validate());

        DateTime badDateTime2 = new DateTime("2022-15T22:99:00");
        assertFalse(badDateTime2.validate());

        DateTime badDateTime3 = new DateTime("2022-15-23T22:99");
        assertFalse(badDateTime3.validate());
    }
}