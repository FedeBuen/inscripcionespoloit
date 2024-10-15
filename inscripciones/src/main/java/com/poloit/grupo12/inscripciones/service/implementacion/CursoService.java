package com.poloit.grupo12.inscripciones.service.implementacion;

import com.poloit.grupo12.inscripciones.dto.CursoDTO;
import com.poloit.grupo12.inscripciones.enums.Rol;
import com.poloit.grupo12.inscripciones.exception.ClaveForaneaException;
import com.poloit.grupo12.inscripciones.exception.RecursoNoEncontradoException;
import com.poloit.grupo12.inscripciones.model.Curso;
import com.poloit.grupo12.inscripciones.model.Ong;
import com.poloit.grupo12.inscripciones.model.Usuario;
import com.poloit.grupo12.inscripciones.repository.ICursoRepository;
import com.poloit.grupo12.inscripciones.repository.IOngRepository;
import com.poloit.grupo12.inscripciones.repository.IUsuarioRepository;
import com.poloit.grupo12.inscripciones.service.interfaces.ICursoService;
import com.poloit.grupo12.inscripciones.validaciones.ValidarIdFormat;
import com.poloit.grupo12.inscripciones.validaciones.ValidarRol;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CursoService implements ICursoService {

    @Autowired
    private ICursoRepository cursoRepository;

    @Autowired
    private IOngRepository ongRepository;

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Override
    public Page<CursoDTO> findAll(Pageable pageable) {
        Page<Curso> cursos = cursoRepository.findAll(pageable);
        if (cursos.isEmpty())
            throw new RecursoNoEncontradoException("No se encontraron cursos en la base de datos");
        return cursos.map(this::convertToDto);
    }

    @Override
    public CursoDTO findById(String idCurso) {
        Long id = ValidarIdFormat.convertirIdALong(idCurso);
        ModelMapper mapper = new ModelMapper();
        Optional<Curso> optCurso = cursoRepository.findById(id);
        return optCurso.map(this::convertToDto).orElseThrow(()
                -> new RecursoNoEncontradoException("No se encontro curso con id: "+ id));
    }

    @Override
    public CursoDTO save(CursoDTO cursoDTO) {
        ModelMapper mapper = new ModelMapper();
        Curso curso = mapper.map(cursoDTO, Curso.class);
        Long idOng = ValidarIdFormat.convertirIdALong(cursoDTO.getOngId());
        Long idMentor = ValidarIdFormat.convertirIdALong(cursoDTO.getMentorId());
        Ong ong = ongRepository.findById(idOng).orElseThrow(() ->
                new RecursoNoEncontradoException("No se encontro ONG con el Id: " +
                        cursoDTO.getOngId()));
        Usuario mentor = usuarioRepository.findById(idMentor).orElseThrow(() ->
                new RecursoNoEncontradoException("No se encontro Usuario con el Id: " +
                        cursoDTO.getMentorId()));
        ValidarRol.validarRolAutorizado(mentor.getRol().toString(), Rol.MENTOR);
        curso.setOng(ong);
        curso.setMentor(mentor);
        Curso nuevoCurso = cursoRepository.save(curso);
        return convertToDto(nuevoCurso);
    }

    @Override
    public CursoDTO update(String id, CursoDTO cursoDTO) {
        ModelMapper mapper = new ModelMapper();
        Curso curso = mapper.map(cursoDTO, Curso.class);
        Long idCurso = ValidarIdFormat.convertirIdALong(id);
        curso.setId(idCurso);
        Long idOng = ValidarIdFormat.convertirIdALong(cursoDTO.getOngId());
        Long idMentor = ValidarIdFormat.convertirIdALong(cursoDTO.getMentorId());
        Ong ong = ongRepository.findById(idOng).orElseThrow(() ->
                new RecursoNoEncontradoException("No se encontro ONG con el Id: " +
                        cursoDTO.getOngId()));
        Usuario mentor = usuarioRepository.findById(idMentor).orElseThrow(() ->
                new RecursoNoEncontradoException("No se encontro Usuario con el Id: " +
                        cursoDTO.getMentorId()));
        ValidarRol.validarRolAutorizado(mentor.getRol().toString(), Rol.MENTOR);
        curso.setOng(ong);
        curso.setMentor(mentor);
        Curso nuevoCurso = cursoRepository.save(curso);
        return convertToDto(nuevoCurso);
    }

    @Override
    public void delete(String idCurso) {
        Long id = ValidarIdFormat.convertirIdALong(idCurso);
        Optional<Curso> curso = cursoRepository.findById(id);
        if (curso.isEmpty())
            throw new RecursoNoEncontradoException("No se encontro Curso con id: :" + id);
        try {
            cursoRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new ClaveForaneaException("No se puede borrar el curso porque tiene alumnos inscriptos");
        }
    }

    @Override
    public Page<CursoDTO> findByTituloLike(String titulo, Pageable pageable) {
        Page<Curso> cursos = cursoRepository.findByTituloLike("%" + titulo + "%", pageable);
        if (cursos.isEmpty())
            throw new RecursoNoEncontradoException("No se encontraron cursos con el titulo: " + titulo);
        return cursos.map(this::convertToDto);
    }

    private CursoDTO convertToDto(Curso curso) {
        ModelMapper mapper = new ModelMapper();
        CursoDTO cursoDTO = mapper.map(curso, CursoDTO.class);
        cursoDTO.setOngId(curso.getOng().getId().toString());
        cursoDTO.setOngNombre(curso.getOng().getNombre());
        cursoDTO.setMentorId(curso.getMentor().getId().toString());
        cursoDTO.setNombreMentor(curso.getMentor().getNombre() +
                " " + curso.getMentor().getApellido());
        return cursoDTO;
    }
}
