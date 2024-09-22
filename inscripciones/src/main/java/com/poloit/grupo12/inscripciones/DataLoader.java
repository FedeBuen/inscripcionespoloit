package com.poloit.grupo12.inscripciones;

import com.poloit.grupo12.inscripciones.enums.Rol;
import com.poloit.grupo12.inscripciones.model.Usuario;
import com.poloit.grupo12.inscripciones.repository.IUsuarioRepository;
import com.poloit.grupo12.inscripciones.repository.IProyectoRepository;
import com.poloit.grupo12.inscripciones.repository.ICursoRepository;
import com.poloit.grupo12.inscripciones.repository.IOngRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private IProyectoRepository proyectoRepository;

    @Autowired
    private ICursoRepository cursoRepository;

    @Autowired
    private IOngRepository ongRepository;

    @Override
    public void run(String... args) throws Exception {

        // Crear usuarios si no existen
        if (!usuarioRepository.existsById(1L)) {
            Usuario adminUser = new Usuario("Flavio", "Salas", "flaviosalas050@gmail.com", new Date(),
                    Rol.ADMINISTRADOR, "123");
            usuarioRepository.save(adminUser);

        }

        if (!usuarioRepository.existsById(2L)) {
            Usuario user1 = new Usuario("Federico", "Buen", "fdbuen@gmail.com", new Date(),
                    Rol.ADMINISTRADOR, "123");
            usuarioRepository.save(user1);

        }

        if (!usuarioRepository.existsById(3L)) {
            Usuario user2 = new Usuario("Fernando", "Utizi", "fer.utizi@gmail.com", new Date(),
                    Rol.ADMINISTRADOR, "123");
            usuarioRepository.save(user2);

        }
    }
}
/*
        // Crear un nuevo proyecto
        if (!proyectoRepository.existsById(1L)) {
            Proyecto proyecto = new Proyecto();
            proyecto.setNombre("TechPioneers");
            proyecto.setDescripcion("Plataforma que enseña desarrollo web moderno, desde HTML/CSS hasta frameworks avanzados como React y Node.js.");
            proyecto.setUrl("https://www.gstatic.com/mobilesdk/240501_mobilesdk/firebase_28dp.png");
            proyecto.setFechaCreacion(new Date());

            proyectoRepository.save(proyecto);
        }

        // Crear un curso de React
        if (!cursoRepository.existsById(1L)) {
            Curso curso = new Curso();
            curso.setTitulo("Introducción a IA");
            curso.setDescripcion("Un curso completo de React que cubre los fundamentos hasta técnicas avanzadas.");
            curso.setUrl("https://www.codingdojo.la/wp-content/uploads/2022/07/react.jpg");
            curso.setLenguaje("Python");
            curso.setDuracion(8);
            curso.setSemanal(true); // Cambia el valor a un número en lugar de un booleano
            curso.setFechaInicio(new Date());
            curso.setFechaFin(new Date()); // Ajusta la fecha de fin según sea necesario
            curso.setCategoria("Python"); // Nuevo campo

            // Asignar la ONG al curso
            Ong ong = ongRepository.findById(1L).orElseGet(() -> {
                Ong newOng = new Ong();
                newOng.setNombre("ONG Test");
                newOng.setDescripcion("Descripción de prueba para la ONG.");
                newOng.setEmail("contacto@ongtest.org");
                ongRepository.save(newOng);
                return newOng;
            });

            curso.setOng(ong);

            // Asignar el mentor al curso
            Usuario mentorUser = usuarioRepository.findById(1L).orElseGet(() -> {
                Usuario newMentorUser = new Usuario("Juan", "Pérez", "juan.perez@example.com", new Date(), null);
                usuarioRepository.save(newMentorUser);
                return newMentorUser;
            });

            Mentor mentor = new Mentor();
            mentor.setUsuario(mentorUser);
            mentorRepository.save(mentor);

            curso.setMentor(mentor);

            cursoRepository.save(curso);
        }

        // Crear un nuevo Mentor
        Usuario usuario = usuarioRepository.findById(1L).orElseGet(() -> {
            Usuario newUser = new Usuario("Juan", "Pérez", "juan.perez@example.com", new Date(), null);
            usuarioRepository.save(newUser);
            return newUser;
        });

        if (!mentorRepository.existsById(1L)) {
            Mentor mentor = new Mentor();
            mentor.setId(1L); // Establecer el ID si es necesario; en general se genera automáticamente
            mentor.setUsuario(usuario);

            mentorRepository.save(mentor);
        }

        // Crear una nueva ONG
        if (!ongRepository.existsById(1L)) {
            Ong ong = new Ong();
            ong.setId(1L); // Establecer el ID si es necesario; en general se genera automáticamente
            ong.setNombre("ONG Test");
            ong.setDescripcion("Descripción de prueba para la ONG.");
            ong.setEmail("contacto@ongtest.org");

            ongRepository.save(ong);
        }
    }
 */

