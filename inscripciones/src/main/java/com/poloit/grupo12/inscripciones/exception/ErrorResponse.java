package com.poloit.grupo12.inscripciones.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
public class ErrorResponse {
    private String mensaje;
    private LocalDateTime timestamp;
    private String url;

    public ErrorResponse(String mensaje, String url) {
        this.mensaje = mensaje;
        this.timestamp = LocalDateTime.now();
        this.url = url;
    }
}
