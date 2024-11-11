package com.hotel.reservahabitaciones.Model.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Habitacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "tipo_habitacion")
    private String tipoHabitacion;
    private String preferencia;
    private int capacidad;
    private int tamaño;
    private BigDecimal precioNoche;
    private boolean estado;
    private  String descripcion;
    private String accesibilidad;

    @ManyToOne
    @JoinColumn(name = "reservacion_id")
    Reservacion reservacion;
}
