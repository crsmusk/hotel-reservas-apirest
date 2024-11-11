package com.hotel.reservahabitaciones.Service.Interface;

import com.hotel.reservahabitaciones.Model.DTOs.ClienteDTO;
import com.hotel.reservahabitaciones.Model.DTOs.EmpleadoDTO;

import java.util.List;

public interface IEmpleado {
    public List<EmpleadoDTO> getAll();
    public EmpleadoDTO getById(Long id);
    public List<EmpleadoDTO>getByNombre(String nombre);
    public List<EmpleadoDTO>getByApellido(String apellido);
    public void save(EmpleadoDTO empleadoDTO);
    public EmpleadoDTO update(Long id,EmpleadoDTO empleadoDTO);
    public void delete(Long id);
}
