package com.hotel.reservahabitaciones.Service.Interface;

import com.hotel.reservahabitaciones.Model.DTOs.entrada.ClienteDto;
import com.hotel.reservahabitaciones.Model.DTOs.salida.ClienteSimplificadoDto;

import java.util.List;

public interface ICliente {
    public List<ClienteSimplificadoDto>obtenerTodos();
    public ClienteSimplificadoDto obtenerPorId(Long id);
    public List<ClienteSimplificadoDto>obtenerPorNombre(String nombre);
    public List<ClienteSimplificadoDto> obtenerPorApellido(String apellido);
    public void guardar(ClienteDto ClienteDto);
    public ClienteSimplificadoDto actualizar(Long id,ClienteDto ClienteDto);
    public void eliminar(Long id);
}

