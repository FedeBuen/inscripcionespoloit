package com.poloit.grupo12.inscripciones.repository;

import com.poloit.grupo12.inscripciones.model.TokenReseteaPassword;
import com.poloit.grupo12.inscripciones.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ITokenReseteaPasswordRepository extends JpaRepository<TokenReseteaPassword, Long> {
    Optional<TokenReseteaPassword> findByToken(String token);

    Optional<TokenReseteaPassword> findByUsuario(Usuario usuario);

}
