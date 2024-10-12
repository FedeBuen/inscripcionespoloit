package com.poloit.grupo12.inscripciones.controller;

import com.poloit.grupo12.inscripciones.dto.UsuarioDTO;
import com.poloit.grupo12.inscripciones.service.implementacion.EmailService;
import com.poloit.grupo12.inscripciones.service.implementacion.EncryptService;
import com.poloit.grupo12.inscripciones.service.implementacion.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService service;

    @Autowired
    private EmailService emailService;

    @Autowired
    private EncryptService encryptService;

    @GetMapping("/listar")
    public ResponseEntity<?> findAll(Pageable pageable) {
       Page<UsuarioDTO> lista = service.findAll(pageable);
       return ResponseEntity.ok(lista);
    }

    @GetMapping("/listar/{rol}")
    public ResponseEntity<?> findByRol(@PathVariable String rol, Pageable pageable) {
        Page<UsuarioDTO> lista = service.findByRol(rol, pageable);
        return ResponseEntity.ok(lista);
    }
    @GetMapping("/buscar/{nombre}")
    public ResponseEntity<?> findByNombreLike(@PathVariable String nombre, Pageable pageable) {
        Page<UsuarioDTO> lista = service.findByNombreLike(nombre, pageable);
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/obtener/{id}")
    public ResponseEntity<?> findById(@PathVariable String id) {
            UsuarioDTO usuarioDTO = service.findById(id);
            return ResponseEntity.ok(usuarioDTO);
    }
    @PostMapping("/crear")
    public ResponseEntity<?> create(@RequestBody UsuarioDTO usuarioDTO) {
            UsuarioDTO nuevoUsuario = service.save(usuarioDTO);
            emailService.enviarEmailUsuario(usuarioDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<?> update(@RequestBody UsuarioDTO usuarioDTO,
                                  @PathVariable String id) {
        UsuarioDTO usuario = service.update(id, usuarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
            service.delete(id);
            return ResponseEntity.ok("Se elimino el usuario con el ID " + id);
    }

    @PutMapping("/editar/email/{id}")
    public ResponseEntity<?> updateEmail(@PathVariable String id,
                                        @RequestBody UsuarioDTO usuarioDTO) {
        service.updateEmail(id, usuarioDTO);
        return ResponseEntity.ok("Se actualizo el email del usuario a: " + usuarioDTO.getEmail());
    }
    @PutMapping("/editar/password/{id}")
    public ResponseEntity<?> updatePassword(@PathVariable String id,
                                         @RequestBody UsuarioDTO usuarioDTO) {
        service.updatePassword(id, usuarioDTO);
        return ResponseEntity.ok("Se actualizo la contraseña del usuario");
    }
}

