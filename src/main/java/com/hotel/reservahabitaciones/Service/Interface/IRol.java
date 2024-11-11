package com.hotel.reservahabitaciones.Service.Interface;

import com.hotel.reservahabitaciones.Model.DTOs.PermisoDTO;
import com.hotel.reservahabitaciones.Model.DTOs.RolDTO;

import java.util.List;

public interface IRol {
    public List<RolDTO>getAll();
    public RolDTO getById(Long id);
    public RolDTO getByNombre(String nombre);
    public RolDTO updateNombre(Long id, RolDTO rolDTO);
    public void save(RolDTO rolDTO);
    public void delete(Long id);
    public RolDTO actualizarPermisos(Long id, List<String>permisos);
}
