package com.nklymok.exception;

/**
 * Custom exception for convenient and proper usage in the program module.
 */
public class FormValidationException extends RuntimeException {
    public FormValidationException(String message) {
        super(message);
    }
}
