package com.poloit.grupo12.inscripciones.exception;

public class TokenNoValidoException extends RuntimeException {
    public TokenNoValidoException(String mensaje) {
        super(mensaje);
    }
}
