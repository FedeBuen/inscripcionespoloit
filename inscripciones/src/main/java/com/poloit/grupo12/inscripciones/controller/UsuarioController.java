package com.poloit.grupo12.inscripciones.controller;

import com.poloit.grupo12.inscripciones.dto.UsuarioDTO;
import com.poloit.grupo12.inscripciones.enums.Rol;
import com.poloit.grupo12.inscripciones.exception.EmailNoValidoException;
import com.poloit.grupo12.inscripciones.exception.RolNoValidoException;
import com.poloit.grupo12.inscripciones.exception.UsuarioIdNoValidoException;
import com.poloit.grupo12.inscripciones.service.implementacion.EmailService;
import com.poloit.grupo12.inscripciones.service.implementacion.EncryptService;
import com.poloit.grupo12.inscripciones.service.implementacion.UsuarioService;
import com.poloit.grupo12.inscripciones.validaciones.ValidarRol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Optional;
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

    @GetMapping("/obtener/{id}")
    public ResponseEntity<?> findById(@PathVariable String id) {
            UsuarioDTO usuarioDTO = service.findById(id);
            return ResponseEntity.ok(usuarioDTO);
    }
    @PostMapping("/crear")
    public ResponseEntity<?> create(@RequestBody UsuarioDTO usuarioDTO) {
            usuarioDTO.setPassword(encryptService.encryptPassword(usuarioDTO.getPassword()));
            UsuarioDTO nuevoUsuario = service.save(usuarioDTO);
            //emailService.sendEmailUsuario(usuarioDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<?> update(@RequestBody UsuarioDTO usuarioDTO,
                                  @PathVariable String id) {
        try {
            Long idUsuario = Long.parseLong(id);
            UsuarioDTO usuarioEditado = service.update(idUsuario, usuarioDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioEditado);
        } catch (NumberFormatException e) {
            throw new UsuarioIdNoValidoException("ID de usuario no válido: " + id);
        }
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
            service.delete(id);
            return ResponseEntity.ok("Se elimino el usuario con el ID " + id);
    }
}

