package com.hotel.reservahabitaciones.Service.Interface;

import com.hotel.reservahabitaciones.Model.DTOs.entrada.ReservacionDto;
import com.hotel.reservahabitaciones.Model.DTOs.salida.ReservacionSimplificadoDto;

import java.time.LocalDate;
import java.util.List;

public interface IReservacion {

    public List<ReservacionSimplificadoDto>obtenerTodos();
    public ReservacionSimplificadoDto obtenerPorId(Long id);
    public void eliminar(Long id);
    public void guardar(ReservacionDto ReservacionDto);
    public ReservacionSimplificadoDto actualizarFechaSalida(Long id,Long idHabitacion, LocalDate salida);
    public List<ReservacionSimplificadoDto> obtenerPorSalidaDespuesDe(LocalDate fecha);
    public List<ReservacionSimplificadoDto> obtenerPorSalidaAntesDe(LocalDate fecha);
    public List<ReservacionSimplificadoDto> obtenerPorEntradaDespuesDe(LocalDate fecha);
    public List<ReservacionSimplificadoDto> obtenerPorEntradaAntesDe(LocalDate fecha);
    public ReservacionSimplificadoDto cambiarHabitacion(Long idReserva, Long idHabitacionActual, Long idNuevaHabitacion, LocalDate salida);
    public ReservacionSimplificadoDto obtenerProximaReservacionPorHabitacion(Long idHabitacion, LocalDate fecha);


}

