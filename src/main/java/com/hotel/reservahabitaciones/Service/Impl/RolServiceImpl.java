package com.hotel.reservahabitaciones.Service.Impl;

import com.hotel.reservahabitaciones.Exception.Exceptions.PermisoNoEncontradoException;
import com.hotel.reservahabitaciones.Exception.Exceptions.RolNoEncontradoException;
import com.hotel.reservahabitaciones.Mapper.RolMapper;
import com.hotel.reservahabitaciones.Model.DTOs.entrada.RolDto;
import com.hotel.reservahabitaciones.Model.Entities.Permiso;
import com.hotel.reservahabitaciones.Model.Entities.Rol;
import com.hotel.reservahabitaciones.Repository.RolRepository;
import com.hotel.reservahabitaciones.Repository.PermisoRepository;
import com.hotel.reservahabitaciones.Service.Interface.IRol;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RolServiceImpl implements IRol {


    private RolRepository rolRepo;
    private RolMapper mapper;
    private PermisoRepository permisoRepo;

    public RolServiceImpl(RolRepository rolRepo, RolMapper mapper, PermisoRepository permisoRepo) {
        this.rolRepo = rolRepo;
        this.mapper = mapper;
        this.permisoRepo = permisoRepo;
    }

    @Override
    public List<RolDto> obtenerTodos() {
        List<Rol>roles=rolRepo.findAll();
        if(roles.isEmpty()){
            return List.of();
        }else{
            return mapper.rolesARolesDto(roles);
        }

    }

    @Override
    public RolDto obtenerPorId(Long id) {
        return rolRepo.findById(id)
                .map(mapper::rolARolDto)
                .orElseThrow(RolNoEncontradoException::new);
    }

    @Override
    public RolDto obtenerPorNombre(String nombre) {
        return rolRepo.findByNombreRolIgnoreCase(nombre)
                .map(mapper::rolARolDto)
                .orElseThrow(RolNoEncontradoException::new);
    }

    @Override
    public RolDto actualizarNombre(Long id, RolDto RolDto) {
        Optional<Rol>rol=rolRepo.findById(id);
        if (rol.isPresent()){
            rol.get().setNombreRol(RolDto.getNombre().toUpperCase());
            rolRepo.save(rol.get());
            return mapper.rolARolDto(rol.get());
        }else {
            throw new RolNoEncontradoException();
        }
    }

    @Override
    public void guardar(RolDto RolDto) {
      Rol rol=new Rol();
      List<Permiso>lista=new ArrayList<>();
      for (String nombre:RolDto.getPermisos()){
          Permiso permiso = permisoRepo.findByNombrePermisoIgnoreCase(nombre)
                  .orElseThrow(() -> new PermisoNoEncontradoException("no se encontro el permiso con el nombre "+nombre));
          lista.add(permiso);
      }
      rol.setNombreRol(RolDto.getNombre().toUpperCase());
      rol.setPermisos(lista);
      rolRepo.save(rol);
    }

    @Override
    public void eliminar(Long id) {
       if (rolRepo.existsById(id)){
           rolRepo.deleteById(id);
       }else{
           throw new RolNoEncontradoException();
       }
    }

    @Override
    public RolDto actualizarPermisos(Long id, List<String> permisos) {
        Rol rol = rolRepo.findById(id).orElseThrow(RolNoEncontradoException::new);
        List<Permiso>lista=new ArrayList<>();
        for (String p:permisos){
            Permiso permiso = permisoRepo.findByNombrePermisoIgnoreCase(p)
                    .orElseThrow(() -> new PermisoNoEncontradoException("no se encontro el permiso con el nombre "+p));
            lista.add(permiso);
        }
        rol.setPermisos(lista);
        rolRepo.save(rol);
        return mapper.rolARolDto(rol);
    }
}

