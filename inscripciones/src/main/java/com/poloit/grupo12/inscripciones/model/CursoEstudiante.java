package com.poloit.grupo12.inscripciones.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.poloit.grupo12.inscripciones.enums.Estado;
import jakarta.persistence.*;
import jakarta.persistence.MapsId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CursoEstudiante {

    @EmbeddedId
    private CursoEstudianteId id;

    @ManyToOne
    @MapsId("idEstudiante")
    @JoinColumn(name = "id_estudiante")
    private Usuario estudiante;

    @ManyToOne
    @MapsId("idCurso")
    @JoinColumn(name = "id_curso")
    private Curso curso;

    @Enumerated(EnumType.STRING)
    private Estado estado;

    private Double calificacion;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private LocalDate fechaInscripcion;
}
