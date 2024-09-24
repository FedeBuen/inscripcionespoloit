package com.poloit.grupo12.inscripciones.utils;

import com.poloit.grupo12.inscripciones.exception.FechaNoValidaException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class FechaUtils {
    public static LocalDate convertirStringALocalDate(String fechaStr) {
        try {
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate fecha = LocalDate.parse(fechaStr, formato);
            return fecha;
        } catch (DateTimeParseException e) {
            throw new FechaNoValidaException("La fecha debe tener el formato yyyy-MM-dd: " + fechaStr);
        }
    }

    public static void validarFechaMenorInicio(String fechaIniStr, String fechaFinStr) {
        try {
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate fechaIni = LocalDate.parse(fechaIniStr, formato);
            LocalDate fechaFin = LocalDate.parse(fechaFinStr, formato);
            if (!fechaIni.isBefore(fechaFin)) {
                throw new FechaNoValidaException("La fecha de inicio debe ser anterior a la fecha de fin.");
            }
        } catch (DateTimeParseException e) {
            throw new FechaNoValidaException("La fecha debe tener el formato yyyy-MM-dd");
        }
    }
}
