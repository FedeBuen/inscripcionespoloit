package com.poloit.grupo12.inscripciones.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CursoEstudianteDTO {

    private Long cursoId;
    private Long estudianteId;
    private String tituloCurso;
    private String nombreEstudiante;
    private String estado;
    private Double calificacion;
    private Date fechaInscripcion;
}
