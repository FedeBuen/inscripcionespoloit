package com.poloit.grupo12.inscripciones.validaciones;

import com.poloit.grupo12.inscripciones.exception.PasswordNoValidoException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidarPassword {
    private static final String PASSWORD_PATTERN =
            "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";

    private static final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
    public static void validarPassword(String password) {
        Matcher matcher = pattern.matcher(password);
        if (!matcher.matches()) {
            throw new PasswordNoValidoException("La contraseña no cumple con los requisitos: " +
                    "Debe tener al menos 8 caracteres, contener letras, números, " +
                    "al menos una mayúscula, y un símbolo.");
        }
    }
}
