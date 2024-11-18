package org.example.exceptions;

public class DateException extends RuntimeException {
    public DateException() {
        super("Incorrect date of birth entered!");
    }
}