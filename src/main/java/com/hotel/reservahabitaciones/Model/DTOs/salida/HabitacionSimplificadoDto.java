package com.hotel.reservahabitaciones.Model.DTOs.salida;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HabitacionSimplificadoDto {
   private Long id;
   private String tipoHabitacion;
   private String preferencia;
   private int capacidad;
   private BigDecimal precioNoche;
   private boolean estado;
   private String accesibilidad;
}
