package com.hotel.reservahabitaciones.Repository;

import com.hotel.reservahabitaciones.Model.Entities.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado,Long> {
    List<Empleado> findByNombreIgnoreCase(String nombre);
    List<Empleado>findByApellidoIgnoreCase(String apellido);
}
