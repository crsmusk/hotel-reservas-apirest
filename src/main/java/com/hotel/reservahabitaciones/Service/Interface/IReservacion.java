package com.hotel.reservahabitaciones.Service.Interface;

import com.hotel.reservahabitaciones.Model.DTOs.ReservacionDTO;

import java.time.LocalDate;
import java.util.List;

public interface IReservacion {

    public List<ReservacionDTO>getAll();
    public ReservacionDTO getById(Long id);
    public void delete(Long id);
    public void save(ReservacionDTO reservacionDTO);
    public ReservacionDTO updateOutPut(Long id, LocalDate salida);
    public List<ReservacionDTO> getByOutPutAfterThan(LocalDate fecha);
    public List<ReservacionDTO> getByOutPutBeforeThan(LocalDate fecha);
    public List<ReservacionDTO> getByInPutAfterThan(LocalDate fecha);
    public List<ReservacionDTO> getByInputBeforeThan(LocalDate fecha);
    public ReservacionDTO changeRoom(Long idReserva, Long idHabitacionActual, Long idNuevaHabitacion, LocalDate salida);


}
