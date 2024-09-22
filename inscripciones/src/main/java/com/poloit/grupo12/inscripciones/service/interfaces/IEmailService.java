package com.poloit.grupo12.inscripciones.service.interfaces;

import com.poloit.grupo12.inscripciones.dto.UsuarioDTO;

public interface IEmailService {
    public void sendEmail(String to, String subject, String body);

    public void sendEmailUsuario(UsuarioDTO usuarioDTO);
}
