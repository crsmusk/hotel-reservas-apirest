package com.hotel.reservahabitaciones.Model.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String email;
    private String password;
    @Column(name = "is_enable")
    private Boolean isEnable=true;
    @Column(name = "account_no_expired")
    private Boolean accountNoExpired=true;
    @Column(name = "account_no_locked")
    private Boolean accountNoLocked=true;
    @Column(name = "credentil_no_expired")
    private Boolean credentialNoExprired=true;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_rol"
            ,joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id"))
    List<Rol>roles;
}
