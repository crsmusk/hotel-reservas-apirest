package com.hotel.reservahabitaciones.Model.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name ="empleados")
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido;
    private String telefono;
    private String puesto;
    private String dni;

    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    Usuario usuario;
}
