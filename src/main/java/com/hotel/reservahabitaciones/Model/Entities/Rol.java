package com.hotel.reservahabitaciones.Model.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name ="roles")
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 
    @Column(name = "nombre_rol")
    String nombreRol;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "rol_permiso",
            joinColumns = @JoinColumn(name = "rol_id"),
            inverseJoinColumns = @JoinColumn(name = "permiso_id"))
    private List<Permiso>permisos;
    
}
