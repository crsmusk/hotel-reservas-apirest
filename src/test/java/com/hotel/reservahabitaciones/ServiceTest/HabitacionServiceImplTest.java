package com.hotel.reservahabitaciones.ServiceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import com.hotel.reservahabitaciones.Model.DTOs.HabitacionDTO;
import com.hotel.reservahabitaciones.Service.Impl.HabitacionServiceImpl;
@Sql(scripts = "/scripts/inicializar_datos_para_habitacion_srvc.sql",executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
@SpringBootTest
public class HabitacionServiceImplTest {
    //se cambio la base de datos y se puso en create-drop para los test 
    @Autowired
    HabitacionServiceImpl service;
    
    HabitacionDTO esperado=new HabitacionDTO(1L,"Doble","Cerca del ascensor",2,25,BigDecimal.valueOf(150.00).setScale(2),true,"Habitación con vista al mar","Acceso para silla de ruedas");
     //se cargaron varios datos para el testeo
    @Test
    void testGetAll(){
        List<HabitacionDTO>obtenido=service.getAll();
        assertTrue(obtenido.contains(esperado));
    }
    @Test
    void testGetById(){
        HabitacionDTO obtenido=service.getById(1L);
        assertEquals(esperado, obtenido);
    }
    @Test
    void testGetByPreference(){
        List<HabitacionDTO>obtenido=service.getByPreference("Cerca del ascensor");
        List<HabitacionDTO>esperados=List.of(esperado);
        assertEquals(esperados,obtenido);
    }
    @Test
    void testGetByCapacity(){
        List<HabitacionDTO>obtenido=service.getByCapacity(2);
        assertTrue(obtenido.contains(esperado));
    }
    @Test
    void testGetByRoomAvailable(){
        List<HabitacionDTO>obtenido=service.getByRoomAvailable();
        assertTrue(obtenido.contains(esperado));
    }
    @Test
    void testGetByAccesibility(){
        List<HabitacionDTO>obtenido=service.getByAccesibility("Acceso para silla de ruedas");
        List<HabitacionDTO>esperados=List.of(esperado);
        assertEquals(esperados, obtenido);
    }
    
    @Test
    void testGetByPriceGreaterThan(){
        List<HabitacionDTO>obtenido=service.getByPriceGreaterThan(BigDecimal.valueOf(160.00));
        assertFalse(obtenido.contains(esperado));
    }
    @Test
    void testGetByPriceLessThan(){
        List<HabitacionDTO>obtenido=service.getByPriceLessThan(BigDecimal.valueOf(160.00));
        assertTrue(obtenido.contains(esperado));
    }
    @Test
    void testGetByRoomUnaVailable(){
        List<HabitacionDTO>obtenido=service.getByRoomUnaVailable();
        assertFalse(obtenido.contains(esperado));
    }

    @Test
    void testGetBySize(){
        List<HabitacionDTO>obtenido=service.getBySize(25);
        assertTrue(obtenido.contains(esperado));
    }

    @Test
    void tesGetByTypeRoom(){
        List<HabitacionDTO>obtenido=service.getByTypeRoom("Doble");
        List<HabitacionDTO>esperados=List.of(esperado);
        assertEquals(esperados, obtenido);
    }
     
   /* @Test
    void testUpdte(){
        esperado.setCapacidad(26);
        service.update(1L,esperado );
        HabitacionDTO obtenido=service.getById(1L);
        assertEquals(esperado, obtenido);
        
    }*/


}
