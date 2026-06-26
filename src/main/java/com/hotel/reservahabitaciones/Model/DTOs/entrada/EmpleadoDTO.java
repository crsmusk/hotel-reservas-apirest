package com.hotel.reservahabitaciones.Model.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmpleadoDTO {
     Long id;
     String nombre;
     String apellido;
     String telefono;
     String puesto;
     String dni;
     String email;
     String password;
}
