package com.poloit.grupo12.inscripciones.repository;

import com.poloit.grupo12.inscripciones.enums.Rol;
import com.poloit.grupo12.inscripciones.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByEmail(String email);
    Page<Usuario> findByRol(Rol rol, Pageable pageable);
    boolean existsByEmail(String email);
    Page<Usuario> findByNombreLike(String nombre, Pageable pageable);
}
