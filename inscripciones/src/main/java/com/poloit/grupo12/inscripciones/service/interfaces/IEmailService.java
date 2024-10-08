package com.poloit.grupo12.inscripciones.service.interfaces;

import com.poloit.grupo12.inscripciones.dto.UsuarioDTO;

public interface IEmailService {
    public void enviarEmail(String to, String subject, String body);

    public void enviarEmailUsuario(UsuarioDTO usuarioDTO);

    public void enviarEmailRecuperacion(String to, String link);
}
