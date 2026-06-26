package com.hotel.reservahabitaciones.Model.DTOs.entrada;

import lombok.Builder;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Builder
public record ReservacionDto(
    Long id,
    @NotNull(message = "La fecha de entrada es obligatoria") LocalDate fechaEntrada,
    @NotNull(message = "La fecha de salida es obligatoria") LocalDate fechaSalida,
    @NotNull(message = "El ID del cliente es obligatorio") Long idCliente,
    @NotNull(message = "Los IDs de las habitaciones son obligatorios") List<Long> habitacionIds
) {}
