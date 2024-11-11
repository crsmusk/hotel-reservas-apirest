package com.hotel.reservahabitaciones.Service.Interface;

import com.hotel.reservahabitaciones.Model.DTOs.HabitacionDTO;

import java.math.BigDecimal;
import java.util.List;

public interface IHabitacion {
    public List<HabitacionDTO>getAll();
    public HabitacionDTO getById(Long id);
    public List<HabitacionDTO>getByTipoHabitacion(String tipo);
    public List<HabitacionDTO>getByPreferencia(String preferencia);
    public List<HabitacionDTO>getByCapacidad(int capacidad);
    public List<HabitacionDTO>getByTamaño(int tamaño);
    public List<HabitacionDTO> getByPrecioNocheMenorQue(BigDecimal precio);
    public List<HabitacionDTO>getByPrecioNocheMayorQue(BigDecimal precio);
    public List<HabitacionDTO>getByHabitacionesDisponibles();
    public List<HabitacionDTO>getByHabitacionesNoDisponibles();
    public List<HabitacionDTO>getByAccesibilidad(String accesibilidad);
    public void save(HabitacionDTO habitacionDTO);
    public HabitacionDTO update(Long id,HabitacionDTO habitacionDTO);
    public void delete(Long id);

}
