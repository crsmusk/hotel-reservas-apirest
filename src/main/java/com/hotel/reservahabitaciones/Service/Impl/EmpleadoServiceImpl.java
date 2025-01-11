package com.hotel.reservahabitaciones.Service.Impl;

import com.hotel.reservahabitaciones.Exception.Exceptions.UsuarioNoEncontradoException;
import com.hotel.reservahabitaciones.Mapper.EmpleadoMapper;
import com.hotel.reservahabitaciones.Model.DTOs.EmpleadoDTO;
import com.hotel.reservahabitaciones.Model.Entities.Empleado;
import com.hotel.reservahabitaciones.Repository.EmpleadoRepository;
import com.hotel.reservahabitaciones.Repository.UsuarioRepository;
import com.hotel.reservahabitaciones.Service.Interface.IEmpleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmpleadoServiceImpl implements IEmpleado {

    @Autowired
    EmpleadoRepository empleadoRepo;
    @Autowired
    UsuarioServiceImpl usuarioService;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    EmpleadoMapper mapper;

    @Override
    public List<EmpleadoDTO> getAll() {
        if(empleadoRepo.findAll().isEmpty()){
            throw new UsuarioNoEncontradoException("no hay empleados registrados");
        }else{
            return  mapper.empleadosAEmpladosDto(empleadoRepo.findAll());
        }

    }

    @Override
    public EmpleadoDTO getById(Long id) {
        if (empleadoRepo.existsById(id)){
            return mapper.empleadoAEmpleadoDto(empleadoRepo.findById(id).get());
        }else{
            throw new UsuarioNoEncontradoException("no se encontro al empleado con el id "+id);
        }

    }

    @Override
    public List<EmpleadoDTO> getByName(String nombre) {
        if (empleadoRepo.findByNombreIgnoreCase(nombre).isEmpty()){
            throw new UsuarioNoEncontradoException("no hay empleados registrados con el nombre "+nombre);
        }else{
            return  mapper.empleadosAEmpladosDto(empleadoRepo.findByNombreIgnoreCase(nombre));
        }

    }

    @Override
    public List<EmpleadoDTO> getByLastName(String apellido) {
        if (empleadoRepo.findByApellidoIgnoreCase(apellido).isEmpty()){
            throw new UsuarioNoEncontradoException("no se hay empleados registrados con el apellido "+apellido);
        }else {
            return mapper.empleadosAEmpladosDto(empleadoRepo.findByApellidoIgnoreCase(apellido));
        }

    }

    @Override
    @Transactional
    public void save(EmpleadoDTO empleadoDTO) {
        Empleado empleado=new Empleado();
        empleado.setApellido(empleadoDTO.getApellido());
        empleado.setTelefono(empleadoDTO.getTelefono());
        empleado.setDni(empleadoDTO.getDni());
        empleado.setPuesto(empleadoDTO.getPuesto());
        empleado.setNombre(empleadoDTO.getNombre());
        usuarioService.registerEmployee(empleadoDTO);
        if (usuarioRepository.findByEmailIgnoreCase(empleadoDTO.getEmail()).isPresent()){
            empleado.setUsuario(usuarioRepository.findByEmailIgnoreCase(empleadoDTO.getEmail()).get());
        }
        empleadoRepo.save(empleado);
    }

    @Override
    public EmpleadoDTO update(Long id, EmpleadoDTO empleadoDTO) {
        if (empleadoRepo.existsById(id)){
            Empleado empleado=empleadoRepo.findById(id).get();
            empleado.setApellido(empleadoDTO.getApellido());
            empleado.setTelefono(empleadoDTO.getTelefono());
            empleado.setDni(empleadoDTO.getDni());
            empleado.setPuesto(empleadoDTO.getPuesto());
            empleado.setNombre(empleadoDTO.getNombre());
            empleadoRepo.save(empleado);
            return mapper.empleadoAEmpleadoDto(empleado);
        }else{
            throw new UsuarioNoEncontradoException("no se econtro el empleado con el id "+id);
        }
    }

    @Override
    public void delete(Long id) {
      if (empleadoRepo.existsById(id)){
          empleadoRepo.deleteById(id);
      }else{
          throw new UsuarioNoEncontradoException("no se encontro el empleado con el id "+id);
      }
    }
}
