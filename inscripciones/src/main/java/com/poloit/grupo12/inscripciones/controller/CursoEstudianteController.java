package com.poloit.grupo12.inscripciones.controller;

import com.poloit.grupo12.inscripciones.dto.CursoEstudianteDTO;
import com.poloit.grupo12.inscripciones.dto.EstudianteDTO;
import com.poloit.grupo12.inscripciones.service.implementacion.CursoEstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inscripcion/curso")
public class CursoEstudianteController {
    @Autowired
    private CursoEstudianteService service;
    @PostMapping("/crear")
    public ResponseEntity<?> create(@RequestBody CursoEstudianteDTO cursoEstudianteDTO) {
        try {
            CursoEstudianteDTO nuevoCursoEstudiante = service.save(cursoEstudianteDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCursoEstudiante);
        } catch (Exception e) {
            String mensajeError = "Ocurrió un error al crear el estudiante";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mensajeError);
        }
    }
}
