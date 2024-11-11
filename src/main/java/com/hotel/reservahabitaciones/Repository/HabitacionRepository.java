package com.hotel.reservahabitaciones.Repository;

import com.hotel.reservahabitaciones.Model.Entities.Habitacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface HabitacionRepository extends JpaRepository<Habitacion,Long> {

    List<Habitacion> findByTipoHabitacionContainingIgnoreCase(String tipo);
    List<Habitacion> findByPreferenciaContainingIgnoreCase(String preferencia);
    List<Habitacion>findByCapacidadGreaterThanEqual(int capacidad);
    List<Habitacion> findByTamañoGreaterThanEqual(int tamaño);
    List<Habitacion> findByPrecioNocheLessThanEqual(BigDecimal precioMax);
    List<Habitacion> findByPrecioNocheGreaterThanEqual(BigDecimal precio);
    List<Habitacion>findByEstadoTrue();
    List<Habitacion>findByEstadoFalse();
    List<Habitacion>findByAccesibilidadContainingIgnoreCase(String palabra);

}
