package com.hotel.reservahabitaciones.Mapper;

import com.hotel.reservahabitaciones.Model.DTOs.entrada.UsuarioDto;
import com.hotel.reservahabitaciones.Model.Entities.Usuario;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsuarioMapper {
    public UsuarioDto usuarioAUsuarioDto(Usuario usuario){
        UsuarioDto usuarioDto = UsuarioDto.builder()
                .email(usuario.getEmail())
                .id(usuario.getId())
                .password(usuario.getPassword())
                .roles(usuario.getRoles().stream().map(rol->rol.getNombreRol()).toList())
                .build();
        return usuarioDto;
    }

    public List<UsuarioDto>usuariosAUsuariosDto(List<Usuario>usuarios){
        return usuarios.stream().map(this::usuarioAUsuarioDto).toList();
    }
}

