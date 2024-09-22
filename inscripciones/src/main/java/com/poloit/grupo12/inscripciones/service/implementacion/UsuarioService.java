package com.poloit.grupo12.inscripciones.service.implementacion;

import com.poloit.grupo12.inscripciones.dto.UsuarioDTO;
import com.poloit.grupo12.inscripciones.enums.Rol;
import com.poloit.grupo12.inscripciones.exception.EmailRepetidoException;
import com.poloit.grupo12.inscripciones.exception.IdNoValidoException;
import com.poloit.grupo12.inscripciones.exception.RecursoNoEncontradoException;
import com.poloit.grupo12.inscripciones.model.Usuario;
import com.poloit.grupo12.inscripciones.repository.IUsuarioRepository;
import com.poloit.grupo12.inscripciones.service.interfaces.IUsuarioSevice;
import com.poloit.grupo12.inscripciones.validaciones.ValidarEmail;
import com.poloit.grupo12.inscripciones.validaciones.ValidarFecha;
import com.poloit.grupo12.inscripciones.validaciones.ValidarRol;
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

    @Override
    public Page<UsuarioDTO> findAll(Pageable pageable) {
        Page<Usuario> usuarios = usuarioRepository.findAll(pageable);
        if(usuarios.isEmpty())
            throw new RecursoNoEncontradoException("No se encontraron usuarios en la base de datos");
        return usuarios.map(this::convertToDto);
    }

    @Override
    public Page<UsuarioDTO> findByRol(String rolTipo, Pageable pageable) {
        ValidarRol.validarRol(rolTipo);
        Rol rol = Rol.valueOf(rolTipo.toUpperCase());
        Page<Usuario> usuarios = usuarioRepository.findByRol(rol, pageable);
        if(usuarios.isEmpty())
            throw new RecursoNoEncontradoException("No se encontraron usuarios con el rol: " + rol.name());
        return usuarios.map(this::convertToDto);
    }

    @Override
    public UsuarioDTO findById(String id) {
        try {
            ModelMapper mapper = new ModelMapper();
            Long idUsuario = Long.parseLong(id);
            Optional<Usuario> optUsuario = usuarioRepository.findById(idUsuario);
            return optUsuario.map(this::convertToDto).orElseThrow(()
                    -> new RecursoNoEncontradoException("No se encontro usuario con id: "+ id));
        } catch (NumberFormatException e) {
            throw new IdNoValidoException("Id no valido: " + id);
        }
    }

    @Override
    public UsuarioDTO save(UsuarioDTO usuarioDTO) {
        ModelMapper mapper = new ModelMapper();
        ValidarEmail.validarEmail(usuarioDTO.getEmail());
        ValidarRol.validarRol(usuarioDTO.getRol());
        ValidarFecha.validarFechaNacimiento(usuarioDTO.getFechaNacimiento());
        Usuario usuario = mapper.map(usuarioDTO, Usuario.class);

        Usuario usuarioExistente = usuarioRepository.findByEmail(usuarioDTO.getEmail());
        if (usuarioExistente != null)
            throw new EmailRepetidoException("El email " + usuarioDTO.getEmail() +
                    " se encuentra registrado");

        Usuario nuevoUsuario = usuarioRepository.save(usuario);
        return convertToDto(nuevoUsuario);
    }

    @Override
    public UsuarioDTO update(Long id, UsuarioDTO usuarioDTO) {
        ModelMapper mapper = new ModelMapper();
        Usuario usuario = mapper.map(usuarioDTO, Usuario.class);
        usuario.setId(id);
            Usuario nuevoUsuario = usuarioRepository.save(usuario);
            return convertToDto(nuevoUsuario);
    }

    @Override
    public void delete(String id) {
        try {
            Long idUsuario = Long.parseLong(id);
            Optional<Usuario> usuario = usuarioRepository.findById(idUsuario);
            if (usuario.isPresent()) {
                usuarioRepository.deleteById(idUsuario);
            } else {
                throw new RecursoNoEncontradoException("No se encontro usuario con id: :" + id);
            }
        } catch (NumberFormatException e) {
            throw new IdNoValidoException("Id no valido: " + id);
        }

    }

    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
    public Usuario findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    private UsuarioDTO convertToDto(Usuario usuario) {
        ModelMapper mapper = new ModelMapper();
        UsuarioDTO usuarioDTO = mapper.map(usuario, UsuarioDTO.class);
        usuarioDTO.setPassword("");
        return usuarioDTO;
    }
}