package com.hotel.reservahabitaciones.Mapper;

import com.hotel.reservahabitaciones.Model.DTOs.ReservacionDTO;
import com.hotel.reservahabitaciones.Model.Entities.Reservacion;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReservacionMapper {
    public ReservacionDTO reservacionAreservacionDto(Reservacion reservacion){
        ReservacionDTO reservacionDTO=ReservacionDTO.builder()
                .id(reservacion.getId())
                .fechaEntrada(reservacion.getFechaEntrada())
                .fechaSalida(reservacion.getFechaSalida())
                .idCliente(reservacion.getCliente().getId())
                .ids(reservacion.getHabitacions().stream().map(Habitacion->Habitacion.getId()).toList())
                .build();
        return reservacionDTO;
    }

    public List<ReservacionDTO>reservacionesAReservacionesDto(List<Reservacion>reservaciones){
        return reservaciones.stream().map(this::reservacionAreservacionDto).toList();
    }
}
