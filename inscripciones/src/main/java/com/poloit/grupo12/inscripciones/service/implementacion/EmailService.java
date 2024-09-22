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

    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        message.setFrom("fdbuen@gmail.com");
        javaMailSender.send(message);
    }

    @Override
    public void sendEmailUsuario(UsuarioDTO usuarioDTO) {
        String to = usuarioDTO.getEmail();
        String subject = "Bienvenido a Punto & Aprende";
        String body = "Hola " + usuarioDTO.getNombre()
                + " " + usuarioDTO.getApellido() + ".\n "
                + "Bienvenido a Punto & Aprende!!\n"
                + "Descubre nuestas ofertas de cursos y disfruta "
                + "aprender las nuevas tecnologías que te brindaran "
                + "herramientas para entrar al futuro de IT.";
        sendEmail(to, subject, body);
    }
}
