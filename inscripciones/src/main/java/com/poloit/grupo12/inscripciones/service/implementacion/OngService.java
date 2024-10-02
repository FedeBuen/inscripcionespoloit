package com.poloit.grupo12.inscripciones.service.implementacion;

import com.poloit.grupo12.inscripciones.dto.OngDTO;
import com.poloit.grupo12.inscripciones.exception.RecursoNoEncontradoException;
import com.poloit.grupo12.inscripciones.model.Ong;
import com.poloit.grupo12.inscripciones.repository.IOngRepository;
import com.poloit.grupo12.inscripciones.service.interfaces.IOngService;
import com.poloit.grupo12.inscripciones.validaciones.ValidarEmail;
import com.poloit.grupo12.inscripciones.validaciones.ValidarIdFormat;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Optional;
@Service
public class OngService implements IOngService {
    @Autowired
    private IOngRepository repository;
    @Override
    public Page<OngDTO> findAll(Pageable pageable) {
        Page<Ong> ongs = repository.findAll(pageable);
        if (ongs.isEmpty()) {
            throw new RecursoNoEncontradoException("No se encontraron Ongs en la base de datos");
        }
        return ongs.map(this::converToDto);
    }
    @Override
    public OngDTO findById(String id) {
        ModelMapper mapper = new ModelMapper();
        Long idOng = ValidarIdFormat.convertirIdALong(id);
        Optional<Ong> optOng = repository.findById(idOng);
        if (optOng.isPresent()) {
            Ong ong = optOng.get();
            return mapper.map(ong, OngDTO.class);
        } else {
            throw new RecursoNoEncontradoException("No se encontro Ong con id: " + id);
        }
    }
    @Override
    public OngDTO save(OngDTO ongDTO) {
        ModelMapper mapper = new ModelMapper();
        ValidarEmail.validarEmail(ongDTO.getEmail());
        Ong ong = mapper.map(ongDTO, Ong.class);
        Ong newOng = repository.save(ong);
        return mapper.map(newOng, OngDTO.class);
    }
    @Override
    public OngDTO update(String id, OngDTO ongDTO) {
        ModelMapper mapper = new ModelMapper();
        Long idOng = ValidarIdFormat.convertirIdALong(id);
        Optional<Ong> optOng = repository.findById(idOng);
        if (optOng.isPresent()) {
            Ong ong = optOng.get();
            Optional.ofNullable(ongDTO.getNombre()).ifPresent(ong::setNombre);
            Optional.ofNullable(ongDTO.getDescripcion()).ifPresent(ong::setDescripcion);
            Optional.ofNullable(ongDTO.getEmail()).ifPresent(ong::setEmail);
            Optional.ofNullable(ongDTO.getLogo()).ifPresent(ong::setLogo);
            Ong newOng = repository.save(ong);
            return mapper.map(newOng, OngDTO.class);
        } else {
            throw new RecursoNoEncontradoException("No se encontro Ong con id: " + id);
        }
    }
    @Override
    public void delete(String id) {
        Long idOng = ValidarIdFormat.convertirIdALong(id);
        repository.deleteById(idOng);
    }
    private OngDTO converToDto(Ong ong) {
        ModelMapper mapper = new ModelMapper();
        OngDTO ongDTO = mapper.map(ong, OngDTO.class);
        return ongDTO;
    }
}
