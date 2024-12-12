package com.biblioteca.exceptions;

public class IsbnDuplicadoException extends IllegalArgumentException {
    public IsbnDuplicadoException(String message) {
        super(message);
    }

}
