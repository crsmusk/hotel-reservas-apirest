package com.hotel.reservahabitaciones.Mapper;

import com.hotel.reservahabitaciones.Model.DTOs.ClienteDTO;
import com.hotel.reservahabitaciones.Model.Entities.Cliente;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClienteMapper {

    public ClienteDTO clienteAClinenteDto(Cliente cliente){
        ClienteDTO clienteDTO=ClienteDTO.builder()
                .nombre(cliente.getNombre())
                .apellido(cliente.getApellido())
                .email(cliente.getUsuario().getEmail())
                .password(cliente.getUsuario().getPassword())
                .dni(cliente.getDni())
                .telefono(cliente.getTelefono())
                .id(cliente.getId())
                .build();
        return  clienteDTO;
    }

    public List<ClienteDTO> clientesAClientesDto(List<Cliente>clientes){
        return clientes.stream().map(this::clienteAClinenteDto).toList();
    }
}
