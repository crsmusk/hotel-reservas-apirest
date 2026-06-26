package com.hotel.reservahabitaciones.Model.DTOs.salida;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClienteSimplificadoDto {
   private Long id;
   private String nombre;
   private String apellido;
   private String email;
   private String telefono;
}
