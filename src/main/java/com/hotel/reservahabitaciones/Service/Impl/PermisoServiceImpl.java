package com.hotel.reservahabitaciones.Service.Impl;

import com.hotel.reservahabitaciones.Exception.Exceptions.PermisoNoEncontradoException;
import com.hotel.reservahabitaciones.Mapper.PermisoMapper;
import com.hotel.reservahabitaciones.Model.DTOs.entrada.PermisoDto;
import com.hotel.reservahabitaciones.Model.Entities.Permiso;
import com.hotel.reservahabitaciones.Repository.PermisoRepository;
import com.hotel.reservahabitaciones.Service.Interface.IPermiso;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PermisoServiceImpl implements IPermiso {


    private final PermisoRepository permisoRepo;
    private final PermisoMapper mapper;

    public PermisoServiceImpl(PermisoRepository permisoRepo, PermisoMapper mapper) {
        this.permisoRepo = permisoRepo;
        this.mapper = mapper;
    }

    @Override
    public List<PermisoDto> obtenerTodos() {
        List<Permiso> permisos = permisoRepo.findAll();
        if (permisos.isEmpty()) {
            return List.of();
        } else {
            return mapper.permisosAPermisosDto(permisos);
        }
    }

    @Override
    public PermisoDto obtenerPorId(Long id) {
        return permisoRepo.findById(id)
                .map(mapper::permisoAPermisoDto)
                .orElseThrow(() -> new PermisoNoEncontradoException("No se encontró el permiso con el id "+id));
    }

    @Override
    public PermisoDto obtenerPorNombre(String nombre) {
        return permisoRepo.findByNombrePermisoIgnoreCase(nombre)
                .map(mapper::permisoAPermisoDto)
                .orElseThrow(() -> new PermisoNoEncontradoException("No se encontró el permiso con el nombre "+nombre));
    }

    @Override
    public PermisoDto actualizar(Long id, PermisoDto permisoDto) {
        Optional<Permiso> permiso=permisoRepo.findById(id);
        if (permiso.isPresent()){
            permiso.get().setNombrePermiso(permisoDto.getNombre());
            permisoRepo.save(permiso.get());
            return mapper.permisoAPermisoDto(permiso.get());
        }else{
            throw new PermisoNoEncontradoException("No se encontró el permiso con el id "+id);
        }

    }

    @Override
    public PermisoDto guardar(PermisoDto permisoDto) {
        Permiso permiso=new Permiso();
        permiso.setNombrePermiso(permisoDto.getNombre());
        permiso = permisoRepo.save(permiso);
        return mapper.permisoAPermisoDto(permiso);
    }

    @Override
    public void eliminar(Long id) {
        if (permisoRepo.existsById(id)){
            permisoRepo.deleteById(id);
        }else{
            throw new PermisoNoEncontradoException("No se encontró el permiso con el id "+id);
        }
    }
}

