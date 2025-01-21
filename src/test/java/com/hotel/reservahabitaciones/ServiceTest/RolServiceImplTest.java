package com.hotel.reservahabitaciones.ServiceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import com.hotel.reservahabitaciones.Model.DTOs.RolDTO;
import com.hotel.reservahabitaciones.Service.Impl.RolServiceImpl;
@Sql(scripts = "/scripts/inicializar_datos_para_rol_srvc.sql",executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
@SpringBootTest
public class RolServiceImplTest {

    @Autowired
    public RolServiceImpl service;

    public RolDTO esperado=new RolDTO(1L,"Admin",List.of("CREAR_USUARIO","EDITAR_USUARIO"));

    @Test
    void testGetAll(){
        List<RolDTO>obtenido=service.getAll();
        assertTrue(obtenido.contains(esperado));
    }
    @Test
    void testGetById(){
        RolDTO obtenido=service.getById(1L);
        assertEquals(esperado, obtenido);
    }
    @Test
    void testGetByNombre(){
        RolDTO obtenido=service.getByName("Admin");
        assertEquals(esperado, obtenido);
    }
    @Test
    void testUpdatePermissions(){
        esperado.setPermisos(List.of("VER_REPORTES"));
        service.updatePermissions(1L,List.of("VER_REPORTES") );
        RolDTO obtenido=service.getById(1L);
        assertEquals(esperado, obtenido);
    }
    @Test
    void testUpdateName(){
        esperado.setNombre("GERENTE");
        service.updateName(1L, esperado);
        RolDTO obtenido=service.getById(1L);
        assertEquals(esperado, obtenido);
    }
    
}
