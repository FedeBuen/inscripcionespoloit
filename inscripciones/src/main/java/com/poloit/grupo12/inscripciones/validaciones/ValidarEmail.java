package com.poloit.grupo12.inscripciones.validaciones;

import com.poloit.grupo12.inscripciones.exception.EmailNoValidoException;

import java.util.regex.Pattern;

public class ValidarEmail {

    private static final String EMAIL_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    private static final Pattern pattern = Pattern.compile(EMAIL_REGEX);

    public static void validarEmail(String email) {
        if (!pattern.matcher(email).matches()) {
            throw new EmailNoValidoException("Correo electrónico no válido: " + email);
        }
    }
}
