package com.poloit.grupo12.inscripciones.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Proyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "proyecto_seq")
    @SequenceGenerator(name = "proyecto_seq", sequenceName = "proyecto_seq", allocationSize = 1)
    private Long id;

    private String nombre;

    @Column(length = 1000)
    private String descripcion;
    private String url;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fechaCreacion;
}
