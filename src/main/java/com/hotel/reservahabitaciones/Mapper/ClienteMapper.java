package com.hotel.reservahabitaciones.Mapper;

import com.hotel.reservahabitaciones.Model.DTOs.ClienteDTO;
import com.hotel.reservahabitaciones.Model.Entities.Cliente;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClienteMapper {

    public ClienteDTO clientACLinenteDto(Cliente cliente){
        ClienteDTO clienteDTO=ClienteDTO.builder()
                .nombre(cliente.getNombre())
                .apellido(cliente.getApellido())
                .email(cliente.getUsuario().getEmail())
                .password(cliente.getUsuario().getPassword())
                .dni(cliente.getDni())
                .telefono(cliente.getTelefono())
                .build();
        return  clienteDTO;
    }

    public List<ClienteDTO> ClientesAClientesDto(List<Cliente>clientes){
        return clientes.stream().map(this::clientACLinenteDto).toList();
    }
}
