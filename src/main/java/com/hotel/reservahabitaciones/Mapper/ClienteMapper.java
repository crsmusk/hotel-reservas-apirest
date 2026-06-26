package com.hotel.reservahabitaciones.Mapper;

import com.hotel.reservahabitaciones.Model.DTOs.entrada.ClienteDto;
import com.hotel.reservahabitaciones.Model.DTOs.salida.ClienteSimplificadoDto;
import com.hotel.reservahabitaciones.Model.Entities.Cliente;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClienteMapper {

    public ClienteSimplificadoDto clienteAClienteDto(Cliente cliente){
       return ClienteSimplificadoDto.builder()
        .nombre(cliente.getNombre())
        .apellido(cliente.getApellido())
        .email(cliente.getUsuario().getEmail())
        .telefono(cliente.getTelefono())
        .id(cliente.getId())
        .build();
    }

    public List<ClienteSimplificadoDto> clientesAClientesDto(List<Cliente>clientes){
        return clientes.stream().map(this::clienteAClienteDto).toList();
    }
}

