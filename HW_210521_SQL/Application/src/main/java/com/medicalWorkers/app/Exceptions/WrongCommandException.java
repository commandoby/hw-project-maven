package com.medicalWorkers.app.Exceptions;

public class WrongCommandException extends Exception{
    public WrongCommandException(String message) {
        super(message);
    }
}
