package com.hotel.reservahabitaciones.Service.Interface;

import com.hotel.reservahabitaciones.Model.DTOs.HabitacionDTO;

import java.math.BigDecimal;
import java.util.List;

public interface IHabitacion {
    public List<HabitacionDTO>getAll();
    public HabitacionDTO getById(Long id);
    public List<HabitacionDTO>getByTypeRoom(String tipo);
    public List<HabitacionDTO> getByPreference(String preferencia);
    public List<HabitacionDTO>getByCapacity(int capacidad);
    public List<HabitacionDTO> getBySize(int tamaño);
    public List<HabitacionDTO> getByPriceLessThan(BigDecimal precio);
    public List<HabitacionDTO> getByPriceGreaterThan(BigDecimal precio);
    public List<HabitacionDTO> getByRoomAvailable();
    public List<HabitacionDTO> getByRoomUnaVailable();
    public List<HabitacionDTO> getByAccesibility(String accesibilidad);
    public void save(HabitacionDTO habitacionDTO);
    public HabitacionDTO update(Long id,HabitacionDTO habitacionDTO);
    public void delete(Long id);

}
