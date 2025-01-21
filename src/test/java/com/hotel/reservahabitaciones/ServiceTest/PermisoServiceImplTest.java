package com.hotel.reservahabitaciones.ServiceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import com.hotel.reservahabitaciones.Model.DTOs.PermisoDTO;
import com.hotel.reservahabitaciones.Service.Impl.PermisoServiceImpl;
@Sql(scripts = "/scripts/inicializar_datos_para_permiso_srvc.sql",executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
@SpringBootTest
public class PermisoServiceImplTest {
    //se cambio la base de datos y se puso en create-drop para los test 
    @Autowired
    public PermisoServiceImpl service;

    public PermisoDTO esperado=new PermisoDTO(1L,"CREAR_USUARIO");

    @Test
    void testGetAll(){
        List<PermisoDTO>obtenido=service.getAll();
        assertTrue(obtenido.contains(esperado));
    }
    @Test
    void testGetById(){
        PermisoDTO obtenido=service.getById(1L);
        assertEquals(esperado, obtenido);
    }
    @Test
    void testGetByName(){
        PermisoDTO obtenido=service.getByName("CREAR_USUARIO");
        assertEquals(esperado, obtenido);
    }
    @Test
    void testUpdate(){
        esperado.setNombre("LEER");
        service.update(1L,esperado);
        PermisoDTO obtenido=service.getById(1L);
        assertEquals(esperado,obtenido);
    }
    


}
