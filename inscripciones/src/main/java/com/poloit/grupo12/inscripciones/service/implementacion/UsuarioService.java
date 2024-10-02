package com.poloit.grupo12.inscripciones.service.implementacion;

import com.poloit.grupo12.inscripciones.dto.UsuarioDTO;
import com.poloit.grupo12.inscripciones.enums.Rol;
import com.poloit.grupo12.inscripciones.exception.EmailRepetidoException;
import com.poloit.grupo12.inscripciones.exception.RecursoNoEncontradoException;
import com.poloit.grupo12.inscripciones.model.Usuario;
import com.poloit.grupo12.inscripciones.repository.IUsuarioRepository;
import com.poloit.grupo12.inscripciones.service.interfaces.IEncryptService;
import com.poloit.grupo12.inscripciones.service.interfaces.IUsuarioSevice;
import com.poloit.grupo12.inscripciones.validaciones.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Optional;
@Service
public class UsuarioService implements IUsuarioSevice {
    @Autowired
    private IUsuarioRepository usuarioRepository;
    @Autowired
    private IEncryptService encryptService;
    @Override
    public Page<UsuarioDTO> findAll(Pageable pageable) {
        Page<Usuario> usuarios = usuarioRepository.findAll(pageable);
        if(usuarios.isEmpty())
            throw new RecursoNoEncontradoException("No se encontraron usuarios en la base de datos");
        return usuarios.map(this::convertToDto);
    }

    @Override
    public Page<UsuarioDTO> findByRol(String rolTipo, Pageable pageable) {
        Rol rol = ValidarRol.validarRolExistente(rolTipo);
        Page<Usuario> usuarios = usuarioRepository.findByRol(rol, pageable);
        if(usuarios.isEmpty())
            throw new RecursoNoEncontradoException("No se encontraron usuarios con el rol: " + rol.name());
        return usuarios.map(this::convertToDto);
    }

    @Override
    public UsuarioDTO findById(String id) {
        Long idUsuario = ValidarIdFormat.convertirIdALong(id);
        Optional<Usuario> optUsuario = usuarioRepository.findById(idUsuario);
        return optUsuario.map(this::convertToDto).orElseThrow(()
                -> new RecursoNoEncontradoException("No se encontro usuario con id: "+ id));
    }

    @Override
    public UsuarioDTO save(UsuarioDTO usuarioDTO) {
        ModelMapper mapper = new ModelMapper();
        ValidarEmail.validarEmail(usuarioDTO.getEmail());
        if (usuarioRepository.existsByEmail(usuarioDTO.getEmail()))
            throw new EmailRepetidoException("El email " + usuarioDTO.getEmail() +
                    " se encuentra registrado");
        ValidarNombre.validarNombre(usuarioDTO.getNombre());
        ValidarApellido.validarApellido(usuarioDTO.getApellido());
        ValidarRol.validarRolExistente(usuarioDTO.getRol());
        ValidarFecha.validarFecha(usuarioDTO.getFechaNacimiento());
        ValidarPassword.validarPassword(usuarioDTO.getPassword());
        usuarioDTO.setPassword(encryptService.encryptPassword(usuarioDTO.getPassword()));
        Usuario usuario = mapper.map(usuarioDTO, Usuario.class);
        Usuario nuevoUsuario = usuarioRepository.save(usuario);
        return convertToDto(nuevoUsuario);
    }

    @Override
    public UsuarioDTO update(String id, UsuarioDTO usuarioDTO) {
        ModelMapper mapper = new ModelMapper();
        Long idUsuario = ValidarIdFormat.convertirIdALong(id);
        Optional<Usuario> optUsuario = usuarioRepository.findById(idUsuario);
        if (optUsuario.isPresent()) {
            ValidarNombre.validarNombre(usuarioDTO.getNombre());
            ValidarApellido.validarApellido(usuarioDTO.getApellido());
            ValidarRol.validarRolExistente(usuarioDTO.getRol());
            ValidarFecha.validarFecha(usuarioDTO.getFechaNacimiento());
            Usuario usuario = optUsuario.get();
            Usuario usuarioActualizado = mapper.map(usuarioDTO, Usuario.class);
            usuarioActualizado.setEmail(usuario.getEmail());
            usuarioActualizado.setPassword(usuario.getPassword());
            usuarioActualizado.setId(idUsuario);
            Usuario nuevoUsuario = usuarioRepository.save(usuarioActualizado);
            return convertToDto(nuevoUsuario);
        } else {
            throw new RecursoNoEncontradoException("No se encontro usuario con id: :" + id);
        }
    }

    @Override
    public void delete(String id) {
        Long idUsuario = ValidarIdFormat.convertirIdALong(id);
        Optional<Usuario> usuario = usuarioRepository.findById(idUsuario);
        if (usuario.isPresent()) {
            usuarioRepository.deleteById(idUsuario);
        } else {
            throw new RecursoNoEncontradoException("No se encontro usuario con id: :" + id);
        }
    }

    @Override
    public Usuario findByEmail(String email) {
        ValidarEmail.validarEmail(email);
        Usuario usuario = usuarioRepository.findByEmail(email);
        if (usuario != null) {
            return usuario;
        } else {
            throw new RecursoNoEncontradoException("El email " + email + " no se encuentra" +
                    "registrado en el sistema");
        }
    }

    @Override
    public UsuarioDTO updateEmail(String id, UsuarioDTO usuarioDTO) {
        ValidarEmail.validarEmail(usuarioDTO.getEmail());
        Long idUsuario = ValidarIdFormat.convertirIdALong(id);
        Optional<Usuario> optUsuario = usuarioRepository.findById(idUsuario);
        if (optUsuario.isPresent()) {
            Usuario usuario = optUsuario.get();
            if (usuarioRepository.existsByEmail(usuarioDTO.getEmail()))
                throw new EmailRepetidoException("El email " + usuarioDTO.getEmail() +
                        " se encuentra registrado");
            usuario.setEmail(usuarioDTO.getEmail());
            Usuario nuevoUsuario = usuarioRepository.save(usuario);
            return convertToDto(nuevoUsuario);
        } else {
           throw new RecursoNoEncontradoException("No se encontro usuario con Id: " + id);
        }
    }

    @Override
    public UsuarioDTO updatePassword(String id, UsuarioDTO usuarioDTO) {
        ValidarPassword.validarPassword(usuarioDTO.getPassword());
        Long idUsuario = ValidarIdFormat.convertirIdALong(id);
        Optional<Usuario> optUsuario = usuarioRepository.findById(idUsuario);
        if (optUsuario.isPresent()) {
            Usuario usuario = optUsuario.get();
            usuario.setPassword(encryptService.encryptPassword(usuarioDTO.getPassword()));
            Usuario nuevoUsuario = usuarioRepository.save(usuario);
            return convertToDto(nuevoUsuario);
        } else {
            throw new RecursoNoEncontradoException("No se encontro usuario con Id: " + id);
        }
    }

    private UsuarioDTO convertToDto(Usuario usuario) {
        ModelMapper mapper = new ModelMapper();
        UsuarioDTO usuarioDTO = mapper.map(usuario, UsuarioDTO.class);
        usuarioDTO.setPassword("");
        return usuarioDTO;
    }
}