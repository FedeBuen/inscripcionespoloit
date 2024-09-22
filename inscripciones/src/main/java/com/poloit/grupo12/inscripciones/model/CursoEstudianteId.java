package com.poloit.grupo12.inscripciones.model;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class CursoEstudianteId implements Serializable {

    private Long idCurso;
    private Long idEstudiante;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CursoEstudianteId that = (CursoEstudianteId) o;
        return Objects.equals(idCurso, that.idCurso) &&
                Objects.equals(idEstudiante, that.idEstudiante);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCurso, idEstudiante);
    }
}
