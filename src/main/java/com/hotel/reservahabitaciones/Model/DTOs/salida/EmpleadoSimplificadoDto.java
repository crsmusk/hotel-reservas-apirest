package com.hotel.reservahabitaciones.Model.DTOs.salida;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmpleadoSimplificadoDto {
   private Long id;
   private String nombre;
   private String apellido;
   private String puesto;
   private String email;
   private String telefono;
}
