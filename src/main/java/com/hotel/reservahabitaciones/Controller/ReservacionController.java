package com.hotel.reservahabitaciones.Controller;

import com.hotel.reservahabitaciones.Model.DTOs.entrada.ReservacionDto;
import com.hotel.reservahabitaciones.Model.DTOs.salida.ReservacionSimplificadoDto;
import com.hotel.reservahabitaciones.Service.Interface.IReservacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/hotel/reservacion")
public class ReservacionController {


    private final IReservacion reservacionService;
    
    public ReservacionController(IReservacion reservacionService){
        this.reservacionService=reservacionService;
    }

    @GetMapping
    public ResponseEntity<List<ReservacionSimplificadoDto>>obtenerTodos(){
        return new ResponseEntity<>(reservacionService.obtenerTodos(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservacionSimplificadoDto>obtenerPorId(@PathVariable Long id){
        return new ResponseEntity<>(reservacionService.obtenerPorId(id),HttpStatus.OK);
    }

    @GetMapping("/buscar-entradas-antes-de/{fecha}")
    public ResponseEntity<List<ReservacionSimplificadoDto>>obtenerPorEntradaAntesDe(@PathVariable LocalDate fecha){
        return new ResponseEntity<>(reservacionService.obtenerPorEntradaAntesDe(fecha),HttpStatus.OK);
    }
    @GetMapping("/buscar-entradas-despues-de/{fecha}")
    public ResponseEntity<List<ReservacionSimplificadoDto>> obtenerPorEntradaDespuesDe(@PathVariable LocalDate fecha){
       return new ResponseEntity<>(reservacionService.obtenerPorEntradaDespuesDe(fecha),HttpStatus.OK);
    }
    @GetMapping("/buscar-salida-despues-de/{fecha}")
    public ResponseEntity<List<ReservacionSimplificadoDto>>obtenerPorSalidaDespuesDe(@PathVariable LocalDate fecha){
      return new ResponseEntity<>(reservacionService.obtenerPorSalidaDespuesDe(fecha),HttpStatus.OK);
    }
    @GetMapping("/buscar-salida-antes-de/{fecha}")
    public ResponseEntity<List<ReservacionSimplificadoDto>>obtenerPorSalidaAntesDe(@PathVariable LocalDate fecha){
       return new ResponseEntity<>(reservacionService.obtenerPorSalidaAntesDe(fecha),HttpStatus.OK);
    }

    @GetMapping("/proxima-reservacion/{idHabitacion}/{fecha}")
    public ResponseEntity<ReservacionSimplificadoDto> obtenerProximaReservacionPorHabitacion(@PathVariable Long idHabitacion, @PathVariable LocalDate fecha){
        return new ResponseEntity<>(reservacionService.obtenerProximaReservacionPorHabitacion(idHabitacion, fecha), HttpStatus.OK);
    }

    @PutMapping("/Cambiar-habitacion/{id}/{idHabitacionActual}/{idNuevaHabitacion}/{salida}")
    public ResponseEntity<ReservacionSimplificadoDto>cambiarHabitacion(@PathVariable Long id,
                                                    @PathVariable Long idHabitacionActual,
                                                    @PathVariable Long idNuevaHabitacion,
                                                    @PathVariable LocalDate salida){
        return new ResponseEntity<>(reservacionService.cambiarHabitacion(id,idHabitacionActual,idNuevaHabitacion,salida),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void>guardar(@Valid @RequestBody ReservacionDto reservacionDto){
        reservacionService.guardar(reservacionDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/actualizar-salida/{id}/{idHabitacion}/{fecha}")
    public ResponseEntity<ReservacionSimplificadoDto>cambiarFechaSalida(@PathVariable Long id,@PathVariable Long idHabitacion,@PathVariable LocalDate fecha){
        return new ResponseEntity<>(reservacionService.actualizarFechaSalida(id,idHabitacion,fecha),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>eliminar(@PathVariable Long id){
        reservacionService.eliminar(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

