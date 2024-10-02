package com.poloit.grupo12.inscripciones;

import com.poloit.grupo12.inscripciones.dto.CursoDTO;
import com.poloit.grupo12.inscripciones.dto.CursoEstudianteDTO;
import com.poloit.grupo12.inscripciones.dto.UsuarioDTO;
import com.poloit.grupo12.inscripciones.model.*;
import com.poloit.grupo12.inscripciones.dto.OngDTO;
import com.poloit.grupo12.inscripciones.repository.*;
import com.poloit.grupo12.inscripciones.service.interfaces.ICursoEstudianteService;
import com.poloit.grupo12.inscripciones.service.interfaces.ICursoService;
import com.poloit.grupo12.inscripciones.service.interfaces.IOngService;
import com.poloit.grupo12.inscripciones.service.interfaces.IUsuarioSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    private IUsuarioRepository usuarioRepository;
    @Autowired
    private IUsuarioSevice usuarioSevice;
    @Autowired
    private IOngRepository ongRepository;
    @Autowired
    private IOngService ongService;
    @Autowired
    private IProyectoRepository proyectoRepository;

    @Autowired
    private ICursoRepository cursoRepository;
    @Autowired
    private ICursoService cursoService;
    @Autowired
    private ICursoEstudianteRepository cursoEstudianteRepository;
    @Autowired
    private ICursoEstudianteService cursoEstudianteService;

    @Override
    public void run(String... args) throws Exception {
        List<Usuario> usuarios = usuarioRepository.findAll();
        if (usuarios.isEmpty()) {
            UsuarioDTO user1 = new UsuarioDTO(
                    "Flavio",
                    "Salas",
                    "flaviosalas050@gmail.com",
                    "2000-01-25",
                    "ADMINISTRADOR",
                    "Prueba@123"
            );
            UsuarioDTO user2 = new UsuarioDTO(
                    "Fernando",
                    "Utizi",
                    "fer.utizi@gmail.com",
                    "2000-01-25",
                    "MENTOR",
                    "Prueba@123"
            );
            UsuarioDTO user3 = new UsuarioDTO(
                    "Federico",
                    "Buen",
                    "fdbuen@gmail.com.com",
                    "2000-01-25",
                    "ESTUDIANTE",
                    "Prueba@123"
            );
            usuarioSevice.save(user1);
            usuarioSevice.save(user2);
            usuarioSevice.save(user3);
        }

        List<Ong> ongs = ongRepository.findAll();
        if (ongs.isEmpty()) {
            OngDTO ong1 = new OngDTO(
                    "Talento Tech",
                    "Agencia de aprendizaje de habilidades IT para el fururo",
                    "https://www.flaticon.es/icono-gratis/programador_644617",
                    "talentotech@gmail.com"
            );
            OngDTO ong2 = new OngDTO(
                    "Argentina Programa",
                    "Ong que brinda cursos gratuitos de programacion y tecnologia",
                    "https://www.flaticon.es/icono-gratis/software_7021228",
                    "aprograma@gmail.com"
            );
            OngDTO ong3 = new OngDTO(
                    "Codo a Codo",
                    "Cursos de habilidades tecnologicas para la insercion laboral en el mundo IT",
                    "https://www.flaticon.es/icono-gratis/programacion_6062646",
                    "codoacodo@gmail.com"
            );
            ongService.save(ong1);
            ongService.save(ong2);
            ongService.save(ong3);
        }

        if (!proyectoRepository.existsById(1L)) {
            Proyecto proyecto = new Proyecto();
            proyecto.setNombre("TechPioneers");
            proyecto.setDescripcion("Plataforma que enseña desarrollo web moderno, desde HTML/CSS hasta frameworks avanzados como React y Node.js.");
            proyecto.setUrl("https://www.gstatic.com/mobilesdk/240501_mobilesdk/firebase_28dp.png");
            proyecto.setFechaCreacion(new Date());
            proyectoRepository.save(proyecto);
        }
        List<Curso> listaCursos = cursoRepository.findAll();
        if (listaCursos.isEmpty()) {
            CursoDTO cursoDTO = new CursoDTO();
            cursoDTO.setTitulo("Introducción a IA");
            cursoDTO.setDescripcion("Un curso completo de React que cubre los fundamentos hasta técnicas avanzadas.");
            cursoDTO.setUrl("https://www.codingdojo.la/wp-content/uploads/2022/07/react.jpg");
            cursoDTO.setLenguaje("Python");
            cursoDTO.setDuracion("8");
            cursoDTO.setSemanal("true");
            cursoDTO.setFechaInicio("2024-10-08");
            cursoDTO.setFechaFin("2024-12-08");
            cursoDTO.setCategoria("Python");
            cursoDTO.setOngId("2");
            cursoDTO.setMentorId("2");
            cursoService.save(cursoDTO);
        }
        List<CursoEstudiante> inscriptos = cursoEstudianteRepository.findAll();
        if(inscriptos.isEmpty()) {
            CursoEstudianteDTO cursoEstudianteDTO = new CursoEstudianteDTO("1", "3");
            cursoEstudianteService.save(cursoEstudianteDTO);
        }
    }
}


