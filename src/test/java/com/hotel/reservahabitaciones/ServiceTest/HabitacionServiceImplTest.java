package com.hotel.reservahabitaciones.ServiceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import com.hotel.reservahabitaciones.Model.DTOs.entrada.HabitacionDto;
import com.hotel.reservahabitaciones.Service.Impl.HabitacionServiceImpl;
@Sql(scripts = "/scripts/inicializar_datos_para_habitacion_srvc.sql",executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
@SpringBootTest
public class HabitacionServiceImplTest {
    //se cambio la base de datos y se puso en create-drop para los test 
    @Autowired
    HabitacionServiceImpl service;
    
    HabitacionDto esperado=new HabitacionDto(1L,"Doble",101,"Cerca del ascensor",2,25,BigDecimal.valueOf(150.00).setScale(2),true,"Habitación con vista al mar","Acceso para silla de ruedas");
     //se cargaron varios datos para el testeo
    @Test
    void testGetAll(){
        List<HabitacionDto>obtenido=service.obtenerTodos();
        assertTrue(obtenido.contains(esperado));
    }
    @Test
    void testGetById(){
        HabitacionDto obtenido=service.obtenerPorId(1L);
        assertEquals(esperado, obtenido);
    }
    @Test
    void testGetByPreference(){
        List<HabitacionDto>obtenido=service.obtenerPorPreferencia("Cerca del ascensor");
        List<HabitacionDto>esperados=List.of(esperado);
        assertEquals(esperados,obtenido);
    }
    @Test
    void testGetByCapacity(){
        List<HabitacionDto>obtenido=service.obtenerPorCapacidad(2);
        assertTrue(obtenido.contains(esperado));
    }
    @Test
    void testGetByRoomAvailable(){
        List<HabitacionDto>obtenido=service.obtenerHabitacionesDisponibles();
        assertTrue(obtenido.contains(esperado));
    }
    @Test
    void testGetByAccesibility(){
        List<HabitacionDto>obtenido=service.obtenerPorAccesibilidad("Acceso para silla de ruedas");
        List<HabitacionDto>esperados=List.of(esperado);
        assertEquals(esperados, obtenido);
    }
    
    @Test
    void testGetByPriceGreaterThan(){
        List<HabitacionDto>obtenido=service.obtenerPorPrecioMayorQue(BigDecimal.valueOf(160.00));
        assertFalse(obtenido.contains(esperado));
    }
    @Test
    void testGetByPriceLessThan(){
        List<HabitacionDto>obtenido=service.obtenerPorPrecioMenorQue(BigDecimal.valueOf(160.00));
        assertTrue(obtenido.contains(esperado));
    }
    @Test
    void testGetByRoomUnaVailable(){
        List<HabitacionDto>obtenido=service.obtenerHabitacionesNoDisponibles();
        assertFalse(obtenido.contains(esperado));
    }

    @Test
    void testGetBySize(){
        List<HabitacionDto>obtenido=service.obtenerPorTamano(25);
        assertTrue(obtenido.contains(esperado));
    }

    @Test
    void tesGetByTypeRoom(){
        List<HabitacionDto>obtenido=service.obtenerPorTipoHabitacion("Doble");
        List<HabitacionDto>esperados=List.of(esperado);
        assertEquals(esperados, obtenido);
    }
     
   /* @Test
    void testUpdte(){
        esperado.setCapacidad(26);
        service.actualizar(1L,esperado );
        HabitacionDto obtenido=service.obtenerPorId(1L);
        assertEquals(esperado, obtenido);
        
    }*/


}

