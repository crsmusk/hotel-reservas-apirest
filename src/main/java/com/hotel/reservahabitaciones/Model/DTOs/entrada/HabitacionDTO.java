package com.hotel.reservahabitaciones.Model.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HabitacionDTO {
      Long id;
      String tipoHabitacion;
      int numeroHabitacion;
      String preferencia;
      int capacidad;
      int tamaño;
      BigDecimal precioNoche;
      boolean estado;
      String descripcion;
      String accesibilidad;
}
