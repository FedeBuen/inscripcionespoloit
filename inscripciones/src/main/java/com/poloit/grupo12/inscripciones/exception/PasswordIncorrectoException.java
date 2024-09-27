package com.poloit.grupo12.inscripciones.exception;

public class PasswordIncorrectoException extends RuntimeException {
    public PasswordIncorrectoException(String mensaje) {
        super(mensaje);
    }
}
