package com.poloit.grupo12.inscripciones.controller;

import com.poloit.grupo12.inscripciones.service.implementacion.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    @GetMapping("/send-email")
    public String sendEmail(@RequestParam String to, @RequestParam String subject, @RequestParam String body) {
        emailService.enviarEmail(to, subject, body);
        return "Email enviado a " + to;
    }
}
