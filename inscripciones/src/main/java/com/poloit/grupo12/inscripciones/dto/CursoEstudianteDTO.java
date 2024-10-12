package com.poloit.grupo12.inscripciones.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CursoEstudianteDTO {

    private String idCurso;
    private String idEstudiante;
    private String tituloCurso;
    private String nombreEstudiante;
    private String estado;
    private Double calificacion;
    private String fechaInscripcion;
    private String email;
    public CursoEstudianteDTO(String idCurso, String idEstudiante) {
        this.idCurso = idCurso;
        this.idEstudiante = idEstudiante;
    }

    public CursoEstudianteDTO(String idCurso, String idEstudiante, String estado, double calificacion, String fechaInscripcion) {
        this.idCurso = idCurso;
        this.idEstudiante = idEstudiante;
        this.estado = estado;
        this.calificacion = calificacion;
        this.fechaInscripcion = fechaInscripcion;
    }
}
