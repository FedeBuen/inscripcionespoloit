package com.poloit.grupo12.inscripciones.controller;
import com.poloit.grupo12.inscripciones.dto.CursoEstudianteDTO;
import com.poloit.grupo12.inscripciones.service.interfaces.ICertificadoService;
import com.poloit.grupo12.inscripciones.service.interfaces.IEmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@RestController
@RequestMapping("/api/certificado")
public class CertificadoController {

    @Autowired
    private ICertificadoService certificadoService;
    @Autowired
    private IEmailService emailService;

    @PostMapping("/generar")
    public ResponseEntity<byte[]> generarCertificado(@RequestBody CursoEstudianteDTO cursoEstudianteDTO) throws MessagingException, IOException {
        ByteArrayInputStream certificadoPdf = certificadoService.generarCertificado(cursoEstudianteDTO);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=certificado.pdf");
        emailService.enviarEmailCertificado(cursoEstudianteDTO, certificadoPdf);
        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(certificadoPdf.readAllBytes());

    }
}
