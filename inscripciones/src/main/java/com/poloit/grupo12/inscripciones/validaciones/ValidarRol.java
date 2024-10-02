package com.poloit.grupo12.inscripciones.validaciones;

import com.poloit.grupo12.inscripciones.enums.Rol;
import com.poloit.grupo12.inscripciones.exception.RolNoAutorizadoException;
import com.poloit.grupo12.inscripciones.exception.RolNoValidoException;

public class ValidarRol {
    public static Rol validarRolExistente(String rol) {
        try {
            return Rol.valueOf(rol.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RolNoValidoException("Rol no valido: " + rol);
        }
    }

    public static void validarRolAutorizado(String rol, Rol autorizado) {
        if (Rol.valueOf(rol.toUpperCase()) != autorizado)
            throw new RolNoAutorizadoException("Rol requerido para esta accion: " + autorizado.name());
    }
}
