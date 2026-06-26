package com.hotel.reservahabitaciones.Securiy;

import com.hotel.reservahabitaciones.Service.SecurityService.UserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
@EnableWebSecurity
@EnableMethodSecurity
@Configuration
public class SecurityConfig{

    @Bean
    public SecurityFilterChain cadenaFiltrosSeguridad(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> {
                          auth.anyRequest().authenticated();
                        }
                )
                .build();
     }

    @Bean
    public AuthenticationManager administradorAutenticacion(AuthenticationConfiguration authenticationConfiguration)throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider proveedorAutenticacion(UserDetailService userDetailService){
        DaoAuthenticationProvider authentication=new DaoAuthenticationProvider();
        authentication.setPasswordEncoder(codificadorContrasena());
        authentication.setUserDetailsService(userDetailService);
        return authentication;
    }

    @Bean
    public PasswordEncoder codificadorContrasena(){
        return NoOpPasswordEncoder.getInstance();
    }

}
