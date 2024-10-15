package com.poloit.grupo12.inscripciones.repository;

import com.poloit.grupo12.inscripciones.model.Proyecto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProyectoRepository extends JpaRepository<Proyecto, Long> {
    public Page<Proyecto> findByNombreLike(String nombre, Pageable pageable);
}
