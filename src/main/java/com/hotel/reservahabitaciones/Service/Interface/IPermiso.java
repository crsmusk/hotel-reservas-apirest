package com.hotel.reservahabitaciones.Service.Interface;

import com.hotel.reservahabitaciones.Model.DTOs.PermisoDTO;

import java.util.List;

public interface IPermiso {
    public List<PermisoDTO>getAll();
    public PermisoDTO getById(Long id);
    public PermisoDTO getByName(String nombre);
    public PermisoDTO update(Long id,PermisoDTO permisoDTO);
    public void save (PermisoDTO permisoDTO);
    public void delete(Long id);

}
