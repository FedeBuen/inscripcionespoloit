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

}
