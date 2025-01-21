package com.hotel.reservahabitaciones.ServiceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import com.hotel.reservahabitaciones.Model.DTOs.EmpleadoDTO;
import com.hotel.reservahabitaciones.Service.Impl.EmpleadoServiceImpl;
@Sql(scripts = "/scripts/inicializar_datos_para_empleados_srvc.sql",executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
@SpringBootTest
public class EmpleadoServiceImplTest {
    //se cambio la base de datos y se puso en create-drop para los test 
    @Autowired
    EmpleadoServiceImpl service;
    
    EmpleadoDTO esperado=new EmpleadoDTO(1L,"Juan","Pérez","3001234567","Recepcionista","12345678","empleado1@hotel.com","hashed_password");
    @Test
    void testGetAll(){
       List<EmpleadoDTO>optenido=service.getAll();
       List<EmpleadoDTO>esperados=List.of(esperado);
       assertEquals(esperados, optenido);
    }
    @Test
    void testGetById(){
        EmpleadoDTO obtenido=service.getById(1L);
        assertNotNull(obtenido);
        assertEquals(esperado, obtenido);
    }
    @Test
    void testGetByName(){
        List<EmpleadoDTO> obtenido=service.getByName("Juan");
        List<EmpleadoDTO> esperados=List.of(esperado);
        assertEquals(esperados, obtenido);
    }
    @Test
    void testGetByLastName(){
        List<EmpleadoDTO>obtenido=service.getByLastName("Pérez");
        List<EmpleadoDTO>esperados=List.of(esperado);
        assertEquals(esperados, obtenido);
    }
    @Test
    void testUpdate(){
        esperado.setDni("123456789");
        EmpleadoDTO optenido=service.update(1L, esperado);
        assertEquals(esperado, optenido);
    }
}
