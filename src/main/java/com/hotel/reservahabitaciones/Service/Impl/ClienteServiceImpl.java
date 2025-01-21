package com.hotel.reservahabitaciones.Service.Impl;

import com.hotel.reservahabitaciones.Exception.Exceptions.UsuarioNoEncontradoException;
import com.hotel.reservahabitaciones.Mapper.ClienteMapper;
import com.hotel.reservahabitaciones.Model.DTOs.ClienteDTO;
import com.hotel.reservahabitaciones.Model.Entities.Cliente;
import com.hotel.reservahabitaciones.Model.Entities.Usuario;
import com.hotel.reservahabitaciones.Repository.ClienteRepository;
import com.hotel.reservahabitaciones.Repository.UsuarioRepository;
import com.hotel.reservahabitaciones.Service.Interface.ICliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ICliente {

    private ClienteRepository clienteRepo;
    @Autowired
    public void setClienteRepositorio(ClienteRepository clienteRepo) {
        this.clienteRepo = clienteRepo;
    }

    private  UsuarioServiceImpl usuarioService;
    @Autowired
    public void setUsuarioService(UsuarioServiceImpl usuarioService) {
        this.usuarioService = usuarioService;
    }

    private UsuarioRepository usuarioRepo;
    @Autowired
    public void setUsuarioRepo(UsuarioRepository usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }

    private ClienteMapper mapper;
    @Autowired
    public void setMapper(ClienteMapper mapper) {
        this.mapper = mapper;
    }


    @Override
    public List<ClienteDTO> getAll() {
        if(clienteRepo.findAll().isEmpty()){
            throw new UsuarioNoEncontradoException();
        }else{
            return mapper.clientesAClientesDto(clienteRepo.findAll());
        }
    }

    @Override
    public ClienteDTO getById(Long id) {
        if (clienteRepo.existsById(id)){
            return mapper.clienteAClinenteDto(clienteRepo.findById(id).get());
        }else{
            throw new UsuarioNoEncontradoException();
        }
    }

    @Override
    public List<ClienteDTO> getByName(String nombre) {
        if(clienteRepo.findByNombreIgnoreCase(nombre).isEmpty()){
            throw new UsuarioNoEncontradoException();
        }else {
            return mapper.clientesAClientesDto(clienteRepo.findByNombreIgnoreCase(nombre));
        }
    }

    @Override
    public List<ClienteDTO> getByLastName(String apellido) {
        if (clienteRepo.findByApellidoIgnoreCase(apellido).isEmpty()){
            throw new UsuarioNoEncontradoException();
        }else{
            return mapper.clientesAClientesDto(clienteRepo.findByApellidoIgnoreCase(apellido));
        }
    }

    @Override
    @Transactional
    public void save(ClienteDTO clienteDTO) {

        Cliente cliente=new Cliente();
        cliente.setApellido(clienteDTO.getApellido());
        cliente.setNombre(clienteDTO.getNombre());
        cliente.setDni(clienteDTO.getDni());
        cliente.setTelefono(clienteDTO.getTelefono());
        usuarioService.registerCustommer(clienteDTO);
        Optional<Usuario>usuario=usuarioRepo.findByEmailIgnoreCase(clienteDTO.getEmail());
        if (usuario.isPresent()){
            cliente.setUsuario(usuario.get());
        }
        clienteRepo.save(cliente);

    }

    @Override
    public ClienteDTO update(Long id, ClienteDTO clienteDTO) {
        if (clienteRepo.existsById(id)){
            Cliente cliente=clienteRepo.findById(id).get();
            cliente.setTelefono(clienteDTO.getTelefono());
            cliente.setNombre(clienteDTO.getNombre());
            cliente.setDni(clienteDTO.getDni());
           clienteRepo.save(cliente);
           return mapper.clienteAClinenteDto(cliente);
        }else {
            throw  new UsuarioNoEncontradoException();
        }

    }

    @Override
    public void delete(Long id) {
      if (clienteRepo.existsById(id)){
          clienteRepo.deleteById(id);
      }else {
          throw new UsuarioNoEncontradoException();
      }
    }
}
