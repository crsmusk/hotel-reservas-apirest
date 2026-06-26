package com.hotel.reservahabitaciones.Service.Impl;

import com.hotel.reservahabitaciones.Exception.Exceptions.UsuarioNoEncontradoException;
import com.hotel.reservahabitaciones.Mapper.ClienteMapper;
import com.hotel.reservahabitaciones.Model.DTOs.entrada.ClienteDto;
import com.hotel.reservahabitaciones.Model.DTOs.salida.ClienteSimplificadoDto;
import com.hotel.reservahabitaciones.Model.Entities.Cliente;
import com.hotel.reservahabitaciones.Model.Entities.Usuario;
import com.hotel.reservahabitaciones.Repository.ClienteRepository;
import com.hotel.reservahabitaciones.Repository.UsuarioRepository;
import com.hotel.reservahabitaciones.Service.Interface.ICliente;
import com.hotel.reservahabitaciones.Service.Interface.IUsuario;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ICliente {

    private ClienteRepository clienteRepo;
    private  IUsuario usuarioService;
    private UsuarioRepository usuarioRepo;
    private ClienteMapper mapper;

    public ClienteServiceImpl(ClienteRepository clienteRepo, IUsuario usuarioService, UsuarioRepository usuarioRepo, ClienteMapper mapper) {
        this.clienteRepo = clienteRepo;
        this.usuarioService = usuarioService;
        this.usuarioRepo = usuarioRepo;
        this.mapper = mapper;
    }

    @Override
    public List<ClienteSimplificadoDto> obtenerTodos() {
        List<Cliente> clientes=clienteRepo.findAll();
        if(clientes.isEmpty()){
           return List.of();
        }else{
            return mapper.clientesAClientesDto(clientes);
        }
    }

    @Override
    public ClienteSimplificadoDto obtenerPorId(Long id) {
        Optional<Cliente> cliente=clienteRepo.findById(id);
        if (cliente.isPresent()){
            return mapper.clienteAClienteDto(cliente.get());
        }else{
            throw new UsuarioNoEncontradoException();
        }
    }

    @Override
    public List<ClienteSimplificadoDto> obtenerPorNombre(String nombre) {
        List<Cliente>clientes=clienteRepo.findByNombreIgnoreCase(nombre);
        if(clientes.isEmpty()){
            throw new UsuarioNoEncontradoException();
        }else {
            return mapper.clientesAClientesDto(clientes);
        }
    }

    @Override
    public List<ClienteSimplificadoDto> obtenerPorApellido(String apellido) {
        List<Cliente>clientes=clienteRepo.findByApellidoIgnoreCase(apellido);
        if (clientes.isEmpty()){
            throw new UsuarioNoEncontradoException();
        }else{
            return mapper.clientesAClientesDto(clientes);
        }
    }

    @Override
    @Transactional
    public void guardar(ClienteDto ClienteDto) {
        Cliente cliente=new Cliente();

        cliente.setApellido(ClienteDto.apellido());
        cliente.setNombre(ClienteDto.nombre());
        cliente.setDni(ClienteDto.dni());
        cliente.setTelefono(ClienteDto.telefono());
        usuarioService.registrarCliente(ClienteDto);
        Optional<Usuario>usuario=usuarioRepo.findByEmailIgnoreCase(ClienteDto.email());
        if (usuario.isPresent()){
            cliente.setUsuario(usuario.get());
        }
        clienteRepo.save(cliente);

    }

    @Override
    public ClienteSimplificadoDto actualizar(Long id, ClienteDto ClienteDto) {
        Optional<Cliente>cliente=clienteRepo.findById(id);
        if(cliente.isPresent()){
            cliente.get().setTelefono(ClienteDto.telefono());
            cliente.get().setNombre(ClienteDto.nombre());
            cliente.get().setDni(ClienteDto.dni());
           clienteRepo.save(cliente.get());
           return mapper.clienteAClienteDto(cliente.get());
        }else {
            throw  new UsuarioNoEncontradoException();
        }

    }

    @Override
    public void eliminar(Long id) {
      if (clienteRepo.existsById(id)){
          clienteRepo.deleteById(id);
      }else {
          throw new UsuarioNoEncontradoException();
      }
    }
}

