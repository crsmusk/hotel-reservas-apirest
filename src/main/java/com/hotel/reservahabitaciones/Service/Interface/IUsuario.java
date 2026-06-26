package com.hotel.reservahabitaciones.Service.Interface;

import com.hotel.reservahabitaciones.Model.DTOs.entrada.ClienteDto;
import com.hotel.reservahabitaciones.Model.DTOs.entrada.EmpleadoDto;
import com.hotel.reservahabitaciones.Model.DTOs.entrada.UsuarioDto;

import java.util.List;

public interface IUsuario {
    public List<UsuarioDto> obtenerTodos();
    public UsuarioDto obtenerPorId(Long id);
    public UsuarioDto obtenerPorEmail(String email);
    public void guardar(UsuarioDto UsuarioDto);
    public UsuarioDto actualizar(Long id,UsuarioDto UsuarioDto);
    public void eliminar(Long id);
    public void registrarCliente(ClienteDto ClienteDto);
    public void registrarEmpleado(EmpleadoDto EmpleadoDto);
    public void actualizarContrasena(String email, String contrasenaNueva);
    public void actualizarRoles(Long id, List<String>roles);
}

