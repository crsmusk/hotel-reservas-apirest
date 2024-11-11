package com.hotel.reservahabitaciones.Service.Impl;

import com.hotel.reservahabitaciones.Exception.Exceptions.UsuarioNoEncontradoException;
import com.hotel.reservahabitaciones.Mapper.ClienteMapper;
import com.hotel.reservahabitaciones.Model.DTOs.ClienteDTO;
import com.hotel.reservahabitaciones.Model.Entities.Cliente;
import com.hotel.reservahabitaciones.Repository.ClienteRepository;
import com.hotel.reservahabitaciones.Repository.UsuarioRepository;
import com.hotel.reservahabitaciones.Service.Interface.ICliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClienteServiceImpl implements ICliente {

    @Autowired
    ClienteRepository clienteRepo;
    @Autowired
    UsuarioServiceImpl usuarioService;
    @Autowired
    UsuarioRepository usuarioRepo;
    @Autowired
    ClienteMapper mapper;

    @Override
    public List<ClienteDTO> getAll() {
        if(clienteRepo.findAll().isEmpty()){
            throw new UsuarioNoEncontradoException("no hay clientes registrados");
        }else{
            return mapper.ClientesAClientesDto(clienteRepo.findAll());
        }
    }

    @Override
    public ClienteDTO getById(Long id) {
        if (clienteRepo.existsById(id)){
            return mapper.clientACLinenteDto(clienteRepo.findById(id).get());
        }else{
            throw new UsuarioNoEncontradoException("no se encotro el cliente con el id "+id);
        }
    }

    @Override
    public List<ClienteDTO> getByNombre(String nombre) {
        if(clienteRepo.findByNombreIgnoreCase(nombre).isEmpty()){
            throw new UsuarioNoEncontradoException("no se encontraron clientes con el nomnre "+nombre);
        }else {
            return mapper.ClientesAClientesDto(clienteRepo.findByNombreIgnoreCase(nombre));
        }
    }

    @Override
    public List<ClienteDTO> getByApellido(String apellido) {
        if (clienteRepo.findByApellidoIgnoreCase(apellido).isEmpty()){
            throw new UsuarioNoEncontradoException("no se econtro ningun usuario con el apellido "+apellido);
        }else{
            return mapper.ClientesAClientesDto(clienteRepo.findByApellidoIgnoreCase(apellido));
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
        usuarioService.registrarCliente(clienteDTO);
        if (usuarioRepo.findByEmailIgnoreCase(clienteDTO.getEmail()).isPresent()){
            cliente.setUsuario(usuarioRepo.findByEmailIgnoreCase(clienteDTO.getEmail()).get());
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
           return mapper.clientACLinenteDto(cliente);
        }else {
            throw  new UsuarioNoEncontradoException("no se encontro al cliente con el id "+id);
        }

    }

    @Override
    public void delete(Long id) {
      if (clienteRepo.existsById(id)){
          clienteRepo.deleteById(id);
      }else {
          throw new UsuarioNoEncontradoException("no se encontro el usuario con el id "+id);
      }
    }
}
