package com.poloit.grupo12.inscripciones.exception;

public class EmailNoValidoException extends RuntimeException {
    public EmailNoValidoException (String mensaje) {
        super(mensaje);
    }
}
