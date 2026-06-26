package com.hotel.reservahabitaciones.Service.Interface;

import com.hotel.reservahabitaciones.Model.DTOs.entrada.HabitacionDto;

import java.math.BigDecimal;
import java.util.List;

public interface IHabitacion {
    public List<HabitacionDto>obtenerTodos();
    public HabitacionDto obtenerPorId(Long id);
    public List<HabitacionDto>obtenerPorTipoHabitacion(String tipo);
    public List<HabitacionDto> obtenerPorPreferencia(String preferencia);
    public List<HabitacionDto>obtenerPorCapacidad(int capacidad);
    public List<HabitacionDto> obtenerPorTamano(int tamaño);
    public List<HabitacionDto> obtenerPorPrecioMenorQue(BigDecimal precio);
    public List<HabitacionDto> obtenerPorPrecioMayorQue(BigDecimal precio);
    public List<HabitacionDto> obtenerHabitacionesDisponibles();
    public List<HabitacionDto> obtenerHabitacionesNoDisponibles();
    public List<HabitacionDto> obtenerPorAccesibilidad(String accesibilidad);
    public void guardar(HabitacionDto HabitacionDto);
    public HabitacionDto actualizar(Long id,HabitacionDto HabitacionDto);
    public void eliminar(Long id);

}

