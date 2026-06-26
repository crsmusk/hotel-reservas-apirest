package com.hotel.reservahabitaciones.Mapper;

import com.hotel.reservahabitaciones.Model.DTOs.entrada.PermisoDto;
import com.hotel.reservahabitaciones.Model.Entities.Permiso;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PermisoMapper {

    public PermisoDto permisoAPermisoDto(Permiso permiso){
        PermisoDto permisoDto = PermisoDto.builder()
                .nombre(permiso.getNombrePermiso())
                .id(permiso.getId())
                .build();
        return permisoDto;
    }

    public List<PermisoDto>permisosAPermisosDto(List<Permiso>permisos){
        return permisos.stream().map(this::permisoAPermisoDto).toList();
    }
}

