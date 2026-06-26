package com.hotel.reservahabitaciones.Service.Interface;

import com.hotel.reservahabitaciones.Model.DTOs.entrada.PermisoDto;

import java.util.List;

public interface IPermiso {
    public List<PermisoDto>obtenerTodos();
    public PermisoDto obtenerPorId(Long id);
    public PermisoDto obtenerPorNombre(String nombre);
    public PermisoDto actualizar(Long id,PermisoDto permisoDto);
    public PermisoDto guardar(PermisoDto permisoDto);
    public void eliminar(Long id);

}

