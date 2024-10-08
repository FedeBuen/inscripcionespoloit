package com.poloit.grupo12.inscripciones.service.implementacion;

import com.poloit.grupo12.inscripciones.dto.UsuarioDTO;
import com.poloit.grupo12.inscripciones.service.interfaces.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService implements IEmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void enviarEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        message.setFrom("fdbuen@gmail.com");
        javaMailSender.send(message);
    }


    @Override
    public void enviarEmailRecuperacion(String to, String token) {
        String subject = "Recuperación de contraseña - Punto & Aprende";
        String body = "Este es un correo automático para reestablcer la contraseña\n "
                + "Copie el siguiente código de verificación para recuperar la contraseña\n"
                + token + "\n" + "\n" + "La validez del codigo de verificación es una hora.\n"
                + "Saludos de Punto & Aprende";
        enviarEmail(to, subject, body);
    }

    @Override
    public void enviarEmailUsuario(UsuarioDTO usuarioDTO) {
        String to = usuarioDTO.getEmail();
        String subject = "Bienvenido a Punto & Aprende";
        String body = "Hola " + usuarioDTO.getNombre()
                + " " + usuarioDTO.getApellido() + ".\n "
                + "Bienvenido a Punto & Aprende!!\n"
                + "Descubre nuestas ofertas de cursos y disfruta "
                + "aprender las nuevas tecnologías que te brindaran "
                + "herramientas para entrar al futuro de IT.";
        enviarEmail(to, subject, body);
    }
}
