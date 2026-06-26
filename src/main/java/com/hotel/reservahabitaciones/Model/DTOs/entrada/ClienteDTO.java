package com.hotel.reservahabitaciones.Model.DTOs.entrada;

import lombok.Builder;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Builder
public record ClienteDto(
    Long id,
    @NotBlank(message = "El nombre es obligatorio") String nombre,
    @NotBlank(message = "El apellido es obligatorio") String apellido,
    @NotBlank(message = "El DNI es obligatorio") String dni,
    @NotBlank(message = "El teléfono es obligatorio") String telefono,
    @NotBlank(message = "El email es obligatorio") @Email(message = "Formato de email inválido") String email,
    @NotBlank(message = "La contraseña es obligatoria") String password
) {}