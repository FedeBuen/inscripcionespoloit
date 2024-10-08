package com.poloit.grupo12.inscripciones.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TokenReseteaPassword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    @OneToOne(targetEntity = Usuario.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private Usuario usuario;

    private LocalDateTime expiracion;

    public TokenReseteaPassword(String token, Usuario usuario, LocalDateTime expiracion) {
        this.token = token;
        this.usuario = usuario;
        this.expiracion = expiracion;
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(this.expiracion);
    }
}
