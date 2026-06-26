package com.hotel.reservahabitaciones.Model.DTOs.salida;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservacionSimplificadoDto {
    private Long id;
    private LocalDate fechaEntrada;
    private LocalDate fechaSalida;
    private String nombreCliente;
    private List<HabitacionResumenDto> habitaciones;
}
