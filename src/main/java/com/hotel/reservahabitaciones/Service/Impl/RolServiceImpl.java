package com.hotel.reservahabitaciones.Service.Impl;

import com.hotel.reservahabitaciones.Exception.Exceptions.PermisoNoEnctradoException;
import com.hotel.reservahabitaciones.Exception.Exceptions.RolNoEncontradoException;
import com.hotel.reservahabitaciones.Mapper.PermisoMapper;
import com.hotel.reservahabitaciones.Mapper.RolMapper;
import com.hotel.reservahabitaciones.Model.DTOs.PermisoDTO;
import com.hotel.reservahabitaciones.Model.DTOs.RolDTO;
import com.hotel.reservahabitaciones.Model.Entities.Permiso;
import com.hotel.reservahabitaciones.Model.Entities.Rol;
import com.hotel.reservahabitaciones.Repository.RolRepository;
import com.hotel.reservahabitaciones.Repository.permisoRepository;
import com.hotel.reservahabitaciones.Service.Interface.IRol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RolServiceImpl implements IRol {

    @Autowired
    RolRepository rolRepo;
    @Autowired
    RolMapper mapper;
    @Autowired
    permisoRepository permisoRepo;

    @Override
    public List<RolDTO> getAll() {
        if(rolRepo.findAll().isEmpty()){
            throw new RolNoEncontradoException("no hay roles registrados");
        }else{
            return mapper.rolesARolesDto(rolRepo.findAll());
        }

    }

    @Override
    public RolDTO getById(Long id) {
        if (rolRepo.existsById(id)){
            return mapper.rolARolDto(rolRepo.findById(id).get());
        }else{
            throw new RolNoEncontradoException("no se encontro al rol con el id "+id);
        }
    }

    @Override
    public RolDTO getByNombre(String nombre) {
        if (rolRepo.findByNombreRolIgnoreCase(nombre).isPresent()){
            return mapper.rolARolDto(rolRepo.findByNombreRolIgnoreCase(nombre).get());
        }else{
            throw new RolNoEncontradoException("no se encontro al rol con el nombre "+nombre);
        }
    }

    @Override
    public RolDTO updateNombre(Long id, RolDTO rolDTO) {
        if (rolRepo.existsById(id)){
            Rol rol=rolRepo.findById(id).get();
            rol.setNombreRol(rolDTO.getNombre());
            return mapper.rolARolDto(rol);
        }else {
            throw new RolNoEncontradoException("no se encontro el rol con el id "+id);
        }
    }

    @Override
    public void save(RolDTO rolDTO) {
      Rol rol=new Rol();
      List<Permiso>lista=new ArrayList<>();
      for (String nombre:rolDTO.getPermisos()){
          if (permisoRepo.findByNombrePermisoIgnoreCase(nombre).isPresent()){
              lista.add(permisoRepo.findByNombrePermisoIgnoreCase(nombre).get());
          }else{
              throw new PermisoNoEnctradoException("no se encontro el permiso con el nombre "+nombre);
          }
      }
      rol.setNombreRol(rolDTO.getNombre().toUpperCase());
      rol.setPermisos(lista);
      rolRepo.save(rol);
    }

    @Override
    public void delete(Long id) {
       if (rolRepo.existsById(id)){
           rolRepo.deleteById(id);
       }else{
           throw new RolNoEncontradoException("no se encontro el rol con el id "+id);
       }
    }

    @Override
    public RolDTO actualizarPermisos(Long id, List<String> permisos) {
        if (permisoRepo.existsById(id)){
            Rol rol=rolRepo.findById(id).get();
            List<Permiso>lista=new ArrayList<>();
            for (String p:permisos){
                if (permisoRepo.findByNombrePermisoIgnoreCase(p).isPresent()){
                    lista.add(permisoRepo.findByNombrePermisoIgnoreCase(p).get());
                }else{
                    throw new PermisoNoEnctradoException("no se encontro el permiso con el nombre "+p);
                }
            }
            rol.setPermisos(lista);
            return mapper.rolARolDto(rol);
        }else{
            throw new RolNoEncontradoException("no se encontro el rol con el id "+id);
        }
    }
}
