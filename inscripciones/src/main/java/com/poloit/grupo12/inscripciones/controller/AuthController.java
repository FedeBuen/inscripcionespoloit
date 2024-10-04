package com.poloit.grupo12.inscripciones.controller;

import com.poloit.grupo12.inscripciones.exception.PasswordIncorrectoException;
import com.poloit.grupo12.inscripciones.exception.RecursoNoEncontradoException;
import com.poloit.grupo12.inscripciones.model.Usuario;
import com.poloit.grupo12.inscripciones.service.implementacion.EncryptService;
import com.poloit.grupo12.inscripciones.service.implementacion.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.poloit.grupo12.inscripciones.dto.UsuarioDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private EncryptService encryptService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UsuarioDTO usuarioDTO)  {
        Usuario usuario = usuarioService.findByEmail(usuarioDTO.getEmail());
        if (encryptService.verifyPassword(usuarioDTO.getPassword(), usuario.getPassword())) {
            String ok = usuario.getNombre() + " " + usuario.getApellido() +
                    ", Bienvenido al sistema";
            UsuarioDTO usuarioDTOLogueado = usuarioService.findById(usuario.getId().toString());
            return ResponseEntity.status(HttpStatus.OK).body(usuarioDTOLogueado);
        } else {

            String passError = usuario.getNombre() + " " + usuario.getApellido() +
                    ", Error en la clave ingresada";
            throw new PasswordIncorrectoException(passError);
        }
    }
}
