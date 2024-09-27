package com.poloit.grupo12.inscripciones.validaciones;

import com.poloit.grupo12.inscripciones.exception.EmailNoValidoException;
import com.poloit.grupo12.inscripciones.exception.NombreNoValidoException;

import java.util.regex.Pattern;

public class ValidarNombre {

    private static final String NOMBRE_PATTERN = "^[A-Za-zÀ-ÿ ]{4,}$";

    private static final Pattern pattern = Pattern.compile(NOMBRE_PATTERN);

    public static void validarNombre(String nombre) {
        if (!pattern.matcher(nombre).matches()) {
            throw new NombreNoValidoException("El nombre " + nombre + " no es válido. " +
                    "Debe contener solo letras y espacios, con al menos 4 caracteres.");
        }
    }
}