package com.poloit.grupo12.inscripciones.controller;

import com.poloit.grupo12.inscripciones.dto.CursoEstudianteDTO;
import com.poloit.grupo12.inscripciones.service.implementacion.CursoEstudianteService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

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
    @GetMapping("/listar/estudiantes/{idCurso}")
    public ResponseEntity<?> findByCursoId(@PathVariable String idCurso, Pageable pageable) {
        Page<CursoEstudianteDTO> lista = service.findByCursoId(idCurso, pageable);
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/obtener/{idCurso}/{idEstudiante}")
    public ResponseEntity<?> findById(@PathVariable String idCurso,
                                      @PathVariable String idEstudiante) {
        CursoEstudianteDTO cursoEstudianteDTO = service.findById(idCurso, idEstudiante);
        return ResponseEntity.ok(cursoEstudianteDTO);
    }

    @DeleteMapping("/borrar/{idCurso}/{idEstudiante}")
    public ResponseEntity<?> delete(@PathVariable String idCurso,
                                    @PathVariable String idEstudiante) {
        CursoEstudianteDTO cursoEstudianteDTO = service.findById(idCurso, idEstudiante);
        service.delete(idCurso, idEstudiante);
        return ResponseEntity.ok("Se eliminó la inscripcion del curso " +
                cursoEstudianteDTO.getTituloCurso() +
                " al estudiante " + cursoEstudianteDTO.getNombreEstudiante());
    }
    @PutMapping("/editar")
    public ResponseEntity<?> update(@RequestBody CursoEstudianteDTO cursoEstudianteDTO) throws MessagingException, IOException {
        CursoEstudianteDTO cursoEstudianteDTOActualizado = null;
        try {
            cursoEstudianteDTOActualizado = service.update(cursoEstudianteDTO);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(cursoEstudianteDTOActualizado);
    }


}
