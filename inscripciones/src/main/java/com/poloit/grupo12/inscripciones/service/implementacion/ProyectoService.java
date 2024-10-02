package com.poloit.grupo12.inscripciones.service.implementacion;

import com.poloit.grupo12.inscripciones.dto.ProyectoDTO;
import com.poloit.grupo12.inscripciones.exception.RecursoNoEncontradoException;
import com.poloit.grupo12.inscripciones.model.Proyecto;
import com.poloit.grupo12.inscripciones.repository.IProyectoRepository;
import com.poloit.grupo12.inscripciones.service.interfaces.IProyectoService;
import com.poloit.grupo12.inscripciones.validaciones.ValidarFecha;
import com.poloit.grupo12.inscripciones.validaciones.ValidarIdFormat;
import com.poloit.grupo12.inscripciones.validaciones.ValidarNombre;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProyectoService implements IProyectoService {

    @Autowired
    private IProyectoRepository proyectoRepository;

    @Override
    public Page<ProyectoDTO> findAll(Pageable pageable) {
        Page<Proyecto> proyectos = proyectoRepository.findAll(pageable);
        if (proyectos.isEmpty())
            throw new RecursoNoEncontradoException("No se encontraron proyectos en la base de datos");
        return proyectos.map(this::convertToDto);
    }

    @Override
    public ProyectoDTO findById(String id) {
        Long idProyecto = ValidarIdFormat.convertirIdALong(id);
        Optional<Proyecto> optProyecto = proyectoRepository.findById(idProyecto);
        return optProyecto.map(this::convertToDto).orElseThrow(()
                -> new RecursoNoEncontradoException("No se encontro proyecto con id: "+ id));
    }

    @Override
    public ProyectoDTO save(ProyectoDTO proyectoDTO) {
        ModelMapper mapper = new ModelMapper();
        ValidarNombre.validarNombre(proyectoDTO.getNombre());
        ValidarFecha.validarFecha(proyectoDTO.getFechaCreacion());
        Proyecto proyecto = mapper.map(proyectoDTO, Proyecto.class);
        Proyecto nuevoProyecto = proyectoRepository.save(proyecto);
        return convertToDto(nuevoProyecto);
    }

    @Override
    public ProyectoDTO update(String id, ProyectoDTO proyectoDTO) {
        ModelMapper mapper = new ModelMapper();
        Long idProyecto = ValidarIdFormat.convertirIdALong(id);
        Optional<Proyecto> optProyecto = proyectoRepository.findById(idProyecto);
        if (optProyecto.isPresent()) {
            ValidarFecha.validarFecha(proyectoDTO.getFechaCreacion());
            ValidarNombre.validarNombre(proyectoDTO.getNombre());
            Proyecto proyecto = mapper.map(proyectoDTO, Proyecto.class);
            proyecto.setId(idProyecto);
            Proyecto proyectoEditado = proyectoRepository.save(proyecto);
            return convertToDto(proyectoEditado);
        } else {
            throw new RecursoNoEncontradoException("No se encontro el proyecto con Id: " + id);
        }
    }

    @Override
    public void delete(String id) {
        Long idProyecto = ValidarIdFormat.convertirIdALong(id);
        Optional<Proyecto> optProyecto = proyectoRepository.findById(idProyecto);
        if (optProyecto.isPresent()) {
            proyectoRepository.deleteById(idProyecto);
        } else {
            throw new RecursoNoEncontradoException("No se encontro el proyecto con Id: " + id);
        }
    }

    private ProyectoDTO convertToDto(Proyecto proyecto) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(proyecto, ProyectoDTO.class);
    }
}
