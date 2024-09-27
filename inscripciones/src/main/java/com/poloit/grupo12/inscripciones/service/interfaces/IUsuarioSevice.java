package com.poloit.grupo12.inscripciones.service.interfaces;

import com.poloit.grupo12.inscripciones.dto.UsuarioDTO;
import com.poloit.grupo12.inscripciones.enums.Rol;
import com.poloit.grupo12.inscripciones.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
public interface IUsuarioSevice {
    public Page<UsuarioDTO> findAll(Pageable pageable);
    public Page<UsuarioDTO> findByRol(String rol, Pageable pageable);

    public UsuarioDTO findById(String id);
    public UsuarioDTO save(UsuarioDTO usuarioDTO);
    public UsuarioDTO update(String id, UsuarioDTO usuarioDTO);
    public void delete(String id);
    //public UsuarioDTO findByEmail(String email);

    public Usuario findByEmail(String email);

    public UsuarioDTO updateEmail(String id, UsuarioDTO usuarioDTO);
    public UsuarioDTO updatePassword(String id, UsuarioDTO usuarioDTO);

}
