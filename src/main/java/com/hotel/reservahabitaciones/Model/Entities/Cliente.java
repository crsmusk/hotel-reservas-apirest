package com.hotel.reservahabitaciones.Model.Entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "clientes")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido;
    private String dni;
    private String telefono;
    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    Usuario usuario;
    @OneToMany(mappedBy = "cliente")
    List<Reservacion>reservaciones;
}
