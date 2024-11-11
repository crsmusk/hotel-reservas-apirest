package com.hotel.reservahabitaciones.Mapper;

import com.hotel.reservahabitaciones.Model.DTOs.EmpleadoDTO;
import com.hotel.reservahabitaciones.Model.Entities.Empleado;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmpleadoMapper {

    public EmpleadoDTO empleadoAEmpleadoDto(Empleado empleado){
        EmpleadoDTO empleadoDTO=EmpleadoDTO.builder()
                .nombre(empleado.getNombre())
                .apellido(empleado.getApellido())
                .dni(empleado.getDni())
                .id(empleado.getId())
                .email(empleado.getUsuario().getEmail())
                .password(empleado.getUsuario().getPassword())
                .telefono(empleado.getTelefono())
                .puesto(empleado.getPuesto())
                .build();
        return empleadoDTO;
    }

    public List<EmpleadoDTO>empleadosAEmpladosDto(List<Empleado>empleados){
        return  empleados.stream().map(this::empleadoAEmpleadoDto).toList();
    }
}
