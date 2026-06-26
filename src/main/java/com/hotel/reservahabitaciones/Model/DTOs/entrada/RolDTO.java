package com.hotel.reservahabitaciones.Model.DTOs.entrada;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RolDto {
    private Long id;
    private String nombre;
    private List<String>permisos;
}

