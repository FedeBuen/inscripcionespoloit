package com.poloit.grupo12.inscripciones.validaciones;

import com.poloit.grupo12.inscripciones.exception.IdNoValidoException;

public class ValidarIdFormat {
    public static Long convertirIdALong(String id) {
        try {
            return Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new IdNoValidoException("Id no valido: " + id);
        }
    }
}
