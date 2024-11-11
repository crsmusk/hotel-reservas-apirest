package com.hotel.reservahabitaciones.Service.Impl;

import com.hotel.reservahabitaciones.Exception.Exceptions.PermisoNoEnctradoException;
import com.hotel.reservahabitaciones.Mapper.PermisoMapper;
import com.hotel.reservahabitaciones.Model.DTOs.PermisoDTO;
import com.hotel.reservahabitaciones.Model.Entities.Permiso;
import com.hotel.reservahabitaciones.Repository.permisoRepository;
import com.hotel.reservahabitaciones.Service.Interface.IPermiso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermisoServiceImpl implements IPermiso {

    @Autowired
    permisoRepository permisoRepo;
    @Autowired
    PermisoMapper mapper;

    @Override
    public List<PermisoDTO> getAll() {
        if (permisoRepo.findAll().isEmpty()){

          throw  new PermisoNoEnctradoException("no se econtraron permisos registrados");
        }else{
            return mapper.permisosAPermisosDto(permisoRepo.findAll());
        }

    }

    @Override
    public PermisoDTO getById(Long id) {
        if(permisoRepo.existsById(id)){
            return mapper.permisoAPermisoDto(permisoRepo.findById(id).get());
        }else{
            throw new PermisoNoEnctradoException("no se encotro el permiso con  el id "+id);
        }
    }

    @Override
    public PermisoDTO getByNombre(String nombre) {
        if (permisoRepo.findByNombrePermisoIgnoreCase(nombre).isPresent()){
            return mapper.permisoAPermisoDto(permisoRepo.findByNombrePermisoIgnoreCase(nombre).get());
        }else{
            throw new PermisoNoEnctradoException("no se encontro el permiso con el nombre "+nombre);
        }

    }

    @Override
    public PermisoDTO update(Long id, PermisoDTO permisoDTO) {
        if (permisoRepo.existsById(id)){
            Permiso permiso=permisoRepo.findById(id).get();
            permiso.setNombrePermiso(permisoDTO.getNombre());
            permisoRepo.save(permiso);
            return mapper.permisoAPermisoDto(permiso);
        }else{
            throw new PermisoNoEnctradoException("no se encontro el permiso con el id");
        }

    }

    @Override
    public void save(PermisoDTO permisoDTO) {
        Permiso permiso=new Permiso();
        permiso.setNombrePermiso(permisoDTO.getNombre());
        permisoRepo.save(permiso);
    }

    @Override
    public void delete(Long id) {
        if (permisoRepo.existsById(id)){
            permisoRepo.deleteById(id);
        }else{
            throw new PermisoNoEnctradoException("no se encontro el permiso con el id");
        }
    }
}
