package com.poloit.grupo12.inscripciones.repository;

import com.poloit.grupo12.inscripciones.model.CursoEstudiante;
import com.poloit.grupo12.inscripciones.model.CursoEstudianteId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICursoEstudianteRepository extends JpaRepository<CursoEstudiante, CursoEstudianteId> {
    Page<CursoEstudiante> findByCursoId(Long idCurso, Pageable pageable);

}
