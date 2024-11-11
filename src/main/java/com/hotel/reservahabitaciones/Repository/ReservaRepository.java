package com.hotel.reservahabitaciones.Repository;

import com.hotel.reservahabitaciones.Model.Entities.Reservacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reservacion,Long>{

    List<Reservacion> findByFechaEntradaGreaterThanEqual(LocalDate fehaEntrada);
    List<Reservacion> findByFechaSalidaGreaterThanEqual(LocalDate fechaSalida);
    List<Reservacion> findByFechaEntradaLessThanEqual(LocalDate fechaEntrada);
    List<Reservacion> findByFechaSalidaLessThanEqual(LocalDate fehaSalida);
}
