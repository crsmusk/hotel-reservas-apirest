package com.hotel.reservahabitaciones.Repository;

import com.hotel.reservahabitaciones.Model.Entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Long> {

    List<Cliente> findByNombreIgnoreCase(String nombre);

    List<Cliente> findByApellidoIgnoreCase(String apellido);

}
