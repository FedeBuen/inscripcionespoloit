package com.poloit.grupo12.inscripciones.exception;

public class PasswordNoValidoException extends RuntimeException {
    public PasswordNoValidoException(String mensaje) {
        super(mensaje);
    }
}
