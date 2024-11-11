package com.hotel.reservahabitaciones.Service.SecurityService;

import com.hotel.reservahabitaciones.Exception.Exceptions.UsuarioNoEncontradoException;
import com.hotel.reservahabitaciones.Model.Entities.Usuario;
import com.hotel.reservahabitaciones.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailService implements UserDetailsService {
    @Autowired
    UsuarioRepository usuarioRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario=usuarioRepo.findByEmailIgnoreCase(username)
                .orElseThrow(()->new UsuarioNoEncontradoException("no se encontro el usuario con el email  "+username));
        List<SimpleGrantedAuthority>listAuthorities=new ArrayList<>();

        usuario.getRoles().forEach(Rol->listAuthorities.add(new SimpleGrantedAuthority("ROLE_".concat(Rol.getNombreRol()))));

        usuario.getRoles().stream().flatMap(Rol->Rol.getPermisos().stream()).forEach(Permiso->listAuthorities.add(new SimpleGrantedAuthority(Permiso.getNombrePermiso())));

        return new User(usuario.getEmail(),
                usuario.getPassword(),usuario.getIsEnable(),
                usuario.getAccountNoExpired(),usuario.getAccountNoLocked(),
                usuario.getCredentialNoExprired(),listAuthorities);
    }
}
