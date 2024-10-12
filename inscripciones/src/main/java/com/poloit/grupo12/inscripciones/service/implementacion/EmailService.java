package com.poloit.grupo12.inscripciones.service.implementacion;

import com.poloit.grupo12.inscripciones.dto.CursoEstudianteDTO;
import com.poloit.grupo12.inscripciones.dto.UsuarioDTO;
import org.springframework.core.io.ByteArrayResource;
import java.io.ByteArrayInputStream;
import com.poloit.grupo12.inscripciones.service.interfaces.IEmailService;
import com.poloit.grupo12.inscripciones.validaciones.ValidarEmail;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.IOException;

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

    public void enviarEmailConAdjunto(String to, String subject, String body,
                                      ByteArrayInputStream adjunto, String nombreAdjunto)
            throws MessagingException, IOException, MessagingException {
        MimeMessage mensaje = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mensaje, true);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(body);
        helper.addAttachment(nombreAdjunto, new ByteArrayResource(adjunto.readAllBytes()));
        javaMailSender.send(mensaje);
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
        ValidarEmail.validarEmail(usuarioDTO.getEmail());
        String to = usuarioDTO.getEmail();
        String subject = "Bienvenido a Punto & Aprende";
        String body = "Hola " + usuarioDTO.getNombre()
                + " " + usuarioDTO.getApellido() + ".\n "
                + "¡Bienvenido a Punto & Aprende!\n"
                + "Descubre nuestras ofertas de cursos y disfruta "
                + "aprendiendo las nuevas tecnologías que te brindarán "
                + "herramientas para entrar al futuro de IT.\n\n"
                + "¡Saludos!\n"
                + "El equipo de Punto & Aprende.";
        enviarEmail(to, subject, body);
    }

    public void enviarEmailNoticias(String to) {
        ValidarEmail.validarEmail(to);
        String subject = "Bienvenido a Punto & Aprende - Suscríbete a nuestras noticias";
        String body = "¡Bienvenido a Punto & Aprende!\n"
                + "Gracias por suscribirte a nuestro canal de noticias.\n "
                + "Te mantendremos informado de nuestras ofertas de cursos con las ultimas "
                + "tecnologías que te brindarán herramientas para entrar al futuro de IT.\n\n"
                + "¡Saludos! Esperamos que disfrutes nuestro canal.\n"
                + "El equipo de Punto & Aprende.";
        enviarEmail(to, subject, body);
    }

    public void enviarEmailCertificado(CursoEstudianteDTO cursoEstudianteDTO,
                                       ByteArrayInputStream certificadoPdf) throws MessagingException, IOException {
        String to = cursoEstudianteDTO.getEmail();
        String subject = "Punto & Aprende - Certificado de Finalización del Curso " + cursoEstudianteDTO.getTituloCurso();
        String body = "Hola " + cursoEstudianteDTO.getNombreEstudiante() + ".\n\n"
                + "¡Felicitaciones por finalizar el curso " + cursoEstudianteDTO.getTituloCurso() + "!\n"
                + "Desde el equipo de Punto & Aprende, nos complace en enviarle el certificado de aprobación del curso.\n"
                + "Esperamos que continúe su formación con nosotros.\n\n"
                + "¡Saludos!\n"
                + "El equipo de Punto & Aprende.";
        enviarEmailConAdjunto(to, subject, body, certificadoPdf, "Certificado.pdf");
    }
}
