package com.poloit.grupo12.inscripciones.service.interfaces;

import com.poloit.grupo12.inscripciones.dto.CursoEstudianteDTO;

import java.io.ByteArrayInputStream;

public interface ICertificadoService {
    public ByteArrayInputStream generarCertificado(CursoEstudianteDTO cursoEstudianteDTO);
}
