package com.hotel.reservahabitaciones.Service.Impl;

import com.hotel.reservahabitaciones.Exception.Exceptions.UsuarioNoEncontradoException;
import com.hotel.reservahabitaciones.Mapper.EmpleadoMapper;
import com.hotel.reservahabitaciones.Model.DTOs.EmpleadoDTO;
import com.hotel.reservahabitaciones.Model.Entities.Empleado;
import com.hotel.reservahabitaciones.Model.Entities.Usuario;
import com.hotel.reservahabitaciones.Repository.EmpleadoRepository;
import com.hotel.reservahabitaciones.Repository.UsuarioRepository;
import com.hotel.reservahabitaciones.Service.Interface.IEmpleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoServiceImpl implements IEmpleado {


    private EmpleadoRepository empleadoRepo;
    @Autowired
    public void setEmpleadoRepositorio(EmpleadoRepository empleadoRepo){
        this.empleadoRepo=empleadoRepo;
    }

    private UsuarioServiceImpl usuarioService;
    @Autowired
    public void setUsuarioServiceImpl(UsuarioServiceImpl usuarioService){
        this.usuarioService=usuarioService;
    }

    private UsuarioRepository usuarioRepo;
    @Autowired
    public void setUsuarioRepository(UsuarioRepository usuarioRepo ){
        this.usuarioRepo=usuarioRepo;
    }

    private EmpleadoMapper mapper;
    @Autowired
    public void setMapper(EmpleadoMapper mapper){
        this.mapper=mapper;
    }

    @Override
    public List<EmpleadoDTO> getAll() {
        if(empleadoRepo.findAll().isEmpty()){
            throw new UsuarioNoEncontradoException();
        }else{
            return  mapper.empleadosAEmpladosDto(empleadoRepo.findAll());
        }

    }

    @Override
    public EmpleadoDTO getById(Long id) {
        Optional<Empleado>empleado=empleadoRepo.findById(id);
        if (empleado.isPresent()){
            return mapper.empleadoAEmpleadoDto(empleado.get());
        }else{
            throw new UsuarioNoEncontradoException();
        }

    }

    @Override
    public List<EmpleadoDTO> getByName(String nombre) {
        if (empleadoRepo.findByNombreIgnoreCase(nombre).isEmpty()){
            throw new UsuarioNoEncontradoException();
        }else{
            return  mapper.empleadosAEmpladosDto(empleadoRepo.findByNombreIgnoreCase(nombre));
        }

    }

    @Override
    public List<EmpleadoDTO> getByLastName(String apellido) {
        if (empleadoRepo.findByApellidoIgnoreCase(apellido).isEmpty()){
            throw new UsuarioNoEncontradoException();
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
        Optional<Usuario>usuario=usuarioRepo.findByEmailIgnoreCase(empleadoDTO.getEmail());
        if (usuario.isPresent()){
            empleado.setUsuario(usuario.get());
        }
        empleadoRepo.save(empleado);
    }

    @Override
    public EmpleadoDTO update(Long id, EmpleadoDTO empleadoDTO) {
        Optional<Empleado>employee=empleadoRepo.findById(id);
        if (employee.isPresent()){
            Empleado empleado=employee.get();
            empleado.setApellido(empleadoDTO.getApellido());
            empleado.setTelefono(empleadoDTO.getTelefono());
            empleado.setDni(empleadoDTO.getDni());
            empleado.setPuesto(empleadoDTO.getPuesto());
            empleado.setNombre(empleadoDTO.getNombre());
            empleadoRepo.save(empleado);
            return mapper.empleadoAEmpleadoDto(empleado);
        }else{
            throw new UsuarioNoEncontradoException();
        }
    }

    @Override
    public void delete(Long id) {
      if (empleadoRepo.existsById(id)){
          empleadoRepo.deleteById(id);
      }else{
          throw new UsuarioNoEncontradoException();
      }
    }
}
