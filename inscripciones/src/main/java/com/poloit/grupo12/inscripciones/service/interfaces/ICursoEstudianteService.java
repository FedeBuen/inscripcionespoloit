package com.poloit.grupo12.inscripciones.service.interfaces;

import com.poloit.grupo12.inscripciones.dto.CursoEstudianteDTO;
import com.poloit.grupo12.inscripciones.dto.CursoEstudianteIdDTO;
import com.poloit.grupo12.inscripciones.model.CursoEstudianteId;
import jakarta.mail.MessagingException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;

public interface ICursoEstudianteService {

    public Page<CursoEstudianteDTO> findAll(Pageable pageable);
    public CursoEstudianteDTO findById(String idCurso, String idEstudiante);
    public CursoEstudianteDTO save(CursoEstudianteDTO cursoEstudianteDTO);
    public CursoEstudianteDTO update(CursoEstudianteDTO cursoEstudianteDTO) throws MessagingException, IOException;
    public void delete(String idCurso, String idEstudiante);
    public Page<CursoEstudianteDTO> findByCursoId(String idCurso, Pageable pageable);

}
