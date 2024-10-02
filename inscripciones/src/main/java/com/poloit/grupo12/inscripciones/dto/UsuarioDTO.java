package com.poloit.grupo12.inscripciones.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.poloit.grupo12.inscripciones.enums.Rol;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UsuarioDTO {

    private Long Id;
    private String nombre;
    private String apellido;
    private String email;
    private String fechaNacimiento;
    private String rol;
    private String password;

    public UsuarioDTO(String nombre, String apellido, String email, String fechaNacimiento, String rol, String password) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
        this.rol = rol;
        this.password = password;
    }
}
