package com.poloit.grupo12.inscripciones.service.implementacion;

import com.poloit.grupo12.inscripciones.dto.CursoDTO;
import com.poloit.grupo12.inscripciones.enums.Rol;
import com.poloit.grupo12.inscripciones.model.Curso;
import com.poloit.grupo12.inscripciones.model.Ong;
import com.poloit.grupo12.inscripciones.model.Usuario;
import com.poloit.grupo12.inscripciones.repository.ICursoRepository;
import com.poloit.grupo12.inscripciones.repository.IOngRepository;
import com.poloit.grupo12.inscripciones.repository.IUsuarioRepository;
import com.poloit.grupo12.inscripciones.service.interfaces.ICursoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
        return cursos.map(this::convertToDto);
    }

    @Override
    public CursoDTO findById(Long id) {
        ModelMapper mapper = new ModelMapper();
        Optional<Curso> optCurso = cursoRepository.findById(id);
        return optCurso.map(this::convertToDto).orElse(null);
    }

    @Override
    public CursoDTO save(CursoDTO cursoDTO) {
        ModelMapper mapper = new ModelMapper();
        Curso curso = mapper.map(cursoDTO, Curso.class);

        Ong ong = ongRepository.findById(cursoDTO.getOngId()).orElse(null);
        Usuario mentor = usuarioRepository.findById(cursoDTO.getMentorId()).orElse(null);

        if (ong != null && mentor.getRol().equals(Rol.MENTOR)) {
            curso.setOng(ong);
            curso.setMentor(mentor);
            Curso nuevoCurso = cursoRepository.save(curso);
            return convertToDto(nuevoCurso);
        }

        return null; // Retorna null si hay algún problema
    }

    @Override
    public CursoDTO update(Long id, CursoDTO cursoDTO) {
        ModelMapper mapper = new ModelMapper();
        Curso curso = mapper.map(cursoDTO, Curso.class);
        curso.setId(id);

        Ong ong = ongRepository.findById(cursoDTO.getOngId()).orElse(null);
        Usuario mentor = usuarioRepository.findById(cursoDTO.getMentorId()).orElse(null);

        if (ong != null && mentor.getRol().equals(Rol.MENTOR)) {
            curso.setOng(ong);
            curso.setMentor(mentor);
            Curso nuevoCurso = cursoRepository.save(curso);
            return convertToDto(nuevoCurso);
        }

        return null; // Retorna null si hay algún problema
    }

    @Override
    public void delete(Long id) {
        cursoRepository.deleteById(id);
    }

    private CursoDTO convertToDto(Curso curso) {
        ModelMapper mapper = new ModelMapper();
        CursoDTO cursoDTO = mapper.map(curso, CursoDTO.class);
        cursoDTO.setOngId(curso.getOng().getId());
        cursoDTO.setOngNombre(curso.getOng().getNombre());
        cursoDTO.setMentorId(curso.getMentor().getId());
        cursoDTO.setNombreMentor(curso.getMentor().getNombre() +
                " " + curso.getMentor().getApellido());
        return cursoDTO;
    }
}
