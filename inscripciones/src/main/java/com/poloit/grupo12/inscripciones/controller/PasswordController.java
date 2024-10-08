package com.poloit.grupo12.inscripciones.controller;

import com.poloit.grupo12.inscripciones.dto.PasswordDTO;
import com.poloit.grupo12.inscripciones.model.TokenReseteaPassword;
import com.poloit.grupo12.inscripciones.model.Usuario;
import com.poloit.grupo12.inscripciones.service.interfaces.IEmailService;
import com.poloit.grupo12.inscripciones.service.interfaces.ITokenReseteaPasswordService;
import com.poloit.grupo12.inscripciones.service.interfaces.IUsuarioSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/password")
public class PasswordController {

    @Autowired
    private IUsuarioSevice usuarioService;

    @Autowired
    private ITokenReseteaPasswordService tokenService;

    @Autowired
    private IEmailService emailService;

    @PostMapping("/recuperar/{email}")
    public ResponseEntity<?> solicitarRecuperacion(@PathVariable String email) {
        Usuario usuario = usuarioService.findByEmail(email);
        TokenReseteaPassword token = tokenService.generarTokenParaUsuario(usuario);
        emailService.enviarEmailRecuperacion(usuario.getEmail(), token.getToken());
        return ResponseEntity.ok("Correo de recuperación enviado.");
    }

    @PostMapping("/restablecer")
    public ResponseEntity<?> restablecerPassword(@RequestBody PasswordDTO passwordDTO) {
        TokenReseteaPassword resetToken = tokenService.validarToken(passwordDTO.getToken());
        usuarioService.restablecerPassword(resetToken.getUsuario(), passwordDTO.getNuevaPassword());
        return ResponseEntity.ok("Contraseña restablecida correctamente.");
    }
}
