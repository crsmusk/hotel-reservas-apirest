package com.hotel.reservahabitaciones.ServiceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cglib.core.Local;
import org.springframework.test.context.jdbc.Sql;

import com.hotel.reservahabitaciones.Model.DTOs.HabitacionDTO;
import com.hotel.reservahabitaciones.Model.DTOs.ReservacionDTO;
import com.hotel.reservahabitaciones.Service.Impl.HabitacionServiceImpl;
import com.hotel.reservahabitaciones.Service.Impl.ReservacionServiceImpl;

@Sql(scripts = "/scripts/inicializar_datos_para_reservas_srvc.sql",executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
@SpringBootTest
public class ReservacionServiceImplTest {
 
    @Autowired
    public ReservacionServiceImpl service;
    @Autowired
    public  HabitacionServiceImpl habitacionService;
    
    ReservacionDTO esperado=new ReservacionDTO(1L,LocalDate.of(2025,01,20),LocalDate.of(2025,01,25) ,1L,List.of(1L));

    @Test
    void testSave(){
        ReservacionDTO reservaEsperada=new ReservacionDTO();
        reservaEsperada.setFechaEntrada(LocalDate.of(2025, 05, 1));
        reservaEsperada.setFechaSalida(LocalDate.of(2025, 05, 7));
        reservaEsperada.setIdCliente(3L);
        reservaEsperada.setIds(List.of(3L));
        service.save(reservaEsperada);
        reservaEsperada.setId(3L);
        ReservacionDTO reservaObtenida=service.getById(3L);

        assertEquals(reservaEsperada, reservaObtenida);
    }
    @Test
    void testGetAll(){
        List<ReservacionDTO>obtenido=service.getAll();
        assertTrue(obtenido.contains(esperado));
    }
    @Test
    void testGetById(){
        ReservacionDTO optenido=service.getById(1L);
        assertEquals(esperado, optenido);
    }
    @Test
    void testGetByOutPutAfterThan(){
       List<ReservacionDTO>obtenido=service.getByInPutAfterThan(LocalDate.now());
       assertTrue(obtenido.contains(esperado));
    }
    @Test
    void testGetByOutPutBeforeThan(){
        List<ReservacionDTO>obtenido=service.getByOutPutBeforeThan(LocalDate.of(2025, 01, 26));
        assertTrue(obtenido.contains(esperado));
    }
    @Test
    void testGetByInPutAfterThan(){
        List<ReservacionDTO>obtenido=service.getByInPutAfterThan(LocalDate.of(2025, 02, 1));
        assertFalse(obtenido.contains(esperado));
        obtenido=service.getByInPutAfterThan(LocalDate.of(2025, 01, 1));
        assertTrue(obtenido.contains(esperado));
    }
    @Test
    void testGetByInputBeforeThan(){
        List<ReservacionDTO>obtenido=service.getByInputBeforeThan(LocalDate.of(2025, 01, 21));
        assertTrue(obtenido.contains(esperado));
    }
    @Test
    void testChangeRoom(){
        esperado.setIds(List.of(3L));
        service.changeRoom(1L, 1L,3L,LocalDate.of(2025, 02, 1));
        ReservacionDTO obtenido=service.getById(1L);
        HabitacionDTO habitacion=habitacionService.getById(1L);
        assertTrue(habitacion.isEstado());
        assertEquals(esperado, obtenido);
    }
    
}
