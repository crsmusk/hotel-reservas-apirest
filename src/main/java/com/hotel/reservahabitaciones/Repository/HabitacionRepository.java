package com.hotel.reservahabitaciones.Repository;

import com.hotel.reservahabitaciones.Model.Entities.Habitacion;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface HabitacionRepository extends JpaRepository<Habitacion,Long> {

    List<Habitacion> findByTipoHabitacionContainingIgnoreCase(String tipo);
    List<Habitacion> findByPreferenciaContainingIgnoreCase(String preferencia);
    List<Habitacion>findByCapacidadGreaterThanEqual(int capacidad);
    List<Habitacion> findByTamanoGreaterThanEqual(int tamaño);
    List<Habitacion> findByPrecioNocheLessThanEqual(BigDecimal precioMax);
    List<Habitacion> findByPrecioNocheGreaterThanEqual(BigDecimal precio);
    List<Habitacion>findByEstadoTrue();
    List<Habitacion>findByEstadoFalse();
    List<Habitacion>findByAccesibilidadContainingIgnoreCase(String palabra);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT h FROM Habitacion h WHERE h.id IN :ids")
    List<Habitacion> findByIdInWithLock(@Param("ids") List<Long> ids);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT h FROM Habitacion h WHERE h.id = :id")
    Optional<Habitacion> findByIdWithLock(@Param("id") Long id);

    boolean existsByNumeroHabitacion(int numeroHabitacion);
}
