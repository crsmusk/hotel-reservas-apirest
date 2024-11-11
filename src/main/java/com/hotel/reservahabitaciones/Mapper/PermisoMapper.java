package com.hotel.reservahabitaciones.Mapper;

import com.hotel.reservahabitaciones.Model.DTOs.PermisoDTO;
import com.hotel.reservahabitaciones.Model.Entities.Permiso;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PermisoMapper {

    public PermisoDTO permisoAPermisoDto(Permiso permiso){
        PermisoDTO permisoDTO=PermisoDTO.builder()
                .nombre(permiso.getNombrePermiso())
                .build();
        return  permisoDTO;
    }

    public List<PermisoDTO>permisosAPermisosDto(List<Permiso>permisos){
        return permisos.stream().map(this::permisoAPermisoDto).toList();
    }
}
