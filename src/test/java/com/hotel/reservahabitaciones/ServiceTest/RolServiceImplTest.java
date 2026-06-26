package com.hotel.reservahabitaciones.ServiceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import com.hotel.reservahabitaciones.Model.DTOs.entrada.RolDto;
import com.hotel.reservahabitaciones.Service.Impl.RolServiceImpl;
@Sql(scripts = "/scripts/inicializar_datos_para_rol_srvc.sql",executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
@SpringBootTest
@Transactional
public class RolServiceImplTest {

    @Autowired
    public RolServiceImpl service;

    public RolDto esperado=new RolDto(1L,"Admin",List.of("CREAR_USUARIO","EDITAR_USUARIO"));

    @Test
    void testGetAll(){
        List<RolDto>obtenido=service.obtenerTodos();
        assertTrue(obtenido.contains(esperado));
    }
    @Test
    void testGetById(){
        RolDto obtenido=service.obtenerPorId(1L);
        assertEquals(esperado, obtenido);
    }
    @Test
    void testGetByNombre(){
        RolDto obtenido=service.obtenerPorNombre("Admin");
        assertEquals(esperado, obtenido);
    }
    @Test
    void testUpdatePermissions(){
        RolDto esperadoActualizado = new RolDto(1L, "Admin", List.of("VER_REPORTES"));
        service.actualizarPermisos(1L,List.of("VER_REPORTES") );
        RolDto obtenido=service.obtenerPorId(1L);
        assertEquals(esperadoActualizado, obtenido);
    }
    @Test
    void testUpdateName(){
        RolDto esperadoActualizado = new RolDto(1L, "GERENTE", List.of("CREAR_USUARIO","EDITAR_USUARIO"));
        service.actualizarNombre(1L, esperadoActualizado);
        RolDto obtenido=service.obtenerPorId(1L);
        assertEquals(esperadoActualizado, obtenido);
    }
    
}

