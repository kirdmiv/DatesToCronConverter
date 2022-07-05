package com.ivanov.kirill.exceptions;

public class DatesToCronConvertException extends RuntimeException {
    public DatesToCronConvertException(String errorMessage) {
        super(errorMessage);
    }

    public DatesToCronConvertException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
