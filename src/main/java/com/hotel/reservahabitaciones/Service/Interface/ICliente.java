package com.hotel.reservahabitaciones.Service.Interface;

import com.hotel.reservahabitaciones.Model.DTOs.ClienteDTO;

import java.util.List;

public interface ICliente {
    public List<ClienteDTO>getAll();
    public ClienteDTO getById(Long id);
    public List<ClienteDTO>getByName(String nombre);
    public List<ClienteDTO> getByLastName(String apellido);
    public void save(ClienteDTO clienteDTO);
    public ClienteDTO update(Long id,ClienteDTO clienteDTO);
    public void delete(Long id);
}
