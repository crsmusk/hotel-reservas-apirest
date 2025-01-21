package com.hotel.reservahabitaciones.Model.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RolDTO {
    Long id;
    String nombre;
    List<String>permisos;
}
