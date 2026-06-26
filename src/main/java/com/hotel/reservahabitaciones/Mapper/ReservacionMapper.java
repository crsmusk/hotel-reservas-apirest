package com.hotel.reservahabitaciones.Mapper;

import com.hotel.reservahabitaciones.Model.DTOs.entrada.ReservacionDto;
import com.hotel.reservahabitaciones.Model.DTOs.salida.ReservacionSimplificadoDto;
import com.hotel.reservahabitaciones.Model.DTOs.salida.HabitacionResumenDto;
import com.hotel.reservahabitaciones.Model.Entities.Habitacion;
import com.hotel.reservahabitaciones.Model.Entities.Reservacion;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReservacionMapper {
    public ReservacionSimplificadoDto reservacionAReservacionDto(Reservacion reservacion){
        return ReservacionSimplificadoDto.builder()
                .id(reservacion.getId())
                .fechaEntrada(reservacion.getFechaEntrada())
                .fechaSalida(reservacion.getFechaSalida())
                .nombreCliente(reservacion.getCliente().getNombre()+" "+reservacion.getCliente().getApellido())
                .habitaciones(reservacion.getHabitacionesActivas().stream().map(cuarto ->new HabitacionResumenDto(cuarto.getId(), cuarto.getNumeroHabitacion())).toList())
                .build();
    }

    public List<ReservacionSimplificadoDto>reservacionesAReservacionesDto(List<Reservacion>reservaciones){
        return reservaciones.stream().map(this::reservacionAReservacionDto).toList();
    }
}

