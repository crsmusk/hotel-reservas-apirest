package com.hotel.reservahabitaciones.Mapper;

import com.hotel.reservahabitaciones.Model.DTOs.entrada.RolDto;
import com.hotel.reservahabitaciones.Model.Entities.Permiso;
import com.hotel.reservahabitaciones.Model.Entities.Rol;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RolMapper {

    public RolDto rolARolDto(Rol rol){
        RolDto rolDto = RolDto.builder()
                .nombre(rol.getNombreRol())
                .id(rol.getId())
                .permisos(rol.getPermisos().stream().map(Permiso::getNombrePermiso).toList())
                .build();
        return rolDto;
    }

    public List<RolDto>rolesARolesDto(List<Rol>roles){
        return roles.stream().map(this::rolARolDto).toList();
    }
}

