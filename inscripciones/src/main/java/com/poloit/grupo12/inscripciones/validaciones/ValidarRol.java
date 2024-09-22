package com.poloit.grupo12.inscripciones.validaciones;

import com.poloit.grupo12.inscripciones.enums.Rol;
import com.poloit.grupo12.inscripciones.exception.RolNoValidoException;

public class ValidarRol {
    public static Rol validarRol(String rol) {
        try {
            return Rol.valueOf(rol.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RolNoValidoException("Rol no valido: " + rol);
        }
    }
}
