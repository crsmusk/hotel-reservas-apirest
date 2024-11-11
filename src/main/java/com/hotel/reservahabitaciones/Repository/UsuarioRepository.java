package com.hotel.reservahabitaciones.Repository;

import com.hotel.reservahabitaciones.Model.Entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    Optional<Usuario>findByEmailIgnoreCase(String email);

}
