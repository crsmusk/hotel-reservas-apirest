package com.hotel.reservahabitaciones.Service.Impl;

import com.hotel.reservahabitaciones.Exception.Exceptions.UsuarioNoEncontradoException;
import com.hotel.reservahabitaciones.Mapper.EmpleadoMapper;
import com.hotel.reservahabitaciones.Model.DTOs.entrada.EmpleadoDto;
import com.hotel.reservahabitaciones.Model.Entities.Empleado;
import com.hotel.reservahabitaciones.Model.Entities.Usuario;
import com.hotel.reservahabitaciones.Repository.EmpleadoRepository;
import com.hotel.reservahabitaciones.Repository.UsuarioRepository;
import com.hotel.reservahabitaciones.Service.Interface.IEmpleado;
import com.hotel.reservahabitaciones.Service.Interface.IUsuario;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoServiceImpl implements IEmpleado {


    private EmpleadoRepository empleadoRepo;
    private IUsuario usuarioService;
    private UsuarioRepository usuarioRepo;
    private EmpleadoMapper mapper;

    public EmpleadoServiceImpl(EmpleadoRepository empleadoRepo, IUsuario usuarioService, UsuarioRepository usuarioRepo, EmpleadoMapper mapper) {
        this.empleadoRepo = empleadoRepo;
        this.usuarioService = usuarioService;
        this.usuarioRepo = usuarioRepo;
        this.mapper = mapper;
    }

    @Override
    public List<EmpleadoDto> obtenerTodos() {
        List<Empleado>empleados=empleadoRepo.findAll();
        if(empleados.isEmpty()){
           return List.of();
        }else{
            return  mapper.empleadosAEmpleadosDto(empleados);
        }

    }

    @Override
    public EmpleadoDto obtenerPorId(Long id) {
        Optional<Empleado>empleado=empleadoRepo.findById(id);
        if (empleado.isPresent()){
            return mapper.empleadoAEmpleadoDto(empleado.get());
        }else{
            throw new UsuarioNoEncontradoException();
        }

    }

    @Override
    public List<EmpleadoDto> obtenerPorNombre(String nombre) {
        List<Empleado>empleados=empleadoRepo.findByNombreIgnoreCase(nombre);
        if (empleados.isEmpty()){
            throw new UsuarioNoEncontradoException();
        }else{
            return  mapper.empleadosAEmpleadosDto(empleados);
        }

    }

    @Override
    public List<EmpleadoDto> obtenerPorApellido(String apellido) {
        List<Empleado>empleados=empleadoRepo.findByApellidoIgnoreCase(apellido);
        if (empleados.isEmpty()){
            throw new UsuarioNoEncontradoException();
        }else {
            return mapper.empleadosAEmpleadosDto(empleados);
        }

    }

    @Override
    @Transactional
    public void guardar(EmpleadoDto EmpleadoDto) {
        Empleado empleado=new Empleado();
        empleado.setApellido(EmpleadoDto.getApellido());
        empleado.setTelefono(EmpleadoDto.getTelefono());
        empleado.setDni(EmpleadoDto.getDni());
        empleado.setPuesto(EmpleadoDto.getPuesto());
        empleado.setNombre(EmpleadoDto.getNombre());
        usuarioService.registrarEmpleado(EmpleadoDto);
        Optional<Usuario>usuario=usuarioRepo.findByEmailIgnoreCase(EmpleadoDto.getEmail());
        if (usuario.isPresent()){
            empleado.setUsuario(usuario.get());
        }
        empleadoRepo.save(empleado);
    }

    @Override
    public EmpleadoDto actualizar(Long id, EmpleadoDto EmpleadoDto) {
        Optional<Empleado>empleado=empleadoRepo.findById(id);
        if (empleado.isPresent()){
            empleado.get().setApellido(EmpleadoDto.getApellido());
            empleado.get().setTelefono(EmpleadoDto.getTelefono());
            empleado.get().setDni(EmpleadoDto.getDni());
            empleado.get().setNombre(EmpleadoDto.getNombre());
            empleado.get().setPuesto(EmpleadoDto.getPuesto());
            empleadoRepo.save(empleado.get());
            return mapper.empleadoAEmpleadoDto(empleado.get());
        }else{
            throw new UsuarioNoEncontradoException();
        }
    }

    @Override
    public void eliminar(Long id) {
      if (empleadoRepo.existsById(id)){
          empleadoRepo.deleteById(id);
      }else{
          throw new UsuarioNoEncontradoException();
      }
    }

    @Override
    public void cambiarPuesto(Long id, String puesto) {
        Optional<Empleado>empleado=empleadoRepo.findById(id);
        if (empleado.isPresent()){
            empleado.get().setPuesto(puesto);
            empleadoRepo.save(empleado.get());
        }else{
            throw new UsuarioNoEncontradoException();
        }
    }
}

