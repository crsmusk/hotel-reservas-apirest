package com.hotel.reservahabitaciones.ServiceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import com.hotel.reservahabitaciones.Model.DTOs.entrada.UsuarioDto;
import com.hotel.reservahabitaciones.Service.Impl.UsuarioServiceImpl;
@Sql(scripts = "/scripts/inicializar_datos_para_usuario_srvc.sql",executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
@SpringBootTest
@Transactional
public class UsuarioServiceImplTest {
  
    @Autowired
    public UsuarioServiceImpl servicio;

    private final UsuarioDto esperado=new UsuarioDto(1L,"usuario1@ejemplo.com","password123",List.of("Admin","Editor"));
    
    @Test
    void testGetAll(){
        List<UsuarioDto>obtenidos=servicio.obtenerTodos();
        assertTrue(obtenidos.contains(esperado));
    }
    @Test
    void testGetById(){
        UsuarioDto obtenido=servicio.obtenerPorId(1L);
        assertEquals(esperado, obtenido);
    }
    @Test
    void testGetByEmail(){
        UsuarioDto obtenido=servicio.obtenerPorEmail("usuario1@ejemplo.com");
        assertEquals(esperado, obtenido);
    }
    @Test
    void testUpdateRoles(){
        servicio.actualizarRoles(1L,List.of("Viewer"));
        UsuarioDto esperadoRolesActualizados = new UsuarioDto(1L, "usuario1@ejemplo.com", "password123", List.of("Viewer"));
        UsuarioDto obtenido=servicio.obtenerPorId(1L);
        assertEquals(esperadoRolesActualizados, obtenido);
    }
    @Test
    void testUpdate(){
        UsuarioDto esperadoActualizado = new UsuarioDto(1L, "pepe@example.com", "password123", List.of("Admin","Editor"));
        servicio.actualizar(1L, esperadoActualizado);
        UsuarioDto obtenido=servicio.obtenerPorId(1L);
        assertEquals(esperadoActualizado, obtenido);
    }

}

