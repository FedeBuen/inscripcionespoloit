package com.poloit.grupo12.inscripciones.service.interfaces;

import com.poloit.grupo12.inscripciones.dto.CursoEstudianteDTO;
import com.poloit.grupo12.inscripciones.dto.UsuarioDTO;
import jakarta.mail.MessagingException;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public interface IEmailService {
    public void enviarEmail(String to, String subject, String body);

    public void enviarEmailUsuario(UsuarioDTO usuarioDTO);

    public void enviarEmailRecuperacion(String to, String link);
    public void enviarEmailNoticias(String to);

    public void enviarEmailCertificado(CursoEstudianteDTO cursoEstudianteDTO,
                                       ByteArrayInputStream certificadoPdf) throws MessagingException, IOException;
}
