package com.hotel.reservahabitaciones.Model.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Reservacion {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;
 @Column(name = "fecha_entrada")
 private LocalDate fechaEntrada;
 @Column(name = "fecha_salida")
 private LocalDate fechaSalida;
 @ManyToOne
 @JoinColumn(name = "cliente_id")
 Cliente cliente;

 @ManyToMany
 @JoinTable(name = "reserva_habitacion",
         joinColumns = @JoinColumn(name = "reserva_id"),
         inverseJoinColumns = @JoinColumn(name = "habitacion_id"))
 List<Habitacion>habitacions;
}
