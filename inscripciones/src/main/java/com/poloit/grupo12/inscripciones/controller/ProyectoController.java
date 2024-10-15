package com.poloit.grupo12.inscripciones.controller;

import com.poloit.grupo12.inscripciones.dto.CursoDTO;
import com.poloit.grupo12.inscripciones.dto.ProyectoDTO;
import com.poloit.grupo12.inscripciones.service.interfaces.IProyectoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/api/proyecto")
public class ProyectoController {

    @Autowired
    private IProyectoService service;

    @GetMapping("/listar")
    public ResponseEntity<?> findAll(Pageable pageable) {
        Page<ProyectoDTO> proyectos = service.findAll(pageable);
        return ResponseEntity.ok(proyectos);
    }

    @GetMapping("obtener/{id}")
    public ResponseEntity<?> findById(@PathVariable String id) {
        Optional<ProyectoDTO> optDTO = Optional.ofNullable(service.findById(id));
        return ResponseEntity.ok(optDTO);
    }

    @PostMapping("/crear")
    public ResponseEntity<?> create(@RequestBody ProyectoDTO proyectoDTO) {
            ProyectoDTO nuevoProyecto = service.save(proyectoDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoProyecto);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<?> update(@RequestBody ProyectoDTO proyectoDTO, @PathVariable String id) {
        ProyectoDTO proyectoEditado = service.update(id, proyectoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(proyectoEditado);
    }
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
            service.delete(id);
            String mensajeOk = "Se eliminó el proyecto con el ID " + id;
            return ResponseEntity.ok(mensajeOk);
    }

    @GetMapping("/buscar/{nombre}")
    public ResponseEntity<?> findByTituloLike(@PathVariable String nombre, Pageable pageable) {
        Page<ProyectoDTO> lista = service.findByNombreLike(nombre, pageable);
        return ResponseEntity.ok(lista);
    }
}
