package com.hotel.reservahabitaciones.Model.DTOs.entrada;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmpleadoDto {
    private Long id;
    private String nombre;
    private String apellido;
    private String telefono;
    private String puesto;
    private String dni;
    private String email;
    private String password;
}

