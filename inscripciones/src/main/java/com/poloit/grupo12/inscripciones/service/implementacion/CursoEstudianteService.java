package com.poloit.grupo12.inscripciones.service.implementacion;

import com.poloit.grupo12.inscripciones.dto.CursoEstudianteDTO;
import com.poloit.grupo12.inscripciones.dto.CursoEstudianteIdDTO;
import com.poloit.grupo12.inscripciones.enums.Estado;
import com.poloit.grupo12.inscripciones.enums.Rol;
import com.poloit.grupo12.inscripciones.exception.RecursoNoEncontradoException;
import com.poloit.grupo12.inscripciones.exception.RolNoAutorizadoException;
import com.poloit.grupo12.inscripciones.model.CursoEstudiante;
import com.poloit.grupo12.inscripciones.model.CursoEstudianteId;
import com.poloit.grupo12.inscripciones.model.Curso;
import com.poloit.grupo12.inscripciones.model.Usuario;
import com.poloit.grupo12.inscripciones.repository.ICursoEstudianteRepository;
import com.poloit.grupo12.inscripciones.repository.ICursoRepository;
import com.poloit.grupo12.inscripciones.repository.IUsuarioRepository;
import com.poloit.grupo12.inscripciones.service.interfaces.ICursoEstudianteService;
import com.poloit.grupo12.inscripciones.validaciones.ValidarEstado;
import com.poloit.grupo12.inscripciones.validaciones.ValidarIdFormat;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.EnumSet;
import java.util.Optional;

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
        Page<CursoEstudiante> listaCursoEstudiante = cursoEstudianteRepository.findAll(pageable);
        if(listaCursoEstudiante.isEmpty())
            throw new RecursoNoEncontradoException("No se encotraron estudiantes inscriptos a ningun curso");
        return listaCursoEstudiante.map(this::convertToDto);
    }

    @Override
    public CursoEstudianteDTO findById(CursoEstudianteIdDTO id) {
        CursoEstudiante cursoEstudiante = getCursoEstudiante(id);
        return convertToDto(cursoEstudiante);
    }

    @Override
    public CursoEstudianteDTO save(CursoEstudianteDTO cursoEstudianteDTO) {
        CursoEstudiante cursoEstudiante = getCursoEstudiante(cursoEstudianteDTO);
        cursoEstudianteRepository.save(cursoEstudiante);
        return convertToDto(cursoEstudiante);
    }

    @Override
    public CursoEstudianteDTO update(CursoEstudianteDTO cursoEstudianteDTO) {
        CursoEstudianteIdDTO id = new CursoEstudianteIdDTO(cursoEstudianteDTO.getIdCurso(), cursoEstudianteDTO.getIdEstudiante());
        CursoEstudiante cursoEstudiante = getCursoEstudiante(id);
        Estado estado = ValidarEstado.validarEstadoExistente(String.valueOf(cursoEstudiante.getEstado()));

        CursoEstudiante cursoEstudianteActualizado = getCursoEstudiante(cursoEstudianteDTO);
        cursoEstudianteRepository.save(cursoEstudianteActualizado);
        return convertToDto(cursoEstudianteActualizado);
    }

    @Override
    public void delete(CursoEstudianteIdDTO id) {
        CursoEstudiante cursoEstudiante = getCursoEstudiante(id);
        cursoEstudianteRepository.delete(cursoEstudiante);
    }

    private CursoEstudianteDTO convertToDto(CursoEstudiante cursoEstudiante) {
        ModelMapper mapper = new ModelMapper();
        CursoEstudianteDTO cursoEstudianteDTO = mapper.map(cursoEstudiante, CursoEstudianteDTO.class);
        cursoEstudianteDTO.setIdEstudiante(cursoEstudiante.getEstudiante().getId().toString());
        cursoEstudianteDTO.setIdCurso(cursoEstudiante.getCurso().getId().toString());
        cursoEstudianteDTO.setTituloCurso(cursoEstudiante.getCurso().getTitulo());
        cursoEstudianteDTO.setNombreEstudiante(cursoEstudiante.getEstudiante()
                .getNombre() + " " + cursoEstudiante.getEstudiante()
                .getApellido());
        return cursoEstudianteDTO;
    }

    private CursoEstudiante getCursoEstudiante(CursoEstudianteDTO cursoEstudianteDTO) {
        Long idCursoL = ValidarIdFormat.convertirIdALong(cursoEstudianteDTO.getIdCurso());
        Long idEstudianteL = ValidarIdFormat.convertirIdALong(cursoEstudianteDTO.getIdEstudiante());
        Optional<Usuario> optEstudiante = estudianteRepository.findById(idEstudianteL);
        Optional<Curso> optCurso = cursoRepository.findById(idCursoL);
        Curso curso = optCurso.orElseThrow(() ->
                new RecursoNoEncontradoException("No se encontró el curso con Id: " +
                        cursoEstudianteDTO.getIdCurso()));
        Usuario estudiante = optEstudiante.orElseThrow(() ->
                new RecursoNoEncontradoException("No se encontro el usuario con Id: " +
                        cursoEstudianteDTO.getIdEstudiante()));
        EnumSet<Rol> rolesPermitidos = EnumSet.of(Rol.ESTUDIANTE, Rol.VISITANTE);
        if (!rolesPermitidos.contains(estudiante.getRol())) {
            throw new RolNoAutorizadoException("Para inscribirse a un curso el usuario debe ser VISITANTE o ESTUDIANTE");
        }
        CursoEstudianteId id = new CursoEstudianteId(idCursoL, idEstudianteL);
        return new CursoEstudiante(id, estudiante, curso, Estado.INSCRIPTO, 0.0, new Date());
    }

    private CursoEstudiante getCursoEstudiante(CursoEstudianteIdDTO id) {
        Long idCurso = ValidarIdFormat.convertirIdALong(id.getIdCurso());
        Long idEstudiante = ValidarIdFormat.convertirIdALong(id.getIdEstudiante());
        CursoEstudianteId idCursoEstudiante = new CursoEstudianteId(idCurso, idEstudiante);
        CursoEstudiante cursoEstudiante = cursoEstudianteRepository.findById(idCursoEstudiante)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "No se encontró el CursoEstudiante con idCurso: "
                                + idCurso + " y idEstudiante: " + idEstudiante));
        return cursoEstudiante;
    }
}
