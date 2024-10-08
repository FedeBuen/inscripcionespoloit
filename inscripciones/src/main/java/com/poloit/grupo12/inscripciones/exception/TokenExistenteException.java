package com.poloit.grupo12.inscripciones.exception;

public class TokenExistenteException extends RuntimeException {
    public TokenExistenteException(String mensaje) {
        super(mensaje);
    }
}
