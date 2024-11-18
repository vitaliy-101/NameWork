package org.example.exceptions;

public class InitialSizeException extends RuntimeException {
    public InitialSizeException() {
        super("The number of words in the initials is incorrect!");
    }
}