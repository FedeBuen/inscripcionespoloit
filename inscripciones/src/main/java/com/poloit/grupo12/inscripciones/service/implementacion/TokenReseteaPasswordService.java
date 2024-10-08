package com.poloit.grupo12.inscripciones.service.implementacion;

import com.poloit.grupo12.inscripciones.exception.TokenExistenteException;
import com.poloit.grupo12.inscripciones.exception.TokenNoValidoException;
import com.poloit.grupo12.inscripciones.model.TokenReseteaPassword;
import com.poloit.grupo12.inscripciones.model.Usuario;
import com.poloit.grupo12.inscripciones.repository.ITokenReseteaPasswordRepository;
import com.poloit.grupo12.inscripciones.service.interfaces.ITokenReseteaPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class TokenReseteaPasswordService implements ITokenReseteaPasswordService {
    @Autowired
    private ITokenReseteaPasswordRepository tokenReseteaPasswordRepository;

    @Override
    public TokenReseteaPassword generarTokenParaUsuario(Usuario usuario) {
        String token = UUID.randomUUID().toString();

        LocalDateTime expiracion = LocalDateTime.now().plusHours(1);
        Optional<TokenReseteaPassword> tokenReseteaPassword = tokenReseteaPasswordRepository.findByToken(token);
        Optional<TokenReseteaPassword> tokenExistente = tokenReseteaPasswordRepository.findByUsuario(usuario);
        if (tokenExistente.isPresent()) {
            tokenReseteaPasswordRepository.delete(tokenExistente.get());
        }
        TokenReseteaPassword passwordResetToken = new TokenReseteaPassword(token, usuario, expiracion);
        return tokenReseteaPasswordRepository.save(passwordResetToken);
    }
    @Override
    public TokenReseteaPassword validarToken(String token) {
        return tokenReseteaPasswordRepository.findByToken(token)
                .filter(t -> !t.isExpired())
                .orElseThrow(() -> new TokenNoValidoException("Token no válido o expirado."));
    }
}