package com.hotel.reservahabitaciones.Service.Interface;

import com.hotel.reservahabitaciones.Model.DTOs.entrada.EmpleadoDto;

import java.util.List;

public interface IEmpleado {
    public List<EmpleadoDto> obtenerTodos();
    public EmpleadoDto obtenerPorId(Long id);
    public List<EmpleadoDto>obtenerPorNombre(String nombre);
    public List<EmpleadoDto>obtenerPorApellido(String apellido);
    public void guardar(EmpleadoDto EmpleadoDto);
    public EmpleadoDto actualizar(Long id,EmpleadoDto EmpleadoDto);
    public void eliminar(Long id);
    public void cambiarPuesto(Long id,String puesto);
}

