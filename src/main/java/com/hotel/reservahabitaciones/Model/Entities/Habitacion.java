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
@Table(name="habitaciones")
public class Habitacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "tipo_habitacion")
    private String tipoHabitacion;
    private int numeroHabitacion;
    private String preferencia;
    private int capacidad;
    private int tamano;
    private BigDecimal precioNoche;
    private boolean estado;
    private  String descripcion;
    private String accesibilidad;
    @Version
    private Integer version;
}
