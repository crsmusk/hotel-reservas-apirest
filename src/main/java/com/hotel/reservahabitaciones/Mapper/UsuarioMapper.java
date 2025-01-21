package com.hotel.reservahabitaciones.Mapper;

import com.hotel.reservahabitaciones.Model.DTOs.UsuarioDTO;
import com.hotel.reservahabitaciones.Model.Entities.Usuario;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsuarioMapper {
    public UsuarioDTO usuarioAUsuarioDto(Usuario usuario){
        UsuarioDTO usuarioDTO=UsuarioDTO.builder()
                .email(usuario.getEmail())
                .id(usuario.getId())
                .password(usuario.getPassword())
                .roles(usuario.getRoles().stream().map(rol->rol.getNombreRol()).toList())
                .build();
        return usuarioDTO;
    }

    public List<UsuarioDTO>usuariosAUsuariosDto(List<Usuario>usuarios){
        return usuarios.stream().map(this::usuarioAUsuarioDto).toList();
    }
}
