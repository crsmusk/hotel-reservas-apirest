package com.hotel.reservahabitaciones.Service.Interface;

import com.hotel.reservahabitaciones.Model.DTOs.entrada.RolDto;

import java.util.List;

public interface IRol {
    public List<RolDto>obtenerTodos();
    public RolDto obtenerPorId(Long id);
    public RolDto obtenerPorNombre(String nombre);
    public RolDto actualizarNombre(Long id, RolDto RolDto);
    public void guardar(RolDto RolDto);
    public void eliminar(Long id);
    public RolDto actualizarPermisos(Long id, List<String>permisos);
}

