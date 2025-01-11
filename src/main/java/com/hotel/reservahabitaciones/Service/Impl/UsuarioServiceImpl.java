package com.hotel.reservahabitaciones.Service.Impl;

import com.hotel.reservahabitaciones.Exception.Exceptions.RolNoEncontradoException;
import com.hotel.reservahabitaciones.Exception.Exceptions.UsuarioNoEncontradoException;
import com.hotel.reservahabitaciones.Mapper.UsuarioMapper;
import com.hotel.reservahabitaciones.Model.DTOs.ClienteDTO;
import com.hotel.reservahabitaciones.Model.DTOs.EmpleadoDTO;
import com.hotel.reservahabitaciones.Model.DTOs.RolDTO;
import com.hotel.reservahabitaciones.Model.DTOs.UsuarioDTO;
import com.hotel.reservahabitaciones.Model.Entities.Rol;
import com.hotel.reservahabitaciones.Model.Entities.Usuario;
import com.hotel.reservahabitaciones.Repository.EmpleadoRepository;
import com.hotel.reservahabitaciones.Repository.RolRepository;
import com.hotel.reservahabitaciones.Repository.UsuarioRepository;
import com.hotel.reservahabitaciones.Service.Interface.IUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioServiceImpl implements IUsuario {

    @Autowired
    UsuarioRepository usuarioRepo;
    @Autowired
    RolRepository rolRepo;
    @Autowired
    UsuarioMapper mapper;

    @Override
    public List<UsuarioDTO> getAll() {
        if (usuarioRepo.findAll().isEmpty()){
            throw new UsuarioNoEncontradoException("no hay usuarios registrados");
        }else {
            return mapper.usuariosAUsuariosDto(usuarioRepo.findAll());
        }
    }

    @Override
    public UsuarioDTO getById(Long id) {
        if (usuarioRepo.existsById(id)){
            return mapper.usuarioAUsuarioDto(usuarioRepo.findById(id).get());
        }else {
            throw new UsuarioNoEncontradoException("no se encotro el usuario con el id "+id);
        }
    }

    @Override
    public UsuarioDTO getByEmail(String email) {
        if (usuarioRepo.findByEmailIgnoreCase(email).isPresent()){
         return mapper.usuarioAUsuarioDto(usuarioRepo.findByEmailIgnoreCase(email).get());
        }else {
            throw new UsuarioNoEncontradoException("no se encotro el usuario con el email "+email);
        }
    }

    @Override
    public void save(UsuarioDTO usuarioDTO) {
        Usuario usuario=new Usuario();
        List<Rol>lista=new ArrayList<>();
        for (String rol:usuarioDTO.getRoles()){
            if (rolRepo.findByNombreRolIgnoreCase(rol).isPresent()){
                lista.add(rolRepo.findByNombreRolIgnoreCase(rol).get());
            }else {
                throw new RolNoEncontradoException("no se encontro el rol con el nombre "+rol);
            }
        }
        usuario.setRoles(lista);
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setPassword(usuarioDTO.getPassword());
        usuarioRepo.save(usuario);
    }

    @Override
    public UsuarioDTO update(Long id, UsuarioDTO usuarioDTO) {
        if (usuarioRepo.existsById(id)){
            Usuario usuario=usuarioRepo.findById(id).get();
            usuario.setEmail(usuarioDTO.getEmail());
            usuario.setPassword(usuarioDTO.getPassword());
            usuarioRepo.save(usuario);
            return mapper.usuarioAUsuarioDto(usuario);
        }else {
            throw new UsuarioNoEncontradoException("no se encotro el usuario con el id "+id);
        }
    }

    @Override
    public void delete(Long id) {
        if (usuarioRepo.existsById(id)){
          usuarioRepo.deleteById(id);
        }else {
            throw new UsuarioNoEncontradoException("no se encotro el usuario con el id "+id);
        }
    }

    @Override
    public void registerCustommer(ClienteDTO clienteDTO) {
        Usuario usuario=new Usuario();
        usuario.setPassword(clienteDTO.getPassword());
        usuario.setEmail(clienteDTO.getEmail());
        if (rolRepo.findByNombreRolIgnoreCase("CUSTOMER").isPresent()){
            usuario.setRoles(List.of(rolRepo.findByNombreRolIgnoreCase("CUSTOMER").get()));
        }else {
            throw new RolNoEncontradoException("no se encontro el rol CUSTOMER");
        }
        usuarioRepo.save(usuario);
    }

    @Override
    public void registerEmployee(EmpleadoDTO empleadoDTO) {
        Usuario usuario=new Usuario();
        usuario.setPassword(empleadoDTO.getPassword());
        usuario.setEmail(empleadoDTO.getEmail());
        if (rolRepo.findByNombreRolIgnoreCase("TRAINEE").isPresent()){
            usuario.setRoles(List.of(rolRepo.findByNombreRolIgnoreCase("TRAINEE").get()));
        }else {
            throw new RolNoEncontradoException("no se encontro el rol TRAINEE");
        }
        usuarioRepo.save(usuario);
    }

    @Override
    public void updatePassword(String email, String contraseñaNueva) {
        if (usuarioRepo.findByEmailIgnoreCase(email).isPresent()){
            Usuario usuario=usuarioRepo.findByEmailIgnoreCase(email).get();
            usuario.setPassword(contraseñaNueva);
        }else{
            throw new UsuarioNoEncontradoException("no se encontro el usuario con el email "+email);
        }
    }

    @Override
    public void UpdateRoles(Long id, List<RolDTO> roles) {
        List<Rol>lista=new ArrayList<>();
        if (usuarioRepo.existsById(id)){
            Usuario usuario=usuarioRepo.findById(id).get();
            for (RolDTO rol:roles){
                if (rolRepo.findByNombreRolIgnoreCase(rol.getNombre()).isPresent()){
                    lista.add(rolRepo.findByNombreRolIgnoreCase(rol.getNombre()).get());
                }else {
                    throw new RolNoEncontradoException("no se econtro el rol con el nombre "+rol.getNombre());
                }
            }
            usuarioRepo.save(usuario);
        }

    }


}
