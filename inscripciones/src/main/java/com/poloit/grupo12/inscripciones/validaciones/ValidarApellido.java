package com.poloit.grupo12.inscripciones.validaciones;

import com.poloit.grupo12.inscripciones.exception.ApellidoNoValidoException;
import com.poloit.grupo12.inscripciones.exception.EmailNoValidoException;

import java.util.regex.Pattern;

public class ValidarApellido {

    private static final String APELLIDO_PATTERN = "^[A-Za-zÀ-ÿ ]{4,}$";

    private static final Pattern pattern = Pattern.compile(APELLIDO_PATTERN);

    public static void validarApellido(String apellido) {
        if (!pattern.matcher(apellido).matches()) {
            throw new ApellidoNoValidoException("El apellido " + apellido + " no es válido. " +
                    "Debe contener solo letras y espacios, con al menos 4 caracteres.");
        }
    }
}
