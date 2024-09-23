package com.poloit.grupo12.inscripciones.controller;

import com.poloit.grupo12.inscripciones.dto.OngDTO;
import com.poloit.grupo12.inscripciones.service.interfaces.IOngService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
@RestController
@RequestMapping("/api/ong")
public class OngController {
    @Autowired
    private IOngService service;

    @GetMapping("/listar")
    public ResponseEntity<?> findAll(Pageable pageable) {
        Page<OngDTO> lista = service.findAll(pageable);
        return ResponseEntity.ok(lista);
    }

    @GetMapping("obtener/{id}")
    public ResponseEntity<?> findById(@PathVariable String id) {
        Optional<OngDTO> opt = Optional.ofNullable(service.findById(id));
        return ResponseEntity.ok(opt.get());

    }

    @PostMapping("/crear")
    public ResponseEntity<?> create(@RequestBody OngDTO ongDTO) {
        OngDTO nuevaOng = service.save(ongDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaOng);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<?> update(@RequestBody OngDTO ongDTO,
                                    @PathVariable String id) {
        if (service.findById(id) != null) {
            OngDTO OngEditada = service.update(id, ongDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(OngEditada);
        } else {
            String mensajeError = "No se encontro la ONG con ID " + id;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensajeError);
        }
    }
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        if (service.findById(id) != null) {
            service.delete(id);
            String mensajeOk = "Se elimino la ONG con ID " + id;
            return ResponseEntity.ok(mensajeOk);

        } else {
            String mensajeError = "No se encontro la ONG con ID " + id;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensajeError);
        }
    }

}
