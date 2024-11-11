package com.hotel.reservahabitaciones.Mapper;

import com.hotel.reservahabitaciones.Model.DTOs.RolDTO;
import com.hotel.reservahabitaciones.Model.Entities.Permiso;
import com.hotel.reservahabitaciones.Model.Entities.Rol;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RolMapper {

    public RolDTO rolARolDto(Rol rol){
        RolDTO rolDTO=RolDTO.builder()
                .nombre(rol.getNombreRol())
                .permisos(rol.getPermisos().stream().map(Permiso::getNombrePermiso).toList())
                .build();
        return rolDTO;
    }

    public List<RolDTO>rolesARolesDto(List<Rol>roles){
        return roles.stream().map(this::rolARolDto).toList();
    }
}
