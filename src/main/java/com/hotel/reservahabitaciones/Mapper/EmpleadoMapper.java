package com.hotel.reservahabitaciones.Mapper;

import com.hotel.reservahabitaciones.Model.DTOs.entrada.EmpleadoDto;
import com.hotel.reservahabitaciones.Model.Entities.Empleado;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmpleadoMapper {

    public EmpleadoDto empleadoAEmpleadoDto(Empleado empleado){
        EmpleadoDto empleadoDto = EmpleadoDto.builder()
                .nombre(empleado.getNombre())
                .apellido(empleado.getApellido())
                .dni(empleado.getDni())
                .id(empleado.getId())
                .email(empleado.getUsuario().getEmail())
                .password(empleado.getUsuario().getPassword())
                .telefono(empleado.getTelefono())
                .puesto(empleado.getPuesto())
                .build();
        return empleadoDto;
    }

    public List<EmpleadoDto>empleadosAEmpleadosDto(List<Empleado>empleados){
        return  empleados.stream().map(this::empleadoAEmpleadoDto).toList();
    }
}

