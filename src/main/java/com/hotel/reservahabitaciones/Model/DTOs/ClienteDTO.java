package com.hotel.reservahabitaciones.Model.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClienteDTO {
     String nombre;
     String apellido;
     String dni;
     String telefono;
     String email;
     String password;
}
