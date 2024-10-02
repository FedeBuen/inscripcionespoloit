package com.poloit.grupo12.inscripciones.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OngDTO {

    private Long id;
    private String nombre;
    private String descripcion;
    private String logo;
    private String email;

    public OngDTO(String nombre, String descripcion, String logo, String email) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.logo = logo;
        this.email = email;
    }
}
