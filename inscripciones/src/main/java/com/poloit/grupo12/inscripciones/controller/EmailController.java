package com.poloit.grupo12.inscripciones.controller;

import com.poloit.grupo12.inscripciones.service.implementacion.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/email")
public class EmailController {

    @Autowired
    private EmailService emailService;
/*
    @GetMapping("/bienvenida")
    public String sendEmail(@RequestParam String to, @RequestParam String subject, @RequestParam String body) {
        emailService.enviarEmail(to, subject, body);
        return "Email enviado a " + to;
    }
 */
    @GetMapping("/noticias/{to}")
    public String sendEmailNoticias(@PathVariable String to) {
        emailService.enviarEmailNoticias(to);
        return "Email enviado a " + to;
    }
}
