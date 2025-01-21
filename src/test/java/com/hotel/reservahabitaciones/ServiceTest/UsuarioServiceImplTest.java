package com.hotel.reservahabitaciones.ServiceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import com.hotel.reservahabitaciones.Model.DTOs.RolDTO;
import com.hotel.reservahabitaciones.Model.DTOs.UsuarioDTO;
import com.hotel.reservahabitaciones.Service.Impl.UsuarioServiceImpl;
@Sql(scripts = "/scripts/inicializar_datos_para_usuario_srvc.sql",executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
@SpringBootTest
public class UsuarioServiceImplTest {
  
    @Autowired
    public UsuarioServiceImpl servicio;

    public UsuarioDTO esperado=new UsuarioDTO(1L,"usuario1@ejemplo.com","password123",List.of("Admin","Editor"));
    
    @Test
    void testGetAll(){
        List<UsuarioDTO>obtenidos=servicio.getAll();
        assertTrue(obtenidos.contains(esperado));
    }
    @Test
    void testGetById(){
        UsuarioDTO obtenido=servicio.getById(1L);
        assertEquals(esperado, obtenido);
    }
    @Test
    void testGetByEmail(){
        UsuarioDTO obtenido=servicio.getByEmail("usuario1@ejemplo.com");
        assertEquals(esperado, obtenido);
    }
    @Test
    void testUpdateRoles(){
        servicio.UpdateRoles(1L,List.of("Viewer"));
        UsuarioDTO obtenido=servicio.getById(1L);
        assertEquals(esperado, obtenido);
    }
    @Test
    void testUpdate(){
        esperado.setEmail("pepe@example.com");
        servicio.update(1L, esperado);
        UsuarioDTO obtenido=servicio.getById(1L);
        assertEquals(esperado, obtenido);
    }

}
