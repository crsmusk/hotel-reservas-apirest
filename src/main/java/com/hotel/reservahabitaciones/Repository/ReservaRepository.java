package com.hotel.reservahabitaciones.Repository;

import com.hotel.reservahabitaciones.Model.Entities.Reservacion;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservaRepository extends JpaRepository<Reservacion,Long>{

    List<Reservacion> findByFechaEntradaGreaterThanEqual(LocalDate fehaEntrada);
    List<Reservacion> findByFechaSalidaGreaterThanEqual(LocalDate fechaSalida);
    List<Reservacion> findByFechaEntradaLessThanEqual(LocalDate fechaEntrada);
    List<Reservacion> findByFechaSalidaLessThanEqual(LocalDate fehaSalida);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Reservacion> findFirstByHabitacionesActivasIdAndFechaEntradaGreaterThanOrderByFechaEntradaAsc(
            Long idHabitacion,
            LocalDate fecha);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT r FROM Reservacion r JOIN r.habitacionesActivas h WHERE h.id = :idHabitacion AND r.fechaEntrada <= :fecha AND r.fechaSalida >= :fecha ORDER BY r.fechaEntrada DESC")
    Optional<Reservacion> findOverlappingReservation(@Param("idHabitacion") Long idHabitacion, @Param("fecha") LocalDate fecha);
}
