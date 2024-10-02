package com.poloit.grupo12.inscripciones.validaciones;

import com.poloit.grupo12.inscripciones.exception.FechaNoValidaException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ValidarFecha {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static void validarFecha(String fecha) {
        try {
            // Intentar analizar la fecha con el formato especificado
            LocalDate.parse(fecha, DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            // Lanzar excepción personalizada si el formato de fecha es incorrecto
            throw new FechaNoValidaException("La fecha debe tener el formato yyyy-MM-dd: " + fecha);
        }
    }
}
