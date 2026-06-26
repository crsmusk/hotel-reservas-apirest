package com.hotel.reservahabitaciones.Mapper;

import com.hotel.reservahabitaciones.Model.DTOs.entrada.HabitacionDto;
import com.hotel.reservahabitaciones.Model.Entities.Habitacion;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HabitacionMapper {
    public HabitacionDto habitacionAHabitacionDto(Habitacion habitacion){
        HabitacionDto habitacionDto = HabitacionDto.builder()
                .id(habitacion.getId())
                .tipoHabitacion(habitacion.getTipoHabitacion())
                .numeroHabitacion(habitacion.getNumeroHabitacion())
                .capacidad(habitacion.getCapacidad())
                .tamaño(habitacion.getTamano())
                .accesibilidad(habitacion.getAccesibilidad())
                .descripcion(habitacion.getDescripcion())
                .estado(habitacion.isEstado())
                .precioNoche(habitacion.getPrecioNoche())
                .preferencia(habitacion.getPreferencia())
                .build();
        return habitacionDto;
    }

    public List<HabitacionDto>habitacionesAHabitacionesDto(List<Habitacion>habitaciones){
        return habitaciones.stream().map(this::habitacionAHabitacionDto).toList();
    }
}

