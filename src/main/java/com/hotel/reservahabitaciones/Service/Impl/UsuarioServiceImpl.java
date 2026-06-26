package com.hotel.reservahabitaciones.Service.Impl;

import com.hotel.reservahabitaciones.Exception.Exceptions.RolNoEncontradoException;
import com.hotel.reservahabitaciones.Exception.Exceptions.UsuarioNoEncontradoException;
import com.hotel.reservahabitaciones.Mapper.UsuarioMapper;
import com.hotel.reservahabitaciones.Model.DTOs.entrada.ClienteDto;
import com.hotel.reservahabitaciones.Model.DTOs.entrada.EmpleadoDto;
import com.hotel.reservahabitaciones.Model.DTOs.entrada.UsuarioDto;
import com.hotel.reservahabitaciones.Model.Entities.Rol;
import com.hotel.reservahabitaciones.Model.Entities.Usuario;
import com.hotel.reservahabitaciones.Repository.RolRepository;
import com.hotel.reservahabitaciones.Repository.UsuarioRepository;
import com.hotel.reservahabitaciones.Service.Interface.IUsuario;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements IUsuario {


    private UsuarioRepository usuarioRepo;
    private  RolRepository rolRepo;
    private UsuarioMapper mapper;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepo, RolRepository rolRepo, UsuarioMapper mapper) {
        this.usuarioRepo = usuarioRepo;
        this.rolRepo = rolRepo;
        this.mapper = mapper;
    }

    @Override
    public List<UsuarioDto> obtenerTodos() {
        if (usuarioRepo.findAll().isEmpty()){
           return List.of();
        }else {
            return mapper.usuariosAUsuariosDto(usuarioRepo.findAll());
        }
    }

    @Override
    public UsuarioDto obtenerPorId(Long id) {
        if (usuarioRepo.existsById(id)){
            return mapper.usuarioAUsuarioDto(usuarioRepo.findById(id).get());
        }else {
            throw new UsuarioNoEncontradoException();
        }
    }

    @Override
    public UsuarioDto obtenerPorEmail(String email) {
        if (usuarioRepo.findByEmailIgnoreCase(email).isPresent()){
         return mapper.usuarioAUsuarioDto(usuarioRepo.findByEmailIgnoreCase(email).get());
        }else {
            throw new UsuarioNoEncontradoException();
        }
    }

    @Override
    public void guardar(UsuarioDto UsuarioDto) {
        Usuario usuario=new Usuario();
        List<Rol>lista=new ArrayList<>();
        for (String rol:UsuarioDto.getRoles()){
          lista.add(buscarRol(rol));
        }
        usuario.setRoles(lista);
        usuario.setEmail(UsuarioDto.getEmail());
        usuario.setPassword(UsuarioDto.getPassword());
        usuarioRepo.save(usuario);
    }

    @Override
    public UsuarioDto actualizar(Long id, UsuarioDto UsuarioDto) {
        Optional<Usuario>usuario=usuarioRepo.findById(id);
        if (usuario.isPresent()){
            usuario.get().setEmail(UsuarioDto.getEmail());
            usuario.get().setPassword(UsuarioDto.getPassword());
            usuarioRepo.save(usuario.get());
            return mapper.usuarioAUsuarioDto(usuario.get());
        }else {
            throw new UsuarioNoEncontradoException();
        }
    }

    @Override
    public void eliminar(Long id) {
        if (usuarioRepo.existsById(id)){
          usuarioRepo.deleteById(id);
        }else {
            throw new UsuarioNoEncontradoException();
        }
    }

    @Override
    public void registrarCliente(ClienteDto ClienteDto) {
        Usuario usuario=new Usuario();
        usuario.setPassword(ClienteDto.password());
        usuario.setEmail(ClienteDto.email());
        usuario.setRoles(List.of(buscarRol("CUSTOMER")));
        usuarioRepo.save(usuario);
    }

    @Override
    public void registrarEmpleado(EmpleadoDto EmpleadoDto) {
        Usuario usuario=new Usuario();
        usuario.setPassword(EmpleadoDto.getPassword());
        usuario.setEmail(EmpleadoDto.getEmail());
        usuario.setRoles(List.of(buscarRol("TRAINEE")));
        usuarioRepo.save(usuario);
    }

    @Override
    public void actualizarContrasena(String email, String contrasenaNueva) {
        Optional<Usuario>usuario=usuarioRepo.findByEmailIgnoreCase(email);
        if (usuario.isPresent()){
            usuario.get().setPassword(contrasenaNueva);
            usuarioRepo.save(usuario.get());
        }else{
            throw new UsuarioNoEncontradoException();
        }
    }

    @Override
    public void actualizarRoles(Long id, List<String> roles) {
        List<Rol>lista=new ArrayList<>();
        Optional<Usuario> usuario=usuarioRepo.findById(id);
        if (usuario.isPresent()){
            for (String rol:roles){
               lista.add(buscarRol(rol));
            }
            usuario.get().setRoles(lista);
            usuarioRepo.save(usuario.get());
        }else{
            throw new UsuarioNoEncontradoException();
        }

    }

    private Rol buscarRol(String rol) {
        if (rolRepo.findByNombreRolIgnoreCase(rol).isEmpty()){
            throw new RolNoEncontradoException();
        }
        return rolRepo.findByNombreRolIgnoreCase(rol).get();
    }


}

