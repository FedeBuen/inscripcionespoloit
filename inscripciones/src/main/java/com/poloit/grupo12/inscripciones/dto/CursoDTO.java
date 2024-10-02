package com.poloit.grupo12.inscripciones.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CursoDTO {
    private Long id;
    private String titulo;
    private String descripcion;
    private String categoria; // Nuevo campo

    private String url;
    private String lenguaje;
    private String duracion; //int
    private String semanal; //boolean

    private String fechaInicio; //Date
    private String fechaFin; //Date
    private String ongId; //Long
    private String ongNombre;
    private String mentorId; //Long
    private String nombreMentor;
}
