package com.poloit.grupo12.inscripciones.controller;

import com.poloit.grupo12.inscripciones.dto.CursoDTO;
import com.poloit.grupo12.inscripciones.service.implementacion.CursoService;
import com.poloit.grupo12.inscripciones.validaciones.ValidarIdFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/curso")
public class CursoController {

    @Autowired
    private CursoService service;

    @GetMapping("/listar")
    public ResponseEntity<?> findAll(Pageable pageable) {
        Page<CursoDTO> lista = service.findAll(pageable);
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/obtener/{id}")
    public ResponseEntity<?> findById(@PathVariable String id) {
        CursoDTO cursoDTO = service.findById(id);
        return ResponseEntity.ok(cursoDTO);
    }

    @PostMapping("/crear")
    public ResponseEntity<?> create(@RequestBody CursoDTO cursoDTO) {
        try {
            CursoDTO nuevoCurso = service.save(cursoDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCurso);
        } catch (Exception e) {
            e.printStackTrace();
            String mensajeError = "Ocurrió un error al crear el curso: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mensajeError);
        }
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<?> update(@RequestBody CursoDTO cursoDTO,
                                    @PathVariable String idCurso) {
        CursoDTO cursoEditado = service.update(idCurso, cursoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoEditado);

    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.ok("Se eliminó el curso con el ID " + id);
    }
}
