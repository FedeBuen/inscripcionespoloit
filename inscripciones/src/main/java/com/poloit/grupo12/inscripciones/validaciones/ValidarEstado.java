package com.poloit.grupo12.inscripciones.validaciones;

import com.poloit.grupo12.inscripciones.enums.Estado;
import com.poloit.grupo12.inscripciones.exception.RolNoValidoException;

public class ValidarEstado {
    public static Estado validarEstadoExistente(String estado) {
        try {
            return Estado.valueOf(estado.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RolNoValidoException("Estado no valido: " + estado);
        }
    }
}
