package com.hotel.reservahabitaciones.Mapper;

import com.hotel.reservahabitaciones.Model.DTOs.HabitacionDTO;
import com.hotel.reservahabitaciones.Model.Entities.Habitacion;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HabitacionMapper {
    public HabitacionDTO habitacionAHabitacionDto(Habitacion habitacion){
        HabitacionDTO habitacionDTO=HabitacionDTO.builder()
                .id(habitacion.getId())
                .tipoHabitacion(habitacion.getTipoHabitacion())
                .capacidad(habitacion.getCapacidad())
                .tamaño(habitacion.getTamano())
                .accesibilidad(habitacion.getAccesibilidad())
                .descripcion(habitacion.getDescripcion())
                .estado(habitacion.isEstado())
                .precioNoche(habitacion.getPrecioNoche())
                .preferencia(habitacion.getPreferencia())
                .build();
        return habitacionDTO;
    }

    public List<HabitacionDTO>habitacionesAHabitacionesDto(List<Habitacion>habitaciones){
        return habitaciones.stream().map(this::habitacionAHabitacionDto).toList();
    }
}
