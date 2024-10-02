package com.poloit.grupo12.inscripciones.service.interfaces;

import com.poloit.grupo12.inscripciones.dto.CursoEstudianteDTO;
import com.poloit.grupo12.inscripciones.model.CursoEstudianteId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICursoEstudianteService {

    public Page<CursoEstudianteDTO> findAll(Pageable pageable);
    public CursoEstudianteDTO findById(CursoEstudianteId id);
    public CursoEstudianteDTO save(CursoEstudianteDTO cursoEstudianteDTO);
    public CursoEstudianteDTO update(CursoEstudianteId id, CursoEstudianteDTO cursoEstudianteDTO);
    public void delete(CursoEstudianteId id);

}
