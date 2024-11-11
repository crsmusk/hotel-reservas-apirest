package com.hotel.reservahabitaciones.Repository;

import com.hotel.reservahabitaciones.Model.Entities.Permiso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface permisoRepository extends JpaRepository<Permiso,Long>{
    Optional<Permiso> findByNombrePermisoIgnoreCase(String nombre);
}
