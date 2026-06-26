package com.hotel.reservahabitaciones.Model.DTOs.entrada;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PermisoDto {
    private Long id;
    private String nombre;
}

