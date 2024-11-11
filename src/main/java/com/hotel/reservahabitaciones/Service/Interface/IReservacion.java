package com.hotel.reservahabitaciones.Service.Interface;

import com.hotel.reservahabitaciones.Model.DTOs.ReservacionDTO;

import java.time.LocalDate;
import java.util.List;

public interface IReservacion {

    public List<ReservacionDTO>getAll();
    public ReservacionDTO getById(Long id);
    public void delete(Long id);
    public void save(ReservacionDTO reservacionDTO);
    public ReservacionDTO updateSalida(Long id,LocalDate salida);
    public List<ReservacionDTO>getBySalidaDespuesDe(LocalDate fecha);
    public List<ReservacionDTO>getBySalidaAntesDe(LocalDate fecha);
    public List<ReservacionDTO>getByEntradaDespuesDe(LocalDate fecha);
    public List<ReservacionDTO>getByEntradaAntesDe(LocalDate fecha);
    public ReservacionDTO cambiarHabitacion(Long idReserva,Long idHabitacionActual,Long idNuevaHabitacion,LocalDate salida);


}
