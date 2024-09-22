package com.poloit.grupo12.inscripciones.service.implementacion;

import com.poloit.grupo12.inscripciones.dto.CursoEstudianteDTO;
import com.poloit.grupo12.inscripciones.enums.Estado;
import com.poloit.grupo12.inscripciones.model.CursoEstudiante;
import com.poloit.grupo12.inscripciones.model.CursoEstudianteId;
import com.poloit.grupo12.inscripciones.model.Curso;
import com.poloit.grupo12.inscripciones.model.Usuario;
import com.poloit.grupo12.inscripciones.repository.ICursoEstudianteRepository;
import com.poloit.grupo12.inscripciones.repository.ICursoRepository;
import com.poloit.grupo12.inscripciones.repository.IUsuarioRepository;
import com.poloit.grupo12.inscripciones.service.interfaces.ICursoEstudianteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CursoEstudianteService implements ICursoEstudianteService {

    @Autowired
    private IUsuarioRepository estudianteRepository;
    @Autowired
    private ICursoRepository cursoRepository;
    @Autowired
    private ICursoEstudianteRepository cursoEstudianteRepository;



    @Override
    public Page<CursoEstudianteDTO> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public CursoEstudianteDTO findById(CursoEstudianteId id) {
        return null;
    }

    @Override
    public CursoEstudianteDTO save(CursoEstudianteDTO cursoEstudianteDTO) {
        ModelMapper mapper = new ModelMapper();
        CursoEstudiante cursoEstudiante = mapper.map(cursoEstudianteDTO, CursoEstudiante.class);
        Usuario estudiante = estudianteRepository.findById(cursoEstudianteDTO.getEstudianteId())
                .orElse(null);
        Curso curso = cursoRepository.findById(cursoEstudianteDTO.getCursoId())
                .orElse(null);
        if (estudiante != null && curso != null) {
            cursoEstudiante.setCurso(curso);
            cursoEstudiante.setEstudiante(estudiante);
            cursoEstudiante.setCalificacion(0.0);
            cursoEstudiante.setEstado(Estado.INSCRIPTO);
            cursoEstudiante.setFechaInscripcion(new Date());
            cursoEstudianteRepository.save(cursoEstudiante);
            return convertToDto(cursoEstudiante);
        }
        return null;
    }

    @Override
    public CursoEstudianteDTO update(CursoEstudianteId id, CursoEstudianteDTO cursoEstudianteDTO) {
        return null;
    }

    @Override
    public void delete(CursoEstudianteId id) {

    }

    private CursoEstudianteDTO convertToDto(CursoEstudiante cursoEstudiante) {
        ModelMapper mapper = new ModelMapper();
        CursoEstudianteDTO cursoEstudianteDTO = mapper.map(cursoEstudiante, CursoEstudianteDTO.class);
        cursoEstudianteDTO.setEstudianteId(cursoEstudiante.getEstudiante().getId());
        cursoEstudianteDTO.setCursoId(cursoEstudiante.getCurso().getId());
        cursoEstudianteDTO.setTituloCurso(cursoEstudiante.getCurso().getTitulo());
        cursoEstudianteDTO.setNombreEstudiante(cursoEstudiante.getEstudiante()
                .getNombre() + " " + cursoEstudiante.getEstudiante()
                .getApellido());
        return cursoEstudianteDTO;
    }
}
