package com.poloit.grupo12.inscripciones.controller;

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
   /*
    @PostMapping("/login")
    public ResponseEntity<Usuario> login(@RequestBody UsuarioDTO usuarioDTO) {

        Usuario existingUsuario = usuarioService.findByEmail(usuarioDTO.getEmail());
        if (existingUsuario != null) {
            return new ResponseEntity<>(existingUsuario, HttpStatus.OK);
        }
        return null;

        // no usar usarioService.save(usuario)
        // usar
        Usuario usuario = new Usuario(usuarioDTO.getNombre(), usuarioDTO.getApellido(), usuarioDTO.getEmail(),
                usuarioDTO.getFechaNacimiento(), Rol.valueOf(usuarioDTO.getRol().toUpperCase()), usuarioDTO.getPassword());
        Usuario savedUsuario = usuarioService.save(usuario);
        return new ResponseEntity<>(savedUsuario, HttpStatus.CREATED);
    }

    }
*/
    @GetMapping("/login")
    public ResponseEntity<?> login(@RequestBody UsuarioDTO loginDTO)  {
        Usuario usuario = usuarioService.findByEmail(loginDTO.getEmail());
        if (usuario == null) {
            String emailError = "No se encuentra registrado el email: " + loginDTO.getEmail();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(emailError);
        }
        if (encryptService.verifyPassword(loginDTO.getPassword() ,usuario.getPassword())) {
            String ok = usuario.getNombre() + " " + usuario.getApellido() +
                    ", Bienvenido al sistema";
            return ResponseEntity.status(HttpStatus.OK).body(ok);
        } else {
            String passError = usuario.getNombre() + " " + usuario.getApellido() +
                    ", Error en la clave ingresada";
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(passError);
        }
    }
}
