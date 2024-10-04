package com.poloit.grupo12.inscripciones.controller;

import com.poloit.grupo12.inscripciones.dto.CursoEstudianteDTO;
import com.poloit.grupo12.inscripciones.dto.CursoEstudianteIdDTO;
import com.poloit.grupo12.inscripciones.service.implementacion.CursoEstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        CursoEstudianteDTO nuevoCursoEstudiante = service.save(cursoEstudianteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCursoEstudiante);
    }
    @GetMapping("/listar")
    public ResponseEntity<?> findAll(Pageable pageable) {
        Page<CursoEstudianteDTO> lista = service.findAll(pageable);
        return ResponseEntity.ok(lista);
    }
    @GetMapping("/obtener")
    public ResponseEntity<?> findById(@RequestBody CursoEstudianteIdDTO cursoEstudianteIdDTO) {
        CursoEstudianteDTO cursoEstudianteDTO = service.findById(cursoEstudianteIdDTO);
        return ResponseEntity.ok(cursoEstudianteDTO);
    }

    @DeleteMapping("/borrar")
    public ResponseEntity<?> delete(@RequestBody CursoEstudianteIdDTO cursoEstudianteIdDTO) {
        CursoEstudianteDTO cursoEstudianteDTO = service.findById(cursoEstudianteIdDTO);
        service.delete(cursoEstudianteIdDTO);
        return ResponseEntity.ok("Se eliminó la inscripcion del curso " +
                cursoEstudianteDTO.getTituloCurso() +
                " al estudiante " + cursoEstudianteDTO.getNombreEstudiante());
    }

    /* terminar - ver service *****
    @PutMapping("/editar")
    public ResponseEntity<?> update(@RequestBody CursoEstudianteDTO cursoEstudianteDTO) {
        CursoEstudianteDTO cursoEstudianteDTOActualizado = service.update(cursoEstudianteDTO);
        return ResponseEntity.ok(cursoEstudianteDTOActualizado);
    }
     */
}
