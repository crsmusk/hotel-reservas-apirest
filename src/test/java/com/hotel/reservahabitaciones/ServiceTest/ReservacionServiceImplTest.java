package com.hotel.reservahabitaciones.ServiceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.List;

import com.hotel.reservahabitaciones.Model.DTOs.salida.ReservacionSimplificadoDto;
import com.hotel.reservahabitaciones.Model.DTOs.salida.HabitacionResumenDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import com.hotel.reservahabitaciones.Model.DTOs.entrada.HabitacionDto;
import com.hotel.reservahabitaciones.Model.DTOs.entrada.ReservacionDto;
import com.hotel.reservahabitaciones.Service.Impl.HabitacionServiceImpl;
import com.hotel.reservahabitaciones.Service.Impl.ReservacionServiceImpl;

@Sql(scripts = "/scripts/inicializar_datos_para_reservas_srvc.sql",executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
@SpringBootTest
@Transactional
public class ReservacionServiceImplTest {
 
    @Autowired
    public ReservacionServiceImpl service;
    @Autowired
    public  HabitacionServiceImpl habitacionService;
    
    // Objeto simplificado esperado según el script SQL
    ReservacionSimplificadoDto esperado = new ReservacionSimplificadoDto(
            1L,
            LocalDate.of(2025,01,20),
            LocalDate.of(2025,01,25),
            "Juan Gómez",
            List.of(new HabitacionResumenDto(1L, 101))
    );

    @Test
    void testSave(){
        ReservacionDto reservaDTO = new ReservacionDto(
                null,
                LocalDate.of(2025, 5, 1),
                LocalDate.of(2025, 5, 7),
                3L,
                List.of(3L)
        );

        service.guardar(reservaDTO);

        List<ReservacionSimplificadoDto> todas = service.obtenerTodos();
        boolean encontro = todas.stream().anyMatch(r ->
                LocalDate.of(2025, 5, 1).equals(r.getFechaEntrada()) &&
                LocalDate.of(2025, 5, 7).equals(r.getFechaSalida()) &&
                "Juan Pérez".equals(r.getNombreCliente()) &&
                r.getHabitaciones().stream().anyMatch(h -> h.getId().equals(3L))
        );

        assertTrue(encontro);
    }

    @Test
    void testGetAll(){
        List<ReservacionSimplificadoDto> obtenido = service.obtenerTodos();
        boolean contiene = obtenido.stream().anyMatch(r -> r.getId().equals(esperado.getId()));
        assertTrue(contiene);
    }

    @Test
    void testGetById(){
        ReservacionSimplificadoDto obtenido = service.obtenerPorId(1L);
        assertNotNull(obtenido);
        assertEquals(esperado.getId(), obtenido.getId());
        assertEquals(esperado.getNombreCliente(), obtenido.getNombreCliente());
    }

    @Test
    void testGetByOutPutAfterThan(){
       List<ReservacionSimplificadoDto> obtenido = service.obtenerPorEntradaDespuesDe(LocalDate.of(2025, 1, 1));
       boolean contiene = obtenido.stream().anyMatch(r -> r.getId().equals(esperado.getId()));
       assertTrue(contiene);
     }

    @Test
    void testGetByOutPutBeforeThan(){
        List<ReservacionSimplificadoDto> obtenido = service.obtenerPorSalidaAntesDe(LocalDate.of(2025, 01, 26));
        boolean contiene = obtenido.stream().anyMatch(r -> r.getId().equals(esperado.getId()));
        assertTrue(contiene);
    }

    @Test
    void testGetByInPutAfterThan(){
        List<ReservacionSimplificadoDto> obtenido = service.obtenerPorEntradaDespuesDe(LocalDate.of(2025, 02, 1));
        boolean contiene = obtenido.stream().anyMatch(r -> r.getId().equals(esperado.getId()));
        assertFalse(contiene);
        
        obtenido = service.obtenerPorEntradaDespuesDe(LocalDate.of(2025, 01, 1));
        contiene = obtenido.stream().anyMatch(r -> r.getId().equals(esperado.getId()));
        assertTrue(contiene);
    }

    @Test
    void testGetByInputBeforeThan(){
        List<ReservacionSimplificadoDto> obtenido = service.obtenerPorEntradaAntesDe(LocalDate.of(2025, 01, 21));
        boolean contiene = obtenido.stream().anyMatch(r -> r.getId().equals(esperado.getId()));
        assertTrue(contiene);
    }

    @Test
    void testChangeRoom(){
        ReservacionSimplificadoDto obtenido = service.cambiarHabitacion(1L, 1L, 3L, LocalDate.of(2025, 02, 1));
        assertNotNull(obtenido);
        
        // La habitacion anterior (1L) deberia estar libre ahora
        HabitacionDto habitacion = habitacionService.obtenerPorId(1L);
        assertTrue(habitacion.isEstado());
        
        assertEquals(LocalDate.of(2025, 02, 1), obtenido.getFechaSalida());
        assertTrue(obtenido.getHabitaciones().stream().anyMatch(h -> h.getId().equals(3L)));
    }

    @Test
    void testUpdateFechaSalidaWithoutConflict(){
        ReservacionSimplificadoDto obtenido = service.actualizarFechaSalida(1L, 1L, LocalDate.of(2025, 1, 26));
        assertNotNull(obtenido);
        assertEquals(LocalDate.of(2025, 1, 26), obtenido.getFechaSalida());
    }

    @Test
    void testUpdateFechaSalidaWithConflict(){
        ReservacionDto reservaFutura = new ReservacionDto(
                null,
                LocalDate.of(2025, 1, 28),
                LocalDate.of(2025, 2, 2),
                2L,
                List.of(1L)
        );
        service.guardar(reservaFutura);

        try {
            service.actualizarFechaSalida(1L, 1L, LocalDate.of(2025, 1, 29));
        } catch (Exception e) {
            assertTrue(e instanceof com.hotel.reservahabitaciones.Exception.Exceptions.HabitacionNoDisponibleException);
            return;
        }
        throw new AssertionError("Se esperaba HabitacionNoDisponibleException");
    }
}


