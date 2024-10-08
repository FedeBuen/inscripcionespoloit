package com.poloit.grupo12.inscripciones.service.interfaces;

import com.poloit.grupo12.inscripciones.model.TokenReseteaPassword;
import com.poloit.grupo12.inscripciones.model.Usuario;

public interface ITokenReseteaPasswordService {
    public TokenReseteaPassword generarTokenParaUsuario(Usuario usuario);
    public TokenReseteaPassword validarToken(String token);

}
