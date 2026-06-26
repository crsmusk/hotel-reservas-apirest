package com.hotel.reservahabitaciones.Model.DTOs.entrada;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HabitacionDto {
    private Long id;
    private String tipoHabitacion;
    private int numeroHabitacion;
    private String preferencia;
    private int capacidad;
    private int tamaño;
    private BigDecimal precioNoche;
    private boolean estado;
    private String descripcion;
    private String accesibilidad;
}

